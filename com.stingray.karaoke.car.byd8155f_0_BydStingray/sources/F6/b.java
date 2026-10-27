package F6;

import A7.m;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import com.stingray.shared.logs.segment.songQueue.SourceType;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SourceType f2425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2426b;

    public b(SourceType sourceType, String str) {
        m.f(sourceType, "type");
        m.f(str, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.f2425a = sourceType;
        this.f2426b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2425a == bVar.f2425a && m.a(this.f2426b, bVar.f2426b);
    }

    public final int hashCode() {
        return this.f2426b.hashCode() + (this.f2425a.hashCode() * 31);
    }

    public final String toString() {
        return "SourceLog(type=" + this.f2425a + ", source=" + this.f2426b + ")";
    }
}
