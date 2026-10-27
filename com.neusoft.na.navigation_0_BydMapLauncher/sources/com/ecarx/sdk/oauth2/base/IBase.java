package com.ecarx.sdk.oauth2.base;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public interface IBase {
    boolean checkArgs();

    void fromBundle(Bundle bundle);

    int getType();

    void toBundle(Bundle bundle);
}
