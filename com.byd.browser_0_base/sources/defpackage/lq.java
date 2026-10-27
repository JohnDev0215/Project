package defpackage;

import android.net.NetworkInfo;

/* JADX INFO: compiled from: ChromeMinidumpUploaderDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class lq implements e10 {
    public final /* synthetic */ mq a;

    public lq(mq mqVar) {
        this.a = mqVar;
    }

    @Override // defpackage.e10
    public final boolean b() {
        return v42.g().b();
    }

    @Override // defpackage.e10
    public final boolean c() {
        return v42.g().c();
    }

    @Override // defpackage.e10
    public final boolean d() {
        NetworkInfo activeNetworkInfo = this.a.c.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return !r1.isActiveNetworkMetered();
    }

    @Override // defpackage.e10
    public final boolean e() {
        return this.a.b.getBoolean("isUploadEnabledForTests", false);
    }

    @Override // defpackage.e10
    public final boolean f() {
        return this.a.b.getBoolean("isClientInMetricsSample", true);
    }
}
