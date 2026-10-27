package com.apollographql.apollo3.internal;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.c00;
import p.e60;
import p.fv0;
import p.fz0;
import p.lq2;
import p.m21;
import p.xl1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.internal.MultipartKt$multipartBodyFlow$1", f = "multipart.kt", l = {30}, m = "invokeSuspend")
final class MultipartKt$multipartBodyFlow$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ Ref$ObjectRef<xl1> $multipartReader;
    final /* synthetic */ m21 $response;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartKt$multipartBodyFlow$1(Ref$ObjectRef ref$ObjectRef, m21 m21Var, c00 c00Var) {
        super(2, c00Var);
        this.$multipartReader = ref$ObjectRef;
        this.$response = m21Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        MultipartKt$multipartBodyFlow$1 multipartKt$multipartBodyFlow$1 = new MultipartKt$multipartBodyFlow$1(this.$multipartReader, this.$response, c00Var);
        multipartKt$multipartBodyFlow$1.L$0 = obj;
        return multipartKt$multipartBodyFlow$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((MultipartKt$multipartBodyFlow$1) a((fv0) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e8  */
    /* JADX WARN: Type inference failed for: r8v0, types: [T, p.xl1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r17) {
        /*
            Method dump skipped, instruction units count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.internal.MultipartKt$multipartBodyFlow$1.i(java.lang.Object):java.lang.Object");
    }
}
