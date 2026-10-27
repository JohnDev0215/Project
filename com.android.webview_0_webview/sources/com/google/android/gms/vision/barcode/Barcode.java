package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import defpackage.AbstractC1116lf;
import defpackage.C0189Kj;
import defpackage.C0207Lj;
import defpackage.C0242Nj;
import defpackage.C0260Oj;
import defpackage.C0278Pj;
import defpackage.C0296Qj;
import defpackage.C0314Rj;
import defpackage.C0332Sj;
import defpackage.C0350Tj;
import defpackage.C0368Uj;
import defpackage.C0386Vj;
import defpackage.C0404Wj;
import defpackage.C0422Xj;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Barcode extends zza {
    public static final Parcelable.Creator CREATOR = new C0207Lj();
    public WiFi A;
    public UrlBookmark B;
    public GeoPoint C;
    public CalendarEvent D;
    public ContactInfo E;
    public DriverLicense F;
    public byte[] G;
    public int s;
    public String t;
    public String u;
    public int v;
    public Point[] w;
    public Email x;
    public Phone y;
    public Sms z;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class Address extends zza {
        public static final Parcelable.Creator CREATOR = new C0189Kj();
        public int s;
        public String[] t;

        public Address(int i, String[] strArr) {
            this.s = i;
            this.t = strArr;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.l(parcel, 2, this.s);
            String[] strArr = this.t;
            if (strArr != null) {
                int i2 = AbstractC1116lf.i(parcel, 3);
                parcel.writeStringArray(strArr);
                AbstractC1116lf.j(parcel, i2);
            }
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class CalendarDateTime extends zza {
        public static final Parcelable.Creator CREATOR = new C0260Oj();
        public int s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public boolean y;
        public String z;

        public CalendarDateTime(int i, int i2, int i3, int i4, int i5, int i6, boolean z, String str) {
            this.s = i;
            this.t = i2;
            this.u = i3;
            this.v = i4;
            this.w = i5;
            this.x = i6;
            this.y = z;
            this.z = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.l(parcel, 2, this.s);
            AbstractC1116lf.l(parcel, 3, this.t);
            AbstractC1116lf.l(parcel, 4, this.u);
            AbstractC1116lf.l(parcel, 5, this.v);
            AbstractC1116lf.l(parcel, 6, this.w);
            AbstractC1116lf.l(parcel, 7, this.x);
            AbstractC1116lf.f(parcel, 8, this.y);
            AbstractC1116lf.e(parcel, 9, this.z, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class CalendarEvent extends zza {
        public static final Parcelable.Creator CREATOR = new C0242Nj();
        public String s;
        public String t;
        public String u;
        public String v;
        public String w;
        public CalendarDateTime x;
        public CalendarDateTime y;

        public CalendarEvent(String str, String str2, String str3, String str4, String str5, CalendarDateTime calendarDateTime, CalendarDateTime calendarDateTime2) {
            this.s = str;
            this.t = str2;
            this.u = str3;
            this.v = str4;
            this.w = str5;
            this.x = calendarDateTime;
            this.y = calendarDateTime2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.e(parcel, 2, this.s, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.e(parcel, 4, this.u, false);
            AbstractC1116lf.e(parcel, 5, this.v, false);
            AbstractC1116lf.e(parcel, 6, this.w, false);
            AbstractC1116lf.d(parcel, 7, this.x, i, false);
            AbstractC1116lf.d(parcel, 8, this.y, i, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class ContactInfo extends zza {
        public static final Parcelable.Creator CREATOR = new C0296Qj();
        public PersonName s;
        public String t;
        public String u;
        public Phone[] v;
        public Email[] w;
        public String[] x;
        public Address[] y;

        public ContactInfo(PersonName personName, String str, String str2, Phone[] phoneArr, Email[] emailArr, String[] strArr, Address[] addressArr) {
            this.s = personName;
            this.t = str;
            this.u = str2;
            this.v = phoneArr;
            this.w = emailArr;
            this.x = strArr;
            this.y = addressArr;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.d(parcel, 2, this.s, i, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.e(parcel, 4, this.u, false);
            AbstractC1116lf.g(parcel, 5, this.v, i);
            AbstractC1116lf.g(parcel, 6, this.w, i);
            String[] strArr = this.x;
            if (strArr != null) {
                int i2 = AbstractC1116lf.i(parcel, 7);
                parcel.writeStringArray(strArr);
                AbstractC1116lf.j(parcel, i2);
            }
            AbstractC1116lf.g(parcel, 8, this.y, i);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class DriverLicense extends zza {
        public static final Parcelable.Creator CREATOR = new C0278Pj();
        public String A;
        public String B;
        public String C;
        public String D;
        public String E;
        public String F;
        public String s;
        public String t;
        public String u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;

        public DriverLicense(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
            this.s = str;
            this.t = str2;
            this.u = str3;
            this.v = str4;
            this.w = str5;
            this.x = str6;
            this.y = str7;
            this.z = str8;
            this.A = str9;
            this.B = str10;
            this.C = str11;
            this.D = str12;
            this.E = str13;
            this.F = str14;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.e(parcel, 2, this.s, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.e(parcel, 4, this.u, false);
            AbstractC1116lf.e(parcel, 5, this.v, false);
            AbstractC1116lf.e(parcel, 6, this.w, false);
            AbstractC1116lf.e(parcel, 7, this.x, false);
            AbstractC1116lf.e(parcel, 8, this.y, false);
            AbstractC1116lf.e(parcel, 9, this.z, false);
            AbstractC1116lf.e(parcel, 10, this.A, false);
            AbstractC1116lf.e(parcel, 11, this.B, false);
            AbstractC1116lf.e(parcel, 12, this.C, false);
            AbstractC1116lf.e(parcel, 13, this.D, false);
            AbstractC1116lf.e(parcel, 14, this.E, false);
            AbstractC1116lf.e(parcel, 15, this.F, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class Email extends zza {
        public static final Parcelable.Creator CREATOR = new C0332Sj();
        public int s;
        public String t;
        public String u;
        public String v;

        public Email(int i, String str, String str2, String str3) {
            this.s = i;
            this.t = str;
            this.u = str2;
            this.v = str3;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.l(parcel, 2, this.s);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.e(parcel, 4, this.u, false);
            AbstractC1116lf.e(parcel, 5, this.v, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class GeoPoint extends zza {
        public static final Parcelable.Creator CREATOR = new C0314Rj();
        public double s;
        public double t;

        public GeoPoint(double d, double d2) {
            this.s = d;
            this.t = d2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            double d = this.s;
            AbstractC1116lf.k(parcel, 2, 8);
            parcel.writeDouble(d);
            double d2 = this.t;
            AbstractC1116lf.k(parcel, 3, 8);
            parcel.writeDouble(d2);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class PersonName extends zza {
        public static final Parcelable.Creator CREATOR = new C0368Uj();
        public String s;
        public String t;
        public String u;
        public String v;
        public String w;
        public String x;
        public String y;

        public PersonName(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.s = str;
            this.t = str2;
            this.u = str3;
            this.v = str4;
            this.w = str5;
            this.x = str6;
            this.y = str7;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.e(parcel, 2, this.s, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.e(parcel, 4, this.u, false);
            AbstractC1116lf.e(parcel, 5, this.v, false);
            AbstractC1116lf.e(parcel, 6, this.w, false);
            AbstractC1116lf.e(parcel, 7, this.x, false);
            AbstractC1116lf.e(parcel, 8, this.y, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class Phone extends zza {
        public static final Parcelable.Creator CREATOR = new C0350Tj();
        public int s;
        public String t;

        public Phone(int i, String str) {
            this.s = i;
            this.t = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.l(parcel, 2, this.s);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class Sms extends zza {
        public static final Parcelable.Creator CREATOR = new C0404Wj();
        public String s;
        public String t;

        public Sms(String str, String str2) {
            this.s = str;
            this.t = str2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.e(parcel, 2, this.s, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class UrlBookmark extends zza {
        public static final Parcelable.Creator CREATOR = new C0386Vj();
        public String s;
        public String t;

        public UrlBookmark(String str, String str2) {
            this.s = str;
            this.t = str2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.e(parcel, 2, this.s, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class WiFi extends zza {
        public static final Parcelable.Creator CREATOR = new C0422Xj();
        public String s;
        public String t;
        public int u;

        public WiFi(String str, String str2, int i) {
            this.s = str;
            this.t = str2;
            this.u = i;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iN = AbstractC1116lf.n(parcel);
            AbstractC1116lf.e(parcel, 2, this.s, false);
            AbstractC1116lf.e(parcel, 3, this.t, false);
            AbstractC1116lf.l(parcel, 4, this.u);
            AbstractC1116lf.j(parcel, iN);
        }
    }

    public Barcode(int i, String str, String str2, int i2, Point[] pointArr, Email email, Phone phone, Sms sms, WiFi wiFi, UrlBookmark urlBookmark, GeoPoint geoPoint, CalendarEvent calendarEvent, ContactInfo contactInfo, DriverLicense driverLicense, byte[] bArr) {
        this.s = i;
        this.t = str;
        this.G = bArr;
        this.u = str2;
        this.v = i2;
        this.w = pointArr;
        this.x = email;
        this.y = phone;
        this.z = sms;
        this.A = wiFi;
        this.B = urlBookmark;
        this.C = geoPoint;
        this.D = calendarEvent;
        this.E = contactInfo;
        this.F = driverLicense;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iN = AbstractC1116lf.n(parcel);
        AbstractC1116lf.l(parcel, 2, this.s);
        AbstractC1116lf.e(parcel, 3, this.t, false);
        AbstractC1116lf.e(parcel, 4, this.u, false);
        AbstractC1116lf.l(parcel, 5, this.v);
        AbstractC1116lf.g(parcel, 6, this.w, i);
        AbstractC1116lf.d(parcel, 7, this.x, i, false);
        AbstractC1116lf.d(parcel, 8, this.y, i, false);
        AbstractC1116lf.d(parcel, 9, this.z, i, false);
        AbstractC1116lf.d(parcel, 10, this.A, i, false);
        AbstractC1116lf.d(parcel, 11, this.B, i, false);
        AbstractC1116lf.d(parcel, 12, this.C, i, false);
        AbstractC1116lf.d(parcel, 13, this.D, i, false);
        AbstractC1116lf.d(parcel, 14, this.E, i, false);
        AbstractC1116lf.d(parcel, 15, this.F, i, false);
        byte[] bArr = this.G;
        if (bArr != null) {
            int i2 = AbstractC1116lf.i(parcel, 16);
            parcel.writeByteArray(bArr);
            AbstractC1116lf.j(parcel, i2);
        }
        AbstractC1116lf.j(parcel, iN);
    }
}
