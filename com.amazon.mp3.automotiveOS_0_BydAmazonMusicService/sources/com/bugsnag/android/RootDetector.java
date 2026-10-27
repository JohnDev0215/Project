package com.bugsnag.android;

import androidx.annotation.VisibleForTesting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: RootDetector.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB3\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\r\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0013J\b\u0010\u0014\u001a\u00020\rH\u0002J\u0015\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u0017J\u0006\u0010\u0018\u001a\u00020\rJ\b\u0010\u0019\u001a\u00020\rH\u0002J\t\u0010\u001a\u001a\u00020\rH\u0082 J\f\u0010\u001b\u001a\u00020\r*\u00020\u001cH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/bugsnag/android/RootDetector;", "", "deviceBuildInfo", "Lcom/bugsnag/android/DeviceBuildInfo;", "rootBinaryLocations", "", "", "buildProps", "Ljava/io/File;", "logger", "Lcom/bugsnag/android/Logger;", "(Lcom/bugsnag/android/DeviceBuildInfo;Ljava/util/List;Ljava/io/File;Lcom/bugsnag/android/Logger;)V", "libraryLoaded", "", "checkBuildProps", "checkBuildProps$bugsnag_android_core_release", "checkBuildTags", "checkBuildTags$bugsnag_android_core_release", "checkRootBinaries", "checkRootBinaries$bugsnag_android_core_release", "checkSuExists", "processBuilder", "Ljava/lang/ProcessBuilder;", "checkSuExists$bugsnag_android_core_release", "isRooted", "nativeCheckRoot", "performNativeRootChecks", "isNotBlank", "Ljava/io/Reader;", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class RootDetector {
    private final File buildProps;
    private final DeviceBuildInfo deviceBuildInfo;
    private volatile boolean libraryLoaded;
    private final Logger logger;
    private final List<String> rootBinaryLocations;
    private static final File BUILD_PROP_FILE = new File("/system/build.prop");
    private static final List<String> ROOT_INDICATORS = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"/system/xbin/su", "/system/bin/su", "/system/app/Superuser.apk", "/system/app/SuperSU.apk", "/system/app/Superuser", "/system/app/SuperSU", "/system/xbin/daemonsu", "/su/bin"});

    private final native boolean performNativeRootChecks();

    @JvmOverloads
    public RootDetector(DeviceBuildInfo deviceBuildInfo, List<String> list, File file, Logger logger) {
        this.deviceBuildInfo = deviceBuildInfo;
        this.rootBinaryLocations = list;
        this.buildProps = file;
        this.logger = logger;
        try {
            System.loadLibrary("bugsnag-root-detection");
            this.libraryLoaded = true;
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public /* synthetic */ RootDetector(DeviceBuildInfo deviceBuildInfo, List list, File file, Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DeviceBuildInfo.INSTANCE.defaultInfo() : deviceBuildInfo, (i & 2) != 0 ? ROOT_INDICATORS : list, (i & 4) != 0 ? BUILD_PROP_FILE : file, logger);
    }

    public final boolean isRooted() {
        try {
            if (!checkBuildTags$bugsnag_android_core_release() && !checkSuExists() && !checkBuildProps$bugsnag_android_core_release() && !checkRootBinaries$bugsnag_android_core_release()) {
                if (!nativeCheckRoot()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            this.logger.w("Root detection failed", th);
            return false;
        }
    }

    private final boolean checkSuExists() {
        return checkSuExists$bugsnag_android_core_release(new ProcessBuilder(new String[0]));
    }

    public final boolean checkBuildTags$bugsnag_android_core_release() {
        String tags = this.deviceBuildInfo.getTags();
        return tags != null && StringsKt__StringsKt.contains$default((CharSequence) tags, (CharSequence) "test-keys", false, 2, (Object) null);
    }

    public final boolean checkRootBinaries$bugsnag_android_core_release() {
        try {
            Result.Companion companion = Result.INSTANCE;
            Iterator<String> it = this.rootBinaryLocations.iterator();
            while (it.hasNext()) {
                if (new File(it.next()).exists()) {
                    return true;
                }
            }
            Result.m2810constructorimpl(Unit.INSTANCE);
            return false;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th));
            return false;
        }
    }

    public final boolean checkBuildProps$bugsnag_android_core_release() {
        try {
            Result.Companion companion = Result.INSTANCE;
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(this.buildProps), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                boolean zAny = SequencesKt___SequencesKt.any(SequencesKt___SequencesKt.filter(SequencesKt___SequencesKt.map(TextStreamsKt.lineSequence(bufferedReader), new Function1<String, String>() { // from class: com.bugsnag.android.RootDetector$checkBuildProps$1$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(String str) {
                        return new Regex("\\s").replace(str, "");
                    }
                }), new Function1<String, Boolean>() { // from class: com.bugsnag.android.RootDetector$checkBuildProps$1$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(String str) {
                        return Boolean.valueOf(invoke2(str));
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2(String str) {
                        return StringsKt__StringsJVMKt.startsWith$default(str, "ro.debuggable=[1]", false, 2, null) || StringsKt__StringsJVMKt.startsWith$default(str, "ro.secure=[0]", false, 2, null);
                    }
                }));
                CloseableKt.closeFinally(bufferedReader, null);
                return zAny;
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th));
            return false;
        }
    }

    @VisibleForTesting
    public final boolean checkSuExists$bugsnag_android_core_release(ProcessBuilder processBuilder) throws Throwable {
        Throwable th;
        Process process;
        boolean zIsNotBlank;
        BufferedReader bufferedReader;
        processBuilder.command(CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"which", "su"}));
        Process process2 = null;
        try {
            process = processBuilder.start();
            try {
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(process, "process");
                InputStream inputStream = process.getInputStream();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(inputStream, "process.inputStream");
                Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            } catch (IOException unused) {
                process2 = process;
                zIsNotBlank = false;
                if (process2 != null) {
                    process2.destroy();
                }
            } catch (Throwable th2) {
                th = th2;
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            th = th3;
            process = null;
        }
        try {
            zIsNotBlank = isNotBlank(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            process.destroy();
            return zIsNotBlank;
        } finally {
        }
    }

    private final boolean isNotBlank(Reader reader) throws IOException {
        int i;
        do {
            i = reader.read();
            if (i == -1) {
                return false;
            }
        } while (CharsKt__CharJVMKt.isWhitespace((char) i));
        return true;
    }

    private final boolean nativeCheckRoot() {
        if (this.libraryLoaded) {
            return performNativeRootChecks();
        }
        return false;
    }
}
