package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
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
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.customview.view.AbsSavedState;
import defpackage.in3;
import defpackage.j20;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    public static final /* synthetic */ int o0 = 0;
    public final ImageView A;
    public final View B;
    public UpdatableTouchDelegate C;
    public final Rect D;
    public final Rect E;
    public final int[] F;
    public final int[] G;
    public final ImageView H;
    public final Drawable I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f3J;
    public final int K;
    public final Intent L;
    public final Intent M;
    public final CharSequence N;
    public OnQueryTextListener O;
    public OnCloseListener P;
    public View.OnFocusChangeListener Q;
    public OnSuggestionListener R;
    public View.OnClickListener S;
    public boolean T;
    public boolean U;
    public j20 V;
    public boolean W;
    public CharSequence a0;
    public boolean b0;
    public boolean c0;
    public int d0;
    public boolean e0;
    public String f0;
    public CharSequence g0;
    public boolean h0;
    public int i0;
    public SearchableInfo j0;
    public Bundle k0;
    public final Runnable l0;
    public final Runnable m0;
    public final WeakHashMap<String, Drawable.ConstantState> n0;
    public final SearchAutoComplete t;
    public final View u;
    public final View v;
    public final View w;
    public final ImageView x;
    public final ImageView y;
    public final ImageView z;

    public static class Api29Impl {
        private Api29Impl() {
        }
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<SearchView> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapInt("imeOptions", R.attr.imeOptions);
            this.c = propertyMapper.mapInt("maxWidth", R.attr.maxWidth);
            this.d = propertyMapper.mapBoolean("iconifiedByDefault", androidx.appcompat.R.attr.iconifiedByDefault);
            this.e = propertyMapper.mapObject("queryHint", androidx.appcompat.R.attr.queryHint);
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(SearchView searchView, PropertyReader propertyReader) {
            SearchView searchView2 = searchView;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readInt(this.b, searchView2.getImeOptions());
            propertyReader.readInt(this.c, searchView2.getMaxWidth());
            propertyReader.readBoolean(this.d, searchView2.T);
            propertyReader.readObject(this.e, searchView2.getQueryHint());
        }
    }

    public interface OnCloseListener {
        void onClose();
    }

    public interface OnQueryTextListener {
        void a();

        void onQueryTextChange(String str);
    }

    public interface OnSuggestionListener {
        boolean a();

        boolean b();
    }

    public static class PreQAutoCompleteTextViewReflector {
        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        public PreQAutoCompleteTextViewReflector() {
            throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.appcompat.widget.SearchView.SavedState.1
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public boolean g;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.g + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeValue(Boolean.valueOf(this.g));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        public int i;
        public SearchView j;
        public boolean k;
        public final Runnable l;

        public SearchAutoComplete(Context context) {
            this(context, null);
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
        public final boolean enoughToFilter() {
            return this.i <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.k) {
                Runnable runnable = this.l;
                removeCallbacks(runnable);
                post(runnable);
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
            SearchView searchView = this.j;
            searchView.w(searchView.U);
            searchView.post(searchView.l0);
            SearchAutoComplete searchAutoComplete = searchView.t;
            if (searchAutoComplete.hasFocus()) {
                searchAutoComplete.refreshAutoCompleteResults();
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
                        this.j.clearFocus();
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
            if (z && this.j.hasFocus() && getVisibility() == 0) {
                this.k = true;
                Context context = getContext();
                int i = SearchView.o0;
                if (context.getResources().getConfiguration().orientation == 2) {
                    setInputMethodMode(1);
                    if (enoughToFilter()) {
                        showDropDown();
                    }
                }
            }
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            Runnable runnable = this.l;
            if (!z) {
                this.k = false;
                removeCallbacks(runnable);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.k = true;
                    return;
                }
                this.k = false;
                removeCallbacks(runnable);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.j = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.i = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, androidx.appcompat.R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.l = new Runnable() { // from class: androidx.appcompat.widget.SearchView.SearchAutoComplete.1
                @Override // java.lang.Runnable
                public final void run() {
                    SearchAutoComplete searchAutoComplete = SearchAutoComplete.this;
                    if (searchAutoComplete.k) {
                        ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
                        searchAutoComplete.k = false;
                    }
                }
            };
            this.i = getThreshold();
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public final void replaceText(CharSequence charSequence) {
        }
    }

    public static class UpdatableTouchDelegate extends TouchDelegate {
        public final View a;
        public final Rect b;
        public final Rect c;
        public final Rect d;
        public final int e;
        public boolean f;

        public UpdatableTouchDelegate(Rect rect, Rect rect2, SearchAutoComplete searchAutoComplete) {
            super(rect, searchAutoComplete);
            int scaledTouchSlop = ViewConfiguration.get(searchAutoComplete.getContext()).getScaledTouchSlop();
            this.e = scaledTouchSlop;
            Rect rect3 = new Rect();
            this.b = rect3;
            Rect rect4 = new Rect();
            this.d = rect4;
            Rect rect5 = new Rect();
            this.c = rect5;
            rect3.set(rect);
            rect4.set(rect);
            int i = -scaledTouchSlop;
            rect4.inset(i, i);
            rect5.set(rect2);
            this.a = searchAutoComplete;
        }

        @Override // android.view.TouchDelegate
        public final boolean onTouchEvent(MotionEvent motionEvent) {
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
            } else if (this.b.contains(x, y)) {
                this.f = true;
                z = true;
            } else {
                z = true;
                z3 = false;
            }
            if (!z3) {
                return false;
            }
            Rect rect = this.c;
            View view = this.a;
            if (!z || rect.contains(x, y)) {
                motionEvent.setLocation(x - rect.left, y - rect.top);
            } else {
                motionEvent.setLocation(view.getWidth() / 2, view.getHeight() / 2);
            }
            return view.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(androidx.appcompat.R.dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(androidx.appcompat.R.dimen.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        SearchAutoComplete searchAutoComplete = this.t;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.c0 = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.t;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.c0 = false;
    }

    public int getImeOptions() {
        return this.t.getImeOptions();
    }

    public int getInputType() {
        return this.t.getInputType();
    }

    public int getMaxWidth() {
        return this.d0;
    }

    public CharSequence getQuery() {
        return this.t.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.a0;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.j0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.N : getContext().getText(this.j0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.K;
    }

    public int getSuggestionRowLayout() {
        return this.f3J;
    }

    public j20 getSuggestionsAdapter() {
        return this.V;
    }

    public final Intent j(String str, Uri uri, String str2, String str3) {
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

    public final Intent k(Intent intent, SearchableInfo searchableInfo) {
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

    public final void l() {
        SearchAutoComplete searchAutoComplete = this.t;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText("");
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.T) {
            OnCloseListener onCloseListener = this.P;
            if (onCloseListener != null) {
                onCloseListener.onClose();
            }
            clearFocus();
            w(true);
        }
    }

    public final boolean m(int i) {
        int position;
        String strJ;
        OnSuggestionListener onSuggestionListener = this.R;
        if (onSuggestionListener != null && onSuggestionListener.a()) {
            return false;
        }
        Cursor cursor = this.V.g;
        if (cursor != null && cursor.moveToPosition(i)) {
            Intent intentJ = null;
            try {
                int i2 = SuggestionsAdapter.B;
                String strJ2 = SuggestionsAdapter.j(cursor, cursor.getColumnIndex("suggest_intent_action"));
                if (strJ2 == null) {
                    strJ2 = this.j0.getSuggestIntentAction();
                }
                if (strJ2 == null) {
                    strJ2 = "android.intent.action.SEARCH";
                }
                String strJ3 = SuggestionsAdapter.j(cursor, cursor.getColumnIndex("suggest_intent_data"));
                if (strJ3 == null) {
                    strJ3 = this.j0.getSuggestIntentData();
                }
                if (strJ3 != null && (strJ = SuggestionsAdapter.j(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                    strJ3 = strJ3 + "/" + Uri.encode(strJ);
                }
                intentJ = j(strJ2, strJ3 == null ? null : Uri.parse(strJ3), SuggestionsAdapter.j(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), SuggestionsAdapter.j(cursor, cursor.getColumnIndex("suggest_intent_query")));
            } catch (RuntimeException e) {
                try {
                    position = cursor.getPosition();
                } catch (RuntimeException unused) {
                    position = -1;
                }
                Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e);
            }
            if (intentJ != null) {
                try {
                    getContext().startActivity(intentJ);
                } catch (RuntimeException e2) {
                    Log.e("SearchView", "Failed launch activity: " + intentJ, e2);
                }
            }
        }
        SearchAutoComplete searchAutoComplete = this.t;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
        return true;
    }

    public final void n(int i) {
        OnSuggestionListener onSuggestionListener = this.R;
        if (onSuggestionListener == null || !onSuggestionListener.b()) {
            Editable text = this.t.getText();
            Cursor cursor = this.V.g;
            if (cursor == null) {
                return;
            }
            if (!cursor.moveToPosition(i)) {
                setQuery(text);
                return;
            }
            String strC = this.V.c(cursor);
            if (strC != null) {
                setQuery(strC);
            } else {
                setQuery(text);
            }
        }
    }

    public final void o(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // androidx.appcompat.view.CollapsibleActionView
    public final void onActionViewCollapsed() {
        q("");
        clearFocus();
        w(true);
        this.t.setImeOptions(this.i0);
        this.h0 = false;
    }

    @Override // androidx.appcompat.view.CollapsibleActionView
    public final void onActionViewExpanded() {
        if (this.h0) {
            return;
        }
        this.h0 = true;
        SearchAutoComplete searchAutoComplete = this.t;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.i0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.l0);
        post(this.m0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            SearchAutoComplete searchAutoComplete = this.t;
            int[] iArr = this.F;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.G;
            getLocationInWindow(iArr2);
            int i5 = iArr[1] - iArr2[1];
            int i6 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i6;
            int height = searchAutoComplete.getHeight() + i5;
            Rect rect = this.D;
            rect.set(i6, i5, width, height);
            int i7 = rect.left;
            int i8 = rect.right;
            int i9 = i4 - i2;
            Rect rect2 = this.E;
            rect2.set(i7, 0, i8, i9);
            UpdatableTouchDelegate updatableTouchDelegate = this.C;
            if (updatableTouchDelegate == null) {
                UpdatableTouchDelegate updatableTouchDelegate2 = new UpdatableTouchDelegate(rect2, rect, searchAutoComplete);
                this.C = updatableTouchDelegate2;
                setTouchDelegate(updatableTouchDelegate2);
            } else {
                updatableTouchDelegate.b.set(rect2);
                Rect rect3 = updatableTouchDelegate.d;
                rect3.set(rect2);
                int i10 = -updatableTouchDelegate.e;
                rect3.inset(i10, i10);
                updatableTouchDelegate.c.set(rect);
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        if (this.U) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int i4 = this.d0;
            size = i4 > 0 ? Math.min(i4, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.d0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i3 = this.d0) > 0) {
            size = Math.min(i3, size);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(size2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        w(savedState.g);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.g = this.U;
        return savedState;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.l0);
    }

    public final void p() {
        SearchAutoComplete searchAutoComplete = this.t;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        OnQueryTextListener onQueryTextListener = this.O;
        if (onQueryTextListener != null) {
            text.toString();
            onQueryTextListener.a();
            return;
        }
        if (this.j0 != null) {
            getContext().startActivity(j("android.intent.action.SEARCH", null, null, text.toString()));
        }
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public final void q(String str) {
        SearchAutoComplete searchAutoComplete = this.t;
        searchAutoComplete.setText(str);
        if (str != null) {
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.g0 = str;
        }
    }

    public final void r() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.t.getText());
        if (!z2 && (!this.T || this.h0)) {
            z = false;
        }
        int i = z ? 0 : 8;
        ImageView imageView = this.z;
        imageView.setVisibility(i);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i, Rect rect) {
        if (this.c0 || !isFocusable()) {
            return false;
        }
        if (this.U) {
            return super.requestFocus(i, rect);
        }
        boolean zRequestFocus = this.t.requestFocus(i, rect);
        if (zRequestFocus) {
            w(false);
        }
        return zRequestFocus;
    }

    public final void s() {
        int[] iArr = this.t.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.v.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.w.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public void setAppSearchData(Bundle bundle) {
        this.k0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            l();
            return;
        }
        w(false);
        SearchAutoComplete searchAutoComplete = this.t;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.S;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.T == z) {
            return;
        }
        this.T = z;
        w(z);
        t();
    }

    public void setImeOptions(int i) {
        this.t.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.t.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.d0 = i;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.P = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.Q = onFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.O = onQueryTextListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.S = onClickListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.R = onSuggestionListener;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.a0 = charSequence;
        t();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.b0 = z;
        j20 j20Var = this.V;
        if (j20Var instanceof SuggestionsAdapter) {
            ((SuggestionsAdapter) j20Var).t = z ? 2 : 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSearchableInfo(android.app.SearchableInfo r8) {
        /*
            r7 = this;
            r7.j0 = r8
            androidx.appcompat.widget.SearchView$SearchAutoComplete r0 = r7.t
            r1 = 1
            r2 = 65536(0x10000, float:9.1835E-41)
            r3 = 0
            if (r8 == 0) goto L68
            int r8 = r8.getSuggestThreshold()
            r0.setThreshold(r8)
            android.app.SearchableInfo r8 = r7.j0
            int r8 = r8.getImeOptions()
            r0.setImeOptions(r8)
            android.app.SearchableInfo r8 = r7.j0
            int r8 = r8.getInputType()
            r4 = r8 & 15
            if (r4 != r1) goto L34
            r4 = -65537(0xfffffffffffeffff, float:NaN)
            r8 = r8 & r4
            android.app.SearchableInfo r4 = r7.j0
            java.lang.String r4 = r4.getSuggestAuthority()
            if (r4 == 0) goto L34
            r8 = r8 | r2
            r4 = 524288(0x80000, float:7.34684E-40)
            r8 = r8 | r4
        L34:
            r0.setInputType(r8)
            j20 r8 = r7.V
            if (r8 == 0) goto L3e
            r8.a(r3)
        L3e:
            android.app.SearchableInfo r8 = r7.j0
            java.lang.String r8 = r8.getSuggestAuthority()
            if (r8 == 0) goto L65
            androidx.appcompat.widget.SuggestionsAdapter r8 = new androidx.appcompat.widget.SuggestionsAdapter
            android.content.Context r4 = r7.getContext()
            android.app.SearchableInfo r5 = r7.j0
            java.util.WeakHashMap<java.lang.String, android.graphics.drawable.Drawable$ConstantState> r6 = r7.n0
            r8.<init>(r4, r7, r5, r6)
            r7.V = r8
            r0.setAdapter(r8)
            j20 r8 = r7.V
            androidx.appcompat.widget.SuggestionsAdapter r8 = (androidx.appcompat.widget.SuggestionsAdapter) r8
            boolean r4 = r7.b0
            if (r4 == 0) goto L62
            r4 = 2
            goto L63
        L62:
            r4 = r1
        L63:
            r8.t = r4
        L65:
            r7.t()
        L68:
            android.app.SearchableInfo r8 = r7.j0
            if (r8 == 0) goto L98
            boolean r8 = r8.getVoiceSearchEnabled()
            if (r8 == 0) goto L98
            android.app.SearchableInfo r8 = r7.j0
            boolean r8 = r8.getVoiceSearchLaunchWebSearch()
            if (r8 == 0) goto L7d
            android.content.Intent r3 = r7.L
            goto L87
        L7d:
            android.app.SearchableInfo r8 = r7.j0
            boolean r8 = r8.getVoiceSearchLaunchRecognizer()
            if (r8 == 0) goto L87
            android.content.Intent r3 = r7.M
        L87:
            if (r3 == 0) goto L98
            android.content.Context r8 = r7.getContext()
            android.content.pm.PackageManager r8 = r8.getPackageManager()
            android.content.pm.ResolveInfo r8 = r8.resolveActivity(r3, r2)
            if (r8 == 0) goto L98
            goto L99
        L98:
            r1 = 0
        L99:
            r7.e0 = r1
            if (r1 == 0) goto La2
            java.lang.String r8 = "nm"
            r0.setPrivateImeOptions(r8)
        La2:
            boolean r8 = r7.U
            r7.w(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.W = z;
        w(this.U);
    }

    public void setSuggestionsAdapter(j20 j20Var) {
        this.V = j20Var;
        this.t.setAdapter(j20Var);
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
        CharSequence queryHint = getQueryHint();
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = "";
        }
        boolean z = this.T;
        SearchAutoComplete searchAutoComplete = this.t;
        CharSequence charSequence2 = charSequence;
        if (z) {
            Drawable drawable = this.I;
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

    public final void u() {
        int i = 0;
        if (!((this.W || this.e0) && !this.U) || (this.y.getVisibility() != 0 && this.A.getVisibility() != 0)) {
            i = 8;
        }
        this.w.setVisibility(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(boolean r3) {
        /*
            r2 = this;
            boolean r0 = r2.W
            if (r0 == 0) goto L21
            r1 = 0
            if (r0 != 0) goto Lb
            boolean r0 = r2.e0
            if (r0 == 0) goto L11
        Lb:
            boolean r0 = r2.U
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
            boolean r3 = r2.e0
            if (r3 != 0) goto L21
            goto L23
        L21:
            r1 = 8
        L23:
            android.widget.ImageView r2 = r2.y
            r2.setVisibility(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.v(boolean):void");
    }

    public final void w(boolean z) {
        this.U = z;
        int i = 0;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.t.getText());
        this.x.setVisibility(i2);
        v(z2);
        this.u.setVisibility(z ? 8 : 0);
        ImageView imageView = this.H;
        imageView.setVisibility((imageView.getDrawable() == null || this.T) ? 8 : 0);
        r();
        boolean z3 = !z2;
        if (this.e0 && !this.U && z3) {
            this.y.setVisibility(8);
        } else {
            i = 8;
        }
        this.A.setVisibility(i);
        u();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.appcompat.R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.D = new Rect();
        this.E = new Rect();
        this.F = new int[2];
        this.G = new int[2];
        this.l0 = new Runnable() { // from class: androidx.appcompat.widget.SearchView.1
            @Override // java.lang.Runnable
            public final void run() {
                SearchView.this.s();
            }
        };
        this.m0 = new Runnable() { // from class: androidx.appcompat.widget.SearchView.2
            @Override // java.lang.Runnable
            public final void run() {
                j20 j20Var = SearchView.this.V;
                if (j20Var instanceof SuggestionsAdapter) {
                    j20Var.a(null);
                }
            }
        };
        this.n0 = new WeakHashMap<>();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: androidx.appcompat.widget.SearchView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchView searchView = SearchView.this;
                ImageView imageView = searchView.x;
                SearchAutoComplete searchAutoComplete = searchView.t;
                if (view == imageView) {
                    searchView.w(false);
                    searchAutoComplete.requestFocus();
                    searchAutoComplete.setImeVisibility(true);
                    View.OnClickListener onClickListener2 = searchView.S;
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(searchView);
                        return;
                    }
                    return;
                }
                if (view == searchView.z) {
                    searchView.l();
                    return;
                }
                if (view == searchView.y) {
                    searchView.p();
                    return;
                }
                if (view != searchView.A) {
                    if (view == searchAutoComplete) {
                        searchAutoComplete.refreshAutoCompleteResults();
                        return;
                    }
                    return;
                }
                SearchableInfo searchableInfo = searchView.j0;
                if (searchableInfo == null) {
                    return;
                }
                try {
                    if (!searchableInfo.getVoiceSearchLaunchWebSearch()) {
                        if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                            searchView.getContext().startActivity(searchView.k(searchView.M, searchableInfo));
                        }
                    } else {
                        Intent intent = new Intent(searchView.L);
                        ComponentName searchActivity = searchableInfo.getSearchActivity();
                        intent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
                        searchView.getContext().startActivity(intent);
                    }
                } catch (ActivityNotFoundException unused) {
                    Log.w("SearchView", "Could not find voice search activity");
                }
            }
        };
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: androidx.appcompat.widget.SearchView.6
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
                SearchView searchView = SearchView.this;
                if (searchView.j0 == null) {
                    return false;
                }
                SearchAutoComplete searchAutoComplete = searchView.t;
                if (!searchAutoComplete.isPopupShowing() || searchAutoComplete.getListSelection() == -1) {
                    if ((TextUtils.getTrimmedLength(searchAutoComplete.getText()) == 0) || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i2 != 66) {
                        return false;
                    }
                    view.cancelLongPress();
                    searchView.getContext().startActivity(searchView.j("android.intent.action.SEARCH", null, null, searchAutoComplete.getText().toString()));
                    return true;
                }
                if (searchView.j0 == null || searchView.V == null || keyEvent.getAction() != 0 || !keyEvent.hasNoModifiers()) {
                    return false;
                }
                if (i2 == 66 || i2 == 84 || i2 == 61) {
                    return searchView.m(searchAutoComplete.getListSelection());
                }
                if (i2 != 21 && i2 != 22) {
                    if (i2 != 19) {
                        return false;
                    }
                    searchAutoComplete.getListSelection();
                    return false;
                }
                searchAutoComplete.setSelection(i2 == 21 ? 0 : searchAutoComplete.length());
                searchAutoComplete.setListSelection(0);
                searchAutoComplete.clearListSelection();
                searchAutoComplete.setInputMethodMode(1);
                if (searchAutoComplete.enoughToFilter()) {
                    searchAutoComplete.showDropDown();
                }
                return true;
            }
        };
        TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { // from class: androidx.appcompat.widget.SearchView.7
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                SearchView.this.p();
                return true;
            }
        };
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.widget.SearchView.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                SearchView.this.m(i2);
            }
        };
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: androidx.appcompat.widget.SearchView.9
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public final void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                SearchView.this.n(i2);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public final void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        TextWatcher textWatcher = new TextWatcher() { // from class: androidx.appcompat.widget.SearchView.10
            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                SearchView searchView = SearchView.this;
                Editable text = searchView.t.getText();
                searchView.g0 = text;
                boolean z = !TextUtils.isEmpty(text);
                searchView.v(z);
                boolean z2 = !z;
                int i5 = 8;
                if (searchView.e0 && !searchView.U && z2) {
                    searchView.y.setVisibility(8);
                    i5 = 0;
                }
                searchView.A.setVisibility(i5);
                searchView.r();
                searchView.u();
                if (searchView.O != null && !TextUtils.equals(charSequence, searchView.f0)) {
                    searchView.O.onQueryTextChange(charSequence.toString());
                }
                searchView.f0 = charSequence.toString();
            }

            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        };
        int[] iArr = androidx.appcompat.R.styleable.SearchView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        TintTypedArray tintTypedArray = new TintTypedArray(context, typedArrayObtainStyledAttributes);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        LayoutInflater.from(context).inflate(tintTypedArray.i(androidx.appcompat.R.styleable.SearchView_layout, androidx.appcompat.R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(androidx.appcompat.R.id.search_src_text);
        this.t = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.u = findViewById(androidx.appcompat.R.id.search_edit_frame);
        View viewFindViewById = findViewById(androidx.appcompat.R.id.search_plate);
        this.v = viewFindViewById;
        View viewFindViewById2 = findViewById(androidx.appcompat.R.id.submit_area);
        this.w = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(androidx.appcompat.R.id.search_button);
        this.x = imageView;
        ImageView imageView2 = (ImageView) findViewById(androidx.appcompat.R.id.search_go_btn);
        this.y = imageView2;
        ImageView imageView3 = (ImageView) findViewById(androidx.appcompat.R.id.search_close_btn);
        this.z = imageView3;
        ImageView imageView4 = (ImageView) findViewById(androidx.appcompat.R.id.search_voice_btn);
        this.A = imageView4;
        ImageView imageView5 = (ImageView) findViewById(androidx.appcompat.R.id.search_mag_icon);
        this.H = imageView5;
        om3.d.n(viewFindViewById, tintTypedArray.e(androidx.appcompat.R.styleable.SearchView_queryBackground));
        om3.d.n(viewFindViewById2, tintTypedArray.e(androidx.appcompat.R.styleable.SearchView_submitBackground));
        int i2 = androidx.appcompat.R.styleable.SearchView_searchIcon;
        imageView.setImageDrawable(tintTypedArray.e(i2));
        imageView2.setImageDrawable(tintTypedArray.e(androidx.appcompat.R.styleable.SearchView_goIcon));
        imageView3.setImageDrawable(tintTypedArray.e(androidx.appcompat.R.styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(tintTypedArray.e(androidx.appcompat.R.styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(tintTypedArray.e(i2));
        this.I = tintTypedArray.e(androidx.appcompat.R.styleable.SearchView_searchHintIcon);
        imageView.setTooltipText(getResources().getString(androidx.appcompat.R.string.abc_searchview_description_search));
        this.f3J = tintTypedArray.i(androidx.appcompat.R.styleable.SearchView_suggestionRowLayout, androidx.appcompat.R.layout.abc_search_dropdown_item_icons_2line);
        this.K = tintTypedArray.i(androidx.appcompat.R.styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        searchAutoComplete.setOnClickListener(onClickListener);
        searchAutoComplete.addTextChangedListener(textWatcher);
        searchAutoComplete.setOnEditorActionListener(onEditorActionListener);
        searchAutoComplete.setOnItemClickListener(onItemClickListener);
        searchAutoComplete.setOnItemSelectedListener(onItemSelectedListener);
        searchAutoComplete.setOnKeyListener(onKeyListener);
        searchAutoComplete.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: androidx.appcompat.widget.SearchView.3
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SearchView searchView = SearchView.this;
                View.OnFocusChangeListener onFocusChangeListener = searchView.Q;
                if (onFocusChangeListener != null) {
                    onFocusChangeListener.onFocusChange(searchView, z);
                }
            }
        });
        setIconifiedByDefault(tintTypedArray.a(androidx.appcompat.R.styleable.SearchView_iconifiedByDefault, true));
        int iD = tintTypedArray.d(androidx.appcompat.R.styleable.SearchView_android_maxWidth, -1);
        if (iD != -1) {
            setMaxWidth(iD);
        }
        this.N = tintTypedArray.k(androidx.appcompat.R.styleable.SearchView_defaultQueryHint);
        this.a0 = tintTypedArray.k(androidx.appcompat.R.styleable.SearchView_queryHint);
        int iH = tintTypedArray.h(androidx.appcompat.R.styleable.SearchView_android_imeOptions, -1);
        if (iH != -1) {
            setImeOptions(iH);
        }
        int iH2 = tintTypedArray.h(androidx.appcompat.R.styleable.SearchView_android_inputType, -1);
        if (iH2 != -1) {
            setInputType(iH2);
        }
        setFocusable(tintTypedArray.a(androidx.appcompat.R.styleable.SearchView_android_focusable, true));
        tintTypedArray.n();
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
            viewFindViewById3.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.appcompat.widget.SearchView.4
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                    SearchView searchView = SearchView.this;
                    View view2 = searchView.B;
                    if (view2.getWidth() > 1) {
                        Resources resources = searchView.getContext().getResources();
                        int paddingLeft = searchView.v.getPaddingLeft();
                        Rect rect = new Rect();
                        boolean zA = ViewUtils.a(searchView);
                        int dimensionPixelSize = searchView.T ? resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(androidx.appcompat.R.dimen.abc_dropdownitem_icon_width) : 0;
                        SearchAutoComplete searchAutoComplete2 = searchView.t;
                        searchAutoComplete2.getDropDownBackground().getPadding(rect);
                        searchAutoComplete2.setDropDownHorizontalOffset(zA ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
                        searchAutoComplete2.setDropDownWidth((((view2.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
                    }
                }
            });
        }
        w(this.T);
        t();
    }
}
