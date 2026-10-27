package b.f.e;

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
public final class j implements Iterable<Intent> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<Intent> f1608c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f1609d;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent f();
    }

    public j(Context context) {
        this.f1609d = context;
    }

    public static j d(Context context) {
        return new j(context);
    }

    public j a(Intent intent) {
        this.f1608c.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public j b(Activity activity) {
        Intent intentF = activity instanceof a ? ((a) activity).f() : null;
        if (intentF == null) {
            intentF = d.a(activity);
        }
        if (intentF != null) {
            ComponentName component = intentF.getComponent();
            if (component == null) {
                component = intentF.resolveActivity(this.f1609d.getPackageManager());
            }
            c(component);
            a(intentF);
        }
        return this;
    }

    public j c(ComponentName componentName) {
        int size = this.f1608c.size();
        try {
            Intent intentB = d.b(this.f1609d, componentName);
            while (intentB != null) {
                this.f1608c.add(size, intentB);
                intentB = d.b(this.f1609d, intentB.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }

    public void e() {
        f(null);
    }

    public void f(Bundle bundle) {
        if (this.f1608c.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f1608c;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (b.f.f.a.f(this.f1609d, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f1609d.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f1608c.iterator();
    }
}
