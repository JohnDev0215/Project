package spi;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public interface nn {

    public interface a {
        void a();
    }

    @MainThread
    void addOnClearedListener(@NonNull a aVar);

    @MainThread
    void removeOnClearedListener(@NonNull a aVar);
}
