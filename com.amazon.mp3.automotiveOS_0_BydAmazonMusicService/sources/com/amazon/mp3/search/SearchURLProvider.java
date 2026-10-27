package com.amazon.mp3.search;

import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.marketplace.Marketplace;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class SearchURLProvider {
    private static final Map<Marketplace, URL> mURLMap = new HashMap(7);

    public static URL getURL() throws Exception {
        return EndpointsProvider.get().getTenzingEndpoint();
    }

    public static URL getSuggestionURL() throws Exception {
        return EndpointsProvider.get().getTenzingSuggestionEndpoint();
    }

    public static URL getInstantSearchURL() throws Exception {
        return EndpointsProvider.get().getTenzingInstantSearchEndpoint();
    }
}
