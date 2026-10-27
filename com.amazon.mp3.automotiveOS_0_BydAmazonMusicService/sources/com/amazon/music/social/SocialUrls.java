package com.amazon.music.social;

import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
final class SocialUrls {
    SocialUrls() {
    }

    public static URL create(Configuration configuration, ServiceEnvironment serviceEnvironment) {
        try {
            return new URL(serviceEnvironment.getUrl(configuration.getMarketPlace()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
