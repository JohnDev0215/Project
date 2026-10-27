package androidx.appcompat.widget;

import B5.RunnableC0079c;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import r.AbstractC2254m;
import r.p0;

/* JADX INFO: loaded from: classes.dex */
public class SearchView$SearchAutoComplete extends AbstractC2254m {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f11348p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f11349q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final RunnableC0079c f11350r;

    public SearchView$SearchAutoComplete(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11350r = new RunnableC0079c(this, 28);
        this.f11348p = getThreshold();
    }

    private int getSearchViewTextMinWidthDp() {
        Configuration configuration = getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        int i10 = configuration.screenHeightDp;
        if (i7 >= 960 && i10 >= 720 && configuration.orientation == 2) {
            return 256;
        }
        if (i7 < 600) {
            return (i7 < 640 || i10 < 480) ? 160 : 192;
        }
        return 192;
    }

    @Override // android.widget.AutoCompleteTextView
    public final boolean enoughToFilter() {
        return this.f11348p <= 0 || super.enoughToFilter();
    }

    @Override // r.AbstractC2254m, android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (this.f11349q) {
            RunnableC0079c runnableC0079c = this.f11350r;
            removeCallbacks(runnableC0079c);
            post(runnableC0079c);
        }
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        throw null;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i7, KeyEvent keyEvent) {
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
                    throw null;
                }
            }
        }
        return super.onKeyPreIme(i7, keyEvent);
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            throw null;
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void performCompletion() {
    }

    @Override // android.widget.AutoCompleteTextView
    public final void replaceText(CharSequence charSequence) {
    }

    public void setImeVisibility(boolean z6) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        RunnableC0079c runnableC0079c = this.f11350r;
        if (!z6) {
            this.f11349q = false;
            removeCallbacks(runnableC0079c);
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        } else {
            if (!inputMethodManager.isActive(this)) {
                this.f11349q = true;
                return;
            }
            this.f11349q = false;
            removeCallbacks(runnableC0079c);
            inputMethodManager.showSoftInput(this, 0);
        }
    }

    public void setSearchView(p0 p0Var) {
    }

    @Override // android.widget.AutoCompleteTextView
    public void setThreshold(int i7) {
        super.setThreshold(i7);
        this.f11348p = i7;
    }
}
