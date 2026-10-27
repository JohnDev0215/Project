package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class InstrumentRequest extends BaseRequest<InstrumentValue> {
    private BYDAutoInstrumentDevice mBYDAutoInstrumentDevice;

    public InstrumentRequest(Context context, InstrumentValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(InstrumentValue requestValue) {
        BydLogHelper.logD("handleRequest InstrumentValue");
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.mBYDAutoInstrumentDevice;
        if (bYDAutoInstrumentDevice == null) {
            return true;
        }
        try {
            bYDAutoInstrumentDevice.setDayNightMode(requestValue.mDayNightMode);
            Thread.sleep(50L);
            this.mBYDAutoInstrumentDevice.setDayNightMode(requestValue.mDayNightMode);
            Thread.sleep(50L);
            this.mBYDAutoInstrumentDevice.setBacklightBrightness(requestValue.mBrightness);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(InstrumentValue wantValue) {
        try {
            BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.mBYDAutoInstrumentDevice;
            if (bYDAutoInstrumentDevice != null) {
                this.mCurrentValue = new InstrumentValue(bYDAutoInstrumentDevice.getBacklightBrightness(), this.mBYDAutoInstrumentDevice.getDayNightMode());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static class InstrumentValue extends BaseValue {
        protected int mBrightness;
        protected int mDayNightMode;

        public InstrumentValue(int brightness, int dayNightMode) {
            this.mBrightness = brightness;
            this.mDayNightMode = dayNightMode;
        }
    }
}
