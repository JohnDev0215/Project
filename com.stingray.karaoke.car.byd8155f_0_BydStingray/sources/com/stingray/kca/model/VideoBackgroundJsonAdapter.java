package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.M;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/VideoBackgroundJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/VideoBackground;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VideoBackgroundJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13234a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13235b;
    public final q c;
    public volatile Constructor d;

    public VideoBackgroundJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13234a = C1489n1.z("id", "videoBackgroundFiles", "textColor", "wipeColor");
        w wVar = w.l;
        this.f13235b = h10.c(String.class, wVar, "id");
        this.c = h10.c(M.g(List.class, VideoBackgroundResource.class), wVar, "videoBackgroundFiles");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        List list = null;
        String str2 = null;
        String str3 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13234a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13235b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                list = (List) this.c.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                str2 = (String) this.f13235b.a(uVar);
                i7 &= -5;
            } else if (iF == 3) {
                str3 = (String) this.f13235b.a(uVar);
                i7 &= -9;
            }
        }
        uVar.j();
        if (i7 == -16) {
            return new VideoBackground(str, list, str2, str3);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = VideoBackground.class.getDeclaredConstructor(String.class, List.class, String.class, String.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, list, str2, str3, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (VideoBackground) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        VideoBackground videoBackground = (VideoBackground) obj;
        m.f(xVar, "writer");
        if (videoBackground == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("id");
        String str = videoBackground.f13232a;
        q qVar = this.f13235b;
        qVar.d(xVar, str);
        xVar.k("videoBackgroundFiles");
        this.c.d(xVar, videoBackground.f13233b);
        xVar.k("textColor");
        qVar.d(xVar, videoBackground.c);
        xVar.k("wipeColor");
        qVar.d(xVar, videoBackground.d);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(37, "GeneratedJsonAdapter(VideoBackground)");
    }
}
