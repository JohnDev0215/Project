package com.android.car.developeroptions.shortcut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$TetherSettingsActivity;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CreateShortcutPreferenceController extends BasePreferenceController {
    static final String SHORTCUT_ID_PREFIX = "component-shortcut-";
    private static final String TAG = "CreateShortcutPrefCtrl";
    private final ConnectivityManager mConnectivityManager;
    private Activity mHost;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final PackageManager mPackageManager;
    private final ShortcutManager mShortcutManager;
    static final Intent SHORTCUT_PROBE = new Intent("android.intent.action.MAIN").addCategory("com.android.car.developeroptions.SHORTCUT").addFlags(268435456);
    private static final Comparator<ResolveInfo> SHORTCUT_COMPARATOR = new Comparator<ResolveInfo>() { // from class: com.android.car.developeroptions.shortcut.CreateShortcutPreferenceController.1
        @Override // java.util.Comparator
        public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            return resolveInfo.priority - resolveInfo2.priority;
        }
    };

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public CreateShortcutPreferenceController(Context context, String str) {
        super(context, str);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mShortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        this.mPackageManager = context.getPackageManager();
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void setActivity(Activity activity) {
        this.mHost = activity;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof PreferenceGroup) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
            preferenceGroup.removeAll();
            List<ResolveInfo> listQueryShortcuts = queryShortcuts();
            Context context = preference.getContext();
            if (listQueryShortcuts.isEmpty()) {
                return;
            }
            PreferenceCategory preferenceCategory = new PreferenceCategory(context);
            preferenceGroup.addPreference(preferenceCategory);
            int i = 0;
            for (final ResolveInfo resolveInfo : listQueryShortcuts) {
                int i2 = resolveInfo.priority / 10;
                if (i2 != i) {
                    preferenceCategory = new PreferenceCategory(context);
                    preferenceGroup.addPreference(preferenceCategory);
                }
                Preference preference2 = new Preference(context);
                preference2.setTitle(resolveInfo.loadLabel(this.mPackageManager));
                preference2.setKey(resolveInfo.activityInfo.getComponentName().flattenToString());
                preference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.shortcut.-$$Lambda$CreateShortcutPreferenceController$ovo2ZvAxeWM25Pq-PPid86gGazg
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference3) {
                        return this.f$0.lambda$updateState$0$CreateShortcutPreferenceController(resolveInfo, preference3);
                    }
                });
                preferenceCategory.addPreference(preference2);
                i = i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateState$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateState$0$CreateShortcutPreferenceController(ResolveInfo resolveInfo, Preference preference) {
        if (this.mHost == null) {
            return false;
        }
        this.mHost.setResult(-1, createResultIntent(buildShortcutIntent(resolveInfo), resolveInfo, preference.getTitle()));
        logCreateShortcut(resolveInfo);
        this.mHost.finish();
        return true;
    }

    Intent createResultIntent(Intent intent, ResolveInfo resolveInfo, CharSequence charSequence) {
        Icon iconCreateWithResource;
        ApplicationInfo applicationInfo;
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        int i = activityInfo.icon;
        if (i != 0 && (applicationInfo = activityInfo.applicationInfo) != null) {
            iconCreateWithResource = Icon.createWithAdaptiveBitmap(createIcon(applicationInfo, i, R.layout.shortcut_badge_maskable, this.mContext.getResources().getDimensionPixelSize(R.dimen.shortcut_size_maskable)));
        } else {
            iconCreateWithResource = Icon.createWithResource(this.mContext, R.drawable.ic_launcher_settings);
        }
        Intent intentCreateShortcutResultIntent = this.mShortcutManager.createShortcutResultIntent(new ShortcutInfo.Builder(this.mContext, SHORTCUT_ID_PREFIX + intent.getComponent().flattenToShortString()).setShortLabel(charSequence).setIntent(intent).setIcon(iconCreateWithResource).build());
        if (intentCreateShortcutResultIntent == null) {
            intentCreateShortcutResultIntent = new Intent();
        }
        intentCreateShortcutResultIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this.mContext, R.mipmap.ic_launcher_settings)).putExtra("android.intent.extra.shortcut.INTENT", intent).putExtra("android.intent.extra.shortcut.NAME", charSequence);
        int i2 = activityInfo.icon;
        if (i2 != 0) {
            intentCreateShortcutResultIntent.putExtra("android.intent.extra.shortcut.ICON", createIcon(activityInfo.applicationInfo, i2, R.layout.shortcut_badge, this.mContext.getResources().getDimensionPixelSize(R.dimen.shortcut_size)));
        }
        return intentCreateShortcutResultIntent;
    }

    List<ResolveInfo> queryShortcuts() {
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> listQueryIntentActivities = this.mPackageManager.queryIntentActivities(SHORTCUT_PROBE, 128);
        if (listQueryIntentActivities == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (!resolveInfo.activityInfo.name.endsWith(Settings$TetherSettingsActivity.class.getSimpleName()) || this.mConnectivityManager.isTetheringSupported()) {
                if (resolveInfo.activityInfo.applicationInfo.isSystemApp()) {
                    arrayList.add(resolveInfo);
                } else {
                    Log.d(TAG, "Skipping non-system app: " + resolveInfo.activityInfo);
                }
            }
        }
        Collections.sort(arrayList, SHORTCUT_COMPARATOR);
        return arrayList;
    }

    private void logCreateShortcut(ResolveInfo resolveInfo) {
        ActivityInfo activityInfo;
        if (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null) {
            return;
        }
        this.mMetricsFeatureProvider.action(this.mContext, 829, activityInfo.name);
    }

    private Intent buildShortcutIntent(ResolveInfo resolveInfo) {
        Intent flags = new Intent(SHORTCUT_PROBE).setFlags(335544320);
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        return flags.setClassName(activityInfo.packageName, activityInfo.name);
    }

    private Bitmap createIcon(ApplicationInfo applicationInfo, int i, int i2, int i3) {
        View viewInflate = LayoutInflater.from(new ContextThemeWrapper(this.mContext, android.R.style.Theme.Material)).inflate(i2, (ViewGroup) null);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        viewInflate.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(viewInflate.getMeasuredWidth(), viewInflate.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        try {
            Drawable drawable = this.mPackageManager.getResourcesForApplication(applicationInfo).getDrawable(i);
            if (drawable instanceof LayerDrawable) {
                drawable = ((LayerDrawable) drawable).getDrawable(1);
            }
            ((ImageView) viewInflate.findViewById(android.R.id.icon)).setImageDrawable(drawable);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, "Cannot load icon from app " + applicationInfo + ", returning a default icon");
            ((ImageView) viewInflate.findViewById(android.R.id.icon)).setImageIcon(Icon.createWithResource(this.mContext, R.drawable.ic_launcher_settings));
        }
        viewInflate.layout(0, 0, viewInflate.getMeasuredWidth(), viewInflate.getMeasuredHeight());
        viewInflate.draw(canvas);
        return bitmapCreateBitmap;
    }
}
