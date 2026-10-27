package com.android.providers.settings;

import android.annotation.SystemApi;
import android.app.ActivityManager;
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
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class DeviceConfigService extends Binder {
    final SettingsProvider mProvider;

    public DeviceConfigService(SettingsProvider settingsProvider) {
        this.mProvider = settingsProvider;
    }

    public void onShellCommand(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, FileDescriptor fileDescriptor3, String[] strArr, ShellCallback shellCallback, ResultReceiver resultReceiver) {
        new MyShellCommand(this.mProvider).exec(this, fileDescriptor, fileDescriptor2, fileDescriptor3, strArr, shellCallback, resultReceiver);
    }

    static final class MyShellCommand extends ShellCommand {
        final SettingsProvider mProvider;

        enum CommandVerb {
            UNSPECIFIED,
            GET,
            PUT,
            DELETE,
            LIST,
            RESET
        }

        MyShellCommand(SettingsProvider settingsProvider) {
            this.mProvider = settingsProvider;
        }

        /* JADX WARN: Removed duplicated region for block: B:128:0x010e A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x006a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int onCommand(java.lang.String r17) {
            /*
                Method dump skipped, instruction units count: 506
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.settings.DeviceConfigService.MyShellCommand.onCommand(java.lang.String):int");
        }

        public void onHelp() {
            PrintWriter outPrintWriter = getOutPrintWriter();
            outPrintWriter.println("Device Config (device_config) commands:");
            outPrintWriter.println("  help");
            outPrintWriter.println("      Print this help text.");
            outPrintWriter.println("  get NAMESPACE KEY");
            outPrintWriter.println("      Retrieve the current value of KEY from the given NAMESPACE.");
            outPrintWriter.println("  put NAMESPACE KEY VALUE [default]");
            outPrintWriter.println("      Change the contents of KEY to VALUE for the given NAMESPACE.");
            outPrintWriter.println("      {default} to set as the default value.");
            outPrintWriter.println("  delete NAMESPACE KEY");
            outPrintWriter.println("      Delete the entry for KEY for the given NAMESPACE.");
            outPrintWriter.println("  list [NAMESPACE]");
            outPrintWriter.println("      Print all keys and values defined, optionally for the given NAMESPACE.");
            outPrintWriter.println("  reset RESET_MODE [NAMESPACE]");
            outPrintWriter.println("      Reset all flag values, optionally for a NAMESPACE, according to RESET_MODE.");
            outPrintWriter.println("      RESET_MODE is one of {untrusted_defaults, untrusted_clear, trusted_defaults}");
            outPrintWriter.println("      NAMESPACE limits which flags are reset if provided, otherwise all flags are reset");
        }

        private boolean delete(IContentProvider iContentProvider, String str, String str2) {
            String str3 = str + "/" + str2;
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("_user", ActivityManager.getService().getCurrentUser().id);
                Bundle bundleCall = iContentProvider.call(resolveCallingPackage(), (String) null, "settings", "DELETE_config", str3, bundle);
                if (bundleCall != null) {
                    if (bundleCall.getInt("result_rows_deleted") == 1) {
                        return true;
                    }
                }
                return false;
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        private List<String> listAll(IContentProvider iContentProvider) {
            ArrayList arrayList = new ArrayList();
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("_user", ActivityManager.getService().getCurrentUser().id);
                Bundle bundleCall = iContentProvider.call(resolveCallingPackage(), (String) null, "settings", "LIST_config", (String) null, bundle);
                if (bundleCall != null) {
                    HashMap map = (HashMap) bundleCall.getSerializable("value");
                    for (String str : map.keySet()) {
                        arrayList.add(str + "=" + ((String) map.get(str)));
                    }
                }
                Collections.sort(arrayList);
                return arrayList;
            } catch (RemoteException e) {
                throw new RuntimeException("Failed in IPC", e);
            }
        }

        private static String resolveCallingPackage() {
            int callingUid = Binder.getCallingUid();
            if (callingUid == 0) {
                return "root";
            }
            if (callingUid != 2000) {
                return null;
            }
            return "com.android.shell";
        }
    }

    /* JADX INFO: renamed from: com.android.providers.settings.DeviceConfigService$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$providers$settings$DeviceConfigService$MyShellCommand$CommandVerb;

        static {
            int[] iArr = new int[MyShellCommand.CommandVerb.values().length];
            $SwitchMap$com$android$providers$settings$DeviceConfigService$MyShellCommand$CommandVerb = iArr;
            try {
                iArr[MyShellCommand.CommandVerb.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$providers$settings$DeviceConfigService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$providers$settings$DeviceConfigService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$providers$settings$DeviceConfigService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$providers$settings$DeviceConfigService$MyShellCommand$CommandVerb[MyShellCommand.CommandVerb.RESET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
