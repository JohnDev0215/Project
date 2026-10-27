package androidx.media;

import android.text.TextUtils;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class e implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f840b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f841c;

    e(String str, int i, int i2) {
        this.f839a = str;
        this.f840b = i;
        this.f841c = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return (this.f840b < 0 || eVar.f840b < 0) ? TextUtils.equals(this.f839a, eVar.f839a) && this.f841c == eVar.f841c : TextUtils.equals(this.f839a, eVar.f839a) && this.f840b == eVar.f840b && this.f841c == eVar.f841c;
    }

    public int hashCode() {
        return androidx.core.d.a.b(this.f839a, Integer.valueOf(this.f841c));
    }
}
