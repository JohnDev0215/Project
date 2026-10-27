package com.android.bluetooth.btservice.storage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class Metadata {
    private String address;
    public boolean is_active_a2dp_device;
    public long last_active_time;
    public boolean migrated = false;
    public ProfilePrioritiesEntity profileConnectionPolicies = new ProfilePrioritiesEntity();
    public CustomizedMetadataEntity publicMetadata = new CustomizedMetadataEntity();
    public int a2dpSupportsOptionalCodecs = -1;
    public int a2dpOptionalCodecsEnabled = -1;

    Metadata(String address) {
        this.address = address;
        int i = MetadataDatabase.sCurrentConnectionNumber;
        MetadataDatabase.sCurrentConnectionNumber = i + 1;
        this.last_active_time = i;
        this.is_active_a2dp_device = true;
    }

    String getAddress() {
        return this.address;
    }

    void setProfileConnectionPolicy(int profile, int connectionPolicy) {
        if (connectionPolicy > 100) {
            connectionPolicy = 100;
        }
        if (profile == 1) {
            this.profileConnectionPolicies.hfp_connection_policy = connectionPolicy;
            return;
        }
        if (profile == 2) {
            this.profileConnectionPolicies.a2dp_connection_policy = connectionPolicy;
            return;
        }
        if (profile == 4) {
            this.profileConnectionPolicies.hid_host_connection_policy = connectionPolicy;
            return;
        }
        if (profile == 5) {
            this.profileConnectionPolicies.pan_connection_policy = connectionPolicy;
            return;
        }
        if (profile == 6) {
            this.profileConnectionPolicies.pbap_connection_policy = connectionPolicy;
            return;
        }
        if (profile != 21) {
            switch (profile) {
                case 9:
                    this.profileConnectionPolicies.map_connection_policy = connectionPolicy;
                    return;
                case 10:
                    this.profileConnectionPolicies.sap_connection_policy = connectionPolicy;
                    return;
                case 11:
                    this.profileConnectionPolicies.a2dp_sink_connection_policy = connectionPolicy;
                    return;
                default:
                    switch (profile) {
                        case 16:
                            this.profileConnectionPolicies.hfp_client_connection_policy = connectionPolicy;
                            return;
                        case 17:
                            this.profileConnectionPolicies.pbap_client_connection_policy = connectionPolicy;
                            return;
                        case 18:
                            this.profileConnectionPolicies.map_client_connection_policy = connectionPolicy;
                            return;
                        default:
                            throw new IllegalArgumentException("invalid profile " + profile);
                    }
            }
        }
        this.profileConnectionPolicies.hearing_aid_connection_policy = connectionPolicy;
    }

    int getProfileConnectionPolicy(int profile) {
        if (profile == 1) {
            return this.profileConnectionPolicies.hfp_connection_policy;
        }
        if (profile == 2) {
            return this.profileConnectionPolicies.a2dp_connection_policy;
        }
        if (profile == 4) {
            return this.profileConnectionPolicies.hid_host_connection_policy;
        }
        if (profile == 5) {
            return this.profileConnectionPolicies.pan_connection_policy;
        }
        if (profile == 6) {
            return this.profileConnectionPolicies.pbap_connection_policy;
        }
        if (profile != 21) {
            switch (profile) {
                case 9:
                    return this.profileConnectionPolicies.map_connection_policy;
                case 10:
                    return this.profileConnectionPolicies.sap_connection_policy;
                case 11:
                    return this.profileConnectionPolicies.a2dp_sink_connection_policy;
                default:
                    switch (profile) {
                        case 16:
                            return this.profileConnectionPolicies.hfp_client_connection_policy;
                        case 17:
                            return this.profileConnectionPolicies.pbap_client_connection_policy;
                        case 18:
                            return this.profileConnectionPolicies.map_client_connection_policy;
                        default:
                            return -1;
                    }
            }
        }
        return this.profileConnectionPolicies.hearing_aid_connection_policy;
    }

    void setCustomizedMeta(int key, byte[] value) {
        switch (key) {
            case 0:
                this.publicMetadata.manufacturer_name = value;
                break;
            case 1:
                this.publicMetadata.model_name = value;
                break;
            case 2:
                this.publicMetadata.software_version = value;
                break;
            case 3:
                this.publicMetadata.hardware_version = value;
                break;
            case 4:
                this.publicMetadata.companion_app = value;
                break;
            case 5:
                this.publicMetadata.main_icon = value;
                break;
            case 6:
                this.publicMetadata.is_untethered_headset = value;
                break;
            case 7:
                this.publicMetadata.untethered_left_icon = value;
                break;
            case 8:
                this.publicMetadata.untethered_right_icon = value;
                break;
            case 9:
                this.publicMetadata.untethered_case_icon = value;
                break;
            case 10:
                this.publicMetadata.untethered_left_battery = value;
                break;
            case 11:
                this.publicMetadata.untethered_right_battery = value;
                break;
            case 12:
                this.publicMetadata.untethered_case_battery = value;
                break;
            case 13:
                this.publicMetadata.untethered_left_charging = value;
                break;
            case 14:
                this.publicMetadata.untethered_right_charging = value;
                break;
            case 15:
                this.publicMetadata.untethered_case_charging = value;
                break;
            case 16:
                this.publicMetadata.enhanced_settings_ui_uri = value;
                break;
        }
    }

    byte[] getCustomizedMeta(int key) {
        switch (key) {
            case 0:
                byte[] value = this.publicMetadata.manufacturer_name;
                return value;
            case 1:
                byte[] value2 = this.publicMetadata.model_name;
                return value2;
            case 2:
                byte[] value3 = this.publicMetadata.software_version;
                return value3;
            case 3:
                byte[] value4 = this.publicMetadata.hardware_version;
                return value4;
            case 4:
                byte[] value5 = this.publicMetadata.companion_app;
                return value5;
            case 5:
                byte[] value6 = this.publicMetadata.main_icon;
                return value6;
            case 6:
                byte[] value7 = this.publicMetadata.is_untethered_headset;
                return value7;
            case 7:
                byte[] value8 = this.publicMetadata.untethered_left_icon;
                return value8;
            case 8:
                byte[] value9 = this.publicMetadata.untethered_right_icon;
                return value9;
            case 9:
                byte[] value10 = this.publicMetadata.untethered_case_icon;
                return value10;
            case 10:
                byte[] value11 = this.publicMetadata.untethered_left_battery;
                return value11;
            case 11:
                byte[] value12 = this.publicMetadata.untethered_right_battery;
                return value12;
            case 12:
                byte[] value13 = this.publicMetadata.untethered_case_battery;
                return value13;
            case 13:
                byte[] value14 = this.publicMetadata.untethered_left_charging;
                return value14;
            case 14:
                byte[] value15 = this.publicMetadata.untethered_right_charging;
                return value15;
            case 15:
                byte[] value16 = this.publicMetadata.untethered_case_charging;
                return value16;
            case 16:
                byte[] value17 = this.publicMetadata.enhanced_settings_ui_uri;
                return value17;
            default:
                return null;
        }
    }

    List<Integer> getChangedCustomizedMeta() {
        List<Integer> list = new ArrayList<>();
        if (this.publicMetadata.manufacturer_name != null) {
            list.add(0);
        }
        if (this.publicMetadata.model_name != null) {
            list.add(1);
        }
        if (this.publicMetadata.software_version != null) {
            list.add(2);
        }
        if (this.publicMetadata.hardware_version != null) {
            list.add(3);
        }
        if (this.publicMetadata.companion_app != null) {
            list.add(4);
        }
        if (this.publicMetadata.main_icon != null) {
            list.add(5);
        }
        if (this.publicMetadata.is_untethered_headset != null) {
            list.add(6);
        }
        if (this.publicMetadata.untethered_left_icon != null) {
            list.add(7);
        }
        if (this.publicMetadata.untethered_right_icon != null) {
            list.add(8);
        }
        if (this.publicMetadata.untethered_case_icon != null) {
            list.add(9);
        }
        if (this.publicMetadata.untethered_left_battery != null) {
            list.add(10);
        }
        if (this.publicMetadata.untethered_right_battery != null) {
            list.add(11);
        }
        if (this.publicMetadata.untethered_case_battery != null) {
            list.add(12);
        }
        if (this.publicMetadata.untethered_left_charging != null) {
            list.add(13);
        }
        if (this.publicMetadata.untethered_right_charging != null) {
            list.add(14);
        }
        if (this.publicMetadata.untethered_case_charging != null) {
            list.add(15);
        }
        if (this.publicMetadata.enhanced_settings_ui_uri != null) {
            list.add(16);
        }
        return list;
    }

    public String toString() {
        return this.address + " {profile connection policy(" + this.profileConnectionPolicies + "), optional codec(support=" + this.a2dpSupportsOptionalCodecs + "|enabled=" + this.a2dpOptionalCodecsEnabled + "), custom metadata(" + this.publicMetadata + ")}";
    }
}
