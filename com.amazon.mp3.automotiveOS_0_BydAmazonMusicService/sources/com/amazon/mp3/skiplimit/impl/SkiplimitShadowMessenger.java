package com.amazon.mp3.skiplimit.impl;

import android.content.Context;
import android.os.Handler;
import com.amazon.mp3.skiplimit.ErrorType;
import com.amazon.mp3.skiplimit.SkipLimitController;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.things.AbstractThingShadowMessenger;
import com.amazon.music.casting.session.things.ThingShadowFactory;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.google.gson.Gson;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkiplimitShadowMessenger.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 +2\u00020\u0001:\u0001+BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u0010\u001b\u001a\u00020\u0007H\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001dH\u0014J\b\u0010\u001f\u001a\u00020\u001dH\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0012H\u0002J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020!H\u0016J\u0017\u0010'\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016¢\u0006\u0002\u0010*R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006,"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkiplimitShadowMessenger;", "Lcom/amazon/music/casting/session/things/AbstractThingShadowMessenger;", "skipLimitController", "Lcom/amazon/mp3/skiplimit/SkipLimitController;", "awsIotMqttManager", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttManager;", "clientId", "", "targetId", "thingShadowName", "backgroundHandler", "Landroid/os/Handler;", "castingSessionConfig", "Lcom/amazon/music/casting/session/CastingSessionConfig;", "context", "Landroid/content/Context;", "(Lcom/amazon/mp3/skiplimit/SkipLimitController;Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttManager;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Handler;Lcom/amazon/music/casting/session/CastingSessionConfig;Landroid/content/Context;)V", "MINUTE_IN_MILLIS", "", "QOS", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttQos;", "getContext", "()Landroid/content/Context;", "getSkipLimitController", "()Lcom/amazon/mp3/skiplimit/SkipLimitController;", "getThingShadowName", "()Ljava/lang/String;", "getIdentifier", "getMessageAcceptedCallback", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttNewMessageCallback;", "getMessageDocumentsCallback", "getMessageRejectedCallback", "handleTSUpdate", "", "skipLimitCount", "subscribe", "getState", "", "unsubscribe", "updateVersion", "currentVersion", "", "(Ljava/lang/Long;)Z", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkiplimitShadowMessenger extends AbstractThingShadowMessenger {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private final int MINUTE_IN_MILLIS;
    private final AWSIotMqttQos QOS;
    private final Context context;
    private final SkipLimitController skipLimitController;
    private final String thingShadowName;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMessageDocumentsCallback$lambda-2, reason: not valid java name */
    public static final void m1638getMessageDocumentsCallback$lambda2(String str, byte[] bArr) {
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageDocumentsCallback() {
        return new AWSIotMqttNewMessageCallback() { // from class: com.amazon.mp3.skiplimit.impl.SkiplimitShadowMessenger$$ExternalSyntheticLambda0
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public final void onMessageArrived(String str, byte[] bArr) {
                SkiplimitShadowMessenger.m1638getMessageDocumentsCallback$lambda2(str, bArr);
            }
        };
    }

    public final SkipLimitController getSkipLimitController() {
        return this.skipLimitController;
    }

    public final String getThingShadowName() {
        return this.thingShadowName;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkiplimitShadowMessenger(SkipLimitController skipLimitController, AWSIotMqttManager awsIotMqttManager, String str, String str2, String thingShadowName, Handler backgroundHandler, CastingSessionConfig castingSessionConfig, Context context) {
        super(awsIotMqttManager, str, str2, backgroundHandler, castingSessionConfig);
        Intrinsics.checkNotNullParameter(skipLimitController, "skipLimitController");
        Intrinsics.checkNotNullParameter(awsIotMqttManager, "awsIotMqttManager");
        Intrinsics.checkNotNullParameter(thingShadowName, "thingShadowName");
        Intrinsics.checkNotNullParameter(backgroundHandler, "backgroundHandler");
        Intrinsics.checkNotNullParameter(context, "context");
        this.skipLimitController = skipLimitController;
        this.thingShadowName = thingShadowName;
        this.context = context;
        this.QOS = AWSIotMqttQos.QOS0;
        this.MINUTE_IN_MILLIS = HardCodedRefreshStrategy.MS_IN_MINUTE;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: SkiplimitShadowMessenger.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/SkiplimitShadowMessenger$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return SkiplimitShadowMessenger.TAG;
        }
    }

    static {
        String name = SkiplimitShadowMessenger.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "SkiplimitShadowMessenger::class.java.name");
        TAG = name;
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageRejectedCallback() {
        return new AWSIotMqttNewMessageCallback() { // from class: com.amazon.mp3.skiplimit.impl.SkiplimitShadowMessenger$$ExternalSyntheticLambda2
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public final void onMessageArrived(String str, byte[] bArr) {
                SkiplimitShadowMessenger.m1639getMessageRejectedCallback$lambda0(this.f$0, str, bArr);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMessageRejectedCallback$lambda-0, reason: not valid java name */
    public static final void m1639getMessageRejectedCallback$lambda0(SkiplimitShadowMessenger this$0, String str, byte[] bArr) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.skipLimitController.onRefreshError(ErrorType.IoT, this$0.context);
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    /* JADX INFO: renamed from: getIdentifier, reason: from getter */
    protected String getThingShadowName() {
        return this.thingShadowName;
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    protected AWSIotMqttNewMessageCallback getMessageAcceptedCallback() {
        return new AWSIotMqttNewMessageCallback() { // from class: com.amazon.mp3.skiplimit.impl.SkiplimitShadowMessenger$$ExternalSyntheticLambda1
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
            public final void onMessageArrived(String str, byte[] bArr) {
                SkiplimitShadowMessenger.m1637getMessageAcceptedCallback$lambda1(this.f$0, str, bArr);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMessageAcceptedCallback$lambda-1, reason: not valid java name */
    public static final void m1637getMessageAcceptedCallback$lambda1(SkiplimitShadowMessenger this$0, String str, byte[] data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = TAG;
        Log.info(str2, "onMessageArrived from topic: " + ((Object) str) + " data: " + data);
        try {
            Intrinsics.checkNotNullExpressionValue(data, "data");
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            String str3 = new String(data, UTF_8);
            Log.info(str2, "onMessageArrived from topic: " + ((Object) str) + " dataDeserialized: " + str3);
            SkipLimitThingsShadow skipLimitThingsShadow = (SkipLimitThingsShadow) new Gson().fromJson(str3, SkipLimitThingsShadow.class);
            Log.info(str2, Intrinsics.stringPlus("SkipLimitThingShadow received: ", skipLimitThingsShadow));
            if (Calendar.getInstance().getTimeInMillis() - this$0.skipLimitController.getLastLocalSkipLimitUpdate() < this$0.MINUTE_IN_MILLIS) {
                Log.info(str2, Intrinsics.stringPlus("Preferring cached skip limit over remote: ", Integer.valueOf(this$0.skipLimitController.getSkipLimitProvider().getCount())));
            } else {
                Log.info(str2, Intrinsics.stringPlus("Preferring remote skip limit over cached: ", Integer.valueOf(skipLimitThingsShadow.getState().getReported().getSkips().getRemaining())));
                this$0.handleTSUpdate(skipLimitThingsShadow.getState().getReported().getSkips().getRemaining());
            }
        } catch (Exception e) {
            Log.error(TAG, "Received exception while executing onMessageArrived or parsing skip limit data ", e);
            this$0.skipLimitController.onRefreshError(ErrorType.IoT, this$0.context);
        }
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public void subscribe(boolean getState) {
        super.subscribe(getState);
        this.awsIotMqttManager.subscribeToTopic(ThingShadowFactory.getUpdateAcceptedTopic(getThingShadowName()), this.QOS, getMessageAcceptedCallback());
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public boolean updateVersion(Long currentVersion) {
        return super.updateVersion(currentVersion);
    }

    @Override // com.amazon.music.casting.session.things.AbstractThingShadowMessenger
    public void unsubscribe() {
        try {
            super.unsubscribe();
            this.awsIotMqttManager.unsubscribeTopic(ThingShadowFactory.getUpdateAcceptedTopic(getThingShadowName()));
        } catch (AmazonClientException e) {
            Log.error(TAG, "Failed to unsubscribe gracefully", e);
        }
    }

    private final void handleTSUpdate(int skipLimitCount) {
        this.skipLimitController.onUpdateCount(skipLimitCount, IoTRefreshStrategy.REFRESH_REASON, this.context);
    }
}
