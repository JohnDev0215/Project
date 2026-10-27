package h6;

import c6.b0;
import c6.v;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ConnectInterceptor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6658a = new a();

    private a() {
    }

    @Override // c6.v
    public b0 a(v.a chain) {
        l.e(chain, "chain");
        i6.g gVar = (i6.g) chain;
        return i6.g.d(gVar, 0, gVar.e().r(gVar), null, 0, 0, 0, 61, null).b(gVar.i());
    }
}
