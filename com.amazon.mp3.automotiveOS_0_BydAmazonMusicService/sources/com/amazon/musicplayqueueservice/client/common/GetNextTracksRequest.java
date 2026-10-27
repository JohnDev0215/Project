package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetNextTracksRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetNextTracksRequest");
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
        if (!(abstractClientRequest instanceof GetNextTracksRequest)) {
            return 1;
        }
        GetNextTracksRequest getNextTracksRequest = (GetNextTracksRequest) abstractClientRequest;
        String pageToken = getPageToken();
        String pageToken2 = getNextTracksRequest.getPageToken();
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
        if (getNumberOfTracks() < getNextTracksRequest.getNumberOfTracks()) {
            return -1;
        }
        if (getNumberOfTracks() > getNextTracksRequest.getNumberOfTracks()) {
            return 1;
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetNextTracksRequest)) {
            return false;
        }
        GetNextTracksRequest getNextTracksRequest = (GetNextTracksRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getPageToken(), getNextTracksRequest.getPageToken()) && internalEqualityCheck(Integer.valueOf(getNumberOfTracks()), Integer.valueOf(getNextTracksRequest.getNumberOfTracks()));
    }

    public int getNumberOfTracks() {
        return this.numberOfTracks;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPageToken(), Integer.valueOf(getNumberOfTracks()));
    }

    public void setNumberOfTracks(int i) {
        this.numberOfTracks = i;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }
}
