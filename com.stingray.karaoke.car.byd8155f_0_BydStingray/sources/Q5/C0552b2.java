package Q5;

import F0.C0221p;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import android.content.Context;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.kca.model.KaraokeSongResponseV2;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.b2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0552b2 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ SongQueuePosition f6710m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Context f6711n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0221p f6712p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0221p f6713q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0221p f6714r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ X1 f6715s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ KaraokeSongResponseV2 f6716t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ F6.b f6717u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ V8.f0 f6718v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0552b2(SongQueuePosition songQueuePosition, Context context, boolean z6, C0221p c0221p, C0221p c0221p2, C0221p c0221p3, X1 x12, KaraokeSongResponseV2 karaokeSongResponseV2, F6.b bVar, V8.f0 f0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6710m = songQueuePosition;
        this.f6711n = context;
        this.o = z6;
        this.f6712p = c0221p;
        this.f6713q = c0221p2;
        this.f6714r = c0221p3;
        this.f6715s = x12;
        this.f6716t = karaokeSongResponseV2;
        this.f6717u = bVar;
        this.f6718v = f0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0552b2(this.f6710m, this.f6711n, this.o, this.f6712p, this.f6713q, this.f6714r, this.f6715s, this.f6716t, this.f6717u, this.f6718v, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0552b2) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            if (AbstractC0727y.i(300L, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        io.sentry.config.a.I(this.f6710m, this.f6711n, this.o, this.f6712p, this.f6713q, this.f6714r, this.f6715s, this.f6716t, this.f6717u, this.f6718v);
        return m7.q.f19267a;
    }
}
