package com.amazon.mp3.net.dmls;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ContentResponse {
    private static final List<String> DEBUG_URLS_LIST = Arrays.asList("digital-music-delivery-na.amazon.com", "cirrus-bits-central-na-prod.s3.amazonaws.com");
    private static final String TAG = "ContentResponse";
    private final List<AssetQuality> mAssetQualities;
    private final StreamingBitrate mBitrate;
    private final long mExpiration;
    private final ContentId mId;
    private final Protocol mProtocol;
    private final StatusCode mStatus;
    private final String mStatusMessage;
    private final List<String> mUrlList;

    public enum Protocol {
        HLS,
        DASH,
        PROGRESSIVE
    }

    public ContentResponse(ContentId id, long expiration, StatusCode status, String statusMessage, List<String> urls, Protocol protocol, StreamingBitrate bitrate, List<AssetQuality> assetQualities) {
        this.mId = id;
        this.mExpiration = expiration;
        this.mStatus = status;
        this.mStatusMessage = statusMessage;
        this.mUrlList = urls;
        this.mProtocol = protocol;
        this.mBitrate = bitrate;
        this.mAssetQualities = assetQualities;
    }

    public static class AssetQuality {
        private final String mQuality;
        private final int mRank;

        public AssetQuality(final String quality, final int rank) {
            this.mQuality = quality;
            this.mRank = rank;
        }

        public String getQuality() {
            return this.mQuality;
        }

        public int getRank() {
            return this.mRank;
        }

        public String toString() {
            return "AssetQuality{quality='" + this.mQuality + "', rank=" + this.mRank + '}';
        }
    }

    public long getExpiration() {
        return this.mExpiration;
    }

    public StatusCode getStatus() {
        return this.mStatus;
    }

    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    public List<String> getUrlList() {
        return this.mUrlList;
    }

    public StreamingBitrate getBitrate() {
        return this.mBitrate;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.mAssetQualities;
    }

    public String toString() {
        return "ID: [" + this.mId.toString() + "], Date: " + new Date(this.mExpiration).toString() + ", Status: " + this.mStatus.toString() + ", Message: " + this.mStatusMessage + ", Protocol: " + this.mProtocol.toString() + ", Bitrate: " + this.mBitrate.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00c8 A[Catch: JSONException -> 0x00da, TryCatch #4 {JSONException -> 0x00da, blocks: (B:39:0x00bb, B:41:0x00c8, B:43:0x00ce, B:45:0x00d4), top: B:50:0x00bb }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.mp3.net.dmls.ContentResponse fromJSON(com.amazon.mp3.net.dmls.StreamingBitrate r12, org.json.JSONObject r13, boolean r14, boolean r15) throws org.json.JSONException, com.amazon.mp3.net.dmls.DMLSExceptions.InvalidContentIdException {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.net.dmls.ContentResponse.fromJSON(com.amazon.mp3.net.dmls.StreamingBitrate, org.json.JSONObject, boolean, boolean):com.amazon.mp3.net.dmls.ContentResponse");
    }

    private static boolean isDebugUrl(String url) {
        Iterator<String> it = DEBUG_URLS_LIST.iterator();
        while (it.hasNext()) {
            if (url.contains(it.next())) {
                return true;
            }
        }
        return false;
    }
}
