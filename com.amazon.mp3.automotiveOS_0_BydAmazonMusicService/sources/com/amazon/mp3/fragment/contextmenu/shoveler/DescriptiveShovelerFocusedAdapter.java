package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.fragment.contextmenu.SeeAllPlaylistItem;
import com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.GridStyle;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: DescriptiveShovelerFocusedAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002#$B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0013H\u0016J\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0005J\u0016\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0013J\u001e\u0010 \u001a\u00020\u000e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\"2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder;", "()V", "onClickListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$OnPlaylistClickListener;", "playlists", "", "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "subscription", "Lrx/Subscription;", "addNewPlaylistItem", "", "context", "Landroid/content/Context;", "addSeeAllPlaylists", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnPlaylistClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "updateActiveItem", "oldPosition", "newPosition", "updatePlaylists", "newPlaylists", "", "OnPlaylistClickListener", "PlaylistShovelerViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveShovelerFocusedAdapter extends RecyclerView.Adapter<PlaylistShovelerViewHolder> {
    private OnPlaylistClickListener onClickListener;
    private StyleSheet styleSheet;
    private final List<SeeAllPlaylistItem> playlists = new ArrayList();
    private Subscription subscription = StyleSheetProvider.getStyleSheetObservable().observeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$$ExternalSyntheticLambda0
        @Override // rx.functions.Action1
        public final void call(Object obj) {
            DescriptiveShovelerFocusedAdapter.m1158_init_$lambda0(this.f$0, (StyleSheet) obj);
        }
    });

    /* JADX INFO: compiled from: DescriptiveShovelerFocusedAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$OnPlaylistClickListener;", "", "onItemClicked", "", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnPlaylistClickListener {
        void onItemClicked(SeeAllPlaylistItem playlist);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1158_init_$lambda0(DescriptiveShovelerFocusedAdapter this$0, StyleSheet sheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sheet, "sheet");
        this$0.styleSheet = sheet;
    }

    /* JADX INFO: compiled from: DescriptiveShovelerFocusedAdapter.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u00107\u001a\u0002082\b\u0010(\u001a\u0004\u0018\u00010)J\u0018\u00109\u001a\u0002082\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0002J\u0012\u0010:\u001a\u0002082\b\u0010;\u001a\u0004\u0018\u00010\u0004H\u0016J\u000e\u0010<\u001a\u0002082\u0006\u0010=\u001a\u00020\u0000J\u000e\u0010>\u001a\u0002082\u0006\u0010=\u001a\u00020\u0000J\u0010\u0010?\u001a\u0002082\b\u0010(\u001a\u0004\u0018\u00010)R\u001b\u0010\n\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0015\u0010\u0012R\u001b\u0010\u0017\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0018\u0010\u0012R\u001b\u0010\u001a\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u000e\u001a\u0004\b\u001b\u0010\u0012R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u000e\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u000e\u001a\u0004\b#\u0010\u0012R\u001b\u0010%\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u000e\u001a\u0004\b&\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010*\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u000e\u001a\u0004\b+\u0010\u0012R\u001d\u0010-\u001a\u0004\u0018\u00010.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u000e\u001a\u0004\b/\u00100R\u001b\u00102\u001a\u0002038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u000e\u001a\u0004\b4\u00105¨\u0006@"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "view", "Landroid/view/View;", "onClickListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$OnPlaylistClickListener;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/view/View;Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$OnPlaylistClickListener;Lcom/amazon/music/views/library/styles/StyleSheet;)V", AppStateModule.APP_STATE_BACKGROUND, "getBackground", "()Landroid/view/View;", "background$delegate", "Lkotlin/Lazy;", "backgroundWidth", "", "getBackgroundWidth", "()I", "backgroundWidth$delegate", "focusedTileLeftDistance", "getFocusedTileLeftDistance", "focusedTileLeftDistance$delegate", "focusedTileRightDistance", "getFocusedTileRightDistance", "focusedTileRightDistance$delegate", "focusedTileWidth", "getFocusedTileWidth", "focusedTileWidth$delegate", ContextMappingConstants.IMAGE, "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "image$delegate", "imageMargin", "getImageMargin", "imageMargin$delegate", "imageSize", "getImageSize", "imageSize$delegate", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "shovelerColumnSpacing", "getShovelerColumnSpacing", "shovelerColumnSpacing$delegate", "shovelerGridStyle", "Lcom/amazon/ui/foundations/styles/GridStyle;", "getShovelerGridStyle", "()Lcom/amazon/ui/foundations/styles/GridStyle;", "shovelerGridStyle$delegate", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "title$delegate", "bindTo", "", "loadThumbnail", "onClick", "v", "setItemActive", "holder", "setItemInactive", "setState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PlaylistShovelerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* JADX INFO: renamed from: background$delegate, reason: from kotlin metadata */
        private final Lazy background;

        /* JADX INFO: renamed from: backgroundWidth$delegate, reason: from kotlin metadata */
        private final Lazy backgroundWidth;

        /* JADX INFO: renamed from: focusedTileLeftDistance$delegate, reason: from kotlin metadata */
        private final Lazy focusedTileLeftDistance;

        /* JADX INFO: renamed from: focusedTileRightDistance$delegate, reason: from kotlin metadata */
        private final Lazy focusedTileRightDistance;

        /* JADX INFO: renamed from: focusedTileWidth$delegate, reason: from kotlin metadata */
        private final Lazy focusedTileWidth;

        /* JADX INFO: renamed from: image$delegate, reason: from kotlin metadata */
        private final Lazy image;

        /* JADX INFO: renamed from: imageMargin$delegate, reason: from kotlin metadata */
        private final Lazy imageMargin;

        /* JADX INFO: renamed from: imageSize$delegate, reason: from kotlin metadata */
        private final Lazy imageSize;
        private final OnPlaylistClickListener onClickListener;
        private SeeAllPlaylistItem playlist;

        /* JADX INFO: renamed from: shovelerColumnSpacing$delegate, reason: from kotlin metadata */
        private final Lazy shovelerColumnSpacing;

        /* JADX INFO: renamed from: shovelerGridStyle$delegate, reason: from kotlin metadata */
        private final Lazy shovelerGridStyle;

        /* JADX INFO: renamed from: title$delegate, reason: from kotlin metadata */
        private final Lazy title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaylistShovelerViewHolder(final View view, OnPlaylistClickListener onClickListener, final StyleSheet styleSheet) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            this.onClickListener = onClickListener;
            this.imageMargin = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$imageMargin$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf((int) this.this$0.getImage().getContext().getResources().getDimension(R.dimen.bauhaus_spacer_mini));
                }
            });
            this.image = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$image$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ImageView invoke() {
                    return (ImageView) view.findViewById(R.id.playlist_image);
                }
            });
            this.title = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$title$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextView invoke() {
                    return (TextView) view.findViewById(R.id.playlist_name);
                }
            });
            this.background = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$background$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final View invoke() {
                    return view.findViewById(R.id.background);
                }
            });
            this.imageSize = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$imageSize$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(this.this$0.getImage().getContext().getResources().getDimensionPixelSize(R.dimen.artwork_dimen_compact));
                }
            });
            this.backgroundWidth = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$backgroundWidth$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(this.this$0.getFocusedTileWidth() - this.this$0.getShovelerColumnSpacing());
                }
            });
            this.shovelerGridStyle = LazyKt__LazyJVMKt.lazy(new Function0<GridStyle>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$shovelerGridStyle$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final GridStyle invoke() {
                    return styleSheet.getGridStyle(GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID);
                }
            });
            this.shovelerColumnSpacing = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$shovelerColumnSpacing$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    GridSize gridSize;
                    GridStyle shovelerGridStyle = this.this$0.getShovelerGridStyle();
                    int columnSpace = 0;
                    if (shovelerGridStyle != null && (gridSize = shovelerGridStyle.getGridSize()) != null) {
                        columnSpace = gridSize.getColumnSpace();
                    }
                    return Integer.valueOf(columnSpace);
                }
            });
            this.focusedTileWidth = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$focusedTileWidth$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    int dimension;
                    DisplayMetrics displayMetrics = this.this$0.getImage().getContext().getResources().getDisplayMetrics();
                    Intrinsics.checkNotNullExpressionValue(displayMetrics, "image.context.resources.displayMetrics");
                    Context context = this.this$0.getImage().getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "image.context");
                    if (!ShovelerExtensionFunctionsKt.shouldDisplayOverflowMenu(context, styleSheet)) {
                        dimension = (int) this.this$0.getImage().getContext().getResources().getDimension(R.dimen.pop_out_context_menu_width);
                    } else {
                        dimension = displayMetrics.widthPixels;
                    }
                    return Integer.valueOf((dimension - this.this$0.getFocusedTileLeftDistance()) - this.this$0.getFocusedTileRightDistance());
                }
            });
            this.focusedTileLeftDistance = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$focusedTileLeftDistance$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    int shovelerColumnSpacing = this.this$0.getShovelerColumnSpacing() / 2;
                    Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI);
                    return Integer.valueOf((spacerInPixels == null ? 0 : spacerInPixels.intValue()) + shovelerColumnSpacing);
                }
            });
            this.focusedTileRightDistance = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$focusedTileRightDistance$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    GridStyle shovelerGridStyle = this.this$0.getShovelerGridStyle();
                    int columnCount = shovelerGridStyle == null ? 1 : shovelerGridStyle.getColumnCount();
                    int imageSize = this.this$0.getImageSize();
                    Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
                    return Integer.valueOf(((imageSize + this.this$0.getShovelerColumnSpacing()) * (columnCount - 1)) + (spacerInPixels == null ? 0 : spacerInPixels.intValue()) + (this.this$0.getShovelerColumnSpacing() / 2));
                }
            });
        }

        private final int getImageMargin() {
            return ((Number) this.imageMargin.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ImageView getImage() {
            Object value = this.image.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-image>(...)");
            return (ImageView) value;
        }

        private final TextView getTitle() {
            Object value = this.title.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-title>(...)");
            return (TextView) value;
        }

        private final View getBackground() {
            Object value = this.background.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-background>(...)");
            return (View) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getImageSize() {
            return ((Number) this.imageSize.getValue()).intValue();
        }

        private final int getBackgroundWidth() {
            return ((Number) this.backgroundWidth.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final GridStyle getShovelerGridStyle() {
            return (GridStyle) this.shovelerGridStyle.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getShovelerColumnSpacing() {
            return ((Number) this.shovelerColumnSpacing.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getFocusedTileWidth() {
            return ((Number) this.focusedTileWidth.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getFocusedTileLeftDistance() {
            return ((Number) this.focusedTileLeftDistance.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getFocusedTileRightDistance() {
            return ((Number) this.focusedTileRightDistance.getValue()).intValue();
        }

        public final void bindTo(SeeAllPlaylistItem playlist) {
            if (playlist == null) {
                return;
            }
            this.playlist = playlist;
            getTitle().setText(playlist.getName());
            getTitle().setVisibility(8);
            getBackground().setVisibility(8);
            loadThumbnail(getImage(), playlist);
            ViewParent parent = getImage().getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            ((ViewGroup) parent).setOnClickListener(this);
            ViewGroup.LayoutParams layoutParams = getImage().getLayoutParams();
            layoutParams.width = getImageSize();
            layoutParams.height = getImageSize();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMargins(marginLayoutParams.getMarginStart(), getImageMargin(), marginLayoutParams.getMarginEnd(), getImageMargin());
            }
            getImage().setLayoutParams(layoutParams);
            getImage().requestLayout();
            ViewGroup.LayoutParams layoutParams2 = getBackground().getLayoutParams();
            if (layoutParams2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginLayoutParams2.width = getBackgroundWidth();
            getBackground().setLayoutParams(marginLayoutParams2);
            getBackground().requestLayout();
        }

        public final void setState(SeeAllPlaylistItem playlist) {
            if (playlist == null) {
                return;
            }
            if (playlist.getIsActive()) {
                setItemActive(this);
            } else {
                setItemInactive(this);
            }
        }

        private final void loadThumbnail(final ImageView image, final SeeAllPlaylistItem playlist) {
            AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter$PlaylistShovelerViewHolder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DescriptiveShovelerFocusedAdapter.PlaylistShovelerViewHolder.m1159loadThumbnail$lambda8(playlist, this, image);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: loadThumbnail$lambda-8, reason: not valid java name */
        public static final void m1159loadThumbnail$lambda8(SeeAllPlaylistItem playlist, PlaylistShovelerViewHolder this$0, ImageView image) {
            Bitmap bitmap$default;
            Bitmap bitmap$default2;
            Bitmap bitmapCreateScaledBitmap;
            Bitmap bitmap$default3;
            Bitmap bitmap$default4;
            Bitmap bitmapCreateScaledBitmap2;
            Intrinsics.checkNotNullParameter(playlist, "$playlist");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(image, "$image");
            String id = playlist.getId();
            Bitmap bitmapCreateScaledBitmap3 = null;
            if (Intrinsics.areEqual(id, "-1")) {
                bitmap$default = Bitmap.createBitmap(this$0.getImageSize(), this$0.getImageSize(), Bitmap.Config.ARGB_8888);
                Intrinsics.checkNotNullExpressionValue(bitmap$default, "createBitmap(imageSize, … Bitmap.Config.ARGB_8888)");
                TypedValue typedValue = new TypedValue();
                image.getContext().getResources().getValue(R.dimen.now_playing_create_playlist_icon_scale_factor, typedValue, true);
                int imageSize = (int) (this$0.getImageSize() * typedValue.getFloat());
                Drawable drawable = ContextCompat.getDrawable(image.getContext(), R.drawable.ic_action_add);
                if (drawable == null || (bitmap$default4 = DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null)) == null) {
                    bitmapCreateScaledBitmap2 = null;
                } else {
                    bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(bitmap$default4, imageSize, imageSize, true);
                    Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap2, "createScaledBitmap(this, width, height, filter)");
                }
                float width = (bitmap$default.getWidth() - imageSize) / 2;
                Canvas canvas = new Canvas(bitmap$default);
                canvas.drawColor(ContextCompat.getColor(image.getContext(), R.color.widget_background));
                if (bitmapCreateScaledBitmap2 != null) {
                    canvas.drawBitmap(bitmapCreateScaledBitmap2, width, width, (Paint) null);
                }
            } else if (Intrinsics.areEqual(id, "-2")) {
                bitmap$default = Bitmap.createBitmap(this$0.getImageSize(), this$0.getImageSize(), Bitmap.Config.ARGB_8888);
                Intrinsics.checkNotNullExpressionValue(bitmap$default, "createBitmap(imageSize, … Bitmap.Config.ARGB_8888)");
                TypedValue typedValue2 = new TypedValue();
                image.getContext().getResources().getValue(R.dimen.now_playing_create_playlist_icon_scale_factor, typedValue2, true);
                int imageSize2 = (int) (this$0.getImageSize() * typedValue2.getFloat());
                Drawable drawable2 = ContextCompat.getDrawable(image.getContext(), R.drawable.ic_chevron_caretright);
                if (drawable2 == null || (bitmap$default2 = DrawableKt.toBitmap$default(drawable2, 0, 0, null, 7, null)) == null) {
                    bitmapCreateScaledBitmap = null;
                } else {
                    bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap$default2, imageSize2, imageSize2, true);
                    Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(this, width, height, filter)");
                }
                float width2 = (bitmap$default.getWidth() - imageSize2) / 2;
                Paint paint = new Paint();
                paint.setColor(ContextCompat.getColor(image.getContext(), R.color.widget_background));
                paint.setAntiAlias(true);
                float imageSize3 = this$0.getImageSize() / 2;
                Canvas canvas2 = new Canvas(bitmap$default);
                canvas2.drawCircle(imageSize3, imageSize3, imageSize3, paint);
                if (bitmapCreateScaledBitmap != null) {
                    canvas2.drawBitmap(bitmapCreateScaledBitmap, width2, width2, (Paint) null);
                }
            } else {
                Drawable onCurrentThread = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL, "cirrus", this$0.getImageSize(), playlist.getId().toString(), true);
                bitmap$default = onCurrentThread == null ? null : DrawableKt.toBitmap$default(onCurrentThread, 0, 0, null, 7, null);
            }
            if (bitmap$default == null) {
                Drawable drawable3 = ContextCompat.getDrawable(image.getContext(), R.drawable.playlist_placeholder);
                if (drawable3 != null && (bitmap$default3 = DrawableKt.toBitmap$default(drawable3, 0, 0, null, 7, null)) != null) {
                    bitmapCreateScaledBitmap3 = Bitmap.createScaledBitmap(bitmap$default3, this$0.getImageSize(), this$0.getImageSize(), true);
                    Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap3, "createScaledBitmap(this, width, height, filter)");
                }
                bitmap$default = bitmapCreateScaledBitmap3;
            }
            new RoundedCornerImageProcessor(image.getContext()).process(bitmap$default, image);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            OnPlaylistClickListener onPlaylistClickListener = this.onClickListener;
            SeeAllPlaylistItem seeAllPlaylistItem = this.playlist;
            if (seeAllPlaylistItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Environment.PLAYLIST_PATH);
                seeAllPlaylistItem = null;
            }
            onPlaylistClickListener.onItemClicked(seeAllPlaylistItem);
        }

        public final void setItemActive(PlaylistShovelerViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            holder.getTitle().setVisibility(0);
            holder.getBackground().setVisibility(0);
            ViewGroup.LayoutParams layoutParams = holder.getImage().getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMargins(holder.getImageMargin(), marginLayoutParams.topMargin, marginLayoutParams.getMarginEnd(), marginLayoutParams.bottomMargin);
            }
            holder.getImage().setLayoutParams(layoutParams);
            holder.getImage().requestLayout();
        }

        public final void setItemInactive(PlaylistShovelerViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            holder.getTitle().setVisibility(8);
            holder.getBackground().setVisibility(8);
            ViewGroup.LayoutParams layoutParams = holder.getImage().getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMargins(0, marginLayoutParams.topMargin, marginLayoutParams.getMarginEnd(), marginLayoutParams.bottomMargin);
            }
            holder.getImage().setLayoutParams(layoutParams);
            holder.getImage().requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PlaylistShovelerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.context_menu_playlist_item, parent, false);
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Intrinsics.checkNotNullExpressionValue(view, "view");
        OnPlaylistClickListener onPlaylistClickListener = this.onClickListener;
        StyleSheet styleSheet = null;
        if (onPlaylistClickListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onClickListener");
            onPlaylistClickListener = null;
        }
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
        } else {
            styleSheet = styleSheet2;
        }
        return new PlaylistShovelerViewHolder(view, onPlaylistClickListener, styleSheet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(PlaylistShovelerViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bindTo(this.playlists.get(position));
        holder.setState(this.playlists.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.playlists.size();
    }

    public final void setOnPlaylistClickListener(OnPlaylistClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onClickListener = listener;
    }

    public final void updatePlaylists(List<SeeAllPlaylistItem> newPlaylists, Context context) {
        Intrinsics.checkNotNullParameter(newPlaylists, "newPlaylists");
        this.playlists.clear();
        if (context != null) {
            addNewPlaylistItem(context);
        }
        if (!newPlaylists.isEmpty()) {
            this.playlists.addAll(newPlaylists);
        }
        if (newPlaylists.size() < 5 || context == null) {
            return;
        }
        addSeeAllPlaylists(context);
    }

    private final void addNewPlaylistItem(Context context) {
        List<SeeAllPlaylistItem> list = this.playlists;
        String string = context.getString(R.string.dmusic_context_see_all_new_playlist);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ext_see_all_new_playlist)");
        list.add(new SeeAllPlaylistItem("-1", "-1", string, 0, false, 16, null));
    }

    private final void addSeeAllPlaylists(Context context) {
        List<SeeAllPlaylistItem> list = this.playlists;
        String string = context.getString(R.string.dmusic_context_see_all_playlists);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ontext_see_all_playlists)");
        list.add(new SeeAllPlaylistItem("-2", "-2", string, this.playlists.size(), false, 16, null));
    }

    public final void updateActiveItem(int oldPosition, int newPosition) {
        this.playlists.get(oldPosition).setActive(false);
        this.playlists.get(newPosition).setActive(true);
    }
}
