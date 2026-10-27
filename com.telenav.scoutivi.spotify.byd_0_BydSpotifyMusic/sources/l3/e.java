package l3;

import android.content.Context;
import i3.m;

/* JADX INFO: compiled from: SteerWheelManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Context f7959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final m f7960b;

    public e(Context context, m mVar) {
        this.f7959a = context.getApplicationContext();
        this.f7960b = mVar;
    }

    protected abstract void a();

    public void b() {
        a();
    }
}
