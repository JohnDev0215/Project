package i0;

import android.content.ClipData;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: compiled from: ContentInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ClipData f6833a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f6834b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f6835c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Uri f6836d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Bundle f6837e;

    /* JADX INFO: compiled from: ContentInfoCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        ClipData f6838a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f6839b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f6840c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Uri f6841d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Bundle f6842e;

        public a(ClipData clipData, int i7) {
            this.f6838a = clipData;
            this.f6839b = i7;
        }

        public c a() {
            return new c(this);
        }

        public a b(Bundle bundle) {
            this.f6842e = bundle;
            return this;
        }

        public a c(int i7) {
            this.f6840c = i7;
            return this;
        }

        public a d(Uri uri) {
            this.f6841d = uri;
            return this;
        }
    }

    c(a aVar) {
        this.f6833a = (ClipData) h0.h.f(aVar.f6838a);
        this.f6834b = h0.h.c(aVar.f6839b, 0, 3, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.f6835c = h0.h.e(aVar.f6840c, 1);
        this.f6836d = aVar.f6841d;
        this.f6837e = aVar.f6842e;
    }

    static String a(int i7) {
        return (i7 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i7);
    }

    static String e(int i7) {
        return i7 != 0 ? i7 != 1 ? i7 != 2 ? i7 != 3 ? String.valueOf(i7) : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP";
    }

    public ClipData b() {
        return this.f6833a;
    }

    public int c() {
        return this.f6835c;
    }

    public int d() {
        return this.f6834b;
    }

    public String toString() {
        return "ContentInfoCompat{clip=" + this.f6833a + ", source=" + e(this.f6834b) + ", flags=" + a(this.f6835c) + ", linkUri=" + this.f6836d + ", extras=" + this.f6837e + "}";
    }
}
