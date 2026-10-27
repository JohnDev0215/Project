package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import androidx.fragment.app.FragmentManager;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$color;
import com.amazon.podcast.views.OverlayViewFragment;
import com.amazon.podcast.views.PopUpUtils;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCBottomSheet {
    private static PopupWindow currentBottomSheetPopUp;

    public static void bindBottomSheet(final GridItemElement gridItemElement, final boolean isSelected, final Context context, final Resources resources, final ImageButton aboutButton, final String ownerId, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final PTCGridItemView ptcGridItemView, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener) {
        if (PopUpUtils.shouldUsePopUp(resources)) {
            showBottomSheetPopUp(gridItemElement, aboutButton, context, resources, methodsDispatcher, ownerId, isSelected, ptcGridItemView, bottomSheetSelectedListener);
        } else {
            showBottomSheetOverlay(gridItemElement, context, methodsDispatcher, ownerId, fragmentManager, isSelected, ptcGridItemView, bottomSheetSelectedListener);
        }
    }

    private static void showBottomSheetOverlay(final GridItemElement gridItemElement, final Context context, final MethodsDispatcher methodsDispatcher, final String ownerId, final FragmentManager fragmentManager, final boolean isSelected, final PTCGridItemView ptcGridItemView, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener) {
        final PTCBottomSheetTemplateView pTCBottomSheetTemplateView = new PTCBottomSheetTemplateView(context, ownerId, methodsDispatcher, isSelected, gridItemElement, ptcGridItemView, bottomSheetSelectedListener, null, null);
        OverlayViewFragment overlayViewFragment = new OverlayViewFragment(new Runnable() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheet.1
            @Override // java.lang.Runnable
            public void run() {
                bottomSheetSelectedListener.onAboutSelected(null, null);
                if (pTCBottomSheetTemplateView.getIsTrailerPlayed()) {
                    pTCBottomSheetTemplateView.playView.stopTrailerPlaybackOnAboutDismiss();
                }
            }
        });
        pTCBottomSheetTemplateView.setOverlayViewFragment(overlayViewFragment);
        pTCBottomSheetTemplateView.bind(gridItemElement.getAbout());
        overlayViewFragment.bind(pTCBottomSheetTemplateView);
        overlayViewFragment.show(fragmentManager, "PTCGridItem");
    }

    private static void showBottomSheetPopUp(final GridItemElement gridItemElement, final ImageButton aboutButton, final Context context, final Resources resources, final MethodsDispatcher methodsDispatcher, final String ownerId, final boolean isSelected, final PTCGridItemView ptcGridItemView, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener) {
        PopupWindow popupWindow = new PopupWindow(context);
        PTCBottomSheetTemplateView pTCBottomSheetTemplateView = new PTCBottomSheetTemplateView(context, ownerId, methodsDispatcher, isSelected, gridItemElement, ptcGridItemView, bottomSheetSelectedListener, null, popupWindow);
        popupWindow.setBackgroundDrawable(resources.getDrawable(R$color.transparent));
        pTCBottomSheetTemplateView.bind(gridItemElement.getAbout());
        pTCBottomSheetTemplateView.showBottomSpacer();
        popupWindow.setContentView(pTCBottomSheetTemplateView);
        popupWindow.setOutsideTouchable(true);
        int iComputePopUpWidth = PopUpUtils.computePopUpWidth(pTCBottomSheetTemplateView, resources);
        int iComputePopUpHeight = computePopUpHeight(pTCBottomSheetTemplateView);
        popupWindow.setWidth(iComputePopUpWidth);
        popupWindow.setHeight(-2);
        PopUpUtils.placePtcBottomSheetPopUpWindow(aboutButton, popupWindow, iComputePopUpWidth, iComputePopUpHeight);
        setBottomSheetPopUpOnDismissListener(popupWindow, getActivity(context), bottomSheetSelectedListener, pTCBottomSheetTemplateView);
        currentBottomSheetPopUp = popupWindow;
    }

    public static void dismissPTCBottomSheetPopup() {
        PopupWindow popupWindow = currentBottomSheetPopUp;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    private static void setBottomSheetPopUpOnDismissListener(final PopupWindow bottomSheetPopUp, final Activity activity, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener, final PTCBottomSheetTemplateView ptcBottomSheetTemplateView) {
        if (activity == null) {
            return;
        }
        final int requestedOrientation = activity.getRequestedOrientation();
        bottomSheetPopUp.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheet.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                PopupWindow unused = PTCBottomSheet.currentBottomSheetPopUp = null;
                activity.setRequestedOrientation(requestedOrientation);
                bottomSheetSelectedListener.onAboutSelected(null, null);
                if (ptcBottomSheetTemplateView.getIsTrailerPlayed()) {
                    ptcBottomSheetTemplateView.playView.stopTrailerPlaybackOnAboutDismiss();
                }
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

    private static int computePopUpHeight(final PTCBottomSheetTemplateView bottomSheetTemplateView) {
        bottomSheetTemplateView.measure(0, 0);
        return bottomSheetTemplateView.getMeasuredHeight();
    }
}
