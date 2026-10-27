package com.amazon.mp3.skiplimit.impl;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.casting.CastingViewsFactory;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.skiplimit.ErrorType;
import com.amazon.mp3.skiplimit.SkipLimitController;
import com.amazon.mp3.skiplimit.SkipLimitRefreshSource;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.Configuration;
import com.amazon.music.casting.session.iot.IotConnectionConfig;
import com.amazon.music.casting.session.iot.IotConnectionManager;
import com.amazon.music.casting.session.iot.MessengerType;
import com.amazon.music.castingviews.Configuration;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IoTRefreshStrategy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 42\u00020\u0001:\u00014B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010'\u001a\u00020(H\u0016J(\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0002J\b\u0010.\u001a\u00020/H\u0002J\u0006\u00100\u001a\u000201J\b\u00102\u001a\u00020(H\u0016J\b\u00103\u001a\u00020(H\u0016R\u0016\u0010\u000b\u001a\n \f*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/IoTRefreshStrategy;", "Lcom/amazon/mp3/skiplimit/SkipLimitRefreshSource;", "skipLimitController", "Lcom/amazon/mp3/skiplimit/SkipLimitController;", "context", "Landroid/content/Context;", "config", "Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;", "thingShadowName", "", "(Lcom/amazon/mp3/skiplimit/SkipLimitController;Landroid/content/Context;Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;Ljava/lang/String;)V", "TAG", "kotlin.jvm.PlatformType", "getConfig", "()Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;", "getContext", "()Landroid/content/Context;", "iotConnectionConfig", "Lcom/amazon/music/casting/session/iot/IotConnectionConfig;", "getIotConnectionConfig", "()Lcom/amazon/music/casting/session/iot/IotConnectionConfig;", "setIotConnectionConfig", "(Lcom/amazon/music/casting/session/iot/IotConnectionConfig;)V", "manager", "Lcom/amazon/music/casting/session/iot/IotConnectionManager;", "getManager", "()Lcom/amazon/music/casting/session/iot/IotConnectionManager;", "setManager", "(Lcom/amazon/music/casting/session/iot/IotConnectionManager;)V", "reconnectAttempts", "", "getSkipLimitController", "()Lcom/amazon/mp3/skiplimit/SkipLimitController;", "skipLimitMessenger", "Lcom/amazon/mp3/skiplimit/impl/SkiplimitShadowMessenger;", "getSkipLimitMessenger", "()Lcom/amazon/mp3/skiplimit/impl/SkiplimitShadowMessenger;", "setSkipLimitMessenger", "(Lcom/amazon/mp3/skiplimit/impl/SkiplimitShadowMessenger;)V", "clearSession", "", "createSkipLimitIotConfiguration", "iotEndPoint", "identityPoolId", "clientId", "region", "getStatusCallback", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttClientStatusCallback;", "isConfigValid", "", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "terminate", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IoTRefreshStrategy implements SkipLimitRefreshSource {
    private static final int DEFAULT_SKIPLIMIT_MAX_DEVICE_OFFLINE_TIMEOUT_SECONDS = 60;
    private static final int DEFAULT_SKIPLIMIT_MAX_RECONNECT_INTERVAL_SECONDS = 5;
    private static final int DEFAULT_SKIPLIMIT_MIN_RECONNECT_INTERVAL_SECONDS = 5;
    private static final int DEFAULT_SKIPLIMIT_RECONNECT_ATTEMPTS_THRESHOLD = 2;
    public static final String REFRESH_REASON = "IoT refreshed update.";
    private final String TAG;
    private final ConnectionInfoMetadata config;
    private final Context context;
    public IotConnectionConfig iotConnectionConfig;
    public IotConnectionManager manager;
    private int reconnectAttempts;
    private final SkipLimitController skipLimitController;
    public SkiplimitShadowMessenger skipLimitMessenger;
    private final String thingShadowName;
    private static final int DEFAULT_SKIPLIMIT_MAX_TIMEOUT_SECONDS = (int) TimeUnit.MINUTES.toSeconds(30);

    public IoTRefreshStrategy(SkipLimitController skipLimitController, Context context, ConnectionInfoMetadata config, String str) {
        Intrinsics.checkNotNullParameter(skipLimitController, "skipLimitController");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.skipLimitController = skipLimitController;
        this.context = context;
        this.config = config;
        this.thingShadowName = str;
        this.TAG = IoTRefreshStrategy.class.getSimpleName();
    }

    public final ConnectionInfoMetadata getConfig() {
        return this.config;
    }

    public final Context getContext() {
        return this.context;
    }

    public SkipLimitController getSkipLimitController() {
        return this.skipLimitController;
    }

    public final IotConnectionConfig getIotConnectionConfig() {
        IotConnectionConfig iotConnectionConfig = this.iotConnectionConfig;
        if (iotConnectionConfig != null) {
            return iotConnectionConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("iotConnectionConfig");
        return null;
    }

    public final void setIotConnectionConfig(IotConnectionConfig iotConnectionConfig) {
        Intrinsics.checkNotNullParameter(iotConnectionConfig, "<set-?>");
        this.iotConnectionConfig = iotConnectionConfig;
    }

    public final IotConnectionManager getManager() {
        IotConnectionManager iotConnectionManager = this.manager;
        if (iotConnectionManager != null) {
            return iotConnectionManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("manager");
        return null;
    }

    public final void setManager(IotConnectionManager iotConnectionManager) {
        Intrinsics.checkNotNullParameter(iotConnectionManager, "<set-?>");
        this.manager = iotConnectionManager;
    }

    public final SkiplimitShadowMessenger getSkipLimitMessenger() {
        SkiplimitShadowMessenger skiplimitShadowMessenger = this.skipLimitMessenger;
        if (skiplimitShadowMessenger != null) {
            return skiplimitShadowMessenger;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skipLimitMessenger");
        return null;
    }

    public final void setSkipLimitMessenger(SkiplimitShadowMessenger skiplimitShadowMessenger) {
        Intrinsics.checkNotNullParameter(skiplimitShadowMessenger, "<set-?>");
        this.skipLimitMessenger = skiplimitShadowMessenger;
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitRefreshSource
    public void start() {
        if (this.config.getClientId() == null || this.config.getIdentityPoolId() == null || this.config.getIotEndPoint() == null || this.config.getRegion() == null || TextUtils.isEmpty(this.thingShadowName)) {
            getSkipLimitController().onRefreshError(ErrorType.IoT, this.context);
            return;
        }
        setIotConnectionConfig(createSkipLimitIotConfiguration(this.config.getIotEndPoint(), this.config.getIdentityPoolId(), this.config.getClientId(), this.config.getRegion()));
        Configuration configurationBuild = CastingViewsFactory.createCastingConfiguration(this.context).build();
        com.amazon.music.casting.Configuration castingServiceConfig = new Configuration.Builder().withCastingSessionConfig(configurationBuild.getCastingSessionConfig()).withMarketplace(configurationBuild.getMarketplace()).withRequestInterceptor(configurationBuild.getRequestInterceptor()).build();
        IotConnectionManager.Companion companion = IotConnectionManager.INSTANCE;
        Context context = this.context;
        IotConnectionConfig iotConnectionConfig = getIotConnectionConfig();
        Intrinsics.checkNotNullExpressionValue(castingServiceConfig, "castingServiceConfig");
        setManager(companion.getInstance(context, iotConnectionConfig, castingServiceConfig));
        SkipLimitController skipLimitController = getSkipLimitController();
        AWSIotMqttManager iotMqttManager = getManager().getIotMqttManager();
        String clientId = getIotConnectionConfig().getClientId();
        String str = this.thingShadowName;
        Intrinsics.checkNotNull(str);
        Handler backgroundHandler = AmazonApplication.getBackgroundHandler();
        Intrinsics.checkNotNullExpressionValue(backgroundHandler, "getBackgroundHandler()");
        setSkipLimitMessenger(new SkiplimitShadowMessenger(skipLimitController, iotMqttManager, clientId, null, str, backgroundHandler, null, this.context));
        getManager().registerStatusCallback(MessengerType.SKIP_LIMIT, getStatusCallback());
        getManager().connect();
    }

    public final boolean isConfigValid() {
        ConnectionInfoMetadata connectionInfoMetadata = this.config;
        return ((connectionInfoMetadata == null ? null : connectionInfoMetadata.getClientId()) == null || this.config.getIdentityPoolId() == null || this.config.getIotEndPoint() == null || this.config.getRegion() == null || TextUtils.isEmpty(this.thingShadowName)) ? false : true;
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitRefreshSource
    public void terminate() {
        if (isConfigValid()) {
            getSkipLimitMessenger().unsubscribe();
            getManager().disconnect(MessengerType.SKIP_LIMIT);
        }
    }

    private final IotConnectionConfig createSkipLimitIotConfiguration(String iotEndPoint, String identityPoolId, String clientId, String region) {
        return new IotConnectionConfig.Builder(DEFAULT_SKIPLIMIT_MAX_TIMEOUT_SECONDS, 60, 5, 5, 2, iotEndPoint, identityPoolId, clientId, region).build();
    }

    private final AWSIotMqttClientStatusCallback getStatusCallback() {
        return new AWSIotMqttClientStatusCallback() { // from class: com.amazon.mp3.skiplimit.impl.IoTRefreshStrategy$$ExternalSyntheticLambda0
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
            public final void onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus aWSIotMqttClientStatus, Throwable th) {
                IoTRefreshStrategy.m1634getStatusCallback$lambda0(this.f$0, aWSIotMqttClientStatus, th);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getStatusCallback$lambda-0, reason: not valid java name */
    public static final void m1634getStatusCallback$lambda0(IoTRefreshStrategy this$0, AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus aWSIotMqttClientStatus, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (th != null) {
            Log.error(this$0.TAG, "Get error from AWS IoT, error: " + th + " status:" + aWSIotMqttClientStatus);
        }
        if (aWSIotMqttClientStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {
            Log.info(this$0.TAG, "Connected to AWS IoT");
            this$0.getSkipLimitMessenger().subscribe(true);
        }
        if (aWSIotMqttClientStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting) {
            int i = this$0.reconnectAttempts + 1;
            this$0.reconnectAttempts = i;
            Log.info(this$0.TAG, Intrinsics.stringPlus("Reconnecting to AWS IoT, reconnect attempts: ", Integer.valueOf(i)));
            if (this$0.reconnectAttempts >= 2) {
                this$0.terminate();
                this$0.getSkipLimitController().onRefreshError(ErrorType.IoT, this$0.context);
            }
        }
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitRefreshSource
    public void clearSession() {
        getManager().disconnect(MessengerType.SKIP_LIMIT);
    }
}
