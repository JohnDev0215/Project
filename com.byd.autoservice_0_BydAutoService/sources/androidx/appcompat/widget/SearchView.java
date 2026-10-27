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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import l0.t;
import o.e0;
import o.g0;
import o.k0;
import o.y;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements m.c {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final n f589q;
    public o A;
    public Rect B;
    public Rect C;
    public int[] D;
    public int[] E;
    public final ImageView F;
    public final Drawable G;
    public final int H;
    public final int I;
    public final Intent J;
    public final Intent K;
    public final CharSequence L;
    public l M;
    public k N;
    public View.OnFocusChangeListener O;
    public m P;
    public View.OnClickListener Q;
    public boolean R;
    public boolean S;
    public o0.a T;
    public boolean U;
    public CharSequence V;
    public boolean W;
    public boolean X;
    public int Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public CharSequence f590a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public CharSequence f591b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f592c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f593d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public SearchableInfo f594e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public Bundle f595f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final Runnable f596g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public Runnable f597h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final WeakHashMap<String, Drawable.ConstantState> f598i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final View.OnClickListener f599j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public View.OnKeyListener f600k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final TextView.OnEditorActionListener f601l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final AdapterView.OnItemClickListener f602m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final AdapterView.OnItemSelectedListener f603n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public TextWatcher f604o0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final SearchAutoComplete f605r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final View f606s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final View f607t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final View f608u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ImageView f609v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ImageView f610w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ImageView f611x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ImageView f612y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final View f613z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f614d;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
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
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f614d + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeValue(Boolean.valueOf(this.f614d));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f614d = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f615e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SearchView f616f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f617g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final Runnable f618h;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.c();
            }
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i3 = configuration.screenWidthDp;
            int i4 = configuration.screenHeightDp;
            if (i3 >= 960 && i4 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i3 < 600) {
                return (i3 < 640 || i4 < 480) ? 160 : 192;
            }
            return 192;
        }

        public void a() {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.f589q.c(this);
                return;
            }
            setInputMethodMode(1);
            if (enoughToFilter()) {
                showDropDown();
            }
        }

        public boolean b() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        public void c() {
            if (this.f617g) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f617g = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f615e <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f617g) {
                removeCallbacks(this.f618h);
                post(this.f618h);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z3, int i3, Rect rect) {
            super.onFocusChanged(z3, i3, rect);
            this.f616f.Z();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i3, KeyEvent keyEvent) {
            if (i3 == 4) {
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
                        this.f616f.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i3, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z3) {
            super.onWindowFocusChanged(z3);
            if (z3 && this.f616f.hasFocus() && getVisibility() == 0) {
                this.f617g = true;
                if (SearchView.M(getContext())) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z3) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z3) {
                this.f617g = false;
                removeCallbacks(this.f618h);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f617g = true;
                    return;
                }
                this.f617g = false;
                removeCallbacks(this.f618h);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f616f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i3) {
            super.setThreshold(i3);
            this.f615e = i3;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i3) {
            super(context, attributeSet, i3);
            this.f618h = new a();
            this.f615e = getThreshold();
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            SearchView.this.Y(charSequence);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.f0();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            o0.a aVar = SearchView.this.T;
            if (aVar instanceof y) {
                aVar.changeCursor(null);
            }
        }
    }

    public class d implements View.OnFocusChangeListener {
        public d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z3) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.O;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z3);
            }
        }
    }

    public class e implements View.OnLayoutChangeListener {
        public e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            SearchView.this.B();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.f609v) {
                searchView.V();
                return;
            }
            if (view == searchView.f611x) {
                searchView.R();
                return;
            }
            if (view == searchView.f610w) {
                searchView.W();
            } else if (view == searchView.f612y) {
                searchView.a0();
            } else if (view == searchView.f605r) {
                searchView.H();
            }
        }
    }

    public class g implements View.OnKeyListener {
        public g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i3, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.f594e0 == null) {
                return false;
            }
            if (searchView.f605r.isPopupShowing() && SearchView.this.f605r.getListSelection() != -1) {
                return SearchView.this.X(view, i3, keyEvent);
            }
            if (SearchView.this.f605r.b() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i3 != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.P(0, null, searchView2.f605r.getText().toString());
            return true;
        }
    }

    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i3, KeyEvent keyEvent) {
            SearchView.this.W();
            return true;
        }
    }

    public class i implements AdapterView.OnItemClickListener {
        public i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
            SearchView.this.S(i3, 0, null);
        }
    }

    public class j implements AdapterView.OnItemSelectedListener {
        public j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j3) {
            SearchView.this.T(i3);
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
        boolean a(int i3);

        boolean b(int i3);
    }

    public static class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Method f630a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Method f631b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f632c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        public n() {
            this.f630a = null;
            this.f631b = null;
            this.f632c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f630a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f631b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f632c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        public static void d() {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
            }
        }

        public void a(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f631b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f630a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f632c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    public static class o extends TouchDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f633a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f634b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Rect f635c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f636d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f637e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f638f;

        public o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f637e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f634b = new Rect();
            this.f636d = new Rect();
            this.f635c = new Rect();
            a(rect, rect2);
            this.f633a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f634b.set(rect);
            this.f636d.set(rect);
            Rect rect3 = this.f636d;
            int i3 = this.f637e;
            rect3.inset(-i3, -i3);
            this.f635c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z3;
            boolean z4;
            int x3 = (int) motionEvent.getX();
            int y3 = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z5 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z4 = this.f638f;
                    if (z4 && !this.f636d.contains(x3, y3)) {
                        z5 = z4;
                        z3 = false;
                    }
                } else {
                    if (action == 3) {
                        z4 = this.f638f;
                        this.f638f = false;
                    }
                    z3 = true;
                    z5 = false;
                }
                z5 = z4;
                z3 = true;
            } else if (this.f634b.contains(x3, y3)) {
                this.f638f = true;
                z3 = true;
            } else {
                z3 = true;
                z5 = false;
            }
            if (!z5) {
                return false;
            }
            if (!z3 || this.f635c.contains(x3, y3)) {
                Rect rect = this.f635c;
                motionEvent.setLocation(x3 - rect.left, y3 - rect.top);
            } else {
                motionEvent.setLocation(this.f633a.getWidth() / 2, this.f633a.getHeight() / 2);
            }
            return this.f633a.dispatchTouchEvent(motionEvent);
        }
    }

    static {
        f589q = Build.VERSION.SDK_INT < 29 ? new n() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public static boolean M(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        this.f605r.setText(charSequence);
        this.f605r.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public void B() {
        if (this.f613z.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.f607t.getPaddingLeft();
            Rect rect = new Rect();
            boolean zB = k0.b(this);
            int dimensionPixelSize = this.R ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) : 0;
            this.f605r.getDropDownBackground().getPadding(rect);
            this.f605r.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.f605r.setDropDownWidth((((this.f613z.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public final Intent C(String str, Uri uri, String str2, String str3, int i3, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f591b0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f595f0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i3 != 0) {
            intent.putExtra("action_key", i3);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.f594e0.getSearchActivity());
        return intent;
    }

    public final Intent D(Cursor cursor, int i3, String str) {
        int position;
        String strK;
        try {
            String strK2 = y.k(cursor, "suggest_intent_action");
            if (strK2 == null) {
                strK2 = this.f594e0.getSuggestIntentAction();
            }
            if (strK2 == null) {
                strK2 = "android.intent.action.SEARCH";
            }
            String str2 = strK2;
            String strK3 = y.k(cursor, "suggest_intent_data");
            if (strK3 == null) {
                strK3 = this.f594e0.getSuggestIntentData();
            }
            if (strK3 != null && (strK = y.k(cursor, "suggest_intent_data_id")) != null) {
                strK3 = strK3 + "/" + Uri.encode(strK);
            }
            return C(str2, strK3 == null ? null : Uri.parse(strK3), y.k(cursor, "suggest_intent_extra_data"), y.k(cursor, "suggest_intent_query"), i3, str);
        } catch (RuntimeException e3) {
            try {
                position = cursor.getPosition();
            } catch (RuntimeException unused) {
                position = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e3);
            return null;
        }
    }

    public final Intent E(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f595f0;
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

    public final Intent F(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    public final void G() {
        this.f605r.dismissDropDown();
    }

    public void H() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.f605r.refreshAutoCompleteResults();
            return;
        }
        n nVar = f589q;
        nVar.b(this.f605r);
        nVar.a(this.f605r);
    }

    public final void I(View view, Rect rect) {
        view.getLocationInWindow(this.D);
        getLocationInWindow(this.E);
        int[] iArr = this.D;
        int i3 = iArr[1];
        int[] iArr2 = this.E;
        int i4 = i3 - iArr2[1];
        int i5 = iArr[0] - iArr2[0];
        rect.set(i5, i4, view.getWidth() + i5, view.getHeight() + i4);
    }

    public final CharSequence J(CharSequence charSequence) {
        if (!this.R || this.G == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.f605r.getTextSize()) * 1.25d);
        this.G.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.G), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    public final boolean K() {
        SearchableInfo searchableInfo = this.f594e0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.f594e0.getVoiceSearchLaunchWebSearch()) {
            intent = this.J;
        } else if (this.f594e0.getVoiceSearchLaunchRecognizer()) {
            intent = this.K;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    public boolean L() {
        return this.S;
    }

    public final boolean N() {
        return (this.U || this.Z) && !L();
    }

    public final void O(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e3) {
            Log.e("SearchView", "Failed launch activity: " + intent, e3);
        }
    }

    public void P(int i3, String str, String str2) {
        getContext().startActivity(C("android.intent.action.SEARCH", null, null, str2, i3, str));
    }

    public final boolean Q(int i3, int i4, String str) {
        Cursor cursor = this.T.getCursor();
        if (cursor == null || !cursor.moveToPosition(i3)) {
            return false;
        }
        O(D(cursor, i4, str));
        return true;
    }

    public void R() {
        if (!TextUtils.isEmpty(this.f605r.getText())) {
            this.f605r.setText("");
            this.f605r.requestFocus();
            this.f605r.setImeVisibility(true);
        } else if (this.R) {
            k kVar = this.N;
            if (kVar == null || !kVar.a()) {
                clearFocus();
                k0(true);
            }
        }
    }

    public boolean S(int i3, int i4, String str) {
        m mVar = this.P;
        if (mVar != null && mVar.b(i3)) {
            return false;
        }
        Q(i3, 0, null);
        this.f605r.setImeVisibility(false);
        G();
        return true;
    }

    public boolean T(int i3) {
        m mVar = this.P;
        if (mVar != null && mVar.a(i3)) {
            return false;
        }
        c0(i3);
        return true;
    }

    public void U(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public void V() {
        k0(false);
        this.f605r.requestFocus();
        this.f605r.setImeVisibility(true);
        View.OnClickListener onClickListener = this.Q;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void W() {
        Editable text = this.f605r.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        l lVar = this.M;
        if (lVar == null || !lVar.b(text.toString())) {
            if (this.f594e0 != null) {
                P(0, null, text.toString());
            }
            this.f605r.setImeVisibility(false);
            G();
        }
    }

    public boolean X(View view, int i3, KeyEvent keyEvent) {
        if (this.f594e0 != null && this.T != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i3 == 66 || i3 == 84 || i3 == 61) {
                return S(this.f605r.getListSelection(), 0, null);
            }
            if (i3 == 21 || i3 == 22) {
                this.f605r.setSelection(i3 == 21 ? 0 : this.f605r.length());
                this.f605r.setListSelection(0);
                this.f605r.clearListSelection();
                this.f605r.a();
                return true;
            }
            if (i3 != 19 || this.f605r.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    public void Y(CharSequence charSequence) {
        Editable text = this.f605r.getText();
        this.f591b0 = text;
        boolean z3 = !TextUtils.isEmpty(text);
        j0(z3);
        l0(!z3);
        e0();
        i0();
        if (this.M != null && !TextUtils.equals(charSequence, this.f590a0)) {
            this.M.a(charSequence.toString());
        }
        this.f590a0 = charSequence.toString();
    }

    public void Z() {
        k0(L());
        b0();
        if (this.f605r.hasFocus()) {
            H();
        }
    }

    public void a0() {
        SearchableInfo searchableInfo = this.f594e0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(F(this.J, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(E(this.K, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public final void b0() {
        post(this.f596g0);
    }

    @Override // m.c
    public void c() {
        if (this.f592c0) {
            return;
        }
        this.f592c0 = true;
        int imeOptions = this.f605r.getImeOptions();
        this.f593d0 = imeOptions;
        this.f605r.setImeOptions(imeOptions | 33554432);
        this.f605r.setText("");
        setIconified(false);
    }

    public final void c0(int i3) {
        Editable text = this.f605r.getText();
        Cursor cursor = this.T.getCursor();
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i3)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceConvertToString = this.T.convertToString(cursor);
        if (charSequenceConvertToString != null) {
            setQuery(charSequenceConvertToString);
        } else {
            setQuery(text);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.X = true;
        super.clearFocus();
        this.f605r.clearFocus();
        this.f605r.setImeVisibility(false);
        this.X = false;
    }

    public void d0(CharSequence charSequence, boolean z3) {
        this.f605r.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f605r;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.f591b0 = charSequence;
        }
        if (!z3 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        W();
    }

    public final void e0() {
        boolean z3 = true;
        boolean z4 = !TextUtils.isEmpty(this.f605r.getText());
        if (!z4 && (!this.R || this.f592c0)) {
            z3 = false;
        }
        this.f611x.setVisibility(z3 ? 0 : 8);
        Drawable drawable = this.f611x.getDrawable();
        if (drawable != null) {
            drawable.setState(z4 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    @Override // m.c
    public void f() {
        d0("", false);
        clearFocus();
        k0(true);
        this.f605r.setImeOptions(this.f593d0);
        this.f592c0 = false;
    }

    public void f0() {
        int[] iArr = this.f605r.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f607t.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f608u.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void g0() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f605r;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(J(queryHint));
    }

    public int getImeOptions() {
        return this.f605r.getImeOptions();
    }

    public int getInputType() {
        return this.f605r.getInputType();
    }

    public int getMaxWidth() {
        return this.Y;
    }

    public CharSequence getQuery() {
        return this.f605r.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.V;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f594e0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.L : getContext().getText(this.f594e0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.I;
    }

    public int getSuggestionRowLayout() {
        return this.H;
    }

    public o0.a getSuggestionsAdapter() {
        return this.T;
    }

    public final void h0() {
        this.f605r.setThreshold(this.f594e0.getSuggestThreshold());
        this.f605r.setImeOptions(this.f594e0.getImeOptions());
        int inputType = this.f594e0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f594e0.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.f605r.setInputType(inputType);
        o0.a aVar = this.T;
        if (aVar != null) {
            aVar.changeCursor(null);
        }
        if (this.f594e0.getSuggestAuthority() != null) {
            y yVar = new y(getContext(), this, this.f594e0, this.f598i0);
            this.T = yVar;
            this.f605r.setAdapter(yVar);
            ((y) this.T).t(this.W ? 2 : 1);
        }
    }

    public final void i0() {
        this.f608u.setVisibility((N() && (this.f610w.getVisibility() == 0 || this.f612y.getVisibility() == 0)) ? 0 : 8);
    }

    public final void j0(boolean z3) {
        this.f610w.setVisibility((this.U && N() && hasFocus() && (z3 || !this.Z)) ? 0 : 8);
    }

    public final void k0(boolean z3) {
        this.S = z3;
        int i3 = z3 ? 0 : 8;
        boolean z4 = !TextUtils.isEmpty(this.f605r.getText());
        this.f609v.setVisibility(i3);
        j0(z4);
        this.f606s.setVisibility(z3 ? 8 : 0);
        this.F.setVisibility((this.F.getDrawable() == null || this.R) ? 8 : 0);
        e0();
        l0(!z4);
        i0();
    }

    public final void l0(boolean z3) {
        int i3 = 8;
        if (this.Z && !L() && z3) {
            this.f610w.setVisibility(8);
            i3 = 0;
        }
        this.f612y.setVisibility(i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f596g0);
        post(this.f597h0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        if (z3) {
            I(this.f605r, this.B);
            Rect rect = this.C;
            Rect rect2 = this.B;
            rect.set(rect2.left, 0, rect2.right, i6 - i4);
            o oVar = this.A;
            if (oVar != null) {
                oVar.a(this.C, this.B);
                return;
            }
            o oVar2 = new o(this.C, this.B, this.f605r);
            this.A = oVar2;
            setTouchDelegate(oVar2);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i3, int i4) {
        int i5;
        if (L()) {
            super.onMeasure(i3, i4);
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode == Integer.MIN_VALUE) {
            int i6 = this.Y;
            size = i6 > 0 ? Math.min(i6, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.Y;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i5 = this.Y) > 0) {
            size = Math.min(i5, size);
        }
        int mode2 = View.MeasureSpec.getMode(i4);
        int size2 = View.MeasureSpec.getSize(i4);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(size2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        k0(savedState.f614d);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f614d = L();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z3) {
        super.onWindowFocusChanged(z3);
        b0();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i3, Rect rect) {
        if (this.X || !isFocusable()) {
            return false;
        }
        if (L()) {
            return super.requestFocus(i3, rect);
        }
        boolean zRequestFocus = this.f605r.requestFocus(i3, rect);
        if (zRequestFocus) {
            k0(false);
        }
        return zRequestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.f595f0 = bundle;
    }

    public void setIconified(boolean z3) {
        if (z3) {
            R();
        } else {
            V();
        }
    }

    public void setIconifiedByDefault(boolean z3) {
        if (this.R == z3) {
            return;
        }
        this.R = z3;
        k0(z3);
        g0();
    }

    public void setImeOptions(int i3) {
        this.f605r.setImeOptions(i3);
    }

    public void setInputType(int i3) {
        this.f605r.setInputType(i3);
    }

    public void setMaxWidth(int i3) {
        this.Y = i3;
        requestLayout();
    }

    public void setOnCloseListener(k kVar) {
        this.N = kVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.O = onFocusChangeListener;
    }

    public void setOnQueryTextListener(l lVar) {
        this.M = lVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.Q = onClickListener;
    }

    public void setOnSuggestionListener(m mVar) {
        this.P = mVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.V = charSequence;
        g0();
    }

    public void setQueryRefinementEnabled(boolean z3) {
        this.W = z3;
        o0.a aVar = this.T;
        if (aVar instanceof y) {
            ((y) aVar).t(z3 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f594e0 = searchableInfo;
        if (searchableInfo != null) {
            h0();
            g0();
        }
        boolean zK = K();
        this.Z = zK;
        if (zK) {
            this.f605r.setPrivateImeOptions("nm");
        }
        k0(L());
    }

    public void setSubmitButtonEnabled(boolean z3) {
        this.U = z3;
        k0(L());
    }

    public void setSuggestionsAdapter(o0.a aVar) {
        this.T = aVar;
        this.f605r.setAdapter(aVar);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.B = new Rect();
        this.C = new Rect();
        this.D = new int[2];
        this.E = new int[2];
        this.f596g0 = new b();
        this.f597h0 = new c();
        this.f598i0 = new WeakHashMap<>();
        f fVar = new f();
        this.f599j0 = fVar;
        this.f600k0 = new g();
        h hVar = new h();
        this.f601l0 = hVar;
        i iVar = new i();
        this.f602m0 = iVar;
        j jVar = new j();
        this.f603n0 = jVar;
        this.f604o0 = new a();
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.SearchView, i3, 0);
        LayoutInflater.from(context).inflate(e0VarV.n(R$styleable.SearchView_layout, R$layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.f605r = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f606s = findViewById(R$id.search_edit_frame);
        View viewFindViewById = findViewById(R$id.search_plate);
        this.f607t = viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.submit_area);
        this.f608u = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.f609v = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.f610w = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.f611x = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.f612y = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_mag_icon);
        this.F = imageView5;
        t.m0(viewFindViewById, e0VarV.g(R$styleable.SearchView_queryBackground));
        t.m0(viewFindViewById2, e0VarV.g(R$styleable.SearchView_submitBackground));
        int i4 = R$styleable.SearchView_searchIcon;
        imageView.setImageDrawable(e0VarV.g(i4));
        imageView2.setImageDrawable(e0VarV.g(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(e0VarV.g(R$styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(e0VarV.g(R$styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(e0VarV.g(i4));
        this.G = e0VarV.g(R$styleable.SearchView_searchHintIcon);
        g0.a(imageView, getResources().getString(R$string.abc_searchview_description_search));
        this.H = e0VarV.n(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.I = e0VarV.n(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.f604o0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.f600k0);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(e0VarV.a(R$styleable.SearchView_iconifiedByDefault, true));
        int iF = e0VarV.f(R$styleable.SearchView_android_maxWidth, -1);
        if (iF != -1) {
            setMaxWidth(iF);
        }
        this.L = e0VarV.p(R$styleable.SearchView_defaultQueryHint);
        this.V = e0VarV.p(R$styleable.SearchView_queryHint);
        int iK = e0VarV.k(R$styleable.SearchView_android_imeOptions, -1);
        if (iK != -1) {
            setImeOptions(iK);
        }
        int iK2 = e0VarV.k(R$styleable.SearchView_android_inputType, -1);
        if (iK2 != -1) {
            setInputType(iK2);
        }
        setFocusable(e0VarV.a(R$styleable.SearchView_android_focusable, true));
        e0VarV.w();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.J = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.K = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.f613z = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new e());
        }
        k0(this.R);
        g0();
    }
}
