package com.amazon.music.uicasting;

import com.amazon.music.casting.Casting;
import com.amazon.music.casting.providers.ChromecastDeviceListFeed;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
public class UiChromecastDeviceListFeed {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) UiChromecastDeviceListFeed.class);
    private final ChromecastDeviceListFeed feed;
    private final UiConverter uiConverter = new UiConverter();

    public UiChromecastDeviceListFeed(Casting casting) {
        this.feed = casting.getChromecastDeviceListFeed();
    }

    public void subscribe(Observer<UiDeviceListResult> observer) {
        this.feed.getFeed().map(new Func1<List, UiDeviceListResult>() { // from class: com.amazon.music.uicasting.UiChromecastDeviceListFeed.1
            @Override // rx.functions.Func1
            public UiDeviceListResult call(List list) {
                UiConverter unused = UiChromecastDeviceListFeed.this.uiConverter;
                return UiConverter.resultToUiResult(list);
            }
        }).onBackpressureLatest().observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void start() {
        this.feed.start();
    }

    public void stop() {
        this.feed.stop();
    }
}
