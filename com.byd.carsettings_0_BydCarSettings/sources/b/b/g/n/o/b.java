package b.b.g.n.o;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import java.util.ArrayList;

/* JADX INFO: compiled from: BydContentObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<String> f1234a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f1235b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1236c;

    public b(Context context) {
        this.f1236c = context;
    }

    public void a(c cVar, String... strArr) {
        if (this.f1235b == null) {
            this.f1235b = new a(new Handler(), this.f1236c);
        }
        if (this.f1234a != null) {
            for (int i = 0; i < strArr.length; i++) {
                this.f1236c.getContentResolver().registerContentObserver(Settings.System.getUriFor(strArr[i]), true, this.f1235b);
                this.f1234a.add(strArr[i]);
            }
            if (this.f1234a.size() > 0) {
                this.f1235b.a(cVar, this.f1234a);
            }
        }
    }

    public void b() {
        a aVar = this.f1235b;
        if (aVar != null) {
            aVar.b();
        }
        ArrayList<String> arrayList = this.f1234a;
        if (arrayList != null && arrayList.size() > 0) {
            this.f1234a.clear();
            this.f1234a = null;
        }
        Context context = this.f1236c;
        if (context != null) {
            context.getContentResolver().unregisterContentObserver(this.f1235b);
            this.f1236c = null;
            this.f1235b = null;
        }
    }
}
