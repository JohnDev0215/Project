package com.telenav.scoutivi.spotify.byd;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;

/* JADX INFO: compiled from: BYDMediaErrorHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends i3.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Resources f5025g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f5026h;

    public j(Application application) {
        super(application);
        this.f5025g = null;
        try {
            Resources resourcesForApplication = application.getPackageManager().getResourcesForApplication("com.spotify.music");
            this.f5025g = resourcesForApplication;
            this.f5026h = resourcesForApplication.getIdentifier("error_network", "string", "com.spotify.music");
        } catch (PackageManager.NameNotFoundException e7) {
            h3.i.b("BYDMediaErrorHandler").e(e7, "getPackageManager", new Object[0]);
        }
    }

    @Override // i3.b
    public boolean q(String str) {
        Resources resources = this.f5025g;
        if (resources == null) {
            return false;
        }
        boolean zEquals = TextUtils.equals(str, resources.getString(this.f5026h, ""));
        h3.i.b("BYDMediaErrorHandler").b("shouldIgnore " + zEquals, new Object[0]);
        return zEquals;
    }
}
