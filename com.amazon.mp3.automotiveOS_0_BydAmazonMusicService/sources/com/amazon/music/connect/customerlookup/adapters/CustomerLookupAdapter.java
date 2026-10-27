package com.amazon.music.connect.customerlookup.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import com.amazon.music.connect.R$dimen;
import com.amazon.music.connect.customerlookup.CustomerLookupResult;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerLookupAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001'B#\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b%\u0010&J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u001e\u0010\u0010\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eJ\u0006\u0010\u0011\u001a\u00020\nJ\b\u0010\u0012\u001a\u00020\u0005H\u0016R#\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u001c\u0010 \u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupAdapter$ViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "onCreateViewHolder", "holder", "position", "", "onBindViewHolder", "Ljava/util/ArrayList;", "Lcom/amazon/music/connect/customerlookup/CustomerLookupResult;", "Lkotlin/collections/ArrayList;", "models", "replaceAll", "clear", "getItemCount", "Lkotlin/Function1;", "clickListener", "Lkotlin/jvm/functions/Function1;", "getClickListener", "()Lkotlin/jvm/functions/Function1;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "", "TAG", "Ljava/lang/String;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "Landroidx/recyclerview/widget/SortedList;", "sortedList", "Landroidx/recyclerview/widget/SortedList;", "<init>", "(Lkotlin/jvm/functions/Function1;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "ViewHolder", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class CustomerLookupAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final String TAG;
    private final Function1<CustomerLookupResult, Unit> clickListener;
    private final Logger logger;
    private final SortedList<CustomerLookupResult> sortedList;
    private final StyleSheet styleSheet;

    /* JADX WARN: Multi-variable type inference failed */
    public CustomerLookupAdapter(Function1<? super CustomerLookupResult, Unit> clickListener, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.clickListener = clickListener;
        this.styleSheet = styleSheet;
        String simpleName = Reflection.getOrCreateKotlinClass(CustomerLookupAdapter.class).getSimpleName();
        this.TAG = simpleName;
        this.logger = LoggerFactory.getLogger(simpleName);
        this.sortedList = new SortedList<>(CustomerLookupResult.class, new SortedList.Callback<CustomerLookupResult>() { // from class: com.amazon.music.connect.customerlookup.adapters.CustomerLookupAdapter$sortedList$1
            @Override // androidx.recyclerview.widget.SortedList.Callback, java.util.Comparator
            public int compare(CustomerLookupResult a2, CustomerLookupResult b) {
                Intrinsics.checkNotNullParameter(a2, "a");
                Intrinsics.checkNotNullParameter(b, "b");
                return a2.getCustomerPublicName().compareTo(b.getCustomerPublicName());
            }

            @Override // androidx.recyclerview.widget.ListUpdateCallback
            public void onInserted(int position, int count) {
                this.this$0.notifyItemRangeInserted(position, count);
            }

            @Override // androidx.recyclerview.widget.ListUpdateCallback
            public void onRemoved(int position, int count) {
                this.this$0.notifyItemRangeRemoved(position, count);
            }

            @Override // androidx.recyclerview.widget.ListUpdateCallback
            public void onMoved(int fromPosition, int toPosition) {
                this.this$0.notifyItemMoved(fromPosition, toPosition);
            }

            @Override // androidx.recyclerview.widget.SortedList.Callback
            public void onChanged(int position, int count) {
                this.this$0.notifyItemRangeChanged(position, count);
            }

            @Override // androidx.recyclerview.widget.SortedList.Callback
            public boolean areContentsTheSame(CustomerLookupResult oldItem, CustomerLookupResult newItem) {
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return oldItem.equals(newItem);
            }

            @Override // androidx.recyclerview.widget.SortedList.Callback
            public boolean areItemsTheSame(CustomerLookupResult item1, CustomerLookupResult item2) {
                Intrinsics.checkNotNullParameter(item1, "item1");
                Intrinsics.checkNotNullParameter(item2, "item2");
                return item1.isSameResult(item2);
            }
        });
    }

    /* JADX INFO: compiled from: CustomerLookupAdapter.kt */
    @Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b!\u0010\"J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/amazon/music/connect/customerlookup/CustomerLookupResult;", "result", "", "bind", "Landroid/view/View;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "", "ARTIST_TYPE", "I", "PROFILE_TYPE", "Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "kotlin.jvm.PlatformType", "picassoImageLoader", "Lcom/amazon/music/picassoimageloader/PicassoImageLoader;", "Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "artworkBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameView", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "<init>", "(Landroid/view/View;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final int ARTIST_TYPE;
        private final int PROFILE_TYPE;
        private final ArtworkFrameBinder artworkBinder;
        private final ArtworkFrameView artworkFrameView;
        private final ImageBinder imageBinder;
        private final PicassoImageLoader picassoImageLoader;
        private final StyleSheet styleSheet;
        private View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view, StyleSheet styleSheet) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            this.view = view;
            this.styleSheet = styleSheet;
            this.ARTIST_TYPE = 4;
            this.PROFILE_TYPE = 6;
            PicassoImageLoader picassoImageLoader = PicassoImageLoader.builder(view.getContext(), Picasso.get()).withTag(this.view.getContext()).build();
            this.picassoImageLoader = picassoImageLoader;
            Context context = this.view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            Intrinsics.checkNotNullExpressionValue(picassoImageLoader, "picassoImageLoader");
            ImageBinder imageBinder = new ImageBinder(context, styleSheet, picassoImageLoader);
            this.imageBinder = imageBinder;
            Context context2 = this.view.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "view.context");
            this.artworkBinder = new ArtworkFrameBinder(context2, styleSheet, imageBinder, null, null, 24, null);
            this.artworkFrameView = ((HorizontalTileView) this.view).getArtworkFrameLayout();
        }

        public final View getView() {
            return this.view;
        }

        public final void bind(CustomerLookupResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            ArtworkFrameModel artworkFrameModel = new ArtworkFrameModel(null, result.getAvatarUrl(), Integer.valueOf(this.ARTIST_TYPE), null, null, null, null, null, null, null, null, null, 4089, null);
            int dimension = (int) this.view.getContext().getResources().getDimension(R$dimen.artwork_default_dimen);
            this.artworkFrameView.setLayoutParams(new ConstraintLayout.LayoutParams(dimension, dimension));
            this.artworkBinder.bind(this.artworkFrameView, artworkFrameModel);
            ImageStyle imageStyle = this.styleSheet.getImageStyle(ImageStyleKey.DEFAULT);
            if (imageStyle != null) {
                this.artworkFrameView.getArtworkImageView().setImageStyle(imageStyle);
            }
            ImageSize imageSize = this.styleSheet.getImageSize(dimension);
            if (imageSize != null) {
                this.artworkFrameView.getArtworkImageView().setImageSize(imageSize);
            }
            ((HorizontalTileView) this.view).setTitle(result.getCustomerPublicName());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return new ViewHolder(new HorizontalTileView(context, this.styleSheet), this.styleSheet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final CustomerLookupResult result = this.sortedList.get(position);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        holder.bind(result);
        holder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.connect.customerlookup.adapters.CustomerLookupAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerLookupAdapter.m1907onBindViewHolder$lambda0(this.f$0, result, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m1907onBindViewHolder$lambda0(CustomerLookupAdapter this$0, CustomerLookupResult result, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<CustomerLookupResult, Unit> function1 = this$0.clickListener;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        function1.invoke(result);
    }

    public final void replaceAll(ArrayList<CustomerLookupResult> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        this.sortedList.beginBatchedUpdates();
        this.sortedList.replaceAll(models);
        this.sortedList.endBatchedUpdates();
    }

    public final void clear() {
        this.sortedList.beginBatchedUpdates();
        this.sortedList.clear();
        this.sortedList.endBatchedUpdates();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.sortedList.size();
    }
}
