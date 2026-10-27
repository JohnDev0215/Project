package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.prime.station.StationTrack;
import com.amazon.mp3.prime.station.StationsFactory;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.station.NoNextTrackException;
import com.amazon.music.station.Station;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.SynchronizedTrackItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class StationTrackProvider implements TrackProvider {
    private static final String TAG = "StationTrackProvider";
    private Uri mContentUri;
    private Context mContext;
    private LoadCookie mCookie;
    private volatile NoNextTrackException.Reason mExceptionReason;
    private boolean mIsCastingToAlexa;
    private volatile boolean mIsLoading;
    private Station mStation;
    private StationItem mStationItem;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private final CopyOnWriteArraySet<TrackProviderListener> mTrackProviderListeners = new CopyOnWriteArraySet<>();
    private final List<MusicTrack> mTracks = new ArrayList();
    private volatile int mExceptionPositionNotified = -1;
    private volatile int mExceptionPosition = -1;

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void cancel() {
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean supportsRepeatMode() {
        return false;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean supportsShuffle() {
        return false;
    }

    private void init(Context context, Uri contentUri, StationItem stationItem) {
        this.mContext = context.getApplicationContext();
        this.mContentUri = contentUri;
        this.mStationItem = stationItem;
        Marketplace homeMarketPlace = AccountDetailUtil.get(context).getHomeMarketPlace();
        StationsFactory stationsFactory = StationsFactory.getInstance(context);
        boolean zIsExplicitLanguageFilterEnabled = AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
        boolean zIsCastingToAlexa = CastingUtil.isCastingToAlexa();
        this.mIsCastingToAlexa = zIsCastingToAlexa;
        this.mStation = Station.create(homeMarketPlace, this.mStationItem, stationsFactory.getStationService(), zIsExplicitLanguageFilterEnabled, zIsCastingToAlexa ? CastingSessionManager.getInstance().getCastingDevice().getTargetId() : null);
    }

    private final class GetNextTrackTask extends AsyncTask<Void, Void, MusicTrack> {
        private final boolean mStartStation;

        GetNextTrackTask(boolean startStation) {
            this.mStartStation = startStation;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public MusicTrack doInBackground(Void... params) {
            MusicTrack nextTrack = null;
            for (int i = 1; i <= 3; i++) {
                Log.info(StationTrackProvider.TAG, "Fetching track %d, attempt %d", Integer.valueOf(StationTrackProvider.this.mTracks.size()), Integer.valueOf(i));
                if (i > 1) {
                    SystemClock.sleep(((long) (i - 1)) * 1000);
                }
                try {
                    nextTrack = getNextTrack();
                    PrimeContentUtil.loadLibraryInfoForTrack(StationTrackProvider.this.mContext, nextTrack);
                    StationTrackProvider.this.clearExceptions();
                    break;
                } catch (NoNextTrackException e) {
                    StationTrackProvider stationTrackProvider = StationTrackProvider.this;
                    stationTrackProvider.mExceptionPosition = stationTrackProvider.mTracks.size();
                    StationTrackProvider.this.mExceptionReason = e.getReason();
                    if (StationTrackProvider.this.mExceptionPosition == PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentIndex()) {
                        StationTrackProvider.this.notifyStationError();
                    }
                    Log.warning(StationTrackProvider.TAG, "no more next track " + e.getMessage());
                } catch (InterruptedException e2) {
                    Log.warning(StationTrackProvider.TAG, "interruption exception: " + e2.getMessage());
                }
            }
            return nextTrack;
        }

        private MusicTrack getNextTrack() throws NoNextTrackException, InterruptedException {
            SynchronizedTrackItem synchronizedTrackItemNextTrack = StationTrackProvider.this.mStation.nextTrack();
            Log.debug(StationTrackProvider.TAG, "isPrime = " + String.valueOf(synchronizedTrackItemNextTrack.isPrime()) + ", isMusicSubscription = " + String.valueOf(synchronizedTrackItemNextTrack.isMusicSubscription()));
            StationTrack stationTrack = new StationTrack(synchronizedTrackItemNextTrack);
            if (stationTrack.getContentCatalogStatus().isPreviouslyCatalog()) {
                throw new NoNextTrackException(new Exception("Service returned non-catalog track"));
            }
            return stationTrack;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(MusicTrack track) {
            if (track != null) {
                StationTrackProvider.this.mTracks.add(track);
            }
            StationTrackProvider.this.mIsLoading = false;
            if (this.mStartStation) {
                StationStorageUtil.insert(StationTrackProvider.this.mContext, StationTrackProvider.this.mStationItem);
                Iterator it = StationTrackProvider.this.mTrackProviderListeners.iterator();
                while (it.hasNext()) {
                    ((TrackProviderListener) it.next()).onCollectionLoaded(StationTrackProvider.this.mCookie);
                }
                return;
            }
            StationTrackProvider stationTrackProvider = StationTrackProvider.this;
            stationTrackProvider.refresh(stationTrackProvider.mCookie);
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void loadCollection(final LoadCookie cookie) {
        this.mIsLoading = true;
        this.mCookie = cookie;
        if (this.mIsCastingToAlexa) {
            return;
        }
        new GetNextTrackTask(true).executeOnExecutor(this.mExecutorService, new Void[0]);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void loadCollectionName() {
        Iterator<TrackProviderListener> it = this.mTrackProviderListeners.iterator();
        while (it.hasNext()) {
            it.next().onCollectionNameLoaded(this.mStationItem.getTitle());
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public boolean isLoading() {
        return this.mIsLoading;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public MusicTrack getTrack(final int position) {
        if (this.mIsCastingToAlexa) {
            return null;
        }
        int size = this.mTracks.size();
        String str = TAG;
        Log.info(str, "get position %d of size %d", Integer.valueOf(position), Integer.valueOf(size));
        int i = size - 1;
        if (position >= i) {
            new GetNextTrackTask(false).executeOnExecutor(this.mExecutorService, new Void[0]);
        }
        if (position >= 0 && position < size) {
            return this.mTracks.get(position);
        }
        if (size > 0) {
            MusicTrack musicTrack = this.mTracks.get(i);
            Log.info(str, "Position %d is out of bounds. Returning track at last position %d", Integer.valueOf(position), Integer.valueOf(i));
            return musicTrack;
        }
        Log.info(str, "getTrack called but there are no tracks");
        return null;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public int getCount() {
        return this.mTracks.size();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public String getCollectionName() {
        return this.mStationItem.getTitle();
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void close() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.library.provider.source.nowplaying.StationTrackProvider.1
            @Override // java.lang.Runnable
            public void run() {
                Uri currentUri = MediaItemHelper.getCurrentUri();
                if (currentUri == null || currentUri.equals(StationTrackProvider.this.mContentUri)) {
                    StationTrackProvider.this.notifyStationError();
                }
            }
        }, 500L);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void refresh(final LoadCookie cookie) {
        Iterator<TrackProviderListener> it = this.mTrackProviderListeners.iterator();
        while (it.hasNext()) {
            it.next().onCountChanged(this.mTracks.size());
        }
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void registerListener(final TrackProviderListener listener) {
        this.mTrackProviderListeners.add(listener);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void deregisterListener(final TrackProviderListener listener) {
        this.mTrackProviderListeners.remove(listener);
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public Uri getUri() {
        return this.mContentUri;
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void populateState(final JSONObject jsonObject) throws JSONException {
        stationItemToJson(jsonObject, this.mStationItem, this.mContentUri);
    }

    public static void stationItemToJson(JSONObject jsonObject, StationItem stationItem, Uri contentUri) throws JSONException {
        StationStorageUtil.toJson(stationItem, jsonObject);
        jsonObject.put("content_uri", contentUri.toString());
    }

    @Override // com.amazon.mp3.library.provider.source.nowplaying.TrackProvider
    public void restore(Context context, final JSONObject jsonObject) throws JSONException {
        Uri uri = Uri.parse(jsonObject.getString("content_uri"));
        StationItem stationItemFromJson = StationStorageUtil.fromJson(jsonObject);
        Log.debug(TAG, "Restoring station \"%s\"", stationItemFromJson.getTitle());
        init(context, uri, stationItemFromJson);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStationError() {
        boolean z = this.mExceptionPosition != -1 && this.mExceptionPosition == this.mExceptionPositionNotified;
        if (this.mExceptionReason == null || z) {
            return;
        }
        this.mExceptionPositionNotified = this.mExceptionPosition;
        Intent intent = new Intent("com.amazon.music.prime.station.error");
        intent.setClass(this.mContext, StationTrackProvider.class);
        intent.putExtra("com.amazon.music.prime.station.error.reason", this.mExceptionReason);
        intent.putExtra("com.amazon.music.prime.station.error.atstartup", this.mExceptionPosition == 0);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
        clearExceptions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExceptions() {
        this.mExceptionReason = null;
        this.mExceptionPosition = -1;
    }
}
