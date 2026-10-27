package androidx.preference;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.l32;
import defpackage.m32;
import defpackage.n32;
import defpackage.qj1;
import defpackage.r90;
import defpackage.s81;

/* JADX INFO: compiled from: PreferenceFragmentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d extends Fragment implements PreferenceManager.c, PreferenceManager.a, PreferenceManager.b, DialogPreference.a {
    public PreferenceManager a0;
    public RecyclerView b0;
    public boolean c0;
    public boolean d0;
    public final c Z = new c();
    public int e0 = R$layout.preference_list_fragment;
    public final a f0 = new a(Looper.getMainLooper());
    public final b g0 = new b();

    /* JADX INFO: compiled from: PreferenceFragmentCompat.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            d dVar;
            PreferenceScreen preferenceScreenI0;
            if (message.what == 1 && (preferenceScreenI0 = (dVar = d.this).I0()) != null) {
                dVar.b0.setAdapter(new androidx.preference.e(preferenceScreenI0));
                preferenceScreenI0.v();
            }
        }
    }

    /* JADX INFO: compiled from: PreferenceFragmentCompat.java */
    public class b implements Runnable {
        public b() {
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public final void run() {
            RecyclerView recyclerView = d.this.b0;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    }

    /* JADX INFO: compiled from: PreferenceFragmentCompat.java */
    public class c extends RecyclerView.l {
        public Drawable a;
        public int b;
        public boolean c = true;

        public c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            if (j(view, recyclerView)) {
                rect.bottom = this.b;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void i(Canvas canvas, RecyclerView recyclerView) {
            if (this.a == null) {
                return;
            }
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (j(childAt, recyclerView)) {
                    int height = childAt.getHeight() + ((int) childAt.getY());
                    this.a.setBounds(0, height, width, this.b + height);
                    this.a.draw(canvas);
                }
            }
        }

        public final boolean j(View view, RecyclerView recyclerView) {
            RecyclerView.a0 a0VarJ = recyclerView.J(view);
            boolean z = false;
            if (!((a0VarJ instanceof n32) && ((n32) a0VarJ).C)) {
                return false;
            }
            boolean z2 = this.c;
            int iIndexOfChild = recyclerView.indexOfChild(view);
            if (iIndexOfChild >= recyclerView.getChildCount() - 1) {
                return z2;
            }
            RecyclerView.a0 a0VarJ2 = recyclerView.J(recyclerView.getChildAt(iIndexOfChild + 1));
            if ((a0VarJ2 instanceof n32) && ((n32) a0VarJ2).B) {
                z = true;
            }
            return z;
        }
    }

    /* JADX INFO: renamed from: androidx.preference.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PreferenceFragmentCompat.java */
    public interface InterfaceC0023d {
        boolean a();
    }

    /* JADX INFO: compiled from: PreferenceFragmentCompat.java */
    public interface e {
        void L0(Preference preference);
    }

    /* JADX INFO: compiled from: PreferenceFragmentCompat.java */
    public interface f {
        boolean a();
    }

    @Override // androidx.preference.PreferenceManager.b
    public final void B(PreferenceScreen preferenceScreen) {
        boolean zA = false;
        for (Fragment fragment = this; !zA && fragment != null; fragment = fragment.y) {
            if (fragment instanceof f) {
                zA = ((f) fragment).a();
            }
        }
        if (!zA && (K() instanceof f)) {
            zA = ((f) K()).a();
        }
        if (zA || !(D() instanceof f)) {
            return;
        }
        ((f) D()).a();
    }

    @Override // androidx.preference.PreferenceManager.c
    public boolean C(Preference preference) {
        if (preference.r == null) {
            return false;
        }
        boolean z = false;
        for (Fragment fragment = this; !z && fragment != null; fragment = fragment.y) {
            if (fragment instanceof e) {
                ((e) fragment).L0(preference);
                z = true;
            }
        }
        if (!z && (K() instanceof e)) {
            ((e) K()).L0(preference);
            z = true;
        }
        if (!z && (D() instanceof e)) {
            ((e) D()).L0(preference);
            z = true;
        }
        if (!z) {
            Log.w("PreferenceFragment", "onPreferenceStartFragment is not implemented in the parent activity - attempting to use a fallback implementation. You should implement this method so that you can configure the new fragment that will be displayed, and set a transition between the fragments.");
            FragmentManager fragmentManagerM = M();
            Bundle bundleM = preference.m();
            androidx.fragment.app.f fVarF = fragmentManagerM.F();
            v0().getClassLoader();
            Fragment fragmentA = fVarF.a(preference.r);
            fragmentA.C0(bundleM);
            fragmentA.E0(0, this);
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManagerM);
            aVar.e(((View) y0().getParent()).getId(), fragmentA, null);
            if (!aVar.h) {
                throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
            }
            aVar.g = true;
            aVar.i = null;
            aVar.g();
        }
        return true;
    }

    public final void H0(int i) {
        PreferenceManager preferenceManager = this.a0;
        if (preferenceManager == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
        Context contextX0 = x0();
        PreferenceScreen preferenceScreenI0 = I0();
        preferenceManager.e = true;
        l32 l32Var = new l32(contextX0, preferenceManager);
        XmlResourceParser xml = contextX0.getResources().getXml(i);
        try {
            PreferenceGroup preferenceGroupC = l32Var.c(xml, preferenceScreenI0);
            xml.close();
            PreferenceScreen preferenceScreen = (PreferenceScreen) preferenceGroupC;
            preferenceScreen.x(preferenceManager);
            SharedPreferences.Editor editor = preferenceManager.d;
            if (editor != null) {
                editor.apply();
            }
            preferenceManager.e = false;
            L0(preferenceScreen);
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    @SuppressLint({"UnknownNullness"})
    public final PreferenceScreen I0() {
        PreferenceManager preferenceManager = this.a0;
        if (preferenceManager == null) {
            return null;
        }
        return preferenceManager.g;
    }

    public abstract void J0(Bundle bundle, String str);

    public final void K0(Drawable drawable) {
        c cVar = this.Z;
        if (drawable != null) {
            cVar.getClass();
            cVar.b = drawable.getIntrinsicHeight();
        } else {
            cVar.b = 0;
        }
        cVar.a = drawable;
        d.this.b0.N();
    }

    public final void L0(@SuppressLint({"UnknownNullness"}) PreferenceScreen preferenceScreen) {
        boolean z;
        PreferenceManager preferenceManager = this.a0;
        PreferenceScreen preferenceScreen2 = preferenceManager.g;
        if (preferenceScreen != preferenceScreen2) {
            if (preferenceScreen2 != null) {
                preferenceScreen2.B();
            }
            preferenceManager.g = preferenceScreen;
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.c0 = true;
            if (this.d0) {
                a aVar = this.f0;
                if (aVar.hasMessages(1)) {
                    return;
                }
                aVar.obtainMessage(1).sendToTarget();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void c0(Bundle bundle) {
        super.c0(bundle);
        TypedValue typedValue = new TypedValue();
        x0().getTheme().resolveAttribute(R$attr.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R$style.PreferenceThemeOverlay;
        }
        x0().getTheme().applyStyle(i, false);
        PreferenceManager preferenceManager = new PreferenceManager(x0());
        this.a0 = preferenceManager;
        preferenceManager.setOnNavigateToScreenListener(this);
        Bundle bundle2 = this.j;
        J0(bundle, bundle2 != null ? bundle2.getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }

    @Override // androidx.fragment.app.Fragment
    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        TypedArray typedArrayObtainStyledAttributes = x0().obtainStyledAttributes(null, R$styleable.PreferenceFragmentCompat, R$attr.preferenceFragmentCompatStyle, 0);
        this.e0 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.PreferenceFragmentCompat_android_layout, this.e0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.PreferenceFragmentCompat_android_divider);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.PreferenceFragmentCompat_android_dividerHeight, -1);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.PreferenceFragmentCompat_allowDividerAfterLastItem, true);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(x0());
        View viewInflate = layoutInflaterCloneInContext.inflate(this.e0, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(R.id.list_container);
        if (!(viewFindViewById instanceof ViewGroup)) {
            throw new IllegalStateException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
        if (!x0().getPackageManager().hasSystemFeature("android.hardware.type.automotive") || (recyclerView = (RecyclerView) viewGroup2.findViewById(R$id.recycler_view)) == null) {
            recyclerView = (RecyclerView) layoutInflaterCloneInContext.inflate(R$layout.preference_recyclerview, viewGroup2, false);
            recyclerView.setLayoutManager(new LinearLayoutManager(x0()));
            recyclerView.setAccessibilityDelegateCompat(new m32(recyclerView));
        }
        this.b0 = recyclerView;
        c cVar = this.Z;
        recyclerView.g(cVar);
        K0(drawable);
        if (dimensionPixelSize != -1) {
            cVar.b = dimensionPixelSize;
            d.this.b0.N();
        }
        cVar.c = z;
        if (this.b0.getParent() == null) {
            viewGroup2.addView(this.b0);
        }
        this.f0.post(this.g0);
        return viewInflate;
    }

    @Override // androidx.preference.DialogPreference.a
    public final <T extends Preference> T f(CharSequence charSequence) {
        PreferenceScreen preferenceScreen;
        PreferenceManager preferenceManager = this.a0;
        if (preferenceManager == null || (preferenceScreen = preferenceManager.g) == null) {
            return null;
        }
        return (T) preferenceScreen.Z(charSequence);
    }

    @Override // androidx.fragment.app.Fragment
    public void g0() {
        b bVar = this.g0;
        a aVar = this.f0;
        aVar.removeCallbacks(bVar);
        aVar.removeMessages(1);
        if (this.c0) {
            this.b0.setAdapter(null);
            PreferenceScreen preferenceScreenI0 = I0();
            if (preferenceScreenI0 != null) {
                preferenceScreenI0.B();
            }
        }
        this.b0 = null;
        this.H = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void n0(Bundle bundle) {
        PreferenceScreen preferenceScreenI0 = I0();
        if (preferenceScreenI0 != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreenI0.l(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void o0() {
        this.H = true;
        this.a0.setOnPreferenceTreeClickListener(this);
        this.a0.setOnDisplayPreferenceDialogListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        this.H = true;
        this.a0.setOnPreferenceTreeClickListener(null);
        this.a0.setOnDisplayPreferenceDialogListener(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void q0(View view, Bundle bundle) {
        PreferenceScreen preferenceScreenI0;
        Bundle bundle2;
        PreferenceScreen preferenceScreenI02;
        if (bundle != null && (bundle2 = bundle.getBundle("android:preferences")) != null && (preferenceScreenI02 = I0()) != null) {
            preferenceScreenI02.j(bundle2);
        }
        if (this.c0 && (preferenceScreenI0 = I0()) != null) {
            this.b0.setAdapter(new androidx.preference.e(preferenceScreenI0));
            preferenceScreenI0.v();
        }
        this.d0 = true;
    }

    @Override // androidx.preference.PreferenceManager.a
    public void y(Preference preference) {
        r90 qj1Var;
        boolean zA = false;
        for (Fragment fragment = this; !zA && fragment != null; fragment = fragment.y) {
            if (fragment instanceof InterfaceC0023d) {
                zA = ((InterfaceC0023d) fragment).a();
            }
        }
        if (!zA && (K() instanceof InterfaceC0023d)) {
            zA = ((InterfaceC0023d) K()).a();
        }
        if (!zA && (D() instanceof InterfaceC0023d)) {
            zA = ((InterfaceC0023d) D()).a();
        }
        if (!zA && M().B("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                String str = preference.p;
                qj1Var = new androidx.preference.a();
                Bundle bundle = new Bundle(1);
                bundle.putString("key", str);
                qj1Var.C0(bundle);
            } else if (preference instanceof ListPreference) {
                String str2 = preference.p;
                qj1Var = new s81();
                Bundle bundle2 = new Bundle(1);
                bundle2.putString("key", str2);
                qj1Var.C0(bundle2);
            } else {
                if (!(preference instanceof MultiSelectListPreference)) {
                    throw new IllegalArgumentException("Cannot display dialog for an unknown Preference type: " + preference.getClass().getSimpleName() + ". Make sure to implement onPreferenceDisplayDialog() to handle displaying a custom dialog for this Preference.");
                }
                String str3 = preference.p;
                qj1Var = new qj1();
                Bundle bundle3 = new Bundle(1);
                bundle3.putString("key", str3);
                qj1Var.C0(bundle3);
            }
            qj1Var.E0(0, this);
            qj1Var.M0(M(), "androidx.preference.PreferenceFragment.DIALOG");
        }
    }
}
