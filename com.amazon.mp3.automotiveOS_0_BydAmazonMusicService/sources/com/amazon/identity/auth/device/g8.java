package com.amazon.identity.auth.device;

import android.net.Uri;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class g8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f349a = Arrays.asList("map_major_version", "map_minor_version", "current_device_type", "dsn_override", "map_sw_version", "map_init_version", "map_brazil_version");
    public static final List<String> b = Arrays.asList("directedId", "namespace", "userdata_account", "token_account", "display_name", "userdata_key", "userdata_value", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_value", "device_data_key", "device_data_value", "timestamp_key", "deleted_key", "dirty_key");

    public static Uri a(String str, String str2) {
        return Uri.parse(String.format("content://%s%s", str, str2));
    }
}
