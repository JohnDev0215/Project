package com.amazon.avod.media.downloadservice.internal;

import android.content.Intent;
import com.amazon.avod.qahooks.QATestFeature;
import com.amazon.avod.util.DLog;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class QATriggerPlaybackBuffer implements QATestFeature {

    private static class SingletonHolder {
        private static final QATriggerPlaybackBuffer INSTANCE = new QATriggerPlaybackBuffer();

        private SingletonHolder() {
        }
    }

    public static QATriggerPlaybackBuffer getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QATriggerPlaybackBuffer() {
    }

    @Override // com.amazon.avod.qahooks.QATestFeature
    public void handleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        String stringExtra2 = intent.getStringExtra("asset");
        int intExtra = intent.getIntExtra("responseCode", 404);
        DLog.logf("QATriggerPlaybackBuffer received %s intent action, with response code: %s", stringExtra, Integer.valueOf(intExtra));
        if (stringExtra != null) {
            String lowerCase = stringExtra.toLowerCase(Locale.US);
            lowerCase.hashCode();
            if (lowerCase.equals("enable")) {
                setIntentAction(stringExtra2, true, intExtra);
            } else if (lowerCase.equals("disable")) {
                setIntentAction(stringExtra2, false, 200);
            } else {
                DLog.errorf("QATriggerPlaybackBuffer unsupported intent action: %s", stringExtra);
            }
        }
    }

    private void setIntentAction(String str, boolean z, int i) {
        if ("manifest".equals(str)) {
            DLog.logf("QATriggerPlaybackBuffer %s manifest request failure with response code %d", z ? "enable" : "disable", Integer.valueOf(i));
            QAHttpRequestThrottlingInterceptor.setShouldFailManifestRequests(z, i);
        } else if ("fragment".equals(str)) {
            DLog.logf("QATriggerPlaybackBuffer %s fragment request failure with response code %d", z ? "enable" : "disable", Integer.valueOf(i));
            QAHttpRequestThrottlingInterceptor.setShouldFailFragmentsRequests(z, i);
        } else if (str == null) {
            DLog.logf("QATriggerPlaybackBuffer %s all request failure with response code %d", z ? "enable" : "disable", Integer.valueOf(i));
            QAHttpRequestThrottlingInterceptor.setShouldFailAllRequests(z, i);
        } else {
            DLog.errorf("QATriggerPlaybackBuffer unsupported intent asset: %s", str);
        }
    }
}
