package com.amazon.music.alps.liveevent.ktx;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: compiled from: String.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0002*\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0002¨\u0006\u0005"}, d2 = {"iso8601DateTime", "Lorg/joda/time/DateTime;", "", "orDefault", "default", "DMMALPS_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class StringKt {
    public static final DateTime iso8601DateTime(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return ISODateTimeFormat.dateTimeParser().parseDateTime(str);
        } catch (Exception e) {
            Log.e("DMMALPS", e.getLocalizedMessage());
            return null;
        }
    }

    public static final String orDefault(String str, String str2) {
        Intrinsics.checkNotNullParameter(str2, "default");
        return str == null || str.length() == 0 ? str2 : str;
    }
}
