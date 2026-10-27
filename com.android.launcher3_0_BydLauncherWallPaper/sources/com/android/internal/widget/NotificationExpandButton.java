package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;

/* JADX INFO: loaded from: classes4.dex */
@RemoteViews.RemoteView
public class NotificationExpandButton extends ImageView {
    private int mOriginalNotificationColor;

    public NotificationExpandButton(Context context) {
        super(context);
    }

    public NotificationExpandButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NotificationExpandButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NotificationExpandButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    public void getBoundsOnScreen(Rect rect, boolean z) {
        super.getBoundsOnScreen(rect, z);
        extendRectToMinTouchSize(rect);
    }

    @RemotableViewMethod
    public void setOriginalNotificationColor(int i) {
        this.mOriginalNotificationColor = i;
    }

    public int getOriginalNotificationColor() {
        return this.mOriginalNotificationColor;
    }

    private void extendRectToMinTouchSize(Rect rect) {
        int i = (int) (getResources().getDisplayMetrics().density * 48.0f);
        int i2 = i / 2;
        rect.left = rect.centerX() - i2;
        rect.right = rect.left + i;
        rect.top = rect.centerY() - i2;
        rect.bottom = rect.top + i;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }
}
