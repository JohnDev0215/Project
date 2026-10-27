package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\fJd\u0010\r\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/stingray/kca/model/ModelResponse;", "", "", "name", "modelPattern", "description", "", "isAlreadyRegistered", "acceptNewRegistration", "retailerId", "image", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/ModelResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class ModelResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13173b;
    public final String c;
    public final Boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Boolean f13174e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f13175f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f13176g;

    public ModelResponse() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public final ModelResponse copy(@o(name = "name") String name, @o(name = "modelPattern") String modelPattern, @o(name = "description") String description, @o(name = "isAlreadyRegistered") Boolean isAlreadyRegistered, @o(name = "acceptNewRegistration") Boolean acceptNewRegistration, @o(name = "retailerId") String retailerId, @o(name = "image") String image) {
        return new ModelResponse(name, modelPattern, description, isAlreadyRegistered, acceptNewRegistration, retailerId, image);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ModelResponse)) {
            return false;
        }
        ModelResponse modelResponse = (ModelResponse) obj;
        return m.a(this.f13172a, modelResponse.f13172a) && m.a(this.f13173b, modelResponse.f13173b) && m.a(this.c, modelResponse.c) && m.a(this.d, modelResponse.d) && m.a(this.f13174e, modelResponse.f13174e) && m.a(this.f13175f, modelResponse.f13175f) && m.a(this.f13176g, modelResponse.f13176g);
    }

    public final int hashCode() {
        String str = this.f13172a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13173b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.d;
        int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.f13174e;
        int iHashCode5 = (iHashCode4 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str4 = this.f13175f;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.f13176g;
        return iHashCode6 + (str5 != null ? str5.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ModelResponse(name=");
        sb.append(this.f13172a);
        sb.append(", modelPattern=");
        sb.append(this.f13173b);
        sb.append(", description=");
        sb.append(this.c);
        sb.append(", isAlreadyRegistered=");
        sb.append(this.d);
        sb.append(", acceptNewRegistration=");
        sb.append(this.f13174e);
        sb.append(", retailerId=");
        sb.append(this.f13175f);
        sb.append(", image=");
        return a.l(sb, this.f13176g, ")");
    }

    public ModelResponse(@o(name = "name") String str, @o(name = "modelPattern") String str2, @o(name = "description") String str3, @o(name = "isAlreadyRegistered") Boolean bool, @o(name = "acceptNewRegistration") Boolean bool2, @o(name = "retailerId") String str4, @o(name = "image") String str5) {
        this.f13172a = str;
        this.f13173b = str2;
        this.c = str3;
        this.d = bool;
        this.f13174e = bool2;
        this.f13175f = str4;
        this.f13176g = str5;
    }

    public /* synthetic */ ModelResponse(String str, String str2, String str3, Boolean bool, Boolean bool2, String str4, String str5, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : str3, (i7 & 8) != 0 ? null : bool, (i7 & 16) != 0 ? null : bool2, (i7 & 32) != 0 ? null : str4, (i7 & 64) != 0 ? null : str5);
    }
}
