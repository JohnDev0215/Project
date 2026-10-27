package com.amazon.music.social;

import com.amazon.music.social.model.Relation;
import com.amazon.music.social.model.RelationsInputRequest;
import com.amazon.music.social.model.RelationshipEntity;
import com.amazon.musicrelationshipservice.model.Edge;
import com.amazon.musicrelationshipservice.model.EdgesRequest;
import com.amazon.musicrelationshipservice.model.EdgesResponse;
import com.amazon.musicrelationshipservice.model.ObjectIdentifier;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class SocialServiceController {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) SocialServiceController.class);
    private final Configuration configuration;
    private final SocialService service;

    public SocialServiceController(Configuration configuration) {
        this.configuration = configuration;
        this.service = new MusicRelationshipService(configuration, new ServiceEnvironment_Prod());
    }

    public void addRelation(RelationsInputRequest relationsInputRequest) throws SocialException {
        validateInputs(relationsInputRequest);
        try {
            validateResponse(this.service.addEdges(createRequestPayload(relationsInputRequest)));
        } catch (VolleyError e) {
            throw new SocialException(e);
        }
    }

    public void removeRelation(RelationsInputRequest relationsInputRequest) throws SocialException {
        validateInputs(relationsInputRequest);
        try {
            validateResponse(this.service.removeEdges(createRequestPayload(relationsInputRequest)));
        } catch (VolleyError e) {
            throw new SocialException(e);
        }
    }

    private void validateInputs(RelationsInputRequest relationsInputRequest) throws SocialException {
        if (relationsInputRequest == null) {
            throw new SocialException("request object is null");
        }
        List<Relation> relations = relationsInputRequest.getRelations();
        if (relations == null || relations.size() < 1) {
            throw new SocialException("No relation specified in the input request");
        }
        Iterator<Relation> it = relations.iterator();
        while (it.hasNext()) {
            validateRelation(it.next());
        }
    }

    private void validateRelation(Relation relation) throws SocialException {
        if (relation == null) {
            throw new SocialException("Relation object is null");
        }
        if (relation.getEdgeType() == null || relation.getTarget() == null) {
            throw new SocialException("Mandatory inputs are missing from the Relation object");
        }
    }

    private EdgesRequest createRequestPayload(RelationsInputRequest relationsInputRequest) {
        EdgesRequest edgesRequest = new EdgesRequest();
        ArrayList arrayList = new ArrayList();
        edgesRequest.setDeviceId(relationsInputRequest.getDeviceId());
        edgesRequest.setDeviceType(relationsInputRequest.getDeviceType());
        edgesRequest.setChildRequest(relationsInputRequest.getChildRequest());
        Iterator<Relation> it = relationsInputRequest.getRelations().iterator();
        while (it.hasNext()) {
            arrayList.add(translate(it.next()));
        }
        edgesRequest.setEdges(arrayList);
        return edgesRequest;
    }

    private Edge translate(Relation relation) {
        Edge edge = new Edge();
        if (relation.getSource() != null) {
            edge.setSource(translate(relation.getSource()));
        }
        edge.setTarget(translate(relation.getTarget()));
        edge.setType(relation.getEdgeType().name());
        return edge;
    }

    private ObjectIdentifier translate(RelationshipEntity relationshipEntity) {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier();
        objectIdentifier.setId(relationshipEntity.getId());
        objectIdentifier.setType(relationshipEntity.getType().name());
        return objectIdentifier;
    }

    private void validateResponse(EdgesResponse edgesResponse) throws SocialException {
        if (edgesResponse != null) {
            LOG.info("Number of edges processed : " + edgesResponse.getEdgeCount());
            List<Edge> failures = edgesResponse.getFailures();
            if (failures == null || failures.isEmpty()) {
                return;
            }
            if (failures.size() > 1) {
                throw new SocialException("Failed to add " + edgesResponse.getFailures().size() + " edges in Social service");
            }
            throw new SocialException("Failed to add edge in Social Service due to " + failures.get(0).getException());
        }
    }
}
