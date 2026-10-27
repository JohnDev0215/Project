package com.amazon.mp3.fragment.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentBetaDialogBinding.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/fragment/view/FragmentBetaDialogBinding;", "", "()V", "mCloseDialogIcon", "Landroid/widget/ImageView;", "getMCloseDialogIcon", "()Landroid/widget/ImageView;", "setMCloseDialogIcon", "(Landroid/widget/ImageView;)V", "mMessageView", "Landroid/widget/TextView;", "getMMessageView", "()Landroid/widget/TextView;", "setMMessageView", "(Landroid/widget/TextView;)V", "mPrimaryButton", "Landroid/widget/Button;", "getMPrimaryButton", "()Landroid/widget/Button;", "setMPrimaryButton", "(Landroid/widget/Button;)V", "mTitleView", "getMTitleView", "setMTitleView", "setView", "", "rootView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentBetaDialogBinding {
    public ImageView mCloseDialogIcon;
    public TextView mMessageView;
    public Button mPrimaryButton;
    public TextView mTitleView;

    public final Button getMPrimaryButton() {
        Button button = this.mPrimaryButton;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPrimaryButton");
        return null;
    }

    public final void setMPrimaryButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.mPrimaryButton = button;
    }

    public final ImageView getMCloseDialogIcon() {
        ImageView imageView = this.mCloseDialogIcon;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCloseDialogIcon");
        return null;
    }

    public final void setMCloseDialogIcon(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.mCloseDialogIcon = imageView;
    }

    public final TextView getMTitleView() {
        TextView textView = this.mTitleView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mTitleView");
        return null;
    }

    public final void setMTitleView(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mTitleView = textView;
    }

    public final TextView getMMessageView() {
        TextView textView = this.mMessageView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMessageView");
        return null;
    }

    public final void setMMessageView(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mMessageView = textView;
    }

    public final void setView(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        View viewFindViewById = rootView.findViewById(R.id.primary_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.primary_button)");
        setMPrimaryButton((Button) viewFindViewById);
        View viewFindViewById2 = rootView.findViewById(R.id.icon_dismiss);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.id.icon_dismiss)");
        setMCloseDialogIcon((ImageView) viewFindViewById2);
        View viewFindViewById3 = rootView.findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.tv_title)");
        setMTitleView((TextView) viewFindViewById3);
        View viewFindViewById4 = rootView.findViewById(R.id.tv_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.id.tv_message)");
        setMMessageView((TextView) viewFindViewById4);
    }
}
