package a.l.d;

import a.b.k.l;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class o<E> extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f1017a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1018b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f1019c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r f1020d;

    public o(e eVar) {
        Handler handler = new Handler();
        this.f1020d = new t();
        this.f1017a = eVar;
        l.i.i(eVar, "context == null");
        this.f1018b = eVar;
        l.i.i(handler, "handler == null");
        this.f1019c = handler;
    }
}
