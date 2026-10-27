package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Iterable<Intent> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ArrayList<Intent> f439c = new ArrayList<>();
    private final Context d;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        @Nullable
        Intent f();
    }

    private h(Context context) {
        this.d = context;
    }

    @NonNull
    public static h d(@NonNull Context context) {
        return new h(context);
    }

    @NonNull
    public h a(@NonNull Intent intent) {
        this.f439c.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public h b(@NonNull Activity activity) {
        Intent intentF = activity instanceof a ? ((a) activity).f() : null;
        if (intentF == null) {
            intentF = f.a(activity);
        }
        if (intentF != null) {
            ComponentName component = intentF.getComponent();
            if (component == null) {
                component = intentF.resolveActivity(this.d.getPackageManager());
            }
            c(component);
            a(intentF);
        }
        return this;
    }

    public h c(ComponentName componentName) {
        int size = this.f439c.size();
        try {
            Intent intentB = f.b(this.d, componentName);
            while (intentB != null) {
                this.f439c.add(size, intentB);
                intentB = f.b(this.d, intentB.getComponent());
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

    public void f(@Nullable Bundle bundle) {
        if (this.f439c.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.f439c;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (androidx.core.content.a.h(this.d, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.d.startActivity(intent);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.f439c.iterator();
    }
}
