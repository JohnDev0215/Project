package com.byd.spi.service;

import android.content.Context;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public interface ServiceCreator extends Serializable {
    Object createService(Context context);

    String getInterfaceQualifiedName();

    String getServiceQualifiedName();
}
