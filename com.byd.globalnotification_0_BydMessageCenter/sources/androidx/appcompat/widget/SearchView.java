package androidx.appcompat.widget;

import a.b.p.b1;
import a.b.p.i0;
import a.b.p.r0;
import a.b.p.x0;
import a.h.k.q;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends i0 implements a.b.o.b {
    public Rect A;
    public int[] B;
    public int[] C;
    public final ImageView D;
    public final Drawable E;
    public final int F;
    public final int G;
    public final Intent H;
    public final Intent I;
    public final CharSequence J;
    public l K;
    public k L;
    public View.OnFocusChangeListener M;
    public m N;
    public View.OnClickListener O;
    public boolean P;
    public boolean Q;
    public a.i.a.a R;
    public boolean S;
    public CharSequence T;
    public boolean U;
    public boolean V;
    public int W;
    public boolean X;
    public CharSequence Y;
    public CharSequence Z;
    public boolean a0;
    public int b0;
    public SearchableInfo c0;
    public Bundle d0;
    public final Runnable e0;
    public Runnable f0;
    public final WeakHashMap<String, Drawable.ConstantState> g0;
    public final View.OnClickListener h0;
    public View.OnKeyListener i0;
    public final TextView.OnEditorActionListener j0;
    public final AdapterView.OnItemClickListener k0;
    public final AdapterView.OnItemSelectedListener l0;
    public TextWatcher m0;
    public final SearchAutoComplete p;
    public final View q;
    public final View r;
    public final View s;
    public final ImageView t;
    public final ImageView u;
    public final ImageView v;
    public final ImageView w;
    public final View x;
    public o y;
    public Rect z;

    public static class SearchAutoComplete extends a.b.p.d {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1445d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public SearchView f1446e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1447f;
        public final Runnable g;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete searchAutoComplete = SearchAutoComplete.this;
                if (searchAutoComplete.f1447f) {
                    ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
                    searchAutoComplete.f1447f = false;
                }
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet, a.b.a.autoCompleteTextViewStyle);
            this.g = new a();
            this.f1445d = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i < 600) {
                return (i < 640 || i2 < 480) ? 160 : 192;
            }
            return 192;
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f1445d <= 0 || super.enoughToFilter();
        }

        @Override // a.b.p.d, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f1447f) {
                removeCallbacks(this.g);
                post(this.g);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            SearchView searchView = this.f1446e;
            searchView.G(searchView.Q);
            searchView.post(searchView.e0);
            if (searchView.p.hasFocus()) {
                searchView.p.refreshAutoCompleteResults();
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
                        this.f1446e.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f1446e.hasFocus() && getVisibility() == 0) {
                this.f1447f = true;
                if (getContext().getResources().getConfiguration().orientation == 2) {
                    setInputMethodMode(1);
                    if (enoughToFilter()) {
                        showDropDown();
                    }
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f1447f = false;
                removeCallbacks(this.g);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f1447f = true;
                    return;
                }
                this.f1447f = false;
                removeCallbacks(this.g);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f1446e = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f1445d = i;
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView searchView = SearchView.this;
            Editable text = searchView.p.getText();
            searchView.Z = text;
            boolean z = !TextUtils.isEmpty(text);
            searchView.F(z);
            searchView.H(!z);
            searchView.B();
            searchView.E();
            if (searchView.K != null && !TextUtils.equals(charSequence, searchView.Y)) {
                searchView.K.b(charSequence.toString());
            }
            searchView.Y = charSequence.toString();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.C();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.i.a.a aVar = SearchView.this.R;
            if (aVar instanceof r0) {
                aVar.b(null);
            }
        }
    }

    public class d implements View.OnFocusChangeListener {
        public d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.M;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    public class e implements View.OnLayoutChangeListener {
        public e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView searchView = SearchView.this;
            if (searchView.x.getWidth() > 1) {
                Resources resources = searchView.getContext().getResources();
                int paddingLeft = searchView.r.getPaddingLeft();
                Rect rect = new Rect();
                boolean zB = b1.b(searchView);
                int dimensionPixelSize = searchView.P ? resources.getDimensionPixelSize(a.b.d.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(a.b.d.abc_dropdownitem_icon_width) : 0;
                searchView.p.getDropDownBackground().getPadding(rect);
                searchView.p.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
                searchView.p.setDropDownWidth((((searchView.x.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
            }
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.t) {
                searchView.y();
                return;
            }
            if (view == searchView.v) {
                searchView.u();
                return;
            }
            if (view == searchView.u) {
                searchView.z();
                return;
            }
            if (view != searchView.w) {
                SearchAutoComplete searchAutoComplete = searchView.p;
                if (view == searchAutoComplete) {
                    searchAutoComplete.refreshAutoCompleteResults();
                    return;
                }
                return;
            }
            SearchableInfo searchableInfo = searchView.c0;
            if (searchableInfo == null) {
                return;
            }
            try {
                if (!searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                        searchView.getContext().startActivity(searchView.s(searchView.I, searchableInfo));
                    }
                } else {
                    Intent intent = new Intent(searchView.H);
                    ComponentName searchActivity = searchableInfo.getSearchActivity();
                    intent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
                    searchView.getContext().startActivity(intent);
                }
            } catch (ActivityNotFoundException unused) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    public class g implements View.OnKeyListener {
        public g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.c0 == null) {
                return false;
            }
            if (searchView.p.isPopupShowing() && SearchView.this.p.getListSelection() != -1) {
                return SearchView.this.A(i, keyEvent);
            }
            if ((TextUtils.getTrimmedLength(SearchView.this.p.getText()) == 0) || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.t(0, null, searchView2.p.getText().toString());
            return true;
        }
    }

    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.z();
            return true;
        }
    }

    public class i implements AdapterView.OnItemClickListener {
        public i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.v(i);
        }
    }

    public class j implements AdapterView.OnItemSelectedListener {
        public j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.w(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public interface k {
        boolean a();
    }

    public interface l {
        boolean a(String str);

        boolean b(String str);
    }

    public interface m {
        boolean a(int i);

        boolean b(int i);
    }

    public static class n extends a.j.a.a {
        public static final Parcelable.Creator<n> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1459c;

        public class a implements Parcelable.ClassLoaderCreator<n> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new n(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new n[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public n createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new n(parcel, classLoader);
            }
        }

        public n(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1459c = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public n(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sbD = b.a.a.a.a.d("SearchView.SavedState{");
            sbD.append(Integer.toHexString(System.identityHashCode(this)));
            sbD.append(" isIconified=");
            sbD.append(this.f1459c);
            sbD.append("}");
            return sbD.toString();
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeValue(Boolean.valueOf(this.f1459c));
        }
    }

    public static class o extends TouchDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1460a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f1461b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Rect f1462c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f1463d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1464e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1465f;

        public o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f1464e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f1461b = new Rect();
            this.f1463d = new Rect();
            this.f1462c = new Rect();
            a(rect, rect2);
            this.f1460a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f1461b.set(rect);
            this.f1463d.set(rect);
            Rect rect3 = this.f1463d;
            int i = this.f1464e;
            rect3.inset(-i, -i);
            this.f1462c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            float width;
            int height;
            boolean z2;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z3 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z2 = this.f1465f;
                    if (z2 && !this.f1463d.contains(x, y)) {
                        z3 = z2;
                        z = false;
                    }
                } else {
                    if (action == 3) {
                        z2 = this.f1465f;
                        this.f1465f = false;
                    }
                    z = true;
                    z3 = false;
                }
                z3 = z2;
                z = true;
            } else if (this.f1461b.contains(x, y)) {
                this.f1465f = true;
                z = true;
            } else {
                z = true;
                z3 = false;
            }
            if (!z3) {
                return false;
            }
            if (!z || this.f1462c.contains(x, y)) {
                Rect rect = this.f1462c;
                width = x - rect.left;
                height = y - rect.top;
            } else {
                width = this.f1460a.getWidth() / 2;
                height = this.f1460a.getHeight() / 2;
            }
            motionEvent.setLocation(width, height);
            return this.f1460a.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.z = new Rect();
        this.A = new Rect();
        this.B = new int[2];
        this.C = new int[2];
        this.e0 = new b();
        this.f0 = new c();
        this.g0 = new WeakHashMap<>();
        this.h0 = new f();
        this.i0 = new g();
        this.j0 = new h();
        this.k0 = new i();
        this.l0 = new j();
        this.m0 = new a();
        x0 x0Var = new x0(context, context.obtainStyledAttributes(attributeSet, a.b.j.SearchView, i2, 0));
        LayoutInflater.from(context).inflate(x0Var.l(a.b.j.SearchView_layout, a.b.g.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(a.b.f.search_src_text);
        this.p = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.q = findViewById(a.b.f.search_edit_frame);
        this.r = findViewById(a.b.f.search_plate);
        this.s = findViewById(a.b.f.submit_area);
        this.t = (ImageView) findViewById(a.b.f.search_button);
        this.u = (ImageView) findViewById(a.b.f.search_go_btn);
        this.v = (ImageView) findViewById(a.b.f.search_close_btn);
        this.w = (ImageView) findViewById(a.b.f.search_voice_btn);
        this.D = (ImageView) findViewById(a.b.f.search_mag_icon);
        q.X(this.r, x0Var.g(a.b.j.SearchView_queryBackground));
        this.s.setBackground(x0Var.g(a.b.j.SearchView_submitBackground));
        this.t.setImageDrawable(x0Var.g(a.b.j.SearchView_searchIcon));
        this.u.setImageDrawable(x0Var.g(a.b.j.SearchView_goIcon));
        this.v.setImageDrawable(x0Var.g(a.b.j.SearchView_closeIcon));
        this.w.setImageDrawable(x0Var.g(a.b.j.SearchView_voiceIcon));
        this.D.setImageDrawable(x0Var.g(a.b.j.SearchView_searchIcon));
        this.E = x0Var.g(a.b.j.SearchView_searchHintIcon);
        this.t.setTooltipText(getResources().getString(a.b.h.abc_searchview_description_search));
        this.F = x0Var.l(a.b.j.SearchView_suggestionRowLayout, a.b.g.abc_search_dropdown_item_icons_2line);
        this.G = x0Var.l(a.b.j.SearchView_commitIcon, 0);
        this.t.setOnClickListener(this.h0);
        this.v.setOnClickListener(this.h0);
        this.u.setOnClickListener(this.h0);
        this.w.setOnClickListener(this.h0);
        this.p.setOnClickListener(this.h0);
        this.p.addTextChangedListener(this.m0);
        this.p.setOnEditorActionListener(this.j0);
        this.p.setOnItemClickListener(this.k0);
        this.p.setOnItemSelectedListener(this.l0);
        this.p.setOnKeyListener(this.i0);
        this.p.setOnFocusChangeListener(new d());
        setIconifiedByDefault(x0Var.a(a.b.j.SearchView_iconifiedByDefault, true));
        int iF = x0Var.f(a.b.j.SearchView_android_maxWidth, -1);
        if (iF != -1) {
            setMaxWidth(iF);
        }
        this.J = x0Var.n(a.b.j.SearchView_defaultQueryHint);
        this.T = x0Var.n(a.b.j.SearchView_queryHint);
        int iJ = x0Var.j(a.b.j.SearchView_android_imeOptions, -1);
        if (iJ != -1) {
            setImeOptions(iJ);
        }
        int iJ2 = x0Var.j(a.b.j.SearchView_android_inputType, -1);
        if (iJ2 != -1) {
            setInputType(iJ2);
        }
        setFocusable(x0Var.a(a.b.j.SearchView_android_focusable, true));
        x0Var.f390b.recycle();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.H = intent;
        intent.addFlags(268435456);
        this.H.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.I = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById = findViewById(this.p.getDropDownAnchor());
        this.x = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.addOnLayoutChangeListener(new e());
        }
        G(this.P);
        D();
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(a.b.d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(a.b.d.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        this.p.setText(charSequence);
        this.p.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public boolean A(int i2, KeyEvent keyEvent) {
        if (this.c0 != null && this.R != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return v(this.p.getListSelection());
            }
            if (i2 == 21 || i2 == 22) {
                this.p.setSelection(i2 == 21 ? 0 : this.p.length());
                this.p.setListSelection(0);
                this.p.clearListSelection();
                SearchAutoComplete searchAutoComplete = this.p;
                searchAutoComplete.setInputMethodMode(1);
                if (searchAutoComplete.enoughToFilter()) {
                    searchAutoComplete.showDropDown();
                }
                return true;
            }
            if (i2 != 19 || this.p.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    public final void B() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.p.getText());
        if (!z2 && (!this.P || this.a0)) {
            z = false;
        }
        this.v.setVisibility(z ? 0 : 8);
        Drawable drawable = this.v.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public void C() {
        int[] iArr = this.p.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.r.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.s.getBackground();
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
    public final void D() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.p;
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = "";
        }
        CharSequence charSequence2 = charSequence;
        if (this.P) {
            charSequence2 = charSequence;
            if (this.E != null) {
                int textSize = (int) (((double) this.p.getTextSize()) * 1.25d);
                this.E.setBounds(0, 0, textSize, textSize);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
                spannableStringBuilder.setSpan(new ImageSpan(this.E), 1, 2, 33);
                spannableStringBuilder.append(charSequence);
                charSequence2 = spannableStringBuilder;
            }
        }
        searchAutoComplete.setHint(charSequence2);
    }

    public final void E() {
        int i2 = 0;
        if (!((this.S || this.X) && !this.Q) || (this.u.getVisibility() != 0 && this.w.getVisibility() != 0)) {
            i2 = 8;
        }
        this.s.setVisibility(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(boolean r3) {
        /*
            r2 = this;
            boolean r0 = r2.S
            r1 = 0
            if (r0 == 0) goto L21
            if (r0 != 0) goto Lb
            boolean r0 = r2.X
            if (r0 == 0) goto L11
        Lb:
            boolean r0 = r2.Q
            if (r0 != 0) goto L11
            r0 = 1
            goto L12
        L11:
            r0 = r1
        L12:
            if (r0 == 0) goto L21
            boolean r0 = r2.hasFocus()
            if (r0 == 0) goto L21
            if (r3 != 0) goto L23
            boolean r3 = r2.X
            if (r3 != 0) goto L21
            goto L23
        L21:
            r1 = 8
        L23:
            android.widget.ImageView r2 = r2.u
            r2.setVisibility(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.F(boolean):void");
    }

    public final void G(boolean z) {
        this.Q = z;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.p.getText());
        this.t.setVisibility(i2);
        F(z2);
        this.q.setVisibility(z ? 8 : 0);
        this.D.setVisibility((this.D.getDrawable() == null || this.P) ? 8 : 0);
        B();
        H(!z2);
        E();
    }

    public final void H(boolean z) {
        int i2 = 8;
        if (this.X && !this.Q && z) {
            this.u.setVisibility(8);
            i2 = 0;
        }
        this.w.setVisibility(i2);
    }

    @Override // a.b.o.b
    public void a() {
        if (this.a0) {
            return;
        }
        this.a0 = true;
        int imeOptions = this.p.getImeOptions();
        this.b0 = imeOptions;
        this.p.setImeOptions(imeOptions | 33554432);
        this.p.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.V = true;
        super.clearFocus();
        this.p.clearFocus();
        this.p.setImeVisibility(false);
        this.V = false;
    }

    @Override // a.b.o.b
    public void f() {
        this.p.setText("");
        SearchAutoComplete searchAutoComplete = this.p;
        searchAutoComplete.setSelection(searchAutoComplete.length());
        this.Z = "";
        clearFocus();
        G(true);
        this.p.setImeOptions(this.b0);
        this.a0 = false;
    }

    public int getImeOptions() {
        return this.p.getImeOptions();
    }

    public int getInputType() {
        return this.p.getInputType();
    }

    public int getMaxWidth() {
        return this.W;
    }

    public CharSequence getQuery() {
        return this.p.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.T;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.c0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.J : getContext().getText(this.c0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.G;
    }

    public int getSuggestionRowLayout() {
        return this.F;
    }

    public a.i.a.a getSuggestionsAdapter() {
        return this.R;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.e0);
        post(this.f0);
        super.onDetachedFromWindow();
    }

    @Override // a.b.p.i0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            SearchAutoComplete searchAutoComplete = this.p;
            Rect rect = this.z;
            searchAutoComplete.getLocationInWindow(this.B);
            getLocationInWindow(this.C);
            int[] iArr = this.B;
            int i6 = iArr[1];
            int[] iArr2 = this.C;
            int i7 = i6 - iArr2[1];
            int i8 = iArr[0] - iArr2[0];
            rect.set(i8, i7, searchAutoComplete.getWidth() + i8, searchAutoComplete.getHeight() + i7);
            Rect rect2 = this.A;
            Rect rect3 = this.z;
            rect2.set(rect3.left, 0, rect3.right, i5 - i3);
            o oVar = this.y;
            if (oVar != null) {
                oVar.a(this.A, this.z);
                return;
            }
            o oVar2 = new o(this.A, this.z, this.p);
            this.y = oVar2;
            setTouchDelegate(oVar2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0049  */
    @Override // a.b.p.i0, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            boolean r0 = r3.Q
            if (r0 == 0) goto L8
            super.onMeasure(r4, r5)
            return
        L8:
            int r0 = android.view.View.MeasureSpec.getMode(r4)
            int r4 = android.view.View.MeasureSpec.getSize(r4)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 == r1) goto L2a
            if (r0 == 0) goto L20
            if (r0 == r2) goto L1b
            goto L37
        L1b:
            int r0 = r3.W
            if (r0 <= 0) goto L37
            goto L2e
        L20:
            int r4 = r3.W
            if (r4 <= 0) goto L25
            goto L37
        L25:
            int r4 = r3.getPreferredWidth()
            goto L37
        L2a:
            int r0 = r3.W
            if (r0 <= 0) goto L2f
        L2e:
            goto L33
        L2f:
            int r0 = r3.getPreferredWidth()
        L33:
            int r4 = java.lang.Math.min(r0, r4)
        L37:
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            if (r0 == r1) goto L49
            if (r0 == 0) goto L44
            goto L51
        L44:
            int r5 = r3.getPreferredHeight()
            goto L51
        L49:
            int r0 = r3.getPreferredHeight()
            int r5 = java.lang.Math.min(r0, r5)
        L51:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r2)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r2)
            super.onMeasure(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof n)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        n nVar = (n) parcelable;
        super.onRestoreInstanceState(nVar.f892a);
        G(nVar.f1459c);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        n nVar = new n(super.onSaveInstanceState());
        nVar.f1459c = this.Q;
        return nVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.e0);
    }

    public final Intent r(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.Z);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.d0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.c0.getSearchActivity());
        return intent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.V || !isFocusable()) {
            return false;
        }
        if (this.Q) {
            return super.requestFocus(i2, rect);
        }
        boolean zRequestFocus = this.p.requestFocus(i2, rect);
        if (zRequestFocus) {
            G(false);
        }
        return zRequestFocus;
    }

    public final Intent s(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.d0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public void setAppSearchData(Bundle bundle) {
        this.d0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            u();
        } else {
            y();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.P == z) {
            return;
        }
        this.P = z;
        G(z);
        D();
    }

    public void setImeOptions(int i2) {
        this.p.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.p.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.W = i2;
        requestLayout();
    }

    public void setOnCloseListener(k kVar) {
        this.L = kVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.M = onFocusChangeListener;
    }

    public void setOnQueryTextListener(l lVar) {
        this.K = lVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.O = onClickListener;
    }

    public void setOnSuggestionListener(m mVar) {
        this.N = mVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.T = charSequence;
        D();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.U = z;
        a.i.a.a aVar = this.R;
        if (aVar instanceof r0) {
            ((r0) aVar).r = z ? 2 : 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSearchableInfo(android.app.SearchableInfo r7) {
        /*
            r6 = this;
            r6.c0 = r7
            r0 = 1
            r1 = 65536(0x10000, float:9.1835E-41)
            r2 = 0
            if (r7 == 0) goto L6e
            androidx.appcompat.widget.SearchView$SearchAutoComplete r3 = r6.p
            int r7 = r7.getSuggestThreshold()
            r3.setThreshold(r7)
            androidx.appcompat.widget.SearchView$SearchAutoComplete r7 = r6.p
            android.app.SearchableInfo r3 = r6.c0
            int r3 = r3.getImeOptions()
            r7.setImeOptions(r3)
            android.app.SearchableInfo r7 = r6.c0
            int r7 = r7.getInputType()
            r3 = r7 & 15
            if (r3 != r0) goto L36
            r3 = -65537(0xfffffffffffeffff, float:NaN)
            r7 = r7 & r3
            android.app.SearchableInfo r3 = r6.c0
            java.lang.String r3 = r3.getSuggestAuthority()
            if (r3 == 0) goto L36
            r7 = r7 | r1
            r3 = 524288(0x80000, float:7.34684E-40)
            r7 = r7 | r3
        L36:
            androidx.appcompat.widget.SearchView$SearchAutoComplete r3 = r6.p
            r3.setInputType(r7)
            a.i.a.a r7 = r6.R
            if (r7 == 0) goto L42
            r7.b(r2)
        L42:
            android.app.SearchableInfo r7 = r6.c0
            java.lang.String r7 = r7.getSuggestAuthority()
            if (r7 == 0) goto L6b
            a.b.p.r0 r7 = new a.b.p.r0
            android.content.Context r3 = r6.getContext()
            android.app.SearchableInfo r4 = r6.c0
            java.util.WeakHashMap<java.lang.String, android.graphics.drawable.Drawable$ConstantState> r5 = r6.g0
            r7.<init>(r3, r6, r4, r5)
            r6.R = r7
            androidx.appcompat.widget.SearchView$SearchAutoComplete r3 = r6.p
            r3.setAdapter(r7)
            a.i.a.a r7 = r6.R
            a.b.p.r0 r7 = (a.b.p.r0) r7
            boolean r3 = r6.U
            if (r3 == 0) goto L68
            r3 = 2
            goto L69
        L68:
            r3 = r0
        L69:
            r7.r = r3
        L6b:
            r6.D()
        L6e:
            android.app.SearchableInfo r7 = r6.c0
            r3 = 0
            if (r7 == 0) goto L9f
            boolean r7 = r7.getVoiceSearchEnabled()
            if (r7 == 0) goto L9f
            android.app.SearchableInfo r7 = r6.c0
            boolean r7 = r7.getVoiceSearchLaunchWebSearch()
            if (r7 == 0) goto L84
            android.content.Intent r2 = r6.H
            goto L8e
        L84:
            android.app.SearchableInfo r7 = r6.c0
            boolean r7 = r7.getVoiceSearchLaunchRecognizer()
            if (r7 == 0) goto L8e
            android.content.Intent r2 = r6.I
        L8e:
            if (r2 == 0) goto L9f
            android.content.Context r7 = r6.getContext()
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            android.content.pm.ResolveInfo r7 = r7.resolveActivity(r2, r1)
            if (r7 == 0) goto L9f
            goto La0
        L9f:
            r0 = r3
        La0:
            r6.X = r0
            if (r0 == 0) goto Lab
            androidx.appcompat.widget.SearchView$SearchAutoComplete r7 = r6.p
            java.lang.String r0 = "nm"
            r7.setPrivateImeOptions(r0)
        Lab:
            boolean r7 = r6.Q
            r6.G(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.S = z;
        G(this.Q);
    }

    public void setSuggestionsAdapter(a.i.a.a aVar) {
        this.R = aVar;
        this.p.setAdapter(aVar);
    }

    public void t(int i2, String str, String str2) {
        getContext().startActivity(r("android.intent.action.SEARCH", null, null, str2, i2, null));
    }

    public void u() {
        if (!TextUtils.isEmpty(this.p.getText())) {
            this.p.setText("");
            this.p.requestFocus();
            this.p.setImeVisibility(true);
        } else if (this.P) {
            k kVar = this.L;
            if (kVar == null || !kVar.a()) {
                clearFocus();
                G(true);
            }
        }
    }

    public boolean v(int i2) {
        int position;
        String strH;
        m mVar = this.N;
        if (mVar != null && mVar.b(i2)) {
            return false;
        }
        Cursor cursor = this.R.f884c;
        if (cursor != null && cursor.moveToPosition(i2)) {
            Intent intentR = null;
            try {
                String strH2 = r0.h(cursor, cursor.getColumnIndex("suggest_intent_action"));
                if (strH2 == null) {
                    strH2 = this.c0.getSuggestIntentAction();
                }
                if (strH2 == null) {
                    strH2 = "android.intent.action.SEARCH";
                }
                String str = strH2;
                String strH3 = r0.h(cursor, cursor.getColumnIndex("suggest_intent_data"));
                if (strH3 == null) {
                    strH3 = this.c0.getSuggestIntentData();
                }
                if (strH3 != null && (strH = r0.h(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                    strH3 = strH3 + "/" + Uri.encode(strH);
                }
                intentR = r(str, strH3 == null ? null : Uri.parse(strH3), r0.h(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), r0.h(cursor, cursor.getColumnIndex("suggest_intent_query")), 0, null);
            } catch (RuntimeException e2) {
                try {
                    position = cursor.getPosition();
                } catch (RuntimeException unused) {
                    position = -1;
                }
                Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e2);
            }
            if (intentR != null) {
                try {
                    getContext().startActivity(intentR);
                } catch (RuntimeException e3) {
                    Log.e("SearchView", "Failed launch activity: " + intentR, e3);
                }
            }
        }
        this.p.setImeVisibility(false);
        this.p.dismissDropDown();
        return true;
    }

    public boolean w(int i2) {
        CharSequence charSequenceC;
        m mVar = this.N;
        if (mVar != null && mVar.a(i2)) {
            return false;
        }
        Editable text = this.p.getText();
        Cursor cursor = this.R.f884c;
        if (cursor == null) {
            return true;
        }
        if (!cursor.moveToPosition(i2) || (charSequenceC = this.R.c(cursor)) == null) {
            setQuery(text);
            return true;
        }
        setQuery(charSequenceC);
        return true;
    }

    public void x(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public void y() {
        G(false);
        this.p.requestFocus();
        this.p.setImeVisibility(true);
        View.OnClickListener onClickListener = this.O;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void z() {
        Editable text = this.p.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        l lVar = this.K;
        if (lVar == null || !lVar.a(text.toString())) {
            if (this.c0 != null) {
                t(0, null, text.toString());
            }
            this.p.setImeVisibility(false);
            this.p.dismissDropDown();
        }
    }
}
