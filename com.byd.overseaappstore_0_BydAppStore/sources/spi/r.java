package spi;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import spi.nn;

/* JADX INFO: loaded from: classes2.dex */
public interface r extends nn {
    @Override // spi.nn
    @MainThread
    /* synthetic */ void addOnClearedListener(@NonNull nn.a aVar);

    @Override // spi.nn
    @MainThread
    /* synthetic */ void removeOnClearedListener(@NonNull nn.a aVar);
}
