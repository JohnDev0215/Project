package org.mockito.internal.creation.bytebuddy;

/* JADX INFO: loaded from: classes4.dex */
public interface BytecodeGenerator {
    <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures);
}
