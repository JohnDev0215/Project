package com.google.android.setupdesign.template;

import android.os.Handler;
import android.os.Looper;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;

/* JADX INFO: loaded from: classes.dex */
public class RequireScrollMixin implements Mixin {
    private OnRequireScrollStateChangedListener listener;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean requiringScrollToBottom = false;
    private boolean everScrolledToBottom = false;

    public interface OnRequireScrollStateChangedListener {
        void onRequireScrollStateChanged(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface ScrollHandlingDelegate {
    }

    public void setScrollHandlingDelegate(ScrollHandlingDelegate scrollHandlingDelegate) {
    }

    public RequireScrollMixin(TemplateLayout templateLayout) {
    }

    void notifyScrollabilityChange(boolean z) {
        if (z == this.requiringScrollToBottom) {
            return;
        }
        if (z) {
            if (this.everScrolledToBottom) {
                return;
            }
            postScrollStateChange(true);
            this.requiringScrollToBottom = true;
            return;
        }
        postScrollStateChange(false);
        this.requiringScrollToBottom = false;
        this.everScrolledToBottom = true;
    }

    private void postScrollStateChange(final boolean z) {
        this.handler.post(new Runnable() { // from class: com.google.android.setupdesign.template.RequireScrollMixin.5
            @Override // java.lang.Runnable
            public void run() {
                if (RequireScrollMixin.this.listener != null) {
                    RequireScrollMixin.this.listener.onRequireScrollStateChanged(z);
                }
            }
        });
    }
}
