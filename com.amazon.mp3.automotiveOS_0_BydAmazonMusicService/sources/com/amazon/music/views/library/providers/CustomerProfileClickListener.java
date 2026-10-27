package com.amazon.music.views.library.providers;

import android.view.View;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.models.CustomerProfileModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: CustomerProfileClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH&J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "", "Landroid/view/View;", "view", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "model", "", "onFollowClick", "onFollowingClick", "onFollowersClick", "Lcom/amazon/music/views/library/binders/ImageBinder;", "imageBinder", "onEditClick", "onSetupClick", "onOverflowClick", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface CustomerProfileClickListener {
    void onEditClick(CustomerProfileModel model, ImageBinder imageBinder);

    void onFollowClick(View view, CustomerProfileModel model);

    void onFollowersClick(CustomerProfileModel model);

    void onFollowingClick(CustomerProfileModel model);

    void onOverflowClick(CustomerProfileModel model);

    void onSetupClick(CustomerProfileModel model, ImageBinder imageBinder);
}
