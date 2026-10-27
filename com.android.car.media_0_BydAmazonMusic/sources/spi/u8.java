package spi;

import android.annotation.SuppressLint;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: PlaybackProgress.java */
/* JADX INFO: loaded from: classes.dex */
public final class u8 {
    public final long a;
    public final long b;

    public u8(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    @SuppressLint({"DefaultLocale"})
    public static String a(long j, boolean z) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(j);
        long minutes = timeUnit.toMinutes(j) % TimeUnit.HOURS.toMinutes(1L);
        long seconds = timeUnit.toSeconds(j) % TimeUnit.MINUTES.toSeconds(1L);
        return z ? String.format("%d:%02d:%02d", Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)) : String.format("%d:%02d", Long.valueOf(minutes), Long.valueOf(seconds));
    }

    public final CharSequence b() {
        return a(this.a, TimeUnit.MILLISECONDS.toHours(this.b) > 0);
    }

    public final CharSequence c() {
        return a(this.b, TimeUnit.MILLISECONDS.toHours(this.b) > 0);
    }

    public final boolean d() {
        return this.b > 0 && this.a != -1;
    }
}
