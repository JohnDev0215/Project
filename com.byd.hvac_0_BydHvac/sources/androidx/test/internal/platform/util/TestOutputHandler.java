package androidx.test.internal.platform.util;

import java.io.Serializable;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface TestOutputHandler {
    boolean addOutputProperties(Map<String, Serializable> map);

    boolean captureWindowHierarchy(String str);

    void dumpThreadStates(String str);

    boolean takeScreenshot(String str);
}
