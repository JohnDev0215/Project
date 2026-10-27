package org.mockito.internal.util;

/* JADX INFO: loaded from: classes4.dex */
public class ConsoleMockitoLogger implements MockitoLogger {
    @Override // org.mockito.internal.util.MockitoLogger
    public void log(Object obj) {
        System.out.println(obj);
    }
}
