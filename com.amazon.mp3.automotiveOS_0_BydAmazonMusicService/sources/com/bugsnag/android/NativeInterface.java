package com.bugsnag.android;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.JsonHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class NativeInterface {
    private static Charset UTF8Charset = Charset.defaultCharset();

    @SuppressLint({"StaticFieldLeak"})
    private static Client client;

    @NonNull
    private static Client getClient() {
        Client client2 = client;
        return client2 != null ? client2 : Bugsnag.getClient();
    }

    private static Event createEmptyEvent() {
        Client client2 = getClient();
        return new Event(new EventInternal(null, client2.getConfig(), SeverityReason.newInstance("handledException"), client2.getMetadataState().getMetadata().copy()), client2.getLogger());
    }

    public static void setClient(@NonNull Client client2) {
        client = client2;
    }

    @Nullable
    public static String getContext() {
        return getClient().getContext();
    }

    @NonNull
    public static File getNativeReportPath() {
        return getNativeReportPath(getPersistenceDirectory());
    }

    @NonNull
    private static File getNativeReportPath(@NonNull File file) {
        return new File(file, "bugsnag-native");
    }

    @NonNull
    private static File getPersistenceDirectory() {
        return getClient().getConfig().getPersistenceDirectory().getValue();
    }

    @NonNull
    public static Map<String, String> getUser() {
        HashMap map = new HashMap();
        User user = getClient().getUser();
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        return map;
    }

    @NonNull
    public static Map<String, Object> getApp() {
        HashMap map = new HashMap();
        AppDataCollector appDataCollector = getClient().getAppDataCollector();
        AppWithState appWithStateGenerateAppWithState = appDataCollector.generateAppWithState();
        map.put("version", appWithStateGenerateAppWithState.getVersion());
        map.put("releaseStage", appWithStateGenerateAppWithState.getReleaseStage());
        map.put("id", appWithStateGenerateAppWithState.getId());
        map.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, appWithStateGenerateAppWithState.getType());
        map.put("buildUUID", appWithStateGenerateAppWithState.getBuildUuid());
        map.put("duration", appWithStateGenerateAppWithState.getDuration());
        map.put("durationInForeground", appWithStateGenerateAppWithState.getDurationInForeground());
        map.put("versionCode", appWithStateGenerateAppWithState.getVersionCode());
        map.put("inForeground", appWithStateGenerateAppWithState.getInForeground());
        map.put("isLaunching", appWithStateGenerateAppWithState.getIsLaunching());
        map.put("binaryArch", appWithStateGenerateAppWithState.getBinaryArch());
        map.putAll(appDataCollector.getAppDataMetadata());
        return map;
    }

    @NonNull
    public static Map<String, Object> getDevice() {
        DeviceDataCollector deviceDataCollector = getClient().getDeviceDataCollector();
        HashMap map = new HashMap(deviceDataCollector.getDeviceMetadata());
        DeviceWithState deviceWithStateGenerateDeviceWithState = deviceDataCollector.generateDeviceWithState(new Date().getTime());
        map.put("freeDisk", deviceWithStateGenerateDeviceWithState.getFreeDisk());
        map.put("freeMemory", deviceWithStateGenerateDeviceWithState.getFreeMemory());
        map.put("orientation", deviceWithStateGenerateDeviceWithState.getOrientation());
        map.put("time", deviceWithStateGenerateDeviceWithState.getTime());
        map.put("cpuAbi", deviceWithStateGenerateDeviceWithState.getCpuAbi());
        map.put("jailbroken", deviceWithStateGenerateDeviceWithState.getJailbroken());
        map.put("id", deviceWithStateGenerateDeviceWithState.getId());
        map.put(Splash.PARAMS_LOCALE, deviceWithStateGenerateDeviceWithState.getLocale());
        map.put("manufacturer", deviceWithStateGenerateDeviceWithState.getManufacturer());
        map.put("model", deviceWithStateGenerateDeviceWithState.getModel());
        map.put("osName", deviceWithStateGenerateDeviceWithState.getOsName());
        map.put("osVersion", deviceWithStateGenerateDeviceWithState.getOsVersion());
        map.put("runtimeVersions", deviceWithStateGenerateDeviceWithState.getRuntimeVersions());
        map.put("totalMemory", deviceWithStateGenerateDeviceWithState.getTotalMemory());
        return map;
    }

    @NonNull
    public static String[] getCpuAbi() {
        return getClient().getDeviceDataCollector().getCpuAbi();
    }

    @NonNull
    public static Map<String, Object> getMetadata() {
        return getClient().getMetadata();
    }

    @NonNull
    public static List<Breadcrumb> getBreadcrumbs() {
        return getClient().getBreadcrumbs();
    }

    public static void setUser(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        getClient().setUser(str, str2, str3);
    }

    public static void setUser(@Nullable byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3) {
        setUser(bArr == null ? null : new String(bArr, UTF8Charset), bArr2 == null ? null : new String(bArr2, UTF8Charset), bArr3 != null ? new String(bArr3, UTF8Charset) : null);
    }

    public static void leaveBreadcrumb(@NonNull String str, @NonNull BreadcrumbType breadcrumbType) {
        if (str == null) {
            return;
        }
        getClient().leaveBreadcrumb(str, new HashMap(), breadcrumbType);
    }

    public static void leaveBreadcrumb(@NonNull byte[] bArr, @NonNull BreadcrumbType breadcrumbType) {
        if (bArr == null) {
            return;
        }
        getClient().leaveBreadcrumb(new String(bArr, UTF8Charset), new HashMap(), breadcrumbType);
    }

    public static void leaveBreadcrumb(@NonNull String str, @NonNull String str2, @NonNull Map<String, Object> map) {
        getClient().leaveBreadcrumb(str, map, BreadcrumbType.valueOf(str2.toUpperCase(Locale.US)));
    }

    public static void clearMetadata(@NonNull String str, @Nullable String str2) {
        if (str2 == null) {
            getClient().clearMetadata(str);
        } else {
            getClient().clearMetadata(str, str2);
        }
    }

    public static void addMetadata(@NonNull String str, @Nullable String str2, @Nullable Object obj) {
        getClient().addMetadata(str, str2, obj);
    }

    public static void addMetadata(@NonNull String str, @NonNull Map<String, ?> map) {
        getClient().addMetadata(str, map);
    }

    @Nullable
    public static String getReleaseStage() {
        return getClient().getConfig().getReleaseStage();
    }

    @NonNull
    public static String getSessionEndpoint() {
        return getClient().getConfig().getEndpoints().getSessions();
    }

    @NonNull
    public static String getEndpoint() {
        return getClient().getConfig().getEndpoints().getNotify();
    }

    public static void setContext(@Nullable String str) {
        getClient().setContext(str);
    }

    public static void setBinaryArch(@NonNull String str) {
        getClient().setBinaryArch(str);
    }

    @Nullable
    public static String getAppVersion() {
        return getClient().getConfig().getAppVersion();
    }

    @Nullable
    public static Collection<String> getEnabledReleaseStages() {
        return getClient().getConfig().getEnabledReleaseStages();
    }

    public static void registerSession(long j, @Nullable String str, int i, int i2) {
        Client client2 = getClient();
        client2.getSessionTracker().registerExistingSession(j > 0 ? new Date(j) : null, str, client2.getUser(), i, i2);
    }

    public static boolean isDiscardErrorClass(@NonNull String str) {
        return getClient().getConfig().getDiscardClasses().contains(str);
    }

    private static void deepMerge(Map<String, Object> map, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object obj = map2.get(key);
            if ((value instanceof Map) && (obj instanceof Map)) {
                deepMerge((Map) value, (Map) obj);
            } else if ((value instanceof Collection) && (obj instanceof Collection)) {
                ((Collection) obj).addAll((Collection) value);
            } else {
                map2.put(key, value);
            }
        }
    }

    public static void deliverReport(@Nullable byte[] bArr, @NonNull byte[] bArr2, @Nullable byte[] bArr3, @NonNull String str, boolean z) throws Throwable {
        if (bArr3 != null) {
            JsonHelper jsonHelper = JsonHelper.INSTANCE;
            Map<? super String, ? extends Object> mapDeserialize = jsonHelper.deserialize(new ByteArrayInputStream(bArr2));
            deepMerge(jsonHelper.deserialize(new ByteArrayInputStream(bArr3)), mapDeserialize);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            jsonHelper.serialize(mapDeserialize, byteArrayOutputStream);
            bArr2 = byteArrayOutputStream.toByteArray();
        }
        String str2 = new String(bArr2, UTF8Charset);
        String str3 = bArr == null ? null : new String(bArr, UTF8Charset);
        Client client2 = getClient();
        ImmutableConfig config = client2.getConfig();
        if (str3 == null || str3.length() == 0 || !config.shouldDiscardByReleaseStage()) {
            EventStore eventStore = client2.getEventStore();
            String ndkFilename = eventStore.getNdkFilename(str2, str);
            if (z) {
                ndkFilename = ndkFilename.replace(".json", "startupcrash.json");
            }
            eventStore.enqueueContentForDelivery(str2, ndkFilename);
        }
    }

    public static void notify(@NonNull byte[] bArr, @NonNull byte[] bArr2, @NonNull Severity severity, @NonNull StackTraceElement[] stackTraceElementArr) {
        if (bArr == null || bArr2 == null || stackTraceElementArr == null) {
            return;
        }
        notify(new String(bArr, UTF8Charset), new String(bArr2, UTF8Charset), severity, stackTraceElementArr);
    }

    public static void notify(@NonNull final String str, @NonNull final String str2, @NonNull final Severity severity, @NonNull StackTraceElement[] stackTraceElementArr) {
        if (getClient().getConfig().shouldDiscardError(str)) {
            return;
        }
        RuntimeException runtimeException = new RuntimeException();
        runtimeException.setStackTrace(stackTraceElementArr);
        getClient().notify(runtimeException, new OnErrorCallback() { // from class: com.bugsnag.android.NativeInterface.1
            @Override // com.bugsnag.android.OnErrorCallback
            public boolean onError(@NonNull Event event) {
                event.updateSeverityInternal(severity);
                List<Error> errors = event.getErrors();
                Error error = event.getErrors().get(0);
                if (errors.isEmpty()) {
                    return true;
                }
                error.setErrorClass(str);
                error.setErrorMessage(str2);
                Iterator<Error> it = errors.iterator();
                while (it.hasNext()) {
                    it.next().setType(ErrorType.C);
                }
                return true;
            }
        });
    }

    public static void notify(@NonNull byte[] bArr, @NonNull byte[] bArr2, @NonNull Severity severity, @NonNull NativeStackframe[] nativeStackframeArr) {
        if (bArr == null || bArr2 == null || nativeStackframeArr == null) {
            return;
        }
        notify(new String(bArr, UTF8Charset), new String(bArr2, UTF8Charset), severity, nativeStackframeArr);
    }

    public static void notify(@NonNull String str, @NonNull String str2, @NonNull Severity severity, @NonNull NativeStackframe[] nativeStackframeArr) {
        Client client2 = getClient();
        if (client2.getConfig().shouldDiscardError(str)) {
            return;
        }
        Event eventCreateEmptyEvent = createEmptyEvent();
        eventCreateEmptyEvent.updateSeverityInternal(severity);
        ArrayList arrayList = new ArrayList(nativeStackframeArr.length);
        for (NativeStackframe nativeStackframe : nativeStackframeArr) {
            arrayList.add(new Stackframe(nativeStackframe));
        }
        eventCreateEmptyEvent.getErrors().add(new Error(new ErrorInternal(str, str2, new Stacktrace(arrayList), ErrorType.C), client2.getLogger()));
        getClient().populateAndNotifyAndroidEvent(eventCreateEmptyEvent, null);
    }

    @NonNull
    public static Event createEvent(@Nullable Throwable th, @NonNull Client client2, @NonNull SeverityReason severityReason) {
        return new Event(th, client2.getConfig(), severityReason, client2.getMetadataState().getMetadata(), client2.getFeatureFlagState().getFeatureFlags(), client2.logger);
    }

    @NonNull
    public static Logger getLogger() {
        return getClient().getConfig().getLogger();
    }

    public static void setAutoNotify(boolean z) {
        getClient().setAutoNotify(z);
    }

    public static void setAutoDetectAnrs(boolean z) {
        getClient().setAutoDetectAnrs(z);
    }

    public static void startSession() {
        getClient().startSession();
    }

    public static void pauseSession() {
        getClient().pauseSession();
    }

    public static boolean resumeSession() {
        return getClient().resumeSession();
    }

    @Nullable
    public static Session getCurrentSession() {
        return getClient().sessionTracker.getCurrentSession();
    }

    public static void markLaunchCompleted() {
        getClient().markLaunchCompleted();
    }

    @Nullable
    public static LastRunInfo getLastRunInfo() {
        return getClient().getLastRunInfo();
    }
}
