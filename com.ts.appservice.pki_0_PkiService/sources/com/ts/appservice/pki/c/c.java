package com.ts.appservice.pki.c;

import android.text.TextUtils;
import android.util.Base64;
import com.byd.pki.filling.BydPKIUtils;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.eac.CertificateBody;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.crypto.tls.ExtensionType;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    private boolean g;
    private ArrayList<Byte> i;
    private ArrayList<Byte> j;
    private ArrayList<Byte> k;
    private ArrayList<Byte> l;
    private ArrayList<Byte> m;
    private ArrayList<Byte> n;
    private ArrayList<Byte> o;
    private ArrayList<Byte> p;
    private b.a.a.a.a q;
    private ArrayList<Byte> r;
    ArrayList<Byte> s;
    private boolean e = false;
    private ArrayList<Byte> f = null;
    private ArrayList<Byte> h = null;

    private boolean T(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, String str) {
        boolean zD = com.ts.appservice.pki.d.a.d(arrayList, arrayList2);
        com.ts.appservice.pki.d.b.f("HsmManager", str + " : checkAesKey sameData " + zD);
        return zD;
    }

    private HashMap<String, ArrayList<Byte>> X(ArrayList<Byte> arrayList) {
        if (o()) {
            return null;
        }
        try {
            if (arrayList == null) {
                com.ts.appservice.pki.d.b.d("HsmManager", " getAesKeyData bytes data is null");
                return null;
            }
            b.a.a.a.a aVarR = this.f23a.r(96, 0, 2048, 1, arrayList);
            ArrayList<Byte> arrayList2 = aVarR.f7b;
            com.ts.appservice.pki.d.b.f("HsmManager", " getAesKeyData " + aVarR);
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList2));
            com.ts.appservice.pki.d.b.f("HsmManager", "mingwen:" + str);
            return r(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean d0(int i, ArrayList<Byte> arrayList, int i2) {
        if (this.f23a == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", "saveDataToHsm mTeeService == null , keyId " + i + " fail");
            return false;
        }
        if (arrayList == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", "saveDataToHsm keyid " + i + " ,data is null，fail");
            return false;
        }
        b.a.a.a.a aVarH = null;
        try {
            int size = arrayList.size() + 2;
            int i3 = size > i2 ? size + 2048 : i2;
            com.ts.appservice.pki.d.b.f("HsmManager", "currentDataLength " + size + " reservedSpace " + i2 + " createDataLength " + i3);
            com.ts.appservice.pki.d.b.f("HsmManager", " createData DATA_ENCRYPT : 1");
            com.ts.appservice.pki.d.b.d("HsmManager", "  saveDataToHSM keyId " + i + " createResult " + this.f23a.q(i, i3, 0, 1));
            StringBuilder sb = new StringBuilder();
            sb.append("saveDataToHSM data ");
            sb.append(arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", sb.toString());
            byte size2 = (byte) (arrayList.size() / CertificateBody.profileType);
            byte size3 = (byte) (arrayList.size() % CertificateBody.profileType);
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Byte.valueOf(size2));
            arrayList2.add(Byte.valueOf(size3));
            ArrayList<Byte> arrayList3 = new ArrayList<>();
            arrayList3.addAll(0, arrayList2);
            arrayList3.addAll(arrayList);
            aVarH = this.f23a.h(i, 0, arrayList3);
            com.ts.appservice.pki.d.b.b("HsmManager", "  saveDataToHSM keyId " + i + " dataSize:" + arrayList3.size() + " ,real dataSize:" + (arrayList3.size() - 2) + " ,height:" + ((int) size2) + " ,low:" + ((int) size3) + " writeData saveResult " + aVarH.f6a);
            if (aVarH.f6a == 0) {
                com.ts.appservice.pki.d.b.f("HsmManager", "save to HSM success " + i);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.pki.d.b.d("HsmManager", i + "  save to HSM fail , saveResult " + aVarH);
        return false;
    }

    private boolean h0(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        if (arrayList == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", " verifyEccCert data is null");
            return false;
        }
        try {
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList));
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyEccCert data : " + str);
            b.a.a.a.a aVarP = this.f23a.p(2, str, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384);
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyEccCert result is " + aVarP);
            boolean z = aVarP.f6a == 0;
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyEccCert isLegal " + z);
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean i0(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strA = com.ts.appservice.pki.d.c.a(new String(com.ts.appservice.pki.d.a.b(arrayList)));
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyEccCertPublicKey certBase64Data " + strA);
            ArrayList<Byte> arrayListA = com.ts.appservice.pki.d.a.a(BydPKIUtils.eccEnCodeStr("helloECC256", ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(strA, 0)))).getPublicKey()));
            com.ts.appservice.pki.d.b.f("HsmManager", "verifyEccCertPublicKey  encodeBytes " + arrayListA);
            b.a.a.a.a aVarR = this.f23a.r(99, 2, 256, 0, arrayListA);
            ArrayList<Byte> arrayList2 = aVarR.f7b;
            com.ts.appservice.pki.d.b.f("HsmManager", "verifyEccCertPublicKey " + aVarR);
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList2));
            boolean zEquals = str.equals("helloECC256");
            com.ts.appservice.pki.d.b.f("HsmManager", "verifyEccCertPublicKey: decodeStr " + str + " checkResult " + zEquals);
            return zEquals;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean j0(ArrayList<Byte> arrayList) {
        com.ts.appservice.pki.d.b.f("HsmManager", " verifyEngineeringCertPublicKey");
        return m0(102, arrayList);
    }

    private boolean k0(ArrayList<Byte> arrayList) {
        com.ts.appservice.pki.d.b.f("HsmManager", " verifyPlanningCertPublicKey ");
        return m0(96, arrayList);
    }

    private boolean l0(ArrayList<Byte> arrayList) {
        if (arrayList == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", " verifyRsaCert data is null");
            return false;
        }
        try {
            boolean zG0 = g0(new String(com.ts.appservice.pki.d.a.b(arrayList)));
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyRsaCert isLegal " + zG0);
            return zG0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean m0(int i, ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strA = com.ts.appservice.pki.d.c.a(new String(com.ts.appservice.pki.d.a.b(arrayList)));
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyRsaCertPublicKey certBase64Data " + strA);
            ArrayList<Byte> arrayListA = com.ts.appservice.pki.d.a.a(BydPKIUtils.encryptStrRSA("helloRsa2048", Base64.decode(strA, 0)));
            com.ts.appservice.pki.d.b.f("HsmManager", "verifyRsaCertPublicKey  encodeBytes " + arrayListA);
            b.a.a.a.a aVarR = this.f23a.r(i, 0, 2048, 1, arrayListA);
            ArrayList<Byte> arrayList2 = aVarR.f7b;
            com.ts.appservice.pki.d.b.f("HsmManager", "verifyRsaCertPublicKey checkRsa2048Cert " + aVarR);
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList2));
            boolean zEquals = str.equals("helloRsa2048");
            com.ts.appservice.pki.d.b.f("HsmManager", "verifyRsaCertPublicKey: decodeStr " + str + " checkResult " + zEquals);
            return zEquals;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean n0(HashMap<String, ArrayList<Byte>> map) {
        boolean z = false;
        boolean zD = false;
        boolean zD2 = false;
        boolean zD3 = false;
        for (String str : map.keySet()) {
            byte b2 = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != 328377285) {
                if (iHashCode != 849468471) {
                    if (iHashCode == 849469523 && str.equals("Key_256")) {
                        b2 = 1;
                    }
                } else if (str.equals("Key_128")) {
                    b2 = 0;
                }
            } else if (str.equals("Key_SecOC")) {
                b2 = 2;
            }
            if (b2 == 0) {
                zD = D(map.get(str), CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256);
                com.ts.appservice.pki.d.b.f("HsmManager", "Key_128 saveAesKeyToHal isSuccess : " + zD + " length : " + map.get(str).size());
            } else if (b2 == 1) {
                zD2 = D(map.get(str), CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256);
                com.ts.appservice.pki.d.b.f("HsmManager", "Key_256 saveAesKeyToHal isSuccess : " + zD2 + " length : " + map.get(str).size());
            } else if (b2 == 2) {
                zD3 = D(map.get(str), 188);
                com.ts.appservice.pki.d.b.f("HsmManager", "Key_SecOC saveAesKeyToHal isSuccess : " + zD3 + " length : " + map.get(str).size());
            }
        }
        if (zD && zD2 && zD3) {
            z = true;
        }
        com.ts.appservice.pki.d.b.f("HsmManager", " writeAesKey isSuccess : " + z);
        return z;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean B() {
        if (o()) {
            return false;
        }
        try {
            this.g = false;
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeEcc256P10File key 99  100");
            com.ts.appservice.pki.d.b.f("HsmManager", " aSymKeyExist " + this.f23a.k(99));
            b.a.a.a.a aVarE = this.f23a.e(2, 256, 99, 100);
            boolean z = true;
            boolean z2 = aVarE.f6a == 0;
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeEcc256P10File genEccAsymKeyResult " + aVarE);
            if (!z2) {
                com.ts.appservice.pki.d.b.f("HsmManager", "requestMakeEcc256P10File genAsymKey fail");
                return false;
            }
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeEcc256P10File keytype 2 DigestType 2");
            b.a.a.a.a aVarM = this.f23a.m("/C=CN/OU=GHY/O=BYD INDUSTRY/CN=BYD Device CA RSA CSR", 2, 2, 99, 100);
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeEcc256P10File ecc256P10 " + aVarM);
            this.h = aVarM.f7b;
            if (aVarM.f6a != 0) {
                z = false;
            }
            this.g = z;
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeEcc256P10File mEcc2568P10FileGenState " + this.g);
            return this.g;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean C() {
        if (o()) {
            return false;
        }
        try {
            this.e = false;
            com.ts.appservice.pki.d.b.f("HsmManager", " aSymKeyExist " + this.f23a.k(96));
            b.a.a.a.a aVarE = this.f23a.e(0, 2048, 96, 97);
            boolean z = true;
            boolean z2 = aVarE.f6a == 0;
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeRsa2048P10File genAsymKeyResult " + aVarE);
            if (!z2) {
                com.ts.appservice.pki.d.b.f("HsmManager", "requestMakeRsa2048P10File genAsymKey fail");
                return false;
            }
            com.ts.appservice.pki.d.b.f("HsmManager", " P10_SUBJECT       /C=CN/OU=GHY/O=BYD INDUSTRY/CN=BYD Device CA RSA CSR");
            b.a.a.a.a aVarM = this.f23a.m("/C=CN/OU=GHY/O=BYD INDUSTRY/CN=BYD Device CA RSA CSR", 0, 2, 96, 97);
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeRsa2048P10File rsa2048P10 " + aVarM);
            this.f = aVarM.f7b;
            if (aVarM.f6a != 0) {
                z = false;
            }
            this.e = z;
            com.ts.appservice.pki.d.b.f("HsmManager", " requestMakeRsa2048P10File mRsa2048P10FileGenState " + this.e);
            return this.e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean J(ArrayList<Byte> arrayList) {
        try {
            this.o = null;
            HashMap<String, ArrayList<Byte>> mapX = X(arrayList);
            if (mapX == null) {
                com.ts.appservice.pki.d.b.d("HsmManager", " writeAesKey aesKeyData == null");
                return false;
            }
            boolean zN0 = n0(mapX);
            if (zN0) {
                this.o = arrayList;
            }
            return zN0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean K(ArrayList<Byte> arrayList) {
        try {
            this.n = null;
            boolean zF = F(ExtensionType.negotiated_ff_dhe_groups, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeDeviceEcc256Cert saveSourceResult " + zF);
            if (zF) {
                this.n = arrayList;
            }
            return zF;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean L(ArrayList<Byte> arrayList) {
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", " writeDeviceRsa2048Cert data : " + arrayList);
            this.k = null;
            boolean zG = G(98, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeDeviceRsa2048Cert saveSourceResult " + zG);
            if (zG) {
                this.k = arrayList;
            }
            return zG;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean M(ArrayList<Byte> arrayList) {
        try {
            this.p = null;
            boolean zH = H(104, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeDeviceRsaCert saveSourceResult " + zH);
            if (zH) {
                this.p = arrayList;
            }
            return zH;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean N(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            if (arrayList == null) {
                com.ts.appservice.pki.d.b.d("HsmManager", " writeDeviceRsaKey data is null");
                return false;
            }
            this.q = this.f23a.i(0, new String(com.ts.appservice.pki.d.a.b(arrayList)), 102, 103);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeDeviceRsaKey " + this.q);
            boolean z = this.q.f6a == 0;
            if (z) {
                this.r = arrayList;
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean O(ArrayList<Byte> arrayList) {
        com.ts.appservice.pki.d.b.f("HsmManager", "writeEngineerPublicKey " + arrayList);
        if (arrayList == null || arrayList.size() == 0) {
            com.ts.appservice.pki.d.b.d("HsmManager", "writeEngineerPublicKey data is null");
            return false;
        }
        boolean zD0 = d0(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, arrayList, PKIFailureInfo.certConfirmed);
        com.ts.appservice.pki.d.b.f("HsmManager", "writeEngineerPublicKey result：" + zD0);
        if (zD0) {
            this.s = arrayList;
        }
        return zD0;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean P(ArrayList<Byte> arrayList) {
        try {
            this.m = null;
            boolean zF = F(CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeIdentityEcc256Cert saveSourceResult " + zF);
            if (zF) {
                this.m = arrayList;
            }
            return zF;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean Q(ArrayList<Byte> arrayList) {
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", " writeIdentityRsa2048Cert data : " + arrayList);
            this.j = null;
            boolean zG = G(CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeIdentityRsa2048Cert saveSourceResult " + zG);
            if (zG) {
                this.j = arrayList;
            }
            return zG;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean R(ArrayList<Byte> arrayList) {
        try {
            this.l = null;
            boolean zF = F(CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeRootEcc256Cert saveSourceResult " + zF);
            if (zF) {
                this.l = arrayList;
            }
            return zF;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean S(ArrayList<Byte> arrayList) {
        try {
            this.i = null;
            boolean zG = G(CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " writeRootRsa2048Cert saveSourceResult " + zG);
            if (zG) {
                this.i = arrayList;
            }
            return zG;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String U(String str) {
        if (o()) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                com.ts.appservice.pki.d.b.f("HsmManager", " decodeEccHsm dataStr is null");
                return null;
            }
            byte[] bArrDecode = Base64.decode(str, 0);
            com.ts.appservice.pki.d.b.f("HsmManager", " dataStr " + str);
            com.ts.appservice.pki.d.b.f("HsmManager", " decode " + Arrays.toString(bArrDecode));
            b.a.a.a.a aVarR = this.f23a.r(99, 2, 256, 0, com.ts.appservice.pki.d.a.a(bArrDecode));
            com.ts.appservice.pki.d.b.f("HsmManager", " decodeEccHsm " + aVarR);
            byte[] bArrB = com.ts.appservice.pki.d.a.b(aVarR.f7b);
            com.ts.appservice.pki.d.b.f("HsmManager", "decodeEccHsm bytesData " + Arrays.toString(bArrB));
            com.ts.appservice.pki.d.b.f("HsmManager", " decodeEccHsm bytesDataStr " + new String(bArrB));
            String str2 = new String(Base64.encode(bArrB, 0));
            com.ts.appservice.pki.d.b.f("HsmManager", "decodeEccHsm " + str2);
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String V(String str) {
        if (o()) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                com.ts.appservice.pki.d.b.f("HsmManager", " decodeRsaHsm dataStr is null");
                return null;
            }
            byte[] bArrDecode = Base64.decode(str, 0);
            com.ts.appservice.pki.d.b.f("HsmManager", "decodeRsaHsm dataStr " + str);
            com.ts.appservice.pki.d.b.f("HsmManager", "decodeRsaHsm decode " + Arrays.toString(bArrDecode));
            b.a.a.a.a aVarR = this.f23a.r(96, 0, 2048, 1, com.ts.appservice.pki.d.a.a(bArrDecode));
            ArrayList<Byte> arrayList = aVarR.f7b;
            com.ts.appservice.pki.d.b.f("HsmManager", " decodeRsaHsm " + aVarR);
            byte[] bArrB = com.ts.appservice.pki.d.a.b(arrayList);
            com.ts.appservice.pki.d.b.f("HsmManager", " decodeRsaHsm dataBytes Str " + new String(bArrB));
            String str2 = new String(Base64.encode(bArrB, 0));
            com.ts.appservice.pki.d.b.f("HsmManager", "decodeRsaHsm resultBase64Str " + str2);
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String W(String str) {
        if (o()) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            com.ts.appservice.pki.d.b.f("HsmManager", "encryptStrHsm data is null");
            return null;
        }
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", " encryptStrHsm data : " + str);
            b.a.a.a.a aVarL = this.f23a.l(102, 0, 2048, 1, com.ts.appservice.pki.d.a.a(str.getBytes()));
            ArrayList<Byte> arrayList = aVarL.f7b;
            com.ts.appservice.pki.d.b.f("HsmManager", " encryptStrHsm " + aVarL);
            if (arrayList != null && arrayList.size() > 0) {
                byte[] bArrB = com.ts.appservice.pki.d.a.b(arrayList);
                com.ts.appservice.pki.d.b.f("HsmManager", " encryptStrHsm " + new String(bArrB));
                return new String(Base64.encode(bArrB, 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String Y(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = 328377285(0x1392a3c5, float:3.701707E-27)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2a
            r1 = 849468471(0x32a1dc37, float:1.8843009E-8)
            if (r0 == r1) goto L20
            r1 = 849469523(0x32a1e053, float:1.8844878E-8)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "Key_256"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r3
            goto L35
        L20:
            java.lang.String r0 = "Key_128"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = 0
            goto L35
        L2a:
            java.lang.String r0 = "Key_SecOC"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = r2
            goto L35
        L34:
            r5 = -1
        L35:
            r0 = 0
            if (r5 == 0) goto L4c
            if (r5 == r3) goto L45
            if (r5 == r2) goto L3e
            r4 = r0
            goto L52
        L3e:
            r5 = 188(0xbc, float:2.63E-43)
            java.util.ArrayList r4 = r4.z(r5)
            goto L52
        L45:
            r5 = 187(0xbb, float:2.62E-43)
            java.util.ArrayList r4 = r4.z(r5)
            goto L52
        L4c:
            r5 = 186(0xba, float:2.6E-43)
            java.util.ArrayList r4 = r4.z(r5)
        L52:
            if (r4 == 0) goto L5d
            java.lang.String r0 = new java.lang.String
            byte[] r4 = com.ts.appservice.pki.d.a.b(r4)
            r0.<init>(r4)
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.c.c.Y(java.lang.String):java.lang.String");
    }

    public ArrayList<Byte> Z() {
        return A(0, 104);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0073  */
    @Override // com.ts.appservice.pki.c.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a() {
        /*
            r16 = this;
            r0 = r16
            java.lang.String r1 = "HsmManager"
            java.lang.String r2 = " checkAesKey enter "
            com.ts.appservice.pki.d.b.f(r1, r2)
            r2 = 186(0xba, float:2.6E-43)
            r3 = 0
            java.util.ArrayList r2 = r0.z(r2)     // Catch: java.lang.Exception -> Lb3
            r4 = 187(0xbb, float:2.62E-43)
            java.util.ArrayList r4 = r0.z(r4)     // Catch: java.lang.Exception -> Lb3
            r5 = 188(0xbc, float:2.63E-43)
            java.util.ArrayList r5 = r0.z(r5)     // Catch: java.lang.Exception -> Lb3
            java.util.ArrayList<java.lang.Byte> r6 = r0.o     // Catch: java.lang.Exception -> Lb3
            java.util.HashMap r6 = r0.X(r6)     // Catch: java.lang.Exception -> Lb3
            if (r6 != 0) goto L2a
            java.lang.String r0 = "checkAesKey data == null"
            com.ts.appservice.pki.d.b.d(r1, r0)     // Catch: java.lang.Exception -> Lb3
            return r3
        L2a:
            java.util.Set r7 = r6.keySet()     // Catch: java.lang.Exception -> Lb3
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Exception -> Lb3
            r8 = 1
            r9 = r8
        L34:
            boolean r10 = r7.hasNext()     // Catch: java.lang.Exception -> Lb3
            if (r10 == 0) goto L9e
            java.lang.Object r10 = r7.next()     // Catch: java.lang.Exception -> Lb3
            java.lang.String r10 = (java.lang.String) r10     // Catch: java.lang.Exception -> Lb3
            int r12 = r10.hashCode()     // Catch: java.lang.Exception -> Lb3
            r13 = 328377285(0x1392a3c5, float:3.701707E-27)
            java.lang.String r14 = "Key_256"
            java.lang.String r15 = "Key_128"
            java.lang.String r11 = "Key_SecOC"
            r3 = 2
            if (r12 == r13) goto L6b
            r13 = 849468471(0x32a1dc37, float:1.8843009E-8)
            if (r12 == r13) goto L63
            r13 = 849469523(0x32a1e053, float:1.8844878E-8)
            if (r12 == r13) goto L5b
            goto L73
        L5b:
            boolean r12 = r10.equals(r14)     // Catch: java.lang.Exception -> Lb3
            if (r12 == 0) goto L73
            r12 = r8
            goto L74
        L63:
            boolean r12 = r10.equals(r15)     // Catch: java.lang.Exception -> Lb3
            if (r12 == 0) goto L73
            r12 = 0
            goto L74
        L6b:
            boolean r12 = r10.equals(r11)     // Catch: java.lang.Exception -> Lb3
            if (r12 == 0) goto L73
            r12 = r3
            goto L74
        L73:
            r12 = -1
        L74:
            if (r12 == 0) goto L91
            if (r12 == r8) goto L86
            if (r12 == r3) goto L7b
            goto L9c
        L7b:
            java.lang.Object r3 = r6.get(r10)     // Catch: java.lang.Exception -> Lb3
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch: java.lang.Exception -> Lb3
            boolean r3 = r0.T(r5, r3, r11)     // Catch: java.lang.Exception -> Lb3
            goto L9b
        L86:
            java.lang.Object r3 = r6.get(r10)     // Catch: java.lang.Exception -> Lb3
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch: java.lang.Exception -> Lb3
            boolean r3 = r0.T(r4, r3, r14)     // Catch: java.lang.Exception -> Lb3
            goto L9b
        L91:
            java.lang.Object r3 = r6.get(r10)     // Catch: java.lang.Exception -> Lb3
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch: java.lang.Exception -> Lb3
            boolean r3 = r0.T(r2, r3, r15)     // Catch: java.lang.Exception -> Lb3
        L9b:
            r9 = r9 & r3
        L9c:
            r3 = 0
            goto L34
        L9e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb3
            r0.<init>()     // Catch: java.lang.Exception -> Lb3
            java.lang.String r2 = "checkAesKey isSuccess : "
            r0.append(r2)     // Catch: java.lang.Exception -> Lb3
            r0.append(r9)     // Catch: java.lang.Exception -> Lb3
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lb3
            com.ts.appservice.pki.d.b.f(r1, r0)     // Catch: java.lang.Exception -> Lb3
            return r9
        Lb3:
            r0 = move-exception
            r0.printStackTrace()
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.c.c.a():boolean");
    }

    public ArrayList<Byte> a0() {
        ArrayList<Byte> arrayListC0 = c0(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256);
        com.ts.appservice.pki.d.b.f("HsmManager", "getEngineerPublicKey " + arrayListC0);
        return arrayListC0;
    }

    public ArrayList<Byte> b0() {
        return A(0, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384);
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean c() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkDeviceEcc256Cert mEcc256DeviceCertPath " + this.n);
            if (!g(this.n, ExtensionType.negotiated_ff_dhe_groups)) {
                return false;
            }
            boolean zH0 = h0(this.n);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceEcc256Cert isCertLegal " + zH0);
            if (!zH0) {
                return false;
            }
            boolean zI0 = i0(this.n);
            com.ts.appservice.pki.d.b.f("HsmManager", "checkDeviceEcc256Cert isPublicKeyLegal " + zI0);
            return zI0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Byte> c0(int i) {
        if (o()) {
            return null;
        }
        try {
            b.a.a.a.a aVarN = this.f23a.n(i);
            com.ts.appservice.pki.d.b.f("HsmManager", " readDataFromHsm " + aVarN);
            if (aVarN.f6a != 0) {
                return null;
            }
            ArrayList<Byte> arrayList = aVarN.f7b;
            int size = arrayList.size() - 1;
            if (arrayList.get(size).byteValue() == 0) {
                arrayList.remove(size);
            }
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList));
            com.ts.appservice.pki.d.b.f("HsmManager", " s " + str);
            int i2 = Integer.parseInt(str.split(":")[1]);
            b.a.a.a.a aVarG = this.f23a.g(i, 0, i2);
            com.ts.appservice.pki.d.b.f("HsmManager", " keyId " + i + " dataLength " + i2 + StringUtils.SPACE + aVarG);
            ArrayList<Byte> arrayList2 = aVarG.f7b;
            Byte b2 = arrayList2.get(0);
            Byte b3 = arrayList2.get(1);
            int iByteValue = (b2.byteValue() * 127) + b3.byteValue();
            com.ts.appservice.pki.d.b.f("HsmManager", "readDataFromHsm keyId:" + i + " ,realDataLength:" + iByteValue + " ,height:" + b2 + " ,low:" + b3);
            List<Byte> listSubList = arrayList2.subList(2, iByteValue + 2);
            ArrayList<Byte> arrayList3 = new ArrayList<>();
            arrayList3.addAll(listSubList);
            StringBuilder sb = new StringBuilder();
            sb.append("readDataFromHsm keyId ");
            sb.append(i);
            sb.append(" realData ");
            sb.append(arrayList3);
            com.ts.appservice.pki.d.b.f("HsmManager", sb.toString());
            return arrayList3;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean d() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkDeviceRsa2048Cert mRsa2048DeviceCertPath " + this.k);
            boolean zM = m(this.k, 98);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceRsa2048Cert isSame " + zM);
            if (!zM) {
                return false;
            }
            boolean zL0 = l0(this.k);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceRsa2048Cert isCertLegal " + zL0);
            if (!zL0) {
                return false;
            }
            boolean zK0 = k0(this.k);
            com.ts.appservice.pki.d.b.f("HsmManager", "checkDeviceRsa2048Cert isPublicKeyLegal " + zK0);
            return zK0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean e() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkDeviceRsaCert mRsaDeviceCertPath " + this.p);
            boolean zN = n(this.p, 104);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceRsaCert isSame " + zN);
            if (!zN) {
                return false;
            }
            boolean zJ0 = j0(this.p);
            com.ts.appservice.pki.d.b.f("HsmManager", "checkDeviceRsaCert isPublicKeyLegal " + zJ0);
            return zJ0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String e0(String str) {
        String str2 = null;
        if (o()) {
            return null;
        }
        if (str == null || str.length() == 0) {
            com.ts.appservice.pki.d.b.d("HsmManager", "signEccHsm mingwen is NULL or size 0");
            return str;
        }
        com.ts.appservice.pki.d.b.f("HsmManager", "signEccHsm : mingwen" + str);
        try {
            b.a.a.a.a aVarA = this.f23a.a(this.f23a.j(2, com.ts.appservice.pki.d.a.a(str.getBytes())).f7b, 2, 256, 1, 2, 99);
            com.ts.appservice.pki.d.b.f("HsmManager", "signEccHsm BoschResult.ret " + aVarA.f6a);
            if (aVarA.f7b != null && aVarA.f7b.size() != 0) {
                str2 = new String(org.bouncycastle.util.encoders.Base64.encode(com.ts.appservice.pki.d.a.b(aVarA.f7b)));
            }
            com.ts.appservice.pki.d.b.f("HsmManager", "signEccHsm sign " + str2);
        } catch (Exception e) {
            com.ts.appservice.pki.d.b.c("HsmManager", e);
        }
        return str2;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean f() {
        if (o()) {
            return false;
        }
        try {
            if (this.r == null) {
                com.ts.appservice.pki.d.b.d("HsmManager", " checkDeviceRsaKey mDeviceRsaKeyData is null");
                return false;
            }
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceRsaKey mWriteDeviceRsaKeyResult " + this.q);
            boolean z = true;
            b.a.a.a.a aVarJ = this.f23a.j(1, com.ts.appservice.pki.d.a.a("hello".getBytes()));
            ArrayList<Byte> arrayList = aVarJ.f7b;
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceRsaKey boschResult " + aVarJ);
            String strB = com.ts.appservice.pki.d.c.b(new String(com.ts.appservice.pki.d.a.b(this.r)));
            com.ts.appservice.pki.d.b.f("HsmManager", " privateKeyBase64Data " + strB);
            b.a.a.a.a aVarB = this.f23a.b(com.ts.appservice.pki.d.a.a(BydPKIUtils.sign("hello", strB)), arrayList, 0, 2048, 1, 1, 103);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkDeviceRsaKey verifyResult " + aVarB);
            if (aVarB.f6a != 0) {
                z = false;
            }
            this.r = null;
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean f0(String str) {
        com.ts.appservice.pki.d.b.f("HsmManager", "upateAesKeyHsm " + str);
        if (TextUtils.isEmpty(str)) {
            com.ts.appservice.pki.d.b.f("HsmManager", "upateAesKeyHsm aeskeySrcData is null");
            return false;
        }
        ArrayList<Byte> arrayListC = com.ts.appservice.pki.d.a.c(str);
        com.ts.appservice.pki.d.b.f("HsmManager", " updateAesKeyHsm bytes " + arrayListC);
        HashMap<String, ArrayList<Byte>> mapX = X(arrayListC);
        if (mapX == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", "upateAesKeyHsm aesKeyData == null");
            return false;
        }
        boolean zN0 = n0(mapX);
        com.ts.appservice.pki.d.b.f("HsmManager", " updateAesKeyHsm " + str + " saveResult " + zN0);
        return zN0;
    }

    public boolean g0(String str) {
        if (o()) {
            return false;
        }
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyCert data : " + str);
            b.a.a.a.a aVarP = this.f23a.p(0, str, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384);
            com.ts.appservice.pki.d.b.f("HsmManager", " verifyCert result is " + aVarP.f6a);
            return aVarP.f6a == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean h() {
        com.ts.appservice.pki.d.b.f("HsmManager", "checkEngineerPublicKey");
        if (this.s == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkEngineerPublicKey sourceData is null");
            return false;
        }
        ArrayList<Byte> arrayListC0 = c0(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256);
        if (arrayListC0 == null) {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkEngineerPublicKey hasSaveData is null");
            return false;
        }
        boolean zD = com.ts.appservice.pki.d.a.d(this.s, arrayListC0);
        com.ts.appservice.pki.d.b.f("HsmManager", "checkEngineerPublicKey sameData：" + zD);
        return zD;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean i() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkIdentityEcc256Cert mEcc256IdentityCertPath " + this.m);
            boolean zG = g(this.m, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkIdentityEcc256Cert isSame " + zG);
            if (!zG) {
                return false;
            }
            boolean zH0 = h0(this.m);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkIdentityEcc256Cert isLegal " + zH0);
            return zH0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean j() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkIdentityRsa2048Cert mRsa2048IdentityCertPath " + this.j);
            boolean zM = m(this.j, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkIdentityRsa2048Cert isSame " + zM);
            if (!zM) {
                return false;
            }
            boolean zL0 = l0(this.j);
            com.ts.appservice.pki.d.b.f("HsmManager", " checkIdentityRsa2048Cert isLegal " + zL0);
            return zL0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean k() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkRootEcc256Cert mEcc256RootCertPath " + this.l);
            return g(this.l, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean l() {
        try {
            com.ts.appservice.pki.d.b.d("HsmManager", "checkRootRsa2048Cert mRsa2048RootCertPath " + this.i);
            return m(this.i, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public ArrayList<Byte> s() {
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", " getEcc256P10File " + this.h);
            return this.h;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean t() {
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", "getEcc256P10FileGenerateState mEcc2568P10FileGenState " + this.g + " mEcc256P10FileData " + this.h);
            if (!this.g || this.h == null) {
                return false;
            }
            return this.h.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public ArrayList<Byte> u() {
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", " getRsa2048P10File " + this.f);
            return this.f;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean v() {
        try {
            com.ts.appservice.pki.d.b.f("HsmManager", "getRsa2048P10FileGenerateState mRsa2048P10FileGenState " + this.e + " mRsa2048P10FileData " + this.f);
            if (!this.e || this.f == null) {
                return false;
            }
            return this.f.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
