package com.neusoft.dlt.service;

import java.nio.channels.Selector;

/* JADX INFO: loaded from: classes.dex */
public interface ILogSink {
    void logMessage(DltMessage dltMessage);

    void register(Selector selector);

    boolean start();

    boolean stop();
}
