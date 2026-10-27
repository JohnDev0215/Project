package android.app.role;

import android.os.Bundle;
import android.os.RemoteCallback;
import com.android.internal.infra.AndroidFuture;

/* JADX INFO: renamed from: android.app.role.-$$Lambda$9DeAxmM9lUVr3-FTSefyo-BW8DY, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$9DeAxmM9lUVr3FTSefyoBW8DY implements RemoteCallback.OnResultListener {
    public final /* synthetic */ AndroidFuture f$0;

    @Override // android.os.RemoteCallback.OnResultListener
    public final void onResult(Bundle bundle) {
        this.f$0.complete(bundle);
    }
}
