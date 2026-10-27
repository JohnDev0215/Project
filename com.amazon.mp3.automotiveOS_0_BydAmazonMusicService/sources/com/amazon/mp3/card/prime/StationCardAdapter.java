package com.amazon.mp3.card.prime;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.ArrayCardAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.search.ArtworkLoader;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.mp3.util.GridTileHelper;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.view.PlayNotificationIcon;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class StationCardAdapter extends ArrayCardAdapter<Station, ViewHolder> {
    private final ArtworkLoader mArtworkLoader;
    private boolean mGridView;

    public static final class ViewHolder extends ArrayCardAdapter.CardViewHolder {
        public Station mStation;
        public ImageView mStationIcon;
    }

    @Override // com.amazon.mp3.card.ArrayCardAdapter, com.amazon.mp3.card.CardAdapter
    public int getOverflowMenuButtonResId() {
        return R.id.overflow_button_open;
    }

    public StationCardAdapter(Context context, List<Station> data, boolean useGridView, boolean showRecommendationReason) {
        super(context, data, useGridView ? R.layout.grid_tile : R.layout.prime_search_station_line, false, showRecommendationReason);
        this.mArtworkLoader = new ArtworkLoader.Builder(this.mContext).setAllowServerToScale(true).setArtworkSize(ArtworkSizeUtil.getGridArtworkSize((Activity) this.mContext)).setPlaceholderResId(R.drawable.station_placeholder).setImageProcessor(new RoundedCornerImageProcessor(this.mContext)).build();
        this.mGridView = useGridView;
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public void bindView(final int position, final Station item, final View itemView) {
        TextView textView;
        ImageView imageView;
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mStation = item;
        viewHolder.mStationIcon = (ImageView) itemView.findViewById(R.id.PlayIcon);
        viewHolder.mPlaystateIcon = (PlayNotificationIcon) itemView.findViewById(R.id.PlaystateIcon);
        if (this.mGridView) {
            Context context = itemView.getContext();
            textView = (TextView) itemView.findViewById(R.id.title);
            ((TextView) itemView.findViewById(R.id.subtitle)).setVisibility(8);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.detail_section);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            if (this.mShowRecommendationReason) {
                layoutParams.rightMargin = itemView.getResources().getDimensionPixelSize(R.dimen.grid_tile_text_indent);
            } else {
                layoutParams.height = itemView.getResources().getDimensionPixelSize(R.dimen.grid_tile_detail_height_stations);
            }
            relativeLayout.setLayoutParams(layoutParams);
            imageView = (ImageView) itemView.findViewById(R.id.artwork);
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.station_placeholder));
            viewHolder.mStationIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.station_outline));
            viewHolder.mPlaystateIcon.setVisibility(8);
            boolean z = false;
            viewHolder.mStationIcon.setVisibility(0);
            if (this.mShowRecommendationReason && item.getRecommendationReason() != null) {
                z = true;
            }
            GridTileHelper.setRecommendationVisibility(z, itemView, item.getRecommendationReason());
        } else {
            textView = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
        textView.setText(item.getTitle());
        this.mArtworkLoader.fetchInto(item, imageView);
        updatePlaystateIcon(item, viewHolder);
        checkContentEnabled(itemView, item.getIsAvailable());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.card.ArrayCardAdapter
    public boolean isItemPlaying(final ViewHolder holder) {
        boolean zIsCurrentlyPlayingCollection = MediaItemHelper.isCurrentlyPlayingCollection(MediaProvider.Station.getContentUri(holder.mStation.getKey()));
        if (zIsCurrentlyPlayingCollection) {
            holder.mStationIcon.setVisibility(8);
        } else {
            holder.mStationIcon.setVisibility(0);
        }
        return zIsCurrentlyPlayingCollection;
    }
}
