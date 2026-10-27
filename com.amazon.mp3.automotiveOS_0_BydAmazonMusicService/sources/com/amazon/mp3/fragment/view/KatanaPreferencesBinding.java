package com.amazon.mp3.fragment.view;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.view.SpannableTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KatanaPreferencesBinding.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u00103\u001a\u0002042\u0006\u00105\u001a\u000206R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u001a\u0010-\u001a\u00020\u001cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001e\"\u0004\b/\u0010 R\u001a\u00100\u001a\u00020\u001cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001e\"\u0004\b2\u0010 ¨\u00067"}, d2 = {"Lcom/amazon/mp3/fragment/view/KatanaPreferencesBinding;", "", "()V", "mCancelButton", "Landroid/widget/Button;", "getMCancelButton", "()Landroid/widget/Button;", "setMCancelButton", "(Landroid/widget/Button;)V", "mDownloadOnWifiCheckbox", "Landroid/widget/CheckBox;", "getMDownloadOnWifiCheckbox", "()Landroid/widget/CheckBox;", "setMDownloadOnWifiCheckbox", "(Landroid/widget/CheckBox;)V", "mKatanaRecyclerViewBinding", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "getMKatanaRecyclerViewBinding", "()Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "mKatanaRecyclerViewBindingForWifi", "getMKatanaRecyclerViewBindingForWifi", "mMessageView", "Lcom/amazon/mp3/view/SpannableTextView;", "getMMessageView", "()Lcom/amazon/mp3/view/SpannableTextView;", "setMMessageView", "(Lcom/amazon/mp3/view/SpannableTextView;)V", "mMobileDataTitle", "Landroid/widget/TextView;", "getMMobileDataTitle", "()Landroid/widget/TextView;", "setMMobileDataTitle", "(Landroid/widget/TextView;)V", "mNetworkPreferenceContainer", "Landroid/widget/LinearLayout;", "getMNetworkPreferenceContainer", "()Landroid/widget/LinearLayout;", "setMNetworkPreferenceContainer", "(Landroid/widget/LinearLayout;)V", "mSaveButton", "getMSaveButton", "setMSaveButton", "mSummaryHeader", "getMSummaryHeader", "setMSummaryHeader", "mTitleView", "getMTitleView", "setMTitleView", "mWifiSummary", "getMWifiSummary", "setMWifiSummary", "setView", "", "rootView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class KatanaPreferencesBinding {
    public Button mCancelButton;
    public CheckBox mDownloadOnWifiCheckbox;
    private final KatanaRecyclerviewBinding mKatanaRecyclerViewBinding = new KatanaRecyclerviewBinding();
    private final KatanaRecyclerviewBinding mKatanaRecyclerViewBindingForWifi = new KatanaRecyclerviewBinding();
    public SpannableTextView mMessageView;
    public TextView mMobileDataTitle;
    public LinearLayout mNetworkPreferenceContainer;
    public Button mSaveButton;
    public LinearLayout mSummaryHeader;
    public TextView mTitleView;
    public TextView mWifiSummary;

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

    public final KatanaRecyclerviewBinding getMKatanaRecyclerViewBinding() {
        return this.mKatanaRecyclerViewBinding;
    }

    public final KatanaRecyclerviewBinding getMKatanaRecyclerViewBindingForWifi() {
        return this.mKatanaRecyclerViewBindingForWifi;
    }

    public final CheckBox getMDownloadOnWifiCheckbox() {
        CheckBox checkBox = this.mDownloadOnWifiCheckbox;
        if (checkBox != null) {
            return checkBox;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mDownloadOnWifiCheckbox");
        return null;
    }

    public final void setMDownloadOnWifiCheckbox(CheckBox checkBox) {
        Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
        this.mDownloadOnWifiCheckbox = checkBox;
    }

    public final SpannableTextView getMMessageView() {
        SpannableTextView spannableTextView = this.mMessageView;
        if (spannableTextView != null) {
            return spannableTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMessageView");
        return null;
    }

    public final void setMMessageView(SpannableTextView spannableTextView) {
        Intrinsics.checkNotNullParameter(spannableTextView, "<set-?>");
        this.mMessageView = spannableTextView;
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

    public final LinearLayout getMSummaryHeader() {
        LinearLayout linearLayout = this.mSummaryHeader;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSummaryHeader");
        return null;
    }

    public final void setMSummaryHeader(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.mSummaryHeader = linearLayout;
    }

    public final TextView getMMobileDataTitle() {
        TextView textView = this.mMobileDataTitle;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMobileDataTitle");
        return null;
    }

    public final void setMMobileDataTitle(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mMobileDataTitle = textView;
    }

    public final TextView getMWifiSummary() {
        TextView textView = this.mWifiSummary;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mWifiSummary");
        return null;
    }

    public final void setMWifiSummary(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.mWifiSummary = textView;
    }

    public final LinearLayout getMNetworkPreferenceContainer() {
        LinearLayout linearLayout = this.mNetworkPreferenceContainer;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mNetworkPreferenceContainer");
        return null;
    }

    public final void setMNetworkPreferenceContainer(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.mNetworkPreferenceContainer = linearLayout;
    }

    public final void setView(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        KatanaRecyclerviewBinding katanaRecyclerviewBinding = this.mKatanaRecyclerViewBinding;
        View viewFindViewById = rootView.findViewById(R.id.preferences_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.preferences_container)");
        katanaRecyclerviewBinding.setView((RecyclerView) viewFindViewById);
        KatanaRecyclerviewBinding katanaRecyclerviewBinding2 = this.mKatanaRecyclerViewBindingForWifi;
        View viewFindViewById2 = rootView.findViewById(R.id.preferences_container_wifi);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.…eferences_container_wifi)");
        katanaRecyclerviewBinding2.setView((RecyclerView) viewFindViewById2);
        View viewFindViewById3 = rootView.findViewById(R.id.alertTitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.alertTitle)");
        setMTitleView((TextView) viewFindViewById3);
        View viewFindViewById4 = rootView.findViewById(R.id.cb_download_on_wifi);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.id.cb_download_on_wifi)");
        setMDownloadOnWifiCheckbox((CheckBox) viewFindViewById4);
        View viewFindViewById5 = rootView.findViewById(R.id.tv_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "rootView.findViewById(R.id.tv_message)");
        setMMessageView((SpannableTextView) viewFindViewById5);
        View viewFindViewById6 = rootView.findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "rootView.findViewById(R.id.save)");
        setMSaveButton((Button) viewFindViewById6);
        View viewFindViewById7 = rootView.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "rootView.findViewById(R.id.cancel)");
        setMCancelButton((Button) viewFindViewById7);
        View viewFindViewById8 = rootView.findViewById(R.id.networkPreferenceContainer);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "rootView.findViewById(R.…tworkPreferenceContainer)");
        setMNetworkPreferenceContainer((LinearLayout) viewFindViewById8);
        View viewFindViewById9 = rootView.findViewById(R.id.summary_header);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "rootView.findViewById(R.id.summary_header)");
        setMSummaryHeader((LinearLayout) viewFindViewById9);
        View viewFindViewById10 = rootView.findViewById(R.id.mobileDataTitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "rootView.findViewById(R.id.mobileDataTitle)");
        setMMobileDataTitle((TextView) viewFindViewById10);
        View viewFindViewById11 = rootView.findViewById(R.id.wifiSummary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "rootView.findViewById(R.id.wifiSummary)");
        setMWifiSummary((TextView) viewFindViewById11);
    }
}
