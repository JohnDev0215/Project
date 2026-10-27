package com.ts.avm.sdk;

import android.content.Context;
import com.ts.avm.bean.AvmCallInfoBean;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AvmManager {
    public static final int AVAILABLE_STATE = 1;
    public static final int BACKGROUND_STATE = 3;
    public static final int DISABLE_STATE = 4;
    public static final int ERROR_STATE = 5;
    public static final int FOREGROUND_STATE = 2;
    public static final int IDLE_STATE = 0;

    public interface AvmCallListener {
        void onCallStatusChange(String str, int i, int i2);
    }

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

        State(int num) {
            this.num = num;
        }
    }

    public enum Back implements Command {
        DEFAULT(1);

        private final int num;

        public int getNum() {
            return this.num;
        }

        Back(int num) {
            this.num = num;
        }
    }

    public enum Kill implements Command {
        DEFAULT(100);

        private final int num;

        public int getNum() {
            return this.num;
        }

        Kill(int num) {
            this.num = num;
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

        ViewMode(int num) {
            this.num = num;
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
        STOP_FOREHOLD(17),
        START_PIP(18),
        STOP_PIP(19);

        int num;

        public int getNum() {
            return this.num;
        }

        VoiceEvent(int num) {
            this.num = num;
        }
    }

    public enum Theme implements Command {
        LIGHT(0),
        DARK(1);

        int num;

        public int getNum() {
            return this.num;
        }

        Theme(int num) {
            this.num = num;
        }
    }

    public enum Orientation implements Command {
        HORIZONTAL(2),
        VERTICAL(3);

        int num;

        public int getNum() {
            return this.num;
        }

        Orientation(int num) {
            this.num = num;
        }
    }

    public enum Language implements Command {
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

        public int getNum() {
            return this.num;
        }

        Language(int num) {
            this.num = num;
        }
    }

    public enum Calibration implements Command {
        START(0),
        STOP(1);

        int num;

        public int getNum() {
            return this.num;
        }

        Calibration(int num) {
            this.num = num;
        }
    }

    public void init(Context context, IAvmServiceConnectionListener listener) {
    }

    public void deinit(IAvmServiceConnectionListener listener) {
    }

    public int voiceRecognitionControl(VoiceEvent mode) {
        return 0;
    }

    public int show() {
        return 0;
    }

    public int hide() {
        return 0;
    }

    public int setProvisionFinished(int mode) {
        return 0;
    }

    public int startPip() {
        return 0;
    }

    public int stopPip() {
        return 0;
    }

    public int setNaviState(int mode) {
        return 0;
    }

    public int onBack() {
        return 0;
    }

    public int getBackLineConfig() {
        return 0;
    }

    public int switchTheme(Theme theme) {
        return 0;
    }

    public int switchOrientation(Orientation orientation) {
        return 0;
    }

    public int switchLanguage(Language language) {
        return 0;
    }

    public int calibrate(Calibration calibration) {
        return 0;
    }

    public int getAvmWorkState() {
        return 0;
    }

    public int getAvmViewMode() {
        return 0;
    }

    public int registerAvmViewStatusListener(AvmViewStateListener listener) {
        return 0;
    }

    public int unregisterAvmViewStatusListener(AvmViewStateListener listener) {
        return 0;
    }

    public int registerAvmDockerStatusListener(AvmDockerStatusListener listener) {
        return 0;
    }

    public int unregisterAvmDockerStatusListener(AvmDockerStatusListener listener) {
        return 0;
    }

    public int registerAvmStatusListener(AvmStatusListener listener) {
        return 0;
    }

    public int unregisterAvmStatusListener(AvmStatusListener listener) {
        return 0;
    }

    public int killAvm() {
        return 0;
    }

    public int syncHvacIcon2Qnx(int value) {
        return 0;
    }

    public int switchRadarAlarmToneStatus(boolean isOn) {
        return 0;
    }

    public int sendCallInfo(String phoneNum, String contactName, String address, int callTime, int callType, int callState, boolean isMute) {
        return 0;
    }

    public int registerCallStatusListener(AvmCallListener listener) {
        return 0;
    }

    public int unregisterCallStatusListener(AvmCallListener listener) {
        return 0;
    }

    public int sendMultiCallInfo(List<AvmCallInfoBean> list) {
        return 0;
    }
}
