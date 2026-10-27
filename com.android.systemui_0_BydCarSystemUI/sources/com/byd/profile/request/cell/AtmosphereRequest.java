package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AtmosphereRequest extends BaseRequest<AtmosphereValue> {
    public static final int LIGHT_OFF = 1;
    public static final int LIGHT_ON = 4;
    private BYDAutoLightDevice mBYDAutoLightDevice;

    public AtmosphereRequest(Context context, AtmosphereValue wantedValue) {
        super(context, wantedValue);
        this.mBYDAutoLightDevice = BYDAutoLightDevice.getInstance(context);
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(AtmosphereValue atmosphereValue) {
        BydLogHelper.logD("handleRequest AtmosphereRequest");
        if (this.mBYDAutoLightDevice == null) {
            return true;
        }
        try {
            BydLogHelper.logD("handleRequest area = " + atmosphereValue.area + " ,brightness = " + atmosphereValue.brightness);
            this.mBYDAutoLightDevice.setIALBrightness(atmosphereValue.area, atmosphereValue.brightness, 0);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(AtmosphereValue wantValue) {
        try {
            int lastSetedArea = this.mBYDAutoLightDevice.getLastSetedArea();
            int iALBrightness = 0;
            int i = 1;
            if (lastSetedArea == 0) {
                lastSetedArea = 3;
            } else {
                iALBrightness = this.mBYDAutoLightDevice.getIALBrightness(lastSetedArea);
                if (wantValue.isLightOn) {
                    wantValue.area = 3;
                    wantValue.brightness = 4;
                } else {
                    wantValue.area = lastSetedArea;
                    wantValue.brightness = 1;
                }
            }
            if (iALBrightness != 0) {
                i = iALBrightness;
            }
            this.mCurrentValue = new AtmosphereValue(lastSetedArea, i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static class AtmosphereValue extends BaseValue {
        private int area;
        private int brightness;
        private boolean isLightOn;

        public AtmosphereValue(int area, int brightness) {
            this.area = area;
            this.brightness = brightness;
            this.isLightOn = brightness > 0;
        }

        public AtmosphereValue(boolean isLightOn, int brightness) {
            this.area = 3;
            this.isLightOn = isLightOn;
            this.brightness = isLightOn ? brightness : 0;
        }
    }
}
