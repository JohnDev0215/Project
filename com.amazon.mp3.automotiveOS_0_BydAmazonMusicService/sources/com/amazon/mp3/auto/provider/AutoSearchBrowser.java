package com.amazon.mp3.auto.provider;

import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;

/* JADX INFO: compiled from: AutoSearchBrowser.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/auto/provider/AutoSearchBrowser;", "", "", SearchIntents.EXTRA_QUERY, "Lcom/amazon/mp3/auto/provider/AutoSearchListener;", "searchListener", "", "search", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface AutoSearchBrowser {
    void search(String query, AutoSearchListener searchListener);
}
