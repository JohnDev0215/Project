package com.amazon.mp3.language.preference.fragment.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.view.KatanaRecyclerviewBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppDisplayLanguagePreferenceBinding.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/language/preference/fragment/view/AppDisplayLanguagePreferenceBinding;", "", "()V", "cancelButton", "Landroid/widget/Button;", "getCancelButton", "()Landroid/widget/Button;", "setCancelButton", "(Landroid/widget/Button;)V", "saveAndRestartButton", "getSaveAndRestartButton", "setSaveAndRestartButton", "showDisplayLanguageRecyclerView", "Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "getShowDisplayLanguageRecyclerView", "()Lcom/amazon/mp3/fragment/view/KatanaRecyclerviewBinding;", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "setTitleView", "(Landroid/widget/TextView;)V", "setView", "", "rootView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppDisplayLanguagePreferenceBinding {
    public Button cancelButton;
    public Button saveAndRestartButton;
    private final KatanaRecyclerviewBinding showDisplayLanguageRecyclerView = new KatanaRecyclerviewBinding();
    public TextView titleView;

    public final TextView getTitleView() {
        TextView textView = this.titleView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titleView");
        return null;
    }

    public final void setTitleView(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleView = textView;
    }

    public final KatanaRecyclerviewBinding getShowDisplayLanguageRecyclerView() {
        return this.showDisplayLanguageRecyclerView;
    }

    public final Button getSaveAndRestartButton() {
        Button button = this.saveAndRestartButton;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("saveAndRestartButton");
        return null;
    }

    public final void setSaveAndRestartButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.saveAndRestartButton = button;
    }

    public final Button getCancelButton() {
        Button button = this.cancelButton;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cancelButton");
        return null;
    }

    public final void setCancelButton(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.cancelButton = button;
    }

    public final void setView(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        KatanaRecyclerviewBinding katanaRecyclerviewBinding = this.showDisplayLanguageRecyclerView;
        View viewFindViewById = rootView.findViewById(R.id.preferences_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.preferences_container)");
        katanaRecyclerviewBinding.setView((RecyclerView) viewFindViewById);
        View viewFindViewById2 = rootView.findViewById(R.id.alertTitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.id.alertTitle)");
        setTitleView((TextView) viewFindViewById2);
        View viewFindViewById3 = rootView.findViewById(R.id.saveAndRestart);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.saveAndRestart)");
        setSaveAndRestartButton((Button) viewFindViewById3);
        View viewFindViewById4 = rootView.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.id.cancel)");
        setCancelButton((Button) viewFindViewById4);
    }
}
