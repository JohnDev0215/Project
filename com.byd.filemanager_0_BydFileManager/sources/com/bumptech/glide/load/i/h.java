package com.bumptech.glide.load.i;

import com.bumptech.glide.load.i.j;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes.dex */
public interface h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public static final h f3200a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f3201b = new j.a().a();

    /* JADX INFO: compiled from: Headers.java */
    class a implements h {
        a() {
        }

        @Override // com.bumptech.glide.load.i.h
        public Map<String, String> a() {
            return Collections.emptyMap();
        }
    }

    Map<String, String> a();
}
