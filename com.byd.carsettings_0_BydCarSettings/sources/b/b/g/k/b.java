package b.b.g.k;

import android.hardware.bydauto.dms.IDMSListener;
import android.util.Log;

/* JADX INFO: compiled from: BYDDMSLisenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends IDMSListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f817b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f818c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f819d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f820e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f821f = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.dms.IDMSListener
    public void onAttentionSwitchChanged(int i) {
        if (this.f819d == i) {
            return;
        }
        this.f819d = i;
        super.onAttentionSwitchChanged(i);
        Log.e("DMS_TAG", " onAttentionSwitchChanged : " + i);
        b.b.a.c.a.j(new b.b.a.c.a(240007, i));
        if (b.b.g.r.e.b(240003)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(240003, i));
    }

    @Override // android.hardware.bydauto.dms.IDMSListener
    public void onDmsModeChanged(int i) {
        if (this.f821f == i) {
            return;
        }
        this.f821f = i;
        Log.e("DMS_TAG", " onDmsModeChanged : " + i);
        b.b.g.r.e.h(240001);
        b.b.a.c.a.j(new b.b.a.c.a(240001, i));
    }

    @Override // android.hardware.bydauto.dms.IDMSListener
    public void onDmsSwitchChanged(int i) {
        if (this.f817b == i) {
            return;
        }
        this.f817b = i;
        super.onDmsSwitchChanged(i);
        Log.e("DMS_TAG", " onDmsSwitchChanged : " + i);
        b.b.a.c.a.j(new b.b.a.c.a(240005, i));
        if (b.b.g.r.e.c(240000)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(240000, i));
    }

    @Override // android.hardware.bydauto.dms.IDMSListener
    public void onFatigueSwitchChanged(int i) {
        if (this.f818c == i) {
            return;
        }
        this.f818c = i;
        super.onFatigueSwitchChanged(i);
        Log.e("DMS_TAG", " onFatigueSwitchChanged : " + i);
        b.b.a.c.a.j(new b.b.a.c.a(240006, i));
        if (b.b.g.r.e.c(240002)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(240002, i));
    }

    @Override // android.hardware.bydauto.dms.IDMSListener
    public void onNotifyDmsServerState(int i) {
        if (this.f820e == i) {
            return;
        }
        this.f820e = i;
        super.onNotifyDmsServerState(i);
        Log.e("DMS_TAG", " onNotifyDmsServerState : " + i);
        b.b.g.r.e.h(240004);
        b.b.a.c.a.j(new b.b.a.c.a(240004, i));
    }
}
