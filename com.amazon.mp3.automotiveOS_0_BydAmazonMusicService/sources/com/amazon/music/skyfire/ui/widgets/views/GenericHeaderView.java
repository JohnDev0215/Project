package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\u00018\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00118\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001e\u0010\u0019\u001a\u00020\u00118\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/GenericHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionButtons", "Landroid/widget/LinearLayout;", "getActionButtons", "()Landroid/widget/LinearLayout;", "setActionButtons", "(Landroid/widget/LinearLayout;)V", "layout", "getLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setLayout", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", ContextMappingConstants.PRIMARY_TEXT, "Landroid/widget/TextView;", "getPrimaryText", "()Landroid/widget/TextView;", "setPrimaryText", "(Landroid/widget/TextView;)V", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "setTertiaryText", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenericHeaderView extends ConstraintLayout {

    @ViewFinder.AView
    public LinearLayout actionButtons;

    @ViewFinder.AView
    public ConstraintLayout layout;

    @ViewFinder.AView
    public TextView primaryText;

    @ViewFinder.AView
    public TextView secondaryText;

    @ViewFinder.AView
    public TextView tertiaryText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericHeaderView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.generic_header_view), (Object) null, 1, (Object) null);
    }

    public final ConstraintLayout getLayout() {
        ConstraintLayout constraintLayout = this.layout;
        if (constraintLayout != null) {
            return constraintLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layout");
        return null;
    }

    public final void setLayout(ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
        this.layout = constraintLayout;
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

    public final TextView getTertiaryText() {
        TextView textView = this.tertiaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.TERTIARY_TEXT);
        return null;
    }

    public final void setTertiaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.tertiaryText = textView;
    }

    public final LinearLayout getActionButtons() {
        LinearLayout linearLayout = this.actionButtons;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("actionButtons");
        return null;
    }

    public final void setActionButtons(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.actionButtons = linearLayout;
    }
}
