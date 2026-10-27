package com.amazon.podcast.views.ptcPodcastsTemplate;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
final class PTCGridLayouts {
    private static Map<Integer, Integer> xsmall = new HashMap();
    private static Map<Integer, Integer> small = new HashMap();
    private static Map<Integer, Integer> medium = new HashMap();
    private static Map<Integer, Integer> large = new HashMap();

    PTCGridLayouts() {
    }

    static {
        xsmall.put(2, 2);
        xsmall.put(7, 2);
        xsmall.put(10, 2);
        xsmall.put(15, 2);
        small.put(1, 2);
        small.put(6, 2);
        small.put(13, 2);
        medium.put(2, 2);
        medium.put(9, 2);
        large.put(0, 2);
        large.put(1, 3);
        large.put(7, 2);
        large.put(11, 2);
    }

    public static Map<Integer, Integer> getArrangement(final int columns) {
        if (columns == 2) {
            return xsmall;
        }
        if (columns == 3) {
            return small;
        }
        if (columns == 4) {
            return medium;
        }
        return large;
    }
}
