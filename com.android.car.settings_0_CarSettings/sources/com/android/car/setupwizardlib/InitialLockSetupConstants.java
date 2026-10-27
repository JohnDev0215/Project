package com.android.car.setupwizardlib;

/* JADX INFO: loaded from: classes5.dex */
public interface InitialLockSetupConstants {
    public static final int LIBRARY_VERSION = 1;

    public @interface LockTypes {
        public static final int PASSWORD = 0;
        public static final int PATTERN = 2;
        public static final int PIN = 1;
    }

    public @interface SetLockCodes {
        public static final int FAIL_LOCK_EXISTS = -1;
        public static final int FAIL_LOCK_GENERIC = -3;
        public static final int FAIL_LOCK_INVALID = -2;
        public static final int SUCCESS = 1;
    }

    public @interface ValidateLockFlags {
        public static final int INVALID_BAD_SYMBOLS = 2;
        public static final int INVALID_GENERIC = 8;
        public static final int INVALID_LACKS_COMPLEXITY = 4;
        public static final int INVALID_LENGTH = 1;
    }
}
