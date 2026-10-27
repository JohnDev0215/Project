package com.android.common;

import android.content.SharedPreferences;
import android.text.format.Time;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class OperationScheduler {
    private static final String PREFIX = "OperationScheduler_";
    private final SharedPreferences mStorage;

    public static class Options {
        public long backoffFixedMillis = 0;
        public long backoffIncrementalMillis = 5000;
        public int backoffExponentialMillis = 0;
        public long maxMoratoriumMillis = 86400000;
        public long minTriggerMillis = 0;
        public long periodicIntervalMillis = 0;

        public String toString() {
            if (this.backoffExponentialMillis > 0) {
                return String.format("OperationScheduler.Options[backoff=%.1f+%.1f+%.1f max=%.1f min=%.1f period=%.1f]", Double.valueOf(this.backoffFixedMillis / 1000.0d), Double.valueOf(this.backoffIncrementalMillis / 1000.0d), Double.valueOf(((double) this.backoffExponentialMillis) / 1000.0d), Double.valueOf(this.maxMoratoriumMillis / 1000.0d), Double.valueOf(this.minTriggerMillis / 1000.0d), Double.valueOf(this.periodicIntervalMillis / 1000.0d));
            }
            return String.format("OperationScheduler.Options[backoff=%.1f+%.1f max=%.1f min=%.1f period=%.1f]", Double.valueOf(this.backoffFixedMillis / 1000.0d), Double.valueOf(this.backoffIncrementalMillis / 1000.0d), Double.valueOf(this.maxMoratoriumMillis / 1000.0d), Double.valueOf(this.minTriggerMillis / 1000.0d), Double.valueOf(this.periodicIntervalMillis / 1000.0d));
        }
    }

    public OperationScheduler(SharedPreferences storage) {
        this.mStorage = storage;
    }

    public static Options parseOptions(String spec, Options options) throws IllegalArgumentException {
        for (String param : spec.split(" +")) {
            if (param.length() != 0) {
                if (param.startsWith("backoff=")) {
                    String[] pieces = param.substring(8).split("\\+");
                    if (pieces.length > 3) {
                        throw new IllegalArgumentException("bad value for backoff: [" + spec + "]");
                    }
                    if (pieces.length > 0 && pieces[0].length() > 0) {
                        options.backoffFixedMillis = parseSeconds(pieces[0]);
                    }
                    if (pieces.length > 1 && pieces[1].length() > 0) {
                        options.backoffIncrementalMillis = parseSeconds(pieces[1]);
                    }
                    if (pieces.length > 2 && pieces[2].length() > 0) {
                        options.backoffExponentialMillis = (int) parseSeconds(pieces[2]);
                    }
                } else if (param.startsWith("max=")) {
                    options.maxMoratoriumMillis = parseSeconds(param.substring(4));
                } else if (param.startsWith("min=")) {
                    options.minTriggerMillis = parseSeconds(param.substring(4));
                } else if (param.startsWith("period=")) {
                    options.periodicIntervalMillis = parseSeconds(param.substring(7));
                } else {
                    options.periodicIntervalMillis = parseSeconds(param);
                }
            }
        }
        return options;
    }

    private static long parseSeconds(String param) throws NumberFormatException {
        return (long) (Float.parseFloat(param) * 1000.0f);
    }

    public long getNextTimeMillis(Options options) {
        long time;
        boolean enabledState = this.mStorage.getBoolean("OperationScheduler_enabledState", true);
        if (!enabledState) {
            return Long.MAX_VALUE;
        }
        boolean permanentError = this.mStorage.getBoolean("OperationScheduler_permanentError", false);
        if (permanentError) {
            return Long.MAX_VALUE;
        }
        int errorCount = this.mStorage.getInt("OperationScheduler_errorCount", 0);
        long now = currentTimeMillis();
        long lastSuccessTimeMillis = getTimeBefore("OperationScheduler_lastSuccessTimeMillis", now);
        long lastErrorTimeMillis = getTimeBefore("OperationScheduler_lastErrorTimeMillis", now);
        long triggerTimeMillis = this.mStorage.getLong("OperationScheduler_triggerTimeMillis", Long.MAX_VALUE);
        long moratoriumSetMillis = getTimeBefore("OperationScheduler_moratoriumSetTimeMillis", now);
        long moratoriumTimeMillis = getTimeBefore("OperationScheduler_moratoriumTimeMillis", options.maxMoratoriumMillis + moratoriumSetMillis);
        if (options.periodicIntervalMillis > 0) {
            long time2 = Math.min(triggerTimeMillis, options.periodicIntervalMillis + lastSuccessTimeMillis);
            time = time2;
        } else {
            time = triggerTimeMillis;
        }
        long time3 = Math.max(time, moratoriumTimeMillis);
        long time4 = options.minTriggerMillis;
        long time5 = Math.max(time3, time4 + lastSuccessTimeMillis);
        if (errorCount > 0) {
            int shift = errorCount - 1;
            if (shift > 30) {
                shift = 30;
            }
            long moratoriumTimeMillis2 = options.backoffFixedMillis;
            long moratoriumSetMillis2 = errorCount;
            long backoff = moratoriumTimeMillis2 + (options.backoffIncrementalMillis * moratoriumSetMillis2) + (((long) options.backoffExponentialMillis) << shift);
            return Math.max(time5, lastErrorTimeMillis + Math.min(backoff, options.maxMoratoriumMillis));
        }
        return time5;
    }

    public long getLastSuccessTimeMillis() {
        return this.mStorage.getLong("OperationScheduler_lastSuccessTimeMillis", 0L);
    }

    public long getLastAttemptTimeMillis() {
        return Math.max(this.mStorage.getLong("OperationScheduler_lastSuccessTimeMillis", 0L), this.mStorage.getLong("OperationScheduler_lastErrorTimeMillis", 0L));
    }

    private long getTimeBefore(String name, long max) {
        long time = this.mStorage.getLong(name, 0L);
        if (time > max) {
            SharedPreferencesCompat.apply(this.mStorage.edit().putLong(name, max));
            return max;
        }
        return time;
    }

    public void setTriggerTimeMillis(long millis) {
        SharedPreferencesCompat.apply(this.mStorage.edit().putLong("OperationScheduler_triggerTimeMillis", millis));
    }

    public void setMoratoriumTimeMillis(long millis) {
        SharedPreferencesCompat.apply(this.mStorage.edit().putLong("OperationScheduler_moratoriumTimeMillis", millis).putLong("OperationScheduler_moratoriumSetTimeMillis", currentTimeMillis()));
    }

    public boolean setMoratoriumTimeHttp(String retryAfter) {
        try {
            long ms = Long.parseLong(retryAfter) * 1000;
            setMoratoriumTimeMillis(currentTimeMillis() + ms);
            return true;
        } catch (NumberFormatException e) {
            try {
                setMoratoriumTimeMillis(LegacyHttpDateTime.parse(retryAfter));
                return true;
            } catch (IllegalArgumentException e2) {
                return false;
            }
        }
    }

    public void setEnabledState(boolean enabled) {
        SharedPreferencesCompat.apply(this.mStorage.edit().putBoolean("OperationScheduler_enabledState", enabled));
    }

    public void onSuccess() {
        resetTransientError();
        resetPermanentError();
        SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_errorCount").remove("OperationScheduler_lastErrorTimeMillis").remove("OperationScheduler_permanentError").remove("OperationScheduler_triggerTimeMillis").putLong("OperationScheduler_lastSuccessTimeMillis", currentTimeMillis()));
    }

    public void onTransientError() {
        SharedPreferences.Editor editor = this.mStorage.edit();
        editor.putLong("OperationScheduler_lastErrorTimeMillis", currentTimeMillis());
        editor.putInt("OperationScheduler_errorCount", this.mStorage.getInt("OperationScheduler_errorCount", 0) + 1);
        SharedPreferencesCompat.apply(editor);
    }

    public void resetTransientError() {
        SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_errorCount"));
    }

    public void onPermanentError() {
        SharedPreferencesCompat.apply(this.mStorage.edit().putBoolean("OperationScheduler_permanentError", true));
    }

    public void resetPermanentError() {
        SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_permanentError"));
    }

    public String toString() {
        StringBuilder out = new StringBuilder("[OperationScheduler:");
        TreeMap<String, Object> copy = new TreeMap<>(this.mStorage.getAll());
        for (Map.Entry<String, Object> e : copy.entrySet()) {
            String key = e.getKey();
            if (key.startsWith(PREFIX)) {
                if (key.endsWith("TimeMillis")) {
                    Time time = new Time();
                    time.set(((Long) e.getValue()).longValue());
                    out.append(" ");
                    out.append(key.substring(PREFIX.length(), key.length() - 10));
                    out.append("=");
                    out.append(time.format("%Y-%m-%d/%H:%M:%S"));
                } else {
                    out.append(" ");
                    out.append(key.substring(PREFIX.length()));
                    Object v = e.getValue();
                    if (v == null) {
                        out.append("=(null)");
                    } else {
                        out.append("=");
                        out.append(v.toString());
                    }
                }
            }
        }
        out.append("]");
        return out.toString();
    }

    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
