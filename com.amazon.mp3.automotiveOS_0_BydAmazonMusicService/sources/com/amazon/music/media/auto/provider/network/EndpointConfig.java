package com.amazon.music.media.auto.provider.network;

import com.amazon.music.featuregating.configuration.Configuration;
import kotlin.Metadata;

/* JADX INFO: compiled from: EndpointConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0012\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¨\u0006\n"}, d2 = {"Lcom/amazon/music/media/auto/provider/network/EndpointConfig;", "", "Lcom/amazon/music/featuregating/configuration/Configuration;", "configuration", "", "getHomeTabEndpoint", "getLibraryEndpoint", "getPodcastEndpoint", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class EndpointConfig {
    public static final EndpointConfig INSTANCE = new EndpointConfig();

    private EndpointConfig() {
    }

    public final String getHomeTabEndpoint(Configuration configuration) {
        String strString;
        return (configuration == null || (strString = configuration.string("media_browser_home_tab_mesk_url", "https://na.mobilemesk.skill.music.a2z.com/api/auto/showHome")) == null) ? "https://na.mobilemesk.skill.music.a2z.com/api/auto/showHome" : strString;
    }

    public final String getLibraryEndpoint(Configuration configuration) {
        String strString;
        return (configuration == null || (strString = configuration.string("media_browser_library_tab_skyfire_url", "https://na.mobilemesk.skill.music.a2z.com/api/auto/showLibraryHome")) == null) ? "https://na.mobilemesk.skill.music.a2z.com/api/auto/showLibraryHome" : strString;
    }

    public final String getPodcastEndpoint(Configuration configuration) {
        String strString;
        return (configuration == null || (strString = configuration.string("media_browser_podcast_tab_mesk_url", "https://na.mobilemesk.skill.music.a2z.com/api/auto/showPodcast")) == null) ? "https://na.mobilemesk.skill.music.a2z.com/api/auto/showPodcast" : strString;
    }
}
