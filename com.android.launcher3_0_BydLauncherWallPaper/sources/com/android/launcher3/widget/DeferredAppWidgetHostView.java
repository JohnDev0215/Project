package com.android.launcher3.widget;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.RemoteViews;
import com.android.launcher3.R;

/* JADX INFO: loaded from: classes4.dex */
public class DeferredAppWidgetHostView extends LauncherAppWidgetHostView {
    private final TextPaint mPaint;
    private Layout mSetupTextLayout;

    @Override // com.android.launcher3.widget.LauncherAppWidgetHostView, android.appwidget.AppWidgetHostView
    public void updateAppWidget(RemoteViews remoteViews) {
    }

    public DeferredAppWidgetHostView(Context context) {
        super(context);
        setWillNotDraw(false);
        TextPaint textPaint = new TextPaint();
        this.mPaint = textPaint;
        textPaint.setColor(-1);
        textPaint.setTextSize(TypedValue.applyDimension(0, this.mLauncher.getDeviceProfile().getFullScreenProfile().iconTextSizePx, getResources().getDisplayMetrics()));
        setBackgroundResource(R.drawable.bg_deferred_app_widget);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        AppWidgetProviderInfo appWidgetInfo = getAppWidgetInfo();
        if (appWidgetInfo == null || TextUtils.isEmpty(appWidgetInfo.label)) {
            return;
        }
        int measuredWidth = getMeasuredWidth() - ((getPaddingLeft() + getPaddingRight()) * 2);
        Layout layout = this.mSetupTextLayout;
        if (layout != null && layout.getText().equals(appWidgetInfo.label) && this.mSetupTextLayout.getWidth() == measuredWidth) {
            return;
        }
        this.mSetupTextLayout = new StaticLayout(appWidgetInfo.label, this.mPaint, measuredWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mSetupTextLayout != null) {
            canvas.translate(getPaddingLeft() * 2, (getHeight() - this.mSetupTextLayout.getHeight()) / 2);
            this.mSetupTextLayout.draw(canvas);
        }
    }
}
