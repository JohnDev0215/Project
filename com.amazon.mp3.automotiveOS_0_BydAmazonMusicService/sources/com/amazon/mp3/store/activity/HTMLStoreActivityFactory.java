package com.amazon.mp3.store.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.store.html5.activity.StoreFragment;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class HTMLStoreActivityFactory {
    private static final String[] STORE_ROUTE_ROOT_BLACKLIST = {"purchase"};
    private static final String TAG = HTMLStoreActivityFactory.class.getSimpleName();

    public static void start(Context context) {
        context.startActivity(getStartIntent(context));
    }

    public static void start(Context context, Bundle extras) {
        context.startActivity(getStartIntent(context, extras));
    }

    public static Intent getStartIntent(Context context) {
        return getStartIntent(context, new Bundle());
    }

    public static Intent getStartIntent(Context context, Bundle extras) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, MusicHomeActivity.class);
        intent.addFlags(131072);
        intent.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        intent.putExtra("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE", extras);
        return intent;
    }

    private static Bundle bundleForBestsellingItemsOfType(Bundle extras, int browseType) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 5);
        bundle.putInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", browseType);
        if (extras != null) {
            bundle.putAll(extras);
        }
        return bundle;
    }

    public static Bundle bundleForDefaultStorePage() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 0);
        return bundle;
    }

    public static Bundle bundleForBestsellingTracks(Bundle extras) {
        return bundleForBestsellingItemsOfType(extras, 0);
    }

    public static Bundle bundleForBestsellingAlbums(Bundle extras) {
        return bundleForBestsellingItemsOfType(extras, 1);
    }

    public static Bundle bundleForTrackRecommendations() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 13);
        bundle.putInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 0);
        return bundle;
    }

    public static Bundle bundleForAlbumRecommendations() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 13);
        bundle.putInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 1);
        return bundle;
    }

    public static Bundle bundleForNewReleaseTracks() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 10);
        bundle.putBoolean("com.amazon.mp3.extra.NEWRELEASE", true);
        bundle.putInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 0);
        return bundle;
    }

    public static Bundle bundleForNewReleaseAlbums() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 10);
        bundle.putBoolean("com.amazon.mp3.extra.NEWRELEASE", true);
        bundle.putInt("com.amazon.mp3.extra.GENRE_BROWSE_TYPE", 1);
        return bundle;
    }

    public static Bundle bundleForRecommendedForYou() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 13);
        return bundle;
    }

    public static Bundle bundleForBestsellers() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 12);
        return bundle;
    }

    public static Bundle bundleForNewReleases() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 10);
        return bundle;
    }

    public static Bundle bundleForGenres() {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 9);
        return bundle;
    }

    public static Bundle bundleForAlbum(String albumAsin, String autoPlayTrackAsin) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 3);
        bundle.putString("com.amazon.mp3.extra.AUTO_PLAY_TRACK_ASIN", autoPlayTrackAsin);
        bundle.putString("com.amazon.mp3.extra.ALBUM_ASIN", albumAsin);
        return bundle;
    }

    public static Bundle bundleForTrack(String trackAsin, String albumAsin) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 7);
        bundle.putString("com.amazon.mp3.extra.TRACK_ASIN", trackAsin);
        bundle.putString("com.amazon.mp3.extra.ALBUM_ASIN", albumAsin);
        return bundle;
    }

    public static Bundle bundleForArtist(int browseType, String contribAsin, String artistAsin, String artistName) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 4);
        bundle.putString("com.amazon.mp3.extra.EXTRA_CONTRIBUTOR_ASIN", contribAsin);
        if (artistAsin != null) {
            bundle.putString("com.amazon.mp3.extra.EXTRA_ARIST_ASIN", artistAsin);
        }
        bundle.putString("com.amazon.mp3.extra.EXTRA_ARTIST_NAME", artistName);
        bundle.putInt("com.amazon.mp3.extra.BROWSE_TYPE", browseType);
        return bundle;
    }

    public static Bundle bundleForSearchString(String searchString) {
        return bundleForSearchString(searchString, 1);
    }

    public static Bundle bundleForSearchString(String searchString, int searchType) {
        return bundleForSearchString(searchString, searchType, null);
    }

    public static Bundle bundleForSearchString(String unsafeSearchString, int searchType, String matchCriteria) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 1);
        bundle.putString("com.amazon.mp3.extra.SEARCH_STRING", unsafeSearchString);
        bundle.putInt("com.amazon.mp3.extra.BROWSE_TYPE", searchType);
        bundle.putString("com.amazon.mp3.extra.MATCH_CRITERIA", matchCriteria);
        return bundle;
    }

    public static Intent intentForSearch(Context context, Bundle arguments) {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setClass(context, MusicHomeActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        intent.putExtra("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE", arguments);
        return intent;
    }

    public static Intent intentForMediaSearch(Context context, Intent mediaSearchIntent) {
        Intent intentMediaSearchToActionSearch = mediaSearchToActionSearch(mediaSearchIntent);
        if (intentMediaSearchToActionSearch != null) {
            intentMediaSearchToActionSearch.setClass(context, MusicHomeActivity.class);
            intentMediaSearchToActionSearch.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
            intentMediaSearchToActionSearch.putExtra("com.amazon.mp3.store.html5.LAUNCH_TYPE", 1);
        }
        return intentMediaSearchToActionSearch;
    }

    public static Bundle bundleForRoute(final String route) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 16);
        bundle.putString("com.amazon.mp3.extra.EXTRA_ROUTE_URL", route);
        return bundle;
    }

    public static void startWithAnywhereLocaleMismatchDialog(Activity parentActivity, String hostname) {
        start(parentActivity);
    }

    public static Bundle bundleForCampaignDetail(String campaignRoute) {
        Bundle bundle = new Bundle();
        if (!isBlackListedStoreRoute(campaignRoute)) {
            bundle.putInt("com.amazon.mp3.store.html5.LAUNCH_TYPE", 8);
            bundle.putString("com.amazon.mp3.extra.EXTRA_CAMPAIGN_ROUTE", campaignRoute);
        }
        return bundle;
    }

    private static String resolveQueryString(String preferred, String def) {
        return (preferred == null || preferred.length() == 0) ? def : preferred;
    }

    public static Intent mediaSearchToActionSearch(Intent intent) {
        if (!"android.intent.action.MEDIA_SEARCH".equals(intent.getAction())) {
            return null;
        }
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        String stringExtra = intent.getStringExtra(SearchIntents.EXTRA_QUERY);
        if (stringExtra == null) {
            stringExtra = "";
        }
        String strResolveQueryString = resolveQueryString(intent.getStringExtra("android.intent.extra.artist"), stringExtra);
        String strResolveQueryString2 = resolveQueryString(intent.getStringExtra("android.intent.extra.album"), stringExtra);
        String strResolveQueryString3 = resolveQueryString(intent.getStringExtra("android.intent.extra.title"), stringExtra);
        String stringExtra2 = intent.getStringExtra("android.intent.extra.focus");
        if ("vnd.android.cursor.item/artist".equals(stringExtra2)) {
            intent2.putExtra("com.amazon.mp3.extra.SEARCH_STRING", resolveQueryString(strResolveQueryString, stringExtra));
            intent2.putExtra("com.amazon.mp3.extra.BROWSE_TYPE", 1);
        } else if ("vnd.android.cursor.item/album".equals(stringExtra2)) {
            intent2.putExtra("com.amazon.mp3.extra.SEARCH_STRING", strResolveQueryString + " " + strResolveQueryString2);
            intent2.putExtra("com.amazon.mp3.extra.BROWSE_TYPE", 1);
        } else if ("audio/*".equals(stringExtra2)) {
            intent2.putExtra("com.amazon.mp3.extra.SEARCH_STRING", strResolveQueryString3);
            intent2.putExtra("com.amazon.mp3.extra.BROWSE_TYPE", 1);
        } else if (strResolveQueryString3.length() > 0) {
            intent2.putExtra("com.amazon.mp3.extra.SEARCH_STRING", strResolveQueryString + " " + strResolveQueryString3);
            intent2.putExtra("com.amazon.mp3.extra.BROWSE_TYPE", 1);
        } else {
            intent2.putExtra("com.amazon.mp3.extra.SEARCH_STRING", stringExtra);
            intent2.putExtra("com.amazon.mp3.extra.BROWSE_TYPE", 1);
        }
        return intent2;
    }

    public static boolean isBlackListedStoreRoute(final String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        }
        List<String> pathSegments = Uri.parse("amzn://mp3/" + path).getPathSegments();
        if (pathSegments.size() < 1) {
            return true;
        }
        String str = pathSegments.get(0);
        for (String str2 : STORE_ROUTE_ROOT_BLACKLIST) {
            if (str.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }
}
