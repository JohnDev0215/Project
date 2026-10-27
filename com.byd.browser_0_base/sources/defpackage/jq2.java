package defpackage;

import android.os.Bundle;
import android.text.Editable;
import android.text.style.BackgroundColorSpan;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: SpannableAutocompleteEditTextModel.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jq2 {
    public static final Pattern n = Pattern.compile("[\\p{script=latin}\\p{script=cyrillic}\\p{script=greek}\\p{script=hebrew}\\p{Punct} 0-9]*");
    public final ia a;
    public final pa b;
    public final pa c;
    public final pa d;
    public final b e;
    public a f;
    public int i;
    public int j;
    public int k;
    public int l;
    public boolean g = true;
    public boolean h = true;
    public boolean m = true;

    /* JADX INFO: compiled from: SpannableAutocompleteEditTextModel.java */
    public class a extends InputConnectionWrapper {
        public final pa a;

        public a() {
            super(null, true);
            this.a = new pa(jq2.this.b);
        }

        public final void a() {
            jq2 jq2Var = jq2.this;
            if (jq2Var.b.c()) {
                pa paVar = jq2Var.b;
                String str = paVar.b;
                paVar.a += paVar.b;
                paVar.b = "";
                jq2Var.d.a(paVar);
                jq2Var.g = false;
                if (jq2Var.i != 0) {
                    jq2Var.a.append(str);
                    return;
                }
                c();
                b bVar = jq2Var.e;
                bVar.a.getEditableText().removeSpan(bVar.b);
                ia iaVar = bVar.a;
                if (iaVar.isFocused()) {
                    iaVar.setCursorVisible(true);
                }
                b();
            }
        }

        public final boolean b() {
            jq2 jq2Var = jq2.this;
            jq2Var.i--;
            boolean zEndBatchEdit = super.endBatchEdit();
            if (jq2Var.i == 0) {
                jq2Var.e();
            }
            return zEndBatchEdit;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean beginBatchEdit() {
            d();
            c();
            return e();
        }

        public final boolean c() {
            jq2.this.i++;
            return super.beginBatchEdit();
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean clearMetaKeyStates(int i) {
            d();
            boolean zClearMetaKeyStates = super.clearMetaKeyStates(i);
            e();
            return zClearMetaKeyStates;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean commitCompletion(CompletionInfo completionInfo) {
            d();
            boolean zCommitCompletion = super.commitCompletion(completionInfo);
            e();
            return zCommitCompletion;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
            d();
            boolean zCommitContent = super.commitContent(inputContentInfo, i, bundle);
            e();
            return zCommitContent;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean commitCorrection(CorrectionInfo correctionInfo) {
            d();
            boolean zCommitCorrection = super.commitCorrection(correctionInfo);
            e();
            return zCommitCorrection;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean commitText(CharSequence charSequence, int i) {
            d();
            boolean zCommitText = super.commitText(charSequence, i);
            e();
            return zCommitText;
        }

        public final void d() {
            c();
            jq2 jq2Var = jq2.this;
            if (jq2Var.i == 1) {
                this.a.a(jq2Var.b);
            } else if (jq2Var.j > 0) {
                ia iaVar = jq2Var.a;
                int length = iaVar.getText().length();
                if (jq2Var.j > length) {
                    jq2Var.j = length;
                }
                iaVar.getText().delete(length - jq2Var.j, length);
            }
            jq2Var.j = 0;
            jq2Var.e.a();
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean deleteSurroundingText(int i, int i2) {
            d();
            boolean zDeleteSurroundingText = super.deleteSurroundingText(i, i2);
            e();
            return zDeleteSurroundingText;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
            d();
            boolean zDeleteSurroundingTextInCodePoints = super.deleteSurroundingTextInCodePoints(i, i2);
            e();
            return zDeleteSurroundingTextInCodePoints;
        }

        /* JADX WARN: Removed duplicated region for block: B:51:0x00f9  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x013e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean e() {
            /*
                Method dump skipped, instruction units count: 324
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: jq2.a.e():boolean");
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean endBatchEdit() {
            d();
            b();
            return e();
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean finishComposingText() {
            d();
            boolean zFinishComposingText = super.finishComposingText();
            e();
            return zFinishComposingText;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final int getCursorCapsMode(int i) {
            d();
            int cursorCapsMode = super.getCursorCapsMode(i);
            e();
            return cursorCapsMode;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
            d();
            ExtractedText extractedText = super.getExtractedText(extractedTextRequest, i);
            e();
            return extractedText;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final CharSequence getSelectedText(int i) {
            d();
            CharSequence selectedText = super.getSelectedText(i);
            e();
            return selectedText;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final CharSequence getTextAfterCursor(int i, int i2) {
            d();
            CharSequence textAfterCursor = super.getTextAfterCursor(i, i2);
            e();
            return textAfterCursor;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final CharSequence getTextBeforeCursor(int i, int i2) {
            d();
            CharSequence textBeforeCursor = super.getTextBeforeCursor(i, i2);
            e();
            return textBeforeCursor;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean performEditorAction(int i) {
            d();
            a();
            boolean zPerformEditorAction = super.performEditorAction(i);
            e();
            return zPerformEditorAction;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean requestCursorUpdates(int i) {
            d();
            boolean zRequestCursorUpdates = super.requestCursorUpdates(i);
            e();
            return zRequestCursorUpdates;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean sendKeyEvent(KeyEvent keyEvent) {
            d();
            boolean zSendKeyEvent = super.sendKeyEvent(keyEvent);
            e();
            return zSendKeyEvent;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean setComposingRegion(int i, int i2) {
            d();
            boolean composingRegion = super.setComposingRegion(i, i2);
            e();
            return composingRegion;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean setComposingText(CharSequence charSequence, int i) {
            d();
            boolean composingText = super.setComposingText(charSequence, i);
            e();
            return composingText;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean setSelection(int i, int i2) {
            d();
            boolean selection = super.setSelection(i, i2);
            e();
            return selection;
        }
    }

    /* JADX INFO: compiled from: SpannableAutocompleteEditTextModel.java */
    public static class b {
        public final ia a;
        public BackgroundColorSpan b;

        public b(ia iaVar) {
            this.a = iaVar;
        }

        public final void a() {
            BackgroundColorSpan backgroundColorSpan;
            ia iaVar = this.a;
            if (iaVar.isFocused()) {
                iaVar.setCursorVisible(true);
            }
            Editable editableText = iaVar.getEditableText();
            int spanStart = (editableText == null || (backgroundColorSpan = this.b) == null) ? -1 : editableText.getSpanStart(backgroundColorSpan);
            if (spanStart == -1) {
                return;
            }
            editableText.removeSpan(this.b);
            editableText.delete(spanStart, editableText.length());
            this.b = null;
        }
    }

    public jq2(ia iaVar) {
        this.a = iaVar;
        pa paVar = new pa(iaVar.getSelectionStart(), iaVar.getSelectionEnd(), iaVar.getText().toString());
        this.b = paVar;
        this.c = new pa(paVar);
        this.d = new pa(paVar);
        this.e = new b(iaVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq2.a():void");
    }

    public final void b(int i, int i2) {
        pa paVar = this.b;
        if (paVar.c == i && paVar.d == i2) {
            return;
        }
        paVar.c = i;
        paVar.d = i2;
        if (this.i > 0) {
            return;
        }
        int length = paVar.a.length();
        if (paVar.c()) {
            if (i > length || i2 > length) {
                a aVar = this.f;
                if (aVar != null) {
                    aVar.a();
                }
            } else {
                this.d.b = "";
                paVar.b = "";
                a aVar2 = this.f;
                if (aVar2 != null) {
                    aVar2.d();
                    this.f.e();
                } else {
                    this.e.a();
                    a();
                }
            }
        }
        e();
        a();
    }

    public final void c(CharSequence charSequence) {
        BackgroundColorSpan backgroundColorSpan;
        String string = charSequence.toString();
        int length = charSequence.length();
        int length2 = charSequence.length();
        pa paVar = this.b;
        paVar.a = string;
        paVar.b = "";
        paVar.c = length;
        paVar.d = length2;
        b bVar = this.e;
        ia iaVar = bVar.a;
        if (iaVar.isFocused()) {
            iaVar.setCursorVisible(true);
        }
        Editable editableText = bVar.a.getEditableText();
        if (((editableText == null || (backgroundColorSpan = bVar.b) == null) ? -1 : editableText.getSpanStart(backgroundColorSpan)) != -1) {
            editableText.removeSpan(bVar.b);
        }
        bVar.b = null;
        this.c.a(paVar);
        this.d.a(paVar);
        if (this.i == 0) {
            e();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(java.lang.CharSequence r7) {
        /*
            r6 = this;
            jq2$b r0 = r6.e
            r0.getClass()
            boolean r1 = r7 instanceof android.text.Editable
            r2 = 0
            pa r3 = r6.b
            if (r1 == 0) goto L2a
            r1 = r7
            android.text.Editable r1 = (android.text.Editable) r1
            r4 = -1
            if (r1 == 0) goto L1c
            android.text.style.BackgroundColorSpan r5 = r0.b
            if (r5 != 0) goto L17
            goto L1c
        L17:
            int r5 = r1.getSpanStart(r5)
            goto L1d
        L1c:
            r5 = r4
        L1d:
            if (r5 == r4) goto L2a
            java.lang.CharSequence r7 = r1.subSequence(r2, r5)
            java.lang.String r7 = r7.toString()
            r3.a = r7
            goto L30
        L2a:
            java.lang.String r7 = r7.toString()
            r3.a = r7
        L30:
            int r7 = r6.i
            if (r7 <= 0) goto L35
            return
        L35:
            r6.g = r2
            pa r7 = r6.d
            java.lang.String r1 = ""
            r7.b = r1
            r3.b = r1
            jq2$a r7 = r6.f
            if (r7 == 0) goto L4c
            r7.d()
            jq2$a r6 = r6.f
            r6.e()
            goto L52
        L4c:
            r0.a()
            r6.a()
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jq2.d(java.lang.CharSequence):void");
    }

    public final void e() {
        ia iaVar = this.a;
        int selectionStart = iaVar.getSelectionStart();
        int selectionEnd = iaVar.getSelectionEnd();
        if (selectionStart == this.k && selectionEnd == this.l) {
            return;
        }
        this.k = selectionStart;
        this.l = selectionEnd;
        iaVar.getClass();
    }
}
