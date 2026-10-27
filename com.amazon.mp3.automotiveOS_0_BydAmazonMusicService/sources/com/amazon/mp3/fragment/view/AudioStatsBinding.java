package com.amazon.mp3.fragment.view;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStatsBinding.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001a\u0010\u0019\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006+"}, d2 = {"Lcom/amazon/mp3/fragment/view/AudioStatsBinding;", "", "()V", "mGotItButton", "Landroid/widget/TextView;", "getMGotItButton", "()Landroid/widget/TextView;", "setMGotItButton", "(Landroid/widget/TextView;)V", "mImmersiveContent", "Landroid/widget/LinearLayout;", "getMImmersiveContent", "()Landroid/widget/LinearLayout;", "setMImmersiveContent", "(Landroid/widget/LinearLayout;)V", "mImmersiveNerdStatDescription", "getMImmersiveNerdStatDescription", "setMImmersiveNerdStatDescription", "mKatanaRecyclerViewBinding", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "getMKatanaRecyclerViewBinding", "()Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "mMessageView", "getMMessageView", "setMMessageView", "mPageTitleView", "getMPageTitleView", "setMPageTitleView", "mSeparatorMiddle", "Landroid/view/View;", "getMSeparatorMiddle", "()Landroid/view/View;", "setMSeparatorMiddle", "(Landroid/view/View;)V", "mUpdateAudioSettingsButton", "Landroid/widget/Button;", "getMUpdateAudioSettingsButton", "()Landroid/widget/Button;", "setMUpdateAudioSettingsButton", "(Landroid/widget/Button;)V", "setView", "", "rootView", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AudioStatsBinding {
    public TextView mGotItButton;
    public LinearLayout mImmersiveContent;
    public TextView mImmersiveNerdStatDescription;
    private final KatanaRecyclerviewBinding mKatanaRecyclerViewBinding = new KatanaRecyclerviewBinding();
    public TextView mMessageView;
    public TextView mPageTitleView;
    public View mSeparatorMiddle;
    public Button mUpdateAudioSettingsButton;

    public final TextView getMPageTitleView() {
        TextView textView = this.mPageTitleView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPageTitleView");
        return null;
    }

    public final void setMPageTitleView(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mPageTitleView = textView;
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

    public final TextView getMImmersiveNerdStatDescription() {
        TextView textView = this.mImmersiveNerdStatDescription;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mImmersiveNerdStatDescription");
        return null;
    }

    public final void setMImmersiveNerdStatDescription(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mImmersiveNerdStatDescription = textView;
    }

    public final Button getMUpdateAudioSettingsButton() {
        Button button = this.mUpdateAudioSettingsButton;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mUpdateAudioSettingsButton");
        return null;
    }

    public final void setMUpdateAudioSettingsButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.mUpdateAudioSettingsButton = button;
    }

    public final KatanaRecyclerviewBinding getMKatanaRecyclerViewBinding() {
        return this.mKatanaRecyclerViewBinding;
    }

    public final LinearLayout getMImmersiveContent() {
        LinearLayout linearLayout = this.mImmersiveContent;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mImmersiveContent");
        return null;
    }

    public final void setMImmersiveContent(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.mImmersiveContent = linearLayout;
    }

    public final View getMSeparatorMiddle() {
        View view = this.mSeparatorMiddle;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSeparatorMiddle");
        return null;
    }

    public final void setMSeparatorMiddle(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.mSeparatorMiddle = view;
    }

    public final TextView getMGotItButton() {
        TextView textView = this.mGotItButton;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mGotItButton");
        return null;
    }

    public final void setMGotItButton(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mGotItButton = textView;
    }

    public final void setView(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        KatanaRecyclerviewBinding katanaRecyclerviewBinding = this.mKatanaRecyclerViewBinding;
        View viewFindViewById = rootView.findViewById(R.id.preferences_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.preferences_container)");
        katanaRecyclerviewBinding.setView((RecyclerView) viewFindViewById);
        View viewFindViewById2 = rootView.findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.id.tv_title)");
        setMPageTitleView((TextView) viewFindViewById2);
        View viewFindViewById3 = rootView.findViewById(R.id.tv_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.tv_message)");
        setMMessageView((TextView) viewFindViewById3);
        View viewFindViewById4 = rootView.findViewById(R.id.immersive_badge_nerd_stat_description);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.…ge_nerd_stat_description)");
        setMImmersiveNerdStatDescription((TextView) viewFindViewById4);
        View viewFindViewById5 = rootView.findViewById(R.id.update_streaming_quality_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "rootView.findViewById(R.…streaming_quality_button)");
        setMUpdateAudioSettingsButton((Button) viewFindViewById5);
        View viewFindViewById6 = rootView.findViewById(R.id.immersiveContent);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "rootView.findViewById(R.id.immersiveContent)");
        setMImmersiveContent((LinearLayout) viewFindViewById6);
        View viewFindViewById7 = rootView.findViewById(R.id.separatorMiddle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "rootView.findViewById(R.id.separatorMiddle)");
        setMSeparatorMiddle(viewFindViewById7);
        View viewFindViewById8 = rootView.findViewById(R.id.got_it_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "rootView.findViewById(R.id.got_it_button)");
        setMGotItButton((TextView) viewFindViewById8);
    }
}
