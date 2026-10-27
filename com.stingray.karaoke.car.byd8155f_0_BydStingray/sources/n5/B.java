package N5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.player.PlayerActionType;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4593m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0451g f4594n;
    public final /* synthetic */ Z o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(C0451g c0451g, Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4594n = c0451g;
        this.o = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        B b10 = new B(this.f4594n, this.o, interfaceC2189c);
        b10.f4593m = obj;
        return b10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((B) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Throwable th;
        InterfaceC0725w interfaceC0725w;
        Exception e10;
        A a10;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0451g c0451g = this.f4594n;
        PlayerActionType playerActionType = c0451g.f4693a;
        Z z6 = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w2 = (InterfaceC0725w) this.f4593m;
            try {
                this.f4593m = interfaceC0725w2;
                this.l = 1;
                if (c0451g.a(this) == enumC2303a) {
                    return enumC2303a;
                }
                interfaceC0725w = interfaceC0725w2;
            } catch (Exception e11) {
                e10 = e11;
                interfaceC0725w = interfaceC0725w2;
                String str = "PlayerManager: Action " + playerActionType + " failed with error: " + e10;
                A7.m.f(str, "error");
                B9.a aVar = Timber.f22577a;
                aVar.e("");
                aVar.b(str, new Object[0]);
                z6.r(e10);
                z6.f4629H.l(null);
                a10 = new A(z6, null);
            } catch (Throwable th2) {
                th = th2;
                interfaceC0725w = interfaceC0725w2;
                z6.f4629H.l(null);
                AbstractC0727y.w(interfaceC0725w, null, null, new A(z6, null), 3);
                throw th;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            interfaceC0725w = (InterfaceC0725w) this.f4593m;
            try {
                try {
                    AbstractC1386c.u(obj);
                } catch (Exception e12) {
                    e10 = e12;
                    String str2 = "PlayerManager: Action " + playerActionType + " failed with error: " + e10;
                    A7.m.f(str2, "error");
                    B9.a aVar2 = Timber.f22577a;
                    aVar2.e("");
                    aVar2.b(str2, new Object[0]);
                    z6.r(e10);
                    z6.f4629H.l(null);
                    a10 = new A(z6, null);
                }
            } catch (Throwable th3) {
                th = th3;
                z6.f4629H.l(null);
                AbstractC0727y.w(interfaceC0725w, null, null, new A(z6, null), 3);
                throw th;
            }
        }
        A7.m.f("PlayerManager: Action " + playerActionType + " completed successfully", "text");
        z6.f4629H.l(null);
        a10 = new A(z6, null);
        AbstractC0727y.w(interfaceC0725w, null, null, a10, 3);
        return m7.q.f19267a;
    }
}
