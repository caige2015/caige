package song;

import java.io.Serializable;
import java.util.Date;

/**
 * @author song
 */
public class AppPkgTO implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer fileSize;

    private String url;

    private String createdBy;

    private String md5;

    private String sha256;

    private String memo;

    private String versionName;

    private Integer versionCode;

    private Integer os;

    private String packageName;

    private String processName;

    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize=fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url=url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy=createdBy;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5=md5;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256=sha256;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo=memo;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName=versionName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode=versionCode;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os=os;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName=packageName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName=processName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate=createdDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}