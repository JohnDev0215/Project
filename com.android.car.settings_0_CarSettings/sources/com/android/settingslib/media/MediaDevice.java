package com.android.settingslib.media;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2Manager;
import android.text.TextUtils;
import com.android.settingslib.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public abstract class MediaDevice implements Comparable<MediaDevice> {
    private static final String TAG = "MediaDevice";
    private int mConnectedRecord;
    protected final Context mContext;
    protected final String mPackageName;
    protected final MediaRoute2Info mRouteInfo;
    protected final MediaRouter2Manager mRouterManager;
    private int mState;
    int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaDeviceType {
        public static final int TYPE_3POINT5_MM_AUDIO_DEVICE = 2;
        public static final int TYPE_BLUETOOTH_DEVICE = 4;
        public static final int TYPE_CAST_DEVICE = 5;
        public static final int TYPE_CAST_GROUP_DEVICE = 6;
        public static final int TYPE_FAST_PAIR_BLUETOOTH_DEVICE = 3;
        public static final int TYPE_PHONE_DEVICE = 7;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_USB_C_AUDIO_DEVICE = 1;
    }

    public abstract Drawable getIcon();

    public abstract Drawable getIconWithoutBackground();

    public abstract String getId();

    public abstract String getName();

    public abstract String getSummary();

    public abstract boolean isConnected();

    MediaDevice(Context context, MediaRouter2Manager routerManager, MediaRoute2Info info, String packageName) {
        this.mContext = context;
        this.mRouteInfo = info;
        this.mRouterManager = routerManager;
        this.mPackageName = packageName;
        setType(info);
    }

    private void setType(MediaRoute2Info info) {
        if (info == null) {
            this.mType = 4;
            return;
        }
        int type = info.getType();
        if (type != 2) {
            if (type == 3 || type == 4) {
                this.mType = 2;
                return;
            }
            if (type != 8) {
                if (type != 9 && type != 22) {
                    if (type != 23) {
                        if (type == 2000) {
                            this.mType = 6;
                            return;
                        }
                        switch (type) {
                            case 11:
                            case 12:
                            case 13:
                                break;
                            default:
                                this.mType = 5;
                                break;
                        }
                        return;
                    }
                }
                this.mType = 1;
                return;
            }
            this.mType = 4;
            return;
        }
        this.mType = 7;
    }

    void initDeviceRecord() {
        ConnectionRecordManager.getInstance().fetchLastSelectedDevice(this.mContext);
        this.mConnectedRecord = ConnectionRecordManager.getInstance().fetchConnectionRecord(this.mContext, getId());
    }

    void setColorFilter(Drawable drawable) {
        ColorStateList list = this.mContext.getResources().getColorStateList(R.color.advanced_icon_color, this.mContext.getTheme());
        drawable.setColorFilter(new PorterDuffColorFilter(list.getDefaultColor(), PorterDuff.Mode.SRC_IN));
    }

    void setConnectedRecord() {
        this.mConnectedRecord++;
        ConnectionRecordManager.getInstance().setConnectionRecord(this.mContext, getId(), this.mConnectedRecord);
    }

    public void requestSetVolume(int volume) {
        this.mRouterManager.setRouteVolume(this.mRouteInfo, volume);
    }

    public int getMaxVolume() {
        return this.mRouteInfo.getVolumeMax();
    }

    public int getCurrentVolume() {
        return this.mRouteInfo.getVolume();
    }

    public String getClientPackageName() {
        return this.mRouteInfo.getClientPackageName();
    }

    public int getDeviceType() {
        return this.mType;
    }

    public boolean connect() {
        setConnectedRecord();
        this.mRouterManager.selectRoute(this.mPackageName, this.mRouteInfo);
        return true;
    }

    public void disconnect() {
    }

    public void setState(int state) {
        this.mState = state;
    }

    public int getState() {
        return this.mState;
    }

    @Override // java.lang.Comparable
    public int compareTo(MediaDevice another) {
        if (isConnected() ^ another.isConnected()) {
            return isConnected() ? -1 : 1;
        }
        int i = this.mType;
        int i2 = another.mType;
        if (i != i2) {
            return i < i2 ? -1 : 1;
        }
        if (isFastPairDevice()) {
            return -1;
        }
        if (another.isFastPairDevice()) {
            return 1;
        }
        if (isCarKitDevice()) {
            return -1;
        }
        if (another.isCarKitDevice()) {
            return 1;
        }
        String lastSelectedDevice = ConnectionRecordManager.getInstance().getLastSelectedDevice();
        if (TextUtils.equals(lastSelectedDevice, getId())) {
            return -1;
        }
        if (TextUtils.equals(lastSelectedDevice, another.getId())) {
            return 1;
        }
        int i3 = this.mConnectedRecord;
        int i4 = another.mConnectedRecord;
        if (i3 != i4 && (i4 > 0 || i3 > 0)) {
            return another.mConnectedRecord - this.mConnectedRecord;
        }
        String s1 = getName();
        String s2 = another.getName();
        return s1.compareToIgnoreCase(s2);
    }

    public List<String> getFeatures() {
        return this.mRouteInfo.getFeatures();
    }

    protected boolean isCarKitDevice() {
        return false;
    }

    protected boolean isFastPairDevice() {
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaDevice)) {
            return false;
        }
        MediaDevice otherDevice = (MediaDevice) obj;
        return otherDevice.getId().equals(getId());
    }
}
