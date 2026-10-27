package com.amazon.mp3.library.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.GridTileHelper;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.amazon.music.uicontentview.ContentViewedListener;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class StationsAdapter extends ArrayAdapter<Station> {
    private final int mArtworkSize;
    private String mContainerBlockRef;
    private ContentViewedListener mContentViewedListener;
    protected View mEmptyView;
    protected final TileAdapter.ItemButtonListener mOverflowButtonListener;
    private final View.OnClickListener mOverflowClickListener;
    protected boolean mShowEmptyLayout;
    private boolean mShowRecommendationReason;

    public static final class ViewHolder {
        public ImageView mOverflowButton;
        public ImageView mPlayIcon;
        public PlayNotificationIcon mPlayNotificationIcon;
        public Station mStation;
        public ImageView mStationArtwork;
        public TextView mStationDescription;
        public TextView mStationName;
    }

    protected int getItemLayoutResourceId() {
        return R.layout.grid_tile;
    }

    public StationsAdapter(final Context context, int artworkSize, final boolean showRecommendationReason, TileAdapter.ItemButtonListener overflowButtonListener) {
        super(context, R.layout.grid_tile);
        this.mOverflowClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.StationsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StationsAdapter.this.mOverflowButtonListener.onClick(view, ((Integer) view.getTag()).intValue());
            }
        };
        this.mArtworkSize = artworkSize;
        this.mShowRecommendationReason = showRecommendationReason;
        this.mOverflowButtonListener = overflowButtonListener;
    }

    public StationsAdapter(final Context context, int layoutId) {
        super(context, 0);
        this.mOverflowClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.StationsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StationsAdapter.this.mOverflowButtonListener.onClick(view, ((Integer) view.getTag()).intValue());
            }
        };
        this.mArtworkSize = 0;
        this.mOverflowButtonListener = null;
        this.mShowEmptyLayout = true;
        this.mEmptyView = View.inflate(getContext(), layoutId, null);
    }

    public StationsAdapter(final Context context, final View emptyView) {
        super(context, 0);
        this.mOverflowClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.StationsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                StationsAdapter.this.mOverflowButtonListener.onClick(view, ((Integer) view.getTag()).intValue());
            }
        };
        this.mArtworkSize = 0;
        this.mOverflowButtonListener = null;
        this.mShowEmptyLayout = true;
        this.mEmptyView = emptyView;
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
            Context context = getContext();
            View viewInflate = View.inflate(context, getItemLayoutResourceId(), null);
            ViewHolder viewHolder2 = new ViewHolder();
            viewHolder2.mStationName = (TextView) viewInflate.findViewById(R.id.title);
            viewHolder2.mStationDescription = (TextView) viewInflate.findViewById(R.id.subtitle);
            viewHolder2.mOverflowButton = (ImageView) viewInflate.findViewById(R.id.overflow_button_open);
            viewHolder2.mStationDescription.setVisibility(8);
            viewHolder2.mStationName.setEllipsize(TextUtils.TruncateAt.END);
            RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.detail_section);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            if (this.mShowRecommendationReason) {
                layoutParams.rightMargin = viewInflate.getResources().getDimensionPixelSize(R.dimen.grid_tile_text_indent);
            } else {
                layoutParams.height = viewInflate.getResources().getDimensionPixelSize(R.dimen.grid_tile_detail_height_stations);
            }
            relativeLayout.setLayoutParams(layoutParams);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.artwork);
            viewHolder2.mStationArtwork = imageView;
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.station_placeholder));
            ImageView imageView2 = (ImageView) viewInflate.findViewById(R.id.PlayIcon);
            viewHolder2.mPlayIcon = imageView2;
            imageView2.setImageDrawable(context.getResources().getDrawable(R.drawable.station_outline));
            viewHolder2.mPlayNotificationIcon = (PlayNotificationIcon) viewInflate.findViewById(R.id.PlaystateIcon);
            viewInflate.setTag(viewHolder2);
            convertView = viewInflate;
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Station station = (Station) getItem(position);
        if (viewHolder.mStation != station) {
            viewHolder.mStation = station;
            viewHolder.mStationName.setText(station.getTitle());
            GridTileHelper.setRecommendationVisibility(this.mShowRecommendationReason && station.getRecommendationReason() != null, convertView, station.getRecommendationReason());
            String artworkUri = station.getArtworkUri();
            if (!ImageUrlModifier.containsModifiers(artworkUri)) {
                artworkUri = ImageUrlModifier.with(artworkUri).scaleToLongest(this.mArtworkSize).toUrl();
            }
            BitmapUtil.getPicassoRequestCreator(artworkUri).fit().placeholder(R.drawable.station_placeholder).transform(new ImageRoundCornerTransformation()).into(viewHolder.mStationArtwork);
        }
        viewHolder.mOverflowButton.setOnClickListener(this.mOverflowClickListener);
        viewHolder.mOverflowButton.setTag(Integer.valueOf(position));
        updatePlaystateIcon(viewHolder);
        checkContentEnabled(convertView, station.getIsAvailable());
        notifyItemRendered(position);
        return convertView;
    }

    private void notifyItemRendered(final int position) {
        if (this.mContentViewedListener == null || this.mContainerBlockRef == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("blockRef", this.mContainerBlockRef);
        map.put("numItemsAccessible", Integer.toString(getCount()));
        this.mContentViewedListener.onItemRenderedInSequence(this.mContainerBlockRef, position, map);
    }

    private void updatePlaystateIcon(ViewHolder viewHolder) {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.NONE);
        if (MediaCollectionInfo.getMediaCollectionIdString(controller.getMediaCollectionInfo(), MediaCollectionId.Type.STATION_KEY, "").equals(viewHolder.mStation.getKey())) {
            viewHolder.mPlayNotificationIcon.setVisibility(0);
            viewHolder.mPlayNotificationIcon.setIsPlaying(controller.getPlayStatus().shouldBePlaying());
            viewHolder.mPlayIcon.setVisibility(8);
        } else {
            viewHolder.mPlayNotificationIcon.setVisibility(8);
            viewHolder.mPlayIcon.setVisibility(0);
        }
    }

    public void checkContentEnabled(final View view, final boolean enabled) {
        ViewCompat.setLayerType(view, 2, null);
        if (enabled) {
            view.setAlpha(1.0f);
        } else {
            view.setAlpha(0.3f);
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        if (this.mShowEmptyLayout) {
            return 1;
        }
        return super.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        if (this.mShowEmptyLayout) {
            return false;
        }
        return super.isEnabled(position);
    }

    public boolean isEmptyLayoutShowing() {
        return this.mShowEmptyLayout;
    }
}
