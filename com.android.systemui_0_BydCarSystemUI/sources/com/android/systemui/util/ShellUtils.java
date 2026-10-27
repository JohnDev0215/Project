package com.android.systemui.util;

import com.bumptech.glide.load.Key;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShellUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isVrRunning() {
        String strExeShell = exeShell("dumpsys window|grep com.byd.vrassistant");
        return (strExeShell == null || !strExeShell.contains("SYSTEM_ALERT_WINDOW") || strExeShell.contains("com.byd.vrassistant.xf")) ? false : true;
    }

    public static String exeShell(String cmd) {
        try {
            Process processExec = Runtime.getRuntime().exec(BydAdbHelper.COMMAND_SH);
            OutputStream outputStream = processExec.getOutputStream();
            outputStream.write(cmd.getBytes(Key.STRING_CHARSET_NAME));
            outputStream.flush();
            outputStream.close();
            processExec.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static CommandResult execCmd(final String command, final boolean isRooted) {
        return execCmd(new String[]{command}, isRooted, true);
    }

    public static CommandResult execCmd(final String command, final List<String> envp, final boolean isRooted) {
        return execCmd(new String[]{command}, envp == null ? null : (String[]) envp.toArray(new String[0]), isRooted, true);
    }

    public static CommandResult execCmd(final List<String> commands, final boolean isRooted) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, true);
    }

    public static CommandResult execCmd(final List<String> commands, final List<String> envp, final boolean isRooted) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), envp != null ? (String[]) envp.toArray(new String[0]) : null, isRooted, true);
    }

    public static CommandResult execCmd(final String[] commands, final boolean isRooted) {
        return execCmd(commands, isRooted, true);
    }

    public static CommandResult execCmd(final String command, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRooted, isNeedResultMsg);
    }

    public static CommandResult execCmd(final String command, final List<String> envp, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, envp == null ? null : (String[]) envp.toArray(new String[0]), isRooted, isNeedResultMsg);
    }

    public static CommandResult execCmd(final String command, final String[] envp, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, envp, isRooted, isNeedResultMsg);
    }

    public static CommandResult execCmd(final List<String> commands, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : (String[]) commands.toArray(new String[0]), isRooted, isNeedResultMsg);
    }

    public static CommandResult execCmd(final String[] commands, final boolean isRooted, final boolean isNeedResultMsg) {
        return execCmd(commands, (String[]) null, isRooted, isNeedResultMsg);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0135 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x016f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x012b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0165 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0121 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x015b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.android.systemui.util.ShellUtils.CommandResult execCmd(java.lang.String[] r8, java.lang.String[] r9, boolean r10, boolean r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.ShellUtils.execCmd(java.lang.String[], java.lang.String[], boolean, boolean):com.android.systemui.util.ShellUtils$CommandResult");
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(final int result, final String successMsg, final String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }

        public String toString() {
            return "result: " + this.result + "\nsuccessMsg: " + this.successMsg + "\nerrorMsg: " + this.errorMsg;
        }
    }
}
