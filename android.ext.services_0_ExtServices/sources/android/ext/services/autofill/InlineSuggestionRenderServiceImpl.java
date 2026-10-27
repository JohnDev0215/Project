package android.ext.services.autofill;

import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Bundle;
import android.service.autofill.InlinePresentation;
import android.service.autofill.InlineSuggestionRenderService;
import android.util.Log;
import android.view.View;
import androidx.autofill.inline.Renderer;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public class InlineSuggestionRenderServiceImpl extends InlineSuggestionRenderService {
    /* JADX WARN: Multi-variable type inference failed */
    public View onRenderSuggestion(InlinePresentation inlinePresentation, int i, int i2) {
        PendingIntent attributionIntent;
        Preconditions.checkNotNull(inlinePresentation, "InlinePresentationRendererServiceImpl: InlinePresentation should not be null");
        Log.v("InlinePresentationRendererServiceImpl", "onRenderSuggestion: width=" + i + ", height=" + i2);
        Bundle style = inlinePresentation.getInlinePresentationSpec().getStyle();
        View viewRender = null;
        if (style == null || style.isEmpty()) {
            attributionIntent = null;
        } else {
            viewRender = Renderer.render(this, inlinePresentation.getSlice(), style);
            attributionIntent = Renderer.getAttributionIntent(inlinePresentation.getSlice());
        }
        if (viewRender != null && attributionIntent != null) {
            final IntentSender intentSender = attributionIntent.getIntentSender();
            viewRender.setOnLongClickListener(new View.OnLongClickListener() { // from class: android.ext.services.autofill.-$$Lambda$InlineSuggestionRenderServiceImpl$Zc7Cw4Af0nVrsEQVRbOUCayIteQ
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return this.f$0.lambda$onRenderSuggestion$0$InlineSuggestionRenderServiceImpl(intentSender, view);
                }
            });
        }
        return viewRender;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onRenderSuggestion$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$onRenderSuggestion$0$InlineSuggestionRenderServiceImpl(IntentSender intentSender, View view) {
        startIntentSender(intentSender);
        return true;
    }
}
