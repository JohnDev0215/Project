package androidx.slidingpanelayout.widget;

import android.app.Activity;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import defpackage.Flow1;
import defpackage.a8;
import defpackage.b00;
import defpackage.c70;
import defpackage.nr0;
import defpackage.s00;
import defpackage.t00;
import defpackage.uw2;
import defpackage.vi3;
import defpackage.xn0;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: FoldingFeatureObserver.kt */
/* JADX INFO: loaded from: classes.dex */
@c70(c = "androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1", f = "FoldingFeatureObserver.kt", l = {97}, m = "invokeSuspend")
@SourceDebugExtension({"SMAP\nFoldingFeatureObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FoldingFeatureObserver.kt\nandroidx/slidingpanelayout/widget/FoldingFeatureObserver$registerLayoutStateChangeCallback$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt\n*L\n1#1,91:1\n54#2:92\n57#2:96\n50#3:93\n55#3:95\n106#4:94\n72#5,3:97\n*S KotlinDebug\n*F\n+ 1 FoldingFeatureObserver.kt\nandroidx/slidingpanelayout/widget/FoldingFeatureObserver$registerLayoutStateChangeCallback$1\n*L\n72#1:92\n72#1:96\n72#1:93\n72#1:95\n72#1:94\n74#1:97,3\n*E\n"})
public final class b extends uw2 implements nr0<s00, b00<? super vi3>, Object> {
    public int i;
    public final /* synthetic */ androidx.slidingpanelayout.widget.a j;
    public final /* synthetic */ Activity k;

    /* JADX INFO: compiled from: Collect.kt */
    @SourceDebugExtension({"SMAP\nCollect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt$collect$3\n+ 2 FoldingFeatureObserver.kt\nandroidx/slidingpanelayout/widget/FoldingFeatureObserver$registerLayoutStateChangeCallback$1\n*L\n1#1,134:1\n75#2,2:135\n*E\n"})
    public static final class a implements xn0<FoldingFeature> {
    }

    /* JADX INFO: renamed from: androidx.slidingpanelayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeCollector.common.kt */
    @SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 3 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt\n*L\n1#1,112:1\n51#2:113\n55#2:117\n72#3,3:114\n*S KotlinDebug\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n51#1:114,3\n*E\n"})
    public static final class C0029b implements Flow1<FoldingFeature> {
        public final /* synthetic */ Flow1 a;

        /* JADX INFO: renamed from: androidx.slidingpanelayout.widget.b$b$a */
        /* JADX INFO: compiled from: Collect.kt */
        @SourceDebugExtension({"SMAP\nCollect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt$collect$3\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 3 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 4 FoldingFeatureObserver.kt\nandroidx/slidingpanelayout/widget/FoldingFeatureObserver$registerLayoutStateChangeCallback$1\n*L\n1#1,134:1\n53#2:135\n55#3:136\n56#3:138\n72#4:137\n*E\n"})
        public static final class a implements xn0<WindowLayoutInfo> {
            public final /* synthetic */ xn0 a;

            public a(xn0 xn0Var) {
                this.a = xn0Var;
            }
        }

        public C0029b(Flow1 flow1, androidx.slidingpanelayout.widget.a aVar) {
            this.a = flow1;
        }

        @Override // defpackage.Flow1
        public final Object a(xn0<? super FoldingFeature> xn0Var, b00 b00Var) {
            Object objA = this.a.a(new a(xn0Var), b00Var);
            return objA == t00.COROUTINE_SUSPENDED ? objA : vi3.a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(androidx.slidingpanelayout.widget.a aVar, Activity activity, b00<? super b> b00Var) {
        super(b00Var);
        this.j = aVar;
        this.k = activity;
    }

    @Override // defpackage.nr0
    public final Object d(s00 s00Var, b00<? super vi3> b00Var) {
        return ((b) e(s00Var, b00Var)).h(vi3.a);
    }

    @Override // defpackage.ed
    public final b00<vi3> e(Object obj, b00<?> b00Var) {
        return new b(this.j, this.k, b00Var);
    }

    @Override // defpackage.ed
    public final Object h(Object obj) throws Throwable {
        t00 t00Var = t00.COROUTINE_SUSPENDED;
        int i = this.i;
        if (i == 0) {
            a8.i0(obj);
            androidx.slidingpanelayout.widget.a aVar = this.j;
            Flow1 flow1W = a8.w(new C0029b(aVar.a.windowLayoutInfo(this.k), aVar));
            a aVar2 = new a();
            this.i = 1;
            if (flow1W.a(aVar2, this) == t00Var) {
                return t00Var;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            a8.i0(obj);
        }
        return vi3.a;
    }
}
