package C5;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Date f998b;
    public Map c;
    public LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f999e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1000f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1001g = false;

    public final d a() {
        if (D5.g.g(this.f999e) && D5.g.g(this.f1000f)) {
            throw new NullPointerException("either userId or anonymousId is required");
        }
        Map mapEmptyMap = D5.g.h(this.d) ? Collections.emptyMap() : Collections.unmodifiableMap(new LinkedHashMap(this.d));
        if (D5.g.g(this.f997a)) {
            this.f997a = UUID.randomUUID().toString();
        }
        if (this.f998b == null) {
            if (this.f1001g) {
                int i7 = D5.b.f1192b;
                this.f998b = new D5.c(System.nanoTime() + D5.b.f1191a);
            } else {
                this.f998b = new Date();
            }
        }
        if (D5.g.h(this.c)) {
            this.c = Collections.emptyMap();
        }
        return b(this.f997a, this.f998b, this.c, mapEmptyMap, this.f999e, this.f1000f, this.f1001g);
    }

    public abstract d b(String str, Date date, Map map, Map map2, String str2, String str3, boolean z6);
}
