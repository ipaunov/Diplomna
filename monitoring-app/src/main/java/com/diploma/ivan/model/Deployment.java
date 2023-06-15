package com.diploma.ivan.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Deployment implements Serializable {


    private Metadata metadata;
    private Spec spec;
    private Status status;

    public Deployment() {
        
    }

    @JsonCreator
    public Deployment( //
            @JsonProperty("metadata") Metadata metadata, //
            @JsonProperty("spec") Spec spec, //
            @JsonProperty("status") Status status //
    ) {
        this.metadata = metadata;
        this.spec = spec;
        this.status = status;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("spec")
    public Spec getSpec() {
        return spec;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    private static class Metadata implements Serializable {

        private String name;
        private String namespace;

        public Metadata() {

        }

        @JsonCreator
        public Metadata( //
                @JsonProperty("name") String name, //
                @JsonProperty("namespace") String namespace //
        ) {
            this.name = name;
            this.namespace = namespace;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("namespace")
        public String getNamespace() {
            return namespace;
        }
    }

    private static class Spec implements Serializable {

        private int replicas;

        public Spec() {

        }

        @JsonCreator
        public Spec( //
                @JsonProperty("replicas") int replicas //
        ) {
            this.replicas = replicas;
        }

        @JsonProperty("replicas")
        public int getReplicas() {
            return replicas;
        }
    }

    private static class Status implements Serializable {
        private int replicas;
        private String selector;

        public Status() {

        }

        @JsonCreator
        public Status( //
                @JsonProperty("replicas") int replicas, //
                @JsonProperty("selector") String selector //
        ) {
            this.replicas = replicas;
            this.selector = selector;
        }

        @JsonProperty("replicas")
        public int getReplicas() {
            return replicas;
        }

        @JsonProperty("selector")
        public String getSelector() {
            return selector;
        }
    }
}
