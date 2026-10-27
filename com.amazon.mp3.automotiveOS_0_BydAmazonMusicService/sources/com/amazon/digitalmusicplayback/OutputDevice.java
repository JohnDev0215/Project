package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class OutputDevice {
    final String displayName;
    final String id;

    public OutputDevice(String str, String str2) {
        this.displayName = str;
        this.id = str2;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OutputDevice)) {
            return false;
        }
        OutputDevice outputDevice = (OutputDevice) obj;
        return this.displayName.equals(outputDevice.displayName) && this.id.equals(outputDevice.id);
    }

    public int hashCode() {
        return ((527 + this.displayName.hashCode()) * 31) + this.id.hashCode();
    }

    public String toString() {
        return "OutputDevice{displayName=" + this.displayName + ",id=" + this.id + "}";
    }
}
