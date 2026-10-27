package p;

/* JADX INFO: loaded from: classes.dex */
public final class uy2 {
    public Object a;
    public String b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;
    public Object h;
    public Object i;

    public final bh a() {
        String strH = ((String) this.a) == null ? " sdkVersion" : "";
        if (this.b == null) {
            strH = strH.concat(" gmpAppId");
        }
        if (((Integer) this.c) == null) {
            strH = u4.h(strH, " platform");
        }
        if (((String) this.d) == null) {
            strH = u4.h(strH, " installationUuid");
        }
        if (((String) this.e) == null) {
            strH = u4.h(strH, " buildVersion");
        }
        if (((String) this.f) == null) {
            strH = u4.h(strH, " displayVersion");
        }
        if (strH.isEmpty()) {
            return new bh((String) this.a, this.b, ((Integer) this.c).intValue(), (String) this.d, (String) this.e, (String) this.f, (f40) this.g, (p30) this.h, (m30) this.i);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final dh b() {
        String strH = ((Integer) this.a) == null ? " pid" : "";
        if (this.b == null) {
            strH = strH.concat(" processName");
        }
        if (((Integer) this.c) == null) {
            strH = u4.h(strH, " reasonCode");
        }
        if (((Integer) this.d) == null) {
            strH = u4.h(strH, " importance");
        }
        if (((Long) this.e) == null) {
            strH = u4.h(strH, " pss");
        }
        if (((Long) this.f) == null) {
            strH = u4.h(strH, " rss");
        }
        if (((Long) this.g) == null) {
            strH = u4.h(strH, " timestamp");
        }
        if (strH.isEmpty()) {
            return new dh(((Integer) this.a).intValue(), this.b, ((Integer) this.c).intValue(), ((Integer) this.d).intValue(), ((Long) this.e).longValue(), ((Long) this.f).longValue(), ((Long) this.g).longValue(), (String) this.h, (z31) this.i);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final mh c() {
        String strH = ((Integer) this.a) == null ? " arch" : "";
        if (this.b == null) {
            strH = strH.concat(" model");
        }
        if (((Integer) this.c) == null) {
            strH = u4.h(strH, " cores");
        }
        if (((Long) this.d) == null) {
            strH = u4.h(strH, " ram");
        }
        if (((Long) this.e) == null) {
            strH = u4.h(strH, " diskSpace");
        }
        if (((Boolean) this.f) == null) {
            strH = u4.h(strH, " simulator");
        }
        if (((Integer) this.g) == null) {
            strH = u4.h(strH, " state");
        }
        if (((String) this.h) == null) {
            strH = u4.h(strH, " manufacturer");
        }
        if (((String) this.i) == null) {
            strH = u4.h(strH, " modelClass");
        }
        if (strH.isEmpty()) {
            return new mh(((Integer) this.a).intValue(), this.b, ((Integer) this.c).intValue(), ((Long) this.d).longValue(), ((Long) this.e).longValue(), ((Boolean) this.f).booleanValue(), ((Integer) this.g).intValue(), (String) this.h, (String) this.i);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }
}
