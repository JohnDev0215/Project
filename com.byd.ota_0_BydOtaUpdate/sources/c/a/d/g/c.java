package c.a.d.g;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import c.a.b.d.e;
import c.a.d.f.i;
import c.a.d.f.j;

/* JADX INFO: compiled from: PowerLevelListener.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends AbsBYDAutoBodyworkListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2490a = "c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f2491b = 800;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f2492c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i f2493d;

    public c(j jVar, i iVar) {
        e.c(f2490a, "init PowerLevelListener");
        this.f2492c = jVar;
        this.f2493d = iVar;
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onPowerLevelChanged(int i2) {
        super.onPowerLevelChanged(i2);
        e.c(f2490a, "onPowerLevelChanged->level = " + i2);
        if (i2 == 0) {
            this.f2493d.a();
        } else {
            if (i2 != 2) {
                return;
            }
            this.f2492c.a();
        }
    }
}
