package com.android.settingslib.fuelgauge;

import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.android.settingslib.R;

/* JADX INFO: loaded from: classes6.dex */
public class BatteryStatus {
    public static final int CHARGING_FAST = 2;
    public static final int CHARGING_REGULAR = 1;
    public static final int CHARGING_SLOWLY = 0;
    public static final int CHARGING_UNKNOWN = -1;
    private static final int DEFAULT_CHARGING_VOLTAGE_MICRO_VOLT = 5000000;
    private static final int LOW_BATTERY_THRESHOLD = 20;
    public final int health;
    public final int level;
    public final int maxChargingWattage;
    public final int plugged;
    public final boolean present;
    public final int status;

    public BatteryStatus(int status, int level, int plugged, int health, int maxChargingWattage, boolean present) {
        this.status = status;
        this.level = level;
        this.plugged = plugged;
        this.health = health;
        this.maxChargingWattage = maxChargingWattage;
        this.present = present;
    }

    public BatteryStatus(Intent batteryChangedIntent) {
        this.status = batteryChangedIntent.getIntExtra(NotificationCompat.CATEGORY_STATUS, 1);
        this.plugged = batteryChangedIntent.getIntExtra("plugged", 0);
        this.level = batteryChangedIntent.getIntExtra("level", 0);
        this.health = batteryChangedIntent.getIntExtra("health", 1);
        this.present = batteryChangedIntent.getBooleanExtra("present", true);
        int maxChargingMicroAmp = batteryChangedIntent.getIntExtra("max_charging_current", -1);
        int maxChargingMicroVolt = batteryChangedIntent.getIntExtra("max_charging_voltage", -1);
        maxChargingMicroVolt = maxChargingMicroVolt <= 0 ? DEFAULT_CHARGING_VOLTAGE_MICRO_VOLT : maxChargingMicroVolt;
        if (maxChargingMicroAmp > 0) {
            this.maxChargingWattage = (maxChargingMicroAmp / 1000) * (maxChargingMicroVolt / 1000);
        } else {
            this.maxChargingWattage = -1;
        }
    }

    public boolean isPluggedIn() {
        int i = this.plugged;
        return i == 1 || i == 2 || i == 4;
    }

    public boolean isPluggedInWired() {
        int i = this.plugged;
        return i == 1 || i == 2;
    }

    public boolean isCharged() {
        return this.status == 5 || this.level >= 100;
    }

    public boolean isBatteryLow() {
        return this.level < 20;
    }

    public boolean isOverheated() {
        return this.health == 3;
    }

    public final int getChargingSpeed(Context context) {
        int slowThreshold = context.getResources().getInteger(R.integer.config_chargingSlowlyThreshold);
        int fastThreshold = context.getResources().getInteger(R.integer.config_chargingFastThreshold);
        int i = this.maxChargingWattage;
        if (i <= 0) {
            return -1;
        }
        if (i < slowThreshold) {
            return 0;
        }
        return i > fastThreshold ? 2 : 1;
    }

    public String toString() {
        return "BatteryStatus{status=" + this.status + ",level=" + this.level + ",plugged=" + this.plugged + ",health=" + this.health + ",maxChargingWattage=" + this.maxChargingWattage + "}";
    }
}
