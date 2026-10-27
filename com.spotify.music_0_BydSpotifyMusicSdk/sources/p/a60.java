package p;

import android.content.Context;
import android.content.Intent;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class a60 {
    public final Context a;
    public final String b;
    public final ph2 c;
    public final gk1 d;
    public final List e;
    public final boolean f;
    public final RoomDatabase$JournalMode g;
    public final Executor h;
    public final Executor i;
    public final Intent j;
    public final boolean k;
    public final boolean l;
    public final Set m;
    public final List n;
    public final List o;

    public a60(Context context, String str, ph2 ph2Var, gk1 gk1Var, ArrayList arrayList, boolean z, RoomDatabase$JournalMode roomDatabase$JournalMode, Executor executor, Executor executor2, boolean z2, boolean z3, LinkedHashSet linkedHashSet, ArrayList arrayList2, ArrayList arrayList3) {
        v03.j(context, "context");
        v03.j(gk1Var, "migrationContainer");
        v03.j(arrayList2, "typeConverters");
        v03.j(arrayList3, "autoMigrationSpecs");
        this.a = context;
        this.b = str;
        this.c = ph2Var;
        this.d = gk1Var;
        this.e = arrayList;
        this.f = z;
        this.g = roomDatabase$JournalMode;
        this.h = executor;
        this.i = executor2;
        this.j = null;
        this.k = z2;
        this.l = z3;
        this.m = linkedHashSet;
        this.n = arrayList2;
        this.o = arrayList3;
    }

    public final boolean a(int i, int i2) {
        if ((i > i2 && this.l) || !this.k) {
            return false;
        }
        Set set = this.m;
        return set == null || !set.contains(Integer.valueOf(i));
    }
}
