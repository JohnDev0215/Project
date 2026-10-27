package defpackage;

import J.N;
import android.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.SurroundingText;
import java.util.concurrent.LinkedBlockingQueue;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: compiled from: ThreadedInputConnection.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f93 extends BaseInputConnection implements bt {
    public static final j l = new j(new y82(0, 0), new y82(-1, -1));
    public final k a;
    public final l b;
    public final m c;
    public final ImeAdapterImpl d;
    public final Handler e;
    public int f;
    public final LinkedBlockingQueue g;
    public int h;
    public j83 i;
    public int j;
    public boolean k;

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class a implements Runnable {
        public final /* synthetic */ int e;

        public a(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93.this.d.i(this.e);
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class b implements Runnable {
        public final /* synthetic */ int e;

        public b(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            WebContentsImpl webContentsImpl = f93.this.d.j;
            switch (this.e) {
                case R.id.selectAll:
                    webContentsImpl.R();
                    N.MNvj1u1S(webContentsImpl.f);
                    break;
                case R.id.cut:
                    webContentsImpl.R();
                    N.MhIiCaN7(webContentsImpl.f);
                    break;
                case R.id.copy:
                    webContentsImpl.R();
                    N.MpfMxfut(webContentsImpl.f);
                    break;
                case R.id.paste:
                    webContentsImpl.R();
                    N.MYRJ_nNk(webContentsImpl.f);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class c implements Runnable {
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;

        public c(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93 f93Var = f93.this;
            int i = f93Var.h;
            ImeAdapterImpl imeAdapterImpl = f93Var.d;
            if (i != 0 && imeAdapterImpl.g()) {
                N.M_V5g5ie(imeAdapterImpl.e, imeAdapterImpl);
            }
            imeAdapterImpl.h();
            if (imeAdapterImpl.g()) {
                N.M1qwlrOP(imeAdapterImpl.e, imeAdapterImpl, null, 7, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
                N.M26GCjn5(imeAdapterImpl.e, imeAdapterImpl, this.e, this.f);
                N.M1qwlrOP(imeAdapterImpl.e, imeAdapterImpl, null, 9, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class d implements Runnable {
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;

        public d(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93 f93Var = f93.this;
            int i = f93Var.h;
            ImeAdapterImpl imeAdapterImpl = f93Var.d;
            if (i != 0 && imeAdapterImpl.g()) {
                N.M_V5g5ie(imeAdapterImpl.e, imeAdapterImpl);
            }
            imeAdapterImpl.h();
            if (imeAdapterImpl.g()) {
                N.M1qwlrOP(imeAdapterImpl.e, imeAdapterImpl, null, 7, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
                N.Mvb046o_(imeAdapterImpl.e, imeAdapterImpl, this.e, this.f);
                N.M1qwlrOP(imeAdapterImpl.e, imeAdapterImpl, null, 9, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class e implements Runnable {
        public final /* synthetic */ KeyEvent e;

        public e(KeyEvent keyEvent) {
            this.e = keyEvent;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93 f93Var = f93.this;
            f93Var.getClass();
            KeyEvent keyEvent = this.e;
            int action = keyEvent.getAction();
            int unicodeChar = keyEvent.getUnicodeChar();
            ImeAdapterImpl imeAdapterImpl = f93Var.d;
            boolean z = false;
            if (action == 0) {
                if (keyEvent.getKeyCode() == 67) {
                    f93Var.h = 0;
                } else {
                    if ((Integer.MIN_VALUE & unicodeChar) != 0) {
                        int i = Integer.MAX_VALUE & unicodeChar;
                        int i2 = f93Var.h;
                        if (i2 == 0) {
                            f93Var.h = i;
                        } else if (i == i2) {
                            f93Var.b(i2, 0);
                        } else {
                            f93Var.b(i2, i);
                        }
                    } else {
                        int i3 = f93Var.h;
                        if (i3 != 0 && unicodeChar != 0) {
                            int deadChar = KeyEvent.getDeadChar(i3, unicodeChar);
                            if (deadChar != 0) {
                                f93Var.b(deadChar, 0);
                            } else {
                                f93Var.b(f93Var.h, 0);
                                if (imeAdapterImpl.g()) {
                                    N.M_V5g5ie(imeAdapterImpl.e, imeAdapterImpl);
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
            imeAdapterImpl.m(keyEvent);
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class f implements Runnable {
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;

        public f(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ImeAdapterImpl imeAdapterImpl = f93.this.d;
            if (imeAdapterImpl.g()) {
                N.MmtjCblb(imeAdapterImpl.e, imeAdapterImpl, this.e, this.f);
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class g implements Runnable {
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;

        public g(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ImeAdapterImpl imeAdapterImpl = f93.this.d;
            if (imeAdapterImpl.g()) {
                int i = this.e;
                int i2 = this.f;
                if (i <= i2) {
                    N.M8ty0WHb(imeAdapterImpl.e, imeAdapterImpl, i, i2);
                } else {
                    N.M8ty0WHb(imeAdapterImpl.e, imeAdapterImpl, i2, i);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class h implements Runnable {
        public h(String str, Bundle bundle) {
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93.this.d.k.getClass();
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class i implements Runnable {
        public final /* synthetic */ int e;

        public i(int i) {
            this.e = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ImeAdapterImpl imeAdapterImpl = f93.this.d;
            imeAdapterImpl.getClass();
            int i = this.e;
            boolean z = (i & 1) != 0;
            boolean z2 = (i & 2) != 0;
            if (imeAdapterImpl.g()) {
                N.MdwW1P2L(imeAdapterImpl.e, imeAdapterImpl, z, z2);
            }
            View viewD = imeAdapterImpl.d();
            org.chromium.content.browser.input.b bVar = imeAdapterImpl.l;
            boolean z3 = bVar.a;
            if (z3) {
                if (bVar.c && !z2 && z3) {
                    bVar.n = null;
                }
                bVar.c = z2;
                if (z) {
                    bVar.b = true;
                    bVar.a(viewD);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class j extends j83 {
        public j(y82 y82Var, y82 y82Var2) {
            super("", y82Var, y82Var2, false, false);
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class k implements Runnable {
        public k() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93 f93Var = f93.this;
            f93Var.a();
            while (true) {
                j83 j83Var = (j83) f93Var.g.poll();
                if (j83Var == null) {
                    return;
                }
                if (!(j83Var instanceof j)) {
                    f93Var.e(j83Var);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class l implements Runnable {
        public l() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93 f93Var = f93.this;
            ImeAdapterImpl imeAdapterImpl = f93Var.d;
            if ((imeAdapterImpl.g() && imeAdapterImpl.g != null) ? N.M7o5Xhhi(imeAdapterImpl.e, imeAdapterImpl) : false) {
                return;
            }
            z7.c();
            j jVar = f93.l;
            z7.c();
            try {
                f93Var.g.put(jVar);
            } catch (InterruptedException e) {
                Log.e("cr_Ime", "addToQueueOnUiThread interrupted", e);
            }
            f93Var.e.post(f93Var.a);
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class m implements Runnable {
        public m() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ImeAdapterImpl imeAdapterImpl = f93.this.d;
            if (imeAdapterImpl.g()) {
                N.M_V5g5ie(imeAdapterImpl.e, imeAdapterImpl);
            }
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class n implements Runnable {
        public n() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93.this.d.n(6);
        }
    }

    /* JADX INFO: compiled from: ThreadedInputConnection.java */
    public class o implements Runnable {
        public final /* synthetic */ CharSequence e;
        public final /* synthetic */ int f;

        public o(CharSequence charSequence, int i) {
            this.e = charSequence;
            this.f = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            f93 f93Var = f93.this;
            f93Var.h = 0;
            f93Var.d.l(this.e, this.f, true, 0);
        }
    }

    public f93(View view, ImeAdapterImpl imeAdapterImpl, Handler handler) {
        super(view, true);
        this.a = new k();
        this.b = new l();
        this.c = new m();
        this.g = new LinkedBlockingQueue();
        z7.c();
        this.d = imeAdapterImpl;
        this.e = handler;
    }

    public static ExtractedText c(j83 j83Var) {
        if (j83Var == null) {
            return null;
        }
        ExtractedText extractedText = new ExtractedText();
        CharSequence charSequence = j83Var.a;
        extractedText.text = charSequence;
        extractedText.partialEndOffset = charSequence.length();
        extractedText.partialStartOffset = -1;
        y82 y82Var = j83Var.b;
        extractedText.selectionStart = y82Var.a;
        extractedText.selectionEnd = y82Var.b;
        extractedText.flags = j83Var.d ? 1 : 0;
        return extractedText;
    }

    public final void a() {
        if (!(this.e.getLooper() == Looper.myLooper())) {
            throw new AssertionError();
        }
    }

    public final void b(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.appendCodePoint(i2);
        this.d.l(sb.toString(), 1, true, 0);
        this.h = i3;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        a();
        a();
        this.f++;
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i2) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i2) {
        if (charSequence == null) {
            return false;
        }
        if (!TextUtils.equals(charSequence, "\n")) {
            PostTask.c(rh3.a, new o(charSequence, i2));
            return true;
        }
        beginBatchEdit();
        commitText("", 1);
        PostTask.c(rh3.a, new n());
        endBatchEdit();
        return true;
    }

    public final j83 d() {
        if (ThreadUtils.g()) {
            Log.w("cr_Ime", "InputConnection API is not called on IME thread. Returning cached result.");
            return this.i;
        }
        a();
        PostTask.c(rh3.a, this.b);
        a();
        boolean z = false;
        while (true) {
            try {
                j83 j83Var = (j83) this.g.take();
                j83Var.getClass();
                if (j83Var instanceof j) {
                    return null;
                }
                if (j83Var.e) {
                    if (!z) {
                        return j83Var;
                    }
                    e(j83Var);
                    return j83Var;
                }
                z = true;
            } catch (InterruptedException e2) {
                Log.e("cr_Ime", e2.toString());
                throw new AssertionError();
            }
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i2, int i3) {
        PostTask.c(rh3.a, new c(i2, i3));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i2, int i3) {
        PostTask.c(rh3.a, new d(i2, i3));
        return true;
    }

    public final void e(j83 j83Var) {
        if (j83Var == null) {
            return;
        }
        a();
        if (this.f != 0) {
            return;
        }
        if (this.k) {
            ExtractedText extractedTextC = c(j83Var);
            int i2 = this.j;
            ImeAdapterImpl imeAdapterImpl = this.d;
            imeAdapterImpl.f.h(imeAdapterImpl.d(), i2, extractedTextC);
        }
        PostTask.c(rh3.a, new w70(this, 6, j83Var.b, j83Var.c));
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        a();
        int i2 = this.f;
        if (i2 == 0) {
            return false;
        }
        int i3 = i2 - 1;
        this.f = i3;
        if (i3 == 0) {
            e(d());
        }
        return this.f != 0;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        PostTask.c(rh3.a, this.c);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i2) {
        j83 j83VarD = d();
        if (j83VarD == null) {
            return 0;
        }
        return TextUtils.getCapsMode(j83VarD.a, j83VarD.b.a, i2);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i2) {
        a();
        boolean z = (i2 & 1) > 0;
        this.k = z;
        if (z) {
            this.j = extractedTextRequest != null ? extractedTextRequest.token : 0;
        }
        return c(d());
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return this.e;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i2) {
        y82 y82Var;
        int i3;
        int i4;
        j83 j83VarD = d();
        if (j83VarD == null || (i3 = (y82Var = j83VarD.b).a) == (i4 = y82Var.b)) {
            return null;
        }
        return TextUtils.substring(j83VarD.a, i3, i4);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    @SuppressLint({"Override"})
    public final SurroundingText getSurroundingText(int i2, int i3, int i4) {
        j83 j83VarD = d();
        if (j83VarD == null) {
            return null;
        }
        return j83VarD.a(i2, i3);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i2, int i3) {
        j83 j83VarD = d();
        if (j83VarD == null) {
            return null;
        }
        CharSequence charSequence = j83VarD.a;
        int length = charSequence.length();
        y82 y82Var = j83VarD.b;
        return TextUtils.substring(charSequence, y82Var.b, Math.min(charSequence.length(), y82Var.b + Math.max(0, Math.min(i2, length - y82Var.b))));
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i2, int i3) {
        j83 j83VarD = d();
        if (j83VarD == null) {
            return null;
        }
        y82 y82Var = j83VarD.b;
        return TextUtils.substring(j83VarD.a, Math.max(0, y82Var.a - Math.max(0, Math.min(i2, y82Var.a))), y82Var.a);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i2) {
        PostTask.c(rh3.a, new b(i2));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i2) {
        PostTask.c(rh3.a, new a(i2));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        PostTask.c(rh3.a, new h(str, bundle));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z) {
        return false;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i2) {
        PostTask.c(rh3.a, new i(i2));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        PostTask.c(rh3.a, new e(keyEvent));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i2, int i3) {
        PostTask.c(rh3.a, new g(i2, i3));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i2) {
        if (charSequence == null) {
            return false;
        }
        PostTask.c(rh3.a, new i93(this, charSequence, i2));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public final boolean setSelection(int i2, int i3) {
        PostTask.c(rh3.a, new f(i2, i3));
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    @SuppressLint({"MissingSuperCall"})
    public final void closeConnection() {
    }
}
