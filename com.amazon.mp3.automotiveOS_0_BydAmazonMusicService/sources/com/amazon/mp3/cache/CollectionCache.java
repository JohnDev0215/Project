package com.amazon.mp3.cache;

import android.net.Uri;
import kotlin.Metadata;

/* JADX INFO: compiled from: CollectionCache.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H&J\b\u0010\u0007\u001a\u00020\u0006H&J\u0019\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\bH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/cache/CollectionCache;", "Parent", "Child", "", "Ljava/lang/Class;", "childClass", "", "getChildrenItemCount", "", "id", "getChildItem", "(Ljava/lang/String;)Ljava/lang/Object;", "Landroid/net/Uri;", "getChildUri", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface CollectionCache<Parent, Child> {
    Class<Child> childClass();

    Child getChildItem(String id);

    Uri getChildUri(String id);

    int getChildrenItemCount();
}
