package com.spotify.externalintegration.externalaccessory;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ExternalAccessoryDescription {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;

    public ExternalAccessoryDescription(@z61(name = "integration_type") String str, @z61(name = "client_id") String str2, @z61(name = "name") String str3, @z61(name = "transport_type") String str4, @z61(name = "connection_label") String str5, @z61(name = "category") String str6, @z61(name = "company") String str7, @z61(name = "model") String str8, @z61(name = "version") String str9, @z61(name = "protocol") String str10, @z61(name = "sender_id") String str11) {
        v03.j(str, "integrationType");
        v03.j(str2, "clientId");
        v03.j(str3, "name");
        v03.j(str4, "transportType");
        v03.j(str5, "connectionLabel");
        v03.j(str6, "category");
        v03.j(str7, "company");
        v03.j(str8, "model");
        v03.j(str9, "version");
        v03.j(str10, "protocol");
        v03.j(str11, "senderId");
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = str9;
        this.j = str10;
        this.k = str11;
    }

    public final ExternalAccessoryDescription copy(@z61(name = "integration_type") String str, @z61(name = "client_id") String str2, @z61(name = "name") String str3, @z61(name = "transport_type") String str4, @z61(name = "connection_label") String str5, @z61(name = "category") String str6, @z61(name = "company") String str7, @z61(name = "model") String str8, @z61(name = "version") String str9, @z61(name = "protocol") String str10, @z61(name = "sender_id") String str11) {
        v03.j(str, "integrationType");
        v03.j(str2, "clientId");
        v03.j(str3, "name");
        v03.j(str4, "transportType");
        v03.j(str5, "connectionLabel");
        v03.j(str6, "category");
        v03.j(str7, "company");
        v03.j(str8, "model");
        v03.j(str9, "version");
        v03.j(str10, "protocol");
        v03.j(str11, "senderId");
        return new ExternalAccessoryDescription(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExternalAccessoryDescription)) {
            return false;
        }
        ExternalAccessoryDescription externalAccessoryDescription = (ExternalAccessoryDescription) obj;
        return v03.b(this.a, externalAccessoryDescription.a) && v03.b(this.b, externalAccessoryDescription.b) && v03.b(this.c, externalAccessoryDescription.c) && v03.b(this.d, externalAccessoryDescription.d) && v03.b(this.e, externalAccessoryDescription.e) && v03.b(this.f, externalAccessoryDescription.f) && v03.b(this.g, externalAccessoryDescription.g) && v03.b(this.h, externalAccessoryDescription.h) && v03.b(this.i, externalAccessoryDescription.i) && v03.b(this.j, externalAccessoryDescription.j) && v03.b(this.k, externalAccessoryDescription.k);
    }

    public final int hashCode() {
        return this.k.hashCode() + hf2.b(this.j, hf2.b(this.i, hf2.b(this.h, hf2.b(this.g, hf2.b(this.f, hf2.b(this.e, hf2.b(this.d, hf2.b(this.c, hf2.b(this.b, this.a.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ExternalAccessoryDescription(integrationType=");
        sb.append(this.a);
        sb.append(", clientId=");
        sb.append(this.b);
        sb.append(", name=");
        sb.append(this.c);
        sb.append(", transportType=");
        sb.append(this.d);
        sb.append(", connectionLabel=");
        sb.append(this.e);
        sb.append(", category=");
        sb.append(this.f);
        sb.append(", company=");
        sb.append(this.g);
        sb.append(", model=");
        sb.append(this.h);
        sb.append(", version=");
        sb.append(this.i);
        sb.append(", protocol=");
        sb.append(this.j);
        sb.append(", senderId=");
        return hf2.d(sb, this.k, ')');
    }

    public /* synthetic */ ExternalAccessoryDescription(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7, (i & 128) != 0 ? "" : str8, (i & 256) != 0 ? "" : str9, (i & 512) != 0 ? "" : str10, (i & 1024) == 0 ? str11 : "");
    }
}
