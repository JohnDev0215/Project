package com.google.i18n.phonenumbers.prefixmapper;

import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: PrefixFileReader.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f4158a = Logger.getLogger(d.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MappingFileProvider f4160c = new MappingFileProvider();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Map<String, PhonePrefixMap> f4161d = new HashMap();

    public d(String str) throws Throwable {
        this.f4159b = str;
        d();
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                f4158a.log(Level.WARNING, e2.toString());
            }
        }
    }

    private PhonePrefixMap c(int i, String str, String str2, String str3) throws Throwable {
        String fileName = this.f4160c.getFileName(i, str, str2, str3);
        if (fileName.length() == 0) {
            return null;
        }
        if (!this.f4161d.containsKey(fileName)) {
            e(fileName);
        }
        return this.f4161d.get(fileName);
    }

    private void d() throws Throwable {
        ObjectInputStream objectInputStream = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(d.class.getResourceAsStream(this.f4159b + "config"));
                try {
                    this.f4160c.readExternal(objectInputStream2);
                    a(objectInputStream2);
                } catch (IOException e2) {
                    e = e2;
                    objectInputStream = objectInputStream2;
                    f4158a.log(Level.WARNING, e.toString());
                    a(objectInputStream);
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    a(objectInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    private void e(String str) throws Throwable {
        ?? r1 = 0;
        ObjectInputStream objectInputStream = null;
        try {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(d.class.getResourceAsStream(this.f4159b + str));
                try {
                    PhonePrefixMap phonePrefixMap = new PhonePrefixMap();
                    phonePrefixMap.readExternal(objectInputStream2);
                    Map<String, PhonePrefixMap> map = this.f4161d;
                    map.put(str, phonePrefixMap);
                    a(objectInputStream2);
                    r1 = map;
                } catch (IOException e2) {
                    e = e2;
                    objectInputStream = objectInputStream2;
                    f4158a.log(Level.WARNING, e.toString());
                    a(objectInputStream);
                    r1 = objectInputStream;
                } catch (Throwable th) {
                    th = th;
                    r1 = objectInputStream2;
                    a(r1);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean f(String str) {
        return (str.equals("zh") || str.equals("ja") || str.equals("ko")) ? false : true;
    }

    public String b(Phonenumber$PhoneNumber phonenumber$PhoneNumber, String str, String str2, String str3) throws Throwable {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        if (countryCode == 1) {
            countryCode = ((int) (phonenumber$PhoneNumber.getNationalNumber() / 10000000)) + 1000;
        }
        PhonePrefixMap phonePrefixMapC = c(countryCode, str, str2, str3);
        String strLookup = phonePrefixMapC != null ? phonePrefixMapC.lookup(phonenumber$PhoneNumber) : null;
        if ((strLookup == null || strLookup.length() == 0) && f(str)) {
            PhonePrefixMap phonePrefixMapC2 = c(countryCode, "en", "", "");
            if (phonePrefixMapC2 == null) {
                return "";
            }
            strLookup = phonePrefixMapC2.lookup(phonenumber$PhoneNumber);
        }
        return strLookup != null ? strLookup : "";
    }
}
