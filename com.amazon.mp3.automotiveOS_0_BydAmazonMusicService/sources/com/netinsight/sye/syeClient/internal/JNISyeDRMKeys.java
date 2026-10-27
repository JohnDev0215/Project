package com.netinsight.sye.syeClient.internal;

import com.netinsight.sye.syeClient.drm.SyeDRMKey;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0005R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/JNISyeDRMKeys;", "", "", "", "identifiers", "[Ljava/lang/String;", "keys", "", "length", "I", "", "Lcom/netinsight/sye/syeClient/drm/SyeDRMKey;", "list", "<init>", "(Ljava/util/List;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class JNISyeDRMKeys {
    private final String[] identifiers;
    private final String[] keys;
    private final int length;

    public JNISyeDRMKeys(List<SyeDRMKey> list) {
        int size = list.size();
        this.length = size;
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = list.get(i).getKeyId();
        }
        this.identifiers = strArr;
        int i2 = this.length;
        String[] strArr2 = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr2[i3] = list.get(i3).getKey();
        }
        this.keys = strArr2;
    }
}
