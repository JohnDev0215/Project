package com.amazon.music.castingviews;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.button.ButtonTileModel;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.model.Block;
import com.amazon.music.ui.model.castingtile.CastingTileModel;
import com.amazon.music.uicasting.CastingTileConverter;
import com.amazon.music.uicasting.UiCasting;
import com.amazon.music.uicasting.UiChromecastDeviceListFeed;
import com.amazon.music.uicasting.UiDeviceListResult;
import com.amazon.music.view.adapter.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDeviceListView {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CastingDeviceListView.class);
    private UniversalAdapter adapter;
    private CastingDeviceComparator castingDeviceComparator;
    private CastingSessionManager castingManager;
    private CastingTileBinder castingTileBinder;
    private final Configuration configuration;
    private LinearLayout deviceListLayout;
    private Handler foregroundHandler;
    private boolean hasFetchedAmazonDevices;
    private boolean hasFetchedChromecastDevices;
    private TextView headerTextView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private boolean shouldFetchAmazonDevices;
    private boolean shouldFetchChromecastDevices;
    private UiChromecastDeviceListFeed uiChromecastFeed;
    private final PublishSubject<ViewGroup> subject = PublishSubject.create();
    private int amazonDevicesCount = 0;
    private CastingConnectionCallback castingConnectionListener = new CastingConnectionCallback() { // from class: com.amazon.music.castingviews.CastingDeviceListView.1
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(CastingDevice castingDevice) {
            if (CastingDeviceListView.this.castingTileBinder != null) {
                CastingDeviceListView.this.castingTileBinder.setLoading(false);
            }
            if (CastingDeviceListView.this.adapter != null) {
                CastingDeviceListView.this.castingDeviceComparator.setPriority(castingDevice);
                CastingDeviceListView.this.adapter.sortItems();
                CastingDeviceListView.this.adapter.notifyDataSetChanged();
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason terminationReason) {
            if (CastingDeviceListView.this.castingTileBinder != null) {
                CastingDeviceListView.this.castingTileBinder.setLoading(false);
            }
            if (CastingDeviceListView.this.adapter != null) {
                CastingDeviceListView.this.adapter.notifyDataSetChanged();
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException castingException) {
            if (CastingDeviceListView.this.castingTileBinder != null) {
                CastingDeviceListView.this.castingTileBinder.setLoading(false);
            }
        }
    };

    public CastingDeviceListView(Configuration configuration) {
        this.configuration = configuration;
        CastingTileBinder.setLocalTracksLoaded(configuration.hasTracksLoaded());
        if (configuration.alexaDevicesEnabled()) {
            CastingTileBinder.enableAlexaDevices();
        } else {
            CastingTileBinder.disableAlexaDevices(configuration.getDisabledReason());
        }
        if (configuration.chromecastDevicesEnabled()) {
            CastingTileBinder.enableChromecastDevices();
        } else {
            CastingTileBinder.disableChromecastDevices(configuration.getDisabledReason());
        }
        this.castingManager = CastingSessionManager.getInstance();
        this.foregroundHandler = new Handler(Looper.getMainLooper());
    }

    public void onDestroy() {
        LinearLayoutManager linearLayoutManager = this.linearLayoutManager;
        if (linearLayoutManager != null) {
            CastingUIMetricsLogger.recordCastingDeviceListContentView(linearLayoutManager.findLastVisibleItemPosition());
        }
        this.foregroundHandler = null;
        this.linearLayoutManager = null;
        this.adapter = null;
        this.deviceListLayout = null;
        this.headerTextView = null;
        this.recyclerView = null;
        UiChromecastDeviceListFeed uiChromecastDeviceListFeed = this.uiChromecastFeed;
        if (uiChromecastDeviceListFeed != null) {
            uiChromecastDeviceListFeed.stop();
            this.uiChromecastFeed = null;
        }
        this.castingManager.unregisterListener(this.castingConnectionListener);
    }

    public Observable<ViewGroup> getView(StyleSheet styleSheet) {
        createView(styleSheet);
        this.foregroundHandler.postDelayed(new Runnable() { // from class: com.amazon.music.castingviews.CastingDeviceListView.2
            @Override // java.lang.Runnable
            public void run() {
                CastingDeviceListView.this.fetchData();
            }
        }, 10L);
        return this.subject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchData() {
        UiCasting uiCastingCreateUiCasting = CastingFactory.createUiCasting(this.configuration);
        this.hasFetchedAmazonDevices = false;
        this.hasFetchedChromecastDevices = false;
        this.shouldFetchAmazonDevices = false;
        this.shouldFetchChromecastDevices = false;
        if (this.configuration.showAlexaDevices()) {
            LOG.debug("Fetching Amazon Devices");
            this.shouldFetchAmazonDevices = true;
            fetchAmazonDevices(uiCastingCreateUiCasting);
        }
        if (this.configuration.showChromecastDevices()) {
            this.shouldFetchChromecastDevices = true;
            LOG.debug("Fetching Chromecast Devices");
            fetchChromecastDevices(uiCastingCreateUiCasting);
        }
        if (!this.shouldFetchAmazonDevices && !this.shouldFetchChromecastDevices) {
            finishFetch();
        }
        this.castingManager.registerListener(this.castingConnectionListener);
    }

    private void fetchAmazonDevices(UiCasting uiCasting) {
        getAmazonDeviceList(uiCasting).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<UiDeviceListResult>() { // from class: com.amazon.music.castingviews.CastingDeviceListView.4
            @Override // rx.functions.Action1
            public void call(UiDeviceListResult uiDeviceListResult) {
                if (CastingDeviceListView.this.adapter == null) {
                    return;
                }
                List<Block> blocks = uiDeviceListResult.getBlocks();
                if (blocks != null) {
                    CastingDeviceListView.this.adapter.addItems(blocks);
                    CastingDeviceListView.this.amazonDevicesCount = blocks.size();
                }
                CastingDeviceListView.this.hasFetchedAmazonDevices = true;
                CastingDeviceListView.this.finishFetch();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.music.castingviews.CastingDeviceListView.5
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                CastingDeviceListView.this.hasFetchedAmazonDevices = true;
                CastingDeviceListView.this.finishFetch();
                CastingDeviceListView.this.subject.onError(th);
            }
        });
    }

    private void fetchChromecastDevices(UiCasting uiCasting) {
        UiChromecastDeviceListFeed uiChromecastDeviceListFeed = uiCasting.getUiChromecastDeviceListFeed();
        this.uiChromecastFeed = uiChromecastDeviceListFeed;
        uiChromecastDeviceListFeed.subscribe(new Observer<UiDeviceListResult>() { // from class: com.amazon.music.castingviews.CastingDeviceListView.6
            @Override // rx.Observer
            public void onError(Throwable th) {
            }

            @Override // rx.Observer
            public void onCompleted() {
                CastingDeviceListView.this.subject.onCompleted();
            }

            @Override // rx.Observer
            public void onNext(UiDeviceListResult uiDeviceListResult) {
                if (CastingDeviceListView.this.adapter == null) {
                    return;
                }
                List<Object> items = CastingDeviceListView.this.adapter.getItems();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < items.size(); i++) {
                    CastingTileModel castingTileModel = (CastingTileModel) items.get(i);
                    if (castingTileModel.category.isPresent() && CastingCategory.CHROMECAST.getName().equals(castingTileModel.category.get())) {
                        arrayList.add(castingTileModel);
                    }
                }
                if (uiDeviceListResult.getBlocks() != null && (!uiDeviceListResult.getBlocks().isEmpty() || !arrayList.isEmpty())) {
                    CastingDeviceListView.this.adapter.removeItems(arrayList);
                    CastingDeviceListView.this.adapter.addItems(uiDeviceListResult.getBlocks());
                }
                CastingDeviceListView.this.hasFetchedChromecastDevices = true;
                CastingDeviceListView.this.finishFetch();
            }
        });
        this.uiChromecastFeed.start();
    }

    private Observable<UiDeviceListResult> getAmazonDeviceList(final UiCasting uiCasting) {
        return Observable.create(new Observable.OnSubscribe<UiDeviceListResult>() { // from class: com.amazon.music.castingviews.CastingDeviceListView.7
            @Override // rx.functions.Action1
            public void call(Subscriber<? super UiDeviceListResult> subscriber) {
                try {
                    subscriber.onNext(uiCasting.getAmazonDeviceList());
                    subscriber.onCompleted();
                } catch (CastingException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private void createView(StyleSheet styleSheet) {
        Context activityContext = this.configuration.getActivityContext();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(activityContext).inflate(R$layout.casting_device_list, (ViewGroup) null);
        this.deviceListLayout = (LinearLayout) viewGroup.findViewById(R$id.deviceListRoot);
        this.headerTextView = (TextView) viewGroup.findViewById(R$id.titleView);
        this.recyclerView = (RecyclerView) viewGroup.findViewById(R$id.recyclerView);
        this.castingTileBinder = new CastingTileBinder(activityContext, styleSheet, this.configuration.wrapDeviceNameEnabled());
        CastingTroubleshootTileBinder castingTroubleshootTileBinder = new CastingTroubleshootTileBinder(activityContext, this.configuration.getHelpUrl(), styleSheet);
        this.castingDeviceComparator = new CastingDeviceComparator();
        if (this.castingManager.getCastingDevice() != null) {
            this.castingDeviceComparator.setPriority(this.castingManager.getCastingDevice());
        }
        UniversalAdapter universalAdapter = new UniversalAdapter(this.castingTileBinder, castingTroubleshootTileBinder);
        this.adapter = universalAdapter;
        universalAdapter.allowRebind(true);
        this.adapter.setComparator(this.castingDeviceComparator);
        this.adapter.sortOnAdd(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activityContext);
        this.linearLayoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.headerTextView.setText(R$string.casting_device_list_title_text);
        initStyle(styleSheet);
    }

    private void initStyle(StyleSheet styleSheet) {
        FontStyle fontStyle;
        if (styleSheet == null || (fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4)) == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(this.headerTextView, fontStyle);
    }

    private void updateTroubleshootButton() {
        Iterator<Object> it = this.adapter.getItems().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (next instanceof ButtonTileModel) {
                this.adapter.remove(next);
                break;
            }
        }
        if (this.adapter.getItemCount() > 0) {
            this.adapter.addItem(new ButtonTileModel(R$string.casting_device_list_troubleshooting_missing_text));
        } else {
            this.adapter.addItem(new ButtonTileModel(R$string.casting_device_list_troubleshooting_setup_text));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void finishFetch() {
        if (this.adapter == null) {
            return;
        }
        filterDeviceId(this.configuration.getDeviceId());
        if ((!this.shouldFetchChromecastDevices || this.hasFetchedChromecastDevices) && (!this.shouldFetchAmazonDevices || this.hasFetchedAmazonDevices)) {
            showDeviceListView();
        }
    }

    private void filterDeviceId(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        List<Object> items = this.adapter.getItems();
        for (int i = 0; i < items.size(); i++) {
            Object obj = items.get(i);
            if (obj instanceof CastingTileModel) {
                CastingTileModel castingTileModel = (CastingTileModel) obj;
                if (castingTileModel.targetId.isPresent() && castingTileModel.targetId.get().contains(str)) {
                    LOG.debug("Device ID '" + str + "' found in device list, removing it.");
                    this.adapter.remove(castingTileModel);
                }
            }
        }
    }

    private void showDeviceListView() {
        if (this.castingManager.isConnected()) {
            addConnectedDeviceIfMissing();
        }
        if (this.configuration.demoModeEnabled()) {
            this.headerTextView.setText(this.configuration.getDemoWarningMessage());
        } else {
            if (this.adapter.getItemCount() == 0) {
                this.headerTextView.setText(R$string.casting_device_list_title_error_text);
            } else {
                this.headerTextView.setText(R$string.casting_device_list_title_text);
            }
            updateTroubleshootButton();
        }
        this.subject.onNext(this.deviceListLayout);
    }

    private void addConnectedDeviceIfMissing() {
        CastingDevice lastDevice = this.castingManager.getLastDevice();
        if (lastDevice == null) {
            return;
        }
        CastingTileModel castingTileModelConvert = new CastingTileConverter().convert(lastDevice);
        List<Object> items = this.adapter.getItems();
        if (items != null) {
            for (Object obj : items) {
                if (obj != null) {
                    try {
                        if (((CastingTileModel) obj).targetId.get().equals(lastDevice.getTargetId())) {
                            return;
                        }
                    } catch (ClassCastException | NoSuchElementException unused) {
                        continue;
                    }
                }
            }
        }
        this.adapter.addItem(castingTileModelConvert);
    }

    public boolean hasAtLeastOneAmazonDevice() {
        return this.hasFetchedAmazonDevices && this.amazonDevicesCount > 0;
    }
}
