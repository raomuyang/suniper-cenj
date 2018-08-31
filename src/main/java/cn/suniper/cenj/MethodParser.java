package cn.suniper.cenj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Rao Mengnan
 *         on 2018/8/30.
 */
public class MethodParser {
    private String tableName;
    private Method method;
    private Interpreter interpreter;
    private String sql;

    private Parameter[] parameters;

    public MethodParser(Method method) {
        this(method, null);
    }

    public MethodParser(Method method, String tableName) {
        this.method = method;
        this.parameters = method.getParameters();
        this.tableName = tableName;
        ensureTableName();
        init();
    }

    private void ensureTableName() {
        // get table from jpa annotation: find(Entity)
        if (tableName == null && parameters.length == 1) {
            Class<?> klass = method.getParameterTypes()[0];
            Table table = klass.getAnnotation(Table.class);
            if (table != null) {
                tableName = table.name();
            } else {
                Entity entityDefine = klass.getAnnotation(Entity.class);
                if (entityDefine != null) tableName = entityDefine.name();
            }
        }
        if (tableName == null) {
            throw new IllegalArgumentException("Unknown table name");
        }
    }

    private void attachParams() {
        StringBuilder suffix = new StringBuilder();
        int realParamsCount = 0;
        if (interpreter.isNoneArgs()) {

            if (parameters.length == 1) {
                Class<?> hypothesisEntityClass = parameters[0].getType();
                Entity entityDefine = hypothesisEntityClass.getAnnotation(Entity.class);
                if (entityDefine != null) {
                    Field[] fields = hypothesisEntityClass.getDeclaredFields();
                    String id = null;
                    for (Field field : fields) {
                        // inner class
                        if (field.getName().startsWith("this$")) continue;
                        realParamsCount += 1;
                        Column column = field.getAnnotation(Column.class);
                        String columnName = column == null ? field.getName() : column.name();
                        if (field.getAnnotation(Id.class) != null) {
                            id = columnName;
                            continue;
                        }
                        appendQuery(suffix, columnName);
                    }

                    if (id != null) {
                        switch (interpreter.getVerb()) {
                            case Update:
                                suffix.append(" WHERE ").append(id).append(" ").append(Predicate.Equals.symbol).append(" ");
                                break;
                            default:
                                appendQuery(suffix, id);
                        }
                    }

                }
            }
            // else: unsupported generate from parameters

            switch (interpreter.getVerb()) {
                case Update:
                    sql = sql.replace(Interpreter.UPDATE_PARAMS_REPLACE_HOLDER, suffix.toString());
                    break;
                case Insert:
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < realParamsCount; i++) {
                        if (sb.length() > 0) sb.append(",");
                        sb.append("?");
                    }
                    sql = sql.replace(Interpreter.INSERT_PARAMS_NAME_REPLACE_HOLDER, suffix.toString())
                            .replace(Interpreter.INSERT_PARAMS_REPLACE_HOLDER, sb);
                    break;
                default:
                    if (suffix.length() > 0) sql += " WHERE " + suffix;
                    break;
            }

            sql = sql.trim();
        }

    }

    private void init() {
        this.interpreter = new Interpreter(method.getName(), tableName);
        this.sql = interpreter.getSQL();
        attachParams();

    }

    private void appendQuery(StringBuilder sb, String name) {
        switch (interpreter.getVerb()) {
            case Insert:
                if (sb.length() > 0) sb.append(", ");
                sb.append(name);
                break;
            default:
                if (sb.length() > 0) sb.append("AND ");
                sb.append(name).append(" ").append(Predicate.Equals.symbol).append(" ");
        }
    }

    public String getSQL() {
        return sql;
    }

    public static void main(String[] args) {
        System.out.println("{a}".replace("{a}", "b"));
    }
}
