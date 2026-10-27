package com.android.bluetooth.map;

import android.graphics.drawable.Drawable;
import com.android.bluetooth.map.BluetoothMapUtils;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapAccountItem implements Comparable<BluetoothMapAccountItem> {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapAccountItem";
    private static final boolean V = false;
    public final String mBase_uri;
    public final String mBase_uri_no_account;
    private final Drawable mIcon;
    private final String mId;
    protected boolean mIsChecked;
    private final String mName;
    private final String mPackageName;
    private final String mProviderAuthority;
    private final BluetoothMapUtils.TYPE mType;
    private final String mUci;
    private final String mUciPrefix;

    public BluetoothMapAccountItem(String id, String name, String packageName, String authority, Drawable icon, BluetoothMapUtils.TYPE appType, String uci, String uciPrefix) {
        this.mName = name;
        this.mIcon = icon;
        this.mPackageName = packageName;
        this.mId = id;
        this.mProviderAuthority = authority;
        this.mType = appType;
        this.mBase_uri_no_account = "content://" + authority;
        this.mBase_uri = this.mBase_uri_no_account + "/" + id;
        this.mUci = uci;
        this.mUciPrefix = uciPrefix;
    }

    public static BluetoothMapAccountItem create(String id, String name, String packageName, String authority, Drawable icon, BluetoothMapUtils.TYPE appType) {
        return new BluetoothMapAccountItem(id, name, packageName, authority, icon, appType, null, null);
    }

    public static BluetoothMapAccountItem create(String id, String name, String packageName, String authority, Drawable icon, BluetoothMapUtils.TYPE appType, String uci, String uciPrefix) {
        return new BluetoothMapAccountItem(id, name, packageName, authority, icon, appType, uci, uciPrefix);
    }

    public long getAccountId() {
        String str = this.mId;
        if (str != null) {
            return Long.parseLong(str);
        }
        return -1L;
    }

    public String getUci() {
        return this.mUci;
    }

    public String getUciPrefix() {
        return this.mUciPrefix;
    }

    public String getUciFull() {
        String str;
        if (this.mUci == null || (str = this.mUciPrefix) == null) {
            return null;
        }
        return str + ":" + this.mUci;
    }

    @Override // java.lang.Comparable
    public int compareTo(BluetoothMapAccountItem other) {
        return (other.mId.equals(this.mId) && other.mName.equals(this.mName) && other.mPackageName.equals(this.mPackageName) && other.mProviderAuthority.equals(this.mProviderAuthority) && other.mIsChecked == this.mIsChecked && other.mType.equals(this.mType)) ? 0 : -1;
    }

    public int hashCode() {
        int i = 1 * 31;
        String str = this.mId;
        int result = i + (str == null ? 0 : str.hashCode());
        int result2 = result * 31;
        String str2 = this.mName;
        int result3 = (result2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.mPackageName;
        int result4 = (result3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.mProviderAuthority;
        return result4 + (str4 != null ? str4.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BluetoothMapAccountItem other = (BluetoothMapAccountItem) obj;
        String str = this.mId;
        if (str == null) {
            if (other.mId != null) {
                return false;
            }
        } else if (!str.equals(other.mId)) {
            return false;
        }
        String str2 = this.mName;
        if (str2 == null) {
            if (other.mName != null) {
                return false;
            }
        } else if (!str2.equals(other.mName)) {
            return false;
        }
        String str3 = this.mPackageName;
        if (str3 == null) {
            if (other.mPackageName != null) {
                return false;
            }
        } else if (!str3.equals(other.mPackageName)) {
            return false;
        }
        String str4 = this.mProviderAuthority;
        if (str4 == null) {
            if (other.mProviderAuthority != null) {
                return false;
            }
        } else if (!str4.equals(other.mProviderAuthority)) {
            return false;
        }
        BluetoothMapUtils.TYPE type = this.mType;
        if (type == null) {
            if (other.mType != null) {
                return false;
            }
        } else if (!type.equals(other.mType)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.mName + " (" + this.mBase_uri + ")";
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public String getName() {
        return this.mName;
    }

    public String getId() {
        return this.mId;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    public BluetoothMapUtils.TYPE getType() {
        return this.mType;
    }
}
