package com.amazon.avod.qahooks;

import android.content.Context;
import android.content.Intent;
import com.amazon.avod.perf.Profiler;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QAProfilerFeature implements QATestFeature {
    private final Context mContext;

    private enum ProfilerIntentAction {
        START,
        STOP
    }

    public QAProfilerFeature(@Nonnull Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.avod.qahooks.QATestFeature
    public void handleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        if (ProfilerIntentAction.START.name().equals(stringExtra)) {
            Profiler.start();
        } else if (ProfilerIntentAction.STOP.name().equals(stringExtra)) {
            Profiler.stop(this.mContext);
        }
    }
}
