package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.visualon.OSMPUtils.voOSType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public final class DynamiteModule {

    @KeepForSdk
    public static final int LOCAL = -1;

    @KeepForSdk
    public static final int NONE = 0;

    @KeepForSdk
    public static final int NO_SELECTION = 0;

    @KeepForSdk
    public static final int REMOTE = 1;

    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzb = null;

    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static String zzc = null;

    @GuardedBy("DynamiteModule.class")
    private static boolean zzd = false;

    @GuardedBy("DynamiteModule.class")
    private static int zze = -1;

    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzf;

    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static zzq zzk;

    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static zzr zzl;
    private final Context zzj;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzf();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzg();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();

    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();

    @NonNull
    public static final VersionPolicy zza = new zzl();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    @DynamiteApi
    public static class DynamiteLoaderClassLoader {

        @Nullable
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    @KeepForSdk
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
        @KeepForSdk
        public interface IVersions {
            int zza(@NonNull Context context, @NonNull String str);

            int zzb(@NonNull Context context, @NonNull String str, boolean z) throws LoadingException;
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
        @KeepForSdk
        public static class SelectionResult {

            @KeepForSdk
            public int localVersion = 0;

            @KeepForSdk
            public int remoteVersion = 0;

            @KeepForSdk
            public int selection = 0;
        }

        @NonNull
        @KeepForSdk
        SelectionResult selectModule(@NonNull Context context, @NonNull String str, @NonNull IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    @KeepForSdk
    public static int getLocalVersion(@NonNull Context context, @NonNull String str) {
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e.getMessage())));
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(@NonNull Context context, @NonNull String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x007d A[Catch: all -> 0x02d6, TRY_LEAVE, TryCatch #6 {all -> 0x02d6, blocks: (B:3:0x0025, B:7:0x006f, B:12:0x0077, B:15:0x007d, B:26:0x009f, B:107:0x0216, B:108:0x0221, B:110:0x0223, B:112:0x0225, B:113:0x022d, B:131:0x028d, B:132:0x02a4, B:115:0x022f, B:117:0x024d, B:119:0x025e, B:129:0x0284, B:130:0x028c, B:133:0x02a5, B:134:0x02d5), top: B:151:0x0025, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009d  */
    @androidx.annotation.NonNull
    @com.google.android.gms.common.annotation.KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.dynamite.DynamiteModule load(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.NonNull com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r18, @androidx.annotation.NonNull java.lang.String r19) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            Method dump skipped, instruction units count: 761
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00a7 A[Catch: all -> 0x00b2, TryCatch #5 {, blocks: (B:9:0x0026, B:11:0x0032, B:48:0x00b0, B:14:0x0038, B:16:0x003f, B:18:0x0045, B:21:0x0048, B:23:0x004c, B:27:0x0056, B:29:0x005e, B:32:0x0065, B:39:0x008f, B:40:0x0097, B:35:0x006c, B:37:0x0072, B:38:0x0081, B:43:0x009a, B:46:0x009d, B:47:0x00a7, B:15:0x003b), top: B:129:0x0026, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0166 A[Catch: all -> 0x01c2, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x01c2, blocks: (B:3:0x0002, B:61:0x00d9, B:63:0x00df, B:68:0x0100, B:90:0x0158, B:94:0x0166, B:115:0x01bb, B:116:0x01be, B:110:0x01b3, B:66:0x00e5, B:119:0x01c1, B:4:0x0003, B:7:0x0009, B:8:0x0025, B:59:0x00d6, B:19:0x0046, B:41:0x0098, B:44:0x009b, B:52:0x00b4, B:60:0x00d8, B:58:0x00ba), top: B:128:0x0002, inners: #9, #12 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x01b1 -> B:130:0x01b6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:110:0x01b3 -> B:130:0x01b6). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zza(@androidx.annotation.NonNull android.content.Context r10, @androidx.annotation.NonNull java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c9  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int zzb(android.content.Context r10, java.lang.String r11, boolean r12, boolean r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context.getApplicationContext());
    }

    @GuardedBy("DynamiteModule.class")
    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = iInterfaceQueryLocalInterface instanceof zzr ? (zzr) iInterfaceQueryLocalInterface : new zzr(iBinder);
            }
            zzl = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzpVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zznVar = (zzn) zzg.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    @GuardedBy("DynamiteModule.class")
    private static boolean zzf(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(zzf)) {
            return true;
        }
        boolean zBooleanValue = false;
        if (zzf == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                zBooleanValue = true;
            }
            Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
            zzf = boolValueOf;
            zBooleanValue = boolValueOf.booleanValue();
            if (zBooleanValue && providerInfoResolveContentProvider != null && (applicationInfo = providerInfoResolveContentProvider.applicationInfo) != null && (applicationInfo.flags & voOSType.VOOSMP_PID_AUDIO_EFFECT_ENDPOINT_TYPE) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!zBooleanValue) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return zBooleanValue;
    }

    @Nullable
    private static zzq zzg(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = zzk;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    zzk = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    @NonNull
    @KeepForSdk
    public Context getModuleContext() {
        return this.zzj;
    }

    @NonNull
    @KeepForSdk
    public IBinder instantiate(@NonNull String str) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e, null);
        }
    }
}
