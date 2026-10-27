package com.android.car.radio.platform;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.radio.Announcement;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.os.HandlerThread;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.blankj.utilcode.util.ViewUtils;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.LogUtil;
import com.byd.music.view.AlarmView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
public class RadioManagerExt {
    private static final int HARDCODED_MODULE_INDEX = 0;
    private static final String TAG = "BcRadioApp.mgrext";
    private static Map<Integer, RadioTuner> mSessions = new HashMap();
    private List<RadioManager.BandDescriptor> mAmFmRegionConfig;
    private final Context mContext;
    private List<RadioManager.ModuleProperties> mModules;
    private final RadioManager mRadioManager;
    private final Object mLock = new Object();
    private final HandlerThread mCallbackHandlerThread = new HandlerThread("BcRadioApp.cbhandler");
    private boolean isAddAnnouncement = false;

    public RadioManagerExt(Context context) {
        this.mContext = (Context) Objects.requireNonNull(context);
        RadioManager radioManager = (RadioManager) context.getSystemService(Context.RADIO_SERVICE);
        this.mRadioManager = radioManager;
        if (radioManager == null) {
            LogUtil.e(TAG, "RadioManagerExt: mRadioManager is null !!!");
            throw new RuntimeException("get Context.RADIO_SERVICE failed!");
        }
        Objects.requireNonNull(radioManager, "RadioManager could not be loaded");
        this.mCallbackHandlerThread.start();
    }

    private List<RadioManager.BandDescriptor> reduceAmFmBands(RadioManager.BandDescriptor[] bandDescriptorArr) {
        if (bandDescriptorArr == null || bandDescriptorArr.length == 0) {
            return null;
        }
        final int region = bandDescriptorArr[0].getRegion();
        LogUtil.d(TAG, "Auto-selecting region " + region);
        return (List) Arrays.stream(bandDescriptorArr).filter(new Predicate() { // from class: com.android.car.radio.platform.-$$Lambda$RadioManagerExt$RqdIfUj9ECvKXGY4giLRHaH0Yy4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RadioManagerExt.lambda$reduceAmFmBands$0(region, (RadioManager.BandDescriptor) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$reduceAmFmBands$0(int i, RadioManager.BandDescriptor bandDescriptor) {
        return bandDescriptor.getRegion() == i;
    }

    private void initModules() {
        synchronized (this.mLock) {
            if (this.mModules != null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            this.mModules = arrayList;
            int iListModules = this.mRadioManager.listModules(arrayList);
            if (iListModules != 0) {
                LogUtil.w(TAG, "Couldn't get radio module list: " + iListModules);
                return;
            }
            if (this.mModules.size() == 0) {
                LogUtil.i(TAG, "No radio modules on this device");
                return;
            }
            this.mAmFmRegionConfig = reduceAmFmBands(this.mModules.get(0).getBands());
            for (RadioManager.ModuleProperties moduleProperties : this.mModules) {
                LogUtil.e(TAG, "zzl initModules: mm once = " + moduleProperties);
                Iterator<RadioManager.BandDescriptor> it = reduceAmFmBands(moduleProperties.getBands()).iterator();
                while (it.hasNext()) {
                    LogUtil.e(TAG, "zzl initModules: bb once = " + it.next());
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x00b4, code lost:
    
        com.byd.music.utils.LogUtil.w(com.android.car.radio.platform.RadioManagerExt.TAG, "openSession tuner close succeed! go to open");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.car.radio.platform.RadioTunerExt openSession(android.hardware.radio.RadioTuner.Callback r11, android.os.Handler r12) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.radio.platform.RadioManagerExt.openSession(android.hardware.radio.RadioTuner$Callback, android.os.Handler):com.android.car.radio.platform.RadioTunerExt");
    }

    public boolean addAnnouncementListener() {
        if (this.isAddAnnouncement) {
            return true;
        }
        synchronized (RadioManager.class) {
            if (this.isAddAnnouncement) {
                return true;
            }
            try {
                LogUtil.e(TAG, "mRadioManager addAnnouncementListener start.");
                this.mRadioManager.addAnnouncementListener(new HashSet<Integer>() { // from class: com.android.car.radio.platform.RadioManagerExt.1
                    {
                        add(1);
                        add(2);
                        add(3);
                        add(4);
                        add(5);
                        add(6);
                        add(7);
                        add(8);
                    }
                }, new Announcement.OnListUpdatedListener() { // from class: com.android.car.radio.platform.RadioManagerExt.2
                    @Override // android.hardware.radio.Announcement.OnListUpdatedListener
                    public void onListUpdated(Collection<Announcement> collection) {
                        LogUtil.e(RadioManagerExt.TAG, "Announcement onListUpdated for collection size = " + collection.size());
                        if (CollectionUtils.isEmpty(collection)) {
                            ViewUtils.runOnUiThread(new Runnable() { // from class: com.android.car.radio.platform.RadioManagerExt.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (RadioServiceWrapper.getInstance().trafficAlarmView != null) {
                                        RadioServiceWrapper.getInstance().trafficAlarmView.hideGlobalWindow();
                                    }
                                }
                            });
                            return;
                        }
                        for (final Announcement announcement : collection) {
                            LogUtil.e(RadioManagerExt.TAG, "Announcement onListUpdated for once = " + announcement.getSelector());
                            LogUtil.e(RadioManagerExt.TAG, "Announcement onListUpdated for once getType = " + announcement.getSelector().getPrimaryId().getType());
                            LogUtil.e(RadioManagerExt.TAG, "Announcement onListUpdated for once getValue = " + announcement.getSelector().getPrimaryId().getValue());
                            LogUtil.e(RadioManagerExt.TAG, "Announcement onListUpdated for once getRadioFrequency = " + RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getRadioFrequency());
                            LogUtil.e(RadioManagerExt.TAG, "Announcement onListUpdated for once getDAB_SID = " + RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getDAB_SID());
                            ViewUtils.runOnUiThread(new Runnable() { // from class: com.android.car.radio.platform.RadioManagerExt.2.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (RadioServiceWrapper.getInstance().isPlaying.get()) {
                                        if (announcement.getSelector().getPrimaryId().getType() == 1 && announcement.getSelector().getPrimaryId().getValue() == RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getRadioFrequency()) {
                                            if (SPUtils.getInstance().getBoolean("ta_settings_switch", true)) {
                                                if (RadioServiceWrapper.getInstance().trafficAlarmView == null) {
                                                    RadioServiceWrapper.getInstance().trafficAlarmView = new AlarmView(Utils.getApp());
                                                }
                                                if (RadioServiceWrapper.getInstance().trafficAlarmView.getShowing()) {
                                                    return;
                                                }
                                                RadioServiceWrapper.getInstance().trafficAlarmView.initTraffic();
                                                RadioServiceWrapper.getInstance().trafficAlarmView.showGlobalWindow();
                                                return;
                                            }
                                            return;
                                        }
                                        if (announcement.getSelector().getPrimaryId().getType() == 5 && announcement.getSelector().getPrimaryId().getValue() == RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getDAB_SID() && SPUtils.getInstance().getBoolean("ta_settings_switch", true)) {
                                            if (RadioServiceWrapper.getInstance().trafficAlarmView == null) {
                                                RadioServiceWrapper.getInstance().trafficAlarmView = new AlarmView(Utils.getApp());
                                            }
                                            if (RadioServiceWrapper.getInstance().trafficAlarmView.getShowing()) {
                                                return;
                                            }
                                            RadioServiceWrapper.getInstance().trafficAlarmView.initTraffic();
                                            RadioServiceWrapper.getInstance().trafficAlarmView.showGlobalWindow();
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
                LogUtil.e(TAG, "mRadioManager addAnnouncementListener finish.");
                this.isAddAnnouncement = true;
                return true;
            } catch (Throwable th) {
                LogUtil.e(TAG, "mRadioManager addAnnouncementListener error.");
                th.printStackTrace();
                return false;
            }
        }
    }

    public List<RadioManager.BandDescriptor> getAmFmRegionConfig() {
        initModules();
        return this.mAmFmRegionConfig;
    }

    public Bitmap getMetadataImage(long j) {
        if (j == 0) {
            return null;
        }
        int i = (int) (j >>> 32);
        int i2 = (int) (j & (-1));
        RadioTuner radioTuner = mSessions.get(Integer.valueOf(i));
        if (radioTuner == null) {
            return null;
        }
        return radioTuner.getMetadataImage(i2);
    }
}
