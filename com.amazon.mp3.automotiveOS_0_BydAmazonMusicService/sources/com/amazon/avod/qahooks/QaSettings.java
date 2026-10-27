package com.amazon.avod.qahooks;

import android.content.Context;
import com.amazon.avod.core.Framework;
import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QaSettings {
    private AppMode mAppMode;

    private static class SingletonHolder {
        private static final QaSettings INSTANCE = new QaSettings();

        private SingletonHolder() {
        }
    }

    public enum AppMode {
        UNKNOWN(false),
        PROD(false),
        QA(true),
        DEBUG(true);

        private final boolean mSupportsQa;

        AppMode(boolean z) {
            this.mSupportsQa = z;
        }

        public boolean supportsQa() {
            return this.mSupportsQa;
        }
    }

    public static QaSettings getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QaSettings() {
        this.mAppMode = AppMode.UNKNOWN;
    }

    @Nonnull
    public AppMode getAppMode() {
        return this.mAppMode;
    }

    public void inferAppMode(Context context) {
        if (!AppMode.UNKNOWN.equals(this.mAppMode)) {
            DLog.logf("App mode is already set and known.");
            return;
        }
        String str = context.getPackageName() + ".test";
        if (Framework.isDebugConfigurationEnabled()) {
            setAppMode(AppMode.DEBUG);
        } else if (context.getPackageManager().checkSignatures(context.getPackageName(), str) == 0) {
            setAppMode(AppMode.QA);
        } else {
            setAppMode(AppMode.PROD);
        }
    }

    public void setAppMode(AppMode appMode) {
        this.mAppMode = appMode;
    }
}
