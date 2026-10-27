package ts.car.diag.bean;

import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class MapCountryCodeConfig {
    private List<AreaBean> allArea;
    private String areaPath;
    private List<AreaBean> notBurningMap;
    private String packageName;
    private String packageSizeCommand;
    private String zone;

    public List<AreaBean> getAllArea() {
        return this.allArea;
    }

    public String getAreaPath() {
        return this.areaPath;
    }

    public List<AreaBean> getNotBurningMap() {
        return this.notBurningMap;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPackageSizeCommand() {
        return this.packageSizeCommand;
    }

    public String getZone() {
        return this.zone;
    }

    public void setAllArea(List<AreaBean> list) {
        this.allArea = list;
    }

    public void setAreaPath(String str) {
        this.areaPath = str;
    }

    public void setNotBurningMap(List<AreaBean> list) {
        this.notBurningMap = list;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setPackageSizeCommand(String str) {
        this.packageSizeCommand = str;
    }

    public void setZone(String str) {
        this.zone = str;
    }

    public String toString() {
        return "MapCountryCodeConfig{packageName='" + this.packageName + "', areaPath='" + this.areaPath + "', packageSizeCommand='" + this.packageSizeCommand + "', zone='" + this.zone + "', allArea=" + this.allArea + ", notBurningMap=" + this.notBurningMap + '}';
    }
}
