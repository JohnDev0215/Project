package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2Manager;
import com.android.settingslib.R;
import com.android.settingslib.bluetooth.BluetoothUtils;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class InfoMediaDevice extends MediaDevice {
    private static final String TAG = "InfoMediaDevice";

    InfoMediaDevice(Context context, MediaRouter2Manager routerManager, MediaRoute2Info info, String packageName) {
        super(context, routerManager, info, packageName);
        initDeviceRecord();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public String getName() {
        return this.mRouteInfo.getName().toString();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public String getSummary() {
        if (this.mRouteInfo.getClientPackageName() != null) {
            return this.mContext.getString(R.string.bluetooth_active_no_battery_level);
        }
        return null;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public Drawable getIcon() {
        Drawable drawable = getIconWithoutBackground();
        setColorFilter(drawable);
        return BluetoothUtils.buildAdvancedDrawable(this.mContext, drawable);
    }

    @Override // com.android.settingslib.media.MediaDevice
    public Drawable getIconWithoutBackground() {
        return this.mContext.getDrawable(getDrawableResIdByFeature());
    }

    int getDrawableResId() {
        int type = this.mRouteInfo.getType();
        if (type == 1001) {
            int resId = R.drawable.ic_media_display_device;
            return resId;
        }
        if (type == 2000) {
            int resId2 = R.drawable.ic_media_group_device;
            return resId2;
        }
        int resId3 = R.drawable.ic_media_speaker_device;
        return resId3;
    }

    int getDrawableResIdByFeature() {
        List<String> features = this.mRouteInfo.getFeatures();
        if (features.contains("android.media.route.feature.REMOTE_GROUP_PLAYBACK")) {
            int resId = R.drawable.ic_media_group_device;
            return resId;
        }
        if (features.contains("android.media.route.feature.REMOTE_VIDEO_PLAYBACK")) {
            int resId2 = R.drawable.ic_media_display_device;
            return resId2;
        }
        int resId3 = R.drawable.ic_media_speaker_device;
        return resId3;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public String getId() {
        return MediaDeviceUtils.getId(this.mRouteInfo);
    }

    @Override // com.android.settingslib.media.MediaDevice
    public boolean isConnected() {
        return true;
    }
}
