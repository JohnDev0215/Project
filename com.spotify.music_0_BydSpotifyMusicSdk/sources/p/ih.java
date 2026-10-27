package p;

/* JADX INFO: loaded from: classes.dex */
public final class ih {
    public String a;
    public String b;
    public Long c;
    public Long d;
    public Boolean e;
    public r30 f;
    public e40 g;
    public d40 h;
    public s30 i;
    public z31 j;
    public Integer k;

    public final jh a() {
        String strH = this.a == null ? " generator" : "";
        if (this.b == null) {
            strH = strH.concat(" identifier");
        }
        if (this.c == null) {
            strH = u4.h(strH, " startedAt");
        }
        if (this.e == null) {
            strH = u4.h(strH, " crashed");
        }
        if (this.f == null) {
            strH = u4.h(strH, " app");
        }
        if (this.k == null) {
            strH = u4.h(strH, " generatorType");
        }
        if (strH.isEmpty()) {
            return new jh(this.a, this.b, this.c.longValue(), this.d, this.e.booleanValue(), this.f, this.g, this.h, this.i, this.j, this.k.intValue());
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }
}
