package com.google.gson;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GsonBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f4437h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Excluder f4430a = Excluder.f4440b;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private s f4431b = s.f4599b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d f4432c = c.f4423b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Type, f<?>> f4433d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<t> f4434e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final List<t> f4435f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4436g = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4438i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    private void a(String str, int i2, int i3, List<t> list) {
        DefaultDateTypeAdapter defaultDateTypeAdapter;
        DefaultDateTypeAdapter defaultDateTypeAdapter2;
        DefaultDateTypeAdapter defaultDateTypeAdapter3;
        if (str != null && !"".equals(str.trim())) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(Date.class, str);
            defaultDateTypeAdapter2 = new DefaultDateTypeAdapter(Timestamp.class, str);
            defaultDateTypeAdapter3 = new DefaultDateTypeAdapter(java.sql.Date.class, str);
        } else {
            if (i2 == 2 || i3 == 2) {
                return;
            }
            DefaultDateTypeAdapter defaultDateTypeAdapter4 = new DefaultDateTypeAdapter(Date.class, i2, i3);
            DefaultDateTypeAdapter defaultDateTypeAdapter5 = new DefaultDateTypeAdapter(Timestamp.class, i2, i3);
            DefaultDateTypeAdapter defaultDateTypeAdapter6 = new DefaultDateTypeAdapter(java.sql.Date.class, i2, i3);
            defaultDateTypeAdapter = defaultDateTypeAdapter4;
            defaultDateTypeAdapter2 = defaultDateTypeAdapter5;
            defaultDateTypeAdapter3 = defaultDateTypeAdapter6;
        }
        list.add(TypeAdapters.b(Date.class, defaultDateTypeAdapter));
        list.add(TypeAdapters.b(Timestamp.class, defaultDateTypeAdapter2));
        list.add(TypeAdapters.b(java.sql.Date.class, defaultDateTypeAdapter3));
    }

    public Gson b() {
        List<t> arrayList = new ArrayList<>(this.f4434e.size() + this.f4435f.size() + 3);
        arrayList.addAll(this.f4434e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f4435f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        a(this.f4437h, this.f4438i, this.j, arrayList);
        return new Gson(this.f4430a, this.f4432c, this.f4433d, this.f4436g, this.k, this.o, this.m, this.n, this.p, this.l, this.f4431b, this.f4437h, this.f4438i, this.j, this.f4434e, this.f4435f, arrayList);
    }

    public e c(Type type, Object obj) {
        boolean z = obj instanceof q;
        com.google.gson.internal.a.a(z || (obj instanceof i) || (obj instanceof f) || (obj instanceof TypeAdapter));
        if (obj instanceof f) {
            this.f4433d.put(type, (f) obj);
        }
        if (z || (obj instanceof i)) {
            this.f4434e.add(TreeTypeAdapter.b(com.google.gson.v.a.b(type), obj));
        }
        if (obj instanceof TypeAdapter) {
            this.f4434e.add(TypeAdapters.a(com.google.gson.v.a.b(type), (TypeAdapter) obj));
        }
        return this;
    }
}
