package com.amazon.mp3.library.util;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.playlist.CuratePlaylistSaver;
import com.amazon.mp3.recentlyplayed.RecentTrack;
import com.amazon.mp3.recentlyplayed.Recents;
import com.amazon.mp3.util.Log;
import com.amazon.music.recents.RecentsException;
import com.amazon.music.recents.model.ActivityType;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class SuggestedSongsViewModel extends AndroidViewModel {
    private static final String TAG = "SuggestedSongsViewModel";
    private List<String> mAddedTracks;
    private String mPlaylistId;
    private Subscription mRecentActivitySubscription;
    private MutableLiveData<List<RecentTrack>> mSuggestedTrackList;
    private MutableLiveData<List<RecentTrack>> mToAddTracks;

    public SuggestedSongsViewModel(Application application) {
        super(application);
        this.mToAddTracks = new MutableLiveData<>();
        this.mAddedTracks = new ArrayList();
        this.mToAddTracks.setValue(new ArrayList());
    }

    public MutableLiveData<List<RecentTrack>> getSuggestedTracks() {
        if (this.mSuggestedTrackList == null) {
            MutableLiveData<List<RecentTrack>> mutableLiveData = new MutableLiveData<>();
            this.mSuggestedTrackList = mutableLiveData;
            mutableLiveData.setValue(new ArrayList());
            unsubscribeRecentActivity();
            this.mRecentActivitySubscription = load(null).subscribe(new Action1() { // from class: com.amazon.mp3.library.util.SuggestedSongsViewModel$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$getSuggestedTracks$0((Recents.RecentsResponse) obj);
                }
            });
        }
        return this.mSuggestedTrackList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getSuggestedTracks$0(Recents.RecentsResponse recentsResponse) {
        this.mSuggestedTrackList.postValue(recentsResponse.getTracks());
    }

    public MutableLiveData<List<RecentTrack>> getAddedTracks() {
        return this.mToAddTracks;
    }

    private Observable<Recents.RecentsResponse> load(final String pageToken) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.util.SuggestedSongsViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$load$1(pageToken, (Subscriber) obj);
            }
        }).cache().subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$1(String str, Subscriber subscriber) {
        try {
            subscriber.onNext(Recents.get(getApplication()).withActivityTypes(ActivityType.PLAYED).withRemoveDuplicateTracks(true).withFilterSubscription(true).withPageToken(str).getRecents());
            subscriber.onCompleted();
        } catch (RecentsException e) {
            Log.error(TAG, "Failed to load recents", e);
            subscriber.onError(e);
        }
    }

    public RecentTrack addTrack(int position) {
        List<RecentTrack> value = this.mSuggestedTrackList.getValue();
        RecentTrack recentTrackRemove = value.remove(position);
        this.mSuggestedTrackList.setValue(value);
        List<RecentTrack> value2 = this.mToAddTracks.getValue();
        value2.add(recentTrackRemove);
        this.mToAddTracks.setValue(value2);
        return recentTrackRemove;
    }

    public void setPlaylistId(final String playlistId) {
        this.mPlaylistId = playlistId;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        saveAddedTracks();
    }

    private void saveAddedTracks() {
        List<RecentTrack> value = this.mToAddTracks.getValue();
        if (value.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(value.size());
        for (RecentTrack recentTrack : value) {
            if (!this.mAddedTracks.contains(recentTrack.getAsin())) {
                MusicTrack trackForAsin = new CirrusSourceLibraryItemFactory(getApplication()).getTrackForAsin(recentTrack.getAsin());
                arrayList.add(trackForAsin == null ? recentTrack.getAsin() : trackForAsin.getLuid());
            }
        }
        if (!arrayList.isEmpty()) {
            new CuratePlaylistSaver().saveTracksToPlaylist(getApplication(), this.mPlaylistId, arrayList);
        }
        this.mAddedTracks.addAll(arrayList);
        unsubscribeRecentActivity();
    }

    public void saveAndClear() {
        saveAddedTracks();
        this.mToAddTracks.getValue().clear();
        this.mAddedTracks.clear();
    }

    private void unsubscribeRecentActivity() {
        Subscription subscription = this.mRecentActivitySubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.mRecentActivitySubscription.unsubscribe();
    }
}
