package com.android.car.developeroptions.applications.appinfo;

import android.app.role.RoleControllerManager;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.util.CollectionUtils;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultAppShortcutPreferenceControllerBase extends BasePreferenceController {
    private boolean mAppQualified;
    protected final String mPackageName;
    private PreferenceScreen mPreferenceScreen;
    private final RoleManager mRoleManager;
    private final String mRoleName;
    private boolean mRoleVisible;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
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

    public DefaultAppShortcutPreferenceControllerBase(Context context, String str, String str2, String str3) {
        super(context, str);
        this.mRoleName = str2;
        this.mPackageName = str3;
        this.mRoleManager = (RoleManager) context.getSystemService(RoleManager.class);
        RoleControllerManager roleControllerManager = (RoleControllerManager) this.mContext.getSystemService(RoleControllerManager.class);
        Executor mainExecutor = this.mContext.getMainExecutor();
        roleControllerManager.isRoleVisible(this.mRoleName, mainExecutor, new Consumer() { // from class: com.android.car.developeroptions.applications.appinfo.-$$Lambda$DefaultAppShortcutPreferenceControllerBase$Ox1nPIKHeRuc63_yNKmbgjoFSHk
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$new$0$DefaultAppShortcutPreferenceControllerBase((Boolean) obj);
            }
        });
        roleControllerManager.isApplicationQualifiedForRole(this.mRoleName, this.mPackageName, mainExecutor, new Consumer() { // from class: com.android.car.developeroptions.applications.appinfo.-$$Lambda$DefaultAppShortcutPreferenceControllerBase$SIYo8aQNj3tJC9vgfiwNjrw_AbE
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$new$1$DefaultAppShortcutPreferenceControllerBase((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$DefaultAppShortcutPreferenceControllerBase(Boolean bool) {
        this.mRoleVisible = bool.booleanValue();
        refreshAvailability();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$DefaultAppShortcutPreferenceControllerBase(Boolean bool) {
        this.mAppQualified = bool.booleanValue();
        refreshAvailability();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
    }

    private void refreshAvailability() {
        Preference preferenceFindPreference;
        PreferenceScreen preferenceScreen = this.mPreferenceScreen;
        if (preferenceScreen == null || (preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey())) == null) {
            return;
        }
        preferenceFindPreference.setVisible(isAvailable());
        updateState(preferenceFindPreference);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (((UserManager) this.mContext.getSystemService(UserManager.class)).isManagedProfile()) {
            return 4;
        }
        return (this.mRoleVisible && this.mAppQualified) ? 0 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mContext.getText(isDefaultApp() ? R.string.yes : R.string.no);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(this.mPreferenceKey, preference.getKey())) {
            return false;
        }
        this.mContext.startActivity(new Intent("android.intent.action.MANAGE_DEFAULT_APP").putExtra("android.intent.extra.ROLE_NAME", this.mRoleName));
        return true;
    }

    private boolean isDefaultApp() {
        return TextUtils.equals(this.mPackageName, (String) CollectionUtils.firstOrNull(this.mRoleManager.getRoleHolders(this.mRoleName)));
    }
}
