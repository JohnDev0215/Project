package defpackage;

import J.N;
import android.view.KeyEvent;
import java.util.Objects;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CB implements Runnable {
    public final /* synthetic */ KeyEvent s;
    public final /* synthetic */ QB t;

    public CB(QB qb, KeyEvent keyEvent) {
        this.t = qb;
        this.s = keyEvent;
    }

    @Override // java.lang.Runnable
    public void run() {
        QB qb = this.t;
        KeyEvent keyEvent = this.s;
        Objects.requireNonNull(qb);
        int action = keyEvent.getAction();
        int unicodeChar = keyEvent.getUnicodeChar();
        boolean z = false;
        if (action == 0) {
            if (keyEvent.getKeyCode() == 67) {
                qb.i = 0;
            } else {
                if ((Integer.MIN_VALUE & unicodeChar) != 0) {
                    int i = Integer.MAX_VALUE & unicodeChar;
                    int i2 = qb.i;
                    if (i2 == 0) {
                        qb.i = i;
                    } else if (i == i2) {
                        qb.d(i2, 0);
                    } else {
                        qb.d(i2, i);
                    }
                } else {
                    int i3 = qb.i;
                    if (i3 != 0 && unicodeChar != 0) {
                        int deadChar = KeyEvent.getDeadChar(i3, unicodeChar);
                        if (deadChar != 0) {
                            qb.d(deadChar, 0);
                        } else {
                            qb.d(qb.i, 0);
                            ImeAdapterImpl imeAdapterImpl = qb.e;
                            if (imeAdapterImpl.S()) {
                                N.M_V5g5ie(imeAdapterImpl.s, imeAdapterImpl);
                            }
                        }
                    }
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        this.t.e.a0(this.s);
    }
}
