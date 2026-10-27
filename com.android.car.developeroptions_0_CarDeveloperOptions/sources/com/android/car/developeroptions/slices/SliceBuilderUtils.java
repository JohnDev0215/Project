package com.android.car.developeroptions.slices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.SubSettingLauncher;

/* JADX INFO: loaded from: classes.dex */
public class SliceBuilderUtils {
    public static int getSliceType(Context context, String str, String str2) {
        return getPreferenceController(context, str, str2).getSliceType();
    }

    public static BasePreferenceController getPreferenceController(Context context, SliceData sliceData) {
        return getPreferenceController(context, sliceData.getPreferenceController(), sliceData.getKey());
    }

    public static Intent buildSearchResultPageIntent(Context context, String str, String str2, String str3, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(":settings:fragment_args_key", str2);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(str);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleText(str3);
        subSettingLauncher.setSourceMetricsCategory(i);
        Intent intent = subSettingLauncher.toIntent();
        intent.putExtra(":settings:fragment_args_key", str2).setAction("com.android.car.developeroptions.SEARCH_RESULT_TRAMPOLINE").setComponent(null);
        return intent;
    }

    private static BasePreferenceController getPreferenceController(Context context, String str, String str2) {
        try {
            return BasePreferenceController.createInstance(context, str);
        } catch (IllegalStateException unused) {
            return BasePreferenceController.createInstance(context, str, str2);
        }
    }

    static IconCompat getSafeIcon(Context context, SliceData sliceData) {
        int iconResource = sliceData.getIconResource();
        if (iconResource == 0) {
            iconResource = R.drawable.ic_settings_accent;
        }
        try {
            return IconCompat.createWithResource(context, iconResource);
        } catch (Exception e) {
            Log.w("SliceBuilder", "Falling back to settings icon because there is an error getting slice icon " + sliceData.getUri(), e);
            return IconCompat.createWithResource(context, R.drawable.ic_settings_accent);
        }
    }
}
