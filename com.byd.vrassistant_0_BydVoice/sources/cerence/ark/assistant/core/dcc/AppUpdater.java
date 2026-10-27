package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.App;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppUpdater extends AsyncDCUpdater {
    public AppUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<App> apps = iDynamicContentProvider.getApps(cancellationSignal);
        if (apps != null && !apps.isEmpty()) {
            for (App app : apps) {
                if (app != null && !TextUtils.isEmpty(app.getName())) {
                    arrayList.add(app.getId() + DccConstant.SEPARATOR + app.getName());
                }
            }
        }
        return arrayList;
    }
}
