package com.android.launcher3.model;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetHost;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.Utilities;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.util.ContentWriter;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.LooperExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class ModelWriter {
    private static final String TAG = "ModelWriter";
    private final BgDataModel mBgDataModel;
    private final Context mContext;
    private final boolean mHasVerticalHotseat;
    private final LauncherModel mModel;
    private boolean mPreparingToUndo;
    private final boolean mVerifyChanges;
    private final List<Runnable> mDeleteRunnables = new ArrayList();
    private final Executor mWorkerExecutor = new LooperExecutor(LauncherModel.getWorkerLooper());
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public ModelWriter(Context context, LauncherModel model, BgDataModel dataModel, boolean hasVerticalHotseat, boolean verifyChanges) {
        this.mContext = context;
        this.mModel = model;
        this.mBgDataModel = dataModel;
        this.mHasVerticalHotseat = hasVerticalHotseat;
        this.mVerifyChanges = verifyChanges;
    }

    private void updateItemInfoProps(ItemInfo item, int container, int screenId, int cellX, int cellY) {
        item.container = container;
        item.cellX = cellX;
        item.cellY = cellY;
        if (container == -101) {
            if (this.mHasVerticalHotseat) {
                cellX = (LauncherAppState.getIDP(this.mContext).numHotseatIcons - cellY) - 1;
            }
            item.screenId = cellX;
            return;
        }
        item.screenId = screenId;
    }

    public void addOrMoveItemInDatabase(ItemInfo item, int container, int screenId, int cellX, int cellY) {
        if (item.container == -1) {
            addItemToDatabase(item, container, screenId, cellX, cellY);
        } else {
            moveItemInDatabase(item, container, screenId, cellX, cellY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkItemInfoLocked(int itemId, ItemInfo item, StackTraceElement[] stackTrace) {
        ItemInfo itemInfo = this.mBgDataModel.itemsIdMap.get(itemId);
        if (itemInfo == null || item == itemInfo) {
            return;
        }
        if (!Utilities.IS_DEBUG_DEVICE && (itemInfo instanceof WorkspaceItemInfo) && (item instanceof WorkspaceItemInfo) && itemInfo.title.toString().equals(item.title.toString()) && itemInfo.getIntent().filterEquals(item.getIntent()) && itemInfo.id == item.id && itemInfo.itemType == item.itemType && itemInfo.container == item.container && itemInfo.screenId == item.screenId && itemInfo.cellX == item.cellX && itemInfo.cellY == item.cellY && itemInfo.spanX == item.spanX && itemInfo.spanY == item.spanY) {
            return;
        }
        RuntimeException runtimeException = new RuntimeException("item: " + (item != null ? item.toString() : "null") + "modelItem: " + (itemInfo != null ? itemInfo.toString() : "null") + "Error: ItemInfo passed to checkItemInfo doesn't match original");
        if (stackTrace != null) {
            runtimeException.setStackTrace(stackTrace);
            throw runtimeException;
        }
        throw runtimeException;
    }

    public void moveItemInDatabase(final ItemInfo item, int container, int screenId, int cellX, int cellY) {
        updateItemInfoProps(item, container, screenId, cellX, cellY);
        enqueueDeleteRunnable(new UpdateItemRunnable(item, new Supplier() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$q5MKlpYBQYlaT2sX_oZPbCO9Czc
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$moveItemInDatabase$0$ModelWriter(item);
            }
        }));
    }

    public /* synthetic */ ContentWriter lambda$moveItemInDatabase$0$ModelWriter(ItemInfo itemInfo) {
        return new ContentWriter(this.mContext).put(LauncherSettings.Favorites.CONTAINER, Integer.valueOf(itemInfo.container)).put(LauncherSettings.Favorites.CELLX, Integer.valueOf(itemInfo.cellX)).put(LauncherSettings.Favorites.CELLY, Integer.valueOf(itemInfo.cellY)).put("rank", Integer.valueOf(itemInfo.rank)).put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(itemInfo.screenId));
    }

    public void moveItemsInDatabase(final ArrayList<ItemInfo> items, int container, int screen) {
        ArrayList arrayList = new ArrayList();
        int size = items.size();
        for (int i = 0; i < size; i++) {
            ItemInfo itemInfo = items.get(i);
            updateItemInfoProps(itemInfo, container, screen, itemInfo.cellX, itemInfo.cellY);
            ContentValues contentValues = new ContentValues();
            contentValues.put(LauncherSettings.Favorites.CONTAINER, Integer.valueOf(itemInfo.container));
            contentValues.put(LauncherSettings.Favorites.CELLX, Integer.valueOf(itemInfo.cellX));
            contentValues.put(LauncherSettings.Favorites.CELLY, Integer.valueOf(itemInfo.cellY));
            contentValues.put("rank", Integer.valueOf(itemInfo.rank));
            contentValues.put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(itemInfo.screenId));
            arrayList.add(contentValues);
        }
        enqueueDeleteRunnable(new UpdateItemsRunnable(items, arrayList));
    }

    public void modifyItemInDatabase(final ItemInfo item, int container, int screenId, int cellX, int cellY, int spanX, int spanY) {
        updateItemInfoProps(item, container, screenId, cellX, cellY);
        item.spanX = spanX;
        item.spanY = spanY;
        this.mWorkerExecutor.execute(new UpdateItemRunnable(item, new Supplier() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$akW8lEDT8OQCEH47Wd0t-sStd1Y
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$modifyItemInDatabase$1$ModelWriter(item);
            }
        }));
    }

    public /* synthetic */ ContentWriter lambda$modifyItemInDatabase$1$ModelWriter(ItemInfo itemInfo) {
        return new ContentWriter(this.mContext).put(LauncherSettings.Favorites.CONTAINER, Integer.valueOf(itemInfo.container)).put(LauncherSettings.Favorites.CELLX, Integer.valueOf(itemInfo.cellX)).put(LauncherSettings.Favorites.CELLY, Integer.valueOf(itemInfo.cellY)).put("rank", Integer.valueOf(itemInfo.rank)).put(LauncherSettings.Favorites.SPANX, Integer.valueOf(itemInfo.spanX)).put(LauncherSettings.Favorites.SPANY, Integer.valueOf(itemInfo.spanY)).put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(itemInfo.screenId));
    }

    public void updateItemInDatabase(final ItemInfo item) {
        this.mWorkerExecutor.execute(new UpdateItemRunnable(item, new Supplier() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$LiYZNx_L_NC75krVui2wSYgS2fk
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$updateItemInDatabase$2$ModelWriter(item);
            }
        }));
    }

    public /* synthetic */ ContentWriter lambda$updateItemInDatabase$2$ModelWriter(ItemInfo itemInfo) {
        ContentWriter contentWriter = new ContentWriter(this.mContext);
        itemInfo.onAddToDatabase(contentWriter);
        return contentWriter;
    }

    public void addItemToDatabase(final ItemInfo item, int container, int screenId, int cellX, int cellY) {
        updateItemInfoProps(item, container, screenId, cellX, cellY);
        final ContentResolver contentResolver = this.mContext.getContentResolver();
        item.id = LauncherSettings.Settings.call(contentResolver, LauncherSettings.Settings.METHOD_NEW_ITEM_ID).getInt("value");
        final ModelVerifier modelVerifier = new ModelVerifier();
        final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        this.mWorkerExecutor.execute(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$NoS76kCgrXoOhT-bZSPbg7RgFRs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$addItemToDatabase$3$ModelWriter(item, contentResolver, stackTrace, modelVerifier);
            }
        });
    }

    public /* synthetic */ void lambda$addItemToDatabase$3$ModelWriter(ItemInfo itemInfo, ContentResolver contentResolver, StackTraceElement[] stackTraceElementArr, ModelVerifier modelVerifier) {
        ContentWriter contentWriter = new ContentWriter(this.mContext);
        itemInfo.onAddToDatabase(contentWriter);
        contentWriter.put("_id", Integer.valueOf(itemInfo.id));
        contentResolver.insert(LauncherSettings.Favorites.CONTENT_URI, contentWriter.getValues(this.mContext));
        synchronized (this.mBgDataModel) {
            checkItemInfoLocked(itemInfo.id, itemInfo, stackTraceElementArr);
            this.mBgDataModel.addItem(this.mContext, itemInfo, true);
            modelVerifier.verifyModel();
        }
    }

    public void deleteItemFromDatabase(ItemInfo item) {
        deleteItemsFromDatabase(Arrays.asList(item));
    }

    public void deleteItemsFromDatabase(ItemInfoMatcher matcher) {
        deleteItemsFromDatabase(matcher.filterItemInfos(this.mBgDataModel.itemsIdMap));
    }

    public void deleteItemsFromDatabase(final Iterable<? extends ItemInfo> items) {
        final ModelVerifier modelVerifier = new ModelVerifier();
        enqueueDeleteRunnable(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$GOaMUrkdgMO6VlbQLXLg11iwJQE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$deleteItemsFromDatabase$4$ModelWriter(items, modelVerifier);
            }
        });
    }

    public /* synthetic */ void lambda$deleteItemsFromDatabase$4$ModelWriter(Iterable iterable, ModelVerifier modelVerifier) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            ItemInfo itemInfo = (ItemInfo) it.next();
            this.mContext.getContentResolver().delete(LauncherSettings.Favorites.getContentUri(itemInfo.id), null, null);
            this.mBgDataModel.removeItem(this.mContext, itemInfo);
            modelVerifier.verifyModel();
        }
    }

    public void deleteFolderAndContentsFromDatabase(final FolderInfo info) {
        final ModelVerifier modelVerifier = new ModelVerifier();
        enqueueDeleteRunnable(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$OTPllvLthyaQ0scR4BkQlRDfEJk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$deleteFolderAndContentsFromDatabase$5$ModelWriter(info, modelVerifier);
            }
        });
    }

    public /* synthetic */ void lambda$deleteFolderAndContentsFromDatabase$5$ModelWriter(FolderInfo folderInfo, ModelVerifier modelVerifier) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.delete(LauncherSettings.Favorites.CONTENT_URI, "container=" + folderInfo.id, null);
        this.mBgDataModel.removeItem(this.mContext, folderInfo.contents);
        folderInfo.contents.clear();
        contentResolver.delete(LauncherSettings.Favorites.getContentUri(folderInfo.id), null, null);
        this.mBgDataModel.removeItem(this.mContext, folderInfo);
        modelVerifier.verifyModel();
    }

    public void deleteWidgetInfo(final LauncherAppWidgetInfo info, final LauncherAppWidgetHost host) {
        if (host != null && !info.isCustomWidget() && info.isWidgetIdAllocated()) {
            enqueueDeleteRunnable(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$73R5mLvLh1VkwrLFGPo7NIu1vkE
                @Override // java.lang.Runnable
                public final void run() {
                    host.deleteAppWidgetId(info.appWidgetId);
                }
            });
        }
        deleteItemFromDatabase(info);
    }

    public void prepareToUndoDelete() {
        if (this.mPreparingToUndo) {
            return;
        }
        this.mDeleteRunnables.isEmpty();
        this.mDeleteRunnables.clear();
        this.mPreparingToUndo = true;
    }

    private void enqueueDeleteRunnable(Runnable r) {
        if (this.mPreparingToUndo) {
            this.mDeleteRunnables.add(r);
        } else {
            this.mWorkerExecutor.execute(r);
        }
    }

    public void commitDelete() {
        this.mPreparingToUndo = false;
        Iterator<Runnable> it = this.mDeleteRunnables.iterator();
        while (it.hasNext()) {
            this.mWorkerExecutor.execute(it.next());
        }
        this.mDeleteRunnables.clear();
    }

    public void abortDelete(int pageToBindFirst) {
        this.mPreparingToUndo = false;
        this.mDeleteRunnables.clear();
        this.mModel.forceReload(pageToBindFirst);
    }

    private class UpdateItemRunnable extends UpdateItemBaseRunnable {
        private final ItemInfo mItem;
        private final int mItemId;
        private final Supplier<ContentWriter> mWriter;

        UpdateItemRunnable(ItemInfo item, Supplier<ContentWriter> writer) {
            super();
            this.mItem = item;
            this.mWriter = writer;
            this.mItemId = item.id;
        }

        @Override // java.lang.Runnable
        public void run() {
            ModelWriter.this.mContext.getContentResolver().update(LauncherSettings.Favorites.getContentUri(this.mItemId), this.mWriter.get().getValues(ModelWriter.this.mContext), null, null);
            updateItemArrays(this.mItem, this.mItemId);
        }
    }

    private class UpdateItemsRunnable extends UpdateItemBaseRunnable {
        private final ArrayList<ItemInfo> mItems;
        private final ArrayList<ContentValues> mValues;

        UpdateItemsRunnable(ArrayList<ItemInfo> items, ArrayList<ContentValues> values) {
            super();
            this.mValues = values;
            this.mItems = items;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
            int size = this.mItems.size();
            for (int i = 0; i < size; i++) {
                ItemInfo itemInfo = this.mItems.get(i);
                int i2 = itemInfo.id;
                Uri contentUri = LauncherSettings.Favorites.getContentUri(i2);
                arrayList.add(ContentProviderOperation.newUpdate(contentUri).withValues(this.mValues.get(i)).build());
                updateItemArrays(itemInfo, i2);
            }
            try {
                ModelWriter.this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, arrayList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private abstract class UpdateItemBaseRunnable implements Runnable {
        private final StackTraceElement[] mStackTrace = new Throwable().getStackTrace();
        private final ModelVerifier mVerifier;

        UpdateItemBaseRunnable() {
            this.mVerifier = ModelWriter.this.new ModelVerifier();
        }

        protected void updateItemArrays(ItemInfo item, int itemId) {
            synchronized (ModelWriter.this.mBgDataModel) {
                ModelWriter.this.checkItemInfoLocked(itemId, item, this.mStackTrace);
                if (item.container != -100 && item.container != -101 && !ModelWriter.this.mBgDataModel.folders.containsKey(item.container)) {
                    Log.e(ModelWriter.TAG, "item: " + item + " container being set to: " + item.container + ", not in the list of folders");
                }
                ItemInfo itemInfo = ModelWriter.this.mBgDataModel.itemsIdMap.get(itemId);
                if (itemInfo == null || (itemInfo.container != -100 && itemInfo.container != -101)) {
                    ModelWriter.this.mBgDataModel.workspaceItems.remove(itemInfo);
                } else {
                    int i = itemInfo.itemType;
                    if ((i == 0 || i == 1 || i == 2 || i == 6) && !ModelWriter.this.mBgDataModel.workspaceItems.contains(itemInfo)) {
                        ModelWriter.this.mBgDataModel.workspaceItems.add(itemInfo);
                    }
                }
                this.mVerifier.verifyModel();
            }
        }
    }

    public class ModelVerifier {
        final int startId;

        ModelVerifier() {
            this.startId = ModelWriter.this.mBgDataModel.lastBindId;
        }

        void verifyModel() {
            if (!ModelWriter.this.mVerifyChanges || ModelWriter.this.mModel.getCallback() == null) {
                return;
            }
            final int i = ModelWriter.this.mBgDataModel.lastBindId;
            ModelWriter.this.mUiHandler.post(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$ModelWriter$ModelVerifier$mavBVE5wayfULJFzRQ97YHBrwDA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$verifyModel$0$ModelWriter$ModelVerifier(i);
                }
            });
        }

        public /* synthetic */ void lambda$verifyModel$0$ModelWriter$ModelVerifier(int i) {
            LauncherModel.Callbacks callback;
            if (ModelWriter.this.mBgDataModel.lastBindId > i || i == this.startId || (callback = ModelWriter.this.mModel.getCallback()) == null) {
                return;
            }
            callback.rebindModel();
        }
    }
}
