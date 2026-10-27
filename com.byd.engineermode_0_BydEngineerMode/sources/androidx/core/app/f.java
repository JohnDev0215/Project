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
public final class f implements Iterable<Intent> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<Intent> f842b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f843c;

    /* JADX INFO: compiled from: TaskStackBuilder.java */
    public interface a {
        Intent f();
    }

    private f(Context context) {
        this.f843c = context;
    }

    public static f d(Context context) {
        return new f(context);
    }

    public f a(Intent intent) {
        this.f842b.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f b(Activity activity) {
        Intent intentF = activity instanceof a ? ((a) activity).f() : null;
        if (intentF == null) {
            intentF = d.a(activity);
        }
        if (intentF != null) {
            ComponentName component = intentF.getComponent();
            if (component == null) {
                component = intentF.resolveActivity(this.f843c.getPackageManager());
            }
            c(component);
            a(intentF);
        }
        return this;
    }

    public f c(ComponentName componentName) {
        int size = this.f842b.size();
        try {
            Intent intentB = d.b(this.f843c, componentName);
            while (intentB != null) {
                this.f842b.add(size, intentB);
                intentB = d.b(this.f843c, intentB.getComponent());
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
        if (this.f842b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f842b;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (androidx.core.content.a.g(this.f843c, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f843c.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f842b.iterator();
    }
}
