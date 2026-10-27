package com.android.systemui.qs.tiles;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ScreenshotHelper;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class ScreenshotTile extends QSTileImpl<QSTile.BooleanState> {
    private static final int DIALOG_DISMISS_DELAY = 300;
    private static final int MESSAGE_DISMISS = 0;
    private static final int MESSAGE_REFRESH = 1;
    private static final int MESSAGE_SHOW = 2;
    private static final String TAG = "ScreenshotTile";
    private int mDialogPressDelay;
    private H mHandler;
    private ScreenshotRunnable mRunnable;
    private ScreenshotHelper mScreenshotHelper;
    private Runnable mSetCloseRunnable;
    private StatusBar statusBar;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_screenshot;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public ScreenshotTile(Context context) {
        super(context);
        this.mDialogPressDelay = 850;
        this.mAlwaysUnSelected = true;
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
    }

    private boolean isMonkeyRunning() {
        return ActivityManager.isUserAMonkey();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_screen_shot);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        Log.d(TAG, "click");
        if (isMonkeyRunning()) {
            Log.e("ScreenshotTile", "Monkey is running");
            return;
        }
        this.statusBar.closePanelsIfNeed();
        if (this.mScreenshotHelper == null) {
            this.mScreenshotHelper = new ScreenshotHelper(this.mContext);
            this.mHandler = new H();
            this.mRunnable = new ScreenshotRunnable();
            this.mSetCloseRunnable = new Runnable() { // from class: com.android.systemui.qs.tiles.-$$Lambda$ScreenshotTile$TuOvjAzQKMLPwupKQ0TF8BOXMeA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$click$0$ScreenshotTile();
                }
            };
        }
        this.mHandler.postDelayed(this.mRunnable, this.mDialogPressDelay);
    }

    public /* synthetic */ void lambda$click$0$ScreenshotTile() {
        this.statusBar.setIsScreenShotWindowShowing(false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = false;
        refreshState();
    }

    private class ScreenshotRunnable implements Runnable {
        private ScreenshotRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i(ScreenshotTile.TAG, "takeScreenshot!");
            ScreenshotTile.this.mScreenshotHelper.takeScreenshot(1, true, true, 0, ScreenshotTile.this.mHandler, (Consumer) null);
            ScreenshotTile.this.statusBar.setIsScreenShotWindowShowing(true);
            ScreenshotTile.this.mHandler.removeCallbacks(ScreenshotTile.this.mSetCloseRunnable);
            ScreenshotTile.this.mHandler.postDelayed(ScreenshotTile.this.mSetCloseRunnable, 10000L);
        }
    }

    private final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.d("ScreenshotTile", "what:" + msg.what);
            int i = msg.what;
        }
    }
}
