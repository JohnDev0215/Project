package com.dee.app.contacts.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceInfo {
    private static final ContactsLogger LOG = ContactsLogger.getLogger("TAG", DeviceInfo.class);

    public String getUniqueDeviceId(Context context) {
        String string = Hashing.md5().newHasher().putString(getDSN(context), Charsets.UTF_8).hash().toString();
        LOG.d("hash of DSN : " + string);
        return string;
    }

    @SuppressLint({"HardwareIds"})
    private String getDSN(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        LOG.d("DSN : " + string);
        return string == null ? "" : string;
    }
}
