package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n4 implements yb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ yb f451a;
    public final /* synthetic */ o4 b;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ byte[] f452a;
        public final /* synthetic */ int b;

        public a(byte[] bArr, int i) {
            this.f452a = bArr;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            n4.this.f451a.a(this.f452a, this.b);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ md f453a;

        public b(md mdVar) {
            this.f453a = mdVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            n4.this.f451a.a(this.f453a);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n4.this.f451a.onNetworkError();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n4.this.f451a.a();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n4.this.f451a.b();
        }
    }

    public n4(o4 o4Var, yb ybVar) {
        this.b = o4Var;
        this.f451a = ybVar;
    }

    @Override // com.amazon.identity.auth.device.yb
    public void a(byte[] bArr, int i) {
        ba.a(this.b.b, new a(bArr, i));
    }

    @Override // com.amazon.identity.auth.device.yb
    public void b() {
        ba.a(this.b.b, new e());
    }

    @Override // com.amazon.identity.auth.device.yb
    public void onNetworkError() {
        ba.a(this.b.b, new c());
    }

    @Override // com.amazon.identity.auth.device.yb
    public void a(md mdVar) {
        ba.a(this.b.b, new b(mdVar));
    }

    @Override // com.amazon.identity.auth.device.yb
    public void a() {
        ba.a(this.b.b, new d());
    }
}
