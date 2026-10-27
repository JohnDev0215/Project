package T0;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public class p implements InputConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A.F f8466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public M.B f8467b;

    public p(M.B b10, A.F f10) {
        this.f8466a = f10;
        this.f8467b = b10;
    }

    public final void a(M.B b10) {
        b10.closeConnection();
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.beginBatchEdit();
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.clearMetaKeyStates(i7);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void closeConnection() {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            if (b10 != null) {
                a(b10);
                this.f8467b = null;
            }
            this.f8466a.b(this);
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.commitCompletion(completionInfo);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i7, Bundle bundle) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.commitContent(inputContentInfo, i7, bundle);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.commitCorrection(correctionInfo);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.commitText(charSequence, i7);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i7, int i10) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.deleteSurroundingText(i7, i10);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i7, int i10) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.deleteSurroundingTextInCodePoints(i7, i10);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.b();
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.finishComposingText();
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.getCursorCapsMode(i7);
        }
        return 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.getExtractedText(extractedTextRequest, i7);
        }
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.getSelectedText(i7);
        }
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i7, int i10) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.getTextAfterCursor(i7, i10);
        }
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i7, int i10) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.getTextBeforeCursor(i7, i10);
        }
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.performContextMenuAction(i7);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.performEditorAction(i7);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.performPrivateCommand(str, bundle);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z6) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.requestCursorUpdates(i7);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.sendKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i7, int i10) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.setComposingRegion(i7, i10);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i7) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.setComposingText(charSequence, i7);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setSelection(int i7, int i10) {
        M.B b10 = this.f8467b;
        if (b10 != null) {
            return b10.setSelection(i7, i10);
        }
        return false;
    }
}
