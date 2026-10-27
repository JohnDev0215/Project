package com.facebook.react.uimanager.util;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.facebook.react.R$id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class ReactFindViewUtil {
    private static final List<OnViewFoundListener> mOnViewFoundListeners = new ArrayList();
    private static final Map<OnMultipleViewsFoundListener, Set<String>> mOnMultipleViewsFoundListener = new HashMap();

    public interface OnMultipleViewsFoundListener {
        void onViewFound(View view, String str);
    }

    public interface OnViewFoundListener {
        String getNativeId();

        void onViewFound(View view);
    }

    @Nullable
    public static View findView(View view, String str) {
        String nativeId = getNativeId(view);
        if (nativeId != null && nativeId.equals(str)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View viewFindView = findView(viewGroup.getChildAt(i), str);
            if (viewFindView != null) {
                return viewFindView;
            }
        }
        return null;
    }

    public static void notifyViewRendered(View view) {
        String nativeId = getNativeId(view);
        if (nativeId == null) {
            return;
        }
        Iterator<OnViewFoundListener> it = mOnViewFoundListeners.iterator();
        while (it.hasNext()) {
            OnViewFoundListener next = it.next();
            if (nativeId.equals(next.getNativeId())) {
                next.onViewFound(view);
                it.remove();
            }
        }
        for (Map.Entry<OnMultipleViewsFoundListener, Set<String>> entry : mOnMultipleViewsFoundListener.entrySet()) {
            Set<String> value = entry.getValue();
            if (value != null && value.contains(nativeId)) {
                entry.getKey().onViewFound(view, nativeId);
            }
        }
    }

    @Nullable
    private static String getNativeId(View view) {
        Object tag = view.getTag(R$id.view_tag_native_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}
