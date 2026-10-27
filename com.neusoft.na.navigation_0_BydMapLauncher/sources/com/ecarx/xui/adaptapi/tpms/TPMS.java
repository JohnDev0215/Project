package com.ecarx.xui.adaptapi.tpms;

import android.content.Context;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TPMS extends com.ecarx.xui.adaptapi.AdaptAPI {
    public static final int TIRE_ID_ALL = 0;
    public static final int TIRE_ID_LEFT_FRONT = 1;
    public static final int TIRE_ID_LEFT_REAR = 2;
    public static final int TIRE_ID_RIGHT_FRONT = 3;
    public static final int TIRE_ID_RIGHT_REAR = 4;
    public static final String XUI_INTENT_ACTION_TPMS_WARNING = "xui.intent.action.TPMS_WARNING";
    public static final String XUI_INTENT_EXTRA_TPMS_WARNING_TIRE = "xui.intent.extra.TPMS_WARNING_TIRE";

    public interface ITireStateMonitor {
        void onTireStateChanged(int i, ITireState iTireState);
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface TireId {
    }

    public abstract ITireState getTireState(int i);

    public abstract boolean registerTireStateMonitor(ITireStateMonitor iTireStateMonitor);

    public abstract boolean unregisterTireStateMonitor(ITireStateMonitor iTireStateMonitor);

    public static TPMS create(Context context) {
        if (SignVerify.instance().appVerify(context)) {
            return new TPMSImpl();
        }
        return null;
    }
}
