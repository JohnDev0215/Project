package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentUpdater extends AsyncDCUpdater {
    public ContentUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        return iDynamicContentProvider.getDynamicContent(str, cancellationSignal);
    }
}
