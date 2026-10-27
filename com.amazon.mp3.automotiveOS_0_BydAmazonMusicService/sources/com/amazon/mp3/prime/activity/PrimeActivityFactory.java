package com.amazon.mp3.prime.activity;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.catalog.fragment.BrushPlaylistListFragment;
import com.amazon.mp3.catalog.fragment.ChartsDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.NewReleasesDetailBrushFragment;
import com.amazon.mp3.library.fragment.RecommendationsTabFragment;
import com.amazon.mp3.library.fragment.StationsFragment;
import com.amazon.mp3.prime.AlbumPaginatedFragment;
import com.amazon.mp3.prime.PlaylistPaginatedFragment;
import com.amazon.mp3.prime.TrackPaginatedFragment;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeActivityFactory {
    public static Intent getPrimeSectionIntent(Context context) {
        return getPrimeTabIntent(context, PrimeTabFragmentHost.class.getSimpleName());
    }

    public static Intent getStationsIntent(Context context) {
        return getPrimeTabIntent(context, StationsFragment.class.getSimpleName());
    }

    public static Intent getPlaylistsIntent(Context context) {
        return getPrimeTabIntent(context, BrushPlaylistListFragment.class.getSimpleName());
    }

    public static Intent getNewIntent(Context context) {
        return getPrimeTabIntent(context, NewReleasesDetailBrushFragment.class.getSimpleName());
    }

    public static Intent getPopularIntent(Context context) {
        return getPrimeTabIntent(context, ChartsDetailBrushFragment.class.getSimpleName());
    }

    public static Intent getRecommendedIntent(Context context) {
        return getPrimeTabIntent(context, RecommendationsTabFragment.class.getSimpleName());
    }

    public static Intent getHomeIntent(Context context) {
        return getPrimeTabIntent(context, BrushFragment.class.getSimpleName());
    }

    public static Intent getMoreAlbumsIntent(Context context, String rankTypeName) {
        return getPrimeDetailPageIntent(context, AlbumPaginatedFragment.class.getSimpleName(), rankTypeName);
    }

    public static Intent getMorePlaylistsIntent(Context context, String rankTypeName) {
        return getPrimeDetailPageIntent(context, PlaylistPaginatedFragment.class.getSimpleName(), rankTypeName);
    }

    public static Intent getMoreStationsIntent(Context context, String rankTypeName) {
        return getPrimeDetailPageIntent(context, StationPaginatedFragment.class.getSimpleName(), rankTypeName);
    }

    public static Intent getMoreTracksIntent(Context context, String rankTypeName) {
        return getPrimeDetailPageIntent(context, TrackPaginatedFragment.class.getSimpleName(), rankTypeName);
    }

    public static Intent getBrushDetailIntent(Context context) {
        return getPrimeTabIntent(context, BrushFragment.class.getSimpleName());
    }

    private static Intent getPrimeTabIntent(Context context, String fragmentName) {
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        startIntent.putExtra("com.amazon.mp3.fragment.extra", fragmentName);
        startIntent.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
        return startIntent;
    }

    private static Intent getPrimeDetailPageIntent(Context context, String fragmentName, String rankTypeName) {
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        startIntent.putExtra("com.amazon.mp3.fragment.extra", fragmentName);
        startIntent.putExtra("com.amazon.mp3.fragment.extra_rank_type", rankTypeName);
        return startIntent;
    }
}
