package com.amazon.music.inappmessaging.internal.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;

/* JADX INFO: loaded from: classes4.dex */
public class IAMLoadingSpinnerFragment extends DialogFragment {
    private boolean shouldShowEnterAnimation = true;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return (FrameLayout) inflater.inflate(R.layout.iam_loading_spinner_view, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || dialog.getWindow() == null) {
            return;
        }
        if (this.shouldShowEnterAnimation) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.LoadingSpinnerFadeInOutAnimation;
            this.shouldShowEnterAnimation = false;
        } else {
            dialog.getWindow().getAttributes().windowAnimations = R.style.LoadingSpinnerFadeOutAnimation;
        }
        dialog.getWindow().setLayout(-1, -1);
    }
}
