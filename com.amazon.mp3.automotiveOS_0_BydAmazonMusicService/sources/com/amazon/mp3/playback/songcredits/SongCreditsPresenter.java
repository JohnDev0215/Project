package com.amazon.mp3.playback.songcredits;

import com.amazon.mp3.util.Log;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.TrackLookup;
import java.util.Iterator;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class SongCreditsPresenter implements SongCreditsContract$Presenter {
    private static final String TAG = "SongCreditsPresenter";
    private BrowseService browseService;
    private Subscription subscription;
    private SongCreditsContract$View view;

    SongCreditsPresenter(SongCreditsContract$View view, BrowseService browseService) {
        this.view = view;
        this.browseService = browseService;
    }

    @Override // com.amazon.mp3.playback.songcredits.SongCreditsContract$Presenter
    public void onDestroy() {
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.view = null;
    }

    @Override // com.amazon.mp3.playback.songcredits.SongCreditsContract$Presenter
    public void presentSongWriterData(final String asin) {
        this.subscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.songcredits.SongCreditsPresenter$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$presentSongWriterData$0(asin, (Subscriber) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.songcredits.SongCreditsPresenter$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$presentSongWriterData$1((String) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.songcredits.SongCreditsPresenter$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SongCreditsPresenter.lambda$presentSongWriterData$2((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$presentSongWriterData$0(String str, Subscriber subscriber) {
        if (this.view != null && !this.subscription.isUnsubscribed()) {
            getSongWriterData(str, subscriber);
        } else {
            Log.debug(TAG, "view detached");
        }
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$presentSongWriterData$1(String str) {
        if (this.view == null || this.subscription.isUnsubscribed()) {
            return;
        }
        this.view.setSongWriterText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$presentSongWriterData$2(Throwable th) {
        th.printStackTrace();
        Log.error(TAG, th.getMessage());
    }

    private void getSongWriterData(String asin, Subscriber<? super String> subscriber) {
        try {
            TrackLookup.Response response = new TrackLookup(this.browseService).get(this.view.getTrackLookupRequest(asin), false);
            if (response == null || response.tracks.isEmpty() || response.tracks.get(0) == null || response.tracks.get(0).getSongWriters() == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = response.tracks.get(0).getSongWriters().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("\n");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            subscriber.onNext(sb.toString());
        } catch (BrowseException e) {
            e.printStackTrace();
            Log.error(TAG, e.getMessage());
        }
    }
}
