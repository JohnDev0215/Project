package com.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarWindowView;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class CellularTile extends QSTileImpl<QSTile.BooleanState> {
    private NetworkController mController;
    private CellSignalCallback mSignalCallback;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_data;
    }

    public CellularTile(Context context) {
        super(context);
        this.mSignalCallback = new CellSignalCallback();
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
        return this.mContext.getString(R.string.qs_title_telephone_data);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        this.mController.setMobileDataEnabled(isSelected);
        if (isSelected) {
            showToastCustom(this.mContext.getString(R.string.qs_title_toast_cellular_enable));
        } else {
            showToastCustom(this.mContext.getString(R.string.qs_title_toast_cellular_disable));
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        if (isLongClickFlag()) {
            final StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
            if (statusBar.isShow()) {
                SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
            }
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.qs.tiles.-$$Lambda$CellularTile$BlPR9zi_uUww4epJGVgKFZn5WvA
                @Override // java.lang.Runnable
                public final void run() {
                    CellularTile.lambda$longClick$0(statusBar);
                }
            }, 300L);
            AppUtil.startCarSettings("cellular_data");
        }
    }

    static /* synthetic */ void lambda$longClick$0(StatusBar statusBar) {
        EventBus.getDefault().post(new StatusBarWindowView.CancelTouchEvent());
        statusBar.closePanelsIfNeed();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = this.mController.isDataEnabled();
        BydLogHelper.logD("value:" + ((QSTile.BooleanState) this.mState).value);
        refreshState();
    }

    private final class CellSignalCallback implements NetworkController.SignalCallback {
        private CellSignalCallback() {
        }

        @Override // com.android.systemui.statusbar.policy.NetworkController.SignalCallback
        public void setMobileDataEnabled(boolean enabled) {
            BydLogHelper.logD("setMobileDataEnabled enabled:" + enabled);
            ((QSTile.BooleanState) CellularTile.this.mState).value = enabled;
            CellularTile.this.refreshState();
        }
    }
}
