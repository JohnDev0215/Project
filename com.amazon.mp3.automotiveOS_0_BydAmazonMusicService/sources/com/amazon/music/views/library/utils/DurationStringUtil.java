package com.amazon.music.views.library.utils;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: DurationStringUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/views/library/utils/DurationStringUtil;", "", "()V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DurationStringUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: DurationStringUtil.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/utils/DurationStringUtil$Companion;", "", "()V", "HOURS_TIME_FORMAT_STRING", "", "MINS_TIME_FORMAT_STRING", "SEC_PER_HOUR", "", "SEC_PER_MINUTE", "getFormattedDate", "millis", "", "(Ljava/lang/Long;)Ljava/lang/String;", "millisStr", "getFormattedDuration", "seconds", "secondsText", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getFormattedDuration(String secondsText) {
            if (secondsText == null || StringsKt__StringsKt.contains$default((CharSequence) secondsText, (CharSequence) ":", false, 2, (Object) null)) {
                return secondsText;
            }
            if (!TextUtils.isDigitsOnly(secondsText) || TextUtils.isEmpty(secondsText)) {
                return null;
            }
            Integer numValueOf = Integer.valueOf(secondsText);
            Intrinsics.checkNotNullExpressionValue(numValueOf, "valueOf(secondsText)");
            return getFormattedDuration(numValueOf.intValue());
        }

        public final String getFormattedDuration(int seconds) {
            int i = seconds % 60;
            int i2 = (seconds % 3600) / 60;
            int i3 = seconds / 3600;
            if (i3 > 0) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(Locale.US, "%d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i)}, 3));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
                return str;
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format(Locale.US, "%d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}, 2));
            Intrinsics.checkNotNullExpressionValue(str2, "format(locale, format, *args)");
            return str2;
        }

        public final String getFormattedDate(String millisStr) {
            Intrinsics.checkNotNullParameter(millisStr, "millisStr");
            return getFormattedDate(StringsKt__StringNumberConversionsKt.toLongOrNull(millisStr));
        }

        public final String getFormattedDate(Long millis) {
            if (millis == null) {
                return null;
            }
            Date date = new Date(millis.longValue());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(date);
        }
    }
}
