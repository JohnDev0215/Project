package com.amazon.avod.media.service;

import android.content.Intent;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class QATriggerManifestAcquisitionFailure implements QATestFeature {
    private volatile boolean mShouldBlockManifestAcquisition;

    private static class SingletonHolder {
        private static final QATriggerManifestAcquisitionFailure INSTANCE = new QATriggerManifestAcquisitionFailure();

        private SingletonHolder() {
        }
    }

    public static QATriggerManifestAcquisitionFailure getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QATriggerManifestAcquisitionFailure() {
        this.mShouldBlockManifestAcquisition = false;
    }

    public void blockManifestAcquisitionIfEnabled() throws ContentException {
        if (this.mShouldBlockManifestAcquisition) {
            throw new ContentException(ContentException.ContentError.NETWORK_ERROR, "QA triggered manifest acquisition failure");
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
                this.mShouldBlockManifestAcquisition = true;
            } else if (lowerCase.equals("disable")) {
                this.mShouldBlockManifestAcquisition = false;
            } else {
                DLog.errorf("Unsupported intent action %s", stringExtra);
            }
        }
    }
}
