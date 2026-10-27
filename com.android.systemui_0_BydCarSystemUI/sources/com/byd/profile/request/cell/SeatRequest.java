package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class SeatRequest extends BaseRequest<SeatValue> {
    private static final int MODE_OFF = 0;
    private static final int MODE_REST = 2;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;

    public SeatRequest(Context context, SeatValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(SeatValue requestValue) {
        BydLogHelper.logD("handleRequest SeatValue");
        if (this.mBYDAutoSettingDevice == null) {
            return true;
        }
        try {
            BydLogHelper.logD("set values backrest = " + requestValue.backrest + " ,cushion = " + requestValue.cushion + " ,footrest = " + requestValue.footrest);
            if (requestValue.backrest >= 0 && requestValue.backrest <= 100) {
                this.mBYDAutoSettingDevice.turnSeatbackrestPercent(requestValue.area, requestValue.backrest);
            }
            if (requestValue.cushion >= 0 && requestValue.cushion <= 100) {
                this.mBYDAutoSettingDevice.turnSeatHorizationPercent(requestValue.area, requestValue.cushion);
            }
            if (requestValue.footrest < 0 || requestValue.footrest > 100) {
                return true;
            }
            this.mBYDAutoSettingDevice.turnCaliperSplintPercent(requestValue.area, requestValue.footrest);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(SeatValue wantValue) {
        if (this.mBYDAutoSettingDevice != null) {
            try {
                int i = wantValue.area;
                int seatbackrestPostion = (int) this.mBYDAutoSettingDevice.getSeatbackrestPostion(i);
                int seatHorization = this.mBYDAutoSettingDevice.getSeatHorization(i);
                int turnCaliperSplintHeight = (int) this.mBYDAutoSettingDevice.getTurnCaliperSplintHeight(i);
                BydLogHelper.logD("current values backrest = " + seatbackrestPostion + " ,cushion = " + seatHorization + " ,footrest = " + turnCaliperSplintHeight);
                this.mCurrentValue = new SeatValue(i, seatbackrestPostion, seatHorization, turnCaliperSplintHeight);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static class SeatValue extends BaseValue {
        protected int area;
        protected int backrest;
        protected int cushion;
        protected int footrest;

        public SeatValue(int area, int backrest, int cushion, int footrest) {
            this.area = area;
            this.backrest = backrest;
            this.cushion = cushion;
            this.footrest = footrest;
        }
    }
}
