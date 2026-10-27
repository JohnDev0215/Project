package com.netinsight.sye.syeClient.view;

import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bR\"\u0010\t\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/netinsight/sye/syeClient/view/SyeDisplaySettings;", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings;", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;", "a", "Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;", "getGravity", "()Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;", "setGravity", "(Lcom/netinsight/sye/syeClient/view/ISyeDisplaySettings$VideoGravity;)V", "gravity", "<init>", "()V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeDisplaySettings implements ISyeDisplaySettings {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public ISyeDisplaySettings.VideoGravity gravity = ISyeDisplaySettings.VideoGravity.ASPECT_FIT;

    @Override // com.netinsight.sye.syeClient.view.ISyeDisplaySettings
    public ISyeDisplaySettings.VideoGravity getGravity() {
        return this.gravity;
    }

    public void setGravity(ISyeDisplaySettings.VideoGravity videoGravity) {
        this.gravity = videoGravity;
    }
}
