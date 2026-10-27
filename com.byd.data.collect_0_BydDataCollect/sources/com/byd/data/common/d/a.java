package com.byd.data.common.d;

import java.io.File;

/* JADX INFO: compiled from: DataPickConstants.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3302a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3303b;

    /* JADX INFO: renamed from: com.byd.data.common.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum EnumC0065a {
        REAL_TIME_DATA((byte) 2),
        GB_EXTRA_FILE_DATA((byte) 112);

        private final byte value;

        EnumC0065a(byte b2) {
            this.value = b2;
        }

        public byte a() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum b {
        INVALID,
        FRONT_DRIVER,
        DUAL_DRIVER,
        REAR_DRIVER,
        FOUR_DRIVER,
        FRONT_DRIVER_DUAL_MOTOR
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum c {
        FRONT,
        REAR,
        DUAL
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum d {
        NOT_ENCRYPT,
        AES_128,
        RSA_ICC_ID,
        RSS_IMEI,
        SN
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum e {
        VEHICLE,
        DRIVER,
        ENGINE,
        EXTREME,
        ALARM
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum f {
        INVALID,
        SECURITY_CERTIFICATE,
        SECURITY_VERITY,
        GB_FILE_LIST,
        GB_ALL_FILE_DATA,
        GB_SPECIAL_FILE_DATA,
        GB_EXTRA_FILE_LIST,
        GB_EXTRA_ALL_FILE_DATA,
        GB_EXTRA_SPECIAL_FILE_DATA
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum g {
        SUCCESS,
        FAILED,
        DESTROY,
        FILE_NOT_EXIST,
        IO_EXCEPTION
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum h {
        SUCCESS((byte) 1),
        FAILED((byte) 2),
        SECURITY_VERITY_FAULT((byte) -6),
        ORDER((byte) -2);

        private final byte value;

        h(byte b2) {
            this.value = b2;
        }

        public byte a() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: DataPickConstants.java */
    public enum i {
        NOT_ENCRYPT((byte) 1),
        RSA_ENCRYPT((byte) 2),
        AES_128((byte) 3),
        GZIP((byte) -3);

        private final byte value;

        i(byte b2) {
            this.value = b2;
        }

        public byte a() {
            return this.value;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("app_cloud");
        String str = File.separator;
        sb.append(str);
        sb.append("period");
        f3302a = sb.toString();
        f3303b = "app_cloud" + str + "event";
    }
}
