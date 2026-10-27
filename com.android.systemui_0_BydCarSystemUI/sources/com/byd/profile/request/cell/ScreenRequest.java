package com.byd.profile.request.cell;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenRequest extends BaseRequest<ScreenValue> {
    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(ScreenValue requestValue) {
        return true;
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(ScreenValue wantValue) {
    }

    public ScreenRequest(Context context, ScreenValue wantedValue) {
        super(context, wantedValue);
    }

    public static class ScreenValue extends BaseValue {
        protected boolean isScreenOn;

        public ScreenValue(boolean isScreenOn) {
            this.isScreenOn = isScreenOn;
        }
    }
}
