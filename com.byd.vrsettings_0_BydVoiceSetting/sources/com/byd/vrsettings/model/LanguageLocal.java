package com.byd.vrsettings.model;

import android.text.TextUtils;
import com.chad.library.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class LanguageLocal {
    String name;
    String path;
    int state;
    int type;
    int used;
    String version;

    public LanguageLocal(String str, String str2) {
        this.version = BuildConfig.FLAVOR;
        this.used = 0;
        this.state = -1;
        this.type = -1;
        this.path = str2;
        this.name = str;
    }

    public LanguageLocal() {
        this.name = BuildConfig.FLAVOR;
        this.path = BuildConfig.FLAVOR;
        this.version = BuildConfig.FLAVOR;
        this.used = 0;
        this.state = -1;
        this.type = -1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getUsed() {
        return this.used;
    }

    public void setUsed(int i) {
        this.used = i;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String convertName() {
        if (!TextUtils.isEmpty(this.name) && this.name.contains("data_")) {
            return this.name.replace("data_", BuildConfig.FLAVOR);
        }
        return this.name;
    }

    public String toString() {
        return "LanguageLocal{name='" + this.name + "', path='" + this.path + "', version='" + this.version + "', used=" + this.used + ", state=" + this.state + ", type=" + this.type + '}';
    }
}
