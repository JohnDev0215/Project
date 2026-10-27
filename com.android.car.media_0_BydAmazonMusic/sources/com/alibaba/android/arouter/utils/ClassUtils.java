package com.alibaba.android.arouter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.DefaultPoolExecutor;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class ClassUtils {
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String PREFS_FILE = "multidex.version";
    private static final String SECONDARY_FOLDER_NAME = c.h(c.k("code_cache"), File.separator, "secondary-dexes");
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;

    public static class a implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Set c;
        public final /* synthetic */ CountDownLatch d;

        public a(String str, String str2, Set set, CountDownLatch countDownLatch) {
            this.a = str;
            this.b = str2;
            this.c = set;
            this.d = countDownLatch;
        }

        @Override // java.lang.Runnable
        public final void run() {
            DexFile dexFile = null;
            try {
                if (this.a.endsWith(ClassUtils.EXTRACTED_SUFFIX)) {
                    dexFile = DexFile.loadDex(this.a, this.a + ".tmp", 0);
                } else {
                    dexFile = new DexFile(this.a);
                }
                Enumeration<String> enumerationEntries = dexFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    String strNextElement = enumerationEntries.nextElement();
                    if (strNextElement.startsWith(this.b)) {
                        this.c.add(strNextElement);
                    }
                }
            } catch (Throwable th) {
                try {
                    Log.e(Consts.SDK_NAME, "Scan map file in dex files made error.", th);
                } finally {
                    if (dexFile != null) {
                        try {
                            dexFile.close();
                        } catch (Throwable unused) {
                        }
                    }
                    this.d.countDown();
                }
            }
        }
    }

    public static Set<String> getFileNameByPackageName(Context context, String str) throws InterruptedException, PackageManager.NameNotFoundException, IOException {
        HashSet hashSet = new HashSet();
        List<String> sourcePaths = getSourcePaths(context);
        CountDownLatch countDownLatch = new CountDownLatch(sourcePaths.size());
        Iterator<String> it = sourcePaths.iterator();
        while (it.hasNext()) {
            DefaultPoolExecutor.getInstance().execute(new a(it.next(), str, hashSet, countDownLatch));
        }
        countDownLatch.await();
        Log.d("ARouter::", "Filter " + hashSet.size() + " classes by packageName <" + str + ">");
        return hashSet;
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, 4);
    }

    public static List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + EXTRACTED_NAME_EXT;
        if (!isVMMultidexCapable()) {
            int i = getMultiDexPreferences(context).getInt(KEY_DEX_NUMBER, 1);
            File file2 = new File(applicationInfo.dataDir, SECONDARY_FOLDER_NAME);
            for (int i2 = 2; i2 <= i; i2++) {
                File file3 = new File(file2, str + i2 + EXTRACTED_SUFFIX);
                if (!file3.isFile()) {
                    StringBuilder sbK = c.k("Missing extracted secondary dex file '");
                    sbK.append(file3.getPath());
                    sbK.append("'");
                    throw new IOException(sbK.toString());
                }
                arrayList.add(file3.getAbsolutePath());
            }
        }
        if (ARouter.debuggable()) {
            arrayList.addAll(tryLoadInstantRunDexFile(applicationInfo));
        }
        return arrayList;
    }

    private static boolean isVMMultidexCapable() {
        boolean z = false;
        String str = null;
        try {
            if (isYunOS()) {
                str = "'YunOS'";
                if (Integer.valueOf(System.getProperty("ro.build.version.sdk")).intValue() >= 21) {
                    z = true;
                }
            } else {
                str = "'Android'";
                String property = System.getProperty("java.vm.version");
                if (property != null) {
                    Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(property);
                    if (matcher.matches()) {
                        int i = Integer.parseInt(matcher.group(1));
                        int i2 = Integer.parseInt(matcher.group(2));
                        if (i > 2 || (i == 2 && i2 >= 1)) {
                            z = true;
                        }
                    }
                }
            }
        } catch (NumberFormatException | Exception unused) {
        }
        StringBuilder sbN = c.n("VM with name ", str);
        sbN.append(z ? " has multidex support" : " does not have multidex support");
        Log.i("ARouter::", sbN.toString());
        return z;
    }

    private static boolean isYunOS() {
        try {
            String property = System.getProperty("ro.yunos.version");
            String property2 = System.getProperty("java.vm.name");
            if (property2 == null || !property2.toLowerCase().contains("lemur")) {
                if (property == null) {
                    return false;
                }
                if (property.trim().length() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static List<String> tryLoadInstantRunDexFile(ApplicationInfo applicationInfo) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = applicationInfo.splitSourceDirs;
        if (strArr != null) {
            arrayList.addAll(Arrays.asList(strArr));
            Log.d("ARouter::", "Found InstantRun support");
        } else {
            try {
                File file = new File((String) Class.forName("com.android.tools.fd.runtime.Paths").getMethod("getDexFileDirectory", String.class).invoke(null, applicationInfo.packageName));
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2 != null && file2.exists() && file2.isFile() && file2.getName().endsWith(".dex")) {
                            arrayList.add(file2.getAbsolutePath());
                        }
                    }
                    Log.d("ARouter::", "Found InstantRun support");
                }
            } catch (Exception e) {
                StringBuilder sbK = c.k("InstantRun support error, ");
                sbK.append(e.getMessage());
                Log.e("ARouter::", sbK.toString());
            }
        }
        return arrayList;
    }
}
