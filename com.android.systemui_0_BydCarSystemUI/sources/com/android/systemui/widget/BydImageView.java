package com.android.systemui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BydImageView extends ImageView {
    private boolean clickChangeColor;
    private boolean defTint;
    private boolean ignoreTint;
    private Context mContext;
    private int mDisabledColor;
    private int mNormalColor;
    private int mTintColor;
    private int mTintColorNormal;
    private boolean showTint;

    public BydImageView(Context context) {
        this(context, null);
    }

    public BydImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BydImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BydImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mDisabledColor = R.color.color_hvac_disable;
        this.ignoreTint = false;
        this.showTint = false;
        this.defTint = false;
        this.clickChangeColor = true;
        this.mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attrs, R.styleable.BydImageView);
        this.showTint = typedArrayObtainStyledAttributes.getBoolean(5, false);
        this.mTintColor = typedArrayObtainStyledAttributes.getColor(0, ContextCompat.getColor(this.mContext, R.color.navi_bar_icon_pressed_tint));
        this.mTintColorNormal = typedArrayObtainStyledAttributes.getColor(1, 0);
        this.ignoreTint = typedArrayObtainStyledAttributes.getBoolean(4, false);
        this.defTint = typedArrayObtainStyledAttributes.getBoolean(3, false);
        this.clickChangeColor = typedArrayObtainStyledAttributes.getBoolean(2, true);
        if (this.defTint) {
            setTintColor();
        } else {
            setNormalColor();
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void setTintColor() {
        BydLogHelper.logD("setTintColor getContentDescription : " + ((Object) getContentDescription()));
        if (getContentDescription() != null && Constants.TileId.CAR_ID_7.contentEquals(getContentDescription())) {
            setColorFilter(this.mContext.getColor(R.color.color_bar_yellow_tint));
        } else {
            setColorFilter(this.mTintColor);
        }
    }

    public final void setNormalColor() {
        if (StyleUtil.isDenza()) {
            setColorFilter(this.mTintColorNormal);
        } else if (getColorFilter() != null) {
            clearColorFilter();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setSelected(boolean isSelected) {
        super.setSelected(isSelected);
        if (isSelected) {
            setTintColor();
        } else {
            setNormalColor();
        }
    }

    public void setSelectedStatus(boolean isSelected) {
        if (isSelected) {
            setTintColor();
        } else {
            setNormalColor();
        }
    }

    public void setColor(int color) {
        setColorFilter(ContextCompat.getColor(this.mContext, color));
    }

    public void setNormalColor(int mNormalColor) {
        this.mNormalColor = mNormalColor;
    }

    public void setTintColor(int mTintColor) {
        this.mTintColor = mTintColor;
    }

    public void setIgnoreTint(boolean ignoreTint) {
        this.ignoreTint = ignoreTint;
    }

    public void setShowTint(boolean showTint) {
        this.showTint = showTint;
    }

    public void setClickChangeColor(boolean clickChangeColor) {
        this.clickChangeColor = clickChangeColor;
    }

    public void setDefTint(boolean defTint) {
        this.defTint = defTint;
        if (defTint) {
            setTintColor();
            setAlpha(1.0f);
        } else {
            setNormalColor();
            setAlpha(0.85f);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.showTint = enabled;
        if (enabled) {
            setNormalColor();
            setAlpha(1.0f);
        } else {
            clearColorFilter();
            setAlpha(0.45f);
        }
    }

    public void setEnabledStatus(boolean enabled) {
        this.showTint = enabled;
        if (enabled) {
            setNormalColor();
            setAlpha(1.0f);
        } else {
            clearColorFilter();
            setAlpha(0.45f);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            if (this.showTint && this.clickChangeColor) {
                setTintColor();
            }
        } else if ((action == 1 || action == 3 || action == 4) && this.showTint && !this.ignoreTint && this.clickChangeColor) {
            setNormalColor();
        }
        return super.onTouchEvent(event);
    }
}
