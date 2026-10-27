package K0;

import S8.InterfaceC0725w;
import android.graphics.Rect;
import android.view.ScrollCaptureSession;
import h8.AbstractC1386c;
import java.util.function.Consumer;
import m7.q;
import n0.AbstractC1861K;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f3479m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ScrollCaptureSession f3480n;
    public final /* synthetic */ Rect o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Consumer f3481p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(f fVar, ScrollCaptureSession scrollCaptureSession, Rect rect, Consumer consumer, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3479m = fVar;
        this.f3480n = scrollCaptureSession;
        this.o = rect;
        this.f3481p = consumer;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new b(this.f3479m, this.f3480n, this.o, this.f3481p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            ScrollCaptureSession scrollCaptureSession = this.f3480n;
            Rect rect = this.o;
            a1.i iVar = new a1.i(rect.left, rect.top, rect.right, rect.bottom);
            this.l = 1;
            obj = f.a(this.f3479m, scrollCaptureSession, iVar, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        this.f3481p.accept(AbstractC1861K.A((a1.i) obj));
        return q.f19267a;
    }
}
