package com.amazon.mp3.card.prime;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.ArrayCardAdapter;
import com.amazon.mp3.library.adapter.AlbumTileAdapter;
import com.amazon.mp3.library.adapter.DefaultAlbumStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.GridTileHelper;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumCardAdapter extends ArrayCardAdapter<PrimeAlbum, ViewHolder> implements PrimeCardAdapter<PrimeAlbum> {
    private static final String TAG = "AlbumCardAdapter";
    private final StateProvider<PrimeAlbum> mAlbumStateProvider;
    private final ArtworkLoader mArtworkLoader;
    private boolean mGridView;

    public static final class ViewHolder extends ArrayCardAdapter.CardViewHolder {
        public PrimeAlbum mPrimeAlbum = null;
    }

    @Override // com.amazon.mp3.card.ArrayCardAdapter, com.amazon.mp3.card.CardAdapter
    public int getOverflowMenuButtonResId() {
        return R.id.overflow_button_open;
    }

    public AlbumCardAdapter(Context context, List<PrimeAlbum> data, boolean useGrid, boolean prefixNumbers, boolean showRecommendationReason) {
        super(context, data, useGrid ? R.layout.grid_tile : R.layout.album_tile_row_line, prefixNumbers, showRecommendationReason);
        this.mArtworkLoader = new ArtworkLoader.Builder(this.mContext).setAllowServerToScale(true).setImageProcessor(new RoundedCornerImageProcessor(this.mContext)).build();
        this.mAlbumStateProvider = new DefaultAlbumStateProvider(this.mContext);
        this.mGridView = useGrid;
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public void bindView(int position, final PrimeAlbum item, final View itemView) {
        TextView textView = (TextView) itemView.findViewById(R.id.title);
        if (this.mPrefixNumbers) {
            textView.setText((position + 1) + ". " + item.getTitle());
        } else {
            textView.setText(item.getTitle());
        }
        ((TextView) itemView.findViewById(R.id.subtitle)).setText(item.getArtist());
        this.mArtworkLoader.fetchInto(item, (ImageView) itemView.findViewById(R.id.artwork));
        if (this.mGridView) {
            ((ImageView) itemView.findViewById(R.id.PrimeSash)).setVisibility((item.isPrime() && Branding.shouldShowPrimeBranding()) ? 0 : 8);
        }
        if (this.mGridView) {
            GridTileHelper.setRecommendationVisibility(this.mShowRecommendationReason && item.getRecommendationReason() != null, itemView, item.getRecommendationReason());
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mPrimeAlbum = item;
        viewHolder.mPlaystateIcon = (PlayNotificationIcon) itemView.findViewById(R.id.PlaystateIcon);
        List<ContentEncoding> contentEncoding = item.getContentEncoding();
        ((BadgeView) itemView.findViewById(R.id.ContentEncodingBadgeTile)).populate(ContentEncodingDecoder.getEncodingBadgeModel(contentEncoding, this.mContext, this.mGridView ? 4 : 8));
        ((BadgeView) itemView.findViewById(R.id.ImmersiveBadgeTile)).populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(contentEncoding, this.mContext, this.mGridView ? 4 : 8));
        if (contentEncoding.contains(ContentEncoding.DOLBY_ATMOS) && contentEncoding.contains(ContentEncoding.SONY_360)) {
            ((BadgeView) itemView.findViewById(R.id.ImmersiveExtraBadgeTile)).populate(ContentEncodingDecoder.getExtraAlbumAtmosContentBadgeModel(contentEncoding, this.mContext, this.mGridView ? 4 : 8, false));
        }
        updatePlaystateIcon(item, viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.card.ArrayCardAdapter
    public boolean isItemPlaying(final ViewHolder holder) {
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem == null) {
            return false;
        }
        PrimeAlbum primeAlbum = holder.mPrimeAlbum;
        String asin = primeAlbum.getAsin();
        String title = primeAlbum.getTitle();
        if (asin != null && title != null) {
            return IdGenerator.generateAlbumId(asin, title) == MediaItemHelper.getAlbumId(currentMediaItem);
        }
        Log.warning(TAG, "Cannot generate album id to check if album is playing: " + primeAlbum.getAsin());
        return false;
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public StateProvider<PrimeAlbum> getStateProvider() {
        return this.mAlbumStateProvider;
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public void updateItemStatus(final PrimeAlbum item) {
        updateItemStatus(item, (StateProvider.Listener<PrimeAlbum>) null);
    }

    @Override // com.amazon.mp3.card.prime.PrimeCardAdapter
    public void updateItemStatus(final PrimeAlbum item, final StateProvider.Listener<PrimeAlbum> listener) {
        this.mAlbumStateProvider.requestState(item, new AlbumStateProviderListener(item, (AlbumTileAdapter.AlbumStateListener) listener));
    }

    public static class AlbumStateProviderListener implements StateProvider.Listener<PrimeAlbum> {
        private final AlbumTileAdapter.AlbumStateListener mAlbumStateListemer;
        private final PrimeAlbum mPrimeAlbum;

        public AlbumStateProviderListener(final PrimeAlbum album, final AlbumTileAdapter.AlbumStateListener listener) {
            this.mPrimeAlbum = album;
            this.mAlbumStateListemer = listener;
        }

        @Override // com.amazon.mp3.library.provider.StateProvider.Listener
        public void onStateChanged(final PrimeAlbum album, final int ownershipStatus, final int downloadStatus) {
            AlbumTileAdapter.AlbumStateListener albumStateListener = this.mAlbumStateListemer;
            if (albumStateListener != null) {
                albumStateListener.onAlbumStateUpdated(this.mPrimeAlbum);
            }
        }
    }

    public static class Builder {
        private final Context mContext;
        private final List<PrimeAlbum> mData;
        private boolean mUseGridView = false;
        private boolean mPrefixNumbers = false;
        private boolean mShowRecommendationreason = false;

        public Builder(Context context, List<PrimeAlbum> data) {
            this.mContext = context;
            this.mData = data;
        }

        public Builder useGrid() {
            this.mUseGridView = true;
            return this;
        }

        public Builder showRecommendationReason() {
            this.mShowRecommendationreason = true;
            return this;
        }

        public AlbumCardAdapter build() {
            return new AlbumCardAdapter(this.mContext, this.mData, this.mUseGridView, this.mPrefixNumbers, this.mShowRecommendationreason);
        }
    }
}
