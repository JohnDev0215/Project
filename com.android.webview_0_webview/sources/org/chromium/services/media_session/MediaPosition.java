package org.chromium.services.media_session;

import android.os.SystemClock;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPosition {
    public Long a;
    public Long b;
    public Float c;
    public Long d;

    public MediaPosition(long j, long j2, float f, long j3) {
        this.a = Long.valueOf(j);
        this.b = Long.valueOf(j2);
        this.c = Float.valueOf(f);
        this.d = Long.valueOf(j3);
    }

    public static MediaPosition create(long j, long j2, float f, long j3) {
        return new MediaPosition(j, j2, f, j3 - (System.currentTimeMillis() - SystemClock.elapsedRealtime()));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaPosition)) {
            return false;
        }
        MediaPosition mediaPosition = (MediaPosition) obj;
        return this.a.longValue() == mediaPosition.a.longValue() && this.b.longValue() == mediaPosition.b.longValue() && this.c.floatValue() == mediaPosition.c.floatValue() && this.d.longValue() == mediaPosition.d.longValue();
    }

    public int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sbF = Z7.f("duration=");
        sbF.append(this.a);
        sbF.append(", position=");
        sbF.append(this.b);
        sbF.append(", rate=");
        sbF.append(this.c);
        sbF.append(", updated=");
        sbF.append(this.d);
        return sbF.toString();
    }
}
