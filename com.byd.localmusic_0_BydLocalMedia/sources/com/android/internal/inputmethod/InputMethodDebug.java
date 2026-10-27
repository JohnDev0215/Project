package com.android.internal.inputmethod;

import java.util.StringJoiner;

/* JADX INFO: loaded from: classes4.dex */
public final class InputMethodDebug {
    private InputMethodDebug() {
    }

    public static String startInputReasonToString(int i) {
        switch (i) {
            case 0:
                return "UNSPECIFIED";
            case 1:
                return "WINDOW_FOCUS_GAIN";
            case 2:
                return "WINDOW_FOCUS_GAIN_REPORT_WITH_CONNECTION";
            case 3:
                return "WINDOW_FOCUS_GAIN_REPORT_WITHOUT_CONNECTION";
            case 4:
                return "APP_CALLED_RESTART_INPUT_API";
            case 5:
                return "CHECK_FOCUS";
            case 6:
                return "BOUND_TO_IMMS";
            case 7:
                return "UNBOUND_FROM_IMMS";
            case 8:
                return "ACTIVATED_BY_IMMS";
            case 9:
                return "DEACTIVATED_BY_IMMS";
            case 10:
                return "SESSION_CREATED_BY_IME";
            default:
                return "Unknown=" + i;
        }
    }

    public static String unbindReasonToString(int i) {
        switch (i) {
            case 0:
                return "UNSPECIFIED";
            case 1:
                return "SWITCH_CLIENT";
            case 2:
                return "SWITCH_IME";
            case 3:
                return "DISCONNECT_IME";
            case 4:
                return "NO_IME";
            case 5:
                return "SWITCH_IME_FAILED";
            case 6:
                return "SWITCH_USER";
            default:
                return "Unknown=" + i;
        }
    }

    public static String softInputModeToString(int i) {
        StringJoiner stringJoiner = new StringJoiner("|");
        int i2 = i & 15;
        int i3 = i & 240;
        boolean z = (i & 256) != 0;
        if (i2 == 0) {
            stringJoiner.add("STATE_UNSPECIFIED");
        } else if (i2 == 1) {
            stringJoiner.add("STATE_UNCHANGED");
        } else if (i2 == 2) {
            stringJoiner.add("STATE_HIDDEN");
        } else if (i2 == 3) {
            stringJoiner.add("STATE_ALWAYS_HIDDEN");
        } else if (i2 == 4) {
            stringJoiner.add("STATE_VISIBLE");
        } else if (i2 == 5) {
            stringJoiner.add("STATE_ALWAYS_VISIBLE");
        } else {
            stringJoiner.add("STATE_UNKNOWN(" + i2 + ")");
        }
        if (i3 == 0) {
            stringJoiner.add("ADJUST_UNSPECIFIED");
        } else if (i3 == 16) {
            stringJoiner.add("ADJUST_RESIZE");
        } else if (i3 == 32) {
            stringJoiner.add("ADJUST_PAN");
        } else if (i3 == 48) {
            stringJoiner.add("ADJUST_NOTHING");
        } else {
            stringJoiner.add("ADJUST_UNKNOWN(" + i3 + ")");
        }
        if (z) {
            stringJoiner.add("IS_FORWARD_NAVIGATION");
        }
        return stringJoiner.setEmptyValue("(none)").toString();
    }

    public static String startInputFlagsToString(int i) {
        StringJoiner stringJoiner = new StringJoiner("|");
        if ((i & 1) != 0) {
            stringJoiner.add("VIEW_HAS_FOCUS");
        }
        if ((i & 2) != 0) {
            stringJoiner.add("IS_TEXT_EDITOR");
        }
        if ((i & 4) != 0) {
            stringJoiner.add("INITIAL_CONNECTION");
        }
        return stringJoiner.setEmptyValue("(none)").toString();
    }

    public static String softInputDisplayReasonToString(int i) {
        switch (i) {
            case 0:
                return "SHOW_SOFT_INPUT";
            case 1:
                return "ATTACH_NEW_INPUT";
            case 2:
                return "SHOW_MY_SOFT_INPUT";
            case 3:
                return "HIDE_SOFT_INPUT";
            case 4:
                return "HIDE_MY_SOFT_INPUT";
            case 5:
                return "SHOW_AUTO_EDITOR_FORWARD_NAV";
            case 6:
                return "SHOW_STATE_VISIBLE_FORWARD_NAV";
            case 7:
                return "SHOW_STATE_ALWAYS_VISIBLE";
            case 8:
                return "SHOW_SETTINGS_ON_CHANGE";
            case 9:
                return "HIDE_SWITCH_USER";
            case 10:
                return "HIDE_INVALID_USER";
            case 11:
                return "HIDE_UNSPECIFIED_WINDOW";
            case 12:
                return "HIDE_STATE_HIDDEN_FORWARD_NAV";
            case 13:
                return "HIDE_ALWAYS_HIDDEN_STATE";
            case 14:
                return "HIDE_RESET_SHELL_COMMAND";
            case 15:
                return "HIDE_SETTINGS_ON_CHANGE";
            case 16:
                return "HIDE_POWER_BUTTON_GO_HOME";
            case 17:
                return "HIDE_DOCKED_STACK_ATTACHED";
            case 18:
                return "HIDE_RECENTS_ANIMATION";
            case 19:
            default:
                return "Unknown=" + i;
            case 20:
                return "HIDE_SAME_WINDOW_FOCUSED_WITHOUT_EDITOR";
        }
    }

    public static String objToString(Object obj) {
        if (obj == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(64);
        sb.setLength(0);
        sb.append(obj.getClass().getName());
        sb.append("@");
        sb.append(Integer.toHexString(obj.hashCode()));
        return sb.toString();
    }
}
