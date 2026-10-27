package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class ActivityChooserModel extends DataSetObservable {
    public static final Object k = new Object();
    public static final HashMap l = new HashMap();
    public final Context d;
    public final String e;
    public OnChooseActivityListener j;
    public final Object a = new Object();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final ActivitySorter f = new DefaultSorter();
    public final int g = 50;
    public boolean h = true;
    public boolean i = true;

    public interface ActivityChooserModelClient {
    }

    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public ActivityResolveInfo() {
            throw null;
        }

        @Override // java.lang.Comparable
        public final int compareTo(ActivityResolveInfo activityResolveInfo) {
            activityResolveInfo.getClass();
            return Float.floatToIntBits(0.0f) - Float.floatToIntBits(0.0f);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ActivityResolveInfo.class != obj.getClass()) {
                return false;
            }
            return Float.floatToIntBits(0.0f) == Float.floatToIntBits(0.0f);
        }

        public final int hashCode() {
            return Float.floatToIntBits(0.0f) + 31;
        }

        public final String toString() {
            throw null;
        }
    }

    public interface ActivitySorter {
    }

    public static final class DefaultSorter implements ActivitySorter {
        public DefaultSorter() {
            new HashMap();
        }
    }

    public static final class HistoricalRecord {
        public final ComponentName a;
        public final long b;
        public final float c;

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.a = componentName;
            this.b = j;
            this.c = f;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || HistoricalRecord.class != obj.getClass()) {
                return false;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            ComponentName componentName = historicalRecord.a;
            ComponentName componentName2 = this.a;
            if (componentName2 == null) {
                if (componentName != null) {
                    return false;
                }
            } else if (!componentName2.equals(componentName)) {
                return false;
            }
            return this.b == historicalRecord.b && Float.floatToIntBits(this.c) == Float.floatToIntBits(historicalRecord.c);
        }

        public final int hashCode() {
            ComponentName componentName = this.a;
            int iHashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.b;
            return Float.floatToIntBits(this.c) + ((((iHashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31);
        }

        public final String toString() {
            return "[; activity:" + this.a + "; time:" + this.b + "; weight:" + new BigDecimal(this.c) + "]";
        }
    }

    public interface OnChooseActivityListener {
    }

    public final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void> {
        @Override // android.os.AsyncTask
        public final Void doInBackground(Object[] objArr) {
            throw null;
        }
    }

    public ActivityChooserModel(Context context, String str) {
        this.d = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(".xml")) {
            this.e = str;
        } else {
            this.e = str.concat(".xml");
        }
    }

    public static ActivityChooserModel b(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (k) {
            HashMap map = l;
            activityChooserModel = (ActivityChooserModel) map.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                map.put(str, activityChooserModel);
            }
        }
        return activityChooserModel;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActivityChooserModel.a():void");
    }

    public final ResolveInfo c(int i) {
        synchronized (this.a) {
            a();
            ((ActivityResolveInfo) this.b.get(i)).getClass();
        }
        return null;
    }

    public final int d() {
        int size;
        synchronized (this.a) {
            a();
            size = this.b.size();
        }
        return size;
    }

    public final ResolveInfo e() {
        synchronized (this.a) {
            a();
            if (this.b.isEmpty()) {
                return null;
            }
            ((ActivityResolveInfo) this.b.get(0)).getClass();
            return null;
        }
    }

    public final void f() {
        ArrayList arrayList = this.c;
        int size = arrayList.size() - this.g;
        if (size <= 0) {
            return;
        }
        this.i = true;
        for (int i = 0; i < size; i++) {
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        synchronized (this.a) {
            this.j = onChooseActivityListener;
        }
    }
}
