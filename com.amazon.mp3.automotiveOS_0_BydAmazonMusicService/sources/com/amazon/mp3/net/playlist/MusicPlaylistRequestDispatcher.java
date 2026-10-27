package com.amazon.mp3.net.playlist;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class MusicPlaylistRequestDispatcher extends BaseRequestDispatcher {
    private static final String TAG = "MusicPlaylistRequestDispatcher";

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServiceName() {
        return "MusicPlaylistService";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServicePackage() {
        return "com.amazon.musicplaylist.model";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected EndPointURL getEndPointURL(ServiceApi serviceApi) {
        EndPointURL endPointURL = Environment.MUSIC_PLAYLIST_SERVICE.get();
        if (serviceApi != null) {
            endPointURL.appendPath(serviceApi.getPath());
        }
        return endPointURL;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getTag() {
        return TAG;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected JsonHttpClient getClient() {
        return new MusicPlaylistHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject requestBody) {
        return createCoralRpcRequest(requestType, requestBody);
    }
}
