package com.amazon.avod.media.service;

import android.content.Intent;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class QATriggerPRSError implements QATestFeature {
    private volatile boolean mShouldTriggerPRSError;

    private static class SingletonHolder {
        private static final QATriggerPRSError INSTANCE = new QATriggerPRSError();

        private SingletonHolder() {
        }
    }

    public static QATriggerPRSError getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QATriggerPRSError() {
        this.mShouldTriggerPRSError = false;
    }

    void triggerPRSErrorIfEnabled() throws ContentException {
        if (this.mShouldTriggerPRSError) {
            throw new ContentException(ContentException.ContentError.SERVICE_ERROR, "QA triggered PRS fatal!");
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
                this.mShouldTriggerPRSError = true;
            } else if (lowerCase.equals("disable")) {
                this.mShouldTriggerPRSError = false;
            } else {
                DLog.errorf("Unsupported intent action: %s", stringExtra);
            }
        }
    }
}
