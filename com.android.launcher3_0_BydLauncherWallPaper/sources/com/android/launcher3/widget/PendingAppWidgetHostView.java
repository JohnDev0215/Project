package com.android.launcher3.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.R;
import com.android.launcher3.graphics.DrawableFactory;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class PendingAppWidgetHostView extends LauncherAppWidgetHostView implements View.OnClickListener, IconCache.ItemInfoUpdateReceiver {
    private static final float MIN_SATUNATION = 0.7f;
    private static final float SETUP_ICON_SIZE_FACTOR = 0.4f;
    private Drawable mCenterDrawable;
    private View.OnClickListener mClickListener;
    private View mDefaultView;
    private final boolean mDisabledForSafeMode;
    private boolean mDrawableSizeChanged;
    private final LauncherAppWidgetInfo mInfo;
    private final TextPaint mPaint;
    private final Rect mRect;
    private Drawable mSettingIconDrawable;
    private Layout mSetupTextLayout;
    private final int mStartState;

    @Override // android.appwidget.AppWidgetHostView
    public void updateAppWidgetSize(Bundle newOptions, int minWidth, int minHeight, int maxWidth, int maxHeight) {
    }

    public PendingAppWidgetHostView(Context context, LauncherAppWidgetInfo info, IconCache cache, boolean disabledForSafeMode) {
        super(new ContextThemeWrapper(context, R.style.WidgetContainerTheme));
        this.mRect = new Rect();
        this.mInfo = info;
        this.mStartState = info.restoreStatus;
        this.mDisabledForSafeMode = disabledForSafeMode;
        TextPaint textPaint = new TextPaint();
        this.mPaint = textPaint;
        textPaint.setColor(Themes.getAttrColor(getContext(), 16842806));
        textPaint.setTextSize(TypedValue.applyDimension(0, this.mLauncher.getDeviceProfile().iconTextSizePx, getResources().getDisplayMetrics()));
        setBackgroundResource(R.drawable.pending_widget_bg);
        setWillNotDraw(false);
        setElevation(getResources().getDimension(R.dimen.pending_widget_elevation));
        updateAppWidget(null);
        setOnClickListener(ItemClickHandler.INSTANCE);
        if (info.pendingItemInfo == null) {
            info.pendingItemInfo = new PackageItemInfo(info.providerName.getPackageName());
            info.pendingItemInfo.user = info.user;
            cache.updateIconInBackground(this, info.pendingItemInfo);
            return;
        }
        reapplyItemInfo(info.pendingItemInfo);
    }

    @Override // android.appwidget.AppWidgetHostView
    protected View getDefaultView() {
        if (this.mDefaultView == null) {
            View viewInflate = this.mInflater.inflate(R.layout.appwidget_not_ready, (ViewGroup) this, false);
            this.mDefaultView = viewInflate;
            viewInflate.setOnClickListener(this);
            applyState();
        }
        return this.mDefaultView;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        this.mClickListener = l;
    }

    public boolean isReinflateIfNeeded() {
        return this.mStartState != this.mInfo.restoreStatus;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mDrawableSizeChanged = true;
    }

    @Override // com.android.launcher3.icons.IconCache.ItemInfoUpdateReceiver
    public void reapplyItemInfo(ItemInfoWithIcon info) {
        Drawable drawable = this.mCenterDrawable;
        if (drawable != null) {
            drawable.setCallback(null);
            this.mCenterDrawable = null;
        }
        if (info.iconBitmap != null) {
            DrawableFactory drawableFactoryLambda$get$0$MainThreadInitializedObject = DrawableFactory.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext());
            if (this.mDisabledForSafeMode) {
                FastBitmapDrawable fastBitmapDrawableNewIcon = drawableFactoryLambda$get$0$MainThreadInitializedObject.newIcon(getContext(), info);
                fastBitmapDrawableNewIcon.setIsDisabled(true);
                this.mCenterDrawable = fastBitmapDrawableNewIcon;
                this.mSettingIconDrawable = null;
            } else if (isReadyForClickSetup()) {
                this.mCenterDrawable = drawableFactoryLambda$get$0$MainThreadInitializedObject.newIcon(getContext(), info);
                this.mSettingIconDrawable = getResources().getDrawable(R.drawable.ic_setting).mutate();
                updateSettingColor(info.iconColor);
            } else {
                this.mCenterDrawable = DrawableFactory.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext()).newPendingIcon(getContext(), info);
                this.mSettingIconDrawable = null;
                applyState();
            }
            this.mCenterDrawable.setCallback(this);
            this.mDrawableSizeChanged = true;
        }
        invalidate();
    }

    private void updateSettingColor(int dominantColor) {
        float[] fArr = new float[3];
        Color.colorToHSV(dominantColor, fArr);
        fArr[1] = Math.min(fArr[1], MIN_SATUNATION);
        fArr[2] = 1.0f;
        this.mSettingIconDrawable.setColorFilter(Color.HSVToColor(fArr), PorterDuff.Mode.SRC_IN);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return who == this.mCenterDrawable || super.verifyDrawable(who);
    }

    public void applyState() {
        Drawable drawable = this.mCenterDrawable;
        if (drawable != null) {
            drawable.setLevel(Math.max(this.mInfo.installProgress, 0));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        View.OnClickListener onClickListener = this.mClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public boolean isReadyForClickSetup() {
        return !this.mInfo.hasRestoreFlag(2) && (this.mInfo.hasRestoreFlag(4) || this.mInfo.hasRestoreFlag(1));
    }

    private void updateDrawableBounds() {
        int i;
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.pending_widget_min_padding);
        int width = (getWidth() - paddingLeft) - paddingRight;
        int i2 = dimensionPixelSize * 2;
        int i3 = width - i2;
        int height = ((getHeight() - paddingTop) - paddingBottom) - i2;
        if (this.mSettingIconDrawable == null) {
            int iMin = Math.min(deviceProfile.iconSizePx, Math.min(i3, height));
            this.mRect.set(0, 0, iMin, iMin);
            this.mRect.offsetTo((getWidth() - this.mRect.width()) / 2, (getHeight() - this.mRect.height()) / 2);
            this.mCenterDrawable.setBounds(this.mRect);
            return;
        }
        float fMax = Math.max(0, Math.min(i3, height));
        float f = fMax * 1.8f;
        float fMax2 = Math.max(i3, height);
        if (f > fMax2) {
            fMax = fMax2 / 1.8f;
        }
        int iMin2 = (int) Math.min(fMax, deviceProfile.iconSizePx);
        int height2 = (getHeight() - iMin2) / 2;
        this.mSetupTextLayout = null;
        if (i3 > 0) {
            i = paddingTop;
            StaticLayout staticLayout = new StaticLayout(getResources().getText(R.string.gadget_setup_text), this.mPaint, i3, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
            this.mSetupTextLayout = staticLayout;
            int height3 = staticLayout.getHeight();
            if (height3 + (iMin2 * 1.8f) + deviceProfile.iconDrawablePaddingPx < height) {
                height2 = (((getHeight() - height3) - deviceProfile.iconDrawablePaddingPx) - iMin2) / 2;
            } else {
                this.mSetupTextLayout = null;
            }
        } else {
            i = paddingTop;
        }
        this.mRect.set(0, 0, iMin2, iMin2);
        this.mRect.offset((getWidth() - iMin2) / 2, height2);
        this.mCenterDrawable.setBounds(this.mRect);
        int i4 = paddingLeft + dimensionPixelSize;
        this.mRect.left = i4;
        Rect rect = this.mRect;
        int i5 = (int) (iMin2 * 0.4f);
        rect.right = rect.left + i5;
        this.mRect.top = i + dimensionPixelSize;
        Rect rect2 = this.mRect;
        rect2.bottom = rect2.top + i5;
        this.mSettingIconDrawable.setBounds(this.mRect);
        if (this.mSetupTextLayout != null) {
            this.mRect.left = i4;
            this.mRect.top = this.mCenterDrawable.getBounds().bottom + deviceProfile.iconDrawablePaddingPx;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mCenterDrawable == null) {
            return;
        }
        if (this.mDrawableSizeChanged) {
            updateDrawableBounds();
            this.mDrawableSizeChanged = false;
        }
        this.mCenterDrawable.draw(canvas);
        Drawable drawable = this.mSettingIconDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        if (this.mSetupTextLayout != null) {
            canvas.save();
            canvas.translate(this.mRect.left, this.mRect.top);
            this.mSetupTextLayout.draw(canvas);
            canvas.restore();
        }
    }
}
