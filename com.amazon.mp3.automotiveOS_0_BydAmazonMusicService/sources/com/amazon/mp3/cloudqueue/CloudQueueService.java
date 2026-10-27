package com.amazon.mp3.cloudqueue;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoRequest;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.amazon.musicplayqueueservice.client.v2.RateEntityRequest;
import com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueRequestV1;
import com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueResponse;
import com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksRequestV1;
import com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksResponse;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.GetConnectionInfoCall;
import com.amazon.musicplayqueueservice.model.client.v2.external.voiceenabled.CreateQueueCall;
import com.amazon.musicplayqueueservice.model.client.v2.external.voiceenabled.GetNextTracksCall;
import com.amazon.musicplayqueueservice.model.client.v2.external.voiceenabled.RateEntityCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class CloudQueueService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private Configuration configuration;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);
    private List<CreateCloudQueueListener> createCloudQueueListeners = new CopyOnWriteArrayList();

    public interface CreateCloudQueueListener {
        void onCreateQueueCallStarted();
    }

    public CloudQueueService(@NonNull final Configuration configuration) {
        this.configuration = (Configuration) Validate.notNull(configuration);
    }

    public CreateQueueResponse startCloudQueue(final CreateQueueRequestV1 request) throws VolleyError {
        for (CreateCloudQueueListener createCloudQueueListener : this.createCloudQueueListeners) {
            createCloudQueueListener.onCreateQueueCallStarted();
            unregisterListener(createCloudQueueListener);
        }
        return new CreateQueueCall(getUrl(this.configuration.getMpqsUrl(), ""), request, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    public GetNextTracksResponse getTrackForCloudQueue(final GetNextTracksRequestV1 request) throws VolleyError {
        return new GetNextTracksCall(getUrl(this.configuration.getMpqsUrl(), ""), request, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    public GetConnectionInfoResponse getConnectionInfo() throws VolleyError {
        return new GetConnectionInfoCall(MusicURL.getMusicPlayqueueServiceUrl(), new GetConnectionInfoRequest(), this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    @WorkerThread
    public synchronized Observable<ConnectionInfoMetadata> getConnectionInfoMetadata() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.cloudqueue.CloudQueueService$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                this.f$0.lambda$getConnectionInfoMetadata$0(observableEmitter);
            }
        }).onErrorResumeNext(new Function() { // from class: com.amazon.mp3.cloudqueue.CloudQueueService$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return CloudQueueService.lambda$getConnectionInfoMetadata$1((Throwable) obj);
            }
        }).subscribeOn(Schedulers.computation());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getConnectionInfoMetadata$0(ObservableEmitter observableEmitter) throws Throwable {
        try {
            observableEmitter.onNext(QueueEntityUtil.convertConnectionInfoMetadata(getConnectionInfo()));
            observableEmitter.onComplete();
        } catch (Exception e) {
            observableEmitter.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ObservableSource lambda$getConnectionInfoMetadata$1(Throwable th) throws Throwable {
        return Observable.just(null);
    }

    public void rateEntity(final RateEntityRequest request) throws VolleyError {
        new RateEntityCall(getUrl(this.configuration.getMpqsUrl(), ""), request, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    private final URL getUrl(final URL url, final String operation) {
        try {
            return new URL(Uri.parse(url.toString()).buildUpon().appendEncodedPath(operation).build().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void unregisterListener(final CreateCloudQueueListener listener) {
        this.createCloudQueueListeners.remove(listener);
    }
}
