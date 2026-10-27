package com.amazon.mp3.amplifyqueue.utils;

import androidx.core.app.NotificationCompat;
import com.BV.LinearGradient.LinearGradientManager;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amplifyframework.api.ApiCategoryConfiguration;
import com.amplifyframework.api.aws.AuthorizationType;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.core.category.CategoryConfiguration;
import com.amplifyframework.core.category.CategoryType;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONObject;

/* JADX INFO: compiled from: AmplifyConfigurationUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/AmplifyConfigurationUtil;", "", "()V", "Companion", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmplifyConfigurationUtil {
    private static ConfigurationProvider _configurationProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String APP_NAME = "myamplifyapp";
    private static final String END_POINT_TYPE_KEY = "endpointType";
    private static final String END_POINT_KEY = IntlConfiguration.ENDPOINT_T;
    private static final String REGION_KEY = "region";
    private static final String AUTH_TYPE_KEY = "authorizationType";
    private static final String API_PLUGIN_KEY = "awsAPIPlugin";

    /* JADX INFO: compiled from: AmplifyConfigurationUtil.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0002J\"\u0010$\u001a\u00020%2\b\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#J\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0006R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006*"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/AmplifyConfigurationUtil$Companion;", "", "()V", "API_PLUGIN_KEY", "", "getAPI_PLUGIN_KEY", "()Ljava/lang/String;", "APP_NAME", "getAPP_NAME", "AUTH_TYPE_KEY", "getAUTH_TYPE_KEY", "CQE_AUTH_THRESHOLD_CONFIG", "CQE_DATASTORE_OPERATION_THRESHOLD_CONFIG", "DEFAULT_THRESHOLD", "", "DEFAULT_TIMEOUT_CONFIG", "END_POINT_KEY", "getEND_POINT_KEY", "END_POINT_TYPE_KEY", "getEND_POINT_TYPE_KEY", "MAX_DEFAULT_TIMEOUT_SEC", "", "PREFIX_TIMEOUT_CONFIG", "REGION_KEY", "getREGION_KEY", "_configurationProvider", "Lcom/amazon/music/platform/providers/ConfigurationProvider;", "configurationProvider", "getConfigurationProvider", "()Lcom/amazon/music/platform/providers/ConfigurationProvider;", "createAmplifyApiPluginConfiguration", "Lcom/amplifyframework/core/category/CategoryConfiguration;", LinearGradientManager.PROP_END_POS, "region", "authType", "Lcom/amplifyframework/api/aws/AuthorizationType;", "createAmplifyConfiguration", "Lcom/amplifyframework/core/AmplifyConfiguration;", "getCQEThresholdConfig", "key", "getTimeoutConfig", NotificationCompat.CATEGORY_EVENT, "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getAPP_NAME() {
            return AmplifyConfigurationUtil.APP_NAME;
        }

        public final String getEND_POINT_TYPE_KEY() {
            return AmplifyConfigurationUtil.END_POINT_TYPE_KEY;
        }

        public final String getEND_POINT_KEY() {
            return AmplifyConfigurationUtil.END_POINT_KEY;
        }

        public final String getREGION_KEY() {
            return AmplifyConfigurationUtil.REGION_KEY;
        }

        public final String getAUTH_TYPE_KEY() {
            return AmplifyConfigurationUtil.AUTH_TYPE_KEY;
        }

        public final String getAPI_PLUGIN_KEY() {
            return AmplifyConfigurationUtil.API_PLUGIN_KEY;
        }

        public final AmplifyConfiguration createAmplifyConfiguration(String endPoint, String region, AuthorizationType authType) {
            Intrinsics.checkNotNullParameter(authType, "authType");
            HashMap map = new HashMap();
            String configurationKey = CategoryType.API.getConfigurationKey();
            Intrinsics.checkNotNullExpressionValue(configurationKey, "API.configurationKey");
            map.put(configurationKey, createAmplifyApiPluginConfiguration(endPoint, region, authType));
            return new AmplifyConfiguration(map, false);
        }

        private final CategoryConfiguration createAmplifyApiPluginConfiguration(String endPoint, String region, AuthorizationType authType) {
            ApiCategoryConfiguration apiCategoryConfiguration = new ApiCategoryConfiguration();
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject2.put(getEND_POINT_TYPE_KEY(), "GraphQL");
            jSONObject2.put(getEND_POINT_KEY(), endPoint);
            jSONObject2.put(getREGION_KEY(), region);
            jSONObject2.put(getAUTH_TYPE_KEY(), authType.name());
            jSONObject.put(getAPP_NAME(), jSONObject2);
            jSONObject3.put(getAPI_PLUGIN_KEY(), jSONObject);
            apiCategoryConfiguration.populateFromJSON(new JSONObject().put("plugins", jSONObject3));
            return apiCategoryConfiguration;
        }

        public final ConfigurationProvider getConfigurationProvider() {
            if (AmplifyConfigurationUtil._configurationProvider == null) {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof ConfigurationProvider)) {
                    interfaceProvider = null;
                }
                AmplifyConfigurationUtil._configurationProvider = (ConfigurationProvider) interfaceProvider;
            }
            return AmplifyConfigurationUtil._configurationProvider;
        }

        public final long getTimeoutConfig(String event) {
            int iIntValue;
            Intrinsics.checkNotNullParameter(event, "event");
            ConfigurationProvider configurationProvider = getConfigurationProvider();
            Integer numValueOf = configurationProvider == null ? null : Integer.valueOf(configurationProvider.getIntForKey(Intrinsics.stringPlus("amplifyqueue_timeout_", event)));
            if (numValueOf == null || numValueOf.intValue() == 0) {
                ConfigurationProvider configurationProvider2 = getConfigurationProvider();
                numValueOf = configurationProvider2 != null ? Integer.valueOf(configurationProvider2.getIntForKey("amplifyqueue_timeout_default")) : null;
            }
            if (numValueOf == null || (iIntValue = numValueOf.intValue()) == 0) {
                return 5L;
            }
            return iIntValue;
        }

        public final int getCQEThresholdConfig(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            ConfigurationProvider configurationProvider = getConfigurationProvider();
            Integer numValueOf = configurationProvider == null ? null : Integer.valueOf(configurationProvider.getIntForKey(key));
            if (numValueOf == null) {
                numValueOf = 25;
            }
            return numValueOf.intValue();
        }
    }
}
