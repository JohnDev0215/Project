package com.apollographql.apollo3.interceptor;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e60;
import p.fv0;
import p.fz0;
import p.k7;
import p.lq2;
import p.m7;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor$intercept$1", f = "AutoPersistedQueryInterceptor.kt", l = {ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER, 49, 50, 56}, m = "invokeSuspend")
final class AutoPersistedQueryInterceptor$intercept$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ k7 $chain;
    final /* synthetic */ boolean $isMutation;
    final /* synthetic */ m7 $request;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoPersistedQueryInterceptor$intercept$1(k7 k7Var, m7 m7Var, a aVar, boolean z, c00 c00Var) {
        super(2, c00Var);
        this.$chain = k7Var;
        this.$request = m7Var;
        this.this$0 = aVar;
        this.$isMutation = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        AutoPersistedQueryInterceptor$intercept$1 autoPersistedQueryInterceptor$intercept$1 = new AutoPersistedQueryInterceptor$intercept$1(this.$chain, this.$request, this.this$0, this.$isMutation, c00Var);
        autoPersistedQueryInterceptor$intercept$1.L$0 = obj;
        return autoPersistedQueryInterceptor$intercept$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((AutoPersistedQueryInterceptor$intercept$1) a((fv0) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d3 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor$intercept$1.i(java.lang.Object):java.lang.Object");
    }
}
