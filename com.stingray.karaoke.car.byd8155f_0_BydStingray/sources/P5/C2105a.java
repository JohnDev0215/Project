package p5;

import j5.m;
import j5.p;
import j5.y;
import j5.z;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import q5.C2185a;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: p5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2105a extends y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0001a f20900b = new C0001a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SimpleDateFormat f20901a;

    /* JADX INFO: renamed from: p5.a$a, reason: collision with other inner class name */
    public class C0001a implements z {
        @Override // j5.z
        public final y a(m mVar, C2185a c2185a) {
            if (c2185a.f21253a == Date.class) {
                return new C2105a(0);
            }
            return null;
        }
    }

    public /* synthetic */ C2105a(int i7) {
        this();
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        Date date;
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        String strO = c2297a.O();
        synchronized (this) {
            TimeZone timeZone = this.f20901a.getTimeZone();
            try {
                try {
                    date = new Date(this.f20901a.parse(strO).getTime());
                } catch (ParseException e10) {
                    throw new p("Failed parsing '" + strO + "' as SQL Date; at path " + c2297a.q(true), e10);
                }
            } finally {
                this.f20901a.setTimeZone(timeZone);
            }
        }
        return date;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        String str;
        Date date = (Date) obj;
        if (date == null) {
            c2298b.v();
            return;
        }
        synchronized (this) {
            str = this.f20901a.format((java.util.Date) date);
        }
        c2298b.L(str);
    }

    private C2105a() {
        this.f20901a = new SimpleDateFormat("MMM d, yyyy");
    }
}
