package org.mockito.internal.runners.util;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* JADX INFO: loaded from: classes4.dex */
public class FailureDetector extends RunListener {
    private boolean failed;

    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        this.failed = true;
    }

    public boolean isSuccessful() {
        return !this.failed;
    }
}
