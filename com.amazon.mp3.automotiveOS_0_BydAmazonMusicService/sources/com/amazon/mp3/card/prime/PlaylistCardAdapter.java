package com.amazon.mp3.card.prime;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.ArrayCardAdapter;
import com.amazon.mp3.library.adapter.DefaultPlaylistStateProvider;
import com.amazon.mp3.library.adapter.PlaylistTileAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.view.PlayNotificationIcon;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistCardAdapter extends ArrayCardAdapter<PrimePlaylist, ViewHolder> implements PrimeCardAdapter<PrimePlaylist> {
    private final ArtworkLoader mArtworkLoader;
    private final StateProvider<PrimePlaylist> mPlaylistStateProvider;

    public static final class ViewHolder extends ArrayCardAdapter.CardViewHolder {
        public PrimePlaylist mPrimePlaylist = null;
    }

    @Override // com.amazon.mp3.card.ArrayCardAdapter, com.amazon.mp3.card.CardAdapter
    public int getOverflowMenuButtonResId() {
        return R.id.overflow_button_open;
    }

    public PlaylistCardAdapter(Context context, List<PrimePlaylist> data, boolean prefixNumbers) {
        super(context, data, R.layout.prime_search_playlist_line, prefixNumbers, false);
        this.mArtworkLoader = new ArtworkLoader.Builder(this.mContext).setAllowServerToScale(true).setArtworkSize(context.getResources().getDimensionPixelSize(R.dimen.playlist_tile_art_size)).setImageProcessor(new RoundedCornerImageProcessor(this.mContext)).build();
        this.mPlaylistStateProvider = new DefaultPlaylistStateProvider(this.mContext);
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public StateProvider<PrimePlaylist> getStateProvider() {
        return this.mPlaylistStateProvider;
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public void updateItemStatus(PrimePlaylist item) {
        updateItemStatus(item, (StateProvider.Listener<PrimePlaylist>) null);
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public void updateItemStatus(PrimePlaylist item, StateProvider.Listener<PrimePlaylist> listener) {
        this.mPlaylistStateProvider.requestState(item, new PlaylistStateProviderListener(item, (PlaylistTileAdapter.PlaylistStateListener) listener));
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public void bindView(int position, PrimePlaylist item, View itemView) {
        TextView textView = (TextView) itemView.findViewById(R.id.title);
        if (this.mPrefixNumbers) {
            textView.setText((position + 1) + ". " + item.getTitle());
        } else {
            textView.setText(item.getTitle());
        }
        ((TextView) itemView.findViewById(R.id.description)).setText(item.getAuthorsText(this.mContext.getString(item.getAuthorStringLocalizedId())));
        this.mArtworkLoader.fetchInto(item, (ImageView) itemView.findViewById(R.id.imageView));
        itemView.setTag(item);
        updateItemStatus(item);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mPlaystateIcon = (PlayNotificationIcon) itemView.findViewById(R.id.PlaystateIcon);
        viewHolder.mPrimePlaylist = item;
        updatePlaystateIcon(item, viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.card.ArrayCardAdapter
    public boolean isItemPlaying(final ViewHolder holder) {
        return MediaItemHelper.isCurrentlyPlayingCollection(MediaProvider.PrimePlaylists.getContentUri("cirrus", holder.mPrimePlaylist.getAsin()));
    }

    public static class PlaylistStateProviderListener implements StateProvider.Listener<PrimePlaylist> {
        private final PlaylistTileAdapter.PlaylistStateListener mPlaylistStateListener;
        private final PrimePlaylist mPrimePlaylist;

        public PlaylistStateProviderListener(final PrimePlaylist playlist, final PlaylistTileAdapter.PlaylistStateListener listener) {
            this.mPrimePlaylist = playlist;
            this.mPlaylistStateListener = listener;
        }

        @Override // com.amazon.mp3.library.provider.StateProvider.Listener
        public void onStateChanged(final PrimePlaylist playlist, final int ownershipState, final int downloadStatus) {
            PlaylistTileAdapter.PlaylistStateListener playlistStateListener = this.mPlaylistStateListener;
            if (playlistStateListener != null) {
                playlistStateListener.onPlaylistStateUpdated(this.mPrimePlaylist);
            }
        }
    }
}
