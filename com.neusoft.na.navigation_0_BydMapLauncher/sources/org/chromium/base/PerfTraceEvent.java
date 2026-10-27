package org.chromium.base;

import android.os.Debug;
import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PerfTraceEvent {
    private static final int MAX_NAME_LENGTH = 40;
    private static final String MEMORY_TRACE_NAME_SUFFIX = "_BZR_PSS";
    private static long sBeginNanoTime = 0;
    private static boolean sEnabled = false;
    private static List<String> sFilter = null;
    private static File sOutputFile = null;
    private static JSONArray sPerfTraceStrings = null;
    private static boolean sTrackMemory = false;
    private static boolean sTrackTiming = true;

    private enum EventType {
        START("S"),
        FINISH(TemperatureUnitEnum.F),
        INSTANT("I");

        private final String mTypeStr;

        EventType(String str) {
            this.mTypeStr = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mTypeStr;
        }
    }

    public static synchronized void setFilter(List<String> list) {
        sFilter = new LinkedList(list);
    }

    public static synchronized void setEnabled(boolean z) {
        if (sEnabled == z) {
            return;
        }
        if (z) {
            sBeginNanoTime = System.nanoTime();
            sPerfTraceStrings = new JSONArray();
        } else {
            dumpPerf();
            sPerfTraceStrings = null;
            sFilter = null;
        }
        sEnabled = z;
    }

    public static synchronized void setMemoryTrackingEnabled(boolean z) {
        sTrackMemory = z;
    }

    public static synchronized void setTimingTrackingEnabled(boolean z) {
        sTrackTiming = z;
    }

    public static synchronized boolean enabled() {
        return sEnabled;
    }

    public static synchronized void instant(String str) {
        long jHashCode = str.hashCode();
        TraceEvent.instant(str);
        if (sEnabled && matchesFilter(str)) {
            savePerfString(str, jHashCode, EventType.INSTANT, false);
        }
    }

    public static synchronized void begin(String str) {
        long jHashCode = str.hashCode();
        TraceEvent.startAsync(str, jHashCode);
        if (sEnabled && matchesFilter(str)) {
            if (sTrackMemory) {
                savePerfString(makeMemoryTraceNameFromTimingName(str), jHashCode, EventType.START, true);
            }
            if (sTrackTiming) {
                savePerfString(str, jHashCode, EventType.START, false);
            }
        }
    }

    public static synchronized void end(String str) {
        long jHashCode = str.hashCode();
        TraceEvent.finishAsync(str, jHashCode);
        if (sEnabled && matchesFilter(str)) {
            if (sTrackTiming) {
                savePerfString(str, jHashCode, EventType.FINISH, false);
            }
            if (sTrackMemory) {
                savePerfString(makeMemoryTraceNameFromTimingName(str), jHashCode, EventType.FINISH, true);
            }
        }
    }

    public static synchronized void begin(String str, Debug.MemoryInfo memoryInfo) {
        long jHashCode = str.hashCode();
        TraceEvent.startAsync(str, jHashCode);
        if (sEnabled && matchesFilter(str)) {
            savePerfString(makeMemoryTraceNameFromTimingName(str), jHashCode, EventType.START, (System.nanoTime() - sBeginNanoTime) / 1000, memoryInfo);
            if (sTrackTiming) {
                savePerfString(str, jHashCode, EventType.START, false);
            }
        }
    }

    public static synchronized void end(String str, Debug.MemoryInfo memoryInfo) {
        long jHashCode = str.hashCode();
        TraceEvent.finishAsync(str, jHashCode);
        if (sEnabled && matchesFilter(str)) {
            if (sTrackTiming) {
                savePerfString(str, jHashCode, EventType.FINISH, false);
            }
            savePerfString(makeMemoryTraceNameFromTimingName(str), jHashCode, EventType.FINISH, (System.nanoTime() - sBeginNanoTime) / 1000, memoryInfo);
        }
    }

    private static boolean matchesFilter(String str) {
        List<String> list = sFilter;
        if (list != null) {
            return list.contains(str);
        }
        return false;
    }

    private static void savePerfString(String str, long j, EventType eventType, boolean z) {
        Debug.MemoryInfo memoryInfo;
        long jNanoTime = (System.nanoTime() - sBeginNanoTime) / 1000;
        if (z) {
            memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
        } else {
            memoryInfo = null;
        }
        savePerfString(str, j, eventType, jNanoTime, memoryInfo);
    }

    private static void savePerfString(String str, long j, EventType eventType, long j2, Debug.MemoryInfo memoryInfo) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cat", "Java");
            jSONObject.put("ts", j2);
            jSONObject.put("ph", eventType);
            jSONObject.put("name", str);
            jSONObject.put("id", j);
            if (memoryInfo != null) {
                jSONObject.put("mem", memoryInfo.nativePss + memoryInfo.dalvikPss + memoryInfo.otherPss);
            }
            sPerfTraceStrings.put(jSONObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeMemoryTraceNameFromTimingName(String str) {
        return makeSafeTraceName(str, MEMORY_TRACE_NAME_SUFFIX);
    }

    public static String makeSafeTraceName(String str, String str2) {
        int length = str2.length();
        if (str.length() + length > 40) {
            str = str.substring(0, 40 - length);
        }
        return str + str2;
    }

    public static synchronized void setOutputFile(File file) {
        sOutputFile = file;
    }

    private static void dumpPerf() {
        String string = sPerfTraceStrings.toString();
        if (sOutputFile == null) {
            System.out.println(string);
            return;
        }
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(sOutputFile, true));
            try {
                printStream.print(string);
                try {
                    printStream.close();
                } catch (Exception unused) {
                    android.util.Log.e("PerfTraceEvent", "Unable to close perf trace output file.");
                }
            } finally {
            }
        } catch (FileNotFoundException unused2) {
            android.util.Log.e("PerfTraceEvent", "Unable to dump perf trace data to output file.");
        }
    }
}
