package android.view;

import android.graphics.Region;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pools;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WindowInfo implements Parcelable {
    private static final int MAX_POOL_SIZE = 10;
    public IBinder activityToken;
    public List<IBinder> childTokens;
    public boolean focused;
    public boolean hasFlagWatchOutsideTouch;
    public boolean inPictureInPicture;
    public int layer;
    public IBinder parentToken;
    public CharSequence title;
    public IBinder token;
    public int type;
    private static final Pools.SynchronizedPool<WindowInfo> sPool = new Pools.SynchronizedPool<>(10);
    public static final Parcelable.Creator<WindowInfo> CREATOR = new Parcelable.Creator<WindowInfo>() { // from class: android.view.WindowInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowInfo createFromParcel(Parcel parcel) {
            WindowInfo windowInfoObtain = WindowInfo.obtain();
            windowInfoObtain.initFromParcel(parcel);
            return windowInfoObtain;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowInfo[] newArray(int i) {
            return new WindowInfo[i];
        }
    };
    public Region regionInScreen = new Region();
    public long accessibilityIdOfAnchor = AccessibilityNodeInfo.UNDEFINED_NODE_ID;
    public int displayId = -1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private WindowInfo() {
    }

    public static WindowInfo obtain() {
        WindowInfo windowInfoAcquire = sPool.acquire();
        return windowInfoAcquire == null ? new WindowInfo() : windowInfoAcquire;
    }

    public static WindowInfo obtain(WindowInfo windowInfo) {
        WindowInfo windowInfoObtain = obtain();
        windowInfoObtain.displayId = windowInfo.displayId;
        windowInfoObtain.type = windowInfo.type;
        windowInfoObtain.layer = windowInfo.layer;
        windowInfoObtain.token = windowInfo.token;
        windowInfoObtain.parentToken = windowInfo.parentToken;
        windowInfoObtain.activityToken = windowInfo.activityToken;
        windowInfoObtain.focused = windowInfo.focused;
        windowInfoObtain.regionInScreen.set(windowInfo.regionInScreen);
        windowInfoObtain.title = windowInfo.title;
        windowInfoObtain.accessibilityIdOfAnchor = windowInfo.accessibilityIdOfAnchor;
        windowInfoObtain.inPictureInPicture = windowInfo.inPictureInPicture;
        windowInfoObtain.hasFlagWatchOutsideTouch = windowInfo.hasFlagWatchOutsideTouch;
        List<IBinder> list = windowInfo.childTokens;
        if (list != null && !list.isEmpty()) {
            List<IBinder> list2 = windowInfoObtain.childTokens;
            if (list2 == null) {
                windowInfoObtain.childTokens = new ArrayList(windowInfo.childTokens);
            } else {
                list2.addAll(windowInfo.childTokens);
            }
        }
        return windowInfoObtain;
    }

    public void recycle() {
        clear();
        sPool.release(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.displayId);
        parcel.writeInt(this.type);
        parcel.writeInt(this.layer);
        parcel.writeStrongBinder(this.token);
        parcel.writeStrongBinder(this.parentToken);
        parcel.writeStrongBinder(this.activityToken);
        parcel.writeInt(this.focused ? 1 : 0);
        this.regionInScreen.writeToParcel(parcel, i);
        parcel.writeCharSequence(this.title);
        parcel.writeLong(this.accessibilityIdOfAnchor);
        parcel.writeInt(this.inPictureInPicture ? 1 : 0);
        parcel.writeInt(this.hasFlagWatchOutsideTouch ? 1 : 0);
        List<IBinder> list = this.childTokens;
        if (list != null && !list.isEmpty()) {
            parcel.writeInt(1);
            parcel.writeBinderList(this.childTokens);
        } else {
            parcel.writeInt(0);
        }
    }

    public String toString() {
        return "WindowInfo[title=" + this.title + ", displayId=" + this.displayId + ", type=" + this.type + ", layer=" + this.layer + ", token=" + this.token + ", region=" + this.regionInScreen + ", bounds=" + this.regionInScreen.getBounds() + ", parent=" + this.parentToken + ", focused=" + this.focused + ", children=" + this.childTokens + ", accessibility anchor=" + this.accessibilityIdOfAnchor + ", pictureInPicture=" + this.inPictureInPicture + ", watchOutsideTouch=" + this.hasFlagWatchOutsideTouch + ']';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFromParcel(Parcel parcel) {
        this.displayId = parcel.readInt();
        this.type = parcel.readInt();
        this.layer = parcel.readInt();
        this.token = parcel.readStrongBinder();
        this.parentToken = parcel.readStrongBinder();
        this.activityToken = parcel.readStrongBinder();
        this.focused = parcel.readInt() == 1;
        this.regionInScreen = Region.CREATOR.createFromParcel(parcel);
        this.title = parcel.readCharSequence();
        this.accessibilityIdOfAnchor = parcel.readLong();
        this.inPictureInPicture = parcel.readInt() == 1;
        this.hasFlagWatchOutsideTouch = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            if (this.childTokens == null) {
                this.childTokens = new ArrayList();
            }
            parcel.readBinderList(this.childTokens);
        }
    }

    private void clear() {
        this.displayId = -1;
        this.type = 0;
        this.layer = 0;
        this.token = null;
        this.parentToken = null;
        this.activityToken = null;
        this.focused = false;
        this.regionInScreen.setEmpty();
        List<IBinder> list = this.childTokens;
        if (list != null) {
            list.clear();
        }
        this.inPictureInPicture = false;
        this.hasFlagWatchOutsideTouch = false;
    }
}
