package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import android.content.SharedPreferences;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.DLog;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ServerConfigEditor implements ConfigEditor {
    volatile boolean mFirstReadComplete = false;
    private final Supplier<SharedPreferences> mSharedPrefSupplier;

    ServerConfigEditor(Supplier<SharedPreferences> supplier) {
        this.mSharedPrefSupplier = Suppliers.memoize(supplier);
    }

    @Override // amazon.android.config.ConfigEditor
    public SharedPreferences getSharedPreferences() {
        return this.mSharedPrefSupplier.get();
    }

    @Override // amazon.android.config.ConfigEditor
    public int getIntConfig(String str, int i) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:Server");
        try {
            try {
                i = Integer.parseInt(getStringConfig(str, Integer.toString(i)));
            } catch (NumberFormatException e) {
                handleParseException(e, str, Integer.valueOf(i));
            }
            return i;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public float getFloatConfig(String str, float f) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:Server");
        try {
            try {
                f = Float.parseFloat(getStringConfig(str, Float.toString(f)));
            } catch (NumberFormatException e) {
                handleParseException(e, str, Float.valueOf(f));
            }
            return f;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public long getLongConfig(String str, long j) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:Server");
        try {
            try {
                j = Long.parseLong(getStringConfig(str, Long.toString(j)));
            } catch (NumberFormatException e) {
                handleParseException(e, str, Long.valueOf(j));
            }
            return j;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public boolean getBooleanConfig(String str, boolean z) {
        return Boolean.parseBoolean(getStringConfig(str, Boolean.toString(z)));
    }

    @Override // amazon.android.config.ConfigEditor
    public String getStringConfig(String str, String str2) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:Server");
        try {
            try {
                str2 = this.mSharedPrefSupplier.get().getString(str, str2);
            } catch (Exception e) {
                handleReadException(e, str, str2);
            }
            return str2;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public void setIntConfig(String str, String str2) {
        throw new UnsupportedOperationException("Cannot modify a server config value");
    }

    @Override // amazon.android.config.ConfigEditor
    public void setFloatConfig(String str, String str2) {
        throw new UnsupportedOperationException("Cannot modify a server config value");
    }

    @Override // amazon.android.config.ConfigEditor
    public void setLongConfig(String str, String str2) {
        throw new UnsupportedOperationException("Cannot modify a server config value");
    }

    @Override // amazon.android.config.ConfigEditor
    public void setBooleanConfig(String str, String str2) {
        throw new UnsupportedOperationException("Cannot modify a server config value");
    }

    @Override // amazon.android.config.ConfigEditor
    public void setStringConfig(String str, String str2) {
        throw new UnsupportedOperationException("Cannot modify a server config value");
    }

    @Override // amazon.android.config.ConfigEditor
    public void remove(String str) {
        throw new UnsupportedOperationException("Cannot modify a server config value");
    }

    @Override // amazon.android.config.ConfigEditor
    public boolean contains(String str) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:Server");
        try {
            return this.mSharedPrefSupplier.get().contains(str);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public Map<String, Object> getAll() {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:Server");
        try {
            return getSharedPreferences().getAll();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    protected void handleReadException(Throwable th, String str, Object obj) {
        DLog.errorf("%s Exception %s caught in %s while reading secure storage for key=[%s]. Returning defaultValue=[%s].", getClass().getSimpleName(), th.getLocalizedMessage(), this, str, obj);
    }

    private void handleParseException(NumberFormatException numberFormatException, String str, Object obj) {
        DLog.errorf("%s Exception %s caught in %s while parsing key=[%s] from a string. Returning defaultValue=[%s].", getClass().getSimpleName(), numberFormatException.getLocalizedMessage(), this, str, obj);
    }

    public String toString() {
        return String.format(Locale.US, "Prefs[%s]", this.mSharedPrefSupplier);
    }
}
