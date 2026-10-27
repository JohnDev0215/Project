package j6;

/* JADX INFO: loaded from: classes.dex */
public final class q extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17063a;

    public q(String str) {
        A7.m.f(str, "newTopScoreSongId");
        this.f17063a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof q) && A7.m.a(this.f17063a, ((q) obj).f17063a);
    }

    public final int hashCode() {
        return this.f17063a.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("TopScoresChanged(newTopScoreSongId="), this.f17063a, ")");
    }
}
