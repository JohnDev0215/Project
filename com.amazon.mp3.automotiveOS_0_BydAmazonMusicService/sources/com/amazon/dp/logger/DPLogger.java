package com.amazon.dp.logger;

import android.os.Build;
import android.util.Log;
import com.amazon.dp.logger.DPLoggerBase;

/* JADX INFO: loaded from: classes2.dex */
public class DPLogger extends DPLoggerBase {
    private static final String BUILD_TYPE = Build.TYPE;
    protected final String mName;

    public DPLogger() {
        this.mName = ensureSafeName(removePackageName(new Throwable().getStackTrace()[1].getClassName()));
    }

    public DPLogger(String str) {
        this.mName = ensureSafeName(str);
    }

    /* JADX INFO: renamed from: com.amazon.dp.logger.DPLogger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel;

        static {
            int[] iArr = new int[DPLoggerBase.DPLevel.values().length];
            $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel = iArr;
            try {
                iArr[DPLoggerBase.DPLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[DPLoggerBase.DPLevel.FATAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.amazon.dp.logger.DPLoggerBase
    protected boolean isEnabled(DPLoggerBase.DPLevel dPLevel) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[dPLevel.ordinal()];
        int i2 = 5;
        if (i == 1) {
            i2 = 3;
        } else if (i == 2) {
            i2 = 4;
        } else if (i != 3) {
            i2 = i != 4 ? i != 5 ? 2 : 7 : 6;
        }
        return Log.isLoggable(this.mName, i2) || "eng".equals(BUILD_TYPE);
    }

    @Override // com.amazon.dp.logger.DPLoggerBase
    protected void log(DPLoggerBase.DPLevel dPLevel, DPFormattedMessage dPFormattedMessage) {
        Throwable throwable = dPFormattedMessage.getThrowable();
        int i = AnonymousClass1.$SwitchMap$com$amazon$dp$logger$DPLoggerBase$DPLevel[dPLevel.ordinal()];
        if (i == 1) {
            if (throwable == null) {
                Log.d(this.mName, dPFormattedMessage.toString());
                return;
            } else {
                Log.d(this.mName, dPFormattedMessage.toString(), throwable);
                return;
            }
        }
        if (i == 2) {
            if (throwable == null) {
                Log.i(this.mName, dPFormattedMessage.toString());
                return;
            } else {
                Log.i(this.mName, dPFormattedMessage.toString(), throwable);
                return;
            }
        }
        if (i == 3) {
            if (throwable == null) {
                Log.w(this.mName, dPFormattedMessage.toString());
                return;
            } else {
                Log.w(this.mName, dPFormattedMessage.toString(), throwable);
                return;
            }
        }
        if (i == 4) {
            if (throwable == null) {
                Log.e(this.mName, dPFormattedMessage.toString());
                return;
            } else {
                Log.e(this.mName, dPFormattedMessage.toString(), throwable);
                return;
            }
        }
        if (i != 5) {
            if (throwable == null) {
                Log.v(this.mName, dPFormattedMessage.toString());
                return;
            } else {
                Log.v(this.mName, dPFormattedMessage.toString(), throwable);
                return;
            }
        }
        if (throwable == null) {
            Log.wtf(this.mName, dPFormattedMessage.toString());
        } else {
            Log.wtf(this.mName, dPFormattedMessage.toString(), throwable);
        }
    }

    private static String removePackageName(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || iLastIndexOf > str.length() + (-1)) ? str : str.substring(iLastIndexOf + 1);
    }

    private String ensureSafeName(String str) {
        if (str == null) {
            Log.w("DPLogger", "Got a null DPLogger name; using an empty string instead");
            return "";
        }
        if (str.length() <= 23) {
            return str;
        }
        String strSubstring = str.substring(0, 23);
        Log.i("DPLogger", DPFormattedMessage.toDPFormat("DPLogger", "Name was too long. Truncating", "original name", str, "truncated name", strSubstring));
        return strSubstring;
    }
}
