package spi;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class sb implements bn<File, File> {
    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull File file, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    public final wm<File> b(@NonNull File file, int i, int i2, @NonNull lk lkVar) throws IOException {
        return new wb(file);
    }
}
