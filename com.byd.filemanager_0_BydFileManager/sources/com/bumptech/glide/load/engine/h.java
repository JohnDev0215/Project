package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;

/* JADX INFO: compiled from: DiskCacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f3021a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f3022b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h f3023c = new c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f3024d = new d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f3025e = new e();

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class a extends h {
        a() {
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean a() {
            return true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean b() {
            return true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean c(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class b extends h {
        b() {
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean a() {
            return false;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean b() {
            return false;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean c(DataSource dataSource) {
            return false;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class c extends h {
        c() {
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean a() {
            return true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean b() {
            return false;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean c(DataSource dataSource) {
            return (dataSource == DataSource.DATA_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class d extends h {
        d() {
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean a() {
            return false;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean b() {
            return true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean c(DataSource dataSource) {
            return false;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    }

    /* JADX INFO: compiled from: DiskCacheStrategy.java */
    class e extends h {
        e() {
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean a() {
            return true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean b() {
            return true;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean c(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // com.bumptech.glide.load.engine.h
        public boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return ((z && dataSource == DataSource.DATA_DISK_CACHE) || dataSource == DataSource.LOCAL) && encodeStrategy == EncodeStrategy.TRANSFORMED;
        }
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean c(DataSource dataSource);

    public abstract boolean d(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy);
}
