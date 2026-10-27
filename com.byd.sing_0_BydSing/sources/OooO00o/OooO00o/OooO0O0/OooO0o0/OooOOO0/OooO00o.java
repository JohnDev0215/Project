package OooO00o.OooO00o.OooO0O0.OooO0o0.OooOOO0;

import android.content.SharedPreferences;
import com.loostone.libtuning.component.provider.TuningProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f29a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final T f30b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final OooO0O0 f31c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<b<T>> f32d;
    public final SharedPreferences e;
    public final SharedPreferences.Editor f;
    public Map<String, Object> g;
    public T h;

    public enum OooO0O0 {
        T_INT,
        T_LONG,
        T_FLOAT,
        T_STRING,
        T_BOOLEAN
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public OooO00o(String str, String str2, T t, OooO0O0 oooO0O0) {
        h.d(str, "dataName");
        h.d(str2, TuningProvider.KEY);
        h.d(oooO0O0, TuningProvider.TYPE);
        this.f29a = str2;
        this.f30b = t;
        this.f31c = oooO0O0;
        this.f32d = new ArrayList();
        SharedPreferences sharedPreferences = OooO00o.OooO00o.OooO0O0.a.f39a.a().getSharedPreferences(str, 0);
        h.c(sharedPreferences, "FakeApplication.instance.getSharedPreferences(dataName, Context.MODE_PRIVATE)");
        this.e = sharedPreferences;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        h.c(editorEdit, "mSpf.edit()");
        this.f = editorEdit;
        this.g = new LinkedHashMap();
    }

    public final T a() {
        T t = (T) this.g.get(this.f29a);
        if (t != null) {
            return t;
        }
        int iOrdinal = this.f31c.ordinal();
        if (iOrdinal == 0) {
            SharedPreferences sharedPreferences = this.e;
            String str = this.f29a;
            T t2 = this.f30b;
            Objects.requireNonNull(t2, "null cannot be cast to non-null type kotlin.Int");
            return (T) Integer.valueOf(sharedPreferences.getInt(str, ((Integer) t2).intValue()));
        }
        if (iOrdinal == 1) {
            SharedPreferences sharedPreferences2 = this.e;
            String str2 = this.f29a;
            T t3 = this.f30b;
            Objects.requireNonNull(t3, "null cannot be cast to non-null type kotlin.Long");
            return (T) Long.valueOf(sharedPreferences2.getLong(str2, ((Long) t3).longValue()));
        }
        if (iOrdinal == 2) {
            SharedPreferences sharedPreferences3 = this.e;
            String str3 = this.f29a;
            T t4 = this.f30b;
            Objects.requireNonNull(t4, "null cannot be cast to non-null type kotlin.Float");
            return (T) Float.valueOf(sharedPreferences3.getFloat(str3, ((Float) t4).floatValue()));
        }
        if (iOrdinal != 3) {
            if (iOrdinal != 4) {
                throw new NoWhenBranchMatchedException();
            }
            SharedPreferences sharedPreferences4 = this.e;
            String str4 = this.f29a;
            T t5 = this.f30b;
            Objects.requireNonNull(t5, "null cannot be cast to non-null type kotlin.Boolean");
            return (T) Boolean.valueOf(sharedPreferences4.getBoolean(str4, ((Boolean) t5).booleanValue()));
        }
        SharedPreferences sharedPreferences5 = this.e;
        String str5 = this.f29a;
        T t6 = this.f30b;
        Objects.requireNonNull(t6, "null cannot be cast to non-null type kotlin.String");
        T t7 = (T) sharedPreferences5.getString(str5, (String) t6);
        h.b(t7);
        h.c(t7, "{\n                    mSpf.getString(key, defaultValue as String)!!\n                }");
        return t7;
    }

    public final void b(b<T> bVar) {
        h.d(bVar, "observer");
        if (this.f32d.contains(bVar)) {
            return;
        }
        this.f32d.add(bVar);
    }

    public final void c(T t) {
        Iterator<T> it = this.f32d.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(t);
        }
    }

    public final T d() {
        T t = (T) this.g.get(this.f29a);
        if (t != null) {
            return t;
        }
        if (!this.e.contains(this.f29a)) {
            return null;
        }
        int iOrdinal = this.f31c.ordinal();
        if (iOrdinal == 0) {
            SharedPreferences sharedPreferences = this.e;
            String str = this.f29a;
            T t2 = this.f30b;
            Objects.requireNonNull(t2, "null cannot be cast to non-null type kotlin.Int");
            return (T) Integer.valueOf(sharedPreferences.getInt(str, ((Integer) t2).intValue()));
        }
        if (iOrdinal == 1) {
            SharedPreferences sharedPreferences2 = this.e;
            String str2 = this.f29a;
            T t3 = this.f30b;
            Objects.requireNonNull(t3, "null cannot be cast to non-null type kotlin.Long");
            return (T) Long.valueOf(sharedPreferences2.getLong(str2, ((Long) t3).longValue()));
        }
        if (iOrdinal == 2) {
            SharedPreferences sharedPreferences3 = this.e;
            String str3 = this.f29a;
            T t4 = this.f30b;
            Objects.requireNonNull(t4, "null cannot be cast to non-null type kotlin.Float");
            return (T) Float.valueOf(sharedPreferences3.getFloat(str3, ((Float) t4).floatValue()));
        }
        if (iOrdinal != 3) {
            if (iOrdinal != 4) {
                throw new NoWhenBranchMatchedException();
            }
            SharedPreferences sharedPreferences4 = this.e;
            String str4 = this.f29a;
            T t5 = this.f30b;
            Objects.requireNonNull(t5, "null cannot be cast to non-null type kotlin.Boolean");
            return (T) Boolean.valueOf(sharedPreferences4.getBoolean(str4, ((Boolean) t5).booleanValue()));
        }
        SharedPreferences sharedPreferences5 = this.e;
        String str5 = this.f29a;
        T t6 = this.f30b;
        Objects.requireNonNull(t6, "null cannot be cast to non-null type kotlin.String");
        T t7 = (T) sharedPreferences5.getString(str5, (String) t6);
        h.b(t7);
        h.c(t7, "{\n                    mSpf.getString(key, defaultValue as String)!!\n                }");
        return t7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e(T t) {
        if (t == 0) {
            return;
        }
        this.h = t;
        this.g.put(this.f29a, t);
        if (t instanceof Integer) {
            this.f.putInt(this.f29a, ((Number) t).intValue());
        } else if (t instanceof String) {
            this.f.putString(this.f29a, (String) t);
        } else if (t instanceof Boolean) {
            this.f.putBoolean(this.f29a, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            this.f.putFloat(this.f29a, ((Number) t).floatValue());
        } else if (!(t instanceof Long)) {
            return;
        } else {
            this.f.putLong(this.f29a, ((Number) t).longValue());
        }
        this.f.apply();
        c(t);
    }
}
