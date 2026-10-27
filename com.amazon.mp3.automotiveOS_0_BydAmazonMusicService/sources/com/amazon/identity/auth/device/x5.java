package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"MissingPermission"})
public class x5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TelephonyManager f657a;

    public x5(t5 t5Var) {
        this.f657a = (TelephonyManager) t5Var.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
    }
}
