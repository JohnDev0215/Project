package com.byd.sing;

import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.SystemProperties;

/* JADX INFO: compiled from: CarUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a() {
        int i = SystemProperties.getInt(BYDAutoSysPropertiesDevice.COUNTRY_CODE, -1);
        a.a("countryCode = " + i);
        return i;
    }
}
