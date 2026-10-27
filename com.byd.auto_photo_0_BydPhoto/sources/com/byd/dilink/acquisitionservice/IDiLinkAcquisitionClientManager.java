package com.byd.dilink.acquisitionservice;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAcquisitionClientManager {
    IDiLinkAcquisitionClient create(Context context, String str);

    IDiLinkAcquisitionClient create(Context context, String str, String str2);
}
