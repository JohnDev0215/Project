package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.RadioStation;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class RadioStationUpdater extends AsyncDCUpdater {
    public RadioStationUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<RadioStation> radioStations = iDynamicContentProvider.getRadioStations(cancellationSignal);
        if (radioStations != null && !radioStations.isEmpty()) {
            for (RadioStation radioStation : radioStations) {
                if (radioStation != null && !TextUtils.isEmpty(radioStation.getName())) {
                    arrayList.add(radioStation.getId() + DccConstant.SEPARATOR + radioStation.getName());
                }
            }
        }
        return arrayList;
    }
}
