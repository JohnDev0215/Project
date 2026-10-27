package N5;

import com.stingray.kca.model.AudioResponse;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.kca.model.PitchInfoResponse;

/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KaraokeSongResponseV2 f4691a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AudioResponse f4692b;
    public PitchInfoResponse c;
    public final String d;

    public f0(KaraokeSongResponseV2 karaokeSongResponseV2) {
        A7.m.f(karaokeSongResponseV2, "songData");
        this.f4691a = karaokeSongResponseV2;
        this.d = A7.k.f("toString(...)");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f0) {
            f0 f0Var = (f0) obj;
            if (A7.m.a(this.d, f0Var.d) && A7.m.a(this.f4691a.f13136a, f0Var.f4691a.f13136a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() + (this.f4691a.hashCode() * 31);
    }

    public final String toString() {
        return "SongQueueItem(songData=" + this.f4691a + ")";
    }
}
