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
import java.util.Map;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/ImagesJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/Images;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ImagesJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13101a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13102b;
    public final q c;
    public volatile Constructor d;

    public ImagesJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13101a = C1489n1.z("defaultType", "imageByType");
        w wVar = w.l;
        this.f13102b = h10.c(ImageType.class, wVar, "defaultType");
        this.c = h10.c(M.g(Map.class, String.class, String.class), wVar, "imageByType");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        ImageType imageType = null;
        Map map = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13101a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                imageType = (ImageType) this.f13102b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                map = (Map) this.c.a(uVar);
                i7 &= -3;
            }
        }
        uVar.j();
        if (i7 == -4) {
            return new Images(imageType, map);
        }
        Constructor declaredConstructor = this.d;
        if (declaredConstructor == null) {
            declaredConstructor = Images.class.getDeclaredConstructor(ImageType.class, Map.class, Integer.TYPE, f.c);
            this.d = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(imageType, map, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (Images) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        Images images = (Images) obj;
        m.f(xVar, "writer");
        if (images == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("defaultType");
        this.f13102b.d(xVar, images.f13099a);
        xVar.k("imageByType");
        this.c.d(xVar, images.f13100b);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(28, "GeneratedJsonAdapter(Images)");
    }
}
