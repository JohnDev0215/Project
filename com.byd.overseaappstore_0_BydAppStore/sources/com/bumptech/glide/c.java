package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.a;
import java.util.List;
import java.util.Map;
import spi.i8;
import spi.m0;
import spi.nf;
import spi.pm;
import spi.tm;
import spi.xt;
import spi.zc;

/* JADX INFO: loaded from: classes.dex */
public final class c extends ContextWrapper {

    @VisibleForTesting
    public static final xt<?, ?> k = new zc();
    public final m0 a;
    public final Registry b;
    public final nf c;
    public final a.InterfaceC0013a d;
    public final List<pm<Object>> e;
    public final Map<Class<?>, xt<?, ?>> f;
    public final i8 g;
    public final d h;
    public final int i;

    @Nullable
    @GuardedBy("this")
    public tm j;

    public c(@NonNull Context context, @NonNull m0 m0Var, @NonNull Registry registry, @NonNull nf nfVar, @NonNull a.InterfaceC0013a interfaceC0013a, @NonNull Map<Class<?>, xt<?, ?>> map, @NonNull List<pm<Object>> list, @NonNull i8 i8Var, @NonNull d dVar, int i) {
        super(context.getApplicationContext());
        this.a = m0Var;
        this.b = registry;
        this.c = nfVar;
        this.d = interfaceC0013a;
        this.e = list;
        this.f = map;
        this.g = i8Var;
        this.h = dVar;
        this.i = i;
    }
}
