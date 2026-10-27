package p;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.util.Base64;
import android.util.Log;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$Flag;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.Adler32;

/* JADX INFO: loaded from: classes.dex */
public final class s61 implements hy2 {
    public final Context a;
    public final jk0 b;
    public final ni c;

    public s61(Context context, jk0 jk0Var, ni niVar) {
        this.a = context;
        this.b = jk0Var;
        this.c = niVar;
    }

    public final void a(wi wiVar, int i, boolean z) {
        Context context = this.a;
        ComponentName componentName = new ComponentName(context, (Class<?>) JobInfoSchedulerService.class);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        Adler32 adler32 = new Adler32();
        adler32.update(context.getPackageName().getBytes(Charset.forName("UTF-8")));
        adler32.update(wiVar.a.getBytes(Charset.forName("UTF-8")));
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        Priority priority = wiVar.c;
        adler32.update(byteBufferAllocate.putInt(gx1.a(priority)).array());
        byte[] bArr = wiVar.b;
        if (bArr != null) {
            adler32.update(bArr);
        }
        int value = (int) adler32.getValue();
        if (!z) {
            Iterator<JobInfo> it = jobScheduler.getAllPendingJobs().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                JobInfo next = it.next();
                int i2 = next.getExtras().getInt("attemptNumber");
                if (next.getId() == value) {
                    if (i2 >= i) {
                        ln1.h("JobInfoScheduler", "Upload for context %s is already scheduled. Returning...", wiVar);
                        return;
                    }
                }
            }
        }
        SQLiteDatabase sQLiteDatabaseB = ((j62) this.b).b();
        String strValueOf = String.valueOf(gx1.a(priority));
        String str = wiVar.a;
        long jLongValue = ((Long) j62.o0(sQLiteDatabaseB.rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{str, strValueOf}), new iy2(5))).longValue();
        JobInfo.Builder builder = new JobInfo.Builder(value, componentName);
        ni niVar = this.c;
        builder.setMinimumLatency(niVar.a(priority, jLongValue, i));
        Set set = ((oi) niVar.b.get(priority)).c;
        if (set.contains(SchedulerConfig$Flag.c)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (set.contains(SchedulerConfig$Flag.k)) {
            builder.setRequiresCharging(true);
        }
        if (set.contains(SchedulerConfig$Flag.e)) {
            builder.setRequiresDeviceIdle(true);
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putInt("attemptNumber", i);
        persistableBundle.putString("backendName", str);
        persistableBundle.putInt("priority", gx1.a(priority));
        if (bArr != null) {
            persistableBundle.putString("extras", Base64.encodeToString(bArr, 0));
        }
        builder.setExtras(persistableBundle);
        Object[] objArr = {wiVar, Integer.valueOf(value), Long.valueOf(niVar.a(priority, jLongValue, i)), Long.valueOf(jLongValue), Integer.valueOf(i)};
        if (Log.isLoggable("TRuntime.".concat("JobInfoScheduler"), 3)) {
            String.format("Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", objArr);
        }
        jobScheduler.schedule(builder.build());
    }
}
