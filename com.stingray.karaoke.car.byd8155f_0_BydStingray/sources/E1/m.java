package e1;

import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.S;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0773y f13798a = new C0773y(S.f8224q, e.o);

    /* JADX WARN: Removed duplicated region for block: B:103:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0180 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x022e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(e1.z r27, z7.InterfaceC2772a r28, e1.C1187A r29, b0.b r30, T.C0761q r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e1.m.a(e1.z, z7.a, e1.A, b0.b, T.q, int, int):void");
    }

    public static final void b(g0.c cVar, long j3, InterfaceC2772a interfaceC2772a, C1187A c1187a, b0.b bVar, C0761q c0761q, int i7) {
        g0.c cVar2;
        C1187A c1187a2;
        c0761q.S(295309329);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.e(j3) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        int i11 = i10 | 3072;
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(bVar) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            cVar2 = cVar;
            c1187a2 = c1187a;
        } else {
            g0.g gVar = g0.b.l;
            C1187A c1187a3 = new C1187A(15, false);
            boolean z6 = ((i11 & 14) == 4) | ((i11 & 112) == 32);
            Object objG = c0761q.G();
            if (z6 || objG == C0753m.f8270a) {
                objG = new C1188a(gVar, j3);
                c0761q.a0(objG);
            }
            a((C1188a) objG, interfaceC2772a, c1187a3, bVar, c0761q, (i11 >> 3) & 8176, 0);
            cVar2 = gVar;
            c1187a2 = c1187a3;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new g(cVar2, j3, interfaceC2772a, c1187a2, bVar, i7);
        }
    }

    public static final boolean c(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }
}
