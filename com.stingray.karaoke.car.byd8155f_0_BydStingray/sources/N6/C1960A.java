package n6;

import com.stingray.karaoke.scoring.NoteResult;
import h2.AbstractC1347d;
import java.util.ArrayList;
import x.AbstractC2641j;

/* JADX INFO: renamed from: n6.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1960A extends AbstractC1961B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19977b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f19979f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f19980g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f19982i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f19983j;
    public final G7.a l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f19978e = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public NoteResult f19981h = NoteResult.UNKNOWN;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList f19984k = new ArrayList();

    public C1960A(long j3, long j10, int i7, int i10, boolean z6, long j11) {
        this.f19976a = j3;
        this.f19977b = j10;
        this.c = i7;
        this.d = i10;
        this.f19979f = z6;
        this.f19980g = j11;
        float f10 = i7;
        float f11 = 6;
        this.l = new G7.a(f10 - f11, f10 + f11);
    }

    @Override // n6.AbstractC1961B
    public final long a() {
        return this.f19977b;
    }

    @Override // n6.AbstractC1961B
    public final G7.a b(Long l) {
        return this.l;
    }

    @Override // n6.AbstractC1961B
    public final long c() {
        return this.f19976a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1960A)) {
            return false;
        }
        C1960A c1960a = (C1960A) obj;
        return this.f19976a == c1960a.f19976a && this.f19977b == c1960a.f19977b && this.c == c1960a.c && this.d == c1960a.d && A7.m.a(this.f19978e, c1960a.f19978e) && this.f19979f == c1960a.f19979f && this.f19980g == c1960a.f19980g;
    }

    public final int hashCode() {
        return Long.hashCode(this.f19980g) + AbstractC1347d.d(M5.b.c(this.f19978e, AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC1347d.c(Long.hashCode(this.f19976a) * 31, 31, this.f19977b), 31), 31), 31), 31, this.f19979f);
    }

    public final String toString() {
        String str = this.f19978e;
        StringBuilder sb = new StringBuilder("MidiNote(startTick=");
        sb.append(this.f19976a);
        sb.append(", endTick=");
        sb.append(this.f19977b);
        sb.append(", pitch=");
        sb.append(this.c);
        sb.append(", velocity=");
        M5.b.p(sb, this.d, ", syllable=", str, ", isRhythmNote=");
        sb.append(this.f19979f);
        sb.append(", forgivenessTicks=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f19980g, ")", sb);
    }
}
