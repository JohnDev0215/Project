package S3;

import S8.InterfaceC0725w;
import a4.AbstractC0922c;
import android.content.Context;
import android.graphics.Typeface;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class q extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ O3.a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f7960m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f7961n;
    public final /* synthetic */ String o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(O3.a aVar, Context context, String str, String str2, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = aVar;
        this.f7960m = context;
        this.f7961n = str;
        this.o = str2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new q(this.l, this.f7960m, this.f7961n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        q qVar = (q) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar2 = m7.q.f19267a;
        qVar.invokeSuspend(qVar2);
        return qVar2;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        for (U3.c cVar : this.l.f5072f.values()) {
            Context context = this.f7960m;
            A7.m.c(cVar);
            String str = cVar.c;
            try {
                Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), this.f7961n + cVar.f9007a + this.o);
                try {
                    A7.m.c(typefaceCreateFromAsset);
                    A7.m.e(str, "getStyle(...)");
                    int i7 = 0;
                    boolean zC0 = Q8.h.C0(str, "Italic", false);
                    boolean zC02 = Q8.h.C0(str, "Bold", false);
                    if (zC0 && zC02) {
                        i7 = 3;
                    } else if (zC0) {
                        i7 = 2;
                    } else if (zC02) {
                        i7 = 1;
                    }
                    if (typefaceCreateFromAsset.getStyle() != i7) {
                        typefaceCreateFromAsset = Typeface.create(typefaceCreateFromAsset, i7);
                    }
                    cVar.d = typefaceCreateFromAsset;
                } catch (Exception unused) {
                    AbstractC0922c.f11094a.getClass();
                }
            } catch (Exception unused2) {
                AbstractC0922c.f11094a.getClass();
            }
        }
        return m7.q.f19267a;
    }
}
