package com.android.systemui.qs.tiles;

import android.app.UiModeManager;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.recents.event.RecentEvent;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.volume.VolumeComponent;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class UiModeNightTile extends QSTileImpl<QSTile.BooleanState> {
    private ContentObserver mContentObserver;
    private UiModeHandler mUiModeHandler;
    private UiModeManager mUiModeManager;
    private String tileLabel;
    private final String uri_mode;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return null;
    }

    public UiModeNightTile(Context context) {
        super(context);
        this.uri_mode = "ui_night_mode";
        this.mAlwaysSelected = true;
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService("uimode");
        this.mContentObserver = new ContentObserver(new Handler()) { // from class: com.android.systemui.qs.tiles.UiModeNightTile.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                String lastPathSegment = uri.getLastPathSegment();
                if (selfChange || !TextUtils.equals("ui_night_mode", lastPathSegment)) {
                    return;
                }
                BydLogHelper.logD("UiModeNightTile onChange");
                UiModeNightTile.this.refreshView();
            }
        };
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("ui_night_mode"), false, this.mContentObserver);
        this.mUiModeHandler = new UiModeHandler(Looper.getMainLooper());
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_dark_mode);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int i;
        String string;
        int nightMode = this.mUiModeManager.getNightMode();
        BydLogHelper.logD("click-->current type:" + nightMode);
        EventBus.getDefault().post(new RecentEvent());
        int i2 = 2;
        if (nightMode == 1) {
            i2 = 0;
            i = R.mipmap.icon_control_intelligent_mode;
            string = this.mContext.getString(R.string.qs_title_auto_mode);
            this.mContext.getString(R.string.qs_title_toast_set_auto_mode);
        } else if (nightMode == 2) {
            i = R.mipmap.icon_control_air_conditioning_heating;
            string = this.mContext.getString(R.string.qs_title_light_mode);
            this.mContext.getString(R.string.qs_title_toast_set_light_mode);
            i2 = 1;
        } else {
            i = R.mipmap.icon_control_night_mode;
            string = this.mContext.getString(R.string.qs_title_dark_mode);
            this.mContext.getString(R.string.qs_title_toast_set_night_mode);
        }
        this.tileLabel = string;
        BydLogHelper.logD("click-->tileLabel:" + this.tileLabel);
        boolean zIsSplitActive = ((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive();
        BydLogHelper.logD("isSplitActive:" + zIsSplitActive);
        if (zIsSplitActive) {
            this.mUiModeHandler.removeMessages(1);
            this.mUiModeHandler.sendEmptyMessageDelayed(1, 1000L);
        } else {
            doStatusBar();
        }
        setMode(i2);
        this.mTextView.setText(string);
        this.mImageView.setImageResource(i);
        VolumeComponent volumeComponent = (VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class);
        if (volumeComponent != null) {
            volumeComponent.dismissLauncherDialog();
        }
        EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.CLICK_UI_MODE));
    }

    private void setMode(final int type) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.qs.tiles.UiModeNightTile.2
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.cancel();
                UiModeNightTile.this.mUiModeManager.setNightMode(type);
                Settings.Global.putInt(UiModeNightTile.this.mContext.getContentResolver(), "ui_night_mode", type);
                int i = type;
                if (i == 0) {
                    ToastUtils.show(UiModeNightTile.this.mContext, R.string.qs_title_toast_set_auto_mode);
                } else if (i == 1) {
                    ToastUtils.show(UiModeNightTile.this.mContext, R.string.qs_title_toast_set_light_mode);
                } else {
                    if (i != 2) {
                        return;
                    }
                    ToastUtils.show(UiModeNightTile.this.mContext, R.string.qs_title_toast_set_night_mode);
                }
            }
        }, 50L);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        this.mRlContent.setSelected(true);
        this.mImageView.setSelectedStatus(true);
        refreshView();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        if (this.mContentObserver != null) {
            try {
                this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mContentObserver = null;
        }
        if (this.mUiModeHandler != null) {
            this.mUiModeHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView() {
        int i;
        String string;
        if (this.mUiModeManager == null) {
            this.mUiModeManager = (UiModeManager) this.mContext.getSystemService("uimode");
        }
        int nightMode = this.mUiModeManager.getNightMode();
        if (nightMode == 1) {
            i = R.mipmap.icon_control_air_conditioning_heating;
            string = this.mContext.getString(R.string.qs_title_light_mode);
        } else if (nightMode == 2) {
            i = R.mipmap.icon_control_night_mode;
            string = this.mContext.getString(R.string.qs_title_dark_mode);
        } else {
            i = R.mipmap.icon_control_intelligent_mode;
            string = this.mContext.getString(R.string.qs_title_auto_mode);
        }
        this.tileLabel = string;
        BydLogHelper.logD("onBindViewHolder-->current type:" + nightMode + " , tileLabel:" + this.tileLabel);
        if (this.mTextView == null || this.mImageView == null) {
            return;
        }
        this.mTextView.setText(string);
        this.mImageView.setImageResource(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStatusBar() {
        StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        statusBar.removeChangeIconMsg();
        statusBar.closePanelsIfNeedNoDelayed();
    }

    private class UiModeHandler extends Handler {
        public static final int HIDE = 1;

        public UiModeHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                UiModeNightTile.this.doStatusBar();
            }
        }
    }
}
