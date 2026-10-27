package com.amazon.mp3.playback.util;

import android.content.Context;
import android.net.Uri;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.playback.PlaybackExperience;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackInitiatedPlaybackHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u00020\u00042\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\u000bH\u0007J:\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/playback/util/TrackInitiatedPlaybackHelper;", "", "()V", "DEFAULT_POSITION", "", "TAG", "", "kotlin.jvm.PlatformType", "findTrackPositionInList", "trackList", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "getTracksForPlayback", "selectedTrack", "eligibilityUtil", "Lcom/amazon/mp3/util/EligibilityUtil;", "collectionUri", "Landroid/net/Uri;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TrackInitiatedPlaybackHelper {
    private static final int DEFAULT_POSITION = 0;
    public static final TrackInitiatedPlaybackHelper INSTANCE = new TrackInitiatedPlaybackHelper();
    private static final String TAG = TrackInitiatedPlaybackHelper.class.getSimpleName();

    /* JADX INFO: compiled from: TrackInitiatedPlaybackHelper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayModeEnum.values().length];
            iArr[PlayModeEnum.SIMILAR.ordinal()] = 1;
            iArr[PlayModeEnum.AUGMENTED.ordinal()] = 2;
            iArr[PlayModeEnum.ON_DEMAND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private TrackInitiatedPlaybackHelper() {
    }

    public static /* synthetic */ List getTracksForPlayback$default(List list, MusicTrack musicTrack, EligibilityUtil eligibilityUtil, Uri uri, int i, Object obj) {
        if ((i & 8) != 0) {
            uri = null;
        }
        return getTracksForPlayback(list, musicTrack, eligibilityUtil, uri);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final List<MusicTrack> getTracksForPlayback(List<? extends MusicTrack> trackList, MusicTrack selectedTrack, EligibilityUtil eligibilityUtil, Uri collectionUri) {
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        Intrinsics.checkNotNullParameter(selectedTrack, "selectedTrack");
        Intrinsics.checkNotNullParameter(eligibilityUtil, "eligibilityUtil");
        Context context = AmazonApplication.getApplication().getApplicationContext();
        if (collectionUri != null) {
            Intrinsics.checkNotNullExpressionValue(context, "context");
            if (eligibilityUtil.getPlaybackExperience(collectionUri, context) == PlaybackExperience.ON_DEMAND) {
                return trackList;
            }
        }
        PlayModeEnum playMode = eligibilityUtil.getPlayMode(selectedTrack, collectionUri, context);
        int i = playMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[playMode.ordinal()];
        if (i == 1 || i == 2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(selectedTrack);
            return arrayList;
        }
        if (i != 3) {
            Log.warning(TAG, "Unknown Play Mode: " + playMode + ", unable to retrieve tracks to play");
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : trackList) {
            if (eligibilityUtil.getPlayMode((MusicTrack) obj) == PlayModeEnum.ON_DEMAND) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    @JvmStatic
    public static final int findTrackPositionInList(List<? extends MusicTrack> trackList, MusicTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        if (trackList != null) {
            int i = 0;
            int size = trackList.size();
            while (i < size) {
                int i2 = i + 1;
                if (Intrinsics.areEqual(trackList.get(i).getContentUri(), track.getContentUri())) {
                    return i;
                }
                i = i2;
            }
        }
        return DEFAULT_POSITION;
    }
}
