 package song.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    public static void main(String[] args) throws Exception {
        if(args.length < 1){
            System.out.println("arguments:annotated");
            System.exit(0);
        }
        for(String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(null == dbTable){
                System.out.println("NO ANNOTATION !");
                continue;
            }
            String tableName = dbTable.name();
            List<String> columnsList = new ArrayList<String>();
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
                for(Field filed : cl.getDeclaredFields()){
                    String columnName = null;
                    Annotation[] anns = filed.getDeclaredAnnotations();
                    if(anns.length < 1){
                        continue;
                    }
                    if(anns[0] instanceof SQLInteger){
                        SQLInteger sqlInt = (SQLInteger)anns[0];
                        if(sqlInt.name().length() < 1){
                            columnName = filed.getName().toUpperCase();
                        }else {
                            columnName = sqlInt.name();
                        }
                        columnsList.add(columnName+" INT"+getConstraints(sqlInt.constrains()));
                    }
                    if(anns[0] instanceof SQLString){
                        SQLString sqlString = (SQLString)anns[0];
                        if(sqlString.name().length() < 1){
                            columnName = filed.getName().toUpperCase();
                        }else {
                            columnName = sqlString.name();
                        }
                       columnsList.add(columnName+" VARCHAR("+sqlString.value()+")" + getConstraints(sqlString.constrains()));
                       StringBuilder sb = new StringBuilder("CREATE TABLE "+tableName+"(");
                       for(String columnS : columnsList){
                           sb.append("\n     "+ columnS + ",");
                           String tableCreate = sb.substring(0,sb.length()-1) + ");";
                           System.out.println("Table for sql"+className+" is "+tableCreate);
                       }
                    }
                }
                
            }
        }
    }
    private static String getConstraints(Constrains con){
        String constrains = "";
        if(!con.allowNull())
            constrains += " NOT NULL";
        if(con.primaryKey())
            constrains += " PRIMARY KEY";
        if(con.unique()){
            constrains += " UNIQUE";
        }
        return constrains;
    }
}






