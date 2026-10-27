package androidx.preference;

import android.R;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.preference.DialogPreference;
import androidx.preference.d;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class PreferenceFragment extends Fragment implements d.c, d.a, d.b, DialogPreference.a {
    private androidx.preference.d d;
    RecyclerView e;
    private boolean f;
    private Context g;
    private Runnable i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f1043c = new c();
    private int h = R$layout.preference_list_fragment;
    private final Handler j = new a();
    private final Runnable k = new b();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PreferenceFragment.this.e();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
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
        public void run() {
            RecyclerView recyclerView = PreferenceFragment.this.e;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class c extends RecyclerView.l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Drawable f1046a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1048c = true;

        c() {
        }

        private boolean m(View view, RecyclerView recyclerView) {
            RecyclerView.A aE0 = recyclerView.e0(view);
            boolean z = false;
            if (!((aE0 instanceof androidx.preference.f) && ((androidx.preference.f) aE0).O())) {
                return false;
            }
            boolean z2 = this.f1048c;
            int iIndexOfChild = recyclerView.indexOfChild(view);
            if (iIndexOfChild >= recyclerView.getChildCount() - 1) {
                return z2;
            }
            RecyclerView.A aE02 = recyclerView.e0(recyclerView.getChildAt(iIndexOfChild + 1));
            if ((aE02 instanceof androidx.preference.f) && ((androidx.preference.f) aE02).N()) {
                z = true;
            }
            return z;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.x xVar) {
            if (m(view, recyclerView)) {
                rect.bottom = this.f1047b;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.x xVar) {
            if (this.f1046a == null) {
                return;
            }
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (m(childAt, recyclerView)) {
                    int y = ((int) childAt.getY()) + childAt.getHeight();
                    this.f1046a.setBounds(0, y, width, this.f1047b + y);
                    this.f1046a.draw(canvas);
                }
            }
        }

        public void j(boolean z) {
            this.f1048c = z;
        }

        public void k(Drawable drawable) {
            if (drawable != null) {
                this.f1047b = drawable.getIntrinsicHeight();
            } else {
                this.f1047b = 0;
            }
            this.f1046a = drawable;
            PreferenceFragment.this.e.v0();
        }

        public void l(int i) {
            this.f1047b = i;
            PreferenceFragment.this.e.v0();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d {
        boolean a(@NonNull PreferenceFragment preferenceFragment, Preference preference);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface e {
        boolean a(PreferenceFragment preferenceFragment, Preference preference);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface f {
        boolean a(PreferenceFragment preferenceFragment, PreferenceScreen preferenceScreen);
    }

    private void q() {
        PreferenceScreen preferenceScreenH = h();
        if (preferenceScreenH != null) {
            preferenceScreenH.P();
        }
        n();
    }

    @Override // androidx.preference.d.a
    @Deprecated
    public void a(Preference preference) {
        DialogFragment dialogFragmentI;
        boolean zA = f() instanceof d ? ((d) f()).a(this, preference) : false;
        if (!zA && (getActivity() instanceof d)) {
            zA = ((d) getActivity()).a(this, preference);
        }
        if (!zA && getFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                dialogFragmentI = EditTextPreferenceDialogFragment.i(preference.p());
            } else if (preference instanceof ListPreference) {
                dialogFragmentI = ListPreferenceDialogFragment.i(preference.p());
            } else {
                if (!(preference instanceof MultiSelectListPreference)) {
                    throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
                }
                dialogFragmentI = MultiSelectListPreferenceDialogFragment.i(preference.p());
            }
            dialogFragmentI.setTargetFragment(this, 0);
            dialogFragmentI.show(getFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
        }
    }

    @Override // androidx.preference.d.b
    @Deprecated
    public void b(PreferenceScreen preferenceScreen) {
        if ((f() instanceof f ? ((f) f()).a(this, preferenceScreen) : false) || !(getActivity() instanceof f)) {
            return;
        }
        ((f) getActivity()).a(this, preferenceScreen);
    }

    @Override // androidx.preference.d.c
    @Deprecated
    public boolean c(Preference preference) {
        if (preference.l() == null) {
            return false;
        }
        boolean zA = f() instanceof e ? ((e) f()).a(this, preference) : false;
        return (zA || !(getActivity() instanceof e)) ? zA : ((e) getActivity()).a(this, preference);
    }

    @Override // androidx.preference.DialogPreference.a
    @Deprecated
    public <T extends Preference> T d(CharSequence charSequence) {
        androidx.preference.d dVar = this.d;
        if (dVar == null) {
            return null;
        }
        return (T) dVar.a(charSequence);
    }

    void e() {
        PreferenceScreen preferenceScreenH = h();
        if (preferenceScreenH != null) {
            g().l1(j(preferenceScreenH));
            preferenceScreenH.L();
        }
        i();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Fragment f() {
        return null;
    }

    @Deprecated
    public final RecyclerView g() {
        return this.e;
    }

    @Deprecated
    public PreferenceScreen h() {
        return this.d.h();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    protected void i() {
    }

    @Deprecated
    protected RecyclerView.Adapter j(PreferenceScreen preferenceScreen) {
        return new androidx.preference.c(preferenceScreen);
    }

    @Deprecated
    public RecyclerView.m k() {
        return new LinearLayoutManager(getActivity());
    }

    @Deprecated
    public abstract void l(Bundle bundle, String str);

    @Deprecated
    public RecyclerView m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (this.g.getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R$id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R$layout.preference_recyclerview, viewGroup, false);
        recyclerView2.o1(k());
        recyclerView2.k1(new androidx.preference.e(recyclerView2));
        return recyclerView2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    protected void n() {
    }

    @Deprecated
    public void o(Drawable drawable) {
        this.f1043c.k(drawable);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R$attr.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R$style.PreferenceThemeOverlay;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), i);
        this.g = contextThemeWrapper;
        androidx.preference.d dVar = new androidx.preference.d(contextThemeWrapper);
        this.d = dVar;
        dVar.k(this);
        l(bundle, getArguments() != null ? getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = this.g;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.PreferenceFragment, androidx.core.content.res.f.a(context, R$attr.preferenceFragmentStyle, R.attr.preferenceFragmentStyle), 0);
        this.h = typedArrayObtainStyledAttributes.getResourceId(R$styleable.PreferenceFragment_android_layout, this.h);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.PreferenceFragment_android_divider);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.PreferenceFragment_android_dividerHeight, -1);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.PreferenceFragment_allowDividerAfterLastItem, true);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(this.g);
        View viewInflate = layoutInflaterCloneInContext.inflate(this.h, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(R.id.list_container);
        if (!(viewFindViewById instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
        RecyclerView recyclerViewM = m(layoutInflaterCloneInContext, viewGroup2, bundle);
        if (recyclerViewM == null) {
            throw new RuntimeException("Could not create RecyclerView");
        }
        this.e = recyclerViewM;
        recyclerViewM.g(this.f1043c);
        o(drawable);
        if (dimensionPixelSize != -1) {
            p(dimensionPixelSize);
        }
        this.f1043c.j(z);
        if (this.e.getParent() == null) {
            viewGroup2.addView(this.e);
        }
        this.j.post(this.k);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        this.j.removeCallbacks(this.k);
        this.j.removeMessages(1);
        if (this.f) {
            q();
        }
        this.e = null;
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreenH = h();
        if (preferenceScreenH != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreenH.f0(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.d.l(this);
        this.d.j(this);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.d.l(null);
        this.d.j(null);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreenH;
        super.onViewCreated(view, bundle);
        if (bundle != null && (bundle2 = bundle.getBundle("android:preferences")) != null && (preferenceScreenH = h()) != null) {
            preferenceScreenH.e0(bundle2);
        }
        if (this.f) {
            e();
            Runnable runnable = this.i;
            if (runnable != null) {
                runnable.run();
                this.i = null;
            }
        }
    }

    @Deprecated
    public void p(int i) {
        this.f1043c.l(i);
    }
}
