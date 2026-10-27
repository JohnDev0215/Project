package org.mockito.internal.creation.bytebuddy;

import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

/* JADX INFO: loaded from: classes4.dex */
interface ClassCreatingMockMaker extends MockMaker {
    <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings);
}
