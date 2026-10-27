package com.amazon.music.platform.data.library;

import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: LibraryUseCase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\u00020\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u001b\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/data/library/LibraryUseCase;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "T", "", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "identifier", "", "isInLibrary", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToLibrary", "removeFromLibrary", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface LibraryUseCase<T extends EntityItem> {
    Object addToLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation);

    Object isInLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation);

    Object removeFromLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation);
}
