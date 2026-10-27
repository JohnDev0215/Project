package b.b.o.a.c;

import java.util.HashMap;

/* JADX INFO: compiled from: CarImagePickerUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap<String, Integer> f2184a = new HashMap<>();

    public static int a(String str) {
        return f2184a.getOrDefault(str, 0).intValue();
    }

    public static void b(String str, Integer num) {
        f2184a.put(str, num);
    }
}
