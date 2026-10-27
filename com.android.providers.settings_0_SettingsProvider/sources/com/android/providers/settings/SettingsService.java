package com.android.providers.settings;

import android.content.IContentProvider;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ShellCallback;
import android.os.ShellCommand;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SettingsService extends Binder {
    final SettingsProvider mProvider;

    public SettingsService(SettingsProvider settingsProvider) {
        this.mProvider = settingsProvider;
    }

    public void onShellCommand(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr, ShellCallback shellCallback, ResultReceiver resultReceiver) {
        new MyShellCommand(this.mProvider, false).exec(this, fileDescriptor, fileDescriptor2, fileDescriptor3, strArr, shellCallback, resultReceiver);
    }

    @Override // android.os.Binder
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str;
        if (this.mProvider.getContext().checkCallingPermission("android.permission.DUMP") != 0) {
            printWriter.println("Permission Denial: can't dump SettingsProvider from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + " without permission android.permission.DUMP");
            return;
        }
        int i = 0;
        boolean z = false;
        while (i < strArr.length && (str = strArr[i]) != null && str.length() > 0 && str.charAt(0) == '-') {
            i++;
            if ("-h".equals(str)) {
                MyShellCommand.dumpHelp(printWriter, true);
                return;
            } else if ("--proto".equals(str)) {
                z = true;
            } else {
                printWriter.println("Unknown argument: " + str + "; use -h for help");
            }
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (z) {
                this.mProvider.dumpProto(fileDescriptor);
            } else {
                this.mProvider.dumpInternal(fileDescriptor, printWriter, strArr);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    static final class MyShellCommand extends ShellCommand {
        final boolean mDumping;
        boolean mMakeDefault;
        final SettingsProvider mProvider;
        int mUser = -10000;
        CommandVerb mVerb = CommandVerb.UNSPECIFIED;
        String mTable = null;
        String mKey = null;
        String mValue = null;
        String mPackageName = null;
        String mTag = null;
        int mResetMode = -1;

        enum CommandVerb {
            UNSPECIFIED,
            GET,
            PUT,
            DELETE,
            LIST,
            RESET
        }

        MyShellCommand(SettingsProvider settingsProvider, boolean z) {
            this.mProvider = settingsProvider;
            this.mDumping = z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:109:0x019a, code lost:
        
            r14.mKey = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:110:0x01a0, code lost:
        
            if (peekNextArg() != null) goto L145;
         */
        /* JADX WARN: Code restructure failed: missing block: B:145:0x0286, code lost:
        
            r2.println("Too many arguments");
         */
        /* JADX WARN: Code restructure failed: missing block: B:146:0x0289, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00ea, code lost:
        
            r3 = true;
         */
        /* JADX WARN: Removed duplicated region for block: B:163:0x01a4 A[EDGE_INSN: B:163:0x01a4->B:112:0x01a4 BREAK  A[LOOP:0: B:9:0x001b->B:164:?], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:164:? A[LOOP:0: B:9:0x001b->B:164:?, LOOP_END, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int onCommand(java.lang.String r15) {
            /*
                Method dump skipped, instruction units count: 655
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.SettingsService.MyShellCommand.onCommand(java.lang.String):int");
        }

        List<String> listForUser(IContentProvider iContentProvider, int i, String str) {
            String str2;
            if ("system".equals(str)) {
                str2 = "LIST_system";
            } else if ("secure".equals(str)) {
                str2 = "LIST_secure";
            } else {
                if (!"global".equals(str)) {
                    getErrPrintWriter().println("Invalid table; no list performed");
                    throw new IllegalArgumentException("Invalid table " + str);
                }
                str2 = "LIST_global";
            }
            String str3 = str2;
            ArrayList arrayList = new ArrayList();
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("_user", i);
                arrayList.addAll(iContentProvider.call(resolveCallingPackage(), (String) null, "settings", str3, (String) null, bundle).getStringArrayList("result_settings_list"));
                Collections.sort(arrayList);
                return arrayList;
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        String getForUser(IContentProvider iContentProvider, int i, String str, String str2) {
            String str3;
            if ("system".equals(str)) {
                str3 = "GET_system";
            } else if ("secure".equals(str)) {
                str3 = "GET_secure";
            } else {
                if (!"global".equals(str)) {
                    getErrPrintWriter().println("Invalid table; no put performed");
                    throw new IllegalArgumentException("Invalid table " + str);
                }
                str3 = "GET_global";
            }
            String str4 = str3;
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("_user", i);
                Bundle bundleCall = iContentProvider.call(resolveCallingPackage(), (String) null, "settings", str4, str2, bundle);
                if (bundleCall != null) {
                    return bundleCall.getPairValue();
                }
                return null;
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        void putForUser(IContentProvider iContentProvider, int i, String str, String str2, String str3, String str4, boolean z) {
            String str5 = "PUT_system";
            if ("system".equals(str)) {
                if (z) {
                    getOutPrintWriter().print("Ignored makeDefault - doesn't apply to system settings");
                    z = false;
                }
            } else if ("secure".equals(str)) {
                str5 = "PUT_secure";
            } else {
                if (!"global".equals(str)) {
                    getErrPrintWriter().println("Invalid table; no put performed");
                    return;
                }
                str5 = "PUT_global";
            }
            String str6 = str5;
            try {
                Bundle bundle = new Bundle();
                bundle.putString("value", str3);
                bundle.putInt("_user", i);
                if (str4 != null) {
                    bundle.putString("_tag", str4);
                }
                if (z) {
                    bundle.putBoolean("_make_default", true);
                }
                iContentProvider.call(resolveCallingPackage(), (String) null, "settings", str6, str2, bundle);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        int deleteForUser(IContentProvider iContentProvider, int i, String str, String str2) {
            String str3;
            if ("system".equals(str)) {
                str3 = "DELETE_system";
            } else if ("secure".equals(str)) {
                str3 = "DELETE_secure";
            } else {
                if (!"global".equals(str)) {
                    getErrPrintWriter().println("Invalid table; no delete performed");
                    throw new IllegalArgumentException("Invalid table " + str);
                }
                str3 = "DELETE_global";
            }
            String str4 = str3;
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("_user", i);
                return iContentProvider.call(resolveCallingPackage(), (String) null, "settings", str4, str2, bundle).getInt("result_rows_deleted");
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        void resetForUser(IContentProvider iContentProvider, int i, String str, String str2) {
            String str3;
            if ("secure".equals(str)) {
                str3 = "RESET_secure";
            } else {
                if (!"global".equals(str)) {
                    getErrPrintWriter().println("Invalid table; no reset performed");
                    return;
                }
                str3 = "RESET_global";
            }
            String str4 = str3;
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("_user", i);
                bundle.putInt("_reset_mode", this.mResetMode);
                if (str2 != null) {
                    bundle.putString("_tag", str2);
                }
                String strResolveCallingPackage = this.mPackageName != null ? this.mPackageName : resolveCallingPackage();
                bundle.putInt("_user", i);
                iContentProvider.call(strResolveCallingPackage, (String) null, "settings", str4, (String) null, bundle);
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        public static String resolveCallingPackage() {
            int callingUid = Binder.getCallingUid();
            if (callingUid == 0) {
                return "root";
            }
            if (callingUid != 2000) {
                return null;
            }
            return "com.android.shell";
        }

        public void onHelp() {
            dumpHelp(getOutPrintWriter(), this.mDumping);
        }

        static void dumpHelp(PrintWriter printWriter, boolean z) {
            if (z) {
                printWriter.println("Settings provider dump options:");
                printWriter.println("  [-h] [--proto]");
                printWriter.println("  -h: print this help.");
                printWriter.println("  --proto: dump as protobuf.");
                return;
            }
            printWriter.println("Settings provider (settings) commands:");
            printWriter.println("  help");
            printWriter.println("      Print this help text.");
            printWriter.println("  get [--user <USER_ID> | current] NAMESPACE KEY");
            printWriter.println("      Retrieve the current value of KEY.");
            printWriter.println("  put [--user <USER_ID> | current] NAMESPACE KEY VALUE [TAG] [default]");
            printWriter.println("      Change the contents of KEY to VALUE.");
            printWriter.println("      TAG to associate with the setting.");
            printWriter.println("      {default} to set as the default, case-insensitive only for global/secure namespace");
            printWriter.println("  delete [--user <USER_ID> | current] NAMESPACE KEY");
            printWriter.println("      Delete the entry for KEY.");
            printWriter.println("  reset [--user <USER_ID> | current] NAMESPACE {PACKAGE_NAME | RESET_MODE}");
            printWriter.println("      Reset the global/secure table for a package with mode.");
            printWriter.println("      RESET_MODE is one of {untrusted_defaults, untrusted_clear, trusted_defaults}, case-insensitive");
            printWriter.println("  list [--user <USER_ID> | current] NAMESPACE");
            printWriter.println("      Print all defined keys.");
            printWriter.println("      NAMESPACE is one of {system, secure, global}, case-insensitive");
        }
    }

    /* JADX INFO: renamed from: com.android.providers.settings.SettingsService$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$providers$settings$SettingsService$MyShellCommand$CommandVerb;

        static {
            int[] iArr = new int[MyShellCommand.CommandVerb.values().length];
            $SwitchMap$com$android$providers$settings$SettingsService$MyShellCommand$CommandVerb = iArr;
            try {
                iArr[MyShellCommand.CommandVerb.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$providers$settings$SettingsService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$providers$settings$SettingsService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$providers$settings$SettingsService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$providers$settings$SettingsService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.RESET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
