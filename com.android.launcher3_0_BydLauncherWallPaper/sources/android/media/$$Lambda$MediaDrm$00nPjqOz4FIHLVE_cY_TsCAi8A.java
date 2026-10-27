package android.media;

import android.media.MediaDrm;
import java.util.function.Function;

/* JADX INFO: renamed from: android.media.-$$Lambda$MediaDrm$00nPjqOz4FIHL-VE_cY_TsCAi8A, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$MediaDrm$00nPjqOz4FIHLVE_cY_TsCAi8A implements Function {
    public final /* synthetic */ MediaDrm f$0;

    public /* synthetic */ $$Lambda$MediaDrm$00nPjqOz4FIHLVE_cY_TsCAi8A(MediaDrm mediaDrm) {
        this.f$0 = mediaDrm;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.f$0.createOnExpirationUpdateListener((MediaDrm.OnExpirationUpdateListener) obj);
    }
}
