package com.sun.xml.bind;

import javax.xml.bind.Marshaller;

/* JADX INFO: loaded from: classes3.dex */
public interface CycleRecoverable {

    public interface Context {
        Marshaller getMarshaller();
    }

    Object onCycleDetected(Context context);
}
