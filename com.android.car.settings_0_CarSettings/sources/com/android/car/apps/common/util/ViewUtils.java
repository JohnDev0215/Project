package com.android.car.apps.common.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ViewUtils {

    public interface Filter {
        boolean isValid(View view);
    }

    public interface ViewAnimEndListener {
        void onAnimationEnd(View view);
    }

    public static void showHideViewAnimated(boolean show, View view, int duration, ViewAnimEndListener listener) {
        if (show) {
            showViewAnimated(view, duration, listener);
        } else {
            hideViewAnimated(view, duration, listener);
        }
    }

    public static void hideViewAnimated(final View view, int duration, final ViewAnimEndListener listener) {
        view.animate().cancel();
        if (!view.isLaidOut()) {
            view.setVisibility(8);
        } else {
            final Animator.AnimatorListener hider = hideViewAfterAnimation(view);
            view.animate().setDuration(duration).setListener(new AnimatorListenerAdapter() { // from class: com.android.car.apps.common.util.ViewUtils.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    hider.onAnimationEnd(animation);
                    ViewAnimEndListener viewAnimEndListener = listener;
                    if (viewAnimEndListener != null) {
                        viewAnimEndListener.onAnimationEnd(view);
                    }
                }
            }).alpha(0.0f);
        }
    }

    public static void hideViewAnimated(View view, int duration) {
        hideViewAnimated(view, duration, null);
    }

    public static Animator.AnimatorListener hideViewAfterAnimation(final View view) {
        return new AnimatorListenerAdapter() { // from class: com.android.car.apps.common.util.ViewUtils.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(8);
            }
        };
    }

    public static void hideViewsAnimated(List<View> views, int duration) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view != null) {
                hideViewAnimated(view, duration, null);
            }
        }
    }

    public static void showViewAnimated(final View view, int duration, final ViewAnimEndListener listener) {
        view.animate().cancel();
        view.animate().setDuration(duration).setListener(new AnimatorListenerAdapter() { // from class: com.android.car.apps.common.util.ViewUtils.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                view.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                ViewAnimEndListener viewAnimEndListener = listener;
                if (viewAnimEndListener != null) {
                    viewAnimEndListener.onAnimationEnd(view);
                }
            }
        }).alpha(1.0f);
    }

    public static void showViewAnimated(View view, int duration) {
        showViewAnimated(view, duration, null);
    }

    public static void showViewsAnimated(List<View> views, int duration) {
        for (View view : views) {
            if (view != null) {
                showViewAnimated(view, duration, null);
            }
        }
    }

    public static void setVisible(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? 0 : 8);
        }
    }

    public static void setVisible(List<View> views, boolean visible) {
        for (View view : views) {
            setVisible(view, visible);
        }
    }

    public static void setInvisible(View view, boolean invisible) {
        if (view != null) {
            view.setVisibility(invisible ? 4 : 0);
        }
    }

    public static void setText(TextView view, int resId) {
        if (view != null) {
            view.setText(resId);
        }
    }

    public static void setText(TextView view, CharSequence text) {
        if (view != null) {
            view.setText(text);
        }
    }

    public static void setEnabled(View view, boolean enabled) {
        if (view != null) {
            view.setEnabled(enabled);
        }
    }

    public static void setActivated(View view, boolean activated) {
        if (view != null) {
            view.setActivated(activated);
        }
    }

    public static void setOnClickListener(View view, View.OnClickListener l) {
        if (view != null) {
            view.setOnClickListener(l);
        }
    }

    public static List<View> getViewsById(View root, Resources res, int arrayId, Filter filter) {
        View view;
        TypedArray viewIds = res.obtainTypedArray(arrayId);
        List<View> views = new ArrayList<>(viewIds.length());
        for (int i = 0; i < viewIds.length(); i++) {
            int viewId = viewIds.getResourceId(i, 0);
            if (viewId != 0 && (view = root.findViewById(viewId)) != null && (filter == null || filter.isValid(view))) {
                views.add(view);
            }
        }
        viewIds.recycle();
        return views;
    }

    public static void removeFromParent(View view) {
        ViewGroup parent;
        if (view != null && (parent = (ViewGroup) view.getParent()) != null) {
            parent.removeView(view);
        }
    }

    public static void setView(View view, FrameLayout container) {
        if (view != null) {
            if (container.getChildCount() == 1 && container.getChildAt(0) == view) {
                return;
            }
            removeFromParent(view);
            container.removeAllViews();
            container.addView(view);
            container.setVisibility(0);
            return;
        }
        if (container.getChildCount() != 0) {
            container.removeAllViews();
        }
        container.setVisibility(4);
    }
}
