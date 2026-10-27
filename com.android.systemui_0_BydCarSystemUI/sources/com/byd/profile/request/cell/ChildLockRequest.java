package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ChildLockRequest extends BaseRequest<ChildLockValue> {
    private BYDAutoDoorLockDevice mBYDAutoDoorLockDevice;

    public ChildLockRequest(Context context, ChildLockValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoDoorLockDevice = BYDAutoDoorLockDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(ChildLockValue childLockValue) {
        BydLogHelper.logD("handleRequest ChildLockValue");
        BYDAutoDoorLockDevice bYDAutoDoorLockDevice = this.mBYDAutoDoorLockDevice;
        if (bYDAutoDoorLockDevice != null) {
            try {
                bYDAutoDoorLockDevice.setDoorLockStatus(6, childLockValue.isLeftLocked ? 1 : 2);
                this.mBYDAutoDoorLockDevice.setDoorLockStatus(7, childLockValue.isRightLocked ? 1 : 2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return true;
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(ChildLockValue wantValue) {
        try {
            this.mCurrentValue = new ChildLockValue(this.mBYDAutoDoorLockDevice.getDoorLockStatus(6) == 1, this.mBYDAutoDoorLockDevice.getDoorLockStatus(7) == 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static class ChildLockValue extends BaseValue {
        protected boolean isLeftLocked;
        protected boolean isRightLocked;

        public ChildLockValue(boolean isLeftLocked, boolean isRightLocked) {
            this.isLeftLocked = isLeftLocked;
            this.isRightLocked = isRightLocked;
        }
    }
}
