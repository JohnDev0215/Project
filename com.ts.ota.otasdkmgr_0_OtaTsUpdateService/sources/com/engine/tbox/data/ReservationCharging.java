package com.engine.tbox.data;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class ReservationCharging {
    public static final byte DEFAULT_ELECTROVALENCE = -1;
    public static final byte GO_OUT_TYPE = 2;
    public static final byte MAX_HOUR = 23;
    public static final byte MAX_MIN = 59;
    public static final byte MIN_HOUR = 0;
    public static final byte MIN_MIN = 0;
    public static final int RC_LENGTH = 15;
    public static final byte RC_OUT_SWITCH_CLOSE = 2;
    public static final byte RC_OUT_SWITCH_OPEN = 1;
    public static final byte SMART_CHARGE_TYPE = 1;
    public static final int WEEK = 7;
    private byte planeSelect;
    private byte preferentialTariffEndHour;
    private byte preferentialTariffEndMin;
    private byte preferentialTariffStartHour;
    private byte preferentialTariffStartMin;
    private byte rsvEndHour;
    private byte rsvEndMin;
    private byte rsvOutHour;
    private byte rsvOutMin;
    private byte rsvOutPeriod;
    private byte rsvOutSwitch;
    private byte rsvPlanePeriod;
    private byte rsvStartHour;
    private byte rsvStartMin;
    private byte rsvplaneSwitch;
    private String timeZone;

    public static class RcTime {
        byte hour;
        byte min;

        public byte getHour() {
            return this.hour;
        }

        public boolean setHour(int hour) {
            if ((hour > 23 || hour < 0) && hour != -1) {
                return false;
            }
            this.hour = (byte) hour;
            return true;
        }

        public byte getMin() {
            return this.min;
        }

        public boolean setMin(int min) {
            if ((min > 59 || min < 0) && min != -1) {
                return false;
            }
            this.min = (byte) min;
            return true;
        }

        public boolean setHourAndMin(int hour, int min) {
            boolean result = true;
            if (!setHour(hour)) {
                result = false;
            }
            if (!setMin(min)) {
                return false;
            }
            return result;
        }
    }

    public byte getRsvPlanePeriod() {
        return this.rsvPlanePeriod;
    }

    public void setRsvPlanePeriod(byte rsvPlanePeriod) {
        this.rsvPlanePeriod = rsvPlanePeriod;
    }

    public byte getRsvPlaneSwitch() {
        return this.rsvplaneSwitch;
    }

    public void setRsvPlaneSwitch(byte rsvplaneSwitch) {
        this.rsvplaneSwitch = rsvplaneSwitch;
    }

    public byte getRsvOutPeriod() {
        return this.rsvOutPeriod;
    }

    public void setRsvOutPeriod(byte rsvOutPeriod) {
        this.rsvOutPeriod = rsvOutPeriod;
    }

    public byte getRsvOutSwitch() {
        return this.rsvOutSwitch;
    }

    public void setRsvOutSwitch(byte rsvOutSwitch) {
        this.rsvOutSwitch = rsvOutSwitch;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public byte getRsvStartHour() {
        return this.rsvStartHour;
    }

    public byte getRsvStartMin() {
        return this.rsvStartMin;
    }

    public byte getRsvEndHour() {
        return this.rsvEndHour;
    }

    public byte getRsvEndMin() {
        return this.rsvEndMin;
    }

    public byte getRsvOutHour() {
        return this.rsvOutHour;
    }

    public byte getRsvOutMin() {
        return this.rsvOutMin;
    }

    public byte getPreferentialTariffStartHour() {
        return this.preferentialTariffStartHour;
    }

    public byte getPreferentialTariffStartMin() {
        return this.preferentialTariffStartMin;
    }

    public byte getPreferentialTariffEndHour() {
        return this.preferentialTariffEndHour;
    }

    public byte getPreferentialTariffEndMin() {
        return this.preferentialTariffEndMin;
    }

    public byte getPlaneSelect() {
        return this.planeSelect;
    }

    public void setPlaneSelect(byte planeSelect) {
        this.planeSelect = planeSelect;
    }

    public boolean setRsvOutTime(RcTime outTime) {
        if (outTime != null) {
            this.rsvOutHour = outTime.getHour();
            this.rsvOutMin = outTime.getMin();
            return true;
        }
        return false;
    }

    public boolean setRsvTime(RcTime startTime, RcTime endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }
        this.rsvStartHour = startTime.getHour();
        this.rsvStartMin = startTime.getMin();
        this.rsvEndHour = endTime.getHour();
        this.rsvEndMin = endTime.getMin();
        return true;
    }

    public boolean setPreferentialTariffTimeSlot(RcTime startTime, RcTime endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }
        this.preferentialTariffStartHour = startTime.getHour();
        this.preferentialTariffStartMin = startTime.getMin();
        this.preferentialTariffEndHour = endTime.getHour();
        this.preferentialTariffEndMin = endTime.getMin();
        return true;
    }

    public void setNoPreferentialTariff() {
        this.preferentialTariffStartHour = (byte) -1;
        this.preferentialTariffStartMin = (byte) -1;
        this.preferentialTariffEndHour = (byte) -1;
        this.preferentialTariffEndMin = (byte) -1;
    }

    public static byte[] toByte(ReservationCharging rc) {
        if (rc == null || rc.getTimeZone() == null) {
            return null;
        }
        String timeZone = rc.getTimeZone();
        byte[] zone = timeZone.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(zone.length + 15);
        buffer.put(rc.rsvStartHour);
        buffer.put(rc.rsvStartMin);
        buffer.put(rc.rsvEndHour);
        buffer.put(rc.rsvEndMin);
        buffer.put(rc.rsvPlanePeriod);
        buffer.put(rc.rsvplaneSwitch);
        buffer.put(rc.rsvOutHour);
        buffer.put(rc.rsvOutMin);
        buffer.put(rc.rsvOutPeriod);
        buffer.put(rc.rsvOutSwitch);
        buffer.put(rc.preferentialTariffStartHour);
        buffer.put(rc.preferentialTariffStartMin);
        buffer.put(rc.preferentialTariffEndHour);
        buffer.put(rc.preferentialTariffEndMin);
        buffer.put(rc.planeSelect);
        buffer.put(zone);
        return buffer.array();
    }

    public static ReservationCharging bytesToReservationCharging(byte[] bytes) {
        if (bytes == null || bytes.length < 15) {
            return null;
        }
        ReservationCharging rc = new ReservationCharging();
        int pos = 0 + 1;
        rc.rsvStartHour = bytes[0];
        int pos2 = pos + 1;
        rc.rsvStartMin = bytes[pos];
        int pos3 = pos2 + 1;
        rc.rsvEndHour = bytes[pos2];
        int pos4 = pos3 + 1;
        rc.rsvEndMin = bytes[pos3];
        int pos5 = pos4 + 1;
        rc.rsvPlanePeriod = bytes[pos4];
        int pos6 = pos5 + 1;
        rc.rsvplaneSwitch = bytes[pos5];
        int pos7 = pos6 + 1;
        rc.rsvOutHour = bytes[pos6];
        int pos8 = pos7 + 1;
        rc.rsvOutMin = bytes[pos7];
        int pos9 = pos8 + 1;
        rc.rsvOutPeriod = bytes[pos8];
        int pos10 = pos9 + 1;
        rc.rsvOutSwitch = bytes[pos9];
        int pos11 = pos10 + 1;
        rc.preferentialTariffStartHour = bytes[pos10];
        int pos12 = pos11 + 1;
        rc.preferentialTariffStartMin = bytes[pos11];
        int pos13 = pos12 + 1;
        rc.preferentialTariffEndHour = bytes[pos12];
        int pos14 = pos13 + 1;
        rc.preferentialTariffEndMin = bytes[pos13];
        rc.planeSelect = bytes[pos14];
        rc.setTimeZone(new String(bytes, pos14 + 1, bytes.length - 15, StandardCharsets.UTF_8).trim());
        return rc;
    }

    public void addOutPeriod(int value) {
        this.rsvOutPeriod = (byte) addPeriod(this.rsvOutPeriod, value);
    }

    private int addPeriod(int origin, int bits) {
        if (bits < 7 && bits >= 1) {
            return origin & (1 << (bits - 1));
        }
        return origin;
    }

    public void addRsvPeriod(int value) {
        this.rsvPlanePeriod = (byte) addPeriod(this.rsvPlanePeriod, value);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReservationCharging{");
        builder.append("rsvStartHour=" + ((int) this.rsvStartHour));
        builder.append(", rsvStartMin=" + ((int) this.rsvStartMin));
        builder.append(", rsvEndHour=" + ((int) this.rsvEndHour));
        builder.append(", rsvEndMin=" + ((int) this.rsvEndMin));
        builder.append(", rsvPlanePeriod=" + ((int) this.rsvPlanePeriod));
        builder.append(", rsvplaneSwitch=" + ((int) this.rsvplaneSwitch));
        builder.append(", rsvOutHour=" + ((int) this.rsvOutHour));
        builder.append(", rsvOutMin=" + ((int) this.rsvOutMin));
        builder.append(", rsvOutPeriod=" + ((int) this.rsvOutPeriod));
        builder.append(", rsvOutSwitch=" + ((int) this.rsvOutSwitch));
        builder.append(", preferentialTariffStartHour=" + ((int) this.preferentialTariffStartHour));
        builder.append(", preferentialTariffStartMin=" + ((int) this.preferentialTariffStartMin));
        builder.append(", preferentialTariffEndHour=" + ((int) this.preferentialTariffEndHour));
        builder.append(", preferentialTariffEndMin=" + ((int) this.preferentialTariffEndMin));
        builder.append(", planeSelect=" + ((int) this.planeSelect));
        builder.append(", timeZone='" + this.timeZone + '\'');
        builder.append("}");
        return builder.toString();
    }
}
