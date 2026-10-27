package T0;

import N0.K;
import N1.C0420a;
import android.R;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class u implements InputConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0420a f8469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f8470b;
    public int c;
    public y d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8471e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8472f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f8473g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8474h = true;

    public u(y yVar, C0420a c0420a, boolean z6) {
        this.f8469a = c0420a;
        this.f8470b = z6;
        this.d = yVar;
    }

    public final void a(i iVar) {
        this.c++;
        try {
            this.f8473g.add(iVar);
        } finally {
            b();
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [A7.o, z7.k] */
    public final boolean b() {
        int i7 = this.c - 1;
        this.c = i7;
        if (i7 == 0) {
            ArrayList arrayList = this.f8473g;
            if (!arrayList.isEmpty()) {
                ((B) this.f8469a.l).f8413e.b(AbstractC1998m.A0(arrayList));
                arrayList.clear();
            }
        }
        return this.c > 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        boolean z6 = this.f8474h;
        if (!z6) {
            return z6;
        }
        this.c++;
        return true;
    }

    public final void c(int i7) {
        sendKeyEvent(new KeyEvent(0, i7));
        sendKeyEvent(new KeyEvent(1, i7));
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i7) {
        boolean z6 = this.f8474h;
        if (z6) {
            return false;
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void closeConnection() {
        this.f8473g.clear();
        this.c = 0;
        this.f8474h = false;
        B b10 = (B) this.f8469a.l;
        int size = b10.f8417i.size();
        for (int i7 = 0; i7 < size; i7++) {
            ArrayList arrayList = b10.f8417i;
            if (A7.m.a(((WeakReference) arrayList.get(i7)).get(), this)) {
                arrayList.remove(i7);
                return;
            }
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        boolean z6 = this.f8474h;
        if (z6) {
            return false;
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i7, Bundle bundle) {
        boolean z6 = this.f8474h;
        if (z6) {
            return false;
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        boolean z6 = this.f8474h;
        return z6 ? this.f8470b : z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i7) {
        boolean z6 = this.f8474h;
        if (z6) {
            a(new C0775a(String.valueOf(charSequence), i7));
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i7, int i10) {
        boolean z6 = this.f8474h;
        if (!z6) {
            return z6;
        }
        a(new C0781g(i7, i10));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i7, int i10) {
        boolean z6 = this.f8474h;
        if (!z6) {
            return z6;
        }
        a(new h(i7, i10));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        return b();
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        boolean z6 = this.f8474h;
        if (!z6) {
            return z6;
        }
        a(new k());
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i7) {
        y yVar = this.d;
        return TextUtils.getCapsMode(yVar.f8481a.l, K.e(yVar.f8482b), i7);
    }

    @Override // android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i7) {
        boolean z6 = (i7 & 1) != 0;
        this.f8472f = z6;
        if (z6) {
            this.f8471e = extractedTextRequest != null ? extractedTextRequest.token : 0;
        }
        return P8.n.V(this.d);
    }

    @Override // android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i7) {
        if (K.b(this.d.f8482b)) {
            return null;
        }
        return com.bumptech.glide.c.h0(this.d).l;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i7, int i10) {
        return com.bumptech.glide.c.i0(this.d, i7).l;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i7, int i10) {
        return com.bumptech.glide.c.j0(this.d, i7).l;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i7) {
        boolean z6 = this.f8474h;
        if (z6) {
            z6 = false;
            switch (i7) {
                case R.id.selectAll:
                    a(new x(0, this.d.f8481a.l.length()));
                    break;
                case R.id.cut:
                    c(277);
                    break;
                case R.id.copy:
                    c(278);
                    break;
                case R.id.paste:
                    c(279);
                    break;
            }
        }
        return z6;
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [A7.o, z7.k] */
    @Override // android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i7) {
        int i10;
        boolean z6 = this.f8474h;
        if (z6) {
            z6 = true;
            if (i7 != 0) {
                switch (i7) {
                    case 2:
                        i10 = 2;
                        break;
                    case 3:
                        i10 = 3;
                        break;
                    case 4:
                        i10 = 4;
                        break;
                    case 5:
                        i10 = 6;
                        break;
                    case 6:
                        i10 = 7;
                        break;
                    case 7:
                        i10 = 5;
                        break;
                    default:
                        android.hardware.bydauto.ac.tsimpl.a.r("IME sends unsupported Editor Action: ", i7, "RecordingIC");
                        i10 = 1;
                        break;
                }
                ((B) this.f8469a.l).f8414f.b(new l(i10));
            } else {
                i10 = 1;
                ((B) this.f8469a.l).f8414f.b(new l(i10));
            }
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        boolean z6 = this.f8474h;
        if (z6) {
            return true;
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z6) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i7) {
        boolean z6;
        boolean z10;
        boolean z11;
        boolean z12 = this.f8474h;
        if (!z12) {
            return z12;
        }
        boolean z13 = false;
        boolean z14 = (i7 & 1) != 0;
        boolean z15 = (i7 & 2) != 0;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33) {
            z6 = (i7 & 16) != 0;
            z10 = (i7 & 8) != 0;
            boolean z16 = (i7 & 4) != 0;
            if (i10 >= 34 && (i7 & 32) != 0) {
                z13 = true;
            }
            if (z6 || z10 || z16 || z13) {
                z11 = z13;
                z13 = z16;
            } else if (i10 >= 34) {
                z11 = true;
                z13 = true;
                z6 = true;
                z10 = true;
            } else {
                z6 = true;
                z10 = true;
                z11 = z13;
                z13 = true;
            }
        } else {
            z6 = true;
            z10 = true;
            z11 = false;
        }
        C0779e c0779e = ((B) this.f8469a.l).l;
        synchronized (c0779e.c) {
            try {
                c0779e.f8439f = z6;
                c0779e.f8440g = z10;
                c0779e.f8441h = z13;
                c0779e.f8442i = z11;
                if (z14) {
                    c0779e.f8438e = true;
                    if (c0779e.f8443j != null) {
                        c0779e.a();
                    }
                }
                c0779e.d = z15;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object, m7.f] */
    @Override // android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        boolean z6 = this.f8474h;
        if (!z6) {
            return z6;
        }
        ((BaseInputConnection) ((B) this.f8469a.l).f8418j.getValue()).sendKeyEvent(keyEvent);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i7, int i10) {
        boolean z6 = this.f8474h;
        if (z6) {
            a(new v(i7, i10));
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i7) {
        boolean z6 = this.f8474h;
        if (z6) {
            a(new w(String.valueOf(charSequence), i7));
        }
        return z6;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setSelection(int i7, int i10) {
        boolean z6 = this.f8474h;
        if (!z6) {
            return z6;
        }
        a(new x(i7, i10));
        return true;
    }
}
