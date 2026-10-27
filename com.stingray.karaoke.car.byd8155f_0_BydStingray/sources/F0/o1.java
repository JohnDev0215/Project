package F0;

import V8.InterfaceC0818g;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class o1 extends s7.i implements InterfaceC2785n {
    public U8.d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2241m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f2242n;
    public final /* synthetic */ ContentResolver o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Uri f2243p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ p1 f2244q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ U8.l f2245r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Context f2246s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o1(ContentResolver contentResolver, Uri uri, p1 p1Var, U8.l lVar, Context context, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = contentResolver;
        this.f2243p = uri;
        this.f2244q = p1Var;
        this.f2245r = lVar;
        this.f2246s = context;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        U8.l lVar = this.f2245r;
        o1 o1Var = new o1(this.o, this.f2243p, this.f2244q, lVar, this.f2246s, interfaceC2189c);
        o1Var.f2242n = obj;
        return o1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((o1) create((InterfaceC0818g) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0050 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d A[Catch: all -> 0x001c, TRY_LEAVE, TryCatch #0 {all -> 0x001c, blocks: (B:7:0x0016, B:18:0x0044, B:22:0x0055, B:24:0x005d, B:14:0x002c, B:17:0x003d), top: B:31:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0080  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x007d -> B:8:0x0019). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
        /*
            r10 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r10.f2241m
            F0.p1 r2 = r10.f2244q
            r3 = 2
            r4 = 1
            android.content.ContentResolver r5 = r10.o
            if (r1 == 0) goto L30
            if (r1 == r4) goto L26
            if (r1 != r3) goto L1e
            U8.d r1 = r10.l
            java.lang.Object r6 = r10.f2242n
            V8.g r6 = (V8.InterfaceC0818g) r6
            h8.AbstractC1386c.u(r11)     // Catch: java.lang.Throwable -> L1c
        L19:
            r11 = r6
            r6 = r1
            goto L44
        L1c:
            r10 = move-exception
            goto L86
        L1e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L26:
            U8.d r1 = r10.l
            java.lang.Object r6 = r10.f2242n
            V8.g r6 = (V8.InterfaceC0818g) r6
            h8.AbstractC1386c.u(r11)     // Catch: java.lang.Throwable -> L1c
            goto L55
        L30:
            h8.AbstractC1386c.u(r11)
            java.lang.Object r11 = r10.f2242n
            V8.g r11 = (V8.InterfaceC0818g) r11
            android.net.Uri r1 = r10.f2243p
            r6 = 0
            r5.registerContentObserver(r1, r6, r2)
            U8.l r1 = r10.f2245r     // Catch: java.lang.Throwable -> L1c
            U8.d r6 = new U8.d     // Catch: java.lang.Throwable -> L1c
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L1c
        L44:
            r10.f2242n = r11     // Catch: java.lang.Throwable -> L1c
            r10.l = r6     // Catch: java.lang.Throwable -> L1c
            r10.f2241m = r4     // Catch: java.lang.Throwable -> L1c
            java.lang.Object r1 = r6.a(r10)     // Catch: java.lang.Throwable -> L1c
            if (r1 != r0) goto L51
            return r0
        L51:
            r9 = r6
            r6 = r11
            r11 = r1
            r1 = r9
        L55:
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch: java.lang.Throwable -> L1c
            boolean r11 = r11.booleanValue()     // Catch: java.lang.Throwable -> L1c
            if (r11 == 0) goto L80
            r1.c()     // Catch: java.lang.Throwable -> L1c
            android.content.Context r11 = r10.f2246s     // Catch: java.lang.Throwable -> L1c
            android.content.ContentResolver r11 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L1c
            java.lang.String r7 = "animator_duration_scale"
            r8 = 1065353216(0x3f800000, float:1.0)
            float r11 = android.provider.Settings.Global.getFloat(r11, r7, r8)     // Catch: java.lang.Throwable -> L1c
            java.lang.Float r7 = new java.lang.Float     // Catch: java.lang.Throwable -> L1c
            r7.<init>(r11)     // Catch: java.lang.Throwable -> L1c
            r10.f2242n = r6     // Catch: java.lang.Throwable -> L1c
            r10.l = r1     // Catch: java.lang.Throwable -> L1c
            r10.f2241m = r3     // Catch: java.lang.Throwable -> L1c
            java.lang.Object r11 = r6.f(r7, r10)     // Catch: java.lang.Throwable -> L1c
            if (r11 != r0) goto L19
            return r0
        L80:
            r5.unregisterContentObserver(r2)
            m7.q r10 = m7.q.f19267a
            return r10
        L86:
            r5.unregisterContentObserver(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.o1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
