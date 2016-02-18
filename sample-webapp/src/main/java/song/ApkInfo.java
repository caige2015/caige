package song;
import java.io.File;

import net.dongliu.apk.parser.ApkParser;
import net.dongliu.apk.parser.bean.ApkMeta;

/**
 * 读取apk信息工具类
 * @author song
 */
public class ApkInfo {

    public static AppPkgTO getApkInfo(String filePath) {
        AppPkgTO appPkgTO=new AppPkgTO();
        try (ApkParser apkParser=new ApkParser(new File(filePath))) {
            ApkMeta apkMeta=apkParser.getApkMeta();
            appPkgTO.setPackageName(apkMeta.getPackageName());
            appPkgTO.setVersionCode(Integer.parseInt(apkMeta.getVersionCode().toString()));
            appPkgTO.setVersionName(apkMeta.getVersionName());
            File f=new File(filePath);
            appPkgTO.setFileSize(Integer.parseInt(f.length() + ""));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return appPkgTO;
    }

    public static void main(String args[]) {
        AppPkgTO to=getApkInfo("D:/apps/fengyun_1.0.0.0_anqu_67892_100066.apk");
        System.out.println(to);
    }
}
