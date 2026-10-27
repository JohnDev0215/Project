package U5;

import S8.InterfaceC0725w;
import T.C0732b0;
import T.X;
import com.stingray.drivemodedetector.R;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9082m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f9083n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X f9084p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f9085q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(int i7, int i10, float f10, boolean z6, X x8, C0732b0 c0732b0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = i7;
        this.f9082m = i10;
        this.f9083n = f10;
        this.o = z6;
        this.f9084p = x8;
        this.f9085q = c0732b0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new B(this.l, this.f9082m, this.f9083n, this.o, this.f9084p, this.f9085q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        B b10 = (B) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        b10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        X x8 = this.f9084p;
        if (!((Boolean) x8.getValue()).booleanValue()) {
            int i7 = R.drawable.star_10;
            C0732b0 c0732b0 = this.f9085q;
            int i10 = this.l;
            int i11 = this.f9082m;
            if (i10 == i11) {
                switch ((int) ((this.f9083n - i11) * 10)) {
                    case 1:
                        i7 = R.drawable.star_1;
                        break;
                    case 2:
                        i7 = R.drawable.star_2;
                        break;
                    case 3:
                        i7 = R.drawable.star_3;
                        break;
                    case 4:
                        i7 = R.drawable.star_4;
                        break;
                    case 5:
                        i7 = R.drawable.star_5;
                        break;
                    case 6:
                        i7 = R.drawable.star_6;
                        break;
                    case 7:
                        i7 = R.drawable.star_7;
                        break;
                    case 8:
                        i7 = R.drawable.star_8;
                        break;
                    case 9:
                        i7 = R.drawable.star_9;
                        break;
                    case 10:
                        break;
                    default:
                        i7 = R.drawable.star_0;
                        break;
                }
                c0732b0.j(i7);
            } else if (i11 > i10) {
                if (this.o) {
                    i7 = R.drawable.star_10_glow;
                }
                c0732b0.j(i7);
                x8.setValue(Boolean.TRUE);
            }
        }
        return m7.q.f19267a;
    }
}
