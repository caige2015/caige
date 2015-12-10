package song.annotation.bean;

import song.annotation.Constrains;
import song.annotation.DBTable;
import song.annotation.SQLInteger;
import song.annotation.SQLString;

@DBTable(name = "member")
public class Member {

    @SQLString(30)String firstName;
    @SQLString(30)String lastName;
    @SQLInteger Integer age;
    @SQLString(value=30,constrains=@Constrains(primaryKey=true))
    String handle;
    static int memberCount;
    public String getHandle(){
         return handle;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public Integer getAge(){
        return age;
    }
    public String toString(){
        return handle;
    }
}



