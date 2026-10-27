package i0;

import A2.l;
import A7.m;
import F0.C0221p;
import F0.P0;
import F0.T;
import L0.o;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.A;
import androidx.lifecycle.DefaultLifecycleObserver;
import java.util.List;
import u.AbstractC2469i;
import u.C2466f;
import u.C2476p;
import u.C2477q;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: i0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnAttachStateChangeListenerC1400d implements DefaultLifecycleObserver, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f15652A;
    public final l B;
    public final AndroidComposeView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0221p f15653m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public I0.d f15654n;
    public final C2476p o = new C2476p();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2477q f15655p = new C2477q();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f15656q = 100;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f15657r = 1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f15658s = true;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C2466f f15659t = new C2466f(null);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final U8.l f15660u = AbstractC2673a.a(1, 6, null);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Handler f15661v = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C2476p f15662w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f15663x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C2476p f15664y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public P0 f15665z;

    public ViewOnAttachStateChangeListenerC1400d(AndroidComposeView androidComposeView, C0221p c0221p) {
        this.l = androidComposeView;
        this.f15653m = c0221p;
        C2476p c2476p = AbstractC2469i.f22602a;
        m.d(c2476p, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>");
        this.f15662w = c2476p;
        this.f15664y = new C2476p();
        o oVarA = androidComposeView.getSemanticsOwner().a();
        m.d(c2476p, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>");
        this.f15665z = new P0(oVarA, c2476p);
        this.B = new l(this, 14);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069 A[Catch: all -> 0x0076, TryCatch #1 {all -> 0x0076, blocks: (B:24:0x0054, B:27:0x0061, B:29:0x0069, B:31:0x0072, B:34:0x007a, B:36:0x007e, B:37:0x0087, B:23:0x004a), top: B:45:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0098 -> B:24:0x0054). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(s7.AbstractC2410c r9) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r9 instanceof i0.C1399c
            if (r0 == 0) goto L13
            r0 = r9
            i0.c r0 = (i0.C1399c) r0
            int r1 = r0.f15651p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f15651p = r1
            goto L18
        L13:
            i0.c r0 = new i0.c
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.f15650n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f15651p
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L47
            if (r2 == r4) goto L3c
            if (r2 != r3) goto L34
            U8.d r8 = r0.f15649m
            i0.d r2 = r0.l
            h8.AbstractC1386c.u(r9)     // Catch: java.lang.Throwable -> L31
            r7 = r2
            r2 = r8
            r8 = r7
            goto L54
        L31:
            r8 = move-exception
            goto La3
        L34:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3c:
            U8.d r8 = r0.f15649m
            i0.d r2 = r0.l
            h8.AbstractC1386c.u(r9)     // Catch: java.lang.Throwable -> L31
            r7 = r2
            r2 = r8
            r8 = r7
            goto L61
        L47:
            h8.AbstractC1386c.u(r9)
            U8.l r9 = r8.f15660u     // Catch: java.lang.Throwable -> L76
            r9.getClass()     // Catch: java.lang.Throwable -> L76
            U8.d r2 = new U8.d     // Catch: java.lang.Throwable -> L76
            r2.<init>(r9)     // Catch: java.lang.Throwable -> L76
        L54:
            r0.l = r8     // Catch: java.lang.Throwable -> L76
            r0.f15649m = r2     // Catch: java.lang.Throwable -> L76
            r0.f15651p = r4     // Catch: java.lang.Throwable -> L76
            java.lang.Object r9 = r2.a(r0)     // Catch: java.lang.Throwable -> L76
            if (r9 != r1) goto L61
            return r1
        L61:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L76
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L76
            if (r9 == 0) goto L9b
            r2.c()     // Catch: java.lang.Throwable -> L76
            boolean r9 = r8.d()     // Catch: java.lang.Throwable -> L76
            if (r9 == 0) goto L7a
            r8.e()     // Catch: java.lang.Throwable -> L76
            goto L7a
        L76:
            r9 = move-exception
            r2 = r8
            r8 = r9
            goto La3
        L7a:
            boolean r9 = r8.f15652A     // Catch: java.lang.Throwable -> L76
            if (r9 != 0) goto L87
            r8.f15652A = r4     // Catch: java.lang.Throwable -> L76
            android.os.Handler r9 = r8.f15661v     // Catch: java.lang.Throwable -> L76
            A2.l r5 = r8.B     // Catch: java.lang.Throwable -> L76
            r9.post(r5)     // Catch: java.lang.Throwable -> L76
        L87:
            u.f r9 = r8.f15659t     // Catch: java.lang.Throwable -> L76
            r9.clear()     // Catch: java.lang.Throwable -> L76
            long r5 = r8.f15656q     // Catch: java.lang.Throwable -> L76
            r0.l = r8     // Catch: java.lang.Throwable -> L76
            r0.f15649m = r2     // Catch: java.lang.Throwable -> L76
            r0.f15651p = r3     // Catch: java.lang.Throwable -> L76
            java.lang.Object r9 = S8.AbstractC0727y.i(r5, r0)     // Catch: java.lang.Throwable -> L76
            if (r9 != r1) goto L54
            return r1
        L9b:
            u.f r8 = r8.f15659t
            r8.clear()
            m7.q r8 = m7.q.f19267a
            return r8
        La3:
            u.f r9 = r2.f15659t
            r9.clear()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.ViewOnAttachStateChangeListenerC1400d.a(s7.c):java.lang.Object");
    }

    public final C2476p c() {
        if (this.f15658s) {
            this.f15658s = false;
            this.f15662w = T.p(this.l.getSemanticsOwner());
            this.f15663x = System.currentTimeMillis();
        }
        return this.f15662w;
    }

    public final boolean d() {
        return this.f15654n != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e() {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.ViewOnAttachStateChangeListenerC1400d.e():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(L0.o r20, F0.P0 r21) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.ViewOnAttachStateChangeListenerC1400d.f(L0.o, F0.P0):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(L0.o r21, F0.P0 r22) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.ViewOnAttachStateChangeListenerC1400d.g(L0.o, F0.P0):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x01f9, code lost:
    
        if (((r5 & ((~r5) << 6)) & (-9187201950435737472L)) == 0) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01fb, code lost:
    
        r15 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0096 A[PHI: r8
  0x0096: PHI (r8v6 android.view.autofill.AutofillId) = (r8v5 android.view.autofill.AutofillId), (r8v51 android.view.autofill.AutofillId) binds: [B:36:0x0083, B:38:0x0092] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h(L0.o r21) {
        /*
            Method dump skipped, instruction units count: 553
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.ViewOnAttachStateChangeListenerC1400d.h(L0.o):void");
    }

    public final void i(o oVar) {
        if (d()) {
            int i7 = oVar.f3920g;
            C2476p c2476p = this.o;
            if (c2476p.c(i7)) {
                c2476p.h(i7);
            } else {
                this.f15655p.a(i7);
            }
            List listH = o.h(oVar, true, 4);
            int size = listH.size();
            for (int i10 = 0; i10 < size; i10++) {
                i((o) listH.get(i10));
            }
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart(A a10) {
        this.f15654n = (I0.d) this.f15653m.a();
        h(this.l.getSemanticsOwner().a());
        e();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop(A a10) {
        i(this.l.getSemanticsOwner().a());
        e();
        this.f15654n = null;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f15661v.removeCallbacks(this.B);
        this.f15654n = null;
    }
}
