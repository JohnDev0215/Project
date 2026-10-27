package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.framework.smartlock.CustomeInformationResultType;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CustomeInformationResultType f490a;
    public o6 b;

    public p6(CustomeInformationResultType customeInformationResultType, o6 o6Var) {
        this.f490a = customeInformationResultType;
        this.b = o6Var;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p6)) {
            return false;
        }
        p6 p6Var = (p6) obj;
        p6Var.getClass();
        CustomeInformationResultType customeInformationResultType = this.f490a;
        CustomeInformationResultType customeInformationResultType2 = p6Var.f490a;
        if (customeInformationResultType != null ? !customeInformationResultType.equals(customeInformationResultType2) : customeInformationResultType2 != null) {
            return false;
        }
        o6 o6Var = this.b;
        o6 o6Var2 = p6Var.b;
        return o6Var != null ? o6Var.equals(o6Var2) : o6Var2 == null;
    }

    public int hashCode() {
        CustomeInformationResultType customeInformationResultType = this.f490a;
        int iHashCode = customeInformationResultType == null ? 43 : customeInformationResultType.hashCode();
        o6 o6Var = this.b;
        return ((iHashCode + 59) * 59) + (o6Var != null ? o6Var.hashCode() : 43);
    }

    public String toString() {
        return "CustomerInformationResult(mResultType=" + this.f490a + ", mInformation=" + this.b + ")";
    }
}
