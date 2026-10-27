package com.android.systemui.qs.tiles;

import android.content.Context;
import android.os.Handler;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.SharedPreferencesUtil;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ScreenOffTile extends QSTileImpl<QSTile.BooleanState> {
    private BluetoothController mBluetoothController;
    private CarController mCarController;
    private final StatusBar statusBar;

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return R.mipmap.icon_control_screen_off;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return null;
    }

    public ScreenOffTile(Context context) {
        super(context);
        this.mAlwaysSelected = true;
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        this.mBluetoothController = (BluetoothController) Dependency.get(BluetoothController.class);
        this.mCarController = (CarController) Dependency.get(CarController.class);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.mContext.getString(R.string.qs_title_rest_screen);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, android.view.View.OnClickListener
    public void onClick(View v) {
        if (TextUtils.equals(SystemProperties.get("sys.byd.uereport.epb", Constant.CarType.CAR_TYPE_FUEL), "1")) {
            this.statusBar.closePanelsIfNeed();
            showToast(this.mContext.getString(R.string.qs_title_toast_epb));
        } else {
            if (this.mCarController.isCarPlayPhoneCall()) {
                BydLogHelper.logD("carPlay is onCalling");
                this.statusBar.closePanelsIfNeed();
                showToast(this.mContext.getString(R.string.operation_prohibited_during_call));
                return;
            }
            super.onClick(v);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        if (this.statusBar.isShow()) {
            SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
        }
        AppUtil.startCarSettings("screen_saver");
        Handler handler = (Handler) Dependency.get(Dependency.MAIN_HANDLER);
        StatusBar statusBar = this.statusBar;
        Objects.requireNonNull(statusBar);
        handler.postDelayed(new $$Lambda$6AFP_jFeRHxKeJbPfqNHVlv_FoI(statusBar), 200L);
        if (isLongClickFlag()) {
            return;
        }
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.qs.tiles.ScreenOffTile.1
            @Override // java.lang.Runnable
            public void run() {
                ScreenOffTile.this.statusBar.showQSPanel();
            }
        }, 1000L);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        this.mRlContent.setSelected(false);
        this.mImageView.setSelectedStatus(false);
    }
}
