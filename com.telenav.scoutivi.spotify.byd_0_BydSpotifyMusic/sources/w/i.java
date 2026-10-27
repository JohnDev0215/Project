package w;

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
public final class i implements Iterable<Intent> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ArrayList<Intent> f9696e = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Context f9697g;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent i();
    }

    private i(Context context) {
        this.f9697g = context;
    }

    public static i g(Context context) {
        return new i(context);
    }

    public i c(Intent intent) {
        this.f9696e.add(intent);
        return this;
    }

    public i d(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            component = intent.resolveActivity(this.f9697g.getPackageManager());
        }
        if (component != null) {
            f(component);
        }
        c(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public i e(Activity activity) {
        Intent intentI = activity instanceof a ? ((a) activity).i() : null;
        if (intentI == null) {
            intentI = g.a(activity);
        }
        if (intentI != null) {
            ComponentName component = intentI.getComponent();
            if (component == null) {
                component = intentI.resolveActivity(this.f9697g.getPackageManager());
            }
            f(component);
            c(intentI);
        }
        return this;
    }

    public i f(ComponentName componentName) {
        int size = this.f9696e.size();
        try {
            Intent intentB = g.b(this.f9697g, componentName);
            while (intentB != null) {
                this.f9696e.add(size, intentB);
                intentB = g.b(this.f9697g, intentB.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e7) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e7);
        }
    }

    public Intent h(int i7) {
        return this.f9696e.get(i7);
    }

    public int i() {
        return this.f9696e.size();
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f9696e.iterator();
    }

    public void j() {
        k(null);
    }

    public void k(Bundle bundle) {
        if (this.f9696e.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f9696e;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (x.a.d(this.f9697g, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f9697g.startActivity(intent);
    }
}
