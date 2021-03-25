package com.example.demo;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.Date;

public class Elastic {

    public void test() throws IOException {
        ClientConfiguration clientConfiguration =
                ClientConfiguration.builder().connectedTo("localhost:9200").build();
        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("fullName", "Test")
                .field("dateOfBirth", new Date())
                .field("age", "10")
                .endObject();

        IndexRequest indexRequest = new IndexRequest("people");
        indexRequest.source(builder);

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        DocWriteResponse.Result result = response.getResult();
        String index = response.getIndex();
        long version = response.getVersion();

        System.out.println(result);
        System.out.println(index);
        System.out.println(version);
    }
}
