package com.android.systemui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.android.systemui.util.ReflectUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class MarqueeTextView extends AppCompatTextView {
    private Choreographer.FrameCallback mFakeRestartCallback;
    private Choreographer.FrameCallback mRealRestartCallbackObj;
    private int marqueeNum;

    public interface OnShowTextListener {
        void onComplete(int delayMillisecond);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public MarqueeTextView(Context context) {
        super(context);
        this.marqueeNum = 1;
        setAttr();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.marqueeNum = 1;
        setAttr();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.marqueeNum = 1;
        setAttr();
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        super.setText(text, type);
    }

    private void setAttr() {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setFocusableInTouchMode(true);
        setSingleLine(true);
        setMarqueeRepeatLimit(this.marqueeNum);
        try {
            Field declaredField = ReflectUtil.getDeclaredField(TextView.class, "mMarquee");
            Object objNewInstance = ReflectUtil.getDeclaredConstructor(Class.forName("android.widget.TextView$Marquee"), TextView.class).newInstance(this);
            Field declaredField2 = ReflectUtil.getDeclaredField(Class.forName("android.widget.TextView$Marquee"), "mRestartCallback");
            this.mRealRestartCallbackObj = (Choreographer.FrameCallback) declaredField2.get(objNewInstance);
            Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() { // from class: com.android.systemui.widget.MarqueeTextView.1
                @Override // android.view.Choreographer.FrameCallback
                public void doFrame(long frameTimeNanos) {
                    BydLogHelper.logD("finish a frame");
                    if (MarqueeTextView.this.mRealRestartCallbackObj != null) {
                        BydLogHelper.logD("doFrame");
                        MarqueeTextView.this.mRealRestartCallbackObj.doFrame(frameTimeNanos);
                    }
                    MarqueeTextView.this.setEllipsize(TextUtils.TruncateAt.END);
                }
            };
            this.mFakeRestartCallback = frameCallback;
            declaredField2.set(objNewInstance, frameCallback);
            declaredField.set(this, objNewInstance);
        } catch (Throwable th) {
            Log.w("MarqueeTextView", "setAttr error", th);
        }
    }
}
