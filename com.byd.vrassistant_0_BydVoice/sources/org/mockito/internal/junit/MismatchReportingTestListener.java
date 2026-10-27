package org.mockito.internal.junit;

import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.junit.util.TestName;
import org.mockito.internal.util.MockitoLogger;
import org.mockito.mock.MockCreationSettings;

/* JADX INFO: loaded from: classes4.dex */
public class MismatchReportingTestListener implements MockitoTestListener {
    private final MockitoLogger logger;
    private List<Object> mocks = new LinkedList();

    public MismatchReportingTestListener(MockitoLogger mockitoLogger) {
        this.logger = mockitoLogger;
    }

    @Override // org.mockito.internal.junit.MockitoTestListener
    public void testFinished(TestFinishedEvent testFinishedEvent) {
        List<Object> list = this.mocks;
        this.mocks = new LinkedList();
        String testName = TestName.getTestName(testFinishedEvent);
        if (testFinishedEvent.getFailure() != null) {
            new ArgMismatchFinder().getStubbingArgMismatches(list).format(testName, this.logger);
        }
    }

    @Override // org.mockito.listeners.MockCreationListener
    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
        this.mocks.add(obj);
    }
}
