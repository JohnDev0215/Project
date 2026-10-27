package org.mockito.plugins;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;

/* JADX INFO: loaded from: classes4.dex */
public interface StackTraceCleanerProvider {
    StackTraceCleaner getStackTraceCleaner(StackTraceCleaner stackTraceCleaner);
}
