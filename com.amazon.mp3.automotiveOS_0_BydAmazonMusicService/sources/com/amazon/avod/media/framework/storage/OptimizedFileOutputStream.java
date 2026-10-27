package com.amazon.avod.media.framework.storage;

import com.amazon.avod.util.DLog;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class OptimizedFileOutputStream extends FileOutputStream {
    private static final Method FLUSH_BEHIND_METHOD = getFlushBehindMethod();

    @SuppressFBWarnings(justification = "Wrong bug, we don't actually open any stream here.", value = {"OBL_UNSATISFIED_OBLIGATION"})
    public OptimizedFileOutputStream(File file) throws FileNotFoundException {
        super(file);
        optionallyEnableOptimization();
    }

    private void optionallyEnableOptimization() {
        Method method = FLUSH_BEHIND_METHOD;
        if (method != null) {
            try {
                method.invoke(this, Boolean.TRUE);
            } catch (Exception e) {
                DLog.exceptionf(e);
            }
        }
    }

    private static Method getFlushBehindMethod() {
        Method method;
        try {
            method = FileOutputStream.class.getMethod("setFlushBehind", Boolean.TYPE);
        } catch (Exception unused) {
            method = null;
        }
        if (method != null) {
            DLog.logf("setFlushBehind available for FileOutputStream");
        } else {
            DLog.logf("setFlushBehind not available for FileOutputStream");
        }
        return method;
    }
}
