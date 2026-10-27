package com.amazon.mp3.library.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.service.PlaybackService;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.GridTileHelper;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.contentEncoding.ContentEncoding;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumTileAdapter extends TileAdapter<PrimeAlbum, ViewHolder> {
    private final ArtworkLoader mArtworkLoader;
    private final boolean mGridView;
    private final boolean mPrefixNumbers;
    private final boolean mShouldShowPrimeBadging;
    private final boolean mShowRecommendationReason;
    private final StateProvider<PrimeAlbum> mStateProvider;

    public interface AlbumStateListener {
        void onAlbumStateUpdated(PrimeAlbum album);
    }

    public static final class ViewHolder extends TileAdapter.TileViewHolder {
        public TextView mAlbumTitle = null;
        public TextView mArtistName = null;
        public ImageView mOverflowButton = null;
        public ImageView mPrimeSash = null;
        public ImageView mAlbumArtwork = null;
        public PrimeAlbum mPrimeAlbum = null;
    }

    public AlbumTileAdapter(Context context, TileAdapter.ItemButtonListener overflowButtonListener, int artworkSize, StateProvider<PrimeAlbum> albumStateProvider, boolean useGridView, boolean prefixNumbers, boolean showRecommendationReason, boolean shouldShowPrimeBadging) {
        super(context, 0, overflowButtonListener);
        this.mArtworkLoader = new ArtworkLoader.Builder(getContext()).setAllowServerToScale(true).setArtworkSize(artworkSize).setImageProcessor(new RoundedCornerImageProcessor(getContext())).build();
        this.mStateProvider = albumStateProvider;
        this.mGridView = useGridView;
        this.mPrefixNumbers = prefixNumbers;
        this.mShowRecommendationReason = showRecommendationReason;
        this.mShouldShowPrimeBadging = shouldShowPrimeBadging;
    }

    private static class AlbumStateProviderListener implements StateProvider.Listener<PrimeAlbum> {
        private final AlbumStateListener mAlbumStateListener;
        private final PrimeAlbum mPrimeAlbum;

        public AlbumStateProviderListener(final PrimeAlbum album, final AlbumStateListener listener) {
            this.mPrimeAlbum = album;
            this.mAlbumStateListener = listener;
        }

        @Override // com.amazon.mp3.library.provider.StateProvider.Listener
        public void onStateChanged(final PrimeAlbum album, final int ownershipStatus, final int downloadState) {
            AlbumStateListener albumStateListener = this.mAlbumStateListener;
            if (albumStateListener != null) {
                albumStateListener.onAlbumStateUpdated(this.mPrimeAlbum);
            }
        }
    }

    public void updateAlbumStatus(final PrimeAlbum album, final AlbumStateListener listener) {
        this.mStateProvider.requestState(album, new AlbumStateProviderListener(album, listener));
    }

    public void updateAlbumStatus(PrimeAlbum album) {
        updateAlbumStatus(album, null);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (this.mShowEmptyLayout) {
            View view = this.mEmptyView;
            view.setTag(null);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            view.setLayoutParams(new AbsListView.LayoutParams(displayMetrics.widthPixels - (getContext().getResources().getDimensionPixelSize(R.dimen.album_tab_outside_padding) * 2), -2));
            return view;
        }
        if (convertView == null || convertView.getTag() == null) {
            convertView = View.inflate(getContext(), this.mGridView ? R.layout.grid_tile : R.layout.album_tile_row_line, null);
            viewHolder = new ViewHolder();
            viewHolder.mAlbumTitle = (TextView) convertView.findViewById(R.id.title);
            viewHolder.mArtistName = (TextView) convertView.findViewById(R.id.subtitle);
            viewHolder.mOverflowButton = (ImageView) convertView.findViewById(R.id.overflow_button_open);
            viewHolder.mAlbumArtwork = (ImageView) convertView.findViewById(R.id.artwork);
            viewHolder.mPlaystateIcon = (PlayNotificationIcon) convertView.findViewById(R.id.PlaystateIcon);
            if (this.mGridView) {
                viewHolder.mPrimeSash = (ImageView) convertView.findViewById(R.id.PrimeSash);
            }
            convertView.setTag(viewHolder);
            this.mCreatedViews.add(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PrimeAlbum primeAlbum = (PrimeAlbum) getItem(position);
        if (viewHolder.mPrimeAlbum != primeAlbum) {
            viewHolder.mPrimeAlbum = primeAlbum;
            if (this.mPrefixNumbers) {
                viewHolder.mAlbumTitle.setText((position + 1) + ". " + primeAlbum.getTitle());
            } else {
                viewHolder.mAlbumTitle.setText(primeAlbum.getTitle());
            }
            viewHolder.mArtistName.setText(primeAlbum.getArtist());
            if (this.mGridView) {
                updateGridItem(convertView, viewHolder);
            } else {
                FrameLayout frameLayout = (FrameLayout) convertView.findViewById(R.id.AlbumArtworkFrame);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
                layoutParams.leftMargin = convertView.getResources().getDimensionPixelSize(R.dimen.playlist_tile_art_indent);
                frameLayout.setLayoutParams(layoutParams);
            }
            List<ContentEncoding> contentEncoding = primeAlbum.getContentEncoding();
            BadgeView badgeView = (BadgeView) convertView.findViewById(R.id.ContentEncodingBadgeTile);
            BadgeView badgeView2 = (BadgeView) convertView.findViewById(R.id.ImmersiveBadgeTile);
            badgeView.populate(ContentEncodingDecoder.getEncodingBadgeModel(primeAlbum.getContentEncoding(), getContext(), this.mGridView ? 4 : 8));
            badgeView2.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(primeAlbum.getContentEncoding(), getContext(), this.mGridView ? 4 : 8));
            if (contentEncoding.contains(ContentEncoding.DOLBY_ATMOS) && contentEncoding.contains(ContentEncoding.SONY_360)) {
                ((BadgeView) convertView.findViewById(R.id.ImmersiveExtraBadgeTile)).populate(ContentEncodingDecoder.getExtraAlbumAtmosContentBadgeModel(contentEncoding, getContext(), this.mGridView ? 4 : 8, false));
            }
            updateAlbumStatus(primeAlbum);
            this.mArtworkLoader.fetchInto(primeAlbum, viewHolder.mAlbumArtwork);
            tagButtons(position, viewHolder.mOverflowButton);
        }
        updatePlaystateIcon(viewHolder);
        return convertView;
    }

    private void updateGridItem(View gridView, ViewHolder viewHolder) {
        PrimeAlbum primeAlbum = viewHolder.mPrimeAlbum;
        viewHolder.mPrimeSash.setVisibility(this.mShouldShowPrimeBadging && primeAlbum.isPrime() ? 0 : 8);
        GridTileHelper.setRecommendationVisibility(this.mShowRecommendationReason && primeAlbum.getRecommendationReason() != null, gridView, primeAlbum.getRecommendationReason());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.library.adapter.TileAdapter
    public boolean isItemPlaying(final ViewHolder holder) {
        Uri currentUri = MediaItemHelper.getCurrentUri();
        if (currentUri != null && PlaybackService.isCreated()) {
            PrimeAlbum primeAlbum = holder.mPrimeAlbum;
            if (IdGenerator.generateAlbumId(primeAlbum.getAsin(), primeAlbum.getTitle()) == MediaProvider.Albums.getAlbumId(currentUri)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.mp3.library.adapter.TileAdapter, com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, String id) {
        Iterator<View> it = this.mCreatedViews.iterator();
        while (it.hasNext()) {
            ViewHolder viewHolder = (ViewHolder) it.next().getTag();
            if (viewHolder.mPrimeAlbum.getArtworkId() != null && String.valueOf(viewHolder.mPrimeAlbum.getArtworkId()).equals(id)) {
                viewHolder.mAlbumArtwork.setImageDrawable(d);
            }
        }
    }
}
