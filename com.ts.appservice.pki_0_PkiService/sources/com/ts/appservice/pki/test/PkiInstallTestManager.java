package com.ts.appservice.pki.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import com.ts.appservice.pki.c.c;
import com.ts.appservice.pki.c.d;
import com.ts.appservice.pki.d.b;
import java.util.ArrayList;
import java.util.HashMap;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class PkiInstallTestManager {
    private static final String ACTION_TEST = "com.ts.appservice.pki.test";
    private static final String ECC = "Ecc256P10.p10";
    private static final int HAS_HSM = 1;
    private static final String RSA = "Rsa2048P10.p10";
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_PkiInstallTestManager";
    private static final String TAG = "PkiInstallTestManager";
    private static PkiInstallTestManager sInstance;
    private Context mContext;
    private int mForTest;
    private c mHsmManager;
    private d mPkiDataManager;
    private HashMap<String, ArrayList<Byte>> mTestData;
    private a mTestReceiver;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("method");
            b.b(PkiInstallTestManager.TAG, " action " + action + " method " + stringExtra);
            int intExtra = intent.getIntExtra("keyId", -1);
            int intExtra2 = intent.getIntExtra("hasHsm", 0);
            String stringExtra2 = intent.getStringExtra("path");
            int intExtra3 = intent.getIntExtra("keyType", -1);
            int intExtra4 = intent.getIntExtra("certId", -1);
            intent.getStringExtra("data");
            if (PkiInstallTestManager.ACTION_TEST.equals(action)) {
                com.ts.appservice.pki.c.a aVar = intExtra2 == 1 ? PkiInstallTestManager.this.mHsmManager : PkiInstallTestManager.this.mPkiDataManager;
                b.b(PkiInstallTestManager.TAG, " deviceHasHsm " + aVar.x());
                PkiInstallTestManager.this.isRsa2048(stringExtra, stringExtra2, aVar);
                PkiInstallTestManager.this.isEcc256(stringExtra, stringExtra2, aVar);
                PkiInstallTestManager.this.isAesKey(stringExtra, stringExtra2, aVar);
                PkiInstallTestManager.this.isAesAndGongChengYuan(stringExtra, stringExtra2, aVar);
            }
            if (!PkiInstallTestManager.this.isReadData(stringExtra, intExtra) && !PkiInstallTestManager.this.isReadCert(stringExtra, intExtra3, intExtra4) && !PkiInstallTestManager.this.isReadHsmCert(stringExtra, intExtra3, intExtra4) && PkiInstallTestManager.this.isReadHsmData(stringExtra, intExtra)) {
            }
        }
    }

    PkiInstallTestManager() {
    }

    public static synchronized PkiInstallTestManager getInstance() {
        if (sInstance == null) {
            sInstance = new PkiInstallTestManager();
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isAesAndGongChengYuan(java.lang.String r7, java.lang.String r8, com.ts.appservice.pki.c.a r9) {
        /*
            r6 = this;
            int r6 = r7.hashCode()
            r0 = 0
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r6) {
                case -2008454817: goto L40;
                case -379306065: goto L36;
                case 264866676: goto L2c;
                case 582722333: goto L22;
                case 874651496: goto L18;
                case 884284710: goto Le;
                default: goto Ld;
            }
        Ld:
            goto L4a
        Le:
            java.lang.String r6 = "checkDeviceRsaCert"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4a
            r6 = r5
            goto L4b
        L18:
            java.lang.String r6 = "writeEngineerPublicKey"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4a
            r6 = r2
            goto L4b
        L22:
            java.lang.String r6 = "checkDeviceRsaKey"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4a
            r6 = r3
            goto L4b
        L2c:
            java.lang.String r6 = "writeDeviceRsaKey"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4a
            r6 = r4
            goto L4b
        L36:
            java.lang.String r6 = "writeDeviceRsaCert"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4a
            r6 = r0
            goto L4b
        L40:
            java.lang.String r6 = "checkEngineerPublicKey"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L4a
            r6 = r1
            goto L4b
        L4a:
            r6 = -1
        L4b:
            if (r6 == 0) goto L74
            if (r6 == r5) goto L70
            if (r6 == r4) goto L68
            if (r6 == r3) goto L64
            if (r6 == r2) goto L5c
            if (r6 == r1) goto L58
            goto L7c
        L58:
            r9.h()
            goto L7b
        L5c:
            java.util.ArrayList r6 = com.ts.appservice.pki.d.a.f(r8)
            r9.O(r6)
            goto L7b
        L64:
            r9.f()
            goto L7b
        L68:
            java.util.ArrayList r6 = com.ts.appservice.pki.d.a.f(r8)
            r9.N(r6)
            goto L7b
        L70:
            r9.e()
            goto L7b
        L74:
            java.util.ArrayList r6 = com.ts.appservice.pki.d.a.f(r8)
            r9.M(r6)
        L7b:
            r0 = r5
        L7c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.test.PkiInstallTestManager.isAesAndGongChengYuan(java.lang.String, java.lang.String, com.ts.appservice.pki.c.a):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isAesKey(java.lang.String r4, java.lang.String r5, com.ts.appservice.pki.c.a r6) {
        /*
            r3 = this;
            int r3 = r4.hashCode()
            r0 = 239448632(0xe45b238, float:2.4367934E-30)
            r1 = 0
            r2 = 1
            if (r3 == r0) goto L1b
            r0 = 883058639(0x34a267cf, float:3.0250382E-7)
            if (r3 == r0) goto L11
            goto L25
        L11:
            java.lang.String r3 = "writeAesKey"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L25
            r3 = r1
            goto L26
        L1b:
            java.lang.String r3 = "checkAesKey"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L25
            r3 = r2
            goto L26
        L25:
            r3 = -1
        L26:
            if (r3 == 0) goto L2f
            if (r3 == r2) goto L2b
            goto L6e
        L2b:
            r6.a()
            goto L6d
        L2f:
            java.util.ArrayList r3 = com.ts.appservice.pki.d.a.f(r5)
            java.lang.String r4 = new java.lang.String
            byte[] r3 = com.ts.appservice.pki.d.a.b(r3)
            r4.<init>(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = " aesKeyData "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "PkiInstallTestManager"
            com.ts.appservice.pki.d.b.b(r5, r3)
            java.util.ArrayList r3 = com.ts.appservice.pki.d.a.c(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = " aesKeyDataBytes "
            r4.append(r0)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.ts.appservice.pki.d.b.b(r5, r4)
            r6.J(r3)
        L6d:
            r1 = r2
        L6e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.test.PkiInstallTestManager.isAesKey(java.lang.String, java.lang.String, com.ts.appservice.pki.c.a):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isEcc256(java.lang.String r3, java.lang.String r4, com.ts.appservice.pki.c.a r5) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.test.PkiInstallTestManager.isEcc256(java.lang.String, java.lang.String, com.ts.appservice.pki.c.a):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReadCert(String str, int i, int i2) {
        if (!"readCert".equals(str)) {
            return false;
        }
        ArrayList<Byte> arrayListA = this.mPkiDataManager.A(i, i2);
        b.a("isReadCert bytes " + arrayListA);
        if (arrayListA == null) {
            return true;
        }
        b.b(TAG, " isReadCert " + new String(com.ts.appservice.pki.d.a.b(arrayListA)));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReadData(String str, int i) {
        if (!"readData".equals(str)) {
            return false;
        }
        ArrayList<Byte> arrayListJ0 = this.mPkiDataManager.j0(i);
        b.a("isReadData bytes " + arrayListJ0);
        if (arrayListJ0 == null) {
            return true;
        }
        b.b(TAG, " isReadData " + new String(com.ts.appservice.pki.d.a.b(arrayListJ0)));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReadHsmCert(String str, int i, int i2) {
        if (!"readHsmCert".equals(str)) {
            return false;
        }
        b.b(TAG, " keyType " + i + " certId " + i2);
        if (i2 == 98) {
            ArrayList<Byte> arrayListA = this.mHsmManager.A(0, i2);
            b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListA)));
            com.ts.appservice.pki.d.a.h(arrayListA, "/data/pki_data/rsa2048_device_cert.cer");
            return true;
        }
        if (i2 == 101) {
            ArrayList<Byte> arrayListA2 = this.mHsmManager.A(2, i2);
            b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListA2)));
            com.ts.appservice.pki.d.a.h(arrayListA2, "/data/pki_data/ecc256_device_cert.cer");
            return true;
        }
        switch (i2) {
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                ArrayList<Byte> arrayListA3 = this.mHsmManager.A(0, i2);
                b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListA3)));
                com.ts.appservice.pki.d.a.h(arrayListA3, "/data/pki_data/rsa2048_root_cert.cer");
                return true;
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                ArrayList<Byte> arrayListA4 = this.mHsmManager.A(0, i2);
                b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListA4)));
                com.ts.appservice.pki.d.a.h(arrayListA4, "/data/pki_data/rsa2048_identity_cert.cer");
                return true;
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /* 172 */:
                ArrayList<Byte> arrayListA5 = this.mHsmManager.A(2, i2);
                b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListA5)));
                com.ts.appservice.pki.d.a.h(arrayListA5, "/data/pki_data/ecc256_root_cert.cer");
                return true;
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                ArrayList<Byte> arrayListA6 = this.mHsmManager.A(2, i2);
                b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListA6)));
                com.ts.appservice.pki.d.a.h(arrayListA6, "/data/pki_data/ecc256_identity_cert.cer");
                return true;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReadHsmData(String str, int i) {
        if (!"isReadHsmData".equals(str)) {
            return false;
        }
        ArrayList<Byte> arrayListC0 = this.mHsmManager.c0(i);
        b.b(TAG, new String(com.ts.appservice.pki.d.a.b(arrayListC0)));
        if (i != 189) {
            return true;
        }
        com.ts.appservice.pki.d.a.h(arrayListC0, "/data/pki_data/engineering_public_key_id.txt");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isRsa2048(java.lang.String r3, java.lang.String r4, com.ts.appservice.pki.c.a r5) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.test.PkiInstallTestManager.isRsa2048(java.lang.String, java.lang.String, com.ts.appservice.pki.c.a):boolean");
    }

    public void init(Context context, d dVar, c cVar) {
        int i = Settings.System.getInt(context.getContentResolver(), S_KEY_FOR_TEST, 0);
        this.mForTest = i;
        if (i == 0) {
            b.b(TAG, "init mForTestValue is " + this.mForTest + " execute return");
            return;
        }
        this.mTestReceiver = new a();
        this.mContext = context.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TEST);
        this.mContext.registerReceiver(this.mTestReceiver, intentFilter);
        this.mPkiDataManager = dVar;
        this.mHsmManager = cVar;
        this.mTestData = new HashMap<>();
    }

    public void onDestroy() {
        if (this.mForTest != 0) {
            a aVar = this.mTestReceiver;
            if (aVar != null) {
                this.mContext.unregisterReceiver(aVar);
                return;
            }
            return;
        }
        b.b(TAG, "init mForTestValue is " + this.mForTest + " execute return");
    }

    public ArrayList<Byte> readData(int i) {
        if (this.mForTest == 0) {
            b.b(TAG, "saveData mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        ArrayList<Byte> arrayList = this.mTestData.get(String.valueOf(i));
        StringBuilder sb = new StringBuilder();
        sb.append("readData from mTestData ，keyId ");
        sb.append(i);
        sb.append(" data is ");
        sb.append(arrayList == null ? "" : "not ");
        sb.append("null");
        b.b(TAG, sb.toString());
        return arrayList;
    }

    public boolean saveData(int i, ArrayList<Byte> arrayList) {
        if (this.mForTest == 0) {
            b.b(TAG, "saveData mForTestValue is " + this.mForTest + " execute return");
            return false;
        }
        HashMap<String, ArrayList<Byte>> map = this.mTestData;
        if (map == null) {
            return false;
        }
        map.put(String.valueOf(i), arrayList);
        b.b(TAG, "saveData to  mTestData success , keyId " + i);
        return true;
    }
}
