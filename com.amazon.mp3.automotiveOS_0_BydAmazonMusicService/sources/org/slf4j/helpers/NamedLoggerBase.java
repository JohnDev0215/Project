package org.slf4j.helpers;

import java.io.Serializable;
import org.slf4j.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes6.dex */
public abstract class NamedLoggerBase implements Logger, Serializable {
    protected String name;

    NamedLoggerBase() {
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.name;
    }
}
