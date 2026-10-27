package com.neusoft.dlt;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class Configuration {
    private Context context;
    private SharedPreferences sharedPrefs;

    public String getFilePath() {
        return "/sdcard/navilog/";
    }

    public Configuration(Context context) {
        this.context = context;
        this.sharedPrefs = context.getSharedPreferences("Configuration", 0);
    }

    private int getInt(int i, int i2) {
        String string = this.sharedPrefs.getString(this.context.getString(i), null);
        return string != null ? Integer.parseInt(string) : i2;
    }

    private boolean getBoolean(int i, boolean z) {
        return this.sharedPrefs.getBoolean(this.context.getString(i), z);
    }

    private String getString(int i, String str) {
        return this.sharedPrefs.getString(this.context.getString(i), str);
    }

    private String getStringOrNull(int i) {
        return this.sharedPrefs.getString(this.context.getString(i), null);
    }

    public int getLoggerPort() {
        return getInt(R.string.pref_key_logger_port, this.context.getResources().getInteger(R.integer.const_logger_port_default));
    }

    public int getLoggerWsPort() {
        return getInt(R.string.pref_key_logger_ws_port, this.context.getResources().getInteger(R.integer.const_logger_ws_port_default));
    }

    public boolean getLogSinkTcpIpEnabled() {
        return getBoolean(R.string.pref_key_logsink_tcpip_enable, true);
    }

    public int getLogSinkTcpIpPort() {
        return getInt(R.string.pref_key_logsink_tcpip_port, this.context.getResources().getInteger(R.integer.const_logsink_tcpip_port_default));
    }

    public boolean getLogSinkFileEnabled() {
        return getBoolean(R.string.pref_key_logsink_file_enable, true);
    }

    public String getLogSinkFileEcu() {
        return getString(R.string.pref_key_logsink_file_ecu, this.context.getString(R.string.const_logsink_file_ecu_default));
    }

    public String getLogSinkFilePath() {
        return getString(R.string.pref_key_logsink_file_path, getFilePath());
    }

    public void setLogSinkFilePath(String str) {
        SharedPreferences.Editor editorEdit = this.sharedPrefs.edit();
        editorEdit.putString(this.context.getString(R.string.pref_key_logsink_file_path), str);
        editorEdit.apply();
    }

    public String getLogSinkFilePattern() {
        return this.context.getString(R.string.const_logsink_file_pattern_default);
    }

    public int getLogSinkFileMaxFileLen() {
        return getInt(R.string.pref_key_logsink_file_maxFileLen, this.context.getResources().getInteger(R.integer.const_logsink_file_maxFileLen_defaut));
    }

    public boolean getStartAtBoot() {
        return getBoolean(R.string.pref_key_server_startAtBoot, false);
    }

    public Error check() {
        return Error.OK;
    }
}
