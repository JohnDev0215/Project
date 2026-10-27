package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class RightChildLock extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final DeviceManager mDeviceManager;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public RightChildLock(Context context) {
        super(context);
        BydLogHelper.logD("new RightChildLock");
        String string = this.mContext.getString(R.string.car_title_right_child_lock);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        this.mDeviceManager = DeviceManager.getInstance();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.RightChildLock.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onDoorLockStatusChanged(final int area, final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.RightChildLock.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onDoorLockStatusChanged", "area : " + area + " , state : " + state);
                        if (RightChildLock.this.mDeviceManager.getCarControlList().contains(RightChildLock.this.mContext.getString(R.string.car_title_right_child_lock)) && area == 7) {
                            RightChildLock.this.updateItemSelected(state == 1);
                        } else {
                            BydLogHelper.logD("onDoorLockStatusChanged no child lock right");
                        }
                    }
                });
            }
        };
        this.mCallback = callback;
        carController.addCallback(callback);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.tileLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return this.imgRes;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        int doorLockStatus = this.mCarController.getDoorLockStatus(7);
        BydLogHelper.logD("onBindViewHolder", "getDoorLockStatus DOOR_LOCK_AREA_CHILDLOCK_RIGHT : " + doorLockStatus);
        updateItemSelected(doorLockStatus == 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        BydLogHelper.logD("RightChildLock click");
        int doorLockStatus = this.mCarController.getDoorLockStatus(7);
        BydLogHelper.logD("handlerClick", "getDoorLockStatus DOOR_LOCK_AREA_CHILDLOCK_RIGHT doorLockStatus : " + doorLockStatus);
        this.mCarController.setDoorLockStatus(7, doorLockStatus != 1 ? 2 : 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        CarController.Callback callback;
        super.release();
        CarController carController = this.mCarController;
        if (carController == null || (callback = this.mCallback) == null) {
            return;
        }
        carController.removeCallback(callback);
    }
}
