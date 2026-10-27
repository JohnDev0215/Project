package defpackage;

import android.content.Context;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Dw {
    public static final long c = TimeUnit.SECONDS.toMillis(2);
    public Cw a;
    public Boolean b;

    public Dw(Context context) {
        Lt.a.b("Autofill.WebView.CreatedByActivityContext", Is.a(context) != null);
    }

    public final void a() {
        Cw cw;
        Boolean bool = this.b;
        if (bool != null && !bool.booleanValue() && (cw = this.a) != null) {
            int i = cw.b;
            int i2 = 1;
            if (i != 0) {
                if (i == 1) {
                    i2 = 5;
                } else {
                    i2 = 3;
                    if (i != 9) {
                        if (i == 17) {
                            i2 = 4;
                        } else if (i == 25) {
                            i2 = 2;
                        } else if (i == 7) {
                            i2 = 9;
                        } else if (i == 23) {
                            i2 = 8;
                        } else if (i == 31) {
                            i2 = 6;
                        } else if (i == 15) {
                            i2 = 7;
                        } else if (i == 3) {
                            i2 = 13;
                        } else if (i == 19) {
                            i2 = 12;
                        } else if (i == 27) {
                            i2 = 10;
                        } else {
                            i2 = 11;
                            if (i != 11) {
                                i2 = 0;
                            }
                        }
                    }
                }
            }
            It.d("Autofill.WebView.AutofillSession", i2, 14);
            Boolean bool2 = cw.c;
            if (bool2 != null) {
                Lt.a.b("Autofill.WebView.UserChangedAutofilledField", bool2.booleanValue());
            }
            Long l = cw.a;
            if (l != null) {
                It.c("Autofill.WebView.SuggestionTime", l.longValue(), 10L, c, 50);
            }
        }
        this.a = null;
    }
}
