package com.android.bluetooth.btservice.storage;

/* JADX INFO: loaded from: classes.dex */
class CustomizedMetadataEntity {
    public byte[] companion_app;
    public byte[] enhanced_settings_ui_uri;
    public byte[] hardware_version;
    public byte[] is_untethered_headset;
    public byte[] main_icon;
    public byte[] manufacturer_name;
    public byte[] model_name;
    public byte[] software_version;
    public byte[] untethered_case_battery;
    public byte[] untethered_case_charging;
    public byte[] untethered_case_icon;
    public byte[] untethered_left_battery;
    public byte[] untethered_left_charging;
    public byte[] untethered_left_icon;
    public byte[] untethered_right_battery;
    public byte[] untethered_right_charging;
    public byte[] untethered_right_icon;

    CustomizedMetadataEntity() {
    }

    public String toString() {
        return "manufacturer_name=" + metadataToString(this.manufacturer_name) + "|model_name=" + metadataToString(this.model_name) + "|software_version=" + metadataToString(this.software_version) + "|hardware_version=" + metadataToString(this.hardware_version) + "|companion_app=" + metadataToString(this.companion_app) + "|main_icon=" + metadataToString(this.main_icon) + "|is_untethered_headset=" + metadataToString(this.is_untethered_headset) + "|untethered_left_icon=" + metadataToString(this.untethered_left_icon) + "|untethered_right_icon=" + metadataToString(this.untethered_right_icon) + "|untethered_case_icon=" + metadataToString(this.untethered_case_icon) + "|untethered_left_battery=" + metadataToString(this.untethered_left_battery) + "|untethered_right_battery=" + metadataToString(this.untethered_right_battery) + "|untethered_case_battery=" + metadataToString(this.untethered_case_battery) + "|untethered_left_charging=" + metadataToString(this.untethered_left_charging) + "|untethered_right_charging=" + metadataToString(this.untethered_right_charging) + "|untethered_case_charging=" + metadataToString(this.untethered_case_charging) + "|enhanced_settings_ui_uri=" + metadataToString(this.enhanced_settings_ui_uri);
    }

    private String metadataToString(byte[] metadata) {
        if (metadata == null) {
            return null;
        }
        return new String(metadata);
    }
}
