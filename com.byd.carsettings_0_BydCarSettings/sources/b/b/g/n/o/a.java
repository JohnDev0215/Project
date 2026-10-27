package b.b.g.n.o;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import java.util.List;

/* JADX INFO: compiled from: BYDSettingsValueChangeContentObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f1231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<String> f1233c;

    public a(Handler handler, Context context) {
        super(handler);
        this.f1232b = context;
    }

    public void a(c cVar, List list) {
        this.f1231a = cVar;
        this.f1233c = list;
    }

    public void b() {
        this.f1231a = null;
        List<String> list = this.f1233c;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        if (this.f1231a != null) {
            for (String str : this.f1233c) {
                try {
                    this.f1231a.a(str, Settings.System.getInt(this.f1232b.getContentResolver(), str));
                } catch (Settings.SettingNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
