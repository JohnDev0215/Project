package androidx.core.widget;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class RichContentReceiverCompat<T extends View> {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_CLIPBOARD = 0;
    public static final int SOURCE_INPUT_METHOD = 1;
    private static final String TAG = "RichContentReceiver";

    public abstract Set<String> getSupportedMimeTypes();

    public abstract boolean onReceive(T t, ClipData clipData, int i, int i2);

    public final boolean supports(ClipDescription description) {
        for (String supportedMimeType : getSupportedMimeTypes()) {
            if (description.hasMimeType(supportedMimeType)) {
                return true;
            }
        }
        return false;
    }

    public final void populateEditorInfoContentMimeTypes(InputConnection ic, EditorInfo outAttrs) {
        if (ic == null || outAttrs == null) {
            return;
        }
        String[] mimeTypes = (String[]) getSupportedMimeTypes().toArray(new String[0]);
        EditorInfoCompat.setContentMimeTypes(outAttrs, mimeTypes);
    }

    public final InputConnectionCompat.OnCommitContentListener buildOnCommitContentListener(final T view) {
        return new InputConnectionCompat.OnCommitContentListener() { // from class: androidx.core.widget.RichContentReceiverCompat.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.core.view.inputmethod.InputConnectionCompat.OnCommitContentListener
            public boolean onCommitContent(InputContentInfoCompat content, int flags, Bundle opts) {
                ClipDescription description = content.getDescription();
                if ((flags & 1) != 0) {
                    try {
                        content.requestPermission();
                    } catch (Exception e) {
                        Log.w(RichContentReceiverCompat.TAG, "Can't insert from IME; requestPermission() failed: " + e);
                        return false;
                    }
                }
                ClipData clip = new ClipData(description, new ClipData.Item(content.getContentUri()));
                return RichContentReceiverCompat.this.onReceive(view, clip, 1, 0);
            }
        };
    }
}
