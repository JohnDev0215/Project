package com.android.bluetooth.btservice.storage;

/* JADX INFO: loaded from: classes.dex */
class ProfilePrioritiesEntity {
    public int a2dp_connection_policy = -1;
    public int a2dp_sink_connection_policy = -1;
    public int hfp_connection_policy = -1;
    public int hfp_client_connection_policy = -1;
    public int hid_host_connection_policy = -1;
    public int pan_connection_policy = -1;
    public int pbap_connection_policy = -1;
    public int pbap_client_connection_policy = -1;
    public int map_connection_policy = -1;
    public int sap_connection_policy = -1;
    public int hearing_aid_connection_policy = -1;
    public int map_client_connection_policy = -1;

    ProfilePrioritiesEntity() {
    }

    public String toString() {
        return "A2DP=" + this.a2dp_connection_policy + "|A2DP_SINK=" + this.a2dp_sink_connection_policy + "|HEADSET=" + this.hfp_connection_policy + "|HEADSET_CLIENT=" + this.hfp_client_connection_policy + "|HID_HOST=" + this.hid_host_connection_policy + "|PAN=" + this.pan_connection_policy + "|PBAP=" + this.pbap_connection_policy + "|PBAP_CLIENT=" + this.pbap_client_connection_policy + "|MAP=" + this.map_connection_policy + "|MAP_CLIENT=" + this.map_client_connection_policy + "|SAP=" + this.sap_connection_policy + "|HEARING_AID=" + this.hearing_aid_connection_policy;
    }
}
