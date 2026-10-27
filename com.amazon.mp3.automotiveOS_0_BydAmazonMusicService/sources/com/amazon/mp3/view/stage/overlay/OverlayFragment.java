package com.amazon.mp3.view.stage.overlay;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ViewExtensions;
import com.amazon.mp3.view.stage.overlay.OverlayPosition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004J&\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayFragment;", "Landroidx/fragment/app/Fragment;", "()V", "overlayPosition", "Lcom/amazon/mp3/view/stage/overlay/OverlayPosition;", "overlayView", "Landroid/view/View;", "initWith", "", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OverlayFragment extends Fragment {
    private static final String TAG = OverlayFragment.class.getSimpleName();
    private OverlayPosition overlayPosition;
    private View overlayView;

    public final void initWith(View view, OverlayPosition overlayPosition) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(overlayPosition, "overlayPosition");
        this.overlayView = view;
        this.overlayPosition = overlayPosition;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ConstraintSet constraintSet;
        Resources resources;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Log.debug(TAG, "onCreateView");
        int dimensionPixelSize = 0;
        View viewInflate = inflater.inflate(R.layout.stage_overlay_layout, container, false);
        if (this.overlayView == null) {
            return viewInflate;
        }
        View view = this.overlayView;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int i = layoutParams == null ? -1 : layoutParams.width;
        View view3 = this.overlayView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            view3 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = view3.getLayoutParams();
        viewInflate.setLayoutParams(new ConstraintLayout.LayoutParams(i, layoutParams2 != null ? layoutParams2.height : -1));
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate.findViewById(R.id.OverlayRoot);
        ConstraintSet constraintSet2 = new ConstraintSet();
        constraintSet2.clone(constraintLayout);
        OverlayPosition overlayPosition = this.overlayPosition;
        if (overlayPosition == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayPosition");
            overlayPosition = null;
        }
        if (overlayPosition instanceof OverlayPosition.Top) {
            View view4 = this.overlayView;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                view4 = null;
            }
            int id = view4.getId();
            int id2 = constraintLayout.getId();
            Context context = getContext();
            if (context != null && (resources = context.getResources()) != null) {
                dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.spacer_96);
            }
            constraintSet2.connect(id, 3, id2, 3, dimensionPixelSize);
            constraintSet2.applyTo(constraintLayout);
        } else if (overlayPosition instanceof OverlayPosition.Custom) {
            OverlayPosition overlayPosition2 = this.overlayPosition;
            if (overlayPosition2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayPosition");
                overlayPosition2 = null;
            }
            OverlayPosition.Custom custom = overlayPosition2 instanceof OverlayPosition.Custom ? (OverlayPosition.Custom) overlayPosition2 : null;
            if (custom != null && (constraintSet = custom.getConstraintSet()) != null) {
                constraintSet.applyTo(constraintLayout);
            }
        }
        View view5 = this.overlayView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            view5 = null;
        }
        ViewExtensions.removeFromParent(view5);
        if (constraintLayout != null) {
            View view6 = this.overlayView;
            if (view6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            } else {
                view2 = view6;
            }
            constraintLayout.addView(view2);
        }
        return viewInflate;
    }
}
