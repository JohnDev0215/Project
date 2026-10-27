package com.android.car.apps.common;

import androidx.fragment.app.Fragment;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentUtils {
    private static Object sParentForTesting;

    private FragmentUtils() {
    }

    public static void setParentForTesting(Object parentForTesting) {
        sParentForTesting = parentForTesting;
    }

    public static <T> T getParent(Fragment fragment, Class<T> cls) {
        if (cls.isInstance(sParentForTesting)) {
            return (T) sParentForTesting;
        }
        T t = (T) fragment.getParentFragment();
        if (cls.isInstance(t)) {
            return t;
        }
        T t2 = (T) fragment.getActivity();
        if (cls.isInstance(t2)) {
            return t2;
        }
        return null;
    }

    public static <T> T requireParent(Fragment fragment, Class<T> cls) {
        T t = (T) getParent(fragment, cls);
        Objects.requireNonNull(t);
        return t;
    }

    public static void checkParent(Fragment fragment, Class<?> parentType) throws AssertionError {
        String parent;
        if (sParentForTesting != null || getParent(fragment, parentType) != null) {
            return;
        }
        if (fragment.getParentFragment() == null) {
            if (fragment.getActivity() != null) {
                parent = fragment.getActivity().getClass().getName();
            } else if (fragment.getHost() != null) {
                parent = fragment.getHost().getClass().getName();
            } else {
                throw new AssertionError(fragment.getClass().getName() + " must be added to a parent that implements " + parentType.getName() + " but is currently unattached to a parent.");
            }
        } else {
            parent = fragment.getParentFragment().getClass().getName();
        }
        throw new AssertionError(fragment.getClass().getName() + " must be added to a parent that implements " + parentType.getName() + ". Instead found " + parent);
    }
}
