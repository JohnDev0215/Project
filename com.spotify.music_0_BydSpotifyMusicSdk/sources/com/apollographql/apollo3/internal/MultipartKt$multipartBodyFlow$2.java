package com.apollographql.apollo3.internal;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.c00;
import p.e60;
import p.fv0;
import p.hz0;
import p.lq2;
import p.xl1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.internal.MultipartKt$multipartBodyFlow$2", f = "multipart.kt", l = {}, m = "invokeSuspend")
final class MultipartKt$multipartBodyFlow$2 extends SuspendLambda implements hz0 {
    final /* synthetic */ Ref$ObjectRef<xl1> $multipartReader;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartKt$multipartBodyFlow$2(Ref$ObjectRef ref$ObjectRef, c00 c00Var) {
        super(3, c00Var);
        this.$multipartReader = ref$ObjectRef;
    }

    @Override // p.hz0
    public final Object e(Object obj, Object obj2, Object obj3) {
        MultipartKt$multipartBodyFlow$2 multipartKt$multipartBodyFlow$2 = new MultipartKt$multipartBodyFlow$2(this.$multipartReader, (c00) obj3);
        multipartKt$multipartBodyFlow$2.L$0 = (fv0) obj;
        lq2 lq2Var = lq2.a;
        multipartKt$multipartBodyFlow$2.i(lq2Var);
        return lq2Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        kotlin.a.d(obj);
        try {
            xl1 xl1Var = this.$multipartReader.element;
            if (xl1Var != null) {
                xl1Var.close();
            }
        } catch (Throwable th) {
            kotlin.a.b(th);
        }
        return lq2.a;
    }
}
