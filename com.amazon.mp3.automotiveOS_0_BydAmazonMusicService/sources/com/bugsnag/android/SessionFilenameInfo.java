package com.bugsnag.android;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.File;
import java.util.UUID;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: SessionFilenameInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0080\b\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/bugsnag/android/SessionFilenameInfo;", "", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class SessionFilenameInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final String defaultFilename() {
        return INSTANCE.defaultFilename();
    }

    @JvmStatic
    public static final long findTimestampInFilename(File file) {
        return INSTANCE.findTimestampInFilename(file);
    }

    /* JADX INFO: compiled from: SessionFilenameInfo.kt */
    @kotlin.Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0007R\u0014\u0010\f\u001a\u00020\u000b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/bugsnag/android/SessionFilenameInfo$Companion;", "", "", TetheredMessageKey.timestamp, "", "uuid", "toFilename", "defaultFilename", "Ljava/io/File;", "file", "findTimestampInFilename", "", "uuidLength", "I", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String toFilename(long timestamp, String uuid) {
            return uuid + timestamp + "_v2.json";
        }

        @JvmStatic
        public final String defaultFilename() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String string = UUID.randomUUID().toString();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(string, "UUID.randomUUID().toString()");
            return toFilename(jCurrentTimeMillis, string);
        }

        @JvmStatic
        public final long findTimestampInFilename(File file) {
            String name = file.getName();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
            String name2 = file.getName();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(name2, "file.name");
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) name2, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 0, false, 6, (Object) null);
            if (name == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = name.substring(36, iIndexOf$default);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            Long longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(strSubstring);
            if (longOrNull != null) {
                return longOrNull.longValue();
            }
            return -1L;
        }
    }
}
