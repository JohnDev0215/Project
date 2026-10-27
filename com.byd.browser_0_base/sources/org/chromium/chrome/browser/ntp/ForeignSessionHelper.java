package org.chromium.chrome.browser.ntp;

import J.N;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class ForeignSessionHelper {
    public long a;

    public static class ForeignSession {
        public final String a;
        public final String b;
        public final long c;
        public final ArrayList d = new ArrayList();

        public ForeignSession(String str, String str2, long j) {
            this.a = str;
            this.b = str2;
            this.c = j;
        }
    }

    public interface ForeignSessionCallback {
        @CalledByNative
        void onUpdated();
    }

    public static class ForeignSessionWindow {
        public final ArrayList a = new ArrayList();
    }

    public static class a {
        public final GURL a;
        public final String b;
        public final int c;

        public a(GURL gurl, String str, int i) {
            this.a = gurl;
            this.b = str;
            this.c = i;
        }
    }

    public ForeignSessionHelper(Profile profile) {
        this.a = N.MpwfXr6h(profile);
    }

    @CalledByNative
    public static ForeignSession pushSession(List<ForeignSession> list, String str, String str2, long j) {
        ForeignSession foreignSession = new ForeignSession(str, str2, j);
        list.add(foreignSession);
        return foreignSession;
    }

    @CalledByNative
    public static void pushTab(ForeignSessionWindow foreignSessionWindow, GURL gurl, String str, long j, int i) {
        foreignSessionWindow.a.add(new a(gurl, str, i));
    }

    @CalledByNative
    public static ForeignSessionWindow pushWindow(ForeignSession foreignSession, long j, int i) {
        ForeignSessionWindow foreignSessionWindow = new ForeignSessionWindow();
        foreignSession.d.add(foreignSessionWindow);
        return foreignSessionWindow;
    }
}
