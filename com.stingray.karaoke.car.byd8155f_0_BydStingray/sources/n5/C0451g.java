package N5;

import com.stingray.karaoke.car.player.PlayerActionType;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0451g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PlayerActionType f4693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s7.i f4694b;
    public final InterfaceC2782k c;

    /* JADX WARN: Multi-variable type inference failed */
    public C0451g(PlayerActionType playerActionType, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        A7.m.f(playerActionType, "actionType");
        A7.m.f(interfaceC2782k2, "onActionCompleted");
        this.f4693a = playerActionType;
        this.f4694b = (s7.i) interfaceC2782k;
        this.c = interfaceC2782k2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r5v1, types: [s7.i, z7.k] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof N5.C0450f
            if (r0 == 0) goto L13
            r0 = r6
            N5.f r0 = (N5.C0450f) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            N5.f r0 = new N5.f
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.f4689m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            z7.k r5 = r0.l
            h8.AbstractC1386c.u(r6)
            goto L5b
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            h8.AbstractC1386c.u(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r2 = "PlayerManager: Executing action: "
            r6.<init>(r2)
            com.stingray.karaoke.car.player.PlayerActionType r2 = r5.f4693a
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            java.lang.String r2 = "text"
            A7.m.f(r6, r2)
            z7.k r6 = r5.c
            r0.l = r6
            r0.o = r3
            s7.i r5 = r5.f4694b
            java.lang.Object r5 = r5.b(r0)
            if (r5 != r1) goto L58
            return r1
        L58:
            r4 = r6
            r6 = r5
            r5 = r4
        L5b:
            r5.b(r6)
            m7.q r5 = m7.q.f19267a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: N5.C0451g.a(s7.c):java.lang.Object");
    }

    public /* synthetic */ C0451g(PlayerActionType playerActionType, InterfaceC2782k interfaceC2782k) {
        this(playerActionType, interfaceC2782k, new A3.c(13));
    }
}
