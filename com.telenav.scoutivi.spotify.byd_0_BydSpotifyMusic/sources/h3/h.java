package h3;

import android.util.Log;

/* JADX INFO: compiled from: LogcatLogStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements g {
    @Override // h3.g
    public void a(int i7, String str, String str2) {
        m.a(str2);
        if (str == null) {
            str = "NO_TAG";
        }
        Log.println(i7, str, str2);
    }
}
