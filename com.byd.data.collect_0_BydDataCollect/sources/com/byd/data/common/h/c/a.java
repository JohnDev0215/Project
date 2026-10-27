package com.byd.data.common.h.c;

import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import com.byd.data.common.i.f;

/* JADX INFO: compiled from: CollectPowerListener.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends AbsBYDAutoPowerListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3400a = "CollectPowerListener";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile byte f3401b = 10;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.data.common.h.a.a f3402c;

    public a(com.byd.data.common.h.a.a aVar) {
        this.f3402c = aVar;
    }

    private void a(int i2) {
        com.byd.data.common.h.a.a aVar;
        boolean z = b(this.f3401b) || c();
        boolean zB = b(i2);
        boolean z2 = (zB ? this.f3401b : i2) == 10;
        this.f3401b = (byte) i2;
        if (z == zB || (aVar = this.f3402c) == null || !z2) {
            return;
        }
        aVar.c(zB);
    }

    private boolean b(int i2) {
        return i2 == 19;
    }

    public boolean c() {
        return d(this.f3401b);
    }

    public boolean d(int i2) {
        return i2 == 16;
    }

    public void e() {
        this.f3402c = null;
    }

    @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
    public void onPowerSTRChanged(int i2) {
        if (i2 == this.f3401b) {
            return;
        }
        f.g("CollectPowerListener", "onPowerSTRChanged change from pre=" + ((int) this.f3401b) + " -> " + i2);
        if (((i2 != 10) & (i2 != 16)) && (i2 != 19)) {
            return;
        }
        if (b(i2) && (this.f3401b != 16)) {
            a(i2);
        } else {
            if (b(this.f3401b) && d(i2)) {
                this.f3401b = (byte) i2;
                return;
            }
            if (c() && (i2 == 10)) {
                a(i2);
            }
        }
    }

    public String toString() {
        return "CollectPowerListener{str_state=" + ((int) this.f3401b) + ", lister=" + this.f3402c + '}';
    }
}
