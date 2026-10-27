package defpackage;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.chromium.build.annotations.DoNotInline;

/* JADX INFO: compiled from: CalendarFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gl {
    public static final a a;
    public static Calendar b;

    /* JADX INFO: compiled from: CalendarFactory.java */
    public static class a extends ac<Calendar> {
        public a(int i) {
        }

        @Override // defpackage.i9
        public final Object b() {
            return Calendar.getInstance();
        }
    }

    static {
        a aVar = new a(0);
        aVar.c(i9.e);
        a = aVar;
    }

    public static Calendar a() {
        if (b == null) {
            try {
                b = a.h(250L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException unused) {
                b = Calendar.getInstance();
            }
        }
        Calendar calendar = (Calendar) b.clone();
        calendar.clear();
        return calendar;
    }

    @DoNotInline
    public static void b() {
    }
}
