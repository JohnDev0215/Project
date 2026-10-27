package net.bytebuddy.agent;

import java.lang.instrument.Instrumentation;

/* JADX INFO: loaded from: classes3.dex */
public class Installer {
    private static volatile Instrumentation instrumentation;

    private Installer() {
        throw new UnsupportedOperationException();
    }

    public static Instrumentation getInstrumentation() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("getInstrumentation"));
        }
        Instrumentation instrumentation2 = instrumentation;
        if (instrumentation2 != null) {
            return instrumentation2;
        }
        throw new IllegalStateException("The Byte Buddy agent is not loaded or this method is not called via the system class loader");
    }

    public static void premain(String str, Instrumentation instrumentation2) {
        instrumentation = instrumentation2;
    }

    public static void agentmain(String str, Instrumentation instrumentation2) {
        instrumentation = instrumentation2;
    }
}
