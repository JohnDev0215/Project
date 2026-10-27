package com.ts.avm.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class AvmManager {

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Back {
        DEFAULT(1);

        private final int num;

        Back(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Calibration {
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

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Default {
        NONE
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Kill {
        DEFAULT(100);

        private final int num;

        Kill(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Language {
        CHINESE(0),
        ENGLISH(1),
        GERMAN(2),
        SPANISH(3),
        SPANISH_AMERICA(4),
        PORTUGUESE_BRAZIL(5),
        FRANCH(6),
        TURKISH(7),
        PORTUGUESE(8),
        NUOWAY(9),
        IVRIT(10),
        SWEDEN(11),
        UZBEKISTAN(12),
        RUSSIAN(13),
        ITALIN(14),
        THAI(15),
        DANISH(16),
        FINNISH(17),
        DUTCH(18),
        HUNGARIAN(19),
        GREEK(20),
        ARABIC(21),
        POLISH(22),
        JAPANESE(23),
        CZECH(24),
        TRADITIONAL_CHINESE(25),
        MALAY(26),
        BAHASA_INDONESIA(27),
        VIETNAMESE(28),
        ROMANIAN(29),
        KOREAN(30),
        SLOVAK(31),
        CROATIAN(32),
        CATALAN(33),
        KAZAKH(34),
        ENGLISH_AUSTRALIA(35),
        HINDI(36),
        BULGARIAN(37),
        UKRAINIAN(38),
        FRENCH_CANADA(39);

        int num;

        Language(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Orientation {
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

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum State {
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

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum Theme {
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

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum ViewMode {
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

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    public enum VoiceEvent {
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
        STOP_FOREHOLD(17),
        START_PIP(18),
        STOP_PIP(19);

        int num;

        VoiceEvent(int i) {
            this.num = i;
        }

        public int getNum() {
            return this.num;
        }
    }

    public interface a {
        void a(String str, int i, int i2);
    }

    public interface b {
        void a(int i);
    }

    public interface c {
        void a(int i, String str);
    }

    public interface d {
        void a(int i);
    }

    public static AvmManager b() {
        return com.ts.avm.sdk.b.a.b();
    }

    public abstract void a(com.ts.avm.sdk.a aVar);

    public abstract void c(Context context, com.ts.avm.sdk.a aVar);

    public abstract int d(boolean z);
}
