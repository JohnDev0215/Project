package com.ts.appservice.pki.c;

import android.text.TextUtils;
import android.util.Base64;
import com.byd.pki.filling.BydPKIUtils;
import com.byd.pki.filling.CertP12Info;
import com.byd.pki.filling.CommonConsts;
import com.byd.pki.filling.CsrP12Info;
import java.io.ByteArrayInputStream;
import java.io.File;
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

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d extends a {
    ArrayList<Byte> A;
    private CsrP12Info e;
    private CertP12Info f;
    private CertP12Info g;
    private CertP12Info h;
    private CsrP12Info i;
    private CertP12Info j;
    private CertP12Info k;
    private CertP12Info l;
    private ArrayList<Byte> m;
    private boolean n = false;
    private boolean o = false;
    private boolean p;
    private CertP12Info q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private CertP12Info z;

    private ArrayList<Byte> T(ArrayList<Byte> arrayList) {
        byte[] bytes = "-----BEGIN CERTIFICATE REQUEST-----\n".getBytes();
        byte[] bytes2 = "\n-----END CERTIFICATE REQUEST-----".getBytes();
        ArrayList<Byte> arrayListA = com.ts.appservice.pki.d.a.a(bytes);
        ArrayList<Byte> arrayListA2 = com.ts.appservice.pki.d.a.a(bytes2);
        com.ts.appservice.pki.d.b.f("PkiDataManager", " headArray " + arrayListA);
        com.ts.appservice.pki.d.b.f("PkiDataManager", " footArray " + arrayListA2);
        arrayList.addAll(0, arrayListA);
        arrayList.addAll(arrayListA2);
        return arrayList;
    }

    private boolean U(ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2, String str) {
        boolean zD = com.ts.appservice.pki.d.a.d(arrayList, arrayList2);
        com.ts.appservice.pki.d.b.f("PkiDataManager", str + " : checkAesKey sameData " + zD);
        return zD;
    }

    private boolean V(String str, int i) throws Throwable {
        if (o()) {
            return false;
        }
        boolean zI0 = i0(str, i);
        com.ts.appservice.pki.d.b.d("PkiDataManager", "checkP12File result is " + zI0);
        if (zI0) {
            p(str);
        }
        return zI0;
    }

    private boolean W(String str, int i) {
        if (o()) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkP12Secret content is null");
            return false;
        }
        boolean zE = com.ts.appservice.pki.d.a.e(j0(i), str.getBytes());
        com.ts.appservice.pki.d.b.d("PkiDataManager", "checkP12Secret result is " + zE);
        return zE;
    }

    private HashMap<String, ArrayList<Byte>> g0(ArrayList<Byte> arrayList) {
        try {
            byte[] bArrB = com.ts.appservice.pki.d.a.b(arrayList);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "writeAesKey sourceStrData " + Arrays.toString(bArrB));
            byte[] bArrDecryptAesKeyTee = BydPKIUtils.decryptAesKeyTee(new String(Base64.encode(bArrB, 0)), k0(10), l0(11));
            if (bArrDecryptAesKeyTee == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "getTeeAesKey aesKeyBytes == null");
                return null;
            }
            String str = new String(bArrDecryptAesKeyTee);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "mingwen:" + str);
            return r(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean i0(String str, int i) throws Throwable {
        ArrayList<Byte> arrayListJ0;
        try {
            ArrayList<Byte> arrayListF = com.ts.appservice.pki.d.a.f(str);
            if (arrayListF != null && (arrayListJ0 = j0(i)) != null) {
                if (arrayListF.size() != arrayListJ0.size()) {
                    com.ts.appservice.pki.d.b.d("PkiDataManager", "is not same file，size is not equal");
                    return false;
                }
                for (int i2 = 0; i2 < arrayListF.size(); i2++) {
                    if (!arrayListJ0.get(i2).equals(arrayListF.get(i2))) {
                        com.ts.appservice.pki.d.b.d("PkiDataManager", "is not same file");
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private byte[] k0(int i) {
        return com.ts.appservice.pki.d.a.b(j0(i));
    }

    private String l0(int i) {
        return new String(com.ts.appservice.pki.d.a.b(j0(i)));
    }

    private boolean m0(int i, ArrayList<Byte> arrayList, int i2) {
        if (this.f23a == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "mTeeService == null , keyId " + i + "  save to TEE fail ");
            return false;
        }
        if (arrayList == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "keyid " + i + " , save to TEE data is null ，fail ");
            return false;
        }
        b.a.a.a.a aVarH = null;
        try {
            int size = arrayList.size() + 2;
            int i3 = size > i2 ? size + 2048 : i2;
            com.ts.appservice.pki.d.b.f("PkiDataManager", "currentDataLength " + size + " reservedSpace " + i2 + " createDataLength " + i3);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " createData DATA_ENCRYPT : 1");
            com.ts.appservice.pki.d.b.d("PkiDataManager", "  saveDataToTee keyId " + i + " createResult " + this.f23a.q(i, i3, 0, 1));
            StringBuilder sb = new StringBuilder();
            sb.append("saveDataToTee data ");
            sb.append(arrayList);
            com.ts.appservice.pki.d.b.f("PkiDataManager", sb.toString());
            byte size2 = (byte) (arrayList.size() / CertificateBody.profileType);
            byte size3 = (byte) (arrayList.size() % CertificateBody.profileType);
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Byte.valueOf(size2));
            arrayList2.add(Byte.valueOf(size3));
            ArrayList<Byte> arrayList3 = new ArrayList<>();
            arrayList3.addAll(0, arrayList2);
            arrayList3.addAll(arrayList);
            aVarH = this.f23a.h(i, 0, arrayList3);
            com.ts.appservice.pki.d.b.b("PkiDataManager", "  saveDataToTee keyId " + i + " dataSize:" + arrayList3.size() + " ,real dataSize:" + (arrayList3.size() - 2) + " ,height:" + ((int) size2) + " ,low:" + ((int) size3) + " writeData saveResult " + aVarH.f6a);
            if (aVarH.f6a == 0) {
                com.ts.appservice.pki.d.b.f("PkiDataManager", "save to TEE success " + i);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.pki.d.b.d("PkiDataManager", i + "  save to TEE fail , saveResult " + aVarH);
        return false;
    }

    private boolean n0(int i, String str) {
        try {
            boolean zM0 = m0(i, com.ts.appservice.pki.d.a.f(str), PKIFailureInfo.certRevoked);
            com.ts.appservice.pki.d.b.d("PkiDataManager", i + "  save File to TEE , " + zM0);
            return zM0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean o0(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "saveP12SecretToHal keyid " + i + " , save to TEE content is null ，fail ");
            return false;
        }
        boolean zM0 = m0(i, com.ts.appservice.pki.d.a.a(str.getBytes()), 2048);
        com.ts.appservice.pki.d.b.d("PkiDataManager", i + "  save Data to TEE , " + zM0);
        return zM0;
    }

    private boolean p0(int i, ArrayList<Byte> arrayList) {
        return m0(i, arrayList, PKIFailureInfo.certRevoked);
    }

    private boolean r0(int i, int i2, int i3, int i4) {
        com.ts.appservice.pki.d.b.f("PkiDataManager", "verifyCertTee: parentP12FileKey " + i + " parentP12PwdKey " + i2 + " sonP12FileKey " + i3 + " sonP12PwdKey " + i4);
        boolean zVerifyCertTee = BydPKIUtils.verifyCertTee(k0(i), l0(i2), k0(i3), l0(i4));
        StringBuilder sb = new StringBuilder();
        sb.append("verifyCertTee: verifyCertResult ");
        sb.append(zVerifyCertTee);
        com.ts.appservice.pki.d.b.f("PkiDataManager", sb.toString());
        return zVerifyCertTee;
    }

    private boolean s0(String str) throws Throwable {
        try {
            byte[] bArrG = com.ts.appservice.pki.d.a.g(str);
            if (bArrG == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "verifyEccCertPublicKey file bytes == null");
                return false;
            }
            String str2 = new String(bArrG);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " certStr " + str2);
            byte[] bArrEccEnCodeStr = BydPKIUtils.eccEnCodeStr("helloECC256", ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(com.ts.appservice.pki.d.c.a(str2), 0)))).getPublicKey());
            if (bArrEccEnCodeStr == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "verifyEccCertPublicKey encode == null");
                return false;
            }
            byte[] bArrK0 = k0(22);
            String strL0 = l0(23);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "verifyEccCertPublicKey encode " + Arrays.toString(bArrEccEnCodeStr));
            byte[] bArrEccDecryptStrTee = BydPKIUtils.eccDecryptStrTee(bArrEccEnCodeStr, bArrK0, strL0);
            String str3 = bArrEccDecryptStrTee != null ? new String(bArrEccDecryptStrTee) : null;
            boolean zEquals = "helloECC256".equals(str3);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "verifyEccCertPublicKey: decodeStr " + str3 + " isCertPublicKeyLegal " + zEquals);
            return zEquals;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean t0(String str, int i, int i2) throws Throwable {
        try {
            byte[] bArrG = com.ts.appservice.pki.d.a.g(str);
            if (bArrG == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "verifyRsaCertPublicKey file bytes == null");
                return false;
            }
            String str2 = new String(bArrG);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "verifyRsaCertPublicKey certStr " + str2);
            byte[] bArrEncryptStrRSA = BydPKIUtils.encryptStrRSA("helloRSA2048", Base64.decode(com.ts.appservice.pki.d.c.a(str2), 0));
            if (bArrEncryptStrRSA == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "verifyRsaCertPublicKey encode == null");
                return false;
            }
            byte[] bArrK0 = k0(i);
            String strL0 = l0(i2);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " encode " + Arrays.toString(bArrEncryptStrRSA));
            byte[] bArrDecryptAesKeyTee = BydPKIUtils.decryptAesKeyTee(new String(Base64.encode(bArrEncryptStrRSA, 0)), bArrK0, strL0);
            String str3 = bArrDecryptAesKeyTee != null ? new String(bArrDecryptAesKeyTee) : null;
            boolean zEquals = "helloRSA2048".equals(str3);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "verifyRsaCertPublicKey: decodeStr " + str3 + " isCertPublicKeyLegal " + zEquals);
            return zEquals;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String u0(ArrayList<Byte> arrayList, String str) {
        if (arrayList == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", " writeDataToFile data is null " + str);
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        if (com.ts.appservice.pki.d.a.h(arrayList, str)) {
            return str;
        }
        return null;
    }

    private String v0(ArrayList<Byte> arrayList, String str) throws Throwable {
        try {
            String strB = com.ts.appservice.pki.d.c.b(new String(com.ts.appservice.pki.d.a.b(arrayList)));
            boolean zI = com.ts.appservice.pki.d.a.i(strB.trim().getBytes(), str);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " privateKeyBase64Data " + strB);
            if (zI) {
                return str;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean w0(HashMap<String, ArrayList<Byte>> map) {
        boolean z = false;
        if (map == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", " writeTeeAesKeyDataToHal aesKeyData == null");
            return false;
        }
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
                com.ts.appservice.pki.d.b.f("PkiDataManager", "Key_128 saveAesKeyToHal isSuccess : " + zD + " length : " + map.get(str).size());
            } else if (b2 == 1) {
                zD2 = D(map.get(str), CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256);
                com.ts.appservice.pki.d.b.f("PkiDataManager", "Key_256 saveAesKeyToHal isSuccess : " + zD2 + " length : " + map.get(str).size());
            } else if (b2 == 2) {
                zD3 = D(map.get(str), 188);
                com.ts.appservice.pki.d.b.f("PkiDataManager", "Key_SecOC saveAesKeyToHal isSuccess : " + zD3 + " length : " + map.get(str).size());
            }
        }
        if (zD && zD2 && zD3) {
            z = true;
        }
        com.ts.appservice.pki.d.b.f("PkiDataManager", " writeAesKey isSuccess : " + z);
        return z;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean B() {
        try {
            this.o = false;
            CsrP12Info csrP12InfoGenerateECC256Csr = BydPKIUtils.generateECC256Csr("/data/pki_data/");
            this.i = csrP12InfoGenerateECC256Csr;
            csrP12InfoGenerateECC256Csr.getCsrPath();
            String privateKeyP12path = this.i.getPrivateKeyP12path();
            String privateKeyP12Secret = this.i.getPrivateKeyP12Secret();
            this.i.getSecretType();
            boolean zN0 = n0(22, privateKeyP12path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " requestMakeEcc256P10File saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(23, privateKeyP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " requestMakeEcc256P10File savePwdResult " + zO0);
                this.o = zO0;
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean C() {
        try {
            this.n = false;
            CsrP12Info csrP12InfoGenerateRsa2048Csr = BydPKIUtils.generateRsa2048Csr("/data/pki_data/");
            this.e = csrP12InfoGenerateRsa2048Csr;
            csrP12InfoGenerateRsa2048Csr.getCsrPath();
            String privateKeyP12path = this.e.getPrivateKeyP12path();
            String privateKeyP12Secret = this.e.getPrivateKeyP12Secret();
            this.e.getSecretType();
            boolean zN0 = n0(10, privateKeyP12path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " requestMakeRsa2048P10File saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(11, privateKeyP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " requestMakeRsa2048P10File savePwdResult " + zO0);
                this.n = zO0;
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean J(ArrayList<Byte> arrayList) {
        try {
            this.m = null;
            this.p = false;
            boolean zW0 = w0(g0(arrayList));
            this.p = zW0;
            com.ts.appservice.pki.d.b.f("PkiDataManager", "writeAesKey isSuccess " + zW0);
            if (zW0) {
                this.m = arrayList;
            }
            return zW0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean K(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/ecc256_device_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.DEVICE_ECC256_CERT);
            this.l = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.l.getCertP12Secret();
            boolean zN0 = n0(31, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceEcc256Cert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(32, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceEcc256Cert savePwdResult " + zO0);
                if (zO0) {
                    this.w = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean L(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/rsa2048_device_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.DEVICE_RSA2048_CERT);
            this.h = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.h.getCertP12Secret();
            boolean zN0 = n0(19, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceRsa2048Cert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(20, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceRsa2048Cert savePwdResult " + zO0);
                if (zO0) {
                    this.t = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean M(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/rsa_device_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.DEVICE_RSA_CERT);
            this.q = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.q.getCertP12Secret();
            boolean zN0 = n0(35, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceRsaCert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(36, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceRsaCert savePwdResult " + zO0);
                if (zO0) {
                    this.x = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean N(ArrayList<Byte> arrayList) throws Throwable {
        try {
            String strV0 = v0(arrayList, "/data/pki_data/rsa_device_key.pem");
            CertP12Info certP12InfoStoreDeviceRSAKey = BydPKIUtils.storeDeviceRSAKey(strV0, "/data/pki_data/");
            this.z = certP12InfoStoreDeviceRSAKey;
            String certP12Path = certP12InfoStoreDeviceRSAKey.getCertP12Path();
            String certP12Secret = this.z.getCertP12Secret();
            boolean zN0 = n0(38, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceRsaKey saveFileResult " + zN0);
            if (!zN0) {
                return false;
            }
            boolean zO0 = o0(39, certP12Secret);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeDeviceRsaKey savePwdResult " + zO0);
            if (zO0) {
                this.y = strV0;
            }
            return zO0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean O(ArrayList<Byte> arrayList) {
        com.ts.appservice.pki.d.b.f("PkiDataManager", "writeEngineerPublicKey " + arrayList);
        if (arrayList == null || arrayList.size() == 0) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "writeEngineerPublicKey data is null");
            return false;
        }
        boolean zP0 = p0(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, arrayList);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "writeEngineerPublicKey result：" + zP0);
        if (zP0) {
            this.A = arrayList;
        }
        return zP0;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean P(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/ecc256_identity_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.IDENTITY_ECC256_CERT);
            this.k = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.k.getCertP12Secret();
            boolean zN0 = n0(28, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeIdentityEcc256Cert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(29, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeIdentityEcc256Cert savePwdResult " + zO0);
                if (zO0) {
                    this.v = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean Q(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/rsa2048_identity_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.IDENTITY_RSA2048_CERT);
            this.g = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.g.getCertP12Secret();
            boolean zN0 = n0(16, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeIdentityRsa2048Cert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(17, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeIdentityRsa2048Cert savePwdResult " + zO0);
                if (zO0) {
                    this.s = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean R(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/ecc256_root_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.ROOT_ECC256_CERT);
            this.j = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.j.getCertP12Secret();
            boolean zN0 = n0(25, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeRootEcc256Cert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(26, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeRootEcc256Cert savePwdResult " + zO0);
                if (zO0) {
                    this.u = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean S(ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String strU0 = u0(arrayList, "/data/pki_data/rsa2048_root_cert.cer");
            CertP12Info certP12InfoStoreCert = BydPKIUtils.storeCert(strU0, "/data/pki_data/", CommonConsts.ROOT_RSA2048_CERT);
            this.f = certP12InfoStoreCert;
            String certP12Path = certP12InfoStoreCert.getCertP12Path();
            String certP12Secret = this.f.getCertP12Secret();
            boolean zN0 = n0(13, certP12Path);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " writeRootRsa2048Cert saveFileResult " + zN0);
            if (zN0) {
                boolean zO0 = o0(14, certP12Secret);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " writeRootRsa2048Cert savePwdResult " + zO0);
                if (zO0) {
                    this.r = strU0;
                }
                return zO0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Byte> X() {
        ArrayList<Byte> arrayListJ0 = j0(19);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getDeviceRsa2048CertP12 result is " + arrayListJ0);
        return arrayListJ0;
    }

    public ArrayList<Byte> Y() {
        ArrayList<Byte> arrayListJ0 = j0(35);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getDeviceRsaCertP12 result is " + arrayListJ0);
        return arrayListJ0;
    }

    public ArrayList<Byte> Z() {
        ArrayList<Byte> arrayListJ0 = j0(38);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getDeviceRsaKeyP12 result is " + arrayListJ0);
        return arrayListJ0;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009f  */
    @Override // com.ts.appservice.pki.c.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a() {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.c.d.a():boolean");
    }

    public String a0() {
        return this.f24b.a();
    }

    public ArrayList<Byte> b0() {
        ArrayList<Byte> arrayListJ0 = j0(16);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getIdentityRsa2048CertP12 result is " + arrayListJ0);
        return arrayListJ0;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean c() throws Throwable {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.l == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceEcc256Cert mEcc256DeviceCertP12Info is null ");
            return false;
        }
        com.ts.appservice.pki.d.b.f("PkiDataManager", " mEcc256DeviceCertPath " + this.w);
        boolean zV = V(this.l.getCertP12Path(), 31);
        boolean zW = W(this.l.getCertP12Secret(), 32);
        boolean z = zV && zW;
        com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceEcc256Cert result is " + z + " isSameP12File " + zV + " isSameP12Secret " + zW);
        if (z) {
            boolean zR0 = r0(28, 29, 31, 32);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " checkDeviceEcc256Cert verifyCertResult " + zR0);
            if (zR0) {
                boolean zS0 = s0(this.w);
                if (zS0) {
                    p(this.w);
                }
                return zS0;
            }
        }
        return false;
    }

    public ArrayList<Byte> c0() {
        ArrayList<Byte> arrayListJ0 = j0(13);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getRootRsa2048CertP12 result is " + arrayListJ0);
        return arrayListJ0;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean d() throws Throwable {
        try {
            if (this.h == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceRsa2048Cert mRsa2048DeviceCertP12Info is null ");
                return false;
            }
            com.ts.appservice.pki.d.b.f("PkiDataManager", " mRsa2048DeviceCertPath " + this.t);
            boolean zV = V(this.h.getCertP12Path(), 19);
            boolean zW = W(this.h.getCertP12Secret(), 20);
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceRsa2048Cert result is  isSameP12File " + zV + " isSameP12Secret " + zW);
            boolean zT0 = zV && zW;
            if (zT0) {
                boolean zR0 = r0(16, 17, 19, 20);
                com.ts.appservice.pki.d.b.f("PkiDataManager", " checkDeviceRsa2048Cert verifyCertResult " + zR0);
                if (zR0) {
                    zT0 = t0(this.t, 10, 11);
                    com.ts.appservice.pki.d.b.f("PkiDataManager", " checkDeviceRsa2048Cert isCertPublicKeyLegal " + zT0);
                    if (zT0) {
                        p(this.t);
                    }
                }
            }
            return zT0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Byte> d0() {
        ArrayList<Byte> arrayListJ0 = j0(10);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getRsa2048PrivateKeyP12Data result is " + arrayListJ0);
        return arrayListJ0;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean e() throws Throwable {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.q == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceRsaCert mRsaDeviceCertP12Info is null ");
            return false;
        }
        com.ts.appservice.pki.d.b.f("PkiDataManager", "mRsaDeviceCertPath " + this.x);
        boolean zV = V(this.q.getCertP12Path(), 35);
        boolean zW = W(this.q.getCertP12Secret(), 36);
        com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceRsaCert result is  isSameP12File " + zV + " isSameP12Secret " + zW);
        if (zV && zW) {
            boolean zT0 = t0(this.x, 38, 39);
            com.ts.appservice.pki.d.b.f("PkiDataManager", "checkDeviceRsaCert: isCertPublicKeyLegal " + zT0);
            if (zT0) {
                p(this.x);
            }
            return zT0;
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<java.lang.Byte> e0(java.lang.String r3) {
        /*
            Method dump skipped, instruction units count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.appservice.pki.c.d.e0(java.lang.String):java.util.ArrayList");
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean f() throws Throwable {
        try {
            if (this.z == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceRsaKey mDeviceRsaKeyCertP12Info is null");
                return false;
            }
            com.ts.appservice.pki.d.b.f("PkiDataManager", "checkDeviceRsaKey mDeviceRsaKeyPath " + this.y);
            if (TextUtils.isEmpty(this.y)) {
                com.ts.appservice.pki.d.b.f("PkiDataManager", " checkDeviceRsaKey mDeviceRsaKeyPath is null ");
                return false;
            }
            boolean zV = V(this.z.getCertP12Path(), 38);
            boolean zW = W(this.z.getCertP12Secret(), 39);
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkDeviceRsaKey result is  isSameP12File " + zV + " isSameP12Secret " + zW);
            boolean z = zV && zW;
            if (z) {
                p(this.y);
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String f0() {
        return this.f24b.b();
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean h() {
        com.ts.appservice.pki.d.b.f("PkiDataManager", "checkEngineerPublicKey");
        if (this.A == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkEngineerPublicKey sourceData is null");
            return false;
        }
        ArrayList<Byte> arrayListJ0 = j0(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256);
        if (arrayListJ0 == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkEngineerPublicKey hasSaveData is null");
            return false;
        }
        boolean zD = com.ts.appservice.pki.d.a.d(arrayListJ0, this.A);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "checkEngineerPublicKey sameData：" + zD);
        return zD;
    }

    public ArrayList<Byte> h0() {
        ArrayList<Byte> arrayListJ0 = j0(22);
        com.ts.appservice.pki.d.b.f("PkiDataManager", "getTEEECC256PrivateKeyP12 result is " + arrayListJ0);
        return arrayListJ0;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean i() throws Throwable {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.k == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkIdentityEcc256Cert mEcc256IdentityCertP12Info is null ");
            return false;
        }
        com.ts.appservice.pki.d.b.f("PkiDataManager", " mEcc256IdentityCertPath " + this.v);
        boolean zV = V(this.k.getCertP12Path(), 28);
        boolean zW = W(this.k.getCertP12Secret(), 29);
        boolean z = zV && zW;
        com.ts.appservice.pki.d.b.d("PkiDataManager", "checkIdentityEcc256Cert result is " + z + " isSameP12File " + zV + " isSameP12Secret " + zW);
        if (z) {
            boolean zR0 = r0(25, 26, 28, 29);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " checkIdentityEcc256Cert " + zR0);
            if (zR0) {
                p(this.v);
            }
            return zR0;
        }
        return false;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean j() throws Throwable {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.g == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkIdentityRsa2048Cert mRsa2048IdentityCertP12Info is null ");
            return false;
        }
        com.ts.appservice.pki.d.b.f("PkiDataManager", " mRsa2048IdentityCertPath " + this.s);
        boolean zV = V(this.g.getCertP12Path(), 16);
        boolean zW = W(this.g.getCertP12Secret(), 17);
        com.ts.appservice.pki.d.b.d("PkiDataManager", "checkIdentityRsa2048Cert result is  isSameP12File " + zV + " isSameP12Secret " + zW);
        if (zV && zW) {
            boolean zR0 = r0(13, 14, 16, 17);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " verifyCertResult " + zR0);
            if (zR0) {
                p(this.s);
            }
            return zR0;
        }
        return false;
    }

    public ArrayList<Byte> j0(int i) {
        if (o()) {
            return null;
        }
        try {
            b.a.a.a.a aVarN = this.f23a.n(i);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " readData " + aVarN);
            if (aVarN.f6a != 0) {
                return null;
            }
            ArrayList<Byte> arrayList = aVarN.f7b;
            int size = arrayList.size() - 1;
            if (arrayList.get(size).byteValue() == 0) {
                arrayList.remove(size);
            }
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList));
            com.ts.appservice.pki.d.b.f("PkiDataManager", " s " + str);
            int i2 = Integer.parseInt(str.split(":")[1]);
            b.a.a.a.a aVarG = this.f23a.g(i, 0, i2);
            com.ts.appservice.pki.d.b.f("PkiDataManager", " keyId " + i + " dataLength " + i2 + StringUtils.SPACE + aVarG);
            ArrayList<Byte> arrayList2 = aVarG.f7b;
            Byte b2 = arrayList2.get(0);
            Byte b3 = arrayList2.get(1);
            int iByteValue = (b2.byteValue() * 127) + b3.byteValue();
            com.ts.appservice.pki.d.b.f("PkiDataManager", "readData keyId:" + i + " ,realDataLength:" + iByteValue + " ,height:" + b2 + " ,low:" + b3);
            List<Byte> listSubList = arrayList2.subList(2, iByteValue + 2);
            ArrayList<Byte> arrayList3 = new ArrayList<>();
            arrayList3.addAll(listSubList);
            StringBuilder sb = new StringBuilder();
            sb.append("readData keyId ");
            sb.append(i);
            sb.append(" realData ");
            sb.append(arrayList3);
            com.ts.appservice.pki.d.b.f("PkiDataManager", sb.toString());
            return arrayList3;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean k() throws Throwable {
        try {
            if (this.j == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "checkRootEcc256Cert mEcc256RootCertP12Info is null ");
                return false;
            }
            com.ts.appservice.pki.d.b.f("PkiDataManager", " mEcc256RootCertPath " + this.u);
            boolean zV = V(this.j.getCertP12Path(), 25);
            boolean zW = W(this.j.getCertP12Secret(), 26);
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkRootEcc256Cert result is  isSameP12File " + zV + " isSameP12Secret " + zW);
            boolean z = zV && zW;
            if (z) {
                p(this.u);
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean l() throws Throwable {
        try {
            if (this.f == null) {
                com.ts.appservice.pki.d.b.d("PkiDataManager", "checkRootRsa2048Cert mRsa2048RootCertP12Info is null ");
                return false;
            }
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkRootRsa2048Cert mRsa2048RootCertPath " + this.r);
            boolean zV = V(this.f.getCertP12Path(), 13);
            boolean zW = W(this.f.getCertP12Secret(), 14);
            com.ts.appservice.pki.d.b.d("PkiDataManager", "checkRootRsa2048Cert result is  isSameP12File " + zV + " isSameP12Secret " + zW);
            boolean z = zV && zW;
            if (z) {
                p(this.r);
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean q0(String str) {
        com.ts.appservice.pki.d.b.f("PkiDataManager", " updateAesKeyTee secretKey : " + str);
        if (TextUtils.isEmpty(str)) {
            com.ts.appservice.pki.d.b.f("PkiDataManager", " updateAesKeyTee secretKey is null");
            return false;
        }
        ArrayList<Byte> arrayListC = com.ts.appservice.pki.d.a.c(str);
        com.ts.appservice.pki.d.b.f("PkiDataManager", " updateAesKeyTee bytes " + arrayListC);
        HashMap<String, ArrayList<Byte>> mapG0 = g0(arrayListC);
        if (mapG0 == null) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", "updateAesKeyTee aesKeyData == null");
            return false;
        }
        boolean zW0 = w0(mapG0);
        com.ts.appservice.pki.d.b.f("PkiDataManager", " updateAesKeyTee " + str + " saveResult " + zW0);
        return zW0;
    }

    @Override // com.ts.appservice.pki.c.a
    public ArrayList<Byte> s() throws Throwable {
        try {
            if (this.i == null) {
                return null;
            }
            String csrPath = this.i.getCsrPath();
            com.ts.appservice.pki.d.b.f("PkiDataManager", "getEcc256P10File " + csrPath);
            ArrayList<Byte> arrayListF = com.ts.appservice.pki.d.a.f(csrPath);
            if (arrayListF != null) {
                T(arrayListF);
            }
            com.ts.appservice.pki.d.b.f("PkiDataManager", "getEcc256P10File " + arrayListF);
            return arrayListF;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean t() {
        CsrP12Info csrP12Info = this.i;
        if (csrP12Info == null || TextUtils.isEmpty(csrP12Info.getCsrPath())) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", " getEcc256P10FileGenerateState " + this.i);
            return false;
        }
        if (!this.o) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", " getEcc256P10FileGenerateState  mEcc256P10GenerateState " + this.o);
            return false;
        }
        boolean zExists = new File(this.i.getCsrPath()).exists();
        com.ts.appservice.pki.d.b.f("PkiDataManager", " getEcc256P10FileGenerateState  exists " + zExists);
        return zExists;
    }

    @Override // com.ts.appservice.pki.c.a
    public ArrayList<Byte> u() throws Throwable {
        CsrP12Info csrP12Info = this.e;
        if (csrP12Info == null) {
            com.ts.appservice.pki.d.b.f("PkiDataManager", "has not generate Rsa2048P10File");
            return null;
        }
        String csrPath = csrP12Info.getCsrPath();
        com.ts.appservice.pki.d.b.f("PkiDataManager", "sendRsa2048P10File " + csrPath);
        ArrayList<Byte> arrayListF = com.ts.appservice.pki.d.a.f(csrPath);
        if (arrayListF != null) {
            T(arrayListF);
        }
        com.ts.appservice.pki.d.b.f("PkiDataManager", "sendRsa2048P10File " + arrayListF);
        return arrayListF;
    }

    @Override // com.ts.appservice.pki.c.a
    public boolean v() {
        CsrP12Info csrP12Info = this.e;
        if (csrP12Info == null || TextUtils.isEmpty(csrP12Info.getCsrPath())) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", " getRsa2048P10FileGenerateState " + this.e);
            return false;
        }
        if (!this.n) {
            com.ts.appservice.pki.d.b.d("PkiDataManager", " getRsa2048P10FileGenerateState  mRsa2048P10GenerateState is false");
            return false;
        }
        boolean zExists = new File(this.e.getCsrPath()).exists();
        com.ts.appservice.pki.d.b.f("PkiDataManager", " getRsa2048P10FileGenerateState  exists " + zExists);
        return zExists;
    }
}
