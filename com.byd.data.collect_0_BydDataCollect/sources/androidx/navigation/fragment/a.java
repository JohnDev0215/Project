package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import androidx.fragment.app.o;
import androidx.navigation.i;
import androidx.navigation.n;
import androidx.navigation.q;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: FragmentNavigator.java */
/* JADX INFO: loaded from: classes.dex */
@q.b("fragment")
public class a extends q<C0024a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f1676b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1677c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ArrayDeque<Integer> f1678d = new ArrayDeque<>();

    /* JADX INFO: renamed from: androidx.navigation.fragment.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentNavigator.java */
    public static class C0024a extends i {
        private String k;

        public C0024a(q<? extends C0024a> qVar) {
            super(qVar);
        }

        @Override // androidx.navigation.i
        public void m(Context context, AttributeSet attributeSet) {
            super.m(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, c.FragmentNavigator);
            String string = typedArrayObtainAttributes.getString(c.FragmentNavigator_android_name);
            if (string != null) {
                t(string);
            }
            typedArrayObtainAttributes.recycle();
        }

        public final String s() {
            String str = this.k;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("Fragment class was not set");
        }

        public final C0024a t(String str) {
            this.k = str;
            return this;
        }

        @Override // androidx.navigation.i
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            String str = this.k;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: FragmentNavigator.java */
    public static final class b implements q.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final LinkedHashMap<View, String> f1679a;

        public Map<View, String> a() {
            return Collections.unmodifiableMap(this.f1679a);
        }
    }

    public a(Context context, j jVar, int i2) {
        this.f1675a = context;
        this.f1676b = jVar;
        this.f1677c = i2;
    }

    private String g(int i2, int i3) {
        return i2 + "-" + i3;
    }

    @Override // androidx.navigation.q
    public void c(Bundle bundle) {
        int[] intArray;
        if (bundle == null || (intArray = bundle.getIntArray("androidx-nav-fragment:navigator:backStackIds")) == null) {
            return;
        }
        this.f1678d.clear();
        for (int i2 : intArray) {
            this.f1678d.add(Integer.valueOf(i2));
        }
    }

    @Override // androidx.navigation.q
    public Bundle d() {
        Bundle bundle = new Bundle();
        int[] iArr = new int[this.f1678d.size()];
        Iterator<Integer> it = this.f1678d.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            iArr[i2] = it.next().intValue();
            i2++;
        }
        bundle.putIntArray("androidx-nav-fragment:navigator:backStackIds", iArr);
        return bundle;
    }

    @Override // androidx.navigation.q
    public boolean e() {
        if (this.f1678d.isEmpty()) {
            return false;
        }
        if (this.f1676b.u0()) {
            Log.i("FragmentNavigator", "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        this.f1676b.F0(g(this.f1678d.size(), this.f1678d.peekLast().intValue()), 1);
        this.f1678d.removeLast();
        return true;
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public C0024a a() {
        return new C0024a(this);
    }

    @Deprecated
    public Fragment h(Context context, j jVar, String str, Bundle bundle) {
        return jVar.f0().a(context.getClassLoader(), str);
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public i b(C0024a c0024a, Bundle bundle, n nVar, q.a aVar) {
        if (this.f1676b.u0()) {
            Log.i("FragmentNavigator", "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String strS = c0024a.s();
        boolean z = false;
        if (strS.charAt(0) == '.') {
            strS = this.f1675a.getPackageName() + strS;
        }
        Fragment fragmentH = h(this.f1675a, this.f1676b, strS, bundle);
        fragmentH.i1(bundle);
        o oVarI = this.f1676b.i();
        int iA = nVar != null ? nVar.a() : -1;
        int iB = nVar != null ? nVar.b() : -1;
        int iC = nVar != null ? nVar.c() : -1;
        int iD = nVar != null ? nVar.d() : -1;
        if (iA != -1 || iB != -1 || iC != -1 || iD != -1) {
            if (iA == -1) {
                iA = 0;
            }
            if (iB == -1) {
                iB = 0;
            }
            if (iC == -1) {
                iC = 0;
            }
            if (iD == -1) {
                iD = 0;
            }
            oVarI.s(iA, iB, iC, iD);
        }
        oVarI.q(this.f1677c, fragmentH);
        oVarI.t(fragmentH);
        int i2 = c0024a.i();
        boolean zIsEmpty = this.f1678d.isEmpty();
        boolean z2 = nVar != null && !zIsEmpty && nVar.g() && this.f1678d.peekLast().intValue() == i2;
        if (zIsEmpty) {
            z = true;
        } else if (!z2) {
            oVarI.g(g(this.f1678d.size() + 1, i2));
            z = true;
        } else if (this.f1678d.size() > 1) {
            this.f1676b.F0(g(this.f1678d.size(), this.f1678d.peekLast().intValue()), 1);
            oVarI.g(g(this.f1678d.size(), i2));
        }
        if (aVar instanceof b) {
            for (Map.Entry<View, String> entry : ((b) aVar).a().entrySet()) {
                oVarI.f(entry.getKey(), entry.getValue());
            }
        }
        oVarI.u(true);
        oVarI.i();
        if (!z) {
            return null;
        }
        this.f1678d.add(Integer.valueOf(i2));
        return c0024a;
    }
}
