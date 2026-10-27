package p;

/* JADX INFO: loaded from: classes.dex */
public final class pc {
    public final String a;
    public final String b;
    public final Boolean c;
    public final Integer d;
    public final String e;
    public final long f;
    public final long g;

    public pc(String str, String str2, Boolean bool, Integer num, String str3, long j, long j2) {
        this.a = str;
        this.b = str2;
        this.c = bool;
        this.d = num;
        this.e = str3;
        this.f = j;
        this.g = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pc)) {
            return false;
        }
        pc pcVar = (pc) obj;
        return v03.b(this.a, pcVar.a) && v03.b(this.b, pcVar.b) && v03.b(this.c, pcVar.c) && v03.b(this.d, pcVar.d) && v03.b(this.e, pcVar.e) && this.f == pcVar.f && this.g == pcVar.g;
    }

    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        Boolean bool = this.c;
        int iHashCode = (iB + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num = this.d;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.e;
        return Long.hashCode(this.g) + ((Long.hashCode(this.f) + ((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31)) * 31);
    }

    public final String toString() {
        return "AssignedPropertyValue(name=" + this.a + ", scope=" + this.b + ", boolValue=" + this.c + ", intValue=" + this.d + ", enumValue=" + this.e + ", groupId=" + this.f + ", policyId=" + this.g + ')';
    }
}
