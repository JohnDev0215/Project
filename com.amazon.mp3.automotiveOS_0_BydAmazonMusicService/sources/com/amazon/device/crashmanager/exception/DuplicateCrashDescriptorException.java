package com.amazon.device.crashmanager.exception;

/* JADX INFO: loaded from: classes2.dex */
public class DuplicateCrashDescriptorException extends Exception {
    private static final long serialVersionUID = 1;

    public DuplicateCrashDescriptorException(String str, Throwable th) {
        super(str, th);
    }

    public DuplicateCrashDescriptorException(String str) {
        super(str);
    }
}
