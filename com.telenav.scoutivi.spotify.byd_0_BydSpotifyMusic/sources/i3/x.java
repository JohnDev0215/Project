package i3;

/* JADX INFO: compiled from: SearchBean.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f6993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6994b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f6995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f6996d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f6997e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f6998f;

    /* JADX INFO: compiled from: SearchBean.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private c f6999a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f7000b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f7001c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f7002d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f7003e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f7004f;

        private b() {
        }

        public static b b(String str) {
            b bVar = new b();
            bVar.f6999a = c.ALBUM;
            bVar.f7002d = str;
            return bVar;
        }

        public static b c(String str) {
            b bVar = new b();
            bVar.f6999a = c.ARTIST;
            bVar.f7001c = str;
            return bVar;
        }

        public static b d(String str) {
            b bVar = new b();
            bVar.f6999a = c.GENRE;
            bVar.f7003e = str;
            return bVar;
        }

        public static b e(String str) {
            b bVar = new b();
            bVar.f6999a = c.PLAYLIST;
            bVar.f7004f = str;
            return bVar;
        }

        public static b f(String str) {
            b bVar = new b();
            bVar.f6999a = c.MEDIA;
            bVar.f7000b = str;
            return bVar;
        }

        public x a() {
            return new x(this.f6999a, this.f7000b, this.f7001c, this.f7002d, this.f7003e, this.f7004f);
        }

        public b g(String str) {
            this.f7002d = str;
            return this;
        }

        public b h(String str) {
            this.f7001c = str;
            return this;
        }

        public b i(String str) {
            this.f7003e = str;
            return this;
        }

        public b j(String str) {
            this.f7004f = str;
            return this;
        }

        public b k(String str) {
            this.f7000b = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: SearchBean.java */
    public enum c {
        ARTIST,
        ALBUM,
        GENRE,
        PLAYLIST,
        MEDIA
    }

    public String a() {
        return this.f6996d;
    }

    public String b() {
        return this.f6995c;
    }

    public String c() {
        return this.f6997e;
    }

    public String d() {
        return this.f6998f;
    }

    public c e() {
        return this.f6993a;
    }

    public String f() {
        return this.f6994b;
    }

    private x(c cVar, String str, String str2, String str3, String str4, String str5) {
        this.f6993a = cVar;
        this.f6994b = str;
        this.f6995c = str2;
        this.f6996d = str3;
        this.f6997e = str4;
        this.f6998f = str5;
    }
}
