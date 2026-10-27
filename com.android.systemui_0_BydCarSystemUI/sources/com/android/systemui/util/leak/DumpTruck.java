package com.android.systemui.util.leak;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.android.systemui.Dependency;
import com.android.systemui.util.leak.GarbageMonitor;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class DumpTruck {
    private static final int BUFSIZ = 524288;
    private static final String FILEPROVIDER_AUTHORITY = "com.android.systemui.fileprovider";
    private static final String FILEPROVIDER_PATH = "leak";
    private static final String TAG = "DumpTruck";
    final StringBuilder body = new StringBuilder();
    private final Context context;
    private Uri hprofUri;

    public DumpTruck(Context context) {
        this.context = context;
    }

    public DumpTruck captureHeaps(int[] pids) {
        GarbageMonitor.ProcessMemInfo memInfo;
        DumpTruck dumpTruck = this;
        GarbageMonitor garbageMonitor = (GarbageMonitor) Dependency.get(GarbageMonitor.class);
        File file = new File(dumpTruck.context.getCacheDir(), FILEPROVIDER_PATH);
        file.mkdirs();
        dumpTruck.hprofUri = null;
        dumpTruck.body.setLength(0);
        dumpTruck.body.append("Build: ").append(Build.DISPLAY).append("\n\nProcesses:\n");
        ArrayList arrayList = new ArrayList();
        int iMyPid = Process.myPid();
        for (int i : Arrays.copyOf(pids, pids.length)) {
            dumpTruck.body.append("  pid ").append(i);
            if (garbageMonitor != null && (memInfo = garbageMonitor.getMemInfo(i)) != null) {
                dumpTruck.body.append(":").append(" up=").append(memInfo.getUptime()).append(" pss=").append(memInfo.currentPss).append(" uss=").append(memInfo.currentUss);
            }
            if (i == iMyPid) {
                String path = new File(file, String.format("heap-%d.ahprof", Integer.valueOf(i))).getPath();
                Log.v(TAG, "Dumping memory info for process " + i + " to " + path);
                try {
                    Debug.dumpHprofData(path);
                    arrayList.add(path);
                    dumpTruck = this;
                } catch (IOException e) {
                    e = e;
                    dumpTruck = this;
                }
                try {
                    dumpTruck.body.append(" (hprof attached)");
                } catch (IOException e2) {
                    e = e2;
                    Log.e(TAG, "error dumping memory:", e);
                    dumpTruck.body.append("\n** Could not dump heap: \n").append(e.toString()).append(BydAdbHelper.COMMAND_LINE_END);
                }
            } else {
                dumpTruck = this;
            }
            dumpTruck.body.append(BydAdbHelper.COMMAND_LINE_END);
        }
        try {
            String canonicalPath = new File(file, String.format("hprof-%d.zip", Long.valueOf(System.currentTimeMillis()))).getCanonicalPath();
            if (zipUp(canonicalPath, arrayList)) {
                dumpTruck.hprofUri = FileProvider.getUriForFile(dumpTruck.context, "com.android.systemui.fileprovider", new File(canonicalPath));
            }
        } catch (IOException e3) {
            Log.e(TAG, "unable to zip up heapdumps", e3);
            dumpTruck.body.append("\n** Could not zip up files: \n").append(e3.toString()).append(BydAdbHelper.COMMAND_LINE_END);
        }
        return dumpTruck;
    }

    public Uri getDumpUri() {
        return this.hprofUri;
    }

    public Intent createShareIntent() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(268435456);
        intent.addFlags(1);
        intent.putExtra("android.intent.extra.SUBJECT", "SystemUI memory dump");
        intent.putExtra("android.intent.extra.TEXT", this.body.toString());
        if (this.hprofUri != null) {
            intent.setType("application/zip");
            intent.putExtra("android.intent.extra.STREAM", this.hprofUri);
        }
        return intent;
    }

    private static boolean zipUp(String zipfilePath, ArrayList<String> paths) {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipfilePath));
            try {
                byte[] bArr = new byte[524288];
                for (String str : paths) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                    try {
                        zipOutputStream.putNextEntry(new ZipEntry(str));
                        while (true) {
                            int i = bufferedInputStream.read(bArr, 0, 524288);
                            if (i <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, i);
                        }
                        zipOutputStream.closeEntry();
                        bufferedInputStream.close();
                    } finally {
                    }
                }
                zipOutputStream.close();
                return true;
            } finally {
            }
        } catch (IOException e) {
            Log.e(TAG, "error zipping up profile data", e);
            return false;
        }
    }
}
