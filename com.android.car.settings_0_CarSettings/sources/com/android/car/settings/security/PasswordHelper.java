package com.android.car.settings.security;

import android.app.admin.PasswordMetrics;
import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.internal.widget.LockscreenCredential;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PasswordHelper {
    static final int CONTAINS_INVALID_CHARACTERS = 1;
    static final int CONTAINS_NON_DIGITS = 4;
    static final int CONTAINS_SEQUENTIAL_DIGITS = 8;
    public static final String EXTRA_CURRENT_PASSWORD_QUALITY = "extra_current_password_quality";
    public static final String EXTRA_CURRENT_SCREEN_LOCK = "extra_current_screen_lock";
    private static final Logger LOG = new Logger((Class<?>) PasswordHelper.class);
    public static final int MIN_LENGTH = 4;
    static final int NO_ERROR = 0;
    static final int TOO_SHORT = 2;
    private final boolean mIsPin;

    public PasswordHelper(boolean isPin) {
        this.mIsPin = isPin;
    }

    public int getPasswordQuality() {
        return this.mIsPin ? 131072 : 327680;
    }

    public int validate(LockscreenCredential password) {
        return this.mIsPin ? validatePin(password.getCredential()) : validatePassword(password.getCredential());
    }

    public int validateSetupWizard(byte[] password) {
        return this.mIsPin ? translateSettingsToSuwError(validatePin(password)) : translateSettingsToSuwError(validatePassword(password));
    }

    public List<String> convertErrorCodeToMessages(Context context, int errorCode) {
        return this.mIsPin ? convertPinErrorCodeToMessages(context, errorCode) : convertPasswordErrorCodeToMessages(context, errorCode);
    }

    private int validatePassword(byte[] password) {
        int errorCode = 0;
        if (password.length < 4) {
            errorCode = 0 | 2;
        }
        for (byte b : password) {
            char c = (char) b;
            if (c < ' ' || c > 127) {
                return errorCode | 1;
            }
        }
        return errorCode;
    }

    private int translateSettingsToSuwError(int error) {
        int output = 0;
        if ((error & 4) > 0) {
            LOG.v("CONTAINS_NON_DIGITS");
            output = 0 | 2;
        }
        if ((error & 1) > 0) {
            LOG.v("INVALID_CHAR");
            output |= 2;
        }
        if ((error & 2) > 0) {
            LOG.v("TOO_SHORT");
            output |= 1;
        }
        if ((error & 8) > 0) {
            LOG.v("SEQUENTIAL_DIGITS");
            return output | 4;
        }
        return output;
    }

    private int validatePin(byte[] pin) {
        int errorCode = 0;
        PasswordMetrics metrics = PasswordMetrics.computeForPassword(pin);
        int passwordQuality = getPasswordQuality();
        if (metrics.length < 4) {
            errorCode = 0 | 2;
        }
        if (metrics.letters > 0 || metrics.symbols > 0) {
            errorCode |= 4;
        }
        if (passwordQuality == 196608) {
            int sequence = PasswordMetrics.maxLengthSequence(pin);
            if (sequence > 3) {
                return errorCode | 8;
            }
            return errorCode;
        }
        return errorCode;
    }

    private List<String> convertPasswordErrorCodeToMessages(Context context, int errorCode) {
        List<String> messages = new LinkedList<>();
        if ((errorCode & 1) > 0) {
            messages.add(context.getString(R.string.lockpassword_illegal_character));
        }
        if ((errorCode & 2) > 0) {
            messages.add(context.getString(R.string.lockpassword_password_too_short, 4));
        }
        return messages;
    }

    private List<String> convertPinErrorCodeToMessages(Context context, int errorCode) {
        List<String> messages = new LinkedList<>();
        if ((errorCode & 4) > 0) {
            messages.add(context.getString(R.string.lockpassword_pin_contains_non_digits));
        }
        if ((errorCode & 8) > 0) {
            messages.add(context.getString(R.string.lockpassword_pin_no_sequential_digits));
        }
        if ((errorCode & 2) > 0) {
            messages.add(context.getString(R.string.lockpin_invalid_pin));
        }
        return messages;
    }

    public static void zeroizeCredentials(LockscreenCredential... credentials) {
        for (LockscreenCredential credential : credentials) {
            if (credential != null) {
                credential.zeroize();
            }
        }
        System.gc();
        System.runFinalization();
        System.gc();
    }
}
