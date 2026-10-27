package com.amazon.music.station.presenter;

import android.os.Handler;
import android.os.Looper;
import com.amazon.music.station.StationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationManager;
import com.amazon.music.station.model.RefinementItem;
import com.amazon.music.station.model.SectionItem;
import com.amazon.music.station.view.StationListView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class StationListPresenter {
    private final ExecutorService executorService;
    private Handler handler;
    private RefinementItem selectedRefinement;
    private final StationManager stationManager;
    private StationListView view;

    public StationListPresenter(StationManager stationManager) {
        Validate.notNull(stationManager, "stationManager can't be null", new Object[0]);
        this.executorService = Executors.newSingleThreadExecutor();
        this.stationManager = stationManager;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void start(StationListView stationListView) {
        setView(stationListView);
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                StationListPresenter.this.getRefinements();
            }
        });
    }

    public void resume(StationListView stationListView) {
        setView(stationListView);
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.2
            @Override // java.lang.Runnable
            public void run() {
                if (StationListPresenter.this.selectedRefinement == null) {
                    StationListPresenter.this.getRefinements();
                } else {
                    StationListPresenter stationListPresenter = StationListPresenter.this;
                    stationListPresenter.getStationsForRefinement(stationListPresenter.selectedRefinement);
                }
            }
        });
    }

    public void refreshData() {
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    StationListPresenter.this.stationManager.rebuildCache();
                } catch (StationException e) {
                    StationListPresenter.this.notifyViewOnError(e);
                }
            }
        });
    }

    public synchronized void pause() {
        this.view = null;
    }

    public void setRefinement(final RefinementItem refinementItem) {
        Validate.notNull(refinementItem, "refinement can't be null", new Object[0]);
        this.selectedRefinement = refinementItem;
        this.executorService.submit(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.4
            @Override // java.lang.Runnable
            public void run() {
                StationListPresenter.this.getStationsForRefinement(refinementItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getRefinements() {
        try {
            HashMap map = new HashMap();
            for (SectionItem sectionItem : this.stationManager.getSectionItems()) {
                map.put(sectionItem, this.stationManager.getRefinementsBySection(sectionItem));
            }
            notifyViewOnRefinementLoaded(map);
        } catch (StationException e) {
            notifyViewOnError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getStationsForRefinement(RefinementItem refinementItem) {
        try {
            notifyViewOnStationItemsLoaded(refinementItem, this.stationManager.getStationItemsByRefinement(refinementItem));
        } catch (StationException e) {
            notifyViewOnError(e);
        }
    }

    private synchronized void setView(StationListView stationListView) {
        Validate.notNull(stationListView, "view can't be null", new Object[0]);
        this.view = stationListView;
    }

    private void notifyViewOnRefinementLoaded(final Map<SectionItem, List<RefinementItem>> map) {
        notifyInUiThread(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.5
            @Override // java.lang.Runnable
            public void run() {
                StationListPresenter.this.view.onRefinementItemLoaded(map);
            }
        });
    }

    private void notifyViewOnStationItemsLoaded(final RefinementItem refinementItem, final List<StationItem> list) {
        notifyInUiThread(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.6
            @Override // java.lang.Runnable
            public void run() {
                StationListPresenter.this.view.onStationItemsLoaded(refinementItem, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyViewOnError(final StationException stationException) {
        notifyInUiThread(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.7
            @Override // java.lang.Runnable
            public void run() {
                if (StationException.Reason.NETWORK_ERROR == stationException.getReason()) {
                    StationListPresenter.this.view.onNetworkError(stationException);
                } else {
                    StationListPresenter.this.view.onProcessingError(stationException);
                }
            }
        });
    }

    private void notifyInUiThread(final Runnable runnable) {
        this.handler.post(new Runnable() { // from class: com.amazon.music.station.presenter.StationListPresenter.8
            @Override // java.lang.Runnable
            public void run() {
                synchronized (StationListPresenter.this) {
                    if (StationListPresenter.this.view != null) {
                        runnable.run();
                    }
                }
            }
        });
    }
}
