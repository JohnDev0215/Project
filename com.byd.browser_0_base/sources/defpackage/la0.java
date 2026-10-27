package defpackage;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.h;
import defpackage.hk2;
import defpackage.r4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.browser_ui.share.a;

/* JADX INFO: compiled from: DirectedAcyclicGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class la0 implements rq {
    public final Object e;
    public final Object f;
    public final Object g;
    public Object h;

    public la0(int i) {
        if (i == 1) {
            this.e = new ArrayList();
            this.f = new HashMap();
            this.g = new HashMap();
        } else {
            this.f = new t22(10, 1);
            this.g = new wm2();
            this.e = new ArrayList();
            this.h = new HashSet();
        }
    }

    @Override // defpackage.rq
    public final void a(a aVar, jr jrVar, long j) {
        n(aVar, jrVar, false);
    }

    public final void b(Fragment fragment) {
        if (((ArrayList) this.e).contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (((ArrayList) this.e)) {
            ((ArrayList) this.e).add(fragment);
        }
        fragment.o = true;
    }

    public final void c() {
        ((HashMap) this.f).values().removeAll(Collections.singleton(null));
    }

    @Override // defpackage.rq
    public final void d(a aVar, jr jrVar, long j) {
        n(aVar, jrVar, true);
    }

    public final void e(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) ((wm2) this.g).get(obj);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                e(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    public final Fragment f(String str) {
        h hVar = (h) ((HashMap) this.f).get(str);
        if (hVar != null) {
            return hVar.c;
        }
        return null;
    }

    public final Fragment g(String str) {
        for (h hVar : ((HashMap) this.f).values()) {
            if (hVar != null) {
                Fragment fragmentG = hVar.c;
                if (!str.equals(fragmentG.i)) {
                    fragmentG = fragmentG.x.c.g(str);
                }
                if (fragmentG != null) {
                    return fragmentG;
                }
            }
        }
        return null;
    }

    public final ArrayList h() {
        ArrayList arrayList = new ArrayList();
        for (h hVar : ((HashMap) this.f).values()) {
            if (hVar != null) {
                arrayList.add(hVar);
            }
        }
        return arrayList;
    }

    public final ArrayList i() {
        ArrayList arrayList = new ArrayList();
        for (h hVar : ((HashMap) this.f).values()) {
            if (hVar != null) {
                arrayList.add(hVar.c);
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public final List j() {
        ArrayList arrayList;
        if (((ArrayList) this.e).isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (((ArrayList) this.e)) {
            arrayList = new ArrayList((ArrayList) this.e);
        }
        return arrayList;
    }

    public final void k(h hVar) {
        Fragment fragment = hVar.c;
        String str = fragment.i;
        Object obj = this.f;
        if (((HashMap) obj).get(str) != null) {
            return;
        }
        ((HashMap) obj).put(fragment.i, hVar);
    }

    public final void l(h hVar) {
        Fragment fragment = hVar.c;
        if (fragment.E) {
            mp0 mp0Var = (mp0) this.h;
            if (!mp0Var.h) {
                mp0Var.c.remove(fragment.i);
            }
        }
    }

    public final Bundle m(Bundle bundle, String str) {
        Object obj = this.g;
        return bundle != null ? (Bundle) ((HashMap) obj).put(str, bundle) : (Bundle) ((HashMap) obj).remove(str);
    }

    public final void n(a aVar, jr jrVar, boolean z) {
        ArrayList arrayList;
        boolean z2;
        Profile profile = (Profile) ((nw2) this.h).get();
        nw2 nw2Var = (nw2) this.e;
        boolean z3 = nw2Var.f() && ((y13) nw2Var.get()).r();
        ArrayList arrayList2 = null;
        if (z) {
            r4 r4Var = new r4((Activity) aVar.a.k().get(), aVar.a, (nw2) this.g, (ji) this.f, aVar, z3, this, oj2.t(profile), aVar.e, profile);
            boolean zIsInMultiWindowMode = ((Activity) aVar.a.k().get()).isInMultiWindowMode();
            try {
                Class.forName("android.service.chooser.ChooserAction$Builder");
                z2 = true;
            } catch (ClassNotFoundException unused) {
                z2 = false;
            }
            if (z2) {
                r4.a aVar2 = new r4.a(r4Var.a, r4Var.d(ak2.a(aVar, jrVar), jrVar.j, zIsInMultiWindowMode), aVar.n);
                Object obj = ThreadUtils.a;
                r4.a aVar3 = r4.m;
                if (aVar3 != null) {
                    r4.n = null;
                    xz.a.unregisterReceiver(aVar3);
                    r4.m = null;
                }
                r4.m = aVar2;
                xz.f(xz.a, aVar2, new IntentFilter(r4.g()));
                arrayList = aVar2.b;
            } else {
                arrayList2 = new ArrayList();
                arrayList = arrayList2;
            }
        } else {
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.size() == 0) {
            Log.i("cr_AndroidShare", "No custom actions provided.");
        }
        boolean z4 = jrVar.a;
        ShareHelper.c(0);
        if (z4) {
            aVar.n = new hk2.b(profile, aVar.n);
        }
        hk2.f(aVar.a, ShareHelper.b(aVar), aVar.n, arrayList);
    }

    public /* synthetic */ la0(ji jiVar, nw2 nw2Var, nw2 nw2Var2, nw2 nw2Var3) {
        this.f = jiVar;
        this.g = nw2Var;
        this.e = nw2Var2;
        this.h = nw2Var3;
    }
}
