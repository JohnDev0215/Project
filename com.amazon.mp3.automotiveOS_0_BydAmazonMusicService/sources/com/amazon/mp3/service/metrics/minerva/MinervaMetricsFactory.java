package com.amazon.mp3.service.metrics.minerva;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.AmazonMinervaAndroidClientBuilder;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;
import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.BuildInfoProvider;
import java.util.Date;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: MinervaMetricsFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003#$%B\t\b\u0002¢\u0006\u0004\b!\u0010\"J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J3\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\"\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u000bJ\u001e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0007J\b\u0010\u000f\u001a\u00020\u0004H\u0007R\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u00060\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001d\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010 \u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006&"}, d2 = {"Lcom/amazon/mp3/service/metrics/minerva/MinervaMetricsFactory;", "", "Lcom/amazon/minerva/client/thirdparty/api/MetricEvent;", NotificationCompat.CATEGORY_EVENT, "", "record", "", "metricGroupId", "schemaId", "", "paramList", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V", "", "keyList", "addKeys", "shutdown", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "buildInfoProvider$delegate", "Lkotlin/Lazy;", "getBuildInfoProvider", "()Lcom/amazon/music/platform/providers/BuildInfoProvider;", "buildInfoProvider", "Lcom/amazon/minerva/client/thirdparty/api/AmazonMinerva;", "minervaClient$delegate", "getMinervaClient", "()Lcom/amazon/minerva/client/thirdparty/api/AmazonMinerva;", "minervaClient", "getSoftwareVersion", "()Ljava/lang/String;", "softwareVersion", "<init>", "()V", "DefaultUserControlVerifier", "KidsProfileVerifier", "MAPOAuthProvider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class MinervaMetricsFactory {
    public static final MinervaMetricsFactory INSTANCE = new MinervaMetricsFactory();
    private static final String TAG = MinervaMetricsFactory.class.getSimpleName();

    /* JADX INFO: renamed from: buildInfoProvider$delegate, reason: from kotlin metadata */
    private static final Lazy buildInfoProvider = LazyKt__LazyJVMKt.lazy(new Function0<BuildInfoProvider>() { // from class: com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory$buildInfoProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BuildInfoProvider invoke() {
            return (BuildInfoProvider) Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(BuildInfoProvider.class));
        }
    });

    /* JADX INFO: renamed from: minervaClient$delegate, reason: from kotlin metadata */
    private static final Lazy minervaClient = LazyKt__LazyJVMKt.lazy(new Function0<AmazonMinerva>() { // from class: com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory$minervaClient$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AmazonMinerva invoke() {
            Context context = AmazonApplication.getApplication().getApplicationContext();
            AmazonMinervaAndroidClientBuilder amazonMinervaAndroidClientBuilderWithDeviceType = AmazonMinervaAndroidClientBuilder.standard(context).withRegion("us-east-1").withDeviceType(AccountCredentialStorage.get(context).getDeviceType());
            Intrinsics.checkNotNullExpressionValue(context, "context");
            return amazonMinervaAndroidClientBuilderWithDeviceType.withOAuthProvider(new MinervaMetricsFactory.MAPOAuthProvider(context)).withChildProfileVerifier(new MinervaMetricsFactory.KidsProfileVerifier()).withUserControlVerifier(new MinervaMetricsFactory.DefaultUserControlVerifier()).build();
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MinervaMetricsFactory.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/service/metrics/minerva/MinervaMetricsFactory$DefaultUserControlVerifier;", "Lcom/amazon/minerva/client/thirdparty/compliance/UserControlVerifier;", "()V", "isUsageCollectionEnabled", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class DefaultUserControlVerifier implements UserControlVerifier {
        @Override // com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier
        public boolean isUsageCollectionEnabled() throws Exception {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MinervaMetricsFactory.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/service/metrics/minerva/MinervaMetricsFactory$KidsProfileVerifier;", "Lcom/amazon/minerva/client/thirdparty/compliance/ChildProfileVerifier;", "()V", "isChildProfile", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class KidsProfileVerifier implements ChildProfileVerifier {
        @Override // com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier
        public boolean isChildProfile() throws Exception {
            return false;
        }
    }

    private MinervaMetricsFactory() {
    }

    private final BuildInfoProvider getBuildInfoProvider() {
        return (BuildInfoProvider) buildInfoProvider.getValue();
    }

    private final String getSoftwareVersion() {
        String appVersion;
        BuildInfoProvider buildInfoProvider2 = getBuildInfoProvider();
        return (buildInfoProvider2 == null || (appVersion = buildInfoProvider2.getAppVersion()) == null) ? "--" : appVersion;
    }

    private final AmazonMinerva getMinervaClient() {
        Object value = minervaClient.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-minervaClient>(...)");
        return (AmazonMinerva) value;
    }

    @JvmStatic
    public static final void record(MetricEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        INSTANCE.getMinervaClient().record(event);
    }

    @JvmStatic
    public static final void record(String metricGroupId, String schemaId, Object... paramList) {
        Intrinsics.checkNotNullParameter(metricGroupId, "metricGroupId");
        Intrinsics.checkNotNullParameter(schemaId, "schemaId");
        Intrinsics.checkNotNullParameter(paramList, "paramList");
        try {
            INSTANCE.getMinervaClient().record(addKeys(new MetricEvent(metricGroupId, schemaId), ArraysKt___ArraysJvmKt.asList(paramList)));
        } catch (Exception e) {
            Log.error(TAG, "Record for metricGroupId:" + metricGroupId + ", schemaId:" + schemaId + " failed with error: " + e);
        }
    }

    @JvmStatic
    public static final MetricEvent addKeys(MetricEvent event, List<? extends Object> keyList) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(keyList, "keyList");
        for (List list : CollectionsKt___CollectionsKt.chunked(keyList, 2)) {
            if (list.size() != 2) {
                Log.warning(TAG, "addKeys require an even number size of keyList");
            } else {
                Object obj = list.get(0);
                Object obj2 = list.get(1);
                if (obj2 instanceof Boolean) {
                    event.addBoolean(obj.toString(), ((Boolean) obj2).booleanValue());
                } else if (obj2 instanceof Long) {
                    event.addLong(obj.toString(), ((Number) obj2).longValue());
                } else if (obj2 instanceof Double) {
                    event.addDouble(obj.toString(), ((Number) obj2).doubleValue());
                } else if (obj2 instanceof String) {
                    event.addString(obj.toString(), (String) obj2);
                } else if (obj2 instanceof Date) {
                    event.addTimestamp(obj.toString(), (Date) obj2);
                } else {
                    Log.warning(TAG, Intrinsics.stringPlus("addKeys unsupported type ", obj2));
                }
            }
        }
        event.addPredefined(Predefined.PLATFORM);
        event.addString("SoftwareVersion", INSTANCE.getSoftwareVersion());
        return event;
    }

    @JvmStatic
    public static final void shutdown() {
        INSTANCE.getMinervaClient().shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MinervaMetricsFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/service/metrics/minerva/MinervaMetricsFactory$MAPOAuthProvider;", "Lcom/amazon/minerva/client/thirdparty/transport/OAuthProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAccessToken", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class MAPOAuthProvider implements OAuthProvider {
        private final Context context;

        public MAPOAuthProvider(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override // com.amazon.minerva.client.thirdparty.transport.OAuthProvider
        public String getAccessToken() throws Exception {
            String account = new MAPAccountManager(this.context).getAccount();
            if (TextUtils.isEmpty(account)) {
                Log.info(MinervaMetricsFactory.TAG, "==> Cannot get Access Token, will use Unauthenticated Metrics");
                throw new Exception("Cannot get access token");
            }
            Bundle bundle = new TokenManagement(this.context).getToken(account, TokenKeys.getAccessTokenKeyForPackage(this.context.getPackageName()), null, null).get();
            Log.info(MinervaMetricsFactory.TAG, "==> Got Access Token for Authenticated Metrics");
            return String.valueOf(bundle.getString("value_key"));
        }
    }
}
