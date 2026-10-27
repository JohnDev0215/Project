package android.app.admin;

import android.content.ComponentName;
import android.stats.devicepolicy.nano.StringList;
import com.android.framework.protobuf.nano.MessageNano;
import com.android.internal.util.FrameworkStatsLog;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class DevicePolicyEventLogger {
    private String mAdminPackageName;
    private boolean mBooleanValue;
    private final int mEventId;
    private int mIntValue;
    private String[] mStringArrayValue;
    private long mTimePeriodMs;

    private DevicePolicyEventLogger(int i) {
        this.mEventId = i;
    }

    public static DevicePolicyEventLogger createEvent(int i) {
        return new DevicePolicyEventLogger(i);
    }

    public int getEventId() {
        return this.mEventId;
    }

    public DevicePolicyEventLogger setInt(int i) {
        this.mIntValue = i;
        return this;
    }

    public int getInt() {
        return this.mIntValue;
    }

    public DevicePolicyEventLogger setBoolean(boolean z) {
        this.mBooleanValue = z;
        return this;
    }

    public boolean getBoolean() {
        return this.mBooleanValue;
    }

    public DevicePolicyEventLogger setTimePeriod(long j) {
        this.mTimePeriodMs = j;
        return this;
    }

    public long getTimePeriod() {
        return this.mTimePeriodMs;
    }

    public DevicePolicyEventLogger setStrings(String... strArr) {
        this.mStringArrayValue = strArr;
        return this;
    }

    public DevicePolicyEventLogger setStrings(String str, String[] strArr) {
        Objects.requireNonNull(strArr, "values parameter cannot be null");
        String[] strArr2 = new String[strArr.length + 1];
        this.mStringArrayValue = strArr2;
        strArr2[0] = str;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return this;
    }

    public DevicePolicyEventLogger setStrings(String str, String str2, String[] strArr) {
        Objects.requireNonNull(strArr, "values parameter cannot be null");
        String[] strArr2 = new String[strArr.length + 2];
        this.mStringArrayValue = strArr2;
        strArr2[0] = str;
        strArr2[1] = str2;
        System.arraycopy(strArr, 0, strArr2, 2, strArr.length);
        return this;
    }

    public String[] getStringArray() {
        String[] strArr = this.mStringArrayValue;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public DevicePolicyEventLogger setAdmin(String str) {
        this.mAdminPackageName = str;
        return this;
    }

    public DevicePolicyEventLogger setAdmin(ComponentName componentName) {
        this.mAdminPackageName = componentName != null ? componentName.getPackageName() : null;
        return this;
    }

    public String getAdminPackageName() {
        return this.mAdminPackageName;
    }

    public void write() {
        FrameworkStatsLog.write(103, this.mEventId, this.mAdminPackageName, this.mIntValue, this.mBooleanValue, this.mTimePeriodMs, stringArrayValueToBytes(this.mStringArrayValue));
    }

    private static byte[] stringArrayValueToBytes(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        StringList stringList = new StringList();
        stringList.stringValue = strArr;
        return MessageNano.toByteArray(stringList);
    }
}
