package com.amazon.mp3.voice;

import android.content.Context;
import android.util.Pair;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.play.queue.DefaultMusicPlayQueueService;
import com.amazon.music.play.queue.MusicPlayQueueService;
import com.amazon.music.play.queue.MusicPlayQueueServiceConfig;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import com.amazon.musicplayqueueservice.model.NonRetriableClientException;
import com.android.volley.VolleyError;
import java.net.URL;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class MusicPlayQueueFactory {
    private static final String TAG = "MusicPlayQueueFactory";

    public enum GetTrackAndQueueMetadataResponseType {
        NONE,
        TRACK,
        STREAM
    }

    public static Observable<Pair<StreamInfo, GetTrackAndQueueMetadataResponse>> getTrackAndQueueMetadataRx(final Context context, final StreamInfo streamInfo) {
        return Observable.create(new Observable.OnSubscribe<Pair<StreamInfo, GetTrackAndQueueMetadataResponse>>() { // from class: com.amazon.mp3.voice.MusicPlayQueueFactory.1
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Pair<StreamInfo, GetTrackAndQueueMetadataResponse>> subscriber) {
                GetTrackAndQueueMetadataResponse trackAndQueueMetadata;
                try {
                    trackAndQueueMetadata = MusicPlayQueueFactory.getTrackAndQueueMetadata(context, streamInfo.getStreamUrl());
                } catch (VolleyError e) {
                    String message = e.getMessage();
                    if (message == null || !message.contains("NonRetriableClientException")) {
                        Log.error(MusicPlayQueueFactory.TAG, "Error while trying to getTrackAndQueueMetadata", e);
                        trackAndQueueMetadata = null;
                    } else {
                        subscriber.onError(new NonRetriableClientException(e));
                        return;
                    }
                }
                subscriber.onNext(new Pair(streamInfo, trackAndQueueMetadata));
                subscriber.onCompleted();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetTrackAndQueueMetadataResponse getTrackAndQueueMetadata(final Context context, final String trackIdentifier) throws VolleyError {
        return createMusicPlayQueueService(context).getTrackAndQueueMetadata(trackIdentifier);
    }

    public static void rateEntity(final Context context, final String rating, final String identifier, final int durationPlayedInMilliseconds, final long utcTimeStamp) {
        try {
            createMusicPlayQueueService(context).rateEntity(rating, identifier, durationPlayedInMilliseconds, utcTimeStamp);
        } catch (VolleyError e) {
            Log.error(TAG, "Error while trying to rateEntity: " + e.getMessage(), e);
        }
    }

    public static GetTrackAndQueueMetadataResponseType getTrackAndQueueMetadataResponseType(final GetTrackAndQueueMetadataResponse response) {
        GetTrackAndQueueMetadataResponseType getTrackAndQueueMetadataResponseType = GetTrackAndQueueMetadataResponseType.NONE;
        if (response == null || response.getTrackMetadata() == null) {
            return getTrackAndQueueMetadataResponseType;
        }
        String identifierType = response.getTrackMetadata().getIdentifierType();
        if (!"ASIN".equals(identifierType) && !"OBJECT_ID".equals(identifierType)) {
            return getTrackAndQueueMetadataResponseType;
        }
        if (response.getTrackMetadata().isIsSample()) {
            return GetTrackAndQueueMetadataResponseType.STREAM;
        }
        return GetTrackAndQueueMetadataResponseType.TRACK;
    }

    private static MusicPlayQueueService createMusicPlayQueueService(final Context context) {
        return new DefaultMusicPlayQueueService(getMusicPlayQueueServiceConfig(context));
    }

    private static MusicPlayQueueServiceConfig getMusicPlayQueueServiceConfig(final Context context) {
        return new MusicPlayQueueServiceConfig() { // from class: com.amazon.mp3.voice.MusicPlayQueueFactory.2
            @Override // com.amazon.music.play.queue.MusicPlayQueueServiceConfig
            public RequestInterceptor getRequestInterceptor() {
                return new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor();
            }

            @Override // com.amazon.music.play.queue.MusicPlayQueueServiceConfig
            public URL getMusicPlayQueueServiceUrl() {
                return MusicURL.getMusicPlayqueueServiceUrl();
            }
        };
    }
}
