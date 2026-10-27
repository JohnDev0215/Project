package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import p.a23;
import p.d33;
import p.g03;
import p.g13;
import p.j23;
import p.rz2;
import p.tz2;
import p.uz2;
import p.wc0;
import p.wz2;
import p.yz2;
import p.z03;
import p.zz2;

/* JADX INFO: loaded from: classes.dex */
public final class a implements d33 {
    public static final tz2 g = new tz2("AssetPackServiceImpl", 0);
    public static final Intent h = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");
    public final String a;
    public final g13 b;
    public final a23 c;
    public final g03 d;
    public final g03 e;
    public final AtomicBoolean f = new AtomicBoolean();

    public a(Context context, g13 g13Var, a23 a23Var) {
        this.a = context.getPackageName();
        this.b = g13Var;
        this.c = a23Var;
        boolean zA = z03.a(context);
        tz2 tz2Var = g;
        if (zA) {
            Context applicationContext = context.getApplicationContext();
            applicationContext = applicationContext == null ? context : applicationContext;
            Intent intent = h;
            this.d = new g03(applicationContext, tz2Var, "AssetPackService", intent);
            Context applicationContext2 = context.getApplicationContext();
            this.e = new g03(applicationContext2 != null ? applicationContext2 : context, tz2Var, "AssetPackService-keepAlive", intent);
        }
        tz2Var.b("AssetPackService initiated.", new Object[0]);
    }

    public static Bundle h() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11003);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        bundle.putIntegerArrayList("supported_patch_formats", arrayList2);
        return bundle;
    }

    public static wc0 i() {
        g.c("onError(%d)", -11);
        AssetPackException assetPackException = new AssetPackException(-11);
        wc0 wc0Var = new wc0(3);
        wc0Var.g(assetPackException);
        return wc0Var;
    }

    public static /* bridge */ /* synthetic */ Bundle k(Map map) {
        Bundle bundleH = h();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        bundleH.putParcelableArrayList("installed_asset_module", arrayList);
        return bundleH;
    }

    @Override // p.d33
    public final void a(int i) {
        g03 g03Var = this.d;
        if (g03Var == null) {
            throw new zzck("The Play Store app is not installed or is an unofficial version.", i);
        }
        g.e("notifySessionFailed", new Object[0]);
        j23 j23Var = new j23();
        g03Var.b(new yz2(this, j23Var, i, j23Var), j23Var);
    }

    @Override // p.d33
    public final wc0 b(HashMap map) {
        g03 g03Var = this.d;
        if (g03Var == null) {
            return i();
        }
        g.e("syncPacks", new Object[0]);
        j23 j23Var = new j23();
        g03Var.b(new rz2(this, j23Var, map, j23Var, 1), j23Var);
        return j23Var.a;
    }

    @Override // p.d33
    public final wc0 c(int i, String str, int i2, String str2) {
        g03 g03Var = this.d;
        if (g03Var == null) {
            return i();
        }
        g.e("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i2), Integer.valueOf(i));
        j23 j23Var = new j23();
        g03Var.b(new uz2(this, j23Var, i, str, str2, i2, j23Var, 1), j23Var);
        return j23Var.a;
    }

    @Override // p.d33
    public final void d(int i, String str) {
        j(str, i, 10);
    }

    @Override // p.d33
    public final synchronized void e() {
        int i = 0;
        if (this.e == null) {
            g.f("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        tz2 tz2Var = g;
        tz2Var.e("keepAlive", new Object[0]);
        if (!this.f.compareAndSet(false, true)) {
            tz2Var.e("Service is already kept alive.", new Object[0]);
        } else {
            j23 j23Var = new j23();
            this.e.b(new zz2(this, j23Var, j23Var, i), j23Var);
        }
    }

    @Override // p.d33
    public final void f(List list) {
        g03 g03Var = this.d;
        if (g03Var == null) {
            return;
        }
        g.e("cancelDownloads(%s)", list);
        j23 j23Var = new j23();
        g03Var.b(new rz2(this, j23Var, list, j23Var, 0), j23Var);
    }

    @Override // p.d33
    public final void g(int i, String str, int i2, String str2) {
        g03 g03Var = this.d;
        if (g03Var == null) {
            throw new zzck("The Play Store app is not installed or is an unofficial version.", i);
        }
        g.e("notifyChunkTransferred", new Object[0]);
        j23 j23Var = new j23();
        g03Var.b(new uz2(this, j23Var, i, str, str2, i2, j23Var, 0), j23Var);
    }

    public final void j(String str, int i, int i2) {
        g03 g03Var = this.d;
        if (g03Var == null) {
            throw new zzck("The Play Store app is not installed or is an unofficial version.", i);
        }
        g.e("notifyModuleCompleted", new Object[0]);
        j23 j23Var = new j23();
        g03Var.b(new wz2(this, j23Var, i, str, j23Var, i2), j23Var);
    }
}
