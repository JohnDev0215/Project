package com.amazon.mp3.library.job;

import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.playlist.PrimePlaylistFollowState;
import com.amazon.mp3.playlist.api.MC2PlaylistApi;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausToastUtils;

/* JADX INFO: loaded from: classes3.dex */
public class FollowPrimePlaylistJob extends Job {
    private final PrimePlaylistDatabaseManager mDbHandler = new PrimePlaylistDatabaseManager(AmazonApplication.getContext());
    private int mErrorCode;
    private String mErrorLog;
    private PrimePlaylistFollowState mFollowState;
    private final String mPrimePlaylistAsin;
    private String mPrimePlaylistLuid;

    public FollowPrimePlaylistJob(String asin) {
        this.mPrimePlaylistAsin = asin;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int run() throws Exception {
        int iFollowPlaylist = followPlaylist();
        if (iFollowPlaylist == 3) {
            showErrorToast();
        }
        return iFollowPlaylist;
    }

    private int followPlaylist() {
        if (!TextUtils.isEmpty(this.mPrimePlaylistAsin)) {
            try {
                String strFollowPlaylistByAsin = new MC2PlaylistApi().followPlaylistByAsin(AmazonApplication.getContext(), this.mPrimePlaylistAsin);
                this.mPrimePlaylistLuid = strFollowPlaylistByAsin;
                this.mDbHandler.follow(this.mPrimePlaylistAsin, strFollowPlaylistByAsin);
                this.mFollowState = PrimePlaylistFollowState.FOLLOWING;
                SettingsUtil.setHasFollowedPrimePlaylists(getContext(), true);
                getContext().getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                return 1;
            } catch (AbstractHttpClient.CanceledException e) {
                this.mErrorCode = -1;
                this.mErrorLog = e.getMessage();
                return 3;
            } catch (AbstractHttpClient.FailedException e2) {
                this.mErrorCode = -1;
                this.mErrorLog = e2.getMessage();
                return 3;
            } catch (AbstractHttpClient.IncompleteResultException e3) {
                this.mErrorCode = -1;
                this.mErrorLog = e3.getMessage();
                return 3;
            } catch (AbstractHttpClient.UnexpectedHttpStatusException e4) {
                this.mErrorCode = -1;
                this.mErrorLog = e4.getMessage();
                return 3;
            } catch (ServiceException e5) {
                this.mErrorCode = -1;
                this.mErrorLog = e5.getMessage();
                return 3;
            } catch (Exception e6) {
                this.mErrorCode = -1;
                this.mErrorLog = e6.getMessage();
                return 3;
            }
        }
        this.mErrorCode = -1;
        this.mErrorLog = "prime playlist asin not available!";
        throw new IllegalStateException(this.mErrorLog);
    }

    public PrimePlaylistFollowState getFollowState() {
        return this.mFollowState;
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

    private void showErrorToast() {
        final String string = getContext().getString(R.string.dmusic_unable_to_add_prime_playlist);
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.library.job.FollowPrimePlaylistJob.1
            @Override // java.lang.Runnable
            public void run() {
                BauhausToastUtils.showDefaultToast(FollowPrimePlaylistJob.this.getContext(), string, 0);
            }
        });
    }
}
