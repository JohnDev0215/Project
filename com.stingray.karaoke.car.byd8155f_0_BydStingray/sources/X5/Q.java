package X5;

import com.stingray.karaoke.car.player.PlayerStatus;
import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends AbstractC2410c {
    public S l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PlayerStatus f10193m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f10194n;
    public final /* synthetic */ S o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f10195p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(S s6, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = s6;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f10194n = obj;
        this.f10195p |= Integer.MIN_VALUE;
        return this.o.a(null, this);
    }
}
