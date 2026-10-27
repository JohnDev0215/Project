package androidx.autofill.inline;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.autofill.inline.common.SlicedContent;
import androidx.autofill.inline.v1.InlineSuggestionUi;

/* JADX INFO: loaded from: classes.dex */
public final class Renderer {
    public static View render(Context context, Slice slice, Bundle bundle) {
        String version = SlicedContent.getVersion(slice);
        if (!VersionUtils.isVersionSupported(version)) {
            Log.w("Renderer", "Content version unsupported.");
            return null;
        }
        Bundle styleByVersion = VersionUtils.readStyleByVersion(bundle, version);
        if (styleByVersion == null) {
            Log.w("Renderer", "Cannot find a style with the same version as the slice.");
            return null;
        }
        byte b = -1;
        if (version.hashCode() == -1222503719 && version.equals("androidx.autofill.inline.ui.version:v1")) {
            b = 0;
        }
        if (b == 0) {
            InlineSuggestionUi.Style styleFromBundle = InlineSuggestionUi.fromBundle(styleByVersion);
            InlineSuggestionUi.Content contentFromSlice = InlineSuggestionUi.fromSlice(slice);
            if (styleFromBundle == null || slice == null) {
                return null;
            }
            return InlineSuggestionUi.render(context, contentFromSlice, styleFromBundle);
        }
        Log.w("Renderer", "Renderer does not support the style/content version: " + version);
        return null;
    }

    public static PendingIntent getAttributionIntent(Slice slice) {
        String version = SlicedContent.getVersion(slice);
        if (!VersionUtils.isVersionSupported(version)) {
            Log.w("Renderer", "Content version unsupported.");
            return null;
        }
        byte b = -1;
        if (version.hashCode() == -1222503719 && version.equals("androidx.autofill.inline.ui.version:v1")) {
            b = 0;
        }
        if (b == 0) {
            InlineSuggestionUi.Content contentFromSlice = InlineSuggestionUi.fromSlice(slice);
            if (contentFromSlice == null) {
                return null;
            }
            return InlineSuggestionUi.getAttributionIntent(contentFromSlice);
        }
        Log.w("Renderer", "Renderer does not support the content version: " + version);
        return null;
    }
}
