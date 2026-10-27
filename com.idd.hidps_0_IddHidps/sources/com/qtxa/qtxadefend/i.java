package com.qtxa.qtxadefend;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
class i {
    private static WeakReference<byte[]> c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final JarFile f127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f126b = new Object();
    private static final String[] d = {"READ_CALENDAR", "WRITE_CALENDAR", "CAMERA", "READ_CONTACTS", "WRITE_CONTACTS", "GET_ACCOUNTS", "ACCESS_FINE_LOCATION", "ACCESS_COARSE_LOCATION", "RECORD_AUDIO", "CALL_PHONE", "READ_CALL_LOG", "WRITE_CALL_LOG", "SEND_SMS", "RECEIVE_SMS", "READ_SMS", "RECEIVE_MMS"};
    private static final HashSet<String> e = new HashSet<>(new ArrayList(Arrays.asList(d)));

    public i(JarFile jarFile) {
        this.f127a = jarFile;
    }

    public static byte[] b(File file, String str) throws Throwable {
        WeakReference<byte[]> weakReference;
        DigestInputStream digestInputStream;
        byte[] bArr;
        FileInputStream fileInputStream;
        Object obj = f126b;
        synchronized (obj) {
            weakReference = c;
            digestInputStream = null;
            if (weakReference != null) {
                c = null;
                bArr = weakReference.get();
            } else {
                bArr = null;
            }
            if (bArr == null) {
                bArr = new byte[8192];
                weakReference = new WeakReference<>(bArr);
            }
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            fileInputStream = new FileInputStream(file);
            try {
                DigestInputStream digestInputStream2 = new DigestInputStream(fileInputStream, messageDigest);
                do {
                    try {
                    } catch (Throwable th) {
                        th = th;
                        digestInputStream = digestInputStream2;
                        synchronized (obj) {
                            c = weakReference;
                        }
                        if (digestInputStream != null) {
                            digestInputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } while (digestInputStream2.read(bArr, 0, bArr.length) != -1);
                byte[] bArrDigest = messageDigest.digest();
                synchronized (obj) {
                    c = weakReference;
                }
                digestInputStream2.close();
                fileInputStream.close();
                return bArrDigest;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    private static List<String> c(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            XmlResourceParser xmlResourceParserOpenXmlResourceParser = context.createPackageContext(str, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
            while (true) {
                int next = xmlResourceParserOpenXmlResourceParser.next();
                if (next == 1) {
                    break;
                }
                if (next == 2 && xmlResourceParserOpenXmlResourceParser.getDepth() == 2 && xmlResourceParserOpenXmlResourceParser.getName().equals("uses-permission")) {
                    for (int i = 0; i < xmlResourceParserOpenXmlResourceParser.getAttributeCount(); i++) {
                        arrayList.add(xmlResourceParserOpenXmlResourceParser.getAttributeValue(i));
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException | XmlPullParserException unused) {
        }
        return arrayList;
    }

    static List<String> d(Context context, String str) {
        List<String> listC = c(context, str);
        ArrayList arrayList = new ArrayList();
        for (String str2 : listC) {
            if (str2.startsWith("android.permission.")) {
                String strSubstring = str2.substring(19);
                if (e.contains(strSubstring)) {
                    arrayList.add(strSubstring);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x008d: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:45:0x008d */
    Certificate[] a() throws Throwable {
        InputStream inputStream;
        InputStream inputStream2;
        InputStream inputStream3;
        JarFile jarFile = this.f127a;
        InputStream inputStream4 = null;
        try {
            try {
                Enumeration<JarEntry> enumerationEntries = jarFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                    if (!jarEntryNextElement.isDirectory()) {
                        String name = jarEntryNextElement.getName();
                        if (name.startsWith("META-INF/") && (name.endsWith(".RSA") || name.endsWith(".DSA"))) {
                            InputStream inputStream5 = jarFile.getInputStream(jarEntryNextElement);
                            try {
                                Certificate[] certificateArr = (Certificate[]) CertificateFactory.getInstance("X.509").generateCertificates(inputStream5).toArray(new Certificate[0]);
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                return certificateArr;
                            } catch (SecurityException e2) {
                                inputStream2 = inputStream5;
                                e = e2;
                                w.f(e.toString());
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                return null;
                            } catch (CertificateException e3) {
                                inputStream = inputStream5;
                                e = e3;
                                w.f(e.toString());
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                return null;
                            } catch (Throwable th) {
                                inputStream4 = inputStream5;
                                th = th;
                                if (inputStream4 != null) {
                                    inputStream4.close();
                                }
                                throw th;
                            }
                        }
                    }
                }
                throw new IOException("empty jar file");
            } catch (Throwable th2) {
                th = th2;
                inputStream4 = inputStream3;
            }
        } catch (SecurityException e4) {
            e = e4;
            inputStream2 = null;
        } catch (CertificateException e5) {
            e = e5;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
