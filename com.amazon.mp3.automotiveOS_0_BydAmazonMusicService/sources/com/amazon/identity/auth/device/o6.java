package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f475a;
    public String b = null;
    public String c;
    public String d;

    public o6(String str, String str2, String str3, String str4) {
        this.f475a = str;
        this.c = str3;
        this.d = str4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof o6)) {
            return false;
        }
        o6 o6Var = (o6) obj;
        o6Var.getClass();
        String str = this.f475a;
        String str2 = o6Var.f475a;
        if (str != null ? !str.equals(str2) : str2 != null) {
            return false;
        }
        String str3 = this.b;
        String str4 = o6Var.b;
        if (str3 != null ? !str3.equals(str4) : str4 != null) {
            return false;
        }
        String str5 = this.c;
        String str6 = o6Var.c;
        if (str5 != null ? !str5.equals(str6) : str6 != null) {
            return false;
        }
        String str7 = this.d;
        String str8 = o6Var.d;
        return str7 != null ? str7.equals(str8) : str8 == null;
    }

    public int hashCode() {
        String str = this.f475a;
        int iHashCode = str == null ? 43 : str.hashCode();
        String str2 = this.b;
        int iHashCode2 = ((iHashCode + 59) * 59) + (str2 == null ? 43 : str2.hashCode());
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 * 59) + (str3 == null ? 43 : str3.hashCode());
        String str4 = this.d;
        return (iHashCode3 * 59) + (str4 != null ? str4.hashCode() : 43);
    }

    public String toString() {
        return "CustomerInformation(mName=" + this.f475a + ", mNamePron=" + this.b + ", mEmail=" + this.c + ", mPhoneNumber=" + this.d + ")";
    }
}
