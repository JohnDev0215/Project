package org.chromium.chrome.browser.content_creation.notes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class LineLimitedTextView extends TextView {
    public boolean e;
    public Runnable f;

    public LineLimitedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView, android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        int height = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / getLineHeight();
        setMaxLines(height);
        if (!this.e && getLayout().getLineCount() > height) {
            this.e = true;
            Runnable runnable = this.f;
            if (runnable != null) {
                runnable.run();
            }
        }
        return true;
    }

    public void setIsEllipsizedListener(Runnable runnable) {
        this.f = runnable;
    }
}
