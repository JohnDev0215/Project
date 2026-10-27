package com.amazon.music.connect.feed;

import com.amazon.music.connect.IdentityProvider;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class FeedService {
    private final String feedApiEndpoint;
    private final String feedVersion;
    public final IdentityProvider identityProvider;
    private final MediaType mediaType = MediaType.parse("application/json");
    private final OkHttpClient httpClient = UnifiedOkHttpClient.newClient();

    public FeedService(IdentityProvider identityProvider, String str, String str2) {
        this.identityProvider = identityProvider;
        this.feedVersion = str == null ? "2" : str;
        this.feedApiEndpoint = str2;
    }

    public ArrayList<FeedItem> getFeedItems(int i, int i2) throws Exception {
        String token = this.identityProvider.getToken();
        String customerId = this.identityProvider.getCustomerId();
        String deviceId = this.identityProvider.getDeviceId();
        String deviceType = this.identityProvider.getDeviceType();
        String string = Locale.getDefault().toString();
        String str = this.feedApiEndpoint;
        if (token == null || customerId == null || deviceId == null || deviceType == null || str == null) {
            throw new Exception("Missing required request information");
        }
        HttpUrl httpUrl = HttpUrl.parse((((((str + "?limit=" + i) + "&offset=" + i2) + "&customerId=" + customerId) + "&scoreVersion=1") + "&feedVersion=" + this.feedVersion) + "&locale=" + string);
        RequestBody.create(this.mediaType, new JSONObject().toString());
        return new FeedServiceResponse(FirebasePerfOkHttpClient.execute(this.httpClient.newCall(new Request.Builder().url(httpUrl).get().header("x-amz-access-token", token).header("x-amz-device-type", deviceType).header("x-amz-device-id", deviceId).build())).body().string()).getFeedItems();
    }
}
