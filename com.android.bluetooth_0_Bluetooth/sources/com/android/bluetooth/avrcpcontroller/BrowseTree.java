package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.net.INetd;
import android.net.Uri;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import com.android.bluetooth.avrcpcontroller.AvrcpItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class BrowseTree {
    public static final String NOW_PLAYING_PREFIX = "NOW_PLAYING";
    public static final String PLAYER_PREFIX = "PLAYER";
    public static final String ROOT = "__ROOT__";
    public static final String SEARCH_PREFIX = "SEARCH";
    public static final String UP = "__UP__";
    private BrowseNode mCurrentAddressedPlayer;
    private BrowseNode mCurrentBrowseNode;
    private BrowseNode mCurrentBrowsedPlayer;
    final BrowseNode mNavigateUpNode;
    final BrowseNode mNowPlayingNode;
    final BrowseNode mRootNode;
    protected BrowseNode mSearchNode;
    private static final String TAG = "BrowseTree";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    private static final boolean VDBG = Log.isLoggable(TAG, 2);
    private final HashMap<String, BrowseNode> mBrowseMap = new HashMap<>();
    private int mDepth = 0;
    private final HashMap<String, ArrayList<String>> mCoverArtMap = new HashMap<>();

    BrowseTree(BluetoothDevice device) {
        if (device != null) {
            this.mRootNode = new BrowseNode(new AvrcpItem.Builder().setDevice(device).setUuid(ROOT + device.getAddress().toString()).setTitle(device.getName()).setBrowsable(true).build());
        } else {
            BrowseNode browseNode = new BrowseNode(new AvrcpItem.Builder().setUuid(ROOT).setTitle(ROOT).setBrowsable(true).build());
            this.mRootNode = browseNode;
            browseNode.setCached(true);
        }
        this.mRootNode.mBrowseScope = (byte) 0;
        this.mRootNode.setExpectedChildren(255);
        this.mNavigateUpNode = new BrowseNode(new AvrcpItem.Builder().setUuid(UP).setTitle(UP).setBrowsable(true).build());
        BrowseNode browseNode2 = new BrowseNode(new AvrcpItem.Builder().setUuid(NOW_PLAYING_PREFIX).setTitle(NOW_PLAYING_PREFIX).setBrowsable(true).build());
        this.mNowPlayingNode = browseNode2;
        browseNode2.mBrowseScope = (byte) 3;
        this.mNowPlayingNode.setExpectedChildren(255);
        this.mBrowseMap.put(ROOT, this.mRootNode);
        this.mBrowseMap.put(NOW_PLAYING_PREFIX, this.mNowPlayingNode);
        BrowseNode browseNode3 = new BrowseNode(new AvrcpItem.Builder().setUuid(SEARCH_PREFIX).setTitle(SEARCH_PREFIX).setDevice(device).setBrowsable(true).build());
        this.mSearchNode = browseNode3;
        browseNode3.mBrowseScope = (byte) 2;
        this.mSearchNode.setExpectedChildren(255);
        this.mBrowseMap.put(SEARCH_PREFIX, this.mSearchNode);
        this.mCurrentBrowseNode = this.mRootNode;
    }

    public void clear() {
        this.mBrowseMap.clear();
        this.mCoverArtMap.clear();
    }

    void onConnected(BluetoothDevice device) {
        BrowseNode browseNode = new BrowseNode(device);
        this.mRootNode.addChild(browseNode);
    }

    BrowseNode getTrackFromNowPlayingList(int trackNumber) {
        return this.mNowPlayingNode.getChild(trackNumber);
    }

    void updateSearchNode(BrowseNode node) {
        this.mSearchNode = node;
        this.mBrowseMap.put(SEARCH_PREFIX, node);
    }

    class BrowseNode {
        byte mBrowseScope;
        boolean mCached;
        private final List<BrowseNode> mChildren;
        private int mExpectedChildrenCount;
        boolean mIsPlayer;
        AvrcpItem mItem;
        private BrowseNode mParent;

        BrowseNode(AvrcpItem item) {
            this.mIsPlayer = false;
            this.mCached = false;
            this.mBrowseScope = (byte) 1;
            this.mChildren = new ArrayList();
            this.mItem = item;
        }

        BrowseNode(AvrcpPlayer player) {
            this.mIsPlayer = false;
            this.mCached = false;
            this.mBrowseScope = (byte) 1;
            this.mChildren = new ArrayList();
            this.mIsPlayer = true;
            AvrcpItem.Builder aid = new AvrcpItem.Builder();
            aid.setDevice(player.getDevice());
            aid.setUid(player.getId());
            aid.setUuid(UUID.randomUUID().toString());
            aid.setDisplayableName(player.getName());
            aid.setTitle(player.getName());
            aid.setBrowsable(player.supportsFeature(59));
            this.mItem = aid.build();
        }

        BrowseNode(BluetoothDevice device) {
            this.mIsPlayer = false;
            this.mCached = false;
            this.mBrowseScope = (byte) 1;
            this.mChildren = new ArrayList();
            this.mIsPlayer = true;
            String playerKey = BrowseTree.PLAYER_PREFIX + device.getAddress().toString();
            AvrcpItem.Builder aid = new AvrcpItem.Builder();
            aid.setDevice(device);
            aid.setUuid(playerKey);
            aid.setDisplayableName(device.getName());
            aid.setTitle(device.getName());
            aid.setBrowsable(true);
            this.mItem = aid.build();
        }

        private BrowseNode(String name) {
            this.mIsPlayer = false;
            this.mCached = false;
            this.mBrowseScope = (byte) 1;
            this.mChildren = new ArrayList();
            AvrcpItem.Builder aid = new AvrcpItem.Builder();
            aid.setUuid(name);
            aid.setDisplayableName(name);
            aid.setTitle(name);
            this.mItem = aid.build();
        }

        synchronized void setExpectedChildren(int count) {
            this.mExpectedChildrenCount = count;
        }

        synchronized int getExpectedChildren() {
            return this.mExpectedChildrenCount;
        }

        synchronized <E> int addChildren(List<E> newChildren) {
            return addChildren(newChildren, (byte) 1);
        }

        synchronized <E> int addChildren(List<E> list, byte b) {
            for (E e : list) {
                BrowseNode browseNode = null;
                if (e instanceof AvrcpItem) {
                    browseNode = BrowseTree.this.new BrowseNode((AvrcpItem) e);
                } else if (e instanceof AvrcpPlayer) {
                    browseNode = BrowseTree.this.new BrowseNode((AvrcpPlayer) e);
                }
                browseNode.setScope(b);
                addChild(browseNode);
            }
            return list.size();
        }

        synchronized boolean addChild(BrowseNode node) {
            if (node != null) {
                node.mParent = this;
                if (this.mBrowseScope == 3) {
                    node.mBrowseScope = this.mBrowseScope;
                }
                this.mChildren.add(node);
                BrowseTree.this.mBrowseMap.put(node.getID(), node);
                String imageUuid = node.getCoverArtUuid();
                if (imageUuid != null) {
                    BrowseTree.this.indicateCoverArtUsed(node.getID(), imageUuid);
                }
                return true;
            }
            return false;
        }

        synchronized void removeChild(BrowseNode node) {
            if (node == null) {
                return;
            }
            this.mChildren.remove(node);
            BrowseTree.this.mBrowseMap.remove(node.getID());
            BrowseTree.this.indicateCoverArtUnused(node.getID(), node.getCoverArtUuid());
        }

        synchronized int getChildrenCount() {
            return this.mChildren.size();
        }

        synchronized List<BrowseNode> getChildren() {
            return this.mChildren;
        }

        synchronized BrowseNode getChild(int index) {
            if (index >= 0) {
                if (index < this.mChildren.size()) {
                    return this.mChildren.get(index);
                }
            }
            return null;
        }

        synchronized BrowseNode getParent() {
            return this.mParent;
        }

        synchronized BluetoothDevice getDevice() {
            return this.mItem.getDevice();
        }

        synchronized String getCoverArtUuid() {
            return this.mItem.getCoverArtUuid();
        }

        synchronized void setCoverArtUri(Uri uri) {
            this.mItem.setCoverArtLocation(uri);
        }

        synchronized List<MediaBrowserCompat.MediaItem> getContents() {
            if (this.mChildren.size() > 0 || this.mCached) {
                List<MediaBrowserCompat.MediaItem> contents = new ArrayList<>(this.mChildren.size());
                for (BrowseNode child : this.mChildren) {
                    contents.add(child.getMediaItem());
                }
                return contents;
            }
            return null;
        }

        synchronized boolean isChild(BrowseNode node) {
            return this.mChildren.contains(node);
        }

        synchronized boolean isCached() {
            return this.mCached;
        }

        synchronized boolean isBrowsable() {
            return this.mItem.isBrowsable();
        }

        synchronized void setCached(boolean cached) {
            if (BrowseTree.DBG) {
                Log.d(BrowseTree.TAG, "Set Cache" + cached + "Node" + toString());
            }
            this.mCached = cached;
            if (!cached) {
                for (BrowseNode child : this.mChildren) {
                    BrowseTree.this.mBrowseMap.remove(child.getID());
                    BrowseTree.this.indicateCoverArtUnused(child.getID(), child.getCoverArtUuid());
                }
                this.mChildren.clear();
            }
        }

        synchronized String getID() {
            return this.mItem.getUuid();
        }

        synchronized int getPlayerID() {
            return Integer.parseInt(getID().replace(BrowseTree.PLAYER_PREFIX, INetd.NEXTHOP_NONE));
        }

        synchronized void setScope(byte scope) {
            this.mBrowseScope = scope;
        }

        synchronized byte getScope() {
            return this.mBrowseScope;
        }

        synchronized String getFolderUID() {
            return getID();
        }

        synchronized long getBluetoothID() {
            return this.mItem.getUid();
        }

        synchronized MediaBrowserCompat.MediaItem getMediaItem() {
            return this.mItem.toMediaItem();
        }

        synchronized boolean isPlayer() {
            return this.mIsPlayer;
        }

        synchronized boolean isNowPlaying() {
            return getID().startsWith(BrowseTree.NOW_PLAYING_PREFIX);
        }

        synchronized boolean isSearch() {
            return getID().startsWith(BrowseTree.SEARCH_PREFIX);
        }

        public boolean equals(Object other) {
            if (!(other instanceof BrowseNode)) {
                return false;
            }
            BrowseNode otherNode = (BrowseNode) other;
            return getID().equals(otherNode.getID());
        }

        public synchronized String toString() {
            if (BrowseTree.VDBG) {
                String serialized = "[ Name: " + this.mItem.getTitle() + " Scope:" + ((int) this.mBrowseScope) + " expected Children: " + this.mExpectedChildrenCount + " ID: " + getID() + "] ";
                for (BrowseNode node : this.mChildren) {
                    serialized = serialized + node.toString();
                }
                return serialized;
            }
            return "ID: " + getID();
        }

        synchronized boolean isDescendant(BrowseNode target) {
            return BrowseTree.getEldestChild(this, target) != null;
        }
    }

    synchronized BrowseNode findBrowseNodeByID(String parentID) {
        BrowseNode bn = this.mBrowseMap.get(parentID);
        if (bn == null) {
            Log.e(TAG, "folder " + parentID + " not found!");
            return null;
        }
        if (VDBG) {
            Log.d(TAG, "Size " + this.mBrowseMap.size());
        }
        return bn;
    }

    synchronized boolean setCurrentBrowsedFolder(String uid) {
        BrowseNode bn = this.mBrowseMap.get(uid);
        if (bn == null) {
            Log.e(TAG, "Setting an unknown browsed folder, ignoring bn " + uid);
            return false;
        }
        if (!bn.equals(this.mCurrentBrowseNode)) {
            Log.d(TAG, "Set cache  " + bn + " curr " + this.mCurrentBrowseNode);
        }
        this.mCurrentBrowseNode = bn;
        return true;
    }

    synchronized BrowseNode getCurrentBrowsedFolder() {
        return this.mCurrentBrowseNode;
    }

    synchronized boolean setCurrentBrowsedPlayer(String uid, int items, int depth) {
        BrowseNode bn = this.mBrowseMap.get(uid);
        if (bn == null) {
            Log.e(TAG, "Setting an unknown browsed player, ignoring bn " + uid);
            return false;
        }
        this.mCurrentBrowsedPlayer = bn;
        this.mCurrentBrowseNode = bn;
        for (Integer level = 0; level.intValue() < depth; level = Integer.valueOf(level.intValue() + 1)) {
            BrowseNode dummyNode = new BrowseNode(level.toString());
            dummyNode.mParent = this.mCurrentBrowseNode;
            dummyNode.mBrowseScope = (byte) 1;
            this.mCurrentBrowseNode = dummyNode;
        }
        this.mCurrentBrowseNode.setExpectedChildren(items);
        this.mDepth = depth;
        return true;
    }

    synchronized BrowseNode getCurrentBrowsedPlayer() {
        return this.mCurrentBrowsedPlayer;
    }

    synchronized boolean setCurrentAddressedPlayer(String uid) {
        BrowseNode bn = this.mBrowseMap.get(uid);
        if (bn == null) {
            if (DBG) {
                Log.d(TAG, "Setting an unknown addressed player, ignoring bn " + uid);
            }
            this.mRootNode.setCached(false);
            this.mRootNode.mChildren.add(this.mNowPlayingNode);
            this.mBrowseMap.put(NOW_PLAYING_PREFIX, this.mNowPlayingNode);
            return false;
        }
        this.mCurrentAddressedPlayer = bn;
        return true;
    }

    synchronized BrowseNode getCurrentAddressedPlayer() {
        return this.mCurrentAddressedPlayer;
    }

    synchronized void indicateCoverArtUsed(String nodeId, String handle) {
        this.mCoverArtMap.putIfAbsent(handle, new ArrayList<>());
        this.mCoverArtMap.get(handle).add(nodeId);
    }

    synchronized void indicateCoverArtUnused(String nodeId, String handle) {
        if (this.mCoverArtMap.containsKey(handle) && this.mCoverArtMap.get(handle).contains(nodeId)) {
            this.mCoverArtMap.get(handle).remove(nodeId);
        }
    }

    synchronized ArrayList<String> getNodesUsingCoverArt(String handle) {
        if (this.mCoverArtMap.containsKey(handle)) {
            return (ArrayList) this.mCoverArtMap.get(handle).clone();
        }
        return new ArrayList<>();
    }

    synchronized ArrayList<String> getAndClearUnusedCoverArt() {
        ArrayList<String> unused;
        unused = new ArrayList<>();
        for (String uuid : this.mCoverArtMap.keySet()) {
            if (this.mCoverArtMap.get(uuid).isEmpty()) {
                unused.add(uuid);
            }
        }
        Iterator<String> it = unused.iterator();
        while (it.hasNext()) {
            this.mCoverArtMap.remove(it.next());
        }
        return unused;
    }

    synchronized Set<BrowseNode> notifyImageDownload(String uuid, Uri uri) {
        HashSet<BrowseNode> parents;
        if (DBG) {
            Log.d(TAG, "Received downloaded image handle to cascade to BrowseNodes using it");
        }
        ArrayList<String> nodes = getNodesUsingCoverArt(uuid);
        parents = new HashSet<>();
        for (String nodeId : nodes) {
            BrowseNode node = findBrowseNodeByID(nodeId);
            if (node == null) {
                Log.e(TAG, "Node was removed without clearing its cover art status");
                indicateCoverArtUnused(nodeId, uuid);
            } else {
                node.setCoverArtUri(uri);
                if (node.mParent != null) {
                    parents.add(node.mParent);
                }
            }
        }
        return parents;
    }

    public String toString() {
        String serialized = "Size: " + this.mBrowseMap.size();
        if (VDBG) {
            serialized = (serialized + this.mRootNode.toString()) + "\n  Image handles in use (" + this.mCoverArtMap.size() + "):";
            for (String handle : this.mCoverArtMap.keySet()) {
                serialized = serialized + "\n    " + handle + "\n";
            }
        }
        return serialized;
    }

    BrowseNode getNextStepToFolder(BrowseNode target) {
        if (target == null) {
            return null;
        }
        if (target.equals(this.mCurrentBrowseNode) || target.equals(this.mNowPlayingNode) || target.equals(this.mRootNode) || target.equals(this.mSearchNode)) {
            return target;
        }
        if (target.isPlayer()) {
            int i = this.mDepth;
            if (i > 0) {
                this.mDepth = i - 1;
                return this.mNavigateUpNode;
            }
            return target;
        }
        if (this.mBrowseMap.get(target.getID()) == null) {
            return null;
        }
        BrowseNode nextChild = getEldestChild(this.mCurrentBrowseNode, target);
        if (nextChild == null) {
            return this.mNavigateUpNode;
        }
        return nextChild;
    }

    static BrowseNode getEldestChild(BrowseNode ancestor, BrowseNode target) {
        BrowseNode descendant = target;
        if (DBG) {
            Log.d(TAG, "NAVIGATING ancestor" + ancestor.toString() + "Target" + target.toString());
        }
        while (!ancestor.equals(descendant.mParent)) {
            descendant = descendant.mParent;
            if (descendant == null) {
                return null;
            }
        }
        if (DBG) {
            Log.d(TAG, "NAVIGATING Descendant" + descendant.toString());
        }
        return descendant;
    }
}
