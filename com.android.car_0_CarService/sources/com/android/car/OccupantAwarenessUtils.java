package com.android.car;

import android.car.occupantawareness.DriverMonitoringDetection;
import android.car.occupantawareness.GazeDetection;
import android.car.occupantawareness.OccupantAwarenessDetection;
import android.car.occupantawareness.Point3D;
import android.car.occupantawareness.SystemStatusEvent;
import android.hardware.automotive.occupant_awareness.OccupantDetection;
import android.hardware.automotive.occupant_awareness.PresenceDetection;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
final class OccupantAwarenessUtils {
    private static final String TAG = "OccupantAwarenessUtils";

    private OccupantAwarenessUtils() {
    }

    static SystemStatusEvent convertToStatusEvent(int inputDetectionFlags, byte inputStatus) {
        int outputStatus = 2;
        if (inputStatus == 0) {
            outputStatus = 0;
        } else if (inputStatus == 1) {
            outputStatus = 1;
        } else if (inputStatus == 2) {
            outputStatus = 2;
        } else if (inputStatus == 3) {
            outputStatus = 3;
        } else {
            Log.e(TAG, "Invalid status code: " + ((int) inputStatus));
        }
        int outputFlags = 0;
        if (inputDetectionFlags == 0) {
            outputFlags = 0;
        } else if (inputDetectionFlags == 1) {
            outputFlags = 1;
        } else if (inputDetectionFlags == 2) {
            outputFlags = 2;
        } else if (inputDetectionFlags == 4) {
            outputFlags = 4;
        } else {
            Log.e(TAG, "Invalid status code: " + inputDetectionFlags);
        }
        return new SystemStatusEvent(outputStatus, outputFlags);
    }

    static int convertToConfidenceScore(int inputConfidence) {
        if (inputConfidence == 1) {
            return 1;
        }
        if (inputConfidence != 2) {
            return inputConfidence != 3 ? 0 : 3;
        }
        return 2;
    }

    static Point3D convertToPoint3D(double[] vector) {
        if (vector != null && vector.length == 3) {
            return new Point3D(vector[0], vector[1], vector[2]);
        }
        return null;
    }

    static long convertTimestamp(long inputTimestamp) {
        return inputTimestamp;
    }

    static int convertToRole(int inputRole) {
        if (inputRole == 0 || inputRole == 1) {
            return 0;
        }
        if (inputRole == 511) {
            return 510;
        }
        int outputRole = 0;
        if ((inputRole & 4) > 0) {
            outputRole = 0 | 4;
        }
        if ((inputRole & 2) > 0) {
            outputRole |= 2;
        }
        if ((inputRole & 8) > 0) {
            outputRole |= 8;
        }
        if ((inputRole & 16) > 0) {
            outputRole |= 16;
        }
        if ((inputRole & 32) > 0) {
            outputRole |= 32;
        }
        if ((inputRole & 64) > 0) {
            outputRole |= 64;
        }
        if ((inputRole & 128) > 0) {
            outputRole |= 128;
        }
        if ((inputRole & 256) > 0) {
            return outputRole | 256;
        }
        return outputRole;
    }

    static GazeDetection convertToGazeDetection(android.hardware.automotive.occupant_awareness.GazeDetection inputDetection) {
        return new GazeDetection(convertToConfidenceScore(inputDetection.gazeConfidence), convertToPoint3D(inputDetection.headPosition), convertToPoint3D(inputDetection.headPosition), convertToPoint3D(inputDetection.headAngleUnitVector), convertToPoint3D(inputDetection.gazeAngleUnitVector), inputDetection.gazeTarget, inputDetection.timeOnTargetMillis);
    }

    static DriverMonitoringDetection convertToDriverMonitoringDetection(android.hardware.automotive.occupant_awareness.DriverMonitoringDetection inputDetection) {
        return new DriverMonitoringDetection(convertToConfidenceScore(inputDetection.confidenceScore), inputDetection.isLookingOnRoad, inputDetection.gazeDurationMillis);
    }

    static boolean convertToPresence(PresenceDetection inputDetection) {
        return inputDetection.isOccupantDetected;
    }

    static OccupantAwarenessDetection convertToDetectionEvent(long timestamp, OccupantDetection inputDetection) {
        DriverMonitoringDetection driverMonitoringDetection;
        boolean isPresent = false;
        if (inputDetection.presenceData != null && inputDetection.presenceData.length > 0) {
            isPresent = convertToPresence(inputDetection.presenceData[0]);
        }
        GazeDetection gazeDetection = null;
        if (inputDetection.gazeData != null && inputDetection.gazeData.length > 0) {
            gazeDetection = convertToGazeDetection(inputDetection.gazeData[0]);
        }
        if (inputDetection.attentionData != null && inputDetection.attentionData.length > 0) {
            DriverMonitoringDetection driverMonitoringDetection2 = convertToDriverMonitoringDetection(inputDetection.attentionData[0]);
            driverMonitoringDetection = driverMonitoringDetection2;
        } else {
            driverMonitoringDetection = null;
        }
        return new OccupantAwarenessDetection(convertToRole(inputDetection.role), timestamp, isPresent, gazeDetection, driverMonitoringDetection);
    }
}
