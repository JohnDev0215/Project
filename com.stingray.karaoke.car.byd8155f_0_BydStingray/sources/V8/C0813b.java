package V8;

import S8.InterfaceC0725w;
import U8.EnumC0810a;
import W8.C0846f;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: renamed from: V8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0813b extends W8.g {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f9608q = AtomicIntegerFieldUpdater.newUpdater(C0813b.class, "consumed$volatile");
    private volatile /* synthetic */ int consumed$volatile;
    public final U8.l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f9609p;

    public /* synthetic */ C0813b(U8.l lVar, boolean z6) {
        this(lVar, z6, C2195i.l, -3, EnumC0810a.l);
    }

    @Override // W8.g, V8.InterfaceC0817f
    public final Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) throws Throwable {
        m7.q qVar = m7.q.f19267a;
        if (this.f9916m != -3) {
            Object objB = super.b(interfaceC0818g, interfaceC2189c);
            return objB == EnumC2303a.l ? objB : qVar;
        }
        boolean z6 = this.f9609p;
        if (z6 && f9608q.getAndSet(this, 1) != 0) {
            throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once");
        }
        Object objI = AbstractC0828q.i(interfaceC0818g, this.o, z6, interfaceC2189c);
        return objI == EnumC2303a.l ? objI : qVar;
    }

    @Override // W8.g
    public final String c() {
        return "channel=" + this.o;
    }

    @Override // W8.g
    public final Object d(U8.x xVar, C0846f c0846f) throws Throwable {
        Object objI = AbstractC0828q.i(new W8.C(xVar), this.o, this.f9609p, c0846f);
        return objI == EnumC2303a.l ? objI : m7.q.f19267a;
    }

    @Override // W8.g
    public final W8.g e(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return new C0813b(this.o, this.f9609p, interfaceC2194h, i7, enumC0810a);
    }

    @Override // W8.g
    public final InterfaceC0817f f() {
        return new C0813b(this.o, this.f9609p);
    }

    @Override // W8.g
    public final U8.z g(InterfaceC0725w interfaceC0725w) {
        if (!this.f9609p || f9608q.getAndSet(this, 1) == 0) {
            return this.f9916m == -3 ? this.o : super.g(interfaceC0725w);
        }
        throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once");
    }

    public C0813b(U8.l lVar, boolean z6, InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        super(interfaceC2194h, i7, enumC0810a);
        this.o = lVar;
        this.f9609p = z6;
        this.consumed$volatile = 0;
    }
}
