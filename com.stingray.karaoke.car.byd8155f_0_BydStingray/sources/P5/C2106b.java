package p5;

import j5.m;
import j5.p;
import j5.y;
import j5.z;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import q5.C2185a;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: p5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2106b extends y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f20902b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SimpleDateFormat f20903a;

    /* JADX INFO: renamed from: p5.b$a */
    public class a implements z {
        @Override // j5.z
        public final y a(m mVar, C2185a c2185a) {
            if (c2185a.f21253a == Time.class) {
                return new C2106b(0);
            }
            return null;
        }
    }

    public /* synthetic */ C2106b(int i7) {
        this();
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        Time time;
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        String strO = c2297a.O();
        synchronized (this) {
            TimeZone timeZone = this.f20903a.getTimeZone();
            try {
                try {
                    time = new Time(this.f20903a.parse(strO).getTime());
                } catch (ParseException e10) {
                    throw new p("Failed parsing '" + strO + "' as SQL Time; at path " + c2297a.q(true), e10);
                }
            } finally {
                this.f20903a.setTimeZone(timeZone);
            }
        }
        return time;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        String str;
        Time time = (Time) obj;
        if (time == null) {
            c2298b.v();
            return;
        }
        synchronized (this) {
            str = this.f20903a.format((Date) time);
        }
        c2298b.L(str);
    }

    private C2106b() {
        this.f20903a = new SimpleDateFormat("hh:mm:ss a");
    }
}
