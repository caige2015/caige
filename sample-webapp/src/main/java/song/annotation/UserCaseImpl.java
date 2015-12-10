package song.annotation;


public class UserCaseImpl {

    @UseCase(id=47,description="sdfsfsd")
    public boolean validatePassword(String password){
        return (password.matches("\\w\\d\\w*"));
    }
    
}
