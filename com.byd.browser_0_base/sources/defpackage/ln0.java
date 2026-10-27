package defpackage;

import java.util.HashMap;

/* JADX INFO: compiled from: Flag.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ln0 {
    public static final HashMap<String, ln0> b = new HashMap<>();
    public final String a;

    public ln0(String str) {
        this.a = str;
        b.put(str, this);
    }
}
