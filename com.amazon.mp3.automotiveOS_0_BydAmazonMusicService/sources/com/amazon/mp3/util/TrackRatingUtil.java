package com.amazon.mp3.util;

/* JADX INFO: loaded from: classes4.dex */
public class TrackRatingUtil {
    public static int determineNewRatingForTrack(final int selectedRating, final int currentTrackRating) {
        if (selectedRating == currentTrackRating) {
            return 0;
        }
        return selectedRating;
    }
}
