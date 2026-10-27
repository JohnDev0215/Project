package c.a.j.f;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.ota.IUpdateListener;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.hardware.bydauto.pki.PkiManager;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import c.a.j.d;
import com.byd.ota.master.VehicleVersionInfo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: BydActionUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f2846a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2847b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PkiManager f2848c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public OtaUpgradeManager f2849d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoVersionDevice f2850e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BYDAutoSpeedDevice f2851f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public BYDAutoGearboxDevice f2852g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public BYDAutoEnergyDevice f2853h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BYDAutoBodyworkDevice f2854i;
    public i j;
    public j k;
    public h l;
    public boolean m;
    public d.m n;
    public IUpdateListener o = new a();

    /* JADX INFO: compiled from: BydActionUtils.java */
    public class a implements IUpdateListener {
        public a() {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onDownloadBackupFail(int i2) {
            c.a.k.b.c("USB-BydActionUtils onDownloadBackupFail->errorCode = " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onDownloadBackupProgress(int i2) {
            c.a.k.b.c("USB-BydActionUtils onDownloadBackupProgress->progress = " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onDownloadBackupSuccess() {
            c.a.k.b.c("USB-BydActionUtils onDownloadBackupSuccess");
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onNotify(Bundle bundle) {
            bundle.getString("progress", "0");
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTBOXConnectState(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxExportLogProgress(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxExportLogResult(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxUpdateProgress(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxUpdateResult(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onUpdateFail(int i2) {
            c.a.k.b.c("USB-BydActionUtils onUpdateFail->errorCode = " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onUpdateProgress(int i2) {
            c.this.n.onUpdateProgress(i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onUpdateSuccess() {
            c.a.k.b.c("USB-BydActionUtils onUpdateSuccess");
            c.this.n.a(true);
        }
    }

    public c(Context context) {
        this.m = false;
        this.f2847b = context;
        this.f2848c = PkiManager.getInstance(context);
        this.f2849d = OtaUpgradeManager.getInstance(context);
        this.f2850e = BYDAutoVersionDevice.getInstance(context);
        this.f2851f = BYDAutoSpeedDevice.getInstance(context);
        this.f2852g = BYDAutoGearboxDevice.getInstance(context);
        this.f2853h = BYDAutoEnergyDevice.getInstance(context);
        this.f2854i = BYDAutoBodyworkDevice.getInstance(context);
        this.m = false;
    }

    public static String F(byte[] bArr) throws Exception {
        return g.e("5d21b0b95049e9ac45baf24cc91d269d9cec4d355d186efb6f45b20f2e2f7af0", g.a.f.j.d.d(bArr), Boolean.TRUE);
    }

    public static void e(String str, String str2, String str3, String str4) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(g.a.f.j.d.a(str3), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(str4.getBytes(StandardCharsets.UTF_8)));
        FileInputStream fileInputStream = new FileInputStream(str);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(str2), cipher);
        byte[] bArr = new byte[10485760];
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 == -1) {
                fileInputStream.close();
                cipherOutputStream.close();
                return;
            }
            cipherOutputStream.write(bArr, 0, i2);
        }
    }

    public static byte[] f(String str, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            if (publicKey == null) {
                return null;
            }
            cipher.init(2, publicKey);
            return cipher.doFinal(g.a.f.j.a.a(str));
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        } catch (BadPaddingException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static synchronized c p(Context context) {
        if (f2846a == null) {
            f2846a = new c(context);
        }
        return f2846a;
    }

    public static String v(X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        g.a.f.k.c.b bVar = new g.a.f.k.c.b("CERTIFICATE", x509Certificate.getEncoded());
        StringWriter stringWriter = new StringWriter();
        g.a.f.k.c.d dVar = new g.a.f.k.c.d(stringWriter);
        dVar.d(bVar);
        dVar.close();
        stringWriter.close();
        return stringWriter.toString();
    }

    public void A(String str, String str2) {
        try {
            c.a.k.b.c("saveCurrentVersion num = " + str + " path = " + str2);
            String strE = f.e(new File(str2), ".times");
            if (strE != null) {
                new File(strE).renameTo(new File(str2 + "/" + str + ".times"));
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void B(String str, String str2) {
        try {
            c.a.k.b.c("saveCurrentVersion ver = " + str + " path = " + str2);
            new File(str2 + "/" + str + ".version").createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void C(String str) {
        try {
            c.a.k.b.c("saveCurrentVersion num = " + str);
            new File(this.f2847b.getFilesDir().getPath() + "/" + str + ".times").createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void D() {
        c.a.k.b.b("setAdbFlag flag... enable adb");
        Settings.Global.putInt(this.f2847b.getContentResolver(), "adb_enabled", 1);
        Settings.Global.putInt(this.f2847b.getContentResolver(), "adb_wifi_enabled", 1);
        String str = SystemProperties.get("persist.sv.debug.adb_enable");
        c.a.k.b.b("adbEnable " + str);
        if ("0".equals(str)) {
            SystemProperties.set("persist.sv.debug.adb_enable", VehicleVersionInfo.FORCE_UPGRADE);
        }
    }

    public void E() {
        Bundle bundle = new Bundle();
        bundle.putInt("usbUpdate", 1);
        this.f2849d.executeCMD(bundle);
        this.f2849d.startUpdate(null);
    }

    public int G() {
        y();
        try {
            String strE = f.e(new File("/data/vendor/nfs/nfs_ota/usb/"), "upgradeConfig.ini");
            c.a.k.b.c("universal upgradeConfig : " + strE);
            if (strE != null) {
                this.l = e.a("/data/vendor/nfs/nfs_ota/usb/upgradeConfig.ini");
                return 3;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            String strE2 = f.e(new File("/data/vendor/nfs/nfs_ota/usb/"), "upgradeConfigSign.ini");
            String strE3 = f.e(new File("/data/vendor/nfs/nfs_ota/usb/"), "upgradeSign.ini");
            if (strE2 == null || strE3 == null) {
                return 1;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        this.j = e.b("/data/vendor/nfs/nfs_ota/usb/upgradeConfigSign.ini");
        this.k = e.c("/data/vendor/nfs/nfs_ota/usb/upgradeSign.ini");
        if (!H(this.j, "/data/vendor/nfs/nfs_ota/usb/upgradeSign.ini", null, null)) {
            return 2;
        }
        try {
            String strE4 = f.e(new File("/data/vendor/nfs/nfs_ota/usb/"), ".zip_enc");
            if (strE4 == null) {
                return 1;
            }
            return !I(this.k, strE4, null, null) ? 1 : 0;
        } catch (Exception e4) {
            e4.printStackTrace();
            return 1;
        }
    }

    public boolean H(i iVar, String str, Certificate certificate, Certificate certificate2) {
        try {
            String strD = g.d(g.c(str));
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(g.a.f.j.a.a(iVar.c())));
            String strD2 = g.a.f.j.d.d(f(iVar.b(), x509Certificate.getPublicKey()));
            if (strD2.length() > 64) {
                if (!strD.equals(strD2.substring(strD2.length() - 64, strD2.length()))) {
                    c.a.k.b.c("USB-BydActionUtils 升级包verify fail : ");
                    return false;
                }
                c.a.k.b.c("USB-BydActionUtils 升级包verify pass ");
            }
            boolean zVerifyCertHSM = this.f2848c.verifyCertHSM(v(x509Certificate));
            c.a.k.b.c("USB-BydActionUtils verifyCertHSM result: " + zVerifyCertHSM);
            return zVerifyCertHSM;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean I(j jVar, String str, Certificate certificate, Certificate certificate2) {
        try {
            String strD = g.d(g.c(str));
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(g.a.f.j.a.a(jVar.f())));
            String strD2 = g.a.f.j.d.d(f(jVar.e(), x509Certificate.getPublicKey()));
            if (strD2.length() > 64) {
                if (!strD.equals(strD2.substring(strD2.length() - 64, strD2.length()))) {
                    c.a.k.b.c("USB-BydActionUtils 升级包verify fail : ");
                    return false;
                }
                c.a.k.b.c("USB-BydActionUtils 升级包verify pass ");
            }
            boolean zVerifyCertHSM = this.f2848c.verifyCertHSM(v(x509Certificate));
            c.a.k.b.c("USB-BydActionUtils verifyCertHSM result: " + zVerifyCertHSM);
            return zVerifyCertHSM;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean J(h hVar, String str) {
        if (hVar.g() == null || hVar.f() == null) {
            return false;
        }
        try {
            String strD = g.d(str);
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(g.a.f.j.a.a(hVar.g())));
            String strD2 = g.a.f.j.d.d(f(hVar.f(), x509Certificate.getPublicKey()));
            if (strD2.length() > 64) {
                if (!strD.equals(strD2.substring(strD2.length() - 64, strD2.length()))) {
                    c.a.k.b.b("USB-BydActionUtils 升级包verify fail !!! ");
                    return false;
                }
                c.a.k.b.c("USB-BydActionUtils 升级包verify pass ");
            }
            c.a.k.b.c("USB-BydActionUtils verifyCertHSM result: " + this.f2848c.verifyCertHSM(v(x509Certificate)));
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public boolean a(String str) {
        String strDecodeEccHSM = this.f2848c.decodeEccHSM(str);
        boolean z = false;
        if (strDecodeEccHSM == null || strDecodeEccHSM.isEmpty()) {
            c.a.k.b.c("decodeEccHSM eccStr : " + strDecodeEccHSM);
            return false;
        }
        String[] strArrSplit = new String(g.a.f.j.a.a(strDecodeEccHSM)).split(":");
        if (strArrSplit.length < 2) {
            c.a.k.b.c("str.length < 2");
            return false;
        }
        String str2 = strArrSplit[0];
        String str3 = strArrSplit[1];
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        long j = Long.parseLong(str2);
        long j2 = (Long.parseLong(str3) * 24 * 3600 * 1000) + j;
        if (lValueOf.longValue() > j && lValueOf.longValue() < j2) {
            z = true;
        }
        if (!z) {
            c.a.k.b.b("AdbFlag file is out of date!!! ");
        }
        return z;
    }

    public boolean b(String str) {
        String strDecodeEccHSM = this.f2848c.decodeEccHSM(str);
        boolean z = false;
        if (strDecodeEccHSM != null && !strDecodeEccHSM.isEmpty()) {
            String[] strArrSplit = new String(g.a.f.j.a.a(strDecodeEccHSM)).split(":");
            if (strArrSplit.length < 2) {
                return false;
            }
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            Long lValueOf = Long.valueOf(System.currentTimeMillis());
            long j = Long.parseLong(str2);
            long j2 = (Long.parseLong(str3) * 24 * 3600 * 1000) + j;
            if (lValueOf.longValue() > j && lValueOf.longValue() < j2) {
                z = true;
            }
            if (!z) {
                c.a.k.b.b("Authorized file is out of date!!! ");
            }
        }
        return z;
    }

    public boolean c() {
        float currentSpeed = (float) this.f2851f.getCurrentSpeed();
        int gearboxAutoModeType = this.f2852g.getGearboxAutoModeType();
        int ePBState = this.f2852g.getEPBState();
        int sOCTarget = this.f2853h.getSOCTarget();
        int doorState = this.f2854i.getDoorState(5);
        c.a.k.b.c("checkCarState speed= " + currentSpeed + " gearType=" + gearboxAutoModeType + " epbState=" + ePBState + " socValue=" + sOCTarget + " doorState=" + doorState);
        if (new Float(currentSpeed).compareTo(new Float(0.0f)) != 0) {
            return false;
        }
        if (gearboxAutoModeType != 1 && gearboxAutoModeType != 0) {
            return false;
        }
        if (ePBState != 7 && ePBState != 3) {
            return false;
        }
        if (sOCTarget > 20 || sOCTarget == 0) {
            return doorState == -1 || doorState == 0;
        }
        return false;
    }

    public boolean d(String str) {
        try {
            String strE = f.e(new File(str), ".version");
            c.a.k.b.c("checkRecoveryFlag path = " + str + " version.file = " + strE);
            if (strE == null) {
                return false;
            }
            String strReplace = new File(strE).getName().replace(".version", "");
            int iCompareTo = strReplace.compareTo(o());
            c.a.k.b.c("checkRecoveryFlag result = " + iCompareTo + " name = " + strReplace);
            if (iCompareTo > 0) {
                return true;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public boolean g(String str) {
        boolean z = false;
        try {
            String strF = F(this.k.d().getBytes(StandardCharsets.UTF_8));
            c.a.k.b.c("start decrypt ...");
            String strE = f.e(new File("/data/vendor/nfs/nfs_ota/usb/"), ".zip_enc");
            if (strE != null) {
                e(strE, str + "/decrypt.zip", strF, this.k.c());
                z = true;
                this.k.a();
                this.j.a();
                c.a.k.b.c("decrypt file finish!!!!");
            } else {
                c.a.k.b.c("decrypt file, src == null");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }

    public boolean h(String str) {
        try {
            String strF = F(g.a.f.j.a.a(this.l.d()));
            c.a.k.b.c("decrypt universal start decrypt, secretKey = " + strF);
            String strE = f.e(new File("/data/vendor/nfs/nfs_ota/usb/"), ".zip_enc");
            if (strE == null) {
                c.a.k.b.c("decrypt universal, src == null");
                return false;
            }
            File file = new File(strE);
            if (!String.valueOf(file.length()).equals(this.l.c())) {
                c.a.k.b.b("file.length is not the same!!!! len:" + String.valueOf(file.length()));
                return false;
            }
            String strB = g.b(file);
            if (!J(this.l, strB)) {
                c.a.k.b.b("verify signature fail!!!");
                return false;
            }
            if (!strB.equals(this.l.e())) {
                c.a.k.b.b("file.getSha256 is not the same!!!!");
                return false;
            }
            c.a.k.b.c("decrypt universal decoder before");
            e(strE, str + "/decrypt.zip", strF, new String(g.a.f.j.a.a(this.l.b())));
            c.a.k.b.c("decrypt universal decoder after");
            this.l.a();
            c.a.k.b.c("decrypt universal file finish!!!!");
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean i() {
        String strDecodeEccHSM = this.f2848c.decodeEccHSM(this.k.b());
        if (strDecodeEccHSM == null || strDecodeEccHSM.isEmpty()) {
            c.a.k.b.c("decryptSecretIV, eccStr=" + strDecodeEccHSM);
            return false;
        }
        for (String str : new String(g.a.f.j.a.a(strDecodeEccHSM)).split(";")) {
            if (str.length() < 7) {
                c.a.k.b.c("decryptSecretIV, str.length()=" + str.length());
                return false;
            }
            if (str.substring(0, 3).equals("IV:")) {
                this.k.l(str.substring(3, str.length()));
            } else if (str.substring(0, 7).equals("Secret:")) {
                this.k.n(str.substring(7, str.length()));
            }
        }
        return true;
    }

    public void j() {
        File[] fileArrListFiles = new File(this.f2847b.getFilesDir().getPath()).listFiles(new FilenameFilter() { // from class: c.a.j.f.b
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.endsWith(".auto.upgrade");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file : fileArrListFiles) {
            c.a.b.d.d.g(file.getPath());
        }
    }

    public void k() {
        try {
            String strE = f.e(new File(this.f2847b.getFilesDir().getPath()), ".times");
            if (strE != null) {
                new File(strE).delete();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void l() {
        File[] fileArrListFiles = new File(this.f2847b.getFilesDir().getPath()).listFiles(new FilenameFilter() { // from class: c.a.j.f.a
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.endsWith(".version");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file : fileArrListFiles) {
            c.a.b.d.d.g(file.getPath());
        }
    }

    public void m() {
        Settings.Global.putInt(this.f2847b.getContentResolver(), "adb_enabled", 0);
        Settings.Global.putInt(this.f2847b.getContentResolver(), "adb_wifi_enabled", 0);
        String str = SystemProperties.get("persist.sv.debug.adb_enable");
        c.a.k.b.b("adbEnable " + str);
        if (VehicleVersionInfo.FORCE_UPGRADE.equals(str)) {
            SystemProperties.set("persist.sv.debug.adb_enable", "0");
        }
        c.a.k.b.b("disableAdb end ");
    }

    public void n(String str, String str2) {
        String[] strArrSplit = str.split(";");
        c.a.k.b.c("executeCmd length = " + strArrSplit.length);
        if (strArrSplit.length > 1) {
            if (!g.d(g.d(strArrSplit[0]) + "yu.shishi.20230802").equals(strArrSplit[1])) {
                c.a.k.b.c("executeCmd command is modified by someone.");
                return;
            }
            String[] strArrSplit2 = strArrSplit[0].split(":");
            c.a.k.b.c("cmd length = " + strArrSplit2.length);
            try {
                for (String strReplace : strArrSplit2) {
                    if (strReplace.contains("UDisk")) {
                        strReplace = strReplace.replace("UDisk", str2);
                    }
                    Runtime.getRuntime().exec(new String[]{"sh", "-c", strReplace});
                    Thread.sleep(100L);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public String o() {
        String str = SystemProperties.get("ro.product.version.soc.name", "99999999");
        c.a.k.b.c("getCurrentVersion version = " + str);
        return str;
    }

    public boolean q() {
        return c.a.b.d.d.o(this.f2847b.getFilesDir().getPath(), "1.auto.upgrade");
    }

    public String r() {
        try {
            String strE = f.e(new File(this.f2847b.getFilesDir().getPath()), ".times");
            if (strE != null) {
                return new File(strE).getName();
            }
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean s() {
        return Long.valueOf(System.currentTimeMillis()).longValue() < Long.parseLong(this.k.g()) + (((Long.parseLong(this.k.h()) * 24) * 3600) * 1000);
    }

    public void w() {
        Bundle bundle = new Bundle();
        bundle.putInt("rebootQNX", 1);
        bundle.putInt("usbUpdate", 0);
        this.f2849d.executeCMD(bundle);
    }

    public void x() {
        c.a.k.b.b("send recovery broadcast ");
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "MasterClearConfirm");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", true);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", true);
        this.f2847b.sendBroadcast(intent);
    }

    public void y() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.f2849d.registerListener(this.o);
    }

    public void z(d.m mVar) {
        this.n = mVar;
    }
}
