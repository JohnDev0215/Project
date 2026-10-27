package com.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarWindowView;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.StringUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class WifiTile extends QSTileImpl<QSTile.BooleanState> {
    private NetworkController mController;
    protected WifiSignalCallback mSignalCallback;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_wifi;
    }

    public WifiTile(Context context) {
        super(context);
        this.mSignalCallback = new WifiSignalCallback();
        NetworkController networkController = (NetworkController) Dependency.get(NetworkController.class);
        this.mController = networkController;
        networkController.addCallback((NetworkController.SignalCallback) this.mSignalCallback);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        NetworkController networkController = this.mController;
        if (networkController != null) {
            networkController.removeCallback((NetworkController.SignalCallback) this.mSignalCallback);
            this.mController = null;
            this.mSignalCallback = null;
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_wifi);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        if (isSelected) {
            boolean zIsWirelessCarPlayConnected = ((CarController) Dependency.get(CarController.class)).isWirelessCarPlayConnected();
            boolean zIsWirelessAndroidAutoConnected = ((CarController) Dependency.get(CarController.class)).isWirelessAndroidAutoConnected();
            if (zIsWirelessAndroidAutoConnected || zIsWirelessCarPlayConnected) {
                BydLogHelper.logD("isAAConnect = " + zIsWirelessAndroidAutoConnected + " ,isCPConnect = " + zIsWirelessCarPlayConnected);
                this.mRlContent.setSelected(false);
                this.mImageView.setSelectedStatus(false);
                AppUtil.startCarSettings("WiFi");
                ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).closePanelsIfNeed();
                return;
            }
            this.mController.setWifiEnabled(true);
            return;
        }
        this.mController.setWifiEnabled(false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        if (isLongClickFlag()) {
            final StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.qs.tiles.-$$Lambda$WifiTile$O1daV2G-ysE2n55DokNuIFwOGGk
                @Override // java.lang.Runnable
                public final void run() {
                    WifiTile.lambda$longClick$0(statusBar);
                }
            }, 200L);
            AppUtil.startCarSettings("WiFi");
        }
    }

    static /* synthetic */ void lambda$longClick$0(StatusBar statusBar) {
        EventBus.getDefault().post(new StatusBarWindowView.CancelTouchEvent());
        statusBar.closePanelsIfNeed();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = this.mController.isWifiEnabled();
        refreshState();
        if (this.mController.isWifiConnected()) {
            String wifiConnectedName = this.mController.getWifiConnectedName();
            BydLogHelper.logD("wifiConnectedName:" + StringUtil.desensitizeString(wifiConnectedName, 0));
            if (!TextUtils.isEmpty(wifiConnectedName)) {
                this.mTextView.setText(this.mController.getWifiConnectedName());
                return;
            } else {
                this.mTextView.setText(getTileLabel());
                return;
            }
        }
        this.mTextView.setText(getTileLabel());
    }

    protected class WifiSignalCallback implements NetworkController.SignalCallback {
        protected WifiSignalCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void setWifiIndicators(SignalController.State state, NetworkController.IconState statusIcon, String description, boolean isTransient, String statusLabel) {
            ((QSTile.BooleanState) WifiTile.this.mState).value = state.enabled;
            WifiTile.this.refreshState();
            if (!state.connected || TextUtils.isEmpty(description)) {
                if (WifiTile.this.mTextView != null) {
                    WifiTile.this.mTextView.setText(WifiTile.this.getTileLabel());
                }
            } else if (WifiTile.this.mTextView != null) {
                WifiTile.this.mTextView.setText(WifiTile.this.removeDoubleQuotes(description));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String removeDoubleQuotes(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }
}
