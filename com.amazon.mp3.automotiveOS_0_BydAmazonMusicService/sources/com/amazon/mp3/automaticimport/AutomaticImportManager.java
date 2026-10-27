package com.amazon.mp3.automaticimport;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.preference.PreferenceManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil;
import com.amazon.mp3.util.Log;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutomaticImportManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0018\u0010\u0019J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0002J\u0006\u0010\u0007\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0002J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001f\u0010\u0014\u001a\n \u0013*\u0004\u0018\u00010\u00120\u00128\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/automaticimport/AutomaticImportManager;", "", "", "showAutomaticImportFtu", "", "automaticImportFtuCompleted", "isAutomaticImportEnabled", "wasRemoveImportContentSuccessful", Constants.ENABLE_DISABLE, "setAutomaticImportPersistedState", "Landroid/os/Handler;", "handler", "removeImportedContent", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomaticImportManager {
    private static final String TAG = AutomaticImportManager.class.getSimpleName();
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public AutomaticImportManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public final boolean showAutomaticImportFtu() {
        return this.sharedPreferences.getBoolean("automatic_import_ftu", true);
    }

    public final void automaticImportFtuCompleted() {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putBoolean("automatic_import_ftu", false);
        editorEdit.apply();
    }

    public final boolean isAutomaticImportEnabled() {
        return this.sharedPreferences.getBoolean(this.context.getString(R.string.setting_import), true);
    }

    public final boolean wasRemoveImportContentSuccessful() {
        return this.sharedPreferences.getBoolean("remove_operation_successfully_completed", false);
    }

    public final void setAutomaticImportPersistedState(boolean isEnabled) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putBoolean(this.context.getString(R.string.setting_import), isEnabled);
        editorEdit.apply();
    }

    public final void removeImportedContent(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        Runnable runnable = new Runnable() { // from class: com.amazon.mp3.automaticimport.AutomaticImportManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AutomaticImportManager.m409removeImportedContent$lambda0(this.f$0);
            }
        };
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putBoolean("remove_operation_successfully_completed", false);
        editorEdit.apply();
        handler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeImportedContent$lambda-0, reason: not valid java name */
    public static final void m409removeImportedContent$lambda0(AutomaticImportManager this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this$0.context);
        SharedPreferences.Editor editorEdit = this$0.sharedPreferences.edit();
        if (RemoveImportedContentOperation.INSTANCE.run(new LocalTrackUtil(writableDatabase), new TrackDataUtil(writableDatabase))) {
            Log.info(TAG, "Imported content successfully removed from My Music");
            editorEdit.putBoolean("remove_operation_successfully_completed", true);
        } else {
            Log.info(TAG, "Remove Imported Content Operation not completed successfully");
            editorEdit.putBoolean("remove_operation_successfully_completed", false);
        }
        editorEdit.apply();
    }
}
