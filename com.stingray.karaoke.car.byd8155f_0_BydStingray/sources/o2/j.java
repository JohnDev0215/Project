package o2;

import android.content.Context;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.util.Pair;
import e2.C1192A;
import e2.C1208p;
import e2.O;
import e2.c0;
import e2.e0;
import e2.f0;
import h2.AbstractC1343A;
import java.util.HashMap;
import l5.C1778f;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f20424A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f20425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f20426b;
    public final PlaybackSession c;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f20431i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public PlaybackMetrics.Builder f20432j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f20433k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public O f20435n;
    public C1778f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1778f f20436p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C1778f f20437q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C1208p f20438r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C1208p f20439s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public C1208p f20440t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f20441u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f20442v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f20443w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f20444x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f20445y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f20446z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e0 f20427e = new e0();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c0 f20428f = new c0();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f20430h = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f20429g = new HashMap();
    public final long d = SystemClock.elapsedRealtime();
    public int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20434m = 0;

    public j(Context context, PlaybackSession playbackSession) {
        this.f20425a = context.getApplicationContext();
        this.c = playbackSession;
        g gVar = new g();
        this.f20426b = gVar;
        gVar.d = this;
    }

    public final boolean a(C1778f c1778f) {
        String str;
        if (c1778f != null) {
            String str2 = (String) c1778f.f18639n;
            g gVar = this.f20426b;
            synchronized (gVar) {
                str = gVar.f20422f;
            }
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final void b() {
        PlaybackMetrics.Builder builder = this.f20432j;
        if (builder != null && this.f20424A) {
            builder.setAudioUnderrunCount(this.f20446z);
            this.f20432j.setVideoFramesDropped(this.f20444x);
            this.f20432j.setVideoFramesPlayed(this.f20445y);
            Long l = (Long) this.f20429g.get(this.f20431i);
            this.f20432j.setNetworkTransferDurationMillis(l == null ? 0L : l.longValue());
            Long l10 = (Long) this.f20430h.get(this.f20431i);
            this.f20432j.setNetworkBytesRead(l10 == null ? 0L : l10.longValue());
            this.f20432j.setStreamSource((l10 == null || l10.longValue() <= 0) ? 0 : 1);
            this.c.reportPlaybackMetrics(this.f20432j.build());
        }
        this.f20432j = null;
        this.f20431i = null;
        this.f20446z = 0;
        this.f20444x = 0;
        this.f20445y = 0;
        this.f20438r = null;
        this.f20439s = null;
        this.f20440t = null;
        this.f20424A = false;
    }

    public final void c(f0 f0Var, C2492C c2492c) {
        int iB;
        PlaybackMetrics.Builder builder = this.f20432j;
        if (c2492c == null || (iB = f0Var.b(c2492c.f22663a)) == -1) {
            return;
        }
        c0 c0Var = this.f20428f;
        int i7 = 0;
        f0Var.f(iB, c0Var, false);
        int i10 = c0Var.c;
        e0 e0Var = this.f20427e;
        f0Var.n(i10, e0Var);
        C1192A c1192a = e0Var.c.f13879b;
        if (c1192a != null) {
            int iB2 = AbstractC1343A.B(c1192a.f13845a, c1192a.f13846b);
            i7 = iB2 != 0 ? iB2 != 1 ? iB2 != 2 ? 1 : 4 : 5 : 3;
        }
        builder.setStreamType(i7);
        if (e0Var.f14094m != -9223372036854775807L && !e0Var.f14093k && !e0Var.f14091i && !e0Var.a()) {
            builder.setMediaDurationMillis(AbstractC1343A.T(e0Var.f14094m));
        }
        builder.setPlaybackType(e0Var.a() ? 2 : 1);
        this.f20424A = true;
    }

    public final void d(C2024a c2024a, String str) {
        C2492C c2492c = c2024a.d;
        if ((c2492c == null || !c2492c.c()) && str.equals(this.f20431i)) {
            b();
        }
        this.f20429g.remove(str);
        this.f20430h.remove(str);
    }

    public final void e(int i7, long j3, C1208p c1208p, int i10) {
        int i11;
        TrackChangeEvent.Builder timeSinceCreatedMillis = h.n(i7).setTimeSinceCreatedMillis(j3 - this.d);
        if (c1208p != null) {
            timeSinceCreatedMillis.setTrackState(1);
            if (i10 != 1) {
                i11 = 3;
                if (i10 != 2) {
                    i11 = i10 != 3 ? 1 : 4;
                }
            } else {
                i11 = 2;
            }
            timeSinceCreatedMillis.setTrackChangeReason(i11);
            String str = c1208p.f14315m;
            if (str != null) {
                timeSinceCreatedMillis.setContainerMimeType(str);
            }
            String str2 = c1208p.f14316n;
            if (str2 != null) {
                timeSinceCreatedMillis.setSampleMimeType(str2);
            }
            String str3 = c1208p.f14314k;
            if (str3 != null) {
                timeSinceCreatedMillis.setCodecName(str3);
            }
            int i12 = c1208p.f14313j;
            if (i12 != -1) {
                timeSinceCreatedMillis.setBitrate(i12);
            }
            int i13 = c1208p.f14322u;
            if (i13 != -1) {
                timeSinceCreatedMillis.setWidth(i13);
            }
            int i14 = c1208p.f14323v;
            if (i14 != -1) {
                timeSinceCreatedMillis.setHeight(i14);
            }
            int i15 = c1208p.f14295C;
            if (i15 != -1) {
                timeSinceCreatedMillis.setChannelCount(i15);
            }
            int i16 = c1208p.f14296D;
            if (i16 != -1) {
                timeSinceCreatedMillis.setAudioSampleRate(i16);
            }
            String str4 = c1208p.d;
            if (str4 != null) {
                int i17 = AbstractC1343A.f15195a;
                String[] strArrSplit = str4.split("-", -1);
                Pair pairCreate = Pair.create(strArrSplit[0], strArrSplit.length >= 2 ? strArrSplit[1] : null);
                timeSinceCreatedMillis.setLanguage((String) pairCreate.first);
                Object obj = pairCreate.second;
                if (obj != null) {
                    timeSinceCreatedMillis.setLanguageRegion((String) obj);
                }
            }
            float f10 = c1208p.f14324w;
            if (f10 != -1.0f) {
                timeSinceCreatedMillis.setVideoFrameRate(f10);
            }
        } else {
            timeSinceCreatedMillis.setTrackState(0);
        }
        this.f20424A = true;
        this.c.reportTrackChangeEvent(timeSinceCreatedMillis.build());
    }
}
