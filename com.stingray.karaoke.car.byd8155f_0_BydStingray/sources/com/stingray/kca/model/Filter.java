package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\u0007\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/stingray/kca/model/Filter;", "", "", "id", "label", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/Filter;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class Filter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13091a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13092b;

    /* JADX WARN: Multi-variable type inference failed */
    public Filter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final Filter copy(@o(name = "id") String id, @o(name = "label") String label) {
        return new Filter(id, label);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Filter)) {
            return false;
        }
        Filter filter = (Filter) obj;
        return m.a(this.f13091a, filter.f13091a) && m.a(this.f13092b, filter.f13092b);
    }

    public final int hashCode() {
        String str = this.f13091a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13092b;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        return "Filter(id=" + this.f13091a + ", label=" + this.f13092b + ")";
    }

    public Filter(@o(name = "id") String str, @o(name = "label") String str2) {
        this.f13091a = str;
        this.f13092b = str2;
    }

    public /* synthetic */ Filter(String str, String str2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2);
    }
}
