package B6;

import A6.c;
import A7.m;
import B5.C0083g;
import S8.AbstractC0727y;
import S8.E;
import Z8.d;
import Z8.e;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f742b;
    public C0083g c;

    public b(Context context, c cVar) {
        m.f(context, "context");
        this.f741a = context;
        this.f742b = cVar;
    }

    public final void a(String str, String str2) {
        e eVar = E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(d.f11010n), null, null, new a(str, str2, this, null), 3);
    }
}
