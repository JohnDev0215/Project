package C5;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class h extends a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1006h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f1007i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Map f1008j;

    @Override // C5.a
    public final d b(String str, Date date, Map map, Map map2, String str2, String str3, boolean z6) {
        switch (this.f1006h) {
            case 0:
                if (D5.g.g(this.f1007i)) {
                    throw new NullPointerException("either name or category is required");
                }
                Map mapEmptyMap = this.f1008j;
                if (D5.g.h(mapEmptyMap)) {
                    mapEmptyMap = Collections.emptyMap();
                }
                String str4 = this.f1007i;
                i iVar = new i(c.f1003m, str, date, map, map2, str2, str3, z6);
                if (!D5.g.g(str4)) {
                    iVar.f(str4, "name");
                }
                iVar.f(mapEmptyMap, "properties");
                return iVar;
            default:
                D5.g.b(this.f1007i, "event");
                Map mapEmptyMap2 = this.f1008j;
                if (D5.g.h(mapEmptyMap2)) {
                    mapEmptyMap2 = Collections.emptyMap();
                }
                String str5 = this.f1007i;
                j jVar = new j(c.f1004n, str, date, map, map2, str2, str3, z6);
                jVar.f(str5, "event");
                jVar.f(mapEmptyMap2, "properties");
                return jVar;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:161:0x0360 A[Catch: IllegalArgumentException | IndexOutOfBoundsException -> 0x0295, TRY_LEAVE, TryCatch #1 {IllegalArgumentException | IndexOutOfBoundsException -> 0x0295, blocks: (B:112:0x026b, B:114:0x0274, B:116:0x027c, B:121:0x029c, B:123:0x02ac, B:124:0x02ae, B:126:0x02ba, B:127:0x02bd, B:129:0x02c3, B:135:0x02d3, B:141:0x02ec, B:143:0x02f2, B:144:0x02f6, B:146:0x02fc, B:151:0x030b, B:153:0x0316, B:159:0x035a, B:161:0x0360, B:184:0x03df, B:184:0x03df, B:170:0x0378, B:170:0x0378, B:171:0x038c, B:171:0x038c, B:172:0x038d, B:172:0x038d, B:174:0x0397, B:174:0x0397, B:177:0x039e, B:177:0x039e, B:179:0x03b8, B:179:0x03b8, B:182:0x03c3, B:182:0x03c3, B:183:0x03de, B:183:0x03de, B:188:0x0452, B:188:0x0452, B:189:0x0459, B:189:0x0459, B:152:0x0310), top: B:196:0x026b }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0452 A[Catch: IllegalArgumentException | IndexOutOfBoundsException -> 0x0295, IllegalArgumentException | IndexOutOfBoundsException -> 0x0295, TRY_ENTER, TryCatch #1 {IllegalArgumentException | IndexOutOfBoundsException -> 0x0295, blocks: (B:112:0x026b, B:114:0x0274, B:116:0x027c, B:121:0x029c, B:123:0x02ac, B:124:0x02ae, B:126:0x02ba, B:127:0x02bd, B:129:0x02c3, B:135:0x02d3, B:141:0x02ec, B:143:0x02f2, B:144:0x02f6, B:146:0x02fc, B:151:0x030b, B:153:0x0316, B:159:0x035a, B:161:0x0360, B:184:0x03df, B:184:0x03df, B:170:0x0378, B:170:0x0378, B:171:0x038c, B:171:0x038c, B:172:0x038d, B:172:0x038d, B:174:0x0397, B:174:0x0397, B:177:0x039e, B:177:0x039e, B:179:0x03b8, B:179:0x03b8, B:182:0x03c3, B:182:0x03c3, B:183:0x03de, B:183:0x03de, B:188:0x0452, B:188:0x0452, B:189:0x0459, B:189:0x0459, B:152:0x0310), top: B:196:0x026b }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x016f A[Catch: IllegalArgumentException | IndexOutOfBoundsException -> 0x009c, TRY_LEAVE, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException -> 0x009c, blocks: (B:21:0x007f, B:23:0x0085, B:25:0x008d, B:31:0x00a3, B:33:0x00b3, B:34:0x00b5, B:36:0x00c1, B:38:0x00c5, B:40:0x00cb, B:46:0x00db, B:52:0x00f4, B:54:0x00fa, B:55:0x00fe, B:57:0x0104, B:62:0x0113, B:64:0x011e, B:70:0x0169, B:72:0x016f, B:95:0x01f1, B:95:0x01f1, B:81:0x0188, B:81:0x0188, B:82:0x019c, B:82:0x019c, B:83:0x019d, B:83:0x019d, B:85:0x01a7, B:85:0x01a7, B:88:0x01ae, B:88:0x01ae, B:90:0x01c8, B:90:0x01c8, B:93:0x01d3, B:93:0x01d3, B:94:0x01f0, B:94:0x01f0, B:97:0x022a, B:97:0x022a, B:98:0x0231, B:98:0x0231, B:63:0x0118), top: B:197:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x022a A[Catch: IllegalArgumentException | IndexOutOfBoundsException -> 0x009c, IllegalArgumentException | IndexOutOfBoundsException -> 0x009c, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException -> 0x009c, blocks: (B:21:0x007f, B:23:0x0085, B:25:0x008d, B:31:0x00a3, B:33:0x00b3, B:34:0x00b5, B:36:0x00c1, B:38:0x00c5, B:40:0x00cb, B:46:0x00db, B:52:0x00f4, B:54:0x00fa, B:55:0x00fe, B:57:0x0104, B:62:0x0113, B:64:0x011e, B:70:0x0169, B:72:0x016f, B:95:0x01f1, B:95:0x01f1, B:81:0x0188, B:81:0x0188, B:82:0x019c, B:82:0x019c, B:83:0x019d, B:83:0x019d, B:85:0x01a7, B:85:0x01a7, B:88:0x01ae, B:88:0x01ae, B:90:0x01c8, B:90:0x01c8, B:93:0x01d3, B:93:0x01d3, B:94:0x01f0, B:94:0x01f0, B:97:0x022a, B:97:0x022a, B:98:0x0231, B:98:0x0231, B:63:0x0118), top: B:197:0x007f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public h(C5.d r30, int r31) {
        /*
            Method dump skipped, instruction units count: 1129
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C5.h.<init>(C5.d, int):void");
    }
}
