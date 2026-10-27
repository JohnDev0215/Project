package com.amazon.avod.drm;

import android.content.Intent;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class QATriggerDrmLicensingFailure implements QATestFeature {
    private volatile boolean mShouldTriggerFailure;

    private static class SingletonHolder {
        private static final QATriggerDrmLicensingFailure INSTANCE = new QATriggerDrmLicensingFailure();

        private SingletonHolder() {
        }
    }

    public static QATriggerDrmLicensingFailure getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QATriggerDrmLicensingFailure() {
        this.mShouldTriggerFailure = false;
    }

    public void triggerFailureIfEnabled() throws DrmLicensingException {
        if (this.mShouldTriggerFailure) {
            throw new DrmLicensingException("QA hook initiated failure");
        }
    }

    @Override // com.amazon.avod.qahooks.QATestFeature
    public void handleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        DLog.logf("Received %s intent", stringExtra);
        if (stringExtra != null) {
            String lowerCase = stringExtra.toLowerCase(Locale.US);
            lowerCase.hashCode();
            if (lowerCase.equals("enable")) {
                this.mShouldTriggerFailure = true;
            } else if (lowerCase.equals("disable")) {
                this.mShouldTriggerFailure = false;
            } else {
                DLog.errorf("Unsupported intent action: %s", stringExtra);
            }
        }
    }
}
