package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f510a;
    public String b;

    public q6(boolean z, String str) {
        this.f510a = z;
        this.b = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof q6)) {
            return false;
        }
        q6 q6Var = (q6) obj;
        q6Var.getClass();
        if (this.f510a != q6Var.f510a) {
            return false;
        }
        String str = this.b;
        String str2 = q6Var.b;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int i = this.f510a ? 79 : 97;
        String str = this.b;
        return ((i + 59) * 59) + (str == null ? 43 : str.hashCode());
    }

    public String toString() {
        return "RegisterMAPSmsReceiverResult(mIsRegistered=" + this.f510a + ", mSms=" + this.b + ")";
    }
}
