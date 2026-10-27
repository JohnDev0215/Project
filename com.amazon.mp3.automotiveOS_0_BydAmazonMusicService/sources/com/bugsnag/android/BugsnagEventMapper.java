package com.bugsnag.android;

import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.util.DateUtils;
import com.bugsnag.android.ThreadType;
import com.bugsnag.android.internal.InternalMetricsImpl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: BugsnagEventMapper.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u00018\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010<\u001a\u00020;¢\u0006\u0004\b>\u0010?J\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H\u0002J/\u0010\u000b\u001a\u00020\b2\u0016\u0010\u0006\u001a\u0012\u0012\u0006\b\u0000\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\t\u0010\nJ'\u0010\u0010\u001a\u00020\r2\u0016\u0010\f\u001a\u0012\u0012\u0006\b\u0000\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0015\u001a\u00020\u00122\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u001a\u001a\u00020\u00172\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001f\u001a\u00020\u001c2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ%\u0010$\u001a\u00020!2\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0000¢\u0006\u0004\b\"\u0010#J%\u0010)\u001a\u00020&2\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005H\u0000¢\u0006\u0004\b'\u0010(J+\u0010/\u001a\u00020,2\u001a\u0010+\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00050*H\u0000¢\u0006\u0004\b-\u0010.J9\u00107\u001a\u0002042\u0016\u0010\u0006\u001a\u0012\u0012\u0006\b\u0000\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\u0006\u00101\u001a\u0002002\b\u00103\u001a\u0004\u0018\u000102H\u0000¢\u0006\u0004\b5\u00106R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=¨\u0006@"}, d2 = {"Lcom/bugsnag/android/BugsnagEventMapper;", "", "", "Ljava/util/Date;", "toDate", "", "map", "apiKey", "Lcom/bugsnag/android/EventInternal;", "convertToEventImpl$bugsnag_android_core_release", "(Ljava/util/Map;Ljava/lang/String;)Lcom/bugsnag/android/EventInternal;", "convertToEventImpl", "error", "Lcom/bugsnag/android/ErrorInternal;", "convertErrorInternal$bugsnag_android_core_release", "(Ljava/util/Map;)Lcom/bugsnag/android/ErrorInternal;", "convertErrorInternal", "user", "Lcom/bugsnag/android/User;", "convertUser$bugsnag_android_core_release", "(Ljava/util/Map;)Lcom/bugsnag/android/User;", "convertUser", "breadcrumb", "Lcom/bugsnag/android/BreadcrumbInternal;", "convertBreadcrumbInternal$bugsnag_android_core_release", "(Ljava/util/Map;)Lcom/bugsnag/android/BreadcrumbInternal;", "convertBreadcrumbInternal", "app", "Lcom/bugsnag/android/AppWithState;", "convertAppWithState$bugsnag_android_core_release", "(Ljava/util/Map;)Lcom/bugsnag/android/AppWithState;", "convertAppWithState", "device", "Lcom/bugsnag/android/DeviceWithState;", "convertDeviceWithState$bugsnag_android_core_release", "(Ljava/util/Map;)Lcom/bugsnag/android/DeviceWithState;", "convertDeviceWithState", "thread", "Lcom/bugsnag/android/ThreadInternal;", "convertThread$bugsnag_android_core_release", "(Ljava/util/Map;)Lcom/bugsnag/android/ThreadInternal;", "convertThread", "", TracePublisherServiceMessageType.TRACE_MESSAGE_KEY, "Lcom/bugsnag/android/Stacktrace;", "convertStacktrace$bugsnag_android_core_release", "(Ljava/util/List;)Lcom/bugsnag/android/Stacktrace;", "convertStacktrace", "", "unhandled", "Lcom/bugsnag/android/Severity;", "severity", "Lcom/bugsnag/android/SeverityReason;", "deserializeSeverityReason$bugsnag_android_core_release", "(Ljava/util/Map;ZLcom/bugsnag/android/Severity;)Lcom/bugsnag/android/SeverityReason;", "deserializeSeverityReason", "com/bugsnag/android/BugsnagEventMapper$ndkDateFormatHolder$1", "ndkDateFormatHolder", "Lcom/bugsnag/android/BugsnagEventMapper$ndkDateFormatHolder$1;", "Lcom/bugsnag/android/Logger;", "logger", "Lcom/bugsnag/android/Logger;", "<init>", "(Lcom/bugsnag/android/Logger;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class BugsnagEventMapper {
    private final Logger logger;
    private final BugsnagEventMapper$ndkDateFormatHolder$1 ndkDateFormatHolder = new ThreadLocal<DateFormat>() { // from class: com.bugsnag.android.BugsnagEventMapper$ndkDateFormatHolder$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN, Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.bugsnag.android.BugsnagEventMapper$ndkDateFormatHolder$1] */
    public BugsnagEventMapper(Logger logger) {
        this.logger = logger;
    }

    public final EventInternal convertToEventImpl$bugsnag_android_core_release(Map<? super String, ? extends Object> map, String apiKey) {
        EventInternal eventInternal = new EventInternal(apiKey, this.logger, null, null, null, null, null, null, null, null, null, null, null, 8188, null);
        Object obj = map.get("exceptions");
        if (!(obj instanceof List)) {
            obj = null;
        }
        List list = (List) obj;
        if (list != null) {
            List<Error> errors = eventInternal.getErrors();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                errors.add(new Error(convertErrorInternal$bugsnag_android_core_release((Map) it.next()), this.logger));
            }
        }
        Object obj2 = map.get("user");
        if (!(obj2 instanceof Map)) {
            if (obj2 == null) {
                throw new IllegalStateException("cannot find json property 'user'");
            }
            throw new IllegalArgumentException("json property 'user' not of expected type, found " + obj2.getClass().getName());
        }
        eventInternal.setUserImpl$bugsnag_android_core_release(convertUser$bugsnag_android_core_release((Map) obj2));
        Object obj3 = map.get("metaData");
        if (!(obj3 instanceof Map)) {
            if (obj3 == null) {
                throw new IllegalStateException("cannot find json property 'metaData'");
            }
            throw new IllegalArgumentException("json property 'metaData' not of expected type, found " + obj3.getClass().getName());
        }
        for (Map.Entry entry : ((Map) obj3).entrySet()) {
            eventInternal.addMetadata((String) entry.getKey(), (Map) entry.getValue());
        }
        Object obj4 = map.get("featureFlags");
        if (!(obj4 instanceof List)) {
            if (obj4 == null) {
                throw new IllegalStateException("cannot find json property 'featureFlags'");
            }
            throw new IllegalArgumentException("json property 'featureFlags' not of expected type, found " + obj4.getClass().getName());
        }
        for (Map map2 : (List) obj4) {
            Object obj5 = map2.get("featureFlag");
            if (!(obj5 instanceof String)) {
                if (obj5 == null) {
                    throw new IllegalStateException("cannot find json property 'featureFlag'");
                }
                throw new IllegalArgumentException("json property 'featureFlag' not of expected type, found " + obj5.getClass().getName());
            }
            String str = (String) obj5;
            Object obj6 = map2.get("variant");
            if (!(obj6 instanceof String)) {
                obj6 = null;
            }
            eventInternal.addFeatureFlag(str, (String) obj6);
        }
        Object obj7 = map.get("breadcrumbs");
        if (!(obj7 instanceof List)) {
            if (obj7 == null) {
                throw new IllegalStateException("cannot find json property 'breadcrumbs'");
            }
            throw new IllegalArgumentException("json property 'breadcrumbs' not of expected type, found " + obj7.getClass().getName());
        }
        List<Breadcrumb> breadcrumbs = eventInternal.getBreadcrumbs();
        Iterator it2 = ((List) obj7).iterator();
        while (it2.hasNext()) {
            breadcrumbs.add(new Breadcrumb(convertBreadcrumbInternal$bugsnag_android_core_release((Map) it2.next()), this.logger));
        }
        Object obj8 = map.get("context");
        if (!(obj8 instanceof String)) {
            obj8 = null;
        }
        eventInternal.setContext((String) obj8);
        Object obj9 = map.get("groupingHash");
        if (!(obj9 instanceof String)) {
            obj9 = null;
        }
        eventInternal.setGroupingHash((String) obj9);
        Object obj10 = map.get("app");
        if (!(obj10 instanceof Map)) {
            if (obj10 == null) {
                throw new IllegalStateException("cannot find json property 'app'");
            }
            throw new IllegalArgumentException("json property 'app' not of expected type, found " + obj10.getClass().getName());
        }
        eventInternal.setApp(convertAppWithState$bugsnag_android_core_release((Map) obj10));
        Object obj11 = map.get("device");
        if (!(obj11 instanceof Map)) {
            if (obj11 == null) {
                throw new IllegalStateException("cannot find json property 'device'");
            }
            throw new IllegalArgumentException("json property 'device' not of expected type, found " + obj11.getClass().getName());
        }
        eventInternal.setDevice(convertDeviceWithState$bugsnag_android_core_release((Map) obj11));
        Object obj12 = map.get("session");
        if (!(obj12 instanceof Map)) {
            obj12 = null;
        }
        Map map3 = (Map) obj12;
        if (map3 != null) {
            eventInternal.session = new Session(map3, this.logger);
            Unit unit = Unit.INSTANCE;
        }
        Object obj13 = map.get("threads");
        if (!(obj13 instanceof List)) {
            obj13 = null;
        }
        List list2 = (List) obj13;
        if (list2 != null) {
            List<Thread> threads = eventInternal.getThreads();
            Iterator it3 = list2.iterator();
            while (it3.hasNext()) {
                threads.add(new Thread(convertThread$bugsnag_android_core_release((Map) it3.next()), this.logger));
            }
        }
        Object obj14 = map.get("projectPackages");
        List list3 = (List) (obj14 instanceof List ? obj14 : null);
        if (list3 != null) {
            eventInternal.setProjectPackages$bugsnag_android_core_release(list3);
            Unit unit2 = Unit.INSTANCE;
        }
        Object obj15 = map.get("severity");
        if (!(obj15 instanceof String)) {
            if (obj15 == null) {
                throw new IllegalStateException("cannot find json property 'severity'");
            }
            throw new IllegalArgumentException("json property 'severity' not of expected type, found " + obj15.getClass().getName());
        }
        Severity severityFromDescriptor$bugsnag_android_core_release = Severity.INSTANCE.fromDescriptor$bugsnag_android_core_release((String) obj15);
        Object obj16 = map.get("unhandled");
        if (obj16 instanceof Boolean) {
            eventInternal.updateSeverityReasonInternal$bugsnag_android_core_release(deserializeSeverityReason$bugsnag_android_core_release(map, ((Boolean) obj16).booleanValue(), severityFromDescriptor$bugsnag_android_core_release));
            eventInternal.normalizeStackframeErrorTypes$bugsnag_android_core_release();
            eventInternal.setInternalMetrics(new InternalMetricsImpl(TypeIntrinsics.asMutableMap(map.get("usage"))));
            return eventInternal;
        }
        if (obj16 == null) {
            throw new IllegalStateException("cannot find json property 'unhandled'");
        }
        throw new IllegalArgumentException("json property 'unhandled' not of expected type, found " + obj16.getClass().getName());
    }

    public final User convertUser$bugsnag_android_core_release(Map<String, ? extends Object> user) {
        Object obj = user.get("id");
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        Object obj2 = user.get("email");
        if (!(obj2 instanceof String)) {
            obj2 = null;
        }
        String str2 = (String) obj2;
        Object obj3 = user.get("name");
        return new User(str, str2, (String) (obj3 instanceof String ? obj3 : null));
    }

    public final AppWithState convertAppWithState$bugsnag_android_core_release(Map<String, ? extends Object> app) {
        Object obj = app.get("binaryArch");
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        Object obj2 = app.get("id");
        if (!(obj2 instanceof String)) {
            obj2 = null;
        }
        String str2 = (String) obj2;
        Object obj3 = app.get("releaseStage");
        if (!(obj3 instanceof String)) {
            obj3 = null;
        }
        String str3 = (String) obj3;
        Object obj4 = app.get("version");
        if (!(obj4 instanceof String)) {
            obj4 = null;
        }
        String str4 = (String) obj4;
        Object obj5 = app.get("codeBundleId");
        if (!(obj5 instanceof String)) {
            obj5 = null;
        }
        String str5 = (String) obj5;
        Object obj6 = app.get("buildUUID");
        if (!(obj6 instanceof String)) {
            obj6 = null;
        }
        String str6 = (String) obj6;
        Object obj7 = app.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        if (!(obj7 instanceof String)) {
            obj7 = null;
        }
        String str7 = (String) obj7;
        Object obj8 = app.get("versionCode");
        if (!(obj8 instanceof Number)) {
            obj8 = null;
        }
        Number number = (Number) obj8;
        Integer numValueOf = number != null ? Integer.valueOf(number.intValue()) : null;
        Object obj9 = app.get("duration");
        if (!(obj9 instanceof Number)) {
            obj9 = null;
        }
        Number number2 = (Number) obj9;
        Long lValueOf = number2 != null ? Long.valueOf(number2.longValue()) : null;
        Object obj10 = app.get("durationInForeground");
        if (!(obj10 instanceof Number)) {
            obj10 = null;
        }
        Number number3 = (Number) obj10;
        Long lValueOf2 = number3 != null ? Long.valueOf(number3.longValue()) : null;
        Object obj11 = app.get("inForeground");
        if (!(obj11 instanceof Boolean)) {
            obj11 = null;
        }
        Boolean bool = (Boolean) obj11;
        Object obj12 = app.get("isLaunching");
        return new AppWithState(str, str2, str3, str4, str5, str6, str7, numValueOf, lValueOf, lValueOf2, bool, (Boolean) (obj12 instanceof Boolean ? obj12 : null));
    }

    public final DeviceWithState convertDeviceWithState$bugsnag_android_core_release(Map<String, ? extends Object> device) {
        String[] strArr;
        Map linkedHashMap;
        Object obj = device.get("manufacturer");
        if (!(obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        Object obj2 = device.get("model");
        if (!(obj2 instanceof String)) {
            obj2 = null;
        }
        String str2 = (String) obj2;
        Object obj3 = device.get("osVersion");
        if (!(obj3 instanceof String)) {
            obj3 = null;
        }
        String str3 = (String) obj3;
        Object obj4 = device.get("cpuAbi");
        if (!(obj4 instanceof List)) {
            obj4 = null;
        }
        List list = (List) obj4;
        if (list != null) {
            Object[] array = list.toArray(new String[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        } else {
            strArr = null;
        }
        DeviceBuildInfo deviceBuildInfo = new DeviceBuildInfo(str, str2, str3, null, null, null, null, null, strArr);
        Object obj5 = device.get("jailbroken");
        if (!(obj5 instanceof Boolean)) {
            obj5 = null;
        }
        Boolean bool = (Boolean) obj5;
        Object obj6 = device.get("id");
        if (!(obj6 instanceof String)) {
            obj6 = null;
        }
        String str4 = (String) obj6;
        Object obj7 = device.get(Splash.PARAMS_LOCALE);
        if (!(obj7 instanceof String)) {
            obj7 = null;
        }
        String str5 = (String) obj7;
        Object obj8 = device.get("totalMemory");
        if (!(obj8 instanceof Number)) {
            obj8 = null;
        }
        Number number = (Number) obj8;
        Long lValueOf = number != null ? Long.valueOf(number.longValue()) : null;
        Object obj9 = device.get("runtimeVersions");
        if (!(obj9 instanceof Map)) {
            obj9 = null;
        }
        Map map = (Map) obj9;
        if (map == null || (linkedHashMap = MapsKt__MapsKt.toMutableMap(map)) == null) {
            linkedHashMap = new LinkedHashMap();
        }
        Map map2 = linkedHashMap;
        Object obj10 = device.get("freeDisk");
        if (!(obj10 instanceof Number)) {
            obj10 = null;
        }
        Number number2 = (Number) obj10;
        Long lValueOf2 = number2 != null ? Long.valueOf(number2.longValue()) : null;
        Object obj11 = device.get("freeMemory");
        if (!(obj11 instanceof Number)) {
            obj11 = null;
        }
        Number number3 = (Number) obj11;
        Long lValueOf3 = number3 != null ? Long.valueOf(number3.longValue()) : null;
        Object obj12 = device.get("orientation");
        if (!(obj12 instanceof String)) {
            obj12 = null;
        }
        String str6 = (String) obj12;
        Object obj13 = device.get("time");
        if (!(obj13 instanceof String)) {
            obj13 = null;
        }
        String str7 = (String) obj13;
        return new DeviceWithState(deviceBuildInfo, bool, str4, str5, lValueOf, map2, lValueOf2, lValueOf3, str6, str7 != null ? toDate(str7) : null);
    }

    public final ThreadInternal convertThread$bugsnag_android_core_release(Map<String, ? extends Object> thread) {
        Stacktrace stacktrace;
        Object obj = thread.get("id");
        if (!(obj instanceof Number)) {
            obj = null;
        }
        Number number = (Number) obj;
        long jLongValue = number != null ? number.longValue() : 0L;
        Object obj2 = thread.get("name");
        if (!(obj2 instanceof String)) {
            if (obj2 == null) {
                throw new IllegalStateException("cannot find json property 'name'");
            }
            throw new IllegalArgumentException("json property 'name' not of expected type, found " + obj2.getClass().getName());
        }
        String str = (String) obj2;
        ThreadType.Companion companion = ThreadType.INSTANCE;
        Object obj3 = thread.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        if (!(obj3 instanceof String)) {
            if (obj3 == null) {
                throw new IllegalStateException("cannot find json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + '\'');
            }
            throw new IllegalArgumentException("json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + "' not of expected type, found " + obj3.getClass().getName());
        }
        ThreadType threadTypeFromDescriptor$bugsnag_android_core_release = companion.fromDescriptor$bugsnag_android_core_release((String) obj3);
        if (threadTypeFromDescriptor$bugsnag_android_core_release == null) {
            threadTypeFromDescriptor$bugsnag_android_core_release = ThreadType.ANDROID;
        }
        boolean zAreEqual = kotlin.jvm.internal.Intrinsics.areEqual(thread.get("errorReportingThread"), Boolean.TRUE);
        Object obj4 = thread.get("state");
        if (obj4 instanceof String) {
            String str2 = (String) obj4;
            Object obj5 = thread.get("stacktrace");
            List<? extends Map<String, ? extends Object>> list = (List) (obj5 instanceof List ? obj5 : null);
            if (list == null || (stacktrace = convertStacktrace$bugsnag_android_core_release(list)) == null) {
                stacktrace = new Stacktrace(CollectionsKt__CollectionsKt.emptyList());
            }
            return new ThreadInternal(jLongValue, str, threadTypeFromDescriptor$bugsnag_android_core_release, zAreEqual, str2, stacktrace);
        }
        if (obj4 == null) {
            throw new IllegalStateException("cannot find json property 'state'");
        }
        throw new IllegalArgumentException("json property 'state' not of expected type, found " + obj4.getClass().getName());
    }

    public final BreadcrumbInternal convertBreadcrumbInternal$bugsnag_android_core_release(Map<String, ? extends Object> breadcrumb) {
        Object obj = breadcrumb.get("name");
        if (!(obj instanceof String)) {
            if (obj == null) {
                throw new IllegalStateException("cannot find json property 'name'");
            }
            throw new IllegalArgumentException("json property 'name' not of expected type, found " + obj.getClass().getName());
        }
        String str = (String) obj;
        Object obj2 = breadcrumb.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        if (!(obj2 instanceof String)) {
            if (obj2 == null) {
                throw new IllegalStateException("cannot find json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + '\'');
            }
            throw new IllegalArgumentException("json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + "' not of expected type, found " + obj2.getClass().getName());
        }
        BreadcrumbType breadcrumbTypeFromDescriptor$bugsnag_android_core_release = BreadcrumbType.INSTANCE.fromDescriptor$bugsnag_android_core_release((String) obj2);
        if (breadcrumbTypeFromDescriptor$bugsnag_android_core_release == null) {
            breadcrumbTypeFromDescriptor$bugsnag_android_core_release = BreadcrumbType.MANUAL;
        }
        Object obj3 = breadcrumb.get("metaData");
        if (!TypeIntrinsics.isMutableMap(obj3)) {
            obj3 = null;
        }
        Map map = (Map) obj3;
        Object obj4 = breadcrumb.get(TetheredMessageKey.timestamp);
        if (obj4 instanceof String) {
            return new BreadcrumbInternal(str, breadcrumbTypeFromDescriptor$bugsnag_android_core_release, map, toDate((String) obj4));
        }
        if (obj4 == null) {
            throw new IllegalStateException("cannot find json property '" + TetheredMessageKey.timestamp + '\'');
        }
        throw new IllegalArgumentException("json property '" + TetheredMessageKey.timestamp + "' not of expected type, found " + obj4.getClass().getName());
    }

    public final ErrorInternal convertErrorInternal$bugsnag_android_core_release(Map<? super String, ? extends Object> error) {
        Object obj = error.get("errorClass");
        if (!(obj instanceof String)) {
            if (obj == null) {
                throw new IllegalStateException("cannot find json property 'errorClass'");
            }
            throw new IllegalArgumentException("json property 'errorClass' not of expected type, found " + obj.getClass().getName());
        }
        String str = (String) obj;
        Object obj2 = error.get("message");
        if (!(obj2 instanceof String)) {
            obj2 = null;
        }
        String str2 = (String) obj2;
        Object obj3 = error.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        if (!(obj3 instanceof String)) {
            if (obj3 == null) {
                throw new IllegalStateException("cannot find json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + '\'');
            }
            throw new IllegalArgumentException("json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + "' not of expected type, found " + obj3.getClass().getName());
        }
        String str3 = (String) obj3;
        ErrorType errorTypeFromDescriptor = ErrorType.INSTANCE.fromDescriptor(str3);
        if (errorTypeFromDescriptor == null) {
            throw new IllegalArgumentException("unknown ErrorType: '" + str3 + '\'');
        }
        Object obj4 = error.get("stacktrace");
        if (obj4 instanceof List) {
            return new ErrorInternal(str, str2, convertStacktrace$bugsnag_android_core_release((List) obj4), errorTypeFromDescriptor);
        }
        if (obj4 == null) {
            throw new IllegalStateException("cannot find json property 'stacktrace'");
        }
        throw new IllegalArgumentException("json property 'stacktrace' not of expected type, found " + obj4.getClass().getName());
    }

    public final SeverityReason deserializeSeverityReason$bugsnag_android_core_release(Map<? super String, ? extends Object> map, boolean unhandled, Severity severity) {
        Set setEntrySet;
        Object obj = map.get("severityReason");
        if (!(obj instanceof Map)) {
            if (obj == null) {
                throw new IllegalStateException("cannot find json property 'severityReason'");
            }
            throw new IllegalArgumentException("json property 'severityReason' not of expected type, found " + obj.getClass().getName());
        }
        Map map2 = (Map) obj;
        Object obj2 = map2.get("unhandledOverridden");
        if (!(obj2 instanceof Boolean)) {
            if (obj2 == null) {
                throw new IllegalStateException("cannot find json property 'unhandledOverridden'");
            }
            throw new IllegalArgumentException("json property 'unhandledOverridden' not of expected type, found " + obj2.getClass().getName());
        }
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        Object obj3 = map2.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        if (!(obj3 instanceof String)) {
            if (obj3 == null) {
                throw new IllegalStateException("cannot find json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + '\'');
            }
            throw new IllegalArgumentException("json property '" + AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE + "' not of expected type, found " + obj3.getClass().getName());
        }
        String str = (String) obj3;
        boolean z = zBooleanValue ? !unhandled : unhandled;
        Object obj4 = map2.get("attributes");
        if (obj4 != null ? obj4 instanceof Map : true) {
            Map map3 = (Map) obj4;
            Map.Entry entry = (map3 == null || (setEntrySet = map3.entrySet()) == null) ? null : (Map.Entry) CollectionsKt___CollectionsKt.singleOrNull(setEntrySet);
            return new SeverityReason(str, severity, unhandled, z, entry != null ? (String) entry.getValue() : null, entry != null ? (String) entry.getKey() : null);
        }
        if (obj4 == null) {
            throw new IllegalStateException("cannot find json property 'attributes'");
        }
        throw new IllegalArgumentException("json property 'attributes' not of expected type, found " + obj4.getClass().getName());
    }

    private final Date toDate(String str) throws ParseException {
        try {
            return com.bugsnag.android.internal.DateUtils.fromIso8601(str);
        } catch (IllegalArgumentException unused) {
            DateFormat dateFormat = get();
            if (dateFormat == null) {
                kotlin.jvm.internal.Intrinsics.throwNpe();
            }
            Date date = dateFormat.parse(str);
            if (date != null) {
                return date;
            }
            throw new IllegalArgumentException("cannot parse date " + str);
        }
    }

    public final Stacktrace convertStacktrace$bugsnag_android_core_release(List<? extends Map<String, ? extends Object>> trace) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(trace, 10));
        Iterator<T> it = trace.iterator();
        while (it.hasNext()) {
            arrayList.add(new Stackframe((Map<String, ? extends Object>) it.next()));
        }
        return new Stacktrace(arrayList);
    }
}
