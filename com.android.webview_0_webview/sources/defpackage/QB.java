package defpackage;

import J.N;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class QB extends BaseInputConnection implements LA {
    public static final C1478sB m = new HB("", new XA(0, 0), new XA(-1, -1), false, false);
    public final Runnable a;
    public final Runnable b;
    public final Runnable c;
    public final Runnable d;
    public final ImeAdapterImpl e;
    public final Handler f;
    public int g;
    public final BlockingQueue h;
    public int i;
    public C1478sB j;
    public int k;
    public boolean l;

    public QB(View view, ImeAdapterImpl imeAdapterImpl, Handler handler) {
        super(view, true);
        this.a = new IB(this);
        this.b = new JB(this);
        this.c = new KB(this);
        this.d = new LB(this);
        this.h = new LinkedBlockingQueue();
        UA.a();
        this.e = imeAdapterImpl;
        this.f = handler;
    }

    public static void a(QB qb) {
        ImeAdapterImpl imeAdapterImpl = qb.e;
        if (imeAdapterImpl.S()) {
            N.M_V5g5ie(imeAdapterImpl.s, imeAdapterImpl);
        }
    }

    public final void b(C1478sB c1478sB) {
        UA.a();
        try {
            this.h.put(c1478sB);
        } catch (InterruptedException e) {
            Ts.a("Ime", "addToQueueOnUiThread interrupted", e);
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        c();
        c();
        this.g++;
        return true;
    }

    public final void c() {
        if (!(this.f.getLooper() == Looper.myLooper())) {
            throw new AssertionError();
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int i) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public void closeConnection() {
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo completionInfo) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        if (charSequence == null) {
            return false;
        }
        if (!TextUtils.equals(charSequence, "\n")) {
            PostTask.a(AbstractC0538bE.a, new RunnableC1753xB(this, charSequence, i), 0L);
            f();
            return true;
        }
        beginBatchEdit();
        commitText("", 1);
        PostTask.a(AbstractC0538bE.a, new PB(this), 0L);
        endBatchEdit();
        return true;
    }

    public final void d(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.appendCodePoint(i);
        this.e.Z(sb.toString(), 1, true, 0);
        this.i = i2;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i, int i2) {
        PostTask.a(AbstractC0538bE.a, new AB(this, i, i2), 0L);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        PostTask.a(AbstractC0538bE.a, new BB(this, i, i2), 0L);
        return true;
    }

    public final ExtractedText e(C1478sB c1478sB) {
        if (c1478sB == null) {
            return null;
        }
        ExtractedText extractedText = new ExtractedText();
        CharSequence charSequence = c1478sB.a;
        extractedText.text = charSequence;
        extractedText.partialEndOffset = charSequence.length();
        extractedText.partialStartOffset = -1;
        XA xa = c1478sB.b;
        extractedText.selectionStart = xa.a;
        extractedText.selectionEnd = xa.b;
        extractedText.flags = c1478sB.d ? 1 : 0;
        return extractedText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        c();
        int i = this.g;
        if (i == 0) {
            return false;
        }
        int i2 = i - 1;
        this.g = i2;
        if (i2 == 0) {
            i(g());
        }
        return this.g != 0;
    }

    public final void f() {
        PostTask.a(AbstractC0538bE.a, this.c, 0L);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        PostTask.a(AbstractC0538bE.a, this.d, 0L);
        return true;
    }

    public final C1478sB g() {
        boolean z = false;
        if (ThreadUtils.j()) {
            Ts.f("Ime", "InputConnection API is not called on IME thread. Returning cached result.", new Object[0]);
            return this.j;
        }
        c();
        PostTask.a(AbstractC0538bE.a, this.b, 0L);
        c();
        while (true) {
            try {
                C1478sB c1478sB = (C1478sB) this.h.take();
                if (c1478sB.a()) {
                    return null;
                }
                if (c1478sB.e) {
                    if (!z) {
                        return c1478sB;
                    }
                    i(c1478sB);
                    return c1478sB;
                }
                z = true;
            } catch (InterruptedException e) {
                AbstractC0279Pk.a.b(e);
                throw new AssertionError();
            }
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int i) {
        C1478sB c1478sBG = g();
        if (c1478sBG != null) {
            return TextUtils.getCapsMode(c1478sBG.a, c1478sBG.b.a, i);
        }
        return 0;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        c();
        boolean z = (i & 1) > 0;
        this.l = z;
        if (z) {
            this.k = extractedTextRequest != null ? extractedTextRequest.token : 0;
        }
        return e(g());
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public Handler getHandler() {
        return this.f;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int i) {
        XA xa;
        int i2;
        int i3;
        C1478sB c1478sBG = g();
        if (c1478sBG == null || (i2 = (xa = c1478sBG.b).a) == (i3 = xa.b)) {
            return null;
        }
        return TextUtils.substring(c1478sBG.a, i2, i3);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int i, int i2) {
        C1478sB c1478sBG = g();
        if (c1478sBG == null) {
            return null;
        }
        int iMax = Math.max(0, Math.min(i, c1478sBG.a.length() - c1478sBG.b.b));
        CharSequence charSequence = c1478sBG.a;
        return TextUtils.substring(charSequence, c1478sBG.b.b, Math.min(charSequence.length(), c1478sBG.b.b + iMax));
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int i, int i2) {
        C1478sB c1478sBG = g();
        if (c1478sBG == null) {
            return null;
        }
        return TextUtils.substring(c1478sBG.a, Math.max(0, c1478sBG.b.a - Math.max(0, Math.min(i, c1478sBG.b.a))), c1478sBG.b.a);
    }

    public void h() {
        UA.a();
        b(m);
        this.f.post(this.a);
    }

    public final void i(C1478sB c1478sB) {
        if (c1478sB == null) {
            return;
        }
        c();
        if (this.g != 0) {
            return;
        }
        final XA xa = c1478sB.b;
        final XA xa2 = c1478sB.c;
        if (this.l) {
            ExtractedText extractedTextE = e(c1478sB);
            ImeAdapterImpl imeAdapterImpl = this.e;
            int i = this.k;
            ND nd = imeAdapterImpl.t;
            View viewQ = imeAdapterImpl.Q();
            InputMethodManager inputMethodManagerB = ((WA) nd).b();
            if (inputMethodManagerB != null) {
                inputMethodManagerB.updateExtractedText(viewQ, i, extractedTextE);
            }
        }
        PostTask.a(AbstractC0538bE.a, new Runnable(this, xa, xa2) { // from class: wB
            public final QB s;
            public final XA t;
            public final XA u;

            {
                this.s = this;
                this.t = xa;
                this.u = xa2;
            }

            @Override // java.lang.Runnable
            public void run() {
                QB qb = this.s;
                XA xa3 = this.t;
                XA xa4 = this.u;
                ImeAdapterImpl imeAdapterImpl2 = qb.e;
                int i2 = xa3.a;
                int i3 = xa3.b;
                int i4 = xa4.a;
                int i5 = xa4.b;
                ND nd2 = imeAdapterImpl2.t;
                View viewQ2 = imeAdapterImpl2.Q();
                InputMethodManager inputMethodManagerB2 = ((WA) nd2).b();
                if (inputMethodManagerB2 == null) {
                    return;
                }
                inputMethodManagerB2.updateSelection(viewQ2, i2, i3, i4, i5);
            }
        }, 0L);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int i) {
        PostTask.a(AbstractC0538bE.a, new RunnableC1863zB(this, i), 0L);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performEditorAction(int i) {
        PostTask.a(AbstractC0538bE.a, new RunnableC1808yB(this, i), 0L);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String str, Bundle bundle) {
        PostTask.a(AbstractC0538bE.a, new FB(this, str, bundle), 0L);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean z) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int i) {
        PostTask.a(AbstractC0538bE.a, new GB(this, i), 0L);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        PostTask.a(AbstractC0538bE.a, new CB(this, keyEvent), 0L);
        f();
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int i, int i2) {
        PostTask.a(AbstractC0538bE.a, new EB(this, i, i2), 0L);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        if (charSequence == null) {
            return false;
        }
        PostTask.a(AbstractC0538bE.a, new OB(this, charSequence, i, false), 0L);
        f();
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setSelection(int i, int i2) {
        PostTask.a(AbstractC0538bE.a, new DB(this, i, i2), 0L);
        return true;
    }
}
