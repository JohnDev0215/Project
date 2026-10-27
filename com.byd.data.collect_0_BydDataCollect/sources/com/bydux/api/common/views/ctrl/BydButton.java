package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.ToneGenerator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import d.a.a.e;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydButton extends TextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ToneGenerator f3551d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3552e;

    public BydButton(Context context) {
        super(context);
        this.f3549b = getClass().getSimpleName();
        this.f3551d = null;
        this.f3552e = 0;
        a(context);
    }

    private void a(Context context) {
        this.f3550c = context;
        setClickable(true);
    }

    private void b(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.f3550c.obtainStyledAttributes(attributeSet, e.BydButton);
            this.f3552e = typedArrayObtainStyledAttributes.getInteger(e.BydButton_byd_button_tone, this.f3552e);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ToneGenerator toneGenerator;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (this.f3552e > 0 && this.f3551d == null) {
                ToneGenerator toneGenerator2 = new ToneGenerator(8, 100);
                this.f3551d = toneGenerator2;
                toneGenerator2.startTone(this.f3552e, 250);
            }
        } else if ((actionMasked == 1 || actionMasked == 3) && (toneGenerator = this.f3551d) != null) {
            toneGenerator.release();
            this.f3551d = null;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBtnTone(int i2) {
        this.f3552e = i2;
    }

    public BydButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3549b = getClass().getSimpleName();
        this.f3551d = null;
        this.f3552e = 0;
        a(context);
        b(attributeSet);
    }
}
