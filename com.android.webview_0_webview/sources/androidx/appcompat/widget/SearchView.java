package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import defpackage.A0;
import defpackage.AbstractC0245Nm;
import defpackage.AbstractC0299Qm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0425Xm;
import defpackage.AbstractC0566bn;
import defpackage.AbstractC0678dn;
import defpackage.AbstractC1251o3;
import defpackage.AbstractC1358q0;
import defpackage.B0;
import defpackage.C0;
import defpackage.C1522t0;
import defpackage.D0;
import defpackage.E0;
import defpackage.F0;
import defpackage.G0;
import defpackage.L0;
import defpackage.R0;
import defpackage.RunnableC1577u0;
import defpackage.RunnableC1632v0;
import defpackage.V;
import defpackage.ViewOnClickListenerC1797y0;
import defpackage.ViewOnFocusChangeListenerC1687w0;
import defpackage.ViewOnKeyListenerC1852z0;
import defpackage.ViewOnLayoutChangeListenerC1742x0;
import defpackage.Z7;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SearchView extends AbstractC1358q0 {
    public static final D0 s0 = new D0();
    public final SearchAutoComplete H;
    public final View I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final View f15J;
    public final View K;
    public final ImageView L;
    public final ImageView M;
    public final ImageView N;
    public final ImageView O;
    public final View P;
    public G0 Q;
    public Rect R;
    public Rect S;
    public int[] T;
    public int[] U;
    public final ImageView V;
    public final Drawable W;
    public final Intent a0;
    public final Intent b0;
    public final CharSequence c0;
    public boolean d0;
    public boolean e0;
    public CharSequence f0;
    public boolean g0;
    public int h0;
    public CharSequence i0;
    public CharSequence j0;
    public final Runnable k0;
    public Runnable l0;
    public final View.OnClickListener m0;
    public View.OnKeyListener n0;
    public final TextView.OnEditorActionListener o0;
    public final AdapterView.OnItemClickListener p0;
    public final AdapterView.OnItemSelectedListener q0;
    public TextWatcher r0;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new E0();
        public boolean u;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sbF = Z7.f("SearchView.SavedState{");
            sbF.append(Integer.toHexString(System.identityHashCode(this)));
            sbF.append(" isIconified=");
            sbF.append(this.u);
            sbF.append("}");
            return sbF.toString();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.s, i);
            parcel.writeValue(Boolean.valueOf(this.u));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.u = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SearchAutoComplete extends V {
        public int v;
        public SearchView w;
        public boolean x;
        public final Runnable y;

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet, AbstractC0245Nm.R);
            this.y = new F0(this);
            this.v = getThreshold();
        }

        public void a(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.x = false;
                removeCallbacks(this.y);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.x = true;
                    return;
                }
                this.x = false;
                removeCallbacks(this.y);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.v <= 0 || super.enoughToFilter();
        }

        @Override // defpackage.V, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.x) {
                removeCallbacks(this.y);
                post(this.y);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            setMinWidth((int) TypedValue.applyDimension(1, (i < 960 || i2 < 720 || configuration.orientation != 2) ? (i >= 600 || (i >= 640 && i2 >= 480)) ? 192 : 160 : 256, displayMetrics));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            SearchView searchView = this.w;
            searchView.u(searchView.e0);
            searchView.post(searchView.k0);
            if (searchView.H.hasFocus()) {
                searchView.n();
            }
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.w.clearFocus();
                        a(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            Method method;
            super.onWindowFocusChanged(z);
            if (z && this.w.hasFocus() && getVisibility() == 0) {
                this.x = true;
                if (!SearchView.o(getContext()) || (method = SearchView.s0.c) == null) {
                    return;
                }
                try {
                    method.invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.v = i;
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public static boolean o(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.g0 = true;
        super.clearFocus();
        this.H.clearFocus();
        this.H.a(false);
        this.g0 = false;
    }

    public void n() {
        D0 d0 = s0;
        SearchAutoComplete searchAutoComplete = this.H;
        Method method = d0.a;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        D0 d02 = s0;
        SearchAutoComplete searchAutoComplete2 = this.H;
        Method method2 = d02.b;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete2, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.k0);
        post(this.l0);
        super.onDetachedFromWindow();
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            SearchAutoComplete searchAutoComplete = this.H;
            Rect rect = this.R;
            searchAutoComplete.getLocationInWindow(this.T);
            getLocationInWindow(this.U);
            int[] iArr = this.T;
            int i5 = iArr[1];
            int[] iArr2 = this.U;
            int i6 = i5 - iArr2[1];
            int i7 = iArr[0] - iArr2[0];
            rect.set(i7, i6, searchAutoComplete.getWidth() + i7, searchAutoComplete.getHeight() + i6);
            Rect rect2 = this.S;
            Rect rect3 = this.R;
            rect2.set(rect3.left, 0, rect3.right, i4 - i2);
            G0 g0 = this.Q;
            if (g0 != null) {
                g0.a(this.S, this.R);
                return;
            }
            G0 g02 = new G0(this.S, this.R, this.H);
            this.Q = g02;
            setTouchDelegate(g02);
        }
    }

    @Override // defpackage.AbstractC1358q0, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        if (this.e0) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int i4 = this.h0;
            size = i4 > 0 ? Math.min(i4, size) : Math.min(getContext().getResources().getDimensionPixelSize(AbstractC0299Qm.a0), size);
        } else if (mode == 0) {
            size = this.h0;
            if (size <= 0) {
                size = getContext().getResources().getDimensionPixelSize(AbstractC0299Qm.a0);
            }
        } else if (mode == 1073741824 && (i3 = this.h0) > 0) {
            size = Math.min(i3, size);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getContext().getResources().getDimensionPixelSize(AbstractC0299Qm.Z), size2);
        } else if (mode2 == 0) {
            size2 = getContext().getResources().getDimensionPixelSize(AbstractC0299Qm.Z);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.s);
        u(savedState.u);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.u = this.e0;
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.k0);
    }

    public boolean p(int i) {
        throw null;
    }

    public void q() {
        Editable text = this.H.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        this.H.a(false);
        this.H.dismissDropDown();
    }

    public final void r() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.H.getText());
        if (!z2 && !this.d0) {
            z = false;
        }
        this.N.setVisibility(z ? 0 : 8);
        Drawable drawable = this.N.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i, Rect rect) {
        if (this.g0 || !isFocusable()) {
            return false;
        }
        if (this.e0) {
            return super.requestFocus(i, rect);
        }
        boolean zRequestFocus = this.H.requestFocus(i, rect);
        if (zRequestFocus) {
            u(false);
        }
        return zRequestFocus;
    }

    public void s() {
        int[] iArr = this.H.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f15J.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.K.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
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
    public final void t() {
        CharSequence charSequence = this.f0;
        CharSequence charSequence2 = charSequence;
        if (charSequence == null) {
            charSequence2 = this.c0;
        }
        SearchAutoComplete searchAutoComplete = this.H;
        CharSequence charSequence3 = charSequence2;
        if (charSequence2 == null) {
            charSequence3 = "";
        }
        CharSequence charSequence4 = charSequence3;
        if (this.d0) {
            charSequence4 = charSequence3;
            if (this.W != null) {
                int textSize = (int) (((double) this.H.getTextSize()) * 1.25d);
                this.W.setBounds(0, 0, textSize, textSize);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
                spannableStringBuilder.setSpan(new ImageSpan(this.W), 1, 2, 33);
                spannableStringBuilder.append(charSequence3);
                charSequence4 = spannableStringBuilder;
            }
        }
        searchAutoComplete.setHint(charSequence4);
    }

    public final void u(boolean z) {
        this.e0 = z;
        int i = z ? 0 : 8;
        TextUtils.isEmpty(this.H.getText());
        this.L.setVisibility(i);
        this.M.setVisibility(8);
        this.I.setVisibility(z ? 8 : 0);
        this.V.setVisibility((this.V.getDrawable() == null || this.d0) ? 8 : 0);
        r();
        this.O.setVisibility(8);
        this.K.setVisibility(8);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0245Nm.q6);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.R = new Rect();
        this.S = new Rect();
        this.T = new int[2];
        this.U = new int[2];
        this.k0 = new RunnableC1577u0(this);
        this.l0 = new RunnableC1632v0(this);
        new WeakHashMap();
        this.m0 = new ViewOnClickListenerC1797y0(this);
        this.n0 = new ViewOnKeyListenerC1852z0(this);
        this.o0 = new A0(this);
        this.p0 = new B0(this);
        this.q0 = new C0(this);
        this.r0 = new C1522t0(this);
        L0 l0 = new L0(context, context.obtainStyledAttributes(attributeSet, AbstractC0678dn.s0, i, 0));
        LayoutInflater.from(context).inflate(l0.i(5, AbstractC0425Xm.z), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(AbstractC0371Um.F3);
        this.H = searchAutoComplete;
        searchAutoComplete.w = this;
        this.I = findViewById(AbstractC0371Um.B3);
        this.f15J = findViewById(AbstractC0371Um.E3);
        this.K = findViewById(AbstractC0371Um.A4);
        this.L = (ImageView) findViewById(AbstractC0371Um.z3);
        this.M = (ImageView) findViewById(AbstractC0371Um.C3);
        this.N = (ImageView) findViewById(AbstractC0371Um.A3);
        this.O = (ImageView) findViewById(AbstractC0371Um.I3);
        this.V = (ImageView) findViewById(AbstractC0371Um.D3);
        AbstractC1251o3.C(this.f15J, l0.f(6));
        this.K.setBackground(l0.f(10));
        this.L.setImageDrawable(l0.f(9));
        this.M.setImageDrawable(l0.f(3));
        this.N.setImageDrawable(l0.f(0));
        this.O.setImageDrawable(l0.f(12));
        this.V.setImageDrawable(l0.f(9));
        this.W = l0.f(8);
        R0.a(this.L, getResources().getString(AbstractC0566bn.H));
        l0.i(11, AbstractC0425Xm.y);
        l0.i(1, 0);
        this.L.setOnClickListener(this.m0);
        this.N.setOnClickListener(this.m0);
        this.M.setOnClickListener(this.m0);
        this.O.setOnClickListener(this.m0);
        this.H.setOnClickListener(this.m0);
        this.H.addTextChangedListener(this.r0);
        this.H.setOnEditorActionListener(this.o0);
        this.H.setOnItemClickListener(this.p0);
        this.H.setOnItemSelectedListener(this.q0);
        this.H.setOnKeyListener(this.n0);
        this.H.setOnFocusChangeListener(new ViewOnFocusChangeListenerC1687w0(this));
        boolean zA = l0.a(4, true);
        if (this.d0 != zA) {
            this.d0 = zA;
            u(zA);
            t();
        }
        int iE = l0.e(14, -1);
        if (iE != -1) {
            this.h0 = iE;
            requestLayout();
        }
        this.c0 = l0.j(2);
        this.f0 = l0.j(7);
        int iH = l0.h(16, -1);
        if (iH != -1) {
            this.H.setImeOptions(iH);
        }
        int iH2 = l0.h(15, -1);
        if (iH2 != -1) {
            this.H.setInputType(iH2);
        }
        setFocusable(l0.a(13, true));
        l0.b.recycle();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.a0 = intent;
        intent.addFlags(268435456);
        this.a0.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.b0 = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById = findViewById(this.H.getDropDownAnchor());
        this.P = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC1742x0(this));
        }
        u(this.d0);
        t();
    }
}
