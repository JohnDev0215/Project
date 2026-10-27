package com.amazonaws.services.iot.model.transform;

import com.amazonaws.regions.ServiceAbbreviations;
import com.amazonaws.services.iot.model.Action;
import com.amazonaws.services.iot.model.CloudwatchAlarmAction;
import com.amazonaws.services.iot.model.CloudwatchLogsAction;
import com.amazonaws.services.iot.model.CloudwatchMetricAction;
import com.amazonaws.services.iot.model.DynamoDBAction;
import com.amazonaws.services.iot.model.DynamoDBv2Action;
import com.amazonaws.services.iot.model.ElasticsearchAction;
import com.amazonaws.services.iot.model.FirehoseAction;
import com.amazonaws.services.iot.model.HttpAction;
import com.amazonaws.services.iot.model.IotAnalyticsAction;
import com.amazonaws.services.iot.model.IotEventsAction;
import com.amazonaws.services.iot.model.IotSiteWiseAction;
import com.amazonaws.services.iot.model.KafkaAction;
import com.amazonaws.services.iot.model.KinesisAction;
import com.amazonaws.services.iot.model.LambdaAction;
import com.amazonaws.services.iot.model.LocationAction;
import com.amazonaws.services.iot.model.OpenSearchAction;
import com.amazonaws.services.iot.model.RepublishAction;
import com.amazonaws.services.iot.model.S3Action;
import com.amazonaws.services.iot.model.SalesforceAction;
import com.amazonaws.services.iot.model.SnsAction;
import com.amazonaws.services.iot.model.SqsAction;
import com.amazonaws.services.iot.model.StepFunctionsAction;
import com.amazonaws.services.iot.model.TimestreamAction;
import com.amazonaws.util.json.AwsJsonWriter;

/* JADX INFO: loaded from: classes5.dex */
class ActionJsonMarshaller {
    private static ActionJsonMarshaller instance;

    ActionJsonMarshaller() {
    }

    public void marshall(Action action, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (action.getDynamoDB() != null) {
            DynamoDBAction dynamoDB = action.getDynamoDB();
            awsJsonWriter.name("dynamoDB");
            DynamoDBActionJsonMarshaller.getInstance().marshall(dynamoDB, awsJsonWriter);
        }
        if (action.getDynamoDBv2() != null) {
            DynamoDBv2Action dynamoDBv2 = action.getDynamoDBv2();
            awsJsonWriter.name("dynamoDBv2");
            DynamoDBv2ActionJsonMarshaller.getInstance().marshall(dynamoDBv2, awsJsonWriter);
        }
        if (action.getLambda() != null) {
            LambdaAction lambda = action.getLambda();
            awsJsonWriter.name("lambda");
            LambdaActionJsonMarshaller.getInstance().marshall(lambda, awsJsonWriter);
        }
        if (action.getSns() != null) {
            SnsAction sns = action.getSns();
            awsJsonWriter.name(ServiceAbbreviations.SNS);
            SnsActionJsonMarshaller.getInstance().marshall(sns, awsJsonWriter);
        }
        if (action.getSqs() != null) {
            SqsAction sqs = action.getSqs();
            awsJsonWriter.name(ServiceAbbreviations.SQS);
            SqsActionJsonMarshaller.getInstance().marshall(sqs, awsJsonWriter);
        }
        if (action.getKinesis() != null) {
            KinesisAction kinesis = action.getKinesis();
            awsJsonWriter.name("kinesis");
            KinesisActionJsonMarshaller.getInstance().marshall(kinesis, awsJsonWriter);
        }
        if (action.getRepublish() != null) {
            RepublishAction republish = action.getRepublish();
            awsJsonWriter.name("republish");
            RepublishActionJsonMarshaller.getInstance().marshall(republish, awsJsonWriter);
        }
        if (action.getS3() != null) {
            S3Action s3 = action.getS3();
            awsJsonWriter.name(ServiceAbbreviations.S3);
            S3ActionJsonMarshaller.getInstance().marshall(s3, awsJsonWriter);
        }
        if (action.getFirehose() != null) {
            FirehoseAction firehose = action.getFirehose();
            awsJsonWriter.name("firehose");
            FirehoseActionJsonMarshaller.getInstance().marshall(firehose, awsJsonWriter);
        }
        if (action.getCloudwatchMetric() != null) {
            CloudwatchMetricAction cloudwatchMetric = action.getCloudwatchMetric();
            awsJsonWriter.name("cloudwatchMetric");
            CloudwatchMetricActionJsonMarshaller.getInstance().marshall(cloudwatchMetric, awsJsonWriter);
        }
        if (action.getCloudwatchAlarm() != null) {
            CloudwatchAlarmAction cloudwatchAlarm = action.getCloudwatchAlarm();
            awsJsonWriter.name("cloudwatchAlarm");
            CloudwatchAlarmActionJsonMarshaller.getInstance().marshall(cloudwatchAlarm, awsJsonWriter);
        }
        if (action.getCloudwatchLogs() != null) {
            CloudwatchLogsAction cloudwatchLogs = action.getCloudwatchLogs();
            awsJsonWriter.name("cloudwatchLogs");
            CloudwatchLogsActionJsonMarshaller.getInstance().marshall(cloudwatchLogs, awsJsonWriter);
        }
        if (action.getElasticsearch() != null) {
            ElasticsearchAction elasticsearch = action.getElasticsearch();
            awsJsonWriter.name("elasticsearch");
            ElasticsearchActionJsonMarshaller.getInstance().marshall(elasticsearch, awsJsonWriter);
        }
        if (action.getSalesforce() != null) {
            SalesforceAction salesforce = action.getSalesforce();
            awsJsonWriter.name("salesforce");
            SalesforceActionJsonMarshaller.getInstance().marshall(salesforce, awsJsonWriter);
        }
        if (action.getIotAnalytics() != null) {
            IotAnalyticsAction iotAnalytics = action.getIotAnalytics();
            awsJsonWriter.name("iotAnalytics");
            IotAnalyticsActionJsonMarshaller.getInstance().marshall(iotAnalytics, awsJsonWriter);
        }
        if (action.getIotEvents() != null) {
            IotEventsAction iotEvents = action.getIotEvents();
            awsJsonWriter.name("iotEvents");
            IotEventsActionJsonMarshaller.getInstance().marshall(iotEvents, awsJsonWriter);
        }
        if (action.getIotSiteWise() != null) {
            IotSiteWiseAction iotSiteWise = action.getIotSiteWise();
            awsJsonWriter.name("iotSiteWise");
            IotSiteWiseActionJsonMarshaller.getInstance().marshall(iotSiteWise, awsJsonWriter);
        }
        if (action.getStepFunctions() != null) {
            StepFunctionsAction stepFunctions = action.getStepFunctions();
            awsJsonWriter.name("stepFunctions");
            StepFunctionsActionJsonMarshaller.getInstance().marshall(stepFunctions, awsJsonWriter);
        }
        if (action.getTimestream() != null) {
            TimestreamAction timestream = action.getTimestream();
            awsJsonWriter.name("timestream");
            TimestreamActionJsonMarshaller.getInstance().marshall(timestream, awsJsonWriter);
        }
        if (action.getHttp() != null) {
            HttpAction http = action.getHttp();
            awsJsonWriter.name("http");
            HttpActionJsonMarshaller.getInstance().marshall(http, awsJsonWriter);
        }
        if (action.getKafka() != null) {
            KafkaAction kafka = action.getKafka();
            awsJsonWriter.name("kafka");
            KafkaActionJsonMarshaller.getInstance().marshall(kafka, awsJsonWriter);
        }
        if (action.getOpenSearch() != null) {
            OpenSearchAction openSearch = action.getOpenSearch();
            awsJsonWriter.name("openSearch");
            OpenSearchActionJsonMarshaller.getInstance().marshall(openSearch, awsJsonWriter);
        }
        if (action.getLocation() != null) {
            LocationAction location = action.getLocation();
            awsJsonWriter.name("location");
            LocationActionJsonMarshaller.getInstance().marshall(location, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static ActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ActionJsonMarshaller();
        }
        return instance;
    }
}
