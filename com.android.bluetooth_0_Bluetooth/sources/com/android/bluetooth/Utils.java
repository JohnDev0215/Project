package com.android.bluetooth;

import android.app.AppOpsManager;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.INetd;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.ParcelUuid;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.map.BluetoothMapContentObserver;
import com.android.bluetooth.mapapi.BluetoothMapContract;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class Utils {
    static final int BD_ADDR_LEN = 6;
    static final int BD_UUID_LEN = 16;
    private static final int MICROS_PER_UNIT = 625;
    public static final char PAUSE = ',';
    private static final String PTS_TEST_MODE_PROPERTY = "persist.bluetooth.pts";
    private static final String TAG = "BluetoothUtils";
    public static final char WAIT = ';';
    static int sSystemUiUid = -10000;
    static int sForegroundUserId = -10000;

    private static boolean isPause(char c) {
        return c == 'p' || c == 'P';
    }

    private static boolean isToneWait(char c) {
        return c == 'w' || c == 'W';
    }

    public static String getAddressStringFromByte(byte[] address) {
        if (address == null || address.length != 6) {
            return null;
        }
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(address[0]), Byte.valueOf(address[1]), Byte.valueOf(address[2]), Byte.valueOf(address[3]), Byte.valueOf(address[4]), Byte.valueOf(address[5]));
    }

    public static byte[] getByteAddress(BluetoothDevice device) {
        return getBytesFromAddress(device.getAddress());
    }

    public static byte[] addressToBytes(String address) {
        return getBytesFromAddress(address);
    }

    public static byte[] getBytesFromAddress(String address) {
        int j = 0;
        byte[] output = new byte[6];
        int i = 0;
        while (i < address.length()) {
            if (address.charAt(i) != ':') {
                output[j] = (byte) Integer.parseInt(address.substring(i, i + 2), 16);
                j++;
                i++;
            }
            i++;
        }
        return output;
    }

    public static int byteArrayToInt(byte[] valueBuf) {
        return byteArrayToInt(valueBuf, 0);
    }

    public static short byteArrayToShort(byte[] valueBuf) {
        ByteBuffer converter = ByteBuffer.wrap(valueBuf);
        converter.order(ByteOrder.nativeOrder());
        return converter.getShort();
    }

    public static int byteArrayToInt(byte[] valueBuf, int offset) {
        ByteBuffer converter = ByteBuffer.wrap(valueBuf);
        converter.order(ByteOrder.nativeOrder());
        return converter.getInt(offset);
    }

    public static String byteArrayToString(byte[] valueBuf) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < valueBuf.length; idx++) {
            if (idx != 0) {
                sb.append(" ");
            }
            sb.append(String.format("%02x", Byte.valueOf(valueBuf[idx])));
        }
        return sb.toString();
    }

    public static String byteArrayToUtf8String(byte[] valueBuf) {
        CharsetDecoder decoder = Charset.forName("UTF8").newDecoder();
        ByteBuffer byteBuffer = ByteBuffer.wrap(valueBuf);
        try {
            String valueStr = decoder.decode(byteBuffer).toString();
            return valueStr;
        } catch (Exception ex) {
            Log.e(TAG, "Error when parsing byte array to UTF8 String. " + ex);
            return INetd.NEXTHOP_NONE;
        }
    }

    public static byte[] intToByteArray(int value) {
        ByteBuffer converter = ByteBuffer.allocate(4);
        converter.order(ByteOrder.nativeOrder());
        converter.putInt(value);
        return converter.array();
    }

    public static byte[] uuidToByteArray(ParcelUuid pUuid) {
        ByteBuffer converter = ByteBuffer.allocate(16);
        converter.order(ByteOrder.BIG_ENDIAN);
        UUID uuid = pUuid.getUuid();
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        converter.putLong(msb);
        converter.putLong(8, lsb);
        return converter.array();
    }

    public static byte[] uuidsToByteArray(ParcelUuid[] uuids) {
        int length = uuids.length * 16;
        ByteBuffer converter = ByteBuffer.allocate(length);
        converter.order(ByteOrder.BIG_ENDIAN);
        for (int i = 0; i < uuids.length; i++) {
            UUID uuid = uuids[i].getUuid();
            long msb = uuid.getMostSignificantBits();
            long lsb = uuid.getLeastSignificantBits();
            converter.putLong(i * 16, msb);
            converter.putLong((i * 16) + 8, lsb);
        }
        return converter.array();
    }

    public static ParcelUuid[] byteArrayToUuid(byte[] val) {
        int numUuids = val.length / 16;
        ParcelUuid[] puuids = new ParcelUuid[numUuids];
        int offset = 0;
        ByteBuffer converter = ByteBuffer.wrap(val);
        converter.order(ByteOrder.BIG_ENDIAN);
        for (int i = 0; i < numUuids; i++) {
            puuids[i] = new ParcelUuid(new UUID(converter.getLong(offset), converter.getLong(offset + 8)));
            offset += 16;
        }
        return puuids;
    }

    public static String debugGetAdapterStateString(int state) {
        switch (state) {
            case 10:
                return "STATE_OFF";
            case 11:
                return "STATE_TURNING_ON";
            case 12:
                return "STATE_ON";
            case 13:
                return "STATE_TURNING_OFF";
            default:
                return "UNKNOWN";
        }
    }

    public static String ellipsize(String s) {
        if (!Build.TYPE.equals("user")) {
            return s;
        }
        if (s == null) {
            return null;
        }
        if (s.length() < 3) {
            return s;
        }
        return s.charAt(0) + "⋯" + s.charAt(s.length() - 1);
    }

    public static void copyStream(InputStream is, OutputStream os, int bufferSize) throws IOException {
        if (is != null && os != null) {
            byte[] buffer = new byte[bufferSize];
            while (true) {
                int bytesRead = is.read(buffer);
                if (bytesRead >= 0) {
                    os.write(buffer, 0, bytesRead);
                } else {
                    return;
                }
            }
        }
    }

    public static void safeCloseStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (Throwable t) {
                Log.d(TAG, "Error closing stream", t);
            }
        }
    }

    public static void safeCloseStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (Throwable t) {
                Log.d(TAG, "Error closing stream", t);
            }
        }
    }

    public static void setSystemUiUid(int uid) {
        sSystemUiUid = uid;
    }

    public static void setForegroundUserId(int uid) {
        sForegroundUserId = uid;
    }

    public static void enforceBluetoothPermission(Context context) {
        context.enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
    }

    public static void enforceBluetoothAdminPermission(Context context) {
        context.enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
    }

    public static void enforceBluetoothPrivilegedPermission(Context context) {
        context.enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH PRIVILEGED permission");
    }

    public static void enforceLocalMacAddressPermission(Context context) {
        context.enforceCallingOrSelfPermission("android.permission.LOCAL_MAC_ADDRESS", "Need LOCAL_MAC_ADDRESS permission");
    }

    public static void enforceDumpPermission(Context context) {
        context.enforceCallingOrSelfPermission("android.permission.DUMP", "Need DUMP permission");
    }

    public static boolean callerIsSystemOrActiveUser(String tag, String method) {
        if (!checkCaller()) {
            Log.w(TAG, method + "() - Not allowed for non-active user and non-system user");
            return false;
        }
        return true;
    }

    public static boolean callerIsSystemOrActiveOrManagedUser(Context context, String tag, String method) {
        if (!checkCallerAllowManagedProfiles(context)) {
            Log.w(TAG, method + "() - Not allowed for non-active user and non-system and non-managed user");
            return false;
        }
        return true;
    }

    public static boolean checkCaller() {
        int callingUser = UserHandle.getCallingUserId();
        int callingUid = Binder.getCallingUid();
        return sForegroundUserId == callingUser || UserHandle.getAppId(sSystemUiUid) == UserHandle.getAppId(callingUid) || UserHandle.getAppId(1000) == UserHandle.getAppId(callingUid);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean checkCallerAllowManagedProfiles(android.content.Context r10) {
        /*
            if (r10 != 0) goto L7
            boolean r0 = checkCaller()
            return r0
        L7:
            int r0 = android.os.UserHandle.getCallingUserId()
            int r1 = android.os.Binder.getCallingUid()
            long r2 = android.os.Binder.clearCallingIdentity()
            r4 = 0
            java.lang.String r5 = "user"
            java.lang.Object r5 = r10.getSystemService(r5)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            android.os.UserManager r5 = (android.os.UserManager) r5     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            android.content.pm.UserInfo r6 = r5.getProfileParent(r0)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r6 == 0) goto L25
            int r7 = r6.id     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            goto L27
        L25:
            r7 = -10000(0xffffffffffffd8f0, float:NaN)
        L27:
            int r8 = com.android.bluetooth.Utils.sForegroundUserId     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r8 == r0) goto L47
            int r8 = com.android.bluetooth.Utils.sForegroundUserId     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r8 == r7) goto L47
            int r8 = com.android.bluetooth.Utils.sSystemUiUid     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            int r8 = android.os.UserHandle.getAppId(r8)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            int r9 = android.os.UserHandle.getAppId(r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r8 == r9) goto L47
            r8 = 1000(0x3e8, float:1.401E-42)
            int r8 = android.os.UserHandle.getAppId(r8)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            int r9 = android.os.UserHandle.getAppId(r1)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e
            if (r8 != r9) goto L48
        L47:
            r4 = 1
        L48:
            android.os.Binder.restoreCallingIdentity(r2)
            return r4
        L4c:
            r4 = move-exception
            goto L6a
        L4e:
            r5 = move-exception
            java.lang.String r6 = "BluetoothUtils"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c
            r7.<init>()     // Catch: java.lang.Throwable -> L4c
            java.lang.String r8 = "checkCallerAllowManagedProfiles: Exception ex="
            r7.append(r8)     // Catch: java.lang.Throwable -> L4c
            r7.append(r5)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L4c
            android.util.Log.e(r6, r7)     // Catch: java.lang.Throwable -> L4c
            android.os.Binder.restoreCallingIdentity(r2)
            return r4
        L6a:
            android.os.Binder.restoreCallingIdentity(r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.Utils.checkCallerAllowManagedProfiles(android.content.Context):boolean");
    }

    public static void enforceAdminPermission(ContextWrapper context) {
        context.enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
    }

    public static boolean blockedByLocationOff(Context context, UserHandle userHandle) {
        return !((LocationManager) context.getSystemService(LocationManager.class)).isLocationEnabledForUser(userHandle);
    }

    public static boolean checkCallerHasCoarseLocation(Context context, AppOpsManager appOps, String callingPackage, String callingFeatureId, UserHandle userHandle) {
        if (blockedByLocationOff(context, userHandle)) {
            Log.e(TAG, "Permission denial: Location is off.");
            return false;
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && isAppOppAllowed(appOps, "android:fine_location", callingPackage, callingFeatureId)) {
            return true;
        }
        Log.e(TAG, "Permission denial: Need ACCESS_COARSE_LOCATION permission to get scan results");
        return false;
    }

    public static boolean checkCallerHasCoarseOrFineLocation(Context context, AppOpsManager appOps, String callingPackage, String callingFeatureId, UserHandle userHandle) {
        if (blockedByLocationOff(context, userHandle)) {
            Log.e(TAG, "Permission denial: Location is off.");
            return false;
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 && isAppOppAllowed(appOps, "android:fine_location", callingPackage, callingFeatureId)) {
            return true;
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && isAppOppAllowed(appOps, "android:fine_location", callingPackage, callingFeatureId)) {
            return true;
        }
        Log.e(TAG, "Permission denial: Need ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATIONpermission to get scan results");
        return false;
    }

    public static boolean checkCallerHasFineLocation(Context context, AppOpsManager appOps, String callingPackage, String callingFeatureId, UserHandle userHandle) {
        if (blockedByLocationOff(context, userHandle)) {
            Log.e(TAG, "Permission denial: Location is off.");
            return false;
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 && isAppOppAllowed(appOps, "android:fine_location", callingPackage, callingFeatureId)) {
            return true;
        }
        Log.e(TAG, "Permission denial: Need ACCESS_FINE_LOCATION permission to get scan results");
        return false;
    }

    public static boolean checkCallerHasNetworkSettingsPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.NETWORK_SETTINGS") == 0;
    }

    public static boolean checkCallerHasNetworkSetupWizardPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.NETWORK_SETUP_WIZARD") == 0;
    }

    public static boolean checkCallerHasScanWithoutLocationPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.RADIO_SCAN_WITHOUT_LOCATION") == 0;
    }

    public static boolean isQApp(Context context, String pkgName) {
        try {
            return context.getPackageManager().getApplicationInfo(pkgName, 0).targetSdkVersion >= 29;
        } catch (PackageManager.NameNotFoundException e) {
            return true;
        }
    }

    private static boolean isAppOppAllowed(AppOpsManager appOps, String op, String callingPackage, String callingFeatureId) {
        return appOps.noteOp(op, Binder.getCallingUid(), callingPackage, callingFeatureId, null) == 0;
    }

    public static int millsToUnit(int milliseconds) {
        return (int) (TimeUnit.MILLISECONDS.toMicros(milliseconds) / 625);
    }

    public static boolean isInstrumentationTestMode() {
        try {
            return Class.forName("com.android.bluetooth.FileSystemWriteTest") != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void enforceInstrumentationTestMode() {
        if (!isInstrumentationTestMode()) {
            throw new IllegalStateException("Not in BluetoothInstrumentationTest");
        }
    }

    public static boolean isPtsTestMode() {
        return SystemProperties.getBoolean(PTS_TEST_MODE_PROPERTY, false);
    }

    public static String getUidPidString() {
        return "uid/pid=" + Binder.getCallingUid() + "/" + Binder.getCallingPid();
    }

    public static String getLocalTimeString() {
        return DateTimeFormatter.ofPattern("MM-dd HH:mm:ss.SSS").withZone(ZoneId.systemDefault()).format(Instant.now());
    }

    public static void skipCurrentTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type != 1) {
                if (type == 3 && parser.getDepth() <= outerDepth) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public static String convertPreDial(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        int len = phoneNumber.length();
        StringBuilder ret = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = phoneNumber.charAt(i);
            if (isPause(c)) {
                c = PAUSE;
            } else if (isToneWait(c)) {
                c = WAIT;
            }
            ret.append(c);
        }
        return ret.toString();
    }

    public static boolean moveMessageToFolder(Context context, Uri uri, boolean messageSent) {
        if (uri == null) {
            return false;
        }
        ContentValues values = new ContentValues(3);
        if (messageSent) {
            values.put(BluetoothMapContract.FILTER_READ_STATUS, (Integer) 1);
            values.put(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, (Integer) 2);
        } else {
            values.put(BluetoothMapContract.FILTER_READ_STATUS, (Integer) 0);
            values.put(BluetoothMapContentObserver.EXTRA_MESSAGE_SENT_MSG_TYPE, (Integer) 5);
        }
        values.put("error_code", (Integer) 0);
        return 1 == context.getContentResolver().update(uri, values, null, null);
    }
}
