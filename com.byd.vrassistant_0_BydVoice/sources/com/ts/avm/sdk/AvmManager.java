package com.ts.avm.sdk;

import android.content.Context;
import com.ts.avm.sdk.impl.AvmManagerImpl;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AvmManager {
    public static final int AVAILABLE_STATE = 1;
    public static final int BACKGROUND_STATE = 3;
    public static final int DISABLE_STATE = 4;
    public static final int ERROR_STATE = 5;
    public static final int FOREGROUND_STATE = 2;
    public static final int IDLE_STATE = 0;

    public interface AvmDockerStatusListener {
        void onAvmDockerStatusChanged(int i);
    }

    public interface AvmStatusListener {
        void onStatusChanged(int i, String str);
    }

    public interface AvmViewStateListener {
        void onAvmViewStateChanged(int i);
    }

    public interface Command {
    }

    public enum Default implements Command {
        NONE
    }

    public int calibrate(Calibration calibration) {
        return 0;
    }

    public void deinit(IAvmServiceConnectionListener iAvmServiceConnectionListener) {
    }

    public int getAvmViewMode() {
        return 0;
    }

    public int getAvmWorkState() {
        return 0;
    }

    public int getBackLineConfig() {
        return 0;
    }

    public int hide() {
        return 0;
    }

    public void init(Context context, IAvmServiceConnectionListener iAvmServiceConnectionListener) {
    }

    public int killAvm() {
        return 0;
    }

    public int onBack() {
        return 0;
    }

    public int registerAvmDockerStatusListener(AvmDockerStatusListener avmDockerStatusListener) {
        return 0;
    }

    public int registerAvmStatusListener(AvmStatusListener avmStatusListener) {
        return 0;
    }

    public int registerAvmViewStatusListener(AvmViewStateListener avmViewStateListener) {
        return 0;
    }

    public int show() {
        return 0;
    }

    public int switchLanguage(Language language) {
        return 0;
    }

    public int switchOrientation(Orientation orientation) {
        return 0;
    }

    public int switchTheme(Theme theme) {
        return 0;
    }

    public int unregisterAvmDockerStatusListener(AvmDockerStatusListener avmDockerStatusListener) {
        return 0;
    }

    public int unregisterAvmStatusListener(AvmStatusListener avmStatusListener) {
        return 0;
    }

    public int unregisterAvmViewStatusListener(AvmViewStateListener avmViewStateListener) {
        return 0;
    }

    public int voiceRecognitionControl(VoiceEvent voiceEvent) {
        return 0;
    }

    public static AvmManager getInstance() {
        return AvmManagerImpl.getInstance();
    }

    public enum State implements Command {
        START(5),
        STOP(4);

        int num;

        public int getNum() {
            return this.num;
        }

        State(int i) {
            this.num = i;
        }
    }

    public enum Back implements Command {
        DEFAULT(1);

        private final int num;

        public int getNum() {
            return this.num;
        }

        Back(int i) {
            this.num = i;
        }
    }

    public enum Kill implements Command {
        DEFAULT(100);

        private final int num;

        public int getNum() {
            return this.num;
        }

        Kill(int i) {
            this.num = i;
        }
    }

    public enum ViewMode implements Command {
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

        public int getNum() {
            return this.num;
        }

        ViewMode(int i) {
            this.num = i;
        }
    }

    public enum VoiceEvent implements Command {
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

        public int getNum() {
            return this.num;
        }

        VoiceEvent(int i) {
            this.num = i;
        }
    }

    public enum Theme implements Command {
        LIGHT(0),
        DARK(1);

        int num;

        public int getNum() {
            return this.num;
        }

        Theme(int i) {
            this.num = i;
        }
    }

    public enum Orientation implements Command {
        HORIZONTAL(2),
        VERTICAL(3);

        int num;

        public int getNum() {
            return this.num;
        }

        Orientation(int i) {
            this.num = i;
        }
    }

    public enum Language implements Command {
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

        public int getNum() {
            return this.num;
        }

        Language(int i) {
            this.num = i;
        }
    }

    public enum Calibration implements Command {
        START(0),
        STOP(1);

        int num;

        public int getNum() {
            return this.num;
        }

        Calibration(int i) {
            this.num = i;
        }
    }
}
