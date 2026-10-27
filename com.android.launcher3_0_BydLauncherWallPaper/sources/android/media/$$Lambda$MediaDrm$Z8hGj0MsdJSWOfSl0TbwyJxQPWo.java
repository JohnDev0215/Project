package android.media;

import android.media.MediaDrm;
import java.util.function.Function;

/* JADX INFO: renamed from: android.media.-$$Lambda$MediaDrm$Z8hGj0MsdJSWOfSl0TbwyJxQPWo, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$MediaDrm$Z8hGj0MsdJSWOfSl0TbwyJxQPWo implements Function {
    public final /* synthetic */ MediaDrm f$0;

    public /* synthetic */ $$Lambda$MediaDrm$Z8hGj0MsdJSWOfSl0TbwyJxQPWo(MediaDrm mediaDrm) {
        this.f$0 = mediaDrm;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.f$0.createOnSessionLostStateListener((MediaDrm.OnSessionLostStateListener) obj);
    }
}
