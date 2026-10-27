package com.amazon.mp3.playback;

import com.amazon.mp3.featuregate.Feature;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: PlaybackExceptions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class PlaybackExceptions$exceptionList$2 extends FunctionReferenceImpl implements Function0<Boolean> {
    PlaybackExceptions$exceptionList$2(Object obj) {
        super(0, obj, Feature.class, Constants.ENABLE_DISABLE, "isEnabled()Z", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(((Feature) this.receiver).isEnabled());
    }
}
