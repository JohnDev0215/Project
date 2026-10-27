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
import o0.t;
import r.e0;
import r.g0;
import r.k0;
import r.y;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements p.c {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final n f688s;
    public final ImageView A;
    public final View B;
    public o C;
    public Rect D;
    public Rect E;
    public int[] F;
    public int[] G;
    public final ImageView H;
    public final Drawable I;
    public final int J;
    public final int K;
    public final Intent L;
    public final Intent M;
    public final CharSequence N;
    public l O;
    public k P;
    public View.OnFocusChangeListener Q;
    public m R;
    public View.OnClickListener S;
    public boolean T;
    public boolean U;
    public s0.a V;
    public boolean W;
    public CharSequence X;
    public boolean Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f689a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f690b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public CharSequence f691c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public CharSequence f692d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f693e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f694f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public SearchableInfo f695g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public Bundle f696h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final Runnable f697i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public Runnable f698j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final WeakHashMap<String, Drawable.ConstantState> f699k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final View.OnClickListener f700l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public View.OnKeyListener f701m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final TextView.OnEditorActionListener f702n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final AdapterView.OnItemClickListener f703o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final AdapterView.OnItemSelectedListener f704p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public TextWatcher f705q0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final SearchAutoComplete f706t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final View f707u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final View f708v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final View f709w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ImageView f710x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ImageView f711y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final ImageView f712z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f713f;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f713f + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeValue(Boolean.valueOf(this.f713f));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f713f = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f714g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public SearchView f715h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f716i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final Runnable f717j;

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
            int i8 = configuration.screenWidthDp;
            int i9 = configuration.screenHeightDp;
            if (i8 >= 960 && i9 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i8 < 600) {
                return (i8 < 640 || i9 < 480) ? 160 : 192;
            }
            return 192;
        }

        public void a() {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.f688s.c(this);
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
            if (this.f716i) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f716i = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f714g <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f716i) {
                removeCallbacks(this.f717j);
                post(this.f717j);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z7, int i8, Rect rect) {
            super.onFocusChanged(z7, i8, rect);
            this.f715h.Z();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i8, KeyEvent keyEvent) {
            if (i8 == 4) {
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
                        this.f715h.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i8, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z7) {
            super.onWindowFocusChanged(z7);
            if (z7 && this.f715h.hasFocus() && getVisibility() == 0) {
                this.f716i = true;
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

        public void setImeVisibility(boolean z7) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z7) {
                this.f716i = false;
                removeCallbacks(this.f717j);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f716i = true;
                    return;
                }
                this.f716i = false;
                removeCallbacks(this.f717j);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f715h = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i8) {
            super.setThreshold(i8);
            this.f714g = i8;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i8) {
            super(context, attributeSet, i8);
            this.f717j = new a();
            this.f714g = getThreshold();
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
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
            s0.a aVar = SearchView.this.V;
            if (aVar instanceof y) {
                aVar.changeCursor(null);
            }
        }
    }

    public class d implements View.OnFocusChangeListener {
        public d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z7) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.Q;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z7);
            }
        }
    }

    public class e implements View.OnLayoutChangeListener {
        public e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            SearchView.this.B();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.f710x) {
                searchView.V();
                return;
            }
            if (view == searchView.f712z) {
                searchView.R();
                return;
            }
            if (view == searchView.f711y) {
                searchView.W();
            } else if (view == searchView.A) {
                searchView.a0();
            } else if (view == searchView.f706t) {
                searchView.H();
            }
        }
    }

    public class g implements View.OnKeyListener {
        public g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i8, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.f695g0 == null) {
                return false;
            }
            if (searchView.f706t.isPopupShowing() && SearchView.this.f706t.getListSelection() != -1) {
                return SearchView.this.X(view, i8, keyEvent);
            }
            if (SearchView.this.f706t.b() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i8 != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.P(0, null, searchView2.f706t.getText().toString());
            return true;
        }
    }

    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
            SearchView.this.W();
            return true;
        }
    }

    public class i implements AdapterView.OnItemClickListener {
        public i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j8) {
            SearchView.this.S(i8, 0, null);
        }
    }

    public class j implements AdapterView.OnItemSelectedListener {
        public j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long j8) {
            SearchView.this.T(i8);
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
        boolean a(int i8);

        boolean b(int i8);
    }

    public static class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Method f729a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Method f730b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f731c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        public n() {
            this.f729a = null;
            this.f730b = null;
            this.f731c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f729a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f730b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f731c = method;
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
            Method method = this.f730b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f729a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f731c;
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
        public final View f732a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f733b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Rect f734c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f735d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f736e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f737f;

        public o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f736e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f733b = new Rect();
            this.f735d = new Rect();
            this.f734c = new Rect();
            a(rect, rect2);
            this.f732a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f733b.set(rect);
            this.f735d.set(rect);
            Rect rect3 = this.f735d;
            int i8 = this.f736e;
            rect3.inset(-i8, -i8);
            this.f734c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z7;
            boolean z8;
            int x7 = (int) motionEvent.getX();
            int y7 = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z9 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z8 = this.f737f;
                    if (z8 && !this.f735d.contains(x7, y7)) {
                        z9 = z8;
                        z7 = false;
                    }
                } else {
                    if (action == 3) {
                        z8 = this.f737f;
                        this.f737f = false;
                    }
                    z7 = true;
                    z9 = false;
                }
                z9 = z8;
                z7 = true;
            } else if (this.f733b.contains(x7, y7)) {
                this.f737f = true;
                z7 = true;
            } else {
                z7 = true;
                z9 = false;
            }
            if (!z9) {
                return false;
            }
            if (!z7 || this.f734c.contains(x7, y7)) {
                Rect rect = this.f734c;
                motionEvent.setLocation(x7 - rect.left, y7 - rect.top);
            } else {
                motionEvent.setLocation(this.f732a.getWidth() / 2, this.f732a.getHeight() / 2);
            }
            return this.f732a.dispatchTouchEvent(motionEvent);
        }
    }

    static {
        f688s = Build.VERSION.SDK_INT < 29 ? new n() : null;
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
        this.f706t.setText(charSequence);
        this.f706t.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public void B() {
        if (this.B.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.f708v.getPaddingLeft();
            Rect rect = new Rect();
            boolean zB = k0.b(this);
            int dimensionPixelSize = this.T ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) : 0;
            this.f706t.getDropDownBackground().getPadding(rect);
            this.f706t.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.f706t.setDropDownWidth((((this.B.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public final Intent C(String str, Uri uri, String str2, String str3, int i8, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f692d0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f696h0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i8 != 0) {
            intent.putExtra("action_key", i8);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.f695g0.getSearchActivity());
        return intent;
    }

    public final Intent D(Cursor cursor, int i8, String str) {
        int position;
        String strK;
        try {
            String strK2 = y.k(cursor, "suggest_intent_action");
            if (strK2 == null) {
                strK2 = this.f695g0.getSuggestIntentAction();
            }
            if (strK2 == null) {
                strK2 = "android.intent.action.SEARCH";
            }
            String str2 = strK2;
            String strK3 = y.k(cursor, "suggest_intent_data");
            if (strK3 == null) {
                strK3 = this.f695g0.getSuggestIntentData();
            }
            if (strK3 != null && (strK = y.k(cursor, "suggest_intent_data_id")) != null) {
                strK3 = strK3 + "/" + Uri.encode(strK);
            }
            return C(str2, strK3 == null ? null : Uri.parse(strK3), y.k(cursor, "suggest_intent_extra_data"), y.k(cursor, "suggest_intent_query"), i8, str);
        } catch (RuntimeException e8) {
            try {
                position = cursor.getPosition();
            } catch (RuntimeException unused) {
                position = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e8);
            return null;
        }
    }

    public final Intent E(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f696h0;
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
        this.f706t.dismissDropDown();
    }

    public void H() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.f706t.refreshAutoCompleteResults();
            return;
        }
        n nVar = f688s;
        nVar.b(this.f706t);
        nVar.a(this.f706t);
    }

    public final void I(View view, Rect rect) {
        view.getLocationInWindow(this.F);
        getLocationInWindow(this.G);
        int[] iArr = this.F;
        int i8 = iArr[1];
        int[] iArr2 = this.G;
        int i9 = i8 - iArr2[1];
        int i10 = iArr[0] - iArr2[0];
        rect.set(i10, i9, view.getWidth() + i10, view.getHeight() + i9);
    }

    public final CharSequence J(CharSequence charSequence) {
        if (!this.T || this.I == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.f706t.getTextSize()) * 1.25d);
        this.I.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.I), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    public final boolean K() {
        SearchableInfo searchableInfo = this.f695g0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.f695g0.getVoiceSearchLaunchWebSearch()) {
            intent = this.L;
        } else if (this.f695g0.getVoiceSearchLaunchRecognizer()) {
            intent = this.M;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2) == null) ? false : true;
    }

    public boolean L() {
        return this.U;
    }

    public final boolean N() {
        return (this.W || this.f690b0) && !L();
    }

    public final void O(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e8) {
            Log.e("SearchView", "Failed launch activity: " + intent, e8);
        }
    }

    public void P(int i8, String str, String str2) {
        getContext().startActivity(C("android.intent.action.SEARCH", null, null, str2, i8, str));
    }

    public final boolean Q(int i8, int i9, String str) {
        Cursor cursor = this.V.getCursor();
        if (cursor == null || !cursor.moveToPosition(i8)) {
            return false;
        }
        O(D(cursor, i9, str));
        return true;
    }

    public void R() {
        if (!TextUtils.isEmpty(this.f706t.getText())) {
            this.f706t.setText("");
            this.f706t.requestFocus();
            this.f706t.setImeVisibility(true);
        } else if (this.T) {
            k kVar = this.P;
            if (kVar == null || !kVar.a()) {
                clearFocus();
                k0(true);
            }
        }
    }

    public boolean S(int i8, int i9, String str) {
        m mVar = this.R;
        if (mVar != null && mVar.b(i8)) {
            return false;
        }
        Q(i8, 0, null);
        this.f706t.setImeVisibility(false);
        G();
        return true;
    }

    public boolean T(int i8) {
        m mVar = this.R;
        if (mVar != null && mVar.a(i8)) {
            return false;
        }
        c0(i8);
        return true;
    }

    public void U(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public void V() {
        k0(false);
        this.f706t.requestFocus();
        this.f706t.setImeVisibility(true);
        View.OnClickListener onClickListener = this.S;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void W() {
        Editable text = this.f706t.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        l lVar = this.O;
        if (lVar == null || !lVar.b(text.toString())) {
            if (this.f695g0 != null) {
                P(0, null, text.toString());
            }
            this.f706t.setImeVisibility(false);
            G();
        }
    }

    public boolean X(View view, int i8, KeyEvent keyEvent) {
        if (this.f695g0 != null && this.V != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i8 == 66 || i8 == 84 || i8 == 61) {
                return S(this.f706t.getListSelection(), 0, null);
            }
            if (i8 == 21 || i8 == 22) {
                this.f706t.setSelection(i8 == 21 ? 0 : this.f706t.length());
                this.f706t.setListSelection(0);
                this.f706t.clearListSelection();
                this.f706t.a();
                return true;
            }
            if (i8 != 19 || this.f706t.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    public void Y(CharSequence charSequence) {
        Editable text = this.f706t.getText();
        this.f692d0 = text;
        boolean z7 = !TextUtils.isEmpty(text);
        j0(z7);
        l0(!z7);
        e0();
        i0();
        if (this.O != null && !TextUtils.equals(charSequence, this.f691c0)) {
            this.O.a(charSequence.toString());
        }
        this.f691c0 = charSequence.toString();
    }

    public void Z() {
        k0(L());
        b0();
        if (this.f706t.hasFocus()) {
            H();
        }
    }

    public void a0() {
        SearchableInfo searchableInfo = this.f695g0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(F(this.L, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(E(this.M, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public final void b0() {
        post(this.f697i0);
    }

    @Override // p.c
    public void c() {
        if (this.f693e0) {
            return;
        }
        this.f693e0 = true;
        int imeOptions = this.f706t.getImeOptions();
        this.f694f0 = imeOptions;
        this.f706t.setImeOptions(imeOptions | 33554432);
        this.f706t.setText("");
        setIconified(false);
    }

    public final void c0(int i8) {
        Editable text = this.f706t.getText();
        Cursor cursor = this.V.getCursor();
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i8)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceConvertToString = this.V.convertToString(cursor);
        if (charSequenceConvertToString != null) {
            setQuery(charSequenceConvertToString);
        } else {
            setQuery(text);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.Z = true;
        super.clearFocus();
        this.f706t.clearFocus();
        this.f706t.setImeVisibility(false);
        this.Z = false;
    }

    public void d0(CharSequence charSequence, boolean z7) {
        this.f706t.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f706t;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.f692d0 = charSequence;
        }
        if (!z7 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        W();
    }

    public final void e0() {
        boolean z7 = true;
        boolean z8 = !TextUtils.isEmpty(this.f706t.getText());
        if (!z8 && (!this.T || this.f693e0)) {
            z7 = false;
        }
        this.f712z.setVisibility(z7 ? 0 : 8);
        Drawable drawable = this.f712z.getDrawable();
        if (drawable != null) {
            drawable.setState(z8 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    @Override // p.c
    public void f() {
        d0("", false);
        clearFocus();
        k0(true);
        this.f706t.setImeOptions(this.f694f0);
        this.f693e0 = false;
    }

    public void f0() {
        int[] iArr = this.f706t.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f708v.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f709w.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void g0() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f706t;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(J(queryHint));
    }

    public int getImeOptions() {
        return this.f706t.getImeOptions();
    }

    public int getInputType() {
        return this.f706t.getInputType();
    }

    public int getMaxWidth() {
        return this.f689a0;
    }

    public CharSequence getQuery() {
        return this.f706t.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.X;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f695g0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.N : getContext().getText(this.f695g0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.K;
    }

    public int getSuggestionRowLayout() {
        return this.J;
    }

    public s0.a getSuggestionsAdapter() {
        return this.V;
    }

    public final void h0() {
        this.f706t.setThreshold(this.f695g0.getSuggestThreshold());
        this.f706t.setImeOptions(this.f695g0.getImeOptions());
        int inputType = this.f695g0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f695g0.getSuggestAuthority() != null) {
                inputType = inputType | CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2 | CarPlayConfig.IapFeature.IAP_FEATURE_WIRELESS_CARPLAY_UPDATE;
            }
        }
        this.f706t.setInputType(inputType);
        s0.a aVar = this.V;
        if (aVar != null) {
            aVar.changeCursor(null);
        }
        if (this.f695g0.getSuggestAuthority() != null) {
            y yVar = new y(getContext(), this, this.f695g0, this.f699k0);
            this.V = yVar;
            this.f706t.setAdapter(yVar);
            ((y) this.V).t(this.Y ? 2 : 1);
        }
    }

    public final void i0() {
        this.f709w.setVisibility((N() && (this.f711y.getVisibility() == 0 || this.A.getVisibility() == 0)) ? 0 : 8);
    }

    public final void j0(boolean z7) {
        this.f711y.setVisibility((this.W && N() && hasFocus() && (z7 || !this.f690b0)) ? 0 : 8);
    }

    public final void k0(boolean z7) {
        this.U = z7;
        int i8 = z7 ? 0 : 8;
        boolean z8 = !TextUtils.isEmpty(this.f706t.getText());
        this.f710x.setVisibility(i8);
        j0(z8);
        this.f707u.setVisibility(z7 ? 8 : 0);
        this.H.setVisibility((this.H.getDrawable() == null || this.T) ? 8 : 0);
        e0();
        l0(!z8);
        i0();
    }

    public final void l0(boolean z7) {
        int i8 = 8;
        if (this.f690b0 && !L() && z7) {
            this.f711y.setVisibility(8);
            i8 = 0;
        }
        this.A.setVisibility(i8);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f697i0);
        post(this.f698j0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        if (z7) {
            I(this.f706t, this.D);
            Rect rect = this.E;
            Rect rect2 = this.D;
            rect.set(rect2.left, 0, rect2.right, i11 - i9);
            o oVar = this.C;
            if (oVar != null) {
                oVar.a(this.E, this.D);
                return;
            }
            o oVar2 = new o(this.E, this.D, this.f706t);
            this.C = oVar2;
            setTouchDelegate(oVar2);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i8, int i9) {
        int i10;
        if (L()) {
            super.onMeasure(i8, i9);
            return;
        }
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        if (mode == Integer.MIN_VALUE) {
            int i11 = this.f689a0;
            size = i11 > 0 ? Math.min(i11, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.f689a0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i10 = this.f689a0) > 0) {
            size = Math.min(i10, size);
        }
        int mode2 = View.MeasureSpec.getMode(i9);
        int size2 = View.MeasureSpec.getSize(i9);
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
        k0(savedState.f713f);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f713f = L();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z7) {
        super.onWindowFocusChanged(z7);
        b0();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i8, Rect rect) {
        if (this.Z || !isFocusable()) {
            return false;
        }
        if (L()) {
            return super.requestFocus(i8, rect);
        }
        boolean zRequestFocus = this.f706t.requestFocus(i8, rect);
        if (zRequestFocus) {
            k0(false);
        }
        return zRequestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.f696h0 = bundle;
    }

    public void setIconified(boolean z7) {
        if (z7) {
            R();
        } else {
            V();
        }
    }

    public void setIconifiedByDefault(boolean z7) {
        if (this.T == z7) {
            return;
        }
        this.T = z7;
        k0(z7);
        g0();
    }

    public void setImeOptions(int i8) {
        this.f706t.setImeOptions(i8);
    }

    public void setInputType(int i8) {
        this.f706t.setInputType(i8);
    }

    public void setMaxWidth(int i8) {
        this.f689a0 = i8;
        requestLayout();
    }

    public void setOnCloseListener(k kVar) {
        this.P = kVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.Q = onFocusChangeListener;
    }

    public void setOnQueryTextListener(l lVar) {
        this.O = lVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.S = onClickListener;
    }

    public void setOnSuggestionListener(m mVar) {
        this.R = mVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.X = charSequence;
        g0();
    }

    public void setQueryRefinementEnabled(boolean z7) {
        this.Y = z7;
        s0.a aVar = this.V;
        if (aVar instanceof y) {
            ((y) aVar).t(z7 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f695g0 = searchableInfo;
        if (searchableInfo != null) {
            h0();
            g0();
        }
        boolean zK = K();
        this.f690b0 = zK;
        if (zK) {
            this.f706t.setPrivateImeOptions("nm");
        }
        k0(L());
    }

    public void setSubmitButtonEnabled(boolean z7) {
        this.W = z7;
        k0(L());
    }

    public void setSuggestionsAdapter(s0.a aVar) {
        this.V = aVar;
        this.f706t.setAdapter(aVar);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.D = new Rect();
        this.E = new Rect();
        this.F = new int[2];
        this.G = new int[2];
        this.f697i0 = new b();
        this.f698j0 = new c();
        this.f699k0 = new WeakHashMap<>();
        f fVar = new f();
        this.f700l0 = fVar;
        this.f701m0 = new g();
        h hVar = new h();
        this.f702n0 = hVar;
        i iVar = new i();
        this.f703o0 = iVar;
        j jVar = new j();
        this.f704p0 = jVar;
        this.f705q0 = new a();
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.SearchView, i8, 0);
        LayoutInflater.from(context).inflate(e0VarV.n(R$styleable.SearchView_layout, R$layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.f706t = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f707u = findViewById(R$id.search_edit_frame);
        View viewFindViewById = findViewById(R$id.search_plate);
        this.f708v = viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.submit_area);
        this.f709w = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.f710x = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.f711y = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.f712z = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.A = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_mag_icon);
        this.H = imageView5;
        t.o0(viewFindViewById, e0VarV.g(R$styleable.SearchView_queryBackground));
        t.o0(viewFindViewById2, e0VarV.g(R$styleable.SearchView_submitBackground));
        int i9 = R$styleable.SearchView_searchIcon;
        imageView.setImageDrawable(e0VarV.g(i9));
        imageView2.setImageDrawable(e0VarV.g(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(e0VarV.g(R$styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(e0VarV.g(R$styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(e0VarV.g(i9));
        this.I = e0VarV.g(R$styleable.SearchView_searchHintIcon);
        g0.a(imageView, getResources().getString(R$string.abc_searchview_description_search));
        this.J = e0VarV.n(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.K = e0VarV.n(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.f705q0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.f701m0);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(e0VarV.a(R$styleable.SearchView_iconifiedByDefault, true));
        int iF = e0VarV.f(R$styleable.SearchView_android_maxWidth, -1);
        if (iF != -1) {
            setMaxWidth(iF);
        }
        this.N = e0VarV.p(R$styleable.SearchView_defaultQueryHint);
        this.X = e0VarV.p(R$styleable.SearchView_queryHint);
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
        this.L = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.M = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.B = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new e());
        }
        k0(this.T);
        g0();
    }
}
