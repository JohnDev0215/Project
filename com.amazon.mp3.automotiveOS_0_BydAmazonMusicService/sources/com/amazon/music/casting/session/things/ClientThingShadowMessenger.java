package com.amazon.music.casting.session.things;

import android.os.Handler;
import android.text.TextUtils;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.casting.util.ClockUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ClientThingShadowMessenger extends AbstractThingShadowMessenger {
    private CastingDevice activeDevice;
    private ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls;
    private CastingConnectionCallback connectionListener;
    private long maxTimestampDeltaSeconds;
    protected AWSIotMqttNewMessageCallback messageAcceptedCallback;
    protected AWSIotMqttNewMessageCallback messageRejectedCallback;
    private TerminationReason terminationReason;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) ClientThingShadowMessenger.class);
    private static final AWSIotMqttQos QOS = AWSIotMqttQos.QOS0;
    private static final CastingCategory DEFAULT_CATEGORY = CastingCategory.ALEXA_1P;

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageDocumentsCallback() {
        return null;
    }

    public ClientThingShadowMessenger(AWSIotMqttManager aWSIotMqttManager, String str, CastingDevice castingDevice, Handler handler, CastingSessionConfig castingSessionConfig) {
        super(aWSIotMqttManager, str, castingDevice.getTargetId(), handler, castingSessionConfig);
        this.terminationReason = TerminationReason.UNKNOWN;
        this.messageAcceptedCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.ClientThingShadowMessenger.1
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public synchronized void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    ClientThingShadowMessenger.LOG.debug("onMessageArrived from topic: " + str2 + " dataDeserialized: " + str3);
                    ClientThingShadow clientThingShadow = (ClientThingShadow) new Gson().fromJson(str3, ClientThingShadow.class);
                    ClientThingShadowMessenger.LOG.debug("ClientThingShadow received: " + clientThingShadow);
                    ClientThingShadowMessenger.this.handleClientTSUpdate(clientThingShadow);
                } catch (JsonSyntaxException | UnsupportedEncodingException e) {
                    ClientThingShadowMessenger.LOG.error("Received exception while executing onMessageArrived ", e);
                }
            }
        };
        this.messageRejectedCallback = new AWSIotMqttNewMessageCallback() { // from class: com.amazon.music.casting.session.things.ClientThingShadowMessenger.2
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public void onMessageArrived(String str2, byte[] bArr) {
                try {
                    String str3 = new String(bArr, ParserUtil.UTF_8);
                    ClientThingShadowMessenger.LOG.error("Message was rejected! from topic = " + str2 + " dataDeserialized = " + str3);
                    ClientThingShadowMessenger.this.handleClientTSRejected((RejectedThingShadowDocument) new Gson().fromJson(str3, RejectedThingShadowDocument.class));
                } catch (UnsupportedEncodingException e) {
                    ClientThingShadowMessenger.LOG.error("Received exception while executing onMessageArrived ", (Throwable) e);
                }
            }
        };
        this.activeDevice = castingDevice;
        this.maxTimestampDeltaSeconds = castingSessionConfig.getMaxTimeoutSeconds();
        this.allowedParentalControls = castingSessionConfig.getAllowedParentalControls();
    }

    public void updateState(boolean z) {
        ClientThingShadow.State.ReportedNode reportedNodeBuild;
        if (z) {
            reportedNodeBuild = new ClientThingShadow.State.ReportedNode.Builder().withConnectedTo(this.targetId).withIsOnline(Boolean.TRUE).withAbnormalDisconnect(null).withAllowedParentalControls(this.allowedParentalControls).build();
        } else {
            reportedNodeBuild = new ClientThingShadow.State.ReportedNode.Builder().withIsOnline(Boolean.FALSE).build();
        }
        updateReportedNode(reportedNodeBuild, true);
    }

    public void updateAllowedParentalControls(ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
        updateReportedNode(new ClientThingShadow.State.ReportedNode.Builder().withAllowedParentalControls(allowedParentalControls).build(), false);
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public void subscribe(boolean z) throws AmazonClientException {
        super.subscribe(z);
        this.awsIotMqttManager.subscribeToTopic(ThingShadowFactory.getUpdateAcceptedTopic(this.clientId), QOS, this.messageAcceptedCallback);
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public void unsubscribe() throws AmazonClientException {
        super.unsubscribe();
        this.awsIotMqttManager.unsubscribeTopic(ThingShadowFactory.getUpdateAcceptedTopic(this.clientId));
    }

    public void setCallback(CastingConnectionCallback castingConnectionCallback) {
        this.connectionListener = castingConnectionCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TerminationReason getTerminationReason() {
        return this.terminationReason;
    }

    private void setTerminationReason(TerminationReason terminationReason) {
        this.terminationReason = terminationReason;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClientTSUpdate(ClientThingShadow clientThingShadow) {
        if (clientThingShadow == null || clientThingShadow.getState() == null) {
            LOG.error("handleClientTSUpdate failed, clientTS = " + clientThingShadow);
            return;
        }
        if (!updateVersion(clientThingShadow.getVersion())) {
            LOG.error("Cannot execute an older version of the message!");
            return;
        }
        ClientThingShadow.State.DesiredNode desiredNode = clientThingShadow.getState().getDesiredNode();
        ClientThingShadow.State.ReportedNode reportedNode = clientThingShadow.getState().getReportedNode();
        if (desiredNode != null) {
            handleDesiredNode(desiredNode, reportedNode, clientThingShadow.getMetadata());
        } else if (reportedNode != null) {
            handleReportedNode(reportedNode, clientThingShadow.getMetadata());
        }
    }

    private void handleDesiredNode(ClientThingShadow.State.DesiredNode desiredNode, ClientThingShadow.State.ReportedNode reportedNode, ClientThingShadow.Metadata metadata) {
        try {
            String connectTo = desiredNode.getConnectTo();
            String connectToName = desiredNode.getConnectToName();
            String disconnectFrom = desiredNode.getDisconnectFrom();
            String disconnectReason = desiredNode.getDisconnectReason();
            if (!TextUtils.isEmpty(connectTo) && !TextUtils.isEmpty(connectToName) && isValidTimestamp(metadata.getDesiredNode().getConnectTo()) && isValidTimestamp(metadata.getDesiredNode().getConnectToName())) {
                notifyOnConnected(new CastingDevice.Builder(DEFAULT_CATEGORY, connectTo).withName(connectToName).build());
                return;
            }
            if (!TextUtils.isEmpty(disconnectFrom) && disconnectFrom.equals(this.activeDevice.getTargetId()) && isValidTimestamp(metadata.getDesiredNode().getDisconnectFrom())) {
                updateState(false);
                if (TextUtils.isEmpty(disconnectReason)) {
                    LOG.debug("Remotely disconnected with no reason specified");
                } else {
                    LOG.debug("Remotely disconnected for reason: " + disconnectReason);
                }
                setTerminationReason(TerminationReason.REMOTELY_DISCONNECTED);
                notifyOnDisconnected();
                return;
            }
            updateReportedNode(reportedNode, true);
        } catch (NullPointerException e) {
            LOG.error("Failed to correctly handle desired node", (Throwable) e);
        }
    }

    private void handleReportedNode(ClientThingShadow.State.ReportedNode reportedNode, ClientThingShadow.Metadata metadata) {
        try {
            Boolean isOnline = reportedNode.getIsOnline();
            Boolean abnormalDisconnect = reportedNode.getAbnormalDisconnect();
            Boolean bool = Boolean.TRUE;
            if (bool.equals(abnormalDisconnect) && isValidTimestamp(metadata.getReportedNode().getAbnormalDisconnect())) {
                updateReportedNode(new ClientThingShadow.State.ReportedNode.Builder().withConnectedTo(this.targetId).withIsOnline(bool).withAbnormalDisconnect(null).withAllowedParentalControls(this.allowedParentalControls).build(), true);
                return;
            }
            if (bool.equals(isOnline) && isValidTimestamp(metadata.getReportedNode().getIsOnline())) {
                notifyOnConnected(this.activeDevice);
            } else if (Boolean.FALSE.equals(isOnline)) {
                setTerminationReason(TerminationReason.CLIENT_THING_SHADOW_UPDATED_AS_DISCONNECTED);
                notifyOnDisconnected();
            }
        } catch (NullPointerException e) {
            LOG.error("Failed to correctly handle reported node", (Throwable) e);
        }
    }

    private boolean isValidTimestamp(ClientThingShadow.Metadata.Timestamp timestamp) {
        long currentTimeMillis = (ClockUtil.getCurrentTimeMillis() / 1000) - timestamp.getTimestamp().longValue();
        if (currentTimeMillis <= this.maxTimestampDeltaSeconds) {
            return true;
        }
        LOG.warn("Timestamp not valid, action won't be executed: timestampsDelta: " + currentTimeMillis + " maxTimestampDelta" + this.maxTimestampDeltaSeconds);
        return false;
    }

    private void updateReportedNode(ClientThingShadow.State.ReportedNode reportedNode, boolean z) {
        Gson gson;
        try {
            ClientThingShadow clientThingShadowBuild = new ClientThingShadow.Builder().withDesiredNode(null).withReportedNode(reportedNode).build();
            if (z) {
                gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
            } else {
                gson = new Gson();
            }
            this.awsIotMqttManager.publishString(gson.toJson(clientThingShadowBuild, ClientThingShadow.class), ThingShadowFactory.getUpdateTopic(this.clientId), AWSIotMqttQos.QOS0);
        } catch (AmazonClientException e) {
            LOG.error("Failed to update reported node", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClientTSRejected(RejectedThingShadowDocument rejectedThingShadowDocument) {
        if (rejectedThingShadowDocument == null) {
            LOG.error("handleClientTSRejected failed, clientTS = " + rejectedThingShadowDocument);
            return;
        }
        String code = rejectedThingShadowDocument.getCode();
        code.hashCode();
        if (code.equals(ThingShadowFactory.THING_SHADOW_ERROR_CODE_404)) {
            LOG.error("Received 404 error, disconnecting");
            setTerminationReason(TerminationReason.ERROR);
            notifyOnDisconnected();
        }
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageAcceptedCallback() {
        return this.messageAcceptedCallback;
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageRejectedCallback() {
        return this.messageRejectedCallback;
    }

    private void notifyOnConnected(final CastingDevice castingDevice) {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.ClientThingShadowMessenger.3
            @Override // java.lang.Runnable
            public void run() {
                ClientThingShadowMessenger.this.connectionListener.onConnected(castingDevice);
            }
        });
    }

    private void notifyOnDisconnected() {
        this.backgroundHandler.post(new Runnable() { // from class: com.amazon.music.casting.session.things.ClientThingShadowMessenger.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ClientThingShadowMessenger.this.unsubscribe();
                    ClientThingShadowMessenger.this.connectionListener.onDisconnected(ClientThingShadowMessenger.this.getTerminationReason());
                } catch (AmazonClientException e) {
                    ClientThingShadowMessenger.LOG.error("Failed to unsubscribe gracefully", (Throwable) e);
                    ClientThingShadowMessenger.this.connectionListener.onDisconnected(TerminationReason.ERROR);
                }
            }
        });
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    /* JADX INFO: renamed from: getIdentifier */
    protected String getThingShadowName() {
        return this.clientId;
    }
}
