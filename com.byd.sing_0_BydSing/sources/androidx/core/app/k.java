package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TaskStackBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements Iterable<Intent> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<Intent> f1391b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1392c;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent h();
    }

    private k(Context context) {
        this.f1392c = context;
    }

    public static k d(Context context) {
        return new k(context);
    }

    public k a(Intent intent) {
        this.f1391b.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public k b(Activity activity) {
        Intent intentH = activity instanceof a ? ((a) activity).h() : null;
        if (intentH == null) {
            intentH = d.a(activity);
        }
        if (intentH != null) {
            ComponentName component = intentH.getComponent();
            if (component == null) {
                component = intentH.resolveActivity(this.f1392c.getPackageManager());
            }
            c(component);
            a(intentH);
        }
        return this;
    }

    public k c(ComponentName componentName) {
        int size = this.f1391b.size();
        try {
            Intent intentB = d.b(this.f1392c, componentName);
            while (intentB != null) {
                this.f1391b.add(size, intentB);
                intentB = d.b(this.f1392c, intentB.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    public void e() {
        f(null);
    }

    public void f(Bundle bundle) {
        if (this.f1391b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f1391b;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (androidx.core.content.a.g(this.f1392c, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f1392c.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f1391b.iterator();
    }
}
