package android.net.util;

import android.text.TextUtils;
import android.util.Log;
import com.android.networkstack.tethering.util.LocalLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes.dex */
public class SharedLog {
    private final String mComponent;
    private final LocalLog mLocalLog;
    private final String mTag;

    private enum Category {
        NONE,
        ERROR,
        MARK,
        WARN
    }

    public SharedLog(String str) {
        this(500, str);
    }

    public SharedLog(int i, String str) {
        this(new LocalLog(i), str, str);
    }

    private SharedLog(LocalLog localLog, String str, String str2) {
        this.mLocalLog = localLog;
        this.mTag = str;
        this.mComponent = str2;
    }

    public SharedLog forSubComponent(String str) {
        if (!isRootLogInstance()) {
            str = this.mComponent + "." + str;
        }
        return new SharedLog(this.mLocalLog, this.mTag, str);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLocalLog.readOnlyLocalLog().dump(fileDescriptor, printWriter, strArr);
    }

    public void e(Exception exc) {
        Log.e(this.mTag, record(Category.ERROR, exc.toString()));
    }

    public void e(String str) {
        Log.e(this.mTag, record(Category.ERROR, str));
    }

    public void e(String str, Throwable th) {
        if (th == null) {
            e(str);
            return;
        }
        Log.e(this.mTag, record(Category.ERROR, str + ": " + th.getMessage()), th);
    }

    public void i(String str) {
        Log.i(this.mTag, record(Category.NONE, str));
    }

    public void w(String str) {
        Log.w(this.mTag, record(Category.WARN, str));
    }

    public void log(String str) {
        record(Category.NONE, str);
    }

    public void mark(String str) {
        record(Category.MARK, str);
    }

    private String record(Category category, String str) {
        String strLogLine = logLine(category, str);
        this.mLocalLog.log(strLogLine);
        return strLogLine;
    }

    private String logLine(Category category, String str) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!isRootLogInstance()) {
            stringJoiner.add("[" + this.mComponent + "]");
        }
        if (category != Category.NONE) {
            stringJoiner.add(category.toString());
        }
        return stringJoiner.add(str).toString();
    }

    private boolean isRootLogInstance() {
        return TextUtils.isEmpty(this.mComponent) || this.mComponent.equals(this.mTag);
    }
}
