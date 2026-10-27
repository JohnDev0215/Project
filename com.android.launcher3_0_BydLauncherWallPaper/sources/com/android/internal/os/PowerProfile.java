package com.android.internal.os;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.proto.ProtoOutputStream;
import com.android.internal.R;
import com.android.internal.os.PowerProfileProto;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class PowerProfile {
    private static final String ATTR_NAME = "name";
    private static final String CPU_CLUSTER_POWER_COUNT = "cpu.cluster_power.cluster";
    private static final String CPU_CORE_POWER_PREFIX = "cpu.core_power.cluster";
    private static final String CPU_CORE_SPEED_PREFIX = "cpu.core_speeds.cluster";
    private static final String CPU_PER_CLUSTER_CORE_COUNT = "cpu.clusters.cores";
    public static final String POWER_AMBIENT_DISPLAY = "ambient.on";
    public static final String POWER_AUDIO = "audio";
    public static final String POWER_BATTERY_CAPACITY = "battery.capacity";

    @Deprecated
    public static final String POWER_BLUETOOTH_ACTIVE = "bluetooth.active";

    @Deprecated
    public static final String POWER_BLUETOOTH_AT_CMD = "bluetooth.at";
    public static final String POWER_BLUETOOTH_CONTROLLER_IDLE = "bluetooth.controller.idle";
    public static final String POWER_BLUETOOTH_CONTROLLER_OPERATING_VOLTAGE = "bluetooth.controller.voltage";
    public static final String POWER_BLUETOOTH_CONTROLLER_RX = "bluetooth.controller.rx";
    public static final String POWER_BLUETOOTH_CONTROLLER_TX = "bluetooth.controller.tx";

    @Deprecated
    public static final String POWER_BLUETOOTH_ON = "bluetooth.on";
    public static final String POWER_CAMERA = "camera.avg";
    public static final String POWER_CPU_ACTIVE = "cpu.active";
    public static final String POWER_CPU_IDLE = "cpu.idle";
    public static final String POWER_CPU_SUSPEND = "cpu.suspend";
    public static final String POWER_FLASHLIGHT = "camera.flashlight";
    public static final String POWER_GPS_ON = "gps.on";
    public static final String POWER_GPS_OPERATING_VOLTAGE = "gps.voltage";
    public static final String POWER_GPS_SIGNAL_QUALITY_BASED = "gps.signalqualitybased";
    public static final String POWER_MEMORY = "memory.bandwidths";
    public static final String POWER_MODEM_CONTROLLER_IDLE = "modem.controller.idle";
    public static final String POWER_MODEM_CONTROLLER_OPERATING_VOLTAGE = "modem.controller.voltage";
    public static final String POWER_MODEM_CONTROLLER_RX = "modem.controller.rx";
    public static final String POWER_MODEM_CONTROLLER_SLEEP = "modem.controller.sleep";
    public static final String POWER_MODEM_CONTROLLER_TX = "modem.controller.tx";
    public static final String POWER_RADIO_ACTIVE = "radio.active";
    public static final String POWER_RADIO_ON = "radio.on";
    public static final String POWER_RADIO_SCANNING = "radio.scanning";
    public static final String POWER_SCREEN_FULL = "screen.full";
    public static final String POWER_SCREEN_ON = "screen.on";
    public static final String POWER_VIDEO = "video";
    public static final String POWER_WIFI_ACTIVE = "wifi.active";
    public static final String POWER_WIFI_BATCHED_SCAN = "wifi.batchedscan";
    public static final String POWER_WIFI_CONTROLLER_IDLE = "wifi.controller.idle";
    public static final String POWER_WIFI_CONTROLLER_OPERATING_VOLTAGE = "wifi.controller.voltage";
    public static final String POWER_WIFI_CONTROLLER_RX = "wifi.controller.rx";
    public static final String POWER_WIFI_CONTROLLER_TX = "wifi.controller.tx";
    public static final String POWER_WIFI_CONTROLLER_TX_LEVELS = "wifi.controller.tx_levels";
    public static final String POWER_WIFI_ON = "wifi.on";
    public static final String POWER_WIFI_SCAN = "wifi.scan";
    private static final String TAG_ARRAY = "array";
    private static final String TAG_ARRAYITEM = "value";
    private static final String TAG_DEVICE = "device";
    private static final String TAG_ITEM = "item";
    private CpuClusterKey[] mCpuClusters;
    static final HashMap<String, Double> sPowerItemMap = new HashMap<>();
    static final HashMap<String, Double[]> sPowerArrayMap = new HashMap<>();
    private static final Object sLock = new Object();

    public PowerProfile(Context context) {
        this(context, false);
    }

    public PowerProfile(Context context, boolean z) {
        synchronized (sLock) {
            if (sPowerItemMap.size() == 0 && sPowerArrayMap.size() == 0) {
                readPowerValuesFromXml(context, z);
            }
            initCpuClusters();
        }
    }

    private void readPowerValuesFromXml(Context context, boolean z) {
        int integer;
        int i = z ? R.xml.power_profile_test : R.xml.power_profile;
        Resources resources = context.getResources();
        XmlResourceParser xml = resources.getXml(i);
        ArrayList arrayList = new ArrayList();
        try {
            try {
                XmlUtils.beginDocument(xml, "device");
                boolean z2 = false;
                String attributeValue = null;
                while (true) {
                    XmlUtils.nextElement(xml);
                    String name = xml.getName();
                    double dDoubleValue = 0.0d;
                    if (name == null) {
                        break;
                    }
                    if (z2 && !name.equals("value")) {
                        sPowerArrayMap.put(attributeValue, (Double[]) arrayList.toArray(new Double[arrayList.size()]));
                        z2 = false;
                    }
                    if (name.equals(TAG_ARRAY)) {
                        arrayList.clear();
                        attributeValue = xml.getAttributeValue(null, "name");
                        z2 = true;
                    } else if (name.equals("item") || name.equals("value")) {
                        String attributeValue2 = !z2 ? xml.getAttributeValue(null, "name") : null;
                        if (xml.next() == 4) {
                            try {
                                dDoubleValue = Double.valueOf(xml.getText()).doubleValue();
                            } catch (NumberFormatException unused) {
                            }
                            if (name.equals("item")) {
                                sPowerItemMap.put(attributeValue2, Double.valueOf(dDoubleValue));
                            } else if (z2) {
                                arrayList.add(Double.valueOf(dDoubleValue));
                            }
                        }
                    }
                }
                if (z2) {
                    sPowerArrayMap.put(attributeValue, (Double[]) arrayList.toArray(new Double[arrayList.size()]));
                }
                xml.close();
                int[] iArr = {R.integer.config_bluetooth_idle_cur_ma, R.integer.config_bluetooth_rx_cur_ma, R.integer.config_bluetooth_tx_cur_ma, R.integer.config_bluetooth_operating_voltage_mv};
                String[] strArr = {POWER_BLUETOOTH_CONTROLLER_IDLE, POWER_BLUETOOTH_CONTROLLER_RX, POWER_BLUETOOTH_CONTROLLER_TX, POWER_BLUETOOTH_CONTROLLER_OPERATING_VOLTAGE};
                for (int i2 = 0; i2 < 4; i2++) {
                    String str = strArr[i2];
                    HashMap<String, Double> map = sPowerItemMap;
                    if ((!map.containsKey(str) || map.get(str).doubleValue() <= 0.0d) && (integer = resources.getInteger(iArr[i2])) > 0) {
                        map.put(str, Double.valueOf(integer));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (XmlPullParserException e2) {
                throw new RuntimeException(e2);
            }
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    private void initCpuClusters() {
        HashMap<String, Double[]> map = sPowerArrayMap;
        if (map.containsKey(CPU_PER_CLUSTER_CORE_COUNT)) {
            Double[] dArr = map.get(CPU_PER_CLUSTER_CORE_COUNT);
            this.mCpuClusters = new CpuClusterKey[dArr.length];
            for (int i = 0; i < dArr.length; i++) {
                this.mCpuClusters[i] = new CpuClusterKey(CPU_CORE_SPEED_PREFIX + i, CPU_CLUSTER_POWER_COUNT + i, CPU_CORE_POWER_PREFIX + i, (int) Math.round(dArr[i].doubleValue()));
            }
            return;
        }
        this.mCpuClusters = new CpuClusterKey[1];
        HashMap<String, Double> map2 = sPowerItemMap;
        this.mCpuClusters[0] = new CpuClusterKey("cpu.core_speeds.cluster0", "cpu.cluster_power.cluster0", "cpu.core_power.cluster0", map2.containsKey(CPU_PER_CLUSTER_CORE_COUNT) ? (int) Math.round(map2.get(CPU_PER_CLUSTER_CORE_COUNT).doubleValue()) : 1);
    }

    public static class CpuClusterKey {
        private final String clusterPowerKey;
        private final String corePowerKey;
        private final String freqKey;
        private final int numCpus;

        private CpuClusterKey(String str, String str2, String str3, int i) {
            this.freqKey = str;
            this.clusterPowerKey = str2;
            this.corePowerKey = str3;
            this.numCpus = i;
        }
    }

    public int getNumCpuClusters() {
        return this.mCpuClusters.length;
    }

    public int getNumCoresInCpuCluster(int i) {
        if (i < 0) {
            return 0;
        }
        CpuClusterKey[] cpuClusterKeyArr = this.mCpuClusters;
        if (i >= cpuClusterKeyArr.length) {
            return 0;
        }
        return cpuClusterKeyArr[i].numCpus;
    }

    public int getNumSpeedStepsInCpuCluster(int i) {
        if (i < 0) {
            return 0;
        }
        CpuClusterKey[] cpuClusterKeyArr = this.mCpuClusters;
        if (i >= cpuClusterKeyArr.length) {
            return 0;
        }
        HashMap<String, Double[]> map = sPowerArrayMap;
        if (map.containsKey(cpuClusterKeyArr[i].freqKey)) {
            return map.get(this.mCpuClusters[i].freqKey).length;
        }
        return 1;
    }

    public double getAveragePowerForCpuCluster(int i) {
        if (i < 0) {
            return 0.0d;
        }
        CpuClusterKey[] cpuClusterKeyArr = this.mCpuClusters;
        if (i < cpuClusterKeyArr.length) {
            return getAveragePower(cpuClusterKeyArr[i].clusterPowerKey);
        }
        return 0.0d;
    }

    public double getAveragePowerForCpuCore(int i, int i2) {
        if (i < 0) {
            return 0.0d;
        }
        CpuClusterKey[] cpuClusterKeyArr = this.mCpuClusters;
        if (i < cpuClusterKeyArr.length) {
            return getAveragePower(cpuClusterKeyArr[i].corePowerKey, i2);
        }
        return 0.0d;
    }

    public int getNumElements(String str) {
        if (sPowerItemMap.containsKey(str)) {
            return 1;
        }
        HashMap<String, Double[]> map = sPowerArrayMap;
        if (map.containsKey(str)) {
            return map.get(str).length;
        }
        return 0;
    }

    public double getAveragePowerOrDefault(String str, double d) {
        HashMap<String, Double> map = sPowerItemMap;
        if (map.containsKey(str)) {
            return map.get(str).doubleValue();
        }
        HashMap<String, Double[]> map2 = sPowerArrayMap;
        return map2.containsKey(str) ? map2.get(str)[0].doubleValue() : d;
    }

    public double getAveragePower(String str) {
        return getAveragePowerOrDefault(str, 0.0d);
    }

    public double getAveragePower(String str, int i) {
        HashMap<String, Double> map = sPowerItemMap;
        if (map.containsKey(str)) {
            return map.get(str).doubleValue();
        }
        HashMap<String, Double[]> map2 = sPowerArrayMap;
        if (map2.containsKey(str)) {
            Double[] dArr = map2.get(str);
            if (dArr.length > i && i >= 0) {
                return dArr[i].doubleValue();
            }
            if (i >= 0 && dArr.length != 0) {
                return dArr[dArr.length - 1].doubleValue();
            }
        }
        return 0.0d;
    }

    public double getBatteryCapacity() {
        return getAveragePower(POWER_BATTERY_CAPACITY);
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream) {
        writePowerConstantToProto(protoOutputStream, POWER_CPU_SUSPEND, 1103806595073L);
        writePowerConstantToProto(protoOutputStream, POWER_CPU_IDLE, 1103806595074L);
        writePowerConstantToProto(protoOutputStream, POWER_CPU_ACTIVE, 1103806595075L);
        for (int i = 0; i < this.mCpuClusters.length; i++) {
            long jStart = protoOutputStream.start(2246267895848L);
            protoOutputStream.write(1120986464257L, i);
            protoOutputStream.write(1103806595074L, sPowerItemMap.get(this.mCpuClusters[i].clusterPowerKey).doubleValue());
            protoOutputStream.write(1120986464259L, this.mCpuClusters[i].numCpus);
            for (Double d : sPowerArrayMap.get(this.mCpuClusters[i].freqKey)) {
                protoOutputStream.write(PowerProfileProto.CpuCluster.SPEED, d.doubleValue());
            }
            for (Double d2 : sPowerArrayMap.get(this.mCpuClusters[i].corePowerKey)) {
                protoOutputStream.write(PowerProfileProto.CpuCluster.CORE_POWER, d2.doubleValue());
            }
            protoOutputStream.end(jStart);
        }
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_SCAN, 1103806595076L);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_ON, 1103806595077L);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_ACTIVE, 1103806595078L);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_CONTROLLER_IDLE, PowerProfileProto.WIFI_CONTROLLER_IDLE);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_CONTROLLER_RX, 1103806595080L);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_CONTROLLER_TX, 1103806595081L);
        writePowerConstantArrayToProto(protoOutputStream, POWER_WIFI_CONTROLLER_TX_LEVELS, PowerProfileProto.WIFI_CONTROLLER_TX_LEVELS);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_CONTROLLER_OPERATING_VOLTAGE, PowerProfileProto.WIFI_CONTROLLER_OPERATING_VOLTAGE);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_CONTROLLER_IDLE, PowerProfileProto.BLUETOOTH_CONTROLLER_IDLE);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_CONTROLLER_RX, PowerProfileProto.BLUETOOTH_CONTROLLER_RX);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_CONTROLLER_TX, PowerProfileProto.BLUETOOTH_CONTROLLER_TX);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_CONTROLLER_OPERATING_VOLTAGE, PowerProfileProto.BLUETOOTH_CONTROLLER_OPERATING_VOLTAGE);
        writePowerConstantToProto(protoOutputStream, POWER_MODEM_CONTROLLER_SLEEP, PowerProfileProto.MODEM_CONTROLLER_SLEEP);
        writePowerConstantToProto(protoOutputStream, POWER_MODEM_CONTROLLER_IDLE, PowerProfileProto.MODEM_CONTROLLER_IDLE);
        writePowerConstantToProto(protoOutputStream, POWER_MODEM_CONTROLLER_RX, 1103806595090L);
        writePowerConstantArrayToProto(protoOutputStream, POWER_MODEM_CONTROLLER_TX, PowerProfileProto.MODEM_CONTROLLER_TX);
        writePowerConstantToProto(protoOutputStream, POWER_MODEM_CONTROLLER_OPERATING_VOLTAGE, 1103806595092L);
        writePowerConstantToProto(protoOutputStream, POWER_GPS_ON, 1103806595093L);
        writePowerConstantArrayToProto(protoOutputStream, POWER_GPS_SIGNAL_QUALITY_BASED, PowerProfileProto.GPS_SIGNAL_QUALITY_BASED);
        writePowerConstantToProto(protoOutputStream, POWER_GPS_OPERATING_VOLTAGE, 1103806595095L);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_ON, 1103806595096L);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_ACTIVE, 1103806595097L);
        writePowerConstantToProto(protoOutputStream, POWER_BLUETOOTH_AT_CMD, 1103806595098L);
        writePowerConstantToProto(protoOutputStream, POWER_AMBIENT_DISPLAY, PowerProfileProto.AMBIENT_DISPLAY);
        writePowerConstantToProto(protoOutputStream, POWER_SCREEN_ON, PowerProfileProto.SCREEN_ON);
        writePowerConstantToProto(protoOutputStream, POWER_RADIO_ON, PowerProfileProto.RADIO_ON);
        writePowerConstantToProto(protoOutputStream, POWER_RADIO_SCANNING, PowerProfileProto.RADIO_SCANNING);
        writePowerConstantToProto(protoOutputStream, POWER_RADIO_ACTIVE, PowerProfileProto.RADIO_ACTIVE);
        writePowerConstantToProto(protoOutputStream, POWER_SCREEN_FULL, PowerProfileProto.SCREEN_FULL);
        writePowerConstantToProto(protoOutputStream, "audio", PowerProfileProto.AUDIO);
        writePowerConstantToProto(protoOutputStream, "video", PowerProfileProto.VIDEO);
        writePowerConstantToProto(protoOutputStream, POWER_FLASHLIGHT, PowerProfileProto.FLASHLIGHT);
        writePowerConstantToProto(protoOutputStream, POWER_MEMORY, PowerProfileProto.MEMORY);
        writePowerConstantToProto(protoOutputStream, POWER_CAMERA, PowerProfileProto.CAMERA);
        writePowerConstantToProto(protoOutputStream, POWER_WIFI_BATCHED_SCAN, PowerProfileProto.WIFI_BATCHED_SCAN);
        writePowerConstantToProto(protoOutputStream, POWER_BATTERY_CAPACITY, PowerProfileProto.BATTERY_CAPACITY);
    }

    private void writePowerConstantToProto(ProtoOutputStream protoOutputStream, String str, long j) {
        HashMap<String, Double> map = sPowerItemMap;
        if (map.containsKey(str)) {
            protoOutputStream.write(j, map.get(str).doubleValue());
        }
    }

    private void writePowerConstantArrayToProto(ProtoOutputStream protoOutputStream, String str, long j) {
        HashMap<String, Double[]> map = sPowerArrayMap;
        if (map.containsKey(str)) {
            for (Double d : map.get(str)) {
                protoOutputStream.write(j, d.doubleValue());
            }
        }
    }
}
