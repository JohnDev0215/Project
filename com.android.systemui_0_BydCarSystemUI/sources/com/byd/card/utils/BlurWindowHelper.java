package com.byd.card.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.util.Consumer;
import com.android.systemui.R;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class BlurWindowHelper {
    private int mBackgroundBlurRadius;
    private int mBackgroundCornersRadius;
    private Context mContext;
    private View mView;
    private Drawable mWindowBackgroundDrawable;
    private WindowManager mWindowManager;
    private final int mWindowBackgroundAlphaWithBlur = 170;
    private final int mWindowBackgroundAlphaNoBlur = 255;

    public BlurWindowHelper(Context context) {
        this.mContext = context;
    }

    public void showWindow() {
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.window_blur, (ViewGroup) null, false);
        this.mView = viewInflate;
        viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.utils.BlurWindowHelper.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BlurWindowHelper.this.mWindowManager.removeView(BlurWindowHelper.this.mView);
            }
        });
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2, 8650792, -2);
        layoutParams.setTitle("LeapMotorNavigationBar");
        layoutParams.windowAnimations = 0;
        layoutParams.gravity = 17;
        initBlur();
        this.mWindowManager.addView(this.mView, layoutParams);
    }

    private void initBlur() {
        this.mBackgroundBlurRadius = dp2px(40.0f);
        this.mBackgroundCornersRadius = dp2px(20.0f);
        Drawable drawable = this.mContext.getDrawable(R.drawable.window_background);
        this.mWindowBackgroundDrawable = drawable;
        this.mView.setBackground(drawable);
        setupWindowBlurListener();
        updateWindowForBlurs(true);
    }

    private void setupWindowBlurListener() {
        new Consumer() { // from class: com.byd.card.utils.-$$Lambda$BlurWindowHelper$juERHfSG9VQ5jB_uy6lnjaqZudM
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.updateWindowForBlurs(((Boolean) obj).booleanValue());
            }
        };
        this.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.byd.card.utils.BlurWindowHelper.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWindowForBlurs(boolean blursEnabled) {
        this.mWindowBackgroundDrawable.setAlpha(blursEnabled ? 170 : 255);
        setBackgroundBlurRadius(this.mView);
    }

    private void setBackgroundBlurRadius(View view) {
        if (view == null) {
            return;
        }
        view.setBackground(new LayerDrawable(new Drawable[]{getBackgroundBlurDrawableByReflect(view.getParent()), view.getBackground()}));
    }

    private Drawable getBackgroundBlurDrawableByReflect(Object viewRootImpl) {
        Exception e;
        Drawable drawable;
        try {
            Method declaredMethod = viewRootImpl.getClass().getDeclaredMethod("createBackgroundBlurDrawable", new Class[0]);
            declaredMethod.setAccessible(true);
            drawable = (Drawable) declaredMethod.invoke(viewRootImpl, new Object[0]);
            try {
                Method declaredMethod2 = drawable.getClass().getDeclaredMethod("setBlurRadius", Integer.TYPE);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(drawable, Integer.valueOf(this.mBackgroundBlurRadius));
                Method declaredMethod3 = drawable.getClass().getDeclaredMethod("setCornerRadius", Float.TYPE);
                declaredMethod3.setAccessible(true);
                declaredMethod3.invoke(drawable, Integer.valueOf(this.mBackgroundCornersRadius));
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (Exception e3) {
            e = e3;
            drawable = null;
        }
        return drawable;
    }

    private int dp2px(float dpValue) {
        return (int) ((dpValue * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
