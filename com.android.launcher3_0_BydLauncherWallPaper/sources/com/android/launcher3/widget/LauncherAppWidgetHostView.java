package com.android.launcher3.widget;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PointF;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.Advanceable;
import android.widget.RemoteViews;
import com.android.launcher3.CheckLongPressHelper;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.R;
import com.android.launcher3.SimpleOnStylusPressListener;
import com.android.launcher3.StylusEventHelper;
import com.android.launcher3.Utilities;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.BaseDragLayer;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppWidgetHostView extends NavigableAppWidgetHostView implements BaseDragLayer.TouchCompleteListener, View.OnLongClickListener {
    private static final long ADVANCE_INTERVAL = 20000;
    private static final long ADVANCE_STAGGER = 250;
    private static final SparseBooleanArray sAutoAdvanceWidgetIds = new SparseBooleanArray();
    private Runnable mAutoAdvanceRunnable;
    protected final LayoutInflater mInflater;
    private boolean mIsAttachedToWindow;
    private boolean mIsAutoAdvanceRegistered;
    private boolean mIsScrollable;
    protected final Launcher mLauncher;
    private final CheckLongPressHelper mLongPressHelper;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mReinflateOnConfigChange;
    private float mScaleToFit;
    private float mSlop;
    private final StylusEventHelper mStylusEventHelper;
    private final PointF mTranslationForCentering;

    public LauncherAppWidgetHostView(Context context) {
        super(context);
        this.mScaleToFit = 1.0f;
        this.mTranslationForCentering = new PointF(0.0f, 0.0f);
        Launcher launcher = Launcher.getLauncher(context);
        this.mLauncher = launcher;
        this.mLongPressHelper = new CheckLongPressHelper(this, this);
        this.mStylusEventHelper = new StylusEventHelper(new SimpleOnStylusPressListener(this), this);
        this.mInflater = LayoutInflater.from(context);
        setAccessibilityDelegate(launcher.getAccessibilityDelegate());
        setBackgroundResource(R.drawable.widget_internal_focus_bg);
        if (Utilities.ATLEAST_OREO) {
            setExecutor(Utilities.THREAD_POOL_EXECUTOR);
        }
        if (Utilities.ATLEAST_Q && Themes.getAttrBoolean(launcher, R.attr.isWorkspaceDarkText)) {
            setOnLightBackground(true);
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (this.mIsScrollable) {
            Launcher.getLauncher(getContext()).getDragLayer().requestDisallowInterceptTouchEvent(false);
        }
        view.performLongClick();
        return true;
    }

    @Override // android.appwidget.AppWidgetHostView
    protected View getErrorView() {
        return this.mInflater.inflate(R.layout.appwidget_error, (ViewGroup) this, false);
    }

    @Override // android.appwidget.AppWidgetHostView
    public void updateAppWidget(RemoteViews remoteViews) {
        super.updateAppWidget(remoteViews);
        checkIfAutoAdvance();
        this.mReinflateOnConfigChange = !isSameOrientation();
    }

    private boolean isSameOrientation() {
        return this.mLauncher.getResources().getConfiguration().orientation == this.mLauncher.getOrientation();
    }

    private boolean checkScrollableRecursively(ViewGroup viewGroup) {
        if (viewGroup instanceof AdapterView) {
            return true;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ViewGroup) && checkScrollableRecursively((ViewGroup) childAt)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mLongPressHelper.cancelLongPress();
        }
        if (this.mLongPressHelper.hasPerformedLongPress()) {
            this.mLongPressHelper.cancelLongPress();
            return true;
        }
        if (this.mStylusEventHelper.onMotionEvent(ev)) {
            this.mLongPressHelper.cancelLongPress();
            return true;
        }
        int action = ev.getAction();
        if (action == 0) {
            DragLayer dragLayer = Launcher.getLauncher(getContext()).getDragLayer();
            if (this.mIsScrollable) {
                dragLayer.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.mStylusEventHelper.inStylusButtonPressed()) {
                this.mLongPressHelper.postCheckForLongPress();
            }
            dragLayer.setTouchCompleteListener(this);
            return false;
        }
        if (action != 1) {
            if (action == 2) {
                if (Utilities.pointInView(this, ev.getX(), ev.getY(), this.mSlop)) {
                    return false;
                }
                this.mLongPressHelper.cancelLongPress();
                return false;
            }
            if (action != 3) {
                return false;
            }
        }
        this.mLongPressHelper.cancelLongPress();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == r1) goto L24
            r2 = 2
            if (r0 == r2) goto Le
            r4 = 3
            if (r0 == r4) goto L24
            goto L29
        Le:
            float r0 = r4.getX()
            float r4 = r4.getY()
            float r2 = r3.mSlop
            boolean r4 = com.android.launcher3.Utilities.pointInView(r3, r0, r4, r2)
            if (r4 != 0) goto L29
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
            goto L29
        L24:
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
        L29:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.widget.LauncherAppWidgetHostView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mIsAttachedToWindow = true;
        checkIfAutoAdvance();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        checkIfAutoAdvance();
    }

    @Override // android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        this.mLongPressHelper.cancelLongPress();
    }

    @Override // android.appwidget.AppWidgetHostView
    public AppWidgetProviderInfo getAppWidgetInfo() {
        AppWidgetProviderInfo appWidgetInfo = super.getAppWidgetInfo();
        if (appWidgetInfo == null || (appWidgetInfo instanceof LauncherAppWidgetProviderInfo)) {
            return appWidgetInfo;
        }
        throw new IllegalStateException("Launcher widget must have LauncherAppWidgetProviderInfo");
    }

    @Override // com.android.launcher3.views.BaseDragLayer.TouchCompleteListener
    public void onTouchComplete() {
        if (this.mLongPressHelper.hasPerformedLongPress()) {
            return;
        }
        this.mLongPressHelper.cancelLongPress();
    }

    public void switchToErrorView() {
        updateAppWidget(new RemoteViews(getAppWidgetInfo().provider.getPackageName(), 0));
    }

    @Override // android.appwidget.AppWidgetHostView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        try {
            super.onLayout(changed, left, top, right, bottom);
        } catch (RuntimeException unused) {
            post(new Runnable() { // from class: com.android.launcher3.widget.LauncherAppWidgetHostView.1
                @Override // java.lang.Runnable
                public void run() {
                    LauncherAppWidgetHostView.this.switchToErrorView();
                }
            });
        }
        this.mIsScrollable = checkScrollableRecursively(this);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(getClass().getName());
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        maybeRegisterAutoAdvance();
    }

    private void checkIfAutoAdvance() {
        boolean z;
        Advanceable advanceable = getAdvanceable();
        if (advanceable != null) {
            advanceable.fyiWillBeAdvancedByHostKThx();
            z = true;
        } else {
            z = false;
        }
        SparseBooleanArray sparseBooleanArray = sAutoAdvanceWidgetIds;
        if (z != (sparseBooleanArray.indexOfKey(getAppWidgetId()) >= 0)) {
            if (z) {
                sparseBooleanArray.put(getAppWidgetId(), true);
            } else {
                sparseBooleanArray.delete(getAppWidgetId());
            }
            maybeRegisterAutoAdvance();
        }
    }

    private Advanceable getAdvanceable() {
        AppWidgetProviderInfo appWidgetInfo = getAppWidgetInfo();
        if (appWidgetInfo == null || appWidgetInfo.autoAdvanceViewId == -1 || !this.mIsAttachedToWindow) {
            return null;
        }
        KeyEvent.Callback callbackFindViewById = findViewById(appWidgetInfo.autoAdvanceViewId);
        if (callbackFindViewById instanceof Advanceable) {
            return (Advanceable) callbackFindViewById;
        }
        return null;
    }

    private void maybeRegisterAutoAdvance() {
        Handler handler = getHandler();
        boolean z = getWindowVisibility() == 0 && handler != null && sAutoAdvanceWidgetIds.indexOfKey(getAppWidgetId()) >= 0;
        if (z != this.mIsAutoAdvanceRegistered) {
            this.mIsAutoAdvanceRegistered = z;
            if (this.mAutoAdvanceRunnable == null) {
                this.mAutoAdvanceRunnable = new Runnable() { // from class: com.android.launcher3.widget.LauncherAppWidgetHostView.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LauncherAppWidgetHostView.this.runAutoAdvance();
                    }
                };
            }
            handler.removeCallbacks(this.mAutoAdvanceRunnable);
            scheduleNextAdvance();
        }
    }

    private void scheduleNextAdvance() {
        if (this.mIsAutoAdvanceRegistered) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            long jIndexOfKey = jUptimeMillis + (ADVANCE_INTERVAL - (jUptimeMillis % ADVANCE_INTERVAL)) + (((long) sAutoAdvanceWidgetIds.indexOfKey(getAppWidgetId())) * ADVANCE_STAGGER);
            Handler handler = getHandler();
            if (handler != null) {
                handler.postAtTime(this.mAutoAdvanceRunnable, jIndexOfKey);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runAutoAdvance() {
        Advanceable advanceable = getAdvanceable();
        if (advanceable != null) {
            advanceable.advance();
        }
        scheduleNextAdvance();
    }

    public void setScaleToFit(float scale) {
        this.mScaleToFit = scale;
        setScaleX(scale);
        setScaleY(scale);
    }

    public float getScaleToFit() {
        return this.mScaleToFit;
    }

    public void setTranslationForCentering(float x, float y) {
        this.mTranslationForCentering.set(x, y);
        setTranslationX(x);
        setTranslationY(y);
    }

    public PointF getTranslationForCentering() {
        return this.mTranslationForCentering;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mReinflateOnConfigChange && isSameOrientation()) {
            this.mReinflateOnConfigChange = false;
            reInflate();
        }
    }

    public void reInflate() {
        if (isAttachedToWindow()) {
            LauncherAppWidgetInfo launcherAppWidgetInfo = (LauncherAppWidgetInfo) getTag();
            this.mLauncher.removeItem(this, launcherAppWidgetInfo, false);
            this.mLauncher.bindAppWidget(launcherAppWidgetInfo);
        }
    }

    @Override // com.android.launcher3.widget.NavigableAppWidgetHostView
    protected boolean shouldAllowDirectClick() {
        if (!(getTag() instanceof ItemInfo)) {
            return false;
        }
        ItemInfo itemInfo = (ItemInfo) getTag();
        return itemInfo.spanX == 1 && itemInfo.spanY == 1;
    }
}
