package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SectionView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/SectionView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "itemContainer", "Landroid/widget/LinearLayout;", "getItemContainer", "()Landroid/widget/LinearLayout;", "setItemContainer", "(Landroid/widget/LinearLayout;)V", "primaryHeader", "Landroid/widget/TextView;", "getPrimaryHeader", "()Landroid/widget/TextView;", "setPrimaryHeader", "(Landroid/widget/TextView;)V", "secondaryHeader", "getSecondaryHeader", "setSecondaryHeader", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SectionView extends ConstraintLayout {

    @ViewFinder.AView
    public LinearLayout itemContainer;

    @ViewFinder.AView
    public TextView primaryHeader;

    @ViewFinder.AView
    public TextView secondaryHeader;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SectionView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.section_view), (Object) null, 1, (Object) null);
    }

    public final TextView getPrimaryHeader() {
        TextView textView = this.primaryHeader;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("primaryHeader");
        return null;
    }

    public final void setPrimaryHeader(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.primaryHeader = textView;
    }

    public final TextView getSecondaryHeader() {
        TextView textView = this.secondaryHeader;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("secondaryHeader");
        return null;
    }

    public final void setSecondaryHeader(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.secondaryHeader = textView;
    }

    public final LinearLayout getItemContainer() {
        LinearLayout linearLayout = this.itemContainer;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("itemContainer");
        return null;
    }

    public final void setItemContainer(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.itemContainer = linearLayout;
    }
}
