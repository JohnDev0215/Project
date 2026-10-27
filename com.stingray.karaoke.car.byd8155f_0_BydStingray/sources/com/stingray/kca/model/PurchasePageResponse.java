package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/kca/model/PurchasePageResponse;", "", "", "purchasePageId", "<init>", "(Ljava/lang/String;)V", "copy", "(Ljava/lang/String;)Lcom/stingray/kca/model/PurchasePageResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class PurchasePageResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13191a;

    /* JADX WARN: Multi-variable type inference failed */
    public PurchasePageResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final PurchasePageResponse copy(@o(name = "purchasePageId") String purchasePageId) {
        return new PurchasePageResponse(purchasePageId);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PurchasePageResponse) && m.a(this.f13191a, ((PurchasePageResponse) obj).f13191a);
    }

    public final int hashCode() {
        String str = this.f13191a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return a.l(new StringBuilder("PurchasePageResponse(purchasePageId="), this.f13191a, ")");
    }

    public PurchasePageResponse(@o(name = "purchasePageId") String str) {
        this.f13191a = str;
    }

    public /* synthetic */ PurchasePageResponse(String str, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str);
    }
}
