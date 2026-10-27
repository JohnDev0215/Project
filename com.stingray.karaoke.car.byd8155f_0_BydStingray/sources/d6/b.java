package D6;

import A7.m;
import com.stingray.shared.logs.segment.player.CastPlatform;
import com.stingray.shared.logs.segment.player.VideoPlayerBackground;
import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1198a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public VideoPlayerBackground f1199b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CastPlatform f1200e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1201f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1202g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1203h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f1204i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1205j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1206k;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return m.a(this.f1198a, bVar.f1198a) && this.f1199b == bVar.f1199b && this.c == bVar.c && this.d == bVar.d && this.f1200e == bVar.f1200e && m.a(this.f1201f, bVar.f1201f) && this.f1202g == bVar.f1202g && m.a(this.f1203h, bVar.f1203h) && m.a(this.f1204i, bVar.f1204i) && this.f1205j == bVar.f1205j && this.f1206k == bVar.f1206k;
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d(AbstractC1347d.d((this.f1199b.hashCode() + (this.f1198a.hashCode() * 31)) * 31, 31, this.c), 31, this.d);
        CastPlatform castPlatform = this.f1200e;
        return Boolean.hashCode(this.f1206k) + AbstractC1347d.d(M5.b.c(this.f1204i, M5.b.c(this.f1203h, AbstractC2641j.b(this.f1202g, M5.b.c(this.f1201f, (iD + (castPlatform == null ? 0 : castPlatform.hashCode())) * 31, 31), 31), 31), 31), 31, this.f1205j);
    }

    public final String toString() {
        return "GenericPlayerLogInfo(videoPlayer=" + this.f1198a + ", videoPlayerBackground=" + this.f1199b + ", fullScreen=" + this.c + ", leadVocals=" + this.d + ", castPlatform=" + this.f1200e + ", lobbyId=" + this.f1201f + ", queueIndex=" + this.f1202g + ", queueRepeat=" + this.f1203h + ", arFilter=" + this.f1204i + ", isGamingMode=" + this.f1205j + ", isScorable=" + this.f1206k + ")";
    }
}
