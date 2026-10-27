package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2Manager;
import com.android.settingslib.R;
import com.android.settingslib.bluetooth.BluetoothUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneMediaDevice extends MediaDevice {
    public static final String PHONE_ID = "phone_media_device_id";
    private static final String TAG = "PhoneMediaDevice";
    public static final String USB_HEADSET_ID = "usb_headset_media_device_id";
    public static final String WIRED_HEADSET_ID = "wired_headset_media_device_id";
    private String mSummary;

    PhoneMediaDevice(Context context, MediaRouter2Manager routerManager, MediaRoute2Info info, String packageName) {
        super(context, routerManager, info, packageName);
        this.mSummary = "";
        initDeviceRecord();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    @Override // com.android.settingslib.media.MediaDevice
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getName() {
        /*
            r2 = this;
            android.media.MediaRoute2Info r0 = r2.mRouteInfo
            int r0 = r0.getType()
            r1 = 3
            if (r0 == r1) goto L27
            r1 = 4
            if (r0 == r1) goto L27
            r1 = 9
            if (r0 == r1) goto L20
            r1 = 22
            if (r0 == r1) goto L27
            switch(r0) {
                case 11: goto L27;
                case 12: goto L27;
                case 13: goto L20;
                default: goto L17;
            }
        L17:
            android.content.Context r0 = r2.mContext
            int r1 = com.android.settingslib.R.string.media_transfer_this_device_name
            java.lang.String r0 = r0.getString(r1)
            goto L30
        L20:
            android.media.MediaRoute2Info r0 = r2.mRouteInfo
            java.lang.CharSequence r0 = r0.getName()
            goto L30
        L27:
            android.content.Context r0 = r2.mContext
            int r1 = com.android.settingslib.R.string.media_transfer_wired_usb_device_name
            java.lang.String r0 = r0.getString(r1)
        L30:
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.media.PhoneMediaDevice.getName():java.lang.String");
    }

    @Override // com.android.settingslib.media.MediaDevice
    public String getSummary() {
        return this.mSummary;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public Drawable getIcon() {
        Drawable drawable = getIconWithoutBackground();
        setColorFilter(drawable);
        return BluetoothUtils.buildAdvancedDrawable(this.mContext, drawable);
    }

    @Override // com.android.settingslib.media.MediaDevice
    public Drawable getIconWithoutBackground() {
        return this.mContext.getDrawable(getDrawableResId());
    }

    int getDrawableResId() {
        int type = this.mRouteInfo.getType();
        if (type != 3 && type != 4 && type != 9 && type != 22) {
            switch (type) {
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    int resId = R.drawable.ic_smartphone;
                    return resId;
            }
        }
        int resId2 = R.drawable.ic_headphone;
        return resId2;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public String getId() {
        int type = this.mRouteInfo.getType();
        if (type == 3 || type == 4) {
            return WIRED_HEADSET_ID;
        }
        if (type != 9 && type != 22) {
            switch (type) {
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    return PHONE_ID;
            }
        }
        return USB_HEADSET_ID;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public boolean isConnected() {
        return true;
    }

    public void updateSummary(boolean isActive) {
        String string;
        if (isActive) {
            string = this.mContext.getString(R.string.bluetooth_active_no_battery_level);
        } else {
            string = "";
        }
        this.mSummary = string;
    }
}
