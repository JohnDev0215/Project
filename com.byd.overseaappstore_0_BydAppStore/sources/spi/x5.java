package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.GlideException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import spi.ti;
import spi.w5;

/* JADX INFO: loaded from: classes.dex */
public final class x5<DataType, ResourceType, Transcode> {
    public final Class<DataType> a;
    public final List<? extends bn<DataType, ResourceType>> b;
    public final in<ResourceType, Transcode> c;
    public final Pools.Pool<List<Throwable>> d;
    public final String e;

    public interface a<ResourceType> {
    }

    public x5(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends bn<DataType, ResourceType>> list, in<ResourceType, Transcode> inVar, Pools.Pool<List<Throwable>> pool) {
        this.a = cls;
        this.b = list;
        this.c = inVar;
        this.d = pool;
        StringBuilder sbK = e.k("Failed DecodePath{");
        sbK.append(cls.getSimpleName());
        sbK.append("->");
        sbK.append(cls2.getSimpleName());
        sbK.append("->");
        sbK.append(cls3.getSimpleName());
        sbK.append("}");
        this.e = sbK.toString();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final wm<Transcode> a(com.bumptech.glide.load.data.a<DataType> aVar, int i, int i2, @NonNull lk lkVar, a<ResourceType> aVar2) throws GlideException {
        wm<ResourceType> wmVarB;
        qt qtVar;
        EncodeStrategy encodeStrategyA;
        ig k5Var;
        List<Throwable> listAcquire = this.d.acquire();
        Objects.requireNonNull(listAcquire, "Argument must not be null");
        List<Throwable> list = listAcquire;
        try {
            wm<ResourceType> wmVarB2 = b(aVar, i, i2, lkVar, list);
            this.d.release(list);
            w5.c cVar = (w5.c) aVar2;
            w5 w5Var = w5.this;
            DataSource dataSource = cVar.a;
            Objects.requireNonNull(w5Var);
            Class<?> cls = wmVarB2.get().getClass();
            en enVarA = null;
            if (dataSource != DataSource.RESOURCE_DISK_CACHE) {
                qt qtVarG = w5Var.a.g(cls);
                qtVar = qtVarG;
                wmVarB = qtVarG.b(w5Var.h, wmVarB2, w5Var.l, w5Var.m);
            } else {
                wmVarB = wmVarB2;
                qtVar = null;
            }
            if (!wmVarB2.equals(wmVarB)) {
                wmVarB2.recycle();
            }
            boolean z = false;
            if (w5Var.a.c.b.d.a(wmVarB.c()) != null) {
                enVarA = w5Var.a.c.b.d.a(wmVarB.c());
                if (enVarA == null) {
                    throw new Registry.NoResultEncoderAvailableException(wmVarB.c());
                }
                encodeStrategyA = enVarA.a(w5Var.o);
            } else {
                encodeStrategyA = EncodeStrategy.NONE;
            }
            en enVar = enVarA;
            v5<R> v5Var = w5Var.a;
            ig igVar = w5Var.x;
            ArrayList arrayList = (ArrayList) v5Var.c();
            int size = arrayList.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    break;
                }
                if (((ti.a) arrayList.get(i3)).a.equals(igVar)) {
                    z = true;
                    break;
                }
                i3++;
            }
            Object obj = wmVarB;
            if (w5Var.n.d(!z, dataSource, encodeStrategyA)) {
                if (enVar == null) {
                    throw new Registry.NoResultEncoderAvailableException(wmVarB.get().getClass());
                }
                int i4 = w5.a.c[encodeStrategyA.ordinal()];
                if (i4 == 1) {
                    k5Var = new k5(w5Var.x, w5Var.i);
                } else {
                    if (i4 != 2) {
                        throw new IllegalArgumentException("Unknown strategy: " + encodeStrategyA);
                    }
                    k5Var = new zm(w5Var.a.c.a, w5Var.x, w5Var.i, w5Var.l, w5Var.m, qtVar, cls, w5Var.o);
                }
                bh<Z> bhVarD = bh.d(wmVarB);
                w5.d<?> dVar = w5Var.f;
                dVar.a = k5Var;
                dVar.b = enVar;
                dVar.c = bhVarD;
                obj = bhVarD;
            }
            return this.c.a(obj, lkVar);
        } catch (Throwable th) {
            this.d.release(list);
            throw th;
        }
    }

    @NonNull
    public final wm<ResourceType> b(com.bumptech.glide.load.data.a<DataType> aVar, int i, int i2, @NonNull lk lkVar, List<Throwable> list) throws GlideException {
        int size = this.b.size();
        wm<ResourceType> wmVarB = null;
        for (int i3 = 0; i3 < size; i3++) {
            bn<DataType, ResourceType> bnVar = this.b.get(i3);
            try {
                if (bnVar.a(aVar.c(), lkVar)) {
                    wmVarB = bnVar.b(aVar.c(), i, i2, lkVar);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + bnVar, e);
                }
                list.add(e);
            }
            if (wmVarB != null) {
                break;
            }
        }
        if (wmVarB != null) {
            return wmVarB;
        }
        throw new GlideException(this.e, new ArrayList(list));
    }

    public final String toString() {
        StringBuilder sbK = e.k("DecodePath{ dataClass=");
        sbK.append(this.a);
        sbK.append(", decoders=");
        sbK.append(this.b);
        sbK.append(", transcoder=");
        sbK.append(this.c);
        sbK.append('}');
        return sbK.toString();
    }
}
