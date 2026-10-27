package spi;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;

/* JADX INFO: loaded from: classes.dex */
public abstract class i6 {
    public static final b a;
    public static final c b;
    public static final d c;
    public static final e d;

    public class a extends i6 {
        @Override // spi.i6
        public final boolean a() {
            return true;
        }

        @Override // spi.i6
        public final boolean b() {
            return true;
        }

        @Override // spi.i6
        public final boolean c(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // spi.i6
        public final boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    }

    public class b extends i6 {
        @Override // spi.i6
        public final boolean a() {
            return false;
        }

        @Override // spi.i6
        public final boolean b() {
            return false;
        }

        @Override // spi.i6
        public final boolean c(DataSource dataSource) {
            return false;
        }

        @Override // spi.i6
        public final boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    }

    public class c extends i6 {
        @Override // spi.i6
        public final boolean a() {
            return true;
        }

        @Override // spi.i6
        public final boolean b() {
            return false;
        }

        @Override // spi.i6
        public final boolean c(DataSource dataSource) {
            return (dataSource == DataSource.DATA_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }

        @Override // spi.i6
        public final boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    }

    public class d extends i6 {
        @Override // spi.i6
        public final boolean a() {
            return false;
        }

        @Override // spi.i6
        public final boolean b() {
            return true;
        }

        @Override // spi.i6
        public final boolean c(DataSource dataSource) {
            return false;
        }

        @Override // spi.i6
        public final boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    }

    public class e extends i6 {
        @Override // spi.i6
        public final boolean a() {
            return true;
        }

        @Override // spi.i6
        public final boolean b() {
            return true;
        }

        @Override // spi.i6
        public final boolean c(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // spi.i6
        public final boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return ((z && dataSource == DataSource.DATA_DISK_CACHE) || dataSource == DataSource.LOCAL) && encodeStrategy == EncodeStrategy.TRANSFORMED;
        }
    }

    static {
        new a();
        a = new b();
        b = new c();
        c = new d();
        d = new e();
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean c(DataSource dataSource);

    public abstract boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy);
}
