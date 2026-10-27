package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Action implements Serializable {
    private CloudwatchAlarmAction cloudwatchAlarm;
    private CloudwatchLogsAction cloudwatchLogs;
    private CloudwatchMetricAction cloudwatchMetric;
    private DynamoDBAction dynamoDB;
    private DynamoDBv2Action dynamoDBv2;
    private ElasticsearchAction elasticsearch;
    private FirehoseAction firehose;
    private HttpAction http;
    private IotAnalyticsAction iotAnalytics;
    private IotEventsAction iotEvents;
    private IotSiteWiseAction iotSiteWise;
    private KafkaAction kafka;
    private KinesisAction kinesis;
    private LambdaAction lambda;
    private LocationAction location;
    private OpenSearchAction openSearch;
    private RepublishAction republish;
    private S3Action s3;
    private SalesforceAction salesforce;
    private SnsAction sns;
    private SqsAction sqs;
    private StepFunctionsAction stepFunctions;
    private TimestreamAction timestream;

    public DynamoDBAction getDynamoDB() {
        return this.dynamoDB;
    }

    public void setDynamoDB(DynamoDBAction dynamoDBAction) {
        this.dynamoDB = dynamoDBAction;
    }

    public Action withDynamoDB(DynamoDBAction dynamoDBAction) {
        this.dynamoDB = dynamoDBAction;
        return this;
    }

    public DynamoDBv2Action getDynamoDBv2() {
        return this.dynamoDBv2;
    }

    public void setDynamoDBv2(DynamoDBv2Action dynamoDBv2Action) {
        this.dynamoDBv2 = dynamoDBv2Action;
    }

    public Action withDynamoDBv2(DynamoDBv2Action dynamoDBv2Action) {
        this.dynamoDBv2 = dynamoDBv2Action;
        return this;
    }

    public LambdaAction getLambda() {
        return this.lambda;
    }

    public void setLambda(LambdaAction lambdaAction) {
        this.lambda = lambdaAction;
    }

    public Action withLambda(LambdaAction lambdaAction) {
        this.lambda = lambdaAction;
        return this;
    }

    public SnsAction getSns() {
        return this.sns;
    }

    public void setSns(SnsAction snsAction) {
        this.sns = snsAction;
    }

    public Action withSns(SnsAction snsAction) {
        this.sns = snsAction;
        return this;
    }

    public SqsAction getSqs() {
        return this.sqs;
    }

    public void setSqs(SqsAction sqsAction) {
        this.sqs = sqsAction;
    }

    public Action withSqs(SqsAction sqsAction) {
        this.sqs = sqsAction;
        return this;
    }

    public KinesisAction getKinesis() {
        return this.kinesis;
    }

    public void setKinesis(KinesisAction kinesisAction) {
        this.kinesis = kinesisAction;
    }

    public Action withKinesis(KinesisAction kinesisAction) {
        this.kinesis = kinesisAction;
        return this;
    }

    public RepublishAction getRepublish() {
        return this.republish;
    }

    public void setRepublish(RepublishAction republishAction) {
        this.republish = republishAction;
    }

    public Action withRepublish(RepublishAction republishAction) {
        this.republish = republishAction;
        return this;
    }

    public S3Action getS3() {
        return this.s3;
    }

    public void setS3(S3Action s3Action) {
        this.s3 = s3Action;
    }

    public Action withS3(S3Action s3Action) {
        this.s3 = s3Action;
        return this;
    }

    public FirehoseAction getFirehose() {
        return this.firehose;
    }

    public void setFirehose(FirehoseAction firehoseAction) {
        this.firehose = firehoseAction;
    }

    public Action withFirehose(FirehoseAction firehoseAction) {
        this.firehose = firehoseAction;
        return this;
    }

    public CloudwatchMetricAction getCloudwatchMetric() {
        return this.cloudwatchMetric;
    }

    public void setCloudwatchMetric(CloudwatchMetricAction cloudwatchMetricAction) {
        this.cloudwatchMetric = cloudwatchMetricAction;
    }

    public Action withCloudwatchMetric(CloudwatchMetricAction cloudwatchMetricAction) {
        this.cloudwatchMetric = cloudwatchMetricAction;
        return this;
    }

    public CloudwatchAlarmAction getCloudwatchAlarm() {
        return this.cloudwatchAlarm;
    }

    public void setCloudwatchAlarm(CloudwatchAlarmAction cloudwatchAlarmAction) {
        this.cloudwatchAlarm = cloudwatchAlarmAction;
    }

    public Action withCloudwatchAlarm(CloudwatchAlarmAction cloudwatchAlarmAction) {
        this.cloudwatchAlarm = cloudwatchAlarmAction;
        return this;
    }

    public CloudwatchLogsAction getCloudwatchLogs() {
        return this.cloudwatchLogs;
    }

    public void setCloudwatchLogs(CloudwatchLogsAction cloudwatchLogsAction) {
        this.cloudwatchLogs = cloudwatchLogsAction;
    }

    public Action withCloudwatchLogs(CloudwatchLogsAction cloudwatchLogsAction) {
        this.cloudwatchLogs = cloudwatchLogsAction;
        return this;
    }

    public ElasticsearchAction getElasticsearch() {
        return this.elasticsearch;
    }

    public void setElasticsearch(ElasticsearchAction elasticsearchAction) {
        this.elasticsearch = elasticsearchAction;
    }

    public Action withElasticsearch(ElasticsearchAction elasticsearchAction) {
        this.elasticsearch = elasticsearchAction;
        return this;
    }

    public SalesforceAction getSalesforce() {
        return this.salesforce;
    }

    public void setSalesforce(SalesforceAction salesforceAction) {
        this.salesforce = salesforceAction;
    }

    public Action withSalesforce(SalesforceAction salesforceAction) {
        this.salesforce = salesforceAction;
        return this;
    }

    public IotAnalyticsAction getIotAnalytics() {
        return this.iotAnalytics;
    }

    public void setIotAnalytics(IotAnalyticsAction iotAnalyticsAction) {
        this.iotAnalytics = iotAnalyticsAction;
    }

    public Action withIotAnalytics(IotAnalyticsAction iotAnalyticsAction) {
        this.iotAnalytics = iotAnalyticsAction;
        return this;
    }

    public IotEventsAction getIotEvents() {
        return this.iotEvents;
    }

    public void setIotEvents(IotEventsAction iotEventsAction) {
        this.iotEvents = iotEventsAction;
    }

    public Action withIotEvents(IotEventsAction iotEventsAction) {
        this.iotEvents = iotEventsAction;
        return this;
    }

    public IotSiteWiseAction getIotSiteWise() {
        return this.iotSiteWise;
    }

    public void setIotSiteWise(IotSiteWiseAction iotSiteWiseAction) {
        this.iotSiteWise = iotSiteWiseAction;
    }

    public Action withIotSiteWise(IotSiteWiseAction iotSiteWiseAction) {
        this.iotSiteWise = iotSiteWiseAction;
        return this;
    }

    public StepFunctionsAction getStepFunctions() {
        return this.stepFunctions;
    }

    public void setStepFunctions(StepFunctionsAction stepFunctionsAction) {
        this.stepFunctions = stepFunctionsAction;
    }

    public Action withStepFunctions(StepFunctionsAction stepFunctionsAction) {
        this.stepFunctions = stepFunctionsAction;
        return this;
    }

    public TimestreamAction getTimestream() {
        return this.timestream;
    }

    public void setTimestream(TimestreamAction timestreamAction) {
        this.timestream = timestreamAction;
    }

    public Action withTimestream(TimestreamAction timestreamAction) {
        this.timestream = timestreamAction;
        return this;
    }

    public HttpAction getHttp() {
        return this.http;
    }

    public void setHttp(HttpAction httpAction) {
        this.http = httpAction;
    }

    public Action withHttp(HttpAction httpAction) {
        this.http = httpAction;
        return this;
    }

    public KafkaAction getKafka() {
        return this.kafka;
    }

    public void setKafka(KafkaAction kafkaAction) {
        this.kafka = kafkaAction;
    }

    public Action withKafka(KafkaAction kafkaAction) {
        this.kafka = kafkaAction;
        return this;
    }

    public OpenSearchAction getOpenSearch() {
        return this.openSearch;
    }

    public void setOpenSearch(OpenSearchAction openSearchAction) {
        this.openSearch = openSearchAction;
    }

    public Action withOpenSearch(OpenSearchAction openSearchAction) {
        this.openSearch = openSearchAction;
        return this;
    }

    public LocationAction getLocation() {
        return this.location;
    }

    public void setLocation(LocationAction locationAction) {
        this.location = locationAction;
    }

    public Action withLocation(LocationAction locationAction) {
        this.location = locationAction;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDynamoDB() != null) {
            sb.append("dynamoDB: " + getDynamoDB() + ",");
        }
        if (getDynamoDBv2() != null) {
            sb.append("dynamoDBv2: " + getDynamoDBv2() + ",");
        }
        if (getLambda() != null) {
            sb.append("lambda: " + getLambda() + ",");
        }
        if (getSns() != null) {
            sb.append("sns: " + getSns() + ",");
        }
        if (getSqs() != null) {
            sb.append("sqs: " + getSqs() + ",");
        }
        if (getKinesis() != null) {
            sb.append("kinesis: " + getKinesis() + ",");
        }
        if (getRepublish() != null) {
            sb.append("republish: " + getRepublish() + ",");
        }
        if (getS3() != null) {
            sb.append("s3: " + getS3() + ",");
        }
        if (getFirehose() != null) {
            sb.append("firehose: " + getFirehose() + ",");
        }
        if (getCloudwatchMetric() != null) {
            sb.append("cloudwatchMetric: " + getCloudwatchMetric() + ",");
        }
        if (getCloudwatchAlarm() != null) {
            sb.append("cloudwatchAlarm: " + getCloudwatchAlarm() + ",");
        }
        if (getCloudwatchLogs() != null) {
            sb.append("cloudwatchLogs: " + getCloudwatchLogs() + ",");
        }
        if (getElasticsearch() != null) {
            sb.append("elasticsearch: " + getElasticsearch() + ",");
        }
        if (getSalesforce() != null) {
            sb.append("salesforce: " + getSalesforce() + ",");
        }
        if (getIotAnalytics() != null) {
            sb.append("iotAnalytics: " + getIotAnalytics() + ",");
        }
        if (getIotEvents() != null) {
            sb.append("iotEvents: " + getIotEvents() + ",");
        }
        if (getIotSiteWise() != null) {
            sb.append("iotSiteWise: " + getIotSiteWise() + ",");
        }
        if (getStepFunctions() != null) {
            sb.append("stepFunctions: " + getStepFunctions() + ",");
        }
        if (getTimestream() != null) {
            sb.append("timestream: " + getTimestream() + ",");
        }
        if (getHttp() != null) {
            sb.append("http: " + getHttp() + ",");
        }
        if (getKafka() != null) {
            sb.append("kafka: " + getKafka() + ",");
        }
        if (getOpenSearch() != null) {
            sb.append("openSearch: " + getOpenSearch() + ",");
        }
        if (getLocation() != null) {
            sb.append("location: " + getLocation());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((getDynamoDB() == null ? 0 : getDynamoDB().hashCode()) + 31) * 31) + (getDynamoDBv2() == null ? 0 : getDynamoDBv2().hashCode())) * 31) + (getLambda() == null ? 0 : getLambda().hashCode())) * 31) + (getSns() == null ? 0 : getSns().hashCode())) * 31) + (getSqs() == null ? 0 : getSqs().hashCode())) * 31) + (getKinesis() == null ? 0 : getKinesis().hashCode())) * 31) + (getRepublish() == null ? 0 : getRepublish().hashCode())) * 31) + (getS3() == null ? 0 : getS3().hashCode())) * 31) + (getFirehose() == null ? 0 : getFirehose().hashCode())) * 31) + (getCloudwatchMetric() == null ? 0 : getCloudwatchMetric().hashCode())) * 31) + (getCloudwatchAlarm() == null ? 0 : getCloudwatchAlarm().hashCode())) * 31) + (getCloudwatchLogs() == null ? 0 : getCloudwatchLogs().hashCode())) * 31) + (getElasticsearch() == null ? 0 : getElasticsearch().hashCode())) * 31) + (getSalesforce() == null ? 0 : getSalesforce().hashCode())) * 31) + (getIotAnalytics() == null ? 0 : getIotAnalytics().hashCode())) * 31) + (getIotEvents() == null ? 0 : getIotEvents().hashCode())) * 31) + (getIotSiteWise() == null ? 0 : getIotSiteWise().hashCode())) * 31) + (getStepFunctions() == null ? 0 : getStepFunctions().hashCode())) * 31) + (getTimestream() == null ? 0 : getTimestream().hashCode())) * 31) + (getHttp() == null ? 0 : getHttp().hashCode())) * 31) + (getKafka() == null ? 0 : getKafka().hashCode())) * 31) + (getOpenSearch() == null ? 0 : getOpenSearch().hashCode())) * 31) + (getLocation() != null ? getLocation().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Action)) {
            return false;
        }
        Action action = (Action) obj;
        if ((action.getDynamoDB() == null) ^ (getDynamoDB() == null)) {
            return false;
        }
        if (action.getDynamoDB() != null && !action.getDynamoDB().equals(getDynamoDB())) {
            return false;
        }
        if ((action.getDynamoDBv2() == null) ^ (getDynamoDBv2() == null)) {
            return false;
        }
        if (action.getDynamoDBv2() != null && !action.getDynamoDBv2().equals(getDynamoDBv2())) {
            return false;
        }
        if ((action.getLambda() == null) ^ (getLambda() == null)) {
            return false;
        }
        if (action.getLambda() != null && !action.getLambda().equals(getLambda())) {
            return false;
        }
        if ((action.getSns() == null) ^ (getSns() == null)) {
            return false;
        }
        if (action.getSns() != null && !action.getSns().equals(getSns())) {
            return false;
        }
        if ((action.getSqs() == null) ^ (getSqs() == null)) {
            return false;
        }
        if (action.getSqs() != null && !action.getSqs().equals(getSqs())) {
            return false;
        }
        if ((action.getKinesis() == null) ^ (getKinesis() == null)) {
            return false;
        }
        if (action.getKinesis() != null && !action.getKinesis().equals(getKinesis())) {
            return false;
        }
        if ((action.getRepublish() == null) ^ (getRepublish() == null)) {
            return false;
        }
        if (action.getRepublish() != null && !action.getRepublish().equals(getRepublish())) {
            return false;
        }
        if ((action.getS3() == null) ^ (getS3() == null)) {
            return false;
        }
        if (action.getS3() != null && !action.getS3().equals(getS3())) {
            return false;
        }
        if ((action.getFirehose() == null) ^ (getFirehose() == null)) {
            return false;
        }
        if (action.getFirehose() != null && !action.getFirehose().equals(getFirehose())) {
            return false;
        }
        if ((action.getCloudwatchMetric() == null) ^ (getCloudwatchMetric() == null)) {
            return false;
        }
        if (action.getCloudwatchMetric() != null && !action.getCloudwatchMetric().equals(getCloudwatchMetric())) {
            return false;
        }
        if ((action.getCloudwatchAlarm() == null) ^ (getCloudwatchAlarm() == null)) {
            return false;
        }
        if (action.getCloudwatchAlarm() != null && !action.getCloudwatchAlarm().equals(getCloudwatchAlarm())) {
            return false;
        }
        if ((action.getCloudwatchLogs() == null) ^ (getCloudwatchLogs() == null)) {
            return false;
        }
        if (action.getCloudwatchLogs() != null && !action.getCloudwatchLogs().equals(getCloudwatchLogs())) {
            return false;
        }
        if ((action.getElasticsearch() == null) ^ (getElasticsearch() == null)) {
            return false;
        }
        if (action.getElasticsearch() != null && !action.getElasticsearch().equals(getElasticsearch())) {
            return false;
        }
        if ((action.getSalesforce() == null) ^ (getSalesforce() == null)) {
            return false;
        }
        if (action.getSalesforce() != null && !action.getSalesforce().equals(getSalesforce())) {
            return false;
        }
        if ((action.getIotAnalytics() == null) ^ (getIotAnalytics() == null)) {
            return false;
        }
        if (action.getIotAnalytics() != null && !action.getIotAnalytics().equals(getIotAnalytics())) {
            return false;
        }
        if ((action.getIotEvents() == null) ^ (getIotEvents() == null)) {
            return false;
        }
        if (action.getIotEvents() != null && !action.getIotEvents().equals(getIotEvents())) {
            return false;
        }
        if ((action.getIotSiteWise() == null) ^ (getIotSiteWise() == null)) {
            return false;
        }
        if (action.getIotSiteWise() != null && !action.getIotSiteWise().equals(getIotSiteWise())) {
            return false;
        }
        if ((action.getStepFunctions() == null) ^ (getStepFunctions() == null)) {
            return false;
        }
        if (action.getStepFunctions() != null && !action.getStepFunctions().equals(getStepFunctions())) {
            return false;
        }
        if ((action.getTimestream() == null) ^ (getTimestream() == null)) {
            return false;
        }
        if (action.getTimestream() != null && !action.getTimestream().equals(getTimestream())) {
            return false;
        }
        if ((action.getHttp() == null) ^ (getHttp() == null)) {
            return false;
        }
        if (action.getHttp() != null && !action.getHttp().equals(getHttp())) {
            return false;
        }
        if ((action.getKafka() == null) ^ (getKafka() == null)) {
            return false;
        }
        if (action.getKafka() != null && !action.getKafka().equals(getKafka())) {
            return false;
        }
        if ((action.getOpenSearch() == null) ^ (getOpenSearch() == null)) {
            return false;
        }
        if (action.getOpenSearch() != null && !action.getOpenSearch().equals(getOpenSearch())) {
            return false;
        }
        if ((action.getLocation() == null) ^ (getLocation() == null)) {
            return false;
        }
        return action.getLocation() == null || action.getLocation().equals(getLocation());
    }
}
