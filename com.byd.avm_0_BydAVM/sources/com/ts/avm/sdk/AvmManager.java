package com.ts.avm.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class AvmManager {

    public enum Back implements d {
        DEFAULT(1);

        private final int num;

        Back(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum Calibration implements d {
        START(0),
        STOP(1);

        int num;

        Calibration(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum Default implements d {
        NONE
    }

    public enum Kill implements d {
        DEFAULT(100);

        private final int num;

        Kill(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum Language implements d {
        CHINESE(0),
        ENGLISH(1),
        OTHER_1(2),
        OTHER_2(3),
        OTHER_3(4),
        OTHER_4(5),
        OTHER_5(6),
        OTHER_6(7),
        OTHER_7(8),
        OTHER_8(9),
        OTHER_9(10),
        OTHER_10(11),
        OTHER_11(12),
        OTHER_12(13),
        OTHER_13(14),
        OTHER_14(15),
        OTHER_15(16),
        OTHER_16(17),
        OTHER_17(18),
        OTHER_18(19),
        OTHER_19(20);

        int num;

        Language(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum Orientation implements d {
        HORIZONTAL(2),
        VERTICAL(3);

        int num;

        Orientation(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum State implements d {
        START(5),
        STOP(4);

        int num;

        State(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum Theme implements d {
        LIGHT(0),
        DARK(1);

        int num;

        Theme(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum ViewMode implements d {
        NONE(0),
        VIEW_2D_FRONT(1),
        VIEW_2D_REAR(2),
        VIEW_2D_LEFT(3),
        VIEW_2D_RIGHT(4),
        VIEW_2D_WIDE_FRONT(5),
        VIEW_2D_WIDE_REAR(6),
        VIEW_2D_MULTI_FRONT(7),
        VIEW_2D_MULTI_REAR(8),
        VIEW_2D_MULTI_VIEW(9),
        VIEW_3D_FRONT(10),
        VIEW_3D_REAR(11),
        VIEW_3D_LEFT(12),
        VIEW_3D_RIGHT(13),
        VIEW_3D_FOREHOLD(14),
        RADAR_FLOATING(15),
        TRACTION_REAR(16),
        TRACTION_WIDE_REAR(17);

        private final int num;

        ViewMode(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public enum VoiceEvent implements d {
        START_AVM(0),
        STOP_AVM(1),
        MODE_3D(2),
        MODE_2D(3),
        TRANSPORT_START(4),
        TRANSPOR_STOP(5),
        LEFT_MODE(6),
        RIGHT_MODE(7),
        FRONT_MODE(8),
        REAR_MODE(9),
        FRONT_WIDE_MODE(10),
        REAR_WIDE_MODE(11),
        MULTI_MODE(12),
        MULTI_FRONT_MODE(13),
        MULTI_REAR_MODE(14),
        STOP_RADAR(15),
        START_FOREHOLD(16),
        STOP_FOREHOLD(17);

        int num;

        VoiceEvent(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public interface a {
        void a(int i);
    }

    public interface b {
        void a(int i, String str);
    }

    public interface c {
        void a(int i);
    }

    public interface d {
    }

    public static AvmManager c() {
        return com.ts.avm.sdk.b.a.c();
    }

    public abstract void a(com.ts.avm.sdk.a aVar);

    public abstract int b();

    public abstract void d(Context context, com.ts.avm.sdk.a aVar);

    public abstract int e(a aVar);

    public abstract int f(b bVar);

    public abstract int g();

    public abstract int h(a aVar);

    public abstract int i(b bVar);

    public abstract int j(VoiceEvent voiceEvent);
}
