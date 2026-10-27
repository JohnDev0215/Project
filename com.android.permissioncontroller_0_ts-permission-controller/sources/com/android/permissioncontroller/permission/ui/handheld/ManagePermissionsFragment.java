package com.android.permissioncontroller.permission.ui.handheld;

import android.R;
import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.permission.model.livedatatypes.PermGroupPackagesUiInfo;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes8.dex */
abstract class ManagePermissionsFragment extends PermissionsFrameFragment implements Preference.OnPreferenceClickListener {
    private static final String LOG_TAG = "ManagePermissionsFragment";
    private Collator mCollator;
    protected Map<String, PermGroupPackagesUiInfo> mPermissionGroups = new HashMap();

    abstract void showPermissionApps(String str);

    ManagePermissionsFragment() {
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
    }

    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        PermGroupPackagesUiInfo group = this.mPermissionGroups.get(key);
        if (group == null) {
            return false;
        }
        showPermissionApps(key);
        return true;
    }

    protected PreferenceScreen updatePermissionsUi() {
        String summary;
        Context context = getPreferenceManager().getContext();
        if (context == null || getActivity() == null || this.mPermissionGroups == null) {
            return null;
        }
        PreferenceScreen screen = getPreferenceScreen();
        if (screen == null) {
            screen = getPreferenceManager().createPreferenceScreen(context);
            setPreferenceScreen(screen);
        }
        List<Preference> toRemove = new ArrayList<>();
        for (int i = screen.getPreferenceCount() - 1; i >= 0; i--) {
            Preference group = screen.getPreference(i);
            if (!this.mPermissionGroups.containsKey(group.getKey())) {
                toRemove.add(group);
            }
        }
        for (Preference pref : toRemove) {
            screen.removePreference(pref);
        }
        for (String groupName : this.mPermissionGroups.keySet()) {
            PermGroupPackagesUiInfo group2 = this.mPermissionGroups.get(groupName);
            Preference preference = findPreference(groupName);
            if (preference == null) {
                preference = new FixedSizeIconPreference(context);
                preference.setOnPreferenceClickListener(this);
                preference.setKey(groupName);
                preference.setIcon(Utils.applyTint(context, KotlinUtils.INSTANCE.getPermGroupIcon(context, groupName), R.attr.colorControlNormal));
                preference.setTitle(KotlinUtils.INSTANCE.getPermGroupLabel(context, groupName));
                preference.setSummary(" ");
                preference.setPersistent(false);
                screen.addPreference(preference);
            }
            if (group2 != null) {
                if (getResources().getBoolean(com.android.permissioncontroller.R.bool.config_useAlternativePermGroupSummary)) {
                    summary = getString(com.android.permissioncontroller.R.string.app_permissions_group_summary2, Integer.valueOf(group2.getNonSystemGranted()), Integer.valueOf(group2.getNonSystemTotal()));
                } else {
                    summary = getString(com.android.permissioncontroller.R.string.app_permissions_group_summary, Integer.valueOf(group2.getNonSystemGranted()), Integer.valueOf(group2.getNonSystemTotal()));
                }
            } else {
                summary = getString(com.android.permissioncontroller.R.string.loading);
            }
            preference.setSummary(summary);
        }
        KotlinUtils.INSTANCE.sortPreferenceGroup(screen, new Function2() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManagePermissionsFragment$VL8E5w7SmChlJ6WVhL_AN5b6gRA
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return this.f$0.lambda$updatePermissionsUi$0$ManagePermissionsFragment((Preference) obj, (Preference) obj2);
            }
        }, false);
        return screen;
    }

    public /* synthetic */ Integer lambda$updatePermissionsUi$0$ManagePermissionsFragment(Preference lhs, Preference rhs) {
        return Integer.valueOf(this.mCollator.compare(lhs.getTitle().toString(), rhs.getTitle().toString()));
    }

    protected static final class FixedSizeIconPreference extends Preference {
        private boolean mShowDividerAbove;
        private boolean mShowDividerBelow;

        FixedSizeIconPreference(Context context) {
            super(context);
            this.mShowDividerAbove = true;
            this.mShowDividerBelow = false;
        }

        FixedSizeIconPreference(Context context, boolean dividerAbove, boolean dividerBelow) {
            super(context);
            this.mShowDividerAbove = true;
            this.mShowDividerBelow = false;
            this.mShowDividerAbove = dividerAbove;
            this.mShowDividerBelow = dividerBelow;
        }

        @Override // androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder holder) {
            super.onBindViewHolder(holder);
            ImageView icon = (ImageView) holder.findViewById(R.id.icon);
            icon.setAdjustViewBounds(true);
            int size = getContext().getResources().getDimensionPixelSize(com.android.permissioncontroller.R.dimen.permission_icon_size);
            icon.setMaxWidth(size);
            icon.setMaxHeight(size);
            icon.getLayoutParams().width = size;
            icon.getLayoutParams().height = size;
            icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            holder.setDividerAllowedAbove(this.mShowDividerAbove);
            holder.setDividerAllowedBelow(this.mShowDividerBelow);
        }
    }
}
