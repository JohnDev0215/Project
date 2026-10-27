package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.Calendar;
import java.util.Date;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: UiUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class vh3 {
    public static final /* synthetic */ int a = 0;

    static {
        gl.b();
    }

    public static int a(int i) {
        return (int) ((i * ya0.a(xz.a).d) + 0.5f);
    }

    public static String b(OfflineItem offlineItem) {
        Context context = xz.a;
        String strJ = oj2.j(1, offlineItem.w);
        long j = offlineItem.o;
        if (j == 0) {
            return context.getString(R$string.download_manager_list_item_description_no_size, strJ);
        }
        return context.getString(R$string.download_manager_list_item_description, Formatter.formatFileSize(context, j), strJ);
    }

    public static String c(OfflineItem offlineItem) {
        Context context = xz.a;
        String fileSize = Formatter.formatFileSize(context, offlineItem.o);
        return context.getString(R$string.download_manager_prefetch_caption, oj2.j(1, offlineItem.w), fileSize);
    }

    public static String d(Date date) {
        Context context = xz.a;
        Calendar calendarA = gl.a();
        Calendar calendarA2 = gl.a();
        calendarA.setTimeInMillis(System.currentTimeMillis());
        calendarA2.setTime(date);
        if (!jl.b(calendarA, calendarA2)) {
            return DateUtils.formatDateTime(context, date.getTime(), 4);
        }
        int iR = (int) a8.r((calendarA.getTimeInMillis() - calendarA2.getTimeInMillis()) / 3600000, 1L, 23L);
        return context.getResources().getQuantityString(R$plurals.download_manager_n_hours, iR, Integer.valueOf(iR));
    }

    public static void e(int i, Activity activity, boolean z) {
        Window window = activity.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        attributes.width = (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 78) / 100;
        if (z) {
            View rootView = window.getDecorView().getRootView();
            rootView.measure(0, 0);
            attributes.height = rootView.getMeasuredHeight();
        } else {
            attributes.height = a(i);
        }
        window.setAttributes(attributes);
    }

    public static boolean f(Activity activity) {
        if (activity.getResources().getConfiguration().orientation == 2) {
            vj1.h.getClass();
            if (!vj1.o(activity)) {
                return true;
            }
        }
        return false;
    }
}
