package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class w3 {
    public static final String b = "w3";
    public static final Uri c = Uri.parse("content://com.amazon.appmanager.preload.device_info.provider");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f643a;

    public w3(Context context) {
        this.f643a = context.getApplicationContext();
    }
}
