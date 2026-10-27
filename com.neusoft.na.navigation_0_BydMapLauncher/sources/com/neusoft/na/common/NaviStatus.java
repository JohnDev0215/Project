package com.neusoft.na.common;

import android.content.Context;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;

/* JADX INFO: compiled from: NaviStatus.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u0004\u0018\u00010\rJ\b\u0010\u000e\u001a\u0004\u0018\u00010\rJ\b\u0010\u000f\u001a\u0004\u0018\u00010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/common/NaviStatus;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "DbRollbackLoagic", "", "checkDBAvailable", "", "checkDbApply", "getBackUpPath", "", "getDbPath", "getDeletePath", "Companion", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NaviStatus {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String FINISH = "finish";
    public static final String NODE = "/files/__org.liquidplayer.node__/_onecloud_client";
    public static final String ROOT_NDS = "/ROOT.NDS";
    public static final String UPDATE_FULL = "/update_full.inf";
    public static final String UPDATE_INC = "/update_inc.inf";
    private static NaviStatus instance;
    private final Context context;

    /* JADX INFO: compiled from: NaviStatus.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/neusoft/na/common/NaviStatus$Companion;", "Lmu/KLogging;", "()V", "FINISH", "", "NODE", "ROOT_NDS", "UPDATE_FULL", "UPDATE_INC", "instance", "Lcom/neusoft/na/common/NaviStatus;", "getInstance", "context", "Landroid/content/Context;", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NaviStatus getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (NaviStatus.instance == null) {
                NaviStatus.instance = new NaviStatus(context);
            }
            return NaviStatus.instance;
        }
    }

    public NaviStatus(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void DbRollbackLoagic() {
        String dbPath = getDbPath();
        String str = dbPath + ROOT_NDS;
        Tool.createDirectory(dbPath);
        Tool.grantDirAuthority(new File(new File(dbPath).getParent()));
        if (Tool.fileIsExists(str)) {
            INSTANCE.getLogger().info("DbRollbackLoagic::hasDB");
            return;
        }
        Companion companion = INSTANCE;
        companion.getLogger().info("DbRollbackLoagic::noDB");
        String str2 = getBackUpPath() + ROOT_NDS;
        companion.getLogger().info("DbRollbackLoagic::backNDSPath" + str2);
        if (Tool.fileIsExists(str2)) {
            companion.getLogger().info("DbRollbackLoagic::hasbackNDSPath");
            Tool.renameFile(getBackUpPath(), dbPath);
            return;
        }
        companion.getLogger().info("DbRollbackLoagic::nobackNDSPath");
        if (Tool.fileIsExists(getDeletePath() + ROOT_NDS)) {
            companion.getLogger().info("DbRollbackLoagic::hasdeleteNDSPath");
            Tool.renameFile(getDeletePath(), dbPath);
        }
    }

    public final boolean checkDBAvailable() throws Throwable {
        try {
            String dbPath = getDbPath();
            Companion companion = INSTANCE;
            companion.getLogger().info("dbuIniFile.absolutePath is " + dbPath);
            String str = dbPath + ROOT_NDS;
            boolean zFileIsExists = Tool.fileIsExists(str);
            companion.getLogger().info(str + " is " + zFileIsExists);
            String str2 = this.context.getFilesDir().getAbsolutePath() + "/files/__org.liquidplayer.node__/_onecloud_client/local/dbu/update_full.inf";
            companion.getLogger().info("updateFinishPath is " + str2);
            boolean zFileIsExists2 = Tool.fileIsExists(str2);
            companion.getLogger().info(str2 + " is " + zFileIsExists2);
            return zFileIsExists && !zFileIsExists2;
        } catch (Exception e) {
            e.printStackTrace();
            INSTANCE.getLogger().info("checkDBAvailable error " + e.getLocalizedMessage());
            return false;
        }
    }

    public final boolean checkDbApply() throws Throwable {
        INSTANCE.getLogger().info("checkDbApply");
        Boolean boolFileHasStr = Tool.FileHasStr(this.context.getFilesDir().getAbsolutePath() + "/files/__org.liquidplayer.node__/_onecloud_client/local/dbu/update_inc.inf", FINISH);
        Intrinsics.checkNotNullExpressionValue(boolFileHasStr, "FileHasStr(updateFinishPath, FINISH)");
        return boolFileHasStr.booleanValue();
    }

    public final String getDbPath() throws Throwable {
        InputStream inputStreamOpen = this.context.getAssets().open(FileUtils.getInstance(this.context).getDbuIniPath());
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(File…ance(context).dbuIniPath)");
        Map<String, Object> ini = Tool.readIni(inputStreamOpen);
        Intrinsics.checkNotNullExpressionValue(ini, "readIni(inputStream)");
        Map map = (Map) ini.get("nds");
        Intrinsics.checkNotNull(map);
        return Tool.getFileDir((String) map.get("database"));
    }

    public final String getBackUpPath() throws Throwable {
        InputStream inputStreamOpen = this.context.getAssets().open(FileUtils.getInstance(this.context).getDbuIniPath());
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(File…ance(context).dbuIniPath)");
        Map<String, Object> ini = Tool.readIni(inputStreamOpen);
        Intrinsics.checkNotNullExpressionValue(ini, "readIni(inputStream)");
        Map map = (Map) ini.get("dbupdate");
        Intrinsics.checkNotNull(map);
        return (String) map.get("temp_install_dir");
    }

    public final String getDeletePath() throws Throwable {
        InputStream inputStreamOpen = this.context.getAssets().open(FileUtils.getInstance(this.context).getDbuIniPath());
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(File…ance(context).dbuIniPath)");
        Map<String, Object> ini = Tool.readIni(inputStreamOpen);
        Intrinsics.checkNotNullExpressionValue(ini, "readIni(inputStream)");
        Map map = (Map) ini.get("dbupdate");
        Intrinsics.checkNotNull(map);
        return (String) map.get("temp_delete_dir");
    }
}
