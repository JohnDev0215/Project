package com.byd.displaymode.present;

/* JADX INFO: loaded from: classes.dex */
public interface IStateChangeCallback {

    public enum Pos {
        SEAT_WELCOME,
        STEEL_WELCOME,
        SMART_LIGHT_WELCOME,
        REAR_MIRROR_FLIP,
        REAR_MIRROR_FOLLOW
    }

    boolean a();

    void b(int i8);

    void c(int i8);
}
