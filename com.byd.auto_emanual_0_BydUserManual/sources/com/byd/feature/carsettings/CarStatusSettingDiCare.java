package com.byd.feature.carsettings;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class CarStatusSettingDiCare {
    public static final String CLEAR_DICARE_RECORD = "clearDiCareRecord";
    public static final String DELETE_DICARE_RECORD = "deleteDiCareRecord";
    public static final String GET_DICARE_RECORD = "getDiCareRecord";
    public static final String INSERT_DICARE_RECORT = "insertDiCareRecorc";
    public static final String KEY = "CarStatusSettingDiCare";

    public static class DiCareRecord implements Serializable {
        public static final String DICARE_HEV_MILEAGE = "hev_mileage";
        public static final String DICARE_MILEAGE_UNIT = "unit";
        public static final String DICARE_TIME = "time";
        public static final String DICARE_TOTAL_MILEAGE = "total_mileage";
        public static final String DICARE_TYPE = "type";
        private int hevMileage;
        private long time;
        private int totalMileage;
        private int type;
        private String unit;

        private DiCareRecord() {
        }

        public long getTime() {
            return this.time;
        }

        public int getType() {
            return this.type;
        }

        public int getTotalMileage() {
            return this.totalMileage;
        }

        public int getHevMileage() {
            return this.hevMileage;
        }

        public String getUnit() {
            return this.unit;
        }

        public static class Builder {
            private int hevMileage;
            private long time;
            private int totalMileage;
            private int type;
            private String unit;

            public Builder time(long j) {
                this.time = j;
                return this;
            }

            public Builder type(int i) {
                this.type = i;
                return this;
            }

            public Builder totalMileage(int i) {
                this.totalMileage = i;
                return this;
            }

            public Builder hevMileage(int i) {
                this.hevMileage = i;
                return this;
            }

            public Builder unit(String str) {
                this.unit = str;
                return this;
            }

            public DiCareRecord build() {
                DiCareRecord diCareRecord = new DiCareRecord();
                diCareRecord.time = this.time;
                diCareRecord.type = this.type;
                diCareRecord.totalMileage = this.totalMileage;
                diCareRecord.hevMileage = this.hevMileage;
                diCareRecord.unit = this.unit;
                return diCareRecord;
            }
        }
    }
}
