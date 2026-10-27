package com.amazon.music.casting.session.things;

import android.os.Handler;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractThingShadowMessenger {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) AbstractThingShadowMessenger.class);
    private static final AWSIotMqttQos QOS = AWSIotMqttQos.QOS0;
    protected static final Long THING_SHADOW_VERSION_THRESHOLD = 100L;
    protected AWSIotMqttManager awsIotMqttManager;
    protected Handler backgroundHandler;
    protected CastingSessionConfig castingSessionConfig;
    protected String clientId;
    protected Long lastVersion = Long.MIN_VALUE;
    protected String targetId;

    protected abstract String getIdentifier();

    protected abstract AWSIotMqttNewMessageCallback getMessageAcceptedCallback();

    protected abstract AWSIotMqttNewMessageCallback getMessageDocumentsCallback();

    protected abstract AWSIotMqttNewMessageCallback getMessageRejectedCallback();

    public AbstractThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, String str2, Handler handler, CastingSessionConfig castingSessionConfig) {
        this.awsIotMqttManager = aWSIotMqttManager;
        this.clientId = str;
        this.targetId = str2;
        this.backgroundHandler = handler;
        this.castingSessionConfig = castingSessionConfig;
    }

    public void subscribe(boolean z) throws AmazonClientException {
        AWSIotMqttManager aWSIotMqttManager = this.awsIotMqttManager;
        String getAcceptedTopic = ThingShadowFactory.getGetAcceptedTopic(getIdentifier());
        AWSIotMqttQos aWSIotMqttQos = QOS;
        aWSIotMqttManager.subscribeToTopic(getAcceptedTopic, aWSIotMqttQos, getMessageAcceptedCallback());
        this.awsIotMqttManager.subscribeToTopic(ThingShadowFactory.getGetRejectedTopic(getIdentifier()), aWSIotMqttQos, getMessageRejectedCallback());
        this.awsIotMqttManager.subscribeToTopic(ThingShadowFactory.getUpdateRejectedTopic(getIdentifier()), aWSIotMqttQos, getMessageRejectedCallback());
        this.awsIotMqttManager.subscribeToTopic(ThingShadowFactory.getDocumentsTopic(getIdentifier()), aWSIotMqttQos, getMessageDocumentsCallback());
        if (z) {
            this.backgroundHandler.postDelayed(new Runnable() { // from class: com.amazon.music.casting.session.things.AbstractThingShadowMessenger.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AbstractThingShadowMessenger.this.awsIotMqttManager.publishString("", ThingShadowFactory.getGetTopic(AbstractThingShadowMessenger.this.getIdentifier()), AWSIotMqttQos.QOS0);
                    } catch (AmazonClientException e) {
                        AbstractThingShadowMessenger.LOG.error("publish failed", (Throwable) e);
                    }
                }
            }, 200L);
        }
    }

    public void unsubscribe() throws AmazonClientException {
        this.awsIotMqttManager.unsubscribeTopic(ThingShadowFactory.getGetAcceptedTopic(getIdentifier()));
        this.awsIotMqttManager.unsubscribeTopic(ThingShadowFactory.getGetRejectedTopic(getIdentifier()));
        this.awsIotMqttManager.unsubscribeTopic(ThingShadowFactory.getUpdateRejectedTopic(getIdentifier()));
        this.awsIotMqttManager.unsubscribeTopic(ThingShadowFactory.getDocumentsTopic(getIdentifier()));
    }

    public boolean updateVersion(Long l) {
        if (l != null && (l.longValue() >= this.lastVersion.longValue() || this.lastVersion.longValue() - l.longValue() > THING_SHADOW_VERSION_THRESHOLD.longValue())) {
            this.lastVersion = l;
            return true;
        }
        LOG.error("updateVersion failed, currentVersion = " + l + " lastVersion = " + this.lastVersion);
        return false;
    }
}
