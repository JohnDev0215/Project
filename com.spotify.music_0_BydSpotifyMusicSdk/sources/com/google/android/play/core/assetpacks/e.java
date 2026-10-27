package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import java.util.concurrent.ExecutionException;
import p.d33;
import p.f13;
import p.h13;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    public final f13 a;

    public e(f13 f13Var) {
        this.a = f13Var;
    }

    public final ParcelFileDescriptor.AutoCloseInputStream a(int i, String str, int i2, String str2) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) he1.c(((d33) ((h13) this.a).a()).c(i, str, i2, str2));
            if (parcelFileDescriptor == null || parcelFileDescriptor.getFileDescriptor() == null) {
                throw new zzck(String.format("Corrupted ParcelFileDescriptor, session %s packName %s sliceId %s, chunkNumber %s", Integer.valueOf(i), str, str2, Integer.valueOf(i2)), i);
            }
            return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
        } catch (InterruptedException e) {
            throw new zzck("Extractor was interrupted while waiting for chunk file.", e, i);
        } catch (ExecutionException e2) {
            throw new zzck(String.format("Error opening chunk file, session %s packName %s sliceId %s, chunkNumber %s", Integer.valueOf(i), str, str2, Integer.valueOf(i2)), e2, i);
        }
    }
}
