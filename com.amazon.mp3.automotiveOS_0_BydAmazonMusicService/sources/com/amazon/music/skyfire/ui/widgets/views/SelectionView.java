package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.ui.foundations.ktx.ViewKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectionView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u00188\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001c¨\u0006 "}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/SelectionView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "divider", "Landroid/view/View;", "getDivider", "()Landroid/view/View;", "setDivider", "(Landroid/view/View;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "setIcon", "(Landroid/widget/ImageView;)V", "value", "", "isDividerVisible", "()Z", "setDividerVisible", "(Z)V", ContextMappingConstants.PRIMARY_TEXT, "Landroid/widget/TextView;", "getPrimaryText", "()Landroid/widget/TextView;", "setPrimaryText", "(Landroid/widget/TextView;)V", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectionView extends ConstraintLayout {

    @ViewFinder.AView
    public View divider;

    @ViewFinder.AView
    public ImageView icon;

    @ViewFinder.AView
    public TextView primaryText;

    @ViewFinder.AView
    public TextView secondaryText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.selection_view), (Object) null, 1, (Object) null);
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

    public final ImageView getIcon() {
        ImageView imageView = this.icon;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("icon");
        return null;
    }

    public final void setIcon(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.icon = imageView;
    }

    public final View getDivider() {
        View view = this.divider;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("divider");
        return null;
    }

    public final void setDivider(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.divider = view;
    }

    public final boolean isDividerVisible() {
        return getDivider().getVisibility() == 0;
    }

    public final void setDividerVisible(boolean z) {
        ViewKt.collapseIf(getDivider(), !z);
    }
}
