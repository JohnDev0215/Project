package com.engine.tbox.item;

import android.os.SystemProperties;
import com.engine.tbox.core.utils.LogUtils;

/* JADX INFO: loaded from: classes.dex */
public class RealNameChange {
    private static final String REAL_NAME_STATUS = "persist.sys.brower_status";
    private int realNameStatus;

    public RealNameChange(final RealNameChangeCallback callback) {
        this.realNameStatus = 0;
        this.realNameStatus = SystemProperties.getInt(REAL_NAME_STATUS, 0);
        SystemProperties.addChangeCallback(new Runnable() { // from class: com.engine.tbox.item.-$$Lambda$RealNameChange$GfpXociYuw4YCEEVDsKaQTrTFxQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$RealNameChange(callback);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$RealNameChange(RealNameChangeCallback callback) {
        int state = SystemProperties.getInt(REAL_NAME_STATUS, 0);
        if (this.realNameStatus != state) {
            LogUtils.i("RealNameChange", "realNameChanged:" + state, new Object[0]);
            this.realNameStatus = state;
            callback.realNameStatusChange(state);
        }
    }

    public int getRealNameStatus() {
        int i = SystemProperties.getInt(REAL_NAME_STATUS, 0);
        this.realNameStatus = i;
        return i;
    }
}
