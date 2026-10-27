package com.amazon.mp3.library.job;

import com.amazon.mp3.playlist.PrimePlaylistFollowState;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes3.dex */
public class UnfollowPrimePlaylistJob extends Job {
    private static final String TAG = "UnfollowPrimePlaylistJob";
    private boolean mBypassRemoteDelete;
    private int mErrorCode;
    private String mErrorLog;
    private PrimePlaylistFollowState mFollowState;
    private final String mPrimePlaylistAsin;
    private String mPrimePlaylistLuid;

    public UnfollowPrimePlaylistJob(String asin, String primePlaylistLuid, boolean bypassRemoteDelete) {
        this.mPrimePlaylistAsin = asin;
        this.mPrimePlaylistLuid = primePlaylistLuid;
        this.mBypassRemoteDelete = bypassRemoteDelete;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        return unfollowPlaylist(getContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int unfollowPlaylist(android.content.Context r4) throws com.amazon.mp3.net.service.ServiceException, com.amazon.mp3.net.AbstractHttpClient.HttpClientException {
        /*
            r3 = this;
            java.lang.String r0 = r3.mPrimePlaylistAsin
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L70
            java.lang.String r0 = r3.mPrimePlaylistLuid
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 1
            if (r0 != 0) goto L22
            com.amazon.mp3.playlist.api.MC2PlaylistApi r0 = new com.amazon.mp3.playlist.api.MC2PlaylistApi
            r0.<init>()
            boolean r2 = r3.mBypassRemoteDelete
            if (r2 == 0) goto L1b
            goto L22
        L1b:
            java.lang.String r2 = r3.mPrimePlaylistLuid
            boolean r0 = r0.deletePlaylist(r4, r2)
            goto L23
        L22:
            r0 = r1
        L23:
            if (r0 == 0) goto L67
            com.amazon.mp3.playlist.PrimePlaylistFollowState r0 = com.amazon.mp3.playlist.PrimePlaylistFollowState.UNFOLLOWED
            r3.mFollowState = r0
            com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager r0 = new com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager
            r0.<init>(r4)
            java.lang.String r2 = r3.mPrimePlaylistAsin
            r0.unfollow(r2)
            java.lang.String r0 = r3.mPrimePlaylistAsin
            com.amazon.mp3.prime.PrimePlaylistUtil.cancelPrimePlaylistDownload(r4, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "localPrimePlaylist-"
            r0.append(r2)
            java.lang.String r2 = r3.mPrimePlaylistLuid
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.amazon.mp3.prime.PrimePlaylistUtil.deleteOfflinePrimePlaylist(r4, r0)
            com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager r0 = new com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager
            r0.<init>()
            java.lang.String r2 = "cirrus"
            boolean r0 = r0.hasPrimePlaylists(r2)
            com.amazon.mp3.activity.settings.SettingsUtil.setHasFollowedPrimePlaylists(r4, r0)
            android.content.ContentResolver r4 = r4.getContentResolver()
            android.net.Uri r0 = com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource.NOTIFICATION_URI
            r2 = 0
            r4.notifyChange(r0, r2)
            return r1
        L67:
            java.lang.String r4 = com.amazon.mp3.library.job.UnfollowPrimePlaylistJob.TAG
            java.lang.String r0 = "Error unfollowing playlist (API call)"
            com.amazon.mp3.util.Log.error(r4, r0)
            r4 = 3
            return r4
        L70:
            r4 = -1
            r3.mErrorCode = r4
            java.lang.String r4 = "prime playlist asin is null or empty!"
            r3.mErrorLog = r4
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = r3.mErrorLog
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.job.UnfollowPrimePlaylistJob.unfollowPlaylist(android.content.Context):int");
    }

    public String getPrimePlaylistLuid() {
        return this.mPrimePlaylistLuid;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorLog() {
        return this.mErrorLog;
    }
}
