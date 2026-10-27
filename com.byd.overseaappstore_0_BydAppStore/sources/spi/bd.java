package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface bd {

    public interface a {
    }

    @Nullable
    Bitmap a();

    void b();

    int c();

    void clear();

    int d();

    int e();

    int f();

    @NonNull
    ByteBuffer getData();
}
