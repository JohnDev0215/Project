package com.android.car.developeroptions.wallpaper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class WallpaperTypePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart {
    private PreferenceScreen mScreen;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public WallpaperTypePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mScreen = preferenceScreen;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference.getIntent() == null) {
            return super.handlePreferenceTreeClick(preference);
        }
        this.mContext.startActivity(preference.getIntent());
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        populateWallpaperTypes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [androidx.preference.PreferenceGroup, androidx.preference.PreferenceScreen] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.preference.PreferenceGroup, androidx.preference.PreferenceScreen] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void populateWallpaperTypes() {
        Intent intent = new Intent("android.intent.action.SET_WALLPAPER");
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        removeUselessExistingPreference(listQueryIntentActivities);
        this.mScreen.setOrderingAsAdded(false);
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            ?? r4 = resolveInfo.activityInfo.packageName;
            Preference preferenceFindPreference = this.mScreen.findPreference(r4);
            ?? preference = preferenceFindPreference;
            if (preferenceFindPreference == null) {
                preference = new Preference(this.mScreen.getContext());
            }
            Intent intentAddFlags = new Intent(intent).addFlags(33554432);
            intentAddFlags.setComponent(new ComponentName((String) r4, resolveInfo.activityInfo.name));
            preference.setIntent(intentAddFlags);
            preference.setKey(r4);
            CharSequence charSequenceLoadLabel = resolveInfo.loadLabel(packageManager);
            if (charSequenceLoadLabel != null) {
                r4 = charSequenceLoadLabel;
            }
            preference.setTitle(r4);
            preference.setIcon(resolveInfo.loadIcon(packageManager));
            this.mScreen.addPreference(preference);
        }
    }

    private void removeUselessExistingPreference(List<ResolveInfo> list) {
        int preferenceCount = this.mScreen.getPreferenceCount();
        if (preferenceCount <= 0) {
            return;
        }
        for (int i = preferenceCount - 1; i >= 0; i--) {
            final Preference preference = this.mScreen.getPreference(i);
            if (((List) list.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.wallpaper.-$$Lambda$WallpaperTypePreferenceController$sr33mExzI9U1MSJ7rTVm60Cioco
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TextUtils.equals(preference.getKey(), ((ResolveInfo) obj).activityInfo.packageName);
                }
            }).collect(Collectors.toList())).isEmpty()) {
                this.mScreen.removePreference(preference);
            }
        }
    }
}
