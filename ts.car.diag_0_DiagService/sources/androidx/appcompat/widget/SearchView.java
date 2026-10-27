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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.byd.mediacenter.api.media.MediaControl;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements androidx.appcompat.c.c {
    static final n o0;
    private o A;
    private Rect B;
    private Rect C;
    private int[] D;
    private int[] E;
    private final ImageView F;
    private final Drawable G;
    private final Intent H;
    private final Intent I;
    private final CharSequence J;
    private l K;
    private k L;
    View.OnFocusChangeListener M;
    private m N;
    private View.OnClickListener O;
    private boolean P;
    private boolean Q;
    androidx.cursoradapter.a.a R;
    private boolean S;
    private CharSequence T;
    private boolean U;
    private int V;
    private boolean W;
    private CharSequence a0;
    private CharSequence b0;
    private boolean c0;
    private int d0;
    SearchableInfo e0;
    private Bundle f0;
    private final Runnable g0;
    private Runnable h0;
    private final View.OnClickListener i0;
    View.OnKeyListener j0;
    private final TextView.OnEditorActionListener k0;
    private final AdapterView.OnItemClickListener l0;
    private final AdapterView.OnItemSelectedListener m0;
    private TextWatcher n0;
    final SearchAutoComplete r;
    private final View s;
    private final View t;
    private final View u;
    final ImageView v;
    final ImageView w;
    final ImageView x;
    final ImageView y;
    private final View z;

    /* JADX INFO: compiled from: caradapter-bug */
    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        boolean e;

        /* JADX INFO: compiled from: caradapter-bug */
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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.e + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.e));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.e = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static class SearchAutoComplete extends C0057c {
        private int f;
        private SearchView g;
        private boolean h;
        final Runnable i;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.j();
            }
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int b() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return MediaControl.ACTION_CUSTOM;
            }
            if (i < 600) {
                return (i < 640 || i2 < 480) ? 160 : 192;
            }
            return 192;
        }

        void a() {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.o0.c(this);
                return;
            }
            setInputMethodMode(1);
            if (enoughToFilter()) {
                showDropDown();
            }
        }

        boolean d() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void e(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.h = false;
                removeCallbacks(this.i);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.h = true;
                    return;
                }
                this.h = false;
                removeCallbacks(this.i);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f <= 0 || super.enoughToFilter();
        }

        void g(SearchView searchView) {
            this.g = searchView;
        }

        void j() {
            if (this.h) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.h = false;
            }
        }

        @Override // androidx.appcompat.widget.C0057c, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.h) {
                removeCallbacks(this.i);
                post(this.i);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, b(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.g.h0();
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
                        this.g.clearFocus();
                        e(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.g.hasFocus() && getVisibility() == 0) {
                this.h = true;
                if (SearchView.U(getContext())) {
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

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.i = new a();
            this.f = getThreshold();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView.this.g0(charSequence);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.t0();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.cursoradapter.a.a aVar = SearchView.this.R;
            if (aVar instanceof N) {
                aVar.changeCursor(null);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements View.OnFocusChangeListener {
        d() {
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

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements View.OnLayoutChangeListener {
        e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView.this.H();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.v) {
                searchView.d0();
                return;
            }
            if (view == searchView.x) {
                searchView.Z();
                return;
            }
            if (view == searchView.w) {
                searchView.e0();
            } else if (view == searchView.y) {
                searchView.i0();
            } else if (view == searchView.r) {
                searchView.N();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g implements View.OnKeyListener {
        g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.e0 == null) {
                return false;
            }
            if (searchView.r.isPopupShowing() && SearchView.this.r.getListSelection() != -1) {
                return SearchView.this.f0(view, i, keyEvent);
            }
            if (SearchView.this.r.d() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.X(0, null, searchView2.r.getText().toString());
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class h implements TextView.OnEditorActionListener {
        h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.e0();
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.a0(i, 0, null);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class j implements AdapterView.OnItemSelectedListener {
        j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.b0(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface k {
        boolean a();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface l {
        boolean a(String str);

        boolean b(String str);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface m {
        boolean a(int i);

        boolean b(int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Method f315a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Method f316b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f317c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        n() {
            this.f315a = null;
            this.f316b = null;
            this.f317c = null;
            d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f315a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f316b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f317c = method;
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
            Method method = this.f316b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f315a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void c(AutoCompleteTextView autoCompleteTextView) {
            d();
            Method method = this.f317c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class o extends TouchDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f318a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Rect f319b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Rect f320c;
        private final Rect d;
        private final int e;
        private boolean f;

        public o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f319b = new Rect();
            this.d = new Rect();
            this.f320c = new Rect();
            a(rect, rect2);
            this.f318a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f319b.set(rect);
            this.d.set(rect);
            Rect rect3 = this.d;
            int i = this.e;
            rect3.inset(-i, -i);
            this.f320c.set(rect2);
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
                    z2 = this.f;
                    if (z2 && !this.d.contains(x, y)) {
                        z3 = z2;
                        z = false;
                    }
                } else {
                    if (action == 3) {
                        z2 = this.f;
                        this.f = false;
                    }
                    z = true;
                    z3 = false;
                }
                z3 = z2;
                z = true;
            } else if (this.f319b.contains(x, y)) {
                this.f = true;
                z = true;
            } else {
                z = true;
                z3 = false;
            }
            if (!z3) {
                return false;
            }
            if (!z || this.f320c.contains(x, y)) {
                Rect rect = this.f320c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            } else {
                motionEvent.setLocation(this.f318a.getWidth() / 2, this.f318a.getHeight() / 2);
            }
            return this.f318a.dispatchTouchEvent(motionEvent);
        }
    }

    static {
        o0 = Build.VERSION.SDK_INT < 29 ? new n() : null;
    }

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    private Intent I(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.b0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.e0.getSearchActivity());
        return intent;
    }

    private Intent J(Cursor cursor, int i2, String str) {
        int position;
        String strI;
        try {
            String strI2 = N.i(cursor, "suggest_intent_action");
            if (strI2 == null) {
                strI2 = this.e0.getSuggestIntentAction();
            }
            if (strI2 == null) {
                strI2 = "android.intent.action.SEARCH";
            }
            String str2 = strI2;
            String strI3 = N.i(cursor, "suggest_intent_data");
            if (strI3 == null) {
                strI3 = this.e0.getSuggestIntentData();
            }
            if (strI3 != null && (strI = N.i(cursor, "suggest_intent_data_id")) != null) {
                strI3 = strI3 + "/" + Uri.encode(strI);
            }
            return I(str2, strI3 == null ? null : Uri.parse(strI3), N.i(cursor, "suggest_intent_extra_data"), N.i(cursor, "suggest_intent_query"), i2, str);
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

    private Intent K(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f0;
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

    private Intent L(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private void M() {
        this.r.dismissDropDown();
    }

    private void O(View view, Rect rect) {
        view.getLocationInWindow(this.D);
        getLocationInWindow(this.E);
        int[] iArr = this.D;
        int i2 = iArr[1];
        int[] iArr2 = this.E;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    private CharSequence P(CharSequence charSequence) {
        if (!this.P || this.G == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.r.getTextSize()) * 1.25d);
        this.G.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.G), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private int Q() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int R() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    static boolean U(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private boolean V() {
        return (this.S || this.W) && !T();
    }

    private void W(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    private boolean Y(int i2, int i3, String str) {
        Cursor cursor = this.R.getCursor();
        if (cursor == null || !cursor.moveToPosition(i2)) {
            return false;
        }
        W(J(cursor, i3, str));
        return true;
    }

    private void j0() {
        post(this.g0);
    }

    private void k0(int i2) {
        Editable text = this.r.getText();
        Cursor cursor = this.R.getCursor();
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i2)) {
            q0(text);
            return;
        }
        CharSequence charSequenceConvertToString = this.R.convertToString(cursor);
        if (charSequenceConvertToString != null) {
            q0(charSequenceConvertToString);
        } else {
            q0(text);
        }
    }

    private void q0(CharSequence charSequence) {
        this.r.setText(charSequence);
        this.r.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void s0() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.r.getText());
        if (!z2 && (!this.P || this.c0)) {
            z = false;
        }
        this.x.setVisibility(z ? 0 : 8);
        Drawable drawable = this.x.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void u0() {
        CharSequence charSequenceS = S();
        SearchAutoComplete searchAutoComplete = this.r;
        if (charSequenceS == null) {
            charSequenceS = "";
        }
        searchAutoComplete.setHint(P(charSequenceS));
    }

    private void v0() {
        this.u.setVisibility((V() && (this.w.getVisibility() == 0 || this.y.getVisibility() == 0)) ? 0 : 8);
    }

    private void w0(boolean z) {
        this.w.setVisibility((this.S && V() && hasFocus() && (z || !this.W)) ? 0 : 8);
    }

    private void x0(boolean z) {
        this.Q = z;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.r.getText());
        this.v.setVisibility(i2);
        w0(z2);
        this.s.setVisibility(z ? 8 : 0);
        this.F.setVisibility((this.F.getDrawable() == null || this.P) ? 8 : 0);
        s0();
        y0(!z2);
        v0();
    }

    private void y0(boolean z) {
        int i2 = 8;
        if (this.W && !T() && z) {
            this.w.setVisibility(8);
            i2 = 0;
        }
        this.y.setVisibility(i2);
    }

    void H() {
        if (this.z.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.t.getPaddingLeft();
            Rect rect = new Rect();
            boolean zB = a0.b(this);
            int dimensionPixelSize = this.P ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) : 0;
            this.r.getDropDownBackground().getPadding(rect);
            this.r.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.r.setDropDownWidth((((this.z.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    void N() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.r.refreshAutoCompleteResults();
        } else {
            o0.b(this.r);
            o0.a(this.r);
        }
    }

    @Nullable
    public CharSequence S() {
        CharSequence charSequence = this.T;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.e0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.J : getContext().getText(this.e0.getHintId());
    }

    public boolean T() {
        return this.Q;
    }

    void X(int i2, String str, String str2) {
        getContext().startActivity(I("android.intent.action.SEARCH", null, null, str2, i2, str));
    }

    void Z() {
        if (!TextUtils.isEmpty(this.r.getText())) {
            this.r.setText("");
            this.r.requestFocus();
            this.r.e(true);
        } else if (this.P) {
            k kVar = this.L;
            if (kVar == null || !kVar.a()) {
                clearFocus();
                x0(true);
            }
        }
    }

    boolean a0(int i2, int i3, String str) {
        m mVar = this.N;
        if (mVar != null && mVar.b(i2)) {
            return false;
        }
        Y(i2, 0, null);
        this.r.e(false);
        M();
        return true;
    }

    boolean b0(int i2) {
        m mVar = this.N;
        if (mVar != null && mVar.a(i2)) {
            return false;
        }
        k0(i2);
        return true;
    }

    @Override // androidx.appcompat.c.c
    public void c() {
        if (this.c0) {
            return;
        }
        this.c0 = true;
        int imeOptions = this.r.getImeOptions();
        this.d0 = imeOptions;
        this.r.setImeOptions(imeOptions | 33554432);
        this.r.setText("");
        l0(false);
    }

    void c0(CharSequence charSequence) {
        q0(charSequence);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.U = true;
        super.clearFocus();
        this.r.clearFocus();
        this.r.e(false);
        this.U = false;
    }

    @Override // androidx.appcompat.c.c
    public void d() {
        r0("", false);
        clearFocus();
        x0(true);
        this.r.setImeOptions(this.d0);
        this.c0 = false;
    }

    void d0() {
        x0(false);
        this.r.requestFocus();
        this.r.e(true);
        View.OnClickListener onClickListener = this.O;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    void e0() {
        Editable text = this.r.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        l lVar = this.K;
        if (lVar == null || !lVar.b(text.toString())) {
            if (this.e0 != null) {
                X(0, null, text.toString());
            }
            this.r.e(false);
            M();
        }
    }

    boolean f0(View view, int i2, KeyEvent keyEvent) {
        if (this.e0 != null && this.R != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return a0(this.r.getListSelection(), 0, null);
            }
            if (i2 == 21 || i2 == 22) {
                this.r.setSelection(i2 == 21 ? 0 : this.r.length());
                this.r.setListSelection(0);
                this.r.clearListSelection();
                this.r.a();
                return true;
            }
            if (i2 != 19 || this.r.getListSelection() == 0) {
                return false;
            }
        }
        return false;
    }

    void g0(CharSequence charSequence) {
        Editable text = this.r.getText();
        this.b0 = text;
        boolean z = !TextUtils.isEmpty(text);
        w0(z);
        y0(!z);
        s0();
        v0();
        if (this.K != null && !TextUtils.equals(charSequence, this.a0)) {
            this.K.a(charSequence.toString());
        }
        this.a0 = charSequence.toString();
    }

    void h0() {
        x0(T());
        j0();
        if (this.r.hasFocus()) {
            N();
        }
    }

    void i0() {
        SearchableInfo searchableInfo = this.e0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(L(this.H, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(K(this.I, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    public void l0(boolean z) {
        if (z) {
            Z();
        } else {
            d0();
        }
    }

    public void m0(boolean z) {
        if (this.P == z) {
            return;
        }
        this.P = z;
        x0(z);
        u0();
    }

    public void n0(int i2) {
        this.r.setImeOptions(i2);
    }

    public void o0(int i2) {
        this.r.setInputType(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.g0);
        post(this.h0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            O(this.r, this.B);
            Rect rect = this.C;
            Rect rect2 = this.B;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            o oVar = this.A;
            if (oVar != null) {
                oVar.a(this.C, this.B);
                return;
            }
            o oVar2 = new o(this.C, this.B, this.r);
            this.A = oVar2;
            setTouchDelegate(oVar2);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        if (T()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.V;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(R(), size);
        } else if (mode == 0) {
            size = this.V;
            if (size <= 0) {
                size = R();
            }
        } else if (mode == 1073741824 && (i4 = this.V) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(Q(), size2);
        } else if (mode2 == 0) {
            size2 = Q();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        x0(savedState.e);
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.e = T();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        j0();
    }

    public void p0(int i2) {
        this.V = i2;
        requestLayout();
    }

    public void r0(CharSequence charSequence, boolean z) {
        this.r.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.r;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.b0 = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        e0();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.U || !isFocusable()) {
            return false;
        }
        if (T()) {
            return super.requestFocus(i2, rect);
        }
        boolean zRequestFocus = this.r.requestFocus(i2, rect);
        if (zRequestFocus) {
            x0(false);
        }
        return zRequestFocus;
    }

    void t0() {
        int[] iArr = this.r.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.t.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.u.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.B = new Rect();
        this.C = new Rect();
        this.D = new int[2];
        this.E = new int[2];
        this.g0 = new b();
        this.h0 = new c();
        new WeakHashMap();
        this.i0 = new f();
        this.j0 = new g();
        this.k0 = new h();
        this.l0 = new i();
        this.m0 = new j();
        this.n0 = new a();
        U u = U.u(context, attributeSet, R$styleable.SearchView, i2, 0);
        LayoutInflater.from(context).inflate(u.m(R$styleable.SearchView_layout, R$layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.r = searchAutoComplete;
        searchAutoComplete.g(this);
        this.s = findViewById(R$id.search_edit_frame);
        this.t = findViewById(R$id.search_plate);
        this.u = findViewById(R$id.submit_area);
        this.v = (ImageView) findViewById(R$id.search_button);
        this.w = (ImageView) findViewById(R$id.search_go_btn);
        this.x = (ImageView) findViewById(R$id.search_close_btn);
        this.y = (ImageView) findViewById(R$id.search_voice_btn);
        this.F = (ImageView) findViewById(R$id.search_mag_icon);
        ViewCompat.N(this.t, u.f(R$styleable.SearchView_queryBackground));
        ViewCompat.N(this.u, u.f(R$styleable.SearchView_submitBackground));
        this.v.setImageDrawable(u.f(R$styleable.SearchView_searchIcon));
        this.w.setImageDrawable(u.f(R$styleable.SearchView_goIcon));
        this.x.setImageDrawable(u.f(R$styleable.SearchView_closeIcon));
        this.y.setImageDrawable(u.f(R$styleable.SearchView_voiceIcon));
        this.F.setImageDrawable(u.f(R$styleable.SearchView_searchIcon));
        this.G = u.f(R$styleable.SearchView_searchHintIcon);
        W.a(this.v, getResources().getString(R$string.abc_searchview_description_search));
        u.m(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        u.m(R$styleable.SearchView_commitIcon, 0);
        this.v.setOnClickListener(this.i0);
        this.x.setOnClickListener(this.i0);
        this.w.setOnClickListener(this.i0);
        this.y.setOnClickListener(this.i0);
        this.r.setOnClickListener(this.i0);
        this.r.addTextChangedListener(this.n0);
        this.r.setOnEditorActionListener(this.k0);
        this.r.setOnItemClickListener(this.l0);
        this.r.setOnItemSelectedListener(this.m0);
        this.r.setOnKeyListener(this.j0);
        this.r.setOnFocusChangeListener(new d());
        m0(u.a(R$styleable.SearchView_iconifiedByDefault, true));
        int iE = u.e(R$styleable.SearchView_android_maxWidth, -1);
        if (iE != -1) {
            p0(iE);
        }
        this.J = u.o(R$styleable.SearchView_defaultQueryHint);
        this.T = u.o(R$styleable.SearchView_queryHint);
        int iJ = u.j(R$styleable.SearchView_android_imeOptions, -1);
        if (iJ != -1) {
            n0(iJ);
        }
        int iJ2 = u.j(R$styleable.SearchView_android_inputType, -1);
        if (iJ2 != -1) {
            o0(iJ2);
        }
        setFocusable(u.a(R$styleable.SearchView_android_focusable, true));
        u.v();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.H = intent;
        intent.addFlags(268435456);
        this.H.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.I = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById = findViewById(this.r.getDropDownAnchor());
        this.z = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.addOnLayoutChangeListener(new e());
        }
        x0(this.P);
        u0();
    }
}
