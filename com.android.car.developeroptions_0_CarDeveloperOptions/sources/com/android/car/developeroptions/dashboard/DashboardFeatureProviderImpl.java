package com.android.car.developeroptions.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.profileselector.ProfileSelectDialog;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import com.android.settingslib.drawer.TileUtils;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.AdaptiveIcon;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DashboardFeatureProviderImpl implements DashboardFeatureProvider {
    private final CategoryManager mCategoryManager;
    protected final Context mContext;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final PackageManager mPackageManager;

    public DashboardFeatureProviderImpl(Context context) {
        this.mContext = context.getApplicationContext();
        this.mCategoryManager = CategoryManager.get(context);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFeatureProvider
    public DashboardCategory getTilesForCategory(String str) {
        return this.mCategoryManager.getTilesByCategory(this.mContext, str);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFeatureProvider
    public List<DashboardCategory> getAllCategories() {
        return this.mCategoryManager.getCategories(this.mContext);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFeatureProvider
    public String getDashboardKeyForTile(Tile tile) {
        if (tile == null) {
            return null;
        }
        if (tile.hasKey()) {
            return tile.getKey(this.mContext);
        }
        return "dashboard_tile_pref_" + tile.getIntent().getComponent().getClassName();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFeatureProvider
    public void bindPreferenceToTile(final FragmentActivity fragmentActivity, boolean z, final int i, Preference preference, final Tile tile, String str, int i2) {
        String string;
        if (preference == null) {
            return;
        }
        preference.setTitle(tile.getTitle(fragmentActivity.getApplicationContext()));
        if (!TextUtils.isEmpty(str)) {
            preference.setKey(str);
        } else {
            preference.setKey(getDashboardKeyForTile(tile));
        }
        bindSummary(preference, tile);
        bindIcon(preference, tile, z);
        Bundle metaData = tile.getMetaData();
        String string2 = null;
        if (metaData != null) {
            string2 = metaData.getString("com.android.car.developeroptions.FRAGMENT_CLASS");
            string = metaData.getString("com.android.car.developeroptions.intent.action");
        } else {
            string = null;
        }
        if (!TextUtils.isEmpty(string2)) {
            preference.setFragment(string2);
        } else {
            final Intent intent = new Intent(tile.getIntent());
            intent.putExtra(":settings:source_metrics", i);
            if (string != null) {
                intent.setAction(string);
            }
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFeatureProviderImpl$clZJVSnmx8eIIRulxImEPfAwdaw
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$bindPreferenceToTile$0$DashboardFeatureProviderImpl(fragmentActivity, tile, intent, i, preference2);
                }
            });
        }
        String packageName = fragmentActivity.getPackageName();
        if (tile.hasOrder()) {
            int order = tile.getOrder();
            if (TextUtils.equals(packageName, tile.getIntent().getComponent().getPackageName()) || i2 == Integer.MAX_VALUE) {
                preference.setOrder(order);
            } else {
                preference.setOrder(order + i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindPreferenceToTile$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$bindPreferenceToTile$0$DashboardFeatureProviderImpl(FragmentActivity fragmentActivity, Tile tile, Intent intent, int i, Preference preference) {
        launchIntentOrSelectProfile(fragmentActivity, tile, intent, i);
        return true;
    }

    private void bindSummary(final Preference preference, final Tile tile) {
        CharSequence summary = tile.getSummary(this.mContext);
        if (summary != null) {
            preference.setSummary(summary);
        } else if (tile.getMetaData() != null && tile.getMetaData().containsKey("com.android.settings.summary_uri")) {
            preference.setSummary(R.string.summary_placeholder);
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFeatureProviderImpl$og6YrUvPINuV2-AFw8iQ8z5cmcI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$bindSummary$2$DashboardFeatureProviderImpl(tile, preference);
                }
            });
        } else {
            preference.setSummary(R.string.summary_placeholder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindSummary$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindSummary$2$DashboardFeatureProviderImpl(Tile tile, final Preference preference) {
        ArrayMap arrayMap = new ArrayMap();
        final String textFromUri = TileUtils.getTextFromUri(this.mContext, TileUtils.getCompleteUri(tile, "com.android.settings.summary_uri", "getDynamicSummary"), arrayMap, "com.android.settings.summary");
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFeatureProviderImpl$7G0VcjAsyqm28b5OQjFJDCA04t8
            @Override // java.lang.Runnable
            public final void run() {
                preference.setSummary(textFromUri);
            }
        });
    }

    void bindIcon(final Preference preference, final Tile tile, boolean z) {
        Icon icon = tile.getIcon(preference.getContext());
        if (icon != null) {
            Drawable drawableLoadDrawable = icon.loadDrawable(preference.getContext());
            if (z && !TextUtils.equals(this.mContext.getPackageName(), tile.getPackageName())) {
                AdaptiveIcon adaptiveIcon = new AdaptiveIcon(this.mContext, drawableLoadDrawable);
                adaptiveIcon.setBackgroundColor(this.mContext, tile);
                drawableLoadDrawable = adaptiveIcon;
            }
            preference.setIcon(drawableLoadDrawable);
            return;
        }
        if (tile.getMetaData() == null || !tile.getMetaData().containsKey("com.android.settings.icon_uri")) {
            return;
        }
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFeatureProviderImpl$k1yEuwp3ZUdUnuFp_PB22UNTfDc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$bindIcon$4$DashboardFeatureProviderImpl(tile, preference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindIcon$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindIcon$4$DashboardFeatureProviderImpl(Tile tile, final Preference preference) {
        String packageName;
        Intent intent = tile.getIntent();
        if (!TextUtils.isEmpty(intent.getPackage())) {
            packageName = intent.getPackage();
        } else {
            packageName = intent.getComponent() != null ? intent.getComponent().getPackageName() : null;
        }
        ArrayMap arrayMap = new ArrayMap();
        Uri completeUri = TileUtils.getCompleteUri(tile, "com.android.settings.icon_uri", "getProviderIcon");
        Pair<String, Integer> iconFromUri = TileUtils.getIconFromUri(this.mContext, packageName, completeUri, arrayMap);
        if (iconFromUri == null) {
            Log.w("DashboardFeatureImpl", "Failed to get icon from uri " + completeUri);
            return;
        }
        final Icon iconCreateWithResource = Icon.createWithResource((String) iconFromUri.first, ((Integer) iconFromUri.second).intValue());
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$DashboardFeatureProviderImpl$HQczgwH4t5XhggfV24vk--gUxMg
            @Override // java.lang.Runnable
            public final void run() {
                Preference preference2 = preference;
                preference2.setIcon(iconCreateWithResource.loadDrawable(preference2.getContext()));
            }
        });
    }

    private void launchIntentOrSelectProfile(FragmentActivity fragmentActivity, Tile tile, Intent intent, int i) {
        if (!isIntentResolvable(intent)) {
            Log.w("DashboardFeatureImpl", "Cannot resolve intent, skipping. " + intent);
            return;
        }
        ProfileSelectDialog.updateUserHandlesIfNeeded(this.mContext, tile);
        if (tile.userHandle == null || tile.isPrimaryProfileOnly()) {
            this.mMetricsFeatureProvider.logStartedIntent(intent, i);
            fragmentActivity.startActivityForResult(intent, 0);
            return;
        }
        if (tile.userHandle.size() == 1) {
            this.mMetricsFeatureProvider.logStartedIntent(intent, i);
            fragmentActivity.startActivityForResultAsUser(intent, 0, tile.userHandle.get(0));
            return;
        }
        UserHandle userHandle = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER");
        if (userHandle != null && tile.userHandle.contains(userHandle)) {
            this.mMetricsFeatureProvider.logStartedIntent(intent, i);
            fragmentActivity.startActivityForResultAsUser(intent, 0, userHandle);
        } else {
            ProfileSelectDialog.show(fragmentActivity.getSupportFragmentManager(), tile);
        }
    }

    private boolean isIntentResolvable(Intent intent) {
        return this.mPackageManager.resolveActivity(intent, 0) != null;
    }
}
