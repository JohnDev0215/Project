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
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SecondaryGenericHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00018\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R\u001e\u0010\u001f\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R\u001e\u0010\"\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016¨\u0006%"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/SecondaryGenericHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionButtons", "Landroid/widget/LinearLayout;", "getActionButtons", "()Landroid/widget/LinearLayout;", "setActionButtons", "(Landroid/widget/LinearLayout;)V", "callToActionButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCallToActionButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "setCallToActionButton", "(Lcom/amazon/ui/foundations/views/BaseButton;)V", ContextMappingConstants.LABEL, "Landroid/widget/TextView;", "getLabel", "()Landroid/widget/TextView;", "setLabel", "(Landroid/widget/TextView;)V", "layout", "getLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setLayout", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "setPrimaryText", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "setTertiaryText", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SecondaryGenericHeaderView extends ConstraintLayout {

    @ViewFinder.AView
    public LinearLayout actionButtons;

    @ViewFinder.AView
    public BaseButton callToActionButton;

    @ViewFinder.AView
    public TextView label;

    @ViewFinder.AView
    public ConstraintLayout layout;

    @ViewFinder.AView
    public TextView primaryText;

    @ViewFinder.AView
    public TextView secondaryText;

    @ViewFinder.AView
    public TextView tertiaryText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecondaryGenericHeaderView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.secondary_generic_header_view), (Object) null, 1, (Object) null);
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
