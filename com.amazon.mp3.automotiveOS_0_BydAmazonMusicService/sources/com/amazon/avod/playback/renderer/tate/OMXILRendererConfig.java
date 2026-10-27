package com.amazon.avod.playback.renderer.tate;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class OMXILRendererConfig extends MediaConfigBase {
    public static final OMXILRendererConfig INSTANCE = new OMXILRendererConfig();
    private final ConfigurationValue<Boolean> mLongLivedAIVPlayEnabled = newBooleanConfigValue("omxil_isLongLivedAIVPlayEnabled", true);
    private final ConfigurationValue<Boolean> mSendClearContentToSecurePath = newBooleanConfigValue("omxil_sendClearContextToSecurePath", false);
    private final ConfigurationValue<Boolean> mSendClearIntersitialsToSecurePath = newBooleanConfigValue("omxil_sendClearInterstitialsToSecurePath", false);

    public boolean isLongLivedAIVPlayEnabled() {
        return this.mLongLivedAIVPlayEnabled.getValue().booleanValue();
    }

    public boolean getSendClearContentToSecurePath() {
        return this.mSendClearContentToSecurePath.getValue().booleanValue();
    }

    public boolean shouldSendClearIntersititalsToSecurePath() {
        return this.mSendClearIntersitialsToSecurePath.getValue().booleanValue();
    }
}
