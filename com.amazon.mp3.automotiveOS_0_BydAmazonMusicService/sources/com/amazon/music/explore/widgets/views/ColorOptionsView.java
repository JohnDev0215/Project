package com.amazon.music.explore.widgets.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ColorOptionsView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/explore/widgets/views/ColorOptionsView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "colorContainer", "Landroid/widget/LinearLayout;", "getColorContainer", "()Landroid/widget/LinearLayout;", "setColorContainer", "(Landroid/widget/LinearLayout;)V", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "setText", "(Landroid/widget/TextView;)V", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ColorOptionsView extends ConstraintLayout {

    @ViewFinder.AView
    public LinearLayout colorContainer;

    @ViewFinder.AView
    public TextView text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorOptionsView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.color_options_view), (Object) null, 1, (Object) null);
    }

    public final TextView getText() {
        TextView textView = this.text;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("text");
        return null;
    }

    public final void setText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.text = textView;
    }

    public final LinearLayout getColorContainer() {
        LinearLayout linearLayout = this.colorContainer;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("colorContainer");
        return null;
    }

    public final void setColorContainer(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.colorContainer = linearLayout;
    }
}
