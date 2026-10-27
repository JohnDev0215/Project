package com.amazon.mp3.external.api;

import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.external.api.uri.SyncAndPlayActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.google.android.gms.cast.HlsSegmentFormat;

/* JADX INFO: loaded from: classes3.dex */
public class AmznUriHandler {
    private static final String TAG = "AmznUriHandler";

    private static boolean isPrimeAccountRequired(int type) {
        if (type == 27 || type == 28) {
            return true;
        }
        switch (type) {
            case 39:
            case 40:
            case 41:
                return true;
            default:
                return false;
        }
    }

    private static boolean isPrimeMarketRequired(int type) {
        switch (type) {
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
                return true;
            default:
                return false;
        }
    }

    private static SyncAndPlayActivity.ContentAction toSyncAndPlayAction(final String action) {
        if ("view".equals(action)) {
            return SyncAndPlayActivity.ContentAction.VIEW;
        }
        if ("playNowPlaying".equals(action)) {
            return SyncAndPlayActivity.ContentAction.PLAY_NOW_PLAYING;
        }
        if ("playDetail".equals(action)) {
            return SyncAndPlayActivity.ContentAction.PLAY_DETAIL;
        }
        if (PageUriUtils.DO_PLAY_VALUE.equals(action)) {
            return SyncAndPlayActivity.ContentAction.PLAY;
        }
        return SyncAndPlayActivity.ContentAction.VIEW;
    }

    @Nullable
    public static Intent uriToIntent(Context context, Uri uri, boolean shouldCloudBeSupported, boolean isStoreSupported) {
        return uriToIntent(context, uri, shouldCloudBeSupported, isStoreSupported, AccountCredentialUtil.get(context).isSignedOut());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x039b  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent uriToIntent(final android.content.Context r16, android.net.Uri r17, boolean r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.external.api.AmznUriHandler.uriToIntent(android.content.Context, android.net.Uri, boolean, boolean, boolean):android.content.Intent");
    }

    private static Intent handlePrimeAlbumLink(final Context context, final String albumAsin, final String trackAsin, final String action) {
        PrimeCollectionTask.Task task;
        PrimeCollectionTask.Task task2;
        PrimeCollectionTask.OnUriReadyCallback onUriReadyCallback;
        Intent intentForContentUri = null;
        if (trackAsin != null && "playFullScreen".equals(action)) {
            task2 = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
            onUriReadyCallback = null;
            intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.NowPlaying.CONTENT_URI);
        } else {
            if (trackAsin != null) {
                task = PrimeCollectionTask.Task.PLAYBACK;
            } else {
                task = PrimeCollectionTask.Task.NONE;
            }
            task2 = task;
            onUriReadyCallback = new PrimeCollectionTask.OnUriReadyCallback() { // from class: com.amazon.mp3.external.api.AmznUriHandler.1
                @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
                public void onAlbumUriReady(Uri uri) {
                    Intent intentForContentUri2 = LibraryActivityFactory.getIntentForContentUri(context, uri);
                    if (intentForContentUri2 != null) {
                        intentForContentUri2.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
                        intentForContentUri2.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", albumAsin);
                        intentForContentUri2.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
                        context.startActivity(intentForContentUri2);
                    }
                }

                @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
                public void onError(Exception e) {
                    Log.error(AmznUriHandler.TAG, "Unable to find album uri", e);
                }
            };
        }
        PrimeCollectionTask.createPlaybackTask(context, albumAsin, trackAsin, PlaybackPageType.ALBUM_DETAIL, task2, new QueryAlbumByAsin(context), onUriReadyCallback).execute();
        return intentForContentUri;
    }

    private static boolean isUniversalUri(Uri uri) {
        String string;
        if (uri == null || (string = uri.toString()) == null) {
            return false;
        }
        return string.matches("^(http|https)://(www.amazon.|amazon.)(com|co[.]uk|de|ch|at|fr|it|es|jp|co[.]jp)[/]music-links[/].*$");
    }

    private static Uri standardizeUniversalUri(Uri uri) {
        String string;
        if (uri == null || (string = uri.toString()) == null) {
            return null;
        }
        return Uri.parse(string.replaceFirst("^(http|https)://(www.amazon.|amazon.)(com|co[.]uk|de|ch|at|fr|it|es|jp|co[.]jp)[/]music-links[/]", "http://universal/"));
    }

    public static final class AmznMp3UriMatcher {
        private static final UriMatcher sUriMatcher;

        static {
            UriMatcher uriMatcher = new UriMatcher(-1);
            sUriMatcher = uriMatcher;
            uriMatcher.addURI("library", "track/asin/*", 24);
            uriMatcher.addURI("library", "album/asin/*", 25);
            uriMatcher.addURI("library", "playlist/asin/*", 26);
            uriMatcher.addURI("store", "prime", 29);
            uriMatcher.addURI("store", "prime/popular", 29);
            uriMatcher.addURI("store", "prime/stations", 30);
            uriMatcher.addURI("store", "prime/playlists", 31);
            uriMatcher.addURI("store", "prime/new", 32);
            uriMatcher.addURI("store", "prime/popular", 33);
            uriMatcher.addURI("store", "prime/recommended", 34);
            uriMatcher.addURI("store", "prime/recommended/songs", 35);
            uriMatcher.addURI("store", "prime/recommended/albums", 36);
            uriMatcher.addURI("store", "prime/recommended/playlists", 37);
            uriMatcher.addURI("store", "prime/recommended/stations", 38);
            uriMatcher.addURI("store", "prime/songs", 33);
            uriMatcher.addURI("store", "prime/albums", 33);
            uriMatcher.addURI("store", "prime/playlists/asin/*", 27);
            uriMatcher.addURI("store", "prime/albums/asin/*", 28);
            uriMatcher.addURI("store", "prime/playlist/asin/*", 27);
            uriMatcher.addURI("store", "prime/album/asin/*", 28);
            uriMatcher.addURI("universal", "prime/albums/asin/*", 39);
            uriMatcher.addURI("universal", "prime/playlists/asin/*", 40);
            uriMatcher.addURI("universal", "prime/stations/*/*", 41);
            uriMatcher.addURI("universal", "prime/new", 32);
            uriMatcher.addURI("universal", "prime/recommended", 34);
        }

        public static int matchUri(Uri uri) {
            return sUriMatcher.match(uri);
        }
    }

    public static final class LegacyUriMatcher {
        private static final UriMatcher sUriMatcher;

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isStoreRequired(int uriType) {
            if (uriType == 22) {
                return false;
            }
            switch (uriType) {
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    return false;
                default:
                    return true;
            }
        }

        static {
            UriMatcher uriMatcher = new UriMatcher(-1);
            sUriMatcher = uriMatcher;
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/local", 1);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/local/songs", 2);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/local/artists", 21);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/local/albums", 3);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/local/playlists", 4);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/local/playlists/latestadditions", 7);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus", 1);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus/songs", 2);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus/artists", 21);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus/albums", 3);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus/playlists", 4);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus/playlists/latestpurchases", 6);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "library/cirrus/playlists/latestuploads", 5);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/", 22);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/featured", 8);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/featured/albums", 8);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/featured/songs", 9);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/bestsellers", 10);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/bestsellers/albums", 10);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/bestsellers/songs", 11);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/newreleases", 12);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/newreleases/albums", 12);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/newreleases/songs", 13);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/genres", 14);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/prime/popular", 29);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/claimcode/", 23);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "store/claimcode/*", 23);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "nowplaying", 15);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "nowplaying/queue", 16);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "downloads", 17);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "settings", 18);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "help", 19);
            uriMatcher.addURI(HlsSegmentFormat.MP3, "home", 20);
        }

        public static int matchUri(Uri uri) {
            return sUriMatcher.match(uri);
        }

        public static String getSource(Uri uri) {
            if (uri.getPathSegments().size() > 1) {
                return uri.getPathSegments().get(1);
            }
            return null;
        }
    }
}
