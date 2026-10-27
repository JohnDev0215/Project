package com.amazon.mp3.library.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.amazon.music.views.library.utils.ScalingType;
import com.amazon.music.views.library.views.FixedAspectImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaylistDetailAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0011\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002%&B+\u0012\"\b\u0002\u0010\u0017\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0015¢\u0006\u0004\b#\u0010$J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0002H\u0016J\u0006\u0010\u0014\u001a\u00020\bR1\u0010\u0017\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR(\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006'"}, d2 = {"Lcom/amazon/mp3/library/adapter/VideoPlaylistDetailAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/library/adapter/VideoPlaylistDetailAdapter$ViewHolder;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "primeTrack", "viewHolder", "", "loadAlbumImage", "", "position", "", "setClickedPosition", "Landroid/view/ViewGroup;", "viewGroup", "p1", "onCreateViewHolder", "getItemCount", "onBindViewHolder", "holder", "onViewRecycled", "getClickedPosition", "Lkotlin/Function2;", "", "onItemClick", "Lkotlin/jvm/functions/Function2;", "getOnItemClick", "()Lkotlin/jvm/functions/Function2;", "clickedPosition", "I", "data", "Ljava/util/List;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "Companion", "ViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoPlaylistDetailAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int clickedPosition;
    private List<? extends PrimeTrack> data;
    private final Function2<List<? extends PrimeTrack>, Integer, Unit> onItemClick;

    public VideoPlaylistDetailAdapter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VideoPlaylistDetailAdapter(Function2<? super List<? extends PrimeTrack>, ? super Integer, Unit> function2) {
        this.onItemClick = function2;
        this.data = new ArrayList();
    }

    public /* synthetic */ VideoPlaylistDetailAdapter(Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function2);
    }

    public final List<PrimeTrack> getData() {
        return this.data;
    }

    public final void setData(List<? extends PrimeTrack> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int p1) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.library_prime_playlist_track_row, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.getAddToLibrary().setVisibility(8);
        PrimeTrack primeTrack = this.data.get(position);
        viewHolder.getTitle().setText((position + 1) + ". " + ((Object) primeTrack.getTitle()));
        viewHolder.getArtistName().setText(primeTrack.getArtist());
        viewHolder.setEnabled(!(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && primeTrack.getIsExplicit()) && UserSubscriptionUtil.isHawkfire());
        loadAlbumImage(primeTrack, viewHolder);
        viewHolder.getTrackNumber().setVisibility(8);
        viewHolder.getTrackCountFrame().setVisibility(8);
        viewHolder.getOverflowButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlaylistDetailAdapter.m1225onBindViewHolder$lambda0(viewHolder, view);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return VideoPlaylistDetailAdapter.m1226onBindViewHolder$lambda1(this.f$0, viewHolder, view);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlaylistDetailAdapter.m1227onBindViewHolder$lambda2(this.f$0, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m1225onBindViewHolder$lambda0(ViewHolder viewHolder, View view) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        viewHolder.itemView.performLongClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-1, reason: not valid java name */
    public static final boolean m1226onBindViewHolder$lambda1(VideoPlaylistDetailAdapter this$0, ViewHolder viewHolder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        this$0.setClickedPosition(viewHolder.getPosition());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-2, reason: not valid java name */
    public static final void m1227onBindViewHolder$lambda2(VideoPlaylistDetailAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function2<List<? extends PrimeTrack>, Integer, Unit> function2 = this$0.onItemClick;
        if (function2 == null) {
            return;
        }
        function2.mo4invoke(this$0.data, Integer.valueOf(i));
    }

    private final void loadAlbumImage(PrimeTrack primeTrack, ViewHolder viewHolder) {
        String artworkUri = primeTrack.getArtworkUri();
        if (artworkUri == null) {
            return;
        }
        Picasso.get().load(artworkUri).transform(new ImageRoundCornerTransformation()).placeholder(R.drawable.videofallback).into(viewHolder.getArtwork());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.itemView.setOnLongClickListener(null);
        holder.itemView.setOnCreateContextMenuListener(null);
        super.onViewRecycled(holder);
    }

    public final int getClickedPosition() {
        return this.clickedPosition;
    }

    private final boolean setClickedPosition(int position) {
        this.clickedPosition = position;
        return true;
    }

    /* JADX INFO: compiled from: VideoPlaylistDetailAdapter.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J&\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010\u00042\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u000e\u0010/\u001a\u00020)2\u0006\u00100\u001a\u000201R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001b\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001c\u0010\tR\u001b\u0010\u001e\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u000b\u001a\u0004\b\u001f\u0010\u000fR\u001b\u0010!\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u000b\u001a\u0004\b\"\u0010#R\u001b\u0010%\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u000b\u001a\u0004\b&\u0010\u000f¨\u00062"}, d2 = {"Lcom/amazon/mp3/library/adapter/VideoPlaylistDetailAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnCreateContextMenuListener;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "addToLibrary", "Landroid/widget/ImageView;", "getAddToLibrary", "()Landroid/widget/ImageView;", "addToLibrary$delegate", "Lkotlin/Lazy;", "artistName", "Landroid/widget/TextView;", "getArtistName", "()Landroid/widget/TextView;", "artistName$delegate", "artwork", "Lcom/amazon/music/views/library/views/FixedAspectImageView;", "getArtwork", "()Lcom/amazon/music/views/library/views/FixedAspectImageView;", "artwork$delegate", "artworkContainer", "Landroidx/cardview/widget/CardView;", "getArtworkContainer", "()Landroidx/cardview/widget/CardView;", "artworkContainer$delegate", "overflowButton", "getOverflowButton", "overflowButton$delegate", "title", "getTitle", "title$delegate", "trackCountFrame", "getTrackCountFrame", "()Landroid/view/View;", "trackCountFrame$delegate", "trackNumber", "getTrackNumber", "trackNumber$delegate", "onCreateContextMenu", "", "contextMenu", "Landroid/view/ContextMenu;", "view", "contextMenuInfo", "Landroid/view/ContextMenu$ContextMenuInfo;", "setEnabled", "enabled", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        /* JADX INFO: renamed from: addToLibrary$delegate, reason: from kotlin metadata */
        private final Lazy addToLibrary;

        /* JADX INFO: renamed from: artistName$delegate, reason: from kotlin metadata */
        private final Lazy artistName;

        /* JADX INFO: renamed from: artwork$delegate, reason: from kotlin metadata */
        private final Lazy artwork;

        /* JADX INFO: renamed from: artworkContainer$delegate, reason: from kotlin metadata */
        private final Lazy artworkContainer;

        /* JADX INFO: renamed from: overflowButton$delegate, reason: from kotlin metadata */
        private final Lazy overflowButton;

        /* JADX INFO: renamed from: title$delegate, reason: from kotlin metadata */
        private final Lazy title;

        /* JADX INFO: renamed from: trackCountFrame$delegate, reason: from kotlin metadata */
        private final Lazy trackCountFrame;

        /* JADX INFO: renamed from: trackNumber$delegate, reason: from kotlin metadata */
        private final Lazy trackNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(final View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.title = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$title$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextView invoke() {
                    return (TextView) itemView.findViewById(R.id.TrackTitle);
                }
            });
            this.artistName = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$artistName$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextView invoke() {
                    return (TextView) itemView.findViewById(R.id.ArtistName);
                }
            });
            this.artwork = LazyKt__LazyJVMKt.lazy(new Function0<FixedAspectImageView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$artwork$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final FixedAspectImageView invoke() {
                    return (FixedAspectImageView) itemView.findViewById(R.id.AlbumArtwork);
                }
            });
            this.artworkContainer = LazyKt__LazyJVMKt.lazy(new Function0<CardView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$artworkContainer$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final CardView invoke() {
                    return (CardView) itemView.findViewById(R.id.AlbumArtworkView);
                }
            });
            this.trackNumber = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$trackNumber$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextView invoke() {
                    return (TextView) itemView.findViewById(R.id.TrackCount);
                }
            });
            this.trackCountFrame = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$trackCountFrame$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final View invoke() {
                    return itemView.findViewById(R.id.TrackCountFrame);
                }
            });
            this.overflowButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$overflowButton$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ImageView invoke() {
                    return (ImageView) itemView.findViewById(R.id.overflow_button_open);
                }
            });
            this.addToLibrary = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.library.adapter.VideoPlaylistDetailAdapter$ViewHolder$addToLibrary$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ImageView invoke() {
                    return (ImageView) itemView.findViewById(R.id.AddToLibraryButton);
                }
            });
            itemView.setOnCreateContextMenuListener(this);
            getArtwork().setScalingType(ScalingType.FixedHeight.INSTANCE);
            getArtwork().setScalingFactor(1 / itemView.getContext().getResources().getFraction(R.fraction.video_aspect_ratio, 1, 1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, AmazonApplication.getApplication().getApplicationContext().getResources().getDimensionPixelSize(R.dimen.margin_right_cardview), 0);
            getArtworkContainer().setLayoutParams(layoutParams);
        }

        public final TextView getTitle() {
            Object value = this.title.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-title>(...)");
            return (TextView) value;
        }

        public final TextView getArtistName() {
            Object value = this.artistName.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-artistName>(...)");
            return (TextView) value;
        }

        public final FixedAspectImageView getArtwork() {
            Object value = this.artwork.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-artwork>(...)");
            return (FixedAspectImageView) value;
        }

        public final CardView getArtworkContainer() {
            Object value = this.artworkContainer.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-artworkContainer>(...)");
            return (CardView) value;
        }

        public final TextView getTrackNumber() {
            Object value = this.trackNumber.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-trackNumber>(...)");
            return (TextView) value;
        }

        public final View getTrackCountFrame() {
            Object value = this.trackCountFrame.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-trackCountFrame>(...)");
            return (View) value;
        }

        public final ImageView getOverflowButton() {
            Object value = this.overflowButton.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-overflowButton>(...)");
            return (ImageView) value;
        }

        public final ImageView getAddToLibrary() {
            Object value = this.addToLibrary.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-addToLibrary>(...)");
            return (ImageView) value;
        }

        public final void setEnabled(boolean enabled) {
            this.itemView.setAlpha(enabled ? 1.0f : 0.3f);
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            new MenuInflater(view == null ? null : view.getContext()).inflate(R.menu.video_context, contextMenu);
            if (SequencerHelper.canModifyQueue()) {
                return;
            }
            if (contextMenu != null) {
                contextMenu.removeItem(R.id.MenuVideoAddToPlayqueue);
            }
            if (contextMenu == null) {
                return;
            }
            contextMenu.removeItem(R.id.MenuVideoPlayNext);
        }
    }
}
