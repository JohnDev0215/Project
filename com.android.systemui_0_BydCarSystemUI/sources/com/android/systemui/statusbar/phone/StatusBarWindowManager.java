package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.android.systemui.Dumpable;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class StatusBarWindowManager implements Dumpable {
    private static final String TAG = "StatusBarWindowManager";
    private int mBarHeight;
    private final Context mContext;
    private boolean mHasTopUi;
    private boolean mHasTopUiChanged;
    private WindowManager.LayoutParams mLp;
    private WindowManager.LayoutParams mLpChanged;
    private View mStatusBarView;
    private final WindowManager mWindowManager;
    private final State mCurrentState = new State();
    private final IActivityManager mActivityManager = ActivityManager.getService();

    public StatusBarWindowManager(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    public void add(View statusBarView, int barHeight) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, barHeight, 2000, -2138832824, -2);
        this.mLp = layoutParams;
        layoutParams.token = new Binder();
        this.mLp.gravity = 48;
        this.mLp.softInputMode = 16;
        this.mLp.setTitle(StatusBar.TAG);
        this.mLp.packageName = this.mContext.getPackageName();
        this.mStatusBarView = statusBarView;
        this.mBarHeight = barHeight;
        this.mWindowManager.addView(statusBarView, this.mLp);
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
        this.mLpChanged = layoutParams2;
        layoutParams2.copyFrom(this.mLp);
    }

    private void applyFocusableFlag(State state) {
        if (state.statusBarFocusable && state.panelExpanded) {
            this.mLpChanged.flags &= -9;
            this.mLpChanged.flags |= 131072;
        } else {
            this.mLpChanged.flags |= 8;
            this.mLpChanged.flags &= -131073;
        }
        this.mLpChanged.softInputMode = 16;
    }

    private void applyHeight(State state) {
        if (isExpanded(state)) {
            this.mLpChanged.height = -1;
            Log.d(TAG, "applyHeight1 " + this.mLpChanged.height);
        } else {
            this.mLpChanged.height = this.mBarHeight;
            Log.d(TAG, "applyHeight2 " + this.mLpChanged.height);
        }
    }

    private boolean isExpanded(State state) {
        Log.d(TAG, "state.panelVisible: " + state.panelVisible);
        return state.panelVisible;
    }

    private void apply(State state) {
        applyForceStatusBarVisibleFlag(state);
        applyFocusableFlag(state);
        applyHeight(state);
        applyHasTopUi(state);
        if (this.mLp.copyFrom(this.mLpChanged) != 0) {
            this.mWindowManager.updateViewLayout(this.mStatusBarView, this.mLp);
        }
        boolean z = this.mHasTopUi;
        boolean z2 = this.mHasTopUiChanged;
        if (z != z2) {
            try {
                this.mActivityManager.setHasTopUi(z2);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to call setHasTopUi", e);
            }
            this.mHasTopUi = this.mHasTopUiChanged;
        }
    }

    private void applyForceStatusBarVisibleFlag(State state) {
        if (state.forceStatusBarVisible) {
            this.mLpChanged.privateFlags |= 4096;
        } else {
            this.mLpChanged.privateFlags &= -4097;
        }
    }

    private void applyHasTopUi(State state) {
        this.mHasTopUiChanged = isExpanded(state);
    }

    public void setPanelVisible(boolean visible) {
        this.mCurrentState.panelVisible = visible;
        apply(this.mCurrentState);
    }

    public void setStatusBarFocusable(boolean focusable) {
        this.mCurrentState.statusBarFocusable = focusable;
        apply(this.mCurrentState);
    }

    public void setQsExpanded(boolean expanded) {
        this.mCurrentState.qsExpanded = expanded;
        apply(this.mCurrentState);
    }

    public void setStatusBarState(int state) {
        this.mCurrentState.statusBarState = state;
        apply(this.mCurrentState);
    }

    public void setForceStatusBarVisible(boolean forceStatusBarVisible) {
        this.mCurrentState.forceStatusBarVisible = forceStatusBarVisible;
        apply(this.mCurrentState);
    }

    public void setPanelExpanded(boolean isExpanded) {
        this.mCurrentState.panelExpanded = isExpanded;
        apply(this.mCurrentState);
    }

    public void setBarHeight(int barHeight) {
        this.mBarHeight = barHeight;
        apply(this.mCurrentState);
    }

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println("StatusBarWindowManager state:");
        pw.println(this.mCurrentState);
    }

    private static class State {
        boolean forceStatusBarVisible;
        boolean panelExpanded;
        boolean panelVisible;
        boolean qsExpanded;
        boolean statusBarFocusable;
        int statusBarState;

        private State() {
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Window State {");
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            for (Field field : getClass().getDeclaredFields()) {
                sb.append("  ");
                try {
                    sb.append(field.getName());
                    sb.append(": ");
                    sb.append(field.get(this));
                } catch (IllegalAccessException unused) {
                }
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
            sb.append("}");
            return sb.toString();
        }
    }
}
