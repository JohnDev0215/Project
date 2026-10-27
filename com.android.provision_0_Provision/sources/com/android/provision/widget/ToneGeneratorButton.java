package com.android.provision.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.ToneGenerator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import com.android.provision.R;

/* JADX INFO: loaded from: classes.dex */
public class ToneGeneratorButton extends Button {
    private final String TAG;
    private Context mContext;
    private int mTone;
    private ToneGenerator mToneGenerator;

    public void setBtnTone(int tone) {
        this.mTone = tone;
    }

    public ToneGeneratorButton(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mToneGenerator = null;
        this.mTone = 0;
        initView(context);
    }

    public ToneGeneratorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.TAG = getClass().getSimpleName();
        this.mToneGenerator = null;
        this.mTone = 0;
        initView(context);
        loadAttrs(attrs);
    }

    private void initView(Context context) {
        this.mContext = context;
        setClickable(true);
    }

    private void loadAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attrs, R.styleable.BydButton);
            this.mTone = typedArrayObtainStyledAttributes.getInteger(0, this.mTone);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        ToneGenerator toneGenerator;
        int actionMasked = event.getActionMasked();
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
        return super.onTouchEvent(event);
    }
}
