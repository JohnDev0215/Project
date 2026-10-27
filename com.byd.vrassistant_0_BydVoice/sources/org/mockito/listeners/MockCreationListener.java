package org.mockito.listeners;

import org.mockito.mock.MockCreationSettings;

/* JADX INFO: loaded from: classes4.dex */
public interface MockCreationListener extends MockitoListener {
    void onMockCreated(Object obj, MockCreationSettings mockCreationSettings);
}
