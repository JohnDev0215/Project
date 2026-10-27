package x6;

import android.hardware.bydauto.TsNormalNum;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23810a = 1625;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23811b = TsNormalNum.THOUSAND_SIX;
    public final int c = 1000;
    public final int d = 750;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f23812e = 500;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f23813f = 500;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f23814g = 400;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f23815h = 700;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23816i = 1000;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f23817j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f23818k = 100;
    public final double l = 0.003d;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final double f23819m = 0.001d;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final double f23820n = 0.004d;
    public final int o = 1000;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f23810a == eVar.f23810a && this.f23811b == eVar.f23811b && this.c == eVar.c && this.d == eVar.d && this.f23812e == eVar.f23812e && this.f23813f == eVar.f23813f && this.f23814g == eVar.f23814g && this.f23815h == eVar.f23815h && this.f23816i == eVar.f23816i && this.f23817j == eVar.f23817j && this.f23818k == eVar.f23818k && Double.compare(this.l, eVar.l) == 0 && Double.compare(this.f23819m, eVar.f23819m) == 0 && Double.compare(this.f23820n, eVar.f23820n) == 0 && this.o == eVar.o;
    }

    public final int hashCode() {
        return Integer.hashCode(this.o) + ((Double.hashCode(this.f23820n) + ((Double.hashCode(this.f23819m) + ((Double.hashCode(this.l) + AbstractC2641j.b(this.f23818k, AbstractC2641j.b(this.f23817j, AbstractC2641j.b(this.f23816i, AbstractC2641j.b(this.f23815h, AbstractC2641j.b(this.f23814g, AbstractC2641j.b(this.f23813f, AbstractC2641j.b(this.f23812e, AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC2641j.b(this.f23811b, Integer.hashCode(this.f23810a) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31), 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LyricsTimingConfig(pageWaitForBreak=");
        sb.append(this.f23810a);
        sb.append(", pageWaitStartNewPage=");
        sb.append(this.f23811b);
        sb.append(", cueBarDuration=");
        sb.append(this.c);
        sb.append(", cueBarMinimumDelay=");
        sb.append(this.d);
        sb.append(", breakFadeDuration=");
        sb.append(this.f23812e);
        sb.append(", timelineItemFadeDuration=");
        sb.append(this.f23813f);
        sb.append(", lineFadeInDuration=");
        sb.append(this.f23814g);
        sb.append(", lineFadeOutDuration=");
        sb.append(this.f23815h);
        sb.append(", delayBeforeStart=");
        sb.append(this.f23816i);
        sb.append(", delayBeforeEnd=");
        sb.append(this.f23817j);
        sb.append(", delayBetweenFadeIn=");
        sb.append(this.f23818k);
        sb.append(", defaultSlopeValue=");
        sb.append(this.l);
        sb.append(", minSlopeValue=");
        sb.append(this.f23819m);
        sb.append(", maxSlopeValue=");
        sb.append(this.f23820n);
        sb.append(", delayBeforeCredits=");
        return M5.b.h(sb, this.o, ")");
    }
}
