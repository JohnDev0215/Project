package com.amazon.mp3.capability;

import com.amazon.mp3.util.IoUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class DevModeCapabilities {
    private static Boolean sStoreDebugPropSet;

    public boolean isHackToCustomCirrusEnabled() {
        return false;
    }

    public boolean isStoreDebugModeEnabled() {
        checkStoreDebugEnabled();
        return sStoreDebugPropSet.booleanValue();
    }

    public boolean isDeveloperToolEnabled() {
        checkStoreDebugEnabled();
        return sStoreDebugPropSet.booleanValue();
    }

    private void checkStoreDebugEnabled() {
        if (sStoreDebugPropSet == null) {
            Boolean boolValueOf = Boolean.valueOf("1".equals(getSystemProp("com.amazon.mp3.html5.debug")));
            sStoreDebugPropSet = boolValueOf;
            if (boolValueOf.booleanValue()) {
                return;
            }
            sStoreDebugPropSet = Boolean.valueOf(new File("/data/local/tmp/com.amazon.mp3.html5.debug").exists());
        }
    }

    private static String getSystemProp(String key) throws Throwable {
        Throwable th;
        InputStream inputStream;
        try {
            inputStream = Runtime.getRuntime().exec("getprop " + key).getInputStream();
        } catch (IOException unused) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            String strReplaceAll = IoUtil.streamToString(inputStream).trim().replaceAll("\n", "");
            IoUtil.close(inputStream);
            return strReplaceAll;
        } catch (IOException unused2) {
            IoUtil.close(inputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            IoUtil.close(inputStream);
            throw th;
        }
    }
}
