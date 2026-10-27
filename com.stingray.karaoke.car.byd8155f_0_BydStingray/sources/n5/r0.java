package N5;

import Q5.C0618s1;
import S8.InterfaceC0725w;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.utils.ToastType;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import java.util.Collection;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t0 f4760m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4761n;
    public final /* synthetic */ String[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f4762p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f4763q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f4764r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f4765s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(t0 t0Var, boolean z6, String[] strArr, boolean z10, int i7, boolean z11, boolean z12, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4760m = t0Var;
        this.f4761n = z6;
        this.o = strArr;
        this.f4762p = z10;
        this.f4763q = i7;
        this.f4764r = z11;
        this.f4765s = z12;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r0(this.f4760m, this.f4761n, this.o, this.f4762p, this.f4763q, this.f4764r, this.f4765s, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((r0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        boolean z6 = this.f4761n;
        t0 t0Var = this.f4760m;
        try {
        } catch (Exception unused) {
            t0Var.f4770a.k();
        }
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (((V8.f0) t0Var.f4773f.l).getValue() == DriveMode.DRIVING && z6) {
                ToastType toastType = ToastType.DRIVING_DISABLED_ERROR;
                A7.m.f(toastType, "toastType");
                h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
                t0Var.f4770a.k();
                return m7.q.f19267a;
            }
            K5.F f10 = t0Var.d;
            String str = this.o[0];
            this.l = 1;
            objC = f10.c(str, this);
            if (objC == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            objC = obj;
        }
        KaraokeSongResponseV2 karaokeSongResponseV2 = (KaraokeSongResponseV2) objC;
        boolean z10 = this.f4762p;
        SongQueuePosition songQueuePosition = z6 ? SongQueuePosition.SCORE_NOW : z10 ? SongQueuePosition.SING_NOW : this.f4763q > ((Collection) ((V8.f0) t0Var.f4770a.f4752v.l).getValue()).size() ? SongQueuePosition.SING_LAST : SongQueuePosition.SING_NEXT;
        boolean z11 = !t0Var.f4770a.g() && !t0Var.f4770a.f4740h.isEmpty() && ((V8.f0) t0Var.f4771b.f4635N.l).getValue() == PlayerStatus.STOPPED && (songQueuePosition == SongQueuePosition.SING_LAST || songQueuePosition == SongQueuePosition.SING_NEXT);
        if (z10) {
            t0Var.f4771b.x(this.f4761n, true, ActionOwner.COMPANION, true, A7.m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE));
        }
        t0Var.f4770a.c(new f0(karaokeSongResponseV2), this.f4763q, z10 || z11, z10 || z11, this.f4764r, this.f4765s, songQueuePosition, ActionOwner.COMPANION, null);
        return m7.q.f19267a;
    }
}
