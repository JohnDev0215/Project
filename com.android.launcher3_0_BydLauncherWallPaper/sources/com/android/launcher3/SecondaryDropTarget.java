package com.android.launcher3;

import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Launcher;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.Themes;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes4.dex */
public class SecondaryDropTarget extends ButtonDropTarget implements OnAlarmListener {
    private static final long CACHE_EXPIRE_TIMEOUT = 5000;
    private static final String TAG = "SecondaryDropTarget";
    private final Alarm mCacheExpireAlarm;
    protected int mCurrentAccessibilityAction;
    private final ArrayMap<UserHandle, Boolean> mUninstallDisabledCache;

    public SecondaryDropTarget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondaryDropTarget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mUninstallDisabledCache = new ArrayMap<>(1);
        this.mCurrentAccessibilityAction = -1;
        Alarm alarm = new Alarm();
        this.mCacheExpireAlarm = alarm;
        alarm.setOnAlarmListener(this);
    }

    @Override // com.android.launcher3.ButtonDropTarget, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setupUi(R.id.action_uninstall);
    }

    protected void setupUi(int action) {
        if (action == this.mCurrentAccessibilityAction) {
            return;
        }
        this.mCurrentAccessibilityAction = action;
        if (action == R.id.action_uninstall) {
            this.mHoverColor = getResources().getColor(R.color.uninstall_target_hover_tint);
            setDrawable(R.drawable.ic_uninstall_shadow);
            updateText(R.string.uninstall_drop_target_label);
        } else {
            this.mHoverColor = Themes.getColorAccent(getContext());
            setDrawable(R.drawable.ic_setup_shadow);
            updateText(R.string.gadget_setup_text);
        }
    }

    @Override // com.android.launcher3.OnAlarmListener
    public void onAlarm(Alarm alarm) {
        this.mUninstallDisabledCache.clear();
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public int getAccessibilityAction() {
        return this.mCurrentAccessibilityAction;
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public LauncherLogProto.Target getDropTargetForLogging() {
        LauncherLogProto.Target targetNewTarget = LoggerUtils.newTarget(2);
        targetNewTarget.controlType = this.mCurrentAccessibilityAction == R.id.action_uninstall ? 6 : 4;
        return targetNewTarget;
    }

    @Override // com.android.launcher3.ButtonDropTarget
    protected boolean supportsDrop(ItemInfo info) {
        return supportsAccessibilityDrop(info, getViewUnderDrag(info));
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public boolean supportsAccessibilityDrop(ItemInfo info, View view) {
        if (view instanceof AppWidgetHostView) {
            if (getReconfigurableWidgetId(view) == 0) {
                return false;
            }
            setupUi(R.id.action_reconfigure);
            return true;
        }
        setupUi(R.id.action_uninstall);
        Boolean boolValueOf = this.mUninstallDisabledCache.get(info.user);
        if (boolValueOf == null) {
            Bundle userRestrictions = ((UserManager) getContext().getSystemService("user")).getUserRestrictions(info.user);
            boolValueOf = Boolean.valueOf(userRestrictions.getBoolean(UserManager.DISALLOW_APPS_CONTROL, false) || userRestrictions.getBoolean(UserManager.DISALLOW_UNINSTALL_APPS, false));
            this.mUninstallDisabledCache.put(info.user, boolValueOf);
        }
        this.mCacheExpireAlarm.setAlarm(5000L);
        if (boolValueOf.booleanValue()) {
            return false;
        }
        if (info instanceof ItemInfoWithIcon) {
            ItemInfoWithIcon itemInfoWithIcon = (ItemInfoWithIcon) info;
            if ((itemInfoWithIcon.runtimeStatusFlags & 192) != 0) {
                return (itemInfoWithIcon.runtimeStatusFlags & 128) != 0;
            }
        }
        return getUninstallTarget(info) != null;
    }

    private ComponentName getUninstallTarget(ItemInfo item) {
        UserHandle userHandle;
        Intent intent;
        LauncherActivityInfo launcherActivityInfoResolveActivity;
        if (item == null || item.itemType != 0) {
            userHandle = null;
            intent = null;
        } else {
            intent = item.getIntent();
            userHandle = item.user;
        }
        if (intent == null || (launcherActivityInfoResolveActivity = LauncherAppsCompat.getInstance(this.mLauncher).resolveActivity(intent, userHandle)) == null || (launcherActivityInfoResolveActivity.getApplicationInfo().flags & 1) != 0) {
            return null;
        }
        return launcherActivityInfoResolveActivity.getComponentName();
    }

    @Override // com.android.launcher3.ButtonDropTarget, com.android.launcher3.DropTarget
    public void onDrop(DropTarget.DragObject d, DragOptions options) {
        d.dragSource = new DeferredOnComplete(d.dragSource, getContext());
        super.onDrop(d, options);
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public void completeDrop(final DropTarget.DragObject d) {
        ComponentName componentNamePerformDropAction = performDropAction(getViewUnderDrag(d.dragInfo), d.dragInfo);
        if (d.dragSource instanceof DeferredOnComplete) {
            DeferredOnComplete deferredOnComplete = (DeferredOnComplete) d.dragSource;
            if (componentNamePerformDropAction == null) {
                deferredOnComplete.sendFailure();
            } else {
                deferredOnComplete.mPackageName = componentNamePerformDropAction.getPackageName();
                this.mLauncher.addOnResumeCallback(deferredOnComplete);
            }
        }
    }

    private View getViewUnderDrag(ItemInfo info) {
        if ((info instanceof LauncherAppWidgetInfo) && info.container == -100 && this.mLauncher.getWorkspace().getDragInfo() != null) {
            return this.mLauncher.getWorkspace().getDragInfo().cell;
        }
        return null;
    }

    private int getReconfigurableWidgetId(View view) {
        AppWidgetHostView appWidgetHostView;
        AppWidgetProviderInfo appWidgetInfo;
        if (!(view instanceof AppWidgetHostView) || (appWidgetInfo = (appWidgetHostView = (AppWidgetHostView) view).getAppWidgetInfo()) == null || appWidgetInfo.configure == null || (LauncherAppWidgetProviderInfo.fromProviderInfo(getContext(), appWidgetInfo).getWidgetFeatures() & 1) == 0) {
            return 0;
        }
        return appWidgetHostView.getAppWidgetId();
    }

    protected ComponentName performDropAction(View view, ItemInfo info) {
        if (this.mCurrentAccessibilityAction == R.id.action_reconfigure) {
            int reconfigurableWidgetId = getReconfigurableWidgetId(view);
            if (reconfigurableWidgetId != 0) {
                this.mLauncher.getAppWidgetHost().startConfigActivity(this.mLauncher, reconfigurableWidgetId, -1);
            }
            return null;
        }
        ComponentName uninstallTarget = getUninstallTarget(info);
        if (uninstallTarget == null) {
            Toast.makeText(this.mLauncher, R.string.uninstall_system_app_text, 0).show();
            return null;
        }
        try {
            this.mLauncher.startActivity(Intent.parseUri(this.mLauncher.getString(R.string.delete_package_intent), 0).setData(Uri.fromParts("package", uninstallTarget.getPackageName(), uninstallTarget.getClassName())).putExtra(Intent.EXTRA_USER, info.user));
            return uninstallTarget;
        } catch (URISyntaxException unused) {
            Log.e(TAG, "Failed to parse intent to start uninstall activity for item=" + info);
            return null;
        }
    }

    @Override // com.android.launcher3.ButtonDropTarget
    public void onAccessibilityDrop(View view, ItemInfo item) {
        performDropAction(view, item);
    }

    private class DeferredOnComplete implements DragSource, Launcher.OnResumeCallback {
        private final Context mContext;
        private DropTarget.DragObject mDragObject;
        private final DragSource mOriginal;
        private String mPackageName;

        public DeferredOnComplete(DragSource original, Context context) {
            this.mOriginal = original;
            this.mContext = context;
        }

        @Override // com.android.launcher3.DragSource
        public void onDropCompleted(View target, DropTarget.DragObject d, boolean success) {
            this.mDragObject = d;
        }

        @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
        public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
            this.mOriginal.fillInLogContainerData(v, info, target, targetParent);
        }

        @Override // com.android.launcher3.Launcher.OnResumeCallback
        public void onLauncherResume() {
            if (LauncherAppsCompat.getInstance(this.mContext).getApplicationInfo(this.mPackageName, 8192, this.mDragObject.dragInfo.user) == null) {
                this.mDragObject.dragSource = this.mOriginal;
                this.mOriginal.onDropCompleted(SecondaryDropTarget.this, this.mDragObject, true);
                return;
            }
            sendFailure();
        }

        public void sendFailure() {
            this.mDragObject.dragSource = this.mOriginal;
            this.mDragObject.cancelled = true;
            this.mOriginal.onDropCompleted(SecondaryDropTarget.this, this.mDragObject, false);
        }
    }
}
