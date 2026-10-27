package spi;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import java.util.Map;

/* JADX INFO: compiled from: CarUxRestrictionsAppConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c2 {
    public static c2 b;
    public final Map<Integer, b> a;

    /* JADX INFO: compiled from: CarUxRestrictionsAppConfig.java */
    public static class b {

        @IdRes
        public final int a;
        public final Integer b;

        @StringRes
        public final Integer c;

        /* JADX INFO: compiled from: CarUxRestrictionsAppConfig.java */
        public static class a {

            @IdRes
            public final int a;
            public Integer b;

            @StringRes
            public Integer c;

            private a(@IdRes int i) {
                this.a = i;
            }
        }

        private b(@IdRes int i, @Nullable Integer num, @StringRes Integer num2) {
            this.a = i;
            this.b = num;
            this.c = num2;
        }
    }

    public c2(Map<Integer, b> map) {
        this.a = map;
    }
}
