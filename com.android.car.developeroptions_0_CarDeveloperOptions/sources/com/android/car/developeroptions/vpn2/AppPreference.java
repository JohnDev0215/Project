package com.android.car.developeroptions.vpn2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.internal.net.VpnConfig;

/* JADX INFO: loaded from: classes.dex */
public class AppPreference extends ManageablePreference {
    public static final int STATE_DISCONNECTED = ManageablePreference.STATE_NONE;
    private final String mName;
    private final String mPackageName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppPreference(Context context, int i, String str) {
        super(context, null);
        Drawable defaultActivityIcon = null;
        super.setUserId(i);
        this.mPackageName = str;
        try {
            Context userContext = getUserContext();
            PackageManager packageManager = userContext.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(this.mPackageName, 0);
                if (packageInfo != null) {
                    defaultActivityIcon = packageInfo.applicationInfo.loadIcon(packageManager);
                    str = VpnConfig.getVpnLabel(userContext, this.mPackageName).toString();
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (defaultActivityIcon == null) {
                defaultActivityIcon = packageManager.getDefaultActivityIcon();
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        this.mName = str;
        setTitle(str);
        setIcon(defaultActivityIcon);
    }

    public PackageInfo getPackageInfo() {
        try {
            return getUserContext().getPackageManager().getPackageInfo(this.mPackageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public String getLabel() {
        return this.mName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    private Context getUserContext() throws PackageManager.NameNotFoundException {
        return getContext().createPackageContextAsUser(getContext().getPackageName(), 0, UserHandle.of(this.mUserId));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.preference.Preference, java.lang.Comparable
    public int compareTo(Preference preference) {
        if (preference instanceof AppPreference) {
            AppPreference appPreference = (AppPreference) preference;
            int i = appPreference.mState - this.mState;
            if (i != 0) {
                return i;
            }
            int iCompareToIgnoreCase = this.mName.compareToIgnoreCase(appPreference.mName);
            if (iCompareToIgnoreCase != 0) {
                return iCompareToIgnoreCase;
            }
            int iCompareTo = this.mPackageName.compareTo(appPreference.mPackageName);
            return iCompareTo == 0 ? this.mUserId - appPreference.mUserId : iCompareTo;
        }
        if (preference instanceof LegacyVpnPreference) {
            return -((LegacyVpnPreference) preference).compareTo((Preference) this);
        }
        return super.compareTo(preference);
    }
}
