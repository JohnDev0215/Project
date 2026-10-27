package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class DoorRequest extends BaseRequest<DoorValue> {
    private static final int LOCK = 2;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(DoorValue wantValue) {
    }

    public DoorRequest(Context context, DoorValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(DoorValue requestValue) {
        BydLogHelper.logD("handleRequest DoorValue");
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice == null) {
            return true;
        }
        try {
            bYDAutoBodyworkDevice.setAllDoorsLock(2);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static class DoorValue extends BaseValue {
        protected int leftFrontState;
        protected int leftRearState;
        protected int rightFrontState;
        protected int rightRearState;

        public DoorValue() {
        }

        public DoorValue(int leftFrontState, int leftRearState, int rightFrontState, int rightRearState) {
            this.leftFrontState = leftFrontState;
            this.leftRearState = leftRearState;
            this.rightFrontState = rightFrontState;
            this.rightRearState = rightRearState;
        }
    }
}
