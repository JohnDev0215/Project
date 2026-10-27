package androidx.preference;

import android.R;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.preference.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class Preference implements Comparable<Preference> {
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private int G;
    private int H;
    private b I;
    private List<Preference> J;
    private PreferenceGroup K;
    private boolean L;
    private e M;
    private f N;
    private final View.OnClickListener O;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1039c;

    @Nullable
    private androidx.preference.d d;

    @Nullable
    private androidx.preference.b e;
    private long f;
    private c g;
    private d h;
    private int i;
    private int j;
    private CharSequence k;
    private CharSequence l;
    private int m;
    private Drawable n;
    private String o;
    private Intent p;
    private String q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private String v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator<BaseSavedState> CREATOR = new a();

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Parcelable.Creator<BaseSavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public BaseSavedState createFromParcel(Parcel parcel) {
                return new BaseSavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public BaseSavedState[] newArray(int i) {
                return new BaseSavedState[i];
            }
        }

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }

        public BaseSavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Preference.this.W(view);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface b {
        void a(Preference preference);

        void b(Preference preference);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        boolean a(Preference preference, Object obj);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d {
        boolean b(Preference preference);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class e implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Preference f1041c;

        e(Preference preference) {
            this.f1041c = preference;
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            CharSequence charSequenceY = this.f1041c.y();
            if (!this.f1041c.D() || TextUtils.isEmpty(charSequenceY)) {
                return;
            }
            contextMenu.setHeaderTitle(charSequenceY);
            contextMenu.add(0, 0, 0, R$string.copy).setOnMenuItemClickListener(this);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            ClipboardManager clipboardManager = (ClipboardManager) this.f1041c.j().getSystemService("clipboard");
            CharSequence charSequenceY = this.f1041c.y();
            clipboardManager.setPrimaryClip(ClipData.newPlainText("Preference", charSequenceY));
            Toast.makeText(this.f1041c.j(), this.f1041c.j().getString(R$string.preference_copied, charSequenceY), 0).show();
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface f<T extends Preference> {
        CharSequence a(T t);
    }

    public Preference(Context context, AttributeSet attributeSet, int i, int i2) {
        this.i = Integer.MAX_VALUE;
        this.j = 0;
        this.r = true;
        this.s = true;
        this.u = true;
        this.w = true;
        this.x = true;
        this.y = true;
        this.z = true;
        this.A = true;
        this.C = true;
        this.F = true;
        this.G = R$layout.preference;
        this.O = new a();
        this.f1039c = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, i2);
        this.m = androidx.core.content.res.f.n(typedArrayObtainStyledAttributes, R$styleable.Preference_icon, R$styleable.Preference_android_icon, 0);
        this.o = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.Preference_key, R$styleable.Preference_android_key);
        this.k = androidx.core.content.res.f.p(typedArrayObtainStyledAttributes, R$styleable.Preference_title, R$styleable.Preference_android_title);
        this.l = androidx.core.content.res.f.p(typedArrayObtainStyledAttributes, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        this.i = androidx.core.content.res.f.d(typedArrayObtainStyledAttributes, R$styleable.Preference_order, R$styleable.Preference_android_order, Integer.MAX_VALUE);
        this.q = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.Preference_fragment, R$styleable.Preference_android_fragment);
        this.G = androidx.core.content.res.f.n(typedArrayObtainStyledAttributes, R$styleable.Preference_layout, R$styleable.Preference_android_layout, R$layout.preference);
        this.H = androidx.core.content.res.f.n(typedArrayObtainStyledAttributes, R$styleable.Preference_widgetLayout, R$styleable.Preference_android_widgetLayout, 0);
        this.r = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.Preference_enabled, R$styleable.Preference_android_enabled, true);
        this.s = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.Preference_selectable, R$styleable.Preference_android_selectable, true);
        this.u = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.Preference_persistent, R$styleable.Preference_android_persistent, true);
        this.v = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes, R$styleable.Preference_dependency, R$styleable.Preference_android_dependency);
        int i3 = R$styleable.Preference_allowDividerAbove;
        this.z = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i3, i3, this.s);
        int i4 = R$styleable.Preference_allowDividerBelow;
        this.A = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i4, i4, this.s);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.Preference_defaultValue)) {
            Q(typedArrayObtainStyledAttributes, R$styleable.Preference_defaultValue);
        } else if (typedArrayObtainStyledAttributes.hasValue(R$styleable.Preference_android_defaultValue)) {
            Q(typedArrayObtainStyledAttributes, R$styleable.Preference_android_defaultValue);
        }
        this.F = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.Preference_shouldDisableView, R$styleable.Preference_android_shouldDisableView, true);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R$styleable.Preference_singleLineTitle);
        this.B = zHasValue;
        if (zHasValue) {
            this.C = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.Preference_singleLineTitle, R$styleable.Preference_android_singleLineTitle, true);
        }
        this.D = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, R$styleable.Preference_iconSpaceReserved, R$styleable.Preference_android_iconSpaceReserved, false);
        int i5 = R$styleable.Preference_isPreferenceVisible;
        this.y = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i5, i5, true);
        int i6 = R$styleable.Preference_enableCopying;
        this.E = androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i6, i6, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void B0(@NonNull SharedPreferences.Editor editor) {
        if (this.d.n()) {
            editor.apply();
        }
    }

    private void C0() {
        Preference preferenceI;
        String str = this.v;
        if (str == null || (preferenceI = i(str)) == null) {
            return;
        }
        preferenceI.D0(this);
    }

    private void D0(Preference preference) {
        List<Preference> list = this.J;
        if (list != null) {
            list.remove(preference);
        }
    }

    private void b0() {
        if (TextUtils.isEmpty(this.v)) {
            return;
        }
        Preference preferenceI = i(this.v);
        if (preferenceI != null) {
            preferenceI.c0(this);
            return;
        }
        throw new IllegalStateException("Dependency \"" + this.v + "\" not found for preference \"" + this.o + "\" (title: \"" + ((Object) this.k) + "\"");
    }

    private void c0(Preference preference) {
        if (this.J == null) {
            this.J = new ArrayList();
        }
        this.J.add(preference);
        preference.O(this, z0());
    }

    private void g0(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                g0(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    public CharSequence A() {
        return this.k;
    }

    protected boolean A0() {
        return this.d != null && F() && C();
    }

    public final int B() {
        return this.H;
    }

    public boolean C() {
        return !TextUtils.isEmpty(this.o);
    }

    public boolean D() {
        return this.E;
    }

    public boolean E() {
        return this.r && this.w && this.x;
    }

    public boolean F() {
        return this.u;
    }

    public boolean G() {
        return this.s;
    }

    public final boolean H() {
        return this.y;
    }

    protected void I() {
        b bVar = this.I;
        if (bVar != null) {
            bVar.b(this);
        }
    }

    public void J(boolean z) {
        List<Preference> list = this.J;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).O(this, z);
        }
    }

    protected void K() {
        b bVar = this.I;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    public void L() {
        b0();
    }

    public void M(androidx.preference.f fVar) {
        Integer numValueOf;
        View view = fVar.f1092a;
        view.setOnClickListener(this.O);
        view.setId(this.j);
        TextView textView = (TextView) fVar.M(R.id.summary);
        if (textView != null) {
            CharSequence charSequenceY = y();
            if (TextUtils.isEmpty(charSequenceY)) {
                textView.setVisibility(8);
                numValueOf = null;
            } else {
                textView.setText(charSequenceY);
                textView.setVisibility(0);
                numValueOf = Integer.valueOf(textView.getCurrentTextColor());
            }
        } else {
            numValueOf = null;
        }
        TextView textView2 = (TextView) fVar.M(R.id.title);
        if (textView2 != null) {
            CharSequence charSequenceA = A();
            if (TextUtils.isEmpty(charSequenceA)) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(charSequenceA);
                textView2.setVisibility(0);
                if (this.B) {
                    textView2.setSingleLine(this.C);
                }
                if (!G() && E() && numValueOf != null) {
                    textView2.setTextColor(numValueOf.intValue());
                }
            }
        }
        ImageView imageView = (ImageView) fVar.M(R.id.icon);
        if (imageView != null) {
            if (this.m != 0 || this.n != null) {
                if (this.n == null) {
                    this.n = androidx.appcompat.a.a.a.b(this.f1039c, this.m);
                }
                Drawable drawable = this.n;
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                }
            }
            if (this.n != null) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(this.D ? 4 : 8);
            }
        }
        View viewM = fVar.M(R$id.icon_frame);
        if (viewM == null) {
            viewM = fVar.M(R.id.icon_frame);
        }
        if (viewM != null) {
            if (this.n != null) {
                viewM.setVisibility(0);
            } else {
                viewM.setVisibility(this.D ? 4 : 8);
            }
        }
        if (this.F) {
            g0(view, E());
        } else {
            g0(view, true);
        }
        boolean zG = G();
        view.setFocusable(zG);
        view.setClickable(zG);
        fVar.Q(this.z);
        fVar.R(this.A);
        boolean zD = D();
        if (zD && this.M == null) {
            this.M = new e(this);
        }
        view.setOnCreateContextMenuListener(zD ? this.M : null);
        view.setLongClickable(zD);
        if (!zD || zG) {
            return;
        }
        ViewCompat.N(view, null);
    }

    protected void N() {
    }

    public void O(Preference preference, boolean z) {
        if (this.w == z) {
            this.w = !z;
            J(z0());
            I();
        }
    }

    public void P() {
        C0();
    }

    protected Object Q(TypedArray typedArray, int i) {
        return null;
    }

    @CallSuper
    @Deprecated
    public void R(androidx.core.view.accessibility.b bVar) {
    }

    public void S(Preference preference, boolean z) {
        if (this.x == z) {
            this.x = !z;
            J(z0());
            I();
        }
    }

    protected void T(Parcelable parcelable) {
        this.L = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    protected Parcelable U() {
        this.L = true;
        return AbsSavedState.EMPTY_STATE;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void V() {
        d.c cVarE;
        if (E() && G()) {
            N();
            d dVar = this.h;
            if (dVar == null || !dVar.b(this)) {
                androidx.preference.d dVarX = x();
                if ((dVarX == null || (cVarE = dVarX.e()) == null || !cVarE.c(this)) && this.p != null) {
                    j().startActivity(this.p);
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected void W(View view) {
        V();
    }

    protected boolean X(boolean z) {
        if (!A0()) {
            return false;
        }
        if (z == s(!z)) {
            return true;
        }
        androidx.preference.b bVarW = w();
        if (bVarW != null) {
            bVarW.e(this.o, z);
        } else {
            SharedPreferences.Editor editorC = this.d.c();
            editorC.putBoolean(this.o, z);
            B0(editorC);
        }
        return true;
    }

    protected boolean Y(int i) {
        if (!A0()) {
            return false;
        }
        if (i == t(~i)) {
            return true;
        }
        androidx.preference.b bVarW = w();
        if (bVarW != null) {
            bVarW.f(this.o, i);
        } else {
            SharedPreferences.Editor editorC = this.d.c();
            editorC.putInt(this.o, i);
            B0(editorC);
        }
        return true;
    }

    protected boolean Z(String str) {
        if (!A0()) {
            return false;
        }
        if (TextUtils.equals(str, u(null))) {
            return true;
        }
        androidx.preference.b bVarW = w();
        if (bVarW != null) {
            bVarW.g(this.o, str);
        } else {
            SharedPreferences.Editor editorC = this.d.c();
            editorC.putString(this.o, str);
            B0(editorC);
        }
        return true;
    }

    public boolean a0(Set<String> set) {
        if (!A0()) {
            return false;
        }
        if (set.equals(v(null))) {
            return true;
        }
        androidx.preference.b bVarW = w();
        if (bVarW != null) {
            bVarW.h(this.o, set);
        } else {
            SharedPreferences.Editor editorC = this.d.c();
            editorC.putStringSet(this.o, set);
            B0(editorC);
        }
        return true;
    }

    public boolean d(Object obj) {
        c cVar = this.g;
        return cVar == null || cVar.a(this, obj);
    }

    void d0() {
        if (TextUtils.isEmpty(this.o)) {
            throw new IllegalStateException("Preference does not have a key assigned.");
        }
        this.t = true;
    }

    final void e() {
    }

    public void e0(Bundle bundle) {
        g(bundle);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull Preference preference) {
        int i = this.i;
        int i2 = preference.i;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.k;
        CharSequence charSequence2 = preference.k;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.k.toString());
    }

    public void f0(Bundle bundle) {
        h(bundle);
    }

    void g(Bundle bundle) {
        Parcelable parcelable;
        if (!C() || (parcelable = bundle.getParcelable(this.o)) == null) {
            return;
        }
        this.L = false;
        T(parcelable);
        if (!this.L) {
            throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
        }
    }

    void h(Bundle bundle) {
        if (C()) {
            this.L = false;
            Parcelable parcelableU = U();
            if (!this.L) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if (parcelableU != null) {
                bundle.putParcelable(this.o, parcelableU);
            }
        }
    }

    public void h0(int i) {
        i0(androidx.appcompat.a.a.a.b(this.f1039c, i));
        this.m = i;
    }

    @Nullable
    protected <T extends Preference> T i(@NonNull String str) {
        androidx.preference.d dVar = this.d;
        if (dVar == null) {
            return null;
        }
        return (T) dVar.a(str);
    }

    public void i0(Drawable drawable) {
        if (this.n != drawable) {
            this.n = drawable;
            this.m = 0;
            I();
        }
    }

    public Context j() {
        return this.f1039c;
    }

    public void j0(boolean z) {
        if (this.D != z) {
            this.D = z;
            I();
        }
    }

    StringBuilder k() {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequenceA = A();
        if (!TextUtils.isEmpty(charSequenceA)) {
            sb.append(charSequenceA);
            sb.append(' ');
        }
        CharSequence charSequenceY = y();
        if (!TextUtils.isEmpty(charSequenceY)) {
            sb.append(charSequenceY);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb;
    }

    public void k0(Intent intent) {
        this.p = intent;
    }

    public String l() {
        return this.q;
    }

    public void l0(String str) {
        this.o = str;
        if (!this.t || C()) {
            return;
        }
        d0();
    }

    public Drawable m() {
        int i;
        if (this.n == null && (i = this.m) != 0) {
            this.n = androidx.appcompat.a.a.a.b(this.f1039c, i);
        }
        return this.n;
    }

    public void m0(int i) {
        this.G = i;
    }

    long n() {
        return this.f;
    }

    final void n0(b bVar) {
        this.I = bVar;
    }

    public Intent o() {
        return this.p;
    }

    public void o0(c cVar) {
        this.g = cVar;
    }

    public String p() {
        return this.o;
    }

    public void p0(d dVar) {
        this.h = dVar;
    }

    public final int q() {
        return this.G;
    }

    public void q0(int i) {
        if (i != this.i) {
            this.i = i;
            K();
        }
    }

    @Nullable
    public PreferenceGroup r() {
        return this.K;
    }

    public void r0(boolean z) {
        this.u = z;
    }

    protected boolean s(boolean z) {
        if (!A0()) {
            return z;
        }
        androidx.preference.b bVarW = w();
        return bVarW != null ? bVarW.a(this.o, z) : this.d.i().getBoolean(this.o, z);
    }

    public void s0(boolean z) {
        if (this.s != z) {
            this.s = z;
            I();
        }
    }

    protected int t(int i) {
        if (!A0()) {
            return i;
        }
        androidx.preference.b bVarW = w();
        return bVarW != null ? bVarW.b(this.o, i) : this.d.i().getInt(this.o, i);
    }

    public void t0(boolean z) {
        if (this.F != z) {
            this.F = z;
            I();
        }
    }

    public String toString() {
        return k().toString();
    }

    protected String u(String str) {
        if (!A0()) {
            return str;
        }
        androidx.preference.b bVarW = w();
        return bVarW != null ? bVarW.c(this.o, str) : this.d.i().getString(this.o, str);
    }

    public void u0(CharSequence charSequence) {
        if (z() != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        }
        if (TextUtils.equals(this.l, charSequence)) {
            return;
        }
        this.l = charSequence;
        I();
    }

    public Set<String> v(Set<String> set) {
        if (!A0()) {
            return set;
        }
        androidx.preference.b bVarW = w();
        return bVarW != null ? bVarW.d(this.o, set) : this.d.i().getStringSet(this.o, set);
    }

    public final void v0(@Nullable f fVar) {
        this.N = fVar;
        I();
    }

    @Nullable
    public androidx.preference.b w() {
        androidx.preference.b bVar = this.e;
        if (bVar != null) {
            return bVar;
        }
        androidx.preference.d dVar = this.d;
        if (dVar != null) {
            return dVar.g();
        }
        return null;
    }

    public void w0(int i) {
        x0(this.f1039c.getString(i));
    }

    public androidx.preference.d x() {
        return this.d;
    }

    public void x0(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.k)) {
            return;
        }
        this.k = charSequence;
        I();
    }

    public CharSequence y() {
        return z() != null ? z().a(this) : this.l;
    }

    public void y0(int i) {
        this.H = i;
    }

    @Nullable
    public final f z() {
        return this.N;
    }

    public boolean z0() {
        return !E();
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.preferenceStyle, R.attr.preferenceStyle));
    }

    public Preference(Context context) {
        this(context, null);
    }
}
