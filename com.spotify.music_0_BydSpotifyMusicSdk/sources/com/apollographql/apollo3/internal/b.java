package com.apollographql.apollo3.internal;

import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.flow.f;
import p.dw2;
import p.hz0;
import p.k62;
import p.m21;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final f a(m21 m21Var) {
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        return new f(new k62(new MultipartKt$multipartBodyFlow$1(ref$ObjectRef, m21Var, null)), new MultipartKt$multipartBodyFlow$2(ref$ObjectRef, null));
    }

    public static final k62 b(dw2 dw2Var, hz0 hz0Var) {
        return new k62(new FlowsKt$transformWhile$1(dw2Var, hz0Var, null));
    }
}
