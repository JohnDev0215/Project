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
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/RecordingItemJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/RecordingItem;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RecordingItemJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13197b;
    public volatile Constructor c;

    public RecordingItemJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13196a = C1489n1.z("fileName", "uploadingLink", "sharingLink");
        this.f13197b = h10.c(String.class, w.l, "fileName");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        String str2 = null;
        String str3 = null;
        int i7 = -1;
        while (uVar.m()) {
            int iF = uVar.F(this.f13196a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0) {
                str = (String) this.f13197b.a(uVar);
                i7 &= -2;
            } else if (iF == 1) {
                str2 = (String) this.f13197b.a(uVar);
                i7 &= -3;
            } else if (iF == 2) {
                str3 = (String) this.f13197b.a(uVar);
                i7 &= -5;
            }
        }
        uVar.j();
        if (i7 == -8) {
            return new RecordingItem(str, str2, str3);
        }
        Constructor declaredConstructor = this.c;
        if (declaredConstructor == null) {
            declaredConstructor = RecordingItem.class.getDeclaredConstructor(String.class, String.class, String.class, Integer.TYPE, f.c);
            this.c = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (RecordingItem) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        RecordingItem recordingItem = (RecordingItem) obj;
        m.f(xVar, "writer");
        if (recordingItem == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("fileName");
        String str = recordingItem.f13194a;
        q qVar = this.f13197b;
        qVar.d(xVar, str);
        xVar.k("uploadingLink");
        qVar.d(xVar, recordingItem.f13195b);
        xVar.k("sharingLink");
        qVar.d(xVar, recordingItem.c);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(35, "GeneratedJsonAdapter(RecordingItem)");
    }
}
