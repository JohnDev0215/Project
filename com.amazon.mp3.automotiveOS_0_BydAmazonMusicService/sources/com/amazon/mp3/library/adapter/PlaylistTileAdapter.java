package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.view.PlayNotificationIcon;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistTileAdapter extends TileAdapter<PrimePlaylist, ViewHolder> {
    protected final ArtworkLoader mArtworkLoader;
    private final boolean mPrefixNumbers;
    private final StateProvider<PrimePlaylist> mStateProvider;

    public interface PlaylistStateListener {
        void onPlaylistStateUpdated(PrimePlaylist playlist);
    }

    public static final class ViewHolder extends TileAdapter.TileViewHolder {
        public TextView mTitleView = null;
        public TextView mDescriptionView = null;
        public ImageView mOverflowButton = null;
        public ImageView mArtwork = null;
        public TextView mReasonView = null;
        public PrimePlaylist mPrimePlaylist = null;
        public int mPosition = -1;
    }

    public PlaylistTileAdapter(Context context, TileAdapter.ItemButtonListener overflowButtonListener, int artworkSize, StateProvider<PrimePlaylist> stateProvider, boolean prefixNumbers) {
        super(context, 0, overflowButtonListener);
        this.mStateProvider = stateProvider;
        this.mArtworkLoader = new ArtworkLoader.Builder(getContext()).setArtworkSize(artworkSize).setImageProcessor(new RoundedCornerImageProcessor(getContext())).build();
        this.mPrefixNumbers = prefixNumbers;
    }

    private static class PlaylistStateProviderListener implements StateProvider.Listener<PrimePlaylist> {
        private final PlaylistStateListener mPlaylistStateListener;

        public PlaylistStateProviderListener(final PlaylistStateListener listener) {
            this.mPlaylistStateListener = listener;
        }

        @Override // com.amazon.mp3.library.provider.StateProvider.Listener
        public void onStateChanged(final PrimePlaylist playlist, final int ownershipStatus, final int downloadState) {
            PlaylistStateListener playlistStateListener = this.mPlaylistStateListener;
            if (playlistStateListener != null) {
                playlistStateListener.onPlaylistStateUpdated(playlist);
            }
        }
    }

    public void updatePlaylistStatus(final PrimePlaylist playlist, final PlaylistStateListener listener) {
        this.mStateProvider.requestState(playlist, new PlaylistStateProviderListener(listener));
    }

    public void updatePlaylistStatus(PrimePlaylist playlist) {
        updatePlaylistStatus(playlist, null);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (this.mShowEmptyLayout) {
            View view = this.mEmptyView;
            view.setTag(null);
            view.setPadding(0, getContext().getResources().getDimensionPixelOffset(R.dimen.album_tab_inside_padding), 0, 0);
            return view;
        }
        if (convertView == null || convertView.getTag() == null) {
            convertView = View.inflate(getContext(), R.layout.playlist_tile, null);
            viewHolder = new ViewHolder();
            viewHolder.mTitleView = (TextView) convertView.findViewById(R.id.title);
            viewHolder.mDescriptionView = (TextView) convertView.findViewById(R.id.description);
            viewHolder.mOverflowButton = (ImageView) convertView.findViewById(R.id.overflow_button_open);
            viewHolder.mArtwork = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.mReasonView = (TextView) convertView.findViewById(R.id.reason);
            viewHolder.mPlaystateIcon = (PlayNotificationIcon) convertView.findViewById(R.id.PlaystateIcon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mPosition = position;
        PrimePlaylist primePlaylist = (PrimePlaylist) getItem(position);
        if (viewHolder.mPrimePlaylist != primePlaylist) {
            viewHolder.mPrimePlaylist = primePlaylist;
            if (this.mPrefixNumbers) {
                viewHolder.mTitleView.setText((position + 1) + ". " + primePlaylist.getTitle());
            } else {
                viewHolder.mTitleView.setText(primePlaylist.getTitle());
            }
            viewHolder.mDescriptionView.setText(primePlaylist.getAuthorsText(getContext().getString(primePlaylist.getAuthorStringLocalizedId())));
            if (primePlaylist.getRecommendationReason() != null) {
                viewHolder.mReasonView.setText(primePlaylist.getRecommendationReason().getReason());
                viewHolder.mReasonView.setVisibility(0);
                viewHolder.mDescriptionView.setVisibility(8);
            } else {
                viewHolder.mReasonView.setVisibility(8);
                viewHolder.mDescriptionView.setVisibility(0);
            }
            updatePlaylistStatus(primePlaylist);
            this.mArtworkLoader.fetchInto(primePlaylist, viewHolder.mArtwork);
            tagButtons(position, viewHolder.mOverflowButton);
        }
        updatePlaystateIcon(viewHolder);
        notifyItemRendered(primePlaylist, position);
        return convertView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.library.adapter.TileAdapter
    public boolean isItemPlaying(final ViewHolder holder) {
        return MediaItemHelper.isCurrentlyPlayingCollection(MediaProvider.PrimePlaylists.getContentUri("cirrus", holder.mPrimePlaylist.getAsin()));
    }
}
