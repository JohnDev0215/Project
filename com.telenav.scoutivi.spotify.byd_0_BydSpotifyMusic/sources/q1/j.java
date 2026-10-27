package q1;

/* JADX INFO: compiled from: DiskCacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f9021a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f9022b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f9023c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f9024d = new d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f9025e = new e();

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class a extends j {
        a() {
        }

        @Override // q1.j
        public boolean a() {
            return true;
        }

        @Override // q1.j
        public boolean b() {
            return true;
        }

        @Override // q1.j
        public boolean c(n1.a aVar) {
            return aVar == n1.a.REMOTE;
        }

        @Override // q1.j
        public boolean d(boolean z6, n1.a aVar, n1.c cVar) {
            return (aVar == n1.a.RESOURCE_DISK_CACHE || aVar == n1.a.MEMORY_CACHE) ? false : true;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class b extends j {
        b() {
        }

        @Override // q1.j
        public boolean a() {
            return false;
        }

        @Override // q1.j
        public boolean b() {
            return false;
        }

        @Override // q1.j
        public boolean c(n1.a aVar) {
            return false;
        }

        @Override // q1.j
        public boolean d(boolean z6, n1.a aVar, n1.c cVar) {
            return false;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class c extends j {
        c() {
        }

        @Override // q1.j
        public boolean a() {
            return true;
        }

        @Override // q1.j
        public boolean b() {
            return false;
        }

        @Override // q1.j
        public boolean c(n1.a aVar) {
            return (aVar == n1.a.DATA_DISK_CACHE || aVar == n1.a.MEMORY_CACHE) ? false : true;
        }

        @Override // q1.j
        public boolean d(boolean z6, n1.a aVar, n1.c cVar) {
            return false;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class d extends j {
        d() {
        }

        @Override // q1.j
        public boolean a() {
            return false;
        }

        @Override // q1.j
        public boolean b() {
            return true;
        }

        @Override // q1.j
        public boolean c(n1.a aVar) {
            return false;
        }

        @Override // q1.j
        public boolean d(boolean z6, n1.a aVar, n1.c cVar) {
            return (aVar == n1.a.RESOURCE_DISK_CACHE || aVar == n1.a.MEMORY_CACHE) ? false : true;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class e extends j {
        e() {
        }

        @Override // q1.j
        public boolean a() {
            return true;
        }

        @Override // q1.j
        public boolean b() {
            return true;
        }

        @Override // q1.j
        public boolean c(n1.a aVar) {
            return aVar == n1.a.REMOTE;
        }

        @Override // q1.j
        public boolean d(boolean z6, n1.a aVar, n1.c cVar) {
            return ((z6 && aVar == n1.a.DATA_DISK_CACHE) || aVar == n1.a.LOCAL) && cVar == n1.c.TRANSFORMED;
        }
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean c(n1.a aVar);

    public abstract boolean d(boolean z6, n1.a aVar, n1.c cVar);
}
