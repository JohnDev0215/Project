package spi;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.load.engine.GlideException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import spi.x5;

/* JADX INFO: loaded from: classes.dex */
public final class vg<Data, ResourceType, Transcode> {
    public final Pools.Pool<List<Throwable>> a;
    public final List<? extends x5<Data, ResourceType, Transcode>> b;
    public final String c;

    public vg(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<x5<Data, ResourceType, Transcode>> list, Pools.Pool<List<Throwable>> pool) {
        this.a = pool;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        this.b = list;
        StringBuilder sbK = e.k("Failed LoadPath{");
        sbK.append(cls.getSimpleName());
        sbK.append("->");
        sbK.append(cls2.getSimpleName());
        sbK.append("->");
        sbK.append(cls3.getSimpleName());
        sbK.append("}");
        this.c = sbK.toString();
    }

    public final wm<Transcode> a(com.bumptech.glide.load.data.a<Data> aVar, @NonNull lk lkVar, int i, int i2, x5.a<ResourceType> aVar2) throws GlideException {
        List<Throwable> listAcquire = this.a.acquire();
        Objects.requireNonNull(listAcquire, "Argument must not be null");
        List<Throwable> list = listAcquire;
        try {
            int size = this.b.size();
            wm<Transcode> wmVarA = null;
            for (int i3 = 0; i3 < size; i3++) {
                try {
                    wmVarA = this.b.get(i3).a(aVar, i, i2, lkVar, aVar2);
                } catch (GlideException e) {
                    list.add(e);
                }
                if (wmVarA != null) {
                    break;
                }
            }
            if (wmVarA != null) {
                return wmVarA;
            }
            throw new GlideException(this.c, new ArrayList(list));
        } finally {
            this.a.release(list);
        }
    }

    public final String toString() {
        StringBuilder sbK = e.k("LoadPath{decodePaths=");
        sbK.append(Arrays.toString(this.b.toArray()));
        sbK.append('}');
        return sbK.toString();
    }
}
