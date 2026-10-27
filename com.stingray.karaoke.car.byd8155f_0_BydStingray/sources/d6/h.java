package d6;

import S8.InterfaceC0725w;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends s7.i implements InterfaceC2785n {
    public j l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13473m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f13474n;
    public final /* synthetic */ String o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(j jVar, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f13474n = jVar;
        this.o = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new h(this.f13474n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((h) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x008c A[Catch: Exception -> 0x0023, TryCatch #0 {Exception -> 0x0023, blocks: (B:9:0x0017, B:37:0x0093, B:16:0x0030, B:34:0x0075, B:36:0x008c, B:19:0x0036, B:26:0x0050, B:28:0x005d, B:30:0x006a, B:22:0x003d), top: B:42:0x000c }] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r7.f13473m
            java.lang.String r2 = ""
            java.lang.String r3 = r7.o
            r4 = 2
            r5 = 1
            d6.j r6 = r7.f13474n
            if (r1 == 0) goto L3a
            if (r1 == r5) goto L34
            if (r1 == r4) goto L2e
            r0 = 3
            if (r1 != r0) goto L26
            d6.j r7 = r7.l
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Exception -> L23
            n6.G r8 = (n6.C1966G) r8     // Catch: java.lang.Exception -> L23
            T.e0 r7 = r7.f13478C     // Catch: java.lang.Exception -> L23
            r7.setValue(r8)     // Catch: java.lang.Exception -> L23
            goto L93
        L23:
            r7 = move-exception
            goto L99
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2e:
            d6.j r7 = r7.l
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Exception -> L23
            goto L75
        L34:
            d6.j r1 = r7.l
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Exception -> L23
            goto L50
        L3a:
            h8.AbstractC1386c.u(r8)
            com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState r8 = com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState.Loading     // Catch: java.lang.Exception -> L23
            r6.l(r8)     // Catch: java.lang.Exception -> L23
            K5.F r8 = r6.f13482u     // Catch: java.lang.Exception -> L23
            r7.l = r6     // Catch: java.lang.Exception -> L23
            r7.f13473m = r5     // Catch: java.lang.Exception -> L23
            java.lang.Object r8 = r8.c(r3, r7)     // Catch: java.lang.Exception -> L23
            if (r8 != r0) goto L4f
            return r0
        L4f:
            r1 = r6
        L50:
            com.stingray.kca.model.KaraokeSongResponseV2 r8 = (com.stingray.kca.model.KaraokeSongResponseV2) r8     // Catch: java.lang.Exception -> L23
            T.e0 r1 = r1.f13486y     // Catch: java.lang.Exception -> L23
            r1.setValue(r8)     // Catch: java.lang.Exception -> L23
            r7.l = r6     // Catch: java.lang.Exception -> L23
            r7.f13473m = r4     // Catch: java.lang.Exception -> L23
            if (r3 != 0) goto L6a
            r6.getClass()     // Catch: java.lang.Exception -> L23
            j6.a r7 = new j6.a     // Catch: java.lang.Exception -> L23
            r8 = 0
            com.stingray.kca.model.AssetTypeV2 r1 = com.stingray.kca.model.AssetTypeV2.SONG     // Catch: java.lang.Exception -> L23
            r7.<init>(r1, r2, r8)     // Catch: java.lang.Exception -> L23
        L68:
            r8 = r7
            goto L71
        L6a:
            j6.n r8 = r6.l     // Catch: java.lang.Exception -> L23
            java.lang.Object r7 = j6.n.d(r8, r3, r7)     // Catch: java.lang.Exception -> L23
            goto L68
        L71:
            if (r8 != r0) goto L74
            return r0
        L74:
            r7 = r6
        L75:
            j6.a r8 = (j6.a) r8     // Catch: java.lang.Exception -> L23
            boolean r8 = r8.f17032a     // Catch: java.lang.Exception -> L23
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch: java.lang.Exception -> L23
            T.e0 r7 = r7.f13487z     // Catch: java.lang.Exception -> L23
            r7.setValue(r8)     // Catch: java.lang.Exception -> L23
            T.e0 r7 = r6.f13486y     // Catch: java.lang.Exception -> L23
            java.lang.Object r7 = r7.getValue()     // Catch: java.lang.Exception -> L23
            com.stingray.kca.model.KaraokeSongResponseV2 r7 = (com.stingray.kca.model.KaraokeSongResponseV2) r7     // Catch: java.lang.Exception -> L23
            if (r7 == 0) goto L93
            java.lang.Boolean r7 = r7.f13146n     // Catch: java.lang.Exception -> L23
            java.lang.Boolean r8 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> L23
            A7.m.a(r7, r8)     // Catch: java.lang.Exception -> L23
        L93:
            com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState r7 = com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState.Done     // Catch: java.lang.Exception -> L23
            r6.l(r7)     // Catch: java.lang.Exception -> L23
            goto La6
        L99:
            B9.a r8 = timber.log.Timber.f22577a
            r8.e(r2)
            r8.a(r7)
            com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState r7 = com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState.Error
            r6.l(r7)
        La6:
            m7.q r7 = m7.q.f19267a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: d6.h.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
