package q3;

import android.util.Log;
import h3.g;
import java.security.SecureRandom;

/* JADX INFO: compiled from: CustomLogCatStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f9153a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f9154b;

    public b(int i7) {
        this.f9153a = i7;
    }

    private String b() {
        int iNextDouble = (int) (new SecureRandom().nextDouble() * 10.0d);
        if (iNextDouble == this.f9154b) {
            iNextDouble = (iNextDouble + 1) % 10;
        }
        this.f9154b = iNextDouble;
        return String.valueOf(iNextDouble);
    }

    @Override // h3.g
    public void a(int i7, String str, String str2) {
        if (i7 >= this.f9153a) {
            Log.println(i7, b() + str, str2);
        }
    }
}
