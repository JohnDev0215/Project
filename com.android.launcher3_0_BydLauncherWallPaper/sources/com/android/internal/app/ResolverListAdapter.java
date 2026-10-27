package com.android.internal.app;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.PermissionChecker;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.app.chooser.TargetInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ResolverListAdapter extends BaseAdapter {
    private static final String TAG = "ResolverListAdapter";
    private static ColorMatrixColorFilter sSuspendedMatrixColorFilter;
    private final List<ResolveInfo> mBaseResolveList;
    protected final Context mContext;
    private boolean mFilterLastUsed;
    private final int mIconDpi;
    protected final LayoutInflater mInflater;
    private final Intent[] mInitialIntents;
    private final List<Intent> mIntents;
    private final boolean mIsAudioCaptureDevice;
    private boolean mIsTabLoaded;
    protected ResolveInfo mLastChosen;
    private DisplayResolveInfo mOtherProfile;
    private int mPlaceholderCount;
    private final PackageManager mPm;
    private Runnable mPostListReadyRunnable;
    final ResolverListCommunicator mResolverListCommunicator;
    ResolverListController mResolverListController;
    private List<ResolverActivity.ResolvedComponentInfo> mUnfilteredResolveList;
    private int mLastChosenPosition = -1;
    List<DisplayResolveInfo> mDisplayList = new ArrayList();

    interface ResolverListCommunicator {
        Intent getReplacementIntent(ActivityInfo activityInfo, Intent intent);

        Intent getTargetIntent();

        void onHandlePackagesChanged(ResolverListAdapter resolverListAdapter);

        void onPostListReady(ResolverListAdapter resolverListAdapter, boolean z, boolean z2);

        boolean resolveInfoMatch(ResolveInfo resolveInfo, ResolveInfo resolveInfo2);

        void sendVoiceChoicesIfNeeded();

        boolean shouldGetActivityMetadata();

        void updateProfileViewButton();

        boolean useLayoutWithDefault();
    }

    protected boolean alwaysShowSubLabel() {
        return false;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ResolverListAdapter(Context context, List<Intent> list, Intent[] intentArr, List<ResolveInfo> list2, boolean z, ResolverListController resolverListController, ResolverListCommunicator resolverListCommunicator, boolean z2) {
        this.mContext = context;
        this.mIntents = list;
        this.mInitialIntents = intentArr;
        this.mBaseResolveList = list2;
        this.mInflater = LayoutInflater.from(context);
        this.mPm = context.getPackageManager();
        this.mFilterLastUsed = z;
        this.mResolverListController = resolverListController;
        this.mResolverListCommunicator = resolverListCommunicator;
        this.mIsAudioCaptureDevice = z2;
        this.mIconDpi = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getLauncherLargeIconDensity();
    }

    public void handlePackagesChanged() {
        this.mResolverListCommunicator.onHandlePackagesChanged(this);
    }

    public void setPlaceholderCount(int i) {
        this.mPlaceholderCount = i;
    }

    public int getPlaceholderCount() {
        return this.mPlaceholderCount;
    }

    public DisplayResolveInfo getFilteredItem() {
        int i;
        if (!this.mFilterLastUsed || (i = this.mLastChosenPosition) < 0) {
            return null;
        }
        return this.mDisplayList.get(i);
    }

    public DisplayResolveInfo getOtherProfile() {
        return this.mOtherProfile;
    }

    public int getFilteredPosition() {
        int i;
        if (!this.mFilterLastUsed || (i = this.mLastChosenPosition) < 0) {
            return -1;
        }
        return i;
    }

    public boolean hasFilteredItem() {
        return this.mFilterLastUsed && this.mLastChosen != null;
    }

    public float getScore(DisplayResolveInfo displayResolveInfo) {
        return this.mResolverListController.getScore(displayResolveInfo);
    }

    public float getScore(ComponentName componentName) {
        return this.mResolverListController.getScore(componentName);
    }

    public List<ComponentName> getTopComponentNames(int i) {
        return this.mResolverListController.getTopComponentNames(i);
    }

    public void updateModel(ComponentName componentName) {
        this.mResolverListController.updateModel(componentName);
    }

    public void updateChooserCounts(String str, String str2) {
        this.mResolverListController.updateChooserCounts(str, getUserHandle().getIdentifier(), str2);
    }

    List<ResolverActivity.ResolvedComponentInfo> getUnfilteredResolveList() {
        return this.mUnfilteredResolveList;
    }

    protected boolean rebuildList(boolean z) {
        List<ResolverActivity.ResolvedComponentInfo> resolversForIntent;
        this.mOtherProfile = null;
        this.mLastChosen = null;
        this.mLastChosenPosition = -1;
        this.mDisplayList.clear();
        this.mIsTabLoaded = false;
        if (this.mBaseResolveList != null) {
            resolversForIntent = new ArrayList<>();
            this.mUnfilteredResolveList = resolversForIntent;
            this.mResolverListController.addResolveListDedupe(resolversForIntent, this.mResolverListCommunicator.getTargetIntent(), this.mBaseResolveList);
        } else {
            resolversForIntent = this.mResolverListController.getResolversForIntent(true, this.mResolverListCommunicator.shouldGetActivityMetadata(), this.mIntents);
            this.mUnfilteredResolveList = resolversForIntent;
            if (resolversForIntent == null) {
                processSortedList(resolversForIntent, z);
                return true;
            }
            ArrayList<ResolverActivity.ResolvedComponentInfo> arrayListFilterIneligibleActivities = this.mResolverListController.filterIneligibleActivities(resolversForIntent, true);
            if (arrayListFilterIneligibleActivities != null) {
                this.mUnfilteredResolveList = arrayListFilterIneligibleActivities;
            }
        }
        Iterator<ResolverActivity.ResolvedComponentInfo> it = resolversForIntent.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolverActivity.ResolvedComponentInfo next = it.next();
            ResolveInfo resolveInfoAt = next.getResolveInfoAt(0);
            if (resolveInfoAt.targetUserId != -2) {
                Intent replacementIntent = this.mResolverListCommunicator.getReplacementIntent(resolveInfoAt.activityInfo, next.getIntentAt(0));
                this.mOtherProfile = new DisplayResolveInfo(next.getIntentAt(0), resolveInfoAt, resolveInfoAt.loadLabel(this.mPm), resolveInfoAt.loadLabel(this.mPm), replacementIntent != null ? replacementIntent : this.mResolverListCommunicator.getReplacementIntent(resolveInfoAt.activityInfo, this.mResolverListCommunicator.getTargetIntent()), makePresentationGetter(resolveInfoAt));
                resolversForIntent.remove(next);
            }
        }
        if (this.mOtherProfile == null) {
            try {
                this.mLastChosen = this.mResolverListController.getLastChosen();
            } catch (RemoteException e) {
                Log.d(TAG, "Error calling getLastChosenActivity\n" + e);
            }
        }
        setPlaceholderCount(0);
        if (resolversForIntent != null && resolversForIntent.size() > 0) {
            ArrayList<ResolverActivity.ResolvedComponentInfo> arrayListFilterLowPriority = this.mResolverListController.filterLowPriority(resolversForIntent, this.mUnfilteredResolveList == resolversForIntent);
            if (arrayListFilterLowPriority != null) {
                this.mUnfilteredResolveList = arrayListFilterLowPriority;
            }
            if (resolversForIntent.size() > 1) {
                int size = resolversForIntent.size();
                if (this.mResolverListCommunicator.useLayoutWithDefault()) {
                    size--;
                }
                setPlaceholderCount(size);
                createSortingTask(z).execute(resolversForIntent);
                postListReadyRunnable(z, false);
                return false;
            }
            processSortedList(resolversForIntent, z);
            return true;
        }
        processSortedList(resolversForIntent, z);
        return true;
    }

    AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>> createSortingTask(final boolean z) {
        return new AsyncTask<List<ResolverActivity.ResolvedComponentInfo>, Void, List<ResolverActivity.ResolvedComponentInfo>>() { // from class: com.android.internal.app.ResolverListAdapter.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<ResolverActivity.ResolvedComponentInfo> doInBackground(List<ResolverActivity.ResolvedComponentInfo>... listArr) {
                ResolverListAdapter.this.mResolverListController.sort(listArr[0]);
                return listArr[0];
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<ResolverActivity.ResolvedComponentInfo> list) {
                ResolverListAdapter.this.processSortedList(list, z);
                ResolverListAdapter.this.notifyDataSetChanged();
                if (z) {
                    ResolverListAdapter.this.mResolverListCommunicator.updateProfileViewButton();
                }
            }
        };
    }

    protected void processSortedList(List<ResolverActivity.ResolvedComponentInfo> list, boolean z) {
        if (list != null && list.size() != 0) {
            if (this.mInitialIntents != null) {
                int i = 0;
                while (true) {
                    Intent[] intentArr = this.mInitialIntents;
                    if (i >= intentArr.length) {
                        break;
                    }
                    Intent intent = intentArr[i];
                    if (intent != null) {
                        ActivityInfo activityInfoResolveActivityInfo = intent.resolveActivityInfo(this.mPm, 0);
                        if (activityInfoResolveActivityInfo == null) {
                            Log.w(TAG, "No activity found for " + intent);
                        } else {
                            ResolveInfo resolveInfo = new ResolveInfo();
                            resolveInfo.activityInfo = activityInfoResolveActivityInfo;
                            UserManager userManager = (UserManager) this.mContext.getSystemService("user");
                            if (intent instanceof LabeledIntent) {
                                LabeledIntent labeledIntent = (LabeledIntent) intent;
                                resolveInfo.resolvePackageName = labeledIntent.getSourcePackage();
                                resolveInfo.labelRes = labeledIntent.getLabelResource();
                                resolveInfo.nonLocalizedLabel = labeledIntent.getNonLocalizedLabel();
                                resolveInfo.icon = labeledIntent.getIconResource();
                                resolveInfo.iconResourceId = resolveInfo.icon;
                            }
                            if (userManager.isManagedProfile()) {
                                resolveInfo.noResourceId = true;
                                resolveInfo.icon = 0;
                            }
                            addResolveInfo(new DisplayResolveInfo(intent, resolveInfo, resolveInfo.loadLabel(this.mPm), null, intent, makePresentationGetter(resolveInfo)));
                        }
                    }
                    i++;
                }
            }
            for (ResolverActivity.ResolvedComponentInfo resolvedComponentInfo : list) {
                if (resolvedComponentInfo.getResolveInfoAt(0) != null) {
                    addResolveInfoWithAlternates(resolvedComponentInfo);
                }
            }
        }
        this.mResolverListCommunicator.sendVoiceChoicesIfNeeded();
        postListReadyRunnable(z, true);
        this.mIsTabLoaded = true;
    }

    void postListReadyRunnable(final boolean z, final boolean z2) {
        if (this.mPostListReadyRunnable == null) {
            this.mPostListReadyRunnable = new Runnable() { // from class: com.android.internal.app.ResolverListAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    ResolverListAdapter.this.mResolverListCommunicator.onPostListReady(ResolverListAdapter.this, z, z2);
                    ResolverListAdapter.this.mPostListReadyRunnable = null;
                }
            };
            this.mContext.getMainThreadHandler().post(this.mPostListReadyRunnable);
        }
    }

    private void addResolveInfoWithAlternates(ResolverActivity.ResolvedComponentInfo resolvedComponentInfo) {
        int count = resolvedComponentInfo.getCount();
        Intent intentAt = resolvedComponentInfo.getIntentAt(0);
        ResolveInfo resolveInfoAt = resolvedComponentInfo.getResolveInfoAt(0);
        Intent replacementIntent = this.mResolverListCommunicator.getReplacementIntent(resolveInfoAt.activityInfo, intentAt);
        Intent replacementIntent2 = this.mResolverListCommunicator.getReplacementIntent(resolveInfoAt.activityInfo, this.mResolverListCommunicator.getTargetIntent());
        if (replacementIntent != null) {
            replacementIntent2 = replacementIntent;
        }
        DisplayResolveInfo displayResolveInfo = new DisplayResolveInfo(intentAt, resolveInfoAt, replacementIntent2, makePresentationGetter(resolveInfoAt));
        displayResolveInfo.setPinned(resolvedComponentInfo.isPinned());
        if (resolvedComponentInfo.isPinned()) {
            Log.i(TAG, "Pinned item: " + resolvedComponentInfo.name);
        }
        addResolveInfo(displayResolveInfo);
        if (replacementIntent == intentAt) {
            for (int i = 1; i < count; i++) {
                displayResolveInfo.addAlternateSourceIntent(resolvedComponentInfo.getIntentAt(i));
            }
        }
        updateLastChosenPosition(resolveInfoAt);
    }

    private void updateLastChosenPosition(ResolveInfo resolveInfo) {
        if (this.mOtherProfile != null) {
            this.mLastChosenPosition = -1;
            return;
        }
        ResolveInfo resolveInfo2 = this.mLastChosen;
        if (resolveInfo2 != null && resolveInfo2.activityInfo.packageName.equals(resolveInfo.activityInfo.packageName) && this.mLastChosen.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
            this.mLastChosenPosition = this.mDisplayList.size() - 1;
        }
    }

    private void addResolveInfo(DisplayResolveInfo displayResolveInfo) {
        if (displayResolveInfo == null || displayResolveInfo.getResolveInfo() == null || displayResolveInfo.getResolveInfo().targetUserId != -2 || !shouldAddResolveInfo(displayResolveInfo)) {
            return;
        }
        this.mDisplayList.add(displayResolveInfo);
        Log.i(TAG, "Add DisplayResolveInfo component: " + displayResolveInfo.getResolvedComponentName() + ", intent component: " + displayResolveInfo.getResolvedIntent().getComponent());
    }

    protected boolean shouldAddResolveInfo(DisplayResolveInfo displayResolveInfo) {
        Iterator<DisplayResolveInfo> it = this.mDisplayList.iterator();
        while (it.hasNext()) {
            if (this.mResolverListCommunicator.resolveInfoMatch(displayResolveInfo.getResolveInfo(), it.next().getResolveInfo())) {
                return false;
            }
        }
        return true;
    }

    public ResolveInfo resolveInfoForPosition(int i, boolean z) {
        TargetInfo targetInfoTargetInfoForPosition = targetInfoForPosition(i, z);
        if (targetInfoTargetInfoForPosition != null) {
            return targetInfoTargetInfoForPosition.getResolveInfo();
        }
        return null;
    }

    public TargetInfo targetInfoForPosition(int i, boolean z) {
        if (z) {
            return getItem(i);
        }
        if (this.mDisplayList.size() > i) {
            return this.mDisplayList.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int size;
        List<DisplayResolveInfo> list = this.mDisplayList;
        if (list == null || list.isEmpty()) {
            size = this.mPlaceholderCount;
        } else {
            size = this.mDisplayList.size();
        }
        return (!this.mFilterLastUsed || this.mLastChosenPosition < 0) ? size : size - 1;
    }

    public int getUnfilteredCount() {
        return this.mDisplayList.size();
    }

    @Override // android.widget.Adapter
    public TargetInfo getItem(int i) {
        int i2;
        if (this.mFilterLastUsed && (i2 = this.mLastChosenPosition) >= 0 && i >= i2) {
            i++;
        }
        if (this.mDisplayList.size() > i) {
            return this.mDisplayList.get(i);
        }
        return null;
    }

    public int getDisplayResolveInfoCount() {
        return this.mDisplayList.size();
    }

    public DisplayResolveInfo getDisplayResolveInfo(int i) {
        return this.mDisplayList.get(i);
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = createView(viewGroup);
        }
        onBindView(view, getItem(i), i);
        return view;
    }

    public final View createView(ViewGroup viewGroup) {
        View viewOnCreateView = onCreateView(viewGroup);
        viewOnCreateView.setTag(new ViewHolder(viewOnCreateView));
        return viewOnCreateView;
    }

    View onCreateView(ViewGroup viewGroup) {
        return this.mInflater.inflate(R.layout.resolve_list_item, viewGroup, false);
    }

    public final void bindView(int i, View view) {
        onBindView(view, getItem(i), i);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onBindView(android.view.View r5, com.android.internal.app.chooser.TargetInfo r6, int r7) {
        /*
            r4 = this;
            java.lang.Object r5 = r5.getTag()
            com.android.internal.app.ResolverListAdapter$ViewHolder r5 = (com.android.internal.app.ResolverListAdapter.ViewHolder) r5
            if (r6 != 0) goto L17
            android.widget.ImageView r5 = r5.icon
            android.content.Context r4 = r4.mContext
            r6 = 17303412(0x1080774, float:2.4984602E-38)
            android.graphics.drawable.Drawable r4 = r4.getDrawable(r6)
            r5.setImageDrawable(r4)
            return
        L17:
            boolean r7 = r6 instanceof com.android.internal.app.chooser.DisplayResolveInfo
            r0 = 0
            if (r7 == 0) goto L2f
            r1 = r6
            com.android.internal.app.chooser.DisplayResolveInfo r1 = (com.android.internal.app.chooser.DisplayResolveInfo) r1
            boolean r2 = r1.hasDisplayLabel()
            if (r2 != 0) goto L2f
            com.android.internal.app.ResolverListAdapter$LoadLabelTask r1 = r4.getLoadLabelTask(r1, r5)
            java.lang.Void[] r2 = new java.lang.Void[r0]
            r1.execute(r2)
            goto L3e
        L2f:
            java.lang.CharSequence r1 = r6.getDisplayLabel()
            java.lang.CharSequence r2 = r6.getExtendedInfo()
            boolean r3 = r4.alwaysShowSubLabel()
            r5.bindLabel(r1, r2, r3)
        L3e:
            if (r7 == 0) goto L54
            r7 = r6
            com.android.internal.app.chooser.DisplayResolveInfo r7 = (com.android.internal.app.chooser.DisplayResolveInfo) r7
            boolean r1 = r7.hasDisplayIcon()
            if (r1 != 0) goto L54
            com.android.internal.app.ResolverListAdapter$LoadIconTask r6 = new com.android.internal.app.ResolverListAdapter$LoadIconTask
            r6.<init>(r7, r5)
            java.lang.Void[] r4 = new java.lang.Void[r0]
            r6.execute(r4)
            goto L57
        L54:
            r5.bindIcon(r6)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.ResolverListAdapter.onBindView(android.view.View, com.android.internal.app.chooser.TargetInfo, int):void");
    }

    protected LoadLabelTask getLoadLabelTask(DisplayResolveInfo displayResolveInfo, ViewHolder viewHolder) {
        return new LoadLabelTask(displayResolveInfo, viewHolder);
    }

    public void onDestroy() {
        if (this.mPostListReadyRunnable != null) {
            this.mContext.getMainThreadHandler().removeCallbacks(this.mPostListReadyRunnable);
            this.mPostListReadyRunnable = null;
        }
        ResolverListController resolverListController = this.mResolverListController;
        if (resolverListController != null) {
            resolverListController.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ColorMatrixColorFilter getSuspendedColorMatrix() {
        if (sSuspendedMatrixColorFilter == null) {
            ColorMatrix colorMatrix = new ColorMatrix();
            float[] array = colorMatrix.getArray();
            array[0] = 0.5f;
            array[6] = 0.5f;
            array[12] = 0.5f;
            float f = 127;
            array[4] = f;
            array[9] = f;
            array[14] = f;
            ColorMatrix colorMatrix2 = new ColorMatrix();
            colorMatrix2.setSaturation(0.0f);
            colorMatrix2.preConcat(colorMatrix);
            sSuspendedMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix2);
        }
        return sSuspendedMatrixColorFilter;
    }

    ActivityInfoPresentationGetter makePresentationGetter(ActivityInfo activityInfo) {
        return new ActivityInfoPresentationGetter(this.mContext, this.mIconDpi, activityInfo);
    }

    ResolveInfoPresentationGetter makePresentationGetter(ResolveInfo resolveInfo) {
        return new ResolveInfoPresentationGetter(this.mContext, this.mIconDpi, resolveInfo);
    }

    Drawable loadIconForResolveInfo(ResolveInfo resolveInfo) {
        return makePresentationGetter(resolveInfo).getIcon(getUserHandle());
    }

    void loadFilteredItemIconTaskAsync(final ImageView imageView) {
        final DisplayResolveInfo filteredItem = getFilteredItem();
        if (imageView == null || filteredItem == null) {
            return;
        }
        new AsyncTask<Void, Void, Drawable>() { // from class: com.android.internal.app.ResolverListAdapter.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Drawable doInBackground(Void... voidArr) {
                return ResolverListAdapter.this.loadIconForResolveInfo(filteredItem.getResolveInfo());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        }.execute(new Void[0]);
    }

    public UserHandle getUserHandle() {
        return this.mResolverListController.getUserHandle();
    }

    protected List<ResolverActivity.ResolvedComponentInfo> getResolversForUser(UserHandle userHandle) {
        return this.mResolverListController.getResolversForIntentAsUser(true, this.mResolverListCommunicator.shouldGetActivityMetadata(), this.mIntents, userHandle);
    }

    protected List<Intent> getIntents() {
        return this.mIntents;
    }

    protected boolean isTabLoaded() {
        return this.mIsTabLoaded;
    }

    protected void markTabLoaded() {
        this.mIsTabLoaded = true;
    }

    static class ViewHolder {
        public Drawable defaultItemViewBackground;
        public ImageView icon;
        public View itemView;
        public TextView text;
        public TextView text2;

        ViewHolder(View view) {
            this.itemView = view;
            this.defaultItemViewBackground = view.getBackground();
            this.text = (TextView) view.findViewById(16908308);
            this.text2 = (TextView) view.findViewById(16908309);
            this.icon = (ImageView) view.findViewById(16908294);
        }

        public void bindLabel(CharSequence charSequence, CharSequence charSequence2, boolean z) {
            this.text.setText(charSequence);
            if (TextUtils.equals(charSequence, charSequence2)) {
                charSequence2 = null;
            }
            this.text2.setText(charSequence2);
            if (z || charSequence2 != null) {
                this.text2.setVisibility(0);
            } else {
                this.text2.setVisibility(8);
            }
            this.itemView.setContentDescription(null);
        }

        public void updateContentDescription(String str) {
            this.itemView.setContentDescription(str);
        }

        public void bindIcon(TargetInfo targetInfo) {
            this.icon.setImageDrawable(targetInfo.getDisplayIcon(this.itemView.getContext()));
            if (targetInfo.isSuspended()) {
                this.icon.setColorFilter(ResolverListAdapter.getSuspendedColorMatrix());
            } else {
                this.icon.setColorFilter((ColorFilter) null);
            }
        }
    }

    protected class LoadLabelTask extends AsyncTask<Void, Void, CharSequence[]> {
        private final DisplayResolveInfo mDisplayResolveInfo;
        private final ViewHolder mHolder;

        protected LoadLabelTask(DisplayResolveInfo displayResolveInfo, ViewHolder viewHolder) {
            this.mDisplayResolveInfo = displayResolveInfo;
            this.mHolder = viewHolder;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public CharSequence[] doInBackground(Void... voidArr) {
            ResolveInfoPresentationGetter resolveInfoPresentationGetterMakePresentationGetter = ResolverListAdapter.this.makePresentationGetter(this.mDisplayResolveInfo.getResolveInfo());
            if (ResolverListAdapter.this.mIsAudioCaptureDevice) {
                ActivityInfo activityInfo = this.mDisplayResolveInfo.getResolveInfo().activityInfo;
                if (!(PermissionChecker.checkPermissionForPreflight(ResolverListAdapter.this.mContext, Manifest.permission.RECORD_AUDIO, -1, activityInfo.applicationInfo.uid, activityInfo.packageName) == 0)) {
                    return new CharSequence[]{resolveInfoPresentationGetterMakePresentationGetter.getLabel(), ResolverListAdapter.this.mContext.getString(R.string.usb_device_resolve_prompt_warn)};
                }
            }
            return new CharSequence[]{resolveInfoPresentationGetterMakePresentationGetter.getLabel(), resolveInfoPresentationGetterMakePresentationGetter.getSubLabel()};
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(CharSequence[] charSequenceArr) {
            this.mDisplayResolveInfo.setDisplayLabel(charSequenceArr[0]);
            this.mDisplayResolveInfo.setExtendedInfo(charSequenceArr[1]);
            this.mHolder.bindLabel(charSequenceArr[0], charSequenceArr[1], ResolverListAdapter.this.alwaysShowSubLabel());
        }
    }

    class LoadIconTask extends AsyncTask<Void, Void, Drawable> {
        protected final DisplayResolveInfo mDisplayResolveInfo;
        private ViewHolder mHolder;
        private final ResolveInfo mResolveInfo;

        LoadIconTask(DisplayResolveInfo displayResolveInfo, ViewHolder viewHolder) {
            this.mDisplayResolveInfo = displayResolveInfo;
            this.mResolveInfo = displayResolveInfo.getResolveInfo();
            this.mHolder = viewHolder;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Drawable doInBackground(Void... voidArr) {
            return ResolverListAdapter.this.loadIconForResolveInfo(this.mResolveInfo);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Drawable drawable) {
            DisplayResolveInfo otherProfile = ResolverListAdapter.this.getOtherProfile();
            DisplayResolveInfo displayResolveInfo = this.mDisplayResolveInfo;
            if (otherProfile == displayResolveInfo) {
                ResolverListAdapter.this.mResolverListCommunicator.updateProfileViewButton();
            } else {
                displayResolveInfo.setDisplayIcon(drawable);
                this.mHolder.bindIcon(this.mDisplayResolveInfo);
            }
        }

        public void setViewHolder(ViewHolder viewHolder) {
            this.mHolder = viewHolder;
            viewHolder.bindIcon(this.mDisplayResolveInfo);
        }
    }

    public static class ResolveInfoPresentationGetter extends ActivityInfoPresentationGetter {
        private final ResolveInfo mRi;

        public ResolveInfoPresentationGetter(Context context, int i, ResolveInfo resolveInfo) {
            super(context, i, resolveInfo.activityInfo);
            this.mRi = resolveInfo;
        }

        @Override // com.android.internal.app.ResolverListAdapter.ActivityInfoPresentationGetter, com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        Drawable getIconSubstituteInternal() {
            Drawable drawableLoadIconFromResource = null;
            try {
                if (this.mRi.resolvePackageName != null && this.mRi.icon != 0) {
                    drawableLoadIconFromResource = loadIconFromResource(this.mPm.getResourcesForApplication(this.mRi.resolvePackageName), this.mRi.icon);
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(ResolverListAdapter.TAG, "SUBSTITUTE_SHARE_TARGET_APP_NAME_AND_ICON permission granted but couldn't find resources for package", e);
            }
            return drawableLoadIconFromResource == null ? super.getIconSubstituteInternal() : drawableLoadIconFromResource;
        }

        @Override // com.android.internal.app.ResolverListAdapter.ActivityInfoPresentationGetter, com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        String getAppSubLabelInternal() {
            return (String) this.mRi.loadLabel(this.mPm);
        }
    }

    public static class ActivityInfoPresentationGetter extends TargetPresentationGetter {
        private final ActivityInfo mActivityInfo;

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ Drawable getIcon(UserHandle userHandle) {
            return super.getIcon(userHandle);
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ Bitmap getIconBitmap(UserHandle userHandle) {
            return super.getIconBitmap(userHandle);
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ String getLabel() {
            return super.getLabel();
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        public /* bridge */ /* synthetic */ String getSubLabel() {
            return super.getSubLabel();
        }

        public ActivityInfoPresentationGetter(Context context, int i, ActivityInfo activityInfo) {
            super(context, i, activityInfo.applicationInfo);
            this.mActivityInfo = activityInfo;
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        Drawable getIconSubstituteInternal() {
            try {
                if (this.mActivityInfo.icon != 0) {
                    return loadIconFromResource(this.mPm.getResourcesForApplication(this.mActivityInfo.applicationInfo), this.mActivityInfo.icon);
                }
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(ResolverListAdapter.TAG, "SUBSTITUTE_SHARE_TARGET_APP_NAME_AND_ICON permission granted but couldn't find resources for package", e);
                return null;
            }
        }

        @Override // com.android.internal.app.ResolverListAdapter.TargetPresentationGetter
        String getAppSubLabelInternal() {
            return (String) this.mActivityInfo.loadLabel(this.mPm);
        }
    }

    private static abstract class TargetPresentationGetter {
        private final ApplicationInfo mAi;
        private Context mCtx;
        private final boolean mHasSubstitutePermission;
        private final int mIconDpi;
        protected PackageManager mPm;

        abstract String getAppSubLabelInternal();

        abstract Drawable getIconSubstituteInternal();

        TargetPresentationGetter(Context context, int i, ApplicationInfo applicationInfo) {
            this.mCtx = context;
            PackageManager packageManager = context.getPackageManager();
            this.mPm = packageManager;
            this.mAi = applicationInfo;
            this.mIconDpi = i;
            this.mHasSubstitutePermission = packageManager.checkPermission(Manifest.permission.SUBSTITUTE_SHARE_TARGET_APP_NAME_AND_ICON, applicationInfo.packageName) == 0;
        }

        public Drawable getIcon(UserHandle userHandle) {
            return new BitmapDrawable(this.mCtx.getResources(), getIconBitmap(userHandle));
        }

        public Bitmap getIconBitmap(UserHandle userHandle) {
            Drawable iconSubstituteInternal = this.mHasSubstitutePermission ? getIconSubstituteInternal() : null;
            if (iconSubstituteInternal == null) {
                try {
                    if (this.mAi.icon != 0) {
                        iconSubstituteInternal = loadIconFromResource(this.mPm.getResourcesForApplication(this.mAi), this.mAi.icon);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            if (iconSubstituteInternal == null) {
                iconSubstituteInternal = this.mAi.loadIcon(this.mPm);
            }
            SimpleIconFactory simpleIconFactoryObtain = SimpleIconFactory.obtain(this.mCtx);
            Bitmap bitmapCreateUserBadgedIconBitmap = simpleIconFactoryObtain.createUserBadgedIconBitmap(iconSubstituteInternal, userHandle);
            simpleIconFactoryObtain.recycle();
            return bitmapCreateUserBadgedIconBitmap;
        }

        public String getLabel() {
            String appSubLabelInternal = this.mHasSubstitutePermission ? getAppSubLabelInternal() : null;
            return appSubLabelInternal == null ? (String) this.mAi.loadLabel(this.mPm) : appSubLabelInternal;
        }

        public String getSubLabel() {
            if (this.mHasSubstitutePermission) {
                return null;
            }
            return getAppSubLabelInternal();
        }

        protected String loadLabelFromResource(Resources resources, int i) {
            return resources.getString(i);
        }

        protected Drawable loadIconFromResource(Resources resources, int i) {
            return resources.getDrawableForDensity(i, this.mIconDpi);
        }
    }
}
