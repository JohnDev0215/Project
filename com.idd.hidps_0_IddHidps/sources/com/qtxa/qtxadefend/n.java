package com.qtxa.qtxadefend;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.jar.JarFile;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f134a;

    n(Context context) {
        this.f134a = context;
    }

    public static Certificate[] a(String str, Context context, File file, boolean z) throws Throwable {
        Certificate[] certificateArrA;
        JarFile jarFile = null;
        try {
            certificateArrA = (Certificate[]) CertificateFactory.getInstance("X.509").generateCertificates(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray())).toArray(new Certificate[0]);
        } catch (Throwable th) {
            th.printStackTrace();
            certificateArrA = null;
        }
        if (certificateArrA != null) {
            return certificateArrA;
        }
        if (file != null) {
            try {
                jarFile = new JarFile(file, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                certificateArrA = new i(jarFile).a();
                if (jarFile != null) {
                    jarFile.close();
                }
                return certificateArrA;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return certificateArrA;
    }

    private boolean b(Certificate[] certificateArr) {
        return certificateArr != null && certificateArr.length == 1;
    }

    boolean c(String str, File file) throws Throwable {
        Certificate[] certificateArrA = a(str, this.f134a, file, false);
        if (certificateArrA == null) {
            w.d("isValidSignUsingPms:certs=null");
            return true;
        }
        w.d("isValidSignUsingPms1");
        return b(certificateArrA) && a.j().g(certificateArrA[0].getPublicKey().getEncoded()) == 1;
    }
}
