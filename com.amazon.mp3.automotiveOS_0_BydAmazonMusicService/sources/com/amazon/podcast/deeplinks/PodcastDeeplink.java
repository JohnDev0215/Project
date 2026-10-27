package com.amazon.podcast.deeplinks;

import android.net.Uri;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastDeeplink {
    private final boolean isBackStackRequired;
    private final Source source;
    private final String url;

    public enum Source {
        FIND,
        SEARCH_RESULTS,
        AOM,
        LIBRARY,
        CARMODE
    }

    public PodcastDeeplink(final String url, final boolean isBackStackRequired) {
        this(url, isBackStackRequired, null);
    }

    public PodcastDeeplink(final String url, final Source source) {
        this(url, false, source);
    }

    public PodcastDeeplink(final String url, final boolean isBackStackRequired, final Source source) {
        this.url = url;
        this.isBackStackRequired = isBackStackRequired;
        this.source = source;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isBackStackRequired() {
        return this.isBackStackRequired;
    }

    public String getSource() {
        Uri uri;
        Source source = this.source;
        if (source == null) {
            if (StringUtils.isBlank(this.url) || !this.url.contains("sourcePage") || (uri = Uri.parse(this.url)) == null) {
                return "";
            }
            String queryParameter = uri.getQueryParameter("sourcePage");
            return StringUtils.isBlank(queryParameter) ? "" : queryParameter;
        }
        return source.name();
    }

    public String getIngressPage() {
        Uri uri;
        if (StringUtils.isBlank(this.url) || !this.url.contains("ingressPage") || (uri = Uri.parse(this.url)) == null) {
            return null;
        }
        String queryParameter = uri.getQueryParameter("ingressPage");
        if (StringUtils.isBlank(queryParameter)) {
            return null;
        }
        return queryParameter;
    }
}
