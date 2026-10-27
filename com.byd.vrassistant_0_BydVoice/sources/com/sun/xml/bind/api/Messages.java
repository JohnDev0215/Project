package com.sun.xml.bind.api;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
enum Messages {
    ARGUMENT_CANT_BE_NULL;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getName());

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... objArr) {
        return MessageFormat.format(rb.getString(name()), objArr);
    }
}
