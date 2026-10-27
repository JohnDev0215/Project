package com.amazon.music.media.playback.ratings;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LikeState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/playback/ratings/LikeState;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "LIKE", "DISLIKE", "NEUTRAL", "Companion", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LikeState {
    LIKE("LIKE"),
    DISLIKE("DISLIKE"),
    NEUTRAL("");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String value;

    LikeState(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: compiled from: LikeState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/media/playback/ratings/LikeState$Companion;", "", "()V", "fromString", "Lcom/amazon/music/media/playback/ratings/LikeState;", "likeState", "", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LikeState fromString(String likeState) {
            return Intrinsics.areEqual(likeState, "LIKE") ? LikeState.LIKE : Intrinsics.areEqual(likeState, "DISLIKE") ? LikeState.DISLIKE : LikeState.NEUTRAL;
        }
    }
}
