package com.netinsight.sye.syeClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0018B\u0011\b\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0017\u0010\u0019J\u0015\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0016\u001a\u00020\u00118\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/netinsight/sye/syeClient/SyeSystem;", "", "Lcom/netinsight/sye/syeClient/SyeDomain;", "syeDomain", "addSyeDomain", "(Lcom/netinsight/sye/syeClient/SyeDomain;)Lcom/netinsight/sye/syeClient/SyeSystem;", "Lcom/netinsight/sye/syeClient/SyeFrontend;", "syeFrontend", "addSyeFrontend", "(Lcom/netinsight/sye/syeClient/SyeFrontend;)Lcom/netinsight/sye/syeClient/SyeSystem;", "", "getSyeDomains", "()Ljava/util/List;", "Ljava/util/ArrayList;", "a", "Ljava/util/ArrayList;", "syeDomains", "", "b", "Ljava/lang/String;", "getCredentials", "()Ljava/lang/String;", "credentials", "<init>", "(Lcom/netinsight/sye/syeClient/SyeDomain;Ljava/lang/String;)V", "(Lcom/netinsight/sye/syeClient/SyeFrontend;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeSystem {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final ArrayList<SyeDomain> syeDomains = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final String credentials;

    public SyeSystem(SyeDomain syeDomain, String str) {
        addSyeDomain(syeDomain);
        this.credentials = str;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Replaced with new API since SDK 1.17")
    public SyeSystem(SyeFrontend syeFrontend) {
        addSyeFrontend(syeFrontend);
        this.credentials = syeFrontend.getCredentials();
    }

    public final SyeSystem addSyeDomain(SyeDomain syeDomain) {
        this.syeDomains.add(syeDomain);
        return this;
    }

    public final SyeSystem addSyeFrontend(SyeFrontend syeFrontend) {
        this.syeDomains.add(new SyeDomain(syeFrontend.getBaseUrl(), "default"));
        return this;
    }

    public final String getCredentials() {
        return this.credentials;
    }

    public final List<SyeDomain> getSyeDomains() {
        List<SyeDomain> listUnmodifiableList = Collections.unmodifiableList(this.syeDomains);
        Intrinsics.checkExpressionValueIsNotNull(listUnmodifiableList, "Collections.unmodifiableList(syeDomains)");
        return listUnmodifiableList;
    }
}
