package com.amazon.mp3.featuregate;

import com.amazon.mp3.util.PlatformUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class Feature$BOP_Videos$getRule$2 extends FunctionReferenceImpl implements Function0<Boolean> {
    public static final Feature$BOP_Videos$getRule$2 INSTANCE = new Feature$BOP_Videos$getRule$2();

    Feature$BOP_Videos$getRule$2() {
        super(0, PlatformUtil.class, "isFireOSGen10AndAbove", "isFireOSGen10AndAbove()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(PlatformUtil.isFireOSGen10AndAbove());
    }
}
