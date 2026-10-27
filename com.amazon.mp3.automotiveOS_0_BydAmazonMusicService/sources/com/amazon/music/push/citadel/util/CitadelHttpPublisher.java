package com.amazon.music.push.citadel.util;

import android.content.Context;
import android.util.Log;
import com.amazon.coral.retry.RetryStrategy;
import com.amazon.coral.retry.RetryingCallable;
import com.amazon.music.metrics.technical.TechnicalMetricsCollection;
import com.amazon.music.push.citadel.request.CitadelRequest;
import com.amazon.music.push.citadel.request.CitadelRequestType;
import com.amazon.music.push.sonarpush.NotificationUtil;
import com.amazon.music.push.sonarpush.SonarPushCacheManager;
import com.amazon.music.push.util.MetricsUtil;
import com.amazon.music.push.util.PushSchedulers;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.concurrent.Callable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelHttpPublisher {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "CitadelHttpPublisher";
    private OkHttpClient httpClient;
    private TechnicalMetricsCollection technicalMetricsCollection;

    public CitadelHttpPublisher(OkHttpClient okHttpClient, TechnicalMetricsCollection technicalMetricsCollection) {
        this.httpClient = okHttpClient;
        this.technicalMetricsCollection = technicalMetricsCollection;
    }

    public void publish(final Context context, RetryStrategy<Void> retryStrategy, final CitadelRequest citadelRequest, String str) throws Exception {
        RequestBody requestBodyCreate = RequestBody.create(JSON, citadelRequest.asJson());
        final Request requestBuild = new Request.Builder().url(str).put(requestBodyCreate).headers(citadelRequest.getHeaders()).build();
        new RetryingCallable(retryStrategy, new Callable() { // from class: com.amazon.music.push.citadel.util.CitadelHttpPublisher$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$publish$0(citadelRequest, context, requestBuild);
            }
        }).call();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$publish$0(CitadelRequest citadelRequest, Context context, Request request) throws Exception {
        citadelRequest.validate(context);
        publish(request, citadelRequest.getType(), context);
        return null;
    }

    void publish(final Request request, final CitadelRequestType citadelRequestType, final Context context) {
        Observable.create(new Observable.OnSubscribe<Response>() { // from class: com.amazon.music.push.citadel.util.CitadelHttpPublisher.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Response> subscriber) {
                String applicationInstallId = NotificationUtil.getApplicationInstallId(context);
                try {
                    Response responseExecute = FirebasePerfOkHttpClient.execute(CitadelHttpPublisher.this.httpClient.newCall(request));
                    try {
                        if (!responseExecute.isSuccessful()) {
                            Log.w(CitadelHttpPublisher.TAG, String.format("Received error response '%s' from Citadel server", responseExecute.body().string()));
                            String str = responseExecute.headers().get("x-amzn-requestid");
                            if (str != null) {
                                Log.w(CitadelHttpPublisher.TAG, "Citadel API failed request id: " + str);
                            }
                            CitadelRequestType citadelRequestType2 = citadelRequestType;
                            if (citadelRequestType2 == CitadelRequestType.ACTION) {
                                CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelActionUpdateFailure", 1.0d);
                                MetricsUtil.sendEventToMTS("citadelActionUpdateFailure", applicationInstallId, "", "citadel", "regular");
                            } else if (citadelRequestType2 == CitadelRequestType.STATE) {
                                CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelStateUpdateFailure", 1.0d);
                                MetricsUtil.sendEventToMTS("citadelStateUpdateFailure", applicationInstallId, "", "citadel", "regular");
                            }
                            responseExecute.close();
                            return;
                        }
                        CitadelRequestType citadelRequestType3 = citadelRequestType;
                        if (citadelRequestType3 == CitadelRequestType.ACTION) {
                            Log.d(CitadelHttpPublisher.TAG, "Successfully published Citadel action event.");
                            CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelActionUpdateSuccess", 1.0d);
                            MetricsUtil.sendEventToMTS("citadelActionUpdateSuccess", applicationInstallId, "", "citadel", "regular");
                        } else if (citadelRequestType3 == CitadelRequestType.STATE) {
                            Log.d(CitadelHttpPublisher.TAG, "Successfully published Citadel state event.");
                            SonarPushCacheManager.putString(context, "latestCitadelSync", Long.toString(System.currentTimeMillis()));
                            CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelStateUpdateSuccess", 1.0d);
                            MetricsUtil.sendEventToMTS("citadelStateUpdateSuccess", applicationInstallId, "", "citadel", "regular");
                        }
                        responseExecute.close();
                    } catch (Throwable th) {
                        if (responseExecute != null) {
                            try {
                                responseExecute.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e) {
                    Log.w(CitadelHttpPublisher.TAG, "IOException when calling Citadel API: " + e.getMessage());
                    CitadelRequestType citadelRequestType4 = citadelRequestType;
                    if (citadelRequestType4 == CitadelRequestType.ACTION) {
                        CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelActionUpdateFailure", 1.0d);
                        MetricsUtil.sendEventToMTS("citadelActionUpdateFailure", applicationInstallId, "", "citadel", "regular");
                    } else if (citadelRequestType4 == CitadelRequestType.STATE) {
                        CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelStateUpdateFailure", 1.0d);
                        MetricsUtil.sendEventToMTS("citadelStateUpdateFailure", applicationInstallId, "", "citadel", "regular");
                    }
                } catch (Exception e2) {
                    Log.w(CitadelHttpPublisher.TAG, "Exception when calling Citadel API: " + e2.getMessage());
                    CitadelRequestType citadelRequestType5 = citadelRequestType;
                    if (citadelRequestType5 == CitadelRequestType.ACTION) {
                        CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelActionUpdateFailure", 1.0d);
                        MetricsUtil.sendEventToMTS("citadelActionUpdateFailure", applicationInstallId, "", "citadel", "regular");
                    } else if (citadelRequestType5 == CitadelRequestType.STATE) {
                        CitadelHttpPublisher.this.technicalMetricsCollection.incrementCounter("citadelStateUpdateFailure", 1.0d);
                        MetricsUtil.sendEventToMTS("citadelStateUpdateFailure", applicationInstallId, "", "citadel", "regular");
                    }
                }
            }
        }).subscribeOn(PushSchedulers.getPushScheduler()).subscribe();
    }
}
