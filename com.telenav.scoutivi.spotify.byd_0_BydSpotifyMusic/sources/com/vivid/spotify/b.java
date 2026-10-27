package com.vivid.spotify;

import android.app.Application;

/* JADX INFO: compiled from: CommandManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final androidx.lifecycle.t<EnumC0056b> f5203a = new androidx.lifecycle.t<>(EnumC0056b.COMMAND_NONE);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final androidx.lifecycle.t<a> f5204b = new androidx.lifecycle.t<>(a.COMMAND_NONE);

    /* JADX INFO: compiled from: CommandManager.java */
    public enum a {
        COMMAND_NONE(0),
        COMMAND_OPEN(1),
        COMMAND_CLOSE(2);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f5206e;

        a(int i7) {
            this.f5206e = i7;
        }

        public int getValue() {
            return this.f5206e;
        }
    }

    /* JADX INFO: renamed from: com.vivid.spotify.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CommandManager.java */
    public enum EnumC0056b {
        COMMAND_NONE(0),
        COMMAND_OPEN(1),
        COMMAND_CLOSE(2);


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f5208e;

        EnumC0056b(int i7) {
            this.f5208e = i7;
        }

        public int getValue() {
            return this.f5208e;
        }
    }

    public b(Application application) {
    }

    public void a(androidx.lifecycle.o oVar, androidx.lifecycle.u uVar) {
        this.f5204b.f(oVar, uVar);
    }

    public void b(androidx.lifecycle.o oVar, androidx.lifecycle.u uVar) {
        this.f5203a.f(oVar, uVar);
    }

    public void c(a aVar) {
        this.f5204b.j(aVar);
    }

    public void d(EnumC0056b enumC0056b) {
        this.f5203a.j(enumC0056b);
    }
}
