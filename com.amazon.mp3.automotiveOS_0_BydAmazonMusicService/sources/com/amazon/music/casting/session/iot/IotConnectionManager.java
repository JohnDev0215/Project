package com.amazon.music.casting.session.iot;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.music.casting.CastingService;
import com.amazon.music.casting.Configuration;
import com.amazon.music.casting.HttpCastingService;
import com.amazon.music.casting.providers.AWSCastingAuthenticationProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.regions.Regions;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: IotConnectionManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0016\u001a\u00020\u0017J\u001c\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002J\u001f\u0010\u001c\u001a\u00020\u00172\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001e\"\u00020\u0014¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0015H\u0002J\u0016\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/music/casting/session/iot/IotConnectionManager;", "", "context", "Landroid/content/Context;", "iotConnectionConfig", "Lcom/amazon/music/casting/session/iot/IotConnectionConfig;", "castingServiceConfiguration", "Lcom/amazon/music/casting/Configuration;", "(Landroid/content/Context;Lcom/amazon/music/casting/session/iot/IotConnectionConfig;Lcom/amazon/music/casting/Configuration;)V", "credentialsProvider", "Lcom/amazonaws/auth/CognitoCachingCredentialsProvider;", "<set-?>", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttManager;", "iotMqttManager", "getIotMqttManager", "()Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttManager;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/casting/CastingService;", "statusCallbackMap", "", "Lcom/amazon/music/casting/session/iot/MessengerType;", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttClientStatusCallback;", "connect", "", "createCredentialsProvider", "identityPoolId", "", "region", "disconnect", "messengerTypes", "", "([Lcom/amazon/music/casting/session/iot/MessengerType;)V", "getStatusCallback", "registerStatusCallback", "messengerType", "callback", "Companion", "DMMCasting_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IotConnectionManager {
    private static volatile IotConnectionManager INSTANCE;
    private final Configuration castingServiceConfiguration;
    private final Context context;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private final IotConnectionConfig iotConnectionConfig;
    private AWSIotMqttManager iotMqttManager;
    private CastingService service;
    private final Map<MessengerType, AWSIotMqttClientStatusCallback> statusCallbackMap;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) IotConnectionManager.class);
    private static final List<MessengerType> supportedMessengerTypes = CollectionsKt__CollectionsKt.listOf((Object[]) new MessengerType[]{MessengerType.CLIENT, MessengerType.NOW_PLAYING, MessengerType.SKIP_LIMIT});

    public /* synthetic */ IotConnectionManager(Context context, IotConnectionConfig iotConnectionConfig, Configuration configuration, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, iotConnectionConfig, configuration);
    }

    @JvmStatic
    public static final IotConnectionManager getInstance(Context context, IotConnectionConfig iotConnectionConfig, Configuration configuration) {
        return INSTANCE.getInstance(context, iotConnectionConfig, configuration);
    }

    @JvmStatic
    public static final void reset() {
        INSTANCE.reset();
    }

    private IotConnectionManager(Context context, IotConnectionConfig iotConnectionConfig, Configuration configuration) {
        this.context = context;
        this.iotConnectionConfig = iotConnectionConfig;
        this.castingServiceConfiguration = configuration;
        this.statusCallbackMap = new LinkedHashMap();
        AWSIotMqttManager aWSIotMqttManager = new AWSIotMqttManager(iotConnectionConfig.getClientId(), iotConnectionConfig.getIotEndpoint());
        this.iotMqttManager = aWSIotMqttManager;
        aWSIotMqttManager.setAutoResubscribe(false);
        this.iotMqttManager.setReconnectRetryLimits(iotConnectionConfig.getMinReconnectIntervalSeconds(), iotConnectionConfig.getMaxReconnectIntervalSeconds());
        this.iotMqttManager.setMaxAutoReconnectAttempts(iotConnectionConfig.getMaxReconnectIntervalSeconds() != 0 ? iotConnectionConfig.getMaxTimeoutSeconds() / iotConnectionConfig.getMaxReconnectIntervalSeconds() : 1);
    }

    public final AWSIotMqttManager getIotMqttManager() {
        return this.iotMqttManager;
    }

    public final void connect() {
        try {
            if (this.credentialsProvider == null) {
                this.credentialsProvider = createCredentialsProvider(this.iotConnectionConfig.getIdentityPoolId(), this.iotConnectionConfig.getRegion());
            }
            this.iotMqttManager.connect(this.credentialsProvider, getStatusCallback());
        } catch (Exception e) {
            LOG.error("connect failed", (Throwable) e);
        }
    }

    private final AWSIotMqttClientStatusCallback getStatusCallback() {
        return new AWSIotMqttClientStatusCallback() { // from class: com.amazon.music.casting.session.iot.IotConnectionManager.getStatusCallback.1
            @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
            public void onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus status, Throwable throwable) {
                Intrinsics.checkNotNullParameter(status, "status");
                if (throwable != null) {
                    IotConnectionManager.LOG.error("Get error from AWS IoT, error: " + throwable + " status:" + status);
                }
                List<MessengerType> list = IotConnectionManager.supportedMessengerTypes;
                IotConnectionManager iotConnectionManager = IotConnectionManager.this;
                for (MessengerType messengerType : list) {
                    AWSIotMqttClientStatusCallback aWSIotMqttClientStatusCallback = (AWSIotMqttClientStatusCallback) iotConnectionManager.statusCallbackMap.get(messengerType);
                    if (aWSIotMqttClientStatusCallback != null) {
                        IotConnectionManager.LOG.info(messengerType + " onStatusChanged status=" + status + " throwable=" + throwable);
                        aWSIotMqttClientStatusCallback.onStatusChanged(status, throwable);
                    }
                }
            }
        };
    }

    public final void disconnect(MessengerType... messengerTypes) {
        Intrinsics.checkNotNullParameter(messengerTypes, "messengerTypes");
        Map<MessengerType, AWSIotMqttClientStatusCallback> map = this.statusCallbackMap;
        int length = messengerTypes.length;
        int i = 0;
        while (i < length) {
            MessengerType messengerType = messengerTypes[i];
            i++;
            map.remove(messengerType);
        }
        if (this.statusCallbackMap.isEmpty()) {
            this.statusCallbackMap.clear();
            try {
                this.iotMqttManager.disconnect();
            } catch (Exception e) {
                LOG.error(Intrinsics.stringPlus("disconnect failed, messengerTypes=", messengerTypes), (Throwable) e);
            }
            this.credentialsProvider = null;
            this.service = null;
        }
    }

    public final void registerStatusCallback(MessengerType messengerType, AWSIotMqttClientStatusCallback callback) {
        Intrinsics.checkNotNullParameter(messengerType, "messengerType");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.statusCallbackMap.put(messengerType, callback);
    }

    private final CognitoCachingCredentialsProvider createCredentialsProvider(String identityPoolId, String region) {
        Regions regionsFromName = Regions.fromName(region);
        if (this.service == null) {
            this.service = new HttpCastingService(this.castingServiceConfiguration);
        }
        return new CognitoCachingCredentialsProvider(this.context, new AWSCastingAuthenticationProvider(this.service, null, identityPoolId, regionsFromName), regionsFromName);
    }

    /* JADX INFO: compiled from: IotConnectionManager.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/casting/session/iot/IotConnectionManager$Companion;", "", "()V", "INSTANCE", "Lcom/amazon/music/casting/session/iot/IotConnectionManager;", "LOG", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "supportedMessengerTypes", "", "Lcom/amazon/music/casting/session/iot/MessengerType;", "getInstance", "context", "Landroid/content/Context;", "iotConnectionConfig", "Lcom/amazon/music/casting/session/iot/IotConnectionConfig;", "castingServiceConfiguration", "Lcom/amazon/music/casting/Configuration;", "reset", "", "DMMCasting_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final IotConnectionManager getInstance(Context context, IotConnectionConfig iotConnectionConfig, Configuration castingServiceConfiguration) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(iotConnectionConfig, "iotConnectionConfig");
            Intrinsics.checkNotNullParameter(castingServiceConfiguration, "castingServiceConfiguration");
            IotConnectionManager iotConnectionManager = IotConnectionManager.INSTANCE;
            if (iotConnectionManager == null) {
                synchronized (this) {
                    iotConnectionManager = IotConnectionManager.INSTANCE;
                    if (iotConnectionManager == null) {
                        iotConnectionManager = new IotConnectionManager(context, iotConnectionConfig, castingServiceConfiguration, null);
                        IotConnectionManager.INSTANCE = iotConnectionManager;
                    }
                }
            }
            return iotConnectionManager;
        }

        @JvmStatic
        public final void reset() {
            IotConnectionManager iotConnectionManager = IotConnectionManager.INSTANCE;
            if (iotConnectionManager != null) {
                iotConnectionManager.disconnect(MessengerType.CLIENT, MessengerType.NOW_PLAYING, MessengerType.SKIP_LIMIT);
            }
            IotConnectionManager.INSTANCE = null;
        }
    }
}
