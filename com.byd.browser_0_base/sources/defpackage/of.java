package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import defpackage.vg2;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class of implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ KeyEvent.Callback f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ KeyEvent.Callback h;
    public final /* synthetic */ Object i;

    public /* synthetic */ of(KeyEvent.Callback callback, Object obj, KeyEvent.Callback callback2, Object obj2, int i) {
        this.e = i;
        this.f = callback;
        this.g = obj;
        this.h = callback2;
        this.i = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        KeyEvent.Callback callback = this.h;
        Object obj = this.g;
        KeyEvent.Callback callback2 = this.f;
        switch (i) {
            case 0:
                BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity = (BookmarkAddEditFolderActivity) callback2;
                Button button = (Button) obj;
                Button button2 = (Button) callback;
                int i2 = BookmarkAddEditFolderActivity.Q;
                if (view == button) {
                    bookmarkAddEditFolderActivity.finish();
                } else if (view == button2) {
                    if (TextUtils.isEmpty(bookmarkAddEditFolderActivity.L1())) {
                        bookmarkAddEditFolderActivity.K1(button2);
                    } else {
                        if (bookmarkAddEditFolderActivity.I) {
                            BookmarkId bookmarkIdB = bookmarkAddEditFolderActivity.K.b(bookmarkAddEditFolderActivity.L1(), bookmarkAddEditFolderActivity.f56J);
                            if (bookmarkIdB == null) {
                                bookmarkAddEditFolderActivity.M1(false);
                            } else {
                                bookmarkAddEditFolderActivity.M1(true);
                                Intent intent = new Intent();
                                intent.putExtra("BookmarkAddEditFolderActivity.createdBookmark", bookmarkIdB.toString());
                                bookmarkAddEditFolderActivity.setResult(-1, intent);
                            }
                        } else if (!bookmarkAddEditFolderActivity.K.f(bookmarkAddEditFolderActivity.M)) {
                            bookmarkAddEditFolderActivity.M1(false);
                            Log.e(bookmarkAddEditFolderActivity.getClass().getSimpleName(), bookmarkAddEditFolderActivity.K.h(bookmarkAddEditFolderActivity.M).a + "   文件夹不存在");
                        } else {
                            BookmarkModel bookmarkModel = bookmarkAddEditFolderActivity.K;
                            BookmarkId bookmarkId = bookmarkAddEditFolderActivity.M;
                            String strL1 = bookmarkAddEditFolderActivity.L1();
                            bookmarkModel.getClass();
                            Object obj2 = ThreadUtils.a;
                            long j = bookmarkModel.a;
                            if (j != 0) {
                                N.MWvvdW1T(j, bookmarkModel, bookmarkId.getId(), bookmarkId.getType(), strL1);
                            }
                        }
                        bookmarkAddEditFolderActivity.M1(true);
                        bookmarkAddEditFolderActivity.finish();
                    }
                }
                if (view == bookmarkAddEditFolderActivity.O) {
                    bookmarkAddEditFolderActivity.N.setText("");
                }
                break;
            default:
                vg2.a aVar = (vg2.a) this.i;
                ((SearchView) callback2).q("");
                vg2.a((MenuItem) obj, "", (Activity) callback);
                ((ke0) aVar).t("");
                break;
        }
    }
}
