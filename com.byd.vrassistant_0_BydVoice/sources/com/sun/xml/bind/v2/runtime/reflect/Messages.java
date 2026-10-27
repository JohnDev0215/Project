package com.sun.xml.bind.v2.runtime.reflect;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
enum Messages {
    UNABLE_TO_ACCESS_NON_PUBLIC_FIELD,
    UNASSIGNABLE_TYPE,
    NO_SETTER,
    NO_GETTER;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getName());

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... objArr) {
        return MessageFormat.format(rb.getString(name()), objArr);
    }
}
