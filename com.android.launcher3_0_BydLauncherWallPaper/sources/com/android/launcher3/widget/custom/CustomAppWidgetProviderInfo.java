package com.android.launcher3.widget.custom;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.Utilities;

/* JADX INFO: loaded from: classes4.dex */
public class CustomAppWidgetProviderInfo extends LauncherAppWidgetProviderInfo implements Parcelable {
    public static final Parcelable.Creator<CustomAppWidgetProviderInfo> CREATOR = new Parcelable.Creator<CustomAppWidgetProviderInfo>() { // from class: com.android.launcher3.widget.custom.CustomAppWidgetProviderInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomAppWidgetProviderInfo createFromParcel(Parcel parcel) {
            return new CustomAppWidgetProviderInfo(parcel, true, 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomAppWidgetProviderInfo[] newArray(int size) {
            return new CustomAppWidgetProviderInfo[size];
        }
    };
    public final int providerId;

    @Override // com.android.launcher3.LauncherAppWidgetProviderInfo
    public void initSpans(Context context) {
    }

    protected CustomAppWidgetProviderInfo(Parcel parcel, boolean readSelf, int providerId) {
        super(parcel);
        if (readSelf) {
            this.providerId = parcel.readInt();
            this.provider = new ComponentName(parcel.readString(), LauncherAppWidgetProviderInfo.CLS_CUSTOM_WIDGET_PREFIX + providerId);
            this.label = parcel.readString();
            this.initialLayout = parcel.readInt();
            this.icon = parcel.readInt();
            this.previewImage = parcel.readInt();
            this.resizeMode = parcel.readInt();
            this.spanX = parcel.readInt();
            this.spanY = parcel.readInt();
            this.minSpanX = parcel.readInt();
            this.minSpanY = parcel.readInt();
            return;
        }
        this.providerId = providerId;
    }

    @Override // com.android.launcher3.LauncherAppWidgetProviderInfo, com.android.launcher3.icons.ComponentWithLabel
    public String getLabel(PackageManager packageManager) {
        return Utilities.trim(this.label);
    }

    @Override // android.appwidget.AppWidgetProviderInfo
    public String toString() {
        return "WidgetProviderInfo(" + this.provider + ")";
    }

    @Override // android.appwidget.AppWidgetProviderInfo, android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(this.providerId);
        out.writeString(this.provider.getPackageName());
        out.writeString(this.label);
        out.writeInt(this.initialLayout);
        out.writeInt(this.icon);
        out.writeInt(this.previewImage);
        out.writeInt(this.resizeMode);
        out.writeInt(this.spanX);
        out.writeInt(this.spanY);
        out.writeInt(this.minSpanX);
        out.writeInt(this.minSpanY);
    }
}
