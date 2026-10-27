package com.android.settingslib.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Tile implements Parcelable {
    private static final String TAG = "Tile";
    private String mCategory;
    protected ComponentInfo mComponentInfo;
    private final String mComponentName;
    private final String mComponentPackage;
    private final Intent mIntent;
    long mLastUpdateTime;
    private Bundle mMetaData;
    private CharSequence mSummaryOverride;
    public ArrayList<UserHandle> userHandle = new ArrayList<>();
    public static final Parcelable.Creator<Tile> CREATOR = new Parcelable.Creator<Tile>() { // from class: com.android.settingslib.drawer.Tile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tile createFromParcel(Parcel source) {
            boolean isProviderTile = source.readBoolean();
            source.setDataPosition(0);
            return isProviderTile ? new ProviderTile(source) : new ActivityTile(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Tile[] newArray(int size) {
            return new Tile[size];
        }
    };
    public static final Comparator<Tile> TILE_COMPARATOR = new Comparator() { // from class: com.android.settingslib.drawer.-$$Lambda$Tile$5_ETnVHzVG6DF0RKPoy76eRI-QM
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Tile.lambda$static$0((Tile) obj, (Tile) obj2);
        }
    };

    protected abstract int getComponentIcon(ComponentInfo componentInfo);

    protected abstract ComponentInfo getComponentInfo(Context context);

    protected abstract CharSequence getComponentLabel(Context context);

    public abstract String getDescription();

    public abstract int getId();

    public Tile(ComponentInfo info, String category) {
        this.mComponentInfo = info;
        this.mComponentPackage = info.packageName;
        this.mComponentName = this.mComponentInfo.name;
        this.mCategory = category;
        this.mIntent = new Intent().setClassName(this.mComponentPackage, this.mComponentName);
    }

    Tile(Parcel in) {
        in.readBoolean();
        this.mComponentPackage = in.readString();
        this.mComponentName = in.readString();
        this.mIntent = new Intent().setClassName(this.mComponentPackage, this.mComponentName);
        int number = in.readInt();
        for (int i = 0; i < number; i++) {
            this.userHandle.add((UserHandle) UserHandle.CREATOR.createFromParcel(in));
        }
        this.mCategory = in.readString();
        this.mMetaData = in.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBoolean(this instanceof ProviderTile);
        dest.writeString(this.mComponentPackage);
        dest.writeString(this.mComponentName);
        int size = this.userHandle.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            this.userHandle.get(i).writeToParcel(dest, flags);
        }
        dest.writeString(this.mCategory);
        dest.writeBundle(this.mMetaData);
    }

    public String getPackageName() {
        return this.mComponentPackage;
    }

    public String getComponentName() {
        return this.mComponentName;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public void setCategory(String newCategoryKey) {
        this.mCategory = newCategoryKey;
    }

    public int getOrder() {
        if (hasOrder()) {
            return this.mMetaData.getInt(TileUtils.META_DATA_KEY_ORDER);
        }
        return 0;
    }

    public boolean hasOrder() {
        return this.mMetaData.containsKey(TileUtils.META_DATA_KEY_ORDER) && (this.mMetaData.get(TileUtils.META_DATA_KEY_ORDER) instanceof Integer);
    }

    public boolean hasSwitch() {
        Bundle bundle = this.mMetaData;
        return bundle != null && bundle.containsKey(TileUtils.META_DATA_PREFERENCE_SWITCH_URI);
    }

    public CharSequence getTitle(Context context) {
        CharSequence title = null;
        ensureMetadataNotStale(context);
        PackageManager packageManager = context.getPackageManager();
        if (this.mMetaData.containsKey(TileUtils.META_DATA_PREFERENCE_TITLE)) {
            if (this.mMetaData.containsKey(TileUtils.META_DATA_PREFERENCE_TITLE_URI)) {
                return null;
            }
            if (this.mMetaData.get(TileUtils.META_DATA_PREFERENCE_TITLE) instanceof Integer) {
                try {
                    Resources res = packageManager.getResourcesForApplication(this.mComponentPackage);
                    title = res.getString(this.mMetaData.getInt(TileUtils.META_DATA_PREFERENCE_TITLE));
                } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                    Log.w(TAG, "Couldn't find info", e);
                }
            } else {
                title = this.mMetaData.getString(TileUtils.META_DATA_PREFERENCE_TITLE);
            }
        }
        if (title == null) {
            return getComponentLabel(context);
        }
        return title;
    }

    public void overrideSummary(CharSequence summaryOverride) {
        this.mSummaryOverride = summaryOverride;
    }

    public CharSequence getSummary(Context context) {
        CharSequence charSequence = this.mSummaryOverride;
        if (charSequence != null) {
            return charSequence;
        }
        ensureMetadataNotStale(context);
        PackageManager packageManager = context.getPackageManager();
        Bundle bundle = this.mMetaData;
        if (bundle == null || bundle.containsKey(TileUtils.META_DATA_PREFERENCE_SUMMARY_URI) || !this.mMetaData.containsKey(TileUtils.META_DATA_PREFERENCE_SUMMARY)) {
            return null;
        }
        if (this.mMetaData.get(TileUtils.META_DATA_PREFERENCE_SUMMARY) instanceof Integer) {
            try {
                Resources res = packageManager.getResourcesForApplication(this.mComponentPackage);
                CharSequence summary = res.getString(this.mMetaData.getInt(TileUtils.META_DATA_PREFERENCE_SUMMARY));
                return summary;
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.d(TAG, "Couldn't find info", e);
                return null;
            }
        }
        CharSequence summary2 = this.mMetaData.getString(TileUtils.META_DATA_PREFERENCE_SUMMARY);
        return summary2;
    }

    public void setMetaData(Bundle metaData) {
        this.mMetaData = metaData;
    }

    public Bundle getMetaData() {
        return this.mMetaData;
    }

    public String getKey(Context context) {
        if (!hasKey()) {
            return null;
        }
        ensureMetadataNotStale(context);
        if (this.mMetaData.get(TileUtils.META_DATA_PREFERENCE_KEYHINT) instanceof Integer) {
            return context.getResources().getString(this.mMetaData.getInt(TileUtils.META_DATA_PREFERENCE_KEYHINT));
        }
        return this.mMetaData.getString(TileUtils.META_DATA_PREFERENCE_KEYHINT);
    }

    public boolean hasKey() {
        Bundle bundle = this.mMetaData;
        return bundle != null && bundle.containsKey(TileUtils.META_DATA_PREFERENCE_KEYHINT);
    }

    public Icon getIcon(Context context) {
        if (context == null || this.mMetaData == null) {
            return null;
        }
        ensureMetadataNotStale(context);
        ComponentInfo componentInfo = getComponentInfo(context);
        if (componentInfo == null) {
            Log.w(TAG, "Cannot find ComponentInfo for " + getDescription());
            return null;
        }
        int iconResId = this.mMetaData.getInt(TileUtils.META_DATA_PREFERENCE_ICON);
        if (iconResId == 0 && !this.mMetaData.containsKey(TileUtils.META_DATA_PREFERENCE_ICON_URI)) {
            iconResId = getComponentIcon(componentInfo);
        }
        if (iconResId == 0) {
            return null;
        }
        Icon icon = Icon.createWithResource(componentInfo.packageName, iconResId);
        if (isIconTintable(context)) {
            TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.colorControlNormal});
            int tintColor = a.getColor(0, 0);
            a.recycle();
            icon.setTint(tintColor);
        }
        return icon;
    }

    public boolean isIconTintable(Context context) {
        ensureMetadataNotStale(context);
        Bundle bundle = this.mMetaData;
        if (bundle != null && bundle.containsKey(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE)) {
            return this.mMetaData.getBoolean(TileUtils.META_DATA_PREFERENCE_ICON_TINTABLE);
        }
        return false;
    }

    private void ensureMetadataNotStale(Context context) {
        PackageManager pm = context.getApplicationContext().getPackageManager();
        try {
            long lastUpdateTime = pm.getPackageInfo(this.mComponentPackage, 128).lastUpdateTime;
            if (lastUpdateTime == this.mLastUpdateTime) {
                return;
            }
            this.mComponentInfo = null;
            getComponentInfo(context);
            this.mLastUpdateTime = lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "Can't find package, probably uninstalled.");
        }
    }

    public boolean isPrimaryProfileOnly() {
        return isPrimaryProfileOnly(this.mMetaData);
    }

    static boolean isPrimaryProfileOnly(Bundle metaData) {
        String profile;
        String profile2 = TileUtils.PROFILE_ALL;
        if (metaData == null) {
            profile = TileUtils.PROFILE_ALL;
        } else {
            profile = metaData.getString(TileUtils.META_DATA_KEY_PROFILE);
        }
        if (profile != null) {
            profile2 = profile;
        }
        return TextUtils.equals(profile2, TileUtils.PROFILE_PRIMARY);
    }

    static /* synthetic */ int lambda$static$0(Tile lhs, Tile rhs) {
        return rhs.getOrder() - lhs.getOrder();
    }
}
