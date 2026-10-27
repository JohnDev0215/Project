package com.amazon.mp3.voice.hints.network;

import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.configuration.AlexaLocaleProvider;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.aom.hints.AHSMarketplaceHelper;
import com.amazon.music.platform.aom.hints.dae.DAE;
import com.amazon.music.platform.aom.hints.provider.RemoteAlexaHintsProvider;
import com.amazonaws.http.HttpHeader;
import com.google.gson.GsonBuilder;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: compiled from: RemoteAlexaHintsProviderImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0006H\u0002J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0011H\u0007J\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/voice/hints/network/RemoteAlexaHintsProviderImpl;", "Lcom/amazon/music/platform/aom/hints/provider/RemoteAlexaHintsProvider;", "()V", "getAlexaHintService", "Lcom/amazon/mp3/voice/hints/network/AlexaHintService;", "baseUrl", "", "getAlexaHints", "Lcom/amazon/music/platform/aom/Outcome;", "Lcom/amazon/music/platform/aom/hints/AlexaHints;", "dae", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "(Lcom/amazon/music/platform/aom/hints/dae/DAE;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentTimeMillis", "", "getMAPToken", "getRequestHeaders", "", "getRequestQueryParams", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RemoteAlexaHintsProviderImpl implements RemoteAlexaHintsProvider {
    private static final String TAG = Reflection.getOrCreateKotlinClass(RemoteAlexaHintsProviderImpl.class).getSimpleName();

    /* JADX INFO: renamed from: com.amazon.mp3.voice.hints.network.RemoteAlexaHintsProviderImpl$getAlexaHints$1, reason: invalid class name */
    /* JADX INFO: compiled from: RemoteAlexaHintsProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.voice.hints.network.RemoteAlexaHintsProviderImpl", f = "RemoteAlexaHintsProviderImpl.kt", i = {0}, l = {42}, m = "getAlexaHints", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RemoteAlexaHintsProviderImpl.this.getAlexaHints(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.aom.hints.provider.RemoteAlexaHintsProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getAlexaHints(com.amazon.music.platform.aom.hints.dae.DAE r9, kotlin.coroutines.Continuation<? super com.amazon.music.platform.aom.Outcome<com.amazon.music.platform.aom.hints.AlexaHints>> r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.voice.hints.network.RemoteAlexaHintsProviderImpl.getAlexaHints(com.amazon.music.platform.aom.hints.dae.DAE, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @VisibleForTesting
    public final Map<String, String> getRequestHeaders() throws Exception {
        return MapsKt__MapsKt.mapOf(TuplesKt.to(HttpHeader.ACCEPT, "application/hal+json"), TuplesKt.to("x-amz-access-token", getMAPToken()));
    }

    @VisibleForTesting
    public final Map<String, String> getRequestQueryParams(DAE dae) {
        Intrinsics.checkNotNullParameter(dae, "dae");
        Locale locale = AlexaLocaleProvider.getLocale();
        AHSMarketplaceHelper aHSMarketplaceHelper = new AHSMarketplaceHelper();
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        Intrinsics.checkNotNullExpressionValue(musicTerritoryOfResidence, "getMusicTerritoryOfResidence()");
        return MapsKt__MapsKt.mapOf(TuplesKt.to("domain", dae.getDomain()), TuplesKt.to("application", dae.getApplication()), TuplesKt.to("eventType", dae.getEventType()), TuplesKt.to("wakeword", "Alexa"), TuplesKt.to(Splash.PARAMS_LOCALE, locale.getLanguage() + '-' + ((Object) locale.getCountry())), TuplesKt.to(IntlConfiguration.MARKETPLACE, aHSMarketplaceHelper.getMarketplace(musicTerritoryOfResidence)), TuplesKt.to("limit", "12"));
    }

    private final AlexaHintService getAlexaHintService(String baseUrl) {
        Object objCreate = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(AHSHints.class, new AHSResponseDeserializer()).create())).build().create(AlexaHintService.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(AlexaHintService::class.java)");
        return (AlexaHintService) objCreate;
    }

    private final String getMAPToken() throws Exception {
        try {
            String token = MAPCookiesManager.getCookiesManager().getToken();
            Intrinsics.checkNotNullExpressionValue(token, "{\n            MAPCookies…Manager().token\n        }");
            return token;
        } catch (Exception e) {
            Log.debug(TAG, "Exception in fetching MAP token");
            throw e;
        }
    }

    @VisibleForTesting
    public final long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
