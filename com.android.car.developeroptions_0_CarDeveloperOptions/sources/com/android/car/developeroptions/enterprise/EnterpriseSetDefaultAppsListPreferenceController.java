package com.android.car.developeroptions.enterprise;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.applications.EnterpriseDefaultApps;
import com.android.car.developeroptions.applications.UserAppInfo;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.users.UserFeatureProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EnterpriseSetDefaultAppsListPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final ApplicationFeatureProvider mApplicationFeatureProvider;
    private List<EnumMap<EnterpriseDefaultApps, List<ApplicationInfo>>> mApps;
    private final EnterprisePrivacyFeatureProvider mEnterprisePrivacyFeatureProvider;
    private final SettingsPreferenceFragment mParent;
    private final PackageManager mPm;
    private final UserFeatureProvider mUserFeatureProvider;
    private List<UserInfo> mUsers;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public EnterpriseSetDefaultAppsListPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, PackageManager packageManager) {
        super(context);
        this.mUsers = Collections.emptyList();
        this.mApps = Collections.emptyList();
        this.mPm = packageManager;
        this.mParent = settingsPreferenceFragment;
        FeatureFactory factory = FeatureFactory.getFactory(context);
        this.mApplicationFeatureProvider = factory.getApplicationFeatureProvider(context);
        this.mEnterprisePrivacyFeatureProvider = factory.getEnterprisePrivacyFeatureProvider(context);
        this.mUserFeatureProvider = factory.getUserFeatureProvider(context);
        buildAppList();
    }

    private void buildAppList() {
        this.mUsers = new ArrayList();
        this.mApps = new ArrayList();
        for (UserHandle userHandle : this.mUserFeatureProvider.getUserProfiles()) {
            EnumMap<EnterpriseDefaultApps, List<ApplicationInfo>> enumMap = null;
            boolean z = false;
            for (EnterpriseDefaultApps enterpriseDefaultApps : EnterpriseDefaultApps.values()) {
                List<UserAppInfo> listFindPersistentPreferredActivities = this.mApplicationFeatureProvider.findPersistentPreferredActivities(userHandle.getIdentifier(), enterpriseDefaultApps.getIntents());
                if (!listFindPersistentPreferredActivities.isEmpty()) {
                    if (!z) {
                        this.mUsers.add(listFindPersistentPreferredActivities.get(0).userInfo);
                        enumMap = new EnumMap<>(EnterpriseDefaultApps.class);
                        this.mApps.add(enumMap);
                        z = true;
                    }
                    ArrayList arrayList = new ArrayList();
                    Iterator<UserAppInfo> it = listFindPersistentPreferredActivities.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().appInfo);
                    }
                    enumMap.put(enterpriseDefaultApps, arrayList);
                }
            }
        }
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.enterprise.-$$Lambda$EnterpriseSetDefaultAppsListPreferenceController$lDpbttOzT8X2J5d7JCEvUoRLY_I
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$buildAppList$0$EnterpriseSetDefaultAppsListPreferenceController();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateUi, reason: merged with bridge method [inline-methods] */
    public void lambda$buildAppList$0$EnterpriseSetDefaultAppsListPreferenceController() {
        Context context = this.mParent.getPreferenceManager().getContext();
        PreferenceScreen preferenceScreen = this.mParent.getPreferenceScreen();
        if (preferenceScreen == null) {
            return;
        }
        if (!this.mEnterprisePrivacyFeatureProvider.isInCompMode() && this.mUsers.size() == 1) {
            createPreferences(context, preferenceScreen, this.mApps.get(0));
            return;
        }
        for (int i = 0; i < this.mUsers.size(); i++) {
            UserInfo userInfo = this.mUsers.get(i);
            PreferenceCategory preferenceCategory = new PreferenceCategory(context);
            preferenceScreen.addPreference(preferenceCategory);
            if (userInfo.isManagedProfile()) {
                preferenceCategory.setTitle(R.string.managed_device_admin_title);
            } else {
                preferenceCategory.setTitle(R.string.personal_device_admin_title);
            }
            preferenceCategory.setOrder(i);
            createPreferences(context, preferenceCategory, this.mApps.get(i));
        }
    }

    private void createPreferences(Context context, PreferenceGroup preferenceGroup, EnumMap<EnterpriseDefaultApps, List<ApplicationInfo>> enumMap) {
        if (preferenceGroup == null) {
            return;
        }
        for (EnterpriseDefaultApps enterpriseDefaultApps : EnterpriseDefaultApps.values()) {
            List<ApplicationInfo> list = enumMap.get(enterpriseDefaultApps);
            if (list != null && !list.isEmpty()) {
                Preference preference = new Preference(context);
                preference.setTitle(getTitle(context, enterpriseDefaultApps, list.size()));
                preference.setSummary(buildSummaryString(context, list));
                preference.setOrder(enterpriseDefaultApps.ordinal());
                preference.setSelectable(false);
                preferenceGroup.addPreference(preference);
            }
        }
    }

    private CharSequence buildSummaryString(Context context, List<ApplicationInfo> list) {
        CharSequence[] charSequenceArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charSequenceArr[i] = list.get(i).loadLabel(this.mPm);
        }
        if (list.size() == 1) {
            return charSequenceArr[0];
        }
        return list.size() == 2 ? context.getString(R.string.app_names_concatenation_template_2, charSequenceArr[0], charSequenceArr[1]) : context.getString(R.string.app_names_concatenation_template_3, charSequenceArr[0], charSequenceArr[1], charSequenceArr[2]);
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.enterprise.EnterpriseSetDefaultAppsListPreferenceController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps;

        static {
            int[] iArr = new int[EnterpriseDefaultApps.values().length];
            $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps = iArr;
            try {
                iArr[EnterpriseDefaultApps.BROWSER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[EnterpriseDefaultApps.CALENDAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[EnterpriseDefaultApps.CONTACTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[EnterpriseDefaultApps.PHONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[EnterpriseDefaultApps.MAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[EnterpriseDefaultApps.EMAIL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[EnterpriseDefaultApps.CAMERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private String getTitle(Context context, EnterpriseDefaultApps enterpriseDefaultApps, int i) {
        switch (AnonymousClass1.$SwitchMap$com$android$car$developeroptions$applications$EnterpriseDefaultApps[enterpriseDefaultApps.ordinal()]) {
            case 1:
                return context.getString(R.string.default_browser_title);
            case 2:
                return context.getString(R.string.default_calendar_app_title);
            case 3:
                return context.getString(R.string.default_contacts_app_title);
            case 4:
                return context.getResources().getQuantityString(R.plurals.default_phone_app_title, i);
            case 5:
                return context.getString(R.string.default_map_app_title);
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return context.getResources().getQuantityString(R.plurals.default_email_app_title, i);
            case 7:
                return context.getResources().getQuantityString(R.plurals.default_camera_app_title, i);
            default:
                throw new IllegalStateException("Unknown type of default " + enterpriseDefaultApps);
        }
    }
}
