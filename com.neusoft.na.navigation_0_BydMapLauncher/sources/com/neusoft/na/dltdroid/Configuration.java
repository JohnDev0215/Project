package com.neusoft.na.dltdroid;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Configuration.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010+\u001a\u00020,J\u0018\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\rH\u0002J\u0006\u00100\u001a\u00020\bJ\u0018\u00101\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u0011H\u0002J\u0018\u00102\u001a\u00020\b2\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\bH\u0002J\u0012\u00103\u001a\u0004\u0018\u00010\b2\u0006\u0010.\u001a\u00020\u0011H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R(\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000bR\u0011\u0010\u001b\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000fR\u0011\u0010\u001d\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\u001f\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u0011\u0010!\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0013R\u0011\u0010#\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b$\u0010\u0013R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b*\u0010\u000f¨\u00064"}, d2 = {"Lcom/neusoft/na/dltdroid/Configuration;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "defaultPath", "", "logSinkFileEcu", "getLogSinkFileEcu", "()Ljava/lang/String;", "logSinkFileEnabled", "", "getLogSinkFileEnabled", "()Z", "logSinkFileMaxFileLen", "", "getLogSinkFileMaxFileLen", "()I", "v", "logSinkFilePath", "getLogSinkFilePath", "setLogSinkFilePath", "(Ljava/lang/String;)V", "logSinkFilePattern", "getLogSinkFilePattern", "logSinkTcpIpEnabled", "getLogSinkTcpIpEnabled", "logSinkTcpIpPort", "getLogSinkTcpIpPort", "loggerPort", "getLoggerPort", "loggerPort4717", "getLoggerPort4717", "loggerWsPort", "getLoggerWsPort", "sharedPrefs", "Landroid/content/SharedPreferences;", "getSharedPrefs", "()Landroid/content/SharedPreferences;", "startAtBoot", "getStartAtBoot", "check", "Lcom/neusoft/na/dltdroid/Error;", "getBoolean", "id", "default", "getFilePath", "getInt", "getString", "getStringOrNull", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Configuration {
    private final Context context;
    private final String defaultPath;
    private final SharedPreferences sharedPrefs;

    public final String getFilePath() {
        return BuildConfig.DLT_PATH;
    }

    public Configuration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(context)");
        this.sharedPrefs = defaultSharedPreferences;
        this.defaultPath = "/XJ/TEST/";
    }

    public final Context getContext() {
        return this.context;
    }

    public final SharedPreferences getSharedPrefs() {
        return this.sharedPrefs;
    }

    private final int getInt(int id, int i) {
        String string = this.sharedPrefs.getString(this.context.getString(id), null);
        return string != null ? Integer.parseInt(string) : i;
    }

    private final boolean getBoolean(int id, boolean z) {
        return this.sharedPrefs.getBoolean(this.context.getString(id), z);
    }

    private final String getString(int id, String str) {
        String string = this.sharedPrefs.getString(this.context.getString(id), str);
        Intrinsics.checkNotNull(string);
        return string;
    }

    private final String getStringOrNull(int id) {
        return this.sharedPrefs.getString(this.context.getString(id), null);
    }

    public final int getLoggerPort() {
        return getInt(R.string.pref_key_logger_port, this.context.getResources().getInteger(R.integer.const_logger_port_default));
    }

    public final int getLoggerPort4717() {
        return getInt(R.string.pref_key_logger_port, this.context.getResources().getInteger(R.integer.const_logger_ws_port_4717));
    }

    public final int getLoggerWsPort() {
        return getInt(R.string.pref_key_logger_ws_port, this.context.getResources().getInteger(R.integer.const_logger_ws_port_default));
    }

    public final boolean getLogSinkTcpIpEnabled() {
        return getBoolean(R.string.pref_key_logsink_tcpip_enable, true);
    }

    public final int getLogSinkTcpIpPort() {
        return getInt(R.string.pref_key_logsink_tcpip_port, this.context.getResources().getInteger(R.integer.const_logsink_tcpip_port_default));
    }

    public final boolean getLogSinkFileEnabled() {
        return getBoolean(R.string.pref_key_logsink_file_enable, true);
    }

    public final String getLogSinkFileEcu() {
        int i = R.string.pref_key_logsink_file_ecu;
        String string = this.context.getString(R.string.const_logsink_file_ecu_default);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…logsink_file_ecu_default)");
        return getString(i, string);
    }

    public final String getLogSinkFilePath() {
        return getString(R.string.pref_key_logsink_file_path, getFilePath());
    }

    public final void setLogSinkFilePath(String str) {
        SharedPreferences.Editor editorEdit = this.sharedPrefs.edit();
        editorEdit.putString(this.context.getString(R.string.pref_key_logsink_file_path), str);
        editorEdit.apply();
    }

    public final String getLogSinkFilePattern() {
        String string = this.context.getString(R.string.const_logsink_file_pattern_default);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ink_file_pattern_default)");
        return string;
    }

    public final int getLogSinkFileMaxFileLen() {
        return getInt(R.string.pref_key_logsink_file_maxFileLen, this.context.getResources().getInteger(R.integer.const_logsink_file_maxFileLen_defaut));
    }

    public final boolean getStartAtBoot() {
        return getBoolean(R.string.pref_key_server_startAtBoot, false);
    }

    public final Error check() {
        return Error.OK;
    }
}
