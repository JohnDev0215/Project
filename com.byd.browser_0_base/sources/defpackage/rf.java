package defpackage;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import defpackage.le3;
import defpackage.pv0;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.bookmarks.BookmarkEditActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.password_entry_edit.CredentialEditFragmentView;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.signin.SyncConsentFragment;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.chrome.browser.ui.appmenu.a;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class rf implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ rf(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                BookmarkEditActivity bookmarkEditActivity = (BookmarkEditActivity) obj2;
                if (view != ((Button) obj)) {
                    if (view != bookmarkEditActivity.M) {
                        if (view == bookmarkEditActivity.N) {
                            bookmarkEditActivity.K.setText("");
                        } else if (view == bookmarkEditActivity.O) {
                            bookmarkEditActivity.L.setText("");
                        }
                        break;
                    } else {
                        boolean zIsEmpty = TextUtils.isEmpty(bookmarkEditActivity.L1(bookmarkEditActivity.K));
                        boolean zIsEmpty2 = TextUtils.isEmpty(bookmarkEditActivity.L1(bookmarkEditActivity.L));
                        if (!zIsEmpty && !zIsEmpty2) {
                            if (bookmarkEditActivity.I.f(bookmarkEditActivity.f57J)) {
                                GURL gurl = bookmarkEditActivity.I.h(bookmarkEditActivity.f57J).b;
                                String strL1 = bookmarkEditActivity.L1(bookmarkEditActivity.K);
                                String strL12 = bookmarkEditActivity.L1(bookmarkEditActivity.L);
                                if (!TextUtils.isEmpty(bookmarkEditActivity.L1(bookmarkEditActivity.K))) {
                                    BookmarkModel bookmarkModel = bookmarkEditActivity.I;
                                    BookmarkId bookmarkId = bookmarkEditActivity.f57J;
                                    bookmarkModel.getClass();
                                    Object obj3 = ThreadUtils.a;
                                    long j = bookmarkModel.a;
                                    if (j != 0) {
                                        N.MWvvdW1T(j, bookmarkModel, bookmarkId.getId(), bookmarkId.getType(), strL1);
                                    }
                                }
                                if (!TextUtils.isEmpty(bookmarkEditActivity.L1(bookmarkEditActivity.K))) {
                                    BookmarkItem bookmarkItemH = bookmarkEditActivity.I.h(bookmarkEditActivity.f57J);
                                    if (bookmarkItemH.a() && bookmarkItemH.c.getType() == 0) {
                                        GURL gurlH = oj2.h(strL12);
                                        if (gurlH.b && !gurlH.equals(gurl)) {
                                            BookmarkModel bookmarkModel2 = bookmarkEditActivity.I;
                                            BookmarkId bookmarkId2 = bookmarkEditActivity.f57J;
                                            bookmarkModel2.getClass();
                                            Object obj4 = ThreadUtils.a;
                                            long j2 = bookmarkModel2.a;
                                            if (j2 != 0) {
                                                N.MiNuz9ZT(j2, bookmarkModel2, bookmarkId2.getId(), bookmarkId2.getType(), gurlH);
                                            }
                                        }
                                    }
                                }
                                int i2 = R$drawable.icon_dark_public_green_cherk;
                                int i3 = R$string.card_edit_sucess;
                                Toast toast = new Toast(bookmarkEditActivity);
                                View viewInflate = LayoutInflater.from(bookmarkEditActivity).inflate(R$layout.layout_toast_clear_success, (ViewGroup) null, false);
                                ImageView imageView = (ImageView) viewInflate.findViewById(R$id.iv_toast);
                                TextView textView = (TextView) viewInflate.findViewById(R$id.tv_toast);
                                toast.setView(viewInflate);
                                if (imageView != null) {
                                    imageView.setImageResource(i2);
                                }
                                if (textView != null) {
                                    textView.setText(i3);
                                }
                                toast.show();
                            } else {
                                int i4 = R$drawable.icon_dark_public_green_cherk;
                                int i5 = R$string.save_failed;
                                Toast toast2 = new Toast(bookmarkEditActivity);
                                View viewInflate2 = LayoutInflater.from(bookmarkEditActivity).inflate(R$layout.layout_toast_clear_success, (ViewGroup) null, false);
                                ImageView imageView2 = (ImageView) viewInflate2.findViewById(R$id.iv_toast);
                                TextView textView2 = (TextView) viewInflate2.findViewById(R$id.tv_toast);
                                toast2.setView(viewInflate2);
                                if (imageView2 != null) {
                                    imageView2.setImageResource(i4);
                                }
                                if (textView2 != null) {
                                    textView2.setText(i5);
                                }
                                toast2.show();
                            }
                            bookmarkEditActivity.finish();
                            break;
                        }
                    }
                } else {
                    int i6 = BookmarkEditActivity.Q;
                    bookmarkEditActivity.finish();
                    break;
                }
                break;
            case 1:
                int i7 = ux0.D;
                ((Callback) ((PropertyModel) obj2).j(t81.q)).onResult((OfflineItem) obj);
                break;
            case 2:
                ((Callback) obj2).onResult(new u10((s10) obj, true));
                break;
            case 3:
                t63 t63Var = (t63) obj2;
                b11 b11Var = (b11) obj;
                t63Var.getClass();
                int i8 = b11Var.a;
                if (t63Var.h.a != i8) {
                    t63Var.g.l(di1.l, t63Var.i == i8);
                    for (xa1 xa1Var : t63Var.c) {
                        int iH = xa1Var.b.h(u63.d);
                        int i9 = t63Var.h.a;
                        PropertyModel propertyModel = xa1Var.b;
                        if (iH == i9) {
                            propertyModel.l(u63.f, false);
                        } else if (iH == i8) {
                            propertyModel.l(u63.f, true);
                        }
                    }
                    t63Var.h = b11Var;
                    break;
                }
                break;
            case 4:
                CredentialEditFragmentView credentialEditFragmentView = (CredentialEditFragmentView) obj2;
                int i10 = CredentialEditFragmentView.o0;
                credentialEditFragmentView.getClass();
                ((CredentialEntryFragmentViewBase.b) obj).e();
                credentialEditFragmentView.M0();
                break;
            case 5:
                ((Callback) ((PropertyModel) obj2).j(le3.a.e)).onResult((Credential) obj);
                break;
            case 6:
                ((Callback) ((PropertyModel) obj2).j(le3.e.d)).onResult((WebAuthnCredential) obj);
                break;
            case 7:
                int[] iArr = a.a;
                ((b7) obj2).a((PropertyModel) obj);
                break;
            case 8:
                pv0.a aVar = (pv0.a) obj2;
                aVar.k.announceForAccessibility(aVar.a.getResources().getString(org.chromium.chrome.browser.ui.fast_checkout.R$string.fast_checkout_home_sheet_accept_button_clicked_description));
                ((ov0) obj).c();
                break;
            case 9:
                xx2 xx2Var = (xx2) obj2;
                xx2Var.e("Continued");
                xx2Var.h.getClass();
                int i11 = SyncConsentFragment.t0;
                Bundle bundle = new Bundle();
                bundle.putInt("SyncConsentFragmentBase.SigninFlowType", 2);
                bundle.putInt("SyncConsentFragmentBase.AccessPoint", xx2Var.c);
                bundle.putInt("SyncConsentFragment.PersonalizedPromoAction", 3);
                a8.S((Context) obj, bundle);
                break;
            case 10:
                ((Callback) ((PropertyModel) obj2).j(h1.b)).onResult((fb0) obj);
                break;
            default:
                ((tl3) obj2).b.setVisibility(8);
                ((Runnable) obj).run();
                break;
        }
    }
}
