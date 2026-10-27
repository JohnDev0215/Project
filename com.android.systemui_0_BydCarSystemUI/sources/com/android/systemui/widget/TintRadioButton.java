package com.android.systemui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.android.systemui.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class TintRadioButton extends AppCompatRadioButton implements CompoundButton.OnCheckedChangeListener {
    public static final int BOTTOM = 3;
    public static final long CHECK_INTERVAL = 10;
    public static final int CLOSE_STATE = 0;
    public static final int INVALID = -2;
    public static final int LEFT = 0;
    public static final int OPEN_STATE = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 1;
    public static final int WHAT_UPDATE_UI = 18;
    protected int drawableHeight;
    protected int drawableWidth;
    protected volatile boolean isFragmentDetach;
    protected volatile boolean isLayoutCompleted;
    protected int mColorBgCloseId;
    protected int mColorBgOpenId;
    protected int mColorCloseId;
    protected int mColorOpenId;
    protected int mDirection;
    private TintHandler mHandler;
    protected volatile boolean supportBgTint;
    protected volatile boolean supportSrcTint;
    protected int textSizeChecked;
    protected int textSizeNormal;

    public TintRadioButton(Context context) {
        super(context);
        this.mDirection = -2;
        this.mColorBgOpenId = -2;
        this.mColorBgCloseId = -2;
        this.mColorOpenId = -2;
        this.mColorCloseId = -2;
        this.textSizeNormal = -2;
        this.textSizeChecked = -2;
        this.drawableWidth = -2;
        this.drawableHeight = -2;
        this.isLayoutCompleted = false;
        this.isFragmentDetach = false;
        this.supportBgTint = true;
        this.supportSrcTint = true;
        initViews(context);
    }

    public TintRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDirection = -2;
        this.mColorBgOpenId = -2;
        this.mColorBgCloseId = -2;
        this.mColorOpenId = -2;
        this.mColorCloseId = -2;
        this.textSizeNormal = -2;
        this.textSizeChecked = -2;
        this.drawableWidth = -2;
        this.drawableHeight = -2;
        this.isLayoutCompleted = false;
        this.isFragmentDetach = false;
        this.supportBgTint = true;
        this.supportSrcTint = true;
        initAttrs(context, attrs);
        initViews(context);
    }

    public TintRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDirection = -2;
        this.mColorBgOpenId = -2;
        this.mColorBgCloseId = -2;
        this.mColorOpenId = -2;
        this.mColorCloseId = -2;
        this.textSizeNormal = -2;
        this.textSizeChecked = -2;
        this.drawableWidth = -2;
        this.drawableHeight = -2;
        this.isLayoutCompleted = false;
        this.isFragmentDetach = false;
        this.supportBgTint = true;
        this.supportSrcTint = true;
        initAttrs(context, attrs);
        initViews(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.tint_rbtn);
        this.mColorBgOpenId = typedArrayObtainStyledAttributes.getResourceId(1, this.mColorBgOpenId);
        this.mColorBgCloseId = typedArrayObtainStyledAttributes.getResourceId(0, this.mColorBgCloseId);
        this.mColorOpenId = typedArrayObtainStyledAttributes.getResourceId(3, this.mColorOpenId);
        this.mColorCloseId = typedArrayObtainStyledAttributes.getResourceId(2, this.mColorCloseId);
        this.textSizeNormal = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.textSizeNormal);
        this.textSizeChecked = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.textSizeChecked);
        this.drawableWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.drawableWidth);
        this.drawableHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.drawableHeight);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initViews(Context context) {
        setOnCheckedChangeListener(this);
        if (this.mHandler == null) {
            this.mHandler = new TintHandler();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resetCompoundDrawableSize();
        if (this.mDirection != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i = 0;
        while (true) {
            if (i >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i] != null) {
                this.mDirection = i;
                break;
            }
            i++;
        }
        this.isLayoutCompleted = true;
        int i2 = this.mColorCloseId;
        if (i2 != -2) {
            changeDrawableColor(this.mDirection, i2);
        }
    }

    private void resetCompoundDrawableSize() {
        if (this.drawableWidth == -2 || this.drawableHeight == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.drawableWidth, this.drawableHeight);
            }
        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isFragmentDetach = true;
        TintHandler tintHandler = this.mHandler;
        if (tintHandler != null) {
            tintHandler.removeCallbacksAndMessages(null);
        }
    }

    public void changeDrawableColor(int direction, int colorId) {
        Drawable compoundDrawable = getCompoundDrawable(direction);
        if (!this.supportSrcTint || colorId <= 0) {
            setCompoundDrawable(direction, compoundDrawable);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(compoundDrawable.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), colorId));
        setCompoundDrawable(direction, drawableWrap);
    }

    private Drawable getCompoundDrawable(int direction) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (direction == 0) {
            return compoundDrawables[0];
        }
        if (direction == 1) {
            return compoundDrawables[1];
        }
        if (direction == 2) {
            return compoundDrawables[2];
        }
        if (direction != 3) {
            return null;
        }
        return compoundDrawables[3];
    }

    private void setCompoundDrawable(int direction, Drawable drawable) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (direction == 0) {
            setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
            return;
        }
        if (direction == 1) {
            setCompoundDrawables(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
        } else if (direction == 2) {
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], drawable, compoundDrawables[3]);
        } else {
            if (direction != 3) {
                return;
            }
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawable);
        }
    }

    public void changeBackgroundDrawableColor(int colorId) {
        Drawable background = getBackground();
        if (!this.supportBgTint || colorId <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), colorId));
        setBackgroundDrawable(drawableWrap);
    }

    public void changeBackgroundState(int state) {
        if (state == 0) {
            changeBackgroundDrawableColor(this.mColorBgCloseId);
        } else {
            if (state != 1) {
                return;
            }
            changeBackgroundDrawableColor(this.mColorBgOpenId);
        }
    }

    public void changeState(int state) {
        if (state == 0) {
            changeDrawableColor(this.mDirection, this.mColorCloseId);
        } else {
            if (state != 1) {
                return;
            }
            changeDrawableColor(this.mDirection, this.mColorOpenId);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
        if (this.isLayoutCompleted) {
            changeStateOnLayoutCompleted(b);
        } else {
            new Thread(new Runnable() { // from class: com.android.systemui.widget.TintRadioButton.1
                @Override // java.lang.Runnable
                public void run() {
                    while (!TintRadioButton.this.isLayoutCompleted) {
                        if (TintRadioButton.this.isFragmentDetach) {
                            return;
                        } else {
                            SystemClock.sleep(10L);
                        }
                    }
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 18;
                    messageObtain.obj = Boolean.valueOf(b);
                    TintRadioButton.this.mHandler.sendMessage(messageObtain);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeStateOnLayoutCompleted(boolean b) {
        if (b) {
            changeState(1);
            changeBackgroundState(1);
            int i = this.textSizeChecked;
            if (i != -2) {
                setTextSize(0, i);
            }
        } else {
            changeState(0);
            changeBackgroundState(0);
            int i2 = this.textSizeNormal;
            if (i2 != -2) {
                setTextSize(0, i2);
            }
        }
        float textSize = getTextSize();
        Log.d("TintRadioButton", "--changeStateOnLayoutCompleted--textSizeChecked = " + this.textSizeChecked + ", textSizeNormal = " + this.textSizeNormal);
        Log.d("TintRadioButton", "--changeStateOnLayoutCompleted--b = " + b + ", textSize = " + textSize);
    }

    public void setSupportBgTint(boolean supportBgTint) {
        this.supportBgTint = supportBgTint;
    }

    public void setSupportSrcTint(boolean supportSrcTint) {
        this.supportSrcTint = supportSrcTint;
    }

    private static class TintHandler extends Handler {
        private WeakReference<TintRadioButton> ref;

        private TintHandler(TintRadioButton button) {
            this.ref = new WeakReference<>(button);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            WeakReference<TintRadioButton> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || msg.what != 18) {
                return;
            }
            this.ref.get().changeStateOnLayoutCompleted(((Boolean) msg.obj).booleanValue());
        }
    }
}
