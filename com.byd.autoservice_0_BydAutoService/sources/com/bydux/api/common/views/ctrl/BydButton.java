package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.ToneGenerator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import com.bydux.api.R;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydButton extends TextView {
    private final String TAG;
    private Context mContext;
    private int mTone;
    private ToneGenerator mToneGenerator;

    public BydButton(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mToneGenerator = null;
        this.mTone = 0;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        setClickable(true);
    }

    private void loadAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.BydButton);
            this.mTone = typedArrayObtainStyledAttributes.getInteger(R.styleable.BydButton_byd_button_tone, this.mTone);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ToneGenerator toneGenerator;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (this.mTone > 0 && this.mToneGenerator == null) {
                ToneGenerator toneGenerator2 = new ToneGenerator(8, 100);
                this.mToneGenerator = toneGenerator2;
                toneGenerator2.startTone(this.mTone, 250);
            }
        } else if ((actionMasked == 1 || actionMasked == 3) && (toneGenerator = this.mToneGenerator) != null) {
            toneGenerator.release();
            this.mToneGenerator = null;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBtnTone(int i3) {
        this.mTone = i3;
    }

    public BydButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.mToneGenerator = null;
        this.mTone = 0;
        initView(context);
        loadAttrs(attributeSet);
    }
}
