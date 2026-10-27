package k0;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: compiled from: InputConnectionCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: compiled from: InputConnectionCompat.java */
    class a extends InputConnectionWrapper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ InterfaceC0096b f7191a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(InputConnection inputConnection, boolean z6, InterfaceC0096b interfaceC0096b) {
            super(inputConnection, z6);
            this.f7191a = interfaceC0096b;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitContent(InputContentInfo inputContentInfo, int i7, Bundle bundle) {
            if (this.f7191a.a(c.f(inputContentInfo), i7, bundle)) {
                return true;
            }
            return super.commitContent(inputContentInfo, i7, bundle);
        }
    }

    /* JADX INFO: renamed from: k0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: InputConnectionCompat.java */
    public interface InterfaceC0096b {
        boolean a(c cVar, int i7, Bundle bundle);
    }

    public static InputConnection a(InputConnection inputConnection, EditorInfo editorInfo, InterfaceC0096b interfaceC0096b) {
        if (inputConnection == null) {
            throw new IllegalArgumentException("inputConnection must be non-null");
        }
        if (editorInfo == null) {
            throw new IllegalArgumentException("editorInfo must be non-null");
        }
        if (interfaceC0096b != null) {
            return new a(inputConnection, false, interfaceC0096b);
        }
        throw new IllegalArgumentException("onCommitContentListener must be non-null");
    }
}
