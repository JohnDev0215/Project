package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class WindowRequest extends BaseRequest<WindowValue> {
    private static final int ROOF_CLOSE = 0;
    private static final int WINDOW_CLOSE = 2;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(WindowValue wantValue) {
    }

    public WindowRequest(Context context, WindowValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(WindowValue requestValue) {
        BydLogHelper.logD("handleRequest WindowValue");
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice == null) {
            return true;
        }
        try {
            bYDAutoBodyworkDevice.setWindowControlState(requestValue.values);
            if (requestValue.roof != 2) {
                return true;
            }
            this.mBYDAutoBodyworkDevice.setWindowOpenPercent(5, 0);
            this.mBYDAutoBodyworkDevice.setWindowOpenPercent(6, 0);
            return true;
        } catch (Throwable th) {
            BydLogHelper.logE("handleRequest error");
            th.printStackTrace();
            return true;
        }
    }

    private boolean setWindowPercent(int area, int state) {
        BydLogHelper.logD("setWindowPercent state " + state);
        if (state != 2) {
            return true;
        }
        this.mBYDAutoBodyworkDevice.setWindowControlState(area, 2);
        return true;
    }

    public static class WindowValue extends BaseValue {
        protected int leftFrontState;
        protected int leftRearState;
        protected int rightFrontState;
        protected int rightRearState;
        protected int roof;
        protected int[] values;

        public static WindowValue createWindowValue(boolean includeRoof) {
            return new WindowValue(2, 2, 2, 2, includeRoof ? 2 : 0);
        }

        public WindowValue(int leftFrontState, int leftRearState, int rightFrontState, int rightRearState, int roof) {
            this.values = new int[]{leftFrontState, rightFrontState, leftRearState, rightRearState};
            this.leftFrontState = leftFrontState;
            this.leftRearState = leftRearState;
            this.rightFrontState = rightFrontState;
            this.rightRearState = rightRearState;
            this.roof = roof;
        }
    }
}
