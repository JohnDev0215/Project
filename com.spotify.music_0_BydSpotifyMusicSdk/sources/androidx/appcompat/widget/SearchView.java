package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import com.spotify.music.R;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import p.ab2;
import p.cb1;
import p.dh2;
import p.hu;
import p.im2;
import p.it2;
import p.ka1;
import p.kk0;
import p.pa2;
import p.qa2;
import p.ra2;
import p.s40;
import p.sa2;
import p.ta2;
import p.u7;
import p.ua2;
import p.v4;
import p.va2;
import p.wa2;
import p.xa2;
import p.yz1;
import p.za2;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends ka1 implements hu {
    public static final kk0 o0;
    public final View A;
    public final ImageView B;
    public final ImageView C;
    public final ImageView D;
    public final ImageView E;
    public final View F;
    public ab2 G;
    public final Rect H;
    public final Rect I;
    public final int[] J;
    public final int[] K;
    public final ImageView L;
    public final Drawable M;
    public final int N;
    public final int O;
    public final Intent P;
    public final Intent Q;
    public final CharSequence R;
    public View.OnFocusChangeListener S;
    public View.OnClickListener T;
    public boolean U;
    public boolean V;
    public s40 W;
    public boolean a0;
    public CharSequence b0;
    public boolean c0;
    public boolean d0;
    public int e0;
    public boolean f0;
    public CharSequence g0;
    public boolean h0;
    public int i0;
    public SearchableInfo j0;
    public Bundle k0;
    public final qa2 l0;
    public final qa2 m0;
    public final WeakHashMap n0;
    public final SearchAutoComplete x;
    public final View y;
    public final View z;

    public static class SearchAutoComplete extends u7 {
        public int m;
        public SearchView n;
        public boolean o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final d f10p;

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f10p = new d(this);
            this.m = getThreshold();
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

        public final void a() {
            if (Build.VERSION.SDK_INT >= 29) {
                c.b(this, 1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            kk0 kk0Var = SearchView.o0;
            kk0Var.getClass();
            kk0.z();
            Object obj = kk0Var.l;
            if (((Method) obj) != null) {
                try {
                    ((Method) obj).invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.m <= 0 || super.enoughToFilter();
        }

        @Override // p.u7, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.o) {
                d dVar = this.f10p;
                removeCallbacks(dVar);
                post(dVar);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            SearchView searchView = this.n;
            searchView.y(searchView.V);
            searchView.post(searchView.l0);
            if (searchView.x.hasFocus()) {
                searchView.n();
            }
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
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
                        this.n.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.n.hasFocus() && getVisibility() == 0) {
                this.o = true;
                Context context = getContext();
                kk0 kk0Var = SearchView.o0;
                if (context.getResources().getConfiguration().orientation == 2) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public final void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            d dVar = this.f10p;
            if (!z) {
                this.o = false;
                removeCallbacks(dVar);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.o = true;
                    return;
                }
                this.o = false;
                removeCallbacks(dVar);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.n = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.m = i;
        }
    }

    static {
        o0 = Build.VERSION.SDK_INT < 29 ? new kk0(3) : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        SearchAutoComplete searchAutoComplete = this.x;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // p.hu
    public final void a() {
        if (this.h0) {
            return;
        }
        this.h0 = true;
        SearchAutoComplete searchAutoComplete = this.x;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.i0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.d0 = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.x;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.d0 = false;
    }

    @Override // p.hu
    public final void e() {
        SearchAutoComplete searchAutoComplete = this.x;
        searchAutoComplete.setText("");
        searchAutoComplete.setSelection(searchAutoComplete.length());
        this.g0 = "";
        clearFocus();
        y(true);
        searchAutoComplete.setImeOptions(this.i0);
        this.h0 = false;
    }

    public int getImeOptions() {
        return this.x.getImeOptions();
    }

    public int getInputType() {
        return this.x.getInputType();
    }

    public int getMaxWidth() {
        return this.e0;
    }

    public CharSequence getQuery() {
        return this.x.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.b0;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.j0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.R : getContext().getText(this.j0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.O;
    }

    public int getSuggestionRowLayout() {
        return this.N;
    }

    public s40 getSuggestionsAdapter() {
        return this.W;
    }

    public final Intent l(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.g0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.k0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        intent.setComponent(this.j0.getSearchActivity());
        return intent;
    }

    public final Intent m(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.k0;
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

    public final void n() {
        int i = Build.VERSION.SDK_INT;
        SearchAutoComplete searchAutoComplete = this.x;
        if (i >= 29) {
            c.a(searchAutoComplete);
            return;
        }
        kk0 kk0Var = o0;
        kk0Var.getClass();
        kk0.z();
        Object obj = kk0Var.e;
        if (((Method) obj) != null) {
            try {
                ((Method) obj).invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        kk0Var.getClass();
        kk0.z();
        Object obj2 = kk0Var.k;
        if (((Method) obj2) != null) {
            try {
                ((Method) obj2).invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public final void o() {
        SearchAutoComplete searchAutoComplete = this.x;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText("");
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.U) {
            clearFocus();
            y(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.l0);
        post(this.m0);
        super.onDetachedFromWindow();
    }

    @Override // p.ka1, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int[] iArr = this.J;
            SearchAutoComplete searchAutoComplete = this.x;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.K;
            getLocationInWindow(iArr2);
            int i5 = iArr[1] - iArr2[1];
            int i6 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i6;
            int height = searchAutoComplete.getHeight() + i5;
            Rect rect = this.H;
            rect.set(i6, i5, width, height);
            int i7 = rect.left;
            int i8 = rect.right;
            int i9 = i4 - i2;
            Rect rect2 = this.I;
            rect2.set(i7, 0, i8, i9);
            ab2 ab2Var = this.G;
            if (ab2Var == null) {
                ab2 ab2Var2 = new ab2(rect2, rect, searchAutoComplete);
                this.G = ab2Var2;
                setTouchDelegate(ab2Var2);
            } else {
                ab2Var.b.set(rect2);
                Rect rect3 = ab2Var.d;
                rect3.set(rect2);
                int i10 = -ab2Var.e;
                rect3.inset(i10, i10);
                ab2Var.c.set(rect);
            }
        }
    }

    @Override // p.ka1, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        if (this.V) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int i4 = this.e0;
            size = i4 > 0 ? Math.min(i4, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.e0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i3 = this.e0) > 0) {
            size = Math.min(i3, size);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof za2)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        za2 za2Var = (za2) parcelable;
        super.onRestoreInstanceState(za2Var.c);
        y(za2Var.k);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        za2 za2Var = new za2(super.onSaveInstanceState());
        za2Var.k = this.V;
        return za2Var;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.l0);
    }

    public final void p(int i) {
        String strH;
        Cursor cursor = this.W.k;
        if (cursor != null && cursor.moveToPosition(i)) {
            Intent intentL = null;
            try {
                try {
                    int i2 = dh2.F;
                    String strH2 = dh2.h(cursor, cursor.getColumnIndex("suggest_intent_action"));
                    if (strH2 == null) {
                        strH2 = this.j0.getSuggestIntentAction();
                    }
                    if (strH2 == null) {
                        strH2 = "android.intent.action.SEARCH";
                    }
                    String strH3 = dh2.h(cursor, cursor.getColumnIndex("suggest_intent_data"));
                    if (strH3 == null) {
                        strH3 = this.j0.getSuggestIntentData();
                    }
                    if (strH3 != null && (strH = dh2.h(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                        strH3 = strH3 + "/" + Uri.encode(strH);
                    }
                    intentL = l(strH2, strH3 == null ? null : Uri.parse(strH3), dh2.h(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), dh2.h(cursor, cursor.getColumnIndex("suggest_intent_query")));
                } catch (RuntimeException unused) {
                    cursor.getPosition();
                }
            } catch (RuntimeException unused2) {
            }
            if (intentL != null) {
                try {
                    getContext().startActivity(intentL);
                } catch (RuntimeException e) {
                    Log.e("SearchView", "Failed launch activity: " + intentL, e);
                }
            }
        }
        SearchAutoComplete searchAutoComplete = this.x;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public final void q(int i) {
        Editable text = this.x.getText();
        Cursor cursor = this.W.k;
        if (cursor == null) {
            return;
        }
        if (!cursor.moveToPosition(i)) {
            setQuery(text);
            return;
        }
        String strC = this.W.c(cursor);
        if (strC != null) {
            setQuery(strC);
        } else {
            setQuery(text);
        }
    }

    public final void r(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i, Rect rect) {
        if (this.d0 || !isFocusable()) {
            return false;
        }
        if (this.V) {
            return super.requestFocus(i, rect);
        }
        boolean zRequestFocus = this.x.requestFocus(i, rect);
        if (zRequestFocus) {
            y(false);
        }
        return zRequestFocus;
    }

    public final void s() {
        SearchAutoComplete searchAutoComplete = this.x;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.j0 != null) {
            getContext().startActivity(l("android.intent.action.SEARCH", null, null, text.toString()));
        }
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public void setAppSearchData(Bundle bundle) {
        this.k0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            o();
            return;
        }
        y(false);
        SearchAutoComplete searchAutoComplete = this.x;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.T;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.U == z) {
            return;
        }
        this.U = z;
        y(z);
        v();
    }

    public void setImeOptions(int i) {
        this.x.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.x.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.e0 = i;
        requestLayout();
    }

    public void setOnCloseListener(va2 va2Var) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.S = onFocusChangeListener;
    }

    public void setOnQueryTextListener(wa2 wa2Var) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.T = onClickListener;
    }

    public void setOnSuggestionListener(xa2 xa2Var) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.b0 = charSequence;
        v();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.c0 = z;
        s40 s40Var = this.W;
        if (s40Var instanceof dh2) {
            ((dh2) s40Var).x = z ? 2 : 1;
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.j0 = searchableInfo;
        Intent intent = null;
        SearchAutoComplete searchAutoComplete = this.x;
        if (searchableInfo != null) {
            searchAutoComplete.setThreshold(searchableInfo.getSuggestThreshold());
            searchAutoComplete.setImeOptions(this.j0.getImeOptions());
            int inputType = this.j0.getInputType();
            if ((inputType & 15) == 1) {
                inputType &= -65537;
                if (this.j0.getSuggestAuthority() != null) {
                    inputType |= 589824;
                }
            }
            searchAutoComplete.setInputType(inputType);
            s40 s40Var = this.W;
            if (s40Var != null) {
                s40Var.b(null);
            }
            if (this.j0.getSuggestAuthority() != null) {
                dh2 dh2Var = new dh2(getContext(), this, this.j0, this.n0);
                this.W = dh2Var;
                searchAutoComplete.setAdapter(dh2Var);
                ((dh2) this.W).x = this.c0 ? 2 : 1;
            }
            v();
        }
        SearchableInfo searchableInfo2 = this.j0;
        boolean z = false;
        if (searchableInfo2 != null && searchableInfo2.getVoiceSearchEnabled()) {
            if (this.j0.getVoiceSearchLaunchWebSearch()) {
                intent = this.P;
            } else if (this.j0.getVoiceSearchLaunchRecognizer()) {
                intent = this.Q;
            }
            if (intent != null) {
                z = getContext().getPackageManager().resolveActivity(intent, 65536) != null;
            }
        }
        this.f0 = z;
        if (z) {
            searchAutoComplete.setPrivateImeOptions("nm");
        }
        y(this.V);
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.a0 = z;
        y(this.V);
    }

    public void setSuggestionsAdapter(s40 s40Var) {
        this.W = s40Var;
        this.x.setAdapter(s40Var);
    }

    public final void t() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.x.getText());
        if (!z2 && (!this.U || this.h0)) {
            z = false;
        }
        int i = z ? 0 : 8;
        ImageView imageView = this.D;
        imageView.setVisibility(i);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public final void u() {
        int[] iArr = this.x.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.z.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.A.getBackground();
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
    public final void v() {
        CharSequence queryHint = getQueryHint();
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = "";
        }
        boolean z = this.U;
        SearchAutoComplete searchAutoComplete = this.x;
        CharSequence charSequence2 = charSequence;
        if (z) {
            Drawable drawable = this.M;
            charSequence2 = charSequence;
            if (drawable != null) {
                int textSize = (int) (((double) searchAutoComplete.getTextSize()) * 1.25d);
                drawable.setBounds(0, 0, textSize, textSize);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
                spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
                spannableStringBuilder.append(charSequence);
                charSequence2 = spannableStringBuilder;
            }
        }
        searchAutoComplete.setHint(charSequence2);
    }

    public final void w() {
        this.A.setVisibility(((this.a0 || this.f0) && !this.V && (this.C.getVisibility() == 0 || this.E.getVisibility() == 0)) ? 0 : 8);
    }

    public final void x(boolean z) {
        boolean z2 = this.a0;
        this.C.setVisibility((!z2 || !(z2 || this.f0) || this.V || !hasFocus() || (!z && this.f0)) ? 8 : 0);
    }

    public final void y(boolean z) {
        this.V = z;
        int i = 8;
        int i2 = z ? 0 : 8;
        boolean zIsEmpty = TextUtils.isEmpty(this.x.getText());
        this.B.setVisibility(i2);
        x(!zIsEmpty);
        this.y.setVisibility(z ? 8 : 0);
        ImageView imageView = this.L;
        imageView.setVisibility((imageView.getDrawable() == null || this.U) ? 8 : 0);
        t();
        if (this.f0 && !this.V && zIsEmpty) {
            this.C.setVisibility(8);
            i = 0;
        }
        this.E.setVisibility(i);
        w();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.H = new Rect();
        this.I = new Rect();
        this.J = new int[2];
        this.K = new int[2];
        int i2 = 0;
        this.l0 = new qa2(this, i2);
        this.m0 = new qa2(this, 1);
        this.n0 = new WeakHashMap();
        a aVar = new a(this);
        b bVar = new b(this);
        ta2 ta2Var = new ta2(this);
        ua2 ua2Var = new ua2(this, i2);
        cb1 cb1Var = new cb1(1, this);
        pa2 pa2Var = new pa2(0, this);
        int[] iArr = yz1.u;
        v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
        zt2.j(this, context, iArr, attributeSet, (TypedArray) v4Var.k, i);
        LayoutInflater.from(context).inflate(v4Var.y(9, R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.x = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.y = findViewById(R.id.search_edit_frame);
        View viewFindViewById = findViewById(R.id.search_plate);
        this.z = viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.submit_area);
        this.A = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R.id.search_button);
        this.B = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.search_go_btn);
        this.C = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.search_close_btn);
        this.D = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R.id.search_voice_btn);
        this.E = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.search_mag_icon);
        this.L = imageView5;
        it2.q(viewFindViewById, v4Var.t(10));
        it2.q(viewFindViewById2, v4Var.t(14));
        imageView.setImageDrawable(v4Var.t(13));
        imageView2.setImageDrawable(v4Var.t(7));
        imageView3.setImageDrawable(v4Var.t(4));
        imageView4.setImageDrawable(v4Var.t(16));
        imageView5.setImageDrawable(v4Var.t(13));
        this.M = v4Var.t(12);
        im2.a(imageView, getResources().getString(R.string.abc_searchview_description_search));
        this.N = v4Var.y(15, R.layout.abc_search_dropdown_item_icons_2line);
        this.O = v4Var.y(5, 0);
        imageView.setOnClickListener(aVar);
        imageView3.setOnClickListener(aVar);
        imageView2.setOnClickListener(aVar);
        imageView4.setOnClickListener(aVar);
        searchAutoComplete.setOnClickListener(aVar);
        searchAutoComplete.addTextChangedListener(pa2Var);
        searchAutoComplete.setOnEditorActionListener(ta2Var);
        searchAutoComplete.setOnItemClickListener(ua2Var);
        searchAutoComplete.setOnItemSelectedListener(cb1Var);
        searchAutoComplete.setOnKeyListener(bVar);
        searchAutoComplete.setOnFocusChangeListener(new ra2(0, this));
        setIconifiedByDefault(v4Var.p(8, true));
        int iS = v4Var.s(1, -1);
        if (iS != -1) {
            setMaxWidth(iS);
        }
        this.R = v4Var.A(6);
        this.b0 = v4Var.A(11);
        int iW = v4Var.w(3, -1);
        if (iW != -1) {
            setImeOptions(iW);
        }
        int iW2 = v4Var.w(2, -1);
        if (iW2 != -1) {
            setInputType(iW2);
        }
        setFocusable(v4Var.p(0, true));
        v4Var.L();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.P = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.Q = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.F = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new sa2(0, this));
        }
        y(this.U);
        v();
    }
}
