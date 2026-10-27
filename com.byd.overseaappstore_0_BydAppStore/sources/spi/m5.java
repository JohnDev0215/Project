package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

/* JADX INFO: loaded from: classes.dex */
public interface m5<T> {

    public interface a<T> {
        void c(@NonNull Exception exc);

        void f(@Nullable T t);
    }

    @NonNull
    Class<T> a();

    void b();

    void cancel();

    void d(@NonNull Priority priority, @NonNull a<? super T> aVar);

    @NonNull
    DataSource e();
}
