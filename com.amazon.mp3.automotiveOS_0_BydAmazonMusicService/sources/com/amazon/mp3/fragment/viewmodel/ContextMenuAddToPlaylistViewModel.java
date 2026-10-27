package com.amazon.mp3.fragment.viewmodel;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.fragment.contextmenu.SeeAllPlaylistItem;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: ContextMenuAddToPlaylistViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u001c\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R%\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/ContextMenuAddToPlaylistViewModel;", "Landroidx/lifecycle/ViewModel;", "Landroid/database/Cursor;", "cursor", "", "isShoveler", "Lrx/Observable;", "", "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "getPlaylistsFromCursor", "", "index", "getPlaylistItem", "", "uri", "", "fetchPlaylists", "Landroidx/lifecycle/MutableLiveData;", "_playlists", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/lifecycle/LiveData;", "playlists", "Landroidx/lifecycle/LiveData;", "getPlaylists", "()Landroidx/lifecycle/LiveData;", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ContextMenuAddToPlaylistViewModel extends ViewModel {
    private final String TAG;
    private final MutableLiveData<List<SeeAllPlaylistItem>> _playlists;
    private final LiveData<List<SeeAllPlaylistItem>> playlists;

    public ContextMenuAddToPlaylistViewModel() {
        MutableLiveData<List<SeeAllPlaylistItem>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);
        this._playlists = mutableLiveData;
        this.playlists = mutableLiveData;
        this.TAG = Reflection.getOrCreateKotlinClass(ContextMenuAddToPlaylistViewModel.class).getSimpleName();
    }

    public final LiveData<List<SeeAllPlaylistItem>> getPlaylists() {
        return this.playlists;
    }

    public static /* synthetic */ void fetchPlaylists$default(ContextMenuAddToPlaylistViewModel contextMenuAddToPlaylistViewModel, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "cirrus";
        }
        if ((i & 2) != 0) {
            z = false;
        }
        contextMenuAddToPlaylistViewModel.fetchPlaylists(str, z);
    }

    public final void fetchPlaylists(final String uri, final boolean isShoveler) {
        final String str = isShoveler ? "dateLastModified" : null;
        Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.fragment.viewmodel.ContextMenuAddToPlaylistViewModel$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ContextMenuAddToPlaylistViewModel.m1164fetchPlaylists$lambda1(uri, str);
            }
        }).flatMap(new Func1() { // from class: com.amazon.mp3.fragment.viewmodel.ContextMenuAddToPlaylistViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return ContextMenuAddToPlaylistViewModel.m1165fetchPlaylists$lambda3(this.f$0, isShoveler, (Cursor) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.fragment.viewmodel.ContextMenuAddToPlaylistViewModel$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContextMenuAddToPlaylistViewModel.m1166fetchPlaylists$lambda4(this.f$0, (List) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.fragment.viewmodel.ContextMenuAddToPlaylistViewModel$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContextMenuAddToPlaylistViewModel.m1167fetchPlaylists$lambda5(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaylists$lambda-1, reason: not valid java name */
    public static final Cursor m1164fetchPlaylists$lambda1(String str, String str2) {
        return MediaProvider.getInstance().query(MediaProvider.UdoPlaylists.getContentUri(str), new String[]{"_id", "name", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "luid"}, null, null, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaylists$lambda-3, reason: not valid java name */
    public static final Observable m1165fetchPlaylists$lambda3(ContextMenuAddToPlaylistViewModel this$0, boolean z, Cursor cursor) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (cursor == null) {
            return null;
        }
        return this$0.getPlaylistsFromCursor(cursor, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaylists$lambda-4, reason: not valid java name */
    public static final void m1166fetchPlaylists$lambda4(ContextMenuAddToPlaylistViewModel this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._playlists.setValue(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPlaylists$lambda-5, reason: not valid java name */
    public static final void m1167fetchPlaylists$lambda5(ContextMenuAddToPlaylistViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Error fetching playlists", th);
    }

    private final Observable<List<SeeAllPlaylistItem>> getPlaylistsFromCursor(Cursor cursor, boolean isShoveler) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            boolean z = true;
            int i = 1;
            while (!cursor.isAfterLast() && z) {
                arrayList.add(getPlaylistItem(cursor, i));
                i++;
                cursor.moveToNext();
                z = !isShoveler || i <= 5;
            }
        }
        cursor.close();
        Observable<List<SeeAllPlaylistItem>> observableJust = Observable.just(arrayList);
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(result)");
        return observableJust;
    }

    private final SeeAllPlaylistItem getPlaylistItem(Cursor cursor, int index) {
        String string = cursor.getString(cursor.getColumnIndex("_id"));
        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…sDatabase.Playlists._ID))");
        String string2 = cursor.getString(cursor.getColumnIndex("luid"));
        Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(cursor.…Database.Playlists.LUID))");
        String string3 = cursor.getString(cursor.getColumnIndex("name"));
        Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(cursor.…Database.Playlists.NAME))");
        return new SeeAllPlaylistItem(string, string2, string3, index, false, 16, null);
    }
}
