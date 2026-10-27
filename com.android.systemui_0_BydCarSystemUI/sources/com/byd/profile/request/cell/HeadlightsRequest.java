package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class HeadlightsRequest extends BaseRequest<HeadlightsValue> {
    private BYDAutoLightDevice mBYDAutoLightDevice;

    public HeadlightsRequest(Context context, HeadlightsValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoLightDevice = BYDAutoLightDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(HeadlightsValue requestValue) {
        BydLogHelper.logD("handleRequest HeadlightsValue");
        BYDAutoLightDevice bYDAutoLightDevice = this.mBYDAutoLightDevice;
        if (bYDAutoLightDevice != null) {
            try {
                bYDAutoLightDevice.setDayTimeLightState(requestValue.isDayLightOn ? 1 : 2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return true;
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(HeadlightsValue wantValue) {
        try {
            BYDAutoLightDevice bYDAutoLightDevice = this.mBYDAutoLightDevice;
            if (bYDAutoLightDevice != null) {
                boolean z = true;
                if (bYDAutoLightDevice.getDayTimeLightState() != 1) {
                    z = false;
                }
                this.mCurrentValue = new HeadlightsValue(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static class HeadlightsValue extends BaseValue {
        public boolean isDayLightOn;

        public HeadlightsValue(boolean isDayLightOn) {
            this.isDayLightOn = isDayLightOn;
        }
    }
}
