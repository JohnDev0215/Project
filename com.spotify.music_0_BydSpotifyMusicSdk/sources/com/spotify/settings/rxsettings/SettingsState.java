package com.spotify.settings.rxsettings;

import p.c71;
import p.hf2;
import p.u4;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class SettingsState {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public final int f;
    public final int g;
    public final boolean h;
    public final String i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final int m;
    public final boolean n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f64p;
    public final boolean q;
    public final String r;
    public final int s;
    public final boolean t;
    public final boolean u;
    public final boolean v;
    public final boolean w;
    public final boolean x;

    public SettingsState(@z61(name = "offline_mode") boolean z, @z61(name = "play_explicit_content") boolean z2, @z61(name = "private_session") boolean z3, @z61(name = "download_over_3g") boolean z4, @z61(name = "download_quality") int i, @z61(name = "stream_quality") int i2, @z61(name = "stream_non_metered_quality") int i3, @z61(name = "allow_audio_quality_downgrade") boolean z5, @z61(name = "ap") String str, @z61(name = "gapless") boolean z6, @z61(name = "automix") boolean z7, @z61(name = "normalize") boolean z8, @z61(name = "loudness_environment") int i4, @z61(name = "crossfade") boolean z9, @z61(name = "crossfade_time_seconds") int i5, @z61(name = "show_unavailable_tracks") boolean z10, @z61(name = "local_devices_only") boolean z11, @z61(name = "webgate_url") String str2, @z61(name = "download_preferred_resource_type") int i6, @z61(name = "trim_silence") boolean z12, @z61(name = "downmix") boolean z13, @z61(name = "connect_debug") boolean z14, @z61(name = "dynamic_normalizer") boolean z15, @z61(name = "on_the_fly_normalizer") boolean z16) {
        v03.j(str, "accessPoint");
        v03.j(str2, "webgateUrl");
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = i;
        this.f = i2;
        this.g = i3;
        this.h = z5;
        this.i = str;
        this.j = z6;
        this.k = z7;
        this.l = z8;
        this.m = i4;
        this.n = z9;
        this.o = i5;
        this.f64p = z10;
        this.q = z11;
        this.r = str2;
        this.s = i6;
        this.t = z12;
        this.u = z13;
        this.v = z14;
        this.w = z15;
        this.x = z16;
    }

    public final SettingsState copy(@z61(name = "offline_mode") boolean z, @z61(name = "play_explicit_content") boolean z2, @z61(name = "private_session") boolean z3, @z61(name = "download_over_3g") boolean z4, @z61(name = "download_quality") int i, @z61(name = "stream_quality") int i2, @z61(name = "stream_non_metered_quality") int i3, @z61(name = "allow_audio_quality_downgrade") boolean z5, @z61(name = "ap") String str, @z61(name = "gapless") boolean z6, @z61(name = "automix") boolean z7, @z61(name = "normalize") boolean z8, @z61(name = "loudness_environment") int i4, @z61(name = "crossfade") boolean z9, @z61(name = "crossfade_time_seconds") int i5, @z61(name = "show_unavailable_tracks") boolean z10, @z61(name = "local_devices_only") boolean z11, @z61(name = "webgate_url") String str2, @z61(name = "download_preferred_resource_type") int i6, @z61(name = "trim_silence") boolean z12, @z61(name = "downmix") boolean z13, @z61(name = "connect_debug") boolean z14, @z61(name = "dynamic_normalizer") boolean z15, @z61(name = "on_the_fly_normalizer") boolean z16) {
        v03.j(str, "accessPoint");
        v03.j(str2, "webgateUrl");
        return new SettingsState(z, z2, z3, z4, i, i2, i3, z5, str, z6, z7, z8, i4, z9, i5, z10, z11, str2, i6, z12, z13, z14, z15, z16);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SettingsState)) {
            return false;
        }
        SettingsState settingsState = (SettingsState) obj;
        return this.a == settingsState.a && this.b == settingsState.b && this.c == settingsState.c && this.d == settingsState.d && this.e == settingsState.e && this.f == settingsState.f && this.g == settingsState.g && this.h == settingsState.h && v03.b(this.i, settingsState.i) && this.j == settingsState.j && this.k == settingsState.k && this.l == settingsState.l && this.m == settingsState.m && this.n == settingsState.n && this.o == settingsState.o && this.f64p == settingsState.f64p && this.q == settingsState.q && v03.b(this.r, settingsState.r) && this.s == settingsState.s && this.t == settingsState.t && this.u == settingsState.u && this.v == settingsState.v && this.w == settingsState.w && this.x == settingsState.x;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    /* JADX WARN: Type inference failed for: r3v15, types: [int] */
    /* JADX WARN: Type inference failed for: r3v17, types: [int] */
    /* JADX WARN: Type inference failed for: r3v20, types: [int] */
    /* JADX WARN: Type inference failed for: r3v23, types: [int] */
    /* JADX WARN: Type inference failed for: r3v25, types: [int] */
    /* JADX WARN: Type inference failed for: r3v29, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v31, types: [int] */
    /* JADX WARN: Type inference failed for: r3v33, types: [int] */
    /* JADX WARN: Type inference failed for: r3v35, types: [int] */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45 */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v5, types: [int] */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v61 */
    /* JADX WARN: Type inference failed for: r3v62 */
    /* JADX WARN: Type inference failed for: r3v63 */
    public final int hashCode() {
        boolean z = this.a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = r1 * 31;
        boolean z2 = this.b;
        ?? r3 = z2;
        if (z2) {
            r3 = 1;
        }
        int i2 = (i + r3) * 31;
        boolean z3 = this.c;
        ?? r32 = z3;
        if (z3) {
            r32 = 1;
        }
        int i3 = (i2 + r32) * 31;
        boolean z4 = this.d;
        ?? r33 = z4;
        if (z4) {
            r33 = 1;
        }
        int iA = hf2.a(this.g, hf2.a(this.f, hf2.a(this.e, (i3 + r33) * 31, 31), 31), 31);
        boolean z5 = this.h;
        ?? r34 = z5;
        if (z5) {
            r34 = 1;
        }
        int iB = hf2.b(this.i, (iA + r34) * 31, 31);
        boolean z6 = this.j;
        ?? r35 = z6;
        if (z6) {
            r35 = 1;
        }
        int i4 = (iB + r35) * 31;
        boolean z7 = this.k;
        ?? r36 = z7;
        if (z7) {
            r36 = 1;
        }
        int i5 = (i4 + r36) * 31;
        boolean z8 = this.l;
        ?? r37 = z8;
        if (z8) {
            r37 = 1;
        }
        int iA2 = hf2.a(this.m, (i5 + r37) * 31, 31);
        boolean z9 = this.n;
        ?? r38 = z9;
        if (z9) {
            r38 = 1;
        }
        int iA3 = hf2.a(this.o, (iA2 + r38) * 31, 31);
        boolean z10 = this.f64p;
        ?? r39 = z10;
        if (z10) {
            r39 = 1;
        }
        int i6 = (iA3 + r39) * 31;
        boolean z11 = this.q;
        ?? r310 = z11;
        if (z11) {
            r310 = 1;
        }
        int iA4 = hf2.a(this.s, hf2.b(this.r, (i6 + r310) * 31, 31), 31);
        boolean z12 = this.t;
        ?? r311 = z12;
        if (z12) {
            r311 = 1;
        }
        int i7 = (iA4 + r311) * 31;
        boolean z13 = this.u;
        ?? r312 = z13;
        if (z13) {
            r312 = 1;
        }
        int i8 = (i7 + r312) * 31;
        boolean z14 = this.v;
        ?? r313 = z14;
        if (z14) {
            r313 = 1;
        }
        int i9 = (i8 + r313) * 31;
        boolean z15 = this.w;
        ?? r314 = z15;
        if (z15) {
            r314 = 1;
        }
        int i10 = (i9 + r314) * 31;
        boolean z16 = this.x;
        return i10 + (z16 ? 1 : z16);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SettingsState(offlineMode=");
        sb.append(this.a);
        sb.append(", playExplicitContent=");
        sb.append(this.b);
        sb.append(", privateSession=");
        sb.append(this.c);
        sb.append(", downloadOver3g=");
        sb.append(this.d);
        sb.append(", downloadQuality=");
        sb.append(this.e);
        sb.append(", streamQuality=");
        sb.append(this.f);
        sb.append(", streamNonMeteredQuality=");
        sb.append(this.g);
        sb.append(", allowAudioQualityDowngrade=");
        sb.append(this.h);
        sb.append(", accessPoint=");
        sb.append(this.i);
        sb.append(", gapless=");
        sb.append(this.j);
        sb.append(", automix=");
        sb.append(this.k);
        sb.append(", normalize=");
        sb.append(this.l);
        sb.append(", loudnessEnvironment=");
        sb.append(this.m);
        sb.append(", crossfade=");
        sb.append(this.n);
        sb.append(", crossfadeTimeSeconds=");
        sb.append(this.o);
        sb.append(", showUnavailableTracks=");
        sb.append(this.f64p);
        sb.append(", localDevicesOnly=");
        sb.append(this.q);
        sb.append(", webgateUrl=");
        sb.append(this.r);
        sb.append(", downloadPreferredResourceType=");
        sb.append(this.s);
        sb.append(", silenceTrimmer=");
        sb.append(this.t);
        sb.append(", stereoMonoDownmixer=");
        sb.append(this.u);
        sb.append(", connectDebug=");
        sb.append(this.v);
        sb.append(", dynamicNormalizer=");
        sb.append(this.w);
        sb.append(", onTheFlyNormalizer=");
        return u4.n(sb, this.x, ')');
    }
}
