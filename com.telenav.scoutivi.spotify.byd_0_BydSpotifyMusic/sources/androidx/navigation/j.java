package androidx.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: NavDestination.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final HashMap<String, Class<?>> f2703n = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f2704e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private k f2705g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2706h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f2707i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CharSequence f2708j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ArrayList<g> f2709k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private n.h<c> f2710l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private HashMap<String, d> f2711m;

    /* JADX INFO: compiled from: NavDestination.java */
    static class a implements Comparable<a> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final j f2712e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final Bundle f2713g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final boolean f2714h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final boolean f2715i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final int f2716j;

        a(j jVar, Bundle bundle, boolean z6, boolean z7, int i7) {
            this.f2712e = jVar;
            this.f2713g = bundle;
            this.f2714h = z6;
            this.f2715i = z7;
            this.f2716j = i7;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            boolean z6 = this.f2714h;
            if (z6 && !aVar.f2714h) {
                return 1;
            }
            if (!z6 && aVar.f2714h) {
                return -1;
            }
            Bundle bundle = this.f2713g;
            if (bundle != null && aVar.f2713g == null) {
                return 1;
            }
            if (bundle == null && aVar.f2713g != null) {
                return -1;
            }
            if (bundle != null) {
                int size = bundle.size() - aVar.f2713g.size();
                if (size > 0) {
                    return 1;
                }
                if (size < 0) {
                    return -1;
                }
            }
            boolean z7 = this.f2715i;
            if (z7 && !aVar.f2715i) {
                return 1;
            }
            if (z7 || !aVar.f2715i) {
                return this.f2716j - aVar.f2716j;
            }
            return -1;
        }

        j b() {
            return this.f2712e;
        }

        Bundle c() {
            return this.f2713g;
        }
    }

    public j(r<? extends j> rVar) {
        this(s.c(rVar.getClass()));
    }

    static String j(Context context, int i7) {
        if (i7 <= 16777215) {
            return Integer.toString(i7);
        }
        try {
            return context.getResources().getResourceName(i7);
        } catch (Resources.NotFoundException unused) {
            return Integer.toString(i7);
        }
    }

    public final void c(String str, d dVar) {
        if (this.f2711m == null) {
            this.f2711m = new HashMap<>();
        }
        this.f2711m.put(str, dVar);
    }

    public final void d(g gVar) {
        if (this.f2709k == null) {
            this.f2709k = new ArrayList<>();
        }
        this.f2709k.add(gVar);
    }

    Bundle e(Bundle bundle) {
        HashMap<String, d> map;
        if (bundle == null && ((map = this.f2711m) == null || map.isEmpty())) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        HashMap<String, d> map2 = this.f2711m;
        if (map2 != null) {
            for (Map.Entry<String, d> entry : map2.entrySet()) {
                entry.getValue().c(entry.getKey(), bundle2);
            }
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            HashMap<String, d> map3 = this.f2711m;
            if (map3 != null) {
                for (Map.Entry<String, d> entry2 : map3.entrySet()) {
                    if (!entry2.getValue().d(entry2.getKey(), bundle2)) {
                        throw new IllegalArgumentException("Wrong argument type for '" + entry2.getKey() + "' in argument bundle. " + entry2.getValue().a().c() + " expected.");
                    }
                }
            }
        }
        return bundle2;
    }

    int[] f() {
        ArrayDeque arrayDeque = new ArrayDeque();
        j jVar = this;
        while (true) {
            k kVarN = jVar.n();
            if (kVarN == null || kVarN.z() != jVar.k()) {
                arrayDeque.addFirst(jVar);
            }
            if (kVarN == null) {
                break;
            }
            jVar = kVarN;
        }
        int[] iArr = new int[arrayDeque.size()];
        int i7 = 0;
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            iArr[i7] = ((j) it.next()).k();
            i7++;
        }
        return iArr;
    }

    public final c g(int i7) {
        n.h<c> hVar = this.f2710l;
        c cVarE = hVar == null ? null : hVar.e(i7);
        if (cVarE != null) {
            return cVarE;
        }
        if (n() != null) {
            return n().g(i7);
        }
        return null;
    }

    public final Map<String, d> h() {
        HashMap<String, d> map = this.f2711m;
        return map == null ? Collections.emptyMap() : Collections.unmodifiableMap(map);
    }

    public String i() {
        if (this.f2707i == null) {
            this.f2707i = Integer.toString(this.f2706h);
        }
        return this.f2707i;
    }

    public final int k() {
        return this.f2706h;
    }

    public final CharSequence l() {
        return this.f2708j;
    }

    public final String m() {
        return this.f2704e;
    }

    public final k n() {
        return this.f2705g;
    }

    a o(i iVar) {
        ArrayList<g> arrayList = this.f2709k;
        if (arrayList == null) {
            return null;
        }
        a aVar = null;
        for (g gVar : arrayList) {
            Uri uriC = iVar.c();
            Bundle bundleC = uriC != null ? gVar.c(uriC, h()) : null;
            String strA = iVar.a();
            boolean z6 = strA != null && strA.equals(gVar.b());
            String strB = iVar.b();
            int iD = strB != null ? gVar.d(strB) : -1;
            if (bundleC != null || z6 || iD > -1) {
                a aVar2 = new a(this, bundleC, gVar.e(), z6, iD);
                if (aVar == null || aVar2.compareTo(aVar) > 0) {
                    aVar = aVar2;
                }
            }
        }
        return aVar;
    }

    public void p(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, w0.a.Navigator);
        r(typedArrayObtainAttributes.getResourceId(w0.a.Navigator_android_id, 0));
        this.f2707i = j(context, this.f2706h);
        s(typedArrayObtainAttributes.getText(w0.a.Navigator_android_label));
        typedArrayObtainAttributes.recycle();
    }

    public final void q(int i7, c cVar) {
        if (u()) {
            if (i7 == 0) {
                throw new IllegalArgumentException("Cannot have an action with actionId 0");
            }
            if (this.f2710l == null) {
                this.f2710l = new n.h<>();
            }
            this.f2710l.i(i7, cVar);
            return;
        }
        throw new UnsupportedOperationException("Cannot add action " + i7 + " to " + this + " as it does not support actions, indicating that it is a terminal destination in your navigation graph and will never trigger actions.");
    }

    public final void r(int i7) {
        this.f2706h = i7;
        this.f2707i = null;
    }

    public final void s(CharSequence charSequence) {
        this.f2708j = charSequence;
    }

    final void t(k kVar) {
        this.f2705g = kVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        String str = this.f2707i;
        if (str == null) {
            sb.append("0x");
            sb.append(Integer.toHexString(this.f2706h));
        } else {
            sb.append(str);
        }
        sb.append(")");
        if (this.f2708j != null) {
            sb.append(" label=");
            sb.append(this.f2708j);
        }
        return sb.toString();
    }

    boolean u() {
        return true;
    }

    public j(String str) {
        this.f2704e = str;
    }
}
