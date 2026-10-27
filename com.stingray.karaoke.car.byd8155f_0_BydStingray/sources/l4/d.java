package l4;

import android.content.Context;
import android.net.Uri;
import e4.h;
import k4.p;
import k4.q;
import z4.C2768d;

/* JADX INFO: loaded from: classes.dex */
public final class d implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f18631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f18632b;
    public final q c;
    public final Class d;

    public d(Context context, q qVar, q qVar2, Class cls) {
        this.f18631a = context.getApplicationContext();
        this.f18632b = qVar;
        this.c = qVar2;
        this.d = cls;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, h hVar) {
        Uri uri = (Uri) obj;
        return new p(new C2768d(uri), new C1772c(this.f18631a, this.f18632b, this.c, uri, i7, i10, hVar, this.d));
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        return com.bumptech.glide.d.F((Uri) obj);
    }
}
