package com.amazon.mp3.voice.hints.network;

import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

/* JADX INFO: compiled from: AlexaHintService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JC\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/voice/hints/network/AlexaHintService;", "", "getAlexaHints", "Lretrofit2/Response;", "Lcom/amazon/mp3/voice/hints/network/AHSHints;", "headers", "", "", "queryParams", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface AlexaHintService {
    @GET("/v1/hints")
    Object getAlexaHints(@HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2, Continuation<? super Response<AHSHints>> continuation);
}
