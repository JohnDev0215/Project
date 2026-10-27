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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/AudioResponseJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/AudioResponse;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AudioResponseJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13079b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Constructor f13080e;

    public AudioResponseJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13078a = C1489n1.z("kml", "audioFiles", "videoBackground");
        w wVar = w.l;
        this.f13079b = h10.c(KmlResourceV2.class, wVar, "kml");
        this.c = h10.c(M.g(List.class, AudioResource.class), wVar, "audioFiles");
        this.d = h10.c(VideoBackground.class, wVar, "videoBackground");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        KmlResourceV2 kmlResourceV2 = null;
        List list = null;
        VideoBackground videoBackground = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13078a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                kmlResourceV2 = (KmlResourceV2) this.f13079b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                list = (List) this.c.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                videoBackground = (VideoBackground) this.d.a(uVar);
                i7 &= -5;
            }
        }
        uVar.j();
        if (i7 == -8) {
            return new AudioResponse(kmlResourceV2, list, videoBackground);
        }
        Constructor declaredConstructor = this.f13080e;
        if (declaredConstructor == null) {
            declaredConstructor = AudioResponse.class.getDeclaredConstructor(KmlResourceV2.class, List.class, VideoBackground.class, Integer.TYPE, f.c);
            this.f13080e = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(kmlResourceV2, list, videoBackground, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (AudioResponse) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        AudioResponse audioResponse = (AudioResponse) obj;
        m.f(xVar, "writer");
        if (audioResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("kml");
        this.f13079b.d(xVar, audioResponse.f13076a);
        xVar.k("audioFiles");
        this.c.d(xVar, audioResponse.f13077b);
        xVar.k("videoBackground");
        this.d.d(xVar, audioResponse.c);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(35, "GeneratedJsonAdapter(AudioResponse)");
    }
}
