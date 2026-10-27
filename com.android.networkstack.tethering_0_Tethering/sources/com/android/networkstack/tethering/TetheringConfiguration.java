package com.android.networkstack.tethering;

import android.content.Context;
import android.content.res.Resources;
import android.net.TetheringConfigurationParcel;
import android.net.util.SharedLog;
import android.provider.DeviceConfig;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes.dex */
public class TetheringConfiguration {
    public final int activeDataSubId;
    public final boolean chooseUpstreamAutomatically;
    public final String[] defaultIPv4DNS;
    public final boolean enableLegacyDhcpServer;
    public final boolean isDunRequired;
    public final String[] legacyDhcpRanges;
    private final boolean mDunPreferred;
    private final boolean mEnableBpfOffload;
    private final int mOffloadPollInterval;
    public final Collection<Integer> preferredUpstreamIfaceTypes;
    public final String[] provisioningApp;
    public final String provisioningAppNoUi;
    public final int provisioningCheckPeriod;
    public final String provisioningResponse;
    public final String[] tetherableBluetoothRegexs;
    public final String[] tetherableNcmRegexs;
    public final String[] tetherableUsbRegexs;
    public final String[] tetherableWifiP2pRegexs;
    public final String[] tetherableWifiRegexs;
    public final String[] tetherableWigigRegexs;
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String[] LEGACY_DHCP_DEFAULT_RANGE = {"192.168.42.2", "192.168.42.254", "192.168.43.2", "192.168.43.254", "192.168.44.2", "192.168.44.254", "192.168.45.2", "192.168.45.254", "192.168.46.2", "192.168.46.254", "192.168.47.2", "192.168.47.254", "192.168.48.2", "192.168.48.254", "192.168.49.2", "192.168.49.254"};
    private static final String[] DEFAULT_IPV4_DNS = {"8.8.4.4", "8.8.8.8"};

    public TetheringConfiguration(Context context, SharedLog sharedLog, int i) {
        SharedLog sharedLogForSubComponent = sharedLog.forSubComponent("config");
        this.activeDataSubId = i;
        Resources resources = getResources(context, i);
        this.tetherableUsbRegexs = getResourceStringArray(resources, R.array.config_tether_usb_regexs);
        this.tetherableNcmRegexs = getResourceStringArray(resources, R.array.config_tether_ncm_regexs);
        this.tetherableWifiRegexs = getResourceStringArray(resources, R.array.config_tether_wifi_regexs);
        this.tetherableWigigRegexs = getResourceStringArray(resources, R.array.config_tether_wigig_regexs);
        this.tetherableWifiP2pRegexs = getResourceStringArray(resources, R.array.config_tether_wifi_p2p_regexs);
        this.tetherableBluetoothRegexs = getResourceStringArray(resources, R.array.config_tether_bluetooth_regexs);
        boolean resourceBoolean = getResourceBoolean(resources, R.bool.config_tether_enable_dun_preferred, false);
        this.mDunPreferred = resourceBoolean;
        this.isDunRequired = resourceBoolean ? true : checkDunRequired(context);
        this.chooseUpstreamAutomatically = getResourceBoolean(resources, R.bool.config_tether_upstream_automatic, false);
        this.preferredUpstreamIfaceTypes = getUpstreamIfaceTypes(resources, this.isDunRequired);
        this.legacyDhcpRanges = getLegacyDhcpRanges(resources);
        this.defaultIPv4DNS = copy(DEFAULT_IPV4_DNS);
        this.mEnableBpfOffload = getEnableBpfOffload(resources);
        this.enableLegacyDhcpServer = getEnableLegacyDhcpServer(resources);
        this.provisioningApp = getResourceStringArray(resources, R.array.config_mobile_hotspot_provision_app);
        this.provisioningAppNoUi = getResourceString(resources, R.string.config_mobile_hotspot_provision_app_no_ui);
        this.provisioningCheckPeriod = getResourceInteger(resources, R.integer.config_mobile_hotspot_provision_check_period, 0);
        this.provisioningResponse = getResourceString(resources, R.string.config_mobile_hotspot_provision_response);
        this.mOffloadPollInterval = getResourceInteger(resources, R.integer.config_tether_offload_poll_interval, 5000);
        sharedLogForSubComponent.log(toString());
    }

    public boolean isUsb(String str) {
        return matchesDownstreamRegexs(str, this.tetherableUsbRegexs);
    }

    public boolean isWifi(String str) {
        return matchesDownstreamRegexs(str, this.tetherableWifiRegexs);
    }

    public boolean isWigig(String str) {
        return matchesDownstreamRegexs(str, this.tetherableWigigRegexs);
    }

    public boolean isWifiP2p(String str) {
        return matchesDownstreamRegexs(str, this.tetherableWifiP2pRegexs);
    }

    public boolean isWifiP2pLegacyTetheringMode() {
        String[] strArr = this.tetherableWifiP2pRegexs;
        return strArr == null || strArr.length == 0;
    }

    public boolean isBluetooth(String str) {
        return matchesDownstreamRegexs(str, this.tetherableBluetoothRegexs);
    }

    public boolean isNcm(String str) {
        return matchesDownstreamRegexs(str, this.tetherableNcmRegexs);
    }

    public boolean hasMobileHotspotProvisionApp() {
        return !TextUtils.isEmpty(this.provisioningAppNoUi);
    }

    public void dump(PrintWriter printWriter) {
        printWriter.print("activeDataSubId: ");
        printWriter.println(this.activeDataSubId);
        dumpStringArray(printWriter, "tetherableUsbRegexs", this.tetherableUsbRegexs);
        dumpStringArray(printWriter, "tetherableWifiRegexs", this.tetherableWifiRegexs);
        dumpStringArray(printWriter, "tetherableWifiP2pRegexs", this.tetherableWifiP2pRegexs);
        dumpStringArray(printWriter, "tetherableBluetoothRegexs", this.tetherableBluetoothRegexs);
        dumpStringArray(printWriter, "tetherableNcmRegexs", this.tetherableNcmRegexs);
        printWriter.print("isDunRequired: ");
        printWriter.println(this.isDunRequired);
        printWriter.print("mDunPreferred");
        printWriter.println(this.mDunPreferred);
        printWriter.print("chooseUpstreamAutomatically: ");
        printWriter.println(this.chooseUpstreamAutomatically);
        printWriter.print("legacyPreredUpstreamIfaceTypes: ");
        printWriter.println(Arrays.toString(toIntArray(this.preferredUpstreamIfaceTypes)));
        dumpStringArray(printWriter, "legacyDhcpRanges", this.legacyDhcpRanges);
        dumpStringArray(printWriter, "defaultIPv4DNS", this.defaultIPv4DNS);
        printWriter.print("offloadPollInterval: ");
        printWriter.println(this.mOffloadPollInterval);
        dumpStringArray(printWriter, "provisioningApp", this.provisioningApp);
        printWriter.print("provisioningAppNoUi: ");
        printWriter.println(this.provisioningAppNoUi);
        printWriter.print("enableBpfOffload: ");
        printWriter.println(this.mEnableBpfOffload);
        printWriter.print("enableLegacyDhcpServer: ");
        printWriter.println(this.enableLegacyDhcpServer);
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(String.format("activeDataSubId:%d", Integer.valueOf(this.activeDataSubId)));
        stringJoiner.add(String.format("tetherableUsbRegexs:%s", makeString(this.tetherableUsbRegexs)));
        stringJoiner.add(String.format("tetherableWifiRegexs:%s", makeString(this.tetherableWifiRegexs)));
        stringJoiner.add(String.format("tetherableWifiP2pRegexs:%s", makeString(this.tetherableWifiP2pRegexs)));
        stringJoiner.add(String.format("tetherableBluetoothRegexs:%s", makeString(this.tetherableBluetoothRegexs)));
        stringJoiner.add(String.format("isDunRequired:%s", Boolean.valueOf(this.isDunRequired)));
        stringJoiner.add(String.format("chooseUpstreamAutomatically:%s", Boolean.valueOf(this.chooseUpstreamAutomatically)));
        stringJoiner.add(String.format("offloadPollInterval:%d", Integer.valueOf(this.mOffloadPollInterval)));
        stringJoiner.add(String.format("preferredUpstreamIfaceTypes:%s", toIntArray(this.preferredUpstreamIfaceTypes)));
        stringJoiner.add(String.format("provisioningApp:%s", makeString(this.provisioningApp)));
        stringJoiner.add(String.format("provisioningAppNoUi:%s", this.provisioningAppNoUi));
        stringJoiner.add(String.format("enableBpfOffload:%s", Boolean.valueOf(this.mEnableBpfOffload)));
        stringJoiner.add(String.format("enableLegacyDhcpServer:%s", Boolean.valueOf(this.enableLegacyDhcpServer)));
        return String.format("TetheringConfiguration{%s}", stringJoiner.toString());
    }

    private static void dumpStringArray(PrintWriter printWriter, String str, String[] strArr) {
        printWriter.print(str);
        printWriter.print(": ");
        if (strArr != null) {
            StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
            for (String str2 : strArr) {
                stringJoiner.add(str2);
            }
            printWriter.print(stringJoiner.toString());
        } else {
            printWriter.print("null");
        }
        printWriter.println();
    }

    private static String makeString(String[] strArr) {
        if (strArr == null) {
            return "null";
        }
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (String str : strArr) {
            stringJoiner.add(str);
        }
        return stringJoiner.toString();
    }

    public static boolean checkDunRequired(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.isTetheringApnRequired();
        }
        return false;
    }

    public int getOffloadPollInterval() {
        return this.mOffloadPollInterval;
    }

    public boolean isBpfOffloadEnabled() {
        return this.mEnableBpfOffload;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Collection<java.lang.Integer> getUpstreamIfaceTypes(android.content.res.Resources r8, boolean r9) {
        /*
            int r0 = com.android.networkstack.tethering.R.array.config_tether_upstream_types
            int[] r8 = r8.getIntArray(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r8.length
            r0.<init>(r1)
            int r1 = r8.length
            r2 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r4 = r2
        L13:
            r5 = 4
            r6 = 5
            if (r4 >= r1) goto L30
            r7 = r8[r4]
            if (r7 == 0) goto L23
            if (r7 == r5) goto L20
            if (r7 == r6) goto L23
            goto L26
        L20:
            if (r9 != 0) goto L26
            goto L2d
        L23:
            if (r9 == 0) goto L26
            goto L2d
        L26:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)
            r0.add(r5)
        L2d:
            int r4 = r4 + 1
            goto L13
        L30:
            if (r9 == 0) goto L36
            appendIfNotPresent(r0, r5)
            goto L52
        L36:
            r8 = 2
            java.lang.Integer[] r8 = new java.lang.Integer[r8]
            r8[r2] = r3
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
            r1 = 1
            r8[r1] = r9
            boolean r8 = containsOneOf(r0, r8)
            if (r8 != 0) goto L52
            r0.add(r3)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            r0.add(r8)
        L52:
            r8 = 9
            prependIfNotPresent(r0, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.networkstack.tethering.TetheringConfiguration.getUpstreamIfaceTypes(android.content.res.Resources, boolean):java.util.Collection");
    }

    private static boolean matchesDownstreamRegexs(String str, String[] strArr) {
        for (String str2 : strArr) {
            if (str.matches(str2)) {
                return true;
            }
        }
        return false;
    }

    private static String[] getLegacyDhcpRanges(Resources resources) {
        String[] resourceStringArray = getResourceStringArray(resources, R.array.config_tether_dhcp_range);
        return (resourceStringArray.length <= 0 || resourceStringArray.length % 2 != 0) ? copy(LEGACY_DHCP_DEFAULT_RANGE) : resourceStringArray;
    }

    private static String getResourceString(Resources resources, int i) {
        try {
            return resources.getString(i);
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    private static boolean getResourceBoolean(Resources resources, int i, boolean z) {
        try {
            return resources.getBoolean(i);
        } catch (Resources.NotFoundException unused) {
            return z;
        }
    }

    private static String[] getResourceStringArray(Resources resources, int i) {
        String[] strArr = EMPTY_STRING_ARRAY;
        try {
            String[] stringArray = resources.getStringArray(i);
            return stringArray != null ? stringArray : strArr;
        } catch (Resources.NotFoundException unused) {
            return strArr;
        }
    }

    private static int getResourceInteger(Resources resources, int i, int i2) {
        try {
            return resources.getInteger(i);
        } catch (Resources.NotFoundException unused) {
            return i2;
        }
    }

    private boolean getEnableBpfOffload(Resources resources) {
        return getDeviceConfigBoolean("override_tether_enable_bpf_offload", getResourceBoolean(resources, R.bool.config_tether_enable_bpf_offload, true));
    }

    private boolean getEnableLegacyDhcpServer(Resources resources) {
        return getResourceBoolean(resources, R.bool.config_tether_enable_legacy_dhcp_server, false) || getDeviceConfigBoolean("tether_enable_legacy_dhcp_server", false);
    }

    private boolean getDeviceConfigBoolean(String str, boolean z) {
        String deviceConfigProperty = getDeviceConfigProperty(str);
        return deviceConfigProperty != null ? Boolean.parseBoolean(deviceConfigProperty) : z;
    }

    protected String getDeviceConfigProperty(String str) {
        return DeviceConfig.getProperty("connectivity", str);
    }

    private Resources getResources(Context context, int i) {
        if (i != -1) {
            return getResourcesForSubIdWrapper(context, i);
        }
        return context.getResources();
    }

    protected Resources getResourcesForSubIdWrapper(Context context, int i) {
        return SubscriptionManager.getResourcesForSubId(context, i);
    }

    private static String[] copy(String[] strArr) {
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    private static void prependIfNotPresent(ArrayList<Integer> arrayList, int i) {
        if (arrayList.contains(Integer.valueOf(i))) {
            return;
        }
        arrayList.add(0, Integer.valueOf(i));
    }

    private static void appendIfNotPresent(ArrayList<Integer> arrayList, int i) {
        if (arrayList.contains(Integer.valueOf(i))) {
            return;
        }
        arrayList.add(Integer.valueOf(i));
    }

    private static boolean containsOneOf(ArrayList<Integer> arrayList, Integer... numArr) {
        for (Integer num : numArr) {
            if (arrayList.contains(num)) {
                return true;
            }
        }
        return false;
    }

    private static int[] toIntArray(Collection<Integer> collection) {
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    public TetheringConfigurationParcel toStableParcelable() {
        TetheringConfigurationParcel tetheringConfigurationParcel = new TetheringConfigurationParcel();
        tetheringConfigurationParcel.subId = this.activeDataSubId;
        tetheringConfigurationParcel.tetherableUsbRegexs = this.tetherableUsbRegexs;
        tetheringConfigurationParcel.tetherableWifiRegexs = this.tetherableWifiRegexs;
        tetheringConfigurationParcel.tetherableBluetoothRegexs = this.tetherableBluetoothRegexs;
        tetheringConfigurationParcel.isDunRequired = this.isDunRequired;
        tetheringConfigurationParcel.chooseUpstreamAutomatically = this.chooseUpstreamAutomatically;
        tetheringConfigurationParcel.preferredUpstreamIfaceTypes = toIntArray(this.preferredUpstreamIfaceTypes);
        tetheringConfigurationParcel.legacyDhcpRanges = this.legacyDhcpRanges;
        tetheringConfigurationParcel.defaultIPv4DNS = this.defaultIPv4DNS;
        tetheringConfigurationParcel.enableLegacyDhcpServer = this.enableLegacyDhcpServer;
        tetheringConfigurationParcel.provisioningApp = this.provisioningApp;
        tetheringConfigurationParcel.provisioningAppNoUi = this.provisioningAppNoUi;
        tetheringConfigurationParcel.provisioningCheckPeriod = this.provisioningCheckPeriod;
        return tetheringConfigurationParcel;
    }
}
