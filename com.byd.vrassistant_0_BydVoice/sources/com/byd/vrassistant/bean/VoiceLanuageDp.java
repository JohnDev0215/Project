package com.byd.vrassistant.bean;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class VoiceLanuageDp {
    String name;
    String path;
    String version = "";
    int used = 0;
    int state = -1;
    int type = -1;

    public VoiceLanuageDp(String str, String str2) {
        this.path = str2;
        this.name = str;
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

    public void copy(VoiceLanuageDp voiceLanuageDp) {
        this.type = voiceLanuageDp.getType();
        this.state = voiceLanuageDp.getState();
        this.version = voiceLanuageDp.getVersion();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VoiceLanuageDp)) {
            return false;
        }
        VoiceLanuageDp voiceLanuageDp = (VoiceLanuageDp) obj;
        return getType() == voiceLanuageDp.getType() && getName().equals(voiceLanuageDp.getName()) && getPath().equals(voiceLanuageDp.getPath()) && getVersion().equals(voiceLanuageDp.getVersion());
    }

    public int hashCode() {
        return Objects.hash(getName(), getPath(), getVersion(), Integer.valueOf(getType()));
    }

    public String toString() {
        return "VoiceLanuageDp{name='" + this.name + "', path='" + this.path + "', version='" + this.version + "', used=" + this.used + ", state=" + this.state + ", type=" + this.type + '}';
    }
}
