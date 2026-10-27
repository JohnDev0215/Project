package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$color;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.OverlayViewFragment;
import com.amazon.podcast.views.PopUpUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class ContextMenus {
    private static OverlayViewFragment currentContextMenuOverlay;
    private static PopupWindow currentContextMenuPopUp;

    public static void show(final EpisodeOptionsElement episodeOptionsElement, final Context context, final Resources resources, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final ImageView overflowButton, final String tag, final UiMetricAttributes$PageType pageType) {
        if (episodeOptionsElement == null) {
            return;
        }
        if (PopUpUtils.shouldUsePopUp(resources)) {
            showContextMenuPopUp(episodeOptionsElement, context, resources, methodsDispatcher, ownerId, lifecycleOwner, overflowButton, pageType);
        } else {
            showContextMenuOverlay(context, episodeOptionsElement, methodsDispatcher, ownerId, tag, lifecycleOwner, fragmentManager, pageType);
        }
        methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getOnEpisodeOptionsShow());
    }

    public static void dismissContextMenuPopUp() {
        PopupWindow popupWindow = currentContextMenuPopUp;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    public static void dismissContextMenuOverlay() {
        OverlayViewFragment overlayViewFragment = currentContextMenuOverlay;
        if (overlayViewFragment == null) {
            return;
        }
        overlayViewFragment.dismiss();
        currentContextMenuOverlay = null;
    }

    public static void dismissContextMenus() {
        dismissContextMenuPopUp();
        dismissContextMenuOverlay();
    }

    private static void showContextMenuOverlay(final Context context, final EpisodeOptionsElement episodeOptionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final String tag, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final UiMetricAttributes$PageType pageType) {
        if (episodeOptionsElement == null) {
            return;
        }
        ContextMenuView contextMenuView = new ContextMenuView(context);
        final OverlayViewFragment overlayViewFragment = new OverlayViewFragment();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.ContextMenus.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                overlayViewFragment.dismiss();
                OverlayViewFragment unused = ContextMenus.currentContextMenuOverlay = null;
            }
        };
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: com.amazon.podcast.views.contextMenu.ContextMenus.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                overlayViewFragment.dismiss();
                OverlayViewFragment unused = ContextMenus.currentContextMenuOverlay = null;
                methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getOnEpisodeOptionsDismiss());
                return false;
            }
        };
        contextMenuView.setMethodsDispatcherAndLifecycleOwner(methodsDispatcher, ownerId, lifecycleOwner);
        contextMenuView.setIsPopUp(false);
        contextMenuView.bindDismissListeners(onClickListener, onTouchListener);
        contextMenuView.bind(episodeOptionsElement, pageType);
        overlayViewFragment.bind(contextMenuView);
        overlayViewFragment.show(fragmentManager, tag);
        currentContextMenuOverlay = overlayViewFragment;
    }

    private static void showContextMenuPopUp(final EpisodeOptionsElement episodeOptionsElement, final Context context, final Resources resources, final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner, final ImageView overflowButton, final UiMetricAttributes$PageType pageType) {
        ContextMenuView contextMenuView = new ContextMenuView(context);
        contextMenuView.setIsPopUp(true);
        final PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setBackgroundDrawable(resources.getDrawable(R$color.transparent));
        contextMenuView.setMethodsDispatcherAndLifecycleOwner(methodsDispatcher, ownerId, lifecycleOwner);
        contextMenuView.bindDismissListeners(new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.ContextMenus.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                popupWindow.dismiss();
            }
        }, null);
        contextMenuView.bind(episodeOptionsElement, pageType);
        contextMenuView.hideDismissButton();
        contextMenuView.showBottomSpacer();
        popupWindow.setContentView(contextMenuView);
        popupWindow.setOutsideTouchable(true);
        int iComputePopUpWidth = PopUpUtils.computePopUpWidth(contextMenuView, resources);
        int iComputePopUpHeight = computePopUpHeight(popupWindow);
        popupWindow.setWidth(iComputePopUpWidth);
        popupWindow.setHeight(iComputePopUpHeight);
        PopUpUtils.placeContextMenuPopUpWindow(overflowButton, popupWindow, iComputePopUpWidth, iComputePopUpHeight);
        setContextMenuPopUpOnDismissListener(popupWindow, getActivity(context));
        currentContextMenuPopUp = popupWindow;
    }

    private static void setContextMenuPopUpOnDismissListener(final PopupWindow contextMenuPopUp, final Activity activity) {
        if (activity == null) {
            return;
        }
        final int requestedOrientation = activity.getRequestedOrientation();
        contextMenuPopUp.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.amazon.podcast.views.contextMenu.ContextMenus.4
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                PopupWindow unused = ContextMenus.currentContextMenuPopUp = null;
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

    private static int computePopUpHeight(final PopupWindow contextMenuPopUp) {
        ContextMenuView contextMenuView = (ContextMenuView) contextMenuPopUp.getContentView();
        return (contextMenuView.isHeaderShowing() ? contextMenuView.getHeaderHeight() : 0) + contextMenuView.getRecyclerViewHeight(true) + contextMenuView.getStatusBarHeight() + contextMenuView.getBottomSpacerHeight();
    }
}
