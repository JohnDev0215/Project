package com.netinsight.sye.syeClient.internal;

import com.netinsight.sye.syeClient.SyeDomain;
import com.netinsight.sye.syeClient.SyeSystem;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0005R\u0016\u0010\n\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/JNISyeSystem;", "", "", "", "urls", "[Ljava/lang/String;", "", "length", "I", "names", "credentials", "Ljava/lang/String;", "Lcom/netinsight/sye/syeClient/SyeSystem;", "syeSystem", "<init>", "(Lcom/netinsight/sye/syeClient/SyeSystem;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class JNISyeSystem {
    private final String credentials;
    private final int length;
    private final String[] names;
    private final String[] urls;

    public JNISyeSystem(SyeSystem syeSystem) {
        List<SyeDomain> syeDomains = syeSystem.getSyeDomains();
        int size = syeDomains.size();
        this.length = size;
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = syeDomains.get(i).getName();
        }
        this.names = strArr;
        int i2 = this.length;
        String[] strArr2 = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr2[i3] = syeDomains.get(i3).getBaseUrl();
        }
        this.urls = strArr2;
        this.credentials = syeSystem.getCredentials();
    }
}
