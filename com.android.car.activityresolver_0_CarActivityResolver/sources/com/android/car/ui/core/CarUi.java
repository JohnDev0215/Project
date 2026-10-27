package com.android.car.ui.core;

import android.app.Activity;
import android.view.View;
import androidx.core.util.Pair;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.baselayout.InsetsChangedListener;
import com.android.car.ui.core.BaseLayoutController;
import com.android.car.ui.toolbar.ToolbarController;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CarUi {
    private CarUi() {
    }

    public static ToolbarController getToolbar(Activity activity) {
        BaseLayoutController controller = getBaseLayoutController(activity);
        if (controller != null) {
            return controller.getToolbarController();
        }
        return null;
    }

    public static ToolbarController requireToolbar(Activity activity) {
        ToolbarController result = getToolbar(activity);
        if (result == null) {
            throw new IllegalArgumentException("Activity " + activity + " does not have a CarUi Toolbar! Are you using Theme.CarUi.WithToolbar?");
        }
        return result;
    }

    public static void replaceInsetsChangedListenerWith(Activity activity, InsetsChangedListener listener) {
        BaseLayoutController controller = getBaseLayoutController(activity);
        if (controller != null) {
            controller.replaceInsetsChangedListenerWith(listener);
        }
    }

    public static Insets getInsets(Activity activity) {
        BaseLayoutController controller = getBaseLayoutController(activity);
        if (controller != null) {
            return controller.getInsets();
        }
        return null;
    }

    public static Insets requireInsets(Activity activity) {
        Insets result = getInsets(activity);
        if (result == null) {
            throw new IllegalArgumentException("Activity " + activity + " does not have a base layout! Are you using Theme.CarUi.WithToolbar or Theme.CarUi.NoToolbar?");
        }
        return result;
    }

    public static ToolbarController installBaseLayoutAround(View view, InsetsChangedListener insetsChangedListener, boolean hasToolbar) {
        Pair<ToolbarController, BaseLayoutController.InsetsUpdater> results = BaseLayoutController.installBaseLayoutAround(null, view, hasToolbar);
        BaseLayoutController.InsetsUpdater insetsUpdater = results.second;
        Objects.requireNonNull(insetsUpdater);
        insetsUpdater.replaceInsetsChangedListenerWith(insetsChangedListener);
        return results.first;
    }

    static BaseLayoutController getBaseLayoutController(Activity activity) {
        if (activity.getClassLoader().equals(CarUi.class.getClassLoader())) {
            return BaseLayoutController.getBaseLayout(activity);
        }
        try {
            Class<?> baseLayoutControllerClass = activity.getClassLoader().loadClass(BaseLayoutController.class.getName());
            Method method = baseLayoutControllerClass.getDeclaredMethod("getBaseLayout", Activity.class);
            return (BaseLayoutController) method.invoke(null, activity);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
