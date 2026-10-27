package android.os;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public abstract class UpdateEngineCallback {
    public abstract void onPayloadApplicationComplete(int i);

    public abstract void onStatusUpdate(int i, float f);
}
