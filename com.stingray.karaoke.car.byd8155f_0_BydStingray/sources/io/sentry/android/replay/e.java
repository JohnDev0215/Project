package io.sentry.android.replay;

import h2.AbstractC1347d;
import io.sentry.G1;
import java.util.Date;
import java.util.List;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f16389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f16390b;
    public final Date c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f16391e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final G1 f16392f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f16393g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f16394h;

    public e(u uVar, i iVar, Date date, int i7, long j3, G1 g12, String str, List list) {
        this.f16389a = uVar;
        this.f16390b = iVar;
        this.c = date;
        this.d = i7;
        this.f16391e = j3;
        this.f16392f = g12;
        this.f16393g = str;
        this.f16394h = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return A7.m.a(this.f16389a, eVar.f16389a) && A7.m.a(this.f16390b, eVar.f16390b) && A7.m.a(this.c, eVar.c) && this.d == eVar.d && this.f16391e == eVar.f16391e && this.f16392f == eVar.f16392f && A7.m.a(this.f16393g, eVar.f16393g) && A7.m.a(this.f16394h, eVar.f16394h);
    }

    public final int hashCode() {
        int iHashCode = (this.f16392f.hashCode() + AbstractC1347d.c(AbstractC2641j.b(this.d, (this.c.hashCode() + ((this.f16390b.hashCode() + (this.f16389a.hashCode() * 31)) * 31)) * 31, 31), 31, this.f16391e)) * 31;
        String str = this.f16393g;
        return this.f16394h.hashCode() + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final String toString() {
        return "LastSegmentData(recorderConfig=" + this.f16389a + ", cache=" + this.f16390b + ", timestamp=" + this.c + ", id=" + this.d + ", duration=" + this.f16391e + ", replayType=" + this.f16392f + ", screenAtStart=" + this.f16393g + ", events=" + this.f16394h + ')';
    }
}
