package com.amazon.music.signin;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class MAPErrorBundleInterpreter {
    private static final Logger LOG = LoggerFactory.getLogger(MAPErrorBundleInterpreter.class.getSimpleName());

    protected MAPErrorBundleInterpreter() {
    }

    protected String getMapAccountManagerDetailedErrorType(@NonNull Bundle bundle) {
        if (!bundle.containsKey(MAPAccountManager.KEY_ERROR_CODE_WEBVIEW_SSL_ERROR)) {
            return "";
        }
        LOG.error("Webview SSL error occurred");
        return MAPAccountManager.KEY_ERROR_CODE_WEBVIEW_SSL_ERROR;
    }
}
