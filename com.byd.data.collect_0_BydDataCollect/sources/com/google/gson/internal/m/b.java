package com.google.gson.internal.m;

import com.google.gson.internal.d;
import java.lang.reflect.AccessibleObject;

/* JADX INFO: compiled from: ReflectionAccessor.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final b f4591a;

    static {
        f4591a = d.c() < 9 ? new a() : new c();
    }

    public static b a() {
        return f4591a;
    }

    public abstract void b(AccessibleObject accessibleObject);
}
