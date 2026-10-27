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
import b.b.q.e0;
import b.b.q.g0;
import b.b.q.k0;
import b.b.q.y;
import b.f.m.s;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements b.b.p.c {
    public static final n O3;
    public boolean A4;
    public int B4;
    public SearchableInfo C4;
    public Bundle D4;
    public final Runnable E4;
    public Runnable F4;
    public final WeakHashMap<String, Drawable.ConstantState> G4;
    public final View.OnClickListener H4;
    public View.OnKeyListener I4;
    public final TextView.OnEditorActionListener J4;
    public final AdapterView.OnItemClickListener K4;
    public final AdapterView.OnItemSelectedListener L4;
    public TextWatcher M4;
    public final SearchAutoComplete P3;
    public final View Q3;
    public final View R3;
    public final View S3;
    public final ImageView T3;
    public final ImageView U3;
    public final ImageView V3;
    public final ImageView W3;
    public final View X3;
    public o Y3;
    public Rect Z3;
    public Rect a4;
    public int[] b4;
    public int[] c4;
    public final ImageView d4;
    public final Drawable e4;
    public final int f4;
    public final int g4;
    public final Intent h4;
    public final Intent i4;
    public final CharSequence j4;
    public l k4;
    public k l4;
    public View.OnFocusChangeListener m4;
    public m n4;
    public View.OnClickListener o4;
    public boolean p4;
    public boolean q4;
    public b.g.a.a r4;
    public boolean s4;
    public CharSequence t4;
    public boolean u4;
    public boolean v4;
    public int w4;
    public boolean x4;
    public CharSequence y4;
    public CharSequence z4;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean s;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.s + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Boolean.valueOf(this.s));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.s = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        public boolean E3;
        public final Runnable F3;
        public int x;
        public SearchView y;

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
            int i2 = configuration.screenWidthDp;
            int i3 = configuration.screenHeightDp;
            if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i2 < 600) {
                return (i2 < 640 || i3 < 480) ? 160 : 192;
            }
            return 192;
        }

        public void a() {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.O3.c(this);
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
            if (this.E3) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.E3 = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.x <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.E3) {
                removeCallbacks(this.F3);
                post(this.F3);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i2, Rect rect) {
            super.onFocusChanged(z, i2, rect);
            this.y.X();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
            if (i2 == 4) {
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
                        this.y.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i2, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.y.hasFocus() && getVisibility() == 0) {
                this.E3 = true;
                if (SearchView.K(getContext())) {
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

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.E3 = false;
                removeCallbacks(this.F3);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.E3 = true;
                    return;
                }
                this.E3 = false;
                removeCallbacks(this.F3);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.y = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i2) {
            super.setThreshold(i2);
            this.x = i2;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, b.b.a.q);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.F3 = new a();
            this.x = getThreshold();
        }
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            SearchView.this.W(charSequence);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.d0();
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.g.a.a aVar = SearchView.this.r4;
            if (aVar instanceof y) {
                aVar.changeCursor(null);
            }
        }
    }

    public class d implements View.OnFocusChangeListener {
        public d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.m4;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    public class e implements View.OnLayoutChangeListener {
        public e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            SearchView.this.z();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.T3) {
                searchView.T();
                return;
            }
            if (view == searchView.V3) {
                searchView.P();
                return;
            }
            if (view == searchView.U3) {
                searchView.U();
            } else if (view == searchView.W3) {
                searchView.Y();
            } else if (view == searchView.P3) {
                searchView.F();
            }
        }
    }

    public class g implements View.OnKeyListener {
        public g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.C4 == null) {
                return false;
            }
            if (searchView.P3.isPopupShowing() && SearchView.this.P3.getListSelection() != -1) {
                return SearchView.this.V(view, i2, keyEvent);
            }
            if (SearchView.this.P3.b() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i2 != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.N(0, null, searchView2.P3.getText().toString());
            return true;
        }
    }

    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            SearchView.this.U();
            return true;
        }
    }

    public class i implements AdapterView.OnItemClickListener {
        public i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            SearchView.this.Q(i2, 0, null);
        }
    }

    public class j implements AdapterView.OnItemSelectedListener {
        public j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            SearchView.this.R(i2);
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
        boolean a(int i2);

        boolean b(int i2);
    }

    public static class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Method f263a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Method f264b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Method f265c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        public n() {
            this.f263a = null;
            this.f264b = null;
            this.f265c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f263a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f264b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f265c = method;
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
            Method method = this.f264b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f263a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        public void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f265c;
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
        public final View f266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f267b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Rect f268c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Rect f269d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f270e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f271f;

        public o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f270e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f267b = new Rect();
            this.f269d = new Rect();
            this.f268c = new Rect();
            a(rect, rect2);
            this.f266a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f267b.set(rect);
            this.f269d.set(rect);
            Rect rect3 = this.f269d;
            int i2 = this.f270e;
            rect3.inset(-i2, -i2);
            this.f268c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            boolean z2;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z3 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z2 = this.f271f;
                    if (z2 && !this.f269d.contains(x, y)) {
                        z3 = z2;
                        z = false;
                    }
                } else {
                    if (action == 3) {
                        z2 = this.f271f;
                        this.f271f = false;
                    }
                    z = true;
                    z3 = false;
                }
                z3 = z2;
                z = true;
            } else if (this.f267b.contains(x, y)) {
                this.f271f = true;
                z = true;
            } else {
                z = true;
                z3 = false;
            }
            if (!z3) {
                return false;
            }
            if (!z || this.f268c.contains(x, y)) {
                Rect rect = this.f268c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            } else {
                motionEvent.setLocation(this.f266a.getWidth() / 2, this.f266a.getHeight() / 2);
            }
            return this.f266a.dispatchTouchEvent(motionEvent);
        }
    }

    static {
        O3 = Build.VERSION.SDK_INT < 29 ? new n() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public static boolean K(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(b.b.d.f728g);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(b.b.d.f729h);
    }

    private void setQuery(CharSequence charSequence) {
        this.P3.setText(charSequence);
        this.P3.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public final Intent A(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.z4);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.D4;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.C4.getSearchActivity());
        return intent;
    }

    public final Intent B(Cursor cursor, int i2, String str) {
        int position;
        String strK;
        try {
            String strK2 = y.k(cursor, "suggest_intent_action");
            if (strK2 == null) {
                strK2 = this.C4.getSuggestIntentAction();
            }
            if (strK2 == null) {
                strK2 = "android.intent.action.SEARCH";
            }
            String str2 = strK2;
            String strK3 = y.k(cursor, "suggest_intent_data");
            if (strK3 == null) {
                strK3 = this.C4.getSuggestIntentData();
            }
            if (strK3 != null && (strK = y.k(cursor, "suggest_intent_data_id")) != null) {
                strK3 = strK3 + "/" + Uri.encode(strK);
            }
            return A(str2, strK3 == null ? null : Uri.parse(strK3), y.k(cursor, "suggest_intent_extra_data"), y.k(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e2) {
            try {
                position = cursor.getPosition();
            } catch (RuntimeException unused) {
                position = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e2);
            return null;
        }
    }

    public final Intent C(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.D4;
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

    public final Intent D(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    public final void E() {
        this.P3.dismissDropDown();
    }

    public void F() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.P3.refreshAutoCompleteResults();
            return;
        }
        n nVar = O3;
        nVar.b(this.P3);
        nVar.a(this.P3);
    }

    public final void G(View view, Rect rect) {
        view.getLocationInWindow(this.b4);
        getLocationInWindow(this.c4);
        int[] iArr = this.b4;
        int i2 = iArr[1];
        int[] iArr2 = this.c4;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    public final CharSequence H(CharSequence charSequence) {
        if (!this.p4 || this.e4 == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.P3.getTextSize()) * 1.25d);
        this.e4.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.e4), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    public final boolean I() {
        SearchableInfo searchableInfo = this.C4;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.C4.getVoiceSearchLaunchWebSearch()) {
            intent = this.h4;
        } else if (this.C4.getVoiceSearchLaunchRecognizer()) {
            intent = this.i4;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    public boolean J() {
        return this.q4;
    }

    public final boolean L() {
        return (this.s4 || this.x4) && !J();
    }

    public final void M(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    public void N(int i2, String str, String str2) {
        getContext().startActivity(A("android.intent.action.SEARCH", null, null, str2, i2, str));
    }

    public final boolean O(int i2, int i3, String str) {
        Cursor cursor = this.r4.getCursor();
        if (cursor == null || !cursor.moveToPosition(i2)) {
            return false;
        }
        M(B(cursor, i3, str));
        return true;
    }

    public void P() {
        if (!TextUtils.isEmpty(this.P3.getText())) {
            this.P3.setText("");
            this.P3.requestFocus();
            this.P3.setImeVisibility(true);
        } else if (this.p4) {
            k kVar = this.l4;
            if (kVar == null || !kVar.a()) {
                clearFocus();
                i0(true);
            }
        }
    }

    public boolean Q(int i2, int i3, String str) {
        m mVar = this.n4;
        if (mVar != null && mVar.b(i2)) {
            return false;
        }
        O(i2, 0, null);
        this.P3.setImeVisibility(false);
        E();
        return true;
    }

    public boolean R(int i2) {
        m mVar = this.n4;
        if (mVar != null && mVar.a(i2)) {
            return false;
        }
        a0(i2);
        return true;
    }

    public void S(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public void T() {
        i0(false);
        this.P3.requestFocus();
        this.P3.setImeVisibility(true);
        View.OnClickListener onClickListener = this.o4;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void U() {
        Editable text = this.P3.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        l lVar = this.k4;
        if (lVar == null || !lVar.b(text.toString())) {
            if (this.C4 != null) {
                N(0, null, text.toString());
            }
            this.P3.setImeVisibility(false);
            E();
        }
    }

    public boolean V(View view, int i2, KeyEvent keyEvent) {
        if (this.C4 != null && this.r4 != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return Q(this.P3.getListSelection(), 0, null);
            }
            if (i2 == 21 || i2 == 22) {
                this.P3.setSelection(i2 == 21 ? 0 : this.P3.length());
                this.P3.setListSelection(0);
                this.P3.clearListSelection();
                this.P3.a();
                return true;
            }
            if (i2 != 19 || this.P3.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    public void W(CharSequence charSequence) {
        Editable text = this.P3.getText();
        this.z4 = text;
        boolean z = !TextUtils.isEmpty(text);
        h0(z);
        j0(!z);
        c0();
        g0();
        if (this.k4 != null && !TextUtils.equals(charSequence, this.y4)) {
            this.k4.a(charSequence.toString());
        }
        this.y4 = charSequence.toString();
    }

    public void X() {
        i0(J());
        Z();
        if (this.P3.hasFocus()) {
            F();
        }
    }

    public void Y() {
        SearchableInfo searchableInfo = this.C4;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(D(this.h4, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(C(this.i4, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public final void Z() {
        post(this.E4);
    }

    public final void a0(int i2) {
        Editable text = this.P3.getText();
        Cursor cursor = this.r4.getCursor();
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i2)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceConvertToString = this.r4.convertToString(cursor);
        if (charSequenceConvertToString != null) {
            setQuery(charSequenceConvertToString);
        } else {
            setQuery(text);
        }
    }

    public void b0(CharSequence charSequence, boolean z) {
        this.P3.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.P3;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.z4 = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        U();
    }

    @Override // b.b.p.c
    public void c() {
        if (this.A4) {
            return;
        }
        this.A4 = true;
        int imeOptions = this.P3.getImeOptions();
        this.B4 = imeOptions;
        this.P3.setImeOptions(imeOptions | 33554432);
        this.P3.setText("");
        setIconified(false);
    }

    public final void c0() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.P3.getText());
        if (!z2 && (!this.p4 || this.A4)) {
            z = false;
        }
        this.V3.setVisibility(z ? 0 : 8);
        Drawable drawable = this.V3.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.v4 = true;
        super.clearFocus();
        this.P3.clearFocus();
        this.P3.setImeVisibility(false);
        this.v4 = false;
    }

    @Override // b.b.p.c
    public void d() {
        b0("", false);
        clearFocus();
        i0(true);
        this.P3.setImeOptions(this.B4);
        this.A4 = false;
    }

    public void d0() {
        int[] iArr = this.P3.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.R3.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.S3.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public final void e0() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.P3;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(H(queryHint));
    }

    public final void f0() {
        this.P3.setThreshold(this.C4.getSuggestThreshold());
        this.P3.setImeOptions(this.C4.getImeOptions());
        int inputType = this.C4.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.C4.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.P3.setInputType(inputType);
        b.g.a.a aVar = this.r4;
        if (aVar != null) {
            aVar.changeCursor(null);
        }
        if (this.C4.getSuggestAuthority() != null) {
            y yVar = new y(getContext(), this, this.C4, this.G4);
            this.r4 = yVar;
            this.P3.setAdapter(yVar);
            ((y) this.r4).t(this.u4 ? 2 : 1);
        }
    }

    public final void g0() {
        this.S3.setVisibility((L() && (this.U3.getVisibility() == 0 || this.W3.getVisibility() == 0)) ? 0 : 8);
    }

    public int getImeOptions() {
        return this.P3.getImeOptions();
    }

    public int getInputType() {
        return this.P3.getInputType();
    }

    public int getMaxWidth() {
        return this.w4;
    }

    public CharSequence getQuery() {
        return this.P3.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.t4;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.C4;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.j4 : getContext().getText(this.C4.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.g4;
    }

    public int getSuggestionRowLayout() {
        return this.f4;
    }

    public b.g.a.a getSuggestionsAdapter() {
        return this.r4;
    }

    public final void h0(boolean z) {
        this.U3.setVisibility((this.s4 && L() && hasFocus() && (z || !this.x4)) ? 0 : 8);
    }

    public final void i0(boolean z) {
        this.q4 = z;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.P3.getText());
        this.T3.setVisibility(i2);
        h0(z2);
        this.Q3.setVisibility(z ? 8 : 0);
        this.d4.setVisibility((this.d4.getDrawable() == null || this.p4) ? 8 : 0);
        c0();
        j0(!z2);
        g0();
    }

    public final void j0(boolean z) {
        int i2 = 8;
        if (this.x4 && !J() && z) {
            this.U3.setVisibility(8);
            i2 = 0;
        }
        this.W3.setVisibility(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.E4);
        post(this.F4);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            G(this.P3, this.Z3);
            Rect rect = this.a4;
            Rect rect2 = this.Z3;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            o oVar = this.Y3;
            if (oVar != null) {
                oVar.a(this.a4, this.Z3);
                return;
            }
            o oVar2 = new o(this.a4, this.Z3, this.P3);
            this.Y3 = oVar2;
            setTouchDelegate(oVar2);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        if (J()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.w4;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.w4;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.w4) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
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
        super.onRestoreInstanceState(savedState.a());
        i0(savedState.s);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.s = J();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Z();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.v4 || !isFocusable()) {
            return false;
        }
        if (J()) {
            return super.requestFocus(i2, rect);
        }
        boolean zRequestFocus = this.P3.requestFocus(i2, rect);
        if (zRequestFocus) {
            i0(false);
        }
        return zRequestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.D4 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            P();
        } else {
            T();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.p4 == z) {
            return;
        }
        this.p4 = z;
        i0(z);
        e0();
    }

    public void setImeOptions(int i2) {
        this.P3.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.P3.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.w4 = i2;
        requestLayout();
    }

    public void setOnCloseListener(k kVar) {
        this.l4 = kVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.m4 = onFocusChangeListener;
    }

    public void setOnQueryTextListener(l lVar) {
        this.k4 = lVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.o4 = onClickListener;
    }

    public void setOnSuggestionListener(m mVar) {
        this.n4 = mVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.t4 = charSequence;
        e0();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.u4 = z;
        b.g.a.a aVar = this.r4;
        if (aVar instanceof y) {
            ((y) aVar).t(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.C4 = searchableInfo;
        if (searchableInfo != null) {
            f0();
            e0();
        }
        boolean zI = I();
        this.x4 = zI;
        if (zI) {
            this.P3.setPrivateImeOptions("nm");
        }
        i0(J());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.s4 = z;
        i0(J());
    }

    public void setSuggestionsAdapter(b.g.a.a aVar) {
        this.r4 = aVar;
        this.P3.setAdapter(aVar);
    }

    public void z() {
        if (this.X3.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.R3.getPaddingLeft();
            Rect rect = new Rect();
            boolean zB = k0.b(this);
            int dimensionPixelSize = this.p4 ? resources.getDimensionPixelSize(b.b.d.f726e) + resources.getDimensionPixelSize(b.b.d.f727f) : 0;
            this.P3.getDropDownBackground().getPadding(rect);
            this.P3.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.P3.setDropDownWidth((((this.X3.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.I);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.Z3 = new Rect();
        this.a4 = new Rect();
        this.b4 = new int[2];
        this.c4 = new int[2];
        this.E4 = new b();
        this.F4 = new c();
        this.G4 = new WeakHashMap<>();
        f fVar = new f();
        this.H4 = fVar;
        this.I4 = new g();
        h hVar = new h();
        this.J4 = hVar;
        i iVar = new i();
        this.K4 = iVar;
        j jVar = new j();
        this.L4 = jVar;
        this.M4 = new a();
        e0 e0VarU = e0.u(context, attributeSet, b.b.j.k2, i2, 0);
        LayoutInflater.from(context).inflate(e0VarU.m(b.b.j.u2, b.b.g.t), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(b.b.f.J);
        this.P3 = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.Q3 = findViewById(b.b.f.F);
        View viewFindViewById = findViewById(b.b.f.I);
        this.R3 = viewFindViewById;
        View viewFindViewById2 = findViewById(b.b.f.P);
        this.S3 = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(b.b.f.D);
        this.T3 = imageView;
        ImageView imageView2 = (ImageView) findViewById(b.b.f.G);
        this.U3 = imageView2;
        ImageView imageView3 = (ImageView) findViewById(b.b.f.E);
        this.V3 = imageView3;
        ImageView imageView4 = (ImageView) findViewById(b.b.f.K);
        this.W3 = imageView4;
        ImageView imageView5 = (ImageView) findViewById(b.b.f.H);
        this.d4 = imageView5;
        s.U(viewFindViewById, e0VarU.f(b.b.j.v2));
        s.U(viewFindViewById2, e0VarU.f(b.b.j.z2));
        int i3 = b.b.j.y2;
        imageView.setImageDrawable(e0VarU.f(i3));
        imageView2.setImageDrawable(e0VarU.f(b.b.j.s2));
        imageView3.setImageDrawable(e0VarU.f(b.b.j.p2));
        imageView4.setImageDrawable(e0VarU.f(b.b.j.B2));
        imageView5.setImageDrawable(e0VarU.f(i3));
        this.e4 = e0VarU.f(b.b.j.x2);
        g0.a(imageView, getResources().getString(b.b.h.n));
        this.f4 = e0VarU.m(b.b.j.A2, b.b.g.s);
        this.g4 = e0VarU.m(b.b.j.q2, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.M4);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.I4);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(e0VarU.a(b.b.j.t2, true));
        int iE = e0VarU.e(b.b.j.m2, -1);
        if (iE != -1) {
            setMaxWidth(iE);
        }
        this.j4 = e0VarU.o(b.b.j.r2);
        this.t4 = e0VarU.o(b.b.j.w2);
        int iJ = e0VarU.j(b.b.j.o2, -1);
        if (iJ != -1) {
            setImeOptions(iJ);
        }
        int iJ2 = e0VarU.j(b.b.j.n2, -1);
        if (iJ2 != -1) {
            setInputType(iJ2);
        }
        setFocusable(e0VarU.a(b.b.j.l2, true));
        e0VarU.v();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.h4 = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.i4 = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.X3 = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new e());
        }
        i0(this.p4);
        e0();
    }
}
