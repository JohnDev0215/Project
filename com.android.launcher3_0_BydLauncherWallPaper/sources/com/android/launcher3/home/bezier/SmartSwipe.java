package com.android.launcher3.home.bezier;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes4.dex */
public class SmartSwipe {
    private static IWrapperFactory factory;

    public interface IWrapperFactory {
        SmartSwipeWrapper createWrapper(Context context);
    }

    public static SmartSwipeWrapper wrap(Activity activity) {
        SmartSwipeWrapper smartSwipeWrapperPeekWrapperFor = peekWrapperFor(activity);
        if (smartSwipeWrapperPeekWrapperFor != null) {
            return smartSwipeWrapperPeekWrapperFor;
        }
        View decorView = activity.getWindow().getDecorView();
        if (decorView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) decorView;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.findViewById(16908290) != null) {
                    return wrap(childAt);
                }
            }
        }
        return wrap(decorView.findViewById(16908290));
    }

    public static SmartSwipeWrapper peekWrapperFor(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        for (View viewFindViewById = decorView.findViewById(16908290); viewFindViewById != null && viewFindViewById != decorView; viewFindViewById = (View) viewFindViewById.getParent()) {
            if (viewFindViewById.getParent() instanceof SmartSwipeWrapper) {
                return (SmartSwipeWrapper) viewFindViewById.getParent();
            }
        }
        return null;
    }

    public static SmartSwipeWrapper wrap(Activity activity, int viewId) {
        View viewFindViewById;
        if (activity == null || (viewFindViewById = activity.findViewById(viewId)) == null) {
            return null;
        }
        return wrap(viewFindViewById);
    }

    public static SmartSwipeWrapper wrap(View view) {
        SmartSwipeWrapper smartSwipeWrapperCreateNewWrapper;
        SmartSwipeWrapper smartSwipeWrapperPeekWrapperFor = peekWrapperFor(view);
        if (smartSwipeWrapperPeekWrapperFor != null) {
            return smartSwipeWrapperPeekWrapperFor;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (view.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            smartSwipeWrapperCreateNewWrapper = createNewWrapper(view.getContext());
            int iIndexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            viewGroup.addView(smartSwipeWrapperCreateNewWrapper, iIndexOfChild, layoutParams);
        } else {
            smartSwipeWrapperCreateNewWrapper = createNewWrapper(view.getContext());
            smartSwipeWrapperCreateNewWrapper.setLayoutParams(layoutParams);
        }
        smartSwipeWrapperCreateNewWrapper.setContentView(view);
        return smartSwipeWrapperCreateNewWrapper;
    }

    public static SmartSwipeWrapper peekWrapperFor(View view) {
        if (view.getParent() instanceof SmartSwipeWrapper) {
            return (SmartSwipeWrapper) view.getParent();
        }
        return null;
    }

    public static void switchDirectionEnable(View view, boolean enable, int direction) {
        enableOrDisableFor(peekWrapperFor(view), enable, direction);
    }

    public static void switchDirectionEnable(Activity activity, boolean enable, int direction) {
        enableOrDisableFor(peekWrapperFor(activity), enable, direction);
    }

    private static void enableOrDisableFor(SmartSwipeWrapper wrapper, boolean enable, int direction) {
        if (wrapper != null) {
            wrapper.enableDirection(direction, enable);
        }
    }

    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(1, dp, context.getResources().getDisplayMetrics());
    }

    public static int ensureBetween(int origin, int min, int max) {
        return Math.max(min, Math.min(origin, max));
    }

    public static float ensureBetween(float origin, float min, float max) {
        return Math.max(min, Math.min(origin, max));
    }

    public static double ensureBetween(double origin, double min, double max) {
        return Math.max(min, Math.min(origin, max));
    }

    private static SmartSwipeWrapper createNewWrapper(Context context) {
        SmartSwipeWrapper smartSwipeWrapperCreateWrapper;
        IWrapperFactory iWrapperFactory = factory;
        return (iWrapperFactory == null || (smartSwipeWrapperCreateWrapper = iWrapperFactory.createWrapper(context)) == null) ? new SmartSwipeWrapper(context) : smartSwipeWrapperCreateWrapper;
    }

    public static void setFactory(IWrapperFactory factory2) {
        factory = factory2;
    }

    static {
        try {
            if (initFactoryByClassName("com.billy.android.swipe.androidx.WrapperFactory")) {
                return;
            }
            initFactoryByClassName("com.billy.android.swipe.support.WrapperFactory");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean initFactoryByClassName(String factoryClassName) {
        try {
            Class<?> cls = Class.forName(factoryClassName);
            if (cls == null) {
                return true;
            }
            Object objNewInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (!(objNewInstance instanceof IWrapperFactory)) {
                return true;
            }
            setFactory((IWrapperFactory) objNewInstance);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
