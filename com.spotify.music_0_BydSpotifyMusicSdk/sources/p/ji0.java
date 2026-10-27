package p;

import com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason;

/* JADX INFO: loaded from: classes.dex */
public final class ji0 {
    public final EventLogger$FetchErrorReason a;
    public final String b;
    public final EventLogger$FetchErrorReason c;
    public final Integer d;
    public final String e;
    public final EventLogger$FetchErrorReason f;
    public final Integer g;
    public final String h;

    public ji0(EventLogger$FetchErrorReason eventLogger$FetchErrorReason, String str, EventLogger$FetchErrorReason eventLogger$FetchErrorReason2, Integer num, String str2, EventLogger$FetchErrorReason eventLogger$FetchErrorReason3, Integer num2, String str3) {
        this.a = eventLogger$FetchErrorReason;
        this.b = str;
        this.c = eventLogger$FetchErrorReason2;
        this.d = num;
        this.e = str2;
        this.f = eventLogger$FetchErrorReason3;
        this.g = num2;
        this.h = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ji0)) {
            return false;
        }
        ji0 ji0Var = (ji0) obj;
        return this.a == ji0Var.a && v03.b(this.b, ji0Var.b) && this.c == ji0Var.c && v03.b(this.d, ji0Var.d) && v03.b(this.e, ji0Var.e) && this.f == ji0Var.f && v03.b(this.g, ji0Var.g) && v03.b(this.h, ji0Var.h);
    }

    public final int hashCode() {
        EventLogger$FetchErrorReason eventLogger$FetchErrorReason = this.a;
        int iHashCode = (eventLogger$FetchErrorReason == null ? 0 : eventLogger$FetchErrorReason.hashCode()) * 31;
        String str = this.b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        EventLogger$FetchErrorReason eventLogger$FetchErrorReason2 = this.c;
        int iHashCode3 = (iHashCode2 + (eventLogger$FetchErrorReason2 == null ? 0 : eventLogger$FetchErrorReason2.hashCode())) * 31;
        Integer num = this.d;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.e;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        EventLogger$FetchErrorReason eventLogger$FetchErrorReason3 = this.f;
        int iHashCode6 = (iHashCode5 + (eventLogger$FetchErrorReason3 == null ? 0 : eventLogger$FetchErrorReason3.hashCode())) * 31;
        Integer num2 = this.g;
        int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str3 = this.h;
        return iHashCode7 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Error(wrapperErrorReason=");
        sb.append(this.a);
        sb.append(", wrapperErrorMessage=");
        sb.append(this.b);
        sb.append(", resolveErrorReason=");
        sb.append(this.c);
        sb.append(", resolveErrorCode=");
        sb.append(this.d);
        sb.append(", resolveErrorMessage=");
        sb.append(this.e);
        sb.append(", attributesErrorReason=");
        sb.append(this.f);
        sb.append(", attributesErrorCode=");
        sb.append(this.g);
        sb.append(", attributesErrorMessage=");
        return hf2.d(sb, this.h, ')');
    }

    public /* synthetic */ ji0(EventLogger$FetchErrorReason eventLogger$FetchErrorReason, String str, EventLogger$FetchErrorReason eventLogger$FetchErrorReason2, String str2, int i) {
        this((i & 1) != 0 ? null : eventLogger$FetchErrorReason, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : eventLogger$FetchErrorReason2, null, (i & 16) != 0 ? null : str2, null, null, null);
    }
}
