package com.android.car.ui.core;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
class CheckCarUiComponents implements Application.ActivityLifecycleCallbacks {
    private static final String INTENT_FILTER = "com.android.car.ui.intent.CHECK_CAR_UI_COMPONENTS";
    private static final String NO_CAR_UI_PREFERENCE = "CarUiPreference is not used: ";
    private static final String NO_CAR_UI_RV = "CarUiRecyclerView not used:";
    private static final String NO_CAR_UI_TOOLBAR = "CarUiToolbar is not used: ";
    private static final String NO_CAR_UI_TOOLBAR_BL = "CarUiBaseLayoutToolbar is not used: ";
    private static final String NO_LIST_ITEM = "CarUiListItem are not used within CarUiRecyclerView: ";
    private static final String TAG = CheckCarUiComponents.class.getSimpleName();
    private boolean mIsScreenVisible;
    private View mRootView;
    private Handler mHandler = new Handler();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.ui.core.CheckCarUiComponents.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CheckCarUiComponents.this.checkForComponents(context, true);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    interface Predicate<T> {
        boolean test(T t);
    }

    CheckCarUiComponents(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(INTENT_FILTER);
        context.registerReceiver(this.mReceiver, filter);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(final Activity activity) {
        this.mRootView = activity.getWindow().getDecorView().getRootView();
        this.mIsScreenVisible = true;
        if (checkComponentsForAllActivities(activity)) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.ui.core.-$$Lambda$CheckCarUiComponents$y7RxpUiLsA8rZaNatst8VPAWq5U
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onActivityPostResumed$0$CheckCarUiComponents(activity);
                }
            }, 2000L);
        }
    }

    public /* synthetic */ void lambda$onActivityPostResumed$0$CheckCarUiComponents(Activity activity) {
        checkForComponents(activity, false);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.mIsScreenVisible = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        View view = this.mRootView;
        if (view != null && CarUiUtils.getActivity(view.getContext()) == activity) {
            this.mRootView = null;
        }
    }

    private boolean checkComponentsForAllActivities(Context context) {
        return context.getResources().getBoolean(R.bool.car_ui_escrow_check_components_automatically);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkForComponents(Context context, boolean showToast) {
        if (!this.mIsScreenVisible) {
            return;
        }
        CarUiComponents carUiComponents = new CarUiComponents();
        checkForCarUiComponents(this.mRootView, carUiComponents);
        if (carUiComponents.mIsUsingCarUiRecyclerView && !carUiComponents.mIsCarUiRecyclerViewUsingListItem) {
            Log.d(TAG, NO_LIST_ITEM);
            mayShowToast(context, NO_LIST_ITEM, showToast);
        }
        if (carUiComponents.mIsUsingAndroidXRecyclerView) {
            Log.d(TAG, NO_CAR_UI_RV);
            mayShowToast(context, NO_CAR_UI_RV, showToast);
        }
        if (!carUiComponents.mIsUsingCarUiToolbar) {
            Log.d(TAG, NO_CAR_UI_TOOLBAR);
            mayShowToast(context, NO_CAR_UI_TOOLBAR, showToast);
        }
        if (!carUiComponents.mIsUsingCarUiBaseLayoutToolbar && carUiComponents.mIsUsingCarUiToolbar) {
            Log.d(TAG, NO_CAR_UI_TOOLBAR_BL);
            mayShowToast(context, NO_CAR_UI_TOOLBAR_BL, showToast);
        }
        if (carUiComponents.mIsUsingCarUiRecyclerViewForPreference && !carUiComponents.mIsUsingCarUiPreference) {
            Log.d(TAG, NO_CAR_UI_PREFERENCE);
            mayShowToast(context, NO_CAR_UI_PREFERENCE, showToast);
        }
    }

    private void checkForCarUiComponents(View v, final CarUiComponents carUiComponents) {
        viewHasChildMatching(v, new Predicate() { // from class: com.android.car.ui.core.-$$Lambda$CheckCarUiComponents$sMvfGKJ7xE4isapA83m1KGgs5T4
            @Override // com.android.car.ui.core.CheckCarUiComponents.Predicate
            public final boolean test(Object obj) {
                return CheckCarUiComponents.lambda$checkForCarUiComponents$1(carUiComponents, (View) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$checkForCarUiComponents$1(CarUiComponents carUiComponents, View view) {
        if (isCarUiRecyclerView(view)) {
            carUiComponents.mIsUsingCarUiRecyclerView = true;
            if (viewHasChildMatching(view, new Predicate() { // from class: com.android.car.ui.core.-$$Lambda$CheckCarUiComponents$NVhRkAsVZLAico2oOqC8mqdd93I
                @Override // com.android.car.ui.core.CheckCarUiComponents.Predicate
                public final boolean test(Object obj) {
                    return CheckCarUiComponents.isCarUiPreference((View) obj);
                }
            })) {
                carUiComponents.mIsUsingCarUiPreference = true;
                return false;
            }
            carUiComponents.mIsCarUiRecyclerViewUsingListItem = viewHasChildMatching(view, new Predicate() { // from class: com.android.car.ui.core.-$$Lambda$CheckCarUiComponents$LQ0QJ1uqNsfWejQq2fxWuLySBiU
                @Override // com.android.car.ui.core.CheckCarUiComponents.Predicate
                public final boolean test(Object obj) {
                    return CheckCarUiComponents.isCarUiListItem((View) obj);
                }
            });
            return false;
        }
        if (isAndroidXRecyclerView(view)) {
            carUiComponents.mIsUsingAndroidXRecyclerView = true;
        }
        if (isCarUiToolbar(view)) {
            carUiComponents.mIsUsingCarUiToolbar = true;
        }
        if (isCarUiBaseLayoutToolbar(view)) {
            carUiComponents.mIsUsingCarUiBaseLayoutToolbar = true;
        }
        return false;
    }

    private static boolean viewHasChildMatching(View view, Predicate<View> p) {
        if (view == null) {
            return false;
        }
        if (p.test(view)) {
            return true;
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                if (viewHasChildMatching(((ViewGroup) view).getChildAt(i), p)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCarUiRecyclerView(View view) {
        return view.getTag() != null && view.getTag().toString().equals("carUiRecyclerView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isCarUiListItem(View view) {
        return view.getTag() != null && view.getTag().toString().equals("carUiListItem");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isCarUiPreference(View view) {
        return view.getTag() != null && view.getTag().toString().equals("carUiPreference");
    }

    private static boolean isCarUiToolbar(View view) {
        return view.getTag() != null && (view.getTag().toString().equals("carUiToolbar") || view.getTag().toString().equals("CarUiBaseLayoutToolbar"));
    }

    private static boolean isCarUiBaseLayoutToolbar(View view) {
        return view.getTag() != null && view.getTag().toString().equals("CarUiBaseLayoutToolbar");
    }

    private static boolean isAndroidXRecyclerView(View view) {
        return view.getClass() == RecyclerView.class;
    }

    private static void mayShowToast(Context context, String message, boolean show) {
        if (!show) {
            return;
        }
        Toast.makeText(context, message, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CarUiComponents {
        boolean mIsCarUiRecyclerViewUsingListItem;
        boolean mIsUsingAndroidXRecyclerView;
        boolean mIsUsingCarUiBaseLayoutToolbar;
        boolean mIsUsingCarUiPreference;
        boolean mIsUsingCarUiRecyclerView;
        boolean mIsUsingCarUiRecyclerViewForPreference;
        boolean mIsUsingCarUiToolbar;

        private CarUiComponents() {
        }
    }

    private static void printViewHierarchy(String indent, View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n ");
        sb.append(indent);
        sb.append('{');
        if (view == null) {
            sb.append("viewNode= NULL, ");
            sb.append('}');
            return;
        }
        sb.append("viewNode= ");
        sb.append(view.toString());
        sb.append(", ");
        sb.append("id= ");
        sb.append(view.getId());
        sb.append(", ");
        sb.append("name= ");
        sb.append(view.getAccessibilityClassName());
        sb.append(", ");
        sb.append('}');
        System.out.println(sb.toString());
        String indent2 = indent + "  ";
        if (!(view instanceof ViewGroup)) {
            return;
        }
        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
            printViewHierarchy(indent2, ((ViewGroup) view).getChildAt(i));
        }
    }
}
