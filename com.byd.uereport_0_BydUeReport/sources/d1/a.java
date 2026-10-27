package d1;

import android.content.Context;
import androidx.room.RoomDatabase;
import h1.c;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: DatabaseConfiguration.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.InterfaceC0050c f4334a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f4335b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4336c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RoomDatabase.c f4337d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<RoomDatabase.b> f4338e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4339f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RoomDatabase.JournalMode f4340g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Executor f4341h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Executor f4342i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f4343j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f4344k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f4345l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Set<Integer> f4346m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f4347n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final File f4348o;

    public a(Context context, String str, c.InterfaceC0050c interfaceC0050c, RoomDatabase.c cVar, List<RoomDatabase.b> list, boolean z7, RoomDatabase.JournalMode journalMode, Executor executor, Executor executor2, boolean z8, boolean z9, boolean z10, Set<Integer> set, String str2, File file) {
        this.f4334a = interfaceC0050c;
        this.f4335b = context;
        this.f4336c = str;
        this.f4337d = cVar;
        this.f4338e = list;
        this.f4339f = z7;
        this.f4340g = journalMode;
        this.f4341h = executor;
        this.f4342i = executor2;
        this.f4343j = z8;
        this.f4344k = z9;
        this.f4345l = z10;
        this.f4346m = set;
        this.f4347n = str2;
        this.f4348o = file;
    }

    public boolean a(int i8, int i9) {
        Set<Integer> set;
        if ((i8 > i9) && this.f4345l) {
            return false;
        }
        return this.f4344k && ((set = this.f4346m) == null || !set.contains(Integer.valueOf(i8)));
    }
}
