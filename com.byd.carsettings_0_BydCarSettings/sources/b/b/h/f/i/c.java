package b.b.h.f.i;

import java.util.HashMap;

/* JADX INFO: compiled from: DbModel.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, String> f1900a = new HashMap<>();

    public void a(String str, String str2) {
        this.f1900a.put(str, str2);
    }

    public int b(String str) {
        return Integer.valueOf(this.f1900a.get(str)).intValue();
    }
}
