package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.RadioStation;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class RadioStationUpdater extends AsyncDCUpdater {
    private static final String TAG = "RadioStationUpdater";

    public RadioStationUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<RadioStation> radioStations = iDynamicContentProvider.getRadioStations(cancellationSignal);
        if (radioStations != null && !radioStations.isEmpty()) {
            Logger.info(TAG, "RadioStations count: " + radioStations.size());
            for (int i = 0; i < radioStations.size(); i++) {
                RadioStation radioStation = radioStations.get(i);
                if (radioStation == null || TextUtils.isEmpty(radioStation.getName())) {
                    Logger.warn(TAG, "RadioStation is null or RadioStation name is empty, index = " + i);
                } else {
                    arrayList.add(radioStation.getId() + DccConstant.DCC_SEPARATOR + radioStation.getName());
                }
            }
        }
        return arrayList;
    }
}
