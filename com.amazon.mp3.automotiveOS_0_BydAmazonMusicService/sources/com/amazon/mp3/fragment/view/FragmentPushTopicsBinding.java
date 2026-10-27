package com.amazon.mp3.fragment.view;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentPushTopicsBinding.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001a\u0010\u001b\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001a\u0010\u001e\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000eR\u001a\u0010!\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006+"}, d2 = {"Lcom/amazon/mp3/fragment/view/FragmentPushTopicsBinding;", "", "()V", "mCancelButton", "Landroid/widget/Button;", "getMCancelButton", "()Landroid/widget/Button;", "setMCancelButton", "(Landroid/widget/Button;)V", "mFifthPushTopicCheckbox", "Landroid/widget/CheckBox;", "getMFifthPushTopicCheckbox", "()Landroid/widget/CheckBox;", "setMFifthPushTopicCheckbox", "(Landroid/widget/CheckBox;)V", "mFirstPushTopicCheckbox", "getMFirstPushTopicCheckbox", "setMFirstPushTopicCheckbox", "mFourthPushTopicCheckbox", "getMFourthPushTopicCheckbox", "setMFourthPushTopicCheckbox", "mSaveButton", "getMSaveButton", "setMSaveButton", "mSecondPushTopicCheckbox", "getMSecondPushTopicCheckbox", "setMSecondPushTopicCheckbox", "mSixthPushTopicCheckbox", "getMSixthPushTopicCheckbox", "setMSixthPushTopicCheckbox", "mThirdPushTopicCheckbox", "getMThirdPushTopicCheckbox", "setMThirdPushTopicCheckbox", "mTitleView", "Landroid/widget/TextView;", "getMTitleView", "()Landroid/widget/TextView;", "setMTitleView", "(Landroid/widget/TextView;)V", "setView", "", "rootView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentPushTopicsBinding {
    public Button mCancelButton;
    public CheckBox mFifthPushTopicCheckbox;
    public CheckBox mFirstPushTopicCheckbox;
    public CheckBox mFourthPushTopicCheckbox;
    public Button mSaveButton;
    public CheckBox mSecondPushTopicCheckbox;
    public CheckBox mSixthPushTopicCheckbox;
    public CheckBox mThirdPushTopicCheckbox;
    public TextView mTitleView;

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

    public final CheckBox getMFirstPushTopicCheckbox() {
        CheckBox checkBox = this.mFirstPushTopicCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFirstPushTopicCheckbox");
        return null;
    }

    public final void setMFirstPushTopicCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mFirstPushTopicCheckbox = checkBox;
    }

    public final CheckBox getMSecondPushTopicCheckbox() {
        CheckBox checkBox = this.mSecondPushTopicCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSecondPushTopicCheckbox");
        return null;
    }

    public final void setMSecondPushTopicCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mSecondPushTopicCheckbox = checkBox;
    }

    public final CheckBox getMThirdPushTopicCheckbox() {
        CheckBox checkBox = this.mThirdPushTopicCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mThirdPushTopicCheckbox");
        return null;
    }

    public final void setMThirdPushTopicCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mThirdPushTopicCheckbox = checkBox;
    }

    public final CheckBox getMFourthPushTopicCheckbox() {
        CheckBox checkBox = this.mFourthPushTopicCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFourthPushTopicCheckbox");
        return null;
    }

    public final void setMFourthPushTopicCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mFourthPushTopicCheckbox = checkBox;
    }

    public final CheckBox getMFifthPushTopicCheckbox() {
        CheckBox checkBox = this.mFifthPushTopicCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFifthPushTopicCheckbox");
        return null;
    }

    public final void setMFifthPushTopicCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mFifthPushTopicCheckbox = checkBox;
    }

    public final CheckBox getMSixthPushTopicCheckbox() {
        CheckBox checkBox = this.mSixthPushTopicCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSixthPushTopicCheckbox");
        return null;
    }

    public final void setMSixthPushTopicCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mSixthPushTopicCheckbox = checkBox;
    }

    public final Button getMSaveButton() {
        Button button = this.mSaveButton;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSaveButton");
        return null;
    }

    public final void setMSaveButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.mSaveButton = button;
    }

    public final Button getMCancelButton() {
        Button button = this.mCancelButton;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCancelButton");
        return null;
    }

    public final void setMCancelButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.mCancelButton = button;
    }

    public final void setView(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        View viewFindViewById = rootView.findViewById(R.id.pushalertTitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.pushalertTitle)");
        setMTitleView((TextView) viewFindViewById);
        View viewFindViewById2 = rootView.findViewById(R.id.cb_dmusic_subscription_music_promo);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.…subscription_music_promo)");
        setMFirstPushTopicCheckbox((CheckBox) viewFindViewById2);
        View viewFindViewById3 = rootView.findViewById(R.id.cb_dmusic_subscription_music_social);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.…ubscription_music_social)");
        setMSecondPushTopicCheckbox((CheckBox) viewFindViewById3);
        View viewFindViewById4 = rootView.findViewById(R.id.cb_dmusic_subscription_music_live_stream);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.…iption_music_live_stream)");
        setMThirdPushTopicCheckbox((CheckBox) viewFindViewById4);
        View viewFindViewById5 = rootView.findViewById(R.id.cb_dmusic_subscription_music_merch);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "rootView.findViewById(R.…subscription_music_merch)");
        setMFourthPushTopicCheckbox((CheckBox) viewFindViewById5);
        View viewFindViewById6 = rootView.findViewById(R.id.cb_dmusic_subscription_music_podcast);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "rootView.findViewById(R.…bscription_music_podcast)");
        setMFifthPushTopicCheckbox((CheckBox) viewFindViewById6);
        View viewFindViewById7 = rootView.findViewById(R.id.cb_dmusic_subscription_music_video);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "rootView.findViewById(R.…subscription_music_video)");
        setMSixthPushTopicCheckbox((CheckBox) viewFindViewById7);
        View viewFindViewById8 = rootView.findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "rootView.findViewById(R.id.save)");
        setMSaveButton((Button) viewFindViewById8);
        View viewFindViewById9 = rootView.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "rootView.findViewById(R.id.cancel)");
        setMCancelButton((Button) viewFindViewById9);
    }
}
