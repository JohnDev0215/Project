package com.bumptech.glide.load.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f2141a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f2142b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f2143c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f2144d = new d();
    public static final j e = new e();

    class a extends j {
        a() {
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean a() {
            return true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean b() {
            return true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean c(com.bumptech.glide.load.a aVar) {
            return aVar == com.bumptech.glide.load.a.REMOTE;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean d(boolean z, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.c cVar) {
            return (aVar == com.bumptech.glide.load.a.RESOURCE_DISK_CACHE || aVar == com.bumptech.glide.load.a.MEMORY_CACHE) ? false : true;
        }
    }

    class b extends j {
        b() {
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean a() {
            return false;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean b() {
            return false;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean c(com.bumptech.glide.load.a aVar) {
            return false;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean d(boolean z, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.c cVar) {
            return false;
        }
    }

    class c extends j {
        c() {
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean a() {
            return true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean b() {
            return false;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean c(com.bumptech.glide.load.a aVar) {
            return (aVar == com.bumptech.glide.load.a.DATA_DISK_CACHE || aVar == com.bumptech.glide.load.a.MEMORY_CACHE) ? false : true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean d(boolean z, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.c cVar) {
            return false;
        }
    }

    class d extends j {
        d() {
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean a() {
            return false;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean b() {
            return true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean c(com.bumptech.glide.load.a aVar) {
            return false;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean d(boolean z, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.c cVar) {
            return (aVar == com.bumptech.glide.load.a.RESOURCE_DISK_CACHE || aVar == com.bumptech.glide.load.a.MEMORY_CACHE) ? false : true;
        }
    }

    class e extends j {
        e() {
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean a() {
            return true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean b() {
            return true;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean c(com.bumptech.glide.load.a aVar) {
            return aVar == com.bumptech.glide.load.a.REMOTE;
        }

        @Override // com.bumptech.glide.load.n.j
        public boolean d(boolean z, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.c cVar) {
            return ((z && aVar == com.bumptech.glide.load.a.DATA_DISK_CACHE) || aVar == com.bumptech.glide.load.a.LOCAL) && cVar == com.bumptech.glide.load.c.TRANSFORMED;
        }
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean c(com.bumptech.glide.load.a aVar);

    public abstract boolean d(boolean z, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.c cVar);
}
