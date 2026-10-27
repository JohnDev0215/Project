package com.android.systemui.qs.tiles;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.util.AppUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class HotspotTile extends QSTileImpl<QSTile.BooleanState> {
    private HotspotAndDataSaverCallbacks mCallBack;
    private NetworkController mController;
    private HotspotController mHotspotController;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_hot_spot;
    }

    public HotspotTile(Context context) {
        super(context);
        this.mCallBack = new HotspotAndDataSaverCallbacks();
        HotspotController hotspotController = (HotspotController) Dependency.get(HotspotController.class);
        this.mHotspotController = hotspotController;
        hotspotController.addCallback(this.mCallBack);
        this.mController = (NetworkController) Dependency.get(NetworkController.class);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        HotspotController hotspotController = this.mHotspotController;
        if (hotspotController != null) {
            hotspotController.removeCallback(this.mCallBack);
            this.mHotspotController = null;
            this.mCallBack = null;
        }
        this.mController = null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_hot_spot);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        this.mHotspotController.setHotspotEnabled(isSelected);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        if (isLongClickFlag()) {
            ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).closePanelsIfNeed();
            AppUtil.startCarSettings("Hotspot");
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        ((QSTile.BooleanState) this.mState).value = this.mHotspotController.isHotspotEnabled();
        refreshState();
    }

    private final class HotspotAndDataSaverCallbacks implements HotspotController.Callback {
        @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
        public void onHotspotAvailabilityChanged(boolean available) {
        }

        private HotspotAndDataSaverCallbacks() {
        }

        @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
        public void onHotspotChanged(boolean enabled, int numDevices) {
            BydLogHelper.logD("onHotspotChanged--> enabled : " + enabled);
            ((QSTile.BooleanState) HotspotTile.this.mState).value = enabled;
            HotspotTile.this.refreshState();
        }
    }
}
