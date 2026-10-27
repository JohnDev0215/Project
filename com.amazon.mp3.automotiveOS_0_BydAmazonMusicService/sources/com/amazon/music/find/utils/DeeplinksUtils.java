package com.amazon.music.find.utils;

import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeeplinksUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/utils/DeeplinksUtils;", "", "()V", "PODCASTS_PATH_SEGMENT", "", "PODCAST_EPISODES_PATH_SEGMENT", "getPodcastPlaybackDeeplink", "Landroid/net/Uri;", ImagesContract.URL, "isPodcastEpisode", "", "isPodcastShow", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeeplinksUtils {
    public static final DeeplinksUtils INSTANCE = new DeeplinksUtils();
    public static final String PODCASTS_PATH_SEGMENT = "podcasts";
    public static final String PODCAST_EPISODES_PATH_SEGMENT = "episodes";

    private DeeplinksUtils() {
    }

    @JvmStatic
    public static final boolean isPodcastShow(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        List<String> pathSegments = Uri.parse(url).getPathSegments();
        return pathSegments.size() == 3 && Intrinsics.areEqual(pathSegments.get(0), PODCASTS_PATH_SEGMENT);
    }

    @JvmStatic
    public static final boolean isPodcastEpisode(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        List<String> pathSegments = Uri.parse(url).getPathSegments();
        return pathSegments.size() == 5 && Intrinsics.areEqual(pathSegments.get(0), PODCASTS_PATH_SEGMENT) && Intrinsics.areEqual(pathSegments.get(2), PODCAST_EPISODES_PATH_SEGMENT);
    }

    @JvmStatic
    public static final Uri getPodcastPlaybackDeeplink(String url) {
        String str;
        Intrinsics.checkNotNullParameter(url, "url");
        Uri.Builder builderBuildUpon = Uri.parse(url).buildUpon();
        if (isPodcastShow(url)) {
            str = "featuredPlay";
        } else {
            str = isPodcastEpisode(url) ? PageUriUtils.DO_PLAY_VALUE : null;
        }
        if (str != null) {
            builderBuildUpon.appendQueryParameter("do", str);
        }
        Uri uriBuild = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "playbackDeeplinkBuilder.build()");
        return uriBuild;
    }
}
