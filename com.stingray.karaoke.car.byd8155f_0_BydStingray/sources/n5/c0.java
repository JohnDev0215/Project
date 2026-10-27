package N5;

import android.net.Uri;
import com.stingray.karaoke.car.player.PlayerService;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        c0 c0Var = new c0(2, interfaceC2189c);
        c0Var.l = obj;
        return c0Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((c0) create((String) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        String str = (String) this.l;
        C0452h c0452h = PlayerService.f13010w;
        if (c0452h != null) {
            return (Uri) c0452h.b(str);
        }
        return null;
    }
}
