package com.android.launcher3.dragndrop;

import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import com.android.launcher3.BaseActivity;
import com.android.launcher3.InstallShortcutReceiver;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetHost;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.R;
import com.android.launcher3.compat.AppWidgetManagerCompat;
import com.android.launcher3.compat.LauncherAppsCompatVO;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.model.WidgetItem;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.LooperExecutor;
import com.android.launcher3.views.BaseDragLayer;
import com.android.launcher3.widget.PendingAddShortcutInfo;
import com.android.launcher3.widget.PendingAddWidgetInfo;
import com.android.launcher3.widget.WidgetHostViewLoader;
import com.android.launcher3.widget.WidgetImageView;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class AddItemActivity extends BaseActivity implements View.OnLongClickListener, View.OnTouchListener {
    private static final int REQUEST_BIND_APPWIDGET = 1;
    private static final int SHADOW_SIZE = 10;
    private static final String STATE_EXTRA_WIDGET_ID = "state.widget.id";
    private LauncherAppState mApp;
    private LauncherAppWidgetHost mAppWidgetHost;
    private AppWidgetManagerCompat mAppWidgetManager;
    private InvariantDeviceProfile mIdp;
    private InstantAppResolver mInstantAppResolver;
    private int mPendingBindWidgetId;
    private LauncherApps.PinItemRequest mRequest;
    private LivePreviewWidgetCell mWidgetCell;
    private Bundle mWidgetOptions;
    private final PointF mLastTouchPos = new PointF();
    private boolean mFinishOnPause = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LauncherApps.PinItemRequest pinItemRequest = LauncherAppsCompatVO.getPinItemRequest(getIntent());
        this.mRequest = pinItemRequest;
        if (pinItemRequest == null) {
            finish();
            return;
        }
        LauncherAppState launcherAppState = LauncherAppState.getInstance(this);
        this.mApp = launcherAppState;
        this.mIdp = launcherAppState.getInvariantDeviceProfile();
        this.mInstantAppResolver = InstantAppResolver.newInstance(this);
        this.mDeviceProfile = this.mIdp.getDeviceProfile(getApplicationContext());
        setContentView(R.layout.add_item_confirmation_activity);
        this.mWidgetCell = (LivePreviewWidgetCell) findViewById(R.id.widget_cell);
        if (this.mRequest.getRequestType() == 1) {
            setupShortcut();
        } else if (!setupWidget()) {
            finish();
        }
        this.mWidgetCell.setOnTouchListener(this);
        this.mWidgetCell.setOnLongClickListener(this);
        if (savedInstanceState == null) {
            logCommand(2);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.mLastTouchPos.set(motionEvent.getX(), motionEvent.getY());
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) throws Throwable {
        WidgetImageView widgetView = this.mWidgetCell.getWidgetView();
        if (widgetView.getBitmap() == null) {
            return false;
        }
        Rect bitmapBounds = widgetView.getBitmapBounds();
        bitmapBounds.offset(widgetView.getLeft() - ((int) this.mLastTouchPos.x), widgetView.getTop() - ((int) this.mLastTouchPos.y));
        PinItemDragListener pinItemDragListener = new PinItemDragListener(this.mRequest, bitmapBounds, widgetView.getBitmap().getWidth(), widgetView.getWidth());
        Intent intentAddToIntent = pinItemDragListener.addToIntent(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).setPackage(getPackageName()).setFlags(268435456));
        pinItemDragListener.initWhenReady();
        startActivity(intentAddToIntent, ActivityOptions.makeCustomAnimation(this, 0, 17432577).toBundle());
        this.mFinishOnPause = true;
        view.startDragAndDrop(new ClipData(new ClipDescription("", new String[]{pinItemDragListener.getMimeType()}), new ClipData.Item("")), new View.DragShadowBuilder(view) { // from class: com.android.launcher3.dragndrop.AddItemActivity.1
            @Override // android.view.View.DragShadowBuilder
            public void onDrawShadow(Canvas canvas) {
            }

            @Override // android.view.View.DragShadowBuilder
            public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                outShadowSize.set(10, 10);
                outShadowTouchPoint.set(5, 5);
            }
        }, null, 256);
        return false;
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mFinishOnPause) {
            finish();
        }
    }

    private void setupShortcut() {
        final PinShortcutRequestActivityInfo pinShortcutRequestActivityInfo = new PinShortcutRequestActivityInfo(this.mRequest, this);
        this.mWidgetCell.getWidgetView().setTag(new PendingAddShortcutInfo(pinShortcutRequestActivityInfo));
        applyWidgetItemAsync(new Supplier() { // from class: com.android.launcher3.dragndrop.-$$Lambda$AddItemActivity$bo1-qDrBKioeFCeYFyUBO2o2WO8
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$setupShortcut$0$AddItemActivity(pinShortcutRequestActivityInfo);
            }
        });
    }

    public /* synthetic */ WidgetItem lambda$setupShortcut$0$AddItemActivity(PinShortcutRequestActivityInfo pinShortcutRequestActivityInfo) {
        return new WidgetItem(pinShortcutRequestActivityInfo, this.mApp.getIconCache(), getPackageManager());
    }

    private boolean setupWidget() {
        final LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfoFromProviderInfo = LauncherAppWidgetProviderInfo.fromProviderInfo(this, this.mRequest.getAppWidgetProviderInfo(this));
        if (launcherAppWidgetProviderInfoFromProviderInfo.minSpanX > this.mIdp.numColumns || launcherAppWidgetProviderInfoFromProviderInfo.minSpanY > this.mIdp.numRows) {
            return false;
        }
        this.mWidgetCell.setPreview(PinItemDragListener.getPreview(this.mRequest));
        this.mAppWidgetManager = AppWidgetManagerCompat.getInstance(this);
        this.mAppWidgetHost = new LauncherAppWidgetHost(this);
        PendingAddWidgetInfo pendingAddWidgetInfo = new PendingAddWidgetInfo(launcherAppWidgetProviderInfoFromProviderInfo);
        pendingAddWidgetInfo.spanX = Math.min(this.mIdp.numColumns, launcherAppWidgetProviderInfoFromProviderInfo.spanX);
        pendingAddWidgetInfo.spanY = Math.min(this.mIdp.numRows, launcherAppWidgetProviderInfoFromProviderInfo.spanY);
        this.mWidgetOptions = WidgetHostViewLoader.getDefaultOptionsForWidget(this, pendingAddWidgetInfo);
        this.mWidgetCell.getWidgetView().setTag(pendingAddWidgetInfo);
        applyWidgetItemAsync(new Supplier() { // from class: com.android.launcher3.dragndrop.-$$Lambda$AddItemActivity$zzXIOsV-URdlXOuGJkJSdUnutmI
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$setupWidget$1$AddItemActivity(launcherAppWidgetProviderInfoFromProviderInfo);
            }
        });
        return true;
    }

    public /* synthetic */ WidgetItem lambda$setupWidget$1$AddItemActivity(LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfo) {
        return new WidgetItem(launcherAppWidgetProviderInfo, this.mIdp, this.mApp.getIconCache());
    }

    private void applyWidgetItemAsync(final Supplier<WidgetItem> itemProvider) {
        new AsyncTask<Void, Void, WidgetItem>() { // from class: com.android.launcher3.dragndrop.AddItemActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public WidgetItem doInBackground(Void... voids) {
                return (WidgetItem) itemProvider.get();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(WidgetItem item) {
                AddItemActivity.this.mWidgetCell.applyFromCellItem(item, AddItemActivity.this.mApp.getWidgetCache());
                AddItemActivity.this.mWidgetCell.ensurePreview();
            }
        }.executeOnExecutor(new LooperExecutor(LauncherModel.getWorkerLooper()), new Void[0]);
    }

    public void onCancelClick(View v) {
        logCommand(3);
        finish();
    }

    public void onPlaceAutomaticallyClick(View v) {
        if (this.mRequest.getRequestType() == 1) {
            InstallShortcutReceiver.queueShortcut(this.mRequest.getShortcutInfo(), this);
            logCommand(4);
            this.mRequest.accept();
            finish();
            return;
        }
        int iAllocateAppWidgetId = this.mAppWidgetHost.allocateAppWidgetId();
        this.mPendingBindWidgetId = iAllocateAppWidgetId;
        if (this.mAppWidgetManager.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, this.mRequest.getAppWidgetProviderInfo(this), this.mWidgetOptions)) {
            acceptWidget(this.mPendingBindWidgetId);
        } else {
            this.mAppWidgetHost.startBindFlow(this, this.mPendingBindWidgetId, this.mRequest.getAppWidgetProviderInfo(this), 1);
        }
    }

    private void acceptWidget(int widgetId) {
        InstallShortcutReceiver.queueWidget(this.mRequest.getAppWidgetProviderInfo(this), widgetId, this);
        this.mWidgetOptions.putInt("appWidgetId", widgetId);
        this.mRequest.accept(this.mWidgetOptions);
        logCommand(4);
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        logCommand(1);
        super.onBackPressed();
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int intExtra;
        if (requestCode != 1) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (data != null) {
            intExtra = data.getIntExtra("appWidgetId", this.mPendingBindWidgetId);
        } else {
            intExtra = this.mPendingBindWidgetId;
        }
        if (resultCode == -1) {
            acceptWidget(intExtra);
        } else {
            this.mAppWidgetHost.deleteAppWidgetId(intExtra);
            this.mPendingBindWidgetId = -1;
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_EXTRA_WIDGET_ID, this.mPendingBindWidgetId);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.mPendingBindWidgetId = savedInstanceState.getInt(STATE_EXTRA_WIDGET_ID, this.mPendingBindWidgetId);
    }

    @Override // com.android.launcher3.views.ActivityContext
    public BaseDragLayer getDragLayer() {
        throw new UnsupportedOperationException();
    }

    private void logCommand(int command) {
        getUserEventDispatcher().dispatchUserEvent(LoggerUtils.newLauncherEvent(LoggerUtils.newCommandAction(command), LoggerUtils.newItemTarget(this.mWidgetCell.getWidgetView(), this.mInstantAppResolver), LoggerUtils.newContainerTarget(10)), null);
    }
}
