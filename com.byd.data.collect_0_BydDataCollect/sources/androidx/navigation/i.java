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
public class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final HashMap<String, Class<?>> f1699b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1700c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private j f1701d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1702e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f1703f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private CharSequence f1704g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ArrayList<g> f1705h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private b.d.h<c> f1706i;
    private HashMap<String, d> j;

    /* JADX INFO: compiled from: NavDestination.java */
    static class a implements Comparable<a> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final i f1707b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Bundle f1708c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f1709d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f1710e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final int f1711f;

        a(i iVar, Bundle bundle, boolean z, boolean z2, int i2) {
            this.f1707b = iVar;
            this.f1708c = bundle;
            this.f1709d = z;
            this.f1710e = z2;
            this.f1711f = i2;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            boolean z = this.f1709d;
            if (z && !aVar.f1709d) {
                return 1;
            }
            if (!z && aVar.f1709d) {
                return -1;
            }
            Bundle bundle = this.f1708c;
            if (bundle != null && aVar.f1708c == null) {
                return 1;
            }
            if (bundle == null && aVar.f1708c != null) {
                return -1;
            }
            if (bundle != null) {
                int size = bundle.size() - aVar.f1708c.size();
                if (size > 0) {
                    return 1;
                }
                if (size < 0) {
                    return -1;
                }
            }
            boolean z2 = this.f1710e;
            if (z2 && !aVar.f1710e) {
                return 1;
            }
            if (z2 || !aVar.f1710e) {
                return this.f1711f - aVar.f1711f;
            }
            return -1;
        }

        i b() {
            return this.f1707b;
        }

        Bundle c() {
            return this.f1708c;
        }
    }

    public i(q<? extends i> qVar) {
        this(r.c(qVar.getClass()));
    }

    static String h(Context context, int i2) {
        if (i2 <= 16777215) {
            return Integer.toString(i2);
        }
        try {
            return context.getResources().getResourceName(i2);
        } catch (Resources.NotFoundException unused) {
            return Integer.toString(i2);
        }
    }

    public final void a(String str, d dVar) {
        if (this.j == null) {
            this.j = new HashMap<>();
        }
        this.j.put(str, dVar);
    }

    public final void b(g gVar) {
        if (this.f1705h == null) {
            this.f1705h = new ArrayList<>();
        }
        this.f1705h.add(gVar);
    }

    Bundle c(Bundle bundle) {
        HashMap<String, d> map;
        if (bundle == null && ((map = this.j) == null || map.isEmpty())) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        HashMap<String, d> map2 = this.j;
        if (map2 != null) {
            for (Map.Entry<String, d> entry : map2.entrySet()) {
                entry.getValue().c(entry.getKey(), bundle2);
            }
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            HashMap<String, d> map3 = this.j;
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

    int[] d() {
        ArrayDeque arrayDeque = new ArrayDeque();
        i iVar = this;
        while (true) {
            j jVarK = iVar.k();
            if (jVarK == null || jVarK.w() != iVar.i()) {
                arrayDeque.addFirst(iVar);
            }
            if (jVarK == null) {
                break;
            }
            iVar = jVarK;
        }
        int[] iArr = new int[arrayDeque.size()];
        int i2 = 0;
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            iArr[i2] = ((i) it.next()).i();
            i2++;
        }
        return iArr;
    }

    public final c e(int i2) {
        b.d.h<c> hVar = this.f1706i;
        c cVarG = hVar == null ? null : hVar.g(i2);
        if (cVarG != null) {
            return cVarG;
        }
        if (k() != null) {
            return k().e(i2);
        }
        return null;
    }

    public final Map<String, d> f() {
        HashMap<String, d> map = this.j;
        return map == null ? Collections.emptyMap() : Collections.unmodifiableMap(map);
    }

    public String g() {
        if (this.f1703f == null) {
            this.f1703f = Integer.toString(this.f1702e);
        }
        return this.f1703f;
    }

    public final int i() {
        return this.f1702e;
    }

    public final String j() {
        return this.f1700c;
    }

    public final j k() {
        return this.f1701d;
    }

    a l(h hVar) {
        ArrayList<g> arrayList = this.f1705h;
        if (arrayList == null) {
            return null;
        }
        a aVar = null;
        for (g gVar : arrayList) {
            Uri uriC = hVar.c();
            Bundle bundleC = uriC != null ? gVar.c(uriC, f()) : null;
            String strA = hVar.a();
            boolean z = strA != null && strA.equals(gVar.b());
            String strB = hVar.b();
            int iD = strB != null ? gVar.d(strB) : -1;
            if (bundleC != null || z || iD > -1) {
                a aVar2 = new a(this, bundleC, gVar.e(), z, iD);
                if (aVar == null || aVar2.compareTo(aVar) > 0) {
                    aVar = aVar2;
                }
            }
        }
        return aVar;
    }

    public void m(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, androidx.navigation.u.a.Navigator);
        o(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.Navigator_android_id, 0));
        this.f1703f = h(context, this.f1702e);
        p(typedArrayObtainAttributes.getText(androidx.navigation.u.a.Navigator_android_label));
        typedArrayObtainAttributes.recycle();
    }

    public final void n(int i2, c cVar) {
        if (r()) {
            if (i2 == 0) {
                throw new IllegalArgumentException("Cannot have an action with actionId 0");
            }
            if (this.f1706i == null) {
                this.f1706i = new b.d.h<>();
            }
            this.f1706i.l(i2, cVar);
            return;
        }
        throw new UnsupportedOperationException("Cannot add action " + i2 + " to " + this + " as it does not support actions, indicating that it is a terminal destination in your navigation graph and will never trigger actions.");
    }

    public final void o(int i2) {
        this.f1702e = i2;
        this.f1703f = null;
    }

    public final void p(CharSequence charSequence) {
        this.f1704g = charSequence;
    }

    final void q(j jVar) {
        this.f1701d = jVar;
    }

    boolean r() {
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        String str = this.f1703f;
        if (str == null) {
            sb.append("0x");
            sb.append(Integer.toHexString(this.f1702e));
        } else {
            sb.append(str);
        }
        sb.append(")");
        if (this.f1704g != null) {
            sb.append(" label=");
            sb.append(this.f1704g);
        }
        return sb.toString();
    }

    public i(String str) {
        this.f1700c = str;
    }
}
