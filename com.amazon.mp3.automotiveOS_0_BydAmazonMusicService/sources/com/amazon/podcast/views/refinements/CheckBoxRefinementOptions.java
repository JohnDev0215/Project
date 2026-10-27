package com.amazon.podcast.views.refinements;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.View;
import android.widget.PopupWindow;
import androidx.fragment.app.FragmentManager;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$color;
import com.amazon.podcast.views.OverlayViewFragment;
import com.amazon.podcast.views.PopUpUtils;
import com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsView;

/* JADX INFO: loaded from: classes5.dex */
public final class CheckBoxRefinementOptions {
    private static PopupWindow currentRefinementOptionsPopUp;

    public static void show(final Context context, final String ownerId, final String selectedOption, final View refinementButton, final FragmentManager fragmentManager, final CheckBoxRefinementEventsListener listener, final MethodsDispatcher methodsDispatcher, final CheckBoxRefinementOptionElement element) {
        if (PopUpUtils.shouldUsePopUp(context.getResources())) {
            showRefinementPopUp(context, ownerId, selectedOption, listener, methodsDispatcher, refinementButton, element);
        } else {
            showRefinementOverlay(context, ownerId, selectedOption, listener, methodsDispatcher, fragmentManager, element);
        }
    }

    public static void dismissRefinementOptionsPopUp() {
        PopupWindow popupWindow = currentRefinementOptionsPopUp;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    private static void showRefinementPopUp(final Context context, final String ownerId, final String selectedOption, final CheckBoxRefinementEventsListener listener, final MethodsDispatcher methodsDispatcher, final View refinementButton, final CheckBoxRefinementOptionElement element) {
        final PopupWindow popupWindow = new PopupWindow(context);
        CheckBoxRefinementOptionsView checkBoxRefinementOptionsView = new CheckBoxRefinementOptionsView(context);
        checkBoxRefinementOptionsView.bind(ownerId, true, selectedOption, methodsDispatcher, new CheckBoxRefinementOptionsView.DismissListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptions.1
            @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsView.DismissListener
            public void onDismissClicked() {
                popupWindow.dismiss();
            }
        }, listener, element);
        Resources resources = context.getResources();
        popupWindow.setBackgroundDrawable(resources.getDrawable(R$color.transparent));
        popupWindow.setContentView(checkBoxRefinementOptionsView);
        popupWindow.setOutsideTouchable(true);
        int iComputePopUpWidth = PopUpUtils.computePopUpWidth(checkBoxRefinementOptionsView, resources);
        int iComputePopUpHeight = computePopUpHeight(checkBoxRefinementOptionsView);
        popupWindow.setWidth(iComputePopUpWidth);
        popupWindow.setHeight(iComputePopUpHeight);
        PopUpUtils.placeRefinementPopUpWindow(refinementButton, popupWindow, iComputePopUpWidth, iComputePopUpHeight);
        setPopUpOnDismissListener(getActivity(context), popupWindow, element);
        currentRefinementOptionsPopUp = popupWindow;
    }

    private static void showRefinementOverlay(final Context context, final String ownerId, final String selectedOption, final CheckBoxRefinementEventsListener listener, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final CheckBoxRefinementOptionElement element) {
        CheckBoxRefinementOptionsView checkBoxRefinementOptionsView = new CheckBoxRefinementOptionsView(context);
        final OverlayViewFragment overlayViewFragment = new OverlayViewFragment();
        checkBoxRefinementOptionsView.bind(ownerId, false, selectedOption, methodsDispatcher, new CheckBoxRefinementOptionsView.DismissListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptions.2
            @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsView.DismissListener
            public void onDismissClicked() {
                overlayViewFragment.dismiss();
            }
        }, listener, element);
        overlayViewFragment.bind(checkBoxRefinementOptionsView);
        overlayViewFragment.show(fragmentManager, "");
    }

    private static void setPopUpOnDismissListener(final Activity activity, final PopupWindow popupWindow, final CheckBoxRefinementOptionElement itemElement) {
        if (activity == null) {
            return;
        }
        final int requestedOrientation = activity.getRequestedOrientation();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptions.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                PopupWindow unused = CheckBoxRefinementOptions.currentRefinementOptionsPopUp = null;
                activity.setRequestedOrientation(requestedOrientation);
            }
        });
        activity.setRequestedOrientation(14);
    }

    private static Activity getActivity(final Context context) {
        if (context == null || !(context instanceof ContextWrapper)) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return getActivity(((ContextWrapper) context).getBaseContext());
    }

    private static int computePopUpHeight(final View view) {
        view.measure(0, 0);
        return view.getMeasuredHeight();
    }
}
