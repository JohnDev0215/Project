package androidx.preference;

import android.R;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.in3;
import defpackage.n32;
import defpackage.om3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: PreferenceGroupAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends RecyclerView.e<n32> implements Preference.b {
    public final PreferenceGroup h;
    public ArrayList i;
    public ArrayList j;
    public final ArrayList k;
    public final a m = new a();
    public final Handler l = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: PreferenceGroupAdapter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            e.this.H();
        }
    }

    /* JADX INFO: compiled from: PreferenceGroupAdapter.java */
    public static class b {
        public final int a;
        public final int b;
        public final String c;

        public b(Preference preference) {
            this.c = preference.getClass().getName();
            this.a = preference.f17J;
            this.b = preference.K;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a == bVar.a && this.b == bVar.b && TextUtils.equals(this.c, bVar.c);
        }

        public final int hashCode() {
            return this.c.hashCode() + ((((527 + this.a) * 31) + this.b) * 31);
        }
    }

    public e(PreferenceScreen preferenceScreen) {
        this.h = preferenceScreen;
        preferenceScreen.setOnPreferenceChangeInternalListener(this);
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        B(preferenceScreen.b0);
        H();
    }

    public static boolean G(PreferenceGroup preferenceGroup) {
        return preferenceGroup.Y != Integer.MAX_VALUE;
    }

    public final ArrayList D(PreferenceGroup preferenceGroup) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int iB0 = preferenceGroup.b0();
        int i = 0;
        for (int i2 = 0; i2 < iB0; i2++) {
            Preference preferenceA0 = preferenceGroup.a0(i2);
            if (preferenceA0.B) {
                if (!G(preferenceGroup) || i < preferenceGroup.Y) {
                    arrayList.add(preferenceA0);
                } else {
                    arrayList2.add(preferenceA0);
                }
                if (preferenceA0 instanceof PreferenceGroup) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) preferenceA0;
                    if (!(!(preferenceGroup2 instanceof PreferenceScreen))) {
                        continue;
                    } else {
                        if (G(preferenceGroup) && G(preferenceGroup2)) {
                            throw new IllegalStateException("Nesting an expandable group inside of another expandable group is not supported!");
                        }
                        for (Preference preference : D(preferenceGroup2)) {
                            if (!G(preferenceGroup) || i < preferenceGroup.Y) {
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
        if (G(preferenceGroup) && i > preferenceGroup.Y) {
            androidx.preference.b bVar = new androidx.preference.b(preferenceGroup.e, arrayList2, preferenceGroup.g);
            bVar.setOnPreferenceClickListener(new f(this, preferenceGroup));
            arrayList.add(bVar);
        }
        return arrayList;
    }

    public final void E(PreferenceGroup preferenceGroup, ArrayList arrayList) {
        synchronized (preferenceGroup) {
            Collections.sort(preferenceGroup.U);
        }
        int iB0 = preferenceGroup.b0();
        for (int i = 0; i < iB0; i++) {
            Preference preferenceA0 = preferenceGroup.a0(i);
            arrayList.add(preferenceA0);
            b bVar = new b(preferenceA0);
            if (!this.k.contains(bVar)) {
                this.k.add(bVar);
            }
            if (preferenceA0 instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preferenceA0;
                if (!(preferenceGroup2 instanceof PreferenceScreen)) {
                    E(preferenceGroup2, arrayList);
                }
            }
            preferenceA0.setOnPreferenceChangeInternalListener(this);
        }
    }

    public final Preference F(int i) {
        if (i < 0 || i >= k()) {
            return null;
        }
        return (Preference) this.j.get(i);
    }

    public final void H() {
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            ((Preference) it.next()).setOnPreferenceChangeInternalListener(null);
        }
        ArrayList arrayList = new ArrayList(this.i.size());
        this.i = arrayList;
        PreferenceGroup preferenceGroup = this.h;
        E(preferenceGroup, arrayList);
        this.j = D(preferenceGroup);
        o();
        Iterator it2 = this.i.iterator();
        while (it2.hasNext()) {
            ((Preference) it2.next()).getClass();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.j.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final long l(int i) {
        if (this.f) {
            return F(i).n();
        }
        return -1L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i) {
        b bVar = new b(F(i));
        ArrayList arrayList = this.k;
        int iIndexOf = arrayList.indexOf(bVar);
        if (iIndexOf != -1) {
            return iIndexOf;
        }
        int size = arrayList.size();
        arrayList.add(bVar);
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        ColorStateList colorStateList;
        n32 n32Var = (n32) a0Var;
        Preference preferenceF = F(i);
        View view = n32Var.e;
        Drawable background = view.getBackground();
        Drawable drawable = n32Var.y;
        if (background != drawable) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(view, drawable);
        }
        TextView textView = (TextView) n32Var.u(R.id.title);
        if (textView != null && (colorStateList = n32Var.z) != null && !textView.getTextColors().equals(colorStateList)) {
            textView.setTextColor(colorStateList);
        }
        preferenceF.y(n32Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        b bVar = (b) this.k.get(i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(recyclerView.getContext());
        TypedArray typedArrayObtainStyledAttributes = recyclerView.getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.BackgroundStyle);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BackgroundStyle_android_selectableItemBackground);
        if (drawable == null) {
            drawable = AppCompatResources.a(recyclerView.getContext(), R.drawable.list_selector_background);
        }
        typedArrayObtainStyledAttributes.recycle();
        View viewInflate = layoutInflaterFrom.inflate(bVar.a, (ViewGroup) recyclerView, false);
        if (viewInflate.getBackground() == null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(viewInflate, drawable);
        }
        ViewGroup viewGroup = (ViewGroup) viewInflate.findViewById(R.id.widget_frame);
        if (viewGroup != null) {
            int i2 = bVar.b;
            if (i2 != 0) {
                layoutInflaterFrom.inflate(i2, viewGroup);
            } else {
                viewGroup.setVisibility(8);
            }
        }
        return new n32(viewInflate);
    }
}
