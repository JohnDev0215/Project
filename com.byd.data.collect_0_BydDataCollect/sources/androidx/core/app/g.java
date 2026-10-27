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
public final class g implements Iterable<Intent> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<Intent> f1202b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1203c;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent h();
    }

    private g(Context context) {
        this.f1203c = context;
    }

    public static g e(Context context) {
        return new g(context);
    }

    public g a(Intent intent) {
        this.f1202b.add(intent);
        return this;
    }

    public g b(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            component = intent.resolveActivity(this.f1203c.getPackageManager());
        }
        if (component != null) {
            d(component);
        }
        a(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g c(Activity activity) {
        Intent intentH = activity instanceof a ? ((a) activity).h() : null;
        if (intentH == null) {
            intentH = e.a(activity);
        }
        if (intentH != null) {
            ComponentName component = intentH.getComponent();
            if (component == null) {
                component = intentH.resolveActivity(this.f1203c.getPackageManager());
            }
            d(component);
            a(intentH);
        }
        return this;
    }

    public g d(ComponentName componentName) {
        int size = this.f1202b.size();
        try {
            Intent intentB = e.b(this.f1203c, componentName);
            while (intentB != null) {
                this.f1202b.add(size, intentB);
                intentB = e.b(this.f1203c, intentB.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }

    public void f() {
        g(null);
    }

    public void g(Bundle bundle) {
        if (this.f1202b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f1202b;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (androidx.core.content.a.g(this.f1203c, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f1203c.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f1202b.iterator();
    }
}
