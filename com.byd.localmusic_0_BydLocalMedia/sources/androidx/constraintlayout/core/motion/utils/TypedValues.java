package androidx.constraintlayout.core.motion.utils;

import android.bluetooth.BluetoothHidDevice;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import com.android.internal.midi.MidiConstants;

/* JADX INFO: loaded from: classes4.dex */
public interface TypedValues {
    public static final int BOOLEAN_MASK = 1;
    public static final int FLOAT_MASK = 4;
    public static final int INT_MASK = 2;
    public static final int STRING_MASK = 8;
    public static final String S_CUSTOM = "CUSTOM";
    public static final int TYPE_FRAME_POSITION = 100;
    public static final int TYPE_TARGET = 101;

    int getId(String str);

    boolean setValue(int i, float f);

    boolean setValue(int i, int i2);

    boolean setValue(int i, String str);

    boolean setValue(int i, boolean z);

    public interface Attributes {
        public static final String S_ALPHA = "alpha";
        public static final String S_CURVE_FIT = "curveFit";
        public static final String S_CUSTOM = "CUSTOM";
        public static final String S_EASING = "easing";
        public static final String S_ELEVATION = "elevation";
        public static final String S_PATH_ROTATE = "pathRotate";
        public static final String S_PIVOT_X = "pivotX";
        public static final String S_PIVOT_Y = "pivotY";
        public static final String S_PROGRESS = "progress";
        public static final String S_ROTATION_X = "rotationX";
        public static final String S_ROTATION_Y = "rotationY";
        public static final String S_ROTATION_Z = "rotationZ";
        public static final String S_SCALE_X = "scaleX";
        public static final String S_SCALE_Y = "scaleY";
        public static final String S_TRANSLATION_X = "translationX";
        public static final String S_TRANSLATION_Y = "translationY";
        public static final String S_TRANSLATION_Z = "translationZ";
        public static final String S_VISIBILITY = "visibility";
        public static final int TYPE_ALPHA = 303;
        public static final int TYPE_CURVE_FIT = 301;
        public static final int TYPE_EASING = 317;
        public static final int TYPE_ELEVATION = 307;
        public static final int TYPE_PATH_ROTATE = 316;
        public static final int TYPE_PIVOT_TARGET = 318;
        public static final int TYPE_PIVOT_X = 313;
        public static final int TYPE_PIVOT_Y = 314;
        public static final int TYPE_PROGRESS = 315;
        public static final int TYPE_ROTATION_X = 308;
        public static final int TYPE_ROTATION_Y = 309;
        public static final int TYPE_ROTATION_Z = 310;
        public static final int TYPE_SCALE_X = 311;
        public static final int TYPE_SCALE_Y = 312;
        public static final int TYPE_TRANSLATION_X = 304;
        public static final int TYPE_TRANSLATION_Y = 305;
        public static final int TYPE_TRANSLATION_Z = 306;
        public static final int TYPE_VISIBILITY = 302;
        public static final String S_FRAME = "frame";
        public static final String S_TARGET = "target";
        public static final String S_PIVOT_TARGET = "pivotTarget";
        public static final String[] KEY_WORDS = {"curveFit", "visibility", "alpha", "translationX", "translationY", "translationZ", "elevation", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "pivotX", "pivotY", "progress", "pathRotate", "easing", "CUSTOM", S_FRAME, S_TARGET, S_PIVOT_TARGET};

        static int getType(int i) {
            if (i == 100) {
                return 2;
            }
            if (i == 101) {
                return 8;
            }
            switch (i) {
                case 301:
                case 302:
                    return 2;
                case 303:
                case 304:
                case 305:
                case 306:
                case 307:
                case 308:
                case 309:
                case 310:
                case 311:
                case 312:
                case 313:
                case 314:
                case 315:
                case 316:
                    return 4;
                case 317:
                case 318:
                    return 8;
                default:
                    return -1;
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        static int getId(String str) {
            byte b;
            switch (str.hashCode()) {
                case -1310311125:
                    b = !str.equals("easing") ? (byte) -1 : (byte) 16;
                    break;
                case -1249320806:
                    b = !str.equals("rotationX") ? (byte) -1 : (byte) 7;
                    break;
                case -1249320805:
                    b = !str.equals("rotationY") ? (byte) -1 : (byte) 8;
                    break;
                case -1249320804:
                    b = !str.equals("rotationZ") ? (byte) -1 : (byte) 9;
                    break;
                case -1225497657:
                    b = !str.equals("translationX") ? (byte) -1 : (byte) 3;
                    break;
                case -1225497656:
                    b = !str.equals("translationY") ? (byte) -1 : (byte) 4;
                    break;
                case -1225497655:
                    b = !str.equals("translationZ") ? (byte) -1 : (byte) 5;
                    break;
                case -1001078227:
                    b = !str.equals("progress") ? (byte) -1 : BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                    break;
                case -987906986:
                    b = !str.equals("pivotX") ? (byte) -1 : (byte) 12;
                    break;
                case -987906985:
                    b = !str.equals("pivotY") ? (byte) -1 : IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                    break;
                case -908189618:
                    b = !str.equals("scaleX") ? (byte) -1 : (byte) 10;
                    break;
                case -908189617:
                    b = !str.equals("scaleY") ? (byte) -1 : (byte) 11;
                    break;
                case -880905839:
                    b = !str.equals(S_TARGET) ? (byte) -1 : (byte) 18;
                    break;
                case -4379043:
                    b = !str.equals("elevation") ? (byte) -1 : (byte) 6;
                    break;
                case 92909918:
                    b = !str.equals("alpha") ? (byte) -1 : (byte) 2;
                    break;
                case 97692013:
                    b = !str.equals(S_FRAME) ? (byte) -1 : (byte) 17;
                    break;
                case 579057826:
                    b = !str.equals("curveFit") ? (byte) -1 : (byte) 0;
                    break;
                case 803192288:
                    b = !str.equals("pathRotate") ? (byte) -1 : MidiConstants.STATUS_CHANNEL_MASK;
                    break;
                case 1167159411:
                    b = !str.equals(S_PIVOT_TARGET) ? (byte) -1 : (byte) 19;
                    break;
                case 1941332754:
                    b = !str.equals("visibility") ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    return 301;
                case 1:
                    return 302;
                case 2:
                    return 303;
                case 3:
                    return 304;
                case 4:
                    return 305;
                case 5:
                    return 306;
                case 6:
                    return 307;
                case 7:
                    return 308;
                case 8:
                    return 309;
                case 9:
                    return 310;
                case 10:
                    return 311;
                case 11:
                    return 312;
                case 12:
                    return 313;
                case 13:
                    return 314;
                case 14:
                    return 315;
                case 15:
                    return 316;
                case 16:
                    return 317;
                case 17:
                    return 100;
                case 18:
                    return 101;
                case 19:
                    return 318;
                default:
                    return -1;
            }
        }
    }

    public interface Cycle {
        public static final String S_ALPHA = "alpha";
        public static final String S_CURVE_FIT = "curveFit";
        public static final String S_EASING = "easing";
        public static final String S_ELEVATION = "elevation";
        public static final String S_PATH_ROTATE = "pathRotate";
        public static final String S_PIVOT_X = "pivotX";
        public static final String S_PIVOT_Y = "pivotY";
        public static final String S_PROGRESS = "progress";
        public static final String S_ROTATION_X = "rotationX";
        public static final String S_ROTATION_Y = "rotationY";
        public static final String S_ROTATION_Z = "rotationZ";
        public static final String S_SCALE_X = "scaleX";
        public static final String S_SCALE_Y = "scaleY";
        public static final String S_TRANSLATION_X = "translationX";
        public static final String S_TRANSLATION_Y = "translationY";
        public static final String S_TRANSLATION_Z = "translationZ";
        public static final String S_VISIBILITY = "visibility";
        public static final String S_WAVE_OFFSET = "offset";
        public static final String S_WAVE_SHAPE = "waveShape";
        public static final int TYPE_ALPHA = 403;
        public static final int TYPE_CURVE_FIT = 401;
        public static final int TYPE_CUSTOM_WAVE_SHAPE = 422;
        public static final int TYPE_EASING = 420;
        public static final int TYPE_ELEVATION = 307;
        public static final int TYPE_PATH_ROTATE = 416;
        public static final int TYPE_PIVOT_X = 313;
        public static final int TYPE_PIVOT_Y = 314;
        public static final int TYPE_PROGRESS = 315;
        public static final int TYPE_ROTATION_X = 308;
        public static final int TYPE_ROTATION_Y = 309;
        public static final int TYPE_ROTATION_Z = 310;
        public static final int TYPE_SCALE_X = 311;
        public static final int TYPE_SCALE_Y = 312;
        public static final int TYPE_TRANSLATION_X = 304;
        public static final int TYPE_TRANSLATION_Y = 305;
        public static final int TYPE_TRANSLATION_Z = 306;
        public static final int TYPE_VISIBILITY = 402;
        public static final int TYPE_WAVE_OFFSET = 424;
        public static final int TYPE_WAVE_PERIOD = 423;
        public static final int TYPE_WAVE_PHASE = 425;
        public static final int TYPE_WAVE_SHAPE = 421;
        public static final String S_CUSTOM_WAVE_SHAPE = "customWave";
        public static final String S_WAVE_PERIOD = "period";
        public static final String S_WAVE_PHASE = "phase";
        public static final String[] KEY_WORDS = {"curveFit", "visibility", "alpha", "translationX", "translationY", "translationZ", "elevation", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "pivotX", "pivotY", "progress", "pathRotate", "easing", "waveShape", S_CUSTOM_WAVE_SHAPE, S_WAVE_PERIOD, "offset", S_WAVE_PHASE};

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        static int getId(String str) {
            byte b;
            switch (str.hashCode()) {
                case -1310311125:
                    b = !str.equals("easing") ? (byte) -1 : MidiConstants.STATUS_CHANNEL_MASK;
                    break;
                case -1249320806:
                    b = !str.equals("rotationX") ? (byte) -1 : (byte) 6;
                    break;
                case -1249320805:
                    b = !str.equals("rotationY") ? (byte) -1 : (byte) 7;
                    break;
                case -1249320804:
                    b = !str.equals("rotationZ") ? (byte) -1 : (byte) 8;
                    break;
                case -1225497657:
                    b = !str.equals("translationX") ? (byte) -1 : (byte) 3;
                    break;
                case -1225497656:
                    b = !str.equals("translationY") ? (byte) -1 : (byte) 4;
                    break;
                case -1225497655:
                    b = !str.equals("translationZ") ? (byte) -1 : (byte) 5;
                    break;
                case -1001078227:
                    b = !str.equals("progress") ? (byte) -1 : IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                    break;
                case -987906986:
                    b = !str.equals("pivotX") ? (byte) -1 : (byte) 11;
                    break;
                case -987906985:
                    b = !str.equals("pivotY") ? (byte) -1 : (byte) 12;
                    break;
                case -908189618:
                    b = !str.equals("scaleX") ? (byte) -1 : (byte) 9;
                    break;
                case -908189617:
                    b = !str.equals("scaleY") ? (byte) -1 : (byte) 10;
                    break;
                case 92909918:
                    b = !str.equals("alpha") ? (byte) -1 : (byte) 2;
                    break;
                case 579057826:
                    b = !str.equals("curveFit") ? (byte) -1 : (byte) 0;
                    break;
                case 803192288:
                    b = !str.equals("pathRotate") ? (byte) -1 : BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                    break;
                case 1941332754:
                    b = !str.equals("visibility") ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    return 401;
                case 1:
                    return 402;
                case 2:
                    return 403;
                case 3:
                    return 304;
                case 4:
                    return 305;
                case 5:
                    return 306;
                case 6:
                    return 308;
                case 7:
                    return 309;
                case 8:
                    return 310;
                case 9:
                    return 311;
                case 10:
                    return 312;
                case 11:
                    return 313;
                case 12:
                    return 314;
                case 13:
                    return 315;
                case 14:
                    return 416;
                case 15:
                    return 420;
                default:
                    return -1;
            }
        }
    }

    public interface Trigger {
        public static final String CROSS = "CROSS";
        public static final String[] KEY_WORDS = {"viewTransitionOnCross", "viewTransitionOnPositiveCross", "viewTransitionOnNegativeCross", "postLayout", "triggerSlack", "triggerCollisionView", "triggerCollisionId", "triggerID", "positiveCross", "negativeCross", "triggerReceiver", "CROSS"};
        public static final String NEGATIVE_CROSS = "negativeCross";
        public static final String POSITIVE_CROSS = "positiveCross";
        public static final String POST_LAYOUT = "postLayout";
        public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
        public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
        public static final String TRIGGER_ID = "triggerID";
        public static final String TRIGGER_RECEIVER = "triggerReceiver";
        public static final String TRIGGER_SLACK = "triggerSlack";
        public static final int TYPE_CROSS = 312;
        public static final int TYPE_NEGATIVE_CROSS = 310;
        public static final int TYPE_POSITIVE_CROSS = 309;
        public static final int TYPE_POST_LAYOUT = 304;
        public static final int TYPE_TRIGGER_COLLISION_ID = 307;
        public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
        public static final int TYPE_TRIGGER_ID = 308;
        public static final int TYPE_TRIGGER_RECEIVER = 311;
        public static final int TYPE_TRIGGER_SLACK = 305;
        public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
        public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
        public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
        public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
        public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
        public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";

        static int getId(String str) {
            switch (str) {
                case "viewTransitionOnCross":
                    return 301;
                case "viewTransitionOnPositiveCross":
                    return 302;
                case "viewTransitionOnNegativeCross":
                    return 303;
                case "postLayout":
                    return 304;
                case "triggerSlack":
                    return 305;
                case "triggerCollisionView":
                    return 306;
                case "triggerCollisionId":
                    return 307;
                case "triggerID":
                    return 308;
                case "positiveCross":
                    return 309;
                case "negativeCross":
                    return 310;
                case "triggerReceiver":
                    return 311;
                case "CROSS":
                    return 312;
                default:
                    return -1;
            }
        }
    }

    public interface Position {
        public static final String[] KEY_WORDS = {"transitionEasing", "drawPath", "percentWidth", "percentHeight", "sizePercent", "percentX", "percentY"};
        public static final String S_DRAWPATH = "drawPath";
        public static final String S_PERCENT_HEIGHT = "percentHeight";
        public static final String S_PERCENT_WIDTH = "percentWidth";
        public static final String S_PERCENT_X = "percentX";
        public static final String S_PERCENT_Y = "percentY";
        public static final String S_SIZE_PERCENT = "sizePercent";
        public static final String S_TRANSITION_EASING = "transitionEasing";
        public static final int TYPE_CURVE_FIT = 508;
        public static final int TYPE_DRAWPATH = 502;
        public static final int TYPE_PATH_MOTION_ARC = 509;
        public static final int TYPE_PERCENT_HEIGHT = 504;
        public static final int TYPE_PERCENT_WIDTH = 503;
        public static final int TYPE_PERCENT_X = 506;
        public static final int TYPE_PERCENT_Y = 507;
        public static final int TYPE_POSITION_TYPE = 510;
        public static final int TYPE_SIZE_PERCENT = 505;
        public static final int TYPE_TRANSITION_EASING = 501;

        static int getId(String str) {
            switch (str) {
                case "transitionEasing":
                    return 501;
                case "drawPath":
                    return 502;
                case "percentWidth":
                    return 503;
                case "percentHeight":
                    return 504;
                case "sizePercent":
                    return 505;
                case "percentX":
                    return 506;
                case "percentY":
                    return 507;
                default:
                    return -1;
            }
        }
    }

    public interface Motion {
        public static final int TYPE_ANIMATE_CIRCLEANGLE_TO = 606;
        public static final int TYPE_ANIMATE_RELATIVE_TO = 605;
        public static final int TYPE_DRAW_PATH = 608;
        public static final int TYPE_EASING = 603;
        public static final int TYPE_PATHMOTION_ARC = 607;
        public static final int TYPE_PATH_ROTATE = 601;
        public static final int TYPE_POLAR_RELATIVETO = 609;
        public static final int TYPE_QUANTIZE_INTERPOLATOR = 604;
        public static final int TYPE_QUANTIZE_INTERPOLATOR_ID = 612;
        public static final int TYPE_QUANTIZE_INTERPOLATOR_TYPE = 611;
        public static final int TYPE_QUANTIZE_MOTIONSTEPS = 610;
        public static final int TYPE_QUANTIZE_MOTION_PHASE = 602;
        public static final int TYPE_STAGGER = 600;
        public static final String S_STAGGER = "Stagger";
        public static final String S_PATH_ROTATE = "PathRotate";
        public static final String S_QUANTIZE_MOTION_PHASE = "QuantizeMotionPhase";
        public static final String S_EASING = "TransitionEasing";
        public static final String S_QUANTIZE_INTERPOLATOR = "QuantizeInterpolator";
        public static final String S_ANIMATE_RELATIVE_TO = "AnimateRelativeTo";
        public static final String S_ANIMATE_CIRCLEANGLE_TO = "AnimateCircleAngleTo";
        public static final String S_PATHMOTION_ARC = "PathMotionArc";
        public static final String S_DRAW_PATH = "DrawPath";
        public static final String S_POLAR_RELATIVETO = "PolarRelativeTo";
        public static final String S_QUANTIZE_MOTIONSTEPS = "QuantizeMotionSteps";
        public static final String S_QUANTIZE_INTERPOLATOR_TYPE = "QuantizeInterpolatorType";
        public static final String S_QUANTIZE_INTERPOLATOR_ID = "QuantizeInterpolatorID";
        public static final String[] KEY_WORDS = {S_STAGGER, S_PATH_ROTATE, S_QUANTIZE_MOTION_PHASE, S_EASING, S_QUANTIZE_INTERPOLATOR, S_ANIMATE_RELATIVE_TO, S_ANIMATE_CIRCLEANGLE_TO, S_PATHMOTION_ARC, S_DRAW_PATH, S_POLAR_RELATIVETO, S_QUANTIZE_MOTIONSTEPS, S_QUANTIZE_INTERPOLATOR_TYPE, S_QUANTIZE_INTERPOLATOR_ID};

        static int getId(String str) {
            switch (str) {
                case "Stagger":
                    return 600;
                case "PathRotate":
                    return 601;
                case "QuantizeMotionPhase":
                    return 602;
                case "TransitionEasing":
                    return 603;
                case "QuantizeInterpolator":
                    return 604;
                case "AnimateRelativeTo":
                    return 605;
                case "AnimateCircleAngleTo":
                    return 606;
                case "PathMotionArc":
                    return 607;
                case "DrawPath":
                    return 608;
                case "PolarRelativeTo":
                    return 609;
                case "QuantizeMotionSteps":
                    return 610;
                case "QuantizeInterpolatorType":
                    return 611;
                case "QuantizeInterpolatorID":
                    return 612;
                default:
                    return -1;
            }
        }
    }

    public interface Custom {
        public static final String S_COLOR = "color";
        public static final String S_INT = "integer";
        public static final int TYPE_BOOLEAN = 904;
        public static final int TYPE_COLOR = 902;
        public static final int TYPE_DIMENSION = 905;
        public static final int TYPE_FLOAT = 901;
        public static final int TYPE_INT = 900;
        public static final int TYPE_REFERENCE = 906;
        public static final int TYPE_STRING = 903;
        public static final String S_FLOAT = "float";
        public static final String S_STRING = "string";
        public static final String S_BOOLEAN = "boolean";
        public static final String S_DIMENSION = "dimension";
        public static final String S_REFERENCE = "refrence";
        public static final String[] KEY_WORDS = {S_FLOAT, "color", S_STRING, S_BOOLEAN, S_DIMENSION, S_REFERENCE};

        static int getId(String str) {
            switch (str) {
                case "integer":
                    return 900;
                case "float":
                    return 901;
                case "color":
                    return 902;
                case "string":
                    return 903;
                case "boolean":
                    return 904;
                case "dimension":
                    return 905;
                case "refrence":
                    return 906;
                default:
                    return -1;
            }
        }
    }
}
