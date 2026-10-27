package com.byd.profile.request.cell;

import android.content.Context;
import com.byd.profile.request.cell.BaseValue;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BaseRequest<V extends BaseValue> {
    protected V mCurrentValue;
    protected V mWantedValue;

    protected boolean canReset() {
        return true;
    }

    public boolean handleRequest(V requestValue) {
        return false;
    }

    public void initRequestValues(V wantValue) {
    }

    public BaseRequest(Context context, V wantedValue) {
        this.mWantedValue = wantedValue;
    }

    public boolean execute(boolean isWanted) {
        if (isWanted) {
            initRequestValues(this.mWantedValue);
            return request();
        }
        return reset();
    }

    protected boolean request() {
        BydLogHelper.logD("request");
        return handleRequest(this.mWantedValue);
    }

    protected boolean reset() {
        if (canReset() && this.mCurrentValue != null) {
            BydLogHelper.logD("reset");
            return handleRequest(this.mCurrentValue);
        }
        BydLogHelper.logD("skip reset");
        return true;
    }
}
