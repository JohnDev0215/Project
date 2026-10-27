package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class IdentificationInfo {
    private String currentLanguage;
    private String firmwareVersion;
    private String hardwareVersion;
    private String manufacturer;
    private String modelIdentifier;
    private String name;
    private String serialNumber;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelIdentifier() {
        return this.modelIdentifier;
    }

    public void setModelIdentifier(String modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getCurrentLanguage() {
        return this.currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public String toString() {
        return "IdentificationInfo{name='" + this.name + "', modelIdentifier='" + this.modelIdentifier + "', manufacturer='" + this.manufacturer + "', serialNumber='" + this.serialNumber + "', firmwareVersion='" + this.firmwareVersion + "', hardwareVersion='" + this.hardwareVersion + "', currentLanguage='" + this.currentLanguage + "'}";
    }
}
