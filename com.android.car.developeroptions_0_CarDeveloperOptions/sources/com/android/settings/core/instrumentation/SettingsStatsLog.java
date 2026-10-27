package com.android.settings.core.instrumentation;

import android.util.StatsEvent;
import android.util.StatsLog;

/* JADX INFO: loaded from: classes.dex */
public class SettingsStatsLog {
    public static void write(int i, int i2, int i3, int i4, String str, long j) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.writeInt(i2);
        builderNewBuilder.writeInt(i3);
        builderNewBuilder.writeInt(i4);
        builderNewBuilder.writeString(str);
        builderNewBuilder.writeLong(j);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }
}
