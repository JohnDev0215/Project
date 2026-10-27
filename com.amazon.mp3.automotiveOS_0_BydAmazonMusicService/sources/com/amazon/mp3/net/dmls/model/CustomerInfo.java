package com.amazon.mp3.net.dmls.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CustomerInfo.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0002! B+\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\u0013\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R*\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\""}, d2 = {"Lcom/amazon/mp3/net/dmls/model/CustomerInfo;", "", "Lorg/json/JSONArray;", "entitlementListToJSONArray", "Lorg/json/JSONObject;", "toJSONObject", "", "toString", "", "hashCode", "other", "", "equals", "marketplaceId", "Ljava/lang/String;", "getMarketplaceId", "()Ljava/lang/String;", "setMarketplaceId", "(Ljava/lang/String;)V", "territoryId", "getTerritoryId", "setTerritoryId", "", "Lcom/amazon/mp3/net/dmls/model/Entitlement;", "entitlementList", "Ljava/util/List;", "getEntitlementList", "()Ljava/util/List;", "setEntitlementList", "(Ljava/util/List;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Companion", "Builder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class CustomerInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private List<? extends Entitlement> entitlementList;
    private String marketplaceId;
    private String territoryId;

    @JvmStatic
    public static final Builder builder() {
        return INSTANCE.builder();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerInfo)) {
            return false;
        }
        CustomerInfo customerInfo = (CustomerInfo) other;
        return Intrinsics.areEqual(this.marketplaceId, customerInfo.marketplaceId) && Intrinsics.areEqual(this.territoryId, customerInfo.territoryId) && Intrinsics.areEqual(this.entitlementList, customerInfo.entitlementList);
    }

    public int hashCode() {
        String str = this.marketplaceId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.territoryId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<? extends Entitlement> list = this.entitlementList;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "CustomerInfo(marketplaceId=" + ((Object) this.marketplaceId) + ", territoryId=" + ((Object) this.territoryId) + ", entitlementList=" + this.entitlementList + ')';
    }

    public CustomerInfo(String str, String str2, List<? extends Entitlement> list) {
        this.marketplaceId = str;
        this.territoryId = str2;
        this.entitlementList = list;
    }

    public final JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("territoryId", this.territoryId).put("marketplaceId", this.marketplaceId).put("entitlementList", entitlementListToJSONArray());
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "JSONObject()\n           …tlementListToJSONArray())");
        return jSONObjectPut;
    }

    private final JSONArray entitlementListToJSONArray() {
        JSONArray jSONArray = new JSONArray();
        List<? extends Entitlement> list = this.entitlementList;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put((Entitlement) it.next());
            }
        }
        return jSONArray;
    }

    /* JADX INFO: compiled from: CustomerInfo.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\u000b\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/net/dmls/model/CustomerInfo$Builder;", "", "()V", "entitlementList", "", "Lcom/amazon/mp3/net/dmls/model/Entitlement;", "marketplaceId", "", "territoryId", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/mp3/net/dmls/model/CustomerInfo;", "withEntitlementList", "withMarketplaceId", "withTerritoryId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private List<? extends Entitlement> entitlementList;
        private String marketplaceId;
        private String territoryId;

        public final Builder withTerritoryId(String territoryId) {
            Intrinsics.checkNotNullParameter(territoryId, "territoryId");
            this.territoryId = territoryId;
            return this;
        }

        public final Builder withMarketplaceId(String marketplaceId) {
            Intrinsics.checkNotNullParameter(marketplaceId, "marketplaceId");
            this.marketplaceId = marketplaceId;
            return this;
        }

        public final Builder withEntitlementList(List<? extends Entitlement> entitlementList) {
            Intrinsics.checkNotNullParameter(entitlementList, "entitlementList");
            this.entitlementList = entitlementList;
            return this;
        }

        public final CustomerInfo build() {
            return new CustomerInfo(this.marketplaceId, this.territoryId, this.entitlementList);
        }
    }

    /* JADX INFO: compiled from: CustomerInfo.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/net/dmls/model/CustomerInfo$Companion;", "", "()V", "builder", "Lcom/amazon/mp3/net/dmls/model/CustomerInfo$Builder;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }
    }
}
