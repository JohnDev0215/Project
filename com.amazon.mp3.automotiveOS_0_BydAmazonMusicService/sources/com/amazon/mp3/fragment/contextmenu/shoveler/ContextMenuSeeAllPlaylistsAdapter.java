package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
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
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMenuSeeAllPlaylistsAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001b\u001c\u001dB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0005J\u001e\u0010\u0018\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001a2\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder;", "()V", "onClickListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$OnPlaylistClickListener;", "playlists", "", "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "addSeeAllPlaylists", "", "context", "Landroid/content/Context;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnPlaylistClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "updatePlaylists", "newPlaylists", "", "Companion", "OnPlaylistClickListener", "PlaylistViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContextMenuSeeAllPlaylistsAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {
    private OnPlaylistClickListener onClickListener;
    private final List<SeeAllPlaylistItem> playlists = new ArrayList();

    /* JADX INFO: compiled from: ContextMenuSeeAllPlaylistsAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$OnPlaylistClickListener;", "", "onItemClicked", "", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnPlaylistClickListener {
        void onItemClicked(SeeAllPlaylistItem playlist);
    }

    /* JADX INFO: compiled from: ContextMenuSeeAllPlaylistsAdapter.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u001f\u001a\u00020 2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0018\u0010!\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0012\u0010\"\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\u0004H\u0016R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\r\u001a\u0004\b\u001c\u0010\u001d¨\u0006$"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "view", "Landroid/view/View;", "onClickListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$OnPlaylistClickListener;", "(Landroid/view/View;Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$OnPlaylistClickListener;)V", "cacheManager", "Lcom/amazon/mp3/library/cache/image/CacheManager;", "getCacheManager", "()Lcom/amazon/mp3/library/cache/image/CacheManager;", "cacheManager$delegate", "Lkotlin/Lazy;", ContextMappingConstants.IMAGE, "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "image$delegate", "imageSize", "", "getImageSize", "()I", "imageSize$delegate", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "title$delegate", "bindTo", "", "loadThumbnail", "onClick", "v", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PlaylistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* JADX INFO: renamed from: cacheManager$delegate, reason: from kotlin metadata */
        private final Lazy cacheManager;

        /* JADX INFO: renamed from: image$delegate, reason: from kotlin metadata */
        private final Lazy image;

        /* JADX INFO: renamed from: imageSize$delegate, reason: from kotlin metadata */
        private final Lazy imageSize;
        private final OnPlaylistClickListener onClickListener;
        private SeeAllPlaylistItem playlist;

        /* JADX INFO: renamed from: title$delegate, reason: from kotlin metadata */
        private final Lazy title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlaylistViewHolder(final View view, OnPlaylistClickListener onClickListener) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
            this.onClickListener = onClickListener;
            this.cacheManager = LazyKt__LazyJVMKt.lazy(new Function0<CacheManager>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder$cacheManager$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final CacheManager invoke() {
                    return CacheManager.getInstance();
                }
            });
            this.imageSize = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder$imageSize$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(this.this$0.getImage().getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size));
                }
            });
            this.image = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder$image$2
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
            this.title = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder$title$2
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
        }

        private final CacheManager getCacheManager() {
            Object value = this.cacheManager.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-cacheManager>(...)");
            return (CacheManager) value;
        }

        private final int getImageSize() {
            return ((Number) this.imageSize.getValue()).intValue();
        }

        public final ImageView getImage() {
            Object value = this.image.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-image>(...)");
            return (ImageView) value;
        }

        public final TextView getTitle() {
            Object value = this.title.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-title>(...)");
            return (TextView) value;
        }

        public final void bindTo(SeeAllPlaylistItem playlist) {
            if (playlist == null) {
                return;
            }
            this.playlist = playlist;
            getTitle().setText(playlist.getName());
            loadThumbnail(getImage(), playlist);
            ViewParent parent = getImage().getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.setOnClickListener(this);
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            layoutParams.width = -1;
            viewGroup.setLayoutParams(layoutParams);
            int dimension = (int) viewGroup.getContext().getResources().getDimension(R.dimen.bauhaus_spacer_mini);
            viewGroup.setPadding(dimension, dimension, dimension, dimension);
            viewGroup.requestLayout();
        }

        private final void loadThumbnail(final ImageView image, final SeeAllPlaylistItem playlist) {
            AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter$PlaylistViewHolder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ContextMenuSeeAllPlaylistsAdapter.PlaylistViewHolder.m1154loadThumbnail$lambda4(playlist, this, image);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: loadThumbnail$lambda-4, reason: not valid java name */
        public static final void m1154loadThumbnail$lambda4(SeeAllPlaylistItem playlist, PlaylistViewHolder this$0, ImageView image) {
            Bitmap bitmap$default;
            Bitmap bitmapCreateScaledBitmap;
            Intrinsics.checkNotNullParameter(playlist, "$playlist");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(image, "$image");
            Bitmap bitmapCreateScaledBitmap2 = null;
            if (!Intrinsics.areEqual(playlist.getId(), "-1")) {
                Drawable onCurrentThread = this$0.getCacheManager().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL, "cirrus", this$0.getImageSize(), playlist.getId(), true);
                if (onCurrentThread != null) {
                    bitmapCreateScaledBitmap2 = DrawableKt.toBitmap$default(onCurrentThread, 0, 0, null, 7, null);
                }
            } else {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this$0.getImageSize(), this$0.getImageSize(), Bitmap.Config.ARGB_8888);
                Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(imageSize, … Bitmap.Config.ARGB_8888)");
                TypedValue typedValue = new TypedValue();
                image.getContext().getResources().getValue(R.dimen.now_playing_create_playlist_icon_scale_factor, typedValue, true);
                int imageSize = (int) (this$0.getImageSize() * typedValue.getFloat());
                Drawable drawable = ContextCompat.getDrawable(image.getContext(), R.drawable.ic_action_add);
                if (drawable == null || (bitmap$default = DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null)) == null) {
                    bitmapCreateScaledBitmap = null;
                } else {
                    bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap$default, imageSize, imageSize, true);
                    Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(this, width, height, filter)");
                }
                float width = (bitmapCreateBitmap.getWidth() - imageSize) / 2;
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.drawColor(ContextCompat.getColor(image.getContext(), R.color.widget_background));
                if (bitmapCreateScaledBitmap != null) {
                    canvas.drawBitmap(bitmapCreateScaledBitmap, width, width, (Paint) null);
                }
                bitmapCreateScaledBitmap2 = bitmapCreateBitmap;
            }
            if (bitmapCreateScaledBitmap2 == null) {
                Drawable drawable2 = ContextCompat.getDrawable(image.getContext(), R.drawable.playlist_placeholder);
                Intrinsics.checkNotNull(drawable2);
                Intrinsics.checkNotNullExpressionValue(drawable2, "getDrawable(image.contex…e.playlist_placeholder)!!");
                bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(DrawableKt.toBitmap$default(drawable2, 0, 0, null, 7, null), this$0.getImageSize(), this$0.getImageSize(), true);
                Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap2, "createScaledBitmap(this, width, height, filter)");
            }
            new RoundedCornerImageProcessor(image.getContext()).process(bitmapCreateScaledBitmap2, image);
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
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.context_menu_playlist_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        OnPlaylistClickListener onPlaylistClickListener = this.onClickListener;
        if (onPlaylistClickListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onClickListener");
            onPlaylistClickListener = null;
        }
        return new PlaylistViewHolder(view, onPlaylistClickListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bindTo(this.playlists.get(position));
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
            addSeeAllPlaylists(context);
        }
        this.playlists.addAll(newPlaylists);
    }

    private final void addSeeAllPlaylists(Context context) {
        List<SeeAllPlaylistItem> list = this.playlists;
        String string = context.getString(R.string.dmusic_context_see_all_new_playlist);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ext_see_all_new_playlist)");
        list.add(new SeeAllPlaylistItem("-1", "-1", string, Integer.parseInt("-1"), false, 16, null));
    }
}
