package com.byd.card.widget;

import android.app.UiStyleConfig;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class BlurView extends RelativeLayout {
    private RoundRectImageView ivBlur;
    private RoundRectImageView ivMask;
    private RelativeLayout mBlurViewRL;

    public BlurView(Context context) {
        super(context);
        init(context);
    }

    public BlurView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BlurView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BlurView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        if (uiStyle == 1) {
            inflate(context, R.layout.denza_blur_view, this);
        } else if (uiStyle != 2) {
            inflate(context, R.layout.blur_view, this);
        }
        this.mBlurViewRL = (RelativeLayout) findViewById(R.id.global_screenshot_action_chip);
        this.ivBlur = (RoundRectImageView) findViewById(R.id.ivBlur);
        this.ivMask = (RoundRectImageView) findViewById(R.id.ivMask);
    }

    public void setBlurViewLayout(int width, int height, int marginStart) {
        RelativeLayout relativeLayout = this.mBlurViewRL;
        if (relativeLayout != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            layoutParams.leftMargin = marginStart;
            this.mBlurViewRL.setLayoutParams(layoutParams);
        }
    }

    public void setBlurViewRadius(int radius) {
        this.ivBlur.setRadius(radius);
        this.ivMask.setRadius(radius);
    }

    public void setBlurView(Bitmap bitmap) {
        this.ivBlur.setImageBitmap(bitmap);
    }

    public void setMaskAlpha(float alpha) {
        this.ivMask.setAlpha(alpha);
    }

    public void setBlurBackgroundColor(int color) {
        this.ivBlur.setBackgroundColor(color);
    }

    public void setMaskBackgroundColor(int color) {
        this.ivMask.setBackground(new ColorDrawable(color));
    }

    public void setMaskVisibly(int visibility) {
        this.ivMask.setVisibility(visibility);
    }
}
