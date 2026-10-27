package androidx.preference;

import android.R;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.PreferenceManager;
import androidx.preference.e;
import defpackage.C0175b;
import defpackage.fh3;
import defpackage.in3;
import defpackage.n32;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Preference implements Comparable<Preference> {
    public boolean A;
    public boolean B;
    public final boolean C;
    public final boolean D;
    public boolean E;
    public boolean F;
    public final boolean G;
    public final boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f17J;
    public int K;
    public b L;
    public ArrayList M;
    public PreferenceGroup N;
    public boolean O;
    public e P;
    public f Q;
    public final a R;
    public final Context e;
    public PreferenceManager f;
    public long g;
    public boolean h;
    public c i;
    public d j;
    public int k;
    public CharSequence l;
    public CharSequence m;
    public int n;
    public Drawable o;
    public String p;
    public Intent q;
    public String r;
    public Bundle s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public final String x;
    public Object y;
    public boolean z;

    public static class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator<BaseSavedState> CREATOR = new a();

        public class a implements Parcelable.Creator<BaseSavedState> {
            @Override // android.os.Parcelable.Creator
            public final BaseSavedState createFromParcel(Parcel parcel) {
                return new BaseSavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final BaseSavedState[] newArray(int i) {
                return new BaseSavedState[i];
            }
        }

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }

        public BaseSavedState(AbsSavedState absSavedState) {
            super(absSavedState);
        }
    }

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Preference.this.H(view);
        }
    }

    public interface b {
    }

    public interface c {
        boolean n(Preference preference, Object obj);
    }

    public interface d {
        boolean r(Preference preference);
    }

    public static class e implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public final Preference e;

        public e(Preference preference) {
            this.e = preference;
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Preference preference = this.e;
            CharSequence charSequenceP = preference.p();
            if (!preference.H || TextUtils.isEmpty(charSequenceP)) {
                return;
            }
            contextMenu.setHeaderTitle(charSequenceP);
            contextMenu.add(0, 0, 0, R$string.copy).setOnMenuItemClickListener(this);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            Preference preference = this.e;
            ClipboardManager clipboardManager = (ClipboardManager) preference.e.getSystemService("clipboard");
            CharSequence charSequenceP = preference.p();
            clipboardManager.setPrimaryClip(ClipData.newPlainText("Preference", charSequenceP));
            if (Build.VERSION.SDK_INT <= 32) {
                Context context = preference.e;
                Toast.makeText(context, context.getString(R$string.preference_copied, charSequenceP), 0).show();
            }
            return true;
        }
    }

    public interface f<T extends Preference> {
        CharSequence a(T t);
    }

    public Preference(Context context, AttributeSet attributeSet, int i, int i2) {
        this.k = Integer.MAX_VALUE;
        this.t = true;
        this.u = true;
        this.w = true;
        this.z = true;
        this.A = true;
        this.B = true;
        this.C = true;
        this.D = true;
        this.F = true;
        this.I = true;
        int i3 = R$layout.preference;
        this.f17J = i3;
        this.R = new a();
        this.e = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, i2);
        this.n = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_icon, typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_android_icon, 0));
        this.p = fh3.f(typedArrayObtainStyledAttributes, R$styleable.Preference_key, R$styleable.Preference_android_key);
        int i4 = R$styleable.Preference_title;
        int i5 = R$styleable.Preference_android_title;
        CharSequence text = typedArrayObtainStyledAttributes.getText(i4);
        this.l = text == null ? typedArrayObtainStyledAttributes.getText(i5) : text;
        int i6 = R$styleable.Preference_summary;
        int i7 = R$styleable.Preference_android_summary;
        CharSequence text2 = typedArrayObtainStyledAttributes.getText(i6);
        this.m = text2 == null ? typedArrayObtainStyledAttributes.getText(i7) : text2;
        this.k = typedArrayObtainStyledAttributes.getInt(R$styleable.Preference_order, typedArrayObtainStyledAttributes.getInt(R$styleable.Preference_android_order, Integer.MAX_VALUE));
        this.r = fh3.f(typedArrayObtainStyledAttributes, R$styleable.Preference_fragment, R$styleable.Preference_android_fragment);
        this.f17J = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_layout, typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_android_layout, i3));
        this.K = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_widgetLayout, typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_android_widgetLayout, 0));
        this.t = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_enabled, typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_android_enabled, true));
        this.u = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_selectable, typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_android_selectable, true));
        this.w = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_persistent, typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_android_persistent, true));
        this.x = fh3.f(typedArrayObtainStyledAttributes, R$styleable.Preference_dependency, R$styleable.Preference_android_dependency);
        int i8 = R$styleable.Preference_allowDividerAbove;
        this.C = typedArrayObtainStyledAttributes.getBoolean(i8, typedArrayObtainStyledAttributes.getBoolean(i8, this.u));
        int i9 = R$styleable.Preference_allowDividerBelow;
        this.D = typedArrayObtainStyledAttributes.getBoolean(i9, typedArrayObtainStyledAttributes.getBoolean(i9, this.u));
        int i10 = R$styleable.Preference_defaultValue;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            this.y = C(typedArrayObtainStyledAttributes, i10);
        } else {
            int i11 = R$styleable.Preference_android_defaultValue;
            if (typedArrayObtainStyledAttributes.hasValue(i11)) {
                this.y = C(typedArrayObtainStyledAttributes, i11);
            }
        }
        this.I = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_shouldDisableView, typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_android_shouldDisableView, true));
        int i12 = R$styleable.Preference_singleLineTitle;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i12);
        this.E = zHasValue;
        if (zHasValue) {
            this.F = typedArrayObtainStyledAttributes.getBoolean(i12, typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_android_singleLineTitle, true));
        }
        this.G = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_iconSpaceReserved, typedArrayObtainStyledAttributes.getBoolean(R$styleable.Preference_android_iconSpaceReserved, false));
        int i13 = R$styleable.Preference_isPreferenceVisible;
        this.B = typedArrayObtainStyledAttributes.getBoolean(i13, typedArrayObtainStyledAttributes.getBoolean(i13, true));
        int i14 = R$styleable.Preference_enableCopying;
        this.H = typedArrayObtainStyledAttributes.getBoolean(i14, typedArrayObtainStyledAttributes.getBoolean(i14, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void K(View view, boolean z) {
        view.setEnabled(z);
        if (!(view instanceof ViewGroup)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                return;
            } else {
                K(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    public final void A(boolean z) {
        if (this.z == z) {
            this.z = !z;
            u(V());
            s();
        }
    }

    public void B() {
        X();
    }

    public Object C(TypedArray typedArray, int i) {
        return null;
    }

    public void D(Parcelable parcelable) {
        this.O = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    public Parcelable E() {
        this.O = true;
        return AbsSavedState.EMPTY_STATE;
    }

    public void H(View view) {
        Intent intent;
        PreferenceManager.c cVar;
        if (q() && this.u) {
            z();
            d dVar = this.j;
            if (dVar == null || !dVar.r(this)) {
                PreferenceManager preferenceManager = this.f;
                if ((preferenceManager == null || (cVar = preferenceManager.h) == null || !cVar.C(this)) && (intent = this.q) != null) {
                    this.e.startActivity(intent);
                }
            }
        }
    }

    public final void I(String str) {
        if (W() && !TextUtils.equals(str, o(null))) {
            SharedPreferences.Editor editorB = this.f.b();
            editorB.putString(this.p, str);
            if (!this.f.e) {
                editorB.apply();
            }
        }
    }

    public final void J(boolean z) {
        if (this.t != z) {
            this.t = z;
            u(V());
            s();
        }
    }

    public final void L(Drawable drawable) {
        if (this.o != drawable) {
            this.o = drawable;
            this.n = 0;
            s();
        }
    }

    public final void M(String str) {
        this.p = str;
        if (!this.v || (!TextUtils.isEmpty(str))) {
            return;
        }
        if (TextUtils.isEmpty(this.p)) {
            throw new IllegalStateException("Preference does not have a key assigned.");
        }
        this.v = true;
    }

    public final void N(int i) {
        if (i != this.k) {
            this.k = i;
            b bVar = this.L;
            if (bVar != null) {
                androidx.preference.e eVar = (androidx.preference.e) bVar;
                Handler handler = eVar.l;
                e.a aVar = eVar.m;
                handler.removeCallbacks(aVar);
                handler.post(aVar);
            }
        }
    }

    public final void O() {
        if (this.u) {
            this.u = false;
            s();
        }
    }

    public final void P() {
        this.E = true;
        this.F = false;
    }

    public void Q(int i) {
        R(this.e.getString(i));
    }

    public void R(CharSequence charSequence) {
        if (this.Q != null) {
            throw new IllegalStateException("Preference already has a SummaryProvider set.");
        }
        if (TextUtils.equals(this.m, charSequence)) {
            return;
        }
        this.m = charSequence;
        s();
    }

    public void S(int i) {
        T(this.e.getString(i));
    }

    public void T(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.l)) {
            return;
        }
        this.l = charSequence;
        s();
    }

    public final void U(boolean z) {
        if (this.B != z) {
            this.B = z;
            b bVar = this.L;
            if (bVar != null) {
                androidx.preference.e eVar = (androidx.preference.e) bVar;
                Handler handler = eVar.l;
                e.a aVar = eVar.m;
                handler.removeCallbacks(aVar);
                handler.post(aVar);
            }
        }
    }

    public boolean V() {
        return !q();
    }

    public final boolean W() {
        return this.f != null && this.w && (TextUtils.isEmpty(this.p) ^ true);
    }

    public final void X() {
        ArrayList arrayList;
        PreferenceScreen preferenceScreen;
        String str = this.x;
        if (str != null) {
            PreferenceManager preferenceManager = this.f;
            Preference preferenceZ = null;
            if (preferenceManager != null && (preferenceScreen = preferenceManager.g) != null) {
                preferenceZ = preferenceScreen.Z(str);
            }
            if (preferenceZ == null || (arrayList = preferenceZ.M) == null) {
                return;
            }
            arrayList.remove(this);
        }
    }

    public final boolean h(Object obj) {
        c cVar = this.i;
        return cVar == null || cVar.n(this, obj);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public int compareTo(Preference preference) {
        int i = this.k;
        int i2 = preference.k;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.l;
        CharSequence charSequence2 = preference.l;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.l.toString());
    }

    public void j(Bundle bundle) {
        Parcelable parcelable;
        if (!(!TextUtils.isEmpty(this.p)) || (parcelable = bundle.getParcelable(this.p)) == null) {
            return;
        }
        this.O = false;
        D(parcelable);
        if (!this.O) {
            throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
        }
    }

    public void l(Bundle bundle) {
        if (!TextUtils.isEmpty(this.p)) {
            this.O = false;
            Parcelable parcelableE = E();
            if (!this.O) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if (parcelableE != null) {
                bundle.putParcelable(this.p, parcelableE);
            }
        }
    }

    public final Bundle m() {
        if (this.s == null) {
            this.s = new Bundle();
        }
        return this.s;
    }

    long n() {
        return this.g;
    }

    public final String o(String str) {
        return !W() ? str : this.f.c().getString(this.p, str);
    }

    public CharSequence p() {
        f fVar = this.Q;
        return fVar != null ? fVar.a(this) : this.m;
    }

    public boolean q() {
        return this.t && this.z && this.A;
    }

    public void s() {
        b bVar = this.L;
        if (bVar != null) {
            androidx.preference.e eVar = (androidx.preference.e) bVar;
            int iIndexOf = eVar.j.indexOf(this);
            if (iIndexOf != -1) {
                eVar.e.d(iIndexOf, 1, this);
            }
        }
    }

    public final void setOnPreferenceChangeInternalListener(b bVar) {
        this.L = bVar;
    }

    public void setOnPreferenceChangeListener(c cVar) {
        this.i = cVar;
    }

    public void setOnPreferenceClickListener(d dVar) {
        this.j = dVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequence = this.l;
        if (!TextUtils.isEmpty(charSequence)) {
            sb.append(charSequence);
            sb.append(' ');
        }
        CharSequence charSequenceP = p();
        if (!TextUtils.isEmpty(charSequenceP)) {
            sb.append(charSequenceP);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public void u(boolean z) {
        ArrayList arrayList = this.M;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Preference) arrayList.get(i)).A(z);
        }
    }

    public void v() {
        PreferenceScreen preferenceScreen;
        String str = this.x;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PreferenceManager preferenceManager = this.f;
        Preference preferenceZ = null;
        if (preferenceManager != null && (preferenceScreen = preferenceManager.g) != null) {
            preferenceZ = preferenceScreen.Z(str);
        }
        if (preferenceZ == null) {
            StringBuilder sbN = C0175b.n("Dependency \"", str, "\" not found for preference \"");
            sbN.append(this.p);
            sbN.append("\" (title: \"");
            sbN.append((Object) this.l);
            sbN.append("\"");
            throw new IllegalStateException(sbN.toString());
        }
        if (preferenceZ.M == null) {
            preferenceZ.M = new ArrayList();
        }
        preferenceZ.M.add(this);
        boolean zV = preferenceZ.V();
        if (this.z == zV) {
            this.z = !zV;
            u(V());
            s();
        }
    }

    public final void x(PreferenceManager preferenceManager) {
        long j;
        this.f = preferenceManager;
        if (!this.h) {
            synchronized (preferenceManager) {
                j = preferenceManager.b;
                preferenceManager.b = 1 + j;
            }
            this.g = j;
        }
        if (W()) {
            PreferenceManager preferenceManager2 = this.f;
            if ((preferenceManager2 != null ? preferenceManager2.c() : null).contains(this.p)) {
                F(null);
                return;
            }
        }
        Object obj = this.y;
        if (obj != null) {
            F(obj);
        }
    }

    public void y(n32 n32Var) {
        Integer numValueOf;
        View view = n32Var.e;
        view.setOnClickListener(this.R);
        view.setId(0);
        TextView textView = (TextView) n32Var.u(R.id.summary);
        if (textView != null) {
            CharSequence charSequenceP = p();
            if (TextUtils.isEmpty(charSequenceP)) {
                textView.setVisibility(8);
                numValueOf = null;
            } else {
                textView.setText(charSequenceP);
                textView.setVisibility(0);
                numValueOf = Integer.valueOf(textView.getCurrentTextColor());
            }
        } else {
            numValueOf = null;
        }
        TextView textView2 = (TextView) n32Var.u(R.id.title);
        if (textView2 != null) {
            CharSequence charSequence = this.l;
            if (TextUtils.isEmpty(charSequence)) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(charSequence);
                textView2.setVisibility(0);
                if (this.E) {
                    textView2.setSingleLine(this.F);
                }
                if (!this.u && q() && numValueOf != null) {
                    textView2.setTextColor(numValueOf.intValue());
                }
            }
        }
        ImageView imageView = (ImageView) n32Var.u(R.id.icon);
        boolean z = this.G;
        if (imageView != null) {
            int i = this.n;
            if (i != 0 || this.o != null) {
                if (this.o == null) {
                    this.o = AppCompatResources.a(this.e, i);
                }
                Drawable drawable = this.o;
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                }
            }
            if (this.o != null) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(z ? 4 : 8);
            }
        }
        View viewU = n32Var.u(R$id.icon_frame);
        if (viewU == null) {
            viewU = n32Var.u(R.id.icon_frame);
        }
        if (viewU != null) {
            if (this.o != null) {
                viewU.setVisibility(0);
            } else {
                viewU.setVisibility(z ? 4 : 8);
            }
        }
        if (this.I) {
            K(view, q());
        } else {
            K(view, true);
        }
        boolean z2 = this.u;
        view.setFocusable(z2);
        view.setClickable(z2);
        n32Var.B = this.C;
        n32Var.C = this.D;
        boolean z3 = this.H;
        if (z3 && this.P == null) {
            this.P = new e(this);
        }
        view.setOnCreateContextMenuListener(z3 ? this.P : null);
        view.setLongClickable(z3);
        if (!z3 || z2) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(view, null);
    }

    public void z() {
    }

    public void F(Object obj) {
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.preferenceStyle, R.attr.preferenceStyle));
    }

    public Preference(Context context) {
        this(context, null);
    }
}
