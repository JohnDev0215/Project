package m9;

import c9.C;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CopyOnWriteArraySet f19339a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f19340b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r22 = C.class.getPackage();
        String name = r22 != null ? r22.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        linkedHashMap.put(C.class.getName(), "okhttp.OkHttpClient");
        linkedHashMap.put(j9.e.class.getName(), "okhttp.Http2");
        linkedHashMap.put(f9.c.class.getName(), "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        f19340b = z.y(linkedHashMap);
    }
}
