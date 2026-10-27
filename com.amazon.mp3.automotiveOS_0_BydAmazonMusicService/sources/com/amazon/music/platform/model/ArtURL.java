package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class ArtURL extends Art {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.ArtURL");
    private String artUrl;
    private String uRL;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Art, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Art art) {
        if (art == null) {
            return -1;
        }
        if (art == this) {
            return 0;
        }
        if (!(art instanceof ArtURL)) {
            return 1;
        }
        ArtURL artURL = (ArtURL) art;
        String url = getURL();
        String url2 = artURL.getURL();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo = url.compareTo(url2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String artUrl = getArtUrl();
        String artUrl2 = artURL.getArtUrl();
        if (artUrl != artUrl2) {
            if (artUrl == null) {
                return -1;
            }
            if (artUrl2 == null) {
                return 1;
            }
            int iCompareTo2 = artUrl.compareTo(artUrl2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(art);
    }

    @Override // com.amazon.music.platform.model.Art
    public boolean equals(Object obj) {
        if (!(obj instanceof ArtURL)) {
            return false;
        }
        ArtURL artURL = (ArtURL) obj;
        return super.equals(obj) && internalEqualityCheck(getURL(), artURL.getURL()) && internalEqualityCheck(getArtUrl(), artURL.getArtUrl());
    }

    public String getArtUrl() {
        return this.artUrl;
    }

    public String getURL() {
        return this.uRL;
    }

    @Override // com.amazon.music.platform.model.Art
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getURL(), getArtUrl());
    }

    public void setArtUrl(String str) {
        this.artUrl = str;
    }

    public void setURL(String str) {
        this.uRL = str;
    }
}
