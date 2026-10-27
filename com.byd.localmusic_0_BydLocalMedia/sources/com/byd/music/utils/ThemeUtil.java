package com.byd.music.utils;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class ThemeUtil {
    public static int getTheme() {
        return R.style.DynastyTheme;
    }

    public static int getAttrValue(Activity activity, int i) {
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }

    public static int getAttrValue(Fragment fragment, int i) {
        TypedValue typedValue = new TypedValue();
        fragment.getContext().getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }

    public static int getAttrValue(View view, int i) {
        TypedValue typedValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }
}
