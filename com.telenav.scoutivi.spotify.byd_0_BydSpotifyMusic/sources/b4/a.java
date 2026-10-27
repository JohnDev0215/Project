package b4;

import android.app.Application;
import android.content.Context;
import c4.b;
import com.vivid.spotify.data.database.AppRoomDatabase;
import h3.i;
import y0.e;
import y0.f;

/* JADX INFO: compiled from: RoomModule.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AppRoomDatabase f3553a;

    public a(Application application) {
        b(application);
    }

    private void b(Context context) {
        i.b("RoomModule").b("room module init", new Object[0]);
        this.f3553a = (AppRoomDatabase) e.a(context, AppRoomDatabase.class, "music.db").a().c(f.c.TRUNCATE).b();
    }

    public b a() {
        return this.f3553a.r();
    }
}
