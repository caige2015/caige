package song.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserCaseTracker {

     public static void trackCase(List<Integer> userCases, Class<?> cl){
         for(Method m : cl.getDeclaredMethods()){
             UseCase uc = m.getAnnotation(UseCase.class);
             if(null!=uc){
                 System.out.println(uc.id()+"-----"+uc.description());
             }
             userCases.remove(new Integer(uc.id()));
         }
         
         for(int i : userCases){
             System.out.println("i:"+i);
         }
         
     }
     
     public static void main(String[] args) {
        List<Integer> userCases = new ArrayList<>();
        Collections.addAll(userCases, 47,48,49);
        trackCase(userCases , UserCaseImpl.class);
    }
}
