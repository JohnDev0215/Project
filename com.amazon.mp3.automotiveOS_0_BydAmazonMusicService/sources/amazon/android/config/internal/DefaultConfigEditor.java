package amazon.android.config.internal;

import amazon.android.config.ConfigEditor;
import android.content.SharedPreferences;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultConfigEditor implements ConfigEditor {
    volatile boolean mFirstReadComplete = false;
    private final String mName;
    private final Supplier<SharedPreferences> mSharedPreferences;

    public DefaultConfigEditor(@Nonnull Supplier<SharedPreferences> supplier, @Nonnull String str) {
        this.mSharedPreferences = Suppliers.memoize(supplier);
        this.mName = (String) Preconditions.checkNotNull(str, "name");
    }

    @Override // amazon.android.config.ConfigEditor
    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences.get();
    }

    @Override // amazon.android.config.ConfigEditor
    public int getIntConfig(String str, int i) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            try {
                i = this.mSharedPreferences.get().getInt(str, i);
            } catch (Exception e) {
                handleReadException(e, str, Integer.valueOf(i));
            }
            return i;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public float getFloatConfig(String str, float f) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            try {
                f = this.mSharedPreferences.get().getFloat(str, f);
            } catch (Exception e) {
                handleReadException(e, str, Float.valueOf(f));
            }
            return f;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public long getLongConfig(String str, long j) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            try {
                j = this.mSharedPreferences.get().getLong(str, j);
            } catch (Exception e) {
                handleReadException(e, str, Long.valueOf(j));
            }
            return j;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public boolean getBooleanConfig(String str, boolean z) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            try {
                z = this.mSharedPreferences.get().getBoolean(str, z);
            } catch (Exception e) {
                handleReadException(e, str, Boolean.valueOf(z));
            }
            return z;
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public String getStringConfig(String str, String str2) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            try {
                str2 = this.mSharedPreferences.get().getString(str, str2);
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
        try {
            setIntConfig(str, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            handleWriteException(e, str);
        }
    }

    public void setIntConfig(String str, int i) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.get().edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    @Override // amazon.android.config.ConfigEditor
    public void setFloatConfig(String str, String str2) {
        try {
            setFloatConfig(str, Float.parseFloat(str2));
        } catch (NumberFormatException e) {
            handleWriteException(e, str);
        }
    }

    public void setFloatConfig(String str, float f) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.get().edit();
        editorEdit.putFloat(str, f);
        editorEdit.apply();
    }

    @Override // amazon.android.config.ConfigEditor
    public void setLongConfig(String str, String str2) {
        try {
            setLongConfig(str, Long.parseLong(str2));
        } catch (NumberFormatException e) {
            handleWriteException(e, str);
        }
    }

    public void setLongConfig(String str, long j) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.get().edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    @Override // amazon.android.config.ConfigEditor
    public void setBooleanConfig(String str, String str2) {
        try {
            setBooleanConfig(str, Boolean.parseBoolean(str2));
        } catch (NumberFormatException e) {
            handleWriteException(e, str);
        }
    }

    public void setBooleanConfig(String str, boolean z) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.get().edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    @Override // amazon.android.config.ConfigEditor
    public void setStringConfig(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.get().edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    @Override // amazon.android.config.ConfigEditor
    public void remove(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.get().edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }

    @Override // amazon.android.config.ConfigEditor
    public boolean contains(String str) {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            return this.mSharedPreferences.get().contains(str);
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    @Override // amazon.android.config.ConfigEditor
    public Map<String, Object> getAll() {
        TraceKey traceKeyBeginTrace = this.mFirstReadComplete ? null : Profiler.beginTrace(Profiler.TraceLevel.INFO, "SharedPreferences:FirstRead:%s", this.mName);
        try {
            return getSharedPreferences().getAll();
        } finally {
            Profiler.endTrace(traceKeyBeginTrace);
            this.mFirstReadComplete = true;
        }
    }

    private void handleReadException(Throwable th, String str, Object obj) {
        DLog.exceptionf(th, "Exception %s caught in %s while reading secure storage for key=[%s]. Returning defaultValue=[%s].", th.getLocalizedMessage(), this, str, obj);
    }

    private void handleWriteException(Throwable th, String str) {
        DLog.exceptionf(th, "Exception %s caught in %s while writing secure storage for key=[%s].", th.getLocalizedMessage(), this, str);
    }

    public String toString() {
        return String.format(Locale.US, "Prefs[%s]", this.mSharedPreferences);
    }
}
