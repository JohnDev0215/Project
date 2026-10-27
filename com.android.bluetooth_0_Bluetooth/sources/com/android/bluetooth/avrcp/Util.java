package com.android.bluetooth.avrcp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.net.INetd;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class Util {
    private static final String GPM_KEY = "com.google.android.music.mediasession.music_metadata";
    public static final String NOW_PLAYING_PREFIX = "NowPlayingId";
    public static String TAG = "AvrcpUtil";
    public static boolean DEBUG = false;

    Util() {
    }

    public static final Metadata empty_data() {
        Metadata ret = new Metadata();
        ret.mediaId = "Not Provided";
        ret.title = "Not Provided";
        ret.artist = INetd.NEXTHOP_NONE;
        ret.album = INetd.NEXTHOP_NONE;
        ret.genre = INetd.NEXTHOP_NONE;
        ret.trackNum = "1";
        ret.numTracks = "1";
        ret.duration = "0";
        return ret;
    }

    public static Metadata bundleToMetadata(Bundle bundle) {
        if (bundle == null) {
            return empty_data();
        }
        Metadata temp = new Metadata();
        temp.title = bundle.getString(MediaMetadataCompat.METADATA_KEY_TITLE, "Not Provided");
        temp.artist = bundle.getString(MediaMetadataCompat.METADATA_KEY_ARTIST, INetd.NEXTHOP_NONE);
        temp.album = bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM, INetd.NEXTHOP_NONE);
        temp.trackNum = INetd.NEXTHOP_NONE + bundle.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, 1L);
        temp.numTracks = INetd.NEXTHOP_NONE + bundle.getLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, 1L);
        temp.genre = bundle.getString(MediaMetadataCompat.METADATA_KEY_GENRE, INetd.NEXTHOP_NONE);
        temp.duration = INetd.NEXTHOP_NONE + bundle.getLong(MediaMetadataCompat.METADATA_KEY_DURATION, 0L);
        return temp;
    }

    public static Bundle descriptionToBundle(MediaDescription desc) {
        Bundle ret = new Bundle();
        if (desc == null) {
            return ret;
        }
        if (desc.getTitle() != null) {
            ret.putString(MediaMetadataCompat.METADATA_KEY_TITLE, desc.getTitle().toString());
        }
        if (desc.getSubtitle() != null) {
            ret.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, desc.getSubtitle().toString());
        }
        if (desc.getDescription() != null) {
            ret.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, desc.getDescription().toString());
        }
        if (desc.getExtras() != null) {
            ret.putAll(desc.getExtras());
        }
        if (ret.containsKey(GPM_KEY)) {
            if (DEBUG) {
                Log.d(TAG, "MediaDescription contains GPM data");
            }
            ret.putAll(mediaMetadataToBundle((MediaMetadata) ret.get(GPM_KEY)));
        }
        return ret;
    }

    public static Bundle mediaMetadataToBundle(MediaMetadata data) {
        Bundle bundle = new Bundle();
        if (data == null) {
            return bundle;
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_TITLE)) {
            bundle.putString(MediaMetadataCompat.METADATA_KEY_TITLE, data.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_ARTIST)) {
            bundle.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, data.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM)) {
            bundle.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, data.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER)) {
            bundle.putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, data.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER));
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS)) {
            bundle.putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, data.getLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS));
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_GENRE)) {
            bundle.putString(MediaMetadataCompat.METADATA_KEY_GENRE, data.getString(MediaMetadataCompat.METADATA_KEY_GENRE));
        }
        if (data.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            bundle.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, data.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
        }
        return bundle;
    }

    public static Metadata toMetadata(MediaSession.QueueItem item) {
        if (item == null) {
            return empty_data();
        }
        Bundle bundle = descriptionToBundle(item.getDescription());
        if (DEBUG) {
            for (String key : bundle.keySet()) {
                Log.d(TAG, "toMetadata: QueueItem: ContainsKey: " + key);
            }
        }
        Metadata ret = bundleToMetadata(bundle);
        ret.mediaId = NOW_PLAYING_PREFIX + item.getQueueId();
        return ret;
    }

    public static Metadata toMetadata(MediaMetadata data) {
        if (data == null) {
            return empty_data();
        }
        data.getDescription();
        Bundle dataBundle = mediaMetadataToBundle(data);
        Bundle bundle = descriptionToBundle(data.getDescription());
        bundle.putAll(dataBundle);
        if (DEBUG) {
            for (String key : bundle.keySet()) {
                Log.d(TAG, "toMetadata: MediaMetadata: ContainsKey: " + key);
            }
        }
        Metadata ret = bundleToMetadata(bundle);
        ret.mediaId = "currsong";
        return ret;
    }

    public static Metadata toMetadata(MediaBrowser.MediaItem item) {
        if (item == null) {
            return empty_data();
        }
        Bundle bundle = descriptionToBundle(item.getDescription());
        if (DEBUG) {
            for (String key : bundle.keySet()) {
                Log.d(TAG, "toMetadata: MediaItem: ContainsKey: " + key);
            }
        }
        Metadata ret = bundleToMetadata(bundle);
        ret.mediaId = item.getMediaId();
        return ret;
    }

    public static List<Metadata> toMetadataList(List<MediaSession.QueueItem> items) {
        ArrayList<Metadata> list = new ArrayList<>();
        if (items == null) {
            return list;
        }
        for (int i = 0; i < items.size(); i++) {
            Metadata data = toMetadata(items.get(i));
            data.trackNum = INetd.NEXTHOP_NONE + (i + 1);
            data.numTracks = INetd.NEXTHOP_NONE + items.size();
            list.add(data);
        }
        return list;
    }

    public static List<ListItem> cloneList(List<ListItem> list) {
        List<ListItem> clone = new ArrayList<>(list.size());
        for (ListItem item : list) {
            clone.add(item.m8clone());
        }
        return clone;
    }

    public static String getDisplayName(Context context, String packageName) {
        try {
            PackageManager manager = context.getPackageManager();
            return manager.getApplicationLabel(manager.getApplicationInfo(packageName, 0)).toString();
        } catch (Exception e) {
            Log.w(TAG, "Name Not Found using package name: " + packageName);
            return packageName;
        }
    }
}
