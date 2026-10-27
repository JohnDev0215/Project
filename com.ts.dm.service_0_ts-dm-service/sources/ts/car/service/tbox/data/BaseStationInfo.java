package ts.car.service.tbox.data;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class BaseStationInfo {
    private static final String TAG = BaseStationInfo.class.getSimpleName();
    public Bundle mExtras;
    public int netgen;
    public String networkOperator;
    public String plmn;
    public boolean registered;
    public int type;

    public BaseStationInfo() {
        this.networkOperator = new String(Constants.ASCII_CODE);
        this.plmn = new String(Constants.ASCII_CODE);
        this.mExtras = null;
        this.mExtras = new Bundle();
    }

    protected BaseStationInfo(Parcel in) {
        this.networkOperator = new String(Constants.ASCII_CODE);
        this.plmn = new String(Constants.ASCII_CODE);
        this.mExtras = null;
        this.registered = in.readByte() != 0;
        this.type = in.readInt();
        this.netgen = in.readInt();
        this.networkOperator = in.readString();
        this.plmn = in.readString();
        this.mExtras = in.readBundle();
    }

    public final boolean equals(Object otherObject) {
        Log.v(TAG, "this:" + this + " otherObject:" + otherObject);
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != BaseStationInfo.class) {
            return false;
        }
        BaseStationInfo other = (BaseStationInfo) otherObject;
        if (this.registered != other.registered || this.type != other.type || this.netgen != other.netgen || equalBundles(this.mExtras, other.mExtras)) {
            return false;
        }
        if (this.networkOperator == null && other.networkOperator != null) {
            return false;
        }
        String str = this.networkOperator;
        if (str == null || str.equals(other.networkOperator)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "{.registered = " + this.registered + ", .type = " + NetworkType.toString(this.type) + ", .netgen = " + NetworkGeneration.toString(this.netgen) + ", .networkOperator = " + this.networkOperator + ", .mExtras = " + this.mExtras + "}";
    }

    private boolean checkBaseStation() {
        int i = this.type;
        if (i < 0 || i > 20) {
            Log.e(TAG, "checkBaseStation NetworkType fails:" + this.type);
            return false;
        }
        int i2 = this.netgen;
        if (i2 < 0 || i2 > 4) {
            Log.e(TAG, "checkBaseStation NetworkGeneration fails:" + this.netgen);
            return false;
        }
        return true;
    }

    private boolean equalBundles(Bundle left, Bundle right) {
        if (!left.keySet().containsAll(right.keySet()) || !right.keySet().containsAll(left.keySet())) {
            return false;
        }
        for (String key : left.keySet()) {
            Object leftValue = left.get(key);
            Object rightValue = right.get(key);
            if ((leftValue instanceof Collection) && (rightValue instanceof Collection)) {
                Collection leftCollection = (Collection) leftValue;
                Collection rightCollection = (Collection) rightValue;
                if (leftCollection.size() != rightCollection.size()) {
                    return false;
                }
                Iterator leftIterator = leftCollection.iterator();
                Iterator rightIterator = rightCollection.iterator();
                while (leftIterator.hasNext()) {
                    if (!equalBundleObjects(leftIterator.next(), rightIterator.next())) {
                        return false;
                    }
                }
            } else if (!equalBundleObjects(leftValue, rightValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean equalBundleObjects(Object left, Object right) {
        if ((left instanceof Bundle) && (right instanceof Bundle)) {
            return equalBundles((Bundle) left, (Bundle) right);
        }
        return left == right || (left != null && left.equals(right));
    }
}
