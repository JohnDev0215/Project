package com.amazon.avod.battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.EnumSet;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BatteryInfo {
    private final Health mHealth;
    private final float mLevel;
    private final PowerSource mPowerSoruce;
    private final Status mStatus;
    private final int mTemperature;
    private final int mVoltage;

    public enum PowerSource {
        NOT_PLUGGED(0),
        AC(1),
        USB(2),
        WIRELESS(4);

        private static final Map<Integer, PowerSource> mValueMap = Maps.newHashMap();
        private final int mValue;

        static {
            for (PowerSource powerSource : EnumSet.allOf(PowerSource.class)) {
                mValueMap.put(Integer.valueOf(powerSource.mValue), powerSource);
            }
        }

        PowerSource(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static PowerSource fromIntValue(int i) {
            Map<Integer, PowerSource> map = mValueMap;
            if (map.containsKey(Integer.valueOf(i))) {
                return map.get(Integer.valueOf(i));
            }
            DLog.devf("Error value %s does not correspond to a known enum value", Integer.valueOf(i));
            return NOT_PLUGGED;
        }
    }

    public enum Status {
        UNKNOWN(1),
        CHARGING(2),
        DISCHARGING(3),
        FULL(5),
        NOT_CHARGING(4);

        private static final Map<Integer, Status> mValueMap = Maps.newHashMap();
        private final int mValue;

        static {
            for (Status status : EnumSet.allOf(Status.class)) {
                mValueMap.put(Integer.valueOf(status.mValue), status);
            }
        }

        Status(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static Status fromIntValue(int i) {
            Map<Integer, Status> map = mValueMap;
            if (map.containsKey(Integer.valueOf(i))) {
                return map.get(Integer.valueOf(i));
            }
            DLog.devf("Error value %s does not correspond to a known enum value", Integer.valueOf(i));
            return UNKNOWN;
        }
    }

    public enum Health {
        COLD(7),
        DEAD(4),
        GOOD(2),
        OVERHEAT(3),
        OVER_VOLTAGE(5),
        UNKNOWN(1),
        UNSPECIFIED_FAILURE(6);

        private static final Map<Integer, Health> mValueMap = Maps.newHashMap();
        private final int mValue;

        static {
            for (Health health : EnumSet.allOf(Health.class)) {
                mValueMap.put(Integer.valueOf(health.mValue), health);
            }
        }

        Health(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static Health fromIntValue(int i) {
            Map<Integer, Health> map = mValueMap;
            if (map.containsKey(Integer.valueOf(i))) {
                return map.get(Integer.valueOf(i));
            }
            DLog.devf("Error value %s does not correspond to a known enum value", Integer.valueOf(i));
            return UNSPECIFIED_FAILURE;
        }
    }

    @Nonnull
    public static BatteryInfo getBatteryInfo(@Nonnull Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return new BatteryInfo(Status.UNKNOWN, Health.UNKNOWN, PowerSource.NOT_PLUGGED, 0.0f, 0, 0);
        }
        int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        BatteryInfo batteryInfo = new BatteryInfo(Status.fromIntValue(intentRegisterReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, Status.UNKNOWN.getValue())), Health.fromIntValue(intentRegisterReceiver.getIntExtra("health", Health.UNSPECIFIED_FAILURE.getValue())), PowerSource.fromIntValue(intentRegisterReceiver.getIntExtra("plugged", PowerSource.NOT_PLUGGED.getValue())), (intExtra == -1 || intExtra2 == -1 || intExtra2 == 0) ? -1.0f : (intExtra / intExtra2) * 100.0f, intentRegisterReceiver.getIntExtra("temperature", 0), intentRegisterReceiver.getIntExtra("voltage", 0));
        DLog.logf("Battery Info: " + batteryInfo);
        return batteryInfo;
    }

    BatteryInfo(@Nonnull Status status, @Nonnull Health health, @Nonnull PowerSource powerSource, float f, int i, int i2) {
        this.mStatus = (Status) Preconditions.checkNotNull(status, NotificationCompat.CATEGORY_STATUS);
        this.mHealth = (Health) Preconditions.checkNotNull(health, "health");
        this.mPowerSoruce = (PowerSource) Preconditions.checkNotNull(powerSource, "powerSource");
        this.mLevel = (f < 0.0f || f > 100.0f) ? 0.0f : f;
        this.mTemperature = Math.max(i, 0);
        this.mVoltage = Math.max(i2, 0);
    }

    public float getLevel() {
        return this.mLevel;
    }

    public int getTemperature() {
        return this.mTemperature;
    }

    public int getVoltage() {
        return this.mVoltage;
    }

    @Nonnull
    public Health getHealth() {
        return this.mHealth;
    }

    @Nonnull
    public PowerSource getPowerSource() {
        return this.mPowerSoruce;
    }

    @Nonnull
    public Status getStatus() {
        return this.mStatus;
    }

    @Nonnull
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Status", this.mStatus).add("Health", this.mHealth).add("Power source", this.mPowerSoruce).add("Level", this.mLevel).add("Temperature", this.mTemperature).add("Voltage", this.mVoltage).toString();
    }
}
