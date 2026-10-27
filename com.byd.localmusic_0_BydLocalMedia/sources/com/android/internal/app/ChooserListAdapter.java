package com.android.internal.app;

import android.app.ActivityManager;
import android.app.prediction.AppPredictor;
import android.app.prediction.AppTarget;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.service.chooser.ChooserTarget;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.R;
import com.android.internal.app.ChooserActivity;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.ResolverListAdapter;
import com.android.internal.app.chooser.ChooserTargetInfo;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.app.chooser.MultiDisplayResolveInfo;
import com.android.internal.app.chooser.SelectableTargetInfo;
import com.android.internal.app.chooser.TargetInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
public class ChooserListAdapter extends ResolverListAdapter {
    public static final float CALLER_TARGET_SCORE_BOOST = 900.0f;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_DIRECT_SHARE_RANKING_SCORE = 1000;
    private static final int MAX_CHOOSER_TARGETS_PER_APP = 2;
    private static final int MAX_SERVICE_TARGET_APP = 8;
    private static final int MAX_SUGGESTED_APP_TARGETS = 4;
    public static final int NO_POSITION = -1;
    public static final float SHORTCUT_TARGET_SCORE_BOOST = 90.0f;
    private static final String TAG = "ChooserListAdapter";
    public static final int TARGET_BAD = -1;
    public static final int TARGET_CALLER = 0;
    public static final int TARGET_SERVICE = 1;
    public static final int TARGET_STANDARD = 2;
    public static final int TARGET_STANDARD_AZ = 3;
    private AppPredictor mAppPredictor;
    private AppPredictor.Callback mAppPredictorCallback;
    private boolean mAppendDirectShareEnabled;
    private int mAvailableServiceTargetsNum;
    private final ChooserActivity.BaseChooserTargetComparator mBaseTargetComparator;
    private final List<DisplayResolveInfo> mCallerTargets;
    private final ChooserListCommunicator mChooserListCommunicator;
    private final Map<ComponentName, Map<String, Integer>> mChooserTargetScores;
    private boolean mEnableStackedApps;
    private Map<DisplayResolveInfo, ResolverListAdapter.LoadIconTask> mIconLoaders;
    private boolean mListViewDataChanged;
    private final int mMaxShortcutTargetsPerApp;
    private int mNumShortcutResults;
    private final Map<ComponentName, Pair<List<ChooserTargetInfo>, Integer>> mParkingDirectShareTargets;
    private Set<ComponentName> mPendingChooserTargetService;
    private ChooserTargetInfo mPlaceHolderTargetInfo;
    private final SelectableTargetInfo.SelectableTargetInfoCommunicator mSelectableTargetInfoCommunicator;
    private final List<ChooserTargetInfo> mServiceTargets;
    private Set<ComponentName> mShortcutComponents;
    private List<DisplayResolveInfo> mSortedList;
    private int mValidServiceTargetsNum;

    interface ChooserListCommunicator extends ResolverListAdapter.ResolverListCommunicator {
        int getMaxRankedTargets();

        boolean isSendAction(Intent intent);

        void sendListViewUpdateMessage(UserHandle userHandle);
    }

    @Override // com.android.internal.app.ResolverListAdapter
    protected boolean alwaysShowSubLabel() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ChooserListAdapter(android.content.Context r13, java.util.List<android.content.Intent> r14, android.content.Intent[] r15, java.util.List<android.content.pm.ResolveInfo> r16, boolean r17, com.android.internal.app.ResolverListController r18, com.android.internal.app.ChooserListAdapter.ChooserListCommunicator r19, com.android.internal.app.chooser.SelectableTargetInfo.SelectableTargetInfoCommunicator r20, android.content.pm.PackageManager r21) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.ChooserListAdapter.<init>(android.content.Context, java.util.List, android.content.Intent[], java.util.List, boolean, com.android.internal.app.ResolverListController, com.android.internal.app.ChooserListAdapter$ChooserListCommunicator, com.android.internal.app.chooser.SelectableTargetInfo$SelectableTargetInfoCommunicator, android.content.pm.PackageManager):void");
    }

    AppPredictor getAppPredictor() {
        return this.mAppPredictor;
    }

    @Override // com.android.internal.app.ResolverListAdapter
    public void handlePackagesChanged() {
        createPlaceHolders();
        this.mChooserListCommunicator.onHandlePackagesChanged(this);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        if (this.mListViewDataChanged) {
            return;
        }
        this.mChooserListCommunicator.sendListViewUpdateMessage(getUserHandle());
        this.mListViewDataChanged = true;
    }

    void refreshListView() {
        if (this.mListViewDataChanged) {
            if (this.mAppendDirectShareEnabled) {
                appendServiceTargetsWithQuota();
            }
            super.notifyDataSetChanged();
        }
        this.mListViewDataChanged = false;
    }

    private void createPlaceHolders() {
        this.mNumShortcutResults = 0;
        this.mServiceTargets.clear();
        this.mValidServiceTargetsNum = 0;
        this.mParkingDirectShareTargets.clear();
        this.mPendingChooserTargetService.clear();
        this.mShortcutComponents.clear();
        for (int i = 0; i < this.mChooserListCommunicator.getMaxRankedTargets(); i++) {
            this.mServiceTargets.add(this.mPlaceHolderTargetInfo);
        }
    }

    @Override // com.android.internal.app.ResolverListAdapter
    View onCreateView(ViewGroup viewGroup) {
        return this.mInflater.inflate(R.layout.resolve_grid_item, viewGroup, false);
    }

    @Override // com.android.internal.app.ResolverListAdapter
    protected void onBindView(View view, TargetInfo targetInfo, int i) {
        ResolverListAdapter.ViewHolder viewHolder = (ResolverListAdapter.ViewHolder) view.getTag();
        if (targetInfo == null) {
            viewHolder.icon.setImageDrawable(this.mContext.getDrawable(R.drawable.resolver_icon_placeholder));
            return;
        }
        if (!(targetInfo instanceof DisplayResolveInfo)) {
            viewHolder.bindLabel(targetInfo.getDisplayLabel(), targetInfo.getExtendedInfo(), alwaysShowSubLabel());
            viewHolder.bindIcon(targetInfo);
            if (targetInfo instanceof SelectableTargetInfo) {
                DisplayResolveInfo displayResolveInfo = ((SelectableTargetInfo) targetInfo).getDisplayResolveInfo();
                String displayLabel = displayResolveInfo != null ? displayResolveInfo.getDisplayLabel() : "";
                CharSequence extendedInfo = targetInfo.getExtendedInfo();
                CharSequence[] charSequenceArr = new CharSequence[3];
                charSequenceArr[0] = targetInfo.getDisplayLabel();
                charSequenceArr[1] = extendedInfo != null ? extendedInfo : "";
                charSequenceArr[2] = displayLabel;
                viewHolder.updateContentDescription(String.join(" ", charSequenceArr));
            }
        } else {
            DisplayResolveInfo displayResolveInfo2 = (DisplayResolveInfo) targetInfo;
            viewHolder.bindLabel(displayResolveInfo2.getDisplayLabel(), displayResolveInfo2.getExtendedInfo(), alwaysShowSubLabel());
            ResolverListAdapter.LoadIconTask loadIconTask = this.mIconLoaders.get(displayResolveInfo2);
            if (loadIconTask == null) {
                ResolverListAdapter.LoadIconTask loadIconTask2 = new ResolverListAdapter.LoadIconTask(displayResolveInfo2, viewHolder);
                this.mIconLoaders.put(displayResolveInfo2, loadIconTask2);
                loadIconTask2.execute(new Void[0]);
            } else {
                loadIconTask.setViewHolder(viewHolder);
            }
        }
        if (targetInfo instanceof ChooserActivity.PlaceHolderTargetInfo) {
            viewHolder.text.setMaxWidth(this.mContext.getResources().getDimensionPixelSize(R.dimen.chooser_direct_share_label_placeholder_max_width));
            viewHolder.text.setBackground(this.mContext.getResources().getDrawable(R.drawable.chooser_direct_share_label_placeholder, this.mContext.getTheme()));
            viewHolder.itemView.setBackground(null);
        } else {
            viewHolder.text.setMaxWidth(Integer.MAX_VALUE);
            viewHolder.text.setBackground(null);
            viewHolder.itemView.setBackground(viewHolder.defaultItemViewBackground);
        }
        if (targetInfo instanceof MultiDisplayResolveInfo) {
            Drawable drawable = this.mContext.getDrawable(R.drawable.chooser_group_background);
            viewHolder.text.setPaddingRelative(0, 0, drawable.getIntrinsicWidth(), 0);
            viewHolder.text.setBackground(drawable);
        } else if (targetInfo.isPinned() && getPositionTargetType(i) == 2) {
            Drawable drawable2 = this.mContext.getDrawable(R.drawable.chooser_pinned_background);
            viewHolder.text.setPaddingRelative(drawable2.getIntrinsicWidth(), 0, 0, 0);
            viewHolder.text.setBackground(drawable2);
        } else {
            viewHolder.text.setBackground(null);
            viewHolder.text.setPaddingRelative(0, 0, 0, 0);
        }
    }

    void updateAlphabeticalList() {
        new AsyncTask<Void, Void, List<DisplayResolveInfo>>() { // from class: com.android.internal.app.ChooserListAdapter.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<DisplayResolveInfo> doInBackground(Void... voidArr) {
                ArrayList<DisplayResolveInfo> arrayList = new ArrayList();
                arrayList.addAll(ChooserListAdapter.this.mDisplayList);
                arrayList.addAll(ChooserListAdapter.this.mCallerTargets);
                if (!ChooserListAdapter.this.mEnableStackedApps) {
                    return arrayList;
                }
                HashMap map = new HashMap();
                for (DisplayResolveInfo displayResolveInfo : arrayList) {
                    String packageName = displayResolveInfo.getResolvedComponentName().getPackageName();
                    DisplayResolveInfo displayResolveInfo2 = (DisplayResolveInfo) map.get(packageName);
                    if (displayResolveInfo2 == null) {
                        map.put(packageName, displayResolveInfo);
                    } else if (displayResolveInfo2 instanceof MultiDisplayResolveInfo) {
                        ((MultiDisplayResolveInfo) displayResolveInfo2).addTarget(displayResolveInfo);
                    } else {
                        MultiDisplayResolveInfo multiDisplayResolveInfo = new MultiDisplayResolveInfo(packageName, displayResolveInfo2);
                        multiDisplayResolveInfo.addTarget(displayResolveInfo);
                        map.put(packageName, multiDisplayResolveInfo);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(map.values());
                Collections.sort(arrayList2, new ChooserActivity.AzInfoComparator(ChooserListAdapter.this.mContext));
                return arrayList2;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<DisplayResolveInfo> list) {
                ChooserListAdapter.this.mSortedList = list;
                ChooserListAdapter.this.notifyDataSetChanged();
            }
        }.execute(new Void[0]);
    }

    @Override // com.android.internal.app.ResolverListAdapter, android.widget.Adapter
    public int getCount() {
        return getRankedTargetCount() + getAlphaTargetCount() + getSelectableServiceTargetCount() + getCallerTargetCount();
    }

    @Override // com.android.internal.app.ResolverListAdapter
    public int getUnfilteredCount() {
        int unfilteredCount = super.getUnfilteredCount();
        if (unfilteredCount > this.mChooserListCommunicator.getMaxRankedTargets()) {
            unfilteredCount += this.mChooserListCommunicator.getMaxRankedTargets();
        }
        return unfilteredCount + getSelectableServiceTargetCount() + getCallerTargetCount();
    }

    public int getCallerTargetCount() {
        return this.mCallerTargets.size();
    }

    public int getSelectableServiceTargetCount() {
        Iterator<ChooserTargetInfo> it = this.mServiceTargets.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next() instanceof SelectableTargetInfo) {
                i++;
            }
        }
        return i;
    }

    public int getServiceTargetCount() {
        ChooserListCommunicator chooserListCommunicator = this.mChooserListCommunicator;
        if (!chooserListCommunicator.isSendAction(chooserListCommunicator.getTargetIntent()) || ActivityManager.isLowRamDeviceStatic()) {
            return 0;
        }
        return Math.min(this.mServiceTargets.size(), this.mChooserListCommunicator.getMaxRankedTargets());
    }

    int getAlphaTargetCount() {
        int size = this.mSortedList.size();
        if (this.mCallerTargets.size() + this.mDisplayList.size() > this.mChooserListCommunicator.getMaxRankedTargets()) {
            return size;
        }
        return 0;
    }

    public int getRankedTargetCount() {
        return Math.min(this.mChooserListCommunicator.getMaxRankedTargets() - getCallerTargetCount(), super.getCount());
    }

    public int getPositionTargetType(int i) {
        int serviceTargetCount = getServiceTargetCount();
        if (i < serviceTargetCount) {
            return 1;
        }
        int i2 = serviceTargetCount + 0;
        int callerTargetCount = getCallerTargetCount();
        if (i - i2 < callerTargetCount) {
            return 0;
        }
        int i3 = i2 + callerTargetCount;
        int rankedTargetCount = getRankedTargetCount();
        if (i - i3 < rankedTargetCount) {
            return 2;
        }
        return i - (i3 + rankedTargetCount) < getAlphaTargetCount() ? 3 : -1;
    }

    @Override // com.android.internal.app.ResolverListAdapter, android.widget.Adapter
    public TargetInfo getItem(int i) {
        return targetInfoForPosition(i, true);
    }

    @Override // com.android.internal.app.ResolverListAdapter
    public TargetInfo targetInfoForPosition(int i, boolean z) {
        int selectableServiceTargetCount;
        if (i == -1) {
            return null;
        }
        if (z) {
            selectableServiceTargetCount = getServiceTargetCount();
        } else {
            selectableServiceTargetCount = getSelectableServiceTargetCount();
        }
        if (i < selectableServiceTargetCount) {
            return this.mServiceTargets.get(i);
        }
        int i2 = selectableServiceTargetCount + 0;
        int callerTargetCount = getCallerTargetCount();
        int i3 = i - i2;
        if (i3 < callerTargetCount) {
            return this.mCallerTargets.get(i3);
        }
        int i4 = i2 + callerTargetCount;
        int rankedTargetCount = getRankedTargetCount();
        int i5 = i - i4;
        if (i5 < rankedTargetCount) {
            if (z) {
                return super.getItem(i5);
            }
            return getDisplayResolveInfo(i5);
        }
        int i6 = i - (i4 + rankedTargetCount);
        if (i6 >= getAlphaTargetCount() || this.mSortedList.isEmpty()) {
            return null;
        }
        return this.mSortedList.get(i6);
    }

    @Override // com.android.internal.app.ResolverListAdapter
    protected boolean shouldAddResolveInfo(DisplayResolveInfo displayResolveInfo) {
        Iterator<DisplayResolveInfo> it = this.mCallerTargets.iterator();
        while (it.hasNext()) {
            if (this.mResolverListCommunicator.resolveInfoMatch(displayResolveInfo.getResolveInfo(), it.next().getResolveInfo())) {
                return false;
            }
        }
        return super.shouldAddResolveInfo(displayResolveInfo);
    }

    public List<ChooserTargetInfo> getSurfacedTargetInfo() {
        return this.mServiceTargets.subList(0, Math.min(this.mChooserListCommunicator.getMaxRankedTargets(), getSelectableServiceTargetCount()));
    }

    public void addServiceResults(DisplayResolveInfo displayResolveInfo, List<ChooserTarget> list, int i, Map<ChooserTarget, ShortcutInfo> map, List<ChooserActivity.ChooserTargetServiceConnection> list2) {
        if (this.mAppendDirectShareEnabled) {
            parkTargetIntoMemory(displayResolveInfo, list, i, map, list2);
            return;
        }
        if (list.size() == 0) {
            return;
        }
        float baseScore = getBaseScore(displayResolveInfo, i);
        Collections.sort(list, this.mBaseTargetComparator);
        int i2 = 0;
        boolean z = i == 2 || i == 3;
        float f = 0.0f;
        int iMin = Math.min(list.size(), z ? this.mMaxShortcutTargetsPerApp : 2);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < iMin) {
            ChooserTarget chooserTarget = list.get(i3);
            float score = chooserTarget.getScore() * baseScore;
            float f2 = (i3 <= 0 || score < f) ? score : f * 0.95f;
            boolean zInsertServiceTarget = insertServiceTarget(new SelectableTargetInfo(this.mContext.createContextAsUser(getUserHandle(), i2), displayResolveInfo, chooserTarget, f2, this.mSelectableTargetInfoCommunicator, z ? map.get(chooserTarget) : null));
            if (zInsertServiceTarget && z) {
                this.mNumShortcutResults++;
            }
            z2 |= zInsertServiceTarget;
            i3++;
            f = f2;
            i2 = 0;
        }
        if (z2) {
            notifyDataSetChanged();
        }
    }

    public void addChooserTargetRankingScore(List<AppTarget> list) {
        Log.i(TAG, "addChooserTargetRankingScore " + list.size() + " targets score.");
        for (AppTarget appTarget : list) {
            if (appTarget.getShortcutInfo() != null) {
                ShortcutInfo shortcutInfo = appTarget.getShortcutInfo();
                if (shortcutInfo.getId().equals(ChooserActivity.CHOOSER_TARGET) && shortcutInfo.getActivity() != null) {
                    ComponentName activity = shortcutInfo.getActivity();
                    if (!this.mChooserTargetScores.containsKey(activity)) {
                        this.mChooserTargetScores.put(activity, new HashMap());
                    }
                    this.mChooserTargetScores.get(activity).put(shortcutInfo.getShortLabel().toString(), Integer.valueOf(appTarget.getRank()));
                }
            }
        }
        this.mChooserTargetScores.keySet().forEach(new Consumer() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$BPqHx-vC6QGjxaqE-AOsfarff-k
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$addChooserTargetRankingScore$0$ChooserListAdapter((ComponentName) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: rankTargetsWithinComponent, reason: merged with bridge method [inline-methods] */
    public void lambda$addChooserTargetRankingScore$0$ChooserListAdapter(ComponentName componentName) {
        if (this.mParkingDirectShareTargets.containsKey(componentName) && this.mChooserTargetScores.containsKey(componentName)) {
            final Map<String, Integer> map = this.mChooserTargetScores.get(componentName);
            Collections.sort(this.mParkingDirectShareTargets.get(componentName).first, new Comparator() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$m4s9b945UP7D1fhargxJSp6PfrQ
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ChooserListAdapter.lambda$rankTargetsWithinComponent$1(map, (ChooserTargetInfo) obj, (ChooserTargetInfo) obj2);
                }
            });
        }
    }

    static /* synthetic */ int lambda$rankTargetsWithinComponent$1(Map map, ChooserTargetInfo chooserTargetInfo, ChooserTargetInfo chooserTargetInfo2) {
        return ((Integer) map.getOrDefault(ChooserUtil.md5(chooserTargetInfo2.getChooserTarget().getTitle().toString()), 1000)).intValue() - ((Integer) map.getOrDefault(ChooserUtil.md5(chooserTargetInfo.getChooserTarget().getTitle().toString()), 1000)).intValue();
    }

    private void parkTargetIntoMemory(final DisplayResolveInfo displayResolveInfo, List<ChooserTarget> list, int i, final Map<ChooserTarget, ShortcutInfo> map, List<ChooserActivity.ChooserTargetServiceConnection> list2) {
        final ComponentName componentName;
        if (displayResolveInfo != null) {
            componentName = displayResolveInfo.getResolvedComponentName();
        } else {
            componentName = !list.isEmpty() ? list.get(0).getComponentName() : null;
        }
        Log.i(TAG, "parkTargetIntoMemory " + componentName + ", " + list.size() + " targets");
        this.mPendingChooserTargetService = (Set) list2.stream().map(new Function() { // from class: com.android.internal.app.-$$Lambda$eUIP3HCCOJv_84jco_cNoee_sJE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ChooserActivity.ChooserTargetServiceConnection) obj).getComponentName();
            }
        }).filter(new Predicate() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$9fcWpLCX10Fv87hEFnpnFupxJkA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ChooserListAdapter.lambda$parkTargetIntoMemory$2(componentName, (ComponentName) obj);
            }
        }).collect(Collectors.toSet());
        if (!list.isEmpty()) {
            boolean z = i == 2 || i == 3;
            final Context contextCreateContextAsUser = this.mContext.createContextAsUser(getUserHandle(), 0);
            final boolean z2 = z;
            List<ChooserTargetInfo> list3 = (List) list.stream().map(new Function() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$8HtMdVlUWw1aFqhES60XWwoBEJA
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$parkTargetIntoMemory$3$ChooserListAdapter(contextCreateContextAsUser, displayResolveInfo, z2, map, (ChooserTarget) obj);
                }
            }).collect(Collectors.toList());
            Pair<List<ChooserTargetInfo>, Integer> orDefault = this.mParkingDirectShareTargets.getOrDefault(componentName, new Pair<>(new ArrayList(), 0));
            for (ChooserTargetInfo chooserTargetInfo : list3) {
                if (!checkDuplicateTarget(chooserTargetInfo, orDefault.first) && !checkDuplicateTarget(chooserTargetInfo, this.mServiceTargets)) {
                    orDefault.first.add(chooserTargetInfo);
                    this.mAvailableServiceTargetsNum++;
                }
            }
            this.mParkingDirectShareTargets.put(componentName, orDefault);
            lambda$addChooserTargetRankingScore$0$ChooserListAdapter(componentName);
            if (z) {
                this.mShortcutComponents.add(componentName);
            }
        }
        notifyDataSetChanged();
    }

    static /* synthetic */ boolean lambda$parkTargetIntoMemory$2(ComponentName componentName, ComponentName componentName2) {
        return !componentName2.equals(componentName);
    }

    public /* synthetic */ SelectableTargetInfo lambda$parkTargetIntoMemory$3$ChooserListAdapter(Context context, DisplayResolveInfo displayResolveInfo, boolean z, Map map, ChooserTarget chooserTarget) {
        return new SelectableTargetInfo(context, displayResolveInfo, chooserTarget, chooserTarget.getScore(), this.mSelectableTargetInfoCommunicator, z ? (ShortcutInfo) map.get(chooserTarget) : null);
    }

    private void appendServiceTargetsWithQuota() {
        int maxRankedTargets = this.mChooserListCommunicator.getMaxRankedTargets();
        List<ComponentName> topComponentNames = getTopComponentNames(maxRankedTargets);
        float score = 0.0f;
        for (ComponentName componentName : topComponentNames) {
            if (this.mPendingChooserTargetService.contains(componentName) || this.mParkingDirectShareTargets.containsKey(componentName)) {
                score += super.getScore(componentName);
            }
        }
        boolean z = false;
        for (ComponentName componentName2 : topComponentNames) {
            if (this.mPendingChooserTargetService.contains(componentName2) || this.mParkingDirectShareTargets.containsKey(componentName2)) {
                int iRound = Math.round((maxRankedTargets * super.getScore(componentName2)) / score);
                if (this.mPendingChooserTargetService.contains(componentName2) && iRound >= 1) {
                    z = true;
                }
                if (this.mParkingDirectShareTargets.containsKey(componentName2)) {
                    Pair<List<ChooserTargetInfo>, Integer> pair = this.mParkingDirectShareTargets.get(componentName2);
                    List<ChooserTargetInfo> list = pair.first;
                    int iIntValue = pair.second.intValue();
                    while (iIntValue < iRound && !list.isEmpty()) {
                        if (!checkDuplicateTarget(list.get(0), this.mServiceTargets)) {
                            this.mServiceTargets.add(this.mValidServiceTargetsNum, list.get(0));
                            this.mValidServiceTargetsNum++;
                            iIntValue++;
                        }
                        list.remove(0);
                    }
                    Log.i(TAG, " appendServiceTargetsWithQuota component=" + componentName2 + " appendNum=" + (iIntValue - pair.second.intValue()));
                    this.mParkingDirectShareTargets.put(componentName2, new Pair<>(list, Integer.valueOf(iIntValue)));
                }
            }
        }
        if (z) {
            return;
        }
        fillAllServiceTargets();
    }

    private void fillAllServiceTargets() {
        if (this.mParkingDirectShareTargets.isEmpty()) {
            return;
        }
        Log.i(TAG, " fillAllServiceTargets");
        for (ComponentName componentName : getTopComponentNames(8)) {
            if (this.mParkingDirectShareTargets.containsKey(componentName)) {
                this.mParkingDirectShareTargets.get(componentName).first.stream().filter(new Predicate() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$t1fxZkPY66xDVAw4rhWJz79dzdo
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.f$0.lambda$fillAllServiceTargets$4$ChooserListAdapter((ChooserTargetInfo) obj);
                    }
                }).forEach(new Consumer() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$Nwx4mGR8tVNyHPJ4o1Cv_NzUD2w
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$fillAllServiceTargets$5$ChooserListAdapter((ChooserTargetInfo) obj);
                    }
                });
                this.mParkingDirectShareTargets.remove(componentName);
            }
        }
        this.mParkingDirectShareTargets.entrySet().stream().filter(new Predicate() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$Khh7sa_N2QGpDvgRJ--JLOOB_s8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$fillAllServiceTargets$6$ChooserListAdapter((Map.Entry) obj);
            }
        }).map(new Function() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$-y1PMD8VhneiKR1eaA1eOCTG3yI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ChooserListAdapter.lambda$fillAllServiceTargets$7((Map.Entry) obj);
            }
        }).map(new Function() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$ykAZrfXZaaDsIfVL4lfLhsTJwOM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ChooserListAdapter.lambda$fillAllServiceTargets$8((Pair) obj);
            }
        }).forEach(new Consumer() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$N30acEPcmN7qH6i_USfrNyHBL1o
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$fillAllServiceTargets$9$ChooserListAdapter((List) obj);
            }
        });
        this.mParkingDirectShareTargets.clear();
    }

    public /* synthetic */ boolean lambda$fillAllServiceTargets$4$ChooserListAdapter(ChooserTargetInfo chooserTargetInfo) {
        return !checkDuplicateTarget(chooserTargetInfo, this.mServiceTargets);
    }

    public /* synthetic */ void lambda$fillAllServiceTargets$5$ChooserListAdapter(ChooserTargetInfo chooserTargetInfo) {
        this.mServiceTargets.add(this.mValidServiceTargetsNum, chooserTargetInfo);
        this.mValidServiceTargetsNum++;
    }

    public /* synthetic */ boolean lambda$fillAllServiceTargets$6$ChooserListAdapter(Map.Entry entry) {
        return this.mShortcutComponents.contains(entry.getKey());
    }

    static /* synthetic */ Pair lambda$fillAllServiceTargets$7(Map.Entry entry) {
        return (Pair) entry.getValue();
    }

    static /* synthetic */ List lambda$fillAllServiceTargets$8(Pair pair) {
        return (List) pair.first;
    }

    public /* synthetic */ void lambda$fillAllServiceTargets$9$ChooserListAdapter(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ChooserTargetInfo chooserTargetInfo = (ChooserTargetInfo) it.next();
            if (!checkDuplicateTarget(chooserTargetInfo, this.mServiceTargets)) {
                this.mServiceTargets.add(this.mValidServiceTargetsNum, chooserTargetInfo);
                this.mValidServiceTargetsNum++;
            }
        }
    }

    private boolean checkDuplicateTarget(ChooserTargetInfo chooserTargetInfo, List<ChooserTargetInfo> list) {
        Iterator<ChooserTargetInfo> it = list.iterator();
        while (it.hasNext()) {
            if (chooserTargetInfo.isSimilar(it.next())) {
                return true;
            }
        }
        return false;
    }

    int getNumServiceTargetsForExpand() {
        return this.mAppendDirectShareEnabled ? this.mAvailableServiceTargetsNum : this.mNumShortcutResults;
    }

    public float getBaseScore(DisplayResolveInfo displayResolveInfo, int i) {
        if (displayResolveInfo == null) {
            return 900.0f;
        }
        if (i == 3) {
            return 90.0f;
        }
        float score = super.getScore(displayResolveInfo);
        return i == 2 ? score * 90.0f : score;
    }

    static /* synthetic */ boolean lambda$completeServiceTargetLoading$10(ChooserTargetInfo chooserTargetInfo) {
        return chooserTargetInfo instanceof ChooserActivity.PlaceHolderTargetInfo;
    }

    public void completeServiceTargetLoading() {
        this.mServiceTargets.removeIf(new Predicate() { // from class: com.android.internal.app.-$$Lambda$ChooserListAdapter$wCx26bRTLs1mYJZo1f9xyVwjoCY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ChooserListAdapter.lambda$completeServiceTargetLoading$10((ChooserTargetInfo) obj);
            }
        });
        if (this.mAppendDirectShareEnabled) {
            fillAllServiceTargets();
        }
        if (this.mServiceTargets.isEmpty()) {
            this.mServiceTargets.add(new ChooserActivity.EmptyTargetInfo());
        }
        notifyDataSetChanged();
    }

    private boolean insertServiceTarget(ChooserTargetInfo chooserTargetInfo) {
        if (this.mServiceTargets.size() == 1 && (this.mServiceTargets.get(0) instanceof ChooserActivity.EmptyTargetInfo)) {
            return false;
        }
        Iterator<ChooserTargetInfo> it = this.mServiceTargets.iterator();
        while (it.hasNext()) {
            if (chooserTargetInfo.isSimilar(it.next())) {
                return false;
            }
        }
        int size = this.mServiceTargets.size();
        float modifiedScore = chooserTargetInfo.getModifiedScore();
        for (int i = 0; i < Math.min(size, this.mChooserListCommunicator.getMaxRankedTargets()); i++) {
            ChooserTargetInfo chooserTargetInfo2 = this.mServiceTargets.get(i);
            if (chooserTargetInfo2 == null) {
                this.mServiceTargets.set(i, chooserTargetInfo);
                return true;
            }
            if (modifiedScore > chooserTargetInfo2.getModifiedScore()) {
                this.mServiceTargets.add(i, chooserTargetInfo);
                return true;
            }
        }
        if (size >= this.mChooserListCommunicator.getMaxRankedTargets()) {
            return false;
        }
        this.mServiceTargets.add(chooserTargetInfo);
        return true;
    }

    public ChooserTarget getChooserTargetForValue(int i) {
        return this.mServiceTargets.get(i).getChooserTarget();
    }

    @Override // com.android.internal.app.ResolverListAdapter
    AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>> createSortingTask(final boolean z) {
        return new AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>>() { // from class: com.android.internal.app.ChooserListAdapter.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<ResolverActivity.ResolvedComponentInfo> doInBackground(List<ResolverActivity.ResolvedComponentInfo>... listArr) {
                ChooserListAdapter.this.mResolverListController.topK(listArr[0], ChooserListAdapter.this.mChooserListCommunicator.getMaxRankedTargets());
                return listArr[0];
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<ResolverActivity.ResolvedComponentInfo> list) {
                ChooserListAdapter.this.processSortedList(list, z);
                if (z) {
                    ChooserListAdapter.this.mChooserListCommunicator.updateProfileViewButton();
                    ChooserListAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    public void setAppPredictor(AppPredictor appPredictor) {
        this.mAppPredictor = appPredictor;
    }

    public void setAppPredictorCallback(AppPredictor.Callback callback) {
        this.mAppPredictorCallback = callback;
    }

    public void destroyAppPredictor() {
        if (getAppPredictor() != null) {
            getAppPredictor().unregisterPredictionUpdates(this.mAppPredictorCallback);
            getAppPredictor().destroy();
            setAppPredictor(null);
        }
    }
}
