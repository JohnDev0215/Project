package com.android.phone.euicc;

/* JADX INFO: loaded from: classes.dex */
public class EuiccPrivilegedActionUiDispatcherActivity extends EuiccUiDispatcherActivity {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    @Override // com.android.phone.euicc.EuiccUiDispatcherActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected android.content.Intent getEuiccUiIntent() {
        /*
            r5 = this;
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r0 = r0.getAction()
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            android.content.Intent r5 = r5.getIntent()
            r1.putExtras(r5)
            int r5 = r0.hashCode()
            r2 = -1864574078(0xffffffff90dcdb82, float:-8.7112905E-29)
            r3 = 2
            r4 = 1
            if (r5 == r2) goto L3e
            r2 = -1744899345(0xffffffff97fef2ef, float:-1.647569E-24)
            if (r5 == r2) goto L34
            r2 = -274006632(0xffffffffefaafd98, float:-1.0583806E29)
            if (r5 == r2) goto L2a
            goto L48
        L2a:
            java.lang.String r5 = "android.telephony.euicc.action.TOGGLE_SUBSCRIPTION_PRIVILEGED"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L48
            r5 = 0
            goto L49
        L34:
            java.lang.String r5 = "android.telephony.euicc.action.DELETE_SUBSCRIPTION_PRIVILEGED"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L48
            r5 = r4
            goto L49
        L3e:
            java.lang.String r5 = "android.telephony.euicc.action.RENAME_SUBSCRIPTION_PRIVILEGED"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L48
            r5 = r3
            goto L49
        L48:
            r5 = -1
        L49:
            if (r5 == 0) goto L73
            if (r5 == r4) goto L6d
            if (r5 == r3) goto L67
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "Unsupported action: "
            r5.append(r1)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "EuiccPrivUiDispatcher"
            android.util.Log.w(r0, r5)
            r5 = 0
            return r5
        L67:
            java.lang.String r5 = "android.service.euicc.action.RENAME_SUBSCRIPTION_PRIVILEGED"
            r1.setAction(r5)
            goto L78
        L6d:
            java.lang.String r5 = "android.service.euicc.action.DELETE_SUBSCRIPTION_PRIVILEGED"
            r1.setAction(r5)
            goto L78
        L73:
            java.lang.String r5 = "android.service.euicc.action.TOGGLE_SUBSCRIPTION_PRIVILEGED"
            r1.setAction(r5)
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.euicc.EuiccPrivilegedActionUiDispatcherActivity.getEuiccUiIntent():android.content.Intent");
    }
}
