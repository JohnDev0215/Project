package io.sentry.android.replay.video;

import A7.m;
import java.io.File;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f16442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16443b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f16444e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f16445f = "video/avc";

    public a(File file, int i7, int i10, int i11, int i12) {
        this.f16442a = file;
        this.f16443b = i7;
        this.c = i10;
        this.d = i11;
        this.f16444e = i12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return m.a(this.f16442a, aVar.f16442a) && this.f16443b == aVar.f16443b && this.c == aVar.c && this.d == aVar.d && this.f16444e == aVar.f16444e && m.a(this.f16445f, aVar.f16445f);
    }

    public final int hashCode() {
        return this.f16445f.hashCode() + AbstractC2641j.b(this.f16444e, AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC2641j.b(this.f16443b, this.f16442a.hashCode() * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MuxerConfig(file=");
        sb.append(this.f16442a);
        sb.append(", recordingWidth=");
        sb.append(this.f16443b);
        sb.append(", recordingHeight=");
        sb.append(this.c);
        sb.append(", frameRate=");
        sb.append(this.d);
        sb.append(", bitRate=");
        sb.append(this.f16444e);
        sb.append(", mimeType=");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, this.f16445f, ')');
    }
}
