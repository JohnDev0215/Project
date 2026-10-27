package com.amazon.mp3.stability;

import android.content.Context;
import com.amazon.device.crashmanager.CrashDetailsCollectable;
import com.amazon.mp3.platform.providers.AppConfigProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.platform.data.BugsnagProperties;
import com.amazon.music.platform.providers.StabilityManagerProvider;
import com.bugsnag.android.BreadcrumbType;
import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.Configuration;
import com.bugsnag.android.EndpointConfiguration;
import com.bugsnag.android.okhttp.BugsnagOkHttpPlugin;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: StabilityManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010%\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 $2\u00020\u0001:\u0002$%B\u0007¢\u0006\u0004\b\"\u0010#J\u0012\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\tH\u0002J8\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00132\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011H\u0002J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0016J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u0004H\u0016J$\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000fH\u0016J\u0006\u0010!\u001a\u00020 ¨\u0006&"}, d2 = {"Lcom/amazon/mp3/stability/StabilityManager;", "Lcom/amazon/music/platform/providers/StabilityManagerProvider;", "", "isFireOS", "", "getBugsnagDefaultApiKey", "Landroid/content/Context;", "context", "apiKey", "", "startBugsnag", "Lcom/bugsnag/android/Configuration;", "config", "loadCommonProperties", "initBugsnagUserId", "", "attributes", "", "attributesFilter", "", "getTrimmedAttributes", "attribute", "trimAttribute", "init", "resyncBugsnag", "name", "treatment", "recordFeatureFlag", "message", "leaveBreadcrumbs", "eventName", "leaveMetricBreadcrumbs", "Lcom/amazon/mp3/stability/StabilityManager$StabilityCrashDetailsCollector;", "getStabilityCrashDetailsCollector", "<init>", "()V", "Companion", "StabilityCrashDetailsCollector", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StabilityManager implements StabilityManagerProvider {
    private static String bugsnagUserId;
    private static volatile StabilityManager instance;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ANDROID_BUGSNAG_API_KEY = "45e1202e1cdea3b1a52d70bd9e998540";
    private static final String FIREOS_BUGSNAG_API_KEY = "88f5c4e90fe90ca21c2e9deb96641ddb";
    private static final BugsnagOkHttpPlugin bugsnagOkHttpPlugin = new BugsnagOkHttpPlugin(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
    private static final String TAG = StabilityManager.class.getSimpleName();
    private static final String LEAK = "LEAK";

    /* JADX INFO: compiled from: StabilityManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0011\u0010\u000bR\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\t0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u000bR\u0016\u0010\u0014\u001a\u00020\t8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0014\u0010\u000bR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/stability/StabilityManager$Companion;", "", "Lcom/amazon/mp3/stability/StabilityManager;", "getInstance", "Lcom/bugsnag/android/okhttp/BugsnagOkHttpPlugin;", "bugsnagOkHttpPlugin", "Lcom/bugsnag/android/okhttp/BugsnagOkHttpPlugin;", "getBugsnagOkHttpPlugin", "()Lcom/bugsnag/android/okhttp/BugsnagOkHttpPlugin;", "", "ANDROID_BUGSNAG_API_KEY", "Ljava/lang/String;", "", "ATTRIBUTE_MAX_LENGTH", "I", "BUGSNAG_API_KEY", "FIREOS_BUGSNAG_API_KEY", "LEAK", "kotlin.jvm.PlatformType", "TAG", "bugsnagUserId", "instance", "Lcom/amazon/mp3/stability/StabilityManager;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BugsnagOkHttpPlugin getBugsnagOkHttpPlugin() {
            return StabilityManager.bugsnagOkHttpPlugin;
        }

        public final StabilityManager getInstance() {
            StabilityManager stabilityManager = StabilityManager.instance;
            if (stabilityManager == null) {
                synchronized (this) {
                    stabilityManager = StabilityManager.instance;
                    if (stabilityManager == null) {
                        stabilityManager = new StabilityManager();
                        StabilityManager.instance = stabilityManager;
                    }
                }
            }
            return stabilityManager;
        }
    }

    /* JADX INFO: compiled from: StabilityManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/stability/StabilityManager$StabilityCrashDetailsCollector;", "Lcom/amazon/device/crashmanager/CrashDetailsCollectable;", "()V", "collect", "", "", "ex", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class StabilityCrashDetailsCollector implements CrashDetailsCollectable {
        @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
        public Map<String, String> collect(Throwable ex) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            String str = StabilityManager.bugsnagUserId;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bugsnagUserId");
                str = null;
            }
            linkedHashMap.put("bugsnagUserId", str);
            return linkedHashMap;
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        startBugsnag(context, getBugsnagDefaultApiKey$default(this, false, 1, null));
    }

    static /* synthetic */ String getBugsnagDefaultApiKey$default(StabilityManager stabilityManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = PlatformUtil.isFireOS();
        }
        return stabilityManager.getBugsnagDefaultApiKey(z);
    }

    private final String getBugsnagDefaultApiKey(boolean isFireOS) {
        if (isFireOS) {
            return FIREOS_BUGSNAG_API_KEY;
        }
        return ANDROID_BUGSNAG_API_KEY;
    }

    public final void resyncBugsnag(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Bugsnag.isStarted()) {
            return;
        }
        AppConfigProvider companion = AppConfigProvider.INSTANCE.getInstance(context);
        String bugsnagDefaultApiKey$default = getBugsnagDefaultApiKey$default(this, false, 1, null);
        String strString = companion.getConfiguration().string("bugsnag_api_key", bugsnagDefaultApiKey$default);
        if (Intrinsics.areEqual(bugsnagDefaultApiKey$default, strString)) {
            return;
        }
        startBugsnag(context, strString);
        Log.debug(TAG, "Bugsnag has been resynced with key from remote config");
    }

    public void recordFeatureFlag(String name, String treatment) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(treatment, "treatment");
        Bugsnag.addFeatureFlag(name, treatment);
    }

    public void leaveBreadcrumbs(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Bugsnag.leaveBreadcrumb(message);
    }

    @Override // com.amazon.music.platform.providers.StabilityManagerProvider
    public void leaveMetricBreadcrumbs(String eventName, Map<String, String> attributes) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Map<String, String> trimmedAttributes = getTrimmedAttributes(attributes, StabilityBreadcrumbFilters.INSTANCE.getMETRIC_ATTRIBUTE_ALLOW_LIST().get(eventName));
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%s | %s", Arrays.copyOf(new Object[]{eventName, trimmedAttributes}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        leaveBreadcrumbs(str);
    }

    private final void startBugsnag(Context context, String apiKey) {
        Configuration configuration = new Configuration(apiKey);
        loadCommonProperties(configuration);
        Bugsnag.start(context, configuration);
        initBugsnagUserId();
    }

    private final void loadCommonProperties(Configuration config) {
        BugsnagProperties bugsnagProperties = new BugsnagProperties(null, null, 3, null);
        config.setEndpoints(new EndpointConfiguration(bugsnagProperties.getNotifyEndpoint(), bugsnagProperties.getSessionEndpoint()));
        config.setProjectPackages(BugsnagProjectPackages.INSTANCE.getPackages());
        config.addPlugin(bugsnagOkHttpPlugin);
        config.setEnabledBreadcrumbTypes(SetsKt__SetsKt.setOf((Object[]) new BreadcrumbType[]{BreadcrumbType.NAVIGATION, BreadcrumbType.REQUEST, BreadcrumbType.USER, BreadcrumbType.STATE, BreadcrumbType.ERROR}));
    }

    private final void initBugsnagUserId() {
        try {
            String id = Bugsnag.getUser().getId();
            Intrinsics.checkNotNull(id);
            bugsnagUserId = id;
            Log.debug(TAG, "Bugsnag user id: %s", id);
        } catch (Exception e) {
            Log.error(TAG, "Bugsnag default user Id was null", e);
        }
    }

    public final StabilityCrashDetailsCollector getStabilityCrashDetailsCollector() {
        return new StabilityCrashDetailsCollector();
    }

    private final Map<String, String> getTrimmedAttributes(Map<String, String> attributes, Set<String> attributesFilter) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            boolean z = false;
            if (attributesFilter != null && attributesFilter.contains(key)) {
                z = true;
            }
            if (z) {
                linkedHashMap.put(key, trimAttribute(value));
            }
        }
        return linkedHashMap;
    }

    private final String trimAttribute(String attribute) {
        String strSubstring = attribute.substring(0, RangesKt___RangesKt.coerceAtMost(attribute.length(), 100));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
