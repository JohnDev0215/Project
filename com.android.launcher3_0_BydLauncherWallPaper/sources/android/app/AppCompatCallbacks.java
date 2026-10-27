package android.app;

import android.compat.Compatibility;
import android.os.Process;
import com.android.internal.compat.ChangeReporter;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class AppCompatCallbacks extends Compatibility.Callbacks {
    private final ChangeReporter mChangeReporter;
    private final long[] mDisabledChanges;

    public static void install(long[] jArr) {
        Compatibility.setCallbacks(new AppCompatCallbacks(jArr));
    }

    private AppCompatCallbacks(long[] jArr) {
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        this.mDisabledChanges = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
        this.mChangeReporter = new ChangeReporter(1);
    }

    protected void reportChange(long j) {
        reportChange(j, 3);
    }

    protected boolean isChangeEnabled(long j) {
        if (Arrays.binarySearch(this.mDisabledChanges, j) < 0) {
            reportChange(j, 1);
            return true;
        }
        reportChange(j, 2);
        return false;
    }

    private void reportChange(long j, int i) {
        this.mChangeReporter.reportChange(Process.myUid(), j, i);
    }
}
