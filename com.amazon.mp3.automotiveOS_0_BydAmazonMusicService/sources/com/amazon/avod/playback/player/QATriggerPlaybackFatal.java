package com.amazon.avod.playback.player;

import android.content.Intent;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class QATriggerPlaybackFatal implements QATestFeature {
    private volatile boolean mShouldTriggerFatal;

    private static class SingletonHolder {
        private static final QATriggerPlaybackFatal INSTANCE = new QATriggerPlaybackFatal();

        private SingletonHolder() {
        }
    }

    public static QATriggerPlaybackFatal getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QATriggerPlaybackFatal() {
        this.mShouldTriggerFatal = false;
    }

    void triggerFatalIfEnabled() throws PlaybackException {
        if (this.mShouldTriggerFatal) {
            throw new PlaybackException("QA triggered fatal!");
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
                this.mShouldTriggerFatal = true;
            } else if (lowerCase.equals("disable")) {
                this.mShouldTriggerFatal = false;
            } else {
                DLog.errorf("Unsupported intent action: %s", stringExtra);
            }
        }
    }
}
