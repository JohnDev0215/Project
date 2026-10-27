package com.android.internal.car;

/* JADX INFO: loaded from: classes3.dex */
final class ExternalConstants {
    private ExternalConstants() {
        throw new UnsupportedOperationException("contains only static constants");
    }

    static final class ICarConstants {
        static final String CAR_SERVICE_INTERFACE = "android.car.ICar";
        static final int ICAR_CALL_FIRST_USER_UNLOCKED = 2;
        static final int ICAR_CALL_GET_INITIAL_USER_INFO = 3;
        static final int ICAR_CALL_ON_USER_LIFECYCLE = 1;
        static final int ICAR_CALL_SET_CAR_SERVICE_HELPER = 0;
        static final int ICAR_CALL_SET_INITIAL_USER = 4;

        private ICarConstants() {
            throw new UnsupportedOperationException("contains only static constants");
        }
    }

    static final class CarUserManagerConstants {
        static final int USER_LIFECYCLE_EVENT_TYPE_STARTING = 1;
        static final int USER_LIFECYCLE_EVENT_TYPE_STOPPED = 6;
        static final int USER_LIFECYCLE_EVENT_TYPE_STOPPING = 5;
        static final int USER_LIFECYCLE_EVENT_TYPE_SWITCHING = 2;
        static final int USER_LIFECYCLE_EVENT_TYPE_UNLOCKED = 4;
        static final int USER_LIFECYCLE_EVENT_TYPE_UNLOCKING = 3;

        private CarUserManagerConstants() {
            throw new UnsupportedOperationException("contains only static constants");
        }
    }
}
