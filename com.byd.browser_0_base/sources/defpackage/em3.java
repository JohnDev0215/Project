package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* JADX INFO: compiled from: VideoTutorialService.java */
/* JADX INFO: loaded from: classes2.dex */
public interface em3 {
    List<String> a(int i);

    void b(Callback callback, int i);

    String c();

    void d(Callback<List<Tutorial>> callback);

    void e(String str);
}
