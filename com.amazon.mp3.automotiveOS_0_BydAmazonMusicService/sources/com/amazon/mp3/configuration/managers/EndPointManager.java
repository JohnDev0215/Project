package com.amazon.mp3.configuration.managers;

import com.amazon.mp3.configuration.models.EndPoint;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class EndPointManager {
    private static final String TAG = "com.amazon.mp3.configuration.managers.EndPointManager";
    private HashMap<String, EndPoint> mEndPoints;

    public static class UnknownEndpointException extends Exception {
    }

    public EndPointManager(HashMap<String, EndPoint> endPoints) {
        this.mEndPoints = endPoints;
    }

    public EndPoint id(String id) throws UnknownEndpointException {
        EndPoint endPoint = this.mEndPoints.get(id);
        if (endPoint != null) {
            return endPoint;
        }
        Log.error(TAG, "$%s id not defined in the environment file.", id);
        throw new UnknownEndpointException();
    }

    public Set<String> idSet() {
        return this.mEndPoints.keySet();
    }
}
