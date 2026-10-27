package com.sun.xml.bind;

import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public final class Util {
    private Util() {
    }

    public static Logger getClassLogger() {
        try {
            return Logger.getLogger(new Exception().getStackTrace()[1].getClassName());
        } catch (SecurityException unused) {
            return Logger.getLogger("com.sun.xml.bind");
        }
    }

    public static String getSystemProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }
}
