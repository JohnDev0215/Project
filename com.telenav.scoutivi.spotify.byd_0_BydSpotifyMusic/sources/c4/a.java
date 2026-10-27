package c4;

import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: compiled from: SearchKeyword.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3602b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3603c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f3606f;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            return TextUtils.equals(this.f3602b, ((a) obj).f3602b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.f3601a));
    }
}
