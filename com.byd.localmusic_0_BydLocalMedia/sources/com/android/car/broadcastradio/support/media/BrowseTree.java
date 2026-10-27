package com.android.car.broadcastradio.support.media;

import android.graphics.Bitmap;
import android.hardware.radio.ProgramList;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.media.MediaDescription;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.util.Log;
import com.android.car.broadcastradio.support.Program;
import com.android.car.broadcastradio.support.R;
import com.android.car.broadcastradio.support.platform.ImageResolver;
import com.android.car.broadcastradio.support.platform.ProgramSelectorExt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseTree {
    public static final String ACTION_PLAY_BROADCASTRADIO = "android.car.intent.action.PLAY_BROADCASTRADIO";
    public static final long BCRADIO_FOLDER_TYPE_BAND = 3;
    public static final long BCRADIO_FOLDER_TYPE_FAVORITES = 2;
    public static final long BCRADIO_FOLDER_TYPE_PROGRAMS = 1;
    public static final String EXTRA_BCRADIO_BAND_NAME_EN = "android.media.extra.EXTRA_BCRADIO_BAND_NAME_EN";
    public static final String EXTRA_BCRADIO_FOLDER_TYPE = "android.media.extra.EXTRA_BCRADIO_FOLDER_TYPE";
    private static final String NODEPREFIX_AMFMCHANNEL = "amfm:";
    private static final String NODEPREFIX_BAND = "band:";
    private static final String NODEPREFIX_PROGRAM = "program:";
    public static final String NODE_BAND_AM = "band:am";
    public static final String NODE_BAND_DAB = "band:dab";
    public static final String NODE_BAND_FM = "band:fm";
    public static final String NODE_FAVORITES = "favorites_id";
    public static final String NODE_PROGRAMS = "programs_id";
    private static final String NODE_ROOT = "root_id";
    private static final String TAG = "BcRadioApp.BrowseTree";
    private final MediaBrowserService mBrowserService;
    private boolean mDABEnabled;
    Set<Program> mFavorites;
    private List<MediaBrowser.MediaItem> mFavoritesCache;
    private final ImageResolver mImageResolver;
    private ProgramList mProgramList;
    private List<MediaBrowser.MediaItem> mProgramListCache;
    private List<RadioManager.ProgramInfo> mProgramListSnapshot;
    private List<MediaBrowser.MediaItem> mRootChildren;
    private final MediaBrowserService.BrowserRoot mRoot = new MediaBrowserService.BrowserRoot("root_id", null);
    private final Object mLock = new Object();
    private final AmFmChannelList mAmChannels = new AmFmChannelList(NODE_BAND_AM, R.string.radio_am_text, "AM");
    private final AmFmChannelList mFmChannels = new AmFmChannelList(NODE_BAND_FM, R.string.radio_fm_text, "FM");
    private final ProgramList.OnCompleteListener mProgramListCompleteListener = new ProgramList.OnCompleteListener() { // from class: com.android.car.broadcastradio.support.media.-$$Lambda$BrowseTree$k_9bWl0MM_xL5WPkgHj5QEPtE70
        @Override // android.hardware.radio.ProgramList.OnCompleteListener
        public final void onComplete() {
            this.f$0.onProgramListUpdated();
        }
    };
    private final List<Runnable> mProgramListTasks = new ArrayList();
    private final Map<String, ProgramSelector> mProgramSelectors = new HashMap();

    public BrowseTree(MediaBrowserService mediaBrowserService, ImageResolver imageResolver) {
        this.mBrowserService = (MediaBrowserService) Objects.requireNonNull(mediaBrowserService);
        this.mImageResolver = imageResolver;
    }

    public MediaBrowserService.BrowserRoot getRoot() {
        return this.mRoot;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaBrowser.MediaItem createChild(MediaDescription.Builder builder, String str, String str2, ProgramSelector programSelector, Bitmap bitmap) {
        return new MediaBrowser.MediaItem(builder.setMediaId(str).setMediaUri(ProgramSelectorExt.toUri(programSelector)).setTitle(str2).setIconBitmap(bitmap).build(), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaBrowser.MediaItem createFolder(MediaDescription.Builder builder, String str, String str2, boolean z, boolean z2, long j, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong(EXTRA_BCRADIO_FOLDER_TYPE, j);
        MediaDescription mediaDescriptionBuild = builder.setMediaId(str).setTitle(str2).setExtras(bundle).build();
        int i = z ? 1 : 0;
        if (z2) {
            i |= 2;
        }
        return new MediaBrowser.MediaItem(mediaDescriptionBuild, i);
    }

    public void setAmFmRegionConfig(List<RadioManager.BandDescriptor> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            for (RadioManager.BandDescriptor bandDescriptor : list) {
                long lowerLimit = bandDescriptor.getLowerLimit();
                if (ProgramSelectorExt.isAmFrequency(lowerLimit)) {
                    arrayList.add(bandDescriptor);
                } else if (ProgramSelectorExt.isFmFrequency(lowerLimit)) {
                    arrayList2.add(bandDescriptor);
                }
            }
        }
        synchronized (this.mLock) {
            this.mAmChannels.setBands(arrayList);
            this.mFmChannels.setBands(arrayList2);
            this.mRootChildren = null;
            this.mBrowserService.notifyChildrenChanged("root_id");
        }
    }

    public void setDABEnabled(boolean z) {
        synchronized (this.mLock) {
            if (this.mDABEnabled != z) {
                this.mDABEnabled = z;
                this.mRootChildren = null;
                this.mBrowserService.notifyChildrenChanged("root_id");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgramListUpdated() {
        synchronized (this.mLock) {
            this.mProgramListSnapshot = this.mProgramList.toList();
            this.mProgramListCache = null;
            this.mBrowserService.notifyChildrenChanged(NODE_PROGRAMS);
            Iterator<Runnable> it = this.mProgramListTasks.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            this.mProgramListTasks.clear();
        }
    }

    public void setProgramList(ProgramList programList) {
        synchronized (this.mLock) {
            if (this.mProgramList != null) {
                this.mProgramList.removeOnCompleteListener(this.mProgramListCompleteListener);
            }
            this.mProgramList = programList;
            if (programList != null) {
                programList.addOnCompleteListener(this.mProgramListCompleteListener);
            }
            this.mBrowserService.notifyChildrenChanged("root_id");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<android.media.browse.MediaBrowser.MediaItem> getPrograms() {
        /*
            r11 = this;
            java.lang.Object r0 = r11.mLock
            monitor-enter(r0)
            java.util.List<android.hardware.radio.RadioManager$ProgramInfo> r1 = r11.mProgramListSnapshot     // Catch: java.lang.Throwable -> L88
            r2 = 0
            if (r1 != 0) goto L11
            java.lang.String r1 = "BcRadioApp.BrowseTree"
            java.lang.String r3 = "There is no snapshot of the program list"
            android.util.Log.w(r1, r3)     // Catch: java.lang.Throwable -> L88
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            return r2
        L11:
            java.util.List<android.media.browse.MediaBrowser$MediaItem> r1 = r11.mProgramListCache     // Catch: java.lang.Throwable -> L88
            if (r1 == 0) goto L19
            java.util.List<android.media.browse.MediaBrowser$MediaItem> r1 = r11.mProgramListCache     // Catch: java.lang.Throwable -> L88
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            return r1
        L19:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L88
            r1.<init>()     // Catch: java.lang.Throwable -> L88
            r11.mProgramListCache = r1     // Catch: java.lang.Throwable -> L88
            android.media.MediaDescription$Builder r1 = new android.media.MediaDescription$Builder     // Catch: java.lang.Throwable -> L88
            r1.<init>()     // Catch: java.lang.Throwable -> L88
            java.util.List<android.hardware.radio.RadioManager$ProgramInfo> r3 = r11.mProgramListSnapshot     // Catch: java.lang.Throwable -> L88
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L88
        L2b:
            boolean r4 = r3.hasNext()     // Catch: java.lang.Throwable -> L88
            if (r4 == 0) goto L75
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Throwable -> L88
            android.hardware.radio.RadioManager$ProgramInfo r4 = (android.hardware.radio.RadioManager.ProgramInfo) r4     // Catch: java.lang.Throwable -> L88
            android.hardware.radio.ProgramSelector r5 = r4.getSelector()     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = selectorToMediaId(r5)     // Catch: java.lang.Throwable -> L88
            java.util.Map<java.lang.String, android.hardware.radio.ProgramSelector> r7 = r11.mProgramSelectors     // Catch: java.lang.Throwable -> L88
            r7.put(r6, r5)     // Catch: java.lang.Throwable -> L88
            android.hardware.radio.RadioMetadata r5 = r4.getMetadata()     // Catch: java.lang.Throwable -> L88
            if (r5 == 0) goto L61
            com.android.car.broadcastradio.support.platform.ImageResolver r7 = r11.mImageResolver     // Catch: java.lang.Throwable -> L88
            if (r7 == 0) goto L61
            java.lang.String r7 = "android.hardware.radio.metadata.ICON"
            long r7 = com.android.car.broadcastradio.support.platform.RadioMetadataExt.getGlobalBitmapId(r5, r7)     // Catch: java.lang.Throwable -> L88
            r9 = 0
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L61
            com.android.car.broadcastradio.support.platform.ImageResolver r5 = r11.mImageResolver     // Catch: java.lang.Throwable -> L88
            android.graphics.Bitmap r5 = r5.resolve(r7)     // Catch: java.lang.Throwable -> L88
            goto L62
        L61:
            r5 = r2
        L62:
            java.util.List<android.media.browse.MediaBrowser$MediaItem> r7 = r11.mProgramListCache     // Catch: java.lang.Throwable -> L88
            r8 = 0
            java.lang.String r8 = com.android.car.broadcastradio.support.platform.ProgramInfoExt.getProgramName(r4, r8)     // Catch: java.lang.Throwable -> L88
            android.hardware.radio.ProgramSelector r4 = r4.getSelector()     // Catch: java.lang.Throwable -> L88
            android.media.browse.MediaBrowser$MediaItem r4 = createChild(r1, r6, r8, r4, r5)     // Catch: java.lang.Throwable -> L88
            r7.add(r4)     // Catch: java.lang.Throwable -> L88
            goto L2b
        L75:
            java.util.List<android.media.browse.MediaBrowser$MediaItem> r1 = r11.mProgramListCache     // Catch: java.lang.Throwable -> L88
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L88
            if (r1 != 0) goto L84
            java.lang.String r1 = "BcRadioApp.BrowseTree"
            java.lang.String r2 = "Program list is empty"
            android.util.Log.v(r1, r2)     // Catch: java.lang.Throwable -> L88
        L84:
            java.util.List<android.media.browse.MediaBrowser$MediaItem> r1 = r11.mProgramListCache     // Catch: java.lang.Throwable -> L88
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            return r1
        L88:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L88
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.broadcastradio.support.media.BrowseTree.getPrograms():java.util.List");
    }

    private void sendPrograms(final MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
        synchronized (this.mLock) {
            if (this.mProgramListSnapshot != null) {
                result.sendResult(getPrograms());
            } else {
                Log.d(TAG, "Program list is not ready yet");
                result.detach();
                this.mProgramListTasks.add(new Runnable() { // from class: com.android.car.broadcastradio.support.media.-$$Lambda$BrowseTree$UhvxZXkSG3Fr9ELzBC7wS4Rq7Fk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$sendPrograms$0$BrowseTree(result);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$sendPrograms$0$BrowseTree(MediaBrowserService.Result result) {
        result.sendResult(getPrograms());
    }

    public void setFavorites(Set<Program> set) {
        synchronized (this.mLock) {
            boolean z = true;
            if ((this.mFavorites == null) == (set == null)) {
                z = false;
            }
            this.mFavorites = set;
            this.mFavoritesCache = null;
            this.mBrowserService.notifyChildrenChanged(NODE_FAVORITES);
            if (z) {
                this.mBrowserService.notifyChildrenChanged("root_id");
            }
        }
    }

    private List<MediaBrowser.MediaItem> getFavorites() {
        synchronized (this.mLock) {
            if (this.mFavorites == null) {
                return null;
            }
            if (this.mFavoritesCache != null) {
                return this.mFavoritesCache;
            }
            this.mFavoritesCache = new ArrayList();
            MediaDescription.Builder builder = new MediaDescription.Builder();
            for (Program program : this.mFavorites) {
                ProgramSelector selector = program.getSelector();
                String strSelectorToMediaId = selectorToMediaId(selector);
                this.mProgramSelectors.putIfAbsent(strSelectorToMediaId, selector);
                this.mFavoritesCache.add(createChild(builder, strSelectorToMediaId, program.getName(), selector, program.getIcon()));
            }
            return this.mFavoritesCache;
        }
    }

    private List<MediaBrowser.MediaItem> getRootChildren() {
        synchronized (this.mLock) {
            if (this.mRootChildren != null) {
                return this.mRootChildren;
            }
            this.mRootChildren = new ArrayList();
            MediaDescription.Builder builder = new MediaDescription.Builder();
            if (this.mProgramList != null) {
                this.mRootChildren.add(createFolder(builder, NODE_PROGRAMS, this.mBrowserService.getString(R.string.program_list_text), true, false, 1L, null));
            }
            if (this.mFavorites != null) {
                this.mRootChildren.add(createFolder(builder, NODE_FAVORITES, this.mBrowserService.getString(R.string.favorites_list_text), true, true, 2L, null));
            }
            MediaBrowser.MediaItem bandRoot = this.mAmChannels.getBandRoot();
            if (bandRoot != null) {
                this.mRootChildren.add(bandRoot);
            }
            MediaBrowser.MediaItem bandRoot2 = this.mFmChannels.getBandRoot();
            if (bandRoot2 != null) {
                this.mRootChildren.add(bandRoot2);
            }
            if (this.mDABEnabled) {
                this.mRootChildren.add(createFolder(builder, NODE_BAND_DAB, this.mBrowserService.getString(R.string.radio_dab_text), false, true, 3L, null));
            }
            return this.mRootChildren;
        }
    }

    private class AmFmChannelList {
        private final int mBandName;
        private final String mBandNameEn;
        private List<RadioManager.BandDescriptor> mBands;
        private List<MediaBrowser.MediaItem> mChannels;
        public final String mMediaId;

        private AmFmChannelList(String str, int i, String str2) {
            this.mMediaId = (String) Objects.requireNonNull(str);
            this.mBandName = i;
            this.mBandNameEn = (String) Objects.requireNonNull(str2);
        }

        public void setBands(List<RadioManager.BandDescriptor> list) {
            synchronized (BrowseTree.this.mLock) {
                this.mBands = list;
                this.mChannels = null;
                BrowseTree.this.mBrowserService.notifyChildrenChanged(this.mMediaId);
            }
        }

        private boolean isEmpty() {
            List<RadioManager.BandDescriptor> list = this.mBands;
            if (list == null) {
                Log.w(BrowseTree.TAG, "AM/FM configuration not set");
                return true;
            }
            return list.isEmpty();
        }

        public MediaBrowser.MediaItem getBandRoot() {
            if (isEmpty()) {
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putString(BrowseTree.EXTRA_BCRADIO_BAND_NAME_EN, this.mBandNameEn);
            return BrowseTree.createFolder(new MediaDescription.Builder(), this.mMediaId, BrowseTree.this.mBrowserService.getString(this.mBandName), true, true, 3L, bundle);
        }

        public List<MediaBrowser.MediaItem> getChannels() {
            synchronized (BrowseTree.this.mLock) {
                if (this.mChannels != null) {
                    return this.mChannels;
                }
                if (isEmpty()) {
                    return null;
                }
                this.mChannels = new ArrayList();
                MediaDescription.Builder builder = new MediaDescription.Builder();
                for (RadioManager.BandDescriptor bandDescriptor : this.mBands) {
                    int upperLimit = bandDescriptor.getUpperLimit();
                    int spacing = bandDescriptor.getSpacing();
                    for (int lowerLimit = bandDescriptor.getLowerLimit(); lowerLimit <= upperLimit; lowerLimit += spacing) {
                        ProgramSelector programSelectorCreateAmFmSelector = ProgramSelectorExt.createAmFmSelector(lowerLimit);
                        this.mChannels.add(BrowseTree.createChild(builder, BrowseTree.NODEPREFIX_AMFMCHANNEL + lowerLimit, ProgramSelectorExt.getDisplayName(programSelectorCreateAmFmSelector, 0), programSelectorCreateAmFmSelector, null));
                    }
                }
                return this.mChannels;
            }
        }
    }

    public void loadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
        if (str == null || result == null) {
            return;
        }
        if ("root_id".equals(str)) {
            result.sendResult(getRootChildren());
            return;
        }
        if (NODE_PROGRAMS.equals(str)) {
            sendPrograms(result);
            return;
        }
        if (NODE_FAVORITES.equals(str)) {
            result.sendResult(getFavorites());
            return;
        }
        if (str.equals(this.mAmChannels.mMediaId)) {
            result.sendResult(this.mAmChannels.getChannels());
            return;
        }
        if (str.equals(this.mFmChannels.mMediaId)) {
            result.sendResult(this.mFmChannels.getChannels());
            return;
        }
        Log.w(TAG, "Invalid parent media ID: " + str);
        result.sendResult(null);
    }

    private static String selectorToMediaId(ProgramSelector programSelector) {
        ProgramSelector.Identifier primaryId = programSelector.getPrimaryId();
        return NODEPREFIX_PROGRAM + primaryId.getType() + '/' + primaryId.getValue();
    }

    public ProgramSelector parseMediaId(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(NODEPREFIX_AMFMCHANNEL)) {
            try {
                return ProgramSelectorExt.createAmFmSelector(Integer.parseInt(str.substring(5)));
            } catch (NumberFormatException e) {
                Log.e(TAG, "Invalid frequency", e);
                return null;
            }
        }
        if (str.startsWith(NODEPREFIX_PROGRAM)) {
            return this.mProgramSelectors.get(str);
        }
        if (str.equals(NODE_FAVORITES)) {
            Set<Program> set = this.mFavorites;
            if (set == null || set.isEmpty()) {
                return null;
            }
            return this.mFavorites.iterator().next().getSelector();
        }
        if (str.equals(NODE_PROGRAMS)) {
            List<RadioManager.ProgramInfo> list = this.mProgramListSnapshot;
            if (list == null || list.isEmpty()) {
                return null;
            }
            return this.mProgramListSnapshot.get(0).getSelector();
        }
        if (str.equals(NODE_BAND_AM)) {
            if (this.mAmChannels.mBands == null || this.mAmChannels.mBands.isEmpty()) {
                return null;
            }
            return ProgramSelectorExt.createAmFmSelector(((RadioManager.BandDescriptor) this.mAmChannels.mBands.get(0)).getLowerLimit());
        }
        if (!str.equals(NODE_BAND_FM) || this.mFmChannels.mBands == null || this.mFmChannels.mBands.isEmpty()) {
            return null;
        }
        return ProgramSelectorExt.createAmFmSelector(((RadioManager.BandDescriptor) this.mFmChannels.mBands.get(0)).getLowerLimit());
    }
}
