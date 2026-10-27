package com.byd.dilink.safekeyservice;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkSafeKeyManager {
    ExecResult asymmetricDe(Context context, int i, String str, int i2);

    ExecResult asymmetricSign(Context context, int i, String str, int i2);

    int asymmetricVerify(Context context, int i, String str, int i2, String str2);

    ExecResult getDataFromChip(Context context, int i, int i2);
}
