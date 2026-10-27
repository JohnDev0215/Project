package A9;

import A3.v;
import A7.m;
import E5.H;
import h6.r;
import m7.n;
import org.openapitools.client.infrastructure.BigDecimalAdapter;
import org.openapitools.client.infrastructure.BigIntegerAdapter;
import org.openapitools.client.infrastructure.ByteArrayAdapter;
import org.openapitools.client.infrastructure.LocalDateAdapter;
import org.openapitools.client.infrastructure.LocalDateTimeAdapter;
import org.openapitools.client.infrastructure.OffsetDateTimeAdapter;
import org.openapitools.client.infrastructure.URIAdapter;
import org.openapitools.client.infrastructure.UUIDAdapter;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final E4.b f488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f489b;

    static {
        E4.b bVar = new E4.b();
        bVar.b(new OffsetDateTimeAdapter());
        bVar.b(new LocalDateTimeAdapter());
        bVar.b(new LocalDateAdapter());
        bVar.b(new UUIDAdapter());
        bVar.b(new ByteArrayAdapter());
        bVar.b(new URIAdapter());
        bVar.b(new BigDecimalAdapter());
        bVar.b(new BigIntegerAdapter());
        f488a = bVar;
        f489b = r.o(new v(2));
    }

    public static final H a() {
        Object value = f489b.getValue();
        m.e(value, "getValue(...)");
        return (H) value;
    }
}
