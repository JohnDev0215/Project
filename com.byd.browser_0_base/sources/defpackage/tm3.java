package defpackage;

import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: ViewGroup.kt */
/* JADX INFO: loaded from: classes.dex */
@c70(c = "androidx.core.view.ViewGroupKt$descendants$1", f = "ViewGroup.kt", l = {119, 121}, m = "invokeSuspend")
@SourceDebugExtension({"SMAP\nViewGroup.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewGroup.kt\nandroidx/core/view/ViewGroupKt$descendants$1\n+ 2 ViewGroup.kt\nandroidx/core/view/ViewGroupKt\n*L\n1#1,175:1\n55#2,4:176\n*S KotlinDebug\n*F\n+ 1 ViewGroup.kt\nandroidx/core/view/ViewGroupKt$descendants$1\n*L\n118#1:176,4\n*E\n"})
public final class tm3 extends ub2 implements nr0<ni2<? super View>, b00<? super vi3>, Object> {
    public ViewGroup g;
    public View h;
    public int i;
    public int j;
    public int k;
    public /* synthetic */ Object l;
    public final /* synthetic */ ViewGroup m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public tm3(ViewGroup viewGroup, b00<? super tm3> b00Var) {
        super(b00Var);
        this.m = viewGroup;
    }

    @Override // defpackage.nr0
    public final Object d(ni2<? super View> ni2Var, b00<? super vi3> b00Var) {
        return ((tm3) e(ni2Var, b00Var)).h(vi3.a);
    }

    @Override // defpackage.ed
    public final b00<vi3> e(Object obj, b00<?> b00Var) {
        tm3 tm3Var = new tm3(this.m, b00Var);
        tm3Var.l = obj;
        return tm3Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00af  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x009f -> B:25:0x00a1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00a7 -> B:27:0x00aa). Please report as a decompilation issue!!! */
    @Override // defpackage.ed
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object h(java.lang.Object r12) throws java.lang.Throwable {
        /*
            r11 = this;
            t00 r0 = defpackage.t00.COROUTINE_SUSPENDED
            int r1 = r11.k
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L36
            if (r1 == r3) goto L24
            if (r1 != r2) goto L1c
            int r1 = r11.j
            int r4 = r11.i
            android.view.ViewGroup r5 = r11.g
            java.lang.Object r6 = r11.l
            ni2 r6 = (defpackage.ni2) r6
            defpackage.a8.i0(r12)
            r7 = r0
            goto La1
        L1c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L24:
            int r1 = r11.j
            int r4 = r11.i
            android.view.View r5 = r11.h
            android.view.ViewGroup r6 = r11.g
            java.lang.Object r7 = r11.l
            ni2 r7 = (defpackage.ni2) r7
            defpackage.a8.i0(r12)
            r12 = r7
            r7 = r0
            goto L68
        L36:
            defpackage.a8.i0(r12)
            java.lang.Object r12 = r11.l
            ni2 r12 = (defpackage.ni2) r12
            android.view.ViewGroup r1 = r11.m
            int r4 = r1.getChildCount()
            r5 = 0
            r6 = r0
        L45:
            if (r5 >= r4) goto Laf
            android.view.View r7 = r1.getChildAt(r5)
            java.lang.String r8 = "getChildAt(index)"
            defpackage.i21.d(r7, r8)
            r11.l = r12
            r11.g = r1
            r11.h = r7
            r11.i = r5
            r11.j = r4
            r11.k = r3
            r12.b(r7, r11)
            if (r0 != r6) goto L62
            return r6
        L62:
            r10 = r6
            r6 = r1
            r1 = r4
            r4 = r5
            r5 = r7
            r7 = r10
        L68:
            boolean r8 = r5 instanceof android.view.ViewGroup
            if (r8 == 0) goto La7
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            java.lang.String r8 = "<this>"
            defpackage.i21.e(r5, r8)
            tm3 r8 = new tm3
            r9 = 0
            r8.<init>(r5, r9)
            r11.l = r12
            r11.g = r6
            r11.h = r9
            r11.i = r4
            r11.j = r1
            r11.k = r2
            r12.getClass()
            mi2 r5 = new mi2
            r5.<init>()
            b00 r8 = defpackage.oj2.g(r5, r5, r8)
            r5.h = r8
            java.lang.Object r5 = r12.d(r5, r11)
            if (r5 != r0) goto L9a
            goto L9c
        L9a:
            vi3 r5 = defpackage.vi3.a
        L9c:
            if (r5 != r7) goto L9f
            return r7
        L9f:
            r5 = r6
            r6 = r12
        La1:
            r12 = r6
            r6 = r7
            r10 = r5
            r5 = r1
            r1 = r10
            goto Laa
        La7:
            r5 = r1
            r1 = r6
            r6 = r7
        Laa:
            int r4 = r4 + r3
            r10 = r5
            r5 = r4
            r4 = r10
            goto L45
        Laf:
            vi3 r11 = defpackage.vi3.a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tm3.h(java.lang.Object):java.lang.Object");
    }
}
