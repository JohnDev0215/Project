package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import defpackage.AbstractBinderC0078Eg;
import defpackage.AbstractC0042Cg;
import defpackage.AbstractC0329Sg;
import defpackage.BinderC0615cg;
import defpackage.CallableC0006Ag;
import defpackage.CallableC1776xg;
import defpackage.CallableC1831yg;
import defpackage.CallableC1886zg;
import defpackage.InterfaceC0559bg;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FlagProviderImpl extends AbstractBinderC0078Eg {
    public boolean a = false;
    public SharedPreferences b;

    @Override // defpackage.InterfaceC0096Fg
    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        if (!this.a) {
            return z;
        }
        SharedPreferences sharedPreferences = this.b;
        Boolean boolValueOf = Boolean.valueOf(z);
        try {
            boolValueOf = (Boolean) AbstractC0329Sg.a(new CallableC1776xg(sharedPreferences, str, boolValueOf));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
        }
        return boolValueOf.booleanValue();
    }

    @Override // defpackage.InterfaceC0096Fg
    public int getIntFlagValue(String str, int i, int i2) {
        if (!this.a) {
            return i;
        }
        SharedPreferences sharedPreferences = this.b;
        Integer numValueOf = Integer.valueOf(i);
        try {
            numValueOf = (Integer) AbstractC0329Sg.a(new CallableC1831yg(sharedPreferences, str, numValueOf));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
        }
        return numValueOf.intValue();
    }

    @Override // defpackage.InterfaceC0096Fg
    public long getLongFlagValue(String str, long j, int i) {
        if (!this.a) {
            return j;
        }
        SharedPreferences sharedPreferences = this.b;
        Long lValueOf = Long.valueOf(j);
        try {
            lValueOf = (Long) AbstractC0329Sg.a(new CallableC1886zg(sharedPreferences, str, lValueOf));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
        }
        return lValueOf.longValue();
    }

    @Override // defpackage.InterfaceC0096Fg
    public String getStringFlagValue(String str, String str2, int i) {
        if (!this.a) {
            return str2;
        }
        try {
            return (String) AbstractC0329Sg.a(new CallableC0006Ag(this.b, str, str2));
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("FlagDataUtils", strValueOf.length() != 0 ? "Flag value not available, returning default: ".concat(strValueOf) : new String("Flag value not available, returning default: "));
            return str2;
        }
    }

    @Override // defpackage.InterfaceC0096Fg
    public void init(InterfaceC0559bg interfaceC0559bg) {
        Context context = (Context) BinderC0615cg.w(interfaceC0559bg);
        if (this.a) {
            return;
        }
        try {
            this.b = AbstractC0042Cg.a(context.createPackageContext("com.google.android.gms", 0));
            this.a = true;
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            Log.w("FlagProviderImpl", strValueOf.length() != 0 ? "Could not retrieve sdk flags, continuing with defaults: ".concat(strValueOf) : new String("Could not retrieve sdk flags, continuing with defaults: "));
        }
    }
}
