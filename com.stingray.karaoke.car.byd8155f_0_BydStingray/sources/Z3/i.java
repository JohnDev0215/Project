package z3;

import A3.k;
import B2.O;
import S8.AbstractC0727y;
import S8.E;
import S8.l0;
import X8.m;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import c9.w;
import g1.AbstractC1274a;
import h6.q;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import m7.n;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f24398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K3.c f24399b;
    public final n c;
    public final N3.f d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final O f24400e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2764b f24401f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f24402g;

    public i(Context context, K3.c cVar, n nVar, n nVar2, n nVar3, C2764b c2764b, N3.f fVar) {
        int i7 = 3;
        int i10 = 4;
        int i11 = 5;
        int i12 = 0;
        this.f24398a = context;
        this.f24399b = cVar;
        this.c = nVar;
        this.d = fVar;
        l0 l0VarC = AbstractC0727y.c();
        Z8.e eVar = E.f8084a;
        AbstractC0727y.a(q.x(l0VarC, m.f10573a.f8932q).v(new h(this)));
        N3.h hVar = new N3.h(this);
        O o = new O(this, hVar);
        this.f24400e = o;
        N0.q qVar = new N0.q();
        qVar.l = AbstractC1998m.A0(c2764b.f24380a);
        qVar.f4399m = AbstractC1998m.A0(c2764b.f24381b);
        qVar.f4400n = AbstractC1998m.A0(c2764b.c);
        qVar.o = AbstractC1998m.A0(c2764b.d);
        qVar.f4401p = AbstractC1998m.A0(c2764b.f24382e);
        qVar.n(new H3.a(2), w.class);
        qVar.n(new H3.a(i11), String.class);
        qVar.n(new H3.a(1), Uri.class);
        qVar.n(new H3.a(i10), Uri.class);
        qVar.n(new H3.a(i7), Integer.class);
        qVar.n(new H3.a(i12), byte[].class);
        G3.c cVar2 = new G3.c();
        ArrayList arrayList = (ArrayList) qVar.f4400n;
        arrayList.add(new m7.i(cVar2, Uri.class));
        arrayList.add(new m7.i(new G3.a(fVar.f4579a), File.class));
        qVar.f(new E3.i(nVar3, nVar2, fVar.c), Uri.class);
        qVar.f(new E3.a(i11), File.class);
        qVar.f(new E3.a(i12), Uri.class);
        qVar.f(new E3.a(i7), Uri.class);
        qVar.f(new E3.a(6), Uri.class);
        qVar.f(new E3.a(i10), Drawable.class);
        qVar.f(new E3.a(1), Bitmap.class);
        qVar.f(new E3.a(2), ByteBuffer.class);
        B3.c cVar3 = new B3.c(fVar.d, fVar.f4581e);
        ArrayList arrayList2 = (ArrayList) qVar.f4401p;
        arrayList2.add(cVar3);
        List listD0 = AbstractC1274a.d0((ArrayList) qVar.l);
        this.f24401f = new C2764b(listD0, AbstractC1274a.d0((ArrayList) qVar.f4399m), AbstractC1274a.d0(arrayList), AbstractC1274a.d0((ArrayList) qVar.o), AbstractC1274a.d0(arrayList2));
        this.f24402g = AbstractC1998m.o0(listD0, new F3.h(this, hVar, o));
        new AtomicBoolean(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cf A[Catch: all -> 0x0174, TryCatch #3 {all -> 0x0174, blocks: (B:43:0x00c5, B:45:0x00cf, B:48:0x00d7, B:50:0x00e1, B:52:0x00e7, B:54:0x00ed, B:55:0x00f3), top: B:97:0x00c5 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e1 A[Catch: all -> 0x0174, TryCatch #3 {all -> 0x0174, blocks: (B:43:0x00c5, B:45:0x00cf, B:48:0x00d7, B:50:0x00e1, B:52:0x00e7, B:54:0x00ed, B:55:0x00f3), top: B:97:0x00c5 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013e A[Catch: all -> 0x0151, TryCatch #0 {all -> 0x0151, blocks: (B:63:0x0138, B:65:0x013e, B:69:0x0153, B:71:0x0157, B:74:0x0169, B:75:0x016e), top: B:93:0x0138 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0153 A[Catch: all -> 0x0151, TryCatch #0 {all -> 0x0151, blocks: (B:63:0x0138, B:65:0x013e, B:69:0x0153, B:71:0x0157, B:74:0x0169, B:75:0x016e), top: B:93:0x0138 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0183 A[Catch: all -> 0x0198, TRY_LEAVE, TryCatch #4 {all -> 0x0198, blocks: (B:82:0x017f, B:84:0x0183, B:89:0x019a, B:90:0x01a3), top: B:99:0x017f }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x019a A[Catch: all -> 0x0198, TRY_ENTER, TryCatch #4 {all -> 0x0198, blocks: (B:82:0x017f, B:84:0x0183, B:89:0x019a, B:90:0x01a3), top: B:99:0x017f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(z3.i r19, K3.i r20, int r21, s7.AbstractC2410c r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: z3.i.a(z3.i, K3.i, int, s7.c):java.lang.Object");
    }

    public static void b(K3.e eVar, k kVar, c cVar) {
        K3.i iVar = eVar.f3527b;
        cVar.getClass();
        iVar.getClass();
    }
}
