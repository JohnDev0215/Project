package org.chromium.base;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CommandLine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String SWITCH_PREFIX = "--";
    private static final String SWITCH_TERMINATOR = "--";
    private static final String SWITCH_VALUE_SEPARATOR = "=";
    private static final String TAG = "CommandLine";
    private static final List<ResetListener> sResetListeners = new ArrayList();
    private static final AtomicReference<CommandLine> sCommandLine = new AtomicReference<>();

    public interface ResetListener {
        void onCommandLineReset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeAppendSwitch(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeAppendSwitchWithValue(String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeAppendSwitchesAndArguments(String[] strArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native String nativeGetSwitchValue(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean nativeHasSwitch(String str);

    private static native void nativeReset();

    public abstract void appendSwitch(String str);

    public abstract void appendSwitchWithValue(String str, String str2);

    public abstract void appendSwitchesAndArguments(String[] strArr);

    public abstract String getSwitchValue(String str);

    public abstract boolean hasSwitch(String str);

    public boolean isNativeImplementation() {
        return false;
    }

    public String getSwitchValue(String str, String str2) {
        String switchValue = getSwitchValue(str);
        return TextUtils.isEmpty(switchValue) ? str2 : switchValue;
    }

    public static boolean isInitialized() {
        return sCommandLine.get() != null;
    }

    public static CommandLine getInstance() {
        return sCommandLine.get();
    }

    public static void init(String[] strArr) {
        setInstance(new JavaCommandLine(strArr));
    }

    public static void initFromFile(String str) throws Throwable {
        char[] utf8FileFully = readUtf8FileFully(str, 8192);
        init(utf8FileFully == null ? null : tokenizeQuotedAruments(utf8FileFully));
    }

    public static void reset() {
        setInstance(null);
        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.base.CommandLine.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = CommandLine.sResetListeners.iterator();
                while (it.hasNext()) {
                    ((ResetListener) it.next()).onCommandLineReset();
                }
            }
        });
    }

    public static void addResetListener(ResetListener resetListener) {
        sResetListeners.add(resetListener);
    }

    public static void removeResetListener(ResetListener resetListener) {
        sResetListeners.remove(resetListener);
    }

    public static String[] tokenizeQuotedAruments(char[] cArr) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = null;
        char c = 0;
        for (char c2 : cArr) {
            if ((c == 0 && (c2 == '\'' || c2 == '\"')) || c2 == c) {
                if (sb == null || sb.length() <= 0 || sb.charAt(sb.length() - 1) != '\\') {
                    c = c == 0 ? c2 : (char) 0;
                } else {
                    sb.setCharAt(sb.length() - 1, c2);
                }
            } else if (c != 0 || !Character.isWhitespace(c2)) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(c2);
            } else if (sb != null) {
                arrayList.add(sb.toString());
                sb = null;
            }
        }
        if (sb != null) {
            if (c != 0) {
                android.util.Log.w(TAG, "Unterminated quoted string: " + ((Object) sb));
            }
            arrayList.add(sb.toString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void enableNativeProxy() {
        sCommandLine.set(new NativeCommandLine());
    }

    public static String[] getJavaSwitchesOrNull() {
        CommandLine commandLine = sCommandLine.get();
        if (commandLine != null) {
            return ((JavaCommandLine) commandLine).getCommandLineArguments();
        }
        return null;
    }

    private static void setInstance(CommandLine commandLine) {
        CommandLine andSet = sCommandLine.getAndSet(commandLine);
        if (andSet == null || !andSet.isNativeImplementation()) {
            return;
        }
        nativeReset();
    }

    private static char[] readUtf8FileFully(String str, int i) throws Throwable {
        InputStreamReader inputStreamReader;
        char[] cArrCopyOfRange;
        File file = new File(str);
        long length = file.length();
        InputStreamReader inputStreamReader2 = null;
        if (length == 0) {
            return null;
        }
        if (length > i) {
            android.util.Log.w(TAG, "File " + str + " length " + length + " exceeds limit " + i);
            return null;
        }
        int i2 = (int) length;
        try {
            cArrCopyOfRange = new char[i2];
            inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        } catch (FileNotFoundException unused) {
            inputStreamReader = null;
        } catch (IOException unused2) {
            inputStreamReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            int i3 = inputStreamReader.read(cArrCopyOfRange);
            if (i3 < i2) {
                cArrCopyOfRange = Arrays.copyOfRange(cArrCopyOfRange, 0, i3);
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                android.util.Log.e(TAG, "Unable to close file reader.", e);
            }
            return cArrCopyOfRange;
        } catch (FileNotFoundException unused3) {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e2) {
                    android.util.Log.e(TAG, "Unable to close file reader.", e2);
                }
            }
            return null;
        } catch (IOException unused4) {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e3) {
                    android.util.Log.e(TAG, "Unable to close file reader.", e3);
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamReader2 = inputStreamReader;
            if (inputStreamReader2 != null) {
                try {
                    inputStreamReader2.close();
                } catch (IOException e4) {
                    android.util.Log.e(TAG, "Unable to close file reader.", e4);
                }
            }
            throw th;
        }
    }

    private CommandLine() {
    }

    private static class JavaCommandLine extends CommandLine {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private ArrayList<String> mArgs;
        private int mArgsBegin;
        private HashMap<String, String> mSwitches;

        JavaCommandLine(String[] strArr) {
            String str;
            super();
            this.mSwitches = new HashMap<>();
            ArrayList<String> arrayList = new ArrayList<>();
            this.mArgs = arrayList;
            this.mArgsBegin = 1;
            if (strArr == null || strArr.length == 0 || (str = strArr[0]) == null) {
                arrayList.add("");
            } else {
                arrayList.add(str);
                appendSwitchesInternal(strArr, 1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String[] getCommandLineArguments() {
            ArrayList<String> arrayList = this.mArgs;
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        @Override // org.chromium.base.CommandLine
        public boolean hasSwitch(String str) {
            return this.mSwitches.containsKey(str);
        }

        @Override // org.chromium.base.CommandLine
        public String getSwitchValue(String str) {
            String str2 = this.mSwitches.get(str);
            if (str2 == null || str2.isEmpty()) {
                return null;
            }
            return str2;
        }

        @Override // org.chromium.base.CommandLine
        public void appendSwitch(String str) {
            appendSwitchWithValue(str, null);
        }

        @Override // org.chromium.base.CommandLine
        public void appendSwitchWithValue(String str, String str2) {
            this.mSwitches.put(str, str2 == null ? "" : str2);
            String str3 = "--" + str;
            if (str2 != null && !str2.isEmpty()) {
                str3 = str3 + CommandLine.SWITCH_VALUE_SEPARATOR + str2;
            }
            ArrayList<String> arrayList = this.mArgs;
            int i = this.mArgsBegin;
            this.mArgsBegin = i + 1;
            arrayList.add(i, str3);
        }

        @Override // org.chromium.base.CommandLine
        public void appendSwitchesAndArguments(String[] strArr) {
            appendSwitchesInternal(strArr, 0);
        }

        private void appendSwitchesInternal(String[] strArr, int i) {
            boolean z = true;
            for (String str : strArr) {
                if (i > 0) {
                    i--;
                } else {
                    if (str.equals("--")) {
                        z = false;
                    }
                    if (z && str.startsWith("--")) {
                        String[] strArrSplit = str.split(CommandLine.SWITCH_VALUE_SEPARATOR, 2);
                        appendSwitchWithValue(strArrSplit[0].substring(2), strArrSplit.length > 1 ? strArrSplit[1] : null);
                    } else {
                        this.mArgs.add(str);
                    }
                }
            }
        }
    }

    private static class NativeCommandLine extends CommandLine {
        @Override // org.chromium.base.CommandLine
        public boolean isNativeImplementation() {
            return true;
        }

        private NativeCommandLine() {
            super();
        }

        @Override // org.chromium.base.CommandLine
        public boolean hasSwitch(String str) {
            return CommandLine.nativeHasSwitch(str);
        }

        @Override // org.chromium.base.CommandLine
        public String getSwitchValue(String str) {
            return CommandLine.nativeGetSwitchValue(str);
        }

        @Override // org.chromium.base.CommandLine
        public void appendSwitch(String str) {
            CommandLine.nativeAppendSwitch(str);
        }

        @Override // org.chromium.base.CommandLine
        public void appendSwitchWithValue(String str, String str2) {
            CommandLine.nativeAppendSwitchWithValue(str, str2);
        }

        @Override // org.chromium.base.CommandLine
        public void appendSwitchesAndArguments(String[] strArr) {
            CommandLine.nativeAppendSwitchesAndArguments(strArr);
        }
    }
}
