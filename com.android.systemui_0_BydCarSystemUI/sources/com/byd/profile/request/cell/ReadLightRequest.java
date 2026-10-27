package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ReadLightRequest extends BaseRequest<ReadLightValue> {
    public static final int STATE_OFF = 1;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;

    @Override // com.byd.profile.request.cell.BaseRequest
    protected boolean canReset() {
        return false;
    }

    public ReadLightRequest(Context context, ReadLightValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(ReadLightValue requestValue) {
        BydLogHelper.logD("handleRequest ReadLightValue");
        BYDAutoSettingDevice bYDAutoSettingDevice = this.mBYDAutoSettingDevice;
        if (bYDAutoSettingDevice == null) {
            return true;
        }
        try {
            bYDAutoSettingDevice.setInsideLightDoorState(requestValue.state);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(ReadLightValue wantValue) {
        this.mWantedValue = new ReadLightValue(1);
    }

    public static class ReadLightValue extends BaseValue {
        protected int state;

        public ReadLightValue(int state) {
            this.state = state;
        }
    }
}
