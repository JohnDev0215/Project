package com.amazon.music.curate.data;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.curate.endpoint.Endpoint;
import com.amazon.music.curate.endpoint.Realm;
import com.amazon.music.curate.extension.UserResultKt;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.nimblymusicservice.GetRecentTrackActivityCall;
import com.amazon.nimblymusicservice.GetRecentTrackActivityRequest;
import com.amazon.nimblymusicservice.GetRecentTrackActivityRequestSerializer;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponse;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: RecentsServiceApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\b\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/curate/data/RecentsServiceApi;", "", "requestInterceptor", "Lcom/amazon/hermes/RequestInterceptor;", "(Lcom/amazon/hermes/RequestInterceptor;)V", "MAPPER", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "fetchRecents", "Lcom/amazon/nimblymusicservice/GetRecentTrackActivityResponse;", "pageToken", "", Splash.PARAMS_DEVICE_TYPE, Splash.PARAMS_DEVICE_ID, "recentUrl", "Ljava/net/URL;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsServiceApi {
    private static final Logger LOG;
    private final ObjectMapper MAPPER;
    private final RequestInterceptor requestInterceptor;

    public RecentsServiceApi(RequestInterceptor requestInterceptor) {
        Intrinsics.checkNotNullParameter(requestInterceptor, "requestInterceptor");
        this.requestInterceptor = requestInterceptor;
        ObjectMapper objectMapper = new ObjectMapper();
        this.MAPPER = objectMapper;
        GetRecentTrackActivityRequestSerializer.register(objectMapper);
        GetRecentTrackActivityResponseDeserializer.register(objectMapper);
    }

    static {
        Logger logger = LoggerFactory.getLogger(RecentsServiceApi.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(RecentsService…i::class.java.simpleName)");
        LOG = logger;
    }

    public static /* synthetic */ GetRecentTrackActivityResponse fetchRecents$default(RecentsServiceApi recentsServiceApi, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return recentsServiceApi.fetchRecents(str, str2, str3);
    }

    public final GetRecentTrackActivityResponse fetchRecents(String pageToken, String deviceType, String deviceId) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        LOG.debug("Fetching recents");
        GetRecentTrackActivityRequest getRecentTrackActivityRequest = new GetRecentTrackActivityRequest();
        getRecentTrackActivityRequest.setDeviceId(deviceId);
        getRecentTrackActivityRequest.setDeviceType(deviceType);
        getRecentTrackActivityRequest.setPageToken(pageToken);
        AccountManager accountManager = AccountManagerSingleton.get();
        Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
        Object objUserResult = UserResultKt.userResult(accountManager);
        if (Result.m2816isFailureimpl(objUserResult)) {
            objUserResult = null;
        }
        User user = (User) objUserResult;
        getRecentTrackActivityRequest.setMusicTerritory(user != null ? user.getMusicTerritoryOfResidence() : null);
        GetRecentTrackActivityResponse getRecentTrackActivityResponseExecute = new GetRecentTrackActivityCall(recentUrl(), getRecentTrackActivityRequest, this.requestInterceptor).execute(this.MAPPER);
        Intrinsics.checkNotNullExpressionValue(getRecentTrackActivityResponseExecute, "call.execute(MAPPER)");
        return getRecentTrackActivityResponseExecute;
    }

    private final URL recentUrl() {
        Endpoint.Companion companion = Endpoint.INSTANCE;
        Realm.Companion companion2 = Realm.INSTANCE;
        AccountManager accountManager = AccountManagerSingleton.get();
        Intrinsics.checkNotNullExpressionValue(accountManager, "get()");
        Object objUserResult = UserResultKt.userResult(accountManager);
        if (Result.m2816isFailureimpl(objUserResult)) {
            objUserResult = null;
        }
        User user = (User) objUserResult;
        Marketplace libraryHomeMarketplace = user != null ? user.getLibraryHomeMarketplace() : null;
        if (libraryHomeMarketplace == null) {
            libraryHomeMarketplace = Marketplace.USA;
        }
        String region = libraryHomeMarketplace.getRegion();
        Intrinsics.checkNotNullExpressionValue(region, "AccountManagerSingleton.…: Marketplace.USA).region");
        return new URL(companion.getRecentsServiceEndpoint(companion2.fromString(region)));
    }
}
