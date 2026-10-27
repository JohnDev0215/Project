package com.vivid.spotify;

import android.content.Context;

/* JADX INFO: compiled from: Hilt_MainActivity.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c extends com.vivid.spotify.common.base.a {
    private boolean B = false;

    /* JADX INFO: compiled from: Hilt_MainActivity.java */
    class a implements c.b {
        a() {
        }

        @Override // c.b
        public void a(Context context) {
            c.this.R();
        }
    }

    c() {
        O();
    }

    private void O() {
        o(new a());
    }

    @Override // com.vivid.spotify.common.base.j
    protected void R() {
        if (this.B) {
            return;
        }
        this.B = true;
        ((h) ((o4.c) o4.e.a(this)).f()).b((MainActivity) o4.e.a(this));
    }
}
