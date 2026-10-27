package com.android.car.developeroptions.connecteddevice.usb;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.connecteddevice.usb.UsbConnectionBroadcastReceiver;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UsbDetailsFragment extends DashboardFragment {
    private List<UsbDetailsController> mControllers;
    private UsbBackend mUsbBackend;
    private UsbConnectionBroadcastReceiver.UsbConnectionListener mUsbConnectionListener = new UsbConnectionBroadcastReceiver.UsbConnectionListener() { // from class: com.android.car.developeroptions.connecteddevice.usb.-$$Lambda$UsbDetailsFragment$2Iok6lYKu05_jQPZVP5I-RUrW70
        @Override // com.android.car.developeroptions.connecteddevice.usb.UsbConnectionBroadcastReceiver.UsbConnectionListener
        public final void onUsbConnectionChanged(boolean z, long j, int i, int i2) {
            this.f$0.lambda$new$0$UsbDetailsFragment(z, j, i, i2);
        }
    };
    UsbConnectionBroadcastReceiver mUsbReceiver;
    private static final String TAG = UsbDetailsFragment.class.getSimpleName();
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.connecteddevice.usb.UsbDetailsFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.usb_details_fragment;
            return Lists.newArrayList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1291;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.usb_details_fragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$UsbDetailsFragment(boolean z, long j, int i, int i2) {
        Iterator<UsbDetailsController> it = this.mControllers.iterator();
        while (it.hasNext()) {
            it.next().refresh(z, j, i, i2);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        UsbBackend usbBackend = new UsbBackend(context);
        this.mUsbBackend = usbBackend;
        this.mControllers = createControllerList(context, usbBackend, this);
        this.mUsbReceiver = new UsbConnectionBroadcastReceiver(context, this.mUsbConnectionListener, this.mUsbBackend);
        getSettingsLifecycle().addObserver(this.mUsbReceiver);
        return new ArrayList(this.mControllers);
    }

    private static List<UsbDetailsController> createControllerList(Context context, UsbBackend usbBackend, UsbDetailsFragment usbDetailsFragment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UsbDetailsHeaderController(context, usbDetailsFragment, usbBackend));
        arrayList.add(new UsbDetailsDataRoleController(context, usbDetailsFragment, usbBackend));
        arrayList.add(new UsbDetailsFunctionsController(context, usbDetailsFragment, usbBackend));
        arrayList.add(new UsbDetailsPowerRoleController(context, usbDetailsFragment, usbBackend));
        return arrayList;
    }
}
