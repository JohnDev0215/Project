package ts.car.service.locationext.data;

/* JADX INFO: loaded from: classes2.dex */
public class GnssHwInfo {
    private String mManufacturer;
    private String mModel;
    private String mVersion;

    public GnssHwInfo(String version, String manufacturer, String model) {
        this.mVersion = version;
        this.mModel = model;
        this.mManufacturer = manufacturer;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }
}
