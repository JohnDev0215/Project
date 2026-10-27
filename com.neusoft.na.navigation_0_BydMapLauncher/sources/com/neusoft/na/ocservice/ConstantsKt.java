package com.neusoft.na.ocservice;

import android.os.Environment;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: compiled from: Constants.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u0006\u0010\u0007\u001a\u00020\u0001¨\u0006\b"}, d2 = {"getExternalCloudClientConfigFile", "Ljava/io/File;", "getExternalDBUIni", "getExternalDltConfigFile", "getExternalMotsBlConfigFile", "getExternalOnecoreIniFile", "getExternalPosIni", "getExternalSensIniFile", "ocservice_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ConstantsKt {
    public static final File getExternalOnecoreIniFile() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/na_onecore.ini");
    }

    public static final File getExternalMotsBlConfigFile() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/mots_runtime_config.json");
    }

    public static final File getExternalSensIniFile() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/na_sens.ini");
    }

    public static final File getExternalDBUIni() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/na_dbu.ini");
    }

    public static final File getExternalPosIni() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/na_pos.ini");
    }

    public static final File getExternalCloudClientConfigFile() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/na_onecloudclient.json");
    }

    public static final File getExternalDltConfigFile() {
        return new File(Environment.getExternalStorageDirectory(), "naviexternconfig/mots_dlt_config.json");
    }
}
