package com.android.launcher3.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.MutableInt;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.logging.DumpTargetWrapper;
import com.android.launcher3.model.nano.LauncherDumpProto;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSet;
import com.android.launcher3.util.IntSparseArrayMap;
import com.google.protobuf.nano.MessageNano;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class BgDataModel {
    private static final String TAG = "BgDataModel";
    public boolean hasShortcutHostPermission;
    public final IntSparseArrayMap<ItemInfo> itemsIdMap = new IntSparseArrayMap<>();
    public final ArrayList<ItemInfo> workspaceItems = new ArrayList<>();
    public final ArrayList<LauncherAppWidgetInfo> appWidgets = new ArrayList<>();
    public final IntSparseArrayMap<FolderInfo> folders = new IntSparseArrayMap<>();
    public final Map<ShortcutKey, MutableInt> pinnedShortcutCounts = new HashMap();
    public final HashMap<ComponentKey, Integer> deepShortcutMap = new HashMap<>();
    public final WidgetsModel widgetsModel = new WidgetsModel();
    public int lastBindId = 0;

    public synchronized void clear() {
        this.workspaceItems.clear();
        this.appWidgets.clear();
        this.folders.clear();
        this.itemsIdMap.clear();
        this.pinnedShortcutCounts.clear();
        this.deepShortcutMap.clear();
    }

    public synchronized IntArray collectWorkspaceScreens() {
        IntSet intSet;
        intSet = new IntSet();
        for (ItemInfo itemInfo : this.itemsIdMap) {
            if (itemInfo.container == -100) {
                intSet.add(itemInfo.screenId);
            }
        }
        if (intSet.isEmpty()) {
            intSet.add(0);
        }
        return intSet.getArray();
    }

    public synchronized void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        if (Arrays.asList(args).contains("--proto")) {
            dumpProto(prefix, fd, writer, args);
            return;
        }
        writer.println(prefix + "Data Model:");
        writer.println(prefix + " ---- workspace items ");
        for (int i = 0; i < this.workspaceItems.size(); i++) {
            writer.println(prefix + '\t' + this.workspaceItems.get(i).toString());
        }
        writer.println(prefix + " ---- appwidget items ");
        for (int i2 = 0; i2 < this.appWidgets.size(); i2++) {
            writer.println(prefix + '\t' + this.appWidgets.get(i2).toString());
        }
        writer.println(prefix + " ---- folder items ");
        for (int i3 = 0; i3 < this.folders.size(); i3++) {
            writer.println(prefix + '\t' + this.folders.valueAt(i3).toString());
        }
        writer.println(prefix + " ---- items id map ");
        for (int i4 = 0; i4 < this.itemsIdMap.size(); i4++) {
            writer.println(prefix + '\t' + this.itemsIdMap.valueAt(i4).toString());
        }
        if (args.length > 0 && TextUtils.equals(args[0], "--all")) {
            writer.println(prefix + "shortcut counts ");
            Iterator<Integer> it = this.deepShortcutMap.values().iterator();
            while (it.hasNext()) {
                writer.print(it.next() + ", ");
            }
            writer.println();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private synchronized void dumpProto(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        int i = 0;
        DumpTargetWrapper dumpTargetWrapper = new DumpTargetWrapper(2, 0);
        IntSparseArrayMap intSparseArrayMap = new IntSparseArrayMap();
        IntArray intArrayCollectWorkspaceScreens = collectWorkspaceScreens();
        for (int i2 = 0; i2 < intArrayCollectWorkspaceScreens.size(); i2++) {
            intSparseArrayMap.put(intArrayCollectWorkspaceScreens.get(i2), new DumpTargetWrapper(1, i2));
        }
        for (int i3 = 0; i3 < this.folders.size(); i3++) {
            FolderInfo folderInfoValueAt = this.folders.valueAt(i3);
            DumpTargetWrapper dumpTargetWrapper2 = new DumpTargetWrapper(3, this.folders.size());
            dumpTargetWrapper2.writeToDumpTarget(folderInfoValueAt);
            for (WorkspaceItemInfo workspaceItemInfo : folderInfoValueAt.contents) {
                DumpTargetWrapper dumpTargetWrapper3 = new DumpTargetWrapper(workspaceItemInfo);
                dumpTargetWrapper3.writeToDumpTarget(workspaceItemInfo);
                dumpTargetWrapper2.add(dumpTargetWrapper3);
            }
            if (folderInfoValueAt.container == -101) {
                dumpTargetWrapper.add(dumpTargetWrapper2);
            } else if (folderInfoValueAt.container == -100) {
                ((DumpTargetWrapper) intSparseArrayMap.get(folderInfoValueAt.screenId)).add(dumpTargetWrapper2);
            }
        }
        for (int i4 = 0; i4 < this.workspaceItems.size(); i4++) {
            ItemInfo itemInfo = this.workspaceItems.get(i4);
            if (!(itemInfo instanceof FolderInfo)) {
                DumpTargetWrapper dumpTargetWrapper4 = new DumpTargetWrapper(itemInfo);
                dumpTargetWrapper4.writeToDumpTarget(itemInfo);
                if (itemInfo.container == -101) {
                    dumpTargetWrapper.add(dumpTargetWrapper4);
                } else if (itemInfo.container == -100) {
                    ((DumpTargetWrapper) intSparseArrayMap.get(itemInfo.screenId)).add(dumpTargetWrapper4);
                }
            }
        }
        for (int i5 = 0; i5 < this.appWidgets.size(); i5++) {
            LauncherAppWidgetInfo launcherAppWidgetInfo = this.appWidgets.get(i5);
            DumpTargetWrapper dumpTargetWrapper5 = new DumpTargetWrapper(launcherAppWidgetInfo);
            dumpTargetWrapper5.writeToDumpTarget(launcherAppWidgetInfo);
            if (launcherAppWidgetInfo.container == -101) {
                dumpTargetWrapper.add(dumpTargetWrapper5);
            } else if (launcherAppWidgetInfo.container == -100) {
                ((DumpTargetWrapper) intSparseArrayMap.get(launcherAppWidgetInfo.screenId)).add(dumpTargetWrapper5);
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(dumpTargetWrapper.getFlattenedList());
        for (int i6 = 0; i6 < intSparseArrayMap.size(); i6++) {
            arrayList.addAll(((DumpTargetWrapper) intSparseArrayMap.valueAt(i6)).getFlattenedList());
        }
        if (Arrays.asList(args).contains("--debug")) {
            while (i < arrayList.size()) {
                writer.println(prefix + DumpTargetWrapper.getDumpTargetStr((LauncherDumpProto.DumpTarget) arrayList.get(i)));
                i++;
            }
            return;
        }
        LauncherDumpProto.LauncherImpression launcherImpression = new LauncherDumpProto.LauncherImpression();
        launcherImpression.targets = new LauncherDumpProto.DumpTarget[arrayList.size()];
        while (i < arrayList.size()) {
            launcherImpression.targets[i] = (LauncherDumpProto.DumpTarget) arrayList.get(i);
            i++;
        }
        try {
            new FileOutputStream(fd).write(MessageNano.toByteArray(launcherImpression));
            Log.d(TAG, MessageNano.toByteArray(launcherImpression).length + "Bytes");
        } catch (IOException e) {
            Log.e(TAG, "Exception writing dumpsys --proto", e);
        }
    }

    public synchronized void removeItem(Context context, ItemInfo... items) {
        removeItem(context, Arrays.asList(items));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003a A[Catch: all -> 0x006e, TryCatch #0 {, blocks: (B:3:0x0001, B:4:0x0005, B:6:0x000b, B:29:0x0064, B:19:0x0025, B:21:0x0033, B:23:0x003a, B:25:0x0044, B:26:0x004c, B:27:0x0052, B:28:0x005f), top: B:36:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void removeItem(android.content.Context r6, java.lang.Iterable<? extends com.android.launcher3.ItemInfo> r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L6e
        L5:
            boolean r0 = r7.hasNext()     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L6c
            java.lang.Object r0 = r7.next()     // Catch: java.lang.Throwable -> L6e
            com.android.launcher3.ItemInfo r0 = (com.android.launcher3.ItemInfo) r0     // Catch: java.lang.Throwable -> L6e
            int r1 = r0.itemType     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto L5f
            r2 = 1
            if (r1 == r2) goto L5f
            r3 = 2
            if (r1 == r3) goto L52
            r3 = 4
            if (r1 == r3) goto L4c
            r3 = 5
            if (r1 == r3) goto L4c
            r3 = 6
            if (r1 == r3) goto L25
            goto L64
        L25:
            com.android.launcher3.shortcuts.ShortcutKey r1 = com.android.launcher3.shortcuts.ShortcutKey.fromItemInfo(r0)     // Catch: java.lang.Throwable -> L6e
            java.util.Map<com.android.launcher3.shortcuts.ShortcutKey, android.util.MutableInt> r3 = r5.pinnedShortcutCounts     // Catch: java.lang.Throwable -> L6e
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L6e
            android.util.MutableInt r3 = (android.util.MutableInt) r3     // Catch: java.lang.Throwable -> L6e
            if (r3 == 0) goto L3a
            int r4 = r3.value     // Catch: java.lang.Throwable -> L6e
            int r4 = r4 - r2
            r3.value = r4     // Catch: java.lang.Throwable -> L6e
            if (r4 != 0) goto L5f
        L3a:
            java.util.HashSet r2 = com.android.launcher3.InstallShortcutReceiver.getPendingShortcuts(r6)     // Catch: java.lang.Throwable -> L6e
            boolean r2 = r2.contains(r1)     // Catch: java.lang.Throwable -> L6e
            if (r2 != 0) goto L5f
            com.android.launcher3.shortcuts.DeepShortcutManager r2 = com.android.launcher3.shortcuts.DeepShortcutManager.getInstance(r6)     // Catch: java.lang.Throwable -> L6e
            r2.unpinShortcut(r1)     // Catch: java.lang.Throwable -> L6e
            goto L5f
        L4c:
            java.util.ArrayList<com.android.launcher3.LauncherAppWidgetInfo> r1 = r5.appWidgets     // Catch: java.lang.Throwable -> L6e
            r1.remove(r0)     // Catch: java.lang.Throwable -> L6e
            goto L64
        L52:
            com.android.launcher3.util.IntSparseArrayMap<com.android.launcher3.FolderInfo> r1 = r5.folders     // Catch: java.lang.Throwable -> L6e
            int r2 = r0.id     // Catch: java.lang.Throwable -> L6e
            r1.remove(r2)     // Catch: java.lang.Throwable -> L6e
            java.util.ArrayList<com.android.launcher3.ItemInfo> r1 = r5.workspaceItems     // Catch: java.lang.Throwable -> L6e
            r1.remove(r0)     // Catch: java.lang.Throwable -> L6e
            goto L64
        L5f:
            java.util.ArrayList<com.android.launcher3.ItemInfo> r1 = r5.workspaceItems     // Catch: java.lang.Throwable -> L6e
            r1.remove(r0)     // Catch: java.lang.Throwable -> L6e
        L64:
            com.android.launcher3.util.IntSparseArrayMap<com.android.launcher3.ItemInfo> r1 = r5.itemsIdMap     // Catch: java.lang.Throwable -> L6e
            int r0 = r0.id     // Catch: java.lang.Throwable -> L6e
            r1.remove(r0)     // Catch: java.lang.Throwable -> L6e
            goto L5
        L6c:
            monitor-exit(r5)
            return
        L6e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.BgDataModel.removeItem(android.content.Context, java.lang.Iterable):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6 A[Catch: all -> 0x00ad, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:16:0x001d, B:18:0x002b, B:21:0x003d, B:23:0x0041, B:19:0x0036, B:24:0x0049, B:25:0x0051, B:26:0x0061, B:28:0x0067, B:32:0x0070, B:34:0x007a, B:35:0x0099, B:36:0x00a6), top: B:42:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void addItem(android.content.Context r5, com.android.launcher3.ItemInfo r6, boolean r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.android.launcher3.util.IntSparseArrayMap<com.android.launcher3.ItemInfo> r0 = r4.itemsIdMap     // Catch: java.lang.Throwable -> Lad
            int r1 = r6.id     // Catch: java.lang.Throwable -> Lad
            r0.put(r1, r6)     // Catch: java.lang.Throwable -> Lad
            int r0 = r6.itemType     // Catch: java.lang.Throwable -> Lad
            if (r0 == 0) goto L61
            r1 = 1
            if (r0 == r1) goto L61
            r2 = 2
            if (r0 == r2) goto L51
            r2 = 4
            if (r0 == r2) goto L49
            r2 = 5
            if (r0 == r2) goto L49
            r2 = 6
            if (r0 == r2) goto L1d
            goto Lab
        L1d:
            com.android.launcher3.shortcuts.ShortcutKey r0 = com.android.launcher3.shortcuts.ShortcutKey.fromItemInfo(r6)     // Catch: java.lang.Throwable -> Lad
            java.util.Map<com.android.launcher3.shortcuts.ShortcutKey, android.util.MutableInt> r2 = r4.pinnedShortcutCounts     // Catch: java.lang.Throwable -> Lad
            java.lang.Object r2 = r2.get(r0)     // Catch: java.lang.Throwable -> Lad
            android.util.MutableInt r2 = (android.util.MutableInt) r2     // Catch: java.lang.Throwable -> Lad
            if (r2 != 0) goto L36
            android.util.MutableInt r2 = new android.util.MutableInt     // Catch: java.lang.Throwable -> Lad
            r2.<init>(r1)     // Catch: java.lang.Throwable -> Lad
            java.util.Map<com.android.launcher3.shortcuts.ShortcutKey, android.util.MutableInt> r3 = r4.pinnedShortcutCounts     // Catch: java.lang.Throwable -> Lad
            r3.put(r0, r2)     // Catch: java.lang.Throwable -> Lad
            goto L3b
        L36:
            int r3 = r2.value     // Catch: java.lang.Throwable -> Lad
            int r3 = r3 + r1
            r2.value = r3     // Catch: java.lang.Throwable -> Lad
        L3b:
            if (r7 == 0) goto L61
            int r2 = r2.value     // Catch: java.lang.Throwable -> Lad
            if (r2 != r1) goto L61
            com.android.launcher3.shortcuts.DeepShortcutManager r5 = com.android.launcher3.shortcuts.DeepShortcutManager.getInstance(r5)     // Catch: java.lang.Throwable -> Lad
            r5.pinShortcut(r0)     // Catch: java.lang.Throwable -> Lad
            goto L61
        L49:
            java.util.ArrayList<com.android.launcher3.LauncherAppWidgetInfo> r5 = r4.appWidgets     // Catch: java.lang.Throwable -> Lad
            com.android.launcher3.LauncherAppWidgetInfo r6 = (com.android.launcher3.LauncherAppWidgetInfo) r6     // Catch: java.lang.Throwable -> Lad
            r5.add(r6)     // Catch: java.lang.Throwable -> Lad
            goto Lab
        L51:
            com.android.launcher3.util.IntSparseArrayMap<com.android.launcher3.FolderInfo> r5 = r4.folders     // Catch: java.lang.Throwable -> Lad
            int r7 = r6.id     // Catch: java.lang.Throwable -> Lad
            r0 = r6
            com.android.launcher3.FolderInfo r0 = (com.android.launcher3.FolderInfo) r0     // Catch: java.lang.Throwable -> Lad
            r5.put(r7, r0)     // Catch: java.lang.Throwable -> Lad
            java.util.ArrayList<com.android.launcher3.ItemInfo> r5 = r4.workspaceItems     // Catch: java.lang.Throwable -> Lad
            r5.add(r6)     // Catch: java.lang.Throwable -> Lad
            goto Lab
        L61:
            int r5 = r6.container     // Catch: java.lang.Throwable -> Lad
            r0 = -100
            if (r5 == r0) goto La6
            int r5 = r6.container     // Catch: java.lang.Throwable -> Lad
            r0 = -101(0xffffffffffffff9b, float:NaN)
            if (r5 != r0) goto L6e
            goto La6
        L6e:
            if (r7 == 0) goto L99
            com.android.launcher3.util.IntSparseArrayMap<com.android.launcher3.FolderInfo> r5 = r4.folders     // Catch: java.lang.Throwable -> Lad
            int r7 = r6.container     // Catch: java.lang.Throwable -> Lad
            boolean r5 = r5.containsKey(r7)     // Catch: java.lang.Throwable -> Lad
            if (r5 != 0) goto Lab
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad
            r5.<init>()     // Catch: java.lang.Throwable -> Lad
            java.lang.String r7 = "adding item: "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch: java.lang.Throwable -> Lad
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r6 = " to a folder that  doesn't exist"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Lad
            java.lang.String r6 = "BgDataModel"
            android.util.Log.e(r6, r5)     // Catch: java.lang.Throwable -> Lad
            goto Lab
        L99:
            int r5 = r6.container     // Catch: java.lang.Throwable -> Lad
            com.android.launcher3.FolderInfo r5 = r4.findOrMakeFolder(r5)     // Catch: java.lang.Throwable -> Lad
            com.android.launcher3.WorkspaceItemInfo r6 = (com.android.launcher3.WorkspaceItemInfo) r6     // Catch: java.lang.Throwable -> Lad
            r7 = 0
            r5.add(r6, r7)     // Catch: java.lang.Throwable -> Lad
            goto Lab
        La6:
            java.util.ArrayList<com.android.launcher3.ItemInfo> r5 = r4.workspaceItems     // Catch: java.lang.Throwable -> Lad
            r5.add(r6)     // Catch: java.lang.Throwable -> Lad
        Lab:
            monitor-exit(r4)
            return
        Lad:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.BgDataModel.addItem(android.content.Context, com.android.launcher3.ItemInfo, boolean):void");
    }

    public synchronized FolderInfo findOrMakeFolder(int id) {
        FolderInfo folderInfo;
        folderInfo = this.folders.get(id);
        if (folderInfo == null) {
            folderInfo = new FolderInfo();
            this.folders.put(id, folderInfo);
        }
        return folderInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x000d, B:7:0x0013, B:9:0x0025, B:11:0x002d, B:12:0x0031, B:13:0x0035, B:15:0x003b, B:17:0x0048, B:19:0x004e, B:24:0x0059, B:28:0x0078, B:27:0x0073), top: B:35:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void updateDeepShortcutCounts(java.lang.String r4, android.os.UserHandle r5, java.util.List<android.content.pm.ShortcutInfo> r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L31
            java.util.HashMap<com.android.launcher3.util.ComponentKey, java.lang.Integer> r0 = r3.deepShortcutMap     // Catch: java.lang.Throwable -> L82
            java.util.Set r0 = r0.keySet()     // Catch: java.lang.Throwable -> L82
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L82
        Ld:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L82
            if (r1 == 0) goto L31
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L82
            com.android.launcher3.util.ComponentKey r1 = (com.android.launcher3.util.ComponentKey) r1     // Catch: java.lang.Throwable -> L82
            android.content.ComponentName r2 = r1.componentName     // Catch: java.lang.Throwable -> L82
            java.lang.String r2 = r2.getPackageName()     // Catch: java.lang.Throwable -> L82
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> L82
            if (r2 == 0) goto Ld
            android.os.UserHandle r1 = r1.user     // Catch: java.lang.Throwable -> L82
            boolean r1 = r1.equals(r5)     // Catch: java.lang.Throwable -> L82
            if (r1 == 0) goto Ld
            r0.remove()     // Catch: java.lang.Throwable -> L82
            goto Ld
        L31:
            java.util.Iterator r4 = r6.iterator()     // Catch: java.lang.Throwable -> L82
        L35:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L82
            if (r5 == 0) goto L80
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L82
            android.content.pm.ShortcutInfo r5 = (android.content.pm.ShortcutInfo) r5     // Catch: java.lang.Throwable -> L82
            boolean r6 = r5.isEnabled()     // Catch: java.lang.Throwable -> L82
            r0 = 1
            if (r6 == 0) goto L56
            boolean r6 = r5.isDeclaredInManifest()     // Catch: java.lang.Throwable -> L82
            if (r6 != 0) goto L54
            boolean r6 = r5.isDynamic()     // Catch: java.lang.Throwable -> L82
            if (r6 == 0) goto L56
        L54:
            r6 = r0
            goto L57
        L56:
            r6 = 0
        L57:
            if (r6 == 0) goto L35
            com.android.launcher3.util.ComponentKey r6 = new com.android.launcher3.util.ComponentKey     // Catch: java.lang.Throwable -> L82
            android.content.ComponentName r1 = r5.getActivity()     // Catch: java.lang.Throwable -> L82
            android.os.UserHandle r5 = r5.getUserHandle()     // Catch: java.lang.Throwable -> L82
            r6.<init>(r1, r5)     // Catch: java.lang.Throwable -> L82
            java.util.HashMap<com.android.launcher3.util.ComponentKey, java.lang.Integer> r5 = r3.deepShortcutMap     // Catch: java.lang.Throwable -> L82
            java.lang.Object r5 = r5.get(r6)     // Catch: java.lang.Throwable -> L82
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: java.lang.Throwable -> L82
            java.util.HashMap<com.android.launcher3.util.ComponentKey, java.lang.Integer> r1 = r3.deepShortcutMap     // Catch: java.lang.Throwable -> L82
            if (r5 != 0) goto L73
            goto L78
        L73:
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> L82
            int r0 = r0 + r5
        L78:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L82
            r1.put(r6, r5)     // Catch: java.lang.Throwable -> L82
            goto L35
        L80:
            monitor-exit(r3)
            return
        L82:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.BgDataModel.updateDeepShortcutCounts(java.lang.String, android.os.UserHandle, java.util.List):void");
    }
}
