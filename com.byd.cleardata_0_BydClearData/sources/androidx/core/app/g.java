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

/* JADX INFO: loaded from: classes.dex */
public final class g implements Iterable<Intent> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<Intent> f1190b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1191c;

    public interface a {
        Intent j();
    }

    private g(Context context) {
        this.f1191c = context;
    }

    public static g d(Context context) {
        return new g(context);
    }

    public g a(Intent intent) {
        this.f1190b.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g b(Activity activity) {
        Intent intentJ = activity instanceof a ? ((a) activity).j() : null;
        if (intentJ == null) {
            intentJ = e.a(activity);
        }
        if (intentJ != null) {
            ComponentName component = intentJ.getComponent();
            if (component == null) {
                component = intentJ.resolveActivity(this.f1191c.getPackageManager());
            }
            c(component);
            a(intentJ);
        }
        return this;
    }

    public g c(ComponentName componentName) {
        int size = this.f1190b.size();
        try {
            Context context = this.f1191c;
            while (true) {
                Intent intentB = e.b(context, componentName);
                if (intentB == null) {
                    return this;
                }
                this.f1190b.add(size, intentB);
                context = this.f1191c;
                componentName = intentB.getComponent();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    public void e() {
        f(null);
    }

    public void f(Bundle bundle) {
        if (this.f1190b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f1190b;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (androidx.core.content.a.g(this.f1191c, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f1191c.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f1190b.iterator();
    }
}
