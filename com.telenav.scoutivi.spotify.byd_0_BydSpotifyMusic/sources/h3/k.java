package h3;

/* JADX INFO: compiled from: PrettyFormatStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f6628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f6629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f6630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final g f6631d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f6632e;

    /* JADX INFO: compiled from: PrettyFormatStrategy.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f6633a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f6634b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f6635c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        g f6636d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        String f6637e;

        public k a() {
            if (this.f6636d == null) {
                this.f6636d = new h();
            }
            return new k(this);
        }

        public b b(g gVar) {
            this.f6636d = gVar;
            return this;
        }

        public b c(int i7) {
            this.f6633a = i7;
            return this;
        }

        public b d(int i7) {
            this.f6634b = i7;
            return this;
        }

        public b e(boolean z6) {
            this.f6635c = z6;
            return this;
        }

        public b f(String str) {
            this.f6637e = str;
            return this;
        }

        private b() {
            this.f6633a = 2;
            this.f6634b = 0;
            this.f6635c = true;
            this.f6637e = "PRETTY_LOGGER";
        }
    }

    private String b(String str) {
        if (m.d(str) || m.b(this.f6632e, str)) {
            return this.f6632e;
        }
        return this.f6632e + "-" + str;
    }

    private String c(String str) {
        m.a(str);
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private int d(StackTraceElement[] stackTraceElementArr) {
        m.a(stackTraceElementArr);
        for (int i7 = 5; i7 < stackTraceElementArr.length; i7++) {
            String className = stackTraceElementArr[i7].getClassName();
            if (!className.equals(j.class.getName()) && !className.equals(i.class.getName())) {
                return i7 - 1;
            }
        }
        return -1;
    }

    private void e(int i7, String str) {
        f(i7, str, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    }

    private void f(int i7, String str, String str2) {
        m.a(str2);
        this.f6631d.a(i7, str, str2);
    }

    private void g(int i7, String str, String str2) {
        m.a(str2);
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            f(i7, str, "│ " + str3);
        }
    }

    private void h(int i7, String str) {
        f(i7, str, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
    }

    private void i(int i7, String str, int i8) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (this.f6630c) {
            f(i7, str, "│ Thread: " + Thread.currentThread().getName());
            h(i7, str);
        }
        int iD = d(stackTrace) + this.f6629b;
        if (i8 + iD > stackTrace.length) {
            i8 = (stackTrace.length - iD) - 1;
        }
        String str2 = "";
        while (i8 > 0) {
            int i9 = i8 + iD;
            if (i9 < stackTrace.length) {
                str2 = str2 + "   ";
                f(i7, str, "│ " + str2 + c(stackTrace[i9].getClassName()) + "." + stackTrace[i9].getMethodName() + "  (" + stackTrace[i9].getFileName() + ":" + stackTrace[i9].getLineNumber() + ")");
            }
            i8--;
        }
    }

    private void j(int i7, String str) {
        f(i7, str, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
    }

    public static b k() {
        return new b();
    }

    @Override // h3.e
    public void a(int i7, String str, String str2) {
        m.a(str2);
        String strB = b(str);
        j(i7, strB);
        i(i7, strB, this.f6628a);
        byte[] bytes = str2.getBytes();
        int length = bytes.length;
        if (length <= 4000) {
            if (this.f6628a > 0) {
                h(i7, strB);
            }
            g(i7, strB, str2);
            e(i7, strB);
            return;
        }
        if (this.f6628a > 0) {
            h(i7, strB);
        }
        for (int i8 = 0; i8 < length; i8 += 4000) {
            g(i7, strB, new String(bytes, i8, Math.min(length - i8, 4000)));
        }
        e(i7, strB);
    }

    private k(b bVar) {
        m.a(bVar);
        this.f6628a = bVar.f6633a;
        this.f6629b = bVar.f6634b;
        this.f6630c = bVar.f6635c;
        this.f6631d = bVar.f6636d;
        this.f6632e = bVar.f6637e;
    }
}
