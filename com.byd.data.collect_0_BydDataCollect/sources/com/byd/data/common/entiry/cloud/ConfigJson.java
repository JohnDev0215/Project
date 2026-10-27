package com.byd.data.common.entiry.cloud;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import com.byd.data.common.g.a;
import com.google.gson.u.c;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ConfigJson implements Serializable {

    @c("vehicleCode")
    private int vehicleCode = BYDAutoBodyworkDevice.getInstance(a.i().d()).getAutoType();
}
