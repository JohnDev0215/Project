package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.App;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class AppUpdater extends AsyncDCUpdater {
    private static final String TAG = "AppUpdater";

    public AppUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<App> apps = iDynamicContentProvider.getApps(cancellationSignal);
        if (apps != null && !apps.isEmpty()) {
            Logger.info(TAG, "Apps count: " + apps.size());
            for (int i = 0; i < apps.size(); i++) {
                App app = apps.get(i);
                if (app == null || TextUtils.isEmpty(app.getName())) {
                    Logger.warn(TAG, "app is null or app name is empty, index = " + i);
                } else {
                    arrayList.add(app.getId() + DccConstant.DCC_SEPARATOR + app.getName());
                }
            }
        }
        return arrayList;
    }
}
