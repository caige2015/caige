package song.annotation.bean;

import java.io.PrintWriter;
import java.util.ArrayList;

import song.annotation.common.ExtracInterface;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;


public class InterfaceExtractorProcessor implements AnnotationProcessor{

    private final AnnotationProcessorEnvironment env;
    private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<MethodDeclaration>();
    public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env){
        this.env = env;
    }
    
    @Override
    public void process() {
        for(TypeDeclaration typeDecl: env.getSpecifiedTypeDeclarations()){
            ExtracInterface annot = typeDecl.getAnnotation(ExtracInterface.class);
            if(annot == null){
                break;
            }
            for(MethodDeclaration m : typeDecl.getMethods()){
                if(m.getModifiers().contains(Modifier.PUBLIC) &&
                        !(m.getModifiers().contains(Modifier.STATIC))){
                    interfaceMethods.add(m);
                }
            }
            if(interfaceMethods.size()>0){
                try {
                    PrintWriter writer = env.getFiler().createSourceFile(annot.value());
                    writer.print("package "+typeDecl.getPackage().getQualifiedName()+";");
                    writer.print("public interface "+annot.value()+" {");
                    for(MethodDeclaration m : interfaceMethods){
                        writer.print(" public ");
                        writer.print(m.getReturnType()+" ");
                        writer.print(m.getSimpleName()+" (");
                        int i = 0;
                        for(ParameterDeclaration parm : m.getParameters()){
                            writer.print(parm.getType()+" "+parm.getSimpleName());
                            if(++i<m.getParameters().size()){
                                writer.print(".");
                            }
                        }
                        writer.print("");
                    }
                    writer.print("}");
                    writer.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
