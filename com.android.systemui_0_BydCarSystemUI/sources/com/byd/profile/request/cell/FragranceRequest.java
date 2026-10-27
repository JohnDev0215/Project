package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.fragrance.BYDAutoFragranceDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class FragranceRequest extends BaseRequest<FragranceValue> {
    private BYDAutoFragranceDevice mBYDAutoFragranceDevice;

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(FragranceValue wantValue) {
    }

    public FragranceRequest(Context context, FragranceValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoFragranceDevice = BYDAutoFragranceDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(FragranceValue requestValue) {
        BydLogHelper.logD("handleRequest FragranceValue");
        BYDAutoFragranceDevice bYDAutoFragranceDevice = this.mBYDAutoFragranceDevice;
        if (bYDAutoFragranceDevice == null) {
            return true;
        }
        try {
            bYDAutoFragranceDevice.setFragranceType(requestValue.mType);
            this.mBYDAutoFragranceDevice.setFragranceConcentration(requestValue.mLevel);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static class FragranceValue extends BaseValue {
        protected int mLevel;
        protected int mType;

        public FragranceValue(int type, int level) {
            this.mType = type;
            this.mLevel = level;
        }
    }
}
