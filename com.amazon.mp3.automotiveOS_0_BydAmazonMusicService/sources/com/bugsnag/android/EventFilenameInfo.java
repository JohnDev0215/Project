package com.bugsnag.android;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.bugsnag.android.internal.ImmutableConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: EventFilenameInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\b\u0018\u0000  2\u00020\u0001:\u0001 B5\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0004\b\u001e\u0010\u001fJ\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\t\u0010\u0006\u001a\u00020\u0002HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u000eR\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/bugsnag/android/EventFilenameInfo;", "", "", "encode", "", "isLaunchCrashReport", "toString", "", "hashCode", "other", "equals", "apiKey", "Ljava/lang/String;", "getApiKey", "()Ljava/lang/String;", "uuid", "getUuid", "", TetheredMessageKey.timestamp, "J", "getTimestamp", "()J", "suffix", "getSuffix", "", "Lcom/bugsnag/android/ErrorType;", "errorTypes", "Ljava/util/Set;", "getErrorTypes", "()Ljava/util/Set;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/util/Set;)V", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class EventFilenameInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String apiKey;
    private final Set<ErrorType> errorTypes;
    private final String suffix;
    private final long timestamp;
    private final String uuid;

    @JvmStatic
    public static final long findTimestampInFilename(File file) {
        return INSTANCE.findTimestampInFilename(file);
    }

    @JvmStatic
    @JvmOverloads
    public static final EventFilenameInfo fromEvent(Object obj, String str, ImmutableConfig immutableConfig) {
        return Companion.fromEvent$default(INSTANCE, obj, null, str, 0L, immutableConfig, null, 42, null);
    }

    @JvmStatic
    public static final EventFilenameInfo fromFile(File file, ImmutableConfig immutableConfig) {
        return INSTANCE.fromFile(file, immutableConfig);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventFilenameInfo)) {
            return false;
        }
        EventFilenameInfo eventFilenameInfo = (EventFilenameInfo) other;
        return kotlin.jvm.internal.Intrinsics.areEqual(this.apiKey, eventFilenameInfo.apiKey) && kotlin.jvm.internal.Intrinsics.areEqual(this.uuid, eventFilenameInfo.uuid) && this.timestamp == eventFilenameInfo.timestamp && kotlin.jvm.internal.Intrinsics.areEqual(this.suffix, eventFilenameInfo.suffix) && kotlin.jvm.internal.Intrinsics.areEqual(this.errorTypes, eventFilenameInfo.errorTypes);
    }

    public int hashCode() {
        String str = this.apiKey;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.uuid;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        long j = this.timestamp;
        int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        String str3 = this.suffix;
        int iHashCode3 = (i + (str3 != null ? str3.hashCode() : 0)) * 31;
        Set<ErrorType> set = this.errorTypes;
        return iHashCode3 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "EventFilenameInfo(apiKey=" + this.apiKey + ", uuid=" + this.uuid + ", timestamp=" + this.timestamp + ", suffix=" + this.suffix + ", errorTypes=" + this.errorTypes + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EventFilenameInfo(String str, String str2, long j, String str3, Set<? extends ErrorType> set) {
        this.apiKey = str;
        this.uuid = str2;
        this.timestamp = j;
        this.suffix = str3;
        this.errorTypes = set;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final Set<ErrorType> getErrorTypes() {
        return this.errorTypes;
    }

    public final String encode() {
        return INSTANCE.toFilename(this.apiKey, this.uuid, this.timestamp, this.suffix, this.errorTypes);
    }

    public final boolean isLaunchCrashReport() {
        return kotlin.jvm.internal.Intrinsics.areEqual(this.suffix, "startupcrash");
    }

    /* JADX INFO: compiled from: EventFilenameInfo.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u0010J\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0012\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0013J!\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\bH\u0007JG\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010!\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0002\u0010#J\u0018\u0010$\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J4\u0010%\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u00042\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/bugsnag/android/EventFilenameInfo$Companion;", "", "()V", "NON_JVM_CRASH", "", "STARTUP_CRASH", "findApiKeyInFilename", "file", "Ljava/io/File;", "config", "Lcom/bugsnag/android/internal/ImmutableConfig;", "findApiKeyInFilename$bugsnag_android_core_release", "findErrorTypesForEvent", "", "Lcom/bugsnag/android/ErrorType;", "obj", "findErrorTypesForEvent$bugsnag_android_core_release", "findErrorTypesInFilename", "eventFile", "findErrorTypesInFilename$bugsnag_android_core_release", "findSuffixForEvent", "launching", "", "findSuffixForEvent$bugsnag_android_core_release", "(Ljava/lang/Object;Ljava/lang/Boolean;)Ljava/lang/String;", "findSuffixInFilename", "findSuffixInFilename$bugsnag_android_core_release", "findTimestampInFilename", "", "fromEvent", "Lcom/bugsnag/android/EventFilenameInfo;", "uuid", "apiKey", TetheredMessageKey.timestamp, "isLaunching", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;JLcom/bugsnag/android/internal/ImmutableConfig;Ljava/lang/Boolean;)Lcom/bugsnag/android/EventFilenameInfo;", "fromFile", "toFilename", "suffix", "errorTypes", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String toFilename(String apiKey, String uuid, long timestamp, String suffix, Set<? extends ErrorType> errorTypes) {
            return timestamp + '_' + apiKey + '_' + DeliveryHeadersKt.serializeErrorTypeHeader(errorTypes) + '_' + uuid + '_' + suffix + ".json";
        }

        public static /* synthetic */ EventFilenameInfo fromEvent$default(Companion companion, Object obj, String str, String str2, long j, ImmutableConfig immutableConfig, Boolean bool, int i, Object obj2) {
            String str3;
            if ((i & 2) != 0) {
                String string = UUID.randomUUID().toString();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(string, "UUID.randomUUID().toString()");
                str3 = string;
            } else {
                str3 = str;
            }
            return companion.fromEvent(obj, str3, str2, (i & 8) != 0 ? System.currentTimeMillis() : j, immutableConfig, (i & 32) != 0 ? null : bool);
        }

        @JvmStatic
        @JvmOverloads
        public final EventFilenameInfo fromEvent(Object obj, String uuid, String apiKey, long timestamp, ImmutableConfig config, Boolean isLaunching) {
            if (obj instanceof Event) {
                apiKey = ((Event) obj).getApiKey();
            } else {
                if (apiKey == null || apiKey.length() == 0) {
                    apiKey = config.getApiKey();
                }
            }
            String str = apiKey;
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(str, "when {\n                o…e -> apiKey\n            }");
            return new EventFilenameInfo(str, uuid, timestamp, findSuffixForEvent$bugsnag_android_core_release(obj, isLaunching), findErrorTypesForEvent$bugsnag_android_core_release(obj));
        }

        @JvmStatic
        public final EventFilenameInfo fromFile(File file, ImmutableConfig config) {
            return new EventFilenameInfo(findApiKeyInFilename$bugsnag_android_core_release(file, config), "", findTimestampInFilename(file), findSuffixInFilename$bugsnag_android_core_release(file), findErrorTypesInFilename$bugsnag_android_core_release(file));
        }

        public final String findApiKeyInFilename$bugsnag_android_core_release(File file, ImmutableConfig config) {
            String strSubstring;
            String name = file.getName();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
            String strRemoveSuffix = StringsKt__StringsKt.removeSuffix(name, (CharSequence) "_startupcrash.json");
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) strRemoveSuffix, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 0, false, 6, (Object) null) + 1;
            int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) strRemoveSuffix, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, iIndexOf$default, false, 4, (Object) null);
            if (iIndexOf$default == 0 || iIndexOf$default2 == -1 || iIndexOf$default2 <= iIndexOf$default) {
                strSubstring = null;
            } else {
                if (strRemoveSuffix == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                strSubstring = strRemoveSuffix.substring(iIndexOf$default, iIndexOf$default2);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            return strSubstring != null ? strSubstring : config.getApiKey();
        }

        public final Set<ErrorType> findErrorTypesInFilename$bugsnag_android_core_release(File eventFile) {
            String name = eventFile.getName();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(name, "name");
            int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 0, false, 6, (Object) null) - 1, false, 4, (Object) null);
            int iLastIndexOf$default2 = StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, iLastIndexOf$default - 1, false, 4, (Object) null) + 1;
            if (iLastIndexOf$default2 < iLastIndexOf$default) {
                String strSubstring = name.substring(iLastIndexOf$default2, iLastIndexOf$default);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) strSubstring, new String[]{","}, false, 0, 6, (Object) null);
                ErrorType[] errorTypeArrValues = ErrorType.values();
                ArrayList arrayList = new ArrayList();
                for (ErrorType errorType : errorTypeArrValues) {
                    if (listSplit$default.contains(errorType.getDesc())) {
                        arrayList.add(errorType);
                    }
                }
                return CollectionsKt___CollectionsKt.toSet(arrayList);
            }
            return SetsKt__SetsKt.emptySet();
        }

        public final String findSuffixInFilename$bugsnag_android_core_release(File eventFile) {
            String nameWithoutExtension = FilesKt__UtilsKt.getNameWithoutExtension(eventFile);
            int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) nameWithoutExtension, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 0, false, 6, (Object) null) + 1;
            if (nameWithoutExtension == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = nameWithoutExtension.substring(iLastIndexOf$default);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            int iHashCode = strSubstring.hashCode();
            if (iHashCode != -2033965238) {
                if (iHashCode == 2127567527 && strSubstring.equals("not-jvm")) {
                    return strSubstring;
                }
            } else if (strSubstring.equals("startupcrash")) {
                return strSubstring;
            }
            return "";
        }

        @JvmStatic
        public final long findTimestampInFilename(File eventFile) {
            Long longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(StringsKt__StringsKt.substringBefore(FilesKt__UtilsKt.getNameWithoutExtension(eventFile), Attributes.PREDEFINED_ATTRIBUTE_PREFIX, "-1"));
            if (longOrNull != null) {
                return longOrNull.longValue();
            }
            return -1L;
        }

        public final Set<ErrorType> findErrorTypesForEvent$bugsnag_android_core_release(Object obj) {
            return obj instanceof Event ? ((Event) obj).getImpl().getErrorTypesFromStackframes$bugsnag_android_core_release() : SetsKt__SetsJVMKt.setOf(ErrorType.C);
        }

        public final String findSuffixForEvent$bugsnag_android_core_release(Object obj, Boolean launching) {
            return (((obj instanceof Event) && kotlin.jvm.internal.Intrinsics.areEqual(((Event) obj).getApp().getIsLaunching(), Boolean.TRUE)) || kotlin.jvm.internal.Intrinsics.areEqual(launching, Boolean.TRUE)) ? "startupcrash" : "";
        }
    }
}
