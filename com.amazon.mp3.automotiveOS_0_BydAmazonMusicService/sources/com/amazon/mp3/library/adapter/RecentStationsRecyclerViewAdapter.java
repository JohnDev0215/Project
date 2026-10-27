package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.GridTileHelper;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.uicontentview.ContentViewedListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentStationsRecyclerViewAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002%&B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0004J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0004H\u0016J\u0014\u0010\"\u001a\u00020\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00160$R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ViewHolder;", "artworkSize", "", "itemViewType", "overflowButtonListener", "Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ItemButtonListener;", "itemClickListener", "onCreateContextMenuListener", "Landroid/view/View$OnCreateContextMenuListener;", "(IILcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ItemButtonListener;Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ItemButtonListener;Landroid/view/View$OnCreateContextMenuListener;)V", "containerBlockRef", "", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "lock", "", "overflowClickListener", "Landroid/view/View$OnClickListener;", "stationList", "", "Lcom/amazon/mp3/search/model/Station;", "getItem", "position", "getItemCount", "getItemViewType", "onBindViewHolder", "", "viewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setStations", "collection", "", "ItemButtonListener", "ViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentStationsRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final int artworkSize;
    private String containerBlockRef;
    private ContentViewedListener contentViewedListener;
    private final ItemButtonListener itemClickListener;
    private final int itemViewType;
    private final Object lock;
    private final View.OnCreateContextMenuListener onCreateContextMenuListener;
    private final ItemButtonListener overflowButtonListener;
    private final View.OnClickListener overflowClickListener;
    private final List<Station> stationList;

    /* JADX INFO: compiled from: RecentStationsRecyclerViewAdapter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ItemButtonListener;", "", "onClick", "", "v", "Landroid/view/View;", "position", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ItemButtonListener {
        void onClick(View v, int position);
    }

    public RecentStationsRecyclerViewAdapter(int i, int i2, ItemButtonListener overflowButtonListener, ItemButtonListener itemClickListener, View.OnCreateContextMenuListener onCreateContextMenuListener) {
        Intrinsics.checkNotNullParameter(overflowButtonListener, "overflowButtonListener");
        Intrinsics.checkNotNullParameter(itemClickListener, "itemClickListener");
        Intrinsics.checkNotNullParameter(onCreateContextMenuListener, "onCreateContextMenuListener");
        this.artworkSize = i;
        this.itemViewType = i2;
        this.overflowButtonListener = overflowButtonListener;
        this.itemClickListener = itemClickListener;
        this.onCreateContextMenuListener = onCreateContextMenuListener;
        this.lock = new Object();
        this.overflowClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecentStationsRecyclerViewAdapter.m1221overflowClickListener$lambda0(this.f$0, view);
            }
        };
        this.stationList = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: overflowClickListener$lambda-0, reason: not valid java name */
    public static final void m1221overflowClickListener$lambda0(RecentStationsRecyclerViewAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object tag = view.getTag();
        if (tag == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        }
        int iIntValue = ((Integer) tag).intValue();
        ItemButtonListener itemButtonListener = this$0.overflowButtonListener;
        Intrinsics.checkNotNullExpressionValue(view, "view");
        itemButtonListener.onClick(view, iIntValue);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.onBind(this.stationList.get(position), position, this.artworkSize, this.containerBlockRef, getItemCount(), this.itemClickListener, this.overflowClickListener, this.contentViewedListener, this.onCreateContextMenuListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.stationList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.itemViewType;
    }

    public final void setStations(Collection<? extends Station> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        synchronized (this.lock) {
            this.stationList.clear();
            this.stationList.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public final Station getItem(int position) {
        return this.stationList.get(position);
    }

    /* JADX INFO: compiled from: RecentStationsRecyclerViewAdapter.kt */
    @Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002JT\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u0012\u0010!\u001a\u00020\u000f2\b\u0010\"\u001a\u0004\u0018\u00010\u0014H\u0002J\u0018\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "overflowButton", "Landroid/widget/ImageView;", "playIcon", "playNotificationIcon", "Lcom/amazon/mp3/view/PlayNotificationIcon;", "stationArtwork", "stationDescription", "Landroid/widget/TextView;", "stationName", "notifyItemRendered", "", "position", "", "itemCount", "containerBlockRef", "", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "onBind", "station", "Lcom/amazon/mp3/search/model/Station;", "artworkSize", "itemClickListener", "Lcom/amazon/mp3/library/adapter/RecentStationsRecyclerViewAdapter$ItemButtonListener;", "overflowClickListener", "Landroid/view/View$OnClickListener;", "onCreateContextMenuListener", "Landroid/view/View$OnCreateContextMenuListener;", "updatePlayStateIcon", "key", "updateViewTransparency", "view", "enabled", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView overflowButton;
        private final ImageView playIcon;
        private final PlayNotificationIcon playNotificationIcon;
        private final ImageView stationArtwork;
        private final TextView stationDescription;
        private final TextView stationName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.title);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.title)");
            TextView textView = (TextView) viewFindViewById;
            this.stationName = textView;
            View viewFindViewById2 = itemView.findViewById(R.id.subtitle);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.subtitle)");
            TextView textView2 = (TextView) viewFindViewById2;
            this.stationDescription = textView2;
            View viewFindViewById3 = itemView.findViewById(R.id.overflow_button_open);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.id.overflow_button_open)");
            this.overflowButton = (ImageView) viewFindViewById3;
            textView2.setVisibility(8);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.detail_section);
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.height = itemView.getResources().getDimensionPixelSize(R.dimen.grid_tile_detail_height_stations);
            relativeLayout.setLayoutParams(layoutParams2);
            View viewFindViewById4 = itemView.findViewById(R.id.artwork);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "itemView.findViewById(R.id.artwork)");
            ImageView imageView = (ImageView) viewFindViewById4;
            this.stationArtwork = imageView;
            Context context = itemView.getContext();
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.station_placeholder));
            View viewFindViewById5 = itemView.findViewById(R.id.PlayIcon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "itemView.findViewById(R.id.PlayIcon)");
            ImageView imageView2 = (ImageView) viewFindViewById5;
            this.playIcon = imageView2;
            imageView2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.station_outline));
            View viewFindViewById6 = itemView.findViewById(R.id.PlaystateIcon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "itemView.findViewById(R.id.PlaystateIcon)");
            this.playNotificationIcon = (PlayNotificationIcon) viewFindViewById6;
        }

        public final void onBind(Station station, final int position, int artworkSize, String containerBlockRef, int itemCount, final ItemButtonListener itemClickListener, View.OnClickListener overflowClickListener, ContentViewedListener contentViewedListener, View.OnCreateContextMenuListener onCreateContextMenuListener) {
            Intrinsics.checkNotNullParameter(station, "station");
            Intrinsics.checkNotNullParameter(itemClickListener, "itemClickListener");
            Intrinsics.checkNotNullParameter(overflowClickListener, "overflowClickListener");
            this.stationName.setText(station.getTitle());
            GridTileHelper.setRecommendationVisibility(false, this.itemView, station.getRecommendationReason());
            String artworkUri = station.getArtworkUri();
            if (!ImageUrlModifier.containsModifiers(artworkUri)) {
                artworkUri = ImageUrlModifier.with(artworkUri).scaleToLongest(artworkSize).toUrl();
            }
            BitmapUtil.getPicassoRequestCreator(artworkUri).fit().placeholder(R.drawable.station_placeholder).into(this.stationArtwork);
            if (!TextUtils.isEmpty(station.getSFASeed())) {
                this.overflowButton.setVisibility(8);
            } else {
                this.overflowButton.setOnClickListener(overflowClickListener);
                this.overflowButton.setTag(Integer.valueOf(position));
                this.stationArtwork.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter$ViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return RecentStationsRecyclerViewAdapter.ViewHolder.m1222onBind$lambda0(this.f$0, view);
                    }
                });
                if (onCreateContextMenuListener != null) {
                    this.overflowButton.setOnCreateContextMenuListener(onCreateContextMenuListener);
                }
            }
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter$ViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecentStationsRecyclerViewAdapter.ViewHolder.m1223onBind$lambda1(itemClickListener, position, view);
                }
            };
            this.stationArtwork.setOnClickListener(onClickListener);
            this.stationName.setOnClickListener(onClickListener);
            this.playIcon.setOnClickListener(onClickListener);
            this.playNotificationIcon.setOnClickListener(onClickListener);
            updatePlayStateIcon(station.getKey());
            View itemView = this.itemView;
            Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
            updateViewTransparency(itemView, station.getIsAvailable());
            notifyItemRendered(position, itemCount, containerBlockRef, contentViewedListener);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBind$lambda-0, reason: not valid java name */
        public static final boolean m1222onBind$lambda0(ViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            return this$0.overflowButton.performClick();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBind$lambda-1, reason: not valid java name */
        public static final void m1223onBind$lambda1(ItemButtonListener itemClickListener, int i, View v) {
            Intrinsics.checkNotNullParameter(itemClickListener, "$itemClickListener");
            Intrinsics.checkNotNullExpressionValue(v, "v");
            itemClickListener.onClick(v, i);
        }

        private final void updatePlayStateIcon(String key) {
            PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.NONE);
            if (Intrinsics.areEqual(MediaCollectionInfo.getMediaCollectionIdString(playbackController.getMediaCollectionInfo(), MediaCollectionId.Type.STATION_KEY, ""), key)) {
                this.playNotificationIcon.setVisibility(0);
                this.playNotificationIcon.setIsPlaying(playbackController.getPlayStatus().shouldBePlaying());
                this.playIcon.setVisibility(8);
            } else {
                this.playNotificationIcon.setVisibility(8);
                this.playIcon.setVisibility(0);
            }
        }

        private final void updateViewTransparency(View view, boolean enabled) {
            view.setAlpha(enabled ? 1.0f : 0.3f);
        }

        private final void notifyItemRendered(int position, int itemCount, String containerBlockRef, ContentViewedListener contentViewedListener) {
            if (contentViewedListener == null || containerBlockRef == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("blockRef", containerBlockRef);
            map.put("numItemsAccessible", String.valueOf(itemCount));
            contentViewedListener.onItemRenderedInSequence(containerBlockRef, position, map);
        }
    }
}
