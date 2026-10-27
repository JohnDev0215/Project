package com.google.i18n.phonenumbers.h;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import com.google.i18n.phonenumbers.prefixmapper.d;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: PhoneNumberOfflineGeocoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f4142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f4143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final PhoneNumberUtil f4144c = PhoneNumberUtil.k();

    a(String str) {
        this.f4143b = null;
        this.f4143b = new d(str);
    }

    private String a(Phonenumber$PhoneNumber phonenumber$PhoneNumber, Locale locale) {
        List<String> listV = this.f4144c.v(phonenumber$PhoneNumber.getCountryCode());
        if (listV.size() == 1) {
            return e(listV.get(0), locale);
        }
        String str = "ZZ";
        for (String str2 : listV) {
            if (this.f4144c.y(phonenumber$PhoneNumber, str2)) {
                if (!str.equals("ZZ")) {
                    return "";
                }
                str = str2;
            }
        }
        return e(str, locale);
    }

    public static synchronized a d() {
        if (f4142a == null) {
            f4142a = new a("/com/google/i18n/phonenumbers/geocoding/data/");
        }
        return f4142a;
    }

    private String e(String str, Locale locale) {
        return (str == null || str.equals("ZZ") || str.equals("001")) ? "" : new Locale("", str).getDisplayCountry(locale);
    }

    public String b(Phonenumber$PhoneNumber phonenumber$PhoneNumber, Locale locale) {
        PhoneNumberUtil.PhoneNumberType phoneNumberTypeQ = this.f4144c.q(phonenumber$PhoneNumber);
        return phoneNumberTypeQ == PhoneNumberUtil.PhoneNumberType.UNKNOWN ? "" : !this.f4144c.w(phoneNumberTypeQ, phonenumber$PhoneNumber.getCountryCode()) ? a(phonenumber$PhoneNumber, locale) : c(phonenumber$PhoneNumber, locale);
    }

    public String c(Phonenumber$PhoneNumber phonenumber$PhoneNumber, Locale locale) throws Throwable {
        String strB;
        Phonenumber$PhoneNumber phonenumber$PhoneNumberJ;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String strJ = PhoneNumberUtil.j(phonenumber$PhoneNumber.getCountryCode());
        String strO = this.f4144c.o(phonenumber$PhoneNumber);
        if (strJ.equals("") || !strO.startsWith(strJ)) {
            strB = this.f4143b.b(phonenumber$PhoneNumber, language, "", country);
        } else {
            try {
                phonenumber$PhoneNumberJ = this.f4144c.J(strO.substring(strJ.length()), this.f4144c.s(phonenumber$PhoneNumber.getCountryCode()));
            } catch (NumberParseException unused) {
                phonenumber$PhoneNumberJ = phonenumber$PhoneNumber;
            }
            strB = this.f4143b.b(phonenumber$PhoneNumberJ, language, "", country);
        }
        return strB.length() > 0 ? strB : a(phonenumber$PhoneNumber, locale);
    }
}
