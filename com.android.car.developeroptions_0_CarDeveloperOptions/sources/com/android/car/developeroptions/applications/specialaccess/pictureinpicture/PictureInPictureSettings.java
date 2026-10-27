package com.android.car.developeroptions.applications.specialaccess.pictureinpicture;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.util.IconDrawableFactory;
import android.util.Pair;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppInfoBase;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.EmptyTextSettings;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.apppreference.AppPreference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PictureInPictureSettings extends EmptyTextSettings {
    static final List<String> IGNORE_PACKAGE_LIST;
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER;
    private Context mContext;
    private IconDrawableFactory mIconDrawableFactory;
    private PackageManager mPackageManager;
    private UserManager mUserManager;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 812;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.picture_in_picture_settings;
    }

    static {
        ArrayList arrayList = new ArrayList();
        IGNORE_PACKAGE_LIST = arrayList;
        arrayList.add("com.android.systemui");
        SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.applications.specialaccess.pictureinpicture.PictureInPictureSettings.2
            @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
            public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
                ArrayList arrayList2 = new ArrayList();
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = R.xml.picture_in_picture_settings;
                arrayList2.add(searchIndexableResource);
                return arrayList2;
            }
        };
    }

    static class AppComparator implements Comparator<Pair<ApplicationInfo, Integer>> {
        private final Collator mCollator = Collator.getInstance();
        private final PackageManager mPm;

        public AppComparator(PackageManager packageManager) {
            this.mPm = packageManager;
        }

        @Override // java.util.Comparator
        public final int compare(Pair<ApplicationInfo, Integer> pair, Pair<ApplicationInfo, Integer> pair2) {
            CharSequence charSequenceLoadLabel = ((ApplicationInfo) pair.first).loadLabel(this.mPm);
            if (charSequenceLoadLabel == null) {
                charSequenceLoadLabel = ((ApplicationInfo) pair.first).name;
            }
            CharSequence charSequenceLoadLabel2 = ((ApplicationInfo) pair2.first).loadLabel(this.mPm);
            if (charSequenceLoadLabel2 == null) {
                charSequenceLoadLabel2 = ((ApplicationInfo) pair2.first).name;
            }
            int iCompare = this.mCollator.compare(charSequenceLoadLabel.toString(), charSequenceLoadLabel2.toString());
            return iCompare != 0 ? iCompare : ((Integer) pair.second).intValue() - ((Integer) pair2.second).intValue();
        }
    }

    public static boolean checkPackageHasPictureInPictureActivities(String str, ActivityInfo[] activityInfoArr) {
        if (!IGNORE_PACKAGE_LIST.contains(str) && activityInfoArr != null) {
            for (int length = activityInfoArr.length - 1; length >= 0; length--) {
                if (activityInfoArr[length].supportsPictureInPicture()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mPackageManager = activity.getPackageManager();
        this.mUserManager = (UserManager) this.mContext.getSystemService("user");
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(this.mContext);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        ArrayList<Pair<ApplicationInfo, Integer>> arrayListCollectPipApps = collectPipApps(UserHandle.myUserId());
        Collections.sort(arrayListCollectPipApps, new AppComparator(this.mPackageManager));
        Context prefContext = getPrefContext();
        for (Pair<ApplicationInfo, Integer> pair : arrayListCollectPipApps) {
            final ApplicationInfo applicationInfo = (ApplicationInfo) pair.first;
            int iIntValue = ((Integer) pair.second).intValue();
            UserHandle userHandleOf = UserHandle.of(iIntValue);
            final String str = applicationInfo.packageName;
            CharSequence charSequenceLoadLabel = applicationInfo.loadLabel(this.mPackageManager);
            AppPreference appPreference = new AppPreference(prefContext);
            appPreference.setIcon(this.mIconDrawableFactory.getBadgedIcon(applicationInfo, iIntValue));
            appPreference.setTitle(this.mPackageManager.getUserBadgedLabel(charSequenceLoadLabel, userHandleOf));
            appPreference.setSummary(PictureInPictureDetails.getPreferenceSummary(prefContext, applicationInfo.uid, str));
            appPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.applications.specialaccess.pictureinpicture.PictureInPictureSettings.1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference) {
                    String str2 = str;
                    int i = applicationInfo.uid;
                    PictureInPictureSettings pictureInPictureSettings = PictureInPictureSettings.this;
                    AppInfoBase.startAppInfoFragment(PictureInPictureDetails.class, R.string.picture_in_picture_app_detail_title, str2, i, pictureInPictureSettings, -1, pictureInPictureSettings.getMetricsCategory());
                    return true;
                }
            });
            preferenceScreen.addPreference(appPreference);
        }
    }

    @Override // com.android.car.developeroptions.widget.EmptyTextSettings, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setEmptyText(R.string.picture_in_picture_empty_text);
    }

    ArrayList<Pair<ApplicationInfo, Integer>> collectPipApps(int i) {
        ArrayList<Pair<ApplicationInfo, Integer>> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.mUserManager.getProfiles(i).iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(((UserInfo) it.next()).id));
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            int iIntValue = ((Integer) it2.next()).intValue();
            for (PackageInfo packageInfo : this.mPackageManager.getInstalledPackagesAsUser(1, iIntValue)) {
                if (checkPackageHasPictureInPictureActivities(packageInfo.packageName, packageInfo.activities)) {
                    arrayList.add(new Pair<>(packageInfo.applicationInfo, Integer.valueOf(iIntValue)));
                }
            }
        }
        return arrayList;
    }
}
