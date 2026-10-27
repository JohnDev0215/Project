package com.byd.vrassistant;

import android.app.Instrumentation;
import android.os.Bundle;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class InstrumentationRobot extends Instrumentation {
    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RLog.v("NEU_InstrumentationRoboot", "InstrumentationRobot onCreate");
    }
}
