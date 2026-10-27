package f2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public abstract class e implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f14551b;
    public b c;
    public b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f14552e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ByteBuffer f14553f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ByteBuffer f14554g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f14555h;

    public e() {
        ByteBuffer byteBuffer = d.f14550a;
        this.f14553f = byteBuffer;
        this.f14554g = byteBuffer;
        b bVar = b.f14547e;
        this.d = bVar;
        this.f14552e = bVar;
        this.f14551b = bVar;
        this.c = bVar;
    }

    @Override // f2.d
    public ByteBuffer a() {
        ByteBuffer byteBuffer = this.f14554g;
        this.f14554g = d.f14550a;
        return byteBuffer;
    }

    @Override // f2.d
    public boolean b() {
        return this.f14552e != b.f14547e;
    }

    @Override // f2.d
    public final b c(b bVar) {
        this.d = bVar;
        this.f14552e = h(bVar);
        return b() ? this.f14552e : b.f14547e;
    }

    @Override // f2.d
    public final void d() {
        flush();
        this.f14553f = d.f14550a;
        b bVar = b.f14547e;
        this.d = bVar;
        this.f14552e = bVar;
        this.f14551b = bVar;
        this.c = bVar;
        k();
    }

    @Override // f2.d
    public final void e() {
        this.f14555h = true;
        j();
    }

    @Override // f2.d
    public boolean f() {
        return this.f14555h && this.f14554g == d.f14550a;
    }

    @Override // f2.d
    public final void flush() {
        this.f14554g = d.f14550a;
        this.f14555h = false;
        this.f14551b = this.d;
        this.c = this.f14552e;
        i();
    }

    public abstract b h(b bVar);

    public void i() {
    }

    public void j() {
    }

    public void k() {
    }

    public final ByteBuffer l(int i7) {
        if (this.f14553f.capacity() < i7) {
            this.f14553f = ByteBuffer.allocateDirect(i7).order(ByteOrder.nativeOrder());
        } else {
            this.f14553f.clear();
        }
        ByteBuffer byteBuffer = this.f14553f;
        this.f14554g = byteBuffer;
        return byteBuffer;
    }
}
