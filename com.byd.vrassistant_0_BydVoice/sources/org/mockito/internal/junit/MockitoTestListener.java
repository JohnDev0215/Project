package org.mockito.internal.junit;

import org.mockito.listeners.MockCreationListener;

/* JADX INFO: loaded from: classes4.dex */
public interface MockitoTestListener extends MockCreationListener {
    void testFinished(TestFinishedEvent testFinishedEvent);
}
