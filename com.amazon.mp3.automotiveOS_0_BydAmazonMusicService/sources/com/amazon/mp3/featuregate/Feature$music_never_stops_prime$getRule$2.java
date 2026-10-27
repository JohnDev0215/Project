package com.amazon.mp3.featuregate;

import com.amazon.mp3.casting.CastingUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: Feature.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class Feature$music_never_stops_prime$getRule$2 extends FunctionReferenceImpl implements Function0<Boolean> {
    public static final Feature$music_never_stops_prime$getRule$2 INSTANCE = new Feature$music_never_stops_prime$getRule$2();

    Feature$music_never_stops_prime$getRule$2() {
        super(0, CastingUtil.class, "isCasting", "isCasting()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(CastingUtil.isCasting());
    }
}
