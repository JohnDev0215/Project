package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.music.destination.MyLibraryDestination;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyLibraryDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/navigation/MyLibraryDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/MyLibraryDestination;", "()V", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "myLibraryDestination", "navigateTo", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyLibraryDestinationHandler implements DestinationHandler<MyLibraryDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, MyLibraryDestination myLibraryDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(myLibraryDestination, "myLibraryDestination");
        context.startActivity(getIntent(context, myLibraryDestination));
    }

    public final Intent getIntent(Context context, MyLibraryDestination myLibraryDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(myLibraryDestination, "myLibraryDestination");
        Intent intent = MusicHomeActivity.getStartIntent(context);
        intent.putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName());
        intent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
        intent.addFlags(268435456);
        intent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), myLibraryDestination.getRef());
        intent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), myLibraryDestination.getTag());
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        return intent;
    }
}
