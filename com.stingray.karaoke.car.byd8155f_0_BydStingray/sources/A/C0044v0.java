package A;

import D.AbstractC0133o;
import D.InterfaceC0123e;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F.C0190a;
import F0.AbstractC0227s0;
import F0.C0210j0;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import android.graphics.Bitmap;
import androidx.compose.ui.platform.AndroidComposeView;
import h2.AbstractC1347d;
import io.sentry.EnumC1469h;
import io.sentry.F1;
import io.sentry.android.replay.ReplayIntegration;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import n3.C1945j;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import x.C2634f0;
import z.C2751b;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0044v0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f285m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f286n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f287p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0044v0(Object obj, Object obj2, Object obj3, int i7) {
        super(2);
        this.f285m = i7;
        this.f286n = obj;
        this.o = obj2;
        this.f287p = obj3;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        io.sentry.E e10;
        io.sentry.E e11;
        io.sentry.transport.m mVarD;
        io.sentry.transport.m mVarD2;
        switch (this.f285m) {
            case 0:
                float fFloatValue = ((Number) obj).floatValue();
                ((Number) obj2).floatValue();
                A7.w wVar = (A7.w) this.f286n;
                float f10 = fFloatValue - wVar.l;
                S0 s02 = (S0) this.o;
                long jG = s02.g(s02.c(f10));
                S0 s03 = ((P0) this.f287p).f88a;
                wVar.l += s02.c(s02.f(S0.a(s03, s03.f105h, jG, 1)));
                return m7.q.f19267a;
            case 1:
                a1.b bVar = (a1.b) obj;
                long j3 = ((a1.a) obj2).f11027a;
                if (a1.a.h(j3) == Integer.MAX_VALUE) {
                    throw new IllegalArgumentException("LazyVerticalGrid's width should be bound by parent.");
                }
                a1.k kVar = a1.k.l;
                D.L l = (D.L) this.f286n;
                int iH = a1.a.h(j3) - bVar.O(androidx.compose.foundation.layout.a.e(l, kVar) + androidx.compose.foundation.layout.a.f(l, kVar));
                InterfaceC0123e interfaceC0123e = (InterfaceC0123e) this.f287p;
                int iO = bVar.O(interfaceC0123e.a());
                int i7 = ((C0190a) this.o).f1863a;
                int i10 = iH - ((i7 - 1) * iO);
                int i11 = i10 / i7;
                int i12 = i10 % i7;
                ArrayList arrayList = new ArrayList(i7);
                int i13 = 0;
                while (i13 < i7) {
                    arrayList.add(Integer.valueOf((i13 < i12 ? 1 : 0) + i11));
                    i13++;
                }
                int[] iArrY0 = AbstractC1998m.y0(arrayList);
                int[] iArr = new int[iArrY0.length];
                interfaceC0123e.b(bVar, iH, iArrY0, kVar, iArr);
                return new F.r(iArrY0, iArr);
            case 2:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    AbstractC0227s0.a((AndroidComposeView) this.f286n, (C0210j0) this.o, (b0.b) this.f287p, c0761q, 0);
                }
                return m7.q.f19267a;
            case 3:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    g0.o oVarO = androidx.compose.foundation.layout.a.o(D.T.b(g0.l.f14684a, 1.0f), ((InterfaceC2785n) this.f286n) != null ? Q.C.f5502b : 0, 0.0f, ((InterfaceC2785n) this.o) != null ? Q.C.f5502b : 0, 0.0f, 10);
                    C0.K kE = AbstractC0133o.e(g0.b.l, false);
                    int i14 = c0761q2.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q2.m();
                    g0.o oVarD = g0.a.d(c0761q2, oVarO);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q2.U();
                    if (c0761q2.f8303O) {
                        c0761q2.l(c0159i);
                    } else {
                        c0761q2.d0();
                    }
                    C0735d.V(c0761q2, kE, C0160j.f1679e);
                    C0735d.V(c0761q2, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i14))) {
                        AbstractC1347d.l(i14, c0761q2, i14, c0158h);
                    }
                    C0735d.V(c0761q2, oVarD, C0160j.c);
                    ((b0.b) this.f287p).invoke(c0761q2, 0);
                    c0761q2.p(true);
                }
                return m7.q.f19267a;
            case 4:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    Z0.a.c((String) this.f286n, (String) this.o, c0761q3, Arrays.copyOf((Object[]) this.f287p, 0));
                }
                return m7.q.f19267a;
            case 5:
                io.sentry.android.replay.i iVar = (io.sentry.android.replay.i) obj;
                long jLongValue = ((Number) obj2).longValue();
                A7.m.f(iVar, "$this$onScreenshotRecorded");
                String str = (String) ((A7.z) this.o).l;
                Bitmap bitmap = (Bitmap) this.f286n;
                if (iVar.g() != null && !bitmap.isRecycled()) {
                    File fileG = iVar.g();
                    if (fileG != null) {
                        fileG.mkdirs();
                    }
                    File file = new File(iVar.g(), jLongValue + ".jpg");
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                        fileOutputStream.flush();
                        AbstractC1985B.c(fileOutputStream, null);
                        iVar.f16409s.add(new io.sentry.android.replay.j(file, jLongValue, str));
                    } finally {
                    }
                }
                ReplayIntegration replayIntegration = (ReplayIntegration) this.f287p;
                if (replayIntegration.f16324v instanceof io.sentry.android.replay.capture.o) {
                    F1 f12 = replayIntegration.f16317n;
                    if (f12 == null) {
                        A7.m.j("options");
                        throw null;
                    }
                    if (f12.getConnectionStatusProvider().e() == io.sentry.F.DISCONNECTED || (((e10 = replayIntegration.o) != null && (mVarD2 = e10.d()) != null && mVarD2.g(EnumC1469h.All)) || ((e11 = replayIntegration.o) != null && (mVarD = e11.d()) != null && mVarD.g(EnumC1469h.Replay)))) {
                        replayIntegration.b();
                    }
                }
                return m7.q.f19267a;
            case 6:
                float fFloatValue2 = ((Number) obj).floatValue();
                ((Number) obj2).floatValue();
                AbstractC0727y.w((InterfaceC0725w) this.f286n, null, null, new o3.u(fFloatValue2, (C2634f0) this.o, (C1945j) this.f287p, null), 3);
                return m7.q.f19267a;
            default:
                C0761q c0761q4 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    C0004b c0004b = (C0004b) this.f287p;
                    C2751b c2751b = (C2751b) this.f286n;
                    z.n.a(c2751b, (g0.o) this.o, b0.g.b(1156688164, new N(c0004b, 6, c2751b), c0761q4), c0761q4, 384);
                }
                return m7.q.f19267a;
        }
    }
}
