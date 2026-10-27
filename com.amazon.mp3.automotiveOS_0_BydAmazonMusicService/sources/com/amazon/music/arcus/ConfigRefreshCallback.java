package com.amazon.music.arcus;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;

/* JADX INFO: loaded from: classes4.dex */
interface ConfigRefreshCallback {
    void onConfigurationUpdateFailed(Exception exc, Configuration configuration);

    void onConfigurationUpdated(Configuration configuration, boolean z);
}
