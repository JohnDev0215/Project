package com.ts.appservice.pki.c;

import android.os.SystemProperties;
import com.byd.pki.filling.BydPKIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.a.a.a.b f23a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f24b;
    private Gson c = new Gson();
    private Type d = new C0001a(this).getType();

    /* JADX INFO: renamed from: com.ts.appservice.pki.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0001a extends TypeToken<HashMap<String, String>> {
        C0001a(a aVar) {
        }
    }

    private boolean E(int i, int i2, int i3, ArrayList<Byte> arrayList) {
        if (o()) {
            return false;
        }
        try {
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList));
            com.ts.appservice.pki.d.b.f("BaseManager", " certData " + str);
            b.a.a.a.a aVarD = this.f23a.d(i3, str, i);
            com.ts.appservice.pki.d.b.f("BaseManager", " saveCertToHal certId " + Integer.toHexString(i) + " boschResult " + aVarD);
            return aVarD.f6a == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private byte[] q(String str, String str2) {
        String strTranslateAesKey = BydPKIUtils.translateAesKey(Hex.decode(str2));
        com.ts.appservice.pki.d.b.f("BaseManager", str + " getAesKey aesKey: " + strTranslateAesKey);
        return strTranslateAesKey.getBytes();
    }

    public ArrayList<Byte> A(int i, int i2) {
        b.a.a.a.a aVarS;
        if (o()) {
            return null;
        }
        try {
            aVarS = this.f23a.s(i, i2);
            com.ts.appservice.pki.d.b.f("BaseManager", "readCertFromHal keyType " + i + " certId " + Integer.toHexString(i2) + " result " + aVarS.f6a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (aVarS.f6a != 0) {
            com.ts.appservice.pki.d.b.d("BaseManager", "readCertFromHal fail");
            return null;
        }
        ArrayList<Byte> arrayList = aVarS.f7b;
        if (arrayList != null) {
            com.ts.appservice.pki.d.b.f("BaseManager", "readCertFromHal success , cert length is " + arrayList.size());
        } else {
            com.ts.appservice.pki.d.b.d("BaseManager", "readCertFromHal data is null");
        }
        return arrayList;
    }

    public abstract boolean B();

    public abstract boolean C();

    public boolean D(ArrayList<Byte> arrayList, int i) {
        if (this.f23a == null) {
            com.ts.appservice.pki.d.b.d("BaseManager", "mTeeService == null , keyId " + i + "  save to aesKey fail ");
            return false;
        }
        if (arrayList == null) {
            com.ts.appservice.pki.d.b.d("BaseManager", "keyid " + i + " , save to aesKey data is null ，fail ");
            return false;
        }
        b.a.a.a.a aVarH = null;
        try {
            com.ts.appservice.pki.d.b.f("BaseManager", " createData DATA_ENCRYPT : 1");
            com.ts.appservice.pki.d.b.d("BaseManager", "  aesKey keyId " + i + " createResult " + this.f23a.q(i, arrayList.size(), 0, 1));
            aVarH = this.f23a.h(i, 0, arrayList);
            com.ts.appservice.pki.d.b.d("BaseManager", "  aesKey keyId " + i + " writeData saveResult " + aVarH.f6a);
            if (aVarH.f6a == 0) {
                com.ts.appservice.pki.d.b.f("BaseManager", "save to aesKey success " + i);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.ts.appservice.pki.d.b.d("BaseManager", i + "  save to aesKey fail , saveResult " + aVarH);
        return false;
    }

    public boolean F(int i, ArrayList<Byte> arrayList) {
        return E(i, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, 2, arrayList);
    }

    public boolean G(int i, ArrayList<Byte> arrayList) {
        return E(i, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, 0, arrayList);
    }

    public boolean H(int i, ArrayList<Byte> arrayList) {
        return E(i, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, 0, arrayList);
    }

    public void I(b.a.a.a.b bVar) {
        this.f23a = bVar;
    }

    public abstract boolean J(ArrayList<Byte> arrayList);

    public abstract boolean K(ArrayList<Byte> arrayList);

    public abstract boolean L(ArrayList<Byte> arrayList);

    public abstract boolean M(ArrayList<Byte> arrayList);

    public abstract boolean N(ArrayList<Byte> arrayList);

    public abstract boolean O(ArrayList<Byte> arrayList);

    public abstract boolean P(ArrayList<Byte> arrayList);

    public abstract boolean Q(ArrayList<Byte> arrayList);

    public abstract boolean R(ArrayList<Byte> arrayList);

    public abstract boolean S(ArrayList<Byte> arrayList);

    public abstract boolean a();

    public boolean b(ArrayList<Byte> arrayList, int i, int i2) {
        if (o()) {
            return false;
        }
        boolean zY = y(arrayList, i2, i);
        com.ts.appservice.pki.d.b.d("BaseManager", "checkCert result is " + zY);
        return zY;
    }

    public abstract boolean c();

    public abstract boolean d();

    public abstract boolean e();

    public abstract boolean f();

    public boolean g(ArrayList<Byte> arrayList, int i) {
        boolean zB = b(arrayList, i, 2);
        com.ts.appservice.pki.d.b.f("BaseManager", " checkEcc256Cert  certId " + Integer.toHexString(i) + " result " + zB);
        return zB;
    }

    public abstract boolean h();

    public abstract boolean i();

    public abstract boolean j();

    public abstract boolean k();

    public abstract boolean l();

    public boolean m(ArrayList<Byte> arrayList, int i) {
        boolean zB = b(arrayList, i, 0);
        com.ts.appservice.pki.d.b.f("BaseManager", " checkRsa2048Cert  certId " + Integer.toHexString(i) + " result " + zB);
        return zB;
    }

    public boolean n(ArrayList<Byte> arrayList, int i) {
        boolean zB = b(arrayList, i, 0);
        com.ts.appservice.pki.d.b.f("BaseManager", " checkRsaCert  certId " + Integer.toHexString(i) + " result " + zB);
        return zB;
    }

    public boolean o() {
        if (this.f23a != null) {
            return false;
        }
        com.ts.appservice.pki.d.b.d("BaseManager", "mTeeService == null");
        return true;
    }

    public boolean p(String str) {
        try {
            com.ts.appservice.pki.d.b.f("BaseManager", " deleteFile " + str);
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            com.ts.appservice.pki.d.b.f("BaseManager", " deleteFile file is not exist, " + str);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, ArrayList<Byte>> r(String str) {
        HashMap<String, ArrayList<Byte>> map = null;
        try {
            HashMap map2 = (HashMap) this.c.fromJson(str.substring(1, str.length() - 1).replace("},{", ","), this.d);
            if (map2 == null) {
                com.ts.appservice.pki.d.b.d("BaseManager", "aesKeyMap is null");
                return null;
            }
            String str2 = (String) map2.get("Key_128");
            com.ts.appservice.pki.d.b.f("BaseManager", "Key_128 String : " + str2);
            String str3 = (String) map2.get("Key_256");
            com.ts.appservice.pki.d.b.f("BaseManager", "Key_256 String : " + str3);
            String str4 = (String) map2.get("Key_SecOC");
            com.ts.appservice.pki.d.b.f("BaseManager", "Key_SecOC String : " + str4);
            HashMap<String, ArrayList<Byte>> map3 = new HashMap<>();
            try {
                map3.put("Key_128", com.ts.appservice.pki.d.a.a(q("Key_128", str2)));
                map3.put("Key_256", com.ts.appservice.pki.d.a.a(q("Key_256", str3)));
                map3.put("Key_SecOC", com.ts.appservice.pki.d.a.a(q("Key_SecOC", str4)));
                return map3;
            } catch (Exception e) {
                e = e;
                map = map3;
                e.printStackTrace();
                return map;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public abstract ArrayList<Byte> s();

    public abstract boolean t();

    public abstract ArrayList<Byte> u();

    public abstract boolean v();

    public void w(b.a.a.a.b bVar, b bVar2) {
        this.f23a = bVar;
        this.f24b = bVar2;
        File file = new File("/data/pki_data/");
        if (file.exists()) {
            return;
        }
        com.ts.appservice.pki.d.b.f("BaseManager", " mkdirs " + file.mkdirs() + "/data/pki_data/");
    }

    public boolean x() {
        String str = SystemProperties.get("persist.vendor.bosch.tee.force");
        boolean z = !"1".equals(str);
        com.ts.appservice.pki.d.b.f("BaseManager", "isDeviceHasHsm value= " + str + " ,isHsm= " + z);
        return z;
    }

    public boolean y(ArrayList<Byte> arrayList, int i, int i2) {
        try {
            if (arrayList != null) {
                ArrayList<Byte> arrayListA = A(i, i2);
                if (arrayListA != null) {
                    if (arrayList.size() != arrayListA.size()) {
                        com.ts.appservice.pki.d.b.d("BaseManager", "is not same cert file，size is not equal");
                        com.ts.appservice.pki.d.b.d("BaseManager", "sourceData " + arrayList.size() + new String(com.ts.appservice.pki.d.a.b(arrayList)));
                        com.ts.appservice.pki.d.b.d("BaseManager", "hasSaveData " + arrayListA.size() + new String(com.ts.appservice.pki.d.a.b(arrayListA)));
                        return false;
                    }
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        if (!arrayListA.get(i3).equals(arrayList.get(i3))) {
                            com.ts.appservice.pki.d.b.d("BaseManager", "is not same cert file");
                            com.ts.appservice.pki.d.b.d("BaseManager", "sourceData " + arrayList.size() + new String(com.ts.appservice.pki.d.a.b(arrayList)));
                            com.ts.appservice.pki.d.b.d("BaseManager", "hasSaveData " + arrayListA.size() + new String(com.ts.appservice.pki.d.a.b(arrayListA)));
                            return false;
                        }
                    }
                    return true;
                }
            } else {
                com.ts.appservice.pki.d.b.d("BaseManager", "Cert sourceData is null " + Integer.toHexString(i2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Byte> z(int i) {
        if (o()) {
            return null;
        }
        try {
            b.a.a.a.a aVarN = this.f23a.n(i);
            com.ts.appservice.pki.d.b.f("BaseManager", " readAesKeyFromHal " + aVarN);
            if (aVarN.f6a != 0) {
                return null;
            }
            ArrayList<Byte> arrayList = aVarN.f7b;
            int size = arrayList.size() - 1;
            if (arrayList.get(size).byteValue() == 0) {
                arrayList.remove(size);
            }
            String str = new String(com.ts.appservice.pki.d.a.b(arrayList));
            com.ts.appservice.pki.d.b.f("BaseManager", " str " + str);
            int i2 = Integer.parseInt(str.split(":")[1]);
            b.a.a.a.a aVarG = this.f23a.g(i, 0, i2);
            com.ts.appservice.pki.d.b.f("BaseManager", " readAesKeyFromHal boschResult" + aVarG);
            ArrayList<Byte> arrayList2 = aVarG.f7b;
            ArrayList<Byte> arrayList3 = new ArrayList<>(64);
            for (int i3 = 0; i3 < 64; i3++) {
                arrayList3.add(arrayList2.get(i3));
            }
            com.ts.appservice.pki.d.b.f("BaseManager", " readAesKeyFromHal aesKeyList64" + arrayList3);
            return arrayList3;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
