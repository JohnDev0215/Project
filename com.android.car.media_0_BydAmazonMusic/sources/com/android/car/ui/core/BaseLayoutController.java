package com.android.car.ui.core;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.view.View;
import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.android.car.ui.R$attr;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.pluginsupport.c;
import com.android.car.ui.toolbar.j;
import java.util.Map;
import java.util.WeakHashMap;
import spi.b2;
import spi.z4;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class BaseLayoutController {
    private static final Map<Activity, BaseLayoutController> sBaseLayoutMap = new WeakHashMap();
    private a mInsetsUpdater;

    @Nullable
    private j mToolbarController;

    public static final class a implements z4 {
        public z4 a;

        @Nullable
        public Activity b;
        public View c;

        @NonNull
        public Insets d = new Insets();

        public a(@Nullable Activity activity, @NonNull View view) {
            this.b = activity;
            this.c = view;
        }

        @Override // spi.z4
        public final void b(@NonNull Insets insets) {
            if (this.d.equals(insets)) {
                return;
            }
            this.d = insets;
            boolean z = false;
            z4 z4Var = this.a;
            boolean z2 = true;
            if (z4Var != null) {
                z4Var.b(insets);
            } else {
                ComponentCallbacks2 componentCallbacks2 = this.b;
                if (componentCallbacks2 instanceof z4) {
                    ((z4) componentCallbacks2).b(insets);
                    z = true;
                }
                Activity activity = this.b;
                if (activity instanceof FragmentActivity) {
                    for (ActivityResultCaller activityResultCaller : ((FragmentActivity) activity).getSupportFragmentManager().getFragments()) {
                        if (activityResultCaller instanceof z4) {
                            ((z4) activityResultCaller).b(insets);
                            z = true;
                        }
                    }
                }
                z2 = z;
            }
            if (z2) {
                return;
            }
            this.c.setPadding(insets.getLeft(), insets.getTop(), insets.getRight(), insets.getBottom());
        }
    }

    private BaseLayoutController(Activity activity) {
        installBaseLayout(activity);
    }

    public static void build(Activity activity) {
        if (b2.i(activity, R$attr.carUiBaseLayout)) {
            sBaseLayoutMap.put(activity, new BaseLayoutController(activity));
        }
    }

    public static void destroy(Activity activity) {
        sBaseLayoutMap.remove(activity);
    }

    @Nullable
    public static BaseLayoutController getBaseLayoutController(@Nullable Activity activity) {
        return sBaseLayoutMap.get(activity);
    }

    private void installBaseLayout(Activity activity) {
        boolean zI = b2.i(activity, R$attr.carUiToolbar);
        View viewN = b2.n(activity.getWindow().getDecorView(), R.id.content);
        this.mInsetsUpdater = new a(activity, viewN);
        this.mToolbarController = c.a(activity).installBaseLayoutAround(viewN, this.mInsetsUpdater, zI, true);
    }

    public void dispatchNewInsets(Insets insets) {
        this.mInsetsUpdater.b(insets);
    }

    public Insets getInsets() {
        return this.mInsetsUpdater.d;
    }

    @Nullable
    public j getToolbarController() {
        return this.mToolbarController;
    }

    public void replaceInsetsChangedListenerWith(z4 z4Var) {
        this.mInsetsUpdater.a = z4Var;
    }
}
