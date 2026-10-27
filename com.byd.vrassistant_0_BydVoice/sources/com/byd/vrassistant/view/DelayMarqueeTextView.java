package com.byd.vrassistant.view;

import android.R;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DelayMarqueeTextView extends AppCompatTextView {
    private static final String TAG = "DelayMarqueeTextView";
    Handler handler;
    Runnable setMarquee;

    @Override // android.view.View
    public int getFocusable() {
        return 1;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public DelayMarqueeTextView(Context context) {
        this(context, null);
    }

    public DelayMarqueeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public DelayMarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler();
        this.setMarquee = new Runnable() { // from class: com.byd.vrassistant.view.DelayMarqueeTextView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m189lambda$new$0$combydvrassistantviewDelayMarqueeTextView();
            }
        };
        setSingleLine(true);
        setDefaultFocusHighlightEnabled(false);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow: ");
        this.handler.postDelayed(this.setMarquee, 500L);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.handler.removeCallbacks(this.setMarquee);
        setEllipsize(TextUtils.TruncateAt.END);
        setFocusable(false);
        setFocusableInTouchMode(false);
        setMarqueeRepeatLimit(-1);
        setHorizontallyScrolling(false);
        setSelected(false);
    }

    /* JADX INFO: renamed from: lambda$new$0$com-byd-vrassistant-view-DelayMarqueeTextView, reason: not valid java name */
    /* synthetic */ void m189lambda$new$0$combydvrassistantviewDelayMarqueeTextView() {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setMarqueeRepeatLimit(-1);
        setHorizontallyScrolling(true);
        setSelected(true);
    }
}
