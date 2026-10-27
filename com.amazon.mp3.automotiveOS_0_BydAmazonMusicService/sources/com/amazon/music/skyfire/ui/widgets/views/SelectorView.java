package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectorView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/SelectorView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "setIcon", "(Landroid/widget/ImageView;)V", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "setText", "(Landroid/widget/TextView;)V", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectorView extends ConstraintLayout {

    @ViewFinder.AView
    public ImageView icon;

    @ViewFinder.AView
    public TextView text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectorView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.selector_view), (Object) null, 1, (Object) null);
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
}
