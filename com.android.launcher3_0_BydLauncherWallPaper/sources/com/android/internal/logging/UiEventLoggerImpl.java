package com.android.internal.logging;

import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.FrameworkStatsLog;

/* JADX INFO: loaded from: classes4.dex */
public class UiEventLoggerImpl implements UiEventLogger {
    @Override // com.android.internal.logging.UiEventLogger
    public void log(UiEventLogger.UiEventEnum uiEventEnum) {
        log(uiEventEnum, 0, null);
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void log(UiEventLogger.UiEventEnum uiEventEnum, int i, String str) {
        int id = uiEventEnum.getId();
        if (id > 0) {
            FrameworkStatsLog.write(90, id, i, str);
        }
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void logWithInstanceId(UiEventLogger.UiEventEnum uiEventEnum, int i, String str, InstanceId instanceId) {
        int id = uiEventEnum.getId();
        if (id > 0 && instanceId != null) {
            FrameworkStatsLog.write(90, id, i, str, instanceId.getId());
        } else {
            log(uiEventEnum, i, str);
        }
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void logWithPosition(UiEventLogger.UiEventEnum uiEventEnum, int i, String str, int i2) {
        int id = uiEventEnum.getId();
        if (id > 0) {
            FrameworkStatsLog.write(260, id, str, 0, i2);
        }
    }

    @Override // com.android.internal.logging.UiEventLogger
    public void logWithInstanceIdAndPosition(UiEventLogger.UiEventEnum uiEventEnum, int i, String str, InstanceId instanceId, int i2) {
        int id = uiEventEnum.getId();
        if (id > 0 && instanceId != null) {
            FrameworkStatsLog.write(260, id, str, instanceId.getId(), i2);
        } else {
            logWithPosition(uiEventEnum, i, str, i2);
        }
    }
}
