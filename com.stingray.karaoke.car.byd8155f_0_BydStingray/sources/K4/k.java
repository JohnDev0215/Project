package k4;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class k implements h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f18198b;
    public volatile Map c;

    public k(Map map) {
        this.f18198b = Collections.unmodifiableMap(map);
    }

    public final HashMap a() {
        HashMap map = new HashMap();
        for (Map.Entry entry : this.f18198b.entrySet()) {
            List list = (List) entry.getValue();
            StringBuilder sb = new StringBuilder();
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                String str = ((j) list.get(i7)).f18197a;
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                    if (i7 != list.size() - 1) {
                        sb.append(',');
                    }
                }
            }
            String string = sb.toString();
            if (!TextUtils.isEmpty(string)) {
                map.put(entry.getKey(), string);
            }
        }
        return map;
    }

    public final Map b() {
        if (this.c == null) {
            synchronized (this) {
                try {
                    if (this.c == null) {
                        this.c = Collections.unmodifiableMap(a());
                    }
                } finally {
                }
            }
        }
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            return this.f18198b.equals(((k) obj).f18198b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f18198b.hashCode();
    }

    public final String toString() {
        return "LazyHeaders{headers=" + this.f18198b + '}';
    }
}
