package com.amazon.music.explore.widgets.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RetailHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u001e\u0010 \u001a\u00020\u00188\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001e\u0010#\u001a\u00020\u00188\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001a\"\u0004\b%\u0010\u001c¨\u0006&"}, d2 = {"Lcom/amazon/music/explore/widgets/views/RetailHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callToActionButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCallToActionButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "setCallToActionButton", "(Lcom/amazon/ui/foundations/views/BaseButton;)V", "carouselImages", "Landroidx/recyclerview/widget/RecyclerView;", "getCarouselImages", "()Landroidx/recyclerview/widget/RecyclerView;", "setCarouselImages", "(Landroidx/recyclerview/widget/RecyclerView;)V", ContextMappingConstants.IMAGE, "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "setImage", "(Landroid/widget/ImageView;)V", ContextMappingConstants.LABEL, "Landroid/widget/TextView;", "getLabel", "()Landroid/widget/TextView;", "setLabel", "(Landroid/widget/TextView;)V", "primaryBadge", "getPrimaryBadge", "setPrimaryBadge", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "setPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RetailHeaderView extends ConstraintLayout {

    @ViewFinder.AView
    public BaseButton callToActionButton;

    @ViewFinder.AView
    public RecyclerView carouselImages;

    @ViewFinder.AView
    public ImageView image;

    @ViewFinder.AView
    public TextView label;

    @ViewFinder.AView
    public ImageView primaryBadge;

    @ViewFinder.AView
    public TextView primaryText;

    @ViewFinder.AView
    public TextView secondaryText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RetailHeaderView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.retail_header_view), (Object) null, 1, (Object) null);
    }

    public final RecyclerView getCarouselImages() {
        RecyclerView recyclerView = this.carouselImages;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("carouselImages");
        return null;
    }

    public final void setCarouselImages(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.carouselImages = recyclerView;
    }

    public final ImageView getImage() {
        ImageView imageView = this.image;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.IMAGE);
        return null;
    }

    public final void setImage(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.image = imageView;
    }

    public final TextView getLabel() {
        TextView textView = this.label;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.LABEL);
        return null;
    }

    public final void setLabel(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.label = textView;
    }

    public final TextView getPrimaryText() {
        TextView textView = this.primaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
        return null;
    }

    public final void setPrimaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.primaryText = textView;
    }

    public final TextView getSecondaryText() {
        TextView textView = this.secondaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
        return null;
    }

    public final void setSecondaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.secondaryText = textView;
    }

    public final ImageView getPrimaryBadge() {
        ImageView imageView = this.primaryBadge;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("primaryBadge");
        return null;
    }

    public final void setPrimaryBadge(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.primaryBadge = imageView;
    }

    public final BaseButton getCallToActionButton() {
        BaseButton baseButton = this.callToActionButton;
        if (baseButton != null) {
            return baseButton;
        }
        Intrinsics.throwUninitializedPropertyAccessException("callToActionButton");
        return null;
    }

    public final void setCallToActionButton(BaseButton baseButton) {
        Intrinsics.checkNotNullParameter(baseButton, "<set-?>");
        this.callToActionButton = baseButton;
    }
}
