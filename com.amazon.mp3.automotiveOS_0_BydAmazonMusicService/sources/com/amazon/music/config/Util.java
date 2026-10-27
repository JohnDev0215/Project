package com.amazon.music.config;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import androidx.core.os.EnvironmentCompat;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class Util {
    private static TelephonyManager sTelephonyManager;
    private static final Logger LOG = LoggerFactory.getLogger(Util.class.getSimpleName());
    private static final Pattern CHARACTERS_TO_REPLACE = Pattern.compile("[\\W_]+");
    public static final Pattern SPLIT_CSV = Pattern.compile("\\s*,\\s*");
    private static final Random sRandom = new Random();

    public static String normalizeId(String str) {
        String strReplaceAll = CHARACTERS_TO_REPLACE.matcher(str).replaceAll("");
        int length = strReplaceAll.length();
        if (length > 50) {
            strReplaceAll = strReplaceAll.substring(length - 50, length);
        }
        return strReplaceAll.toUpperCase(Locale.ROOT);
    }

    public static void randomSleepThread(long j, long j2) {
        try {
            Thread.sleep(j + Math.abs(((long) sRandom.nextInt()) % (j2 - j)));
        } catch (InterruptedException unused) {
        }
    }

    public static synchronized String getCarrierName(Context context) {
        if (sTelephonyManager == null) {
            if (context != null) {
                sTelephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            } else {
                LOG.error("Unknown carrier");
                return EnvironmentCompat.MEDIA_UNKNOWN;
            }
        }
        String simOperatorName = sTelephonyManager.getSimOperatorName();
        if (simOperatorName == null || simOperatorName.length() == 0) {
            simOperatorName = sTelephonyManager.getNetworkOperatorName();
        }
        if (simOperatorName == null || simOperatorName.length() == 0) {
            simOperatorName = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return simOperatorName;
    }

    public static boolean saveToFile(String str, File file) throws Throwable {
        createDirectory(file.getAbsolutePath());
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file), ParserUtil.UTF_8);
                try {
                    outputStreamWriter2.write(str);
                    try {
                        outputStreamWriter2.close();
                        return true;
                    } catch (IOException e) {
                        LOG.warn("Failed to close stream while saving", (Throwable) e);
                        return true;
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    outputStreamWriter = outputStreamWriter2;
                    LOG.warn("Failed to save file", (Throwable) e);
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e3) {
                            LOG.warn("Failed to close stream while saving", (Throwable) e3);
                        }
                    }
                    return false;
                } catch (IOException e4) {
                    e = e4;
                    outputStreamWriter = outputStreamWriter2;
                    LOG.warn("Failed to save file", (Throwable) e);
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e5) {
                            LOG.warn("Failed to close stream while saving", (Throwable) e5);
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e6) {
                            LOG.warn("Failed to close stream while saving", (Throwable) e6);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e = e7;
            } catch (IOException e8) {
                e = e8;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean createDirectory(String str) {
        if (str == null) {
            return false;
        }
        if (!str.endsWith("/")) {
            str = str.substring(0, str.lastIndexOf(47) + 1);
        }
        if (str.length() == 0) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return false;
        }
        return file.mkdirs();
    }
}
