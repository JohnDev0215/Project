package com.android.launcher3.dragndrop;

import android.appwidget.AppWidgetManager;
import android.content.pm.LauncherApps;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.DragEvent;
import android.view.View;
import android.widget.RemoteViews;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.uioverrides.UiFactory;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.widget.PendingAddShortcutInfo;
import com.android.launcher3.widget.PendingAddWidgetInfo;
import com.android.launcher3.widget.PendingItemDragHelper;
import com.android.launcher3.widget.WidgetAddFlowHandler;

/* JADX INFO: loaded from: classes4.dex */
public class PinItemDragListener extends BaseItemDragListener {
    private final CancellationSignal mCancelSignal;
    private final LauncherApps.PinItemRequest mRequest;

    public PinItemDragListener(LauncherApps.PinItemRequest request, Rect previewRect, int previewBitmapWidth, int previewViewWidth) {
        super(previewRect, previewBitmapWidth, previewViewWidth);
        this.mRequest = request;
        this.mCancelSignal = new CancellationSignal();
    }

    @Override // com.android.launcher3.dragndrop.BaseItemDragListener
    protected boolean onDragStart(DragEvent event) {
        if (this.mRequest.isValid()) {
            return super.onDragStart(event);
        }
        return false;
    }

    @Override // com.android.launcher3.dragndrop.BaseItemDragListener, com.android.launcher3.states.InternalStateHandler
    public boolean init(Launcher launcher, boolean alreadyOnHome) {
        super.init(launcher, alreadyOnHome);
        if (alreadyOnHome) {
            return false;
        }
        UiFactory.useFadeOutAnimationForLauncherStart(launcher, this.mCancelSignal);
        return false;
    }

    @Override // com.android.launcher3.dragndrop.BaseItemDragListener
    protected PendingItemDragHelper createDragHelper() {
        Object pendingAddShortcutInfo;
        if (this.mRequest.getRequestType() == 1) {
            pendingAddShortcutInfo = new PendingAddShortcutInfo(new PinShortcutRequestActivityInfo(this.mRequest, this.mLauncher));
        } else {
            LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfoFromProviderInfo = LauncherAppWidgetProviderInfo.fromProviderInfo(this.mLauncher, this.mRequest.getAppWidgetProviderInfo(this.mLauncher));
            final PinWidgetFlowHandler pinWidgetFlowHandler = new PinWidgetFlowHandler(launcherAppWidgetProviderInfoFromProviderInfo, this.mRequest);
            pendingAddShortcutInfo = new PendingAddWidgetInfo(launcherAppWidgetProviderInfoFromProviderInfo) { // from class: com.android.launcher3.dragndrop.PinItemDragListener.1
                @Override // com.android.launcher3.widget.PendingAddWidgetInfo
                public WidgetAddFlowHandler getHandler() {
                    return pinWidgetFlowHandler;
                }
            };
        }
        View view = new View(this.mLauncher);
        view.setTag(pendingAddShortcutInfo);
        PendingItemDragHelper pendingItemDragHelper = new PendingItemDragHelper(view);
        if (this.mRequest.getRequestType() == 2) {
            pendingItemDragHelper.setPreview(getPreview(this.mRequest));
        }
        return pendingItemDragHelper;
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        targetParent.containerType = 10;
    }

    @Override // com.android.launcher3.dragndrop.BaseItemDragListener
    protected void postCleanup() {
        super.postCleanup();
        this.mCancelSignal.cancel();
    }

    public static RemoteViews getPreview(LauncherApps.PinItemRequest request) {
        Bundle extras = request.getExtras();
        if (extras == null || !(extras.get(AppWidgetManager.EXTRA_APPWIDGET_PREVIEW) instanceof RemoteViews)) {
            return null;
        }
        return (RemoteViews) extras.get(AppWidgetManager.EXTRA_APPWIDGET_PREVIEW);
    }
}
