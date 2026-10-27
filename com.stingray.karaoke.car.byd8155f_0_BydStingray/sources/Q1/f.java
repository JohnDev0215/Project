package Q1;

import A7.m;
import android.content.SharedPreferences;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SharedPreferences f6115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f6116b;

    public f(SharedPreferences sharedPreferences, Set set) {
        m.f(sharedPreferences, "prefs");
        this.f6115a = sharedPreferences;
        this.f6116b = set;
    }
}
