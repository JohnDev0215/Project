package com.amazon.podcast.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$style;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public final class OverlayViewFragment extends BottomSheetDialogFragment {
    private Activity activity;
    private View attachedView;
    private Runnable onDismiss;
    private int originalOrientationSetting;

    public OverlayViewFragment() {
    }

    public OverlayViewFragment(final Runnable onDismiss) {
        this.onDismiss = onDismiss;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R$style.CustomBottomSheetDialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.podcast.views.OverlayViewFragment.1
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(final DialogInterface dialog) {
                FrameLayout frameLayout = (FrameLayout) ((BottomSheetDialog) dialog).findViewById(R$id.design_bottom_sheet);
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) frameLayout.getParent();
                BottomSheetBehavior.from(frameLayout).setPeekHeight(frameLayout.getHeight());
                coordinatorLayout.getParent().requestLayout();
            }
        });
        return this.attachedView;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle savedInstance) {
        FragmentActivity activity = getActivity();
        this.activity = activity;
        if (activity != null) {
            this.originalOrientationSetting = activity.getRequestedOrientation();
            this.activity.setRequestedOrientation(14);
        }
        super.onActivityCreated(savedInstance);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        dismissAllowingStateLoss();
        super.onPause();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        Activity activity = this.activity;
        if (activity != null) {
            activity.setRequestedOrientation(this.originalOrientationSetting);
        }
        super.onStop();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Runnable runnable = this.onDismiss;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        Runnable runnable = this.onDismiss;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public void bind(final View view) {
        this.attachedView = view;
    }
}
