package com.amazon.mp3.dialog;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: RenamePlaylistDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0015B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/dialog/RenamePlaylistDialog;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/Playlist;", "title", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/dialog/RenamePlaylistDialog$OnPlaylistRenamedListener;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/item/Playlist;Ljava/lang/String;Lcom/amazon/mp3/dialog/RenamePlaylistDialog$OnPlaylistRenamedListener;)V", "contentUri", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "resources", "Landroid/content/res/Resources;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "renamePlaylist", "", "show", "OnPlaylistRenamedListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RenamePlaylistDialog {
    private final FragmentActivity activity;
    private final Uri contentUri;
    private final OnPlaylistRenamedListener listener;
    private final Playlist playlist;
    private final Resources resources;
    private final StyleSheet styleSheet;
    private String title;

    /* JADX INFO: compiled from: RenamePlaylistDialog.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/dialog/RenamePlaylistDialog$OnPlaylistRenamedListener;", "", "onRenameCompleted", "", "title", "", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/Playlist;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnPlaylistRenamedListener {
        void onRenameCompleted(String title, Playlist playlist);
    }

    public RenamePlaylistDialog(FragmentActivity activity, Playlist playlist, String title, OnPlaylistRenamedListener onPlaylistRenamedListener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(title, "title");
        this.activity = activity;
        this.playlist = playlist;
        this.title = title;
        this.listener = onPlaylistRenamedListener;
        this.styleSheet = StyleSheetProvider.getStyleSheet().getValue();
        this.resources = activity.getResources();
        this.contentUri = playlist.getContentUri();
    }

    public final void show() {
        FontStyle fontStyle;
        List<Fragment> fragments = this.activity.getSupportFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "activity.supportFragmentManager.fragments");
        if (((Fragment) CollectionsKt___CollectionsKt.firstOrNull((List) fragments)) == null) {
            return;
        }
        EditText editText = new EditText(this.activity);
        editText.setBackgroundResource(R.drawable.bauhaus_dialog_form_field_border);
        editText.setText(this.title);
        editText.setHint(R.string.dmusic_playlist_create_edit_hint);
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY)) != null) {
            FontUtil.INSTANCE.applyFontStyle(editText, fontStyle);
        }
        editText.addTextChangedListener(new TextWatcher() { // from class: com.amazon.mp3.dialog.RenamePlaylistDialog$show$1$editText$1$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
                this.this$0.title = s.toString();
            }
        });
        new BauhausDialogBuilder(this.activity).setTitle(this.resources.getString(R.string.dmusic_playlist_rename_playlist)).setView(editText).setPositiveButton(this.resources.getString(R.string.dmusic_prime_search_apply_text), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.RenamePlaylistDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RenamePlaylistDialog.m902show$lambda3$lambda2(this.f$0, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: show$lambda-3$lambda-2, reason: not valid java name */
    public static final void m902show$lambda3$lambda2(RenamePlaylistDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.renamePlaylist();
    }

    private final void renamePlaylist() {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.dialog.RenamePlaylistDialog$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RenamePlaylistDialog.m900renamePlaylist$lambda5(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnCompleted(new Action0() { // from class: com.amazon.mp3.dialog.RenamePlaylistDialog$$ExternalSyntheticLambda2
            @Override // rx.functions.Action0
            public final void call() {
                RenamePlaylistDialog.m901renamePlaylist$lambda6(this.f$0);
            }
        }).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: renamePlaylist$lambda-5, reason: not valid java name */
    public static final void m900renamePlaylist$lambda5(RenamePlaylistDialog this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri("cirrus", PlaylistUtil.getPlaylistId(this$0.contentUri));
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this$0.title);
        ContentResolver contentResolver = this$0.activity.getContentResolver();
        if (contentResolver != null) {
            contentResolver.update(contentUri, contentValues, null, null);
        }
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: renamePlaylist$lambda-6, reason: not valid java name */
    public static final void m901renamePlaylist$lambda6(RenamePlaylistDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnPlaylistRenamedListener onPlaylistRenamedListener = this$0.listener;
        if (onPlaylistRenamedListener == null) {
            return;
        }
        onPlaylistRenamedListener.onRenameCompleted(this$0.title, this$0.playlist);
    }
}
