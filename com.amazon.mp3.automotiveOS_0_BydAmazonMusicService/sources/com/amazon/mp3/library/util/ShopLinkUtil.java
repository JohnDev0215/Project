package com.amazon.mp3.library.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.library.item.ItemWrapper$IdType;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.Log;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
public final class ShopLinkUtil {
    private static final String LOGTAG = "ShopLinkUtil";
    private static final TreeSet<String> sLocalBlacklist;

    static {
        TreeSet<String> treeSet = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        sLocalBlacklist = treeSet;
        treeSet.add("");
        treeSet.add(EnvironmentCompat.MEDIA_UNKNOWN);
        treeSet.add("unknown artist");
        treeSet.add("unknown artists");
        treeSet.add("<unknown>");
        treeSet.add("various");
        treeSet.add("various artists");
        treeSet.add("Unknown Genre");
        Resources resources = AmazonApplication.getContext().getResources();
        Collections.addAll(treeSet, resources.getStringArray(R.array.dmusic_cirrus_unknown_artist_list));
        Collections.addAll(treeSet, resources.getStringArray(R.array.dmusic_cirrus_unknown_genre_list));
        Collections.addAll(treeSet, resources.getStringArray(R.array.dmusic_cirrus_unknown_album_list));
    }

    private ShopLinkUtil() {
    }

    public static boolean isArtistBlacklisted(String artistName) {
        return isBlacklisted(artistName);
    }

    public static boolean isAlbumBlacklisted(String albumName) {
        return isBlacklisted(albumName);
    }

    private static boolean isBlacklisted(String name) {
        return name == null || sLocalBlacklist.contains(name);
    }

    public static Intent getArtistExploreIntent(Context context, String artistId, String artistName, ItemWrapper$ItemType itemType) {
        if (isBlacklisted(artistName)) {
            return null;
        }
        Uri uri = ItemWrapper$IdType.MERGED_ID.getUri(itemType, artistId);
        ContributorAccessObject.Contributor defaultContributor = uri != null ? ContributorManagerImpl.INSTANCE.getDefaultContributor(uri.toString()) : null;
        if (defaultContributor == null) {
            Log.debug(LOGTAG, "could not get contributor ASIN - doing search by artist name instead.");
            return HTMLStoreActivityFactory.intentForSearch(context, HTMLStoreActivityFactory.bundleForSearchString(artistName, 1, "field-author"));
        }
        Log.debug(LOGTAG, "got contributor ASIN - launching artist detail page.");
        return HTMLStoreActivityFactory.getStartIntent(context, HTMLStoreActivityFactory.bundleForArtist(1, defaultContributor.getAsin(), defaultContributor.getArtistAsin(), artistName));
    }
}
