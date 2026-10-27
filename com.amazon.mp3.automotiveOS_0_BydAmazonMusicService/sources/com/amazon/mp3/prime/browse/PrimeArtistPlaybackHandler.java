package com.amazon.mp3.prime.browse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.prime.PrimeArtistPlaybackTask;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.browse.RxArtistBrowse;
import com.amazon.music.browse.artist.ArtistBrowse;
import com.amazon.music.browse.artist.ArtistBrowseRequest;
import com.amazon.music.browse.artist.SortBy;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.storeservice.model.TrackItem;
import com.amazon.musicensembleservice.ArtistDetailsMetadataResponse;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeArtistPlaybackHandler extends CatalogContentPlaybackHandler<PrimeArtist> {
    private static final String TAG = "PrimeArtistPlaybackHandler";
    private Observable<ArtistDetailsMetadataResponse> mArtistDetailsObservable;
    private ControlSource mControlSource;
    private PrimeCollectionTask.Task mPlaybackTask;
    private PrimeArtist mPrimeArtist;
    private CompositeSubscription mSubscriptions;

    public PrimeArtistPlaybackHandler(final Context context) {
        super(context);
        this.mControlSource = ControlSource.APP_UI;
    }

    public PrimeArtistPlaybackHandler(final Context context, final ControlSource controlSource) {
        super(context);
        ControlSource controlSource2 = ControlSource.APP_UI;
        this.mControlSource = controlSource;
    }

    @Override // com.amazon.mp3.prime.browse.CatalogContentPlaybackHandler
    public void startPlayback(final PrimeArtist artist, final PrimeCollectionTask.Task task) {
        if (!isPlaybackTask(task)) {
            Log.w(TAG, "RECEIVED TASK THAT CANNOT BE PLAYED: " + task);
            return;
        }
        this.mPrimeArtist = artist;
        this.mPlaybackTask = task;
        this.mSubscriptions = new CompositeSubscription();
        if (this.mArtistDetailsObservable == null) {
            this.mArtistDetailsObservable = createArtistDetailsObservable();
        }
        subscribe(this.mArtistDetailsObservable, new Observer<ArtistDetailsMetadataResponse>() { // from class: com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler.1
            boolean isError = false;

            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable e) {
                ConnectivityUtil.checkConnectivityAndShowDialog(PrimeArtistPlaybackHandler.this.mContext);
                Log.e(PrimeArtistPlaybackHandler.TAG, "Unable to load top tracks and albums", e);
                this.isError = true;
            }

            @Override // rx.Observer
            public void onNext(final ArtistDetailsMetadataResponse response) {
                if (response != null && !TextUtils.isEmpty(response.getArtistName())) {
                    PrimeArtistPlaybackHandler.this.mPrimeArtist.setTitle(response.getArtistName());
                    PrimeArtistPlaybackHandler.this.startPlayback(response.getTrackList());
                }
                PrimeArtistPlaybackHandler.this.mArtistDetailsObservable = null;
                PrimeArtistPlaybackHandler.this.mSubscriptions.unsubscribe();
                PrimeArtistPlaybackHandler.this.mSubscriptions = null;
            }
        });
    }

    private Observable<ArtistDetailsMetadataResponse> createArtistDetailsObservable() {
        AccountDetailUtil.get(this.mContext);
        return new RxArtistBrowse(new ArtistBrowse(BrowseFactory.createBrowseService(this.mContext), AccountDetailUtil.getMusicTerritoryOfResidence())).getArtistDetails(ArtistBrowseRequest.createBuilder(new BrowseContentSelectionProvider().getBrowseContentSelection(AmazonApplication.getContext()), AccountCredentialStorage.get().getDeviceId(), AccountCredentialStorage.get().getDeviceType()).withAsin(this.mPrimeArtist.getAsin()).withTracks(PrimeTracksLoadedCallbacks.getMaxQueueSize(), SortBy.POPULARITY).withExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()).build()).cache();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void subscribe(final Observable<T> request, final Observer<T> observer) {
        this.mSubscriptions.add(request.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void startPlayback(final List<TrackItem> trackList) {
        if (trackList == null || trackList.isEmpty()) {
            return;
        }
        PrimeArtistPlaybackTask.createPlaybackTask(this.mContext, this.mPrimeArtist, PrimeItemsTransformationUtil.toPrimeTrackList(trackList), getPlaybackTask(this.mPlaybackTask), this.mControlSource).execute(new Void[0]);
    }

    private PrimeArtistPlaybackTask.Task getPlaybackTask(final PrimeCollectionTask.Task task) {
        if (task.equals(PrimeCollectionTask.Task.PLAYBACK)) {
            return PrimeArtistPlaybackTask.Task.PLAYBACK;
        }
        if (task.equals(PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN)) {
            return PrimeArtistPlaybackTask.Task.PLAYBACK_FULLSCREEN;
        }
        if (task.equals(PrimeCollectionTask.Task.PLAYBACK_SHUFFLE)) {
            return PrimeArtistPlaybackTask.Task.PLAYBACK_SHUFFLE;
        }
        return null;
    }
}
