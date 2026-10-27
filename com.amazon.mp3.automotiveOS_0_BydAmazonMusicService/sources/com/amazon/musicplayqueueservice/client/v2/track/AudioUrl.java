package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AudioUrl extends AudioSourceBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AudioUrl");
    private String url;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AudioSourceBase audioSourceBase) {
        if (audioSourceBase == null) {
            return -1;
        }
        if (audioSourceBase == this) {
            return 0;
        }
        if (!(audioSourceBase instanceof AudioUrl)) {
            return 1;
        }
        String url = getUrl();
        String url2 = ((AudioUrl) audioSourceBase).getUrl();
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
        return super.compareTo(audioSourceBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase
    public boolean equals(Object obj) {
        if (obj instanceof AudioUrl) {
            return super.equals(obj) && internalEqualityCheck(getUrl(), ((AudioUrl) obj).getUrl());
        }
        return false;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUrl());
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
