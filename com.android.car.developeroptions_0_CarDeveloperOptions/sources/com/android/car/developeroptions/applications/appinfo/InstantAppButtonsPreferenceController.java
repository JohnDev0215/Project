package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppStoreUtil;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;
import com.android.settingslib.core.lifecycle.events.OnOptionsItemSelected;
import com.android.settingslib.core.lifecycle.events.OnPrepareOptionsMenu;
import com.android.settingslib.widget.LayoutPreference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class InstantAppButtonsPreferenceController extends BasePreferenceController implements LifecycleObserver, OnCreateOptionsMenu, OnPrepareOptionsMenu, OnOptionsItemSelected {
    private static final String KEY_INSTANT_APP_BUTTONS = "instant_app_buttons";
    private static final String META_DATA_DEFAULT_URI = "default-url";
    private MenuItem mInstallMenu;
    private String mLaunchUri;
    private final String mPackageName;
    private final AppInfoDashboardFragment mParent;
    private LayoutPreference mPreference;

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

    public InstantAppButtonsPreferenceController(Context context, AppInfoDashboardFragment appInfoDashboardFragment, String str, Lifecycle lifecycle) {
        super(context, KEY_INSTANT_APP_BUTTONS);
        this.mParent = appInfoDashboardFragment;
        this.mPackageName = str;
        this.mLaunchUri = getDefaultLaunchUri();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return AppUtils.isInstant(this.mParent.getPackageInfo().applicationInfo) ? 0 : 4;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(KEY_INSTANT_APP_BUTTONS);
        this.mPreference = layoutPreference;
        initButtons(layoutPreference.findViewById(R.id.instant_app_button_container));
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (TextUtils.isEmpty(this.mLaunchUri)) {
            return;
        }
        menu.add(0, 3, 2, R.string.install_text).setShowAsAction(0);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnOptionsItemSelected
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 3) {
            return false;
        }
        Intent appStoreLink = AppStoreUtil.getAppStoreLink(this.mContext, this.mPackageName);
        if (appStoreLink == null) {
            return true;
        }
        this.mParent.startActivity(appStoreLink);
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPrepareOptionsMenu
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(3);
        this.mInstallMenu = menuItemFindItem;
        if (menuItemFindItem == null || AppStoreUtil.getAppStoreLink(this.mContext, this.mPackageName) != null) {
            return;
        }
        this.mInstallMenu.setEnabled(false);
    }

    private void initButtons(View view) {
        Button button = (Button) view.findViewById(R.id.install);
        Button button2 = (Button) view.findViewById(R.id.clear_data);
        Button button3 = (Button) view.findViewById(R.id.launch);
        if (!TextUtils.isEmpty(this.mLaunchUri)) {
            button.setVisibility(8);
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(this.mPackageName);
            intent.setData(Uri.parse(this.mLaunchUri));
            intent.addFlags(268435456);
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.appinfo.-$$Lambda$InstantAppButtonsPreferenceController$jtobC2WdgF4V7p17PUY0FPFTHJ0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$initButtons$0$InstantAppButtonsPreferenceController(intent, view2);
                }
            });
        } else {
            button3.setVisibility(8);
            final Intent appStoreLink = AppStoreUtil.getAppStoreLink(this.mContext, this.mPackageName);
            if (appStoreLink != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.appinfo.-$$Lambda$InstantAppButtonsPreferenceController$n6z9xKvhzCoG2S-zUncQx2c_H-g
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$initButtons$1$InstantAppButtonsPreferenceController(appStoreLink, view2);
                    }
                });
            } else {
                button.setEnabled(false);
            }
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.appinfo.-$$Lambda$InstantAppButtonsPreferenceController$90Uqb1i6l844qBv3YdtHLr7rC9E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initButtons$2$InstantAppButtonsPreferenceController(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initButtons$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initButtons$0$InstantAppButtonsPreferenceController(Intent intent, View view) {
        this.mParent.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initButtons$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initButtons$1$InstantAppButtonsPreferenceController(Intent intent, View view) {
        this.mParent.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initButtons$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initButtons$2$InstantAppButtonsPreferenceController(View view) {
        showDialog();
    }

    private void showDialog() {
        InstantAppButtonDialogFragment instantAppButtonDialogFragmentNewInstance = InstantAppButtonDialogFragment.newInstance(this.mPackageName);
        instantAppButtonDialogFragmentNewInstance.setTargetFragment(this.mParent, 0);
        instantAppButtonDialogFragmentNewInstance.show(this.mParent.getFragmentManager(), KEY_INSTANT_APP_BUTTONS);
    }

    private String getDefaultLaunchUri() {
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(this.mPackageName);
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 8388736).iterator();
        while (it.hasNext()) {
            Bundle bundle = it.next().activityInfo.metaData;
            if (bundle != null) {
                String string = bundle.getString(META_DATA_DEFAULT_URI);
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
        }
        return null;
    }
}
