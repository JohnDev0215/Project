package com.ts.appservice.pki.b;

import android.content.Context;
import com.ts.appservice.pki.c.c;
import com.ts.appservice.pki.c.d;
import com.ts.lib.pki.IPkiSdkManager;
import com.ts.lib.pki.bean.PkiFileData;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b extends IPkiSdkManager.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f21a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f22b;
    private final Object c = new Object();

    public b(Context context) {
    }

    public void a(d dVar, c cVar) {
        this.f21a = dVar;
        this.f22b = cVar;
    }

    public String decodeEccHsm(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " decodeEccHSM.in " + str);
        String strU = this.f22b.U(str);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " decodeEccHSM.out=" + strU);
        return strU;
    }

    public String decodeRsaHsm(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " decodeRsaHsm.in " + str);
        String strV = this.f22b.V(str);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " decodeRsaHsm.out=" + strV);
        return strV;
    }

    public String encryptStrHsm(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " encryptStrHsm.in");
        String strW = this.f22b.W(str);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " encryptStrHsm.out=" + strW);
        return strW;
    }

    public String getAesKey(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getAESKey.in type : " + str);
        String strY = this.f22b.Y(str);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getAESKey.out= " + strY);
        return strY;
    }

    public PkiFileData getDeviceRsa2048CertP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsa2048CertP12.in");
        ArrayList<Byte> arrayListX = this.f21a.X();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListX);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsa2048CertP12.out=");
        return pkiFileData;
    }

    public PkiFileData getDeviceRsaCertHsm() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsaCertHSM.in");
        ArrayList<Byte> arrayListZ = this.f22b.Z();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListZ);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsaCertHSM.out = ");
        return pkiFileData;
    }

    public PkiFileData getDeviceRsaCertP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsaCertP12.in");
        ArrayList<Byte> arrayListY = this.f21a.Y();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListY);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsaCertP12.out=");
        return pkiFileData;
    }

    public PkiFileData getDeviceRsaKeyP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsaKeyP12.in");
        ArrayList<Byte> arrayListZ = this.f21a.Z();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListZ);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getDeviceRsaKeyP12.out=");
        return pkiFileData;
    }

    public PkiFileData getEngineerPublicKey() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getEngineerPublicKey.in");
        ArrayList<Byte> arrayListA0 = this.f22b.a0();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListA0);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getEngineerPublicKey.out =  data " + arrayListA0);
        return pkiFileData;
    }

    public PkiFileData getHalRsa2048IdentityCertData() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getHalRsa2048IdentityCertData.in");
        ArrayList<Byte> arrayListB0 = this.f22b.b0();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListB0);
        StringBuilder sb = new StringBuilder();
        sb.append(" getHalRsa2048IdentityCertData.out = data length : ");
        sb.append(arrayListB0 == null ? 0 : arrayListB0.size());
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", sb.toString());
        return pkiFileData;
    }

    public PkiFileData getIdentityRsa2048CertP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getIdentityRsa2048CertP12.in");
        ArrayList<Byte> arrayListB0 = this.f21a.b0();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListB0);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getIdentityRsa2048CertP12.out=");
        return pkiFileData;
    }

    public PkiFileData getRootRsa2048CertP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getRootRsa2048CertP12.in");
        ArrayList<Byte> arrayListC0 = this.f21a.c0();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListC0);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getRootRsa2048CertP12.out=");
        return pkiFileData;
    }

    public PkiFileData getRsa2048PrivateKeyP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getRsa2048PrivateKeyP12.in");
        ArrayList<Byte> arrayListD0 = this.f21a.d0();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListD0);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getRsa2048PrivateKeyP12.out=");
        return pkiFileData;
    }

    public PkiFileData getSecretForP12(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getSecretForP12.in");
        ArrayList<Byte> arrayListE0 = this.f21a.e0(str);
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListE0);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getSecretForP12.out=");
        return pkiFileData;
    }

    public PkiFileData getTeeEcc256PrivateKeyP12() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getTeeEcc256PrivateKeyP12.in");
        ArrayList<Byte> arrayListH0 = this.f21a.h0();
        PkiFileData pkiFileData = new PkiFileData();
        pkiFileData.setData(arrayListH0);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " getTeeEcc256PrivateKeyP12.out=");
        return pkiFileData;
    }

    public boolean isDeviceHasHsm() {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " isDeviceHasHsm.in");
        boolean zX = this.f21a.x();
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " isDeviceHasHsm.out=" + zX);
        return zX;
    }

    public String signEccHsm(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " signEccHsm.in");
        String strE0 = this.f22b.e0(str);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " signEccHsm.out = " + strE0);
        return strE0;
    }

    public boolean updateAesKeyHsm(String str) {
        boolean zF0;
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " upateAESKeyHSM.in");
        synchronized (this.c) {
            zF0 = this.f22b.f0(str);
        }
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " upateAESKeyHSM.out=" + zF0);
        return zF0;
    }

    public boolean updateAesKeyTee(String str) {
        boolean zQ0;
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " upateAesKeyTee.in");
        synchronized (this.c) {
            zQ0 = this.f21a.q0(str);
        }
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " upateAesKeyTee.out=" + zQ0);
        return zQ0;
    }

    public boolean verifyCertHsm(String str) {
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " verifyCertHSM.in");
        boolean zG0 = this.f22b.g0(str);
        com.ts.appservice.pki.d.b.f("PkiSdkBinder", " verifyCertHSM.out=" + zG0);
        return zG0;
    }
}
