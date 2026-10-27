package com.apollographql.apollo3.internal;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.dv0;
import p.e60;
import p.fv0;
import p.fz0;
import p.hz0;
import p.jv0;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.internal.FlowsKt$transformWhile$1", f = "flows.kt", l = {61}, m = "invokeSuspend")
final class FlowsKt$transformWhile$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ dv0 $this_transformWhile;
    final /* synthetic */ hz0 $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowsKt$transformWhile$1(dv0 dv0Var, hz0 hz0Var, c00 c00Var) {
        super(2, c00Var);
        this.$this_transformWhile = dv0Var;
        this.$transform = hz0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        FlowsKt$transformWhile$1 flowsKt$transformWhile$1 = new FlowsKt$transformWhile$1(this.$this_transformWhile, this.$transform, c00Var);
        flowsKt$transformWhile$1.L$0 = obj;
        return flowsKt$transformWhile$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((FlowsKt$transformWhile$1) a((fv0) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        a aVar;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            fv0 fv0Var = (fv0) this.L$0;
            dv0 dv0Var = this.$this_transformWhile;
            a aVar2 = new a(this.$transform, fv0Var);
            try {
                jv0 jv0Var = new jv0(0, aVar2);
                this.L$0 = aVar2;
                this.label = 1;
                if (dv0Var.c(jv0Var, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } catch (AbortFlowException e) {
                e = e;
                aVar = aVar2;
                e.a(aVar);
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            aVar = (a) this.L$0;
            try {
                kotlin.a.d(obj);
            } catch (AbortFlowException e2) {
                e = e2;
                e.a(aVar);
            }
        }
        return lq2.a;
    }
}
