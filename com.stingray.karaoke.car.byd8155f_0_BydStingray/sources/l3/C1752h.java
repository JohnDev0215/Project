package l3;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Process;
import io.sentry.C1489n1;
import k3.E0;
import u.C2465e;

/* JADX INFO: renamed from: l3.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1752h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f18570a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaBrowser f18571b;
    public final Bundle c;
    public final E0 d = new E0(this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2465e f18572e = new C2465e(0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C1489n1 f18573f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Messenger f18574g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public O f18575h;

    public C1752h(Context context, ComponentName componentName, h3.y yVar, Bundle bundle) {
        this.f18570a = context;
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        this.c = bundle2;
        bundle2.putInt("extra_client_version", 1);
        bundle2.putInt("extra_calling_pid", Process.myPid());
        yVar.f15500n = this;
        C1751g c1751g = (C1751g) yVar.f15499m;
        c1751g.getClass();
        this.f18571b = new MediaBrowser(context, componentName, c1751g, bundle2);
    }
}
