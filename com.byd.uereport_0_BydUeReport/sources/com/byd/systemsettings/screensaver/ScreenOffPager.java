package com.byd.systemsettings.screensaver;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.byd.systemsettings.R;
import com.byd.systemsettings.common.Fields;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.system.SystemSettingsModule;
import k4.e;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenOffPager extends a implements View.OnClickListener, e, k4.a {
    private static final String TAG = "ScreenOffPager";
    private View bgScreen;
    private volatile boolean isDetached;

    public ScreenOffPager(Context context) {
        super(context);
    }

    private void updateScreenOffTime(Context context, boolean z7) {
        if (context == null) {
            return;
        }
        x3.a.a(TAG, "---updateScreenOffTime---on: " + z7);
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Fields.FILE_SYSTEM_SETTINGS, 0).edit();
        editorEdit.putInt(Fields.KEY_SCREEN_OFF_STATE, z7 ? 1 : 0);
        editorEdit.commit();
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // z3.a
    public String getDialogTag() {
        return TAG;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.activity_screen_off;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public boolean hasEditText() {
        return false;
    }

    @Override // z3.e
    public void initData(Context context) {
        x3.a.a(TAG, "---initData-----");
        updateScreenOffTime(context, false);
        BydSystemSettings.Singleton.get().setNavibarModeScreensaver(getContext(), 0);
    }

    @Override // z3.a
    public int initHeight(Context context) {
        return getFullSrceenHeight(context);
    }

    @Override // z3.e
    public void initViews(Context context) {
        View viewFindViewById = this.mRootView.findViewById(R.id.bg_screen_off);
        this.bgScreen = viewFindViewById;
        viewFindViewById.setBackgroundResource(R.drawable.bg_screen);
        this.bgScreen.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.screensaver.ScreenOffPager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                x3.a.a(ScreenOffPager.TAG, "---initViews---onClick--bgScreen--");
                if (ScreenOffPager.this.mCallback != null) {
                    ScreenOffPager.this.mCallback.onBackPress();
                }
            }
        });
    }

    @Override // z3.a
    public int initWidth(Context context) {
        return getFullSrceenWidth(context);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onActionModeFinished(ActionMode actionMode) {
        super.onActionModeFinished(actionMode);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public /* bridge */ /* synthetic */ void onAnimationCancel(Animator animator) {
        super.onAnimationCancel(animator);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        x3.a.a(TAG, "---onAnimationEnd---");
        if (this.isDetached) {
            return;
        }
        SystemSettingsModule.getInstance().hideAvm();
        x3.a.a(TAG, "---onAnimationEnd---setBacklightOff.");
        BydPowerManagerDevice.Singleton.get().setBacklightState(false);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public /* bridge */ /* synthetic */ void onAnimationRepeat(Animator animator) {
        super.onAnimationRepeat(animator);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        x3.a.a(TAG, "---onAnimationStart---");
    }

    @Override // k4.e, android.view.Window.Callback
    public void onAttachedToWindow() {
        x3.a.a(TAG, "---onAttachedToWindow---");
        this.isDetached = false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onContentChanged() {
        super.onContentChanged();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onCreatePanelMenu(int i8, Menu menu) {
        return super.onCreatePanelMenu(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ View onCreatePanelView(int i8) {
        return super.onCreatePanelView(i8);
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        updateScreenOffTime(getContext(), true);
        BydSystemSettings.Singleton.get().setNavibarModeScreensaver(getContext(), 1);
        super.onDestroy();
    }

    @Override // k4.e, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.isDetached = true;
        x3.a.a(TAG, "---onDetachedFromWindow---setBacklightOn.");
        BydPowerManagerDevice.Singleton.get().setBacklightState(true);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        return super.onMenuItemSelected(i8, menuItem);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onMenuOpened(int i8, Menu menu) {
        return super.onMenuOpened(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onPanelClosed(int i8, Menu menu) {
        super.onPanelClosed(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onPreparePanel(int i8, View view, Menu menu) {
        return super.onPreparePanel(i8, view, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onSearchRequested(SearchEvent searchEvent) {
        return super.onSearchRequested(searchEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
    }

    @Override // k4.e, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z7) {
        x3.a.a(TAG, "---onWindowFocusChanged---hasFocus: " + z7);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
        return super.onWindowStartingActionMode(callback, i8);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }
}
