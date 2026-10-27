package com.byd.card.widget;

import android.app.UiStyleConfig;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.R;
import com.byd.card.bean.Constant;
import com.byd.card.floatcard.TopActivityManager;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.DensityUtil;
import com.byd.card.utils.TransportModeManager;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes.dex */
public class TransportMode {
    private static final String TAG = "TransportMode";
    private DisplayMetrics displayMetrics;
    private final float[] lastX = {0.0f};
    private final float[] lastY = {0.0f};
    private int mCardHeight;
    private Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private ImageView mTransportModeIcon;
    private TextView mTransportModeTv;
    private View mView;
    private LinearLayout mViewBg;
    private final WindowManager mWindowManager;
    private int uiMode;

    public TransportMode(Context context) {
        this.mContext = context;
        this.uiMode = context.getResources().getConfiguration().uiMode;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.format = 1;
        layoutParams.flags = 262152;
        layoutParams.windowAnimations = 0;
        layoutParams.type = 2038;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.setTitle(TAG);
        initLocation();
        initView();
    }

    private void initView() {
        if (AppUtils.isLandSpace(this.mContext)) {
            this.mCardHeight = (int) this.mContext.getResources().getDimension(R.dimen.dp_265);
        } else {
            this.mCardHeight = (int) this.mContext.getResources().getDimension(R.dimen.dp_320);
        }
        int uiStyle = UiStyleConfig.getInstance(this.mContext).getUiStyle();
        if (uiStyle == 1) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.denza_transport_mode, (ViewGroup) null);
        } else if (uiStyle != 2) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.transport_mode, (ViewGroup) null);
        }
        this.mViewBg = (LinearLayout) this.mView.findViewById(R.id.transport_view);
        this.mTransportModeIcon = (ImageView) this.mView.findViewById(R.id.icon_transport);
        this.mTransportModeTv = (TextView) this.mView.findViewById(R.id.tv_transport);
        this.mView.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.widget.TransportMode.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == 1) {
                    TransportMode.this.lastX[0] = 0.0f;
                    TransportMode.this.lastY[0] = 0.0f;
                } else if (action == 2) {
                    if (TransportMode.this.lastX[0] == 0.0f || TransportMode.this.lastY[0] == 0.0f) {
                        TransportMode.this.lastX[0] = event.getRawX();
                        TransportMode.this.lastY[0] = event.getRawY();
                    }
                    Float fValueOf = Float.valueOf(event.getRawX());
                    Float fValueOf2 = Float.valueOf(event.getRawY());
                    int iFloatValue = (int) (fValueOf.floatValue() - TransportMode.this.lastX[0]);
                    int iFloatValue2 = (int) (fValueOf2.floatValue() - TransportMode.this.lastY[0]);
                    TransportMode.this.mLayoutParams.x += iFloatValue;
                    TransportMode.this.mLayoutParams.y += iFloatValue2;
                    if (TransportMode.this.mLayoutParams.y > (TransportMode.this.displayMetrics.heightPixels - TransportMode.this.mCardHeight) - DensityUtil.getNavigationBarHeight(TransportMode.this.mContext)) {
                        TransportMode.this.mLayoutParams.y = (TransportMode.this.displayMetrics.heightPixels - TransportMode.this.mCardHeight) - DensityUtil.getNavigationBarHeight(TransportMode.this.mContext);
                    }
                    TransportMode.this.mWindowManager.updateViewLayout(TransportMode.this.mView, TransportMode.this.mLayoutParams);
                    TransportMode.this.lastX[0] = fValueOf.floatValue();
                    TransportMode.this.lastY[0] = fValueOf2.floatValue();
                }
                return true;
            }
        });
    }

    private void initLocation() {
        this.mLayoutParams.gravity = BadgeDrawable.TOP_START;
        this.displayMetrics = this.mContext.getResources().getDisplayMetrics();
        this.mLayoutParams.x = (int) (r0.widthPixels - this.mContext.getResources().getDimension(R.dimen.dp_200));
        this.mLayoutParams.y = 0;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.mView == null) {
            initView();
            initLocation();
            addView();
        } else {
            if (this.uiMode != newConfig.uiMode) {
                Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.transport_mode_bg);
                if (drawable instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                    gradientDrawable.setColors(new int[]{this.mContext.getResources().getColor(R.color.transport_mode_start_bg), this.mContext.getResources().getColor(R.color.transport_mode_end_bg)});
                    gradientDrawable.setGradientType(0);
                    gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                    gradientDrawable.setGradientCenter(0.0f, 0.0f);
                    gradientDrawable.setGradientRadius(180.0f);
                    this.mViewBg.setBackground(gradientDrawable);
                } else {
                    this.mViewBg.setBackgroundResource(R.drawable.transport_mode_bg);
                }
                this.mTransportModeIcon.setBackgroundResource(R.mipmap.icon_transport_mode);
                this.mTransportModeTv.setTextColor(this.mContext.getResources().getColor(R.color.dialog_carplay_title));
                this.uiMode = newConfig.uiMode;
            }
            this.mTransportModeTv.setText(this.mContext.getResources().getString(R.string.transport_mode));
        }
        if (1 != TransportModeManager.getInstance().getTransportMode(this.mContext)) {
            hideView();
        } else {
            TopActivityManager.getInstance(this.mContext).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.widget.TransportMode.2
                @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
                public void onTopActivityCallback(ComponentName componentName) {
                    if (componentName == null) {
                        TransportMode.this.hideView();
                        return;
                    }
                    String className = componentName.getClassName();
                    if ("com.byd.launcherwallpaper".equals(className) || Constant.TopActivity.LAUNCHER3.equals(className) || "com.neusoft.na.navigation".equals(className) || "com.huawei.maps.auto.app".equals(className)) {
                        return;
                    }
                    TransportMode.this.hideView();
                }
            });
        }
    }

    public void addView() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.addView(this.mView, this.mLayoutParams);
        }
    }

    public void removeView() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeViewImmediate(this.mView);
        }
    }

    public void hideView() {
        View view = this.mView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mView.setVisibility(8);
    }

    public void showView() {
        View view = this.mView;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.mView.setVisibility(0);
    }

    public boolean isShow() {
        return this.mView.getVisibility() == 0;
    }
}
