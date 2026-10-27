package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.ads.AdBreak;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
public class AdBreakTimeBasedComparator implements Comparator<AdBreak>, Serializable {
    public static final AdBreakTimeBasedComparator INSTANCE = new AdBreakTimeBasedComparator();

    @Override // java.util.Comparator
    public int compare(AdBreak adBreak, AdBreak adBreak2) {
        return adBreak.getStartTime().compareTo(adBreak2.getStartTime());
    }
}
