package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.j;
import androidx.navigation.o;
import androidx.navigation.r;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: FragmentNavigator.java */
/* JADX INFO: loaded from: classes.dex */
@r.b("fragment")
public class a extends r<C0025a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2672a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final FragmentManager f2673b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2674c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ArrayDeque<Integer> f2675d = new ArrayDeque<>();

    /* JADX INFO: renamed from: androidx.navigation.fragment.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentNavigator.java */
    public static class C0025a extends j {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private String f2676o;

        public C0025a(r<? extends C0025a> rVar) {
            super(rVar);
        }

        @Override // androidx.navigation.j
        public void p(Context context, AttributeSet attributeSet) {
            super.p(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, c.FragmentNavigator);
            String string = typedArrayObtainAttributes.getString(c.FragmentNavigator_android_name);
            if (string != null) {
                w(string);
            }
            typedArrayObtainAttributes.recycle();
        }

        @Override // androidx.navigation.j
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            String str = this.f2676o;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            return sb.toString();
        }

        public final String v() {
            String str = this.f2676o;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("Fragment class was not set");
        }

        public final C0025a w(String str) {
            this.f2676o = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: FragmentNavigator.java */
    public static final class b implements r.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final LinkedHashMap<View, String> f2677a;

        public Map<View, String> a() {
            return Collections.unmodifiableMap(this.f2677a);
        }
    }

    public a(Context context, FragmentManager fragmentManager, int i7) {
        this.f2672a = context;
        this.f2673b = fragmentManager;
        this.f2674c = i7;
    }

    private String g(int i7, int i8) {
        return i7 + "-" + i8;
    }

    @Override // androidx.navigation.r
    public void c(Bundle bundle) {
        int[] intArray;
        if (bundle == null || (intArray = bundle.getIntArray("androidx-nav-fragment:navigator:backStackIds")) == null) {
            return;
        }
        this.f2675d.clear();
        for (int i7 : intArray) {
            this.f2675d.add(Integer.valueOf(i7));
        }
    }

    @Override // androidx.navigation.r
    public Bundle d() {
        Bundle bundle = new Bundle();
        int[] iArr = new int[this.f2675d.size()];
        Iterator<Integer> it = this.f2675d.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            iArr[i7] = it.next().intValue();
            i7++;
        }
        bundle.putIntArray("androidx-nav-fragment:navigator:backStackIds", iArr);
        return bundle;
    }

    @Override // androidx.navigation.r
    public boolean e() {
        if (this.f2675d.isEmpty()) {
            return false;
        }
        if (this.f2673b.K0()) {
            Log.i("FragmentNavigator", "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        this.f2673b.U0(g(this.f2675d.size(), this.f2675d.peekLast().intValue()), 1);
        this.f2675d.removeLast();
        return true;
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public C0025a a() {
        return new C0025a(this);
    }

    @Deprecated
    public Fragment h(Context context, FragmentManager fragmentManager, String str, Bundle bundle) {
        return fragmentManager.q0().a(context.getClassLoader(), str);
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public j b(C0025a c0025a, Bundle bundle, o oVar, r.a aVar) {
        if (this.f2673b.K0()) {
            Log.i("FragmentNavigator", "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String strV = c0025a.v();
        boolean z6 = false;
        if (strV.charAt(0) == '.') {
            strV = this.f2672a.getPackageName() + strV;
        }
        Fragment fragmentH = h(this.f2672a, this.f2673b, strV, bundle);
        fragmentH.C1(bundle);
        androidx.fragment.app.r rVarL = this.f2673b.l();
        int iA = oVar != null ? oVar.a() : -1;
        int iB = oVar != null ? oVar.b() : -1;
        int iC = oVar != null ? oVar.c() : -1;
        int iD = oVar != null ? oVar.d() : -1;
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
            rVarL.s(iA, iB, iC, iD);
        }
        rVarL.q(this.f2674c, fragmentH);
        rVarL.u(fragmentH);
        int iK = c0025a.k();
        boolean zIsEmpty = this.f2675d.isEmpty();
        boolean z7 = oVar != null && !zIsEmpty && oVar.g() && this.f2675d.peekLast().intValue() == iK;
        if (zIsEmpty) {
            z6 = true;
        } else if (!z7) {
            rVarL.h(g(this.f2675d.size() + 1, iK));
            z6 = true;
        } else if (this.f2675d.size() > 1) {
            this.f2673b.U0(g(this.f2675d.size(), this.f2675d.peekLast().intValue()), 1);
            rVarL.h(g(this.f2675d.size(), iK));
        }
        if (aVar instanceof b) {
            for (Map.Entry<View, String> entry : ((b) aVar).a().entrySet()) {
                rVarL.g(entry.getKey(), entry.getValue());
            }
        }
        rVarL.v(true);
        rVarL.i();
        if (!z6) {
            return null;
        }
        this.f2675d.add(Integer.valueOf(iK));
        return c0025a;
    }
}
