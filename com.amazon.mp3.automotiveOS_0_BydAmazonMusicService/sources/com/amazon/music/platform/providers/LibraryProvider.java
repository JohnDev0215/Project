package com.amazon.music.platform.providers;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.library.LibraryUseCase;
import kotlin.Metadata;

/* JADX INFO: compiled from: LibraryProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/providers/LibraryProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "T", "Lcom/amazon/music/platform/data/entity/EntityItemType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/platform/data/library/LibraryUseCase;", "getInteractor", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface LibraryProvider extends InterfaceProvider {
    <T extends EntityItem> LibraryUseCase<T> getInteractor(EntityItemType type);
}
