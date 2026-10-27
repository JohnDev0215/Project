package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.common.WebResponseParser;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class d1 {
    public static final String c = "com.amazon.identity.auth.device.d1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AmazonAccountManager f274a;
    public Context b;

    public d1(Context context) {
        t5 t5VarA = t5.a(context);
        this.b = t5VarA;
        this.f274a = (AmazonAccountManager) t5VarA.getSystemService("dcp_amazon_account_man");
    }

    public void a(String str, ld ldVar, WebResponseParser webResponseParser, tb tbVar, Context context, String str2, y5 y5Var) {
        d3 d3VarA;
        synchronized (e3.class) {
            d3VarA = e3.a(context, str, str2);
        }
        tc tcVar = new tc(d3VarA);
        xc xcVar = new xc(webResponseParser);
        if (d3VarA != null && d3VarA.a(context)) {
            d3 d3VarB = e3.b(context, null, null);
            boolean z = tcVar.c;
            tcVar = new tc(d3VarB);
            tcVar.c = z;
            if (z) {
                Log.i(ga.a(tc.d), "Try to set useLegacyAuthentication to be true when algorithm is: " + tcVar.f585a);
                if (tcVar.b != null) {
                    tcVar.a();
                    if (!tcVar.f585a.equalsIgnoreCase("SHA256WithRSA")) {
                        throw new IllegalStateException("LegacyAuthentication is not compatible with algorithm:" + tcVar.f585a);
                    }
                }
            }
        }
        new o4(context, ldVar, new uc(xcVar, tbVar, ldVar.j), tcVar, y5Var).a();
    }

    public final void a(Callback callback, MAPError mAPError, String str, int i, String str2) {
        String str3;
        Log.e(ga.a(c), "Rename Device Error: " + str2);
        SSOMetrics.a();
        String[] strArr = new String[1];
        switch (i) {
            case 1:
                str3 = "NetworkFailure";
                break;
            case 2:
                str3 = "AuthenticationFailed";
                break;
            case 3:
                str3 = "InvalidInput";
                break;
            case 4:
                str3 = "ParseError";
                break;
            case 5:
                str3 = "NameAlreadyUsed";
                break;
            case 6:
                str3 = "NoAmazonAccount";
                break;
            default:
                str3 = "Unrecognized";
                break;
        }
        strArr[0] = str3;
        SSOMetrics.a("RenameDeviceRequestFailure", strArr);
        Bundle bundle = new Bundle();
        bundle.putInt("error_code_key", i);
        bundle.putString("error_message_key", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        callback.onError(bundle);
    }

    public final void a(String str, Callback callback, String str2, qc qcVar, tb tbVar, y5 y5Var) {
        String strC = z9.c(this.b, str);
        if (TextUtils.isEmpty(strC)) {
            a(callback, MAPError.AttributeError.CANNOT_DETERMINE_OVERRIDE_DEVICE_TYPE, "Unable to determine the override device type. The child device type is null", 8, "Cannot determine the override device type, child device type is null.");
            return;
        }
        new c8(this.b).c(str2, e.a(str, AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN));
        a(str2, qcVar.a(), new sc(), tbVar, this.b, strC, y5Var);
    }
}
