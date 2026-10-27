package com.amazon.identity.auth.device;

import android.util.Base64;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p7 extends l7 {
    public final /* synthetic */ String b;

    public p7(BackwardsCompatiableDataStorage backwardsCompatiableDataStorage, String str) {
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.l7
    public byte[] b() {
        return Base64.decode(this.b, 0);
    }
}
