package defpackage;

import android.graphics.Typeface;
import android.os.HandlerThread;
import defpackage.gt0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: GoogleFontService.java */
/* JADX INFO: loaded from: classes.dex */
public final class ft0 extends a8 {
    public final /* synthetic */ HandlerThread A;
    public final /* synthetic */ gt0 B;
    public final /* synthetic */ Map w;
    public final /* synthetic */ mh3 x;
    public final /* synthetic */ Set y;
    public final /* synthetic */ gt0.a z;

    public ft0(gt0 gt0Var, HashMap map, mh3 mh3Var, HashSet hashSet, do1 do1Var, HandlerThread handlerThread) {
        this.B = gt0Var;
        this.w = map;
        this.x = mh3Var;
        this.y = hashSet;
        this.z = do1Var;
        this.A = handlerThread;
    }

    @Override // defpackage.a8
    public final void Y(int i) {
        nh3 nh3Var = new nh3(i);
        mh3 mh3Var = this.x;
        Map map = this.w;
        map.put(mh3Var, nh3Var);
        gt0.a(this.B, map, this.y.size(), this.z, this.A);
    }

    @Override // defpackage.a8
    public final void Z(Typeface typeface) {
        nh3 nh3Var = new nh3(typeface);
        Map map = this.w;
        map.put(this.x, nh3Var);
        gt0.a(this.B, map, this.y.size(), this.z, this.A);
    }
}
