package h3;

import android.os.Environment;
import android.os.HandlerThread;
import h3.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: CsvFormatStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f6612e = System.getProperty("line.separator");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Date f6613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SimpleDateFormat f6614b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f6615c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f6616d;

    /* JADX INFO: renamed from: h3.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CsvFormatStrategy.java */
    public static final class C0080b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Date f6617a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        SimpleDateFormat f6618b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        g f6619c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f6620d;

        public b a() {
            if (this.f6617a == null) {
                this.f6617a = new Date();
            }
            if (this.f6618b == null) {
                this.f6618b = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.UK);
            }
            if (this.f6619c == null) {
                String str = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "logger";
                HandlerThread handlerThread = new HandlerThread("AndroidFileLogger." + str);
                handlerThread.start();
                this.f6619c = new d(new d.a(handlerThread.getLooper(), str, 512000));
            }
            return new b(this);
        }

        public C0080b b(g gVar) {
            this.f6619c = gVar;
            return this;
        }

        private C0080b() {
            this.f6620d = "PRETTY_LOGGER";
        }
    }

    private String b(String str) {
        if (m.d(str) || m.b(this.f6616d, str)) {
            return this.f6616d;
        }
        return this.f6616d + "-" + str;
    }

    public static C0080b c() {
        return new C0080b();
    }

    @Override // h3.e
    public void a(int i7, String str, String str2) {
        m.a(str2);
        String strB = b(str);
        this.f6613a.setTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toString(this.f6613a.getTime()));
        sb.append(",");
        sb.append(this.f6614b.format(this.f6613a));
        sb.append(",");
        sb.append(m.e(i7));
        sb.append(",");
        sb.append(strB);
        String str3 = f6612e;
        if (str2.contains(str3)) {
            str2 = str2.replaceAll(str3, " <br> ");
        }
        sb.append(",");
        sb.append(str2);
        sb.append(str3);
        this.f6615c.a(i7, strB, sb.toString());
    }

    private b(C0080b c0080b) {
        m.a(c0080b);
        this.f6613a = c0080b.f6617a;
        this.f6614b = c0080b.f6618b;
        this.f6615c = c0080b.f6619c;
        this.f6616d = c0080b.f6620d;
    }
}
