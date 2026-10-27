package com.amazon.ion.system;

import com.amazon.ion.IonWriter;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IonWriterBuilder {

    public enum InitialIvmHandling {
        ENSURE,
        SUPPRESS
    }

    public enum IvmMinimizing {
        ADJACENT,
        DISTANT
    }

    public abstract IonWriter build(OutputStream outputStream);

    IonWriterBuilder() {
    }
}
