package androidx.preference;

import android.R;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.d;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class c extends RecyclerView.Adapter<f> implements Preference.b {
    private PreferenceGroup d;
    private List<Preference> e;
    private List<Preference> f;
    private List<d> g;
    private Runnable i = new a();
    private Handler h = new Handler();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.J();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends f.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f1057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f1058b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d.AbstractC0038d f1059c;

        b(c cVar, List list, List list2, d.AbstractC0038d abstractC0038d) {
            this.f1057a = list;
            this.f1058b = list2;
            this.f1059c = abstractC0038d;
        }

        @Override // androidx.recyclerview.widget.f.b
        public boolean a(int i, int i2) {
            return this.f1059c.a((Preference) this.f1057a.get(i), (Preference) this.f1058b.get(i2));
        }

        @Override // androidx.recyclerview.widget.f.b
        public boolean b(int i, int i2) {
            return this.f1059c.b((Preference) this.f1057a.get(i), (Preference) this.f1058b.get(i2));
        }

        @Override // androidx.recyclerview.widget.f.b
        public int d() {
            return this.f1058b.size();
        }

        @Override // androidx.recyclerview.widget.f.b
        public int e() {
            return this.f1057a.size();
        }
    }

    /* JADX INFO: renamed from: androidx.preference.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0037c implements Preference.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ PreferenceGroup f1060c;

        C0037c(PreferenceGroup preferenceGroup) {
            this.f1060c = preferenceGroup;
        }

        @Override // androidx.preference.Preference.d
        public boolean b(Preference preference) {
            this.f1060c.K0(Integer.MAX_VALUE);
            c.this.a(preference);
            PreferenceGroup.a aVarG0 = this.f1060c.G0();
            if (aVarG0 == null) {
                return true;
            }
            aVarG0.a();
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1062b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f1063c;

        d(Preference preference) {
            this.f1063c = preference.getClass().getName();
            this.f1061a = preference.q();
            this.f1062b = preference.B();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return this.f1061a == dVar.f1061a && this.f1062b == dVar.f1062b && TextUtils.equals(this.f1063c, dVar.f1063c);
        }

        public int hashCode() {
            return ((((527 + this.f1061a) * 31) + this.f1062b) * 31) + this.f1063c.hashCode();
        }
    }

    public c(PreferenceGroup preferenceGroup) {
        this.d = preferenceGroup;
        this.d.n0(this);
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        PreferenceGroup preferenceGroup2 = this.d;
        if (preferenceGroup2 instanceof PreferenceScreen) {
            A(((PreferenceScreen) preferenceGroup2).M0());
        } else {
            A(true);
        }
        J();
    }

    private androidx.preference.a C(PreferenceGroup preferenceGroup, List<Preference> list) {
        androidx.preference.a aVar = new androidx.preference.a(preferenceGroup.j(), list, preferenceGroup.n());
        aVar.p0(new C0037c(preferenceGroup));
        return aVar;
    }

    private List<Preference> D(PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iI0 = preferenceGroup.I0();
        int i = 0;
        for (int i2 = 0; i2 < iI0; i2++) {
            Preference preferenceH0 = preferenceGroup.H0(i2);
            if (preferenceH0.H()) {
                if (!G(preferenceGroup) || i < preferenceGroup.F0()) {
                    arrayList.add(preferenceH0);
                } else {
                    arrayList2.add(preferenceH0);
                }
                if (preferenceH0 instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) preferenceH0;
                    if (!preferenceGroup2.J0()) {
                        continue;
                    } else {
                        if (G(preferenceGroup) && G(preferenceGroup2)) {
                            throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                        }
                        for (Preference preference : D(preferenceGroup2)) {
                            if (!G(preferenceGroup) || i < preferenceGroup.F0()) {
                                arrayList.add(preference);
                            } else {
                                arrayList2.add(preference);
                            }
                            i++;
                        }
                    }
                } else {
                    i++;
                }
            }
        }
        if (G(preferenceGroup) && i > preferenceGroup.F0()) {
            arrayList.add(C(preferenceGroup, arrayList2));
        }
        return arrayList;
    }

    private void E(List<Preference> list, PreferenceGroup preferenceGroup) {
        preferenceGroup.L0();
        int iI0 = preferenceGroup.I0();
        for (int i = 0; i < iI0; i++) {
            Preference preferenceH0 = preferenceGroup.H0(i);
            list.add(preferenceH0);
            d dVar = new d(preferenceH0);
            if (!this.g.contains(dVar)) {
                this.g.add(dVar);
            }
            if (preferenceH0 instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preferenceH0;
                if (preferenceGroup2.J0()) {
                    E(list, preferenceGroup2);
                }
            }
            preferenceH0.n0(this);
        }
    }

    private boolean G(PreferenceGroup preferenceGroup) {
        return preferenceGroup.F0() != Integer.MAX_VALUE;
    }

    public Preference F(int i) {
        if (i < 0 || i >= f()) {
            return null;
        }
        return this.f.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public void r(@NonNull f fVar, int i) {
        Preference preferenceF = F(i);
        fVar.P();
        preferenceF.M(fVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public f t(@NonNull ViewGroup viewGroup, int i) {
        d dVar = this.g.get(i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        TypedArray typedArrayObtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.BackgroundStyle);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BackgroundStyle_android_selectableItemBackground);
        if (drawable == null) {
            drawable = androidx.appcompat.a.a.a.b(viewGroup.getContext(), R.drawable.list_selector_background);
        }
        typedArrayObtainStyledAttributes.recycle();
        View viewInflate = layoutInflaterFrom.inflate(dVar.f1061a, viewGroup, false);
        if (viewInflate.getBackground() == null) {
            ViewCompat.N(viewInflate, drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(R.id.widget_frame);
        if (viewGroup2 != null) {
            int i2 = dVar.f1062b;
            if (i2 != 0) {
                layoutInflaterFrom.inflate(i2, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new f(viewInflate);
    }

    void J() {
        Iterator<Preference> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().n0(null);
        }
        ArrayList arrayList = new ArrayList(this.e.size());
        this.e = arrayList;
        E(arrayList, this.d);
        List<Preference> list = this.f;
        List<Preference> listD = D(this.d);
        this.f = listD;
        androidx.preference.d dVarX = this.d.x();
        if (dVarX == null || dVarX.f() == null) {
            k();
        } else {
            androidx.recyclerview.widget.f.b(new b(this, list, listD, dVarX.f())).c(this);
        }
        Iterator<Preference> it2 = this.e.iterator();
        while (it2.hasNext()) {
            it2.next().e();
        }
    }

    @Override // androidx.preference.Preference.b
    public void a(Preference preference) {
        this.h.removeCallbacks(this.i);
        this.h.post(this.i);
    }

    @Override // androidx.preference.Preference.b
    public void b(Preference preference) {
        int iIndexOf = this.f.indexOf(preference);
        if (iIndexOf != -1) {
            l(iIndexOf, preference);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int f() {
        return this.f.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long g(int i) {
        if (j()) {
            return F(i).n();
        }
        return -1L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int h(int i) {
        d dVar = new d(F(i));
        int iIndexOf = this.g.indexOf(dVar);
        if (iIndexOf != -1) {
            return iIndexOf;
        }
        int size = this.g.size();
        this.g.add(dVar);
        return size;
    }
}
