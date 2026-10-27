package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public class ReadOnlyValueException extends IonException {
    public ReadOnlyValueException() {
        super("Read-only IonValue cannot be modified");
    }

    public ReadOnlyValueException(Class cls) {
        super("Cannot modify read-only instance of " + cls);
    }
}
