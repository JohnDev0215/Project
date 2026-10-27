package ts.car.diag.bean;

import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class GwMapBean {
    private String areaCode;
    private String areaPath;
    private String currentCountryCode;
    private List<AreaBean> currentMapCountryCodeConfig;
    private List<AreaBean> currentNotBurningMap;
    private String currentPackageSizeCommand;
    private String mapAreaCode;
    private String mapDataVersion;
    private String mapPackageName;
    private int mapSize;
    private String zone;

    public void clearData() {
        this.currentCountryCode = "";
        this.mapPackageName = "";
        this.currentMapCountryCodeConfig = null;
        this.areaCode = "";
        this.mapDataVersion = "";
        this.mapAreaCode = "";
        this.mapSize = 0;
        this.currentPackageSizeCommand = null;
        this.areaPath = null;
        this.zone = "";
        this.currentNotBurningMap = null;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public String getAreaPath() {
        return this.areaPath;
    }

    public String getCurrentCountryCode() {
        return this.currentCountryCode;
    }

    public List<AreaBean> getCurrentMapCountryCodeConfig() {
        return this.currentMapCountryCodeConfig;
    }

    public List<AreaBean> getCurrentNotBurningMap() {
        return this.currentNotBurningMap;
    }

    public String getCurrentPackageSizeCommand() {
        return this.currentPackageSizeCommand;
    }

    public String getMapAreaCode() {
        return this.mapAreaCode;
    }

    public String getMapDataVersion() {
        return this.mapDataVersion;
    }

    public String getMapPackageName() {
        return this.mapPackageName;
    }

    public int getMapSize() {
        return this.mapSize;
    }

    public String getZone() {
        return this.zone;
    }

    public void setAreaCode(String str) {
        this.areaCode = str;
    }

    public void setAreaPath(String str) {
        this.areaPath = str;
    }

    public void setCurrentCountryCode(String str) {
        this.currentCountryCode = str;
    }

    public void setCurrentMapCountryCodeConfig(List<AreaBean> list) {
        this.currentMapCountryCodeConfig = list;
    }

    public void setCurrentNotBurningMap(List<AreaBean> list) {
        this.currentNotBurningMap = list;
    }

    public void setCurrentPackageSizeCommand(String str) {
        this.currentPackageSizeCommand = str;
    }

    public void setMapAreaCode(String str) {
        this.mapAreaCode = str;
    }

    public void setMapDataVersion(String str) {
        this.mapDataVersion = str;
    }

    public void setMapPackageName(String str) {
        this.mapPackageName = str;
    }

    public void setMapSize(int i) {
        this.mapSize = i;
    }

    public void setZone(String str) {
        this.zone = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GwMapBean{");
        stringBuffer.append("currentCountryCode='");
        stringBuffer.append(this.currentCountryCode);
        stringBuffer.append('\'');
        stringBuffer.append(", mapPackageName='");
        stringBuffer.append(this.mapPackageName);
        stringBuffer.append('\'');
        stringBuffer.append(", currentMapCountryCodeConfig=");
        stringBuffer.append(this.currentMapCountryCodeConfig);
        stringBuffer.append(", currentPackageSizeCommand='");
        stringBuffer.append(this.currentPackageSizeCommand);
        stringBuffer.append('\'');
        stringBuffer.append(", areaPath='");
        stringBuffer.append(this.areaPath);
        stringBuffer.append('\'');
        stringBuffer.append(", mapDataVersion='");
        stringBuffer.append(this.mapDataVersion);
        stringBuffer.append('\'');
        stringBuffer.append(", areaCode='");
        stringBuffer.append(this.areaCode);
        stringBuffer.append('\'');
        stringBuffer.append(", mapAreaCode='");
        stringBuffer.append(this.mapAreaCode);
        stringBuffer.append('\'');
        stringBuffer.append(", mapSize=");
        stringBuffer.append(this.mapSize);
        stringBuffer.append(", zone=");
        stringBuffer.append(this.zone);
        stringBuffer.append(", currentNotBurningMap=");
        stringBuffer.append(this.currentNotBurningMap);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
