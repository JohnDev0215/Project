package com.google.android.exoplayer2.util;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
public class Logger {
    private static final int[] enabledModules;
    private static boolean mForceInfoLogs;
    private int mModule;
    private String mTag;

    public enum Module {
        Unknown,
        AudioVideoCommon,
        Audio,
        Video,
        AudioVideo,
        Text,
        Source,
        Manifest,
        Player,
        All
    }

    static {
        int[] iArr = new int[Module.All.ordinal()];
        enabledModules = iArr;
        Arrays.fill(iArr, 4);
    }

    public Logger(Module module, String str) {
        this.mTag = "UNKNOWN";
        this.mModule = Module.Unknown.ordinal();
        if (str == null) {
            throw new IllegalArgumentException("Null Tag");
        }
        this.mTag = str;
        this.mModule = module.ordinal();
    }

    public static void setLogLevel(Module module, int i) {
        if (module.compareTo(Module.All) == 0) {
            Arrays.fill(enabledModules, i);
        } else {
            enabledModules[module.ordinal()] = i;
        }
        Module module2 = Module.Audio;
        if (module.compareTo(module2) >= 0 && module.compareTo(Module.AudioVideo) <= 0) {
            enabledModules[Module.AudioVideoCommon.ordinal()] = i;
        }
        if (module.compareTo(Module.AudioVideo) == 0) {
            int[] iArr = enabledModules;
            iArr[module2.ordinal()] = i;
            iArr[Module.Video.ordinal()] = i;
        }
    }

    public void setTAG(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null Tag");
        }
        this.mTag = str;
    }

    public void setModule(Module module) {
        this.mModule = module.ordinal();
    }

    public boolean allowVerbose() {
        return enabledModules[this.mModule] == 2;
    }

    public boolean allowDebug() {
        return enabledModules[this.mModule] <= 3;
    }

    public void v(String str) {
        if (enabledModules[this.mModule] == 2) {
            if (mForceInfoLogs) {
                i(str);
            } else {
                Log.v(this.mTag, str);
            }
        }
    }

    public void d(String str) {
        if (enabledModules[this.mModule] <= 3) {
            if (mForceInfoLogs) {
                i(str);
            } else {
                Log.d(this.mTag, str);
            }
        }
    }

    public void i(String str) {
        Log.i(this.mTag, str);
    }

    public void w(String str) {
        Log.w(this.mTag, str);
    }
}
