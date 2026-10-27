package com.netinsight.sye.syeClient.status;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0005\u0010\u0004J$\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0004J\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0004R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u0004¨\u0006\u001a"}, d2 = {"Lcom/netinsight/sye/syeClient/status/SyeFrontendInfo;", "", "", "component1", "()Ljava/lang/String;", "component2", "domainName", ImagesContract.URL, "copy", "(Ljava/lang/String;Ljava/lang/String;)Lcom/netinsight/sye/syeClient/status/SyeFrontendInfo;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getDomainName", "b", "getUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final /* data */ class SyeFrontendInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata and from toString */
    public final String domainName;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata and from toString */
    public final String url;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.status.SyeFrontendInfo$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final SyeFrontendInfo a(String str) {
            if (Intrinsics.areEqual(str, "null")) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            String domainName = jSONObject.getString("domainName");
            String url = jSONObject.getString(ImagesContract.URL);
            try {
                Intrinsics.checkExpressionValueIsNotNull(domainName, "domainName");
                Intrinsics.checkExpressionValueIsNotNull(url, "url");
                return new SyeFrontendInfo(domainName, url);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public SyeFrontendInfo(String str, String str2) {
        this.domainName = str;
        this.url = str2;
    }

    public static /* synthetic */ SyeFrontendInfo copy$default(SyeFrontendInfo syeFrontendInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = syeFrontendInfo.domainName;
        }
        if ((i & 2) != 0) {
            str2 = syeFrontendInfo.url;
        }
        return syeFrontendInfo.copy(str, str2);
    }

    @JvmStatic
    public static final SyeFrontendInfo parseJson(String str) {
        return INSTANCE.a(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDomainName() {
        return this.domainName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final SyeFrontendInfo copy(String domainName, String url) {
        return new SyeFrontendInfo(domainName, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyeFrontendInfo)) {
            return false;
        }
        SyeFrontendInfo syeFrontendInfo = (SyeFrontendInfo) other;
        return Intrinsics.areEqual(this.domainName, syeFrontendInfo.domainName) && Intrinsics.areEqual(this.url, syeFrontendInfo.url);
    }

    public final String getDomainName() {
        return this.domainName;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.domainName;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SyeFrontendInfo(domainName=" + this.domainName + ", url=" + this.url + ")";
    }
}
