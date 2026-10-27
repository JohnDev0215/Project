package android.window;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.util.ArrayMap;
import android.view.SurfaceControl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowContainerTransaction implements Parcelable {
    public static final Parcelable.Creator<WindowContainerTransaction> CREATOR = new Parcelable.Creator<WindowContainerTransaction>() { // from class: android.window.WindowContainerTransaction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowContainerTransaction createFromParcel(Parcel parcel) {
            return new WindowContainerTransaction(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowContainerTransaction[] newArray(int i) {
            return new WindowContainerTransaction[i];
        }
    };
    private final ArrayMap<IBinder, Change> mChanges;
    private final ArrayList<HierarchyOp> mHierarchyOps;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WindowContainerTransaction() {
        this.mChanges = new ArrayMap<>();
        this.mHierarchyOps = new ArrayList<>();
    }

    private WindowContainerTransaction(Parcel parcel) {
        ArrayMap<IBinder, Change> arrayMap = new ArrayMap<>();
        this.mChanges = arrayMap;
        ArrayList<HierarchyOp> arrayList = new ArrayList<>();
        this.mHierarchyOps = arrayList;
        parcel.readMap(arrayMap, null);
        parcel.readList(arrayList, null);
    }

    private Change getOrCreateChange(IBinder iBinder) {
        Change change = this.mChanges.get(iBinder);
        if (change != null) {
            return change;
        }
        Change change2 = new Change();
        this.mChanges.put(iBinder, change2);
        return change2;
    }

    public WindowContainerTransaction setBounds(WindowContainerToken windowContainerToken, Rect rect) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mConfiguration.windowConfiguration.setBounds(rect);
        Change.access$176(orCreateChange, 536870912);
        Change.access$276(orCreateChange, 1);
        return this;
    }

    public WindowContainerTransaction setAppBounds(WindowContainerToken windowContainerToken, Rect rect) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mConfiguration.windowConfiguration.setAppBounds(rect);
        Change.access$176(orCreateChange, 536870912);
        Change.access$276(orCreateChange, 2);
        return this;
    }

    public WindowContainerTransaction setScreenSizeDp(WindowContainerToken windowContainerToken, int i, int i2) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mConfiguration.screenWidthDp = i;
        orCreateChange.mConfiguration.screenHeightDp = i2;
        Change.access$176(orCreateChange, 1024);
        return this;
    }

    public WindowContainerTransaction scheduleFinishEnterPip(WindowContainerToken windowContainerToken, Rect rect) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mPinnedBounds = new Rect(rect);
        Change.access$476(orCreateChange, 4);
        return this;
    }

    public WindowContainerTransaction setBoundsChangeTransaction(WindowContainerToken windowContainerToken, SurfaceControl.Transaction transaction) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mBoundsChangeTransaction = transaction;
        Change.access$476(orCreateChange, 2);
        return this;
    }

    public WindowContainerTransaction setBoundsChangeTransaction(WindowContainerToken windowContainerToken, Rect rect) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        if (orCreateChange.mBoundsChangeSurfaceBounds == null) {
            orCreateChange.mBoundsChangeSurfaceBounds = new Rect();
        }
        orCreateChange.mBoundsChangeSurfaceBounds.set(rect);
        Change.access$476(orCreateChange, 16);
        return this;
    }

    public WindowContainerTransaction setActivityWindowingMode(WindowContainerToken windowContainerToken, int i) {
        getOrCreateChange(windowContainerToken.asBinder()).mActivityWindowingMode = i;
        return this;
    }

    public WindowContainerTransaction setWindowingMode(WindowContainerToken windowContainerToken, int i) {
        getOrCreateChange(windowContainerToken.asBinder()).mWindowingMode = i;
        return this;
    }

    public WindowContainerTransaction setFocusable(WindowContainerToken windowContainerToken, boolean z) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mFocusable = z;
        Change.access$476(orCreateChange, 1);
        return this;
    }

    public WindowContainerTransaction setHidden(WindowContainerToken windowContainerToken, boolean z) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mHidden = z;
        Change.access$476(orCreateChange, 8);
        return this;
    }

    public WindowContainerTransaction setSmallestScreenWidthDp(WindowContainerToken windowContainerToken, int i) {
        Change orCreateChange = getOrCreateChange(windowContainerToken.asBinder());
        orCreateChange.mConfiguration.smallestScreenWidthDp = i;
        Change.access$176(orCreateChange, 2048);
        return this;
    }

    public WindowContainerTransaction reparent(WindowContainerToken windowContainerToken, WindowContainerToken windowContainerToken2, boolean z) {
        this.mHierarchyOps.add(new HierarchyOp(windowContainerToken.asBinder(), windowContainerToken2 == null ? null : windowContainerToken2.asBinder(), z));
        return this;
    }

    public WindowContainerTransaction reorder(WindowContainerToken windowContainerToken, boolean z) {
        this.mHierarchyOps.add(new HierarchyOp(windowContainerToken.asBinder(), z));
        return this;
    }

    public void merge(WindowContainerTransaction windowContainerTransaction, boolean z) {
        int size = windowContainerTransaction.mChanges.size();
        for (int i = 0; i < size; i++) {
            IBinder iBinderKeyAt = windowContainerTransaction.mChanges.keyAt(i);
            Change change = this.mChanges.get(iBinderKeyAt);
            if (change == null) {
                change = new Change();
                this.mChanges.put(iBinderKeyAt, change);
            }
            change.merge(windowContainerTransaction.mChanges.valueAt(i), z);
        }
        int size2 = windowContainerTransaction.mHierarchyOps.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.mHierarchyOps.add(z ? windowContainerTransaction.mHierarchyOps.get(i2) : new HierarchyOp(windowContainerTransaction.mHierarchyOps.get(i2)));
        }
    }

    public Map<IBinder, Change> getChanges() {
        return this.mChanges;
    }

    public List<HierarchyOp> getHierarchyOps() {
        return this.mHierarchyOps;
    }

    public String toString() {
        return "WindowContainerTransaction { changes = " + this.mChanges + " hops = " + this.mHierarchyOps + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.mChanges);
        parcel.writeList(this.mHierarchyOps);
    }

    public static class Change implements Parcelable {
        public static final int CHANGE_BOUNDS_TRANSACTION = 2;
        public static final int CHANGE_BOUNDS_TRANSACTION_RECT = 16;
        public static final int CHANGE_FOCUSABLE = 1;
        public static final int CHANGE_HIDDEN = 8;
        public static final int CHANGE_PIP_CALLBACK = 4;
        public static final Parcelable.Creator<Change> CREATOR = new Parcelable.Creator<Change>() { // from class: android.window.WindowContainerTransaction.Change.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Change createFromParcel(Parcel parcel) {
                return new Change(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Change[] newArray(int i) {
                return new Change[i];
            }
        };
        private int mActivityWindowingMode;
        private Rect mBoundsChangeSurfaceBounds;
        private SurfaceControl.Transaction mBoundsChangeTransaction;
        private int mChangeMask;
        private int mConfigSetMask;
        private final Configuration mConfiguration;
        private boolean mFocusable;
        private boolean mHidden;
        private Rect mPinnedBounds;
        private int mWindowSetMask;
        private int mWindowingMode;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        static /* synthetic */ int access$176(Change change, int i) {
            int i2 = i | change.mConfigSetMask;
            change.mConfigSetMask = i2;
            return i2;
        }

        static /* synthetic */ int access$276(Change change, int i) {
            int i2 = i | change.mWindowSetMask;
            change.mWindowSetMask = i2;
            return i2;
        }

        static /* synthetic */ int access$476(Change change, int i) {
            int i2 = i | change.mChangeMask;
            change.mChangeMask = i2;
            return i2;
        }

        public Change() {
            this.mConfiguration = new Configuration();
            this.mFocusable = true;
            this.mHidden = false;
            this.mChangeMask = 0;
            this.mConfigSetMask = 0;
            this.mWindowSetMask = 0;
            this.mPinnedBounds = null;
            this.mBoundsChangeTransaction = null;
            this.mBoundsChangeSurfaceBounds = null;
            this.mActivityWindowingMode = -1;
            this.mWindowingMode = -1;
        }

        protected Change(Parcel parcel) {
            Configuration configuration = new Configuration();
            this.mConfiguration = configuration;
            this.mFocusable = true;
            this.mHidden = false;
            this.mChangeMask = 0;
            this.mConfigSetMask = 0;
            this.mWindowSetMask = 0;
            this.mPinnedBounds = null;
            this.mBoundsChangeTransaction = null;
            this.mBoundsChangeSurfaceBounds = null;
            this.mActivityWindowingMode = -1;
            this.mWindowingMode = -1;
            configuration.readFromParcel(parcel);
            this.mFocusable = parcel.readBoolean();
            this.mHidden = parcel.readBoolean();
            this.mChangeMask = parcel.readInt();
            this.mConfigSetMask = parcel.readInt();
            this.mWindowSetMask = parcel.readInt();
            if ((this.mChangeMask & 4) != 0) {
                Rect rect = new Rect();
                this.mPinnedBounds = rect;
                rect.readFromParcel(parcel);
            }
            if ((this.mChangeMask & 2) != 0) {
                this.mBoundsChangeTransaction = SurfaceControl.Transaction.CREATOR.createFromParcel(parcel);
            }
            if ((this.mChangeMask & 16) != 0) {
                Rect rect2 = new Rect();
                this.mBoundsChangeSurfaceBounds = rect2;
                rect2.readFromParcel(parcel);
            }
            this.mWindowingMode = parcel.readInt();
            this.mActivityWindowingMode = parcel.readInt();
        }

        public void merge(Change change, boolean z) {
            this.mConfiguration.setTo(change.mConfiguration, change.mConfigSetMask, change.mWindowSetMask);
            this.mConfigSetMask |= change.mConfigSetMask;
            this.mWindowSetMask |= change.mWindowSetMask;
            int i = change.mChangeMask;
            if ((i & 1) != 0) {
                this.mFocusable = change.mFocusable;
            }
            if (z && (i & 2) != 0) {
                this.mBoundsChangeTransaction = change.mBoundsChangeTransaction;
                change.mBoundsChangeTransaction = null;
            }
            if ((i & 4) != 0) {
                this.mPinnedBounds = z ? change.mPinnedBounds : new Rect(change.mPinnedBounds);
            }
            int i2 = change.mChangeMask;
            if ((i2 & 8) != 0) {
                this.mHidden = change.mHidden;
            }
            this.mChangeMask = i2 | this.mChangeMask;
            int i3 = change.mActivityWindowingMode;
            if (i3 >= 0) {
                this.mActivityWindowingMode = i3;
            }
            int i4 = change.mWindowingMode;
            if (i4 >= 0) {
                this.mWindowingMode = i4;
            }
            Rect rect = change.mBoundsChangeSurfaceBounds;
            if (rect != null) {
                if (!z) {
                    rect = new Rect(change.mBoundsChangeSurfaceBounds);
                }
                this.mBoundsChangeSurfaceBounds = rect;
            }
        }

        public int getWindowingMode() {
            return this.mWindowingMode;
        }

        public int getActivityWindowingMode() {
            return this.mActivityWindowingMode;
        }

        public Configuration getConfiguration() {
            return this.mConfiguration;
        }

        public boolean getFocusable() {
            if ((this.mChangeMask & 1) == 0) {
                throw new RuntimeException("Focusable not set. check CHANGE_FOCUSABLE first");
            }
            return this.mFocusable;
        }

        public boolean getHidden() {
            if ((this.mChangeMask & 8) == 0) {
                throw new RuntimeException("Hidden not set. check CHANGE_HIDDEN first");
            }
            return this.mHidden;
        }

        public int getChangeMask() {
            return this.mChangeMask;
        }

        public int getConfigSetMask() {
            return this.mConfigSetMask;
        }

        public int getWindowSetMask() {
            return this.mWindowSetMask;
        }

        public Rect getEnterPipBounds() {
            return this.mPinnedBounds;
        }

        public SurfaceControl.Transaction getBoundsChangeTransaction() {
            return this.mBoundsChangeTransaction;
        }

        public Rect getBoundsChangeSurfaceBounds() {
            return this.mBoundsChangeSurfaceBounds;
        }

        public String toString() {
            int i = this.mConfigSetMask;
            boolean z = ((i & 536870912) == 0 || (this.mWindowSetMask & 1) == 0) ? false : true;
            boolean z2 = ((536870912 & i) == 0 || (this.mWindowSetMask & 2) == 0) ? false : true;
            boolean z3 = (i & 1024) != 0;
            boolean z4 = (i & 2048) != 0;
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            if (z) {
                sb.append("bounds:" + this.mConfiguration.windowConfiguration.getBounds() + SmsManager.REGEX_PREFIX_DELIMITER);
            }
            if (z2) {
                sb.append("appbounds:" + this.mConfiguration.windowConfiguration.getAppBounds() + SmsManager.REGEX_PREFIX_DELIMITER);
            }
            if (z4) {
                sb.append("ssw:" + this.mConfiguration.smallestScreenWidthDp + SmsManager.REGEX_PREFIX_DELIMITER);
            }
            if (z3) {
                sb.append("sw/h:" + this.mConfiguration.screenWidthDp + "x" + this.mConfiguration.screenHeightDp + SmsManager.REGEX_PREFIX_DELIMITER);
            }
            if ((this.mChangeMask & 1) != 0) {
                sb.append("focusable:" + this.mFocusable + SmsManager.REGEX_PREFIX_DELIMITER);
            }
            if (this.mBoundsChangeTransaction != null) {
                sb.append("hasBoundsTransaction,");
            }
            sb.append("}");
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.mConfiguration.writeToParcel(parcel, i);
            parcel.writeBoolean(this.mFocusable);
            parcel.writeBoolean(this.mHidden);
            parcel.writeInt(this.mChangeMask);
            parcel.writeInt(this.mConfigSetMask);
            parcel.writeInt(this.mWindowSetMask);
            Rect rect = this.mPinnedBounds;
            if (rect != null) {
                rect.writeToParcel(parcel, i);
            }
            SurfaceControl.Transaction transaction = this.mBoundsChangeTransaction;
            if (transaction != null) {
                transaction.writeToParcel(parcel, i);
            }
            Rect rect2 = this.mBoundsChangeSurfaceBounds;
            if (rect2 != null) {
                rect2.writeToParcel(parcel, i);
            }
            parcel.writeInt(this.mWindowingMode);
            parcel.writeInt(this.mActivityWindowingMode);
        }
    }

    public static class HierarchyOp implements Parcelable {
        public static final Parcelable.Creator<HierarchyOp> CREATOR = new Parcelable.Creator<HierarchyOp>() { // from class: android.window.WindowContainerTransaction.HierarchyOp.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HierarchyOp createFromParcel(Parcel parcel) {
                return new HierarchyOp(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HierarchyOp[] newArray(int i) {
                return new HierarchyOp[i];
            }
        };
        private final IBinder mContainer;
        private final IBinder mReparent;
        private final boolean mToTop;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public HierarchyOp(IBinder iBinder, IBinder iBinder2, boolean z) {
            this.mContainer = iBinder;
            this.mReparent = iBinder2;
            this.mToTop = z;
        }

        public HierarchyOp(IBinder iBinder, boolean z) {
            this.mContainer = iBinder;
            this.mReparent = iBinder;
            this.mToTop = z;
        }

        public HierarchyOp(HierarchyOp hierarchyOp) {
            this.mContainer = hierarchyOp.mContainer;
            this.mReparent = hierarchyOp.mReparent;
            this.mToTop = hierarchyOp.mToTop;
        }

        protected HierarchyOp(Parcel parcel) {
            this.mContainer = parcel.readStrongBinder();
            this.mReparent = parcel.readStrongBinder();
            this.mToTop = parcel.readBoolean();
        }

        public boolean isReparent() {
            return this.mContainer != this.mReparent;
        }

        public IBinder getNewParent() {
            return this.mReparent;
        }

        public IBinder getContainer() {
            return this.mContainer;
        }

        public boolean getToTop() {
            return this.mToTop;
        }

        public String toString() {
            if (isReparent()) {
                return "{reparent: " + this.mContainer + " to " + (this.mToTop ? "top of " : "bottom of ") + this.mReparent + "}";
            }
            return "{reorder: " + this.mContainer + " to " + (this.mToTop ? "top" : "bottom") + "}";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeStrongBinder(this.mContainer);
            parcel.writeStrongBinder(this.mReparent);
            parcel.writeBoolean(this.mToTop);
        }
    }
}
