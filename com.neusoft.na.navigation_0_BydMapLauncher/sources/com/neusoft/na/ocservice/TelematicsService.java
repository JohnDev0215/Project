package com.neusoft.na.ocservice;

import android.content.Intent;
import android.util.Log;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.Tool;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.Executors;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import org.json.JSONObject;

/* JADX INFO: compiled from: TelematicsService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0016J\b\u0010(\u001a\u00020&H\u0016J\b\u0010)\u001a\u00020&H\u0014J\"\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020+H\u0016J\b\u00100\u001a\u00020&H\u0002J\b\u00101\u001a\u000202H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0011\u0010\u0006R\u001b\u0010\u0013\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0014\u0010\u0006R\u000e\u0010\u0016\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u0018\u0010\u0006R\u001b\u0010\u001a\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001b\u0010\u0006R\u000e\u0010\u001d\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\b\u001a\u0004\b \u0010\u0006R\u001b\u0010\"\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\b\u001a\u0004\b#\u0010\u0006¨\u00063"}, d2 = {"Lcom/neusoft/na/ocservice/TelematicsService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "dbuPrivateTempPath", "Ljava/io/File;", "getDbuPrivateTempPath", "()Ljava/io/File;", "dbuPrivateTempPath$delegate", "Lkotlin/Lazy;", "dbuPublicTempPath", "getDbuPublicTempPath", "dbuPublicTempPath$delegate", "dbuTempPath", "", "mTelematics", "Lcom/neusoft/na/ocservice/Telematics;", "resourceUpdatePrivateTempPath", "getResourceUpdatePrivateTempPath", "resourceUpdatePrivateTempPath$delegate", "resourceUpdatePublicTempPath", "getResourceUpdatePublicTempPath", "resourceUpdatePublicTempPath$delegate", "resourceUpdateTempPath", "trafficPrivateTempPath", "getTrafficPrivateTempPath", "trafficPrivateTempPath$delegate", "trafficPublicTempPath", "getTrafficPublicTempPath", "trafficPublicTempPath$delegate", "trafficTempPath", "uplinkdataPath", "uplinkdataPrivatePath", "getUplinkdataPrivatePath", "uplinkdataPrivatePath$delegate", "uplinkdataPubicPath", "getUplinkdataPubicPath", "uplinkdataPubicPath$delegate", "init", "", "onCreate", "onDestroy", "onStart", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "startCL", "startTelematics", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TelematicsService extends ServiceBase {

    /* JADX INFO: renamed from: dbuPrivateTempPath$delegate, reason: from kotlin metadata */
    private final Lazy dbuPrivateTempPath;

    /* JADX INFO: renamed from: dbuPublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy dbuPublicTempPath;
    private final String dbuTempPath;
    private Telematics mTelematics;

    /* JADX INFO: renamed from: resourceUpdatePrivateTempPath$delegate, reason: from kotlin metadata */
    private final Lazy resourceUpdatePrivateTempPath;

    /* JADX INFO: renamed from: resourceUpdatePublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy resourceUpdatePublicTempPath;
    private final String resourceUpdateTempPath;

    /* JADX INFO: renamed from: trafficPrivateTempPath$delegate, reason: from kotlin metadata */
    private final Lazy trafficPrivateTempPath;

    /* JADX INFO: renamed from: trafficPublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy trafficPublicTempPath;
    private final String trafficTempPath;
    private final String uplinkdataPath;

    /* JADX INFO: renamed from: uplinkdataPrivatePath$delegate, reason: from kotlin metadata */
    private final Lazy uplinkdataPrivatePath;

    /* JADX INFO: renamed from: uplinkdataPubicPath$delegate, reason: from kotlin metadata */
    private final Lazy uplinkdataPubicPath;

    public TelematicsService() {
        super("telematics");
        this.trafficTempPath = "/local/traffic";
        this.trafficPrivateTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$trafficPrivateTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.trafficTempPath));
            }
        });
        this.trafficPublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$trafficPublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.trafficTempPath));
            }
        });
        this.dbuTempPath = "/local/dbu/var/nais";
        this.dbuPrivateTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$dbuPrivateTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), "__org.liquidplayer.node__/_onecloud_client" + this.this$0.dbuTempPath);
            }
        });
        this.dbuPublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$dbuPublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), "__org.liquidplayer.node__/_onecloud_client" + this.this$0.dbuTempPath);
            }
        });
        this.uplinkdataPath = "/local/uplinkdata";
        this.uplinkdataPrivatePath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$uplinkdataPrivatePath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.uplinkdataPath));
            }
        });
        this.uplinkdataPubicPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$uplinkdataPubicPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.uplinkdataPath));
            }
        });
        this.resourceUpdateTempPath = "/local/resourceUpdate";
        this.resourceUpdatePrivateTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$resourceUpdatePrivateTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.resourceUpdateTempPath));
            }
        });
        this.resourceUpdatePublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.TelematicsService$resourceUpdatePublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.resourceUpdateTempPath));
            }
        });
    }

    private final File getTrafficPrivateTempPath() {
        return (File) this.trafficPrivateTempPath.getValue();
    }

    private final File getTrafficPublicTempPath() {
        return (File) this.trafficPublicTempPath.getValue();
    }

    private final File getDbuPrivateTempPath() {
        return (File) this.dbuPrivateTempPath.getValue();
    }

    private final File getDbuPublicTempPath() {
        return (File) this.dbuPublicTempPath.getValue();
    }

    private final File getUplinkdataPrivatePath() {
        return (File) this.uplinkdataPrivatePath.getValue();
    }

    private final File getUplinkdataPubicPath() {
        return (File) this.uplinkdataPubicPath.getValue();
    }

    private final File getResourceUpdatePrivateTempPath() {
        return (File) this.resourceUpdatePrivateTempPath.getValue();
    }

    private final File getResourceUpdatePublicTempPath() {
        return (File) this.resourceUpdatePublicTempPath.getValue();
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceBase.INSTANCE.getLogger().debug("TelematicsService.onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        ServiceBase.INSTANCE.getLogger().debug("TelematicsService.onCreate()");
        super.onCreate();
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.neusoft.na.ocservice.TelematicsService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TelematicsService.onCreate$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(TelematicsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.init();
    }

    @Override // android.app.Service
    public void onDestroy() {
        ServiceBase.INSTANCE.getLogger().debug("TelematicsService.onDestroy()");
        Telematics telematics = this.mTelematics;
        if (telematics != null) {
            telematics.stop();
        }
        super.onDestroy();
    }

    private final synchronized void init() {
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZING, null, 2, null));
        getPrivateTempDir().mkdirs();
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZED, null, 2, null));
        startCL();
    }

    private final boolean startTelematics() {
        Telematics telematics = this.mTelematics;
        Intrinsics.checkNotNull(telematics);
        if (telematics.getRunning()) {
            ServiceBase.INSTANCE.getLogger().warn("Telematics already running!");
            return true;
        }
        getTrafficPublicTempPath().mkdirs();
        getTrafficPrivateTempPath().mkdirs();
        getDbuPublicTempPath().mkdirs();
        getUplinkdataPubicPath().mkdirs();
        getUplinkdataPrivatePath().mkdirs();
        getResourceUpdatePublicTempPath().mkdirs();
        getResourceUpdatePrivateTempPath().mkdirs();
        StringBuilder sb = new StringBuilder("--config.localStoragePath=\"");
        File cacheDir = getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
        List<String> listMutableListOf = CollectionsKt.mutableListOf(sb.append(FilesKt.resolve(cacheDir, "na_telematics")).append(Typography.quote).toString(), "--config.logger.level=\"fatal\"", "--config.logger.backends.dlt.enabled=false", "--config.logger.backends.console.enabled=false", "--config.logger.redirectConsole=false", "--config.managers.trafficinfoprovider.config.filepathmapping.privateFilepath=\"" + getTrafficPrivateTempPath() + Typography.quote, "--config.managers.trafficinfoprovider.config.filepathmapping.publicFilepath=\"" + getTrafficPublicTempPath() + Typography.quote, "--config.managers.dbupdate.config.filepathmapping.privateFilepath=\"" + getDbuPrivateTempPath() + Typography.quote, "--config.managers.dbupdate.config.filepathmapping.publicFilepath=\"" + getDbuPublicTempPath() + Typography.quote, "--config.managers.resourceUpdate.config.filepathmapping.privateFilepath=\"" + getResourceUpdatePrivateTempPath() + Typography.quote, "--config.managers.resourceUpdate.config.filepathmapping.publicFilepath=\"" + getResourceUpdatePublicTempPath() + Typography.quote, "--config.managers.uplinkdata.config.privateFilepath=\"" + getUplinkdataPrivatePath() + Typography.quote, "--config.managers.uplinkdata.config.publicFilepath=\"" + getUplinkdataPubicPath() + Typography.quote);
        try {
            File externalMotsBlConfigFile = ConstantsKt.getExternalMotsBlConfigFile();
            if (externalMotsBlConfigFile.exists() && externalMotsBlConfigFile.isFile()) {
                Reader inputStreamReader = new InputStreamReader(new FileInputStream(externalMotsBlConfigFile), Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String text = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    Log.d(ServiceBase.INSTANCE.getTAG(), "externalConfigFile config == " + text);
                    try {
                        listMutableListOf.add("--config.logger.level=\"" + new JSONObject(text).getJSONObject("logger").getString("level") + Typography.quote);
                        listMutableListOf.add("--config.logger.backends.dlt.enabled=true");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(ServiceBase.INSTANCE.getTAG(), "config --> json error!");
                    }
                } finally {
                }
            } else {
                ServiceBase.INSTANCE.getLogger().info("external config file not found: " + externalMotsBlConfigFile);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Log.i(ServiceBase.INSTANCE.getTAG(), "args == " + listMutableListOf);
        ServiceBase.INSTANCE.getLogger().debug("arguments: " + listMutableListOf);
        Telematics telematics2 = this.mTelematics;
        if (telematics2 != null) {
            if (telematics2.start(listMutableListOf)) {
                return true;
            }
            ServiceBase.INSTANCE.getLogger().error("failed to start Telematics");
        }
        return false;
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected synchronized void onStart() {
    }

    private final void startCL() throws Throwable {
        if (this.mTelematics == null) {
            this.mTelematics = new Telematics(this);
        }
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.INITIALIZED) {
            updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.STARTING, null, 2, null));
            initPrivateTempDir();
            File privateCacheDir = getPrivateCacheDir();
            if (!privateCacheDir.isDirectory()) {
                privateCacheDir.mkdirs();
            }
            if (startTelematics()) {
                updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.RUNNING, null, 2, null));
                Tool.getSelfMaps("TELEMATICS");
            } else {
                ServiceBase.INSTANCE.getLogger().error("failed to start native applications; now we're stuck in undefined state :(");
                updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.ERROR, null, 2, null));
            }
        }
    }
}
