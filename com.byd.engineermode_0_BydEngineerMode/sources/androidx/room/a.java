package androidx.room;

import android.content.Context;
import androidx.room.RoomDatabase;
import b.m.a.c;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: DatabaseConfiguration.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.InterfaceC0049c f1317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1318b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1319c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RoomDatabase.c f1320d;
    public final List<RoomDatabase.b> e;
    public final boolean f;
    public final RoomDatabase.JournalMode g;
    public final Executor h;
    public final Executor i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    private final Set<Integer> m;
    public final String n;
    public final File o;

    public a(Context context, String str, c.InterfaceC0049c interfaceC0049c, RoomDatabase.c cVar, List<RoomDatabase.b> list, boolean z, RoomDatabase.JournalMode journalMode, Executor executor, Executor executor2, boolean z2, boolean z3, boolean z4, Set<Integer> set, String str2, File file) {
        this.f1317a = interfaceC0049c;
        this.f1318b = context;
        this.f1319c = str;
        this.f1320d = cVar;
        this.e = list;
        this.f = z;
        this.g = journalMode;
        this.h = executor;
        this.i = executor2;
        this.j = z2;
        this.k = z3;
        this.l = z4;
        this.m = set;
        this.n = str2;
        this.o = file;
    }

    public boolean a(int i, int i2) {
        Set<Integer> set;
        if ((i > i2) && this.l) {
            return false;
        }
        return this.k && ((set = this.m) == null || !set.contains(Integer.valueOf(i)));
    }
}
