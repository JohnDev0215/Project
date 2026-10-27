package com.amazon.mp3.social;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.music.social.model.Relation;
import com.amazon.music.social.model.RelationsInputRequest;
import com.amazon.music.social.model.RelationshipEntity;
import com.amazon.music.social.rx.RxSocialServiceController;
import java.util.ArrayList;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class MusicRelationshipProviderImpl implements MusicRelationshipProvider {
    private final Context context;
    private boolean isChildRequest;
    private final RxSocialServiceController rxSocialServiceController;

    public MusicRelationshipProviderImpl(final Context context, final RxSocialServiceController rxSocialServiceController) {
        this.context = context;
        this.rxSocialServiceController = rxSocialServiceController;
    }

    @Override // com.amazon.mp3.social.MusicRelationshipProvider
    public Observable<Void> followArtist(final String artistAsin, final boolean isChildRequest) {
        this.isChildRequest = isChildRequest;
        return this.rxSocialServiceController.addRelation(createRelationsRequest(RelationshipEntity.ObjectType.ARTIST, artistAsin));
    }

    @Override // com.amazon.mp3.social.MusicRelationshipProvider
    public Observable<Void> unFollowArtist(final String artistAsin) {
        return this.rxSocialServiceController.removeRelation(createRelationsRequest(RelationshipEntity.ObjectType.ARTIST, artistAsin));
    }

    public RelationsInputRequest createRelationsRequest(final RelationshipEntity.ObjectType targetObjectType, final String targetObjectId) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(createRelation(targetObjectType, targetObjectId));
        return RelationsInputRequest.builder().deviceId(accountCredentialStorage.getDeviceId()).deviceType(accountCredentialStorage.getDeviceType()).isChildRequest(this.isChildRequest).relations(arrayList).build();
    }

    public Relation createRelation(final RelationshipEntity.ObjectType targetObjectType, final String targetObjectId) {
        return Relation.createBuilder(Relation.EdgeType.FOLLOWS, new RelationshipEntity(targetObjectId, targetObjectType)).build();
    }
}
