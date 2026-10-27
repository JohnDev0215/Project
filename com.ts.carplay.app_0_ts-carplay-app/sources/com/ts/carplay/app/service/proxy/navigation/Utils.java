package com.ts.carplay.app.service.proxy.navigation;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final long DAY = 86400;
    private static final int DRIVE_SIDE_RIGHT = 0;
    private static final long HOUR = 3600;
    private static final long MINUTE = 60;

    public static EndTimeInfo convertTime(long endTime, long estimatedTime) {
        int days = (int) (endTime / DAY);
        int hours = (int) ((estimatedTime % DAY) / HOUR);
        int minutes = (int) ((estimatedTime % HOUR) / MINUTE);
        int seconds = (int) (estimatedTime % MINUTE);
        return new EndTimeInfo(days, hours, minutes, seconds);
    }

    public static int convertManeuver(int maneuver, int side) {
        switch (maneuver) {
            case 0:
            case 3:
                return 8;
            case 1:
            case 20:
                return 1;
            case 2:
            case 21:
                return 2;
            case 4:
            case 26:
                return side == 0 ? 7 : 14;
            case 5:
            case 11:
            case 27:
                return 15;
            case 6:
                return side == 0 ? 60 : 4;
            case 7:
                return side == 0 ? 63 : 47;
            case 8:
            case 9:
                return side == 0 ? 53 : 51;
            case 10:
            case 12:
            case 24:
            case 25:
                return 12;
            case 13:
            case 49:
            case 52:
                return 3;
            case 14:
            case 50:
            case 53:
                return 4;
            case 15:
            case 16:
            case 17:
                return 58;
            case 18:
                return side == 0 ? 7 : 14;
            case 19:
                return side == 0 ? 64 : 46;
            case 22:
                return 51;
            case 23:
            case 51:
                return 53;
            case 28:
                return 20;
            case 29:
            case 30:
            case 34:
            case 35:
            case 38:
            case 39:
            case 40:
            case 43:
            case 44:
            case 45:
                return 60;
            case 31:
                return 16;
            case 32:
                return 22;
            case 33:
                return 47;
            case 36:
            case 41:
                return 64;
            case 37:
            case 46:
                return 19;
            case 42:
                return 46;
            case 47:
                return 5;
            case 48:
                return 6;
            default:
                return 0;
        }
    }

    public class BydManeuver {
        public static final int CONTACT = 11;
        public static final int DESTINATION = 12;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_RIGHT_HAND_STRAIGHT_AHEAD = 65;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD = 66;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1 = 50;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2 = 52;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_1 = 48;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_2 = 51;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_3 = 54;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_4 = 55;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_1 = 49;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_2 = 53;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3 = 56;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_4 = 57;
        public static final int ENTER_THE_TUNNEL = 13;
        public static final int FERRY = 58;
        public static final int INVALID = 0;
        public static final int LEFT_FRONT = 3;
        public static final int LEFT_REAR = 5;
        public static final int LEFT_TURNAROUND = 7;
        public static final int PASSING_POINTS_1 = 85;
        public static final int PASSING_POINTS_2 = 86;
        public static final int PASSING_POINTS_3 = 87;
        public static final int PASSING_POINTS_4 = 88;
        public static final int PASSING_POINTS_5 = 89;
        public static final int PROGRADE_MOTION = 15;
        public static final int REST_POINT = 10;
        public static final int RIGHT_FRONT = 4;
        public static final int RIGHT_REAR = 6;
        public static final int RIGHT_TURNAROUND = 14;
        public static final int ROUNDABOUT_EXIT_1 = 16;
        public static final int ROUNDABOUT_EXIT_10 = 45;
        public static final int ROUNDABOUT_EXIT_11 = 46;
        public static final int ROUNDABOUT_EXIT_12 = 47;
        public static final int ROUNDABOUT_EXIT_13 = 59;
        public static final int ROUNDABOUT_EXIT_14 = 60;
        public static final int ROUNDABOUT_EXIT_15 = 61;
        public static final int ROUNDABOUT_EXIT_16 = 62;
        public static final int ROUNDABOUT_EXIT_17 = 63;
        public static final int ROUNDABOUT_EXIT_18 = 64;
        public static final int ROUNDABOUT_EXIT_2 = 17;
        public static final int ROUNDABOUT_EXIT_3 = 18;
        public static final int ROUNDABOUT_EXIT_4 = 19;
        public static final int ROUNDABOUT_EXIT_5 = 20;
        public static final int ROUNDABOUT_EXIT_6 = 21;
        public static final int ROUNDABOUT_EXIT_7 = 22;
        public static final int ROUNDABOUT_EXIT_8 = 23;
        public static final int ROUNDABOUT_EXIT_9 = 44;
        public static final int STRAIGHT_AHEAD = 8;
        public static final int THREE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD = 82;
        public static final int THREE_CROSS_SMALL_ANGLE_RIGHT_RIGHT_STRAIGHT_AHEAD = 84;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1 = 68;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2 = 76;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_3 = 79;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_4 = 83;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_1 = 67;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_2 = 70;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_3 = 71;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4 = 72;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5 = 80;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_6 = 81;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_1 = 69;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_2 = 73;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3 = 74;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_4 = 75;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5 = 77;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_6 = 78;
        public static final int TURN_LEFT = 1;
        public static final int TURN_RIGHT = 2;

        public BydManeuver() {
        }
    }

    public static class EndTimeInfo {
        private int days;
        private int hours;
        private int minutes;
        private int seconds;

        public EndTimeInfo(int days, int hours, int minutes, int seconds) {
            this.days = days;
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }

        public int getDays() {
            return this.days;
        }

        public int getHours() {
            return this.hours;
        }

        public int getMinutes() {
            return this.minutes;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public String toString() {
            return "days:" + this.days + " hours:" + this.hours + " minutes:" + this.minutes + " seconds:" + this.seconds;
        }
    }
}
