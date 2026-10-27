package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;

/* JADX INFO: loaded from: classes.dex */
public abstract class KeyButtonView extends RelativeLayout {
    protected View btn_bg;
    protected ImageView img_btn;
    private int mTintColor;

    public abstract int getImageResId();

    public KeyButtonView(Context context) {
        super(context);
        this.mTintColor = R.color.navi_bar_btn_pressed_tint;
        initView();
    }

    public KeyButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTintColor = R.color.navi_bar_btn_pressed_tint;
        initView();
    }

    public KeyButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTintColor = R.color.navi_bar_btn_pressed_tint;
        initView();
    }

    protected final void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(StyleUtil.getResLayout(R.layout.view_navi_bar_icon, "view_navi_bar_icon"), (ViewGroup) this, false);
        this.btn_bg = viewInflate.findViewById(R.id.btn_bg);
        this.img_btn = (ImageView) viewInflate.findViewById(R.id.img_btn);
        addView(viewInflate);
        setBydThemeView();
    }

    public void setTintColor() {
        ImageView imageView = this.img_btn;
        if (imageView != null) {
            imageView.setColorFilter(ContextCompat.getColor(getContext(), this.mTintColor));
        }
    }

    public void setNormalColor() {
        if (this.img_btn != null) {
            if (StyleUtil.isDenza()) {
                this.img_btn.setColorFilter(ContextCompat.getColor(getContext(), R.color.denza_bg_navi_btn_normal_tint));
            } else if (this.img_btn.getColorFilter() != null) {
                this.img_btn.clearColorFilter();
            }
        }
    }

    public final void setBydThemeView() {
        if (this.img_btn == null || getImageResId() == 0) {
            return;
        }
        this.img_btn.setImageResource(getImageResId());
        setNormalColor();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1 && event.getY() < 0.0f) {
            setPressed(false);
            View view = this.btn_bg;
            if (view != null) {
                view.setPressed(false);
            }
            setNormalColor();
            return false;
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            View view2 = this.btn_bg;
            if (view2 != null) {
                view2.setPressed(true);
            }
            setTintColor();
        } else if (actionMasked == 1 || actionMasked == 3 || actionMasked == 4) {
            View view3 = this.btn_bg;
            if (view3 != null) {
                view3.setPressed(false);
            }
            setNormalColor();
        }
        return super.onTouchEvent(event);
    }
}
