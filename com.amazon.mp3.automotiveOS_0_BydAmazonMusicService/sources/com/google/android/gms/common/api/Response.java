package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class Response<T extends Result> {
    private Result zza;

    public Response() {
    }

    protected Response(@NonNull T t) {
        this.zza = t;
    }

    @NonNull
    protected T getResult() {
        return (T) this.zza;
    }

    public void setResult(@NonNull T t) {
        this.zza = t;
    }
}
