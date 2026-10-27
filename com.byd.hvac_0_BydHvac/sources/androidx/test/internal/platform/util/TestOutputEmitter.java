package androidx.test.internal.platform.util;

import androidx.test.internal.platform.ServiceLoaderWrapper;
import java.io.Serializable;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class TestOutputEmitter {
    private static final TestOutputHandler debugHandler = (TestOutputHandler) ServiceLoaderWrapper.loadSingleService(TestOutputHandler.class, TestOutputEmitter$$Lambda$0.$instance);

    private TestOutputEmitter() {
    }

    public static boolean addOutputProperties(Map<String, Serializable> map) {
        return debugHandler.addOutputProperties(map);
    }

    public static boolean captureWindowHierarchy(String str) {
        return debugHandler.captureWindowHierarchy(str);
    }

    public static void dumpThreadStates(String str) {
        debugHandler.dumpThreadStates(str);
    }

    public static final /* synthetic */ TestOutputHandler lambda$static$0$TestOutputEmitter() {
        return new TestOutputHandler() { // from class: androidx.test.internal.platform.util.TestOutputEmitter.1
            @Override // androidx.test.internal.platform.util.TestOutputHandler
            public boolean addOutputProperties(Map<String, Serializable> map) {
                return false;
            }

            @Override // androidx.test.internal.platform.util.TestOutputHandler
            public boolean captureWindowHierarchy(String str) {
                return false;
            }

            @Override // androidx.test.internal.platform.util.TestOutputHandler
            public void dumpThreadStates(String str) {
            }

            @Override // androidx.test.internal.platform.util.TestOutputHandler
            public boolean takeScreenshot(String str) {
                return false;
            }
        };
    }

    public static boolean takeScreenshot(String str) {
        return debugHandler.takeScreenshot(str);
    }
}
