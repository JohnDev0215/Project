package androidx.preference;

import android.R;
import android.app.DialogFragment;
import android.app.Fragment;
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
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.fh3;
import defpackage.m32;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class PreferenceFragment extends Fragment implements PreferenceManager.c, PreferenceManager.a, PreferenceManager.b, DialogPreference.a {
    public PreferenceManager f;
    public RecyclerView g;
    public ContextThemeWrapper h;
    public final c e = new c();
    public int i = R$layout.preference_list_fragment;
    public final a j = new a();
    public final b k = new b();

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PreferenceFragment preferenceFragment = PreferenceFragment.this;
            PreferenceScreen preferenceScreen = preferenceFragment.f.g;
            if (preferenceScreen != null) {
                preferenceFragment.g.setAdapter(new androidx.preference.e(preferenceScreen));
                preferenceScreen.v();
            }
        }
    }

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
            RecyclerView recyclerView = PreferenceFragment.this.g;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    }

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

    public interface d {
        boolean a();
    }

    public interface e {
        boolean a();
    }

    public interface f {
        boolean a();
    }

    @Override // androidx.preference.PreferenceManager.b
    @Deprecated
    public final void B(PreferenceScreen preferenceScreen) {
        if (getActivity() instanceof f) {
            ((f) getActivity()).a();
        }
    }

    @Override // androidx.preference.PreferenceManager.c
    @Deprecated
    public final boolean C(Preference preference) {
        if (preference.r == null || !(getActivity() instanceof e)) {
            return false;
        }
        return ((e) getActivity()).a();
    }

    @Deprecated
    public abstract void a(Bundle bundle, String str);

    @Override // androidx.preference.DialogPreference.a
    @Deprecated
    public final <T extends Preference> T f(CharSequence charSequence) {
        PreferenceScreen preferenceScreen;
        PreferenceManager preferenceManager = this.f;
        if (preferenceManager == null || (preferenceScreen = preferenceManager.g) == null) {
            return null;
        }
        return (T) preferenceScreen.Z(charSequence);
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R$attr.preferenceTheme, typedValue, true);
        int i = typedValue.resourceId;
        if (i == 0) {
            i = R$style.PreferenceThemeOverlay;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), i);
        this.h = contextThemeWrapper;
        PreferenceManager preferenceManager = new PreferenceManager(contextThemeWrapper);
        this.f = preferenceManager;
        preferenceManager.setOnNavigateToScreenListener(this);
        a(bundle, getArguments() != null ? getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        ContextThemeWrapper contextThemeWrapper = this.h;
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(null, R$styleable.PreferenceFragment, fh3.a(contextThemeWrapper, R$attr.preferenceFragmentStyle, R.attr.preferenceFragmentStyle), 0);
        this.i = typedArrayObtainStyledAttributes.getResourceId(R$styleable.PreferenceFragment_android_layout, this.i);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.PreferenceFragment_android_divider);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.PreferenceFragment_android_dividerHeight, -1);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.PreferenceFragment_allowDividerAfterLastItem, true);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(this.h);
        View viewInflate = layoutInflaterCloneInContext.inflate(this.i, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(R.id.list_container);
        if (!(viewFindViewById instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
        if (!this.h.getPackageManager().hasSystemFeature("android.hardware.type.automotive") || (recyclerView = (RecyclerView) viewGroup2.findViewById(R$id.recycler_view)) == null) {
            recyclerView = (RecyclerView) layoutInflaterCloneInContext.inflate(R$layout.preference_recyclerview, viewGroup2, false);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAccessibilityDelegateCompat(new m32(recyclerView));
        }
        this.g = recyclerView;
        c cVar = this.e;
        recyclerView.g(cVar);
        if (drawable != null) {
            cVar.getClass();
            cVar.b = drawable.getIntrinsicHeight();
        } else {
            cVar.b = 0;
        }
        cVar.a = drawable;
        PreferenceFragment preferenceFragment = PreferenceFragment.this;
        preferenceFragment.g.N();
        if (dimensionPixelSize != -1) {
            cVar.b = dimensionPixelSize;
            preferenceFragment.g.N();
        }
        cVar.c = z;
        if (this.g.getParent() == null) {
            viewGroup2.addView(this.g);
        }
        this.j.post(this.k);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public final void onDestroyView() {
        b bVar = this.k;
        a aVar = this.j;
        aVar.removeCallbacks(bVar);
        aVar.removeMessages(1);
        this.g = null;
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferenceScreen = this.f.g;
        if (preferenceScreen != null) {
            Bundle bundle2 = new Bundle();
            preferenceScreen.l(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.f.setOnPreferenceTreeClickListener(this);
        this.f.setOnDisplayPreferenceDialogListener(this);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.f.setOnPreferenceTreeClickListener(null);
        this.f.setOnDisplayPreferenceDialogListener(null);
    }

    @Override // android.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen preferenceScreen;
        super.onViewCreated(view, bundle);
        if (bundle == null || (bundle2 = bundle.getBundle("android:preferences")) == null || (preferenceScreen = this.f.g) == null) {
            return;
        }
        preferenceScreen.j(bundle2);
    }

    @Override // androidx.preference.PreferenceManager.a
    @Deprecated
    public final void y(Preference preference) {
        DialogFragment multiSelectListPreferenceDialogFragment;
        if (!(getActivity() instanceof d ? ((d) getActivity()).a() : false) && getFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                String str = preference.p;
                multiSelectListPreferenceDialogFragment = new EditTextPreferenceDialogFragment();
                Bundle bundle = new Bundle(1);
                bundle.putString("key", str);
                multiSelectListPreferenceDialogFragment.setArguments(bundle);
            } else if (preference instanceof ListPreference) {
                String str2 = preference.p;
                multiSelectListPreferenceDialogFragment = new ListPreferenceDialogFragment();
                Bundle bundle2 = new Bundle(1);
                bundle2.putString("key", str2);
                multiSelectListPreferenceDialogFragment.setArguments(bundle2);
            } else {
                if (!(preference instanceof MultiSelectListPreference)) {
                    throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
                }
                String str3 = preference.p;
                multiSelectListPreferenceDialogFragment = new MultiSelectListPreferenceDialogFragment();
                Bundle bundle3 = new Bundle(1);
                bundle3.putString("key", str3);
                multiSelectListPreferenceDialogFragment.setArguments(bundle3);
            }
            multiSelectListPreferenceDialogFragment.setTargetFragment(this, 0);
            multiSelectListPreferenceDialogFragment.show(getFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
        }
    }
}
