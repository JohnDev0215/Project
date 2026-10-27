package com.amazon.mp3.card;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.PrimeCard;
import com.amazon.mp3.card.prime.StationCardAdapter;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.search.model.Station;

/* JADX INFO: loaded from: classes3.dex */
public class CardFactory {
    @NonNull
    public Card<Station, StationCardAdapter> buildStationsCard(final Context context) {
        Resources resources = context.getResources();
        return new Card<>(context, buildHorizontalTileGridCardConfiguration(resources, resources.getString(R.string.dmusic_recommended_prime_stations_card), null));
    }

    @NonNull
    public PrimeCard<PrimePlaylist> buildPlaylistsCard(final Context context) {
        Resources resources = context.getResources();
        return new PrimeCard<>(context, buildVerticalTileGroupCardConfiguration(resources, resources.getString(R.string.dmusic_recommended_prime_playlists_card), null));
    }

    @NonNull
    public PrimeCard<PrimeAlbum> buildAlbumsCard(final Context context) {
        Resources resources = context.getResources();
        return new PrimeCard<>(context, buildHorizontalTileGridCardConfiguration(resources, resources.getString(R.string.dmusic_recommended_prime_albums_card), null));
    }

    @NonNull
    public PrimeCard<PrimeAlbum> buildAlbumsUpsellCard(Context context) {
        Resources resources = context.getResources();
        return new PrimeCard<>(context, buildHorizontalTileGridCardConfiguration(resources, resources.getString(R.string.dmusic_growth_browse_home_album_upsell_title), resources.getString(R.string.dmusic_growth_browse_home_album_upsell_subtitle)));
    }

    @NonNull
    public PrimeCard<PrimeTrack> buildTracksCard(Context context) {
        Resources resources = context.getResources();
        return new PrimeCard<>(context, buildVerticalTileGroupCardConfiguration(resources, resources.getString(R.string.dmusic_recommended_prime_songs_card), null));
    }

    private CardStyle getDefaultCardStyle(final Resources resources) {
        return new CardStyle(resources.getDimensionPixelSize(R.dimen.prime_browse_card_margin_bottom), resources.getDimensionPixelSize(R.dimen.prime_card_horiz_padding));
    }

    private CardConfiguration buildVerticalTileGroupCardConfiguration(final Resources resources, final String headerText, final String subHeaderText) {
        return new CardConfiguration(headerText, subHeaderText, new CardLayout(CardLayoutType.LINEAR), getDefaultCardStyle(resources));
    }

    private CardConfiguration buildHorizontalTileGridCardConfiguration(Resources resources, String headerText, String subHeaderText) {
        return new CardConfiguration(headerText, subHeaderText, new GridCardLayout(resources.getInteger(R.integer.grid_tile_num_columns), resources.getDimensionPixelSize(R.dimen.prime_card_horiz_padding)), getDefaultCardStyle(resources));
    }
}
