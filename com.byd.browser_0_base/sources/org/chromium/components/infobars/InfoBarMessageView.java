package org.chromium.components.infobars;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: loaded from: classes2.dex */
public class InfoBarMessageView extends TextViewWithClickableSpans {
    public boolean g;

    public InfoBarMessageView(Context context) {
        super(context);
    }

    @Override // org.chromium.ui.widget.TextViewWithClickableSpans, android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (!this.g && motionEvent.getActionMasked() == 1) {
            long eventTime = motionEvent.getEventTime() - motionEvent.getDownTime();
            boolean z = eventTime >= ((long) ViewConfiguration.getTapTimeout()) && eventTime <= ((long) ViewConfiguration.getLongPressTimeout());
            ClickableSpan[] clickableSpans = getClickableSpans();
            if (z && clickableSpans != null && clickableSpans.length == 1 && !c(motionEvent)) {
                clickableSpans[0].onClick(this);
            }
        }
        return zOnTouchEvent;
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        if (onClickListener != null) {
            this.g = true;
        }
    }

    public InfoBarMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
