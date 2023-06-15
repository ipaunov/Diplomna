package com.diploma.ivan.kafka;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.*;
import java.util.concurrent.ExecutionException;
public class TopicCreator {
    private static final int PARTITIONS = 5;
    private static final short REPLICATION = 1;

    public void createTopic(Properties properties, String topicName) {
        AdminClient admin = AdminClient.create(properties);
        if (!checkTopicExists(admin, topicName)) {
            createNewTopic(admin, topicName);
        }
    }

    private Set<String> getAllTopics(Admin admin) throws ExecutionException, InterruptedException {
        ListTopicsResult listTopics = admin.listTopics();
        return listTopics.names().get();
    }

    private boolean checkTopicExists(Admin admin, String topicName) {
        Set<String> names = null;
        try {
            names = getAllTopics(admin);
        } catch (InterruptedException | ExecutionException e) {
            //TODO throw custom exception
            throw new RuntimeException("Test");
        }
        return names.contains(topicName);
    }

    private void createNewTopic(Admin admin, String topicName) {
        List<NewTopic> topicList = new ArrayList<NewTopic>();
        NewTopic newTopic = new NewTopic(topicName, PARTITIONS, REPLICATION).configs(new HashMap<>());
        topicList.add(newTopic);
        admin.createTopics(topicList);
    }
}
