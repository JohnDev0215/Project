package com.neusoft.na.ocservice;

import android.content.Intent;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.Tool;
import com.neusoft.na.onecore.DbUpdate;
import com.neusoft.na.onecore.DbUpdateProvider;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: DbuService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0014J\"\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/neusoft/na/ocservice/DbuService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "dbuPrivateTempPath", "", "dbuPublicTempPath", "Ljava/io/File;", "getDbuPublicTempPath", "()Ljava/io/File;", "dbuPublicTempPath$delegate", "Lkotlin/Lazy;", "dbuTempPath", "mDbUpdate", "Lcom/neusoft/na/onecore/DbUpdate;", "init", "", "onCreate", "onDestroy", "onStart", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "startDbUpdate", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DbuService extends ServiceBase {
    private final String dbuPrivateTempPath;

    /* JADX INFO: renamed from: dbuPublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy dbuPublicTempPath;
    private final String dbuTempPath;
    private DbUpdate mDbUpdate;

    public DbuService() {
        super("dbu");
        this.dbuTempPath = "/local/dbu/var/nais/nds_updates";
        this.dbuPrivateTempPath = "/home/local/dbu/var/nais/nds_updates";
        this.dbuPublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.DbuService$dbuPublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), "__org.liquidplayer.node__/_onecloud_client" + this.this$0.dbuTempPath);
            }
        });
    }

    private final File getDbuPublicTempPath() {
        return (File) this.dbuPublicTempPath.getValue();
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceBase.INSTANCE.getLogger().debug("DbuService.onStartCommand()");
        int iOnStartCommand = super.onStartCommand(intent, flags, startId);
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.UNINITIALIZED) {
            init();
        }
        return iOnStartCommand;
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        ServiceBase.INSTANCE.getLogger().debug("DbuService.onCreate()");
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        ServiceBase.INSTANCE.getLogger().debug("DbuService.onDestroy()");
        DbUpdate dbUpdate = this.mDbUpdate;
        if (dbUpdate != null) {
            dbUpdate.stop();
        }
        super.onDestroy();
    }

    private final synchronized void init() {
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZING, null, 2, null));
        getPrivateTempDir().mkdirs();
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZED, null, 2, null));
    }

    private final boolean startDbUpdate() {
        DbUpdate dbUpdate = this.mDbUpdate;
        if (dbUpdate == null) {
            return false;
        }
        if (dbUpdate.isRunning()) {
            ServiceBase.INSTANCE.getLogger().warn("DbUpdate already running!");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sbAppend = sb.append("--main.path_share=" + getAssetsInstallDir() + "/ocrsc/share/");
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringsKt.appendln(sbAppend);
        StringBuilder sbAppend2 = sb.append("--main.PersistencyPath=" + getFilesDir() + IOUtils.DIR_SEPARATOR_UNIX);
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append(value)");
        StringsKt.appendln(sbAppend2);
        StringBuilder sbAppend3 = sb.append("--main.path_temp=" + getPrivateTempDir() + IOUtils.DIR_SEPARATOR_UNIX);
        Intrinsics.checkNotNullExpressionValue(sbAppend3, "append(value)");
        StringsKt.appendln(sbAppend3);
        StringBuilder sbAppend4 = sb.append("--main.path_cache=" + getPrivateCacheDir() + IOUtils.DIR_SEPARATOR_UNIX);
        Intrinsics.checkNotNullExpressionValue(sbAppend4, "append(value)");
        StringsKt.appendln(sbAppend4);
        StringBuilder sbAppend5 = sb.append("--main.resource_set=byd_dx");
        Intrinsics.checkNotNullExpressionValue(sbAppend5, "append(value)");
        StringsKt.appendln(sbAppend5);
        StringBuilder sbAppend6 = sb.append("--main.config=" + getAssetsInstallDir() + "/ocrsc/etc/nais/na_dbu.ini");
        Intrinsics.checkNotNullExpressionValue(sbAppend6, "append(value)");
        StringsKt.appendln(sbAppend6);
        final File file = new File(getAssetsInstallDir(), FileUtils.getInstance(this).getDbuIniPath());
        if (file.exists() && file.isFile()) {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.DbuService$startDbUpdate$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "using mots na_dbu.ini file: " + file;
                }
            });
            StringBuilder sbAppend7 = sb.append("--main.config+=" + file);
            Intrinsics.checkNotNullExpressionValue(sbAppend7, "append(value)");
            StringsKt.appendln(sbAppend7);
        } else {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.DbuService$startDbUpdate$1$1$2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "no mots na_dbu.ini file found, skipping";
                }
            });
        }
        StringBuilder sbAppend8 = sb.append("--dbupdate.deploy=" + getAssetsInstallDir() + "/ocrsc/etc/nais/na_dbu_deploy.json");
        Intrinsics.checkNotNullExpressionValue(sbAppend8, "append(value)");
        StringsKt.appendln(sbAppend8);
        StringBuilder sbAppend9 = sb.append("--nds.ndslib=" + getSharedLibraryPath("libNdsLib.so"));
        Intrinsics.checkNotNullExpressionValue(sbAppend9, "append(value)");
        StringsKt.appendln(sbAppend9);
        File file2 = new File(getAssetsInstallDir(), "db/ROOT.NDS");
        if (file2.exists() && file2.isFile()) {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.DbuService$startDbUpdate$1$2$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "using bundled database";
                }
            });
            StringBuilder sbAppend10 = sb.append("--nds.database=" + file2);
            Intrinsics.checkNotNullExpressionValue(sbAppend10, "append(value)");
            StringsKt.appendln(sbAppend10);
        } else {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.DbuService$startDbUpdate$1$2$2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "using external database";
                }
            });
        }
        StringBuilder sbAppend11 = sb.append("--dbupdate.download_dir=" + getDbuPublicTempPath() + IOUtils.DIR_SEPARATOR_UNIX);
        Intrinsics.checkNotNullExpressionValue(sbAppend11, "append(value)");
        StringsKt.appendln(sbAppend11);
        try {
            File externalDBUIni = ConstantsKt.getExternalDBUIni();
            if (externalDBUIni.exists() && externalDBUIni.isFile()) {
                ServiceBase.INSTANCE.getLogger().info("using extern navi dbu ini file: " + externalDBUIni);
                StringBuilder sbAppend12 = sb.append("--main.config+=" + externalDBUIni.getAbsolutePath());
                Intrinsics.checkNotNullExpressionValue(sbAppend12, "append(value)");
                StringsKt.appendln(sbAppend12);
            } else {
                ServiceBase.INSTANCE.getLogger().info("extern navi na_dbu.ini not found: " + externalDBUIni);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        ServiceBase.INSTANCE.getLogger().debug("arguments: " + string);
        if (dbUpdate.start(string)) {
            return true;
        }
        ServiceBase.INSTANCE.getLogger().error("failed to start na_dbu");
        return false;
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected synchronized void onStart() {
        if (this.mDbUpdate == null) {
            this.mDbUpdate = DbUpdateProvider.INSTANCE.getInstance();
        }
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.INITIALIZED) {
            updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.STARTING, null, 2, null));
            initPrivateTempDir();
            File privateCacheDir = getPrivateCacheDir();
            if (!privateCacheDir.isDirectory()) {
                privateCacheDir.mkdirs();
            }
            if (startDbUpdate()) {
                Tool.getSelfMaps("Dbu");
                updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.RUNNING, null, 2, null));
            } else {
                ServiceBase.INSTANCE.getLogger().error("failed to start native applications; now we're stuck in undefined state :(");
                updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.ERROR, null, 2, null));
            }
        }
    }
}
