package com.amazon.music.recents;

import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
final class RecentServiceUrls {
    RecentServiceUrls() {
    }

    public static URL create(Configuration configuration, ServiceEnvironment serviceEnvironment) {
        try {
            return new URL(serviceEnvironment.getUrl(configuration.getMarketPlace()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
