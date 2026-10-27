package com.android.launcher3;

import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.UserHandle;
import com.android.launcher3.icons.ComponentWithLabel;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppWidgetProviderInfo extends AppWidgetProviderInfo implements ComponentWithLabel {
    public static final String CLS_CUSTOM_WIDGET_PREFIX = "#custom-widget-";
    public int minSpanX;
    public int minSpanY;
    public int spanX;
    public int spanY;

    public static LauncherAppWidgetProviderInfo fromProviderInfo(Context context, AppWidgetProviderInfo info) {
        LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfo;
        if (info instanceof LauncherAppWidgetProviderInfo) {
            launcherAppWidgetProviderInfo = (LauncherAppWidgetProviderInfo) info;
        } else {
            Parcel parcelObtain = Parcel.obtain();
            info.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            launcherAppWidgetProviderInfo = new LauncherAppWidgetProviderInfo(parcelObtain);
            parcelObtain.recycle();
        }
        launcherAppWidgetProviderInfo.initSpans(context);
        return launcherAppWidgetProviderInfo;
    }

    protected LauncherAppWidgetProviderInfo() {
    }

    protected LauncherAppWidgetProviderInfo(Parcel in) {
        super(in);
    }

    public void initSpans(Context context) {
        InvariantDeviceProfile idp = LauncherAppState.getIDP(context);
        Point cellSize = idp.landscapeProfile.getCellSize();
        Point cellSize2 = idp.portraitProfile.getCellSize();
        float fMin = Math.min(cellSize.x, cellSize2.x);
        float fMin2 = Math.min(cellSize.y, cellSize2.y);
        Rect defaultPaddingForWidget = AppWidgetHostView.getDefaultPaddingForWidget(context, this.provider, null);
        this.spanX = Math.max(1, (int) Math.ceil(((this.minWidth + defaultPaddingForWidget.left) + defaultPaddingForWidget.right) / fMin));
        this.spanY = Math.max(1, (int) Math.ceil(((this.minHeight + defaultPaddingForWidget.top) + defaultPaddingForWidget.bottom) / fMin2));
        this.minSpanX = Math.max(1, (int) Math.ceil(((this.minResizeWidth + defaultPaddingForWidget.left) + defaultPaddingForWidget.right) / fMin));
        this.minSpanY = Math.max(1, (int) Math.ceil(((this.minResizeHeight + defaultPaddingForWidget.top) + defaultPaddingForWidget.bottom) / fMin2));
    }

    @Override // com.android.launcher3.icons.ComponentWithLabel
    public String getLabel(PackageManager packageManager) {
        return super.loadLabel(packageManager);
    }

    public Point getMinSpans() {
        return new Point((this.resizeMode & 1) != 0 ? this.minSpanX : -1, (this.resizeMode & 2) != 0 ? this.minSpanY : -1);
    }

    public boolean isCustomWidget() {
        return this.provider.getClassName().startsWith(CLS_CUSTOM_WIDGET_PREFIX);
    }

    public int getWidgetFeatures() {
        if (Utilities.ATLEAST_P) {
            return this.widgetFeatures;
        }
        return 0;
    }

    @Override // com.android.launcher3.icons.ComponentWithLabel
    public final ComponentName getComponent() {
        return this.provider;
    }

    @Override // com.android.launcher3.icons.ComponentWithLabel
    public final UserHandle getUser() {
        return getProfile();
    }
}
