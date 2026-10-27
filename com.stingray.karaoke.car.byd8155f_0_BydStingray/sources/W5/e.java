package W5;

import T.C0735d;
import T.C0738e0;
import T.S;
import V8.AbstractC0828q;
import V8.f0;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.os.Bundle;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import n3.C1930E;
import n3.C1945j;
import n7.AbstractC1998m;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C6.b f9818a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0738e0 f9819b;
    public final ArrayList c;
    public final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f0 f9820e;

    public e(C6.b bVar) {
        A7.m.f(bVar, "navigationLogger");
        this.f9818a = bVar;
        this.f9819b = C0735d.O(StingrayKaraokeTabScreen.Homepage, S.f8224q);
        this.c = new ArrayList();
        this.d = new LinkedHashMap();
        this.f9820e = AbstractC0828q.c(Boolean.FALSE);
    }

    public static String c(String str, String str2, SourceType sourceType) {
        if (str2 != null) {
            str = str + "?source=" + str2;
        }
        if (sourceType == null) {
            return str;
        }
        return android.hardware.bydauto.ac.tsimpl.a.h(str, str2 != null ? "&" : "?") + "source_type=" + sourceType.name();
    }

    public static F6.b d(C1930E c1930e) {
        Object next;
        C1945j c1945jH;
        Bundle bundleC;
        C1945j c1945jH2;
        Bundle bundleC2;
        String string = (c1930e == null || (c1945jH2 = c1930e.h()) == null || (bundleC2 = c1945jH2.c()) == null) ? null : bundleC2.getString("source_type");
        String string2 = (c1930e == null || (c1945jH = c1930e.h()) == null || (bundleC = c1945jH.c()) == null) ? null : bundleC.getString(BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (string == null) {
            return null;
        }
        SourceType.Companion.getClass();
        Iterator<E> it = SourceType.getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (A7.m.a(((SourceType) next).name(), string)) {
                break;
            }
        }
        SourceType sourceType = (SourceType) next;
        if (sourceType == null || string2 == null) {
            return null;
        }
        String strDecode = URLDecoder.decode(string2, "utf-8");
        A7.m.e(strDecode, "decode(...)");
        return new F6.b(sourceType, strDecode);
    }

    public final StingrayKaraokeTabScreen a() {
        return (StingrayKaraokeTabScreen) this.f9819b.getValue();
    }

    public final C1930E b(NavigationType navigationType) {
        A7.m.f(navigationType, "navigationType");
        LinkedHashMap linkedHashMap = this.d;
        C1930E c1930e = (C1930E) linkedHashMap.get(navigationType);
        if (c1930e != null) {
            return c1930e;
        }
        Object obj = linkedHashMap.get(NavigationType.MAIN);
        A7.m.c(obj);
        return (C1930E) obj;
    }

    public final void e() {
        SourceType sourceType;
        F6.b bVarD = d((C1930E) this.d.get(NavigationType.MAIN));
        String str = (bVarD == null || (sourceType = bVarD.f2425a) == null || !(sourceType == SourceType.SWIMLANE || sourceType == SourceType.PROMO_BANNER)) ? null : bVarD.f2426b;
        ArrayList arrayList = this.c;
        boolean zIsEmpty = arrayList.isEmpty();
        C6.b bVar = this.f9818a;
        if (zIsEmpty) {
            bVar.a(a().name(), null);
        } else {
            String str2 = (String) AbstractC1998m.h0(arrayList);
            bVar.a(Q8.h.b1(str2, "?", str2), str);
        }
    }

    public final boolean f() {
        io.sentry.hints.i iVar = k6.o.f18284a;
        if (io.sentry.hints.i.h()) {
            return false;
        }
        ArrayList arrayList = this.c;
        boolean zIsEmpty = arrayList.isEmpty();
        LinkedHashMap linkedHashMap = this.d;
        if (!zIsEmpty) {
            arrayList.remove(AbstractC1999n.C(arrayList));
            C1930E c1930e = (C1930E) linkedHashMap.get(NavigationType.MAIN);
            if (c1930e != null) {
                c1930e.q();
            }
            e();
            return true;
        }
        StingrayKaraokeTabScreen stingrayKaraokeTabScreenA = a();
        StingrayKaraokeTabScreen stingrayKaraokeTabScreen = StingrayKaraokeTabScreen.Homepage;
        if (stingrayKaraokeTabScreenA == stingrayKaraokeTabScreen) {
            return false;
        }
        j(stingrayKaraokeTabScreen);
        C1930E c1930e2 = (C1930E) linkedHashMap.get(NavigationType.TAB);
        if (c1930e2 != null) {
            C1930E.p(c1930e2, a().name(), null, 6);
        }
        e();
        return true;
    }

    public final void g(NavigationRoutes navigationRoutes, String str, F6.b bVar) {
        String strConcat;
        A7.m.f(navigationRoutes, "route");
        String value = navigationRoutes.getValue();
        if (str == null || (strConcat = "/".concat(str)) == null) {
            strConcat = "";
        }
        String strC = c(android.hardware.bydauto.ac.tsimpl.a.h(value, strConcat), bVar != null ? bVar.f2426b : null, bVar != null ? bVar.f2425a : null);
        ArrayList arrayList = this.c;
        if (A7.m.a(strC, AbstractC1998m.i0(arrayList))) {
            return;
        }
        arrayList.add(strC);
        C1930E c1930e = (C1930E) this.d.get(NavigationType.MAIN);
        if (c1930e != null) {
            C1930E.p(c1930e, strC, null, 6);
        }
        e();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0082 A[PHI: r4
  0x0082: PHI (r4v8 java.lang.String) = (r4v2 java.lang.String), (r4v2 java.lang.String), (r4v3 java.lang.String) binds: [B:24:0x0080, B:29:0x008b, B:77:0x0082] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010a A[LOOP:0: B:14:0x004c->B:58:0x010a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010f A[EDGE_INSN: B:75:0x010f->B:60:0x010f BREAK  A[LOOP:0: B:14:0x004c->B:58:0x010a], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W5.e.h(com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen, boolean):void");
    }

    public final void i(String str) {
        A7.m.f(str, "name");
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            if (Q8.h.C0((CharSequence) arrayList.get(size), str, false)) {
                arrayList.remove(size);
                C1930E c1930e = (C1930E) this.d.get(NavigationType.MAIN);
                if (c1930e != null) {
                    c1930e.q();
                }
            }
        }
    }

    public final void j(StingrayKaraokeTabScreen stingrayKaraokeTabScreen) {
        A7.m.f(stingrayKaraokeTabScreen, "<set-?>");
        this.f9819b.setValue(stingrayKaraokeTabScreen);
    }

    public final void k() {
        f0 f0Var = this.f9820e;
        if (((Boolean) f0Var.getValue()).booleanValue()) {
            return;
        }
        Boolean bool = Boolean.TRUE;
        f0Var.getClass();
        f0Var.m(null, bool);
        this.f9818a.a("Player Screen", null);
    }
}
