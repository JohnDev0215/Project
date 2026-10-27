package com.amazon.music.platform.playback.capabilities.reduce;

import com.amazon.music.platform.Outcome;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReduciblePrioritizable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/reduce/ReduciblePrioritizable;", "Lcom/amazon/music/platform/playback/capabilities/reduce/Reducible;", "Lcom/amazon/music/platform/playback/capabilities/reduce/Prioritizable;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface ReduciblePrioritizable extends Reducible<Prioritizable> {

    /* JADX INFO: compiled from: ReduciblePrioritizable.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static Outcome<Prioritizable> reduce(ReduciblePrioritizable reduciblePrioritizable, Outcome<? extends Prioritizable> outcome1, Outcome<? extends Prioritizable> outcome2) {
            Intrinsics.checkNotNullParameter(outcome1, "outcome1");
            Intrinsics.checkNotNullParameter(outcome2, "outcome2");
            Outcome<Prioritizable> outcomeReduceFailures = ReducibleKt.reduceFailures(reduciblePrioritizable, outcome1, outcome2);
            if (outcomeReduceFailures != null) {
                return outcomeReduceFailures;
            }
            Outcome.Success success = (Outcome.Success) outcome1;
            Outcome.Success success2 = (Outcome.Success) outcome2;
            return ((Prioritizable) success.getValue()).getPriority() < ((Prioritizable) success2.getValue()).getPriority() ? success : success2;
        }
    }
}
