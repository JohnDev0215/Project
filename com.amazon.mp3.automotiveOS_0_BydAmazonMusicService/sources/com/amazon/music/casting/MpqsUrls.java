package com.amazon.music.casting;

import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
final class MpqsUrls {
    MpqsUrls() {
    }

    public static URL create(Configuration configuration, ServiceEnvironment serviceEnvironment) {
        try {
            return new URL(serviceEnvironment.getUrl(configuration.getMarketplace()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
