package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/VideoResponseJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/VideoResponse;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VideoResponseJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13248b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Constructor f13249e;

    public VideoResponseJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13247a = C1489n1.z("uri", "expirationMilliSec", "durationSec", "format", "hasLeadVocals", "background", "videoCodec", "isSample");
        w wVar = w.l;
        this.f13248b = h10.c(String.class, wVar, "uri");
        this.c = h10.c(Integer.class, wVar, "expirationMilliSec");
        this.d = h10.c(Boolean.class, wVar, "hasLeadVocals");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        int i7 = -1;
        Integer num = null;
        Integer num2 = null;
        String str2 = null;
        Boolean bool = null;
        String str3 = null;
        String str4 = null;
        Boolean bool2 = null;
        while (uVar.m()) {
            switch (uVar.F(this.f13247a)) {
                case -1:
                    uVar.I();
                    uVar.K();
                    break;
                case 0:
                    str = (String) this.f13248b.a(uVar);
                    i7 &= -2;
                    break;
                case 1:
                    num = (Integer) this.c.a(uVar);
                    i7 &= -3;
                    break;
                case 2:
                    num2 = (Integer) this.c.a(uVar);
                    i7 &= -5;
                    break;
                case 3:
                    str2 = (String) this.f13248b.a(uVar);
                    i7 &= -9;
                    break;
                case 4:
                    bool = (Boolean) this.d.a(uVar);
                    i7 &= -17;
                    break;
                case 5:
                    str3 = (String) this.f13248b.a(uVar);
                    i7 &= -33;
                    break;
                case 6:
                    str4 = (String) this.f13248b.a(uVar);
                    i7 &= -65;
                    break;
                case 7:
                    bool2 = (Boolean) this.d.a(uVar);
                    i7 &= -129;
                    break;
            }
        }
        uVar.j();
        if (i7 == -256) {
            return new VideoResponse(str, num, num2, str2, bool, str3, str4, bool2);
        }
        Constructor declaredConstructor = this.f13249e;
        if (declaredConstructor == null) {
            declaredConstructor = VideoResponse.class.getDeclaredConstructor(String.class, Integer.class, Integer.class, String.class, Boolean.class, String.class, String.class, Boolean.class, Integer.TYPE, f.c);
            this.f13249e = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, num, num2, str2, bool, str3, str4, bool2, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (VideoResponse) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        VideoResponse videoResponse = (VideoResponse) obj;
        m.f(xVar, "writer");
        if (videoResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("uri");
        String str = videoResponse.f13241a;
        q qVar = this.f13248b;
        qVar.d(xVar, str);
        xVar.k("expirationMilliSec");
        Integer num = videoResponse.f13242b;
        q qVar2 = this.c;
        qVar2.d(xVar, num);
        xVar.k("durationSec");
        qVar2.d(xVar, videoResponse.c);
        xVar.k("format");
        qVar.d(xVar, videoResponse.d);
        xVar.k("hasLeadVocals");
        Boolean bool = videoResponse.f13243e;
        q qVar3 = this.d;
        qVar3.d(xVar, bool);
        xVar.k("background");
        qVar.d(xVar, videoResponse.f13244f);
        xVar.k("videoCodec");
        qVar.d(xVar, videoResponse.f13245g);
        xVar.k("isSample");
        qVar3.d(xVar, videoResponse.f13246h);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(35, "GeneratedJsonAdapter(VideoResponse)");
    }
}
