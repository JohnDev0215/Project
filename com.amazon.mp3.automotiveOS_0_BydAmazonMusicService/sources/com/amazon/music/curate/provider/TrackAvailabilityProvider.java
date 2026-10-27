package com.amazon.music.curate.provider;

import com.amazon.music.curate.model.Track;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TrackAvailabilityProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/provider/TrackAvailabilityProvider;", "", "isAvailable", "", "track", "Lcom/amazon/music/curate/model/Track;", "callback", "Lkotlin/Function0;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TrackAvailabilityProvider {
    void isAvailable(Track track, Function0<Unit> callback);

    /* JADX INFO: compiled from: TrackAvailabilityProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void isAvailable$default(TrackAvailabilityProvider trackAvailabilityProvider, Track track, Function0 function0, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isAvailable");
            }
            if ((i & 2) != 0) {
                function0 = null;
            }
            trackAvailabilityProvider.isAvailable(track, function0);
        }
    }
}
