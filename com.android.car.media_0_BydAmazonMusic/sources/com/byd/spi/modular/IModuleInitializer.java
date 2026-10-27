package com.byd.spi.modular;

/* JADX INFO: loaded from: classes2.dex */
public interface IModuleInitializer {
    String getModuleName();

    void initialize(Object obj);

    boolean isInitialized();
}
