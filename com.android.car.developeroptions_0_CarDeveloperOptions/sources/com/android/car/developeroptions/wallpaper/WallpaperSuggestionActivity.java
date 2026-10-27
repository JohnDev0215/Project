package com.android.car.developeroptions.wallpaper;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.google.android.setupcompat.util.WizardManagerHelper;

/* JADX INFO: loaded from: classes.dex */
public class WallpaperSuggestionActivity extends Activity {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.wallpaper.WallpaperSuggestionActivity.1
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PackageManager packageManager = getPackageManager();
        Intent intentAddFlags = new Intent().setClassName(getString(R.string.config_wallpaper_picker_package), getString(R.string.config_wallpaper_picker_class)).addFlags(33554432);
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intentAddFlags);
        if (packageManager.resolveActivity(intentAddFlags, 0) != null) {
            startActivity(intentAddFlags);
        } else {
            startFallbackSuggestion();
        }
        finish();
    }

    void startFallbackSuggestion() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this);
        subSettingLauncher.setDestination(WallpaperTypeSettings.class.getName());
        subSettingLauncher.setTitleRes(R.string.wallpaper_suggestion_title);
        subSettingLauncher.setSourceMetricsCategory(35);
        subSettingLauncher.addFlags(33554432);
        subSettingLauncher.launch();
    }

    public static boolean isSuggestionComplete(Context context) {
        return !isWallpaperServiceEnabled(context) || ((WallpaperManager) context.getSystemService("wallpaper")).getWallpaperId(1) > 0;
    }

    private static boolean isWallpaperServiceEnabled(Context context) {
        return context.getResources().getBoolean(android.R.bool.config_cecPowerControlModeNone_default);
    }
}
