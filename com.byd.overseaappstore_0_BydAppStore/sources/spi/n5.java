package spi;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.DataSource;

/* JADX INFO: loaded from: classes.dex */
public interface n5 {

    public interface a {
        void a(ig igVar, @Nullable Object obj, m5<?> m5Var, DataSource dataSource, ig igVar2);

        void c(ig igVar, Exception exc, m5<?> m5Var, DataSource dataSource);

        void d();
    }

    boolean b();

    void cancel();
}
