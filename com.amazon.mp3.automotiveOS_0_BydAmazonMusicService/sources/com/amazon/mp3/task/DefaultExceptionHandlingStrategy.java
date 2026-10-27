package com.amazon.mp3.task;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultExceptionHandlingStrategy implements ExceptionHandlingStrategy {
    @Override // com.amazon.mp3.task.ExceptionHandlingStrategy
    public boolean retryOnException(Throwable t) {
        return true;
    }
}
