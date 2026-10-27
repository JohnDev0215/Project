package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.musicplayqueueservice.client.common.AbstractClientRequest;
import com.amazon.musicplayqueueservice.client.v2.queue.AdContext;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AbstractGetNextTracksRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AbstractGetNextTracksRequest");
    private AdContext adContext;
    private int numberOfTracks;
    private String pageToken;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        if (!(abstractClientRequest instanceof AbstractGetNextTracksRequest)) {
            return 1;
        }
        AbstractGetNextTracksRequest abstractGetNextTracksRequest = (AbstractGetNextTracksRequest) abstractClientRequest;
        String pageToken = getPageToken();
        String pageToken2 = abstractGetNextTracksRequest.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo = pageToken.compareTo(pageToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (getNumberOfTracks() < abstractGetNextTracksRequest.getNumberOfTracks()) {
            return -1;
        }
        if (getNumberOfTracks() > abstractGetNextTracksRequest.getNumberOfTracks()) {
            return 1;
        }
        AdContext adContext = getAdContext();
        AdContext adContext2 = abstractGetNextTracksRequest.getAdContext();
        if (adContext != adContext2) {
            if (adContext == null) {
                return -1;
            }
            if (adContext2 == null) {
                return 1;
            }
            int iCompareTo2 = adContext.compareTo(adContext2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractGetNextTracksRequest)) {
            return false;
        }
        AbstractGetNextTracksRequest abstractGetNextTracksRequest = (AbstractGetNextTracksRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getPageToken(), abstractGetNextTracksRequest.getPageToken()) && internalEqualityCheck(Integer.valueOf(getNumberOfTracks()), Integer.valueOf(abstractGetNextTracksRequest.getNumberOfTracks())) && internalEqualityCheck(getAdContext(), abstractGetNextTracksRequest.getAdContext());
    }

    public AdContext getAdContext() {
        return this.adContext;
    }

    public int getNumberOfTracks() {
        return this.numberOfTracks;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPageToken(), Integer.valueOf(getNumberOfTracks()), getAdContext());
    }

    public void setAdContext(AdContext adContext) {
        this.adContext = adContext;
    }

    public void setNumberOfTracks(int i) {
        this.numberOfTracks = i;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }
}
