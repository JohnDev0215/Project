package com.amazon.mp3.library.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: SongsUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001b\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"isLibraryArtistsEmpty", "", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryCirrusLibraryArtists", "Landroid/database/Cursor;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class SongsUtilKt {

    /* JADX INFO: renamed from: com.amazon.mp3.library.util.SongsUtilKt$isLibraryArtistsEmpty$1, reason: invalid class name */
    /* JADX INFO: compiled from: SongsUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.library.util.SongsUtilKt", f = "SongsUtil.kt", i = {}, l = {51}, m = "isLibraryArtistsEmpty", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SongsUtilKt.isLibraryArtistsEmpty(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.util.SongsUtilKt$queryCirrusLibraryArtists$2, reason: invalid class name */
    /* JADX INFO: compiled from: SongsUtil.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroid/database/Cursor;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.library.util.SongsUtilKt$queryCirrusLibraryArtists$2", f = "SongsUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Cursor>, Object> {
        final /* synthetic */ ContentResolver $contentResolver;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ContentResolver contentResolver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$contentResolver = contentResolver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$contentResolver, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Cursor> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String[] strArr = {"300", String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
            String[] strArr2 = {"_id", "name", "sort_name", "album_count", "track_count", "download_state", "download_status"};
            Uri filterContentUri = MediaProvider.Artists.getFilterContentUri("cirrus", "");
            ContentResolver contentResolver = this.$contentResolver;
            if (contentResolver == null) {
                return null;
            }
            return contentResolver.query(filterContentUri, strArr2, "ownership_status < ? AND playlist_track_status = ?", strArr, null);
        }
    }

    public static final Object queryCirrusLibraryArtists(ContentResolver contentResolver, Continuation<? super Cursor> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(contentResolver, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object isLibraryArtistsEmpty(android.content.ContentResolver r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            boolean r0 = r6 instanceof com.amazon.mp3.library.util.SongsUtilKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.library.util.SongsUtilKt$isLibraryArtistsEmpty$1 r0 = (com.amazon.mp3.library.util.SongsUtilKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.library.util.SongsUtilKt$isLibraryArtistsEmpty$1 r0 = new com.amazon.mp3.library.util.SongsUtilKt$isLibraryArtistsEmpty$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L32
            if (r2 != r4) goto L2a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L40
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r6)
            if (r5 == 0) goto L4e
            r0.label = r4
            java.lang.Object r6 = queryCirrusLibraryArtists(r5, r0)
            if (r6 != r1) goto L40
            return r1
        L40:
            android.database.Cursor r6 = (android.database.Cursor) r6
            if (r6 == 0) goto L4a
            int r5 = r6.getCount()
            if (r5 != 0) goto L4b
        L4a:
            r3 = r4
        L4b:
            com.amazon.mp3.util.DbUtil.closeCursor(r6)
        L4e:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.util.SongsUtilKt.isLibraryArtistsEmpty(android.content.ContentResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
