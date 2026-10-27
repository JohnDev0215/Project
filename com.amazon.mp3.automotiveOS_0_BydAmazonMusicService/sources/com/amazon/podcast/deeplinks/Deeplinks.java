package com.amazon.podcast.deeplinks;

import UpsellInterface.v1_0.PlaybackMode;
import android.net.Uri;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.endpoints.MusicTerritory;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.redhoodPlus.SearchRedhoodPlusClientInterfaces;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes5.dex */
public final class Deeplinks {
    private static String OLD_PREVIEWS_PATH;
    private static String PREVIEWS_PATH;
    private static String PTC_PATH;
    private static final Pattern TITLE_PATTERN;
    private static final Map<String, String> localeToUrlMapping;

    static {
        HashMap map = new HashMap();
        localeToUrlMapping = map;
        PTC_PATH = "tastes";
        PREVIEWS_PATH = "previews";
        OLD_PREVIEWS_PATH = "bt";
        TITLE_PATTERN = Pattern.compile("([^\\w\\s])+");
        map.put(MusicTerritory.US.value, "https://music.amazon.com");
        map.put(MusicTerritory.UK.value, "https://music.amazon.co.uk");
        map.put(MusicTerritory.GERMANY.value, "https://music.amazon.de");
        map.put(MusicTerritory.FRANCE.value, "https://music.amazon.fr");
        map.put(MusicTerritory.ITALY.value, "https://music.amazon.it");
        map.put(MusicTerritory.SPAIN.value, "https://music.amazon.es");
        map.put(MusicTerritory.IRELAND.value, "https://music.amazon.com");
        map.put(MusicTerritory.BRAZIL.value, "https://music.amazon.com.br");
        map.put(MusicTerritory.MEXICO.value, "https://music.amazon.com.mx");
        map.put(MusicTerritory.JAPAN.value, "https://music.amazon.co.jp");
        map.put(MusicTerritory.AUSTRALIA.value, "https://music.amazon.com.au");
        map.put(MusicTerritory.NEW_ZEALAND.value, "https://music.amazon.com.au");
        map.put(MusicTerritory.INDIA.value, "https://music.amazon.in");
        map.put(MusicTerritory.CANADA.value, "https://music.amazon.ca");
        map.put(MusicTerritory.COLOMBIA.value, "https://music.amazon.com");
        map.put(MusicTerritory.CHILE.value, "https://music.amazon.com");
        map.put(MusicTerritory.UAE.value, "https://music.amazon.ae");
        map.put(MusicTerritory.SAUDIARABIA.value, "https://music.amazon.sa");
        map.put(MusicTerritory.EGYPT.value, "https://music.amazon.eg");
        map.put(MusicTerritory.ARGENTINA.value, "https://music.amazon.com");
        map.put(MusicTerritory.PERU.value, "https://music.amazon.com");
        map.put(MusicTerritory.ECUADOR.value, "https://music.amazon.com");
        map.put(MusicTerritory.COSTRICA.value, "https://music.amazon.com");
        map.put(MusicTerritory.BOLIVIA.value, "https://music.amazon.com");
        map.put(MusicTerritory.GUATEMALA.value, "https://music.amazon.com");
        map.put(MusicTerritory.NICARAGUA.value, "https://music.amazon.com");
        map.put(MusicTerritory.PARAGUAY.value, "https://music.amazon.com");
        map.put(MusicTerritory.ELSALADOR.value, "https://music.amazon.com");
        map.put(MusicTerritory.URUGUAY.value, "https://music.amazon.com");
        map.put(MusicTerritory.PANAMA.value, "https://music.amazon.com");
        map.put(MusicTerritory.DOMINICANREPUBLIC.value, "https://music.amazon.com");
        map.put(MusicTerritory.HONDURAS.value, "https://music.amazon.com");
    }

    public static String podcast(final String podcastId, final String title) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(getTitleForUrl(title)).build().uri().toString();
    }

    public static String podcastHomeForBrowseHome() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addQueryParameter("sourcePage", "browseHome").build().uri().toString();
    }

    public static String podcastForSearch(final String podcastId, final String title) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(getTitleForUrl(title)).addQueryParameter("sourcePage", "SEARCH_RESULTS").build().uri().toString();
    }

    public static String podcastForPlaybackFromSearchResults(final String podcastId, final String title, final List<String> contentAccess) {
        if (PlaybackMode.FULL_ACCESS.equals(SearchRedhoodPlusClientInterfaces.getPlaybackMode(contentAccess))) {
            return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(getTitleForUrl(title)).addEncodedQueryParameter("do", "featuredPlay").addEncodedQueryParameter("sourcePage", "SEARCH_RESULTS").build().uri().toString();
        }
        return podcastForSearch(podcastId, title);
    }

    public static String episodeForPlaybackFromSearchResults(final String podcastId, final String episodeId, final String podcastTitle, final String episodeTitle, final List<String> contentAccess) {
        if (PlaybackMode.FULL_ACCESS.equals(SearchRedhoodPlusClientInterfaces.getPlaybackMode(contentAccess))) {
            return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addPathSegment(episodeId).addPathSegment(getTitleForUrl(podcastTitle) + getTitleForUrl(episodeTitle)).addEncodedQueryParameter("do", PageUriUtils.DO_PLAY_VALUE).addEncodedQueryParameter("sourcePage", "SEARCH_RESULTS").build().uri().toString();
        }
        return episodeForSearch(podcastId, episodeId, podcastTitle, episodeTitle);
    }

    public static String episode(final String podcastId, final String episodeId, final String podcastTitle, final String episodeTitle) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addPathSegment(episodeId).addPathSegment(getTitleForUrl(podcastTitle) + getTitleForUrl(episodeTitle)).build().uri().toString();
    }

    public static String episodeForSearch(final String podcastId, final String episodeId, final String podcastTitle, final String episodeTitle) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addPathSegment(episodeId).addPathSegment(getTitleForUrl(podcastTitle) + getTitleForUrl(episodeTitle)).addQueryParameter("sourcePage", "SEARCH_RESULTS").build().uri().toString();
    }

    public static String episodeForShare(final String podcastId, final String episodeId, final String podcastTitle, final String episodeTitle) {
        return HttpUrl.parse(getBaseUrl()).newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addPathSegment(episodeId).addPathSegment(getTitleForUrl(podcastTitle) + getTitleForUrl(episodeTitle)).build().uri().toString();
    }

    public static String episodePlay(final String podcastId, final String episodeId, final String podcastTitle, final String episodeTitle) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addPathSegment(episodeId).addPathSegment(getTitleForUrl(podcastTitle) + getTitleForUrl(episodeTitle)).addEncodedQueryParameter("do", PageUriUtils.DO_PLAY_VALUE).build().uri().toString();
    }

    public static String savedEpisodePlay(final String episodeId) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment("library").addPathSegment(Environment.PLAYLIST_PATH).addPathSegment(episodeId).addQueryParameter("do", PageUriUtils.DO_PLAY_VALUE).build().uri().toString();
    }

    public static String heartbeatFrequencies() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("writeAndReportFrequencies").build().uri().toString();
    }

    public static String completed() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("library").addEncodedPathSegment("completed").build().uri().toString();
    }

    public static String latest() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("library").addEncodedPathSegment("latest").build().uri().toString();
    }

    public static String shows() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("library").addEncodedPathSegment("shows").build().uri().toString();
    }

    public static String bookmarks() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("library").addEncodedPathSegment("recently-played").build().uri().toString();
    }

    public static String playlist() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("library").addEncodedPathSegment(Environment.PLAYLIST_PATH).build().uri().toString();
    }

    private static String getTitleForUrl(final String title) {
        return TITLE_PATTERN.matcher(title.toUpperCase()).replaceAll("").replaceAll("(\\s)+", "-");
    }

    public static String download(final String episodeId) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("library").addEncodedPathSegment("downloads").addEncodedPathSegment(episodeId).build().uri().toString();
    }

    public static String jumpBackIn() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("jumpBackIn").build().uri().toString();
    }

    public static String top1RecommenderEpisodePlay(final String podcastId, final String episodeId, final String podcastTitle, final String episodeTitle) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addPathSegment(episodeId).addPathSegment(getTitleForUrl(podcastTitle) + getTitleForUrl(episodeTitle)).addEncodedQueryParameter("do", "featuredPlay").build().uri().toString();
    }

    public static String top1RecommenderShowPlay(final String podcastId, final String title) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addPathSegment(podcastId).addPathSegment(getTitleForUrl(title)).addEncodedQueryParameter("do", "featuredPlay").build().uri().toString();
    }

    public static String ptc() {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment(PTC_PATH).build().uri().toString();
    }

    public static String bites(final boolean includeFollowShow) {
        if (includeFollowShow) {
            return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment(PREVIEWS_PATH).addEncodedQueryParameter("follows", "true").build().uri().toString();
        }
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment(PREVIEWS_PATH).build().uri().toString();
    }

    public static String syncEpisodeMetadataDeeplink(final String episodeId) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT).addEncodedPathSegment("metadata").addEncodedPathSegment(episodeId).build().uri().toString();
    }

    public static String syncPodcastMetadataDeeplink(final String podcastId) {
        return HttpUrl.parse("https://music.amazon.com").newBuilder().addEncodedPathSegment(DeeplinksUtils.PODCASTS_PATH_SEGMENT).addEncodedPathSegment("metadata").addEncodedPathSegment(podcastId).build().uri().toString();
    }

    private static String getBaseUrl() {
        String musicTerritory = Podcast.getMusicTerritory();
        if (musicTerritory == null) {
            return "https://music.amazon.com";
        }
        Map<String, String> map = localeToUrlMapping;
        return map.containsKey(musicTerritory) ? map.get(musicTerritory) : "https://music.amazon.com";
    }

    public static String getEpisodeIdFromDeeplink(final String deeplink) {
        Uri uri;
        if (deeplink.isEmpty() || (uri = Uri.parse(deeplink)) == null) {
            return "";
        }
        List<String> pathSegments = uri.getPathSegments();
        for (int i = 0; i < pathSegments.size() - 1; i++) {
            if (pathSegments.get(i).equals(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT)) {
                return pathSegments.get(i + 1);
            }
            if (pathSegments.get(i).equals("downloads")) {
                return pathSegments.get(i + 1);
            }
        }
        return "";
    }

    public static String getPodcastIdFromDeeplink(final String deeplink) {
        Uri uri;
        return (deeplink.isEmpty() || (uri = Uri.parse(deeplink)) == null || uri.getPathSegments().size() < 2) ? "" : uri.getPathSegments().get(1);
    }

    public static String modifyUrlForFacetedNavigation(final String deeplink) {
        return (deeplink.isEmpty() || Uri.parse(deeplink) == null) ? "" : HttpUrl.parse(deeplink).newBuilder().addQueryParameter("ingressPage", "facetedNavigation").build().uri().toString();
    }

    public static boolean isPreviewsDeeplink(final String deeplink) {
        String path = URI.create(deeplink).getPath();
        return isPatternMatch("(?s)/podcasts/bt(/.*)?", path) || isPatternMatch("(?s)/podcasts/previews(/.*)?", path) || isPatternMatch("(?s)/podcasts/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})/episodes/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})/previews/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})(/.*)?", path);
    }

    public static boolean isPodcastDeeplink(final String deeplink) {
        return isPatternMatch("(?s)/podcasts/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})(/.*)?", getPath(deeplink));
    }

    public static boolean isEpisodeDeeplink(final String deeplink) {
        return isPatternMatch("(?s)/podcasts/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})/episodes/([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})(/.*)?", getPath(deeplink));
    }

    public static boolean isPodcastHomeDeeplink(final String deeplink) {
        return isPatternMatch("(?s)/podcasts", getPath(deeplink));
    }

    public static boolean isPodcastTasteCollectionDeeplink(final String deeplink) {
        return isPatternMatch("(?s)/podcasts/tastes(/.*)?", getPath(deeplink));
    }

    public static boolean isPodcastsCuratedPageDeeplink(final String deeplink) {
        String path = getPath(deeplink);
        return isPatternMatch("(?s)/podcasts/pages(/.*)?", path) || isPatternMatch("(?s)/podcasts/categories(/.*)?", path) || isPatternMatch("(?s)/podcasts/providers/(.*?)", path);
    }

    private static final boolean isPatternMatch(final String pattern, final String pathString) {
        return Pattern.compile(pattern).matcher(pathString).matches();
    }

    private static String getPath(final String deeplink) {
        try {
            URI uriCreate = URI.create(deeplink);
            return uriCreate == null ? "" : uriCreate.getPath();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String internalReportEpisodeDeeplink(final String id, final UiMetricAttributes$PageType pageType) {
        return String.format("/report?type=%s&id=%s&reportSource=%s", "PODCAST_EPISODE", id, pageType == UiMetricAttributes$PageType.PODCAST_NOW_PLAYING ? "NOW_PLAYING" : "PODCAST_EPISODE_OVERFLOW_MENU");
    }
}
