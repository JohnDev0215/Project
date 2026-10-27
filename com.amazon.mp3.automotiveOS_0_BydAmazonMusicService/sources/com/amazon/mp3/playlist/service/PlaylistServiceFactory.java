package com.amazon.mp3.playlist.service;

import android.content.Context;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.net.RequestInterceptorFactory;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistServiceFactory {
    public static PlaylistService create(final Context context) {
        try {
            return new PlaylistService(new Configuration(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor(), EndpointsProvider.get().getPlaylistEndpoint()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
