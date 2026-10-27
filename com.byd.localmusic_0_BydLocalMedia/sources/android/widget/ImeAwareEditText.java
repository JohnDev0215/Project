package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes3.dex */
public class ImeAwareEditText extends EditText {
    private boolean mHasPendingShowSoftInputRequest;
    final Runnable mRunShowSoftInputIfNecessary;

    public ImeAwareEditText(Context context) {
        super(context, null);
        this.mRunShowSoftInputIfNecessary = new Runnable() { // from class: android.widget.-$$Lambda$ImeAwareEditText$eLozNZ28l7LCrAM2viVrmqyIggA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$ImeAwareEditText();
            }
        };
    }

    public ImeAwareEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRunShowSoftInputIfNecessary = new Runnable() { // from class: android.widget.-$$Lambda$ImeAwareEditText$eLozNZ28l7LCrAM2viVrmqyIggA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$ImeAwareEditText();
            }
        };
    }

    public ImeAwareEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRunShowSoftInputIfNecessary = new Runnable() { // from class: android.widget.-$$Lambda$ImeAwareEditText$eLozNZ28l7LCrAM2viVrmqyIggA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$ImeAwareEditText();
            }
        };
    }

    public ImeAwareEditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mRunShowSoftInputIfNecessary = new Runnable() { // from class: android.widget.-$$Lambda$ImeAwareEditText$eLozNZ28l7LCrAM2viVrmqyIggA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$ImeAwareEditText();
            }
        };
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (this.mHasPendingShowSoftInputRequest) {
            removeCallbacks(this.mRunShowSoftInputIfNecessary);
            post(this.mRunShowSoftInputIfNecessary);
        }
        return inputConnectionOnCreateInputConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showSoftInputIfNecessary, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$ImeAwareEditText() {
        if (this.mHasPendingShowSoftInputRequest) {
            ((InputMethodManager) getContext().getSystemService(InputMethodManager.class)).showSoftInput(this, 0);
            this.mHasPendingShowSoftInputRequest = false;
        }
    }

    public void scheduleShowSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
        if (inputMethodManager.isActive(this)) {
            this.mHasPendingShowSoftInputRequest = false;
            removeCallbacks(this.mRunShowSoftInputIfNecessary);
            inputMethodManager.showSoftInput(this, 0);
            return;
        }
        this.mHasPendingShowSoftInputRequest = true;
    }
}
