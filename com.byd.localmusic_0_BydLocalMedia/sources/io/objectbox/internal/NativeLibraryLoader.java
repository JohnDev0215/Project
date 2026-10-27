package io.objectbox.internal;

import com.android.internal.content.NativeLibraryHelper;
import io.objectbox.BoxStore;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.greenrobot.essentials.io.IoUtils;

/* JADX INFO: loaded from: classes5.dex */
public class NativeLibraryLoader {
    private static final String OBJECTBOX_JNI = "objectbox-jni";

    public static void ensureLoaded() {
    }

    static {
        String str;
        boolean z;
        String str2;
        String str3;
        String str4;
        String str5 = OBJECTBOX_JNI + ".so";
        String property = System.getProperty("java.vendor");
        String lowerCase = System.getProperty("os.name").toLowerCase();
        boolean zContains = property.contains("Android");
        if (zContains) {
            str = OBJECTBOX_JNI;
            z = true;
        } else {
            if (lowerCase.contains("mac")) {
                str3 = OBJECTBOX_JNI + "-macos";
                str4 = NativeLibraryHelper.LIB_DIR_NAME + str3 + ".dylib";
                checkUnpackLib(str4);
            } else {
                String str6 = NativeLibraryHelper.CLEAR_ABI_OVERRIDE + getCpuArch();
                if (lowerCase.contains("windows")) {
                    str3 = OBJECTBOX_JNI + "-windows" + str6;
                    str4 = str3 + ".dll";
                    checkUnpackLib(str4);
                } else {
                    if (lowerCase.contains("linux")) {
                        str3 = OBJECTBOX_JNI + "-linux" + str6;
                        str4 = NativeLibraryHelper.LIB_DIR_NAME + str3 + ".so";
                        checkUnpackLib(str4);
                        z = true;
                        String str7 = str4;
                        str = str3;
                        str5 = str7;
                    }
                    str = OBJECTBOX_JNI;
                    z = true;
                }
            }
            z = false;
            String str72 = str4;
            str = str3;
            str5 = str72;
        }
        try {
            File file = new File(str5);
            if (file.exists()) {
                System.load(file.getAbsolutePath());
                return;
            }
            try {
                if (zContains) {
                    if (!loadLibraryAndroid()) {
                        System.loadLibrary(str);
                    }
                } else {
                    System.err.println("File not available: " + file.getAbsolutePath());
                    System.loadLibrary(str);
                }
            } catch (UnsatisfiedLinkError e) {
                if (!zContains && z) {
                    if (loadLibraryAndroid()) {
                        return;
                    }
                    System.loadLibrary(OBJECTBOX_JNI);
                    return;
                }
                throw e;
            }
        } catch (UnsatisfiedLinkError e2) {
            String property2 = System.getProperty("os.arch");
            if (zContains) {
                str2 = String.format("[ObjectBox] Android failed to load native library, check your APK/App Bundle includes a supported ABI or use ReLinker https://docs.objectbox.io/android/app-bundle-and-split-apk (vendor=%s,os=%s,os.arch=%s,SUPPORTED_ABIS=%s)", property, lowerCase, property2, getSupportedABIsAndroid());
            } else {
                str2 = String.format("[ObjectBox] Loading native library failed, please report this to us: vendor=%s,os=%s,os.arch=%s,model=%s,linux=%s,machine=%s", property, lowerCase, property2, System.getProperty("sun.arch.data.model"), Boolean.valueOf(z), getCpuArchOSOrNull());
            }
            throw new LinkageError(str2, e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a7 A[PHI: r0
  0x00a7: PHI (r0v2 java.lang.String) = (r0v1 java.lang.String), (r0v6 java.lang.String) binds: [B:3:0x0013, B:20:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getCpuArch() {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.objectbox.internal.NativeLibraryLoader.getCpuArch():java.lang.String");
    }

    @Nullable
    private static String getCpuArchOSOrNull() {
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("uname -m").getInputStream()));
            line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (Exception unused) {
            return line;
        }
    }

    private static void checkUnpackLib(String str) {
        String str2 = "/native/" + str;
        URL resource = NativeLibraryLoader.class.getResource(str2);
        if (resource == null) {
            System.err.println("Not available in classpath: " + str2);
            return;
        }
        File file = new File(str);
        try {
            URLConnection uRLConnectionOpenConnection = resource.openConnection();
            int contentLength = uRLConnectionOpenConnection.getContentLength();
            long lastModified = uRLConnectionOpenConnection.getLastModified();
            if (file.exists() && file.length() == contentLength && file.lastModified() == lastModified) {
                return;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(uRLConnectionOpenConnection.getInputStream());
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    IoUtils.copyAllBytes(bufferedInputStream, bufferedOutputStream);
                    IoUtils.safeClose(bufferedInputStream);
                    if (lastModified > 0) {
                        file.setLastModified(lastModified);
                    }
                } finally {
                    IoUtils.safeClose(bufferedOutputStream);
                }
            } catch (Throwable th) {
                IoUtils.safeClose(bufferedInputStream);
                throw th;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean loadLibraryAndroid() {
        if (BoxStore.getContext() == null) {
            return false;
        }
        try {
            Class<?> cls = Class.forName("android.content.Context");
            if (BoxStore.getRelinker() == null) {
                Class.forName("com.getkeepsafe.relinker.ReLinker").getMethod("loadLibrary", cls, String.class, String.class).invoke(null, BoxStore.getContext(), OBJECTBOX_JNI, BoxStore.JNI_VERSION);
            } else {
                BoxStore.getRelinker().getClass().getMethod("loadLibrary", cls, String.class, String.class).invoke(BoxStore.getRelinker(), BoxStore.getContext(), OBJECTBOX_JNI, BoxStore.JNI_VERSION);
            }
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    @Nonnull
    private static String getSupportedABIsAndroid() {
        String[] strArr = null;
        try {
            strArr = (String[]) Class.forName("android.os.Build").getField("SUPPORTED_ABIS").get(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
        }
        return strArr != null ? Arrays.toString(strArr) : "";
    }
}
