package p;

import android.content.Context;
import android.content.res.Resources;
import com.spotify.music.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class vj {
    public final DateFormat a;
    public final SimpleDateFormat b;
    public final SimpleDateFormat c;
    public final u6 d;
    public final Context e;

    public vj(u6 u6Var, Context context) {
        Locale localeB = new ac1(new cc1(yw.a(context.getResources().getConfiguration()))).b(0);
        this.d = u6Var;
        this.e = context;
        this.b = new SimpleDateFormat("d MMM", localeB);
        this.a = DateFormat.getDateInstance(2, localeB);
        this.c = new SimpleDateFormat("EEE", localeB);
    }

    public static String a(Resources resources, bc0 bc0Var) {
        long j = bc0Var.b;
        long j2 = bc0Var.c;
        if (j <= 0) {
            return resources.getQuantityString(R.plurals.android_auto_podcast_episode_time_format_min, (int) j2, Long.valueOf(j2));
        }
        return resources.getQuantityString(R.plurals.android_auto_podcast_episode_time_format_hours, (int) j, Long.valueOf(j)) + ' ' + resources.getQuantityString(R.plurals.android_auto_podcast_episode_time_format_min, (int) j2, Long.valueOf(j2));
    }

    public static String d(Resources resources, bc0 bc0Var) {
        String quantityString;
        long j = bc0Var.b;
        long j2 = bc0Var.c;
        if (j > 0) {
            quantityString = resources.getQuantityString(R.plurals.android_auto_podcast_episode_time_format_hours, (int) j, Long.valueOf(j)) + ' ' + resources.getQuantityString(R.plurals.android_auto_podcast_episode_time_format_min, (int) j2, Long.valueOf(j2));
        } else {
            quantityString = resources.getQuantityString(R.plurals.android_auto_podcast_episode_time_format_min, (int) j2, Long.valueOf(j2));
        }
        return resources.getString(R.string.android_auto_podcast_episode_time_left, quantityString);
    }

    public final String b(Resources resources, rv1 rv1Var) {
        this.d.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        Date date = rv1Var.d;
        switch ((int) bc0.a(jCurrentTimeMillis - date.getTime()).a) {
            case 0:
                return resources.getString(R.string.android_auto_podcast_episode_publication_today);
            case 1:
                return resources.getString(R.string.android_auto_podcast_episode_publication_yesterday);
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return this.c.format(Long.valueOf(date.getTime()));
            default:
                Calendar calendar = (Calendar) Calendar.getInstance().clone();
                calendar.setTime(date);
                if (Calendar.getInstance().get(1) == calendar.get(1)) {
                    return this.b.format(Long.valueOf(date.getTime()));
                }
                return this.a.format(Long.valueOf(date.getTime()));
        }
    }

    public final String c(rv1 rv1Var) {
        String strB;
        Resources resources = this.e.getResources();
        if (rv1Var.f) {
            strB = rv1Var.g ? resources.getString(R.string.audiobook_sample_episode_subtitle) : null;
        } else {
            String str = rv1Var.i;
            strB = (str == null || str.isEmpty()) ? b(resources, rv1Var) : resources.getString(R.string.one_separator_placeholder, str, b(resources, rv1Var));
        }
        int i = rv1Var.b;
        int i2 = rv1Var.j;
        return i2 != 1 ? i2 != 2 ? strB == null ? a(resources, bc0.a(((long) i) * 1000)) : resources.getString(R.string.one_separator_placeholder, strB, a(resources, bc0.a(((long) i) * 1000))) : strB == null ? resources.getString(R.string.android_auto_podcast_episode_played) : resources.getString(R.string.one_separator_placeholder, strB, resources.getString(R.string.android_auto_podcast_episode_played)) : strB == null ? d(resources, bc0.a(((long) i) * 1000)) : resources.getString(R.string.one_separator_placeholder, strB, d(resources, bc0.a(((long) i) * 1000)));
    }
}
