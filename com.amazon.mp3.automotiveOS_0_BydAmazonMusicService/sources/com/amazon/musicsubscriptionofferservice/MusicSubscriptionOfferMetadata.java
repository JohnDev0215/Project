package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class MusicSubscriptionOfferMetadata implements Comparable<MusicSubscriptionOfferMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.MusicSubscriptionOfferMetadata");
    private String musicSubscriptionOfferCta;
    private String musicSubscriptionOfferTou;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata) {
        if (musicSubscriptionOfferMetadata == null) {
            return -1;
        }
        if (musicSubscriptionOfferMetadata == this) {
            return 0;
        }
        String musicSubscriptionOfferCta = getMusicSubscriptionOfferCta();
        String musicSubscriptionOfferCta2 = musicSubscriptionOfferMetadata.getMusicSubscriptionOfferCta();
        if (musicSubscriptionOfferCta != musicSubscriptionOfferCta2) {
            if (musicSubscriptionOfferCta == null) {
                return -1;
            }
            if (musicSubscriptionOfferCta2 == null) {
                return 1;
            }
            int iCompareTo = musicSubscriptionOfferCta.compareTo(musicSubscriptionOfferCta2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String musicSubscriptionOfferTou = getMusicSubscriptionOfferTou();
        String musicSubscriptionOfferTou2 = musicSubscriptionOfferMetadata.getMusicSubscriptionOfferTou();
        if (musicSubscriptionOfferTou != musicSubscriptionOfferTou2) {
            if (musicSubscriptionOfferTou == null) {
                return -1;
            }
            if (musicSubscriptionOfferTou2 == null) {
                return 1;
            }
            int iCompareTo2 = musicSubscriptionOfferTou.compareTo(musicSubscriptionOfferTou2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicSubscriptionOfferMetadata)) {
            return false;
        }
        MusicSubscriptionOfferMetadata musicSubscriptionOfferMetadata = (MusicSubscriptionOfferMetadata) obj;
        return internalEqualityCheck(getMusicSubscriptionOfferCta(), musicSubscriptionOfferMetadata.getMusicSubscriptionOfferCta()) && internalEqualityCheck(getMusicSubscriptionOfferTou(), musicSubscriptionOfferMetadata.getMusicSubscriptionOfferTou());
    }

    public String getMusicSubscriptionOfferCta() {
        return this.musicSubscriptionOfferCta;
    }

    public String getMusicSubscriptionOfferTou() {
        return this.musicSubscriptionOfferTou;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMusicSubscriptionOfferCta(), getMusicSubscriptionOfferTou());
    }

    public void setMusicSubscriptionOfferCta(String str) {
        this.musicSubscriptionOfferCta = str;
    }

    public void setMusicSubscriptionOfferTou(String str) {
        this.musicSubscriptionOfferTou = str;
    }
}
