package com.amazon.music.media.auto.provider.network;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.CredentialStorage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONObject;

/* JADX INFO: compiled from: HomeTabRequestProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/media/auto/provider/network/HomeTabRequestProvider;", "Lcom/amazon/music/media/auto/provider/network/DefaultMESKRequestProvider;", "credentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "(Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;)V", "buildBody", "Lokhttp3/RequestBody;", "meskRequestBody", "Lcom/amazon/music/media/auto/provider/network/MESKRequestBody;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HomeTabRequestProvider extends DefaultMESKRequestProvider {
    private static final String TAG = HomeTabRequestProvider.class.getSimpleName();
    private final CredentialStorage.Credentials credentials;

    public HomeTabRequestProvider(CredentialStorage.Credentials credentials) {
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        this.credentials = credentials;
    }

    @Override // com.amazon.music.media.auto.provider.network.DefaultMESKRequestProvider, com.amazon.music.media.auto.provider.network.MESKRequestProvider
    public RequestBody buildBody(MESKRequestBody meskRequestBody) throws Exception {
        Intrinsics.checkNotNullParameter(meskRequestBody, "meskRequestBody");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", meskRequestBody.getId());
            jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, this.credentials.getMusicTerritory());
            jSONObject.put(Splash.PARAMS_LOCALE, this.credentials.getLocale());
            jSONObject.put("requestedContent", this.credentials.getContentType());
            jSONObject.put("nextToken", JSONObject.NULL);
            MediaType mediaType = MediaType.INSTANCE.get("application/json; charset=utf-8");
            RequestBody.Companion companion = RequestBody.INSTANCE;
            String string = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(string, "bodyObject.toString()");
            return companion.create(string, mediaType);
        } catch (Exception e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Failed to build request body", e);
            throw e;
        }
    }
}
