package org.chromium.content.browser;

import android.speech.tts.TextToSpeech;
import defpackage.C1630uz;
import defpackage.Is;
import defpackage.Nz;
import defpackage.Pz;
import defpackage.Qz;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TtsPlatformImpl {
    public long a;
    public List d;
    public String e;
    public Pz f;
    public boolean c = false;
    public final TextToSpeech b = new TextToSpeech(Is.a, new TextToSpeech.OnInitListener(this) { // from class: Iz
        public final TtsPlatformImpl a;

        {
            this.a = this;
        }

        @Override // android.speech.tts.TextToSpeech.OnInitListener
        public void onInit(int i) {
            final TtsPlatformImpl ttsPlatformImpl = this.a;
            Objects.requireNonNull(ttsPlatformImpl);
            if (i == 0) {
                PostTask.b(AbstractC0538bE.a, new Runnable(ttsPlatformImpl) { // from class: Mz
                    public final TtsPlatformImpl s;

                    {
                        this.s = ttsPlatformImpl;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        TtsPlatformImpl ttsPlatformImpl2 = this.s;
                        Objects.requireNonNull(ttsPlatformImpl2);
                        TraceEvent.x("TtsPlatformImpl:initialize", null);
                        new Oz(ttsPlatformImpl2).d(Su.f);
                    }
                });
            }
        }
    });

    public TtsPlatformImpl(long j) {
        this.a = j;
        b();
        ApplicationStatus.d.f(this);
    }

    public static TtsPlatformImpl create(long j) {
        return new C1630uz(j);
    }

    private void destroy() {
        ApplicationStatus.d.g(this);
        synchronized (ApplicationStatus.a) {
            Iterator it = ApplicationStatus.a.values().iterator();
            if (it.hasNext()) {
                throw null;
            }
        }
        this.a = 0L;
    }

    private int getVoiceCount() {
        return this.d.size();
    }

    private String getVoiceLanguage(int i) {
        return ((Qz) this.d.get(i)).b;
    }

    private String getVoiceName(int i) {
        return ((Qz) this.d.get(i)).a;
    }

    private boolean isInitialized() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean speak(int i, String str, String str2, float f, float f2, float f3) {
        if (!ApplicationStatus.hasVisibleActivities()) {
            return false;
        }
        if (!this.c) {
            this.f = new Pz(this, i, str, str2, f, f2, f3, null);
            return true;
        }
        if (this.f != null) {
            this.f = null;
        }
        if (!str2.equals(this.e)) {
            this.b.setLanguage(new Locale(str2));
            this.e = str2;
        }
        this.b.setSpeechRate(f);
        this.b.setPitch(f2);
        return c(str, f3, i) == 0;
    }

    private void stop() {
        if (this.c) {
            this.b.stop();
        }
        if (this.f != null) {
            this.f = null;
        }
    }

    public void b() {
        this.b.setOnUtteranceProgressListener(new Nz(this));
    }

    public int c(String str, float f, int i) {
        HashMap<String, String> map = new HashMap<>();
        double d = f;
        if (d != 1.0d) {
            map.put("volume", Double.toString(d));
        }
        map.put("utteranceId", Integer.toString(i));
        return this.b.speak(str, 0, map);
    }
}
