package defpackage;

import android.os.Bundle;
import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import android.widget.TextView;
import androidx.emoji2.text.d;

/* JADX INFO: compiled from: EmojiInputConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class mg0 extends InputConnectionWrapper {
    public final TextView a;
    public final a b;

    /* JADX INFO: compiled from: EmojiInputConnection.java */
    public static class a {
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0050, code lost:
        
            if (java.lang.Character.isHighSurrogate(r5) != false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x008d, code lost:
        
            if (java.lang.Character.isLowSurrogate(r5) != false) goto L64;
         */
        /* JADX WARN: Removed duplicated region for block: B:109:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x00ab A[EDGE_INSN: B:96:0x00ab->B:72:0x00ab BREAK  A[LOOP:2: B:53:0x0079->B:64:0x0090, LOOP_LABEL: LOOP:2: B:53:0x0079->B:64:0x0090], EDGE_INSN: B:99:0x00ab->B:72:0x00ab BREAK  A[LOOP:2: B:53:0x0079->B:64:0x0090]] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x00ae A[ADDED_TO_REGION] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static boolean a(android.view.inputmethod.InputConnection r7, android.text.Editable r8, int r9, int r10, boolean r11) {
            /*
                Method dump skipped, instruction units count: 252
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: mg0.a.a(android.view.inputmethod.InputConnection, android.text.Editable, int, int, boolean):boolean");
        }
    }

    public mg0(EditText editText, InputConnection inputConnection, EditorInfo editorInfo) {
        a aVar = new a();
        super(inputConnection, false);
        this.a = editText;
        this.b = aVar;
        if (d.k != null) {
            d dVarA = d.a();
            if (!(dVarA.b() == 1) || editorInfo == null) {
                return;
            }
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            d.a aVar2 = dVarA.e;
            aVar2.getClass();
            Bundle bundle = editorInfo.extras;
            nh1 nh1Var = aVar2.c.a;
            int iA = nh1Var.a(4);
            bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iA != 0 ? nh1Var.b.getInt(iA + nh1Var.a) : 0);
            Bundle bundle2 = editorInfo.extras;
            aVar2.a.getClass();
            bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        Editable editableText = this.a.getEditableText();
        this.b.getClass();
        return a.a(this, editableText, i, i2, false) || super.deleteSurroundingText(i, i2);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        Editable editableText = this.a.getEditableText();
        this.b.getClass();
        return a.a(this, editableText, i, i2, true) || super.deleteSurroundingTextInCodePoints(i, i2);
    }
}
