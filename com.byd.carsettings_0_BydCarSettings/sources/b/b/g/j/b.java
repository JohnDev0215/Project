package b.b.g.j;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.a.f.h;
import b.b.g.d.e;
import b.b.g.d.f;
import b.b.g.d.g;
import b.b.g.d.i;
import b.b.g.d.j;
import b.b.g.d.n;
import b.b.g.d.o;
import b.b.g.d.p;
import b.b.g.d.q;
import b.b.g.n.n.c;
import b.b.g.n.n.d;
import b.b.g.p.d;
import b.b.g.r.k;
import com.byd.base.hilt.impl.StudyConfigImpl;
import com.byd.base.thread.Priority;
import com.byd.mvp.common.CarModel$Brand;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: PageItemHider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, b.b.g.m.b> f809a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static StringBuilder f810b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f811c = false;

    /* JADX INFO: compiled from: PageItemHider.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.a("PageItemHider", "---checkRegistered---");
            b.b.g.n.g.b bVar = new b.b.g.n.g.b();
            bVar.j();
            if (bVar.n()) {
                b.this.F(new b.b.g.m.b(5, 0, 1));
                b.this.F(new b.b.g.m.b(5, 0, 2));
            } else {
                b.this.k(new b.b.g.m.b(5, 0, 1));
                b.this.k(new b.b.g.m.b(5, 0, 2));
            }
        }
    }

    /* JADX INFO: renamed from: b.b.g.j.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PageItemHider.java */
    public static class C0048b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static b f813a = new b();

        public static b a() {
            return f813a;
        }
    }

    @SuppressLint({"WrongConstant"})
    public void A(@NonNull StudyConfigImpl studyConfigImpl) {
        int i;
        BydLogHelper.logD("PageItemHider", "registerPageItemHider");
        StringBuilder sb = new StringBuilder("Start hide item:\n");
        int i2 = 64;
        while (true) {
            if (i2 > 70) {
                break;
            }
            if (!studyConfigImpl.c(i2)) {
                k(b.b.g.m.b.b(0, i2));
                sb.append("hide drive: ");
                sb.append(i2);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
            i2++;
        }
        for (i = 0; i <= 33; i++) {
            if (!studyConfigImpl.c(i)) {
                k(b.b.g.m.b.b(1, i));
                sb.append("hide safe: ");
                sb.append(i);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
        }
        for (int i3 = 80; i3 <= 81; i3++) {
            if (!studyConfigImpl.c(i3)) {
                k(b.b.g.m.b.b(2, i3));
                sb.append("hide park: ");
                sb.append(i3);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
        }
        BydLogHelper.logD("PageItemHider", sb.toString());
    }

    public final void B(Context context) {
        n nVar = new n();
        boolean zA = nVar.a();
        j(!zA, b.b.g.m.b.e(13));
        if (zA) {
            j(nVar.b() != 1, b.b.g.m.b.d(13, 0, 0));
        }
    }

    public final void C(Context context) {
        boolean zA = new o().a();
        j(!zA, b.b.g.m.b.e(10));
        if (zA) {
            j(!r5.d(), b.b.g.m.b.d(10, 1, 0));
            j(!r5.e(), b.b.g.m.b.d(10, 1, 1));
            j(!r5.c(), b.b.g.m.b.c(10, 3));
            j(!r5.b(), b.b.g.m.b.c(10, 6));
        }
    }

    public final void D(Context context) {
        j(!new q().a(), b.b.g.m.b.e(11));
    }

    public void E(Context context) {
        b.b.m.c.a.a("PageItemHider", "---reloadCarSettingsHideItems-----");
        q(context);
    }

    public void F(b.b.g.m.b bVar) {
        String strC = c(bVar);
        b.b.m.c.a.a("PageItemHider", "---unregister--key: " + strC);
        f809a.remove(strC);
    }

    public final boolean a(String[] strArr, int[] iArr) {
        int i;
        int length = iArr.length - 1;
        try {
            i = Integer.parseInt(strArr[length]);
        } catch (RuntimeException unused) {
            i = -1;
        }
        return i <= iArr[length];
    }

    public void b(Context context) {
        h.a().h(new a(), Priority.HIGH);
    }

    public final synchronized String c(b.b.g.m.b bVar) {
        if (f810b == null) {
            f810b = new StringBuilder();
        }
        f810b.setLength(0);
        if (bVar == null) {
            return f810b.toString();
        }
        StringBuilder sb = f810b;
        sb.append(bVar.f880a);
        sb.append("_");
        int i = bVar.f881b;
        if (i < 0) {
            return f810b.toString();
        }
        StringBuilder sb2 = f810b;
        sb2.append(i);
        sb2.append("_");
        int i2 = bVar.f882c;
        if (i2 < 0) {
            return f810b.toString();
        }
        StringBuilder sb3 = f810b;
        sb3.append(i2);
        sb3.append("_");
        int i3 = bVar.f883d;
        if (i3 < 0) {
            return f810b.toString();
        }
        f810b.append(i3);
        return f810b.toString();
    }

    public int d(int[] iArr) {
        int i = 0;
        if (iArr != null && iArr.length > 0 && iArr.length <= 4) {
            Iterator<String> it = f809a.keySet().iterator();
            while (it.hasNext()) {
                String[] strArrF = f(it.next(), iArr);
                if (strArrF != null && i(strArrF, iArr) && a(strArrF, iArr)) {
                    i++;
                }
            }
        }
        return i;
    }

    public void e(Context context) {
        b.b.m.c.a.a("PageItemHider", "---init---inited: " + this.f811c);
        if (this.f811c) {
            return;
        }
        t(context);
        v(context);
        n(context);
        q(context);
        this.f811c = true;
    }

    public final String[] f(String str, int[] iArr) {
        String[] strArrSplit;
        if (!TextUtils.isEmpty(str) && (strArrSplit = str.split("_")) != null && strArrSplit.length > 0 && iArr.length == strArrSplit.length) {
            return strArrSplit;
        }
        return null;
    }

    public boolean g(int i, int i2) {
        String strC = c(new b.b.g.m.b(i, i2));
        StringBuilder sb = new StringBuilder();
        sb.append("---isPageRegistered--key: ");
        sb.append(strC);
        sb.append(", item: ");
        ConcurrentHashMap<String, b.b.g.m.b> concurrentHashMap = f809a;
        sb.append(concurrentHashMap.get(strC));
        b.b.m.c.a.a("PageItemHider", sb.toString());
        return concurrentHashMap.get(strC) != null;
    }

    public boolean h(int i, int i2, int i3, int i4) {
        String strC = c(new b.b.g.m.b(i, i2, i3, i4));
        StringBuilder sb = new StringBuilder();
        sb.append("---isRegistered--key: ");
        sb.append(strC);
        sb.append(", item: ");
        ConcurrentHashMap<String, b.b.g.m.b> concurrentHashMap = f809a;
        sb.append(concurrentHashMap.get(strC));
        b.b.m.c.a.a("PageItemHider", sb.toString());
        return concurrentHashMap.get(strC) != null;
    }

    public final boolean i(String[] strArr, int[] iArr) {
        if (iArr.length == 1) {
            return true;
        }
        int i = -1;
        int length = iArr.length - 1;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            try {
                i = Integer.parseInt(strArr[i3]);
            } catch (RuntimeException unused) {
            }
            if (i != iArr[i3]) {
                break;
            }
            i2++;
        }
        return i2 == length;
    }

    public b.b.g.m.b j(boolean z, b.b.g.m.b bVar) {
        String strC = c(bVar);
        b.b.m.c.a.a("PageItemHider", "---register-----register: " + z + ", key: " + strC + ", hii: " + bVar);
        if (!z) {
            return f809a.remove(strC);
        }
        ConcurrentHashMap<String, b.b.g.m.b> concurrentHashMap = f809a;
        b.b.g.m.b bVar2 = concurrentHashMap.get(strC);
        return bVar2 != null ? bVar2 : concurrentHashMap.put(strC, bVar);
    }

    public void k(b.b.g.m.b bVar) {
        String strC = c(bVar);
        ConcurrentHashMap<String, b.b.g.m.b> concurrentHashMap = f809a;
        if (concurrentHashMap.get(strC) != null) {
            return;
        }
        concurrentHashMap.put(strC, bVar);
    }

    public final void l(Context context) {
        boolean zA = new b.b.g.d.a().a();
        j(!zA, b.b.g.m.b.e(0));
        if (zA) {
            j(!r5.e(), b.b.g.m.b.d(0, 0, 0));
            j(!r5.c(), b.b.g.m.b.d(0, 0, 1));
            j(!r5.g(), b.b.g.m.b.c(0, 1));
            j(!r5.f(), b.b.g.m.b.c(0, 2));
            j(!r5.d(), b.b.g.m.b.c(0, 3));
        }
    }

    public final void m(Context context) {
        boolean zA = new b.b.g.d.b().a();
        j(!zA, b.b.g.m.b.e(7));
        if (zA) {
            j(!r8.b(), b.b.g.m.b.d(7, 0, 0));
            j(!r8.c(), b.b.g.m.b.d(7, 0, 1));
            j(!r8.i(), b.b.g.m.b.d(7, 1, 0));
            j(!r8.h(), b.b.g.m.b.d(7, 1, 1));
            j(!r8.j(), b.b.g.m.b.d(7, 2, 0));
            j(!r8.j(), b.b.g.m.b.d(7, 2, 1));
            j(!r8.j(), b.b.g.m.b.d(7, 2, 2));
            j(!r8.d(), b.b.g.m.b.d(7, 2, 3));
            j(!r8.e(), b.b.g.m.b.d(7, 3, 0));
            j(!r8.f(), b.b.g.m.b.d(7, 3, 1));
            j(!r8.e(), b.b.g.m.b.d(7, 3, 2));
            j(!r8.g(), b.b.g.m.b.c(7, 4));
        }
    }

    public final void n(Context context) {
        b.b.m.c.a.a("PageItemHider", "---registerCarHealthyAndEnergyHideItems---");
        c cVar = new c();
        cVar.x();
        if (!cVar.D()) {
            k(new b.b.g.m.b(3, 0, 0));
        }
        if (!cVar.C()) {
            k(new b.b.g.m.b(3, 0, 2));
        }
        if (!cVar.y()) {
            k(new b.b.g.m.b(3, 0, 3));
        }
        if (!cVar.z()) {
            k(new b.b.g.m.b(3, 0, 4));
        }
        if (cVar.B()) {
            k(new b.b.g.m.b(3, 0, 5));
            k(new b.b.g.m.b(3, 0, 6));
            k(new b.b.g.m.b(3, 0, 7));
        } else {
            int iQ = cVar.q();
            if (iQ != 2 && iQ != 1) {
                k(new b.b.g.m.b(3, 0, 5));
            }
            int iO = cVar.o();
            if (iO != 2 && iO != 1) {
                k(new b.b.g.m.b(3, 0, 6));
            }
            int iN = cVar.n();
            if ((iN != 2 && iN != 1) || !cVar.D()) {
                k(new b.b.g.m.b(3, 0, 7));
            }
        }
        if (!cVar.A()) {
            k(new b.b.g.m.b(3, 0, 8));
        }
        if (!cVar.E()) {
            k(new b.b.g.m.b(3, 0, 9));
        }
        if (!cVar.D()) {
            k(new b.b.g.m.b(3, 0, 10));
        }
        b.b.g.n.n.b bVar = new b.b.g.n.n.b();
        bVar.r();
        if (!bVar.A()) {
            k(new b.b.g.m.b(3, 1, 0));
        }
        if (!bVar.u()) {
            k(new b.b.g.m.b(3, 1, 1));
        }
        if (!bVar.B()) {
            k(new b.b.g.m.b(3, 1, 3));
        }
        if (!bVar.y()) {
            k(new b.b.g.m.b(3, 1, 4));
        }
        if (!bVar.z()) {
            k(new b.b.g.m.b(3, 1, 5));
        }
        if (!bVar.C()) {
            k(new b.b.g.m.b(3, 1, 6));
        }
        if (!bVar.D()) {
            k(new b.b.g.m.b(3, 1, 7));
        }
        if (!bVar.s()) {
            k(new b.b.g.m.b(3, 1, 8));
        }
        if (bVar.v()) {
            k(new b.b.g.m.b(3, 2, 1));
        }
        d dVar = new d();
        dVar.g();
        if (!dVar.i()) {
            k(new b.b.g.m.b(3, 3, 0));
            k(new b.b.g.m.b(3, 3, 1));
            k(new b.b.g.m.b(3, 3, 2));
        }
        if (!dVar.l()) {
            k(new b.b.g.m.b(3, 3, 1));
        }
        if (!dVar.h()) {
            k(new b.b.g.m.b(3, 3, 2));
        }
        b.b.g.n.g.b bVar2 = new b.b.g.n.g.b();
        bVar2.j();
        if (!bVar2.n()) {
            k(new b.b.g.m.b(5, 0, 1));
            k(new b.b.g.m.b(5, 0, 2));
        }
        if (!bVar2.m()) {
            k(new b.b.g.m.b(5, 0, 3));
        }
        b.b.g.n.g.c cVar2 = new b.b.g.n.g.c();
        cVar2.h();
        if (!cVar2.j()) {
            k(new b.b.g.m.b(5, 1, 1));
        }
        if (!cVar2.o()) {
            k(new b.b.g.m.b(5, 1, 2));
        }
        if (!cVar2.l()) {
            k(new b.b.g.m.b(5, 1, 3));
        }
        if (!cVar2.i()) {
            k(new b.b.g.m.b(5, 1, 4));
        }
        if (!cVar2.m()) {
            k(new b.b.g.m.b(5, 1, 5));
        }
        if (!cVar2.n()) {
            k(new b.b.g.m.b(5, 1, 6));
        }
        b.b.g.n.g.a aVar = new b.b.g.n.g.a();
        aVar.f();
        if (aVar.g()) {
            return;
        }
        k(new b.b.g.m.b(5, 2, 1));
    }

    public final void o(Context context) {
        boolean zA = new j().a();
        j(!zA, b.b.g.m.b.e(1));
        if (zA) {
            j(!r7.o(), b.b.g.m.b.c(1, 0));
            j(!r7.p(), b.b.g.m.b.c(1, 1));
            j(!r7.c(), b.b.g.m.b.c(1, 2));
            j(!r7.b(), b.b.g.m.b.c(1, 3));
            j(!r7.i(), b.b.g.m.b.d(1, 4, 0));
            j(!r7.h(), b.b.g.m.b.d(1, 4, 1));
            j(!r7.j(), b.b.g.m.b.d(1, 4, 2));
            j(!r7.g(), b.b.g.m.b.d(1, 5, 0));
            j(!r7.d(), b.b.g.m.b.d(1, 5, 1));
        }
    }

    public final void p(Context context) {
        p pVar = new p();
        boolean zA = pVar.a();
        j(!zA, b.b.g.m.b.e(8));
        if (zA) {
            int i = 1;
            while (true) {
                if (i > 4) {
                    break;
                }
                if (pVar.j(i)) {
                    j(false, b.b.g.m.b.c(8, 0));
                    break;
                } else {
                    j(true, b.b.g.m.b.c(8, 0));
                    i++;
                }
            }
            j(!pVar.K(), b.b.g.m.b.c(8, 1));
            j(!pVar.w(), b.b.g.m.b.c(8, 2));
        }
    }

    public final void q(Context context) {
        b.b.m.c.a.a("PageItemHider", "---registerCarSettingsHideItems---");
        l(context);
        o(context);
        s(context);
        u(context);
        y(context);
        z(context);
        m(context);
        p(context);
        w(context);
        C(context);
        D(context);
        r(context);
        B(context);
        x(context);
    }

    public final void r(Context context) {
        boolean zA = new b.b.g.d.c().a();
        j(!zA, b.b.g.m.b.e(12));
        if (zA) {
            j(!r5.b(), b.b.g.m.b.c(12, 0));
            j(!r5.c(), b.b.g.m.b.c(12, 1));
            j(!r5.d(), b.b.g.m.b.c(12, 2));
        }
    }

    public final void s(Context context) {
        b.b.g.d.d dVar = new b.b.g.d.d();
        boolean zA = dVar.a();
        j(!zA, b.b.g.m.b.e(2));
        if (zA) {
            j(!dVar.f(), b.b.g.m.b.d(2, 0, 0));
            j(!dVar.d(), b.b.g.m.b.d(2, 0, 1));
            j(!dVar.i(), b.b.g.m.b.d(2, 1, 0));
            j(!dVar.h(), b.b.g.m.b.d(2, 1, 1));
            j(!dVar.g(), b.b.g.m.b.d(2, 2, 0));
            j(!dVar.c(), b.b.g.m.b.d(2, 2, 1));
            j(!dVar.j(), b.b.g.m.b.d(2, 2, 2));
            j(!dVar.e(), b.b.g.m.b.d(2, 3, 0));
            j(dVar.k() == 0, b.b.g.m.b.d(2, 3, 1));
        }
    }

    public void t(Context context) {
        b.b.m.c.a.a("PageItemHider", "---registerCommonHideItems---");
        k(new b.b.g.m.b(0));
    }

    public final void u(Context context) {
        boolean zA = new e().a();
        j(!zA, b.b.g.m.b.e(3));
        if (zA) {
            j(!r6.f(), b.b.g.m.b.c(3, 0));
            j(!r6.e(), b.b.g.m.b.d(3, 1, 0));
            j(!r6.d(), b.b.g.m.b.d(3, 1, 1));
            j(!r6.c(), b.b.g.m.b.c(3, 2));
        }
    }

    public void v(Context context) {
        b.b.m.c.a.a("PageItemHider", "---registerDilinkHideItems---");
        k(b.b.g.m.b.a(1, 3));
        k(b.b.g.m.b.a(5, 4));
        k(b.b.g.m.b.a(5, 5));
        CarModel$Brand carModel$Brand = k.f1817c;
        j(CarModel$Brand.DENZA == carModel$Brand, b.b.g.m.b.a(2, 3));
        boolean zV = d.g.a().V(context);
        b.b.m.c.a.a("PageItemHider", "---registerCommonAndDilinkHideItems-----brand：" + carModel$Brand + ", hasLauncherSelector: " + zV);
        j(zV ^ true, b.b.g.m.b.a(2, 4));
    }

    public final void w(Context context) {
        f fVar = new f();
        boolean zA = fVar.a();
        j(!zA, b.b.g.m.b.e(9));
        if (zA) {
            j(fVar.b() == 0, b.b.g.m.b.c(9, 0));
            j(!fVar.c(), b.b.g.m.b.d(9, 1, 0));
            j(!fVar.e(), b.b.g.m.b.d(9, 1, 1));
            j(!fVar.p(), b.b.g.m.b.d(9, 2, 0));
            j(!fVar.n(), b.b.g.m.b.d(9, 2, 1));
            j(!fVar.o(), b.b.g.m.b.d(9, 2, 2));
            j(!fVar.l(), b.b.g.m.b.d(9, 2, 3));
            j(!fVar.m(), b.b.g.m.b.d(9, 2, 4));
            j(!fVar.k(), b.b.g.m.b.d(9, 3, 0));
            j(!fVar.j(), b.b.g.m.b.d(9, 3, 1));
            j(!fVar.q(), b.b.g.m.b.d(9, 4, 0));
            j(!fVar.r(), b.b.g.m.b.d(9, 4, 1));
            j(!fVar.t(), b.b.g.m.b.d(9, 4, 2));
            j(!fVar.g(), b.b.g.m.b.d(9, 4, 3));
            j(!fVar.d(), b.b.g.m.b.c(9, 5));
            j(!fVar.f(), b.b.g.m.b.c(9, 6));
            j(!fVar.s(), b.b.g.m.b.c(9, 7));
            j(!fVar.h(), b.b.g.m.b.d(9, 8, 0));
            j(!fVar.i(), b.b.g.m.b.d(9, 8, 1));
        }
    }

    public final void x(Context context) {
        g gVar = new g();
        boolean zA = gVar.a();
        j(!zA, b.b.g.m.b.e(4));
        if (zA) {
            j(!gVar.j(), b.b.g.m.b.c(4, 0));
            j(!gVar.l(), b.b.g.m.b.c(4, 1));
            j(!gVar.l() || gVar.d() == 3, b.b.g.m.b.c(4, 2));
            j(!gVar.l() || gVar.d() == 3, b.b.g.m.b.c(4, 3));
            j(!gVar.h(), b.b.g.m.b.c(4, 4));
            j(!gVar.i(), b.b.g.m.b.c(4, 5));
            j(!gVar.g(), b.b.g.m.b.c(4, 6));
        }
    }

    public final void y(Context context) {
        j(!new b.b.g.d.h().a(), b.b.g.m.b.e(5));
    }

    public final void z(Context context) {
        i iVar = new i();
        if (!iVar.a()) {
            k(b.b.g.m.b.e(6));
            return;
        }
        if (!iVar.d()) {
            k(b.b.g.m.b.c(6, 0));
        }
        if (iVar.g()) {
            return;
        }
        k(b.b.g.m.b.c(6, 1));
    }
}
