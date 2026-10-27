package org.chromium.content.browser;

import android.speech.tts.TextToSpeech;
import defpackage.lg3;
import defpackage.xz;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: loaded from: classes3.dex */
public class TtsPlatformImpl {
    public long a;
    public final b b;
    public final HashMap c;

    public static class a {
        public final TtsPlatformImpl a;
        public final int b;
        public final String c;
        public final String d;
        public final String e;
        public final float f;
        public final float g;
        public final float h;

        public a(TtsPlatformImpl ttsPlatformImpl, int i, String str, String str2, String str3, float f, float f2, float f3) {
            this.a = ttsPlatformImpl;
            this.b = i;
            this.c = str;
            this.d = str2;
            this.f = f;
            this.g = f2;
            this.h = f3;
            this.e = str3;
        }
    }

    public static class c {
        public final String a;
        public final String b;

        public c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    public TtsPlatformImpl(long j) {
        this.a = j;
        b bVar = new b(j);
        this.b = bVar;
        this.c = new HashMap();
        bVar.a.setOnUtteranceProgressListener(new lg3(this));
    }

    @CalledByNative
    public static TtsPlatformImpl create(long j) {
        return new TtsPlatformImpl(j);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public final int getVoiceCount() {
        return this.b.b.size();
    }

    @CalledByNative
    public final String getVoiceLanguage(int i) {
        return this.b.b.get(i).b;
    }

    @CalledByNative
    public final String getVoiceName(int i) {
        return this.b.b.get(i).a;
    }

    @CalledByNative
    public final boolean isInitialized() {
        return this.b.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean speak(int r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, float r17, float r18, float r19) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.TtsPlatformImpl.speak(int, java.lang.String, java.lang.String, java.lang.String, float, float, float):boolean");
    }

    @CalledByNative
    public final void stop() {
        b bVar = this.b;
        if (bVar.c) {
            bVar.a.stop();
        }
        if (bVar.e != null) {
            bVar.e = null;
        }
        Iterator it = this.c.entrySet().iterator();
        while (it.hasNext()) {
            b bVar2 = (b) ((Map.Entry) it.next()).getValue();
            if (bVar2.c) {
                bVar2.a.stop();
            }
            if (bVar2.e != null) {
                bVar2.e = null;
            }
        }
    }

    public static class b {
        public final TextToSpeech a;
        public List<c> b;
        public boolean c;
        public String d;
        public a e;
        public final long f;

        public b(long j) {
            this.f = j;
            this.c = false;
            this.a = new TextToSpeech(xz.a, new TextToSpeech.OnInitListener() { // from class: mg3
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public final void onInit(int i) {
                    TtsPlatformImpl.b bVar = this.a;
                    bVar.getClass();
                    if (i == 0) {
                        PostTask.d(rh3.a, new i22(9, bVar));
                    }
                }
            });
        }

        public b(String str) {
            this.c = false;
            this.a = new TextToSpeech(xz.a, new TextToSpeech.OnInitListener() { // from class: ng3
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public final void onInit(int i) {
                    TtsPlatformImpl.b bVar = this.a;
                    if (i != 0) {
                        bVar.getClass();
                        return;
                    }
                    bVar.c = true;
                    TtsPlatformImpl.a aVar = bVar.e;
                    if (aVar != null) {
                        aVar.a.speak(aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.h);
                    }
                }
            }, str);
        }
    }
}
