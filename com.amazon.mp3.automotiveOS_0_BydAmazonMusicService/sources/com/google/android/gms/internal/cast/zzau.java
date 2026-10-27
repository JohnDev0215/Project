package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzau {
    public static PendingResult zza(Task task, final zzat zzatVar, final zzat zzatVar2) {
        final zzas zzasVar = new zzas(zzatVar2);
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.internal.cast.zzar
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                zzas zzasVar2 = zzasVar;
                int i = CastSession.zza;
                zzasVar2.setResult(new Status(0));
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.android.gms.internal.cast.zzaq
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                zzas zzasVar2 = zzasVar;
                Status status = new Status(8, "unknown error");
                if (exc instanceof ApiException) {
                    ApiException apiException = (ApiException) exc;
                    status = new Status(apiException.getStatusCode(), apiException.getMessage());
                }
                int i = CastSession.zza;
                zzasVar2.setResult(status);
            }
        });
        return zzasVar;
    }
}
