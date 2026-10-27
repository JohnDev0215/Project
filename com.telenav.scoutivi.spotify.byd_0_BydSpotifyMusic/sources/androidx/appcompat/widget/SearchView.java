package androidx.appcompat.widget;

import android.annotation.SuppressLint;
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
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.net.Uri;
import android.os.Build;
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
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements i.c {

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    static final n f895v0;
    final ImageView A;
    final ImageView B;
    private final View C;
    private o D;
    private Rect E;
    private Rect F;
    private int[] G;
    private int[] H;
    private final ImageView I;
    private final Drawable J;
    private final int K;
    private final int L;
    private final Intent M;
    private final Intent N;
    private final CharSequence O;
    private l P;
    private k Q;
    View.OnFocusChangeListener R;
    private m S;
    private View.OnClickListener T;
    private boolean U;
    private boolean V;
    l0.a W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private boolean f896a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private CharSequence f897b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private boolean f898c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private boolean f899d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private int f900e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private boolean f901f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private CharSequence f902g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private CharSequence f903h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private boolean f904i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private int f905j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    SearchableInfo f906k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private Bundle f907l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private final Runnable f908m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private Runnable f909n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private final WeakHashMap<String, Drawable.ConstantState> f910o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final View.OnClickListener f911p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    View.OnKeyListener f912q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private final TextView.OnEditorActionListener f913r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private final AdapterView.OnItemClickListener f914s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private final AdapterView.OnItemSelectedListener f915t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    final SearchAutoComplete f916u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private TextWatcher f917u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final View f918v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final View f919w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final View f920x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    final ImageView f921y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    final ImageView f922z;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f923h;

        class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f923h + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeValue(Boolean.valueOf(this.f923h));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f923h = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends androidx.appcompat.widget.c {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f924i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private SearchView f925j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private boolean f926k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final Runnable f927l;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.c();
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, e.a.autoCompleteTextViewStyle);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i7 = configuration.screenWidthDp;
            int i8 = configuration.screenHeightDp;
            if (i7 >= 960 && i8 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i7 < 600) {
                return (i7 < 640 || i8 < 480) ? 160 : 192;
            }
            return 192;
        }

        void a() {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.f895v0.c(this);
                return;
            }
            setInputMethodMode(1);
            if (enoughToFilter()) {
                showDropDown();
            }
        }

        boolean b() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void c() {
            if (this.f926k) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f926k = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f924i <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.c, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f926k) {
                removeCallbacks(this.f927l);
                post(this.f927l);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z6, int i7, Rect rect) {
            super.onFocusChanged(z6, i7, rect);
            this.f925j.Z();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i7, KeyEvent keyEvent) {
            if (i7 == 4) {
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
                        this.f925j.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i7, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z6) {
            super.onWindowFocusChanged(z6);
            if (z6 && this.f925j.hasFocus() && getVisibility() == 0) {
                this.f926k = true;
                if (SearchView.M(getContext())) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence) {
        }

        void setImeVisibility(boolean z6) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z6) {
                this.f926k = false;
                removeCallbacks(this.f927l);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f926k = true;
                    return;
                }
                this.f926k = false;
                removeCallbacks(this.f927l);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        void setSearchView(SearchView searchView) {
            this.f925j = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i7) {
            super.setThreshold(i7);
            this.f924i = i7;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i7) {
            super(context, attributeSet, i7);
            this.f927l = new a();
            this.f924i = getThreshold();
        }
    }

    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
            SearchView.this.Y(charSequence);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.f0();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l0.a aVar = SearchView.this.W;
            if (aVar instanceof m0) {
                aVar.b(null);
            }
        }
    }

    class d implements View.OnFocusChangeListener {
        d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z6) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.R;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z6);
            }
        }
    }

    class e implements View.OnLayoutChangeListener {
        e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
            SearchView.this.B();
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.f921y) {
                searchView.V();
                return;
            }
            if (view == searchView.A) {
                searchView.R();
                return;
            }
            if (view == searchView.f922z) {
                searchView.W();
            } else if (view == searchView.B) {
                searchView.a0();
            } else if (view == searchView.f916u) {
                searchView.H();
            }
        }
    }

    class g implements View.OnKeyListener {
        g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i7, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.f906k0 == null) {
                return false;
            }
            if (searchView.f916u.isPopupShowing() && SearchView.this.f916u.getListSelection() != -1) {
                return SearchView.this.X(view, i7, keyEvent);
            }
            if (SearchView.this.f916u.b() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i7 != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.P(0, null, searchView2.f916u.getText().toString());
            return true;
        }
    }

    class h implements TextView.OnEditorActionListener {
        h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i7, KeyEvent keyEvent) {
            SearchView.this.W();
            return true;
        }
    }

    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
            SearchView.this.S(i7, 0, null);
        }
    }

    class j implements AdapterView.OnItemSelectedListener {
        j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i7, long j7) {
            SearchView.this.T(i7);
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
        boolean a(int i7);

        boolean b(int i7);
    }

    private static class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Method f939a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Method f940b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f941c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        n() {
            this.f939a = null;
            this.f940b = null;
            this.f941c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f939a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f940b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f941c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        private static void d() {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f940b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f939a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f941c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    private static class o extends TouchDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Rect f943b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Rect f944c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Rect f945d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f946e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f947f;

        public o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f946e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f943b = new Rect();
            this.f945d = new Rect();
            this.f944c = new Rect();
            a(rect, rect2);
            this.f942a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f943b.set(rect);
            this.f945d.set(rect);
            Rect rect3 = this.f945d;
            int i7 = this.f946e;
            rect3.inset(-i7, -i7);
            this.f944c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z6;
            boolean z7;
            int x6 = (int) motionEvent.getX();
            int y6 = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z8 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z7 = this.f947f;
                    if (z7 && !this.f945d.contains(x6, y6)) {
                        z8 = z7;
                        z6 = false;
                    }
                } else {
                    if (action == 3) {
                        z7 = this.f947f;
                        this.f947f = false;
                    }
                    z6 = true;
                    z8 = false;
                }
                z8 = z7;
                z6 = true;
            } else if (this.f943b.contains(x6, y6)) {
                this.f947f = true;
                z6 = true;
            } else {
                z6 = true;
                z8 = false;
            }
            if (!z8) {
                return false;
            }
            if (!z6 || this.f944c.contains(x6, y6)) {
                Rect rect = this.f944c;
                motionEvent.setLocation(x6 - rect.left, y6 - rect.top);
            } else {
                motionEvent.setLocation(this.f942a.getWidth() / 2, this.f942a.getHeight() / 2);
            }
            return this.f942a.dispatchTouchEvent(motionEvent);
        }
    }

    static {
        f895v0 = Build.VERSION.SDK_INT < 29 ? new n() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private Intent C(String str, Uri uri, String str2, String str3, int i7, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f903h0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f907l0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i7 != 0) {
            intent.putExtra("action_key", i7);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.f906k0.getSearchActivity());
        return intent;
    }

    private Intent D(Cursor cursor, int i7, String str) {
        int position;
        String strO;
        try {
            String strO2 = m0.o(cursor, "suggest_intent_action");
            if (strO2 == null) {
                strO2 = this.f906k0.getSuggestIntentAction();
            }
            if (strO2 == null) {
                strO2 = "android.intent.action.SEARCH";
            }
            String str2 = strO2;
            String strO3 = m0.o(cursor, "suggest_intent_data");
            if (strO3 == null) {
                strO3 = this.f906k0.getSuggestIntentData();
            }
            if (strO3 != null && (strO = m0.o(cursor, "suggest_intent_data_id")) != null) {
                strO3 = strO3 + "/" + Uri.encode(strO);
            }
            return C(str2, strO3 == null ? null : Uri.parse(strO3), m0.o(cursor, "suggest_intent_extra_data"), m0.o(cursor, "suggest_intent_query"), i7, str);
        } catch (RuntimeException e7) {
            try {
                position = cursor.getPosition();
            } catch (RuntimeException unused) {
                position = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e7);
            return null;
        }
    }

    private Intent E(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f907l0;
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

    private Intent F(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private void G() {
        this.f916u.dismissDropDown();
    }

    private void I(View view, Rect rect) {
        view.getLocationInWindow(this.G);
        getLocationInWindow(this.H);
        int[] iArr = this.G;
        int i7 = iArr[1];
        int[] iArr2 = this.H;
        int i8 = i7 - iArr2[1];
        int i9 = iArr[0] - iArr2[0];
        rect.set(i9, i8, view.getWidth() + i9, view.getHeight() + i8);
    }

    private CharSequence J(CharSequence charSequence) {
        if (!this.U || this.J == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.f916u.getTextSize()) * 1.25d);
        this.J.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.J), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private boolean K() {
        SearchableInfo searchableInfo = this.f906k0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.f906k0.getVoiceSearchLaunchWebSearch()) {
            intent = this.M;
        } else if (this.f906k0.getVoiceSearchLaunchRecognizer()) {
            intent = this.N;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    static boolean M(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private boolean N() {
        return (this.f896a0 || this.f901f0) && !L();
    }

    private void O(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e7) {
            Log.e("SearchView", "Failed launch activity: " + intent, e7);
        }
    }

    private boolean Q(int i7, int i8, String str) {
        Cursor cursorC = this.W.c();
        if (cursorC == null || !cursorC.moveToPosition(i7)) {
            return false;
        }
        O(D(cursorC, i8, str));
        return true;
    }

    private void b0() {
        post(this.f908m0);
    }

    private void c0(int i7) {
        Editable text = this.f916u.getText();
        Cursor cursorC = this.W.c();
        if (cursorC == null) {
            return;
        }
        if (!cursorC.moveToPosition(i7)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceA = this.W.a(cursorC);
        if (charSequenceA != null) {
            setQuery(charSequenceA);
        } else {
            setQuery(text);
        }
    }

    private void e0() {
        boolean z6 = true;
        boolean z7 = !TextUtils.isEmpty(this.f916u.getText());
        if (!z7 && (!this.U || this.f904i0)) {
            z6 = false;
        }
        this.A.setVisibility(z6 ? 0 : 8);
        Drawable drawable = this.A.getDrawable();
        if (drawable != null) {
            drawable.setState(z7 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void g0() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f916u;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(J(queryHint));
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(e.d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(e.d.abc_search_view_preferred_width);
    }

    private void h0() {
        this.f916u.setThreshold(this.f906k0.getSuggestThreshold());
        this.f916u.setImeOptions(this.f906k0.getImeOptions());
        int inputType = this.f906k0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f906k0.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.f916u.setInputType(inputType);
        l0.a aVar = this.W;
        if (aVar != null) {
            aVar.b(null);
        }
        if (this.f906k0.getSuggestAuthority() != null) {
            m0 m0Var = new m0(getContext(), this, this.f906k0, this.f910o0);
            this.W = m0Var;
            this.f916u.setAdapter(m0Var);
            ((m0) this.W).x(this.f898c0 ? 2 : 1);
        }
    }

    private void i0() {
        this.f920x.setVisibility((N() && (this.f922z.getVisibility() == 0 || this.B.getVisibility() == 0)) ? 0 : 8);
    }

    private void j0(boolean z6) {
        this.f922z.setVisibility((this.f896a0 && N() && hasFocus() && (z6 || !this.f901f0)) ? 0 : 8);
    }

    private void k0(boolean z6) {
        this.V = z6;
        int i7 = z6 ? 0 : 8;
        boolean z7 = !TextUtils.isEmpty(this.f916u.getText());
        this.f921y.setVisibility(i7);
        j0(z7);
        this.f918v.setVisibility(z6 ? 8 : 0);
        this.I.setVisibility((this.I.getDrawable() == null || this.U) ? 8 : 0);
        e0();
        l0(!z7);
        i0();
    }

    private void l0(boolean z6) {
        int i7 = 8;
        if (this.f901f0 && !L() && z6) {
            this.f922z.setVisibility(8);
            i7 = 0;
        }
        this.B.setVisibility(i7);
    }

    private void setQuery(CharSequence charSequence) {
        this.f916u.setText(charSequence);
        this.f916u.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    void B() {
        if (this.C.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.f919w.getPaddingLeft();
            Rect rect = new Rect();
            boolean zB = w0.b(this);
            int dimensionPixelSize = this.U ? resources.getDimensionPixelSize(e.d.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(e.d.abc_dropdownitem_text_padding_left) : 0;
            this.f916u.getDropDownBackground().getPadding(rect);
            this.f916u.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.f916u.setDropDownWidth((((this.C.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    void H() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.f916u.refreshAutoCompleteResults();
            return;
        }
        n nVar = f895v0;
        nVar.b(this.f916u);
        nVar.a(this.f916u);
    }

    public boolean L() {
        return this.V;
    }

    void P(int i7, String str, String str2) {
        getContext().startActivity(C("android.intent.action.SEARCH", null, null, str2, i7, str));
    }

    void R() {
        if (!TextUtils.isEmpty(this.f916u.getText())) {
            this.f916u.setText("");
            this.f916u.requestFocus();
            this.f916u.setImeVisibility(true);
        } else if (this.U) {
            k kVar = this.Q;
            if (kVar == null || !kVar.a()) {
                clearFocus();
                k0(true);
            }
        }
    }

    boolean S(int i7, int i8, String str) {
        m mVar = this.S;
        if (mVar != null && mVar.b(i7)) {
            return false;
        }
        Q(i7, 0, null);
        this.f916u.setImeVisibility(false);
        G();
        return true;
    }

    boolean T(int i7) {
        m mVar = this.S;
        if (mVar != null && mVar.a(i7)) {
            return false;
        }
        c0(i7);
        return true;
    }

    void U(CharSequence charSequence) {
        setQuery(charSequence);
    }

    void V() {
        k0(false);
        this.f916u.requestFocus();
        this.f916u.setImeVisibility(true);
        View.OnClickListener onClickListener = this.T;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    void W() {
        Editable text = this.f916u.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        l lVar = this.P;
        if (lVar == null || !lVar.b(text.toString())) {
            if (this.f906k0 != null) {
                P(0, null, text.toString());
            }
            this.f916u.setImeVisibility(false);
            G();
        }
    }

    boolean X(View view, int i7, KeyEvent keyEvent) {
        if (this.f906k0 != null && this.W != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i7 == 66 || i7 == 84 || i7 == 61) {
                return S(this.f916u.getListSelection(), 0, null);
            }
            if (i7 == 21 || i7 == 22) {
                this.f916u.setSelection(i7 == 21 ? 0 : this.f916u.length());
                this.f916u.setListSelection(0);
                this.f916u.clearListSelection();
                this.f916u.a();
                return true;
            }
            if (i7 == 19) {
                this.f916u.getListSelection();
                return false;
            }
        }
        return false;
    }

    void Y(CharSequence charSequence) {
        Editable text = this.f916u.getText();
        this.f903h0 = text;
        boolean z6 = !TextUtils.isEmpty(text);
        j0(z6);
        l0(!z6);
        e0();
        i0();
        if (this.P != null && !TextUtils.equals(charSequence, this.f902g0)) {
            this.P.a(charSequence.toString());
        }
        this.f902g0 = charSequence.toString();
    }

    void Z() {
        k0(L());
        b0();
        if (this.f916u.hasFocus()) {
            H();
        }
    }

    void a0() {
        SearchableInfo searchableInfo = this.f906k0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(F(this.M, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(E(this.N, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    @Override // i.c
    public void c() {
        if (this.f904i0) {
            return;
        }
        this.f904i0 = true;
        int imeOptions = this.f916u.getImeOptions();
        this.f905j0 = imeOptions;
        this.f916u.setImeOptions(imeOptions | 33554432);
        this.f916u.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.f899d0 = true;
        super.clearFocus();
        this.f916u.clearFocus();
        this.f916u.setImeVisibility(false);
        this.f899d0 = false;
    }

    public void d0(CharSequence charSequence, boolean z6) {
        this.f916u.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f916u;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.f903h0 = charSequence;
        }
        if (!z6 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        W();
    }

    @Override // i.c
    public void f() {
        d0("", false);
        clearFocus();
        k0(true);
        this.f916u.setImeOptions(this.f905j0);
        this.f904i0 = false;
    }

    void f0() {
        int[] iArr = this.f916u.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f919w.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f920x.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public int getImeOptions() {
        return this.f916u.getImeOptions();
    }

    public int getInputType() {
        return this.f916u.getInputType();
    }

    public int getMaxWidth() {
        return this.f900e0;
    }

    public CharSequence getQuery() {
        return this.f916u.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.f897b0;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f906k0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.O : getContext().getText(this.f906k0.getHintId());
    }

    int getSuggestionCommitIconResId() {
        return this.L;
    }

    int getSuggestionRowLayout() {
        return this.K;
    }

    public l0.a getSuggestionsAdapter() {
        return this.W;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.f908m0);
        post(this.f909n0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6) {
            I(this.f916u, this.E);
            Rect rect = this.F;
            Rect rect2 = this.E;
            rect.set(rect2.left, 0, rect2.right, i10 - i8);
            o oVar = this.D;
            if (oVar != null) {
                oVar.a(this.F, this.E);
                return;
            }
            o oVar2 = new o(this.F, this.E, this.f916u);
            this.D = oVar2;
            setTouchDelegate(oVar2);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    protected void onMeasure(int i7, int i8) {
        int i9;
        if (L()) {
            super.onMeasure(i7, i8);
            return;
        }
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (mode == Integer.MIN_VALUE) {
            int i10 = this.f900e0;
            size = i10 > 0 ? Math.min(i10, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.f900e0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i9 = this.f900e0) > 0) {
            size = Math.min(i9, size);
        }
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(size2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        k0(savedState.f923h);
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f923h = L();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        b0();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i7, Rect rect) {
        if (this.f899d0 || !isFocusable()) {
            return false;
        }
        if (L()) {
            return super.requestFocus(i7, rect);
        }
        boolean zRequestFocus = this.f916u.requestFocus(i7, rect);
        if (zRequestFocus) {
            k0(false);
        }
        return zRequestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.f907l0 = bundle;
    }

    public void setIconified(boolean z6) {
        if (z6) {
            R();
        } else {
            V();
        }
    }

    public void setIconifiedByDefault(boolean z6) {
        if (this.U == z6) {
            return;
        }
        this.U = z6;
        k0(z6);
        g0();
    }

    public void setImeOptions(int i7) {
        this.f916u.setImeOptions(i7);
    }

    public void setInputType(int i7) {
        this.f916u.setInputType(i7);
    }

    public void setMaxWidth(int i7) {
        this.f900e0 = i7;
        requestLayout();
    }

    public void setOnCloseListener(k kVar) {
        this.Q = kVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.R = onFocusChangeListener;
    }

    public void setOnQueryTextListener(l lVar) {
        this.P = lVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.T = onClickListener;
    }

    public void setOnSuggestionListener(m mVar) {
        this.S = mVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.f897b0 = charSequence;
        g0();
    }

    public void setQueryRefinementEnabled(boolean z6) {
        this.f898c0 = z6;
        l0.a aVar = this.W;
        if (aVar instanceof m0) {
            ((m0) aVar).x(z6 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f906k0 = searchableInfo;
        if (searchableInfo != null) {
            h0();
            g0();
        }
        boolean zK = K();
        this.f901f0 = zK;
        if (zK) {
            this.f916u.setPrivateImeOptions("nm");
        }
        k0(L());
    }

    public void setSubmitButtonEnabled(boolean z6) {
        this.f896a0 = z6;
        k0(L());
    }

    public void setSuggestionsAdapter(l0.a aVar) {
        this.W = aVar;
        this.f916u.setAdapter(aVar);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.E = new Rect();
        this.F = new Rect();
        this.G = new int[2];
        this.H = new int[2];
        this.f908m0 = new b();
        this.f909n0 = new c();
        this.f910o0 = new WeakHashMap<>();
        f fVar = new f();
        this.f911p0 = fVar;
        this.f912q0 = new g();
        h hVar = new h();
        this.f913r0 = hVar;
        i iVar = new i();
        this.f914s0 = iVar;
        j jVar = new j();
        this.f915t0 = jVar;
        this.f917u0 = new a();
        s0 s0VarV = s0.v(context, attributeSet, e.j.SearchView, i7, 0);
        LayoutInflater.from(context).inflate(s0VarV.n(e.j.SearchView_layout, e.g.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(e.f.search_src_text);
        this.f916u = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f918v = findViewById(e.f.search_edit_frame);
        View viewFindViewById = findViewById(e.f.search_plate);
        this.f919w = viewFindViewById;
        View viewFindViewById2 = findViewById(e.f.submit_area);
        this.f920x = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(e.f.search_button);
        this.f921y = imageView;
        ImageView imageView2 = (ImageView) findViewById(e.f.search_go_btn);
        this.f922z = imageView2;
        ImageView imageView3 = (ImageView) findViewById(e.f.search_close_btn);
        this.A = imageView3;
        ImageView imageView4 = (ImageView) findViewById(e.f.search_voice_btn);
        this.B = imageView4;
        ImageView imageView5 = (ImageView) findViewById(e.f.search_mag_icon);
        this.I = imageView5;
        i0.u.n0(viewFindViewById, s0VarV.g(e.j.SearchView_queryBackground));
        i0.u.n0(viewFindViewById2, s0VarV.g(e.j.SearchView_submitBackground));
        int i8 = e.j.SearchView_searchIcon;
        imageView.setImageDrawable(s0VarV.g(i8));
        imageView2.setImageDrawable(s0VarV.g(e.j.SearchView_goIcon));
        imageView3.setImageDrawable(s0VarV.g(e.j.SearchView_closeIcon));
        imageView4.setImageDrawable(s0VarV.g(e.j.SearchView_voiceIcon));
        imageView5.setImageDrawable(s0VarV.g(i8));
        this.J = s0VarV.g(e.j.SearchView_searchHintIcon);
        u0.a(imageView, getResources().getString(e.h.abc_searchview_description_search));
        this.K = s0VarV.n(e.j.SearchView_suggestionRowLayout, e.g.abc_search_dropdown_item_icons_2line);
        this.L = s0VarV.n(e.j.SearchView_commitIcon, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.f917u0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.f912q0);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(s0VarV.a(e.j.SearchView_iconifiedByDefault, true));
        int iF = s0VarV.f(e.j.SearchView_android_maxWidth, -1);
        if (iF != -1) {
            setMaxWidth(iF);
        }
        this.O = s0VarV.p(e.j.SearchView_defaultQueryHint);
        this.f897b0 = s0VarV.p(e.j.SearchView_queryHint);
        int iK = s0VarV.k(e.j.SearchView_android_imeOptions, -1);
        if (iK != -1) {
            setImeOptions(iK);
        }
        int iK2 = s0VarV.k(e.j.SearchView_android_inputType, -1);
        if (iK2 != -1) {
            setInputType(iK2);
        }
        setFocusable(s0VarV.a(e.j.SearchView_android_focusable, true));
        s0VarV.w();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.M = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.N = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.C = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new e());
        }
        k0(this.U);
        g0();
    }
}
