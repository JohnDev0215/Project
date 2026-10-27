package org.mockito.internal.exceptions.stacktrace;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;
import org.mockito.plugins.StackTraceCleanerProvider;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultStackTraceCleanerProvider implements StackTraceCleanerProvider {
    @Override // org.mockito.plugins.StackTraceCleanerProvider
    public StackTraceCleaner getStackTraceCleaner(StackTraceCleaner stackTraceCleaner) {
        return stackTraceCleaner;
    }
}
