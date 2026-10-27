package defpackage;

import android.content.Context;
import java.text.NumberFormat;
import java.util.Locale;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class pu2 {
    public static final int[] a = {R$string.download_manager_ui_space_free_kb, R$string.download_manager_ui_space_free_mb, R$string.download_manager_ui_space_free_gb};

    public static String a(OfflineItem.a aVar) {
        Context context = xz.a;
        Long l = aVar.b;
        boolean z = l == null;
        long j = aVar.a;
        if (z && j == 0) {
            return context.getResources().getString(R$string.download_started);
        }
        int i = aVar.c;
        if (i == 0) {
            String strA = md0.a(context, j);
            if (aVar.b == null) {
                return context.getResources().getString(R$string.download_ui_indeterminate_bytes, strA);
            }
            return context.getResources().getString(R$string.download_ui_determinate_bytes, strA, md0.a(context, l.longValue()));
        }
        if (i != 1) {
            if (i != 2) {
                return "";
            }
            if (l == null) {
                return context.getResources().getString(R$string.download_started);
            }
            return NumberFormat.getPercentInstance(Locale.getDefault()).format(((double) (l.longValue() == 0 ? 100 : (int) ((j * 100) / l.longValue()))) / 100.0d);
        }
        if (l == null) {
            int iMin = (int) Math.min(2147483647L, j);
            return context.getResources().getQuantityString(R$plurals.download_ui_files_downloaded, iMin, Integer.valueOf(iMin));
        }
        int iLongValue = (int) (l.longValue() - j);
        return iLongValue == 1 ? context.getResources().getString(R$string.one_file_left) : context.getResources().getString(R$string.files_left, Integer.valueOf(iLongValue));
    }

    public static String b(Context context, long j) {
        int i;
        int i2;
        int i3;
        long j2 = j / 1000;
        if (j2 >= 86400) {
            i = (int) (j2 / 86400);
            j2 -= ((long) i) * 86400;
        } else {
            i = 0;
        }
        if (j2 >= 3600) {
            i2 = (int) (j2 / 3600);
            j2 -= ((long) i2) * 3600;
        } else {
            i2 = 0;
        }
        if (j2 >= 60) {
            i3 = (int) (j2 / 60);
            j2 -= ((long) i3) * 60;
        } else {
            i3 = 0;
        }
        int i4 = (int) j2;
        return i >= 2 ? context.getString(R$string.remaining_duration_days, Integer.valueOf(((i2 + 12) / 24) + i)) : i > 0 ? context.getString(R$string.remaining_duration_one_day) : i2 >= 2 ? context.getString(R$string.remaining_duration_hours, Integer.valueOf(((i3 + 30) / 60) + i2)) : i2 > 0 ? context.getString(R$string.remaining_duration_one_hour) : i3 >= 2 ? context.getString(R$string.remaining_duration_minutes, Integer.valueOf(((i4 + 30) / 60) + i3)) : i3 > 0 ? context.getString(R$string.remaining_duration_one_minute) : i4 == 1 ? context.getString(R$string.remaining_duration_one_second) : context.getString(R$string.remaining_duration_seconds, Integer.valueOf(i4));
    }
}
