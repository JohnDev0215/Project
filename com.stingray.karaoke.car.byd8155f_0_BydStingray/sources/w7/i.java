package W7;

import H7.InterfaceC0320e;
import java.lang.reflect.Member;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i extends A7.i implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final i f9886t = new i(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        Member member = (Member) obj;
        A7.m.f(member, "p0");
        return Boolean.valueOf(member.isSynthetic());
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "isSynthetic";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A7.A.f443a.b(Member.class);
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "isSynthetic()Z";
    }
}
