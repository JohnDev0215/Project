package com.amazonaws.services.iot.model.transform;

import com.amazonaws.regions.ServiceAbbreviations;
import com.amazonaws.services.iot.model.Action;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class ActionJsonUnmarshaller implements Unmarshaller<Action, JsonUnmarshallerContext> {
    private static ActionJsonUnmarshaller instance;

    ActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public Action unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        Action action = new Action();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("dynamoDB")) {
                action.setDynamoDB(DynamoDBActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("dynamoDBv2")) {
                action.setDynamoDBv2(DynamoDBv2ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lambda")) {
                action.setLambda(LambdaActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(ServiceAbbreviations.SNS)) {
                action.setSns(SnsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(ServiceAbbreviations.SQS)) {
                action.setSqs(SqsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("kinesis")) {
                action.setKinesis(KinesisActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("republish")) {
                action.setRepublish(RepublishActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(ServiceAbbreviations.S3)) {
                action.setS3(S3ActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("firehose")) {
                action.setFirehose(FirehoseActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("cloudwatchMetric")) {
                action.setCloudwatchMetric(CloudwatchMetricActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("cloudwatchAlarm")) {
                action.setCloudwatchAlarm(CloudwatchAlarmActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("cloudwatchLogs")) {
                action.setCloudwatchLogs(CloudwatchLogsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("elasticsearch")) {
                action.setElasticsearch(ElasticsearchActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("salesforce")) {
                action.setSalesforce(SalesforceActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("iotAnalytics")) {
                action.setIotAnalytics(IotAnalyticsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("iotEvents")) {
                action.setIotEvents(IotEventsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("iotSiteWise")) {
                action.setIotSiteWise(IotSiteWiseActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stepFunctions")) {
                action.setStepFunctions(StepFunctionsActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("timestream")) {
                action.setTimestream(TimestreamActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("http")) {
                action.setHttp(HttpActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("kafka")) {
                action.setKafka(KafkaActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("openSearch")) {
                action.setOpenSearch(OpenSearchActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("location")) {
                action.setLocation(LocationActionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return action;
    }

    public static ActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ActionJsonUnmarshaller();
        }
        return instance;
    }
}
