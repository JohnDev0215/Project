package p5;

import j5.m;
import j5.y;
import j5.z;
import java.sql.Timestamp;
import java.util.Date;
import q5.C2185a;

/* JADX INFO: renamed from: p5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2107c implements z {
    @Override // j5.z
    public final y a(m mVar, C2185a c2185a) {
        if (c2185a.f21253a != Timestamp.class) {
            return null;
        }
        mVar.getClass();
        return new C2108d(mVar.c(new C2185a(Date.class)));
    }
}
