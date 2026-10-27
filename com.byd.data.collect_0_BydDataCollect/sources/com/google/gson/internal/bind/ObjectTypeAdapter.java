package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.g;
import com.google.gson.t;
import com.google.gson.w.c;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f4469a = new t() { // from class: com.google.gson.internal.bind.ObjectTypeAdapter.1
        @Override // com.google.gson.t
        public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
            if (aVar.c() == Object.class) {
                return new ObjectTypeAdapter(gson);
            }
            return null;
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Gson f4470b;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4471a;

        static {
            int[] iArr = new int[com.google.gson.w.b.values().length];
            f4471a = iArr;
            try {
                iArr[com.google.gson.w.b.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4471a[com.google.gson.w.b.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4471a[com.google.gson.w.b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4471a[com.google.gson.w.b.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4471a[com.google.gson.w.b.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4471a[com.google.gson.w.b.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    ObjectTypeAdapter(Gson gson) {
        this.f4470b = gson;
    }

    @Override // com.google.gson.TypeAdapter
    public Object read(com.google.gson.w.a aVar) throws IOException {
        switch (a.f4471a[aVar.j0().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.V()) {
                    arrayList.add(read(aVar));
                }
                aVar.R();
                return arrayList;
            case 2:
                g gVar = new g();
                aVar.d();
                while (aVar.V()) {
                    gVar.put(aVar.d0(), read(aVar));
                }
                aVar.S();
                return gVar;
            case 3:
                return aVar.h0();
            case 4:
                return Double.valueOf(aVar.a0());
            case 5:
                return Boolean.valueOf(aVar.Z());
            case 6:
                aVar.f0();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.Y();
            return;
        }
        TypeAdapter typeAdapterL = this.f4470b.l(obj.getClass());
        if (!(typeAdapterL instanceof ObjectTypeAdapter)) {
            typeAdapterL.write(cVar, obj);
        } else {
            cVar.y();
            cVar.S();
        }
    }
}
