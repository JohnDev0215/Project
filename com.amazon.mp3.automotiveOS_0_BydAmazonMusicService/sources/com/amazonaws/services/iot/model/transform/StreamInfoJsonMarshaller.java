package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StreamFile;
import com.amazonaws.services.iot.model.StreamInfo;
import com.amazonaws.util.json.AwsJsonWriter;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class StreamInfoJsonMarshaller {
    private static StreamInfoJsonMarshaller instance;

    StreamInfoJsonMarshaller() {
    }

    public void marshall(StreamInfo streamInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (streamInfo.getStreamId() != null) {
            String streamId = streamInfo.getStreamId();
            awsJsonWriter.name("streamId");
            awsJsonWriter.value(streamId);
        }
        if (streamInfo.getStreamArn() != null) {
            String streamArn = streamInfo.getStreamArn();
            awsJsonWriter.name("streamArn");
            awsJsonWriter.value(streamArn);
        }
        if (streamInfo.getStreamVersion() != null) {
            Integer streamVersion = streamInfo.getStreamVersion();
            awsJsonWriter.name("streamVersion");
            awsJsonWriter.value(streamVersion);
        }
        if (streamInfo.getDescription() != null) {
            String description = streamInfo.getDescription();
            awsJsonWriter.name(MediaTrack.ROLE_DESCRIPTION);
            awsJsonWriter.value(description);
        }
        if (streamInfo.getFiles() != null) {
            List<StreamFile> files = streamInfo.getFiles();
            awsJsonWriter.name("files");
            awsJsonWriter.beginArray();
            for (StreamFile streamFile : files) {
                if (streamFile != null) {
                    StreamFileJsonMarshaller.getInstance().marshall(streamFile, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (streamInfo.getCreatedAt() != null) {
            Date createdAt = streamInfo.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        if (streamInfo.getLastUpdatedAt() != null) {
            Date lastUpdatedAt = streamInfo.getLastUpdatedAt();
            awsJsonWriter.name("lastUpdatedAt");
            awsJsonWriter.value(lastUpdatedAt);
        }
        if (streamInfo.getRoleArn() != null) {
            String roleArn = streamInfo.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static StreamInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new StreamInfoJsonMarshaller();
        }
        return instance;
    }
}
