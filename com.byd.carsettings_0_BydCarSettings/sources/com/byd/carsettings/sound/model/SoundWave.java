package com.byd.carsettings.sound.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class SoundWave implements Serializable {
    private boolean checked;
    private String name;

    public SoundWave() {
    }

    public String getName() {
        return this.name;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "SoundWave{name='" + this.name + "', checked=" + this.checked + '}';
    }

    public SoundWave(String str, boolean z) {
        this.name = str;
        this.checked = z;
    }
}
