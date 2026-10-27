package spi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class ce {
    public static Gson a = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().setPrettyPrinting().create();

    private ce() {
    }

    public static <T> T a(String str, Class<T> cls) {
        Gson gson = a;
        if (gson != null) {
            return (T) gson.fromJson(str, (Class) cls);
        }
        return null;
    }
}
