package com.amazon.music.platform.playback.capabilities.reduce;

import com.amazon.music.platform.Outcome;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReducibleBoolean.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/reduce/ReducibleBoolean;", "Lcom/amazon/music/platform/playback/capabilities/reduce/Reducible;", "", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface ReducibleBoolean extends Reducible<Boolean> {

    /* JADX INFO: compiled from: ReducibleBoolean.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static Outcome<Boolean> reduce(ReducibleBoolean reducibleBoolean, Outcome<Boolean> outcome1, Outcome<Boolean> outcome2) {
            Intrinsics.checkNotNullParameter(outcome1, "outcome1");
            Intrinsics.checkNotNullParameter(outcome2, "outcome2");
            Outcome<Boolean> outcomeReduceFailures = ReducibleKt.reduceFailures(reducibleBoolean, outcome1, outcome2);
            if (outcomeReduceFailures == null) {
                outcomeReduceFailures = new Outcome.Success<>(Boolean.valueOf(((Boolean) ((Outcome.Success) outcome1).getValue()).booleanValue() || ((Boolean) ((Outcome.Success) outcome2).getValue()).booleanValue()));
            }
            return outcomeReduceFailures;
        }
    }
}
