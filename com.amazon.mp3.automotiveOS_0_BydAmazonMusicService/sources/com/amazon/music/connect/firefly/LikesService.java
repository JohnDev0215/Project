package com.amazon.music.connect.firefly;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ratings.LikeState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LikesService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/connect/firefly/LikesService;", "", "getLikeStates", "", "Lcom/amazon/music/media/playback/ratings/LikeState;", "trackAsins", "", "setLikeState", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "likeState", "DMMConnect_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface LikesService {
    List<LikeState> getLikeStates(List<String> trackAsins) throws Exception;

    void setLikeState(String trackAsin, LikeState likeState);
}
